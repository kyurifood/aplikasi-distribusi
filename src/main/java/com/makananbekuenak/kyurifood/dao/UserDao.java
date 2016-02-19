package com.makananbekuenak.kyurifood.dao;

import com.makananbekuenak.kyurifood.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author gilang
 */
public interface UserDao extends PagingAndSortingRepository<User, String> {
}
