package team4.softwareengineering.com.cateringsystem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import team4.softwareengineering.com.cateringsystem.model.DatabaseEventsModel;
import team4.softwareengineering.com.cateringsystem.model.DatabaseUsersModel;

import static android.provider.Telephony.Carriers.PASSWORD;


/**
 * Created by vikra on 3/11/2018.
 */

public class DatabaseAdapter {


    private static final String DB_NAME = "cateringSystem.db";
    private static final int DB_VERSION = 1;


    private static final String USER_TABLE = "tableUser";
    private static final String USER_COLUMN_USER_ID = "userColumnUserId";
    private static final String USER_COLUMN_UTA_ID = "userColumnUtaId";
    private static final String USER_COLUMN_FIRST_NAME = "userColumnFirstName";
    private static final String USER_COLUMN_LAST_NAME = "userColumnLastName";
    private static final String USER_COLUMN_EMAIL_ID = "userColumnEmailId";
    private static final String USER_COLUMN_PHONE_NUMBER = "userColumnPhoneNumber";
    private static final String USER_COLUMN_CATEGORY = "userColumnCategory";
    private static final String USER_COLUMN_PASSWORD = "userColumnPassword";
    private static final String USER_COLUMN_TIMESTAMP = "userColumnTimestamp";
    private static final String USER_COLUMN_STATUS = "userColumnStatus";
    private static final String USER_COLUMN_ADDRESS = "userColumnAddress";

//    private static final String HALL_TABLE ="tableHall";
//    private static final String HALL_COLUMN_HALL_NAME ="hallColumnHallName";
//    private static final String HALL_COLUMN_HALL_ID ="hallColumnHallId";
//    private static final String HALL_COLUMN_HALL_CAPACITY ="hallColumnHallCapacity";
//    private static final String HALL_COLUMN_HALL_PRICE ="hallColumnHallPrice";
//    private static final String HALL_COLUMN_HALL_LOCATION ="hallColumnLocation";


    private static final String EVENT_TABLE = "tableEvent";
    private static final String EVENT_COLUMN_EVENT_ID = "eventColumnId";
    private static final String EVENT_COLUMN_ASSIGNED_EVENT_ID = "eventAssignedColumnId";
    private static final String EVENT_COLUMN_EVENT_STATUS = "eventColumnStatus";
    private static final String EVENT_COLUMN_TIME_STAMP = "eventColumnTimestamp";
    private static final String EVENT_COLUMN_DURATION = "eventColumnDuration";
    private static final String EVENT_COLUMN_OCCASION_TYPE = "eventColumnOccasionType";
    private static final String EVENT_COLUMN_ENTERTAINMENT = "eventColumnEntertainment";
    private static final String EVENT_COLUMN_MEAL_TYPE = "eventColumnMealType";
    private static final String EVENT_COLUMN_DRINKS = "eventColumnDrinks";
    private static final String EVENT_COLUMN_LOCATION = "eventColumnLocation";
    private static final String EVENT_COLUMN_SIZE_OF_PARTY = "eventColumnSizeOfParty";
    private static final String EVENT_COLUMN_CATERER_ID = "eventColumnCatererId";
    private static final String EVENT_COLUMN_MEAL_FORMALITY = "eventColumnMealFormality";
    private static final String EVENT_COLUMN_FOOD_VENUE = "eventColumnFoodVenue";
    private static final String EVENT_COLUMN_STAFF_ID = "eventColumnStaffId";
    private static final String EVENT_COLUMN_UTA_ID = "eventColumnUtaId";
    private static final String EVENT_COLUMN_USER_ID = "eventColumnUserId";//?? why do we need this field
    private static final String EVENT_COLUMN_EVENT_COST = "eventColumnEventCost";
    private static final String EVENT_COLUMN_DATE = "eventColumnDate";
    private static final String EVENT_COLUMN_TIME = "eventColumnTime";
    private static final String EVENT_HALL_ID = "eventColumnHallId";
    private static final String EVENT_USER_FIRST_NAME = "eventColumnUserFirstName";


