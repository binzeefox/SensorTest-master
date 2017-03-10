package com.binzeefox.sensortest.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.binzeefox.sensortest.R;
import com.binzeefox.sensortest.util.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    /*自定义标题栏的按钮和标题*/

    private SensorManager sensorManager;
    private ListView showSensor;

    private Button chooseAction;
    private Button mExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }



        /*初始化主页按钮*/
        chooseAction = (Button) findViewById(R.id.chooseActionMain);
        chooseAction.setOnClickListener(this);
        mExit = (Button) findViewById(R.id.exitMain);
        mExit.setOnClickListener(this);

        /**
         * 获得传感器清单
         */
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        Log.d("TAG", "ccc Sensor == " + sensors.toString());

        /**
         * 用ListView显示
         */
        List<String> data = new ArrayList<>();
        for (Sensor sensor : sensors) {
            data.add(sensor.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                MainActivity.this, android.R.layout.simple_list_item_1, data);
        showSensor = (ListView) findViewById(R.id.showSensor_list);
        showSensor.setAdapter(adapter);
    }

    /**
     * 菜单功能
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_menu:
                AlertDialog.Builder main_Dialog = new AlertDialog.Builder(MainActivity.this);
                main_Dialog.setTitle(R.string.aboutTitle_mainMenu);
                main_Dialog.setMessage(R.string.aboutText_mainMenu);
                main_Dialog.setCancelable(true);
                main_Dialog.setNegativeButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                main_Dialog.show();
                break;
            case R.id.exit_menu:
                ActivityCollector.finishAll();
                break;
            default:
                break;


        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chooseActionMain:
                Intent intent = new Intent(MainActivity.this, ChooseActionActivity.class);
                startActivity(intent);
                break;
            case R.id.exitMain:
                ActivityCollector.finishAll();
                break;

            default:
                break;
        }
    }


}
