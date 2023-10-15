package br.com.raph.demo.service.auth;

import br.com.raph.demo.security.jwt.JwtService;
import br.com.raph.demo.exception.CustomEntityNotFoundException;
import br.com.raph.demo.message.request.auth.AuthenticationRequest;
import br.com.raph.demo.message.request.auth.RegisterRequest;
import br.com.raph.demo.message.response.auth.AuthenticationResponse;
import br.com.raph.demo.model.Role;
import br.com.raph.demo.model.User;
import br.com.raph.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = new User();
        user.setFullName(registerRequest.getFullName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.ROLE_USER);

        userRepository.save(user);
        var token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        var user = userRepository.findByEmail(authenticationRequest.getEmail()).
                orElseThrow(CustomEntityNotFoundException::new);

        var token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
