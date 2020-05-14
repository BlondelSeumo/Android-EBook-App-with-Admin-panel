<?php defined('BASEPATH') OR exit('No direct script access allowed');

class apimodel extends CI_Model{

	public function __construct() {
		$this->load->database();
	}

	public function login_user($where){
		$this->db->select("*");
		$this->db->from("e_user");		  
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function registration_api($data){
		$query = $this->db->insert('e_user',$data);
		return $this->db->insert_id();
	}

	public function profile($where){
		$this->db->select("*");
		$this->db->from("e_user");		  
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function category(){
		$this->db->select("*");
		$this->db->from("e_category");		  
		$q = $this->db->get();
		return $q->result();
	}

	public function booklist_by_category($where){
		$this->db->select("*");
		$this->db->from("e_book");		  
		$this->db->join('e_category', 'e_category.cat_id = e_book.fcat_id');
		$this->db->join('e_author', 'e_author.a_id = e_book.fa_id');
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function author(){
		$this->db->select("*");
		$this->db->from("e_author");		  
		$q = $this->db->get();
		return $q->result();
	}

	public function booklist_by_author($where){
		$this->db->select("*");
		$this->db->from("e_book");		  
		$this->db->join('e_category', 'e_category.cat_id = e_book.fcat_id');
		$this->db->join('e_author', 'e_author.a_id = e_book.fa_id');
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function readcnt_by_author($where){
		$this->db->select("sum(readcnt) as readcount, sum(download) as download");
		$this->db->from("e_book");		  
		$this->db->join('e_category', 'e_category.cat_id = e_book.fcat_id');
		$this->db->join('e_author', 'e_author.a_id = e_book.fa_id');
		$this->db->group_by('a_id'); 
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function booklist(){
		$this->db->select("*");
		$this->db->from("e_book");		  
		$this->db->join('e_category', 'e_category.cat_id = e_book.fcat_id');
		$this->db->join('e_author', 'e_author.a_id = e_book.fa_id');
		$q = $this->db->get();
		return $q->result();
	}

	public function bookdetails($where){
		$this->db->select("*");
		$this->db->from("e_book");		  
		$this->db->join('e_category', 'e_category.cat_id = e_book.fcat_id');
		$this->db->join('e_author', 'e_author.a_id = e_book.fa_id');
		    // $this->db->join('e_transacation', 'e_transacation.t_fb_id = e_book.b_id');
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function popularbooklist(){
		$this->db->select("*");
		$this->db->from("e_book");		  
		$this->db->join('e_category', 'e_category.cat_id = e_book.fcat_id');
		$this->db->join('e_author', 'e_author.a_id = e_book.fa_id');
		$this->db->order_by("readcnt","DESC");
		$q = $this->db->get();
		return $q->result();
	}

	public function booklist_free_paid($where){
		$this->db->select("*");
		$this->db->from("e_book");		  
		$this->db->join('e_category', 'e_category.cat_id = e_book.fcat_id');
		$this->db->join('e_author', 'e_author.a_id = e_book.fa_id');
		$this->db->where($where);
		$this->db->order_by("readcnt","DESC");
		$q = $this->db->get();
		return $q->result();
	}

	public function allbook(){
		$this->db->select("*");
		$this->db->from("e_book");		  
		$this->db->join('e_category', 'e_category.cat_id = e_book.fcat_id');
		$this->db->join('e_author', 'e_author.a_id = e_book.fa_id');
		    // $this->db->join('e_transacation', 'e_transacation.t_fb_id = e_book.b_id');
		     // $this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function transacation($where){
		$this->db->select("*");
		$this->db->from("e_transacation");		  
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}
	public function related_item($where){
		$this->db->select("*");
		$this->db->from("e_book");		  
		$this->db->join('e_category', 'e_category.cat_id = e_book.fcat_id');
		$this->db->join('e_author', 'e_author.a_id = e_book.fa_id');
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function newarrival(){
		$this->db->select("*");
		$this->db->from("e_book");		  
		$this->db->join('e_category', 'e_category.cat_id = e_book.fcat_id');
		$this->db->join('e_author', 'e_author.a_id = e_book.fa_id');
		$this->db->order_by("b_date","DESC");
		$q = $this->db->get();
		return $q->result();
	}

	public function featureitem($where){
		$this->db->select("*");
		$this->db->from("e_book");	
		$this->db->join('e_category', 'e_category.cat_id = e_book.fcat_id');
		$this->db->join('e_author', 'e_author.a_id = e_book.fa_id');	  
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function view_cnt($where){
		$this->db->select("*");
		$this->db->from("e_book");	
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function update_view($where,$add_point){			
		$this->db->set('readcnt', $add_point, FALSE);
		$this->db->where($where);
		$this->db->update('e_book');
		return true;
	}

	public function update_download($where,$add_point){			
		$this->db->set('download', $add_point, FALSE);
		$this->db->where($where);
		$this->db->update('e_book');
		return true;
	}

	public function add_comment($data){
		$query = $this->db->insert('e_comment',$data);
		return $this->db->insert_id();
	}

	public function view_comment($where){
		$this->db->select("*");
		$this->db->from("e_comment");	
		$this->db->join('e_user', 'e_user.id = e_comment.user_id');	  
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}
	
	public function purchase($data){
		$query = $this->db->insert('e_transacation',$data);
		return $this->db->insert_id();
	}

	public function add_continue_read($data){
		$query = $this->db->insert('e_continue_read',$data);
		return $this->db->insert_id();
	}

	public function continue_read($where){
		$this->db->select("*");
		$this->db->from("e_continue_read");		  
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function add_bookmark($data){
		$query = $this->db->insert('e_bookmark',$data);
		return $this->db->insert_id();
	}

	public function insert_book_rating($data){
		$query = $this->db->insert('e_rating',$data);
		return $this->db->insert_id();
	}
	public function get_book_rating($where){
		$this->db->select("*");
		$this->db->from("e_rating");		  
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function avg_book_rating($where){
		$this->db->select('AVG(rating) as average');
		$this->db->where('book_id',$where);
		return $result=$this->db->get('e_rating')->row();
	}

	public function update_book_rating($where,$data){
		$this->db->where($where);
		$this->db->update('e_rating',$data);
		return true;
	}

	public function delete_bookmark($where){
		$this->db->where($where);
		$this->db->delete("e_bookmark");
		return "1";
	}

	public function bookmarks($where){
		$this->db->select("*");
		$this->db->from("e_bookmark");		  
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function add_download($data){
		$query = $this->db->insert('e_download',$data);
		return $this->db->insert_id();
	}
	
	public function bookdownload($where){
		$this->db->select("*");
		$this->db->from("e_download");		  
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function downloads($where){
		$this->db->select("*");
		$this->db->from("e_download");		  
		$this->db->where($where);
		$q = $this->db->get();
		return $q->result();
	}

	public function genaral_setting(){
		$this->db->select("*");
		$this->db->from("e_general_setting");
		$q = $this->db->get();
		return $q->result_array();
	}

	public function userlist(){
		$this->db->select("*");
		$this->db->from("wl_user");
		$q = $this->db->get();
		return $q->result_array();
	}

}