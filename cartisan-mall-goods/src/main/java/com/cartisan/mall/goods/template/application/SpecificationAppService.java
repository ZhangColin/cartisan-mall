package com.cartisan.mall.goods.template.application;

import com.cartisan.mall.goods.template.domain.Specification;
import com.cartisan.mall.goods.template.repository.SpecificationRepository;
import com.cartisan.mall.goods.template.request.SpecificationParam;
import com.cartisan.mall.goods.template.response.SpecificationConverter;
import com.cartisan.mall.goods.template.response.SpecificationDto;
import lombok.NonNull;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.util.AssertionUtil.requirePresent;

/**
 * @author colin
 */
@Service
public class SpecificationAppService {
    private final SpecificationRepository repository;

    private final SpecificationConverter converter = SpecificationConverter.CONVERTER;

    public SpecificationAppService(SpecificationRepository repository) {
        this.repository = repository;
    }

    public List<SpecificationDto> getSpecificationsByTemplateId(@NonNull Long templateId) {
        return converter.convert(repository.getSpecificationsByTemplateId(templateId,
                Sort.by(Sort.Direction.ASC, "sequence")));
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
