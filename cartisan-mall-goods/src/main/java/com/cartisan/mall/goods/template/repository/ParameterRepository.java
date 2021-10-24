package com.cartisan.mall.goods.template.repository;

import com.cartisan.mall.goods.template.domain.Parameter;
import com.cartisan.repository.BaseRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author colin
 */
public interface ParameterRepository extends BaseRepository<Parameter, Long> {
    List<Parameter> getParametersByTemplateId(Long templateId, Sort sort);
}
