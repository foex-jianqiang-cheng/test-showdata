/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbitest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sbitest.entity.db.Price;

public interface PriceRepository extends CrudRepository<Price, Long> {
    @Query(value="select * from price u order by id desc limit 1200", nativeQuery = true)
    public List<Price> findPrcie();
}