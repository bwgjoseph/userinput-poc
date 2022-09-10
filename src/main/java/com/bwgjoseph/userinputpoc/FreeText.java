package com.bwgjoseph.userinputpoc;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonTypeName("freetext")
public class FreeText implements UserInput {
    public String value;
}
