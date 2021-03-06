package racinggame.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racinggame.domain.Car;
import racinggame.domain.LeagueHistory;
import racinggame.domain.TrialHistory;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarRacingTest {
    private List<Car> cars;
    private int totalTrial;

    @BeforeEach
    void setUp() {
        cars = Arrays.asList(new Car("pobi"), new Car("crong"));
        totalTrial = 3;
    }

    @Test
    void 객체를_제대로_생성하는지_테스트() {
        /* Then */
        assertThat(new CarRacing(cars, totalTrial)).isEqualTo(new CarRacing(cars, 3));
    }

    @Test
    void 리그가_제대로_진행되는지_확인() {
        /* Given */
        CarRacing carRacing = new CarRacing(cars, totalTrial);
        LeagueHistory leagueHistory = new LeagueHistory();

        /* When */
        CarRaceResult carRaceResult = carRacing.raceStart(new CarAlwaysRun());
        for (int i = 1; i <= totalTrial; i++) {
            leagueHistory.record(i, new TrialHistory(Arrays.asList(new Car("pobi", i),
                    new Car("crong", i))));
        }

        /* Then */
        assertThat(carRaceResult).isEqualTo(new CarRaceResult(leagueHistory, totalTrial));
    }
}