<?php defined('BASEPATH') OR exit('No direct script access allowed');

class Adminmodel extends CI_Model{

	public function __construct() {
		parent::__construct();
		$this->load->database();
	}

	public function admin_varify($where){
		$this->db->select("*");
		$this->db->from("e_admin");		  
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	/*=====================Category========================*/

	public function category_list(){
		$this->db->select("*");
		$this->db->from("e_category");	
		$q = $this->db->get();
		return $q->result();
	}

	public function add_category($data){
		$query = $this->db->insert('e_category',$data);
// 		return $this->db->insert_id();	
	}

	public function delete_category($id){
// 		$this->db->delete('e_category', array('cat_id' => $id)); 
	}

	public function get_category($where){
		$this->db->select("*");
		$this->db->from("e_category");
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function update_status_category($id,$status){
		$this->db->where('cat_id', $id);
// 		return $this->db->update('e_category', $status);
	}

	/*=====================End Category========================*/

	/*=====================Users===============================*/

	public function add_user($data){
		$query = $this->db->insert('e_user',$data);
// 		return $this->db->insert_id();	
	}

	public function userlist(){
		$this->db->select("*");
		$this->db->from("e_user");	
		$this->db->order_by("c_date", "desc");
		$q = $this->db->get();
		return $q->result();
	}

	public function userlist_by_where($where){
		$this->db->select("*");
		$this->db->from("e_user");	
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function user_status_change($where,$data){
		$this->db->where('id', $where);
// 		$this->db->update('e_user', $data);
	}

	public function update_user($data,$where){
		$this->db->where('id', $where);
// 		return $this->db->update('e_admin', $data);
	}

	/*==================End Users============================*/

	/*=====================Author===============================*/

	public function authorall(){
		$this->db->select("*");
		$this->db->from("e_author");	
		$q = $this->db->get();
		return $q->result();
	}

	public function add_author($data){
// 		$query = $this->db->insert('e_author',$data);
// 		return $this->db->insert_id();	
	}

	public function author_by_id($where){
		$this->db->select("*");
		$this->db->from("e_author");
		$this->db->where($where);	
		$q = $this->db->get();
		return $q->result();
	}

	public function update_author($id,$status){
		$this->db->where('a_id', $id);
// 		return $this->db->update('e_author', $status);
	}

	public function delete_author($id){
// 		$this->db->delete('e_author', array('a_id' => $id)); 
	}


	/*====================End Author============================*/

	/*======================Books================================*/

	public function booksall(){
		$this->db->select("*,b_id");
		$this->db->from("e_book");	
		$this->db->join('e_category', 'e_category.cat_id = e_book.fcat_id');
		$this->db->join('e_author', 'e_author.a_id = e_book.fa_id');
		$q = $this->db->get();
		return $q->result();
	}

	public function books_by_id($where){
		$this->db->select("*");
		$this->db->from("e_book");	
		$this->db->join('e_category', 'e_category.cat_id = e_book.fcat_id');
		$this->db->join('e_author', 'e_author.a_id = e_book.fa_id');
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function add_book($data){
// 		$query = $this->db->insert('e_book',$data);
// 		return $this->db->insert_id();	
	}


	public function update_book($data,$where){
// 		$this->db->where('b_id', $where);
// 		return $this->db->update('e_book', $data);
	}

	public function books_download(){
		$this->db->select("SUM(download) AS downcount");
		$this->db->from("e_book");	
		$q = $this->db->get();
		return $q->result();
	}

	public function books_view(){
		$this->db->select("SUM(readcnt) AS readcount");
		$this->db->from("e_book");	
		$q = $this->db->get();
		return $q->result();
	}

	public function books_transacation(){
		$this->db->select("SUM(t_amount) AS earncount");
		$this->db->from("e_transacation");	
		$q = $this->db->get();
		return $q->result();
	}

	public function delete_book($id){
// 		$this->db->delete('e_book', array('b_id' => $id)); 
	}

	/*======================End Books================================*/

	public function settings_data(){
		$this->db->select("*");
		$this->db->from("e_general_setting");	
		$q = $this->db->get();
		return $q->result();
	}

	public function update_general_setting($data,$where){
// 		$this->db->where('key', $where);
		// $this->db->update('e_general_setting', $data);
		return true;
	}
	
}