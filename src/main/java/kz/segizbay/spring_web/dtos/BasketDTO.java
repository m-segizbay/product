package kz.segizbay.spring_web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketDTO {
    private Long id;
    private Integer countProducts;
    private ProductDTO productDTO;
}
