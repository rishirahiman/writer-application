package com.digital14.writer.factory.writer;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class StringWriter extends Writer {

    private StringBuffer buffer = new StringBuffer();

    //to write/append string to the string buffer
    @Override
    public void write(String s) {
        if(!isClosed) buffer.append(s);
    }

    //convert contents case to lowercase
    @Override
    public void toLowerCase() {
        buffer = new StringBuffer(buffer.toString().toLowerCase());
    }

    //convert contents case to uppercase
    @Override
    public void toUpperCase() {
        buffer = new StringBuffer(buffer.toString().toUpperCase());
    }

    //replaces all stupid keyword in the contents
    @Override
    public void stupidRemover() {
        buffer = new StringBuffer(buffer.toString().replaceAll("stupid","s*****"));
    }

    //removes all consecutive duplicates in the contents
    @Override
    public void duplicateRemover() {
        StringBuffer newBuffer = new StringBuffer();
        String[] words = buffer.toString().split(" ");
        String prevWord = "";
        for(String word : words) {
            if(!prevWord.equals(word)) newBuffer.append(word+" ");
            prevWord = word;
        }
        buffer = newBuffer;
    }

    //to retrieve the contents in the string buffer
    @Override
    public String getContent() {
        return buffer.toString().trim();
    }
}
