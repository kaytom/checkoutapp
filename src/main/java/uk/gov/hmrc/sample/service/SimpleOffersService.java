package uk.gov.hmrc.sample.service;

/**
 * Created by otom on 30/10/2016.
 */

import java.math.BigDecimal;

public interface SimpleOffersService {

  BigDecimal applyOffer(int applesQuantity, int orangesQuantity);
}
