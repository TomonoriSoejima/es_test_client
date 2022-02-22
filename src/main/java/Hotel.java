import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
class Hotel {

    int star;
    int price;
    int capacity;
    String hotel_name;
    String group;
    String country;
    String city;
    String busy_month;
    String created;
    String has_parking;
    List<String> menu;
    List<String> cloesd;


    public Hotel () {}


}