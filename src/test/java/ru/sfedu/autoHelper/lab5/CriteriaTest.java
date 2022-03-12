package ru.sfedu.autoHelper.lab5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import ru.sfedu.autoHelper.lab5.dataProvider.DataProviderCriteria;
import ru.sfedu.autoHelper.lab5.dataProvider.DataProviderHQL;
import ru.sfedu.autoHelper.lab5.dataProvider.IHibernateDataProvider;
import ru.sfedu.autoHelper.lab5.entity.Car;
import ru.sfedu.autoHelper.lab5.entity.CarProperties;
import ru.sfedu.autoHelper.lab5.entity.SparePart;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public class CriteriaTest {
    private static final Logger logger = LogManager.getLogger(CriteriaTest.class);
    IHibernateDataProvider dataProviderCriteria = new DataProviderCriteria();
    IHibernateDataProvider dataProviderHQL = new DataProviderHQL();
    Car car;
    Car newCar;
    boolean success;

    /**
     * чтение объекта по id
     * тип: позитивный
     */
    @Test
    public void readByIdPositive() {
        Optional<Car> optionalCar;
        if (initCar().isPresent()) {
            car = initCar().get();
        } else {
            fail();
        }
        optionalCar = dataProviderHQL.create(car);
        if(optionalCar.isPresent()) {
            optionalCar = dataProviderCriteria.readById(Car.class, optionalCar.get().getId());
        } else {
            fail();
        }
        assertNotEquals( Optional.empty(), optionalCar);
    }

    /**
     * Инициализация объекта автомобиля
     * @return собранный экземпляр
     */
    public Optional<Car> initCar() {
        try {

            car = new Car("carModel", "carMaker", 11, "carColor", 2011);

            Set<SparePart> spareParts = new HashSet<>();
            SparePart sparePart1 = new SparePart( "type1", "maker1", "11/11/2021");
            SparePart sparePart2 = new SparePart( "type2", "maker2", "12/12/2021");
            sparePart1.setCar(car);
            sparePart2.setCar(car);
            spareParts.add(sparePart1);
            spareParts.add(sparePart2);
            car.setSpareParts(spareParts);

            CarProperties carProperties = new CarProperties(10, "LukOil", "Winter",
                    "22/12/2022");
            car.setCarProperties(carProperties);
        } catch (Exception e) {
            logger.error(e);
            return Optional.empty();
        }
        return Optional.of(car);
    }

    /**
     * Инициализауия экземпляра автомобиля для обновления
     * @return созданный экземпляр
     */
    public Optional<Car> initNewCar() {
        try {

            newCar = new Car("carModelNEW", "carMakerNEW", 22, "carColorNEW",
                    2022);

            Set<SparePart> newSpareParts = new HashSet<>();
            SparePart newSparePart1 = new SparePart( "type1NEW", "maker1NEW", "11/11/2021NEW");
            SparePart newSparePart2 = new SparePart( "type2NEW", "maker2NEW", "12/12/2021NEW");
            newSparePart1.setCar(newCar);
            newSparePart2.setCar(newCar);
            newSpareParts.add(newSparePart1);
            newSpareParts.add(newSparePart2);
            newCar.setSpareParts(newSpareParts);

            CarProperties newCarProperties = new CarProperties(20, "LukOilNEW", "WinterNEW",
                    "22/12/2022NEW");
            newCar.setCarProperties(newCarProperties);
        } catch (Exception e) {
            logger.error(e);
            return Optional.empty();
        }
        return Optional.of(newCar);
    }

}
