package com.example.blog32;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.postList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();
    }


        private void getData ()
        {
            Call<PosList> posList = BloggerAPI.getService().getPosList();
            posList.enqueue(new Callback<PosList>() {
                @Override
                public void onResponse(Call<PosList> call, Response<PosList> response) {
                    PosList list = response.body();
                    recyclerView.setAdapter(new PostAdapter(MainActivity.this, list.getItems()));

                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<PosList> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                }

            });

        }

    }

