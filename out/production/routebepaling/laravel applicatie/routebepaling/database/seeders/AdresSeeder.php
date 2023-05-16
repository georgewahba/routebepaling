<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class AdresSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
    DB::table("adressen")->insert([
        "stad" => "Amsterdam",
        "postcode" => "1012AB",
        "straatnaam" => "Damstraat",
        "huisnummer" => "1",
        "route_id" => null,
        'created_at' => now(),
        'updated_at' => now()
    ]);

    DB::table("adressen")->insert([
        "stad" => "Rotterdam",
        "postcode" => "3011EA",
        "straatnaam" => "Coolsingel",
        "huisnummer" => "42",
        "route_id" => null,
        'created_at' => now(),
        'updated_at' => now()
    ]);

    DB::table("adressen")->insert([
        "stad" => "Utrecht",
        "postcode" => "3511ER",
        "straatnaam" => "Oudegracht",
        "huisnummer" => "123",
        "route_id" => null,
        'created_at' => now(),
        'updated_at' => now()
    ]);

    DB::table("adressen")->insert([
        "stad" => "Den Haag",
        "postcode" => "2511CC",
        "straatnaam" => "Binnenhof",
        "huisnummer" => "1",
        "route_id" => null,
        'created_at' => now(),
        'updated_at' => now()
    ]);

    DB::table("adressen")->insert([
        "stad" => "Groningen",
        "postcode" => "9712AA",
        "straatnaam" => "Grote Markt",
        "huisnummer" => "1",
        "route_id" => null,
        'created_at' => now(),
        'updated_at' => now()
    ]);

    DB::table("adressen")->insert([
        "stad" => "Eindhoven",
        "postcode" => "5611AE",
        "straatnaam" => "Markt",
        "huisnummer" => "21",
        "route_id" => null,
        'created_at' => now(),
        'updated_at' => now()
    ]);

    DB::table("adressen")->insert([
        "stad" => "Amsterdam",
        "postcode" => "1012AB",
        "straatnaam" => "Damstraat",
        "huisnummer" => "1",
        "route_id" => null,
        'created_at' => now(),
        'updated_at' => now()
    ]);

    DB::table("adressen")->insert([
        "stad" => "Maastricht",
        "postcode" => "6211CH",
        "straatnaam" => "Vrijthof",
        "huisnummer" => "1",
        "route_id" => null,
        'created_at' => now(),
        'updated_at' => now()
    ]);

    DB::table("adressen")->insert([
        "stad" => "Haarlem",
        "postcode" => "2011PL",
        "straatnaam" => "Grote Houtstraat",
        "huisnummer" => "1",
        "route_id" => null,
        'created_at' => now(),
        'updated_at' => now()
    ]);

    DB::table("adressen")->insert([
        "stad" => "Amsterdam",
        "postcode" => "1012AB",
        "straatnaam" => "Damstraat",
        "huisnummer" => "1",
        "route_id" => null,
        'created_at' => now(),
        'updated_at' => now()
    ]);

    DB::table("adressen")->insert([
        "stad" => "Leeuwarden",
        "postcode" => "8911DM",
        "straatnaam" => "Nieuwestad",
        "huisnummer" => "1",
        "route_id" => null,
        'created_at' => now(),
        'updated_at' => now()
    ]);

    DB::table("adressen")->insert([
        "stad" => "Arnhem",
        "postcode" => "6811BJ",
        "straatnaam" => "Korenmarkt",
        "huisnummer" => "1",
        "route_id" => null,
        'created_at' => now(),
        'updated_at' => now()
    ]);

    DB::table("adressen")->insert([
        "stad" => "Amsterdam",
        "postcode" => "1012AB",
        "straatnaam" => "Damstraat",
        "huisnummer" => "1",
        "route_id" => null,
        'created_at' => now(),
        'updated_at' => now()
    ]);

    DB::table("adressen")->insert([
        "stad" => "Rotterdam",
        "postcode" => "3011EA",
        "straatnaam" => "Coolsingel",
        "huisnummer" => "42",
        "route_id" => null,
        'created_at' => now(),
        'updated_at' => now()
    ]);

    DB::table("adressen")->insert([
        "stad" => "Utrecht",
        "postcode" => "3511ER",
        "straatnaam" => "Oudegracht",
        "huisnummer" => "123",
        "route_id" => null,
        'created_at' => now(),
        'updated_at' => now()
    ]);
    
    }
}
