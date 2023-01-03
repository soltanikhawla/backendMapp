package com.learnjwt.jwt_learn.Controller;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.learnjwt.jwt_learn.Entity.Projet;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "id", "county_fips", "county_name", "state_name", "date", "county_vmt",
            "baseline_jan_vmt", "percent_change_from_jan", "mean7_county_vmt", "mean7_percent_change_from_jan",
            "date_at_low", "mean7_county_vmt_at_low", "percent_change_from_low" };

    public static boolean hasCSVFormat(MultipartFile file) {
        System.err.println(file.getContentType());
        if (TYPE.equals(file.getContentType()) || file.getContentType().equals("application/vnd.ms-excel")) {
            return true;
        }

        return false;
    }

    public static List<Projet> csvToDataSet(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Projet> dataSetModeloList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Projet projet = new Projet(Long.parseLong(csvRecord.get("Id")),
                        csvRecord.get("Column_Name"),
                        csvRecord.get("Descritption"),
                        csvRecord.get("Column_Type")
                        );

                dataSetModeloList.add(projet);
            }

            return dataSetModeloList;
        } catch (IOException e) {
            throw new RuntimeException("fallo to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream dataSetModelosToCSV(List<Projet> dataSetModeloList) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (Projet projet : dataSetModeloList) {
                List<String> data = Arrays.asList(String.valueOf(projet.getId()),

                        projet.getColumn_Name(),
                        projet.getDescritption(),
                        projet.getColumn_Type()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fallo to import data to CSV file: " + e.getMessage());
        }
    }
}
