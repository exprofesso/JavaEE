package evilNerd.service.impl;

import evilNerd.domain.Cars;
import evilNerd.repository.CarsRepository;
import evilNerd.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarsServiceImpl implements CarsService {

    private final CarsRepository carsRepository;


    @Override
    public List<Cars> findAll() {
        return carsRepository.findAll();
    }

    @Override
    public Cars save(Cars cars) {
        return carsRepository.save(cars);
    }

    @Override
    public Cars findById(Long id) {
        return carsRepository.findById(id);
    }

    @Override
    public List<Cars> search(String query) {
        return carsRepository.search(query);
    }
}
