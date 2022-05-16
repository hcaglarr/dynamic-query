package com.hcaglar.dynamicquery.service;

import com.hcaglar.dynamicquery.dto.PersonDto;
import com.hcaglar.dynamicquery.dto.converter.PersonConverter;
import com.hcaglar.dynamicquery.entity.Person;
import com.hcaglar.dynamicquery.repository.PersonRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Person findByPersonId(UUID id){
        UUID personId = Optional
                .ofNullable(id)
                .orElseThrow(() -> new UnsupportedOperationException("Check the id."));

        return personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("The specified %s ids could not be found.", personId)));
    }

    public void save(List<PersonDto> persons) {
        List<Person> newPeople = PersonConverter.INSTANCE.converter(persons);
        personRepository.saveAll(newPeople);
    }

    public List<PersonDto> dynamicPersonQuery(String firstName, String lastName, Integer age){
        List<Person> persons = personRepository.findAll((Specification<Person>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<Predicate>();
            if (firstName != null && !firstName.isEmpty())
                list.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("firstName"), firstName)));
            if (lastName != null && !lastName.isEmpty())
                list.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("lastName"), lastName)));
            if (age != null && age > 0)
                list.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("age"), age)));

            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        });

        return PersonConverter.INSTANCE.convert(persons);
    }
}
