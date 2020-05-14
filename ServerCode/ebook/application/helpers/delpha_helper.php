<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

if ( ! function_exists('frontendcheck'))
{
    function frontendcheck()
    {	
		$CI =& get_instance();
		$user = $CI->session->userdata('4k_id');
		
        if (!isset($user)) { 
            redirect(base_url().'index.php/admin');             
        }else {
            return TRUE;    
            // redirect(base_url().'property');
        }       		
    }   
} ?>