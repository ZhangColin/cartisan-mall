package com.cartisan.mall.content.ad;

import com.cartisan.dto.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.cartisan.response.ResponseUtil.success;

/**
 * @author colin
 */
@Api(tags = "内容服务：广告")
@RestController
@RequestMapping("/ads")
@Validated
@Slf4j
public class AdController {
    private final AdAppService service;

    public AdController(AdAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索广告")
    @GetMapping("/search")
    public ResponseEntity<PageResult<AdDto>> searchAds(
            @ApiParam(value = "查询参数") AdQuery adQuery,
            @PageableDefault Pageable pageable) {
        return success(service.searchAds(adQuery, pageable));
    }

    @ApiOperation(value = "获取广告")
    @GetMapping("/{id}")
    public ResponseEntity<AdDto> getAd(@ApiParam(value = "广告Id", required = true) @PathVariable Long id) {
        return success(service.getAd(id));
    }

    @ApiOperation(value = "添加广告")
    @PostMapping
    public ResponseEntity<AdDto> addAd(
            @ApiParam(value = "广告信息", required = true) @Validated @RequestBody AdParam adParam) {
        return success(service.addAd(adParam));
    }

    @ApiOperation(value = "编辑广告")
    @PutMapping("/{id}")
    public ResponseEntity<AdDto> editAd(
            @ApiParam(value = "广告Id", required = true) @PathVariable Long id,
            @ApiParam(value = "广告信息", required = true) @Validated @RequestBody AdParam adParam) {
        return success(service.editAd(id, adParam));
    }

    @ApiOperation(value = "删除广告")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(
            @ApiParam(value = "广告Id", required = true) @PathVariable Long id) {
        service.removeAd(id);
        return success();
    }
}
