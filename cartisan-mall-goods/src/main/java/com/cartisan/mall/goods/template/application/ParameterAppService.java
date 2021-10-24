package com.cartisan.mall.goods.template.application;

import com.cartisan.mall.goods.template.domain.Parameter;
import com.cartisan.mall.goods.template.repository.ParameterRepository;
import com.cartisan.mall.goods.template.request.ParameterParam;
import com.cartisan.mall.goods.template.response.ParameterConverter;
import com.cartisan.mall.goods.template.response.ParameterDto;
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
public class ParameterAppService {
    private final ParameterRepository repository;

    private final ParameterConverter converter = ParameterConverter.CONVERTER;

    public ParameterAppService(ParameterRepository repository) {
        this.repository = repository;
    }

    public List<ParameterDto> getParametersByTemplateId(@NonNull Long templateId) {
        return converter.convert(repository.getParametersByTemplateId(templateId,
                Sort.by(Sort.Direction.ASC, "sequence")));
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
