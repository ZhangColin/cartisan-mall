package com.cartisan.mall.goods.template.mapper;

import com.cartisan.mall.goods.template.request.TemplateQuery;
import com.cartisan.mall.goods.template.response.TemplateDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author colin
 */
public interface TemplateMapper {
    @Select("<script>" +
            "select id, name,\n" +
            "        (select count(1) from gds_specifications where template_id=gds_templates.id) as specifications,\n" +
            "        (select count(1) from gds_parameters where template_id=gds_templates.id) as parameters\n" +
            "from gds_templates\n" +
            "<where>" +
            "<if test=\"query != null and query.name != null\">" +
            "name like concat('%', #{query.name}, '%')\n" +
            "</if>" +
            "</where>" +
            "</script>")
    List<TemplateDto> searchTemplates(@Param(value = "query")TemplateQuery query);
}
