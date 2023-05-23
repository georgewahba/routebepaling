<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class HomeController extends Controller
{
    public function index()
    {
        $adressen = \App\Models\Adres::where('is_completed', 0)
            ->orderBy('postcode')
            ->get();
        $users = \App\Models\User::all();
        return view('plan', compact('adressen', 'users'));
    }
}
