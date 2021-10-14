package com.cartisan.mall.goods.album;

import com.cartisan.constant.CodeMessage;
import com.cartisan.dto.PageResult;
import com.cartisan.exception.CartisanException;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.repository.ConditionSpecifications.querySpecification;
import static com.cartisan.util.AssertionUtil.requirePresent;
import static java.util.stream.Collectors.toList;

@Service
public class AlbumAppService {
    private final AlbumRepository repository;

    private final AlbumConverter converter = AlbumConverter.CONVERTER;

    public AlbumAppService(AlbumRepository repository) {
        this.repository = repository;
    }

    public PageResult<AlbumDto> searchAlbums(@NonNull AlbumQuery albumQuery, @NonNull Pageable pageable) {
        final Page<Album> searchResult = repository.findAll(querySpecification(albumQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public AlbumDto getAlbum(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public AlbumDto addAlbum(AlbumParam albumParam) {
        final Album album = new Album(albumParam.getTitle(),
        albumParam.getImage(),
        albumParam.getImageItems());

        return converter.convert(repository.save(album));
    }

    @Transactional(rollbackOn = Exception.class)
    public AlbumDto editAlbum(Long id, AlbumParam albumParam) {
        final Album album = requirePresent(repository.findById(id));

        album.describe(albumParam.getTitle(),
        albumParam.getImage(),
        albumParam.getImageItems());

        return converter.convert(repository.save(album));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeAlbum(long id) {
        repository.deleteById(id);
    }
}
