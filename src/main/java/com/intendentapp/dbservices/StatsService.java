package com.intendentapp.dbservices;

import com.intendentapp.dtomodel.StatsEntity;
import com.intendentapp.repository.StatsRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StatsService {
    private final StatsRepository statsRepository;

    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public List<StatsEntity> findAll(){return this.statsRepository.findAll();}
    public List<StatsEntity> findByMonth_year(String month_year){return this.statsRepository.findByMonth_year(month_year);}
    public void save(StatsEntity statsEntity){this.statsRepository.save(statsEntity);}
    public void delete(Integer statid){this.statsRepository.delete(statid);}
}
