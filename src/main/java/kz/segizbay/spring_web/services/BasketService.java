package kz.segizbay.spring_web.services;

import kz.segizbay.spring_web.model.Basket;
import kz.segizbay.spring_web.model.Product;
import kz.segizbay.spring_web.repositories.BasketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BasketService {
    private final BasketRepository basketRepository;
    private final ProductService productService;

    public List<Basket> findAll(){
        return basketRepository.findAll();
    }

    @Transactional
    public void add(Long product_id) {
        Product product = productService.findtById(product_id);
        Optional<Basket> updatedBasket = basketRepository.findBasketByProduct(product);
        if (updatedBasket.isPresent()){
            Basket basket = updatedBasket.get();
            basket.setCountProducts(basket.getCountProducts()+1);
        } else {
            basketRepository.save(new Basket(1, product));
        }
    }

    @Transactional
    public void deleteById(Long id) {
        basketRepository.deleteById(id);
    }
}
