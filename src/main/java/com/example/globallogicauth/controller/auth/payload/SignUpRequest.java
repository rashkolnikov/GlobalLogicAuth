package com.example.globallogicauth.controller.auth.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Value
@AllArgsConstructor(onConstructor=@__(@JsonCreator))
public class SignUpRequest {

    String name;

    @NonNull
    @Email(regexp = "[A-Z0-9._]+@[A-Z0-9.-]+\\.[A-Z]{2,4}")
    String email;

    @NonNull
    @Pattern(regexp = "^(?=[^A-Z]*[A-Z][^A-Z]*$)(?=.*[0-9][^0-9]*[0-9]|.*[0-9][0-9]*)[a-zA-Z0-9]{8,12}$")
    String password;

    List<PhoneRequest> phones;



}
