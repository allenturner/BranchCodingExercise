package com.branch.branchcodingexercise.util;

import lombok.extern.java.Log;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Log
public class Formatter {
    private static final DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String formatIsoTs(ZonedDateTime isoDate) {
        String format = null;
        if (isoDate != null) {
            format = isoDate.format(FOMATTER);
        }
        return format;
    }
}