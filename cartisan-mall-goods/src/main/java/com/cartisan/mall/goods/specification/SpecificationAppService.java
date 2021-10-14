package com.cartisan.mall.goods.specification;

import com.cartisan.constant.CodeMessage;
import com.cartisan.dto.PageResult;
import com.cartisan.exception.CartisanException;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.repository.ConditionSpecifications.querySpecification;
import static com.cartisan.util.AssertionUtil.requirePresent;
import static java.util.stream.Collectors.toList;

@Service
public class SpecificationAppService {
    private final SpecificationRepository repository;

    private final SpecificationConverter converter = SpecificationConverter.CONVERTER;

    public SpecificationAppService(SpecificationRepository repository) {
        this.repository = repository;
    }

    public PageResult<SpecificationDto> searchSpecifications(@NonNull SpecificationQuery specificationQuery, @NonNull Pageable pageable) {
        final Page<Specification> searchResult = repository.findAll(querySpecification(specificationQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public SpecificationDto getSpecification(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public SpecificationDto addSpecification(SpecificationParam specificationParam) {
        final Specification specification = new Specification(specificationParam.getTemplateId(),
        specificationParam.getName(),
        specificationParam.getOptions(),
        specificationParam.getSequence());

        return converter.convert(repository.save(specification));
    }

    @Transactional(rollbackOn = Exception.class)
    public SpecificationDto editSpecification(Long id, SpecificationParam specificationParam) {
        final Specification specification = requirePresent(repository.findById(id));

        specification.describe(specificationParam.getTemplateId(),
        specificationParam.getName(),
        specificationParam.getOptions(),
        specificationParam.getSequence());

        return converter.convert(repository.save(specification));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeSpecification(long id) {
        repository.deleteById(id);
    }
}
