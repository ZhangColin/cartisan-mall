package com.cartisan.mall.goods.spu;

import com.cartisan.domain.AggregateRoot;
import com.cartisan.domain.SoftDeleteEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gds_spus")
@Getter
public class Spu extends SoftDeleteEntity implements AggregateRoot {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "sn")
    private String sn;

    @Column(name = "name")
    private String name;

    @Column(name = "caption")
    private String caption;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "category1_id")
    private Long category1Id;

    @Column(name = "category2_id")
    private Long category2Id;

    @Column(name = "category3_id")
    private Long category3Id;

    @Column(name = "template_id")
    private Long templateId;

    @Column(name = "image")
    private String image;

    @Column(name = "images")
    private String images;

    @Column(name = "sale_service")
    private String saleService;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "specification_items")
    private String specificationItems;

    @Column(name = "parameter_items")
    private String parameterItems;

    @Column(name = "is_marketable")
    @Setter
    private Boolean isMarketable;

    @Column(name = "enable_specification")
    private Boolean enableSpecification;

    @Column(name = "audit_status")
    @Setter
    private Integer auditStatus;

    protected Spu() {}

    public Spu(Long id, String sn, String name, String caption, Long brandId, Long category1Id, Long category2Id, Long category3Id, Long templateId, String image, String images, String saleService, String introduction, String specificationItems, String parameterItems, Boolean isMarketable, Boolean enableSpecification, Integer auditStatus) {
        this.id = id;
        this.sn = sn;
        this.name = name;
        this.caption = caption;
        this.brandId = brandId;
        this.category1Id = category1Id;
        this.category2Id = category2Id;
        this.category3Id = category3Id;
        this.templateId = templateId;
        this.image = image;
        this.images = images;
        this.saleService = saleService;
        this.introduction = introduction;
        this.specificationItems = specificationItems;
        this.parameterItems = parameterItems;
        this.isMarketable = isMarketable;
        this.enableSpecification = enableSpecification;
        this.auditStatus = auditStatus;
    }

    public void describe(String sn, String name, String caption, Long brandId, Long category1Id, Long category2Id, Long category3Id, Long templateId, String image, String images, String saleService, String introduction, String specificationItems, String parameterItems, Boolean isMarketable, Boolean enableSpecification, Integer auditStatus) {
        this.sn = sn;
        this.name = name;
        this.caption = caption;
        this.brandId = brandId;
        this.category1Id = category1Id;
        this.category2Id = category2Id;
        this.category3Id = category3Id;
        this.templateId = templateId;
        this.image = image;
        this.images = images;
        this.saleService = saleService;
        this.introduction = introduction;
        this.specificationItems = specificationItems;
        this.parameterItems = parameterItems;
        this.isMarketable = isMarketable;
        this.enableSpecification = enableSpecification;
        this.auditStatus = auditStatus;
    }
}
