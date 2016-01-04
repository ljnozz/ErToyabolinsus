<?php

namespace API_Medico;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Citas extends Model
{
    use SoftDeletes;

    public $timestamps = false;

    protected $table="citas";
	
	protected $fillable = 	[
								'fecha', 	
								'hora', 	
								'paciente', 		
								'medicos', 	
								'tratamiento',				
								'diagnostico',	
								'motivo',			
							];

	
    protected $dates = ['deleted_at'];	
}
