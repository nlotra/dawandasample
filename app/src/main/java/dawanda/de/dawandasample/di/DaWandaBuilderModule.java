package dawanda.de.dawandasample.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dawanda.de.dawandasample.activity.CategoriesActivity;
import dawanda.de.dawandasample.activity.ProductDetailActivity;
import dawanda.de.dawandasample.activity.ProductsActivity;
import dawanda.de.dawandasample.activity.ViewImageActivity;

/**
 * Define activities to be injected
 */
@Module
public abstract class DaWandaBuilderModule {
    @ContributesAndroidInjector
    abstract CategoriesActivity contributeCategoriesActivityInjector();

    @ContributesAndroidInjector
    abstract ProductsActivity contributeProductsActivityInjector();

    @ContributesAndroidInjector
    abstract ProductDetailActivity contributeProductDetailActivityInjector();

    @ContributesAndroidInjector
    abstract ViewImageActivity contributeViewImageActivityInjector();
}
