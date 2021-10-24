package com.cartisan.mall.goods.template.repository;

import com.cartisan.mall.goods.template.domain.Specification;
import com.cartisan.repository.BaseRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author colin
 */
public interface SpecificationRepository extends BaseRepository<Specification, Long> {
    List<Specification> getSpecificationsByTemplateId(Long templateId, Sort sort);
}
