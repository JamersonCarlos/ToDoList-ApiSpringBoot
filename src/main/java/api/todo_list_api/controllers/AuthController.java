package api.todo_list_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.todo_list_api.VO.AccountCredentialsVO;
import api.todo_list_api.VO.RegisterUserVO;
import api.todo_list_api.VO.UserVO;
import api.todo_list_api.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Endpoint")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity loginMethod(@RequestBody AccountCredentialsVO credentials) {
        if (credentials == null || credentials.getUsername() == null || credentials.getUsername().isBlank()
                || credentials.getPassword() == null || credentials.getPassword().isBlank())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        var token = authService.login(credentials);
        if (token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        return token;
    }

    @PostMapping("/register")
    public ResponseEntity<UserVO> createUserMethod(@RequestBody RegisterUserVO user) throws Exception {
        return new ResponseEntity<>(authService.register(user), HttpStatus.CREATED);
    }

}
