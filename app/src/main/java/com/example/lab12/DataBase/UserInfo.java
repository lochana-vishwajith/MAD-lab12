package com.example.lab12.DataBase;

import android.provider.BaseColumns;

public final class UserInfo {

    private UserInfo() {}

    public static final class Users implements BaseColumns {

        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_PASSWORD = "Password";
        public static final String COLUMN_NAME_TYPE = "Type";

    }

    public static final class Message implements BaseColumns{

        public static final String TABLE_NAME = "message";
        public static final String COLUMN_NAME_NAME = "user";
        public static final String COLUMN_NAME_SUBJECT = "Subject";
        public static final String COLUMN_NAME_MESSAGE = "Message";
    }
}
