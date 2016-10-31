package uk.gov.hmrc.sample.service;
/**
 * Created by otom on 30/10/2016.
 */

import uk.gov.hmrc.sample.domain.Fruit;
import uk.gov.hmrc.sample.exception.EmptyShoppingCartException;

import java.math.BigDecimal;
import java.util.List;

public interface CheckoutService {

    BigDecimal calculateTotalCost(List<Fruit> products, boolean applyOffers) throws EmptyShoppingCartException;
}
