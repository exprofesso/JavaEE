package evilNerd.controller.request;

import lombok.Data;

@Data
public class UserChangeRequest extends UserCreateRequest{
    private Long id;
}
