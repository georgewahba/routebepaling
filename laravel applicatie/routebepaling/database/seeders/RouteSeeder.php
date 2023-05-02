<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class RouteSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        DB::table('route')->insert([
            'user_id' => '1',
            'created_at' => now(),
            'updated_at' => now()
        ]);
        DB::table('route')->insert([
            'user_id' => '2',  
            'created_at' => now(),
            'updated_at' => now()
        ]);
        DB::table('route')->insert([
            'user_id' => '3',
            'created_at' => now(),
            'updated_at' => now()
        ]);
        DB::table('route')->insert([
            'user_id' => '4',
            'created_at' => now(),
            'updated_at' => now()
        ]);

        DB::table('route')->insert([
            'user_id' => '5',
            'created_at' => now(),
            'updated_at' => now()
        ]);

        DB::table('route')->insert([
            'user_id' => '6',
            'created_at' => now(),
            'updated_at' => now()
        ]);
    }
}
