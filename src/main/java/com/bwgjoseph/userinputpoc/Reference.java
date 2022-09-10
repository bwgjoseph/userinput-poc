package com.bwgjoseph.userinputpoc;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonTypeName("reference")
public class Reference implements UserInput {
    private String value;
    private String to;
}
