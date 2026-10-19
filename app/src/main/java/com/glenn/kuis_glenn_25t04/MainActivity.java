package com.glenn.kuis_glenn_25t04;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText etNim, etNama, etTempat, etTanggal, etAlamat;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("mahasiswa", MODE_PRIVATE);

        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etTempat = findViewById(R.id.etTempat);
        etTanggal = findViewById(R.id.etTanggal);
        etAlamat = findViewById(R.id.etAlamat);

        Button btnSimpan = findViewById(R.id.btnSimpan);
        Button btnTampil = findViewById(R.id.btnTampil);

        etNim.setText(sp.getString("nim", ""));
        etNama.setText(sp.getString("nama", ""));
        etTempat.setText(sp.getString("tempat", ""));
        etTanggal.setText(sp.getString("tanggal", ""));
        etAlamat.setText(sp.getString("alamat", ""));

        btnSimpan.setOnClickListener(v -> {
            String nim = etNim.getText().toString().trim();
            String nama = etNama.getText().toString().trim();
            String tempat = etTempat.getText().toString().trim();
            String tanggal = etTanggal.getText().toString().trim();
            String alamat = etAlamat.getText().toString().trim();

            if (nim.isEmpty() || nama.isEmpty()
                    || tempat.isEmpty() || tanggal.isEmpty()
                    || alamat.isEmpty()) {
                Toast.makeText(this, "Semua data harus diisi",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            sp.edit()
                    .putString("nim", nim)
                    .putString("nama", nama)
                    .putString("tempat", tempat)
                    .putString("tanggal", tanggal)
                    .putString("alamat", alamat)
                    .apply();

            Toast.makeText(this, "Data berhasil disimpan",
                    Toast.LENGTH_SHORT).show();
        });

        btnTampil.setOnClickListener(v -> {
            String nim = sp.getString("nim", "");

            if (nim.isEmpty()) {
                Toast.makeText(this, "Belum ada data",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            String data = "NIM: " + nim
                    + "\nNama Lengkap: " + sp.getString("nama", "")
                    + "\nTempat Lahir: " + sp.getString("tempat", "")
                    + "\nTanggal Lahir: " + sp.getString("tanggal", "")
                    + "\nAlamat: " + sp.getString("alamat", "");

            new AlertDialog.Builder(this)
                    .setTitle("Data Mahasiswa")
                    .setMessage(data)
                    .setPositiveButton("OK", null)
                    .show();
        });
    }
}
