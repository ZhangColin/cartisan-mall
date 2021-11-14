package com.cartisan.mall.content.activity;

import com.cartisan.constant.CodeMessage;
import com.cartisan.dto.PageResult;
import com.cartisan.exception.CartisanException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cartisan.response.ResponseUtil.success;

@Api(tags = "内容服务：活动")
@RestController
@RequestMapping("/activities")
@Validated
@Slf4j
public class ActivityController {
    private final ActivityAppService service;

    public ActivityController(ActivityAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索活动")
    @GetMapping("/search")
    public ResponseEntity<PageResult<ActivityDto>> searchActivities(
            @ApiParam(value = "查询参数") ActivityQuery activityQuery,
            @PageableDefault Pageable pageable) {
        return success(service.searchActivities(activityQuery, pageable));
    }

    @ApiOperation(value = "获取活动")
    @GetMapping("/{id}")
    public ResponseEntity<ActivityDto> getActivity(@ApiParam(value = "活动Id", required = true) @PathVariable Long id){
        return success(service.getActivity(id));
    }

    @ApiOperation(value = "添加活动")
    @PostMapping
    public ResponseEntity<ActivityDto> addActivity(
            @ApiParam(value = "活动信息", required = true) @Validated @RequestBody ActivityParam activityParam) {
        return success(service.addActivity(activityParam));
    }

    @ApiOperation(value = "编辑活动")
    @PutMapping("/{id}")
    public ResponseEntity<ActivityDto> editActivity(
            @ApiParam(value = "活动Id", required = true) @PathVariable Long id,
            @ApiParam(value = "活动信息", required = true) @Validated @RequestBody ActivityParam activityParam) {
        return success(service.editActivity(id, activityParam));
    }

    @ApiOperation(value = "删除活动")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeActivity(
            @ApiParam(value = "活动Id", required = true) @PathVariable Long id) {
        service.removeActivity(id);
        return success();
    }
}
