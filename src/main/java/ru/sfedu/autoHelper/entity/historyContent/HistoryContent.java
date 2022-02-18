package ru.sfedu.autoHelper.entity.historyContent;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import ru.sfedu.autoHelper.ConstantsValues;
import ru.sfedu.autoHelper.entity.enums.Status;
import ru.sfedu.autoHelper.util.ConfigurationUtil;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Класс, участвующий в реализации взаимодейтсвия объектов с СУБД MongoDB
 */
public class HistoryContent {
    private static final Logger logger = LogManager.getLogger(HistoryContent.class);
    long id;
    String className;
    String createdDate = new SimpleDateFormat(ConstantsValues.MONGODB_DATE_TEMPLATE).format(new Date());
    String actor = ConstantsValues.ACTOR_NAME;
    Status status;
    String jsonObject;

    /**
     * Конструктор для создания объекта истории
     * @param className наименование класса, необходимого для записи
     * @param status статус операции - Success или Fault
     * @param jsonObject параметр для JSON-представления объекта
     */
    public HistoryContent(String className, Status status, String jsonObject) {
        this.className = className;
        this.status = status;
        this.jsonObject = jsonObject;
    }

    public HistoryContent() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(String jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public String toString() {
        return "HistoryContent{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", actor='" + actor + '\'' +
                ", status=" + status +
                ", jsonObject='" + jsonObject + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryContent that = (HistoryContent) o;
        return id == that.id && Objects.equals(className, that.className) && Objects.equals(createdDate, that.createdDate) && Objects.equals(actor, that.actor) && status == that.status && Objects.equals(jsonObject, that.jsonObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, className, createdDate, actor, status, jsonObject);
    }

    /**
     * Метод для реализации взаимодейтсвия данных с MongoDB. Включает возможность создания коллекции,
     * преобразования объекта в JSON-представлении и сохранения изменений
     * @param className наименование класса, необходимого для записи
     * @param status статус операции - Success или Fault
     * @param json параметр для JSON-представления объекта
     * @return isSaved - boolean-результат операций
     */
    public static boolean saveHistoryContent(String className, Status status, String json){
        boolean isSaved;
        try {
            MongoClient mongoClient = MongoClients.create(ConfigurationUtil.getConfigurationEntry(ConstantsValues.MONGODB_HOST));
            MongoDatabase database = mongoClient.getDatabase(ConfigurationUtil.getConfigurationEntry(ConstantsValues.MONGODB_DATABASE_NAME));
            try {
                database.createCollection(ConfigurationUtil.getConfigurationEntry(ConstantsValues.MONGODB_COLLECTION_NAME));
            } catch (Exception e) {
                logger.info(ConstantsValues.MONGODB_COLLECTION_ALREADY_EXIST);
            }
            HistoryContent historyContent = new HistoryContent(className, status, json);
            MongoCollection<Document> collection = database.getCollection(ConfigurationUtil.getConfigurationEntry(ConstantsValues.MONGODB_COLLECTION_NAME));
            collection.insertOne(Document.parse(new Gson().toJson(historyContent)));
            isSaved = true;
        } catch (Exception e) {
            logger.error(e);
            isSaved = false;
        }
        return isSaved;
    }

}
