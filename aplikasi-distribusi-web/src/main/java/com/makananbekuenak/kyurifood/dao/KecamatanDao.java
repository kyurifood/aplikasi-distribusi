package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.entity.Kecamatan;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KecamatanDao extends PagingAndSortingRepository<Kecamatan, String> {
    
}
