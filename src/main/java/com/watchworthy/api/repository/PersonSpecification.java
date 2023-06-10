package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Person;
import com.watchworthy.api.entity.TvShow;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class PersonSpecification implements Specification<Person> {
    private String searchString;
    @Override
    public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return filterByName(root,criteriaBuilder);
    }

    private Predicate filterByName(Root<Person> root, CriteriaBuilder criteriaBuilder){
        String searchStringToLower = searchString.toLowerCase();
        Expression<String> nameToLower = criteriaBuilder.lower(root.get("name"));
        return criteriaBuilder.or(
                criteriaBuilder.like(nameToLower,"%" + searchStringToLower + "%")
        );
    }
}
