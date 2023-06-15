package com.watchworthy.api.service.impl;

import com.watchworthy.api.entity.*;
import com.watchworthy.api.repository.*;
import com.watchworthy.api.service.CommentLikesService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentLikesServiceImpl implements CommentLikesService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentLikesRepository commentLikesRepository;
    private final CommentDissLikesRepository commentDissLikesRepository;
    private final NotificationRepository notificationRepository;

    public CommentLikesServiceImpl (CommentRepository commentRepository , UserRepository userRepository, CommentLikesRepository commentLikesRepository,CommentDissLikesRepository dissLikesRepository,NotificationRepository notificationRepository){
        this.commentRepository=commentRepository;
        this.userRepository = userRepository;
        this.commentLikesRepository = commentLikesRepository;
        this.commentDissLikesRepository = dissLikesRepository;
        this.notificationRepository=notificationRepository;
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

        Notifications notification = new Notifications();
        notification.setUserId(findComment.getUser().getId());
        notification.setMessage(findUser.getFirstName() +" "+ findUser.getLastName() + " liked your comment !");
        notification.setRead(false);
        notification.setDateTimeCreated(LocalDateTime.now());
        notificationRepository.save(notification);

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

        Notifications notification = new Notifications();
        notification.setUserId(findComment.getUser().getId());
        notification.setMessage(findUser.getFirstName() +" "+ findUser.getLastName() + " Diss liked your comment !");
        notification.setRead(false);
        notification.setDateTimeCreated(LocalDateTime.now());
        notificationRepository.save(notification);

        return true;
    }
    @Override
    public int countCommentDissLikes(Integer commentId) {
        return commentDissLikesRepository.countByCommentId(commentId);
    }
}
