package com.bwgjoseph.userinputpoc;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class UserInputTests {
    private final ObjectMapper objectMapper = JsonMapper.builder().build();

    @Test
    void shouldCastToUserInput_givenEmptyJson() throws JsonMappingException, JsonProcessingException {
        String json = formatJson("{}");

        assertThatExceptionOfType(InvalidTypeIdException.class)
            .isThrownBy(() -> objectMapper.readValue(json, UserInput.class));
    }

    @Test
    void shouldCastToReferenceClass_givenUniquePropertiesAssociatedWithReference() throws JsonMappingException, JsonProcessingException {
        String input = formatJson("{'type':'reference','value':'112233','to':'address'}");

        UserInput pojo = objectMapper.readValue(input, UserInput.class);

        System.out.println(pojo);
        assertTrue(pojo instanceof UserInput);
        assertTrue(pojo instanceof Reference);
        assertSame(pojo.getClass(), Reference.class);
        assertEquals("112233", pojo.getValue());
        assertEquals("address", ((Reference)pojo).getTo());
    }

    @Test
    void shouldCastToReferenceClass_givenUniquePropertiesAssociatedWithReferenc2() throws JsonMappingException, JsonProcessingException {
        String input = formatJson("{'type':'freetext','value':'112233'}");

        UserInput pojo = objectMapper.readValue(input, UserInput.class);

        System.out.println(pojo);
        assertTrue(pojo instanceof UserInput);
        assertTrue(pojo instanceof FreeText);
        assertSame(pojo.getClass(), FreeText.class);
        assertEquals("112233", pojo.getValue());
    }

    @Test
    void shouldCastToReferenceClass_givenUniquePropertiesAssociatedWithReferenc3() throws JsonMappingException, JsonProcessingException {
        String input = formatJson("{'type':'selection','value':'112233'}");

        UserInput pojo = objectMapper.readValue(input, UserInput.class);

        System.out.println(pojo);
        assertTrue(pojo instanceof UserInput);
        assertTrue(pojo instanceof Selection);
        assertSame(pojo.getClass(), Selection.class);
        assertEquals("112233", pojo.getValue());
    }

    @Test
    void shouldCastToFreeTextClass_givenBoxedReferenceClass() throws JsonMappingException, JsonProcessingException {
        String input = formatJson("{'address':'12 abc road','country':{'type':'reference','value':'reference','to':'hello'}}");

        Address pojo = objectMapper.readValue(input, Address.class);

        System.out.println(pojo);
        assertTrue(pojo instanceof Address);
        assertSame(pojo.getClass(), Address.class);
        assertTrue(pojo.getCountry() instanceof UserInput);
        assertTrue(pojo.getCountry() instanceof Reference);
        assertEquals("reference", ((Reference)pojo.getCountry()).getValue());
        assertEquals("hello", ((Reference)pojo.getCountry()).getTo());
    }

    @Test
    void shouldCastToFreeTextClass_givenBoxedReferenceClass2() throws JsonMappingException, JsonProcessingException {
        String input = formatJson("{'address':'12 abc road','country':{'type':'freetext','value':'freetext'}}");

        Address pojo = objectMapper.readValue(input, Address.class);

        System.out.println(pojo);
        assertTrue(pojo instanceof Address);
        assertSame(pojo.getClass(), Address.class);
        assertTrue(pojo.getCountry() instanceof UserInput);
        assertTrue(pojo.getCountry() instanceof FreeText);
        assertEquals("freetext", ((FreeText)pojo.getCountry()).getValue());
    }

    @Test
    void shouldCastToFreeTextClass_givenBoxedReferenceClass3() throws JsonMappingException, JsonProcessingException {
        String input = formatJson("{'address':'12 abc road','country':{'type':'selection','value':'selection'}}");

        Address pojo = objectMapper.readValue(input, Address.class);

        System.out.println(pojo);
        assertTrue(pojo instanceof Address);
        assertSame(pojo.getClass(), Address.class);
        assertTrue(pojo.getCountry() instanceof UserInput);
        assertTrue(pojo.getCountry() instanceof Selection);
        assertEquals("selection", ((Selection)pojo.getCountry()).getValue());
    }

    public static String formatJson(String input) {
        return input.replaceAll("'", "\"");
    }
}
