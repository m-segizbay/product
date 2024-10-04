package kz.segizbay.spring_web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer price;

    @OneToOne(mappedBy = "product")
    private Basket basket;

    public Product(Long id, String title, Integer price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
}
