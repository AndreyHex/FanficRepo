package com.fanficApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto {

    @JsonIgnore
    private Long id;
    private String username;

}
