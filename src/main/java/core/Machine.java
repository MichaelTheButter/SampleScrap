package core;

import java.util.Objects;

/**
 * Machine definition with standard methods
 */
public class Machine {
    private String name;
    private String condition;
    private int productionYear;
    private int price;

    public Machine(String name) {
        this.name = name;
    }

    public Machine(String name, String condition, int productionYear, int price) {
        this.name = name;
        this.condition = condition;
        this.productionYear = productionYear;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Machine{" + "name='" + name + '\'' + ", condition='" + condition + '\'');
        if (productionYear != 0) builder.append(", Manufacture year=" + productionYear);
        else builder.append(", Manufacture year= Unknown");
        if (price != 0) builder.append(", price= " + price + "}");
        else builder.append(", price= unknown}");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Machine machine = (Machine) o;
        return getProductionYear() == machine.getProductionYear() && getPrice() == machine.getPrice() && Objects.equals(getName(), machine.getName()) && Objects.equals(getCondition(), machine.getCondition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCondition(), getProductionYear(), getPrice());
    }
}
