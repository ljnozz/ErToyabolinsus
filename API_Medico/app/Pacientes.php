<?php

namespace API_Medico;

use Illuminate\Database\Eloquent\Model;

class Pacientes extends Model
{
	public $timestamps = false;
	
    protected $table="pacientes";
	
	protected $fillable = 	[
								'cedula', 	
								'nombre', 	
								'apellido', 		
								'direccion', 	
								'correo',				
								'tlfncasa',		
								'tlfncelular',		
							];
}
