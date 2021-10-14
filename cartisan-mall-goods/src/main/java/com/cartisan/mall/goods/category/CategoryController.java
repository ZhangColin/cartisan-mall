package com.cartisan.mall.goods.category;

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

@Api(tags = "商品服务：商品分类")
@RestController
@RequestMapping("/categories")
@Validated
@Slf4j
public class CategoryController {
    private final CategoryAppService service;

    public CategoryController(CategoryAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索商品分类")
    @GetMapping("/search")
    public ResponseEntity<PageResult<CategoryDto>> searchCategories(
            @ApiParam(value = "查询参数") CategoryQuery categoryQuery,
            @PageableDefault Pageable pageable) {
        return success(service.searchCategories(categoryQuery, pageable));
    }

    @ApiOperation(value = "获取商品分类")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@ApiParam(value = "商品分类Id", required = true) @PathVariable Long id){
        return success(service.getCategory(id));
    }

    @ApiOperation(value = "添加商品分类")
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(
            @ApiParam(value = "商品分类信息", required = true) @Validated @RequestBody CategoryParam categoryParam) {
        return success(service.addCategory(categoryParam));
    }

    @ApiOperation(value = "编辑商品分类")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> editCategory(
            @ApiParam(value = "商品分类Id", required = true) @PathVariable Long id,
            @ApiParam(value = "商品分类信息", required = true) @Validated @RequestBody CategoryParam categoryParam) {
        return success(service.editCategory(id, categoryParam));
    }

    @ApiOperation(value = "删除商品分类")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCategory(
            @ApiParam(value = "商品分类Id", required = true) @PathVariable Long id) {
        service.removeCategory(id);
        return success();
    }
}
