package com.kackan.system_to_manage_cars_with_database.repository;

import com.kackan.system_to_manage_cars_with_database.exception.IdDuplicationException;
import com.kackan.system_to_manage_cars_with_database.model.Car;

import java.util.List;

public interface CarDao {

    List<Car> findAll();
    Car save(Car car) throws IdDuplicationException;
    Car delete(long id);
    Car  getOne(long id);
    Car update(Car newCar, long id);
    List<Car> findAllByYear(int yearOfProductionDownLimit, int yearOfProductionUpLimit);
}
