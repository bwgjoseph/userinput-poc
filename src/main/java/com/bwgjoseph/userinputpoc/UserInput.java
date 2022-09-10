package com.bwgjoseph.userinputpoc;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
    @Type(value = Reference.class, name = "reference"),
    @Type(value = Selection.class, name = "selection"),
    @Type(value = FreeText.class, name = "freetext")
})
public interface UserInput {
    public String getValue();
}
