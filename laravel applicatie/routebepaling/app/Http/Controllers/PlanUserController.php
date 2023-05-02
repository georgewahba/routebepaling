<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Termwind\Components\Dd;

class PlanUserController extends Controller
{
    public function index(Request $request)
    {
        $adres = \App\Models\Adres::find($request->adres_id);
        $user = $request->user_id;
        $adres->route_id = $user;
        $adres->save();
        return redirect('/');

    }
}
