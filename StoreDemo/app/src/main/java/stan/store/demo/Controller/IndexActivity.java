package stan.store.demo.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import stan.store.demo.Adapter.Adapter_Product;
import stan.store.demo.Helper.SQLiteHelper;
import stan.store.demo.Model.Product;
import stan.store.demo.R;

public class IndexActivity extends AppCompatActivity {
    //UI
    private RecyclerView mRecyclerView_Product;

    //Adapter
    private Adapter_Product mAdapter_Product;

    //DataBase
    private SQLiteHelper mDBHelper ;

    //Parse
    private ArrayList<HashMap<String,String>> mArrayList_ProductData;
    private ArrayList<Product> mArrayList_ProductModel = new ArrayList<Product>();

    //Model
    private Product mProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mDBHelper = new SQLiteHelper(IndexActivity.this);

        //元件定義
        mRecyclerView_Product = (RecyclerView) findViewById(R.id.RecyclerView_Product);

        //先拿到產品資料
        mArrayList_ProductData = mDBHelper.getAll("product");
        for (int i = 0 ; i < mArrayList_ProductData.size() ; i++) {
            mProduct = new Product(mArrayList_ProductData.get(i));
            mArrayList_ProductModel.add(mProduct);
        }

        //設定Adapter + RecyclerView
        mAdapter_Product = new Adapter_Product(IndexActivity.this, mArrayList_ProductModel);
        mRecyclerView_Product.setLayoutManager(new LinearLayoutManager(IndexActivity.this));
        mRecyclerView_Product.setAdapter(mAdapter_Product);

    }
}
