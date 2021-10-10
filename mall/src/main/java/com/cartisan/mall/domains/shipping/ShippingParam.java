package com.cartisan.mall.domains.shipping;

import lombok.Data;

/**
 * @author colin
 */
@Data
public class ShippingParam {
    private String receiverName;
    private String receiverPhone;
    private String receiverMobile;
    private String receiverProvince;
    private String receiverCity;
    private String receiverDistrict;
    private String receiverAddress;
    private String receiverZip;
}
