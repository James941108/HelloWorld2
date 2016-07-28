package com.example.james.helloworld2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.helloworld2.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvResult;
    EditText editText1, editText2;
    Button btnCalculate;
    RadioButton rbPlus;
    RadioButton rbMinus;
    RadioButton rbMultiply;
    RadioButton rbDevide;
    RadioGroup rgOperator;

    CustomViewGroup customViewGroup1;
    CustomViewGroup customViewGroup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        setContentView(R.layout.activity_main);

        intInstances();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Toast.makeText(MainActivity.this, "WIDTH : " + width + ", HEIGHT : " + height,
                Toast.LENGTH_SHORT).show();
    }

    private void intInstances() {
        tvResult = (TextView) findViewById(R.id.textViewResults);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(this);

        rbPlus = (RadioButton) findViewById(R.id.rbPlus);
        rbMinus = (RadioButton) findViewById(R.id.rbMinus);
        rbMultiply = (RadioButton) findViewById(R.id.rbMultiply);
        rbDevide = (RadioButton) findViewById(R.id.rbDevide);
        rgOperator = (RadioGroup) findViewById(R.id.rgOperator);

        customViewGroup1 = (CustomViewGroup) findViewById(R.id.customViewGroup1);
        customViewGroup2 = (CustomViewGroup) findViewById(R.id.customViewGroup2);

        customViewGroup1.setButtonText("HELLO...");
        customViewGroup1.setButtonText("WORLD!!!");
    }

    @Override
    public void onClick(View v) {
        int val1 = 0;
        int val2 = 0;
        int result = 0;
        try {
            val1 = Integer.parseInt(editText1.getText().toString());
        } catch (NumberFormatException e){

        }
        try {
            val2 = Integer.parseInt(editText2.getText().toString());
        } catch (NumberFormatException e){

        }

        if (v == btnCalculate) {
            switch (rgOperator.getCheckedRadioButtonId()) {
                case R.id.rbPlus:
                    result = val1 + val2;
                    break;
                case R.id.rbMinus:
                    result = val1 - val2;
                    break;
                case R.id.rbMultiply:
                    result = val1 * val2;
                    break;
                case R.id.rbDevide:
                    result = val1 / val2;
                    break;
            }

            tvResult.setText(result + "");

            Log.d("Calculations", "Results = " + result);
            Toast.makeText(MainActivity.this, "Results = " + result, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this,
                    SecondActivity.class);
            intent.putExtra("result", result);

            Coordinate c1 = new Coordinate();
            c1.x = 5;
            c1.y = 10;
            c1.z = 20;
            Bundle bundle = new Bundle();
            bundle.putInt("x", c1.x);
            bundle.putInt("y", c1.y);
            bundle.putInt("z", c1.z);
            intent.putExtra("cBundle", bundle);

            CoordinateSerializable c2 = new CoordinateSerializable();
            c2.x = 5;
            c2.y = 10;
            c2.z = 20;
            intent.putExtra("cSerializable", c2);

            CoordinateParcelable c3 = new CoordinateParcelable();
            c3.x = 5;
            c3.y = 10;
            c3.z = 20;
            intent.putExtra("cParcelable", c3);

            startActivityForResult(intent, 12345);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12345){
            String result = data.getStringExtra("result");
            if (resultCode == RESULT_OK){
                //Get data from data
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings){
            Toast.makeText(MainActivity.this, "Yooo!", Toast.LENGTH_SHORT).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save things here
//        outState.putString("text", tvResult.getText().toString());
    }



    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore things here
//        tvResult.setText(savedInstanceState.getString("text"));
    }
}
