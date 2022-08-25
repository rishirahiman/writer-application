package com.digital14.writer.factory.writer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileWriterTest {

    Writer writer;

    @BeforeEach
    void setUp() {
        writer = new FileWriter();
    }

    @Test
    void write(){
        writer.write("Hi");
        writer.close();
        assertEquals("Hi",writer.getContent());
    }

    @Test
    void close(){
        writer.write("Hi");
        writer.close();
        writer.write("Hello");
        assertEquals("Hi",writer.getContent());
    }

    @Test
    void toLowerCase(){
        writer.write("Hi Hello");
        writer.toLowerCase();
        writer.close();
        assertEquals("hi hello",writer.getContent());
    }

    @Test
    void toUpperCase(){
        writer.write("Hi Hello");
        writer.toUpperCase();
        writer.close();
        assertEquals("HI HELLO",writer.getContent());
    }
}