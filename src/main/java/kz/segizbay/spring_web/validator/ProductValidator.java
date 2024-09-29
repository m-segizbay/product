package kz.segizbay.spring_web.validator;

import kz.segizbay.spring_web.dtos.ProductDTO;
import kz.segizbay.spring_web.exceptions.ValidationException;
import kz.segizbay.spring_web.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductValidator{
    public void validate(ProductDTO productDTO){
        List<String> list = new ArrayList<>();
        if (productDTO.getPrice()<1){
            list.add("Цена продукта не может быть меньше 1");
        }
        if (productDTO.getTitle().isBlank()){
            list.add("Продукт не может иметь пустое ззначение");
        }

        if (!list.isEmpty()){
            throw new ValidationException(list);
        }
    }
}
