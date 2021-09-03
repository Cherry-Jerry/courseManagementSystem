package com.atcyj.pojo;

/**
 * @author chenyujie
 */
public class Course {
    private Integer id;
    private String courseName;
    private String information;
    private Integer places;
    private Integer selected;

    public Course() {}

    public Course(Integer id, String courseName, String information, Integer places, Integer selected) {
        this.id = id;
        this.courseName = courseName;
        this.information = information;
        this.places = places;
        this.selected = selected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", information='" + information + '\'' +
                ", places=" + places +
                ", selected=" + selected +
                '}';
    }
}
