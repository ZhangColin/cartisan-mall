package com.cartisan.mall.goods.album;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

import static com.cartisan.response.ResponseUtil.success;

/**
 * @author colin
 */
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
    public ResponseEntity<List<AlbumDto>> searchAlbums(
            @ApiParam(value = "查询参数") AlbumQuery albumQuery) {
        return success(service.searchAlbums(albumQuery));
    }

    @ApiOperation(value = "获取相册")
    @GetMapping("/{id}")
    public ResponseEntity<AlbumDto> getAlbum(@ApiParam(value = "相册Id", required = true) @PathVariable Long id) {
        return success(service.getAlbum(id));
    }

    @ApiOperation(value = "获取相册图片")
    @GetMapping("/{id}/images")
    public ResponseEntity<List<String>> getAlbumImages(@ApiParam(value = "相册Id", required = true) @PathVariable Long id) {
        return success(service.getAlbumImages(id));
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

    @ApiOperation(value = "添加相册图片")
    @PutMapping("/{id}/addImage")
    public ResponseEntity<?> addAlbumImage(
            @ApiParam(value = "相册Id", required = true) @PathVariable Long id,
            @ApiParam(value = "相册信息", required = true) @Validated @NotBlank @RequestParam String imageUrl) {
        service.addAlbumImage(id, imageUrl);
        return success();
    }

    @ApiOperation(value = "删除相册图片")
    @PutMapping("/{id}/removeImage")
    public ResponseEntity<?> removeAlbumImage(
            @ApiParam(value = "相册Id", required = true) @PathVariable Long id,
            @ApiParam(value = "相册信息", required = true) @Validated @NotBlank @RequestParam String imageUrl) {
        service.removeAlbumImage(id, imageUrl);
        return success();
    }

    @ApiOperation(value = "删除相册")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAlbum(
            @ApiParam(value = "相册Id", required = true) @PathVariable Long id) {
        service.removeAlbum(id);
        return success();
    }
}
