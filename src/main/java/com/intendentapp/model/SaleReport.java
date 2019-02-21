package com.intendentapp.model;

import java.util.List;

public class SaleReport {
    private List<String> date;
    private String filename;
    private List<Double> dueamount;
    private List<Double> writeoff;
    private List<Double> payment;
    private List<Double> excess;
    private List<Double> overdue;

    public SaleReport(){}

    public SaleReport(List<String> date, String filename, List<Double> dueamount, List<Double> writeoff, List<Double> payment, List<Double> excess, List<Double> overdue) {
        this.date = date;
        this.filename = filename;
        this.dueamount = dueamount;
        this.writeoff = writeoff;
        this.payment = payment;
        this.excess = excess;
        this.overdue = overdue;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<Double> getDueamount() {
        return dueamount;
    }

    public void setDueamount(List<Double> dueamount) {
        this.dueamount = dueamount;
    }

    public List<Double> getWriteoff() {
        return writeoff;
    }

    public void setWriteoff(List<Double> writeoff) {
        this.writeoff = writeoff;
    }

    public List<Double> getPayment() {
        return payment;
    }

    public void setPayment(List<Double> payment) {
        this.payment = payment;
    }

    public List<Double> getExcess() {
        return excess;
    }

    public void setExcess(List<Double> excess) {
        this.excess = excess;
    }

    public List<Double> getOverdue() {
        return overdue;
    }

    public void setOverdue(List<Double> overdue) {
        this.overdue = overdue;
    }
}
