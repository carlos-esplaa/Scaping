import com.google.gson.Gson;
import java.util.List;
import static spark.Spark.get;

public class Webservices {
    public void start(){
        BookingScraper bookingScraper = new BookingScraper();
        Gson gson = new Gson();
        get("/hotels", ((request, response) -> {
            response.status(200);
            List result = bookingScraper.AllHotels();
            return result;
        }), gson::toJson);

        get("/hotels/:name", (request, response)
                        -> {
            HotelsNames hotelsNames = bookingScraper.Buscar(request.params(":name"));
            if (hotelsNames != null){
                response.status(200);
                return hotelsNames;
            } else {
                response.status(404);
                return ("Usuario no encontrado");
            }
        }, gson::toJson);
        get("/hotels/:name/services", (request, response)
                    -> {
            HotelsNames hotelsNames = bookingScraper.Buscar(request.params(":name"));
            if (hotelsNames != null){
                response.status(200);
                return hotelsNames;
            } else {
                response.status(404);
                return ("Usuario no encontrado");
            }
        }, gson::toJson);
        get("/hotels/:name/comments", (request, response)
                    -> {
            HotelsNames hotelsNames = bookingScraper.Buscar(request.params(":name"));
            if (hotelsNames != null){
                response.status(200);
                return hotelsNames;
            } else {
                response.status(404);
                return ("Usuario no encontrado");
            }
        }, gson::toJson);
        get("/hotels/:name/location", (request, response)
                    -> {
            HotelsNames hotelsNames = bookingScraper.Buscar(request.params(":name"));
            if (hotelsNames != null){
                response.status(200);
                return hotelsNames;
            } else {
                response.status(404);
                return ("Usuario no encontrado");
            }
        }, gson::toJson);
        get("/hotels/:name/rating", (request, response)
                    -> {
            HotelsNames hotelsNames = bookingScraper.Buscar(request.params(":name"));
            if (hotelsNames != null){
                response.status(200);
                return hotelsNames;
            } else {
                response.status(404);
                return ("Usuario no encontrado");
            }
        }, gson::toJson);
    }
}
