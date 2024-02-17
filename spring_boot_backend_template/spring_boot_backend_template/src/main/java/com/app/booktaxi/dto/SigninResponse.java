package com.app.booktaxi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SigninResponse {

    private String jwt;
    private Object mesg;

    public SigninResponse(String jwt, Object responseDto) {
        this.jwt = jwt;
        this.mesg = responseDto;
    }
}

