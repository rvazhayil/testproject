package com.xyzcorp.testproject.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class UserDto {

    @CsvBindByName
    private String name;

    @CsvBindByName
    private Float salary;

    public UserDto(String name, Float salary) {
        this.name = name;
        this.salary = salary;
    }

    public UserDto(){}
}
