package com.cartisan.mall.goods.album;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.repository.ConditionSpecifications.querySpecification;
import static com.cartisan.util.AssertionUtil.requirePresent;

/**
 * @author colin
 */
@Service
public class AlbumAppService {
    private final AlbumRepository repository;

    private final AlbumConverter converter = AlbumConverter.CONVERTER;

    public AlbumAppService(AlbumRepository repository) {
        this.repository = repository;
    }

    public List<AlbumDto> searchAlbums(@NonNull AlbumQuery albumQuery) {
        return converter.convert(repository.findAll(querySpecification(albumQuery)));
    }

    public AlbumDto getAlbum(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    public List<String> getAlbumImages(Long id) {
        return requirePresent(repository.findById(id)).getImageItems();
    }

    @Transactional(rollbackOn = Exception.class)
    public AlbumDto addAlbum(AlbumParam albumParam) {
        final Album album = new Album(albumParam.getTitle(),
                albumParam.getCoverImage());

        return converter.convert(repository.save(album));
    }

    @Transactional(rollbackOn = Exception.class)
    public AlbumDto editAlbum(Long id, AlbumParam albumParam) {
        final Album album = requirePresent(repository.findById(id));

        album.describe(albumParam.getTitle(),
                albumParam.getCoverImage());

        return converter.convert(repository.save(album));
    }

    @Transactional(rollbackOn = Exception.class)
    public void addAlbumImage(Long id, String imageUrl) {
        final Album album = requirePresent(repository.findById(id));

        album.addImage(imageUrl);

        repository.save(album);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeAlbumImage(Long id, String imageUrl) {
        final Album album = requirePresent(repository.findById(id));

        album.removeImage(imageUrl);

        repository.save(album);
    }


    @Transactional(rollbackOn = Exception.class)
    public void removeAlbum(long id) {
        repository.deleteById(id);
    }
}
