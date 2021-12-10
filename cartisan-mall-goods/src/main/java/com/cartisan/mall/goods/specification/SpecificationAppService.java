package com.cartisan.mall.goods.specification;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.repository.ConditionSpecifications.querySpecification;
import static com.cartisan.util.AssertionUtil.requirePresent;

/**
* @author colin
*/
@Service
@Slf4j
public class SpecificationAppService {
    private final SpecificationRepository repository;

    private final SpecificationConverter converter = SpecificationConverter.CONVERTER;

    public SpecificationAppService(SpecificationRepository repository) {
        this.repository = repository;
    }

    public List<SpecificationDto> searchSpecifications(@NonNull SpecificationQuery specificationQuery) {
        final List<Specification> searchResult = repository.findAll(querySpecification(specificationQuery),
                Sort.by(Sort.Direction.ASC, "sequence"));

        return converter.convert(searchResult);
    }

    public SpecificationDto getSpecification(@NonNull Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public SpecificationDto addSpecification(@NonNull SpecificationParam specificationParam) {
        final Specification specification = new Specification(
            specificationParam.getCategoryId(),
            specificationParam.getName(),
            specificationParam.getValueOptions(),
            specificationParam.getManualAdd(),
            specificationParam.getEnable(),
            specificationParam.getSequence());

        return converter.convert(repository.save(specification));
    }

    @Transactional(rollbackOn = Exception.class)
    public SpecificationDto editSpecification(@NonNull Long id, @NonNull SpecificationParam specificationParam) {
        final Specification specification = requirePresent(repository.findById(id));

        specification.describe(
            specificationParam.getCategoryId(),
            specificationParam.getName(),
            specificationParam.getValueOptions(),
            specificationParam.getManualAdd(),
            specificationParam.getEnable(),
            specificationParam.getSequence());

        return converter.convert(repository.save(specification));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeSpecification(@NonNull Long id) {
        repository.deleteById(id);
    }
}
