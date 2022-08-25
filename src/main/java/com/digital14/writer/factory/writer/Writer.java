package com.digital14.writer.factory.writer;


public abstract class Writer /*implements Appendable, Closeable, Flushable */{

    boolean isClosed = false;

    public abstract void write(String s);
    public abstract void toLowerCase();
    public abstract void toUpperCase();
    public abstract void stupidRemover();
    public abstract void duplicateRemover();

    public void close(){
        isClosed = true;
    }

    public abstract String getContent();
}
