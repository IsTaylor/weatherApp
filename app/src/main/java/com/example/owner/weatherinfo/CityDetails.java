package com.example.owner.weatherinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.owner.weatherinfo.data.WeatherResult;
import com.example.owner.weatherinfo.network.CityAPI;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityDetails extends AppCompatActivity {

    public static final String METRIC = "metric";
    public static final String APPID = "69b787ad2e0f0df53100a900d587acf8";
    public static final String KEY_CITY = "KEY_CITY";
    private String iconLink = "http://openweathermap.org/img/w/";

    @BindView(R.id.ivIcon)
    ImageView ivIcon;
    @BindView(R.id.tvCurrentTemp)
    TextView tvCurrentTemp;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.tvCityName)
    TextView tvCityName;
    @BindView(R.id.tvHumidity)
    TextView tvHumidity;
    @BindView(R.id.tvMaxtemp)
    TextView tvMaxtemp;
    @BindView(R.id.tvMintemp)
    TextView tvMintemp;
    @BindView(R.id.tvSunrise)
    TextView tvSunrise;
    @BindView(R.id.tvSunset)
    TextView tvSunset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        ButterKnife.bind(this);

        String cityName = null;

        Intent my_intent = this.getIntent();
        Bundle extras = this.getIntent().getExtras();

        if(my_intent != null) {
            if (extras != null) {
                cityName = extras.get(KEY_CITY).toString();
                tvCityName.setText(extras.get(KEY_CITY).toString());
            }
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final CityAPI cityAPI = retrofit.create(CityAPI.class);

        Call<WeatherResult> call
                = cityAPI.getCityWeather(cityName, METRIC, APPID);

        call.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                if (response.body() != null) {
                    tvCurrentTemp.append("" + Double.toString(response.body().getMain().getTemp()));
                    tvDescription.append("" + response.body().getWeather().get(0).getDescription());
                    tvHumidity.append("" + Double.toString(response.body().getMain().getHumidity()));
                    tvMaxtemp.append("" + Double.toString(response.body().getMain().getTempMax()));
                    tvMintemp.append("" + Double.toString(response.body().getMain().getTempMin()));
                    tvSunrise.append("" + Double.toString(response.body().getSys().sunrise));
                    tvSunset.append("" + Double.toString(response.body().getSys().sunset));
                    iconLink += response.body().getWeather().get(0).getIcon() + getString(R.string.png) ;
                    Glide.with(CityDetails.this).load(iconLink).into(ivIcon);
                } else {
                    tvCurrentTemp.setText("This city has no data!");
                    tvDescription.setText("");
                    tvHumidity.setText("");
                    tvMaxtemp.setText("");
                    tvMintemp.setText("");
                    tvSunrise.setText("");
                    tvSunset.setText("");
                }
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {
                tvCurrentTemp.setText(t.getMessage());
            }
        });





    }
}
