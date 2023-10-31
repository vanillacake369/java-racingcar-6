package racingcar.domain.car;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.utils.StringTrimmer;

public final class CarDtoMapper {
    private CarDtoMapper() {
    }

    public static List<Car> mapToCar(CarDto carDto) {
        String carNamesWithCommas = carDto.carNamesWithCommas();
        String[] trimAndSplitCarNames = StringTrimmer.trimAndSplit(carNamesWithCommas);

        List<Car> cars = new ArrayList<>();
        for (String carName : trimAndSplitCarNames) {
            cars.add(Car.createCar(carName, 0));
        }

        return cars;
    }
}
