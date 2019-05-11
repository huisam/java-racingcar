package racinggame.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrialHistory {
    private Map<String, Integer> trialHistory = new LinkedHashMap<>();

    public TrialHistory(final List<Car> cars) {
        for (Car car : cars) {
            trialHistory.putAll(car.makeCarHistory());
        }
    }

    public List<String> findWinners() {
        final int maxCarPosition = getMaxCarPosition();

        return trialHistory.keySet().stream()
                .filter(carName -> trialHistory.get(carName) == maxCarPosition)
                .collect(Collectors.toList());
    }

    private int getMaxCarPosition() {
        int maxCarPosition = 0;

        for (String carName : trialHistory.keySet()) {
            maxCarPosition = Math.max(trialHistory.get(carName), maxCarPosition);
        }
        return maxCarPosition;
    }

    @Override
    public String toString() {
        StringBuilder history = new StringBuilder();

        for (String carName : trialHistory.keySet()) {
            history.append(carName).append(" : ");
            for (int i = 0; i < trialHistory.get(carName); i++) {
                history.append('-');
            }
            history.append('\n');
        }
        return history.toString();
    }
}
