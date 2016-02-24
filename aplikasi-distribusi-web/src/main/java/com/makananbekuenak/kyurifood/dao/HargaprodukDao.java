package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.entity.Hargaproduk;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HargaprodukDao extends PagingAndSortingRepository<Hargaproduk, String> {
    
}
