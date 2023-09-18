package com.watchworthy.api.controller;

import com.watchworthy.api.dto.CreateTestChildDTO;
import com.watchworthy.api.dto.TestChildDTO;
import com.watchworthy.api.entity.TestChild;
import com.watchworthy.api.service.TestChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testchild")
@CrossOrigin(origins = "*")
public class TestChildController {
    private final TestChildService testChildService;
    @Autowired
    public TestChildController(TestChildService testChildService){
        this.testChildService = testChildService;
    }
    @PostMapping
    public ResponseEntity<Void> createChildEntity(
            @RequestBody CreateTestChildDTO createTestChildDTO
    ) {
        TestChild createdEntity = testChildService.createChildEntity(createTestChildDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<TestChildDTO> getAllChildEntities() {
        return testChildService.getAllChildEntities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestChild> getChildEntityById(@PathVariable Long id) {
        TestChild entity = testChildService.getChildEntityById(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PutMapping("/{parentId}/{childId}")
    public ResponseEntity<TestChildDTO> updateChildEntity(
            @PathVariable Long parentId,
            @PathVariable Long childId,
            @RequestBody TestChildDTO updatedChildDTO
    ) {
        TestChildDTO entity = testChildService.updateChildEntity(parentId, childId, updatedChildDTO);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }


    @DeleteMapping("/{parentId}/{childId}")
    public ResponseEntity<Void> deleteChildEntity(
            @PathVariable Long parentId,
            @PathVariable Long childId
    ) {
        testChildService.deleteChildEntity(parentId, childId);
        return ResponseEntity.noContent().build();
    }

}
