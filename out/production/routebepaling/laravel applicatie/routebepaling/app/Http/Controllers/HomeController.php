<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class HomeController extends Controller
{
    public function index()
    {
        $adressen = \App\Models\Adres::all();
        $users = \App\Models\Users::all();
        return view('plan', compact('adressen', 'users'));
    }
}
