package com.watchworthy.api.repository;

import com.watchworthy.api.entity.CommentDissLikes;
import com.watchworthy.api.entity.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDissLikesRepository extends JpaRepository<CommentDissLikes, Integer> {
    CommentDissLikes findByCommentIdAndUserId(Integer commentId, Long userId);
    int countByCommentId(Integer commentId);
}
