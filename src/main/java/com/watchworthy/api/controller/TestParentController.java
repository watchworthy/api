package com.watchworthy.api.controller;

import com.watchworthy.api.dto.CreateTestParentDTO;
import com.watchworthy.api.dto.TestParentDTO;
import com.watchworthy.api.entity.TestParent;
import com.watchworthy.api.service.TestParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testparent")
@CrossOrigin(origins = "*")
public class TestParentController {
    private final TestParentService testParentService;
    @Autowired
    public TestParentController (TestParentService testParentService){
        this.testParentService = testParentService;
    }

    @PostMapping
    public ResponseEntity<Void> createParentEntity(@RequestBody CreateTestParentDTO createTestParentDTO) {
        TestParent createdEntity = testParentService.createTestParent(createTestParentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<TestParentDTO> getAllParentEntities() {
        return testParentService.getAllParentEntities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestParent> getParentEntityById(@PathVariable Long id) {
        TestParent entity = testParentService.getParentById(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestParent> updateParentEntity(
            @PathVariable Long id,
            @RequestBody TestParent updatedEntity
    ) {
        TestParent entity = testParentService.updateParent(id, updatedEntity);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParentEntity(@PathVariable Long id) {
        testParentService.deleteParent(id);
        return ResponseEntity.noContent().build();
    }
}
