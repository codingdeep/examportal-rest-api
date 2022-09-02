package com.examportal.exam.exceptions;

public class ResourceExistingException extends RuntimeException {
    String property;
    String propertyName;
    String propertyValue;

    public ResourceExistingException(String property, String propertyName,String propertyValue){
        super(String.format("%s is already exist with %s: %s", property, propertyName, propertyValue));
        this.property = property;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }
}
