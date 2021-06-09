import lombok.EqualsAndHashCode;
import lombok.Getter;
import static java.util.Objects.requireNonNull;
import java.math.BigDecimal;
import java.util.UUID;



@Getter
@EqualsAndHashCode

public class Item {

    private final UUID id;
    private final String name;
    private BigDecimal price;
    private int quantity;
    private final Float weight;

    public Item( String name, BigDecimal price, int quantity, Float weight) {
        this.id = UUID.randomUUID();
        this.name = requireNonNull(name);
        this.price = checkPrice(price);
        this.quantity = checkQuantity(quantity);
        this.weight = checkWeight(weight);
    }

    public void setPrice(BigDecimal price) {
        this.price = checkPrice(price);
    }

    public void setQuantity(int quantity) {
        this.quantity = checkQuantity(quantity);
    }

    private BigDecimal checkPrice(BigDecimal price){
        if (price == null) {
            throw new NullPointerException("Co ty mi tutaj z tym nullem");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Po prostu musi być większe od zera");
        }
        return price;
    }
    private int checkQuantity(int quantity){
        if (price == null) {
            throw new NullPointerException("Co ty mi tutaj z tym nullem");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Ilość musi być większa od zera");
        }
        return quantity;
    }
    private Float checkWeight(Float weight){
        if (price == null) {
            throw new NullPointerException("Co ty mi tutaj z tym nullem");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Po prostu musi być większe od zera");
        }
        return weight;
    }
}
