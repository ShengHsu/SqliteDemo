package stan.store.demo.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import stan.store.demo.Helper.SQLiteHelper;
import stan.store.demo.Model.User;
import stan.store.demo.R;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "Demo";

    //UI
    private EditText mEditText_Name, mEditText_Phone, mEditText_Password;
    private Button mButton_Register;

    //DB
    private SQLiteHelper mDBHelper ;

    //Model
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDBHelper = new SQLiteHelper(RegisterActivity.this);

        // getAll Method
//        for (HashMap<String,String> mapData : mDBHelper.getAll("user")) {
//            for (String key : mapData.keySet()) {
//                Log.e(TAG, "onCreate: " + key + "->" + mapData.get(key));
//            }
//        }

        // QuerySQL Method
        String SQL = "SELECT * FROM user WHERE id = 2";
        for (HashMap<String,String> mapData : mDBHelper.QuerySQL(SQL)) {
            for (String key : mapData.keySet()) {
//                Log.e(TAG, "onCreate: " + key + "->" + mapData.get(key));
            }
            mUser = new User(mapData);
        }

        Log.e(TAG, "User: " + mUser.getPhone());


        //Step1 賦予元件id
        mEditText_Name = (EditText) findViewById(R.id.editText_Name);
        mEditText_Phone = (EditText) findViewById(R.id.editText_Phone);
        mEditText_Password = (EditText) findViewById(R.id.editText_Password);
        mButton_Register = (Button) findViewById(R.id.button_Register);

        //step2 按鈕監聽事件
        mButton_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEditText_Name.getText().toString();
                String phone = mEditText_Phone.getText().toString();
                String password = mEditText_Password.getText().toString();
                HashMap<String,String> tmpMap = new HashMap<String,String>();
                tmpMap.put("name",name);
                tmpMap.put("phone",phone);
                tmpMap.put("password",password);
                mDBHelper.addData("user", tmpMap);

                Log.e(TAG, "Register Success");
            }
        });

    }

}
