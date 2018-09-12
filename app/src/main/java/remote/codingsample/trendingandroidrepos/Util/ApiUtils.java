package remote.codingsample.trendingandroidrepos.Util;

public class ApiUtils {

    public static final String BASE_URL="https://api.github.com/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }

}
