package ru.webarmour.weatercompose.domain.usecase

import ru.webarmour.weatercompose.domain.repository.FavouriteRepository
import ru.webarmour.weatercompose.domain.repository.WeatherRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

   suspend operator fun invoke(cityId:Int) = weatherRepository.getForecast(cityId)
}