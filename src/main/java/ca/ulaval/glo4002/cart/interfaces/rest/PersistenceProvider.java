package ca.ulaval.glo4002.cart.interfaces.rest;

import ca.ulaval.glo4002.cart.domain.cart.CartRepository;

public class PersistenceProvider {
    public static CartRepository getCartRepository() {
        if(System.getProperty("store").equalsIgnoreCase("xml")) {
            return new CartRepositoryXml();
        } else {
            return new CartRepositoryInMemory();
        }
    }
}
