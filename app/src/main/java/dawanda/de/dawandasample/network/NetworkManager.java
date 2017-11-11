package dawanda.de.dawandasample.network;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dawanda.de.dawandasample.model.Category;
import dawanda.de.dawandasample.model.Product;
import io.reactivex.Observable;

@Singleton
public class NetworkManager {
    private DaWandaService mDaWandaService;

    @Inject
    public NetworkManager(DaWandaService daWandaService) {
        this.mDaWandaService = daWandaService;
    }

    public Observable<List<Category>> getCategories() {
        return mDaWandaService.getCategories();
    }

    public Observable<List<Product>> getProducts(long categoryId) {
        return mDaWandaService.getProducts(categoryId);
    }
}
