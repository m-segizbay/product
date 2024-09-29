package kz.segizbay.spring_web.converters;

import kz.segizbay.spring_web.dtos.ProductDTO;
import kz.segizbay.spring_web.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDTO productDTO){
        return new Product(productDTO.getId(), productDTO.getTitle(), productDTO.getPrice());
    }

    public ProductDTO entityToDTO(Product product){
        return new ProductDTO(product.getId(), product.getTitle(), product.getPrice());
    }
}
