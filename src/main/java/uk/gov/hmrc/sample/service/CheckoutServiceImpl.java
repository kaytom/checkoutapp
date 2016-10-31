package uk.gov.hmrc.sample.service;
/**
 * Created by otom on 30/10/2016.
 */

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.gov.hmrc.sample.domain.Fruit;
import uk.gov.hmrc.sample.exception.EmptyShoppingCartException;
import uk.gov.hmrc.sample.util.CurrencyFormatter;
import uk.gov.hmrc.sample.util.Scanner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutServiceImpl.class);

    public BigDecimal calculateTotalCost(List<Fruit> products, boolean applyOffers) throws EmptyShoppingCartException {

        LOGGER.info("calculating total cost of products in the shopping cart in checkout service impl ...");

        if (products.isEmpty()) {
            throw new EmptyShoppingCartException("Your shopping cart is empty, please add your products to proceed to checkout");
        }

        Map<String, Integer> scannedItemsQtyMap= Scanner.scanItemsQuantity(products);

        int appleQuantity = ((Integer) scannedItemsQtyMap.get(Scanner.APPLE_KEY)).intValue();
        int orangeQuantity = ((Integer)scannedItemsQtyMap.get(Scanner.ORANGE_KEY)).intValue();

        BigDecimal totalCost = BigDecimal.ZERO;

        // Donot apply offers i.e. flag is set to false
        if (!applyOffers ) {
            LOGGER.info("calculating total cost of products in the shopping cart WITHOUT Special Offers ...");
            BigDecimal totalCostOfApples = Scanner.APPLE_PRICE.multiply(new BigDecimal(appleQuantity));
            BigDecimal totalCostOfOranges = Scanner.ORANGE_PRICE.multiply(new BigDecimal(orangeQuantity));
            totalCost = totalCostOfApples.add(totalCostOfOranges);
        }else{
            LOGGER.info("calculating total cost of products in the shopping cart WITH Special Offers ...");
            //TODO: Apply simple offers
        }

        totalCost= CurrencyFormatter.rounded(totalCost);
        
        return totalCost;
    }

}
