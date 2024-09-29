package kz.segizbay.spring_web.converters;

import kz.segizbay.spring_web.dtos.BasketDTO;
import kz.segizbay.spring_web.dtos.ProductDTO;
import kz.segizbay.spring_web.model.Basket;
import kz.segizbay.spring_web.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BasketConverter {
    private final ProductConverter productConverter;
    public Basket dtoToEntity(BasketDTO basketDTO){
        return new Basket(basketDTO.getId(), basketDTO.getCountProducts(), productConverter.dtoToEntity(basketDTO.getProductDTO()));
    }

    public BasketDTO entityToDTO(Basket basket){
        return new BasketDTO(basket.getId(), basket.getCountProducts(), productConverter.entityToDTO(basket.getProduct()));
    }
}
