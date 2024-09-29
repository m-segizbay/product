package kz.segizbay.spring_web.services;

import kz.segizbay.spring_web.model.Product;
import kz.segizbay.spring_web.repositories.ProductRepository;
import kz.segizbay.spring_web.repositories.specifications.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> finaAll(Integer minPrice, Integer maxPrice, String titlePart, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecification.priceGraterOrEqualsThan(minPrice));
        }
        if (maxPrice != null){
            spec = spec.and(ProductSpecification.priceLessOrEqualsThan(maxPrice));
        }
        if (titlePart != null){
            spec = spec.and(ProductSpecification.titleLike(titlePart));
        }
        return productRepository.findAll(spec, PageRequest.of(page-1, 5));
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> findtById(long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
