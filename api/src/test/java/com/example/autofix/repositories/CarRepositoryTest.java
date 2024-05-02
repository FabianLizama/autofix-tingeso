package com.example.autofix.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.example.autofix.entities.CarEntity;

@DataJpaTest
@ActiveProfiles("test")
class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private CarRepository carRepository;

    @Test
    public void saveCarTest() {
        // given
        CarEntity car = new CarEntity(
            null,
            "ABC123",
            "Toyota",
            "Corolla",
            "Sedan",
            2020,
            "Gasoline",
            5,
            "123456789",
            "John Doe",
            100
        );

        // when
        CarEntity saved = carRepository.save(car);

        // then
        assertThat(saved.getLicensePlate())
            .isEqualTo(car.getLicensePlate());
    }

    @Test
    public void findAllCarsTest() {
        // given
        CarEntity car1 = new CarEntity(
            null,
            "ABC123",
            "Toyota",
            "Corolla",
            "Sedan",
            2020,
            "Gasoline",
            5,
            "123456789",
            "John Doe",
            100
        );
        entityManager.persist(car1);

        CarEntity car2 = new CarEntity(
            null,
            "DEF456",
            "Toyota",
            "Corolla",
            "Sedan",
            2020,
            "Gasoline",
            5,
            "123456789",
            "John Doe",
            100
        );
        entityManager.persistAndFlush(car2);

        // when
        Iterable<CarEntity> cars = carRepository.findAll();

        // then
        assertThat(cars).hasSize(2).contains(car1, car2);
    }

    @Test
    public void getCarByIdTest(){
        // given
        CarEntity car = new CarEntity(
            null,
            "ABC123",
            "Toyota",
            "Corolla",
            "Sedan",
            2020,
            "Gasoline",
            5,
            "123456789",
            "John Doe",
            100
        );
        entityManager.persistAndFlush(car);

        // when
        CarEntity found = carRepository.findById(car.getId()).orElse(null);

        // then
        assertThat(found.getId())
            .isEqualTo(car.getId());
    }

    @Test
    public void findByLicensePlateTest() {
        // given
        CarEntity car = new CarEntity(
            null,
            "ABC123",
            "Toyota",
            "Corolla",
            "Sedan",
            2020,
            "Gasoline",
            5,
            "123456789",
            "John Doe",
            100
        );
        entityManager.persistAndFlush(car);
        
        // when
        CarEntity found = carRepository.findByLicensePlate(car.getLicensePlate());

        // then
        assertThat(found.getLicensePlate())
            .isEqualTo(car.getLicensePlate());
    }

    @Test
    public void updateCarTest() {
        // given
        CarEntity car = new CarEntity(
            null,
            "ABC123",
            "Toyota",
            "Corolla",
            "Sedan",
            2020,
            "Gasoline",
            5,
            "123456789",
            "John Doe",
            100
        );
        entityManager.persistAndFlush(car);

        // when
        car.setBrand("Chevrolet");
        CarEntity updated = carRepository.save(car);

        // then
        assertThat(updated.getBrand())
            .isEqualTo("Chevrolet");
    }

    @Test
    public void deleteCarTest() {
        // given
        CarEntity car = new CarEntity(
            null,
            "ABC123",
            "Toyota",
            "Corolla",
            "Sedan",
            2020,
            "Gasoline",
            5,
            "123456789",
            "John Doe",
            100
        );
        entityManager.persistAndFlush(car);

        // when
        carRepository.deleteById(car.getId());

        // then
        assertThat(carRepository.findById(car.getId()).orElse(null))
            .isNull();
    }
}
