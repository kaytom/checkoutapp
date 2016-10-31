package uk.gov.hmrc.sample.controller;

/**
 * Created by otom on 30/10/2016.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.gov.hmrc.sample.domain.Fruit;
import uk.gov.hmrc.sample.exception.EmptyShoppingCartException;
import uk.gov.hmrc.sample.service.CheckoutService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class CheckoutController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutController.class);

    private CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    public void setCheckoutService(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    /**
     * Calculates the total cost of scanned fruits at checkout
     *
     * @param fruitList is the list of fruits to total cost during at checkout.
     * @param applyOffer is the indicator of when to apply special offer.
     *
     * @return BigDecimal calculated total cost of fruits to be checked out.
     */
    @RequestMapping(
            value = "/cost",
            method = RequestMethod.POST,
            produces = "application/json; charset=UTF-8"
    )
    public BigDecimal calculateTotalCost(@RequestBody List<Fruit> fruitList,
                                         @RequestParam("applyOffer") boolean applyOffer) throws EmptyShoppingCartException {
        LOGGER.info("totaling the cost of products in the shopping cart...");
        return checkoutService.calculateTotalCost(fruitList, applyOffer);
    }
}