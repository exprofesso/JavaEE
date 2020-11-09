package evilNerd.service;

import evilNerd.domain.Cars;

import java.util.List;
import java.util.Optional;

public interface CarsService {

    List<Cars> findAll();

    Cars save(Cars cars);

    Cars findById(Long id);

    List<Cars> search (String query);

    Optional<Cars> findOne(Long key);

    Cars update (Cars user);

    int delete (Cars user);


}
