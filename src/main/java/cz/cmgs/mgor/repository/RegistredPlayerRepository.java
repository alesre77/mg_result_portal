package cz.cmgs.mgor.repository;

import cz.cmgs.mgor.entity.RegistredPlayer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistredPlayerRepository extends CrudRepository<RegistredPlayer, Long> {

    @Query(value = "select rp from RegistredPlayer rp where cast(rp.regNr as string) like %:regNr% ")
    List<RegistredPlayer> findByRegNr(@Param("regNr") String regNr);

    @Query(value = "select rp from RegistredPlayer rp where cast(rp.regNr as string) like %:keyword%  or UPPER(rp.lastName) like %:keyword% or UPPER(rp.firstName) like %:keyword%  ")
    List<RegistredPlayer> findByKeyword(@Param("keyword") String keyword);

}
