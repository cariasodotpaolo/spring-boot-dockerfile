package mp.cariaso.springboot.utils;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;

public class AmountUtil {

    //convert long amount to BigDecimal with given decimal precision
    // PREVENT BIGDECIMAL FROM INCLUDING LENGTHY DECIMAL PLACES
    public static BigDecimal getBigDecimalAmount(Long amount, int decimalPlace, int divisor) {

        Double doubleAmount;

        if(nonNull(amount)) {
            doubleAmount = ((double) amount / divisor);
        } else {
            doubleAmount = 0.00;
        }

        //absolute value to convert to non-negative
        return new BigDecimal(doubleAmount.toString()).setScale(decimalPlace).abs();

    }
}
