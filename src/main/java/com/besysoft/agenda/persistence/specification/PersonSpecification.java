package com.besysoft.agenda.persistence.specification;

import com.besysoft.agenda.persistence.domain.Person;
import com.besysoft.agenda.presentation.dto.PersonFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PersonSpecification implements Specification<Person> {

    private final PersonFilter personFilter;
    public PersonSpecification(PersonFilter quizFilter){
        this.personFilter = quizFilter;
    }
    @Override
    public Predicate toPredicate(Root<Person> person, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> restrictions = new ArrayList<>();
        if(personFilter.getName() != null && !personFilter.getName().isEmpty()){
            restrictions.add(criteriaBuilder.equal(person.get("name"), personFilter.getName()));
        }
        if((personFilter.getAgeFrom()!= null) && (personFilter.getAgeTo() != null)){
            restrictions.add(criteriaBuilder.between(person.get("age"), personFilter.getAgeFrom(), personFilter.getAgeTo()));
        }else{
            if (personFilter.getAgeFrom() != null) {
                restrictions.add(criteriaBuilder.greaterThanOrEqualTo(person.get("age"), personFilter.getAgeFrom()));
            }
            if (personFilter.getAgeTo() != null) {
            restrictions.add(criteriaBuilder.lessThanOrEqualTo(person.get("age"), personFilter.getAgeTo()));
            }
        }
        if(personFilter.getCities() != null){  //generates Predicates for persons in city
            for(Long id:personFilter.getCities()){
                       restrictions.add(criteriaBuilder.equal(person.get("cityId"), id));
            }
        }
        return criteriaBuilder.and(restrictions.toArray(new Predicate[0]));
    }
}
