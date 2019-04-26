package stan.store.demo.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import stan.store.demo.Helper.Global;
import stan.store.demo.Helper.SQLiteHelper;
import stan.store.demo.Model.User;
import stan.store.demo.R;

public class LoginActivity extends AppCompatActivity {
    //UI
    private EditText mEditText_Account;
    private EditText mEditText_Password;
    private Button mButton_Login;
    private Button mButton_Regitster;
    private Button mButton_AddProduct;

    //DataBase
    private SQLiteHelper mDBHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDBHelper = new SQLiteHelper(LoginActivity.this);

        //InitUI
        mEditText_Account = (EditText) findViewById(R.id.editText_Account);
        mEditText_Password = (EditText) findViewById(R.id.editText_Password);
        mButton_Login = (Button) findViewById(R.id.button_Login);
        mButton_Regitster = (Button) findViewById(R.id.button_Register);
        mButton_AddProduct = (Button) findViewById(R.id.button_AddProduct);

        //Click Event
        mButton_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = mEditText_Account.getText().toString();
                String password = mEditText_Password.getText().toString();

                //Step1 檢查資料完整性
                if (account.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this,"請輸入登入資訊", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Step2 驗證資料正確性
                String Check_SQL = "SELECT * FROM user WHERE id = " + account + " AND password = " + password;
                if (mDBHelper.QuerySQL(Check_SQL).size() > 0) {
                    Global.UserData = new User(mDBHelper.QuerySQL(Check_SQL).get(0));
                    Toast.makeText(LoginActivity.this,Global.UserData.getName() + " 您好，登入成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, IndexActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,"帳號密碼有誤", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButton_Regitster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        mButton_AddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 1 ; i <= 10 ; i++) {
                    HashMap<String,String> tmpMap = new HashMap<String,String>();
                    tmpMap.put("name", "產品名稱" + i);
                    tmpMap.put("price", String.valueOf(100 * i));
                    mDBHelper.addData("product", tmpMap);
                }
                Toast.makeText(LoginActivity.this,"新增成功", Toast.LENGTH_SHORT).show();
                mButton_AddProduct.setEnabled(false);
            }
        });

    }
}
