package br.com.raph.demo.security;

import br.com.raph.demo.message.response.auth.CustomErrorBodyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Log4j2
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("CustomAuthenticationEntryPoint | commence | Unauthorized error: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        CustomErrorBodyResponse body = new CustomErrorBodyResponse();
        body.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        body.setError("Unauthorized");
        body.setMessage(authException.getMessage());
        body.setPath(request.getServletPath());

        mapper.writeValue(response.getOutputStream(), body);
    }
}
