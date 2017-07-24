package com.example.android.foundvet.login;

import android.provider.BaseColumns;

/**
 * Created by dfilipep on 19/07/2017.
 */

public class LoginContract {

    public static final class LoginEntry implements BaseColumns {
        public static final String TABLE_NAME = "login";
        public static final String COLUMN_NAME = "loginName";
        public static final String COLUMN_PASSOWORD = "loginPassword";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PHONE = "phoneNumber";
    }
}
