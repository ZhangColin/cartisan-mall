package com.cartisan.mall.goods.brand;

import com.cartisan.dto.PageResult;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.repository.ConditionSpecifications.querySpecification;
import static com.cartisan.util.AssertionUtil.requirePresent;

/**
 * @author colin
 */
@Service
public class BrandAppService {
    private final BrandRepository repository;

    private final BrandConverter converter = BrandConverter.CONVERTER;

    public BrandAppService(BrandRepository repository) {
        this.repository = repository;
    }

    public PageResult<BrandDto> searchBrands(@NonNull BrandQuery brandQuery, @NonNull Pageable pageable) {
        final Page<Brand> searchResult = repository.findAll(querySpecification(brandQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public List<BrandDto> getBrands(@NonNull BrandQuery brandQuery) {
        return converter.convert(repository.findAll(querySpecification(brandQuery)));
    }

    public BrandDto getBrand(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public BrandDto addBrand(BrandParam brandParam) {
        final Brand brand = new Brand(brandParam.getName(),
                brandParam.getImage(),
                brandParam.getLetter(),
                brandParam.getSequence());

        return converter.convert(repository.save(brand));
    }

    @Transactional(rollbackOn = Exception.class)
    public BrandDto editBrand(Long id, BrandParam brandParam) {
        final Brand brand = requirePresent(repository.findById(id));

        brand.describe(brandParam.getName(),
                brandParam.getImage(),
                brandParam.getLetter(),
                brandParam.getSequence());

        return converter.convert(repository.save(brand));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeBrand(long id) {
        repository.deleteById(id);
    }
}