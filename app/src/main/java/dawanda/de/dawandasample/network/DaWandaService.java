package dawanda.de.dawandasample.network;

import java.util.List;

import dawanda.de.dawandasample.model.Category;
import dawanda.de.dawandasample.model.Product;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Retrofit interface
 */

public interface DaWandaService {
    @GET("categories.json")
    Observable<List<Category>> getCategories();

    @GET("categories/{category_id}.json")
    Observable<List<Product>> getProducts(
            @Path("category_id") long categoryId
    );
}
