package com.atcyj.pojo;

public class Learn {
    private Integer courseId;
    private Integer studentId;

    public Learn() {
    }

    public Learn(Integer courseId, Integer studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Learn{" +
                "courseId=" + courseId +
                ", studentId=" + studentId +
                '}';
    }
}
