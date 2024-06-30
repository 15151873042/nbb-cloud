package com.nbb.auth.controller;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DateTestDTO {

    private Date date;

    private LocalDateTime localDatetime;

    private LocalDate localDate;

}

