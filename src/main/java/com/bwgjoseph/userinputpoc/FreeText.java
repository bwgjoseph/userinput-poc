package com.bwgjoseph.userinputpoc;

import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
public class FreeText extends NonReference {
    public String name;
    public boolean angry;
}
