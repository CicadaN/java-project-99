package hexlet.code.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {

    @Email
    @NotBlank(groups = OnCreate.class)
    private String email;

    private String firstName;
    private String lastName;

    @NotBlank(groups = OnCreate.class)
    @Size(min = 3, groups = {OnCreate.class, OnUpdate.class})
    private String password;

    public interface OnCreate { }
    public interface OnUpdate { }
}
