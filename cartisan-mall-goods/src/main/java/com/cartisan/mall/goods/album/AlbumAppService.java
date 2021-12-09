package com.cartisan.mall.goods.album;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.repository.ConditionSpecifications.querySpecification;
import static com.cartisan.util.AssertionUtil.requirePresent;

/**
 * @author colin
 */
@Service
@Slf4j
public class AlbumAppService {
    private final AlbumRepository repository;

    private final AlbumConverter converter = AlbumConverter.CONVERTER;

    public AlbumAppService(AlbumRepository repository) {
        this.repository = repository;
    }

    public List<AlbumDto> searchAlbums(@NonNull AlbumQuery albumQuery) {
        return converter.convert(repository.findAll(querySpecification(albumQuery), Sort.by(Sort.Direction.ASC, "sequence")));
    }

    public AlbumDto getAlbum(@NonNull Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    public List<String> getAlbumImages(@NonNull Long id) {
        return requirePresent(repository.findById(id)).getImageItems();
    }

    @Transactional(rollbackOn = Exception.class)
    public AlbumDto addAlbum(@NonNull AlbumParam albumParam) {
        final Album album = new Album(
                albumParam.getTitle(),
                albumParam.getCoverImage(),
                albumParam.getDescription(),
                albumParam.getSequence()
        );

        return converter.convert(repository.save(album));
    }

    @Transactional(rollbackOn = Exception.class)
    public AlbumDto editAlbum(@NonNull Long id, @NonNull AlbumParam albumParam) {
        final Album album = requirePresent(repository.findById(id));

        album.describe(
                albumParam.getTitle(),
                albumParam.getCoverImage(),
                albumParam.getDescription(),
                albumParam.getSequence()
        );

        return converter.convert(repository.save(album));
    }

    @Transactional(rollbackOn = Exception.class)
    public void addAlbumImage(@NonNull Long id, @NonNull String imageUrl) {
        final Album album = requirePresent(repository.findById(id));

        album.addImage(imageUrl);

        repository.save(album);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeAlbumImage(@NonNull Long id, @NonNull String imageUrl) {
        final Album album = requirePresent(repository.findById(id));

        album.removeImage(imageUrl);

        repository.save(album);
    }


    @Transactional(rollbackOn = Exception.class)
    public void removeAlbum(@NonNull Long id) {
        repository.deleteById(id);
    }
}
