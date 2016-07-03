package com.ganjar.androidview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etJumlahItem, etRadiusLingkaran;
    private Button btnGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set layout
        setContentView(R.layout.activity_main);

        // initialize view
        etJumlahItem = (EditText) findViewById(R.id.activity_main_et_jumlah_item);
        etRadiusLingkaran = (EditText) findViewById(R.id.activity_main_et_radius_lingkaran);
        btnGambar = (Button) findViewById(R.id.activity_main_btn_gambar);

        // set on click listener buat button gambar
        btnGambar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_main_btn_gambar:
                prosesGambar();
                break;
        }
    }

    private void prosesGambar(){

        // validate form
        String radiusString = etRadiusLingkaran.getText().toString();
        String jumlahItemString = etJumlahItem.getText().toString();

        if (radiusString.isEmpty()){
            etRadiusLingkaran.setError(getString(R.string.error_field_kosong));
            return;
        } else if (jumlahItemString.isEmpty()){
            etJumlahItem.setError(getString(R.string.error_field_kosong));
            return;
        }

        int radius, jumlahItem;

        try {
            radius = Integer.parseInt(radiusString);
            if (radius <=0 ){
                etRadiusLingkaran.setError(getString(R.string.error_radius_invalid));
                return;
            }
        } catch (NumberFormatException e){
            etRadiusLingkaran.setError(getString(R.string.error_input_bukan_nomor));
            return;
        }

        try {
            jumlahItem = Integer.parseInt(jumlahItemString);
        } catch (NumberFormatException e){
            etJumlahItem.setError(getString(R.string.error_input_bukan_nomor));
            return;
        }

        // everything is fine, go to draw screen
        Intent intent = new Intent(MainActivity.this, DrawActivity.class);
        intent.putExtra(DrawActivity.KEY_RADIUS, radius);
        intent.putExtra(DrawActivity.KEY_ITEM, jumlahItem);
        startActivity(intent);
    }
}
