package com.se7en.URLshortner.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MethordArgumentsNotValidException extends RuntimeException{

    String resourse_name;
    String field_name;
    long field_value;


    public MethordArgumentsNotValidException(String resourse_name, String field_name, long field_value) {
        super("%s not found with %s : %s".formatted(resourse_name, field_name, field_value));
        this.resourse_name = resourse_name;
        this.field_name = field_name;
        this.field_value = field_value;
    }



}

