package com.bwgjoseph.userinputpoc;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonTypeName("selection")
public class Selection implements UserInput {
    public String value;
}
