package stan.store.demo.Model;

import java.util.HashMap;

public class Product {
    // 定義 ColumnName
    final String ID = "id";
    final String NAME = "name";
    final String PRICE = "price";

    //Product的資料
    private HashMap<String,String> mHashMap_Data;

    public Product (HashMap<String,String> tmpMap) {
        mHashMap_Data = tmpMap;
    }

    public String getID(){
        return mHashMap_Data.get(ID);
    }

    public String getName(){
        return mHashMap_Data.get(NAME);
    }

    public String getPrice(){
        return mHashMap_Data.get(PRICE);
    }
}
