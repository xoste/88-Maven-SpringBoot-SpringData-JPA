package com.xoste.leon.dao;

import com.xoste.leon.pojo.Users;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * PageingAndSortRepository
 * @author Leon
 */
public interface UsersRepositoryPagingAndSorting extends PagingAndSortingRepository<Users, Integer> {
}
