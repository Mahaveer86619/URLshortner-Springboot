package com.se7en.URLshortner.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFound  extends RuntimeException {

    String resource_name;
    String field_name;
    long field_value_int;
    String field_value_string;


    // if the field value is ID then it is Long
    public ResourceNotFound(String resource_name, String field_name, long field_value) {
        super("%s not found with %s : %s".formatted(resource_name, field_name, field_value));
        this.resource_name = resource_name;
        this.field_name = field_name;
        this.field_value_int = field_value;
    }

    // but if it is String like email then String
    public ResourceNotFound(String resource_name, String field_name, String field_value) {
        super("%s not found with %s : %s".formatted(resource_name, field_name, field_value));
        this.resource_name = resource_name;
        this.field_name = field_name;
        this.field_value_string = field_value;
    }
}
