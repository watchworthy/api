package com.watchworthy.api.repository;

import com.watchworthy.api.entity.TvShow;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class TvShowSpecification implements Specification<TvShow> {
    private String searchString;
    private String genre;
    @Override
    public Predicate toPredicate(@NotNull Root<TvShow> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder criteriaBuilder) {
        return filterByGenre(root,criteriaBuilder);
    }

    private Predicate filterByTitle(Root<TvShow> root, CriteriaBuilder criteriaBuilder){
        return criteriaBuilder.or(
                criteriaBuilder.like(root.get("title"),"%" + searchString + "%")
        );
    }
    private Predicate filterByGenre(Root<TvShow> root, CriteriaBuilder criteriaBuilder) {
        if (genre != null && !genre.isEmpty()) {
            Join<Object, Object> genreJoin = root.join("genres");

            return criteriaBuilder.and(
                    filterByTitle(root, criteriaBuilder),
                    criteriaBuilder.equal(genreJoin.get("name"), genre)
            );
        } else {
            return filterByTitle(root, criteriaBuilder);
        }
    }
}
