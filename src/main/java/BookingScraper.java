import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BookingScraper {
    private List<HotelsNames> hotelsNamesList;
    public BookingScraper(){
        try {
            String url = "https://www.booking.com/searchresults.es.html?label=gen173nr-1FCAEoggI46AdIM1gEaEaIAQGYAQq4ARfIAQzYAQHoAQH4AQuIAgGoAgO4Apio8J0GwAIB0gIkM2RjODgwMTUtNWZjYy00NTlmLWIzMGQtNzQ5MmZkZTE2NWQw2AIG4AIB&sid=1c54fe47c864ff28f788bc14a7b9f120&sb=1&sb_lp=1&src=region&src_elem=sb&error_url=https%3A%2F%2Fwww.booking.com%2Fregion%2Fes%2Fgran-canaria.es.html%3Flabel%3Dgen173nr-1FCAEoggI46AdIM1gEaEaIAQGYAQq4ARfIAQzYAQHoAQH4AQuIAgGoAgO4Apio8J0GwAIB0gIkM2RjODgwMTUtNWZjYy00NTlmLWIzMGQtNzQ5MmZkZTE2NWQw2AIG4AIB%26sid%3D1c54fe47c864ff28f788bc14a7b9f120%26&ss=Gran+Canaria&is_ski_area=0&ssne=Gran+Canaria&ssne_untouched=Gran+Canaria&region=754&checkin_year=&checkin_month=&checkout_year=&checkout_month=&group_adults=2&group_children=0&no_rooms=1&b_h4u_keep_filters=&from_sf=1";
            Document doc = Jsoup.connect(url).userAgent("Chrome/5.0").get();
            Elements hotel = doc.getElementsByClass("a1b3f50dcd f7c6687c3d a1f3ecff04 f996d8c258");
            hotelsNamesList = new ArrayList<>();
            for (Element values : hotel) {
                String hotelName = values.select("div.fcab3ed991.a23c043802").text();
                String hotelLocation = values.select("span.f4bd0794db.b4273d69aa").text();
                String hotelDescription = values.select("div.d8eab2cf7f").text();
                String hotelRating = values.select("div.b5cd09854e.d10a6220b4").text();
                String hotelUrl = values.select("a.e13098a59f").attr("href");
                Document hotelInfo = Jsoup.connect(hotelUrl).userAgent("Chrome/5.0").get();
                String hotelServices = hotelInfo.select("div.a432050e3a").text();
                String hotelComments = hotelInfo.select("div.c9545e7647").text();
                hotelsNamesList.add(new HotelsNames(hotelName,hotelDescription, hotelLocation, hotelRating,
                        hotelServices, hotelComments));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public List<HotelsNames> AllHotels() {
        return hotelsNamesList;
    }
    public HotelsNames Buscar(String name){
        for (HotelsNames h : hotelsNamesList){
            if (h.getHotelName().equals(name)){
                return h;
            }
        }
        return null;
    }
}


