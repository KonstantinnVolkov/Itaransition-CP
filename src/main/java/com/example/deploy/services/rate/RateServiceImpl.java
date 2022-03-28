package com.example.deploy.services.rate;

import com.example.deploy.models.Rate;
import com.example.deploy.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateServiceImpl implements RateService{

    private final RateRepository rateRepository;

    @Autowired
    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public void save(long post_id, int rate) {
        Rate rateEntity = new Rate();
        rateEntity.setPost_id(post_id);
        rateEntity.setRate(rate);
        rateRepository.save(rateEntity);
    }
}
