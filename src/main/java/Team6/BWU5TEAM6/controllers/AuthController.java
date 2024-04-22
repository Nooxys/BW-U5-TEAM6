package Team6.BWU5TEAM6.controllers;

import Team6.BWU5TEAM6.dto.NewUserDTO;
import Team6.BWU5TEAM6.dto.UserLoginDTO;
import Team6.BWU5TEAM6.dto.UserLoginResponseDTO;
import Team6.BWU5TEAM6.entities.User;
import Team6.BWU5TEAM6.exceptions.BadRequestException;
import Team6.BWU5TEAM6.services.AuthService;
import Team6.BWU5TEAM6.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService us;

    @Autowired
    private AuthService as;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginResponseDTO(this.as.authenticateUsersAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return us.save(body);
    }
}
