package com.shopping.cart.repository;

import com.shopping.cart.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person getByUsername(String username);

    boolean existsByEmailIgnoreCase(String email);

}
