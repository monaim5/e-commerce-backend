package com.app.ecommerce.services.interfaces;

import com.app.ecommerce.dto.ProductDto;

import java.util.List;

public interface ProductService {
    public List<ProductDto> list();
    public ProductDto retrieve(Long id);
    public ProductDto create(ProductDto productDto);
    public ProductDto update(Long id, ProductDto productDto);
    public void destroy(Long id);
}
