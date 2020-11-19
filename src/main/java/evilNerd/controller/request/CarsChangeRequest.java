package evilNerd.controller.request;

import lombok.Data;

@Data
public class CarsChangeRequest extends CarsCreateRequest{
    private Long id;
}
