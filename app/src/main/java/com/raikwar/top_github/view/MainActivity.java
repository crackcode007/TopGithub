package com.raikwar.top_github.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.raikwar.top_github.R;
import com.raikwar.top_github.model.RepositoryInfo;
import com.raikwar.top_github.presenter.RecyclerInterface;
import com.raikwar.top_github.presenter.RepositoryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RepositoryAdapter repositoryAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        fetchJSON();

    }

    private void fetchJSON(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RecyclerInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RecyclerInterface api = retrofit.create(RecyclerInterface.class);

        Call<String> call = api.getString();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void writeRecycler(String response){

        try {
            //getting the whole json object from the response
            JSONArray jsonArray = new JSONArray(response);


                ArrayList<RepositoryInfo> modelRecyclerArrayList = new ArrayList<>();


                for (int i = 0; i < jsonArray.length(); i++) {

                    RepositoryInfo modelRecycler = new RepositoryInfo();
                    JSONObject dataobj = jsonArray.getJSONObject(i);

                    modelRecycler.setAvatar(dataobj.getString("avatar"));
                    modelRecycler.setUsername(dataobj.getString("username"));
                   // modelRecycler.setName(dataobj.getString("name"));
                    modelRecycler.setUrl(dataobj.getString("url"));
                    modelRecyclerArrayList.add(modelRecycler);

                }

                repositoryAdapter = new RepositoryAdapter(this,modelRecyclerArrayList);
                recyclerView.setAdapter(repositoryAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
