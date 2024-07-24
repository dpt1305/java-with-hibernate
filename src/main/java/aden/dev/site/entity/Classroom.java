package aden.dev.site.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String className;

    @Override
    public String toString() {
        return "Classroom{" +
                "id='" + id + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Classroom() {
    }

    public Classroom(String className) {
        this.className = className;
    }


}
