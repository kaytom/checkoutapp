package uk.gov.hmrc.sample.exception;

/**
 * Created by otom on 30/10/2016.
 */
public class EmptyShoppingCartException extends Exception  {

    public EmptyShoppingCartException(String message) {
        super(message);
    }
}