package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Person;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class PersonSpecification implements Specification<Person> {
    private String searchString;
    @Override
    public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return filterByTitle(root,criteriaBuilder);
    }

    private Predicate filterByTitle(Root<Person> root, CriteriaBuilder criteriaBuilder){
        return criteriaBuilder.or(
                criteriaBuilder.like(root.get("title"),"%" + searchString + "%")
        );
    }
}
