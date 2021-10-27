package com.cartisan.mall.goods.goods.mapper;

import com.cartisan.mall.goods.goods.request.SpuQuery;
import com.cartisan.mall.goods.goods.response.SpuDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author colin
 */
public interface SpuMapper {
    @Select("<script>" +
            "select spu.id, spu.image, spu.name, spu.brand_id, gb.name as brand_name, spu.sn, spu.is_marketable, spu.audit_status \n" +
            "from gds_spus as spu\n" +
            "    inner join gds_brands gb on spu.brand_id = gb.id\n" +
            "<where>" +
            "<if test=\"query != null and query.nameOrSn != null\">" +
            "(spu.name like concat('%', #{query.nameOrSn}, '%') or spu.sn like concat('%', #{query.nameOrSn}, '%'))\n" +
            "</if>" +
            "<if test=\"query != null and query.category1Id !=null\">" +
            "and spu.category1_id=#{query.category1Id}\n" +
            "</if>" +
            "<if test=\"query != null and query.category2Id !=null\">" +
            "and spu.category2_id=#{query.category2Id}\n" +
            "</if>" +
            "<if test=\"query != null and query.category3Id !=null\">" +
            "and spu.category3_id=#{query.category3Id}\n" +
            "</if>" +
            "<if test=\"query != null and query.brandId != null\">" +
            "and spu.brand_id=#{query.brandId}\n" +
            "</if>" +
            "</where>" +
            "</script>")
    List<SpuDto> searchSpus(@Param(value = "query") SpuQuery query);
}
