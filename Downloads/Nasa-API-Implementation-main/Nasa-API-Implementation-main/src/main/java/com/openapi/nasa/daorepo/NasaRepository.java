package com.openapi.nasa.daorepo;

import com.openapi.nasa.entity.NasaApod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Data Access-JPA Repository For Astronomy Picture Of The Day //
public interface NasaRepository extends JpaRepository<NasaApod,Integer> {
    // add methods if wanted //
    // no need to add sometimes since JpaRepo has methods already //


    @Query("select n from NasaApod n where n.date=?1")
    List<NasaApod> findByDate(String date);

    @Query("select n from NasaApod n where n.copyright=:theData")
    NasaApod findApodByCopyright(@Param("theData") String copyright);

}
