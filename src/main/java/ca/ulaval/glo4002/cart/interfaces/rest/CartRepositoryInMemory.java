package ca.ulaval.glo4002.cart.interfaces.rest;

import ca.ulaval.glo4002.cart.domain.cart.CartRepository;
import ca.ulaval.glo4002.cart.domain.cart.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartRepositoryInMemory implements CartRepository {

    private List<Cart> carts = new ArrayList<Cart>();

    @Override
    public List<Cart> getAll() {
        return carts;
    }

    @Override
    public void save(List<Cart> carts) {
        carts.clear();
        carts.addAll(carts);
    }
}
