package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
    private final BigDecimal itemCost;
    private OrderStatus status;


    public Order(UUID customerId, boolean discount, List<Item> items) {
        this.id = UUID.randomUUID();
        this.customerId = requireNonNull(customerId);
        this.discount = discount;
        this.items = checkItemList(items);
        this.orderTime = LocalDateTime.now();
        this.status = OrderStatus.WAITING;
        this.itemCost = countItemCost();
        this.deliveryCost = countDeliveryCost();
    }

    public BigDecimal getTotalCost() {
        BigDecimal discount = this.isDiscount() ? BigDecimal.valueOf(0.9) : BigDecimal.ONE;
        return itemCost.multiply(discount).add(deliveryCost);
    }

    private BigDecimal countItemCost() {
        Optional<BigDecimal> cost = items.stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal::add);
        if(cost.isEmpty()) {
            throw new IllegalArgumentException();
        }
        countDeliveryCost();
        return cost.get();
    }

    private BigDecimal countDeliveryCost() {
        float totalWeight = getTotalWeight();
        BigDecimal deliveryCost = BigDecimal.valueOf(50);
        if (totalWeight < 1) {
            if(itemCost.compareTo(BigDecimal.valueOf(250)) > 0) {
                deliveryCost = BigDecimal.ZERO;
            }
            deliveryCost = BigDecimal.valueOf(15);
        }
        if (totalWeight < 5) {
            deliveryCost = BigDecimal.valueOf(35);
        }
        return deliveryCost;
    }

     private Float getTotalWeight() {
         Optional<Float> totalWeight = items.stream()
                 .map(i -> i.getWeight() * i.getQuantity())
                 .reduce(Float::sum);
         if(totalWeight.isEmpty()) {
             throw new IllegalArgumentException();
         }
         return totalWeight.get();
     }

    private List<Item> checkItemList(List<Item> items) {
        if(items.isEmpty()){
            throw new IllegalArgumentException("W koszyku muszą znajdować się rzeczy");
        }
        return items;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}

