package demo.test;
import evilNerd.repository.impl.CarsRepositorylmpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCar  {

    private final CarsRepositorylmpl carsRepository = new CarsRepositorylmpl();

    @Test
    public void testFindAllCars() {
        assertNotNull(carsRepository.findAll());
    }

    }
