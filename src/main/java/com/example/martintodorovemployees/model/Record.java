package com.example.martintodorovemployees.model;

import java.time.LocalDate;

public class Record {
    private final int empId;
    private final int projectId;
    private final LocalDate from;
    private final LocalDate to;

    public Record(int empId, int projectId, LocalDate from, LocalDate to) {
        this.empId = empId;
        this.projectId = projectId;
        this.from = from;
        this.to = to;
    }

    public int getEmpId() {
        return empId;
    }

    public int getProjectId() {
        return projectId;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }
}