package com.watchworthy.api.service;

import org.springframework.stereotype.Service;

@Service
public interface CommentLikesService {
    boolean likeComment (Integer commentId, Long userId);
    int countCommentLikes (Integer commentId);
    boolean dissLikeComment (Integer commentId, Long userId);
    int countCommentDissLikes (Integer commentId);
}
