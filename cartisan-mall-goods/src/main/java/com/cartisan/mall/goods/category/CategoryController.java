package com.cartisan.mall.goods.category;

import com.cartisan.dto.TreeNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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

    @ApiOperation(value = "获取分类树")
    @GetMapping("/tree")
    public ResponseEntity<List<TreeNode>> getCategoryTree() {
        return success(service.getCategoryTree());
    }

    @ApiOperation(value = "获取商品分类")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@ApiParam(value = "角色Id", required = true) @PathVariable Long id) {
        return success(service.getCategory(id));
    }

    @ApiOperation(value = "添加商品分类")
    @PostMapping
    public ResponseEntity<?> addCategory(
            @ApiParam(value = "商品分类信息", required = true) @Validated @RequestBody CategoryParam categoryParam) {
        service.addCategory(categoryParam);
        return success();
    }

    @ApiOperation(value = "移动商品分类")
    @PostMapping("/moveCategories")
    public ResponseEntity<?> moveCategories(
            @ApiParam(value = "商品分类位置信息", required = true) @Validated @RequestBody List<MoveCategoryCommand> commands) {
        service.moveCategories(commands);
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
