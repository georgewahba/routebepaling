<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/', [\App\Http\Controllers\HomeController::class, 'index']);
Route::post('/planuser', [\App\Http\Controllers\PlanUserController::class, 'index']);
Route::get("/neighbor", [\App\Http\Controllers\NearestNeighborController::class, 'algorithm']);
Route::get("/run", [\App\Http\Controllers\NearestNeighborController::class, 'algorithm']);