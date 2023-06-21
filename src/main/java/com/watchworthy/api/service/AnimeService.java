package com.watchworthy.api.service;


import com.watchworthy.api.dto.AnimeDTO;
import com.watchworthy.api.dto.ReplyDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnimeService {

    AnimeDTO getAnimeDetails(Long id);

    List<AnimeDTO> getAnimeList();

    void saveAnime(AnimeDTO animeDTO);

    AnimeDTO updateAnime(Long id, AnimeDTO animeDTO);

    void deleteAnime(Long id);

    boolean addReplyToAnime(Long userId, Long animeId, ReplyDTO replyDTO);

    boolean removeReply(Long replyId);

    boolean updateReply(Long replyId, String text);
}
