package com.watchworthy.api.service;

import com.watchworthy.api.dto.CreateTestChildDTO;
import com.watchworthy.api.dto.TestChildDTO;
import com.watchworthy.api.entity.TestChild;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestChildService {
    TestChild createChildEntity(CreateTestChildDTO createTestChildDTO);
    List<TestChildDTO> getAllChildEntities();
    TestChild getChildEntityById(Long id);
    TestChild updateChildEntity(Long id, TestChild testChild);
    void deleteChildEntity(Long id);
}
