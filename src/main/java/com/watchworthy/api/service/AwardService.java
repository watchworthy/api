package com.watchworthy.api.service;

import com.watchworthy.api.dto.AwardDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AwardService {
    AwardDTO getAwardDetails(Long id);
    List<AwardDTO> getAwards();
    void saveAward(AwardDTO awardDto);
    AwardDTO updateAward(Long id, AwardDTO awardDto);
    void deleteAward(Long id);
}
