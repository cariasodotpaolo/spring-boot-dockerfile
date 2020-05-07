package mp.cariaso.springboot.utils;

import java.time.LocalDate;

public class DateUtil {

    //julian date example: 2020340
    public static LocalDate getLocalDateFromJulianDate(String julianDate) {

        LocalDate localDate = LocalDate.ofYearDay(Integer.parseInt(julianDate.substring(0,4)),
                              Integer.parseInt((julianDate.substring(4,7))));

        return localDate;

    }
}
