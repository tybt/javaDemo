package com.example.demo.email;

import lombok.Data;

@Data
public class EmailDTO {

    String to;
    String text;
    String subject;
}
