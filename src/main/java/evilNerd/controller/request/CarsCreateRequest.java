package evilNerd.controller.request;


import lombok.Data;

@Data
public class CarsCreateRequest {

    private Long id;

    private String model;

    private Integer creationYear;

    private Long userId;

    private Float price;

    private String color;



}
