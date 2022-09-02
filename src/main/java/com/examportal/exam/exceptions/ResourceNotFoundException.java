package com.examportal.exam.exceptions;

import lombok.AllArgsConstructor;


public class ResourceNotFoundException extends RuntimeException {
    String property;
    String propertyName;
    String propertyValue;


    public ResourceNotFoundException(String property, String propertyName, String propertyValue) {
        super(String.format("%s is not found with the %s : %s",property,propertyName,propertyValue));
        this.property = property;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

}
