package kz.segizbay.spring_web.controllers;

import kz.segizbay.spring_web.converters.ProductConverter;
import kz.segizbay.spring_web.dtos.ProductDTO;
import kz.segizbay.spring_web.exceptions.ResourceNotFoundException;
import kz.segizbay.spring_web.model.Product;
import kz.segizbay.spring_web.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @GetMapping
    public Page<ProductDTO> findAll(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer max_price,
            @RequestParam(name = "title_part", required = false) String title_part
    ) {
        if (page<1){
            page = 1;
        }
        return productService.finaAll(minPrice, max_price, title_part, page)
                .map(p -> productConverter.entityToDTO(p));
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id){
        Product product = productService.findtById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        return productConverter.entityToDTO(product);
    }

    @PostMapping
    public ProductDTO save(@RequestBody ProductDTO productDTO){
        Product product = productConverter.dtoToEntity(productDTO);
        product.setId(null);
        Product save = productService.save(product);
        return productConverter.entityToDTO(save);
    }
}
