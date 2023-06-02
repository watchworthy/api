package com.watchworthy.api.service.impl;

import com.watchworthy.api.entity.Comment;
import com.watchworthy.api.entity.CommentDissLikes;
import com.watchworthy.api.entity.CommentLikes;
import com.watchworthy.api.entity.User;
import com.watchworthy.api.repository.CommentDissLikesRepository;
import com.watchworthy.api.repository.CommentLikesRepository;
import com.watchworthy.api.repository.CommentRepository;
import com.watchworthy.api.repository.UserRepository;
import com.watchworthy.api.service.CommentLikesService;
import org.springframework.stereotype.Service;

@Service
public class CommentLikesServiceImpl implements CommentLikesService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentLikesRepository commentLikesRepository;
    private final CommentDissLikesRepository commentDissLikesRepository;

    public CommentLikesServiceImpl (CommentRepository commentRepository , UserRepository userRepository, CommentLikesRepository commentLikesRepository,CommentDissLikesRepository dissLikesRepository){
        this.commentRepository=commentRepository;
        this.userRepository = userRepository;
        this.commentLikesRepository = commentLikesRepository;
        this.commentDissLikesRepository = dissLikesRepository;
    }
    @Override
    public boolean likeComment(Integer commentId, Long userId) {
        Comment findComment = commentRepository.findById(commentId).orElse(null);
        User findUser = userRepository.findById(userId).orElse(null);
        if(findComment == null || findUser == null){
            return false;
        }
        CommentLikes commentLikeExists = commentLikesRepository.findByCommentIdAndUserId(commentId, userId);
        if(commentLikeExists != null ){
            commentLikesRepository.delete(commentLikeExists);
            return true;
        }
        CommentLikes commentLikes = new CommentLikes();
        commentLikes.setCommentId(commentId);
        commentLikes.setUserId(userId);

        commentLikesRepository.save(commentLikes);
        return true;
    }

    @Override
    public int countCommentLikes(Integer commentId) {
        return commentLikesRepository.countByCommentId(commentId);
    }

    @Override
    public boolean dissLikeComment(Integer commentId, Long userId) {
        Comment findComment = commentRepository.findById(commentId).orElse(null);
        User findUser = userRepository.findById(userId).orElse(null);
        if(findComment == null || findUser == null){
            return false;
        }
        CommentDissLikes commentDissLikesExists = commentDissLikesRepository.findByCommentIdAndUserId(commentId,userId);
        if(commentDissLikesExists != null ){
            commentDissLikesRepository.delete(commentDissLikesExists);
            return true;
        }
        CommentDissLikes commentDissLikes = new CommentDissLikes();
        commentDissLikes.setCommentId(commentId);
        commentDissLikes.setUserId(userId);

        commentDissLikesRepository.save(commentDissLikes);
        return true;
    }
    @Override
    public int countCommentDissLikes(Integer commentId) {
        return commentDissLikesRepository.countByCommentId(commentId);
    }
}
