package com.atcyj.pojo;

public class Teach {
    private Integer courseId;
    private Integer teacherId;

    public Teach() {
    }

    public Teach(Integer courseId, Integer teacherId) {
        this.courseId = courseId;
        this.teacherId = teacherId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Teach{" +
                "courseId=" + courseId +
                ", teacherId=" + teacherId +
                '}';
    }
}
