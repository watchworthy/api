package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.CreateTestChildDTO;
import com.watchworthy.api.dto.TestChildDTO;
import com.watchworthy.api.entity.TestChild;
import com.watchworthy.api.entity.TestParent;

import com.watchworthy.api.repository.TestChildRepository;
import com.watchworthy.api.repository.TestParentRepository;
import com.watchworthy.api.service.TestChildService;
import jakarta.persistence.EntityManager;
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
    public TestChildDTO updateChildEntity(Long parentId, Long childId, TestChildDTO updatedChildDTO) {
        // Retrieve the parent entity by its ID
        System.out.println("une e dua jeten");
        TestParent testParent = testParentRepository.findById(updatedChildDTO.getTestParentE_id())
                .orElseThrow();

        // Retrieve the existing child entity by its ID and parent ID
        TestChild existingEntity = getChildEntityByIdAndParentId(childId, parentId);

        // Check if the child exists
        if (existingEntity == null) {
            System.out.println("Child with ID " + childId + " not found under parent with ID " + parentId);
        }

        // Update the existing entity with the new data
        existingEntity.setName(updatedChildDTO.getName());

        // Set the parent for the child
        existingEntity.setTestParent(testParent);

        // Save the updated entity
        existingEntity = testChildRepository.save(existingEntity);

        // Convert the updated entity to DTO and return it
        return convertToDto(existingEntity);
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
}
