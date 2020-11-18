package evilNerd.controller.request;

import evilNerd.domain.Gender;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
public class UserCreateRequest {

    private String name;

    private String surname;

    private Date birthDate;

    private Gender gender = Gender.NOT_SELECTED;

    private Float weight;


}
