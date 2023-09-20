package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.CreateTestChildDTO;
import com.watchworthy.api.dto.TestChildDTO;
import com.watchworthy.api.dto.UpdateChildDTO;
import com.watchworthy.api.entity.TestChild;
import com.watchworthy.api.entity.TestParent;

import com.watchworthy.api.repository.TestChildRepository;
import com.watchworthy.api.repository.TestParentRepository;
import com.watchworthy.api.service.TestChildService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestChildServiceImpl implements TestChildService {
    private final TestChildRepository testChildRepository;
    private final TestParentRepository testParentRepository;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    public TestChildServiceImpl (TestChildRepository testChildRepository, TestParentRepository testParentRepository, ModelMapper modelMapper){
        this.testChildRepository = testChildRepository;
        this.testParentRepository = testParentRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    @Transactional
    public TestChild createChildEntity(CreateTestChildDTO createTestChildDTO) {
        TestParent testParent = testParentRepository.findById(createTestChildDTO.getParent_id()).orElse(null);
        TestChild newTestChild = new TestChild();
        newTestChild.setName(createTestChildDTO.getName());
        if (testParent != null) {
            // Ensure the testParent entity is managed
            testParent = entityManager.merge(testParent);
            newTestChild.setTestParent(testParent);
        }

        return testChildRepository.save(newTestChild);
    }

    @Override
    public List<TestChildDTO> getAllChildEntities() {
        List<TestChild> testParentsList = testChildRepository.findAll();
        return testParentsList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public TestChild getChildEntityById(Long id) {
        return testChildRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateChildEntity(Long id,  UpdateChildDTO updatedChildDTO) {

        TestChild existingTestChild = testChildRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TestChild with ID " + id + " not found"));


        existingTestChild.setName(updatedChildDTO.getName());


        Long parentId = updatedChildDTO.getTestParentE_id();
        TestParent newParent = testParentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("TestParent with ID " + parentId + " not found"));


        existingTestChild.setTestParent(newParent);

        testChildRepository.save(existingTestChild);
        return true;
    }

    @Override
    public void deleteChildEntity(Long id) {
        testChildRepository.findById(id).ifPresent(testChild -> testChildRepository.deleteById(id));
    }

    // Helper method to get a child entity by ID and parent ID
    private TestChild getChildEntityByIdAndParentId(Long childId, Long parentId) {
        return testChildRepository.findByIdAndTestParent_Id(childId, parentId).orElse(null);
    }

    public TestChildDTO convertToDto(TestChild testChild) {
        var result = modelMapper.map(testChild, TestChildDTO.class);
        result.setTestParentE_id(testChild.getTestParent().getId());
        return result ;
    }


    @Override
    public List<TestChildDTO> filterChildEntitiesByName(String name) {
        List<TestChild> filteredEntities = testChildRepository.findByName(name);

        TestParent testParent = testParentRepository.findByName(name);

        if (testParent != null) {
            // Filter the TestChild entities further by TestParent name
            filteredEntities = filteredEntities.stream()
                    .filter(testChild -> testChild.getTestParent() != null &&
                            name.equals(testChild.getTestParent().getName()))
                    .collect(Collectors.toList());
        }

        // Convert the filtered entities to DTOs
        return filteredEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TestChildDTO> filterChildEntitiesByParentName(String parentName) {
        List<TestChild> filteredEntities;

        if (parentName != null) {
            filteredEntities = testChildRepository.findByTestParent_Name(parentName);
        } else {
            filteredEntities = testChildRepository.findAll();
        }

        // Convert the filtered entities to DTOs
        return filteredEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }





}
