package com.news.newsapp.db;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NEWS_ASSETS".
*/
public class news_assetsDao extends AbstractDao<news_assets, Long> {

    public static final String TABLENAME = "NEWS_ASSETS";

    /**
     * Properties of entity news_assets.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property News_assets_file_path = new Property(1, String.class, "news_assets_file_path", false, "NEWS_ASSETS_FİLE_PATH");
        public final static Property Is_active_news_assets = new Property(2, Boolean.class, "is_active_news_assets", false, "İS_ACTİVE_NEWS_ASSETS");
        public final static Property News_id = new Property(3, long.class, "news_id", false, "NEWS_İD");
    }

    private DaoSession daoSession;


    public news_assetsDao(DaoConfig config) {
        super(config);
    }
    
    public news_assetsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEWS_ASSETS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NEWS_ASSETS_FİLE_PATH\" TEXT NOT NULL ," + // 1: news_assets_file_path
                "\"İS_ACTİVE_NEWS_ASSETS\" INTEGER," + // 2: is_active_news_assets
                "\"NEWS_İD\" INTEGER NOT NULL );"); // 3: news_id
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEWS_ASSETS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, news_assets entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNews_assets_file_path());
 
        Boolean is_active_news_assets = entity.getIs_active_news_assets();
        if (is_active_news_assets != null) {
            stmt.bindLong(3, is_active_news_assets ? 1L: 0L);
        }
        stmt.bindLong(4, entity.getNews_id());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, news_assets entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNews_assets_file_path());
 
        Boolean is_active_news_assets = entity.getIs_active_news_assets();
        if (is_active_news_assets != null) {
            stmt.bindLong(3, is_active_news_assets ? 1L: 0L);
        }
        stmt.bindLong(4, entity.getNews_id());
    }

    @Override
    protected final void attachEntity(news_assets entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public news_assets readEntity(Cursor cursor, int offset) {
        news_assets entity = new news_assets( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // news_assets_file_path
            cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0, // is_active_news_assets
            cursor.getLong(offset + 3) // news_id
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, news_assets entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNews_assets_file_path(cursor.getString(offset + 1));
        entity.setIs_active_news_assets(cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0);
        entity.setNews_id(cursor.getLong(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(news_assets entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(news_assets entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(news_assets entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getNewsDao().getAllColumns());
            builder.append(" FROM NEWS_ASSETS T");
            builder.append(" LEFT JOIN NEWS T0 ON T.\"NEWS_İD\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected news_assets loadCurrentDeep(Cursor cursor, boolean lock) {
        news_assets entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        News news = loadCurrentOther(daoSession.getNewsDao(), cursor, offset);
         if(news != null) {
            entity.setNews(news);
        }

        return entity;    
    }

    public news_assets loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<news_assets> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<news_assets> list = new ArrayList<news_assets>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<news_assets> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<news_assets> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
