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

class UserInputTests {
    private final ObjectMapper objectMapper = JsonMapper.builder().build();

    @Test
    void shouldCastToUserInput_givenEmptyJson() throws JsonMappingException, JsonProcessingException {
        String json = formatJson("{}");

        assertThatExceptionOfType(InvalidTypeIdException.class)
            .isThrownBy(() -> objectMapper.readValue(json, UserInput.class));
    }

    @Test
    void shouldCastToReferenceClass_givenUniquePropertiesAssociatedWithReference() throws JsonMappingException, JsonProcessingException {
        String input = formatJson("{'id':'112233','to':'address'}");

        Reference pojo = objectMapper.readValue(input, Reference.class);

        System.out.println(pojo);
        assertTrue(pojo instanceof Reference);
        assertSame(pojo.getClass(), Reference.class);
        assertEquals("112233", pojo.getId());
        assertEquals("address", pojo.getTo());
    }

    @Test
    void shouldCastToSelectionClass_givenUniquePropertiesAssociatedWithSelection() throws JsonMappingException, JsonProcessingException {
        String input = formatJson("{'name':'Felix','causeOfDeath':'fell'}");

        NonReference pojo = objectMapper.readValue(input, NonReference.class);

        System.out.println(pojo);
        assertTrue(pojo instanceof Selection);
        assertSame(pojo.getClass(), Selection.class);
        assertEquals("Felix", ((Selection)pojo).getName());
        assertEquals("fell", ((Selection)pojo).getCauseOfDeath());

    }

    @Test
    void shouldCastToFreeTextClass_givenUniquePropertiesAssociatedWithFreeText() throws JsonMappingException, JsonProcessingException {
        String input = formatJson("{'name':'Felix','angry':'false'}");

        NonReference pojo = objectMapper.readValue(input, NonReference.class);

        System.out.println(pojo);
        assertTrue(pojo instanceof FreeText);
        assertSame(pojo.getClass(), FreeText.class);
        assertEquals("Felix", ((FreeText)pojo).getName());
        assertEquals(false, ((FreeText)pojo).isAngry());
    }

    @Test
    void shouldCastToReferenceClass_givenBoxedReferenceClass() throws JsonMappingException, JsonProcessingException {
        String input = formatJson("{'address':'12 abc road','ui':{'id':'112233','to':'address'}}");

        Address pojo = objectMapper.readValue(input, Address.class);

        System.out.println(pojo);
        assertTrue(pojo instanceof Address);
        assertSame(pojo.getClass(), Address.class);
        assertTrue(pojo.getUi() instanceof UserInput);
        assertTrue(pojo.getUi() instanceof Reference);
        assertEquals("112233", ((Reference)pojo.getUi()).getId());
        assertEquals("address", ((Reference)pojo.getUi()).getTo());
    }

    @Test
    void shouldCastToSelectionClass_givenBoxedReferenceClass() throws JsonMappingException, JsonProcessingException {
        String input = formatJson("{'address':'12 abc road','ui':{'type':'selection','name':'Felix','causeOfDeath':'fell'}}");

        Address pojo = objectMapper.readValue(input, Address.class);

        System.out.println(pojo);
        assertTrue(pojo instanceof Address);
        assertSame(pojo.getClass(), Address.class);
        assertTrue(pojo.getUi() instanceof UserInput);
        assertTrue(pojo.getUi() instanceof NonReference);
        assertTrue(pojo.getUi() instanceof Selection);
        assertEquals("Felix", ((Selection)pojo.getUi()).getName());
        assertEquals("fell", ((Selection)pojo.getUi()).getCauseOfDeath());
    }

    @Test
    void shouldCastToFreeTextClass_givenBoxedReferenceClass() throws JsonMappingException, JsonProcessingException {
        String input = formatJson("{'address':'12 abc road','ui':{'name':'Felix','angry':'false'}}");

        Address pojo = objectMapper.readValue(input, Address.class);

        System.out.println(pojo);
        assertTrue(pojo instanceof Address);
        assertSame(pojo.getClass(), Address.class);
        assertTrue(pojo.getUi() instanceof UserInput);
        assertTrue(pojo.getUi() instanceof NonReference);
        assertTrue(pojo.getUi() instanceof FreeText);
        assertEquals("Felix", ((FreeText)pojo.getUi()).getName());
        assertEquals(false, ((FreeText)pojo.getUi()).isAngry());
    }

    public static String formatJson(String input) {
        return input.replaceAll("'", "\"");
    }
}
