package com.cartisan.mall.goods.brand.application;

import com.cartisan.mall.goods.brand.Brand;
import com.cartisan.mall.goods.brand.BrandRepository;
import com.cartisan.mall.goods.brand.mapper.BrandCategoryMapper;
import com.cartisan.mall.goods.brand.request.BuildBrandCategoryRelationCommand;
import com.cartisan.mall.goods.brand.request.CancelBrandCategoryRelationCommand;
import com.cartisan.mall.goods.brand.response.BrandCategoryDto;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.util.AssertionUtil.requirePresent;

/**
 * @author colin
 */
@Service
public class BrandCategoryAppService {
    private final BrandRepository repository;
    private final BrandCategoryMapper brandCategoryMapper;

    public BrandCategoryAppService(BrandRepository repository, BrandCategoryMapper brandCategoryMapper) {
        this.repository = repository;
        this.brandCategoryMapper = brandCategoryMapper;
    }

    public List<BrandCategoryDto> getByBrand(@NonNull Long brandId) {
        return brandCategoryMapper.getByBrand(brandId);
    }

    public List<BrandCategoryDto> getByCategory(@NonNull Long categoryId) {
        return brandCategoryMapper.getByCategory(categoryId);
    }

    @Transactional(rollbackOn = Exception.class)
    public void buildRelation(BuildBrandCategoryRelationCommand command) {
        final Brand brand = requirePresent(repository.findById(command.getBrandId()));

        brand.addCategory(command.getCategoryId());

        repository.save(brand);
    }

    @Transactional(rollbackOn = Exception.class)
    public void cancelRelation(CancelBrandCategoryRelationCommand command) {
        final Brand brand = requirePresent(repository.findById(command.getBrandId()));

        brand.removeCategory(command.getCategoryId());

        repository.save(brand);
    }
}
