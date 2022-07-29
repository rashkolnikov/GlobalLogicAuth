package com.example.globallogicauth.controller.auth.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Value
@AllArgsConstructor(onConstructor=@__(@JsonCreator))

public class SignUpRequest {

    String name;

    @Email(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}")
    @NonNull
    String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,12}$\n")
    @NonNull
    String password;

    List<PhoneRequest> phones;

}
