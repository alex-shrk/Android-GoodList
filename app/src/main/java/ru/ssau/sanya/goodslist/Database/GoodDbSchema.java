package ru.ssau.sanya.goodslist.Database;

public class GoodDbSchema {
    public static final class GoodTable{
        public static final String NAME = "goods";
        public static final class Cols{
            public static final String ID = "_id";
            public static final String NAME = "name";
            public static final String DESCR = "descr";
            public static final String PRICE = "price";
            public static final String COUNT = "count";
        }
    }
}
