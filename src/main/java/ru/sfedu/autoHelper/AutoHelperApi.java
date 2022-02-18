package ru.sfedu.autoHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.autoHelper.dataProvider.DataProviderCsv;
import ru.sfedu.autoHelper.dataProvider.DataProviderDB;
import ru.sfedu.autoHelper.dataProvider.DataProviderXml;
import ru.sfedu.autoHelper.dataProvider.IDataProvider;
import ru.sfedu.autoHelper.entity.enums.TypeOfTransmission;

import java.io.IOException;
import java.util.Optional;

/**
 * Сервис для учёта автомобильных характеристик
 * @author Олег Панченко
 * Главный класс приложения
 */
public class AutoHelperApi {
    private static final Logger logger = LogManager.getLogger(AutoHelperApi.class);

    /**
     * Главный метод
     * @param args параметр для ввода строковых аргументов (если потребуется)
     */
    public static void main( String[] args ) {
        logger.info(ConstantsValues.HELLO_WORLD);
    }
}
