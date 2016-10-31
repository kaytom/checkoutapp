package uk.gov.hmrc.sample.service;

/**
 * Created by otom on 30/10/2016.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import uk.gov.hmrc.sample.domain.Fruit;
import uk.gov.hmrc.sample.exception.EmptyShoppingCartException;
import uk.gov.hmrc.sample.util.CurrencyFormatter;
import uk.gov.hmrc.sample.util.Scanner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceTest {

    private static final BigDecimal PRICE_FOR_3_APPLES_1_ORANGE_WITHOFFER=new BigDecimal(1.45);
    @InjectMocks
    private static CheckoutService checkoutService = new CheckoutServiceImpl();

    @Test
    public void testTotalShoppingCost_WithoutOffers() throws EmptyShoppingCartException {
        BigDecimal calculatedtotalCost = checkoutService.calculateTotalCost(shoppingCart(), false);
        assertEquals(calculatedtotalCost, shoppingCartCost());
    }

    @Test
    public void testTotalShoppingCost_WithOffers() throws EmptyShoppingCartException {
        BigDecimal calculatedtotalCost = checkoutService.calculateTotalCost(shoppingCart(), true);
        assertEquals(calculatedtotalCost, CurrencyFormatter.rounded(PRICE_FOR_3_APPLES_1_ORANGE_WITHOFFER));
    }

    @Test(expected = EmptyShoppingCartException.class)
    public void testTotalShoppingCost_EmptyShoppingCart() throws EmptyShoppingCartException {
        BigDecimal calculatedtotalCost = checkoutService.calculateTotalCost(emptyShoppingCart(), false);
        assertEquals(calculatedtotalCost, BigDecimal.ZERO);
    }

    private List emptyShoppingCart() {
        List<Fruit> productList=new ArrayList();
        return productList;
    }

    private List<Fruit> shoppingCart() {
        List<Fruit> productList=new ArrayList<>();
        
        Fruit apple=new Fruit();
        apple.setName("Apple");
        apple.setPrice(Scanner.APPLE_PRICE);
        
        Fruit orange=new Fruit();
        orange.setName("Orange");
        orange.setPrice(Scanner.ORANGE_PRICE);
        
        productList.add(apple);
        productList.add(apple);
        productList.add(orange);
        productList.add(apple);
    	
        return productList;
    }

    private BigDecimal shoppingCartCost() {
        BigDecimal totalCostForShopping = BigDecimal.ZERO;

        for (Fruit product : shoppingCart()) {
                totalCostForShopping = totalCostForShopping.add(product.getPrice());
        }

        return CurrencyFormatter.rounded(totalCostForShopping);
    }
}