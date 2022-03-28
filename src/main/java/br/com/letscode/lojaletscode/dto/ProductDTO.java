package br.com.letscode.lojaletscode.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Setter
@Getter
public class ProductDTO {

    @NotNull(message = "name is required")
    @Size(message = "name must be equal to or lower than 300", min = 1, max = 300)
    private String name;

    @NotNull(message = "name is required")
    @Min(0)
    private BigDecimal price;
}
