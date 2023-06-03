package com.watchworthy.api.controller;

import com.watchworthy.api.service.TvShowRatesService;
import com.watchworthy.api.service.impl.TvShowRatesServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tvshowrates")
public class TvShowRatesController {
    private final TvShowRatesService tvShowRatesService;

    public TvShowRatesController (TvShowRatesService tvShowRatesService){
        this.tvShowRatesService = tvShowRatesService;
    }


    @RequestMapping(path = "/ratetvshow/{tvShowId}/{userId}",method = RequestMethod.POST)
    public ResponseEntity<Void> rateTvShow (@PathVariable Integer tvShowId, @PathVariable Long userId, @RequestParam Double rateNum){
        boolean result =  tvShowRatesService.rateTvShow(userId,tvShowId,rateNum);
        if(result){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @RequestMapping(path = "average-rating/{tvShowId}", method = RequestMethod.GET)
    public ResponseEntity<Double> calculateAverageRating(@PathVariable("tvShowId") Integer tvShowId) {
        double averageRating = tvShowRatesService.calculateTvShowRateNum(tvShowId);
        return ResponseEntity.ok(averageRating);
    }
    @RequestMapping(path = "getuserratenum/{tvShowId}/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Double> calculateAverageRating(@PathVariable("tvShowId") Integer tvShowId, @PathVariable("userId") Long userId) {
        double findRateNum = tvShowRatesService.findTvShowRate(userId, tvShowId);
        return ResponseEntity.ok(findRateNum);
    }

    @RequestMapping(path = "/removetvshowrate/{tvShowId}/{userId}",method = RequestMethod.POST)
    public ResponseEntity<Void> removeTvShowRate (@PathVariable Integer tvShowId, @PathVariable Long userId){
        boolean result =  tvShowRatesService.removeTvShowRate(userId,tvShowId);
        if(result){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
