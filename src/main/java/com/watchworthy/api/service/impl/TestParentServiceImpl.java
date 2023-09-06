package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.CreateTestParentDTO;
import com.watchworthy.api.dto.TestParentDTO;
import com.watchworthy.api.entity.TestParent;
import com.watchworthy.api.repository.TestParentRepository;
import com.watchworthy.api.service.TestParentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestParentServiceImpl implements TestParentService {

    private final TestParentRepository parentRepository;
    private final ModelMapper modelMapper;
    public TestParentServiceImpl (TestParentRepository parentRepository, ModelMapper modelMapper){
        this.parentRepository = parentRepository;

        this.modelMapper = modelMapper;
    }
    @Override
    public TestParent createTestParent(CreateTestParentDTO createTestParentDTO) {
        TestParent testParent = new TestParent();
        testParent.setName((createTestParentDTO.getName()));
        return parentRepository.save(testParent);
    }

    @Override
    public List<TestParentDTO> getAllParentEntities() {
        List<TestParent> testParentsList = parentRepository.findAll();
        return testParentsList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public TestParent getParentById(Long id) {
        return parentRepository.findById(id).orElse(null);
    }

    @Override
    public TestParent updateParent(Long id, TestParent testParent) {
        TestParent existingParent = getParentById(id);
        existingParent.setName(testParent.getName());

        return parentRepository.save(existingParent);
    }

    @Override
    public void deleteParent(Long id) {
        TestParent existingParent  = getParentById(id);
        parentRepository.delete(existingParent);
    }
    public TestParentDTO convertToDto(TestParent testParent) {
        return modelMapper.map(testParent, TestParentDTO.class);
    }

}
