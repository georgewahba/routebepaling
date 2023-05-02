<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Google\Client;
use Google\Service\Maps\Geocoding;
use Google\Service\Maps\Directions;


class NearestNeighborController extends Controller
{
    public function nearestNeighbor()
    {
        $client = new Client();
        $client->setApplicationName('Your Application Name');
        $client->setDeveloperKey('Your API Key');
        $addresses = [
            '123 Main St, Anytown USA',
            '456 Oak St, Anytown USA',
            '789 Maple St, Anytown USA'
        ];
        $locations = [];
        // Geocode all addresses and convert them to latitude/longitude coordinates
        $service = new Geocoding($client);
        foreach ($addresses as $address) {
            $response = $service->geocode($address);
            $location = $response->results[0]->geometry->location;
            $locations[] = [$location->lat, $location->lng];
        }
        // Sort locations by their driving distances from the first location
        $firstLocation = $locations[0];
        array_shift($locations);
        usort($locations, function ($a, $b) use ($firstLocation, $client) {
            $service = new Directions($client);
            $responseA = $service->route($firstLocation, $a, ['mode' => 'driving']);
            $responseB = $service->route($firstLocation, $b, ['mode' => 'driving']);
            return $responseA->routes[0]->legs[0]->distance->value - $responseB->routes[0]->legs[0]->distance->value;
        });
        $sortedLocations = [];
        foreach ($locations as $location) {
            $response = $service->reverseGeocode($location);
            $address = $response->results[0]->formatted_address;
            $sortedLocations[] = $address;
        }
        return view('nearest_neighbor', ['sortedLocations' => $sortedLocations]);
    }
    

}
