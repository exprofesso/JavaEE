package evilNerd.repository;

import evilNerd.domain.Cars;

import java.util.List;

public interface CarsRepository extends CrudRepository <Long, Cars>{

    List<Cars> search (String query);
}
