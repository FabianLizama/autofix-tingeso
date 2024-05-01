package com.example.autofix.repositories;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.example.autofix.entities.ChargueByTypeEntity;

@DataJpaTest
@ActiveProfiles("test")
public class ChargueByTypeRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ChargueByTypeRepository chargueByTypeRepository;

    @Test
    public void saveChargueByTypeTest() {
        // given
        ChargueByTypeEntity chargueByType = new ChargueByTypeEntity(
            1L,
            "km",
            0,
            1000,
            0,
            0,
            0.1,
            0.2,
            0.3,
            0.4,
            0.5
        );

        // when
        ChargueByTypeEntity saved = chargueByTypeRepository.save(chargueByType);

        // then
        assertThat(saved.getSurchargeType())
            .isEqualTo(chargueByType.getSurchargeType());
    }

    @Test
    public void findAllChargueByTypesTest() {
        // given
        ChargueByTypeEntity chargueByType1 = new ChargueByTypeEntity(
            null,
            "km",
            0,
            1000,
            0,
            0,
            0.1,
            0.2,
            0.3,
            0.4,
            0.5
        );

        entityManager.persist(chargueByType1);

        ChargueByTypeEntity chargueByType2 = new ChargueByTypeEntity(
            null,
            "antiquity",
            0,
            0,
            0,
            10,
            0.1,
            0.2,
            0.3,
            0.4,
            0.5
        );
        entityManager.persistAndFlush(chargueByType2);

        // when
        Iterable<ChargueByTypeEntity> chargueByTypes = chargueByTypeRepository.findAll();

        // then
        assertThat(chargueByTypes).hasSize(2).contains(chargueByType1, chargueByType2);
    }

    @Test
    public void getAllKmChargueByTypes() {
        // given
        ChargueByTypeEntity chargueByType1 = new ChargueByTypeEntity(
            null,
            "km",
            0,
            1000,
            0,
            0,
            0.1,
            0.2,
            0.3,
            0.4,
            0.5
        );

        entityManager.persist(chargueByType1);

        ChargueByTypeEntity chargueByType2 = new ChargueByTypeEntity(
            null,
            "antiquity",
            0,
            0,
            0,
            10,
            0.1,
            0.2,
            0.3,
            0.4,
            0.5
        );
        entityManager.persistAndFlush(chargueByType2);

        // when
        Iterable<ChargueByTypeEntity> chargueByTypes = chargueByTypeRepository.findBySurchargeType("km");

        // then
        assertThat(chargueByTypes).hasSize(1).contains(chargueByType1);
    }

    @Test
    public void getAllAntiqChargueByTypes() {
        // given
        ChargueByTypeEntity chargueByType1 = new ChargueByTypeEntity(
            null,
            "km",
            0,
            1000,
            0,
            0,
            0.1,
            0.2,
            0.3,
            0.4,
            0.5
        );

        entityManager.persist(chargueByType1);

        ChargueByTypeEntity chargueByType2 = new ChargueByTypeEntity(
            null,
            "antiquity",
            0,
            0,
            0,
            10,
            0.1,
            0.2,
            0.3,
            0.4,
            0.5
        );
        entityManager.persistAndFlush(chargueByType2);

        // when
        Iterable<ChargueByTypeEntity> chargueByTypes = chargueByTypeRepository.findBySurchargeType("antiquity");

        // then
        assertThat(chargueByTypes).hasSize(1).contains(chargueByType2);
    }

    @Test
    public void getChargueByTypeByIdTest() {
        // given
        ChargueByTypeEntity chargueByType = new ChargueByTypeEntity(
            null,
            "km",
            0,
            1000,
            0,
            0,
            0.1,
            0.2,
            0.3,
            0.4,
            0.5
        );

        entityManager.persist(chargueByType);

        // when
        ChargueByTypeEntity found = chargueByTypeRepository.findById(chargueByType.getId()).orElse(null);

        // then
        assertThat(found.getId())
            .isEqualTo(chargueByType.getId());
    }

    @Test
    public void updateChargueByTypeTest() {
        // given
        ChargueByTypeEntity chargueByType = new ChargueByTypeEntity(
            null,
            "km",
            0,
            1000,
            0,
            0,
            0.1,
            0.2,
            0.3,
            0.4,
            0.5
        );

        entityManager.persist(chargueByType);

        ChargueByTypeEntity chargueByTypeUpdated = new ChargueByTypeEntity(
            chargueByType.getId(),
            "antiquity",
            0,
            0,
            0,
            10,
            0.1,
            0.2,
            0.3,
            0.4,
            0.5
        );

        // when
        ChargueByTypeEntity updated = chargueByTypeRepository.save(chargueByTypeUpdated);

        // then
        assertThat(updated.getSurchargeType())
            .isEqualTo(chargueByTypeUpdated.getSurchargeType());
    }

}
