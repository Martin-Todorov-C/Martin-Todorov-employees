package com.example.martintodorovemployees.model;

public class PairDetail {
    private final int emp1;
    private final int emp2;
    private final int projectId;
    private final long days;

    public PairDetail(int emp1, int emp2, int projectId, long days) {
        this.emp1 = emp1;
        this.emp2 = emp2;
        this.projectId = projectId;
        this.days = days;
    }

    public int getEmp1() {
        return emp1;
    }

    public int getEmp2() {
        return emp2;
    }

    public int getProjectId() {
        return projectId;
    }

    public long getDays() {
        return days;
    }
}