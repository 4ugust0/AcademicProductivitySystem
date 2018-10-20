package com.company.models;

public class Orientation {
    public Orientation(Student orientedStudent, Publication associatedPublication) {
        this.orientedStudent = orientedStudent;
        this.orientedContributor = null;
        this.associatedPublication = associatedPublication;
    }

    public Orientation(Contributor orientedContributor, Publication associatedPublication) {
        this.orientedStudent = null;
        this.orientedContributor = orientedContributor;
        this.associatedPublication = associatedPublication;
    }

    private Student orientedStudent;
    private Contributor orientedContributor;
    private Publication associatedPublication;

    public Student getOrientedStudent() {
        return orientedStudent;
    }

    public void setOrientedStudent(Student orientedStudent) {
        this.orientedStudent = orientedStudent;
    }

    public Publication getAssociatedPublication() {
        return associatedPublication;
    }

    public void setAssociatedPublication(Publication associatedPublication) {
        this.associatedPublication = associatedPublication;
    }
}
