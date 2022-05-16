package com.hcaglar.dynamicquery.bootstrap;

import com.hcaglar.dynamicquery.dto.PersonDto;
import com.hcaglar.dynamicquery.entity.Person;
import com.hcaglar.dynamicquery.service.PersonService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 16.05.2022
 */

@Component
public class PersonBootstrap {

    private final PersonService personService;

    public PersonBootstrap(PersonService personService) {
        this.personService = personService;
    }

    @PostConstruct
    private void init() {
        List<PersonDto> people = new ArrayList<>();
        people.add(new PersonDto("Ken", "Thompson", 79));
        people.add(new PersonDto("Dennis", "Ritchie", 70));
        people.add(new PersonDto("Alan", "Turing", 42));
        people.add(new PersonDto("Tim", "Berners-Lee", 66));
        people.add(new PersonDto("Grace", "Hopper", 86));
        people.add(new PersonDto("Ken1", "Thompson", 79));
        people.add(new PersonDto("Dennis1", "Ritchie", 70));
        people.add(new PersonDto("Ken", "Thompson1", 79));
        people.add(new PersonDto("Dennis", "Ritchie1", 70));

        personService.save(people);
    }
}
