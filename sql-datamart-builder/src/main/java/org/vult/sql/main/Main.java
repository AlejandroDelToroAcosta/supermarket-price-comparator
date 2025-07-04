package org.vult.sql.main;

import org.vult.sql.service.DatamartBuilder;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        DatamartBuilder datamartBuilder = new DatamartBuilder();
        datamartBuilder.execute();

    }

}
