package com.example.product.Controller;

import com.example.product.Entity.Charges;
import com.example.product.Entity.Product;
import com.example.product.Payload.ProductDto;
import com.example.product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        ProductDto productResponseDto = createProductResponseDto(product);
        return ResponseEntity.ok(productResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productResponseDtos = products.stream().map(this::createProductResponseDto).collect(Collectors.toList());
        return ResponseEntity.ok(productResponseDtos);
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productRequestDto) {
        Product product = createProductFromRequestDto(productRequestDto);
        product = productService.addProduct(product);
        ProductDto productResponseDto = createProductResponseDto(product);
        return ResponseEntity.ok(productResponseDto);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productRequestDto) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        product = updateProductFromRequestDto(productRequestDto, product);
        product = productService.updateProduct(product);
        ProductDto productResponseDto = createProductResponseDto(product);
        return ResponseEntity.ok(productResponseDto);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    private Product createProductFromRequestDto(ProductDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setProductType(productRequestDto.getProductType());
        product.setCategory(productRequestDto.getCategory());
        product.setBasePrice(productRequestDto.getBasePrice());
        product.setDiscount(productRequestDto.getDiscount());
        product.setFinalPrice(productRequestDto.getFinalPrice());

        Charges charge = new Charges();
        charge.setGst(productRequestDto.getGst());
        charge.setDelivery(productRequestDto.getDelivery());
        product.setCharge(charge);

        return product;
    }

    private Product updateProductFromRequestDto(ProductDto productRequestDto, Product product) {
        product.setName(productRequestDto.getName());
        product.setProductType(productRequestDto.getProductType());
        product.setCategory(productRequestDto.getCategory());
        product.setBasePrice(productRequestDto.getBasePrice());
        product.setDiscount(productRequestDto.getDiscount());
        product.setFinalPrice(productRequestDto.getFinalPrice());

        Charges charge = product.getCharge();
        charge.setGst(productRequestDto.getGst());
        charge.setDelivery(productRequestDto.getDelivery());

        return product;
    }

    private ProductDto createProductResponseDto(Product product) {
        ProductDto productResponseDto = new ProductDto();
        productResponseDto.setProductId(product.getProductId());
        productResponseDto.setName(product.getName());
        productResponseDto.setProductType(product.getProductType());
        productResponseDto.setCategory(product.getCategory());
        productResponseDto.setBasePrice(product.getBasePrice());
        productResponseDto.setDiscount(product.getDiscount());
        productResponseDto.setFinalPrice(product.getFinalPrice());

        Charges charge = product.getCharge();
        productResponseDto.setGst(charge.getGst());
        productResponseDto.setDelivery(charge.getDelivery());

        return productResponseDto;

    }
}