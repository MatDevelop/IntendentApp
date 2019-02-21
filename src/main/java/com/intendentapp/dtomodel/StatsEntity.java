package com.intendentapp.dtomodel;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "stats")
public class StatsEntity implements Serializable {

    @Id
    @Column(name = "statid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer statid;

    @Column(name = "month_year")
    private String month_year;

    @Column(name = "consumers")
    private Integer consumers;

    @Column(name = "reports")
    private Integer reports;

    @Column(name = "unpaid")
    private Integer unpaid;

    public StatsEntity(){}

    public StatsEntity(Integer statid, String month_year, Integer consumers, Integer reports, Integer unpaid) {
        this.statid = statid;
        this.month_year = month_year;
        this.consumers = consumers;
        this.reports = reports;
        this.unpaid = unpaid;
    }

    public Integer getStatid() {
        return statid;
    }

    public void setStatid(Integer statid) {
        this.statid = statid;
    }

    public String getMonth_year() {
        return month_year;
    }

    public void setMonth_year(String month_year) {
        this.month_year = month_year;
    }

    public Integer getConsumers() {
        return consumers;
    }

    public void setConsumers(Integer consumers) {
        this.consumers = consumers;
    }

    public Integer getReports() {
        return reports;
    }

    public void setReports(Integer reports) {
        this.reports = reports;
    }

    public Integer getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(Integer unpaid) {
        this.unpaid = unpaid;
    }
}
