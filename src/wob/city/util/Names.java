package wob.city.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Names {
    private final List<String> femaleNames = new ArrayList<>();
    private final List<String> maleNames = new ArrayList<>();
    private final List<String> lastNames = new ArrayList<>();

    public Names() {
        fillUpList("femaleNames.txt", femaleNames);
        fillUpList("maleNames.txt", maleNames);
        fillUpList("lastNames.txt", lastNames);
    }

    private void fillUpList(String fromFile, List<String> toList) {
        try (FileReader fileReader = new FileReader(fromFile); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String current;
            while ((current = bufferedReader.readLine()) != null) {
                toList.add(current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFemaleName() {
        return getRandomName(femaleNames);
    }

    public String getMaleName() {
        return getRandomName(maleNames);
    }

    public String getLastName() {
        return getRandomName(lastNames);
    }

    private String getRandomName(List<String> fromList) {
        return fromList.get(Calculations.getRandomIntBetween(0, fromList.size() - 1));
    }
}
