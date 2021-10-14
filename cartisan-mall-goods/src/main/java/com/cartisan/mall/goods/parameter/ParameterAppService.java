package com.cartisan.mall.goods.parameter;

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
public class ParameterAppService {
    private final ParameterRepository repository;

    private final ParameterConverter converter = ParameterConverter.CONVERTER;

    public ParameterAppService(ParameterRepository repository) {
        this.repository = repository;
    }

    public PageResult<ParameterDto> searchParameters(@NonNull ParameterQuery parameterQuery, @NonNull Pageable pageable) {
        final Page<Parameter> searchResult = repository.findAll(querySpecification(parameterQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public ParameterDto getParameter(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public ParameterDto addParameter(ParameterParam parameterParam) {
        final Parameter parameter = new Parameter(parameterParam.getTemplateId(),
        parameterParam.getName(),
        parameterParam.getOptions(),
        parameterParam.getSequence());

        return converter.convert(repository.save(parameter));
    }

    @Transactional(rollbackOn = Exception.class)
    public ParameterDto editParameter(Long id, ParameterParam parameterParam) {
        final Parameter parameter = requirePresent(repository.findById(id));

        parameter.describe(parameterParam.getTemplateId(),
        parameterParam.getName(),
        parameterParam.getOptions(),
        parameterParam.getSequence());

        return converter.convert(repository.save(parameter));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeParameter(long id) {
        repository.deleteById(id);
    }
}
