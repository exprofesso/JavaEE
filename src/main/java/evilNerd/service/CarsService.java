package evilNerd.service;

import evilNerd.domain.Cars;

import java.util.List;

public interface CarsService {

    List<Cars> findAll();

    Cars save(Cars cars);

    Cars findById(Long id);

    List<Cars> search (String query);


}
