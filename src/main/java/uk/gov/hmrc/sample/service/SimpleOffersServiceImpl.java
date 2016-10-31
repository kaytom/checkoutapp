package uk.gov.hmrc.sample.service;

/**
 * Created by otom on 30/10/2016.
 */

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import uk.gov.hmrc.sample.util.CurrencyFormatter;
import uk.gov.hmrc.sample.util.Scanner;

import java.math.BigDecimal;


public class SimpleOffersServiceImpl implements SimpleOffersService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleOffersServiceImpl.class);

    public BigDecimal applyOffer(int applesQuantity, int orangesQuantity) {

        LOGGER.info("Appling simple offers to products in the shopping cart  ...");

        BigDecimal totalCostApples = BigDecimal.ZERO;
        if (applesQuantity > 1) {
            //check if there is an even number of apples in cart
            final boolean applyAppleOffer = applesQuantity % 2 == 0;

            //Logic for Buy 1 Get 1 Free offer
            totalCostApples = (new BigDecimal(applesQuantity / 2)).multiply(CurrencyFormatter.rounded(Scanner.APPLE_PRICE));

            //If there an odd number of apples in cart, add price of spare apple to the total cost after offer.
            if (!applyAppleOffer) {
                totalCostApples = totalCostApples.add(CurrencyFormatter.rounded(Scanner.APPLE_PRICE));
            }
        } else {
            totalCostApples = (new BigDecimal(applesQuantity)).multiply(CurrencyFormatter.rounded(Scanner.APPLE_PRICE));
        }

        BigDecimal totalCostOranges = BigDecimal.ZERO;
        BigDecimal totalCostOrangesRemainder = BigDecimal.ZERO;

        if (orangesQuantity > 2) {
            // Three for the price of 2 for oranges
            // Determine the price of 2
            BigDecimal priceofTwoOranges = (new BigDecimal(2)).multiply(Scanner.ORANGE_PRICE);
            //Determine how many set of 3
            double setOfThreeOrangesFraction = orangesQuantity / 3;
            double fractionalPart = setOfThreeOrangesFraction % 1;
            double setOfThreeOranges = setOfThreeOrangesFraction - fractionalPart;
            int remainderAfterSetOfThree = orangesQuantity % 3;

            totalCostOranges = (new BigDecimal(setOfThreeOranges)).multiply(priceofTwoOranges);

            // Cost of any remainder after sets of 3 (if any remainder)
            if (remainderAfterSetOfThree != 0) {
                totalCostOrangesRemainder = (new BigDecimal(remainderAfterSetOfThree)).multiply(Scanner.ORANGE_PRICE);
            }

        } else {
            totalCostOranges = (new BigDecimal(orangesQuantity)).multiply(Scanner.ORANGE_PRICE);
        }

        //Grand Total Cost of Oranges after applying 3 for the price of 2
        totalCostOranges = totalCostOranges.add(totalCostOrangesRemainder);
        BigDecimal totalCostAfterOffer = totalCostApples.add(totalCostOranges);

        return CurrencyFormatter.rounded(totalCostAfterOffer);
    }
}
