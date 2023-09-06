package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.CreateTestChildDTO;
import com.watchworthy.api.dto.TestChildDTO;
import com.watchworthy.api.entity.TestChild;
import com.watchworthy.api.entity.TestParent;
import com.watchworthy.api.repository.TestChildRepository;
import com.watchworthy.api.repository.TestParentRepository;
import com.watchworthy.api.service.TestChildService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestChildServiceImpl implements TestChildService {
    private final TestChildRepository testChildRepository;
    private final TestParentRepository testParentRepository;
    private final ModelMapper modelMapper;

    public TestChildServiceImpl (TestChildRepository testChildRepository, TestParentRepository testParentRepository, ModelMapper modelMapper){
        this.testChildRepository = testChildRepository;
        this.testParentRepository = testParentRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public TestChild createChildEntity(CreateTestChildDTO createTestChildDTO) {
        TestParent testParent = testParentRepository.findById(createTestChildDTO.getParent_id()).orElse(null);
        TestChild newTestChild = new TestChild();
        newTestChild.setName(createTestChildDTO.getName());
        newTestChild.setTestParent(testParent);
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
    public TestChild updateChildEntity(Long id, TestChild testChild) {
        TestChild existingEntity = getChildEntityById(id);
        existingEntity.setName(testChild.getName());
        return testChildRepository.save(existingEntity);
    }

    @Override
    public void deleteChildEntity(Long id) {
        testChildRepository.findById(id).ifPresent(testChild -> testChildRepository.deleteById(id));
    }
    public TestChildDTO convertToDto(TestChild testChild) {
        return modelMapper.map(testChild, TestChildDTO.class);
    }
}
