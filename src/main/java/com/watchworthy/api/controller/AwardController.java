package com.watchworthy.api.controller;

import com.watchworthy.api.dto.AwardDTO;
import com.watchworthy.api.service.AwardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/award")
@CrossOrigin(origins = "*")
public class AwardController {
    private final AwardService awardService;

    public AwardController(AwardService awardService) {
        this.awardService = awardService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAwardDetails(@PathVariable("id") Long id) {
        try {
            AwardDTO awardDTO = awardService.getAwardDetails(id);
            return ResponseEntity.ok(awardDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<AwardDTO> getAwards() {
        return awardService.getAwards();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveAward(@RequestBody AwardDTO awardDto) {
        awardService.saveAward(awardDto);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public AwardDTO updateAward(
            @PathVariable("id") Long id,
            @RequestBody AwardDTO awardDto) {
        return awardService.updateAward(id, awardDto);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteAward(@PathVariable("id") Long id) {
        awardService.deleteAward(id);
    }
}
