package com.bwgjoseph.userinputpoc;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import lombok.ToString;

@JsonTypeInfo(use = Id.DEDUCTION)
@JsonSubTypes( {@Type(Selection.class), @Type(FreeText.class)})
@ToString
public abstract class NonReference implements UserInput {
}
