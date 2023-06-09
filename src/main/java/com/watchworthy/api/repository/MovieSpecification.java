package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Movie;

import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class MovieSpecification implements Specification<Movie> {

    private String searchString;
    private Integer genre;


    @Override
    public Predicate toPredicate(@NotNull Root<Movie> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder criteriaBuilder) {
        return filterByGenre(root,criteriaBuilder);
    }

    private Predicate filterByTitle(Root<Movie> root, CriteriaBuilder criteriaBuilder){
        String searchStringLower = searchString.toLowerCase();
        Expression<String> titleLower = criteriaBuilder.lower(root.get("title"));
        return criteriaBuilder.or(
                criteriaBuilder.like(titleLower, "%" + searchStringLower + "%")
        );
    }
    private Predicate filterByGenre(Root<Movie> root, CriteriaBuilder criteriaBuilder) {
        if (genre != null && genre.describeConstable().isPresent() && genre != 0){
            Join<Object, Object> genreJoin = root.join("genres");

            return criteriaBuilder.and(
                    filterByTitle(root, criteriaBuilder),
                    criteriaBuilder.equal(genreJoin.get("id"), genre)
            );
        } else {
            return filterByTitle(root, criteriaBuilder);
        }
    }
}
