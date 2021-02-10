package com.kackan.system_to_manage_cars_with_database.repository;

import com.kackan.system_to_manage_cars_with_database.exception.IdDuplicationException;
import com.kackan.system_to_manage_cars_with_database.model.Car;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CarDaoImpl implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> findAll() {
        List<Car>cars = new ArrayList<>();
        String sql = "SELECT * from cars";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return getCars(cars, maps);
    }

    @Override
    public List<Car> findAllByYear(int yearOfProductionDownLimit, int yearOfProductionUpLimit) {

        List<Car> cars=new ArrayList<>();
        String sql="SELECT * from cars where cars.year_of_production BETWEEN ? and ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, yearOfProductionDownLimit, yearOfProductionUpLimit);
        return getCars(cars, maps);
    }

    @Override
    public Car save(Car car) throws IdDuplicationException {
        String sql="INSERT INTO cars values(?,?,?,?,?)";

            carIdExist(car);
            int update = jdbcTemplate.update(sql, car.getId(), car.getMark(), car.getModel(), car.getColor(), car.getYearOfProduction());
            if(update>0){
                return car;
            }else{
                return null;
            }
    }

    @Override
    public Car delete(long id) {
        Car one = getOne(id);
        String sql = "DELETE from cars where car_id = ?";
        int update = jdbcTemplate.update(sql, id);
        if(update>0){
            return one;
        }else{
            return null;
        }
    }

    @Override
    public Car getOne(long id) {

        String sql="Select * from cars where cars.car_id=?";
        return jdbcTemplate.queryForObject(sql, (rs, i) -> new Car(rs.getLong("car_id"), rs.getString("mark"), rs.getString("model"), rs.getString("color"), rs.getInt("year_of_production")), id);
    }

    @Override
    public Car update(Car newCar, long id) {
        String sql="UPDATE cars SET cars.mark=?, cars.model=?, cars.color=?, cars.year_of_production=? where cars.car_id=?";
        int update = jdbcTemplate.update(sql, newCar.getMark(), newCar.getModel(), newCar.getColor(), newCar.getYearOfProduction(), id);

        if(update>0)
        {
            return newCar;
        }else{
            return null;
        }
    }

    public void carIdExist(Car car) throws IdDuplicationException {
        List<Car> all = findAll();
        boolean present = all.stream().anyMatch(c -> c.getId()==(car.getId()));
        if(present){
                throw new IdDuplicationException("The same id exists in db");
            }
        }

    private List<Car> getCars(List<Car> cars, List<Map<String, Object>> maps) {
        maps.forEach(el->{
           cars.add(new Car(Long.parseLong(String.valueOf(el.get("car_id")), 10),
                   String.valueOf(el.get("model")),
                   String.valueOf(el.get("mark")),
                   String.valueOf(el.get("color")),
                   Integer.parseInt(String.valueOf(el.get("year_of_production")))));
        });
         return cars;
    }

}
