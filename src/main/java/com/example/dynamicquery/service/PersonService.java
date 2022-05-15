package com.example.dynamicquery.service;

import com.example.dynamicquery.entity.Person;
import com.example.dynamicquery.repository.PersonRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.net.URI;
import java.util.*;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 15.05.2022
 */
@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findByPersonId(Person person){
        return findByPersonId(person.getId());
    }

    public Person findByPersonId(UUID id){
        final var personId = notNull(id,"Check the id.");

        return personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("The specified %s ids could not be found.", personId)));
    }

    public URI save(Person person){
        final var newPerson = personRepository.save(notNull(person,"Check the id."));
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/persons/" + newPerson.getId())
                .build()
                .toUri();
    }

    public List<Person> dynamicPersonQuery(String firstName, String lastName, Integer age){
        return personRepository.findAll((Specification<Person>) (root, query, criteriaBuilder) -> {
            final var list = new ArrayList<Predicate>();
            if (firstName != null)
                list.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("firstName"), firstName)));
            if (lastName != null)
                list.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("lastName"), lastName)));
            if (age != null)
                list.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("age"), age)));

            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        });
    }


    private Person notNull(Person person, String text){
        return  Optional
                .ofNullable(person)
                .orElseThrow(() -> new UnsupportedOperationException(text));
    }

    private UUID notNull(UUID id, String text){
        return  Optional
                .ofNullable(id)
                .orElseThrow(() -> new UnsupportedOperationException(text));
    }
}
