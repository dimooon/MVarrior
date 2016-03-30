package com.mv.dimooon.mvarrior.dao;

import com.orm.SugarRecord;

/**
 * Created by dimooon on 28.03.16.
 */
public class Education extends SugarRecord{

    String title;
    String university;
    String degree;
    long expectedGraduationDate;
    String gradaAverage;

    Player owner;

    public Education() {
    }

    public Education(String title, String university, String degree, long expectedGraduationDate, String gradaAverage, Player owner) {
        this.title = title;
        this.university = university;
        this.degree = degree;
        this.expectedGraduationDate = expectedGraduationDate;
        this.gradaAverage = gradaAverage;
        this.owner = owner;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Education{");
        sb.append("\ntitle='").append(title).append('\'');
        sb.append(", \nuniversity='").append(university).append('\'');
        sb.append(", \ndegree='").append(degree).append('\'');
        sb.append(", \nexpectedGraduationDate=").append(expectedGraduationDate);
        sb.append(", \ngradaAverage='").append(gradaAverage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
