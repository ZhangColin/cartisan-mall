package com.cartisan.mall.goods.category;

import com.cartisan.dto.PageResult;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.cartisan.repository.ConditionSpecifications.querySpecification;
import static com.cartisan.util.AssertionUtil.requirePresent;

@Service
public class CategoryAppService {
    private final CategoryRepository repository;

    private final CategoryConverter converter = CategoryConverter.CONVERTER;

    public CategoryAppService(CategoryRepository repository) {
        this.repository = repository;
    }

    public PageResult<CategoryDto> searchCategories(@NonNull CategoryQuery categoryQuery, @NonNull Pageable pageable) {
        final Page<Category> searchResult = repository.findAll(querySpecification(categoryQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public CategoryDto getCategory(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public CategoryDto addCategory(CategoryParam categoryParam) {
        final Category category = new Category(categoryParam.getParentId(),
        categoryParam.getTemplateId(),
        categoryParam.getName(),
        categoryParam.getIsShow(),
        categoryParam.getIsMenu(),
        categoryParam.getSequence());

        return converter.convert(repository.save(category));
    }

    @Transactional(rollbackOn = Exception.class)
    public CategoryDto editCategory(Long id, CategoryParam categoryParam) {
        final Category category = requirePresent(repository.findById(id));

        category.describe(categoryParam.getParentId(),
        categoryParam.getTemplateId(),
        categoryParam.getName(),
        categoryParam.getIsShow(),
        categoryParam.getIsMenu(),
        categoryParam.getSequence());

        return converter.convert(repository.save(category));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeCategory(long id) {
        repository.deleteById(id);
    }
}
