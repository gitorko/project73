package com.demo.project73;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Audit {
    String message;
    LocalDateTime date;
}
