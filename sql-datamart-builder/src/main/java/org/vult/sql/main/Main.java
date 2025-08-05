package org.vult.sql.main;

import org.vult.sql.service.DatamartBuilder;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        DatamartBuilder datamartBuilder = new DatamartBuilder(args[0], args[1], args[2]);
        datamartBuilder.execute();
    }
}
