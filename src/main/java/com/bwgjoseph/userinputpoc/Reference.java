package com.bwgjoseph.userinputpoc;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Reference implements UserInput {
    private String id;
    private String to;
}
