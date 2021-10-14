package com.cartisan.mall.goods.album;

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

@Api(tags = "商品服务：相册")
@RestController
@RequestMapping("/albums")
@Validated
@Slf4j
public class AlbumController {
    private final AlbumAppService service;

    public AlbumController(AlbumAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索相册")
    @GetMapping("/search")
    public ResponseEntity<PageResult<AlbumDto>> searchAlbums(
            @ApiParam(value = "查询参数") AlbumQuery albumQuery,
            @PageableDefault Pageable pageable) {
        return success(service.searchAlbums(albumQuery, pageable));
    }

    @ApiOperation(value = "获取相册")
    @GetMapping("/{id}")
    public ResponseEntity<AlbumDto> getAlbum(@ApiParam(value = "相册Id", required = true) @PathVariable Long id){
        return success(service.getAlbum(id));
    }

    @ApiOperation(value = "添加相册")
    @PostMapping
    public ResponseEntity<AlbumDto> addAlbum(
            @ApiParam(value = "相册信息", required = true) @Validated @RequestBody AlbumParam albumParam) {
        return success(service.addAlbum(albumParam));
    }

    @ApiOperation(value = "编辑相册")
    @PutMapping("/{id}")
    public ResponseEntity<AlbumDto> editAlbum(
            @ApiParam(value = "相册Id", required = true) @PathVariable Long id,
            @ApiParam(value = "相册信息", required = true) @Validated @RequestBody AlbumParam albumParam) {
        return success(service.editAlbum(id, albumParam));
    }

    @ApiOperation(value = "删除相册")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAlbum(
            @ApiParam(value = "相册Id", required = true) @PathVariable Long id) {
        service.removeAlbum(id);
        return success();
    }
}
