package uk.gov.hmrc.sample.util;
/**
 * Created by otom on 30/10/2016.
 */

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import uk.gov.hmrc.sample.domain.Fruit;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Scanner.class);

    public static final String APPLE_KEY = "APPLE";
    public static final String ORANGE_KEY = "ORANGE";
    public static final String APPLE_NAME = "Apple";
    public static final String ORANGE_NAME = "Orange";
    public static final BigDecimal APPLE_PRICE = new BigDecimal(0.60);
    public static final BigDecimal ORANGE_PRICE = new BigDecimal(0.25);

    public static Map<String, Integer> scanItemsQuantity(List<Fruit> products) {
        LOGGER.info("scanning products in the shopping cart...");

        Map<String, Integer> scannedItemsQtyMap = new HashMap<>();

        int appleQuantity = 0;
        int orangeQuantity = 0;

        for (Fruit product : products) {
            if (product.getName().equals(APPLE_NAME)) {
                appleQuantity++;
            }
            if (product.getName().equals(ORANGE_NAME)) {
                orangeQuantity++;
            }
        }

        LOGGER.info("Scanning # apples =  " + appleQuantity + " # oranges = "+ orangeQuantity);

        scannedItemsQtyMap.put(APPLE_KEY, new Integer(appleQuantity));
        scannedItemsQtyMap.put(ORANGE_KEY, new Integer(orangeQuantity));

        return scannedItemsQtyMap;
    }
}
