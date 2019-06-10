
package com.leandro.retrofitsimplemodel;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.leandro.retrofitsimplemodel.adapter.CustomAdapter;
import com.leandro.retrofitsimplemodel.model.RetroPhoto;
import com.leandro.retrofitsimplemodel.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Main Activity");


        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroPhoto>> call = service.getAllPhotos();
        ((Call) call).enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call <List<RetroPhoto>> call, Response <List<RetroPhoto>>response) {
                generateDatalist(response.body());

            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Toast.makeText(MainActivity.this, "Oooops....Please try later!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void generateDatalist(List<RetroPhoto> photoList){
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this, photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
