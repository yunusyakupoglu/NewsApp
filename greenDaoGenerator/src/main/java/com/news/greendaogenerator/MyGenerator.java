package com.news.greendaogenerator;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

public class MyGenerator {
    public static void main(String args[]) throws Exception{
        Schema schema = new Schema(1, "com.news.newsapp.db"); // Your app package name and the (.db) is the folder where the DAO files will be generated into.
        schema.enableKeepSectionsByDefault();

       // addTables(schema);

        Entity user = schema.addEntity("User");
        user.addIdProperty().primaryKey().autoincrement();
       // user.addIntProperty("user_id").notNull();
        user.addStringProperty("last_name").notNull();
        user.addStringProperty("first_name").notNull();
        user.addStringProperty("username").notNull();
        user.addStringProperty("email").notNull();
        user.addStringProperty("password").notNull();
        user.addBooleanProperty("is_active_user");

        Entity category = schema.addEntity("Category");
        category.addIdProperty().primaryKey().autoincrement();
       // category.addIntProperty("category_id").notNull();
        category.addStringProperty("category_name").notNull();
        category.addBooleanProperty("is_active_category");

        Entity news = schema.addEntity("News");
        news.addIdProperty().primaryKey().autoincrement();
       // news.addIntProperty("news_id").notNull();
        news.addStringProperty("news_title").notNull();
        news.addStringProperty("news_content").notNull();
        news.addBooleanProperty("is_active_news");
        Property categoryIdProperty = news.addLongProperty("category_id").notNull().getProperty();
        news.addToOne(category,categoryIdProperty);

        Entity newsAssets = schema.addEntity("news_assets");
        newsAssets.addIdProperty().primaryKey().autoincrement();
        newsAssets.addStringProperty("news_assets_file_path").notNull();
        newsAssets.addBooleanProperty("is_active_news_assets");
        Property newsIdProperty = newsAssets.addLongProperty("news_id").notNull().getProperty();
        newsAssets.addToOne(news,newsIdProperty);


        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    private static void addTables(final Schema schema) {
        addUserEntities(schema);
        addCategoryEntities(schema);
    }

    // This is use to describe the colums of your table
    private static Entity addUserEntities(final Schema schema) {
        Entity user = schema.addEntity("User");
        user.addIdProperty().primaryKey().autoincrement();
        user.addIntProperty("user_id").notNull();
        user.addStringProperty("last_name").notNull();
        user.addStringProperty("first_name").notNull();
        user.addStringProperty("username").notNull();
        user.addStringProperty("email").notNull();
        user.addStringProperty("password").notNull();
        user.addBooleanProperty("is_active_user");
        return user;
    }

    public static Entity addCategoryEntities(final Schema schema){
        Entity category = schema.addEntity("Category");
        category.addIdProperty().primaryKey().autoincrement();
        category.addIntProperty("category_id").notNull();
        category.addStringProperty("category_name").notNull();
        category.addBooleanProperty("is_active_category");
        return category;
    }

    private static Entity addNewsEntities(final Schema schema){
        Entity news = schema.addEntity("News");
        news.addIdProperty().primaryKey().autoincrement();
        news.addIntProperty("news_id").notNull();
        news.addStringProperty("news_title").notNull();
        news.addStringProperty("news_content").notNull();
        news.addBooleanProperty("is_active_news");
        return news;
    }
    */
}