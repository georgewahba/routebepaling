<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('completed_parcels', function (Blueprint $table) {
            $table->id();
            $table->string('stad');
            $table->string('postcode');
            $table->string('straatnaam');
            $table->string('huisnummer');
            $table->string('route_id')->nullable()->default(null);
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('completed_parcels');
    }
};
