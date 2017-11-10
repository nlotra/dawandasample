package dawanda.de.dawandasample.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dawanda.de.dawandasample.activity.CategoriesActivity;
import dawanda.de.dawandasample.activity.ProductsActivity;

/**
 * Define activities to be injected
 */
@Module
public abstract class DaWandaBuilderModule {
    @ContributesAndroidInjector
    abstract CategoriesActivity contributeCategoriesActivityInjector();

    @ContributesAndroidInjector
    abstract ProductsActivity contributeProductsActivityInjector();
}
