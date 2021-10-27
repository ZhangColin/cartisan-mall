package com.cartisan.mall.goods.template.application;

import com.cartisan.dp.IdName;
import com.cartisan.dto.PageResult;
import com.cartisan.mall.goods.template.domain.Template;
import com.cartisan.mall.goods.template.mapper.TemplateMapper;
import com.cartisan.mall.goods.template.repository.TemplateRepository;
import com.cartisan.mall.goods.template.request.TemplateParam;
import com.cartisan.mall.goods.template.request.TemplateQuery;
import com.cartisan.mall.goods.template.response.TemplateDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.util.AssertionUtil.requirePresent;

/**
 * @author colin
 */
@Service
public class TemplateAppService {
    private final TemplateRepository repository;
    private final TemplateMapper templateMapper;


    public TemplateAppService(TemplateRepository repository, TemplateMapper templateMapper) {
        this.repository = repository;
        this.templateMapper = templateMapper;
    }

    public PageResult<TemplateDto> searchTemplates(@NonNull TemplateQuery templateQuery, @NonNull Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        final List<TemplateDto> templates = templateMapper.searchTemplates(templateQuery);

        PageInfo<TemplateDto> pageTemplates = new PageInfo<>(templates);

        return new PageResult<>(pageTemplates.getTotal(), pageTemplates.getPages(), pageTemplates.getList());
    }

    public List<IdName<Long, String>> getTemplates() {
        return templateMapper.getTemplates();
    }

    @Transactional(rollbackOn = Exception.class)
    public void addTemplate(TemplateParam templateParam) {
        repository.save(new Template(templateParam.getName()));
    }

    @Transactional(rollbackOn = Exception.class)
    public void editTemplate(Long id, TemplateParam templateParam) {
        final Template template = requirePresent(repository.findById(id));

        template.describe(templateParam.getName());

        repository.save(template);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeTemplate(long id) {
        repository.deleteById(id);
    }
}
