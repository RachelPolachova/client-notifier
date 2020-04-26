package com.market.client.notifier.notifier.controller;

import com.market.client.notifier.notifier.data.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/client", produces = {"application/json"})
public class ClientController {

    RestTemplate restTemplate = new RestTemplate();
    List<Client> potentialClientList = new ArrayList<Client>() {
        {
            add(new Client(new Location2DCoordinates(49.18129348754883, 16.638113021850586), "Ladislav Mňačko", "+4219123456789")); //Charbulova 104
            add(new Client(new Location2DCoordinates(49.208918, 16.555369), "Milan Kundera", "+4219123456789")); //lekarna Atlas, Jundrov
            add(new Client(new Location2DCoordinates(49.206474, 16.556535), "Jean-Paul Sartre", "+4219123456789")); //penzion Vanessa, Jundrov
            add(new Client(new Location2DCoordinates(49.813677, 18.266428), "Chuck Palahniuk", "+4219123456789")); //club Guliver, Ostrava
            add(new Client(new Location2DCoordinates(49.210200, 16.617151), "Anthony Burgess", "+4219123456789")); //nedaleko Zemedelska 1
        }
    };

    final double DISTANCE = 0.5;

    @PostMapping(path = "/potential")
    public @ResponseBody
    String checkPotentialClientNearLocation(@RequestBody NearClientRequest nearClientRequest) {
        System.out.println("received location");


        potentialClientList.forEach(client -> {
            Location2DCoordinates clientsLocation = client.getLocation();
            double distanceFromClient = clientsLocation.distance(nearClientRequest.getLocation());
            if (distanceFromClient < DISTANCE) {
                System.out.println("my loc: " + nearClientRequest.getLocation().getLatitude() + ", " + nearClientRequest.getLocation().getLongitude() + " their loc: " + clientsLocation.getLatitude() + ", " + clientsLocation.getLongitude());
                System.out.println("NEAR CLIENT! " + client.getName() + " distance: " + distanceFromClient);
                sendClientDetails(client, nearClientRequest.getToken());
            }
        });

        return "coolio";
    }

    private void sendClientDetails(Client client, String token) {
        String url = "https://fcm.googleapis.com/fcm/send";
        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header

        String firebaseApiKey = System.getenv().get("FIREBASE_API");
        headers.add("authorization", "key=" + firebaseApiKey);
        Map<String, Object> map = new HashMap<>();
        map.put("data", new PushNotificationData("Potential client near you!",
                "There's a potential client near you. Contact them!",
                "near-customer", client));
        map.put("to", token);

        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(map, headers);

        // send POST request

        ResponseEntity<Post> response = this.restTemplate.postForEntity(url, entity, Post.class);

        // check response status code
        if (response.getStatusCode() == HttpStatus.CREATED) {
            response.getBody();
        } else {
        }
    }
}
