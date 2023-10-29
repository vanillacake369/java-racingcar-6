package racingcar.domain.race;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.Car;

class RaceServiceTest {

    private RaceService raceService;

    @BeforeEach
    void setUp() {
        raceService = RaceService.getInstance();
    }

    @AfterEach
    void tearDown() {
        raceService = null;
    }

    @Test
    @DisplayName("입력문자열을 검증하고, 입력으부터 자동차들을 생성합니다.")
    void getCarsFromInput() {
        // GIVEN
        String raceCarNames = "pobi,woni,jun";

        // WHEN
        List<Car> cars = raceService.getCarsFromInput(raceCarNames);

        // THEN
        assertEquals(3, cars.size());
        assertEquals("pobi", cars.get(0).getName());
        assertEquals("woni", cars.get(1).getName());
        assertEquals("jun", cars.get(2).getName());

    }

    @Test
    @DisplayName("입력문자열을 검증하고, 입력으부터 시도횟수를 생성합니다.")
    void getRoundFromInput() {
        // GIVEN
        String roundInput = "3";
        int expectedRound = 3;

        // WHEN
        int round = raceService.getRoundFromInput(roundInput);

        // THEN
        assertEquals(expectedRound, round);
    }

    @Test
    @DisplayName("조건에 따른 이동을 합니다.")
    void moveByStrategy() {
        // GIVEN
        List<Car> cars = new ArrayList<>();
        cars.add(Car.createCar("pobi", 0));
        cars.add(Car.createCar("woni", 0));
        cars.add(Car.createCar("jun", 0));

        // WHEN
        raceService.moveByStrategy(cars);

        // THEN
        int movesSum = cars.get(0).getCurrentLocation() + cars.get(1).getCurrentLocation()
                + cars.get(2).getCurrentLocation();
        assertTrue(movesSum > 0);
        assertTrue(movesSum <= 3);
        System.out.println(RaceView.printCarsStatus(cars));
    }

    @Test
    @DisplayName("시도횟수만큼 조건에 따른 이동을 합니다.")
    void raceByTrial() {
        // GIVEN
        List<Car> cars = new ArrayList<>();
        cars.add(Car.createCar("pobi", 0));
        cars.add(Car.createCar("woni", 0));
        cars.add(Car.createCar("jun", 0));
        int round = 4;

        // WHEN
        raceService.raceForRounds(cars, round);

        // THEN
        int movesSum = cars.get(0).getCurrentLocation() + cars.get(1).getCurrentLocation()
                + cars.get(2).getCurrentLocation();
        assertTrue(movesSum > 0);
        assertTrue(movesSum <= cars.size() * round);
    }

    @Test
    @DisplayName("우승자들을 구합니다.")
    public void getSingularWinners() throws Exception {
        // GIVEN
        List<Car> cars = new ArrayList<>();
        Car pobi = Car.createCar("pobi", 4);
        Car woni = Car.createCar("woni", 3);
        Car jun = Car.createCar("jun", 2);
        cars.add(pobi);
        cars.add(woni);
        cars.add(jun);
        List<Car> expectedWinners = new ArrayList<>();
        expectedWinners.add(pobi);

        // WHEN
        List<Car> actualWinners = raceService.getWinners(cars);

        // THEN
        assertEquals(expectedWinners, actualWinners);
    }

    @Test
    @DisplayName("동순위를 포함한 이름 오름차순 우승자들을 구합니다.")
    public void getMultipleWinners() throws Exception {
        // GIVEN
        List<Car> cars = new ArrayList<>();
        Car coni = Car.createCar("coni", 4);
        Car boni = Car.createCar("boni", 4);
        Car aobi = Car.createCar("aobi", 4);
        Car jun = Car.createCar("jun", 2);
        cars.add(aobi);
        cars.add(boni);
        cars.add(coni);
        cars.add(jun);
        List<Car> expectedWinners = new ArrayList<>();
        expectedWinners.add(aobi);
        expectedWinners.add(boni);
        expectedWinners.add(coni);

        // WHEN
        List<Car> actualWinners = raceService.getWinners(cars);

        // THEN
        assertEquals(expectedWinners, actualWinners);
    }
}