package stan.store.demo.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import stan.store.demo.Helper.SQLiteHelper;
import stan.store.demo.Model.User;
import stan.store.demo.R;

public class LoginActivity extends AppCompatActivity {
    //UI
    private EditText mEditText_Account;
    private EditText mEditText_Password;
    private Button mButton_Login;
    private Button mButton_Regitster;

    //DataBase
    private SQLiteHelper mDBHelper ;

    //Model
    private User mUser;

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
                    mUser = new User(mDBHelper.QuerySQL(Check_SQL).get(0));
                    Toast.makeText(LoginActivity.this,mUser.getName() + " 您好，登入成功", Toast.LENGTH_SHORT).show();
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

    }
}
