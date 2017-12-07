package com.acebook.util;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Provides mapping function to Hibernate from Java 8 {@link #java.time.LocalDate} to {@link #java.sql.Date}.
 *
 * Details: https://www.thoughts-on-java.org/persist-localdate-localdatetime-jpa/
 *
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {
	
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
    	return (localDate == null ? null : Date.valueOf(localDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
    	return (sqlDate == null ? null : sqlDate.toLocalDate());
    }
}