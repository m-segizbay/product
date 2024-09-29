package kz.segizbay.spring_web.controllers;

import kz.segizbay.spring_web.converters.BasketConverter;
import kz.segizbay.spring_web.dtos.BasketDTO;
import kz.segizbay.spring_web.services.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/baskets")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;
    private final BasketConverter basketConverter;

    @GetMapping
    public List<BasketDTO> findAll(){
        return basketService.findAll().stream().map(b -> basketConverter.entityToDTO(b)).toList();
    }

    @GetMapping("/{id}/add")
    public void add(@PathVariable("id") Long product_id){
        basketService.add(product_id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        basketService.deleteById(id);
    }
}
