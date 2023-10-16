package br.com.raph.demo.message.response.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomErrorBodyResponse {
    private String path;
    private String error;
    private String message;
    private int status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();;

    public CustomErrorBodyResponse() {
    }
}
