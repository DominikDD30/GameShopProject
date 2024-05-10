package com.project.gameHubBackend.infrastructure.database.repository.jpa;

import com.project.gameHubBackend.infrastructure.database.entity.CustomerEntity;
import com.project.gameHubBackend.integration.configuration.PersistenceContainerTestConfiguration;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static com.project.gameHubBackend.util.EntityFixturesT.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CustomerJpaRepositoryTest {

    private CustomerJpaRepository customerJpaRepository;


    @Test
    void thatCustomerCanBeSavedCorrectly() {
        //given
        CustomerEntity someCustomer = getSomeCustomer1();
        customerJpaRepository.save(someCustomer);
        //when
        CustomerEntity result = customerJpaRepository.findByEmail(someCustomer.getEmail());
        //then
        Assertions.assertThat(result)
                .usingRecursiveComparison()
                .ignoringFields("customerId")
                .isEqualTo(someCustomer);
    }




}