package br.com.car.core.mapper

import br.com.car.domain.http.CarHttp
import br.com.car.domain.model.Car

object CarHttpToModelMapper {

    fun toModel(cars: List<CarHttp>): List<Car> {
        var count = 0L
        return cars.map { car ->
            Car(
                id = count++,
                name = car.model,
                model = car.make,
                year = car.year
            ).isEligibleToUber()
        }
    }
}