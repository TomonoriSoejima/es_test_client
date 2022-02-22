import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TrackHits;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

public class Main {


    public static void main(String[] args) throws Exception {


//        search_producct();
         search_hotel();



    }

    public static void search_hotel() throws Exception{

        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200)).build();

// Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

// And create the API client
        ElasticsearchClient client = new ElasticsearchClient(transport);




        SearchResponse<Hotel> search = client.search(s -> s
                        .index("hotel")

                        .query(q -> q
                                .term(t -> t
                                        .field("has_parking.keyword")
                                        .value(v -> v.stringValue("yes"))
                                )),
                Hotel.class);

        System.out.println(search.hits().total().value());
        for (Hit<Hotel> hit: search.hits().hits()) {

            System.out.println(hit.source());

        }

    }

    public static void search_producct() throws Exception{

        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200)).build();


        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());


        ElasticsearchClient client = new ElasticsearchClient(transport);




        SearchResponse<Product> search = client.search(s -> s
                        .index("products")

                        .query(q -> q
                                .term(t -> t
                                        .field("name.keyword")
                                        .value(v -> v.stringValue("jo"))
                                )),
                Product.class);


        System.out.println(search.hits().total().value());
        for (Hit<Product> hit: search.hits().hits()) {

            System.out.println(hit.toString());
            System.out.println(hit.innerHits());

        }


    }
}
