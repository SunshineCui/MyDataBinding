package com.example.f.myapplication.view.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import com.example.f.myapplication.R;
import com.example.f.myapplication.databinding.FragmentMineBinding;
import com.example.f.myapplication.model.ItemMineCommon;
import com.example.f.myapplication.model.User;

/**
 * Created by Billy_Cui on 2018/5/17
 * Describe:
 */
public class mineFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, AdapterView.OnItemSelectedListener {

    private FragmentMineBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false);
        initData();
        return binding.getRoot();
    }

    private void initData() {
        User user = new User("李西坜", "护士长", "一二三四五六七八九十", "妇产科病区");
        binding.setUser(user);
        ItemMineCommon itemMineCommon = new ItemMineCommon(new ColorDrawable(0xff17bf8d), "计时功能", "00:00.00", false);
        binding.setCommon0(itemMineCommon);
        binding.setCommon1(new ItemMineCommon(new ColorDrawable(0xffff9800), "体温测试", null, false));
        binding.setCommon2(new ItemMineCommon(new ColorDrawable(0xfff54d42), "只显示我的患者", null, true));
        binding.setCommon3(new ItemMineCommon(new ColorDrawable(0xff2783d9), "医嘱通知", null, true));
        binding.setCommon4(new ItemMineCommon(new ColorDrawable(0xfff54d42), "我的排班", null, false));
        binding.setCommon5(new ItemMineCommon(new ColorDrawable(0xff2783d9), "请假", null, false));

        binding.setOnClick(this);
        binding.setSwitchClick(this);
        String[] stringArray = getResources().getStringArray(R.array.workSpaceList);
//        List<String> strings = Arrays.asList(stringArray);
//        binding.setWorkList(strings);
        AppCompatSpinner itemMineHeadAcs = binding.itemMineHead.itemMineHeadAcs;
        //Spinner中文框显示样式
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.widget_mine_spinner_self_item, stringArray);
        //Spinner下拉菜单显示样式
        adapter.setDropDownViewResource(R.layout.widget_mine_spinner_dropdown_item);
        itemMineHeadAcs.setAdapter(adapter);
        itemMineHeadAcs.setOnItemSelectedListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
