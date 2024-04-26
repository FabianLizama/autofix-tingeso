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
            "John Doe"
        );
        entityManager.persistAndFlush(car);
        
        // when
        CarEntity found = carRepository.findByLicensePlate(car.getLicensePlate());

        // then
        assertThat(found.getLicensePlate())
            .isEqualTo(car.getLicensePlate());
    }
}
