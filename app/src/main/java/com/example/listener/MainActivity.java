package com.example.listener;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    ImageView iv_bian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        Object month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        Log.e("time_now", ""+year+"_"+month+1+"_"+day+"_"+hour+"_"+minute);


        SimpleDateFormat formatter  =  new    SimpleDateFormat    ("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str10 = formatter.format(curDate);
        Log.e("time_now1",""+str10);


        long time=System.currentTimeMillis();
        Log.e("time_now2", ""+time);
        TextView textview1 = (TextView)findViewById(R.id.textview1);
        textview1.setAutoLinkMask(Linkify.ALL);

        String linktext = "百度链接 www.baidu.com";
        textview1.setText(linktext);

        //AutoCompleteTextView
        AutoCompleteTextView autv_1 = (AutoCompleteTextView)findViewById(R.id.autv_1);
        String[] str = {"ab","abc","abcd"};

        ArrayAdapter<String> stringArrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,str);

        autv_1.setAdapter(stringArrayAdapter);

        //Button 点击
        Button btn_1 = (Button)findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "按钮点击监听", Toast.LENGTH_SHORT).show();
            }
        });

        //Button 长按监听
        btn_1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(MainActivity.this, "按钮长按监听", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //RadioGroup 的监听
        RadioGroup radio_gp = (RadioGroup)findViewById(R.id.radio_gp);
        radio_gp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rb_shi = (RadioButton) findViewById(R.id.rb_shi);

                RadioButton rb_fou = (RadioButton) findViewById(R.id.rb_fou);

                switch (checkedId) {
                    case R.id.rb_shi:
                        Toast.makeText(MainActivity.this, "选中了" + rb_shi.getText(), Toast.LENGTH_SHORT).show();

                    case R.id.rb_fou:
                        Toast.makeText(MainActivity.this, "选中了" + rb_fou.getText(), Toast.LENGTH_SHORT).show();

                }

            }
        });

        //显示Edittext的输入内容
        EditText editText = (EditText)findViewById(R.id.et_1);

        String str1 = editText.getText().toString();

        Toast.makeText(MainActivity.this, str1, Toast.LENGTH_SHORT).show();


        //checkbox的监听

        CheckBox cb_fuxuan1 = (CheckBox)findViewById(R.id.cb_fuxuan1);
        cb_fuxuan1.setOnCheckedChangeListener(new checkboxcheckedlistener());


        CheckBox cb_fuxuan2 = (CheckBox)findViewById(R.id.cb_fuxuan2);
        cb_fuxuan2.setOnCheckedChangeListener(new checkboxcheckedlistener());

        //menu 菜单 上下文菜单
        Button changan_menu = (Button)findViewById(R.id.menu_1);
        changan_menu.setOnCreateContextMenuListener(this);

        //SeekBar 可拖动进度条
        SeekBar sbr_td = (SeekBar)findViewById(R.id.sbr_tuodong);

        sbr_td.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //开关键控制按钮背景
        iv_bian = (ImageView)findViewById(R.id.iv_bian);

        //开关按钮
        ToggleButton tb = (ToggleButton)findViewById(R.id.tgb_1);

        tb.setOnCheckedChangeListener(new anniucheckedlistener());

        //推拉开关
        Switch swh = (Switch)findViewById(R.id.swh_1);
        swh.setOnCheckedChangeListener(new anniucheckedlistener());
    }

    //普通内部类  checkbox的监听
    private class checkboxcheckedlistener implements CompoundButton.OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            CheckBox cb = (CheckBox)buttonView;

            if (isChecked)
            {
                Toast.makeText(MainActivity.this, "选中了"+cb.getText(), Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "取消选中了"+cb.getText(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //menu 菜单 上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1,1,1,"添加");
        menu.add(1,2,2,"删除");
        menu.add(1,3,3,"修改");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case 1:
                Toast.makeText(MainActivity.this, "触发了添加功能", Toast.LENGTH_SHORT).show();
            case 2:
                Toast.makeText(MainActivity.this, "触发了删除功能", Toast.LENGTH_SHORT).show();
            case 3:
                Toast.makeText(MainActivity.this, "触发了修改功能", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    private class anniucheckedlistener implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked)
            {
                iv_bian.setImageResource(R.drawable.timg);
            }
            else
            {
                iv_bian.setImageResource(R.drawable.timg1);
            }
        }
    }
    //普通对话框
    public void putongdhkonclick(View view)
    {
        //普通对话框
        //对话框的构建器
       /* AlertDialog.Builder ab = new AlertDialog.Builder(this);

        ab.setTitle("数据删除");
        ab.setMessage("确定删除吗？");
        ab.setPositiveButton("确定",null);
        ab.setNegativeButton("取消",null);
        ab.setCancelable(false);
        ab.show();*/

        //方法链的方法
        new AlertDialog.Builder(this)
                .setTitle("数据删除")
                .setMessage("确定删除吗？")
                .setPositiveButton("确定",null)
                .setNegativeButton("取消",null)
                .setCancelable(false)
                .show();
    }

    public void danxuandhkonclick(View view)
    {
        final String[] yanse = {"红","黄","绿","蓝","白"};
        //方法链
        new AlertDialog.Builder(this)

                .setTitle("单选对话框")
                .setSingleChoiceItems(yanse, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this, "选中了" + yanse[which], Toast.LENGTH_SHORT).show();

                        //移除属性 dialog.dismiss(); 选中一个就会关闭
                    }
                })
                .setNeutralButton("确定", null)//普通按钮
                .setCancelable(false)
                .show();
    }

    public void duoxuandhkonclick(View view)
    {
        final String[] yanse = {"红","黄","绿","蓝","白"};
        final boolean[] bl = {true,true,false,false,false};
        //方法链

        new AlertDialog.Builder(this)
                .setTitle("多选对话框")
                .setMultiChoiceItems(yanse, bl, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        if (isChecked) {
                            Toast.makeText(MainActivity.this, "选中了" + yanse[which], Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "取消选中了" + yanse[which], Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //遍历数组 foreach循环
                        for (boolean b : bl) {
                            try {
                                sleep(100);
                            } catch (Exception ex) {


                            }Toast.makeText(MainActivity.this, "取值"+b, Toast.LENGTH_SHORT).show();

                        }
                    }
                })
                .setCancelable(false)
                .show();
    }

    //自定义对话框
    public void zidingyidhkonclick(View view)
    {
        //1.获取加载器
        LayoutInflater layoutInflater = getLayoutInflater();
        //2.用加载器加载文件
        final View view2 = layoutInflater.inflate(R.layout.loginlayout, null);

        //方法链构造页面加两个按钮

        new AlertDialog.Builder(this)
               .setView(view2)//兼容性好，比较适用
                //.setView(R.layout.loginlayout)
                .setNegativeButton("取消", null)
                .setPositiveButton("登陆", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText user = (EditText) view2.findViewById(R.id.et_username);

                        EditText pas = (EditText) view2.findViewById(R.id.et_password);
                        String user1=user.getText().toString();
                        String pas1=pas.getText().toString();
                        Log.e("登录","账号"+user1+"    密码"+pas1);
                    }
                })

                .show();
    }
    //旋转进度条
    public void xuanzhuanjdtonclick(View view)
    {
        final ProgressDialog pd = new ProgressDialog(this);

        pd.setMessage("正在加载");
        pd.show();

        //创建thread实例  =【重写run方法  启动多线程
        new Thread()
        {
            @Override
            public void run() {
                super.run();

                try{

                    Thread.sleep(3000);
                }
                catch (Exception ex){}

                pd.dismiss();//关闭
            }
        }.start();
    }

    //水平进度条
    public void shuipingjdtonclick(View view)
    {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("正在加载");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.show();
    }

    //日期对话框
    public void riqidhkonclick(View view)
    {
        //获取当前日期
        //单例模式，设计模式的一种  静态方法
        Calendar cl = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Toast.makeText(MainActivity.this, year+"-"+ (monthOfYear+1) + "-" + dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        },cl.get(Calendar.YEAR),cl.get(Calendar.MONTH),cl.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.setCancelable(false);
        datePickerDialog.show();

    }

    //时间对话框
    public void shijiandhkonclick(View view)
    {
        //获取当前日期
        //单例模式，设计模式的一种  静态方法
        Calendar cl = Calendar.getInstance();

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                Toast.makeText(MainActivity.this, hourOfDay+":"+minute , Toast.LENGTH_SHORT).show();
            }
        },cl.get(Calendar.HOUR),cl.get(Calendar.MINUTE),true);

        timePickerDialog.setCancelable(false);
        timePickerDialog.show();

    }
}