    private static String CREATE_TABLE_USER = "CREATE TABLE " + USER_TABLE + "(" + USER_COLUMN_USER_ID + " INTEGER PRIMARY KEY, "
            + USER_COLUMN_FIRST_NAME + " TEXT," + USER_COLUMN_LAST_NAME + " TEXT,"
            + USER_COLUMN_EMAIL_ID + " TEXT," + USER_COLUMN_PHONE_NUMBER + " TEXT,"
            + USER_COLUMN_CATEGORY + " TEXT," + USER_COLUMN_PASSWORD + " TEXT,"
            + USER_COLUMN_TIMESTAMP + " TEXT," + USER_COLUMN_STATUS + " TEXT,"
            + USER_COLUMN_ADDRESS + " TEXT,"
            + USER_COLUMN_UTA_ID + " TEXT NOT NULL" + ")";


    private static String CREATE_TABLE_EVENT = "CREATE TABLE " + EVENT_TABLE + "(" +
            EVENT_COLUMN_EVENT_ID + " INTEGER PRIMARY KEY, " + EVENT_COLUMN_ASSIGNED_EVENT_ID + " TEXT," +
            EVENT_COLUMN_EVENT_STATUS + " TEXT," + EVENT_COLUMN_TIME_STAMP + " TEXT,"
            + EVENT_COLUMN_DURATION + " TEXT," + EVENT_COLUMN_OCCASION_TYPE + " TEXT,"
            + EVENT_COLUMN_ENTERTAINMENT + " TEXT," + EVENT_COLUMN_MEAL_TYPE + " TEXT,"
            + EVENT_COLUMN_DRINKS + " TEXT," + EVENT_COLUMN_LOCATION + " TEXT,"
            + EVENT_COLUMN_SIZE_OF_PARTY + " INTEGER," + EVENT_COLUMN_CATERER_ID + " TEXT,"
            + EVENT_COLUMN_MEAL_FORMALITY + " TEXT," + EVENT_COLUMN_FOOD_VENUE + " TEXT,"
            + EVENT_COLUMN_STAFF_ID + " TEXT," + EVENT_COLUMN_EVENT_COST + " INTEGER,"
            + EVENT_COLUMN_DATE + " TEXT," + EVENT_COLUMN_USER_ID + " TEXT,"
            + EVENT_COLUMN_TIME + " TEXT," + EVENT_HALL_ID + " TEXT," + EVENT_USER_FIRST_NAME + " TEXT,"
            + EVENT_COLUMN_UTA_ID + " TEXT NOT NULL" + ")";


    private Context context;
    private SQLiteDatabase sqLliteDatabase;
    private static DatabaseAdapter databaseAdapter;


    private DatabaseAdapter(Context context) {
        this.context = context;
        sqLliteDatabase = new DBHelper(this.context, DB_NAME, null, DB_VERSION).getWritableDatabase();
    }

    /*

    Singleton implementation of Database
     */
    public static DatabaseAdapter getDBAdapterInstance(Context context) {
        if (databaseAdapter == null) {
            databaseAdapter = new DatabaseAdapter(context);
        }
        return databaseAdapter;
    }

    public boolean addUser(DatabaseUsersModel databaseUsersModel) {

        ContentValues contentValues = new ContentValues();
        //    contentValues.put(USER_COLUMN_USER_ID,databaseUsersModel.getUserColumnUserId());
        contentValues.put(USER_COLUMN_FIRST_NAME, databaseUsersModel.getUserColumnFirstName());
        contentValues.put(USER_COLUMN_LAST_NAME, databaseUsersModel.getUserColumnLastName());
        contentValues.put(USER_COLUMN_EMAIL_ID, databaseUsersModel.getUserColumnEmailId());
        contentValues.put(USER_COLUMN_PHONE_NUMBER, databaseUsersModel.getUserColumnPhoneNumber());
        contentValues.put(USER_COLUMN_CATEGORY, databaseUsersModel.getUserColumnCategory());
        contentValues.put(USER_COLUMN_PASSWORD, databaseUsersModel.getUserColumnPassword());
        contentValues.put(USER_COLUMN_TIMESTAMP, databaseUsersModel.getUserColumnTimestamp());
        contentValues.put(USER_COLUMN_STATUS, databaseUsersModel.getUserColumnStatus());
        contentValues.put(USER_COLUMN_ADDRESS, databaseUsersModel.getUserColumnAddress());
        contentValues.put(USER_COLUMN_UTA_ID, databaseUsersModel.getUserColumnUtaId());

        return sqLliteDatabase.insert(USER_TABLE, null, contentValues) > 0;
    }

