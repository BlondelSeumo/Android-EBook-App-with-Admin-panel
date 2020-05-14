<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Login extends CI_Controller {

	public function __construct() {
		parent::__construct();
		$CI =& get_instance();
		$CI->load->library('session');
		$this->load->helper('url');
		$this->load->model('Adminmodel');
	}

	public function index(){

		if($this->session->userdata('id')) {
			$data['settinglist'] = $this->Adminmodel->settings_data();
			redirect(base_url().'index.php/admin/dashboard',$data);
		}else{
			$data['settinglist'] = $this->Adminmodel->settings_data();
			$this->load->view("admin/login",$data);
		} 
	} 

	public function adminlogin(){
		$email= $this->input->post('email');
		$password= $this->input->post('password');
		$where='password="'.$password.'" and email="'.$email.'"';
		$result = $this->Adminmodel->admin_varify($where);

		if(count($result) > 0) {
			$this->session->set_userdata('email',$result[0]->email);
			$this->session->set_userdata('id',$result[0]->id);	
			$this->session->set_userdata('name',$result[0]->fullname);			
			echo '100';
		} else {
			echo '200'; 
		}

	}

	public function login_admin() {
		$email= $this->input->post('email');
		$password= $this->input->post('password');
		$where='password="'.$password.'" and email="'.$email.'"';
		$result = $this->Adminmodel->admin_varify($where);
		
		if(count($result) > 0) {
			$this->session->set_userdata('email',$result[0]->email);
			$this->session->set_userdata('id',$result[0]->id);	
			$this->session->set_userdata('name',$result[0]->fullname);			
			echo '100';
		} else {
			echo '200'; 
		}
	}
}
