package com.branch.branchcodingexercise.util;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
class FormatterTest {
    Formatter aFormatter = new Formatter();

    @Test
    public void formatIsoTs(){
        ZonedDateTime localTime = ZonedDateTime.of ( LocalDate.of ( 2016 , 1 , 4 ) , LocalTime.of ( 9 , 30 ) , ZoneId.of ( "America/New_York" ) );
        String isoTs = aFormatter.formatIsoTs(localTime);

        assertThat(isoTs, is("2016-01-04 09:30:00"));

    }

}