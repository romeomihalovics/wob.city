package wob.city.objects;

import wob.city.abstractions.NewsPaper;
import wob.city.dto.ConsumptionDTO;

import java.util.HashMap;

public class ConsumptionNews extends NewsPaper {
    public ConsumptionNews(){
        super("ConsumptionNews", new HashMap<String, Double>());
        flushData();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addData(Object data) {
        if(((ConsumptionDTO) data).getType().equals("meat") || ((ConsumptionDTO) data).getType().equals("grain") ||
        ((ConsumptionDTO) data).getType().equals("vegetable") || ((ConsumptionDTO) data).getType().equals("dairy")) {
            ((HashMap<String, Double>) this.getData()).put(((ConsumptionDTO) data).getType(),
                    ((HashMap<String, Double>) this.getData()).get(((ConsumptionDTO) data).getType()) + ((ConsumptionDTO) data).getAmount() );
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void flushData() {
        ((HashMap<String, Double>) this.getData()).put("meat", 0.0);
        ((HashMap<String, Double>) this.getData()).put("dairy", 0.0);
        ((HashMap<String, Double>) this.getData()).put("vegetable", 0.0);
        ((HashMap<String, Double>) this.getData()).put("grain", 0.0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String toString() {
        return "{" +
               "\n  meat: " + ((HashMap<String, Double>) this.getData()).get("meat") + "," +
               "\n  dairy:" + ((HashMap<String, Double>) this.getData()).get("dairy") + "," +
               "\n  vegetable: " + ((HashMap<String, Double>) this.getData()).get("vegetable") + "," +
               "\n  grain: " + ((HashMap<String, Double>) this.getData()).get("grain") +
               "\n}";
    }
}
