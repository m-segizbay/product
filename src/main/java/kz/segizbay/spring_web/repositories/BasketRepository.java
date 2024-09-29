package kz.segizbay.spring_web.repositories;

import kz.segizbay.spring_web.model.Basket;
import kz.segizbay.spring_web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findBasketByProduct(Product product);
}
