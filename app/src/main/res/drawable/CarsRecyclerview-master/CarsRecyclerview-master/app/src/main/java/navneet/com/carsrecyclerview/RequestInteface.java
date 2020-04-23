package navneet.com.carsrecyclerview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Navneet Krishna on 11/05/19.
 */
interface RequestInteface {
    @GET("cars_list.json")
    Call<List<CarsModel>> getCarsJson();
}
