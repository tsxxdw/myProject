package bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Student {
    String name;
    Integer year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
