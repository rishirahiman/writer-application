package com.digital14.writer.factory.writer;

import lombok.extern.log4j.Log4j2;

import java.io.*;

@Log4j2
public class FileWriter extends Writer {

    private static final String FILE_NAME = "temp.dat";

    private PrintWriter printWriter;

    public FileWriter() {
        try {
            File file = new File(FILE_NAME);
           file.delete();
            printWriter = new PrintWriter (new FileOutputStream(FILE_NAME, true));
        } catch (FileNotFoundException e) {
            log.error("Not able to access temp file: {}",e.getMessage());
        }
    }

    //to write/append string to the file
    @Override
    public void write(String s) {
        if(!isClosed) {
            printWriter.print(s);
            printWriter.flush();
        }
    }

    //convert file content case to lowercase
    @Override
    public void toLowerCase() {
        StringBuffer outContent = new StringBuffer(getContent().toLowerCase());
        replaceFileContent(outContent.toString());
    }

    //convert file content case to uppercase
    @Override
    public void toUpperCase() {
        StringBuffer outContent = new StringBuffer(getContent().toUpperCase());
        replaceFileContent(outContent.toString());
    }

    //replaces all stupid keyword in the file content
    @Override
    public void stupidRemover() {
        StringBuffer outContent = new StringBuffer(getContent().replaceAll("stupid","s*****"));
        replaceFileContent(outContent.toString());
    }

    //removes all consecutive duplicates in the file content
    @Override
    public void duplicateRemover() {
        StringBuffer fileContent = new StringBuffer();
        String[] words = getContent().split(" ");
        String prevWord = "";
        for(String word : words) {
            if(!prevWord.equals(word)) fileContent.append(word+" ");
            prevWord = word;
        }
        replaceFileContent(fileContent.toString());
    }

    //to retrieve the contents in the string buffer
    @Override
    public String getContent() {
        StringBuffer fileContent = new StringBuffer();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_NAME));
            String line = reader.readLine();
            while (line != null) {
                fileContent.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            log.error("Not able to find temp file: {}",e.getMessage());
        } catch (IOException e) {
            log.error("Not able to access temp file: {}",e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileContent.toString().trim();
    }

    private void replaceFileContent(String newContent){
        try {
            printWriter.close();
            File file = new File(FILE_NAME);
            file.delete();
            printWriter = new PrintWriter (new FileOutputStream(FILE_NAME, true));
        } catch (FileNotFoundException e) {
            log.error("Not able to find temp file: {}",e.getMessage());
        }
        printWriter.write(newContent.trim());
        printWriter.flush();
        printWriter.close();
    }

    @Override
    public void close() {
        super.close();
        printWriter.close();
    }
}
