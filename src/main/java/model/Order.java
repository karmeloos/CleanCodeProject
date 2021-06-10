package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Getter
@EqualsAndHashCode

public class Order {
    private final UUID id;
    private final UUID customerId;
    private final LocalDateTime orderTime;
    private final boolean discount;
    private final List<Item> items;
    private final BigDecimal deliveryCost;
    private OrderStatus status;

    public Order(UUID customerId, boolean discount, List<Item> items, BigDecimal deliveryCost) {
        this.id = UUID.randomUUID();
        this.customerId = requireNonNull(customerId);
        this.discount = requireNonNull(discount);
        this.items = checkItemList(items);
        this.deliveryCost = checkDeliveryCost(deliveryCost);
        this.orderTime = LocalDateTime.now();
        this.status = OrderStatus.WAITING;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    private BigDecimal checkDeliveryCost(BigDecimal deliveryCost){
        if (deliveryCost.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Po prostu musi być większe od zera");
        }
        return deliveryCost;
    }
    private List<Item> checkItemList(List<Item> items) {
        if(!items.isEmpty()){
            throw new IllegalArgumentException("W koszyku muszą znajdować się rzeczy");
        }
        return items;
    }
}
