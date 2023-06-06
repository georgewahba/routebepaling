<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Google\Client;
use Google\Service\Maps\Geocoding;
use Google\Service\Maps\Directions;


class NearestNeighborController extends Controller
{
    public function getCoordinates($address, $apiKey) {
        // Urlencode the address to safely include it in the URL
        $address = urlencode($address);
    
        // Generate the request URL
        $url = "https://maps.googleapis.com/maps/api/geocode/json?address={$address}&key={$apiKey}";
    
        // Fetch the data from the API
        $data = file_get_contents($url);
    
        // Decode the JSON response
        $data = json_decode($data, true);
        // If everything went well, the 'results' array will not be empty
        if (!empty($data['results'])) {
            // Extract the latitude and longitude
            $latitude = $data['results'][0]['geometry']['location']['lat'];
            $longitude = $data['results'][0]['geometry']['location']['lng'];
    
            // Return the coordinates as an array
            return [$latitude, $longitude];
        }
    
        // If anything went wrong, return false
        return false;
    }

    public function algorithm(){
    // Use the function
    $adressen = \App\Models\Adres::where('is_completed', 0)
    ->get();
    
    echo "<h1> adressen voor het algoritme</h1>";

    foreach($adressen as $adres){
        echo ($adres->postcode . " " . $adres->straatnaam . " " . $adres->huisnummer . " " . $adres->stad) . "<br>";

    }

    $apiKey = "AIzaSyCrAh6_Yx0STeJdcpdM_dlqvw6ELt5HuEQ";

    $lijst = array();
    foreach($adressen as $adres){
        $address = $adres->postcode . " " . $adres->straatnaam . " " . $adres->huisnummer . " " . $adres->stad;
        $coordinates = $this->getCoordinates($address, $apiKey);

        if ($coordinates) {
            array_push($lijst, ["adress"=>$address, "lat"=>$coordinates[0], "lng"=>$coordinates[1]]);
        } else {
            echo "There was an error retrieving the coordinates for {$address}.\n";
        }
    }
    $this->nearestNeighbour($lijst);

    return view('neighbor', compact('lijst'));


    }

    function nearestNeighbour($points) {
        $startPoint = array_shift($points);
        $route = array($startPoint);
        $currentPoint = $startPoint;
    
        while (!empty($points)) {
            $nearestDistance = null;
            $nearestPointIndex = null;
    
            foreach ($points as $index => $point) {
                $distance = $this->calculateDistance($currentPoint, $point);
    
                if ($nearestDistance === null || $distance < $nearestDistance) {
                    $nearestDistance = $distance;
                    $nearestPointIndex = $index;
                }
            }
    
            $currentPoint = $points[$nearestPointIndex];
            unset($points[$nearestPointIndex]);
            array_push($route, $currentPoint);
        }
        
        echo "<h1> adressen na het algoritme</h1>";
        foreach($route as $adres){
            echo ($adres["adress"]) . "<br>";
        }

        return $route;
    }

    function calculateDistance($point1, $point2) {
        // Assuming that each point is an associative array with 'lat' and 'lng' values
        $lat1 = $point1['lat'];
        $lng1 = $point1['lng'];
        $lat2 = $point2['lat'];
        $lng2 = $point2['lng'];
      
        // Calculate Euclidean distance (not accurate for geographical coordinates, but for simplicity's sake)
        return sqrt(pow($lat1 - $lat2, 2) + pow($lng1 - $lng2, 2));
    }
}
    

    
    
    

