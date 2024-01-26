package models;

import lombok.Data;

import java.util.List;


@Data
public class Question {
    private int id;
    private String question;
    private List<Option> options;

    public Question() {
        this.options = List.of();
    }
}
