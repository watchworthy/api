package com.watchworthy.api.controller;

import com.watchworthy.api.service.CommentLikesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commentlikes")
@CrossOrigin(origins = "*")
public class CommentLikesController {
    private final CommentLikesService commentLikesService;


    public CommentLikesController(CommentLikesService commentLikesService) {
        this.commentLikesService = commentLikesService;
    }

    @RequestMapping(path = "/likeComment/{commentId}/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Void> likeComment(@PathVariable Integer commentId, @PathVariable Long userId) {
        boolean result = commentLikesService.likeComment(commentId,userId);
        if(result){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @RequestMapping(path= "/countCommentLikes/{commentId}", method = RequestMethod.GET)
    public int countCommentLikes (@PathVariable Integer commentId){
        return commentLikesService.countCommentLikes(commentId);
    }
    @RequestMapping(path = "/dissLikeComment/{commentId}/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Void> dissLikeComment(@PathVariable Integer commentId, @PathVariable Long userId) {
        boolean result = commentLikesService.dissLikeComment(commentId,userId);
        if(result){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @RequestMapping(path= "/countCommentDissLikes/{commentId}", method = RequestMethod.GET)
    public int countCommentDissLikes (@PathVariable Integer commentId){
        return commentLikesService.countCommentDissLikes(commentId);
    }
}
