package ca.ulaval.glo4002.cart.application.cart;

import java.util.List;

import ca.ulaval.glo4002.cart.domain.cart.Cart;
import ca.ulaval.glo4002.cart.domain.cart.CartItem;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;
import ca.ulaval.glo4002.cart.domain.cart.CartRepository;
import ca.ulaval.glo4002.cart.interfaces.rest.PersistenceProvider;

public class CartApplicationService {

    public static final int SHIPPING_PRICE_PER_KG = 2;
    private CartRepository cartRepository;

	public CartApplicationService() {
		this.cartRepository = PersistenceProvider.getCartRepository();
	}

	public Cart findOrCreateCartForClient(String email) {
		List<Cart> carts = cartRepository.getAll();

		Cart cart = getCartByOwner(email, carts);

		return cart;
	}

	public void addItemToCart(String email, ShopItem item) {
		List<Cart> carts = cartRepository.getAll();
		Cart cart = getCartByOwner(email, carts);

		cart.addItem(new CartItem(item.getName(), 1, getItemPriceWithShipping(item)));

		cartRepository.save(carts);
	}

    private int getItemPriceWithShipping(ShopItem item) {
        int totalPrice = item.getPrice();

        if (!item.isPrime()) {
            totalPrice += item.getWeight() * SHIPPING_PRICE_PER_KG;
        }

        return totalPrice;
    }

    private Cart getCartByOwner(String email, List<Cart> carts) {
		return carts.stream().filter(c -> c.ownerEmail.equals(email)).findFirst().orElseGet(() -> {
			Cart newCart = new Cart(email);
			carts.add(newCart);
			cartRepository.save(carts);
			return newCart;
		});
	}
}
