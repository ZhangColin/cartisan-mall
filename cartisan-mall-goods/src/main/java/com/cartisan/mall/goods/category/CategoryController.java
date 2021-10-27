package com.cartisan.mall.goods.category;

import com.cartisan.dp.IdName;
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

import java.util.List;

import static com.cartisan.response.ResponseUtil.success;

/**
 * @author colin
 */
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
            @ApiParam(value = "上级分类", defaultValue = "0") @RequestParam(defaultValue = "0") Long parentId,
            @PageableDefault Pageable pageable) {
        return success(service.searchCategories(parentId, pageable));
    }

    @ApiOperation(value = "不分页获取商品分类（用于分类显示、选择）")
    @GetMapping
    public ResponseEntity<List<IdName<Long, String>>> getCategories(
            @ApiParam(value = "上级分类", defaultValue = "0") @RequestParam(defaultValue = "0") Long parentId) {
        return success(service.getCategories(parentId));
    }

    @ApiOperation(value = "添加商品分类")
    @PostMapping
    public ResponseEntity<?> addCategory(
            @ApiParam(value = "商品分类信息", required = true) @Validated @RequestBody CategoryParam categoryParam) {
        service.addCategory(categoryParam);
        return success();
    }

    @ApiOperation(value = "编辑商品分类")
    @PutMapping("/{id}")
    public ResponseEntity<?> editCategory(
            @ApiParam(value = "商品分类Id", required = true) @PathVariable Long id,
            @ApiParam(value = "商品分类信息", required = true) @Validated @RequestBody CategoryParam categoryParam) {
        service.editCategory(id, categoryParam);
        return success();
    }

    @ApiOperation(value = "删除商品分类")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCategory(
            @ApiParam(value = "商品分类Id", required = true) @PathVariable Long id) {
        service.removeCategory(id);
        return success();
    }
}
