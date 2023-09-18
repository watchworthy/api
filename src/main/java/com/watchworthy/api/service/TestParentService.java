package com.watchworthy.api.service;

import com.watchworthy.api.dto.CreateTestParentDTO;
import com.watchworthy.api.dto.TestParentDTO;
import com.watchworthy.api.entity.TestParent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestParentService {
    TestParent createTestParent(CreateTestParentDTO createTestParentDTO);
    List<TestParentDTO> getAllParentEntities();
    TestParent getParentById(Long id);
    TestParent updateParent(Long id, TestParent testParent);
    void deleteParent(Long id);
}
