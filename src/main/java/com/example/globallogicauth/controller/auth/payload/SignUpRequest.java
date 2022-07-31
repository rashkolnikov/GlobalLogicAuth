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

    @Email(message = "The email isn't valid", regexp = "[a-z0-9]+[@][a-z]+\\.[a-z]{2,3}")
    @NonNull
    String email;

    //^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,12}$    "" + "AAqweqe"
    @Pattern(message = "The password isn't valid", regexp = "^[^A-Z]*[A-Z][^A-Z]*$&^[^0-9]*[0-9][^0-9]*[0-9][^0-9]*$&^[a-zA-Z0-9]{8,12}")
    @NonNull
    String password;

    List<PhoneRequest> phones;

}
