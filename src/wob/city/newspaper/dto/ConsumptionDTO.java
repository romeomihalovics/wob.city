package wob.city.newspaper.dto;

public class ConsumptionDTO {
    private final double amount;
    private final String type;

    public ConsumptionDTO(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }
}