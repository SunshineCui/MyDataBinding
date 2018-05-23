package com.example.f.myapplication.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.f.myapplication.Base.myApplication;
import com.example.f.myapplication.R;
import com.example.f.myapplication.databinding.ActivityMineBinding;
import com.example.f.myapplication.model.ItemMineCommon;
import com.example.f.myapplication.model.User;
import com.example.f.myapplication.utils.TimeUtil;
import com.example.f.myapplication.viewModel.MineViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class MineActivity extends AppCompatActivity implements IView, View.OnClickListener, CompoundButton.OnCheckedChangeListener,AdapterView.OnItemSelectedListener {

    private static final String TAG = "MineActivity.class";
    private MineViewModel viewModel;
    private User user;
    private ItemMineCommon itemMineCommon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getSupportActionBar().hide();

        ActivityMineBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mine);
//        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
//        Drawable  colorDrawable = new ColorDrawable(0xffff0000);
//        Drawable drawable1 = getResources().getDrawable(R.mipmap.message);
        user = new User("李西坜", "护士长", "一二三四五六七八九十", "妇产科病区");
        binding.setUser(user);
        itemMineCommon = new ItemMineCommon(new ColorDrawable(0xff17bf8d), "计时功能", "00:00.00", false);
//        binding.setCommon_0(itemMineCommon);
//        binding.setCommon_1(new ItemMineCommon(new ColorDrawable(0xffff9800), "体温测试", null, false));
//        binding.setCommon_2(new ItemMineCommon(new ColorDrawable(0xfff54d42), "只显示我的患者", null, true));
//        binding.setCommon_3(new ItemMineCommon(new ColorDrawable(0xff2783d9), "医嘱通知", null, true));
//        binding.setCommon_4(new ItemMineCommon(new ColorDrawable(0xfff54d42), "我的排班", null, false));
//        binding.setCommon_5(new ItemMineCommon(new ColorDrawable(0xff2783d9), "请假", null, false));

        binding.setOnClick(this);
        binding.setSwitchClick(this);
        String[] stringArray = getResources().getStringArray(R.array.workSpaceList);
//        List<String> strings = Arrays.asList(stringArray);
//        binding.setWorkList(strings);
        viewModel = new MineViewModel(this);
        viewModel.init();
        AppCompatSpinner itemMineHeadAcs = binding.itemMineHead.itemMineHeadAcs;
        //Spinner中文框显示样式
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.widget_mine_spinner_self_item, stringArray);
        //Spinner下拉菜单显示样式
        adapter.setDropDownViewResource(R.layout.widget_mine_spinner_dropdown_item);
        itemMineHeadAcs.setAdapter(adapter);
        itemMineHeadAcs.setOnItemSelectedListener(this);
    }

    @Override
    public void refresh() {
        Log.d(TAG, "refrech()");
        Runtime runtime = Runtime.getRuntime();
        Log.d(TAG,"maxMemory:"+runtime.maxMemory());
        Log.d(TAG,"totalMemory:"+runtime.totalMemory());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.destroy();
        destroyTimer();
        myApplication.getRefWatcher(this).watch(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_mine_common_0://计时功能
                if (!isPause) {
                    if (currentSecond != 0) {//暂停
                        isPause = !isPause;
                        timer.purge();
                        break;
                    }
                } else {
                    isPause = !isPause;
                    destroyTimer();
                }
                initTimer();
                // 参数：
                // 1000，延时1秒后执行。
                // 2000，每隔2秒执行1次task。
                timer.schedule(task, 0, 10);
                break;
            case R.id.item_mine_common_1:
                Toast.makeText(this, "第er个页面！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_mine_common_4:
                Toast.makeText(this, "第五个页面！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_mine_common_5:
                Toast.makeText(this, "第六个页面！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.title_iv:
                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(),MainActivity.class);
                intent.setClass(getApplicationContext(),DownloadActivity.class);
                startActivity(intent);
                break;
            default:

                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ViewGroup viewGroup = (ViewGroup) buttonView.getParent();
        switch (viewGroup.getId()) {
            case R.id.item_mine_common_2:
                Log.d(TAG, "isChecked2  " + isChecked);
                break;
            case R.id.item_mine_common_3:
                Log.d(TAG, "isChecked3  " + isChecked);
                break;
            default:
                break;
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemSelected  " + position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d(TAG, "onItemSelected  " );
    }

        /*****************计时器*******************/
    private boolean isPause = false;//是否暂停
    private long currentSecond = 0;//当前毫秒数
    private Timer timer; //计时器
    private TimerTask task; // 计时任务

    //销毁timer
    @SuppressLint("StaticFieldLeak")
    public void destroyTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }
    }
    //初始化timer
    public void initTimer() {
        timer = new Timer();
        currentSecond = 0;
        task = new TimerTask() {
            @Override
            public void run() {
                if (!isPause) {
                    currentSecond += 10;
                    itemMineCommon.setTime(TimeUtil.getFormatMS(currentSecond));
                }
            }
        };
    }
    /*****************计时器*******************/
}
