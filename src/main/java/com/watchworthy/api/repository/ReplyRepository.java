package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Award;
import com.watchworthy.api.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
