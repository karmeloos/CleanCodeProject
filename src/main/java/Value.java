import java.util.Objects;

import static java.util.Objects.requireNonNull;

abstract class Value<T> {

    private final T value;

    protected Value(T value) {
        this.value = requireNonNull(value);
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Value<?> value1 = (Value<?>) o;
        return value.equals(value1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}