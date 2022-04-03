package com.news.newsapp.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.news.newsapp.db.User;
import com.news.newsapp.db.Category;
import com.news.newsapp.db.News;
import com.news.newsapp.db.news_assets;

import com.news.newsapp.db.UserDao;
import com.news.newsapp.db.CategoryDao;
import com.news.newsapp.db.NewsDao;
import com.news.newsapp.db.news_assetsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig categoryDaoConfig;
    private final DaoConfig newsDaoConfig;
    private final DaoConfig news_assetsDaoConfig;

    private final UserDao userDao;
    private final CategoryDao categoryDao;
    private final NewsDao newsDao;
    private final news_assetsDao news_assetsDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        categoryDaoConfig = daoConfigMap.get(CategoryDao.class).clone();
        categoryDaoConfig.initIdentityScope(type);

        newsDaoConfig = daoConfigMap.get(NewsDao.class).clone();
        newsDaoConfig.initIdentityScope(type);

        news_assetsDaoConfig = daoConfigMap.get(news_assetsDao.class).clone();
        news_assetsDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        categoryDao = new CategoryDao(categoryDaoConfig, this);
        newsDao = new NewsDao(newsDaoConfig, this);
        news_assetsDao = new news_assetsDao(news_assetsDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(Category.class, categoryDao);
        registerDao(News.class, newsDao);
        registerDao(news_assets.class, news_assetsDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        categoryDaoConfig.clearIdentityScope();
        newsDaoConfig.clearIdentityScope();
        news_assetsDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public NewsDao getNewsDao() {
        return newsDao;
    }

    public news_assetsDao getNews_assetsDao() {
        return news_assetsDao;
    }

}