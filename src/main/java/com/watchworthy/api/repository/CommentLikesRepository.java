package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Comment;
import com.watchworthy.api.entity.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Integer> {
    CommentLikes findByCommentIdAndUserId(Integer commentId, Long userId);
    int countByCommentId(Integer commentId);
}
