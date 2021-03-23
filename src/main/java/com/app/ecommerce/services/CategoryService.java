package com.app.ecommerce.services;

import com.app.ecommerce.models.dtos.CategoryDto;
import com.app.ecommerce.models.entities.Category;
import com.app.ecommerce.models.mappers.CategoryMapper;
import com.app.ecommerce.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = this.categoryRepository.save(
                categoryMapper.toEntity(categoryDto)
        );
        categoryDto.setId(category.getId());
        return categoryDto;
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> getAll() {
        return this.categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

//    private Category mapFromDto(CategoryDto categoryDto) {
//        return Category.builder()
//                .name(categoryDto.getName())
//                .description(categoryDto.getDescription())
//                .photo(categoryDto.getPhoto())
//                .icon(categoryDto.getIcon())
//                .build();
//    }

//    private CategoryDto mapToDto(Category category) {
//        return CategoryDto.builder()
//                .id(category.getId())
//                .name(category.getName())
//                .description(category.getDescription())
//                .photo(category.getPhoto())
//                .icon(category.getIcon())
//                .build();
//    }
}
