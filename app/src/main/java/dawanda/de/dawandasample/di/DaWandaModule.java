package dawanda.de.dawandasample.di;

import dagger.Module;
import dagger.Provides;
import dawanda.de.dawandasample.Constants;
import dawanda.de.dawandasample.network.DaWandaService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Application-wide injection
 */

@Module
public class DaWandaModule {
    @Provides
    public DaWandaService provideDaWandaService() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        Retrofit retrofit = builder.client(httpClient.build())
                .build();

        return retrofit.create(DaWandaService.class);
    }
}
