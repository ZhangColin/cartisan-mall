package com.cartisan.mall.goods.template;

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
public class TemplateAppService {
    private final TemplateRepository repository;

    private final TemplateConverter converter = TemplateConverter.CONVERTER;

    public TemplateAppService(TemplateRepository repository) {
        this.repository = repository;
    }

    public PageResult<TemplateDto> searchTemplates(@NonNull TemplateQuery templateQuery, @NonNull Pageable pageable) {
        final Page<Template> searchResult = repository.findAll(querySpecification(templateQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public TemplateDto getTemplate(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public TemplateDto addTemplate(TemplateParam templateParam) {
        final Template template = new Template(templateParam.getName());

        return converter.convert(repository.save(template));
    }

    @Transactional(rollbackOn = Exception.class)
    public TemplateDto editTemplate(Long id, TemplateParam templateParam) {
        final Template template = requirePresent(repository.findById(id));

        template.describe(templateParam.getName());

        return converter.convert(repository.save(template));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeTemplate(long id) {
        repository.deleteById(id);
    }
}
