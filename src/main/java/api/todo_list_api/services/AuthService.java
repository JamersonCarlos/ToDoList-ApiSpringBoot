package api.todo_list_api.services;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.stereotype.Service;

import api.todo_list_api.Exceptions.EmailAlreadyExistsException;
import api.todo_list_api.Exceptions.PasswordLengthInvalidException;
import api.todo_list_api.VO.AccountCredentialsVO;
import api.todo_list_api.VO.RegisterUserVO;
import api.todo_list_api.VO.TokenVO;
import api.todo_list_api.VO.UserVO;
import api.todo_list_api.mapper.Mapper;
import api.todo_list_api.models.UserModel;
import api.todo_list_api.repository.UserRepository;
import api.todo_list_api.security.jwt.JwtTokenProvider;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    public ResponseEntity login(AccountCredentialsVO data) {
        try {

            var username = data.getUsername();
            var password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            var user = repository.findByUsername(username);
            var tokenResponse = new TokenVO();
            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username);
            } else {
                throw new UsernameNotFoundException("username: " + username + "not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    public UserVO register(RegisterUserVO user) throws Exception {
        if (user.getUsername() == null || user.getFullName() == null || user.getPassword() == null) {
            throw new IllegalAccessException("Email, full name, and password must be provided");
        }
        if (repository.findByUsername(user.getUsername()) != null) {
            throw new EmailAlreadyExistsException(" Email address already in use! ");
        }
        if (user.getPassword().length() <= 8) {
            throw new PasswordLengthInvalidException("Password must contain at least 8 characters");
        }

        Map<String, PasswordEncoder> encoders = new HashMap();
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder("", 8, 185000,
                SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
        encoders.put("pbkdf2", pbkdf2PasswordEncoder);
        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2PasswordEncoder);
        user.setPassword(delegatingPasswordEncoder.encode(user.getPassword()).substring("{pbkdf2}".length()));
        UserModel entity = Mapper.parseObject(user, UserModel.class);
        System.out.println(entity.getPassword());
        return Mapper.parseObject(repository.save(entity), UserVO.class);
    }
}
