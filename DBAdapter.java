package com.ersolution.ernavigator.sqlite;

/**
 * Created by Jeremy on 8/16/2014.
 */
public class DBAdapter {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "data";

    public static final String USER_TABLE = "user";
    public static final String USER_TABLE_EMAIL = "email";
    public static final String USER_TABLE_USER_NAME = "userName";
    public static final String USER_TABLE_FULL_NAME = "fullName";
    public static final String USER_TABLE_CELL_PHONE = "cellPhone";
    public static final String USER_TABLE_USER_AVATAR = "userAvatar";
    public static final String IMAGE_TABLE = "image";
    public static final String IMAGE_TABLE_URL = "url";
    public static final String IMAGE_TABLE_IMAGE = "image";

    public static final String USER_TABLE_INIT =
            "CREATE TABLE `" + USER_TABLE + "` (\n" +
                    "\t`" + USER_TABLE_EMAIL + "`\tTEXT,\n" +
                    "\t`" + USER_TABLE_USER_NAME + "`\tTEXT,\n" +
                    "\t`" + USER_TABLE_FULL_NAME + "`\tTEXT,\n" +
                    "\t`" + USER_TABLE_CELL_PHONE + "`\tTEXT,\n" +
                    "\t`" + USER_TABLE_USER_AVATAR + "`\tTEXT\n" +
                    ");";

    public static final String IMAGE_TABLE_INIT =
            "CREATE TABLE `" + IMAGE_TABLE + "` (\n" +
                    "\t`" + IMAGE_TABLE_URL + "`\tTEXT,\n" +
                    "\t`" + IMAGE_TABLE_IMAGE + "`\tBLOB,\n" +
                    "\tPRIMARY KEY(" + IMAGE_TABLE_URL + ")\n" +
                    ");";

    public static final String[] ALL_TABLES =
            {
                    USER_TABLE_INIT,
                    IMAGE_TABLE_INIT
            };
}