    public List<DatabaseUsersModel> getUsers() {
        List<DatabaseUsersModel> databaseUsersModels = new ArrayList<DatabaseUsersModel>();

        Cursor cursor = sqLliteDatabase.query(USER_TABLE, new String[]{USER_COLUMN_USER_ID, USER_COLUMN_FIRST_NAME, USER_COLUMN_LAST_NAME,
                USER_COLUMN_EMAIL_ID, USER_COLUMN_PHONE_NUMBER, USER_COLUMN_CATEGORY, USER_COLUMN_PASSWORD, USER_COLUMN_TIMESTAMP,
                USER_COLUMN_STATUS, USER_COLUMN_ADDRESS, USER_COLUMN_UTA_ID}, null, null, null, null, null, null);


        if (cursor != null & cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                DatabaseUsersModel user = new DatabaseUsersModel();
                user.setUserColumnUserId(cursor.getInt(0));
                user.setUserColumnFirstName(cursor.getString(1));
                user.setUserColumnLastName(cursor.getString(2));
                user.setUserColumnEmailId(cursor.getString(3));
                user.setUserColumnPhoneNumber(cursor.getString(4));
                user.setUserColumnCategory(cursor.getString(5));
                user.setUserColumnPassword(cursor.getString(6));
                user.setUserColumnTimestamp(cursor.getString(7));
                user.setUserColumnStatus(cursor.getString(8));
                user.setUserColumnAddress(cursor.getString(9));
                user.setUserColumnUtaId(cursor.getString(10));

                databaseUsersModels.add(user);
            }
        }
        cursor.close();
        return databaseUsersModels;
    }

    public boolean insertEvents(DatabaseEventsModel databaseEventsModel) {

        ContentValues contentValues = new ContentValues();
//        contentValues.put(EVENT_COLUMN_EVENT_ID,databaseEventsModel.getEventColumnId());
        contentValues.put(EVENT_COLUMN_ASSIGNED_EVENT_ID, databaseEventsModel.getEventAssignedColumnId());
        contentValues.put(EVENT_COLUMN_EVENT_STATUS, databaseEventsModel.getEventColumnStatus());
        contentValues.put(EVENT_COLUMN_DURATION, databaseEventsModel.getEventColumnDuration());
        contentValues.put(EVENT_COLUMN_OCCASION_TYPE, databaseEventsModel.getEventColumnOccasionType());
        contentValues.put(EVENT_COLUMN_ENTERTAINMENT, databaseEventsModel.getEventColumnEntertainment());
        contentValues.put(EVENT_COLUMN_MEAL_TYPE, databaseEventsModel.getEventColumnMealType());
        contentValues.put(EVENT_COLUMN_DRINKS, databaseEventsModel.getEventColumnDrinks());
        contentValues.put(EVENT_COLUMN_LOCATION, databaseEventsModel.getEventColumnLocation());
        contentValues.put(EVENT_COLUMN_SIZE_OF_PARTY, databaseEventsModel.getEventColumnSizeOfParty());
        contentValues.put(EVENT_COLUMN_CATERER_ID, databaseEventsModel.getEventColumnCatererId());
        contentValues.put(EVENT_COLUMN_MEAL_FORMALITY, databaseEventsModel.getEventColumnMealFormality());
        contentValues.put(EVENT_COLUMN_FOOD_VENUE, databaseEventsModel.getEventColumnFoodVenue());
        contentValues.put(EVENT_COLUMN_STAFF_ID, databaseEventsModel.getEventColumnStaffId());
        contentValues.put(EVENT_COLUMN_EVENT_COST, databaseEventsModel.getEventColumnEventCost());
        contentValues.put(EVENT_COLUMN_DATE, databaseEventsModel.getEventColumnDate());
        contentValues.put(EVENT_COLUMN_USER_ID, databaseEventsModel.getEventColumnUserId());
        contentValues.put(EVENT_COLUMN_TIME, databaseEventsModel.getEventColumnTime());
        contentValues.put(EVENT_HALL_ID, databaseEventsModel.getEventColumnHallId());
        contentValues.put(EVENT_USER_FIRST_NAME, databaseEventsModel.getEventColumnUserFirstName());
        contentValues.put(EVENT_COLUMN_UTA_ID, databaseEventsModel.getEventColumnUtaId());

        return sqLliteDatabase.insert(EVENT_TABLE, null, contentValues) > 0;
    }

    public boolean deleteEvent(int eventId) {
        return sqLliteDatabase.delete(EVENT_TABLE, EVENT_COLUMN_EVENT_ID + " = " + eventId, null) > 0;
    }

    public boolean deleteUser(int userID) {
        return sqLliteDatabase.delete(USER_TABLE, USER_COLUMN_USER_ID + " = " + userID, null) > 0;
    }

    public boolean updateUserProfile(int userID, DatabaseUsersModel databaseUsersModel){
        ContentValues contentValues=new ContentValues();

        contentValues.put(USER_COLUMN_FIRST_NAME, databaseUsersModel.getUserColumnFirstName());
        contentValues.put(USER_COLUMN_LAST_NAME, databaseUsersModel.getUserColumnLastName());
        contentValues.put(USER_COLUMN_EMAIL_ID, databaseUsersModel.getUserColumnEmailId());
        contentValues.put(USER_COLUMN_PHONE_NUMBER, databaseUsersModel.getUserColumnPhoneNumber());
      // contentValues.put(USER_COLUMN_CATEGORY, databaseUsersModel.getUserColumnCategory());   //   I COMMENTED THIS BECAUSE WE DO NOT NEED TO UPDATE THIS.
                                                                                                // AT THE TIME OF RUNNING THE GET VALUE RETURNS NULL WHICH
                                                                                                 //WILL REPLACE THE ORIGINAL VALUE. THE SAME APPLIES TO ALL THE
                                                                                                 // ONES I COMMENTED OUT.
        contentValues.put(USER_COLUMN_PASSWORD, databaseUsersModel.getUserColumnPassword());
        contentValues.put(USER_COLUMN_ADDRESS, databaseUsersModel.getUserColumnAddress());
      //  contentValues.put(USER_COLUMN_UTA_ID, databaseUsersModel.getUserColumnUtaId());       //DOES NOT NEED TO BE UPDATED

        contentValues.put(USER_COLUMN_TIMESTAMP, databaseUsersModel.getUserColumnTimestamp());
      //  contentValues.put(USER_COLUMN_STATUS, databaseUsersModel.getUserColumnStatus());

        return sqLliteDatabase.update(USER_TABLE,contentValues, USER_COLUMN_USER_ID+" = "+userID,null)>0;
    }

    public boolean updateEvent(int eventID, DatabaseEventsModel databaseEventsModel){
        ContentValues contentValues=new ContentValues();

        contentValues.put(EVENT_COLUMN_EVENT_STATUS, databaseEventsModel.getEventColumnStatus());
        contentValues.put(EVENT_COLUMN_LOCATION, databaseEventsModel.getEventColumnLocation());
        contentValues.put(EVENT_COLUMN_CATERER_ID, databaseEventsModel.getEventColumnCatererId());
        contentValues.put(EVENT_COLUMN_STAFF_ID, databaseEventsModel.getEventColumnStaffId());
        contentValues.put(EVENT_HALL_ID, databaseEventsModel.getEventColumnHallId());

        return sqLliteDatabase.update(EVENT_TABLE,contentValues, EVENT_COLUMN_EVENT_ID+" = "+eventID,null)>0;
    }

    /**
     * This method to check user exist or not
     *
     * @param utaId
     * @param password
     * @return true/false
     */
    public String checkUser(String utaId, String password) {

        String category="Not Found";
        // array of columns to fetch
        String[] columns = {
                USER_COLUMN_USER_ID,USER_COLUMN_CATEGORY
        };
        // selection criteria
        String selection = USER_COLUMN_UTA_ID + " = ?" + " AND " + USER_COLUMN_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {utaId, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = sqLliteDatabase.query(USER_TABLE, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        if (cursorCount > 0 && cursor.moveToFirst()) {
                category = cursor.getString(1);
            }

        cursor.close();
        return category;
    }



    public List<DatabaseEventsModel> getEvents() {
        List<DatabaseEventsModel> databaseEventsModels = new ArrayList<DatabaseEventsModel>();

        Cursor cursor = sqLliteDatabase.query(EVENT_TABLE, new String[]{EVENT_COLUMN_EVENT_ID, EVENT_COLUMN_ASSIGNED_EVENT_ID, EVENT_COLUMN_EVENT_STATUS,
                EVENT_COLUMN_TIME_STAMP, EVENT_COLUMN_DURATION, EVENT_COLUMN_OCCASION_TYPE, EVENT_COLUMN_ENTERTAINMENT, EVENT_COLUMN_MEAL_TYPE,
                EVENT_COLUMN_DRINKS, EVENT_COLUMN_LOCATION, EVENT_COLUMN_SIZE_OF_PARTY, EVENT_COLUMN_CATERER_ID, EVENT_COLUMN_MEAL_FORMALITY,
                EVENT_COLUMN_FOOD_VENUE, EVENT_COLUMN_STAFF_ID, EVENT_COLUMN_UTA_ID, EVENT_COLUMN_USER_ID, EVENT_COLUMN_EVENT_COST,
                EVENT_COLUMN_DATE, EVENT_COLUMN_TIME, EVENT_HALL_ID, EVENT_USER_FIRST_NAME}, null, null, null, null, null, null);


        if (cursor != null & cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                DatabaseEventsModel event = new DatabaseEventsModel();
                event.setEventColumnId(cursor.getInt(0));
                event.setEventAssignedColumnId(cursor.getString(1));
                event.setEventColumnStatus(cursor.getString(2));
                event.setEventColumnTimestamp(cursor.getString(3));
                event.setEventColumnDuration(cursor.getString(4));
                event.setEventColumnOccasionType(cursor.getString(5));
                event.setEventColumnEntertainment(cursor.getString(6));
                event.setEventColumnMealType(cursor.getString(7));
                event.setEventColumnDrinks(cursor.getString(8));
                event.setEventColumnLocation(cursor.getString(9));
                event.setEventColumnSizeOfParty(cursor.getInt(10));
                event.setEventColumnCatererId(cursor.getString(11));
                event.setEventColumnMealFormality(cursor.getString(12));
                event.setEventColumnFoodVenue(cursor.getString(13));
                event.setEventColumnStaffId(cursor.getString(14));
                event.setEventColumnUtaId(cursor.getString(15));
                event.setEventColumnUserId(cursor.getString(16));
                event.setEventColumnEventCost(cursor.getInt(17));
                event.setEventColumnDate(cursor.getString(18));
                event.setEventColumnTime(cursor.getString(19));
                event.setEventColumnHallId(cursor.getString(20));
                event.setEventColumnUserFirstName(cursor.getString(21));

                databaseEventsModels.add(event);

            }
        }
        cursor.close();
        return databaseEventsModels;
    }


    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int dbVersion) {
            super(context, databaseName, factory, dbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE_USER);
            sqLiteDatabase.execSQL(CREATE_TABLE_EVENT);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                              int oldVersion, final int newVersion) {
        }
    }
}
