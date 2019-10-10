package ca.ulaval.glo4002.cart.domain.cart;

import ca.ulaval.glo4002.cart.domain.cart.Cart;

import java.util.List;

public interface CartRepository {
    List<Cart> getAll();
    void save(List<Cart> carts);
}
