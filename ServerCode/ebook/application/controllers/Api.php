<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class api extends CI_Controller {

	public function __construct() {
		parent::__construct();
		$this->load->model('Apimodel');
		$this->load->helper('url');
		$this->load->helper('form'); 
	}

	public function index(){
		$this->load->view('welcome_message');
	}

	public function login(){
		$email=$_REQUEST['email'];
		$password=$_REQUEST['password'];

		$where='password="'.$password.'" and email="'.$email.'" ';
		$resultr=$this->Apimodel->login_user($where);

		if(sizeof($resultr)>0){
			$uid= $resultr[0]->id;
			$status=$resultr[0]->status;
			if($status=='enable')
				$response=array('status'=>200,'message'=>'Login Success','User_id'=>"".$uid);
			else
				$response=array('status'=>400,'message'=>'User is deactivated. please contact to support team.','User_id'=>"".$uid);
		}else{
			$r=array();
			$response=array('status'=>400,'message'=>'Please enter valid Email OR Password','User_id'=>'');
		}
		echo json_encode($response);
	}

	public function registration(){
		
		$fullname= $_REQUEST['fullname'];
		$email= $_REQUEST['email'];
		$password= $_REQUEST['password'];
		$mobile_number= $_REQUEST['mobile_number'];

		$where='email="'.$email.'"';
		$r = $this->Apimodel->login_user($where); 

		$where_mobile='mobile_number="'.$mobile_number.'"';
		$r_mobile = $this->Apimodel->login_user($where_mobile); 

		if(sizeof($r)>0){
			$response=array('status'=>400,'message'=>'Email address already exists.','User_id'=>'');
			echo json_encode($response);
		}else if(sizeof($r_mobile)>0){
			$response=array('status'=>400,'message'=>'Mobile Number already exists.','User_id'=>'');
			echo json_encode($response);
		}else{
			$data = array(
				'fullname'=>$email,
				'email'=>$email,
				'password'=>$password,
				'mobile_number'=>$mobile_number,
				'c_date'=>date('Y-m-d H:i:s')
			);

			$user_id=$this->Apimodel->registration_api($data);

			$response=array('status'=>200,'message'=>'User registration sucessfuly','User_id'=>(string)$user_id);
			echo json_encode($response);

		}
	}

	public function profile(){
		
		$user_id=$_REQUEST['user_id'];
		$where='id="'.$user_id.'" ';

		$resultr=$this->Apimodel->profile($where);
		unset($resultr[0]->password);
		unset($resultr[0]->c_date);

		if(sizeof($resultr)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
		}
		// $response=array('status'=>200,'result'=>"".$user_id,'message'=>'Login Success');
		echo json_encode($response);
	}

	public function categorylist(){
		$resultr=$this->Apimodel->category();

		$rk=array();
		foreach($resultr as $ra){
			$ra->cat_image=base_url().'assets/images/category/'.$ra->cat_image;
			$rk[]= $ra;  
		}

		if(sizeof($rk)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$rk);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$rk);
		}
		echo json_encode($response);
	}

	public function books_by_category(){
		
		$a_id=$_REQUEST['cat_id'];
		
		$where='fcat_id="'.$a_id.'"';

		$resultr=$this->Apimodel->booklist_by_category($where);

		$rk=array();
		foreach($resultr as $ra){
			$avg=$this->Apimodel->avg_book_rating($ra->b_id);
			$ra->b_image=base_url().'assets/images/book/'.$ra->b_image;
			$ra->sample_b_url=base_url().'assets/images/book/'.$ra->sample_b_url;
			$ra->b_url=base_url().'assets/images/book/'.$ra->b_url;
			$ra->a_image=base_url().'assets/images/book/'.$ra->a_image;
			if($avg->average>0){
				$ra->avg_rating = $avg->average;
			}else{
				$ra->avg_rating = "0";
			}
			$rk[]= $ra;  
		}

		if(sizeof($resultr)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
		}
		echo json_encode($response);
	}

	public function autherlist(){
		$resultr=$this->Apimodel->author();

		$rk=array();
		foreach($resultr as $ra){
			$ra->a_image=base_url().'assets/images/author/'.$ra->a_image;
			$rk[]= $ra;  
		}

		if(sizeof($rk)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$rk);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$rk);
		}
		echo json_encode($response);
	}

	public function books_by_author(){
		
		$a_id=$_REQUEST['a_id'];
		
		$where='fa_id="'.$a_id.'"';

		$resultr=$this->Apimodel->booklist_by_author($where);

		$rk=array();
		foreach($resultr as $ra){
			$avg=$this->Apimodel->avg_book_rating($ra->b_id);
			$ra->b_image=base_url().'assets/images/book/'.$ra->b_image;
			$ra->sample_b_url=base_url().'assets/images/book/'.$ra->sample_b_url;
			$ra->b_url=base_url().'assets/images/book/'.$ra->b_url;
			$ra->a_image=base_url().'assets/images/book/'.$ra->a_image;
			if($avg->average>0){
				$ra->avg_rating = $avg->average;
			}else{
				$ra->avg_rating = "0";
			}
			$rk[]= $ra;  
		}

		if(sizeof($resultr)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
		}
		echo json_encode($response);
	}

	public function readcnt_by_author(){
		
		$a_id=$_REQUEST['a_id'];
		
		$where='fa_id="'.$a_id.'"';

		$resultr=$this->Apimodel->readcnt_by_author($where);

		if(sizeof($resultr)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
		}
		echo json_encode($response);
	}

	public function booklist(){
		
		$resultr=$this->Apimodel->booklist();

		$rk=array();
		foreach($resultr as $ra){
			$avg=$this->Apimodel->avg_book_rating($ra->b_id);
			$ra->b_image=base_url().'assets/images/book/'.$ra->b_image;
			$ra->sample_b_url=base_url().'assets/images/book/'.$ra->sample_b_url;
			$ra->b_url=base_url().'assets/images/book/'.$ra->b_url;
			$ra->a_image=base_url().'assets/images/book/'.$ra->a_image;
			if($avg->average>0){
				$ra->avg_rating = $avg->average;
			}else{
				$ra->avg_rating = "0";
			}
			$rk[]= $ra;  
		}

		if(sizeof($resultr)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
		}
		echo json_encode($response);
	}

	public function bookdetails(){
		
		$b_id=$_REQUEST['b_id'];
		$user_id=$_REQUEST['user_id'];
		if($user_id!=0){
			$where='b_id="'.$b_id.'"';
			$where_tra='t_fb_id="'.$b_id.'" AND t_user_id='.$user_id.' ';

			$resultr=$this->Apimodel->bookdetails($where);

			$resultr_tra=$this->Apimodel->transacation($where_tra);

			$rk=array();
			foreach($resultr as $ra){
				$avg=$this->Apimodel->avg_book_rating($ra->b_id);
				$ra->b_image=base_url().'assets/images/book/'.$ra->b_image;
				$ra->sample_b_url=base_url().'assets/images/book/'.$ra->sample_b_url;
				$ra->b_url=base_url().'assets/images/book/'.$ra->b_url;
				$ra->a_image=base_url().'assets/images/book/'.$ra->a_image;
				if($avg->average>0){
					$ra->avg_rating = $avg->average;
				}else{
					$ra->avg_rating = "0";
				}
				$rk[]= $ra;  

				if(sizeof($resultr_tra)>0){
					$ra->is_paid="0";
				}
			}

			if(sizeof($resultr)>0){
				$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
			}else{
				$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
			}
			echo json_encode($response);
		}else{

			$where='b_id="'.$b_id.'"';

			$resultr=$this->Apimodel->bookdetails($where);

			foreach($resultr as $ra){
				$avg=$this->Apimodel->avg_book_rating($ra->b_id);
				$ra->b_image=base_url().'assets/images/book/'.$ra->b_image;
				$ra->b_url=base_url().'assets/images/book/'.$ra->b_url;
				if($avg->average>0){
					$ra->avg_rating = $avg->average;
				}else{
					$ra->avg_rating = "0";
				}
			}

			if(sizeof($resultr)>0){
				$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
			}else{
				$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
			}
			echo json_encode($response);
		}
	}

	public function popularbooklist(){
		
		$resultr=$this->Apimodel->popularbooklist();

		$rk=array();
		foreach($resultr as $ra){
			$avg=$this->Apimodel->avg_book_rating($ra->b_id);
			$ra->b_image=base_url().'assets/images/book/'.$ra->b_image;
			$ra->sample_b_url=base_url().'assets/images/book/'.$ra->sample_b_url;
			$ra->b_url=base_url().'assets/images/book/'.$ra->b_url;
			$ra->a_image=base_url().'assets/images/book/'.$ra->a_image;
			if($avg->average>0){
				$ra->avg_rating = $avg->average;
			}else{
				$ra->avg_rating = "0";
			}
			$rk[]= $ra;  
		}

		if(sizeof($resultr)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
		}
		echo json_encode($response);
	}

	public function free_paid_booklist(){

		$where='is_paid="'.$_REQUEST['is_paid'].'" ';
		$resultr=$this->Apimodel->booklist_free_paid($where);

		$rk=array();
		foreach($resultr as $ra){
			$avg=$this->Apimodel->avg_book_rating($ra->b_id);
			$ra->b_image=base_url().'assets/images/book/'.$ra->b_image;
			$ra->sample_b_url=base_url().'assets/images/book/'.$ra->sample_b_url;
			$ra->b_url=base_url().'assets/images/book/'.$ra->b_url;
			$ra->a_image=base_url().'assets/images/book/'.$ra->a_image;
			if($avg->average>0){
				$ra->avg_rating = $avg->average;
			}else{
				$ra->avg_rating = "0";
			}
			$rk[]= $ra;  
		}

		if(sizeof($resultr)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
		}
		echo json_encode($response);
	}

	public function related_item(){

		$b_id=$_REQUEST['fcat_id'];
		$where='fcat_id="'.$b_id.'" ';

		$resultr=$this->Apimodel->related_item($where);

		$rk=array();
		foreach($resultr as $ra){
			$avg=$this->Apimodel->avg_book_rating($ra->b_id);
			$ra->b_image=base_url().'assets/images/book/'.$ra->b_image;
			$ra->sample_b_url=base_url().'assets/images/book/'.$ra->sample_b_url;
			$ra->b_url=base_url().'assets/images/book/'.$ra->b_url;
			$ra->a_image=base_url().'assets/images/book/'.$ra->a_image;
			if($avg->average>0){
				$ra->avg_rating = $avg->average;
			}else{
				$ra->avg_rating = "0";
			}
			$rk[]= $ra;  
		}

		if(sizeof($resultr)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
		}
		echo json_encode($response);
	}

	public function newarriaval(){

		$resultr=$this->Apimodel->newarrival();

		$rk=array();
		foreach($resultr as $ra){
			$avg=$this->Apimodel->avg_book_rating($ra->b_id);
			$ra->b_image=base_url().'assets/images/book/'.$ra->b_image;
			$ra->sample_b_url=base_url().'assets/images/book/'.$ra->sample_b_url;
			$ra->b_url=base_url().'assets/images/book/'.$ra->b_url;
			$ra->a_image=base_url().'assets/images/book/'.$ra->a_image;
			if($avg->average>0){
				$ra->avg_rating = $avg->average;
			}else{
				$ra->avg_rating = "0";
			}
			$rk[]= $ra;  
		}

		if(sizeof($resultr)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
		}
		echo json_encode($response);
	}

	public function feature_item(){

		$where='is_feature="yes" ';
		$resultr=$this->Apimodel->featureitem($where);

		$rk=array();
		foreach($resultr as $ra){
			$avg=$this->Apimodel->avg_book_rating($ra->b_id);
			$ra->b_image=base_url().'assets/images/book/'.$ra->b_image;
			$ra->sample_b_url=base_url().'assets/images/book/'.$ra->sample_b_url;
			$ra->b_url=base_url().'assets/images/book/'.$ra->b_url;
			$ra->a_image=base_url().'assets/images/book/'.$ra->a_image;
			if($avg->average>0){
				$ra->avg_rating = $avg->average;
			}else{
				$ra->avg_rating = "0";
			}
			$rk[]= $ra;  
		}

		if(sizeof($resultr)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
		}
		echo json_encode($response);
	}

	public function add_view(){

		$user_id=$_REQUEST['b_id'];
		$add_point=1;

		$where='b_id="'.$user_id.'" ';

		$result_total=$this->Apimodel->view_cnt($where);

		if(!empty($result_total)){
			$points= $result_total[0]->readcnt;
			$a=$points+$add_point;

			$resultr=$this->Apimodel->update_view($where,$a);
		}

		if(sizeof($resultr)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
		}
		echo json_encode($response);
	}

	public function add_download(){

		$b_id=$_REQUEST['b_id'];
		$user_id=$_REQUEST['user_id'];

		$add_point=1;

		$where='b_id="'.$b_id.'" ';
 
		$result_total=$this->Apimodel->view_cnt($where);

		if(!empty($result_total)){
			$points= $result_total[0]->download;
			$a=$points+$add_point;

			$resultr=$this->Apimodel->update_download($where,$a);
		}

		$where_down='bd_user_id='.$user_id.' and bd_b_id="'.$b_id.'"';

		$resultr_down=$this->Apimodel->bookdownload($where_down);

		$data = array(
			'bd_user_id'=>$user_id,
			'bd_b_id'=>$b_id,
			'bd_datetime'=>date('Y-m-d H:i:s')
		);

		if(sizeof($resultr_down)>0){			
			$response=array('status'=>201,'message'=>'Already download');
		}else{
			$user_id=$this->Apimodel->add_download($data);
			$response=array('status'=>200,'message'=>'Download Success');
		}

		echo json_encode($response);
	}

	public function alldownload(){
		
		$user_id=$_REQUEST['user_id'];
		
		$where_tra='bd_user_id='.$user_id.' ';

		$resultr=$this->Apimodel->allbook();

		$resultr_con=$this->Apimodel->downloads($where_tra);

		$rk=array();
		foreach($resultr as $ra){
			$ra->b_image=base_url().'assets/images/book/'.$ra->b_image;
			$ra->sample_b_url=base_url().'assets/images/book/'.$ra->sample_b_url;
			$ra->b_url=base_url().'assets/images/book/'.$ra->b_url;
			$ra->a_image=base_url().'assets/images/book/'.$ra->a_image;

			foreach($resultr_con as $racon){
				if($racon->bd_b_id == $ra->b_id){	
					$rk[]= $ra; 
				}
			}
		}

		if(sizeof($rk)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$rk);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>'');
		}
		echo json_encode($response);
	}

	public function add_comment(){

		$b_id=$_REQUEST['b_id'];
		$user_id=$_REQUEST['user_id'];
		$comment=$_REQUEST['comment'];

		$data = array(
			'b_id'=>$b_id,
			'comment'=>$comment,
			'user_id'=>$user_id,
			'c_status'=>'yes',
			'c_date'=>date('Y-m-d H:i:s')
		);

		$user_id=$this->Apimodel->add_comment($data);

		$response=array('status'=>200,'message'=>'Add comment sucessfuly','User_id'=>(string)$user_id);
		echo json_encode($response);
	}

	public function view_comment(){

		$b_id=$_REQUEST['b_id'];

		$where='b_id="'.$b_id.'"';

		$resultr=$this->Apimodel->view_comment($where);

		if(sizeof($resultr)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
		}
		echo json_encode($response);
	}

	public function add_purchase(){

		$b_id=$_REQUEST['fb_id'];
		$user_id=$_REQUEST['user_id'];
		$amount=$_REQUEST['amount'];
		$currency_code=$_REQUEST['currency_code'];
		$short_description=$_REQUEST['short_description'];
		$payment_id=$_REQUEST['payment_id'];
		$state=$_REQUEST['state'];
		$create_time=$_REQUEST['create_time'];

		$data = array(
			't_user_id'=>$user_id,
			't_fb_id'=>$b_id,
			't_currency_code'=>$currency_code,
			't_description'=>$short_description,
			't_payment_id'=>$payment_id,
			't_state'=>$state,
			't_amount'=>$amount,
			't_datetime'=>$create_time
		);

		$user_id=$this->Apimodel->purchase($data);

		$response=array('status'=>200,'message'=>'purchase sucessfuly','User_id'=>(string)$user_id);
		echo json_encode($response);
	}

	public function purchaselist(){

		$user_id=$_REQUEST['user_id'];
		
		$where_tra='t_user_id='.$user_id.' ';

		$resultr=$this->Apimodel->allbook();

		$resultr_con=$this->Apimodel->transacation($where_tra);

		$rk=array();
		foreach($resultr as $ra){
			$ra->b_image=base_url().'assets/images/book/'.$ra->b_image;
			$ra->b_url=base_url().'assets/images/book/'.$ra->b_url;
			// $rk[]= $ra;  

			foreach($resultr_con as $racon){
				if($racon->t_fb_id == $ra->b_id){	
					$rk[]= $ra; 
				}
			}
		}

		if(sizeof($rk)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$rk);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$rk);
		}
		echo json_encode($response);
	}

	public function add_continue_read(){

		$user_id=$_REQUEST['user_id'];
		$b_id=$_REQUEST['b_id'];

		$data = array(
			'co_user_id'=>$user_id,
			'co_b_id'=>$b_id,
			'co_datetime'=>date('Y-m-d H:i:s')
		);

		$user_id=$this->Apimodel->add_continue_read($data);

		$response=array('status'=>200,'message'=>'Add sucessfuly','User_id'=>(string)$user_id);
		echo json_encode($response);
	}

	public function continue_read(){
		
		$user_id=$_REQUEST['user_id'];
		
		$where_tra='co_user_id='.$user_id.' ';

		$resultr=$this->Apimodel->allbook();

		$resultr_con=$this->Apimodel->continue_read($where_tra);

		$rk=array();
		foreach($resultr as $ra){
			$avg=$this->Apimodel->avg_book_rating($ra->b_id);
			$ra->b_image=base_url().'assets/images/book/'.$ra->b_image;
			$ra->sample_b_url=base_url().'assets/images/book/'.$ra->sample_b_url;
			$ra->b_url=base_url().'assets/images/book/'.$ra->b_url;
			$ra->a_image=base_url().'assets/images/book/'.$ra->a_image;
			if($avg->average>0){
				$ra->avg_rating = $avg->average;
			}else{
				$ra->avg_rating = "0";
			}

			foreach($resultr_con as $racon){
				if($racon->co_b_id == $ra->b_id){	
					$rk[]= $ra; 
				}
			}
		}

		if(sizeof($resultr)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$rk);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr_tra);
		}
		echo json_encode($response);
	}

	public function add_bookmark(){

		$user_id=$_REQUEST['user_id'];
		$b_id=$_REQUEST['b_id'];

		$where_dele='bo_user_id='.$user_id.' and bo_b_id="'.$b_id.'"';

		$resultr_con=$this->Apimodel->bookmarks($where_dele);

		$data = array(
			'bo_user_id'=>$user_id,
			'bo_b_id'=>$b_id,
			'bo_datetime'=>date('Y-m-d H:i:s')
		);

		if(sizeof($resultr_con)>0){
			$user_id=$this->Apimodel->delete_bookmark($where_dele);
			$response=array('status'=>201,'message'=>'Bookmark Remove','User_id'=>(string)$user_id);
			echo json_encode($response);
		}else{
			$user_id=$this->Apimodel->add_bookmark($data);
			$response=array('status'=>200,'message'=>'Bookmark Success','User_id'=>(string)$user_id);
			echo json_encode($response);
		}
	}

	public function checkbookmark(){

		$user_id=$_REQUEST['user_id'];
		$b_id=$_REQUEST['b_id'];
		
		$where_check='bo_user_id='.$user_id.' and bo_b_id="'.$b_id.'"';

		$resultr_con=$this->Apimodel->bookmarks($where_check);

		if(sizeof($resultr_con)>0){
			$response=array('status'=>200,'message'=>'Book is already Bookmark','Result'=>"");
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>'');
		}
		echo json_encode($response);
	}

	public function allBookmark(){
		
		$user_id=$_REQUEST['user_id'];
		
		$where_tra='bo_user_id='.$user_id.' ';

		$resultr=$this->Apimodel->allbook();

		$resultr_con=$this->Apimodel->bookmarks($where_tra);

		$rk=array();
		foreach($resultr as $ra){
			$ra->b_image=base_url().'assets/images/book/'.$ra->b_image;
			$ra->sample_b_url=base_url().'assets/images/book/'.$ra->sample_b_url;
			$ra->b_url=base_url().'assets/images/book/'.$ra->b_url;
			$ra->a_image=base_url().'assets/images/book/'.$ra->a_image;

			foreach($resultr_con as $racon){
				if($racon->bo_b_id == $ra->b_id){	
					$rk[]= $ra; 
				}
			}
		}

		if(sizeof($rk)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$rk);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>'');
		}
		echo json_encode($response);
	}

	public function general_setting(){

		$resultr=$this->Apimodel->genaral_setting();

		if(sizeof($resultr)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
		}
		echo json_encode($response);
	}

	public function userlist(){

		$resultr=$this->Apimodel->userlist();

		if(sizeof($resultr)>0){
			$response=array('status'=>200,'message'=>'Success','Result'=>$resultr);
		}else{
			$response=array('status'=>400,'message'=>'Sorry, we could not find any matches.<br>Please try again.','Result'=>$resultr);
		}
		echo json_encode($response);
	}

	public function give_rating(){
		$book_id = $_POST['book_id'];
		$user_id = $_POST['user_id'];
		$ratingData  = $_POST['rating'];
		$where   ='book_id="'.$book_id.'" and user_id="'.$user_id.'"';
		$rating = $this->Apimodel->get_book_rating($where);

		$data = array(
			'book_id' => $book_id,
			'user_id'=>$user_id,
			'rating'=>$ratingData);
		if(sizeof($rating)>0){
			$where ='rating_id="'.$rating[0]->rating_id.'"';	
			$this->Apimodel->update_book_rating($where,$data);
		}else{
			$this->Apimodel->insert_book_rating($data);
		}
		$response=array('status'=>200,'message'=>'Success');
		echo json_encode($response);
	}
	
}