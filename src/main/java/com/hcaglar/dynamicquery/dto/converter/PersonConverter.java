package com.hcaglar.dynamicquery.dto.converter;

import com.hcaglar.dynamicquery.dto.PersonDto;
import com.hcaglar.dynamicquery.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 16.05.2022
 */

@Mapper
public interface PersonConverter {

    PersonConverter INSTANCE = Mappers.getMapper(PersonConverter.class);

    Person convert(PersonDto personDto);
    PersonDto convert(Person person);

    List<PersonDto> convert(List<Person> persons);
    List<Person> converter(List<PersonDto> persons);

}
