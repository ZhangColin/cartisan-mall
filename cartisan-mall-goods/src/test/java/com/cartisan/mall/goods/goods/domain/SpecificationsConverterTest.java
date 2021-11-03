package com.cartisan.mall.goods.goods.domain;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author colin
 */
public class SpecificationsConverterTest {
    @Test
    public void should_return_one_empty_sku_specification_when_spu_specifications_is_null() {
        // given
        final SpecificationsConverter converter = new SpecificationsConverter();

        // when
        final List<String> skuSpecifications = converter.convert(null);

        // then
        assertThat(skuSpecifications.size()).isEqualTo(1);
        assertThat(skuSpecifications.get(0)).isEqualTo("{}");
    }

    @Test
    public void should_return_one_empty_sku_specification_when_spu_specifications_is_empty() {
        // given
        final SpecificationsConverter converter = new SpecificationsConverter();

        // when
        final List<String> skuSpecifications = converter.convert("{}");

        // then
        assertThat(skuSpecifications.size()).isEqualTo(1);
        assertThat(skuSpecifications.get(0)).isEqualTo("{}");
    }

    @Test
    public void should_return_one_sku_specification_when_spu_specifications_is_one_specifications_and_one_item() {
        // given
        final SpecificationsConverter converter = new SpecificationsConverter();

        // when
        final List<String> skuSpecifications = converter.convert("{\"颜色\":[\"蓝色\"]}");

        // then
        assertThat(skuSpecifications.size()).isEqualTo(1);
        assertThat(skuSpecifications.get(0)).isEqualTo("{\"颜色\":\"蓝色\"}");
    }

    @Test
    public void should_return_two_sku_specification_when_spu_specifications_is_one_specifications_and_two_item() {
        // given
        final SpecificationsConverter converter = new SpecificationsConverter();

        // when
        final List<String> skuSpecifications = converter.convert("{\"颜色\":[\"黑色\",\"蓝色\"]}");

        // then
        assertThat(skuSpecifications.size()).isEqualTo(2);
        assertThat(skuSpecifications.get(0)).isEqualTo("{\"颜色\":\"黑色\"}");
        assertThat(skuSpecifications.get(1)).isEqualTo("{\"颜色\":\"蓝色\"}");
    }

    @Test
    public void should_return_two_sku_specification_when_spu_specifications_is_two_specifications_and_two_one_item() {
        // given
        final SpecificationsConverter converter = new SpecificationsConverter();

        // when
        final List<String> skuSpecifications = converter.convert("{\"颜色\":[\"黑色\",\"蓝色\"],\"版本\":[\"6GB+64GB\"]}");

        // then
        assertThat(skuSpecifications.size()).isEqualTo(2);
        assertThat(skuSpecifications.get(0)).isEqualTo("{\"颜色\":\"黑色\",\"版本\":\"6GB+64GB\"}");
        assertThat(skuSpecifications.get(1)).isEqualTo("{\"颜色\":\"蓝色\",\"版本\":\"6GB+64GB\"}");
    }
}
