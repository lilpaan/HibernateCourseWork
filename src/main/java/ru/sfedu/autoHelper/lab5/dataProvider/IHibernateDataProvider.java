package ru.sfedu.autoHelper.lab5.dataProvider;

import ru.sfedu.autoHelper.lab5.entity.Car;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс провайдера данных Hibernate для выполнения задач лабораторной работы 5
 */
public interface IHibernateDataProvider {

    /**
     * Метод для добавления объекта в базу данных
     * @param object необходимый для добавления экземпляр
     */
    <T> Optional<T> create(T object);

    /**
     * Метод для получения объекта по конкретному id
     * @param typeClass передаёт объект, откуда считается файл
     * @param id необходимый для получения экземпляр (id объекта)
     * @return экземпляр объекта, найденный по введённому id
     */
    <T> Optional<T> readById(Class<T> typeClass, long id);

    /**
     * Метод для получения всех объектов
     * @return список с объектами
     */
    public Optional<List<Car>> readAll();

    /**
     * Метод для внесения изменений в экземпляр объекта
     * @param object экземпляр, который необходимо изменить
     */
    <T> boolean update(T object);

    /**
     * Метод для удаления экземпляра объекта по выбранному id
     * @param object  экземпляр, который необъодимо удалить
     */
    <T> boolean delete(T object);

}
