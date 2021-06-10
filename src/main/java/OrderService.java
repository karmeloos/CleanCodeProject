
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class OrderService {
    private final CustomerDao customerDao;
    private final DiscountCouponsDao couponsDao;
    private final OrderDao orderDao;

    public OrderService(CustomerDao customerDao, DiscountCouponsDao couponsDao, OrderDao orderDao) {
        this.customerDao = requireNonNull(customerDao);
        this.couponsDao =  requireNonNull(couponsDao);
        this.orderDao =  requireNonNull(orderDao);
    }

    public void makeOrder(UUID customerId, List<Item> items, UUID couponId) {
        Customer customer = customerDao.findById(customerId);
        Coupon coupon = couponsDao.findById(couponId);
        boolean hasDiscount = coupon != null;
        BigDecimal deliveryCost = calculateDeliveryCost(items);
        Order order = new Order(customerId, hasDiscount, items, deliveryCost);
        this.orderDao.save(order);
        sentEmail(customer.getEmail()
                , "Your order is placed!"
                , "Thanks for ordering our products. Your order will be send very soon!");
    }

    private BigDecimal calculateDeliveryCost(List<Item> items) {
        //TODO::
        throw new UnsupportedOperationException("Not implemented yet");
    }
    private void sentEmail(String email, String header, String massage){
        //TODO::
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
