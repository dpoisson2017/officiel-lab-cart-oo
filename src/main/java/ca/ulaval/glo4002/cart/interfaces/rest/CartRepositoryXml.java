package ca.ulaval.glo4002.cart.interfaces.rest;

import ca.ulaval.glo4002.cart.application.PersistenceException;
import ca.ulaval.glo4002.cart.application.XmlUtils;
import ca.ulaval.glo4002.cart.application.cart.CartList;
import ca.ulaval.glo4002.cart.domain.cart.CartRepository;
import ca.ulaval.glo4002.cart.domain.cart.Cart;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CartRepositoryXml implements CartRepository {
    private static final String CART_STORAGE = "cart-storage";

    private static File storageFile;

    static {
        storageFile = XmlUtils.createStorageFile(CART_STORAGE);
    }

    @Override
    public List<Cart> getAll() {
        Unmarshaller unmarshaller = XmlUtils.createUnmarshaller();
        try {
            return ((CartList) unmarshaller.unmarshal(storageFile)).getCarts();
        } catch (JAXBException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void save(List<Cart> carts) {
        Marshaller marshaller = XmlUtils.createMarshaller();
        try {
            marshaller.marshal(new CartList(carts), storageFile);
        } catch (JAXBException e) {
            throw new PersistenceException(e);
        }
    }
}
