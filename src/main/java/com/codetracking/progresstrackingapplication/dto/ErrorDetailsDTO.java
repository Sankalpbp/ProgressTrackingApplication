package com.codetracking.progresstrackingapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

public record ErrorDetailsDTO ( Date timestamp, String message, String details ) {

}
