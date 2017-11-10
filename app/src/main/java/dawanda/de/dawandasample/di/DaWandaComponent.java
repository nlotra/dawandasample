package dawanda.de.dawandasample.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dawanda.de.dawandasample.DaWandaApplication;

/**
 * Created by natashalotra on 2017/11/06.
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, DaWandaBuilderModule.class, DaWandaModule.class})
public interface DaWandaComponent extends AndroidInjector<DaWandaApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        DaWandaComponent build();
    }

    void inject(DaWandaApplication application);
}
