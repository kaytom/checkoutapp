package uk.gov.hmrc.sample.controller;

/**
 * Created by otom on 30/10/2016.
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.MockitoAnnotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import uk.gov.hmrc.sample.CheckoutApplication;
import uk.gov.hmrc.sample.domain.Fruit;
import uk.gov.hmrc.sample.service.CheckoutService;
import uk.gov.hmrc.sample.util.Scanner;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = CheckoutApplication.class)

public class CheckoutControllerTest {
    Logger logger = LoggerFactory.getLogger(CheckoutControllerTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateTotalCost_NoOffers() throws Exception {
        mockMvc.perform(post("/products/cost")
                .param("applyOffer", "false")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(shoppingCart()))
        ).andExpect(status().isOk());
    }

    @Test
    public void testCalculateTotalCost_WithOffers() throws Exception {
        mockMvc.perform(post("/products/cost")
                .param("applyOffer", "true")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(shoppingCart()))
        ).andExpect(status().isOk());
    }

    @Test(expected = Exception.class)
    public void testCalculateTotalCost_EmptyShoppingCart() throws Exception {
        mockMvc.perform(post("/products/cost")
                .param("applyOffer", "true")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(emptyShoppingCart()))
        ).andExpect(status().is5xxServerError());
    }

    private List shoppingCart() {
        List<Fruit> productList = new ArrayList();

        Fruit apple = new Fruit();
        apple.setName("Apple");
        apple.setPrice(Scanner.APPLE_PRICE);

        Fruit orange = new Fruit();
        orange.setName("Orange");
        orange.setPrice(Scanner.ORANGE_PRICE);

        productList.add(apple);
        productList.add(apple);
        productList.add(apple);
        productList.add(apple);
        productList.add(apple);
        productList.add(apple);
        productList.add(orange);
        productList.add(orange);
        productList.add(orange);
        productList.add(orange);
        productList.add(orange);
        productList.add(orange);
        productList.add(orange);
        productList.add(orange);
        productList.add(orange);
        productList.add(orange);
        productList.add(orange);
        return productList;
    }

    private List emptyShoppingCart() {
        List<Fruit> productList = new ArrayList();
        return productList;
    }
}