package com.watchworthy.api.controller;

import com.watchworthy.api.dto.CreateTestChildDTO;
import com.watchworthy.api.dto.TestChildDTO;
import com.watchworthy.api.dto.UpdateChildDTO;
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

    @PutMapping("/{id}")
    public boolean updateChildEntity(
            @PathVariable Long id, @RequestBody UpdateChildDTO updatedTestChildDTO
    ) {
        return testChildService.updateChildEntity(id, updatedTestChildDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChildEntity(
            @PathVariable Long id
    ) {
        testChildService.deleteChildEntity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<TestChildDTO>> filterChildEntitiesByName(
            @RequestParam(value = "name", required = false) String name
    ) {
        List<TestChildDTO> filteredEntities = testChildService.filterChildEntitiesByName(name);

        if (filteredEntities.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(filteredEntities);
        }
    }

    @GetMapping("/filterByParentName")
    public ResponseEntity<List<TestChildDTO>> filterChildEntitiesByParentName(
            @RequestParam(value = "parentName", required = false) String parentName
    ) {
        List<TestChildDTO> filteredEntities = testChildService.filterChildEntitiesByParentName(parentName);

        if (filteredEntities.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(filteredEntities);
        }
    }




}