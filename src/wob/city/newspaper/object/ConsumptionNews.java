package wob.city.newspaper.object;

import wob.city.newspaper.abstraction.NewsPaper;
import wob.city.newspaper.dto.ConsumptionDTO;
import wob.city.util.Calculations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConsumptionNews extends NewsPaper {
    private final HashMap<String, Double> consumptionData;

    @SuppressWarnings("unchecked")
    public ConsumptionNews(){
        super("ConsumptionNews", new HashMap<String, Double>(), true);
        this.consumptionData = (HashMap<String, Double>) this.getData();
        flushData();

    }

    @Override
    public void addData(Object data) {
        List<String> acceptedTypes = Arrays.asList("Meat", "Grain", "Vegetable", "Dairy");
        ConsumptionDTO consumptionDTO = (ConsumptionDTO) data;

        if(acceptedTypes.contains(consumptionDTO.getType())) {
            consumptionData.put(consumptionDTO.getType(), consumptionData.get(consumptionDTO.getType()) + consumptionDTO.getAmount() );
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void flushData() {
        consumptionData.put("Meat", 0.0);
        consumptionData.put("Dairy", 0.0);
        consumptionData.put("Vegetable", 0.0);
        consumptionData.put("Grain", 0.0);
    }

    @Override
    public String toString() {
        return "{" +
               "\n  \"meat\": \"" + Calculations.round(consumptionData.get("Meat") / 1000, 2) + "kg\"," +
               "\n  \"dairy\": \"" + Calculations.round(consumptionData.get("Dairy") / 1000, 2)  + "kg\"," +
               "\n  \"vegetable\": \"" + Calculations.round(consumptionData.get("Vegetable") / 1000, 2) + "kg\"," +
               "\n  \"grain\": \"" + Calculations.round(consumptionData.get("Grain") / 1000, 2) + "kg\"" +
               "\n}";
    }
}
