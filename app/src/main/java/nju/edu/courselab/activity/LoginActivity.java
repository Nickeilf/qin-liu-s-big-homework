package nju.edu.courselab.activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import nju.edu.courselab.R;
import nju.edu.courselab.Util.AsycThread;
import nju.edu.courselab.Util.JsonPost;
import nju.edu.courselab.bean.Login;
import nju.edu.courselab.bean.Student;
import nju.edu.courselab.bean.Teacher;
import nju.edu.courselab.bean.User;
import nju.edu.courselab.dataService.LoginDataService;
import nju.edu.courselab.dataService.impl.LoginDataServiceImpl;

public class LoginActivity extends AppCompatActivity implements OnClickListener {
    private LoginDataService loginService;
    private User user;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            String response = (String) msg.obj;
            switch (msg.what){
                case 1:
                    System.out.println("get result");
                    Gson gson = new Gson();
                    if(response.length()!=0) {
                        if (response.contains("authority")) {
                            //teacher
                            Teacher teacher = gson.fromJson(response, Teacher.class);
                            user = teacher;
                        } else if (response.contains("gitId")){
                            //student
                            Student student = gson.fromJson(response, Student.class);
                            user = student;
                        }
                        System.out.println(response);
                    }
                    actLogin();
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }
    };

    private void actLogin() {
        if (user==null){
            Toast.makeText(this,"密码错误",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
            if (user instanceof Teacher){
                //TODO 进入老师界面
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("avatar", user.getAvatar());
                bundle.putString("username",user.getUsername());
                bundle.putString("name",user.getName());
                bundle.putString("gender",user.getGender());
                bundle.putString("email",user.getEmail());
                bundle.putString("password",et_pass.getText().toString());
                intent.putExtras(bundle);
                intent.setClass(this, TeacherMainActivity.class);
                startActivity(intent);
            }else{
                //TODO 进入学生界面
                Student student = (Student) user;
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("avatar", student.getAvatar());
                bundle.putString("username",student.getUsername());
                bundle.putString("name",student.getName());
                bundle.putString("gender",student.getGender());
                bundle.putString("email",student.getEmail());
                bundle.putString("git_username",student.getGitUsername());
                bundle.putString("student_id",student.getId()+"");
                bundle.putString("number",student.getNumber()+"");
                bundle.putString("password",et_pass.getText().toString());
                intent.putExtras(bundle);
                intent.setClass(this, StudentMainActivity.class);
                startActivity(intent);
            }

        }
    }

    // 声明控件对象
    private EditText et_name, et_pass;
    private Button mLoginButton;
    boolean isReLogin=false;
    public final static int LOGIN_ENABLE=0x01;    //注册完毕了
    public final static int LOGIN_UNABLE=0x02;    //注册完毕了
    public final static int PASS_ERROR=0x03;      //注册完毕了
    public final static int NAME_ERROR=0x04;      //注册完毕了


    private Button bt_username_clear;
    private Button bt_pwd_clear;
    private Button bt_pwd_eye;
    private TextWatcher username_watcher;
    private TextWatcher password_watcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//  requestWindowFeature(Window.FEATURE_NO_TITLE);
//  //不显示系统的标题栏
//  getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
//    WindowManager.LayoutParams.FLAG_FULLSCREEN );

        setContentView(R.layout.activity_login);
        et_name = (EditText) findViewById(R.id.username);
        et_pass = (EditText) findViewById(R.id.password);

        bt_username_clear = (Button)findViewById(R.id.bt_username_clear);
        bt_pwd_clear = (Button)findViewById(R.id.bt_pwd_clear);
        bt_pwd_eye = (Button)findViewById(R.id.bt_pwd_eye);
        bt_username_clear.setOnClickListener(this);
        bt_pwd_clear.setOnClickListener(this);
        bt_pwd_eye.setOnClickListener(this);
        initWatcher();
        et_name.addTextChangedListener(username_watcher);
        et_pass.addTextChangedListener(password_watcher);

        mLoginButton = (Button) findViewById(R.id.login);
        mLoginButton.setOnClickListener(this);
        loginService= new LoginDataServiceImpl();
    }

    /**
     * 手机号，密码输入控件公用这一个watcher
     */
    private void initWatcher() {
        username_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
            public void afterTextChanged(Editable s) {
                et_pass.setText("");
                if(s.toString().length()>0){
                    bt_username_clear.setVisibility(View.VISIBLE);
                }else{
                    bt_username_clear.setVisibility(View.INVISIBLE);
                }
            }
        };

        password_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    bt_pwd_clear.setVisibility(View.VISIBLE);
                }else{
                    bt_pwd_clear.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    private void login(){
        if (et_name.getText().length()==0||et_pass.getText().length()==0){
            Toast.makeText(this,"用户名或密码为空",Toast.LENGTH_SHORT).show();
        }else{
            System.out.println("????");
            JsonPost.PostJsonThread t = new JsonPost.PostJsonThread("http://115.29.184.56:8090/api/user/auth",new Gson().toJson(new Login(et_name.getText().toString(),et_pass.getText().toString())),handler);
            t.start();
        }
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.login:
                login();
                break;
            case R.id.bt_username_clear:
                et_name.setText("");
                et_pass.setText("");
                break;
            case R.id.bt_pwd_clear:
                et_pass.setText("");
                break;
            case R.id.bt_pwd_eye:
                if(et_pass.getInputType() == (InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD)){
                    bt_pwd_eye.setBackgroundResource(R.drawable.button_eye_s);
                    et_pass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL);
                }else{
                    bt_pwd_eye.setBackgroundResource(R.drawable.button_eye_n);
                    et_pass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                et_pass.setSelection(et_pass.getText().toString().length());
                break;
        }
    }


    /**
     * 监听Back键按下事件,方法2:
     * 注意:
     * 返回值表示:是否能完全处理该事件
     * 在此处返回false,所以会继续传播该事件.
     * 在具体项目中此处的返回值视情况而定.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if(isReLogin){
                Intent mHomeIntent = new Intent(Intent.ACTION_MAIN);
                mHomeIntent.addCategory(Intent.CATEGORY_HOME);
                mHomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                LoginActivity.this.startActivity(mHomeIntent);
            }else{
                LoginActivity.this.finish();
            }
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

}