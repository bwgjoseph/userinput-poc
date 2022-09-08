package com.bwgjoseph.userinputpoc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Data
public class Address {
    private String address;
    private UserInput ui;
}
