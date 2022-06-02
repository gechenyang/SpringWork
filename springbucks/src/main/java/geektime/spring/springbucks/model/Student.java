package geektime.spring.springbucks.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Student {
    private String username;
    private BigDecimal score;

    public Student(String username,BigDecimal score){
        this.username = username;
        this.score = score;
    }
}
