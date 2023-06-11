package com.watchworthy.api.service;

import com.watchworthy.api.dto.AwardDTO;

import java.util.List;

public interface AwardService {
    AwardDTO getAwardDetails(Long id);
    List<AwardDTO> getAwards();
    void saveAward(AwardDTO awardDto);
    AwardDTO updateAward(Long id, AwardDTO awardDto);
    void deleteAward(Long id);
}
