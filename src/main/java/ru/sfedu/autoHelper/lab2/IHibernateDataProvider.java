package ru.sfedu.autoHelper.lab2;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс провайдера данных Hibernate для выполнения задач лабораторной работы 2
 */
public interface IHibernateDataProvider {
    /**
     * Метод для добавления экземпляра TestEntity в БД
     * @param testEntity необходимый для передачи экземпляр
     */
    boolean create(TestEntity testEntity);
    /**
     * Метод для получения TestEntity по конкретному id
     * @param id необходимый для получения экземпляр (id объекта)
     * @return экземпляр TestEntity найденный по введённому id
     */
    Optional<TestEntity> readById(long id);

    /**
     * Метод для получения всех существующих экземпляров TestEntity
     * @return список объектов
     */
    Optional<List<TestEntity>> readAll();

    /**
     * Метод для внесения изменений в экземпляр объекта TestEntity
     * @param testEntity экземпляр, который необходимо изменить
     */
    boolean update(TestEntity testEntity);

    /**
     * Метод для удаления экземпляра TestEntity по выбранному id
     * @param id идентификатор экземпляра, который необъодимо удалить
     */
    boolean delete(long id);

}
