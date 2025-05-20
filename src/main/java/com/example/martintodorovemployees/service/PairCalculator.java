package com.example.martintodorovemployees.service;

import com.example.martintodorovemployees.model.PairDetail;
import com.example.martintodorovemployees.model.Record;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * For each project, find each pair of employees
 * and calculate how many days their work periods overlap.
 */
public class PairCalculator {

    /**
     * Returns a list of PairDetail, one per (emp1, emp2, project).
     */
    public static List<PairDetail> calculate(List<Record> records) {
        // Group all records by project ID
        Map<Integer, List<Record>> byProject = new HashMap<>();
        for (Record r : records) {
            byProject
                    .computeIfAbsent(r.getProjectId(), id -> new ArrayList<>())
                    .add(r);
        }

        List<PairDetail> output = new ArrayList<>();

        // Now, for each project, compare each pair
        for (Map.Entry<Integer, List<Record>> entry : byProject.entrySet()) {
            int projectId = entry.getKey();
            List<Record> recs = entry.getValue();

            for (int i = 0; i < recs.size(); i++) {
                for (int j = i + 1; j < recs.size(); j++) {
                    Record a = recs.get(i);
                    Record b = recs.get(j);

                    // Overlap window = max(startDates) … min(endDates)
                    LocalDate start = a.getFrom().isAfter(b.getFrom())
                            ? a.getFrom()
                            : b.getFrom();
                    LocalDate end = a.getTo().isBefore(b.getTo())
                            ? a.getTo()
                            : b.getTo();

                    // If they actually overlap, count days
                    if (!start.isAfter(end)) {
                        long days = ChronoUnit.DAYS.between(start, end) + 1;
                        int e1 = Math.min(a.getEmpId(), b.getEmpId());
                        int e2 = Math.max(a.getEmpId(), b.getEmpId());
                        output.add(new PairDetail(e1, e2, projectId, days));
                    }
                }
            }
        }
        return output;
    }
}