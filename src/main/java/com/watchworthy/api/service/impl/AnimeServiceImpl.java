package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.AnimeDTO;
import com.watchworthy.api.dto.ReplyDTO;
import com.watchworthy.api.entity.Anime;
import com.watchworthy.api.entity.Reply;
import com.watchworthy.api.repository.AnimeRepository;
import com.watchworthy.api.repository.ReplyRepository;
import com.watchworthy.api.service.AnimeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimeServiceImpl implements AnimeService {

    private final AnimeRepository animeRepository;
    private final ReplyRepository replyRepository;

    public AnimeServiceImpl(AnimeRepository animeRepository, ReplyRepository replyRepository) {
        this.animeRepository = animeRepository;
        this.replyRepository = replyRepository;
    }

    @Override
    public AnimeDTO getAnimeDetails(Long id) {
        Optional<Anime> animeOptional = animeRepository.findById(id);
        if (animeOptional.isPresent()) {
            Anime anime = animeOptional.get();
            return mapAnimeToDTO(anime);
        } else {
            throw new NoSuchElementException("Anime not found");
        }
    }

    @Override
    public List<AnimeDTO> getAnimeList() {
        List<Anime> animeList = animeRepository.findAll();
        return animeList.stream()
                .map(this::mapAnimeToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void saveAnime(AnimeDTO animeDTO) {
        Anime anime = mapDTOToAnime(animeDTO);
        animeRepository.save(anime);
    }

    @Override
    public AnimeDTO updateAnime(Long id, AnimeDTO animeDTO) {
        Optional<Anime> animeOptional = animeRepository.findById(id);
        if (animeOptional.isPresent()) {
            Anime anime = animeOptional.get();
            anime.setName(animeDTO.getName());
            anime.setCategory(animeDTO.getCategory());
            animeRepository.save(anime);
            return mapAnimeToDTO(anime);
        } else {
            throw new NoSuchElementException("Anime not found");
        }
    }

    @Override
    public void deleteAnime(Long id) {
        animeRepository.deleteById(id);
    }

    @Override
    public boolean addReplyToAnime(Long userId, Long animeId, ReplyDTO replyDTO) {
        Optional<Anime> animeOptional = animeRepository.findById(animeId);
        if (animeOptional.isPresent()) {
            Anime anime = animeOptional.get();
            Reply reply = new Reply();
            reply.setText(replyDTO.getText());
            // Set the anime and user references accordingly

            anime.getReplies().add(reply);
            animeRepository.save(anime);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeReply(Long replyId) {
        Optional<Reply> replyOptional = replyRepository.findById(replyId);
        if (replyOptional.isPresent()) {
            replyRepository.delete(replyOptional.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateReply(Long replyId, String text) {
        Optional<Reply> replyOptional = replyRepository.findById(replyId);
        if (replyOptional.isPresent()) {
            Reply reply = replyOptional.get();
            reply.setText(text);
            replyRepository.save(reply);
            return true;
        }
        return false;
    }

    private AnimeDTO mapAnimeToDTO(Anime anime) {
        return AnimeDTO.builder()
                .id(anime.getId())
                .name(anime.getName())
                .category(anime.getCategory())
                .build();
    }

    private Anime mapDTOToAnime(AnimeDTO animeDTO) {
        Anime anime = new Anime();
        anime.setName(animeDTO.getName());
        anime.setCategory(animeDTO.getCategory());
        return anime;
    }
}
