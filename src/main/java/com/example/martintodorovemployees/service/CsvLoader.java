package com.example.martintodorovemployees.service;

import com.example.martintodorovemployees.model.Record;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Reads our EmpID/ProjectID/DateFrom/DateTo CSV.
 *
 * - If the first row looks like a header (contains “EmpID”), skip it
 * - Supports three date formats (yyyy-MM-dd, dd/MM/yyyy, MM-dd-yyyy)
 * - Treats “NULL” as LocalDate.now()
 */
public class CsvLoader {
    private static final DateTimeFormatter[] F = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy")
    };

    /**
     * Load and parse the CSV into a list of Record objects.
     */
    public static List<Record> load(File file) throws IOException {
        List<Record> out = new ArrayList<>();
        LocalDate today = LocalDate.now();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirst = true;
            while ((line = br.readLine()) != null) {
                // Skip header if present
                if (isFirst) {
                    isFirst = false;
                    if (line.toLowerCase().contains("empid")) {
                        continue;
                    }
                }
                String[] c = line.split(",");
                int eid = Integer.parseInt(c[0].trim());
                int pid = Integer.parseInt(c[1].trim());
                LocalDate from = parse(c[2].trim());
                LocalDate to = c[3].trim().equalsIgnoreCase("NULL") ? today : parse(c[3].trim());
                out.add(new Record(eid, pid, from, to));
            }
        }
        return out;
    }

    /**
     * Try each supported format until one works.
     */
    private static LocalDate parse(String s) {
        for (DateTimeFormatter fmt : F) {
            try {
                return LocalDate.parse(s, fmt);
            } catch (Exception ignore) {
            }
        }
        throw new IllegalArgumentException("Unsupported date: " + s);
    }
}