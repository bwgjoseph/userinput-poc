package com.bwgjoseph.userinputpoc;

import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
public class Selection extends NonReference {
    public String name;
    public String causeOfDeath;
}
