package dawanda.de.dawandasample.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dawanda.de.dawandasample.activity.CategoriesActivity;

/**
 * Define activities to be injected
 */
@Module
public abstract class DaWandaBuilderModule {
    @ContributesAndroidInjector
    abstract CategoriesActivity contributeActivityInjector();
}
