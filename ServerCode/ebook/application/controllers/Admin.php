<?php
defined('BASEPATH') OR exit('No direct script access allowed');


class Admin extends CI_Controller{

	public function __construct() {
		parent::__construct();
		$CI =& get_instance();
		$CI->load->library('session');
		$this->load->helper('url');
		// $this->load->helper('delpha_helper');	
		// frontendcheck();
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

	public function logout(){
		$this->session->unset_userdata('email');
		$this->session->unset_userdata('id');
		$this->session->unset_userdata('name');
		redirect(base_url().'index.php/login');
	}

	public function dashboard(){
		
		/*for Book Count*/
		$result = $this->Adminmodel->booksall();
		$data['wallpaper']=sizeof($result);

		/*for category Count*/
		$result = $this->Adminmodel->category_list();
		$data['category']=sizeof($result);

		/*Books Download*/
		$result = $this->Adminmodel->books_download();
		$data['wallpaper_down']=$result;

			// /*Books Views*/
		$result = $this->Adminmodel->books_view();
		$data['book_view']=$result;

		/*Register User*/
		$result = $this->Adminmodel->userlist();
		$data['register_user']=sizeof($result);

		$result = $this->Adminmodel->books_transacation();
		$data['book_earn']=$result;

		$data['settinglist'] = $this->Adminmodel->settings_data();

		$this->load->view("admin/dashboard", $data);

	}

	/*======================Category=============================*/

	public function categorylist(){
		$data['settinglist'] = $this->Adminmodel->settings_data();
		$data['categorydata'] = $this->Adminmodel->category_list();
		$this->load->view("admin/categorylist",$data);
	}

	public function addcategory(){
		$data['settinglist'] = $this->Adminmodel->settings_data();
		$this->load->view("admin/addcategory",$data);
	}

	public function savecategory(){
		$category_name=$_POST['category_name'];

		$category_image=$this->imageupload($_FILES['category_image'],'category_image', FCPATH . 'assets/images/category');

		$data = array(
			'cat_name' => $category_name,
			'cat_image' => $category_image,
			'cat_date'=>date('Y-m-d h:i:s'),
			'cat_status'=>'enable'
		);

		$cat_id=$this->Adminmodel->add_category($data);
		
		if($cat_id){
			$res=array('status'=>'200','msg'=>'New Category Create.','id'=>$cat_id);
			echo json_encode($res);exit;
		}else{
			$res=array('status'=>'400','msg'=>'fail');
			echo json_encode($res);exit;
		}
	}

	public function editcategory(){
		$id=$_GET['id'];
		$where='cat_id="'.$id.'"';
		$data['category'] = $this->Adminmodel->get_category($where);
		$data['settinglist'] = $this->Adminmodel->settings_data();
		$this->load->view("admin/editCategory",$data);
	}
	
	public function updatecategory(){
		$category_name=$_POST['category_name'];

		if (isset($_FILES['category_image']) && !empty($_FILES['category_image']['name'])) {
			
			$CatImage=$this->imageupload($_FILES['category_image'],'category_image', FCPATH . 'assets/images/category');	
		}else{
			$CatImage=$_POST['categoryimage'];	
		}
		$id=$_POST['id'];
		$data = array(
			'cat_name' => $category_name,
			'cat_image' => $CatImage,
			'cat_date'=>date('Y-m-d h:i:s')
		);
		$cat_id=$this->Adminmodel->update_status_category($id,$data);
		if($cat_id){
			$res=array('status'=>'200','msg'=>'Update category success','id'=>$cat_id);
			echo json_encode($res);exit;
		}else{
			$res=array('status'=>'400','msg'=>'fail');
			echo json_encode($res);exit;
		}
	}

	/*======================End Category=============================*/

	
	/*====================== Users =============================*/

	public function adduser(){
		$data['settinglist'] = $this->Adminmodel->settings_data();
		$this->load->view("admin/adduser",$data);
	}

	public function saveuser(){
		$input_name = $_POST['input_name'];
		$input_email = $_POST['input_email'];
		$input_mobile = $_POST['input_mobile'];
		$input_password = $_POST['input_password'];

		$where='mobile_number="'.$input_mobile.'" OR email="'.$input_email.'"';
		$userlist= $this->Adminmodel->userlist_by_where($where);

		if(sizeof($userlist)>0){
			$res=array('status'=>'400','msg'=>'User already exits');
			echo json_encode($res);
			exit;
		}else{
			$data = array(
				'fullname' => $input_name,
				'email' => $input_email,
				'password' => $input_password,
				'mobile_number' => $input_mobile,
				'c_date' => date('Y-m-d h:i:s')
			);

			$res_id=$this->Adminmodel->add_user($data);

			if($res_id){
				$res=array('status'=>'200','msg'=>'New user added Sucessfully',
					'id'=>$res_id);
				echo json_encode($res);
			}else{
				$res=array('status'=>'400','msg'=>'Please try again');
				echo json_encode($res);
			}
		}
	}

	public function userlist(){
		$data['settinglist'] = $this->Adminmodel->settings_data();
		$data['userlist'] = $this->Adminmodel->userlist();
		$this->load->view("admin/userlist",$data);
	}

	public function edituser(){
		$id=$_GET['id'];

		$where='id="'.$id.'"';
		$data['userlist'] = $this->Adminmodel->userlistbywhere($where);
		$this->load->view("admin/edituser",$data);
	}

	public function updateuser(){
		$user_name=$_POST['user_name'];
		$user_email=$_POST['user_email'];
		$user_password=$_POST['user_password'];
		$user_number=$_POST['user_number'];
		$id=$_POST['id'];

		$data = array(
			'fullname' => $user_name,
			'email' => $user_email,
			'password' => $user_password,
			'mobile_number' => $user_number,
			'c_date'=>date('Y-m-d h:i:s')
		);

		$cat_id=$this->Adminmodel->update_user($data,$id);
		if($cat_id){
			$res=array('status'=>'200','msg'=>'Sucessfully updated','id'=>$cat_id);
			echo json_encode($res);exit;
		}else{
			$res=array('status'=>'400','msg'=>'failed','id'=>$cat_id);
			echo json_encode($res);exit;
		}
	}


	/*======================End Users=============================*/


	/*==========================Authors==========================*/

	public function authorlist(){
		$data['authorlist'] = $this->Adminmodel->authorall();
		$data['settinglist'] = $this->Adminmodel->settings_data();
		$this->load->view("admin/authorlist",$data);
	}

	public function addauthor(){
		$data['settinglist'] = $this->Adminmodel->settings_data();
		$this->load->view("admin/addauthor",$data);
	}

	public function saveauthor(){
		$input_name = $_POST['input_name'];
		$input_description = $_POST['input_description'];
		$input_address = $_POST['input_address'];
		$b_status = "enable";
		
		$image=$this->imageupload($_FILES['input_profile'],'input_profile', FCPATH . 'assets/images/author');

		$data = array(
			'a_title' => $input_name,
			'a_address' => $input_address,
			'a_bio' => $input_description,
			'a_image' => $image,
			'a_status' => $b_status
		);

		$res_id=$this->Adminmodel->add_author($data);
		
		if($res_id){
			$res=array('status'=>'200','msg'=>'Author added successfully.','id'=>$res_id);
			echo json_encode($res);exit;
		}else{
			$res=array('status'=>'400','msg'=>'fail');
			echo json_encode($res);exit;
		}
	}

	public function editauthor(){

		$id=$_GET['a_id'];
		$where='a_id="'.$id.'"';
		$data['authorlist'] = $this->Adminmodel->author_by_id($where);
		$data['settinglist'] = $this->Adminmodel->settings_data();
		$this->load->view("admin/editauthor",$data);
	}

	public function updateauthor(){
		$name=$_POST['input_name'];
		$description=$_POST['input_description'];
		$input_address = $_POST['input_address'];

		if (isset($_FILES['input_profile']) && !empty($_FILES['input_profile']['name'])) {
			$authorimage=$this->imageupload($_FILES['input_profile'],'input_profile', FCPATH . 'assets/images/author');
		}else{
			$authorimage=$_POST['inputprofile'];	
		}

		$id=$_POST['id'];
		$data = array(
			'a_title' => $name,
			'a_address' => $input_address,
			'a_image' => $authorimage,
			'a_bio' => $description
		);

		$res_id=$this->Adminmodel->update_author($id,$data);
		
		if($res_id){
			$res=array('status'=>'200','msg'=>'Author added successfully.','id'=>$res_id);
			echo json_encode($res);exit;
		}else{
			$res=array('status'=>'400','msg'=>'fail');
			echo json_encode($res);exit;
		}
	}


	/*==========================End Authors==========================*/


	/*============================Books===================================*/

	public function booklist(){
		$data['bookslist'] = $this->Adminmodel->booksall();
		$data['settinglist'] = $this->Adminmodel->settings_data();
		$this->load->view("admin/booklist",$data);
	}

	public function addbook(){
		$data['booklist'] = $this->Adminmodel->booksall();
		$data['authorlist'] = $this->Adminmodel->authorall();
		$data['categorylist'] = $this->Adminmodel->category_list();
		$data['settinglist'] = $this->Adminmodel->settings_data();
		$this->load->view("admin/addbook",$data);
	}

	public function savebook(){
		$input_name = $_POST['input_name'];
		$input_description = $_POST['input_description'];
		$input_price = $_POST['input_price'];
		$select_category = $_POST['select_category'];
		$select_author = $_POST['select_author'];
		$select_cost = $_POST['select_cost'];
		$is_feature = "yes";
		$b_status = "enable";
		$fa_id = $_POST['select_author'];

		$input_bookcover=$this->imageupload($_FILES['input_bookcover'],'input_bookcover', FCPATH . 'assets/images/book');

		if (isset($_FILES['input_sample_book']) && !empty($_FILES['input_sample_book']['name'])) {
			$input_sample_book=$this->fileupload($_FILES['input_sample_book'],'input_sample_book', FCPATH . 'assets/images/book');
		}

		if (isset($_FILES['input_full_book']) && !empty($_FILES['input_full_book']['name'])) {
			$input_full_book=$this->fileupload($_FILES['input_full_book'],'input_full_book', FCPATH . 'assets/images/book');
		}

		$data = array(
			'b_title' => $input_name,
			'b_description' => $input_description,
			'is_paid' => $select_cost,
			'sample_b_url' => $input_sample_book,
			'b_url' => $input_full_book,
			'b_price' => $input_price,
			'fcat_id'=> $select_category,
			'b_image' => $input_bookcover,
			'is_feature' => $is_feature,
			'b_status' => $b_status,
			'fa_id' => $fa_id,
			'b_date' => date('Y-m-d h:i:s')
		);
		
		$res_id=$this->Adminmodel->add_book($data);

		if($res_id){
			$res=array('status'=>'200','msg'=>'Book added successfully.','id'=>$res_id);
			echo json_encode($res);exit;
		}else{
			$res=array('status'=>'400','msg'=>'fail');
			echo json_encode($res);exit;
		}
	}	

	public function editbook(){
		$id=$_GET['b_id'];
		$data['categorylist'] = $this->Adminmodel->category_list();
		
		$where='b_id="'.$id.'"';

		$data['booklist'] = $this->Adminmodel->books_by_id($where);
		$data['authorlist'] = $this->Adminmodel->authorall();
		$data['settinglist'] = $this->Adminmodel->settings_data();
		$this->load->view("admin/editbook",$data);
	}

	public function update_book(){
		$id = $_POST['id'];
		$input_name = $_POST['input_name'];
		$input_description = $_POST['input_description'];
		$input_price = $_POST['input_price'];
		$select_category = $_POST['select_category'];
		$select_author = $_POST['select_author'];
		$select_cost = $_POST['select_cost'];
		$is_feature = "yes";
		$b_status = "enable";
		$fa_id = $_POST['select_author'];

		if (isset($_FILES['input_bookcover']) && !empty($_FILES['input_bookcover']['name'])) {
			$BookCoverImage=$this->imageupload($_FILES['input_bookcover'],'input_bookcover', FCPATH . 'assets/images/book');
		}else{
			$BookCoverImage=$_POST['inputbookcover'];	
		}

		if (isset($_FILES['input_sample_book']) && !empty($_FILES['input_sample_book']['name'])) {
			$SampleBook=$this->fileupload($_FILES['input_sample_book'],'input_sample_book', FCPATH . 'assets/images/book');
		}else{
			$SampleBook=$_POST['inputsamplebook'];	
		}

		if (isset($_FILES['input_full_book']) && !empty($_FILES['input_full_book']['name'])) {
			$FullBook=$this->fileupload($_FILES['input_full_book'],'input_full_book', FCPATH . 'assets/images/book');
		}else{
			$FullBook=$_POST['inputfullbook'];	
		}

		$data = array(
			'b_title' => $input_name,
			'b_description' => $input_description,
			'is_paid' => $select_cost,
			'sample_b_url' => $SampleBook,
			'b_url' => $FullBook,
			'b_price' => $input_price,
			'fcat_id'=> $select_category,
			'b_image' =>$BookCoverImage,
			'is_feature' => $is_feature,
			'b_status' => $b_status,
			'fa_id' => $fa_id,
			'b_date' => date('Y-m-d h:i:s')
		);

		$res_id=$this->Adminmodel->update_book($data,$id);

		if($res_id){
			$res=array('status'=>'200','msg'=>'Book added successfully.','id'=>$res_id);
			echo json_encode($res);exit;
		}else{
			$res=array('status'=>'400','msg'=>'Please try again');
			echo json_encode($res);exit;
		}
	}

	/*============================End Book===================================*/
	
	public function settingpage(){
		$data['settinglist'] = $this->Adminmodel->settings_data();
		$where='id="'.$this->session->userdata('id').'"';
		$admin = $this->Adminmodel->admin_varify($where);
		$data['admin'] = $admin[0];
		$this->load->view("admin/settings",$data);
	}

	public function savesetting(){
		$app_name=$_POST['app_name'];
		$app_image_logo=$_FILES['app_image']['name'];
		$app_desc=$_POST['app_desc'];
		$app_privacy=$_POST['app_privacy'];
		$host_email=$_POST['host_email'];

		$app_author=$_POST['app_author'];
		$host_contact=$_POST['host_contact'];
		$host_website=$_POST['host_website'];

		if (isset($_FILES['app_image']) && !empty($_FILES['app_image']['name'])) {
			$config = array(
				'allowed_types' => 'jpg|jpeg|gif|png',
				'upload_path' => FCPATH . 'assets/images/app'
			);
			$this->load->library('upload');
			$this->upload->initialize($config);
			$this->upload->do_upload('app_image');

			$data3 = array(					 
				'value' => $app_image_logo,                  
			);
               // $where3='key=';
			$this->Adminmodel->update_general_setting($data3,'app_logo');

		}

		$data = array(					 
			'value' => $host_email,                  
		);
               // $where='key="host_email"';
		$this->Adminmodel->update_general_setting($data,'host_email');

		$data1 = array(					 
			'value' => $app_desc,                  
		);
                //$where='key="app_desripation"';
		$this->Adminmodel->update_general_setting($data1,'app_desripation');

		$data2 = array(					 
			'value' => $app_name,                  
		);
		$this->Adminmodel->update_general_setting($data2,'app_name');


		$data_author = array(					 
			'value' => $app_author,                  
		);
		$this->Adminmodel->update_general_setting($data_author,'Author');

		$data_contact = array(					 
			'value' => $host_contact,                  
		);
		$this->Adminmodel->update_general_setting($data_contact,'contact');

		$data_priv = array(					 
			'value' => $app_privacy,                  
		);
		$this->Adminmodel->update_general_setting($data_priv,'privacy_policy');

		$data_website = array(					 
			'value' => $host_website,                  
		);
		$res_id=$this->Adminmodel->update_general_setting($data_website,'website');

		if($res_id){
			$res=array('status'=>'200','msg'=>'Sucessfully updated','id'=>$res_id);
			echo json_encode($res);
		}else{
			$res=array('status'=>'400','msg'=>'fail');
			echo json_encode($res);
		}

	}
	
	public function delete_record(){
		$id=$_POST['id'];
		$tablename=$_POST['tablename'];
		if($tablename=='book'){
			$this->Adminmodel->delete_book($id);
		}elseif ($tablename=='category') {
			$this->Adminmodel->delete_category($id);	
		}elseif ($tablename=='author') {
			$this->Adminmodel->delete_author($id);	
		}
		return true;
	}

	public function notification(){
		$data['settinglist'] = $this->Adminmodel->settings_data();
		$this->load->view("admin/notification",$data);
	}

	public function save_admob(){
		$publisher_id=$_POST['publisher_id'];
		$banner_ad=$_POST['banner_ad'];
		$banner_ad_id=$_POST['banner_ad_id'];
		$interstital_ad=$_POST['interstital_ad'];
		$interstital_adid=$_POST['interstital_adid'];
		$interstital_adid_click=$_POST['interstital_adid_click'];
		$custom_ad=$_POST['custom_ad'];

		$data3 = array(					 
			'value' => $publisher_id,                  
		);
               // $where3='key=';
		$this->Adminmodel->update_general_setting($data3,'publisher_id');

		$data = array(					 
			'value' => $banner_ad,                  
		);
               // $where='key="host_email"';
		$this->Adminmodel->update_general_setting($data,'banner_ad');

		$data1 = array(					 
			'value' => $banner_ad_id,                  
		);
                //$where='key="app_desripation"';
		$this->Adminmodel->update_general_setting($data1,'banner_adid');

		$data2 = array(					 
			'value' => $interstital_ad,                  
		);
               // $where2='key="app_name"';
		$this->Adminmodel->update_general_setting($data2,'interstital_ad');

		$data5 = array(					 
			'value' => $interstital_adid,                  
		);
               // $where2='key="app_name"';
		$this->Adminmodel->update_general_setting($data5,'interstital_adid');

		$data4 = array(					 
			'value' => $interstital_adid_click,                  
		);
               // $where2='key="app_name"';
		$this->Adminmodel->update_general_setting($data4,'interstital_adid_click');

		$data_custom_ads = array(					 
			'value' => $custom_ad,                  
		);
		$this->Adminmodel->update_general_setting($data_custom_ads,'custom_ads');

		if (isset($_FILES['app_image1']) && !empty($_FILES['app_image1']['name'])) {
			$BookCoverImage=$this->imageupload($_FILES['app_image1'],'app_image1', FCPATH . 'assets/images/app');
		}else{
			$BookCoverImage=$this->imageupload($_FILES['app_image_logo1'],'app_image_logo1', FCPATH . 'assets/images/app');
		}

		$data3 = array(					 
			'value' => $BookCoverImage,                  
		);
		$this->Adminmodel->update_general_setting($data3,'custom_image');
	}

	public function save_signal_noti(){
		$one_signal=$_POST['one_signal'];
		$rest_key=$_POST['rest_key'];

		$data = array(					 
			'value' => $one_signal,                  
		);
               // $where='key="host_email"';
		$this->Adminmodel->update_general_setting($data,'onesignal_apid');
		$data1 = array(					 
			'value' => $rest_key,                  
		);
                //$where='key="app_desripation"';
		$this->Adminmodel->update_general_setting($data1,'onesignal_rest_key');

	}

	public function change_password(){
		$password  =$_POST['password'];
		$confirm_password  =$_POST['confirm_password'];
		$where = $_POST['admin_id'];
		$data =  array('password' => $password );

		$res_id=$this->Adminmodel->update_user($data,$where);

		if($res_id){
			$res=array('status'=>'200','msg'=>'Sucessfully updated','id'=>$res_id);
			echo json_encode($res);
		}else{
			$res=array('status'=>'400','msg'=>'fail');
			echo json_encode($res);
		}
	}

	public function save_payment(){
		$paypal_name=$_POST['paypal_name'];
		$paypal_client_id=$_POST['paypal_client_id'];
		$upi_name=$_POST['upi_name'];
		$upi_id=$_POST['upi_id'];

		$data = array(					 
			'value' => $paypal_name,                  
		);
		$this->Adminmodel->update_general_setting($data,'paypal_name');
		
		$data1 = array(					 
			'value' => $paypal_client_id,                  
		);
		$this->Adminmodel->update_general_setting($data1,'paypal_client_id');

		$data2 = array(					 
			'value' => $upi_name,                  
		);
		$this->Adminmodel->update_general_setting($data2,'UPI_Name');
		
		$data3 = array(					 
			'value' => $upi_id,                  
		);
		$this->Adminmodel->update_general_setting($data3,'UPI');

	}

	public function savenotification(){
		$setting= $this->Adminmodel->settings_data();

		foreach($setting as $set)
		{
			$setn[$set->key]=$set->value;
		}

		$ONESIGNAL_APP_ID=$setn['onesignal_apid'];
		$ONESIGNAL_REST_KEY=$setn['onesignal_rest_key'];
		$big_picture=$_FILES['video_thumbnail']['name'];
		$tpath2= FCPATH . 'assets/images/book/';
		$config = array(
			'allowed_types' => 'jpg|jpeg|gif|png',
			'upload_path' => FCPATH . 'assets/images/book'
		);
		$this->load->library('upload');
		$this->upload->initialize($config);
		$this->upload->do_upload('video_thumbnail');

		$content = array(
			"en" => $_POST['message']                                                 
		);

		if(isset($_FILES['video_thumbnail']['name']))
		{
			$_FILES['video_thumbnail']['name'];
			$file_path = base_url().'assets/images/book/'.$big_picture;
			$fields = array(
				'app_id' =>  $ONESIGNAL_APP_ID,
				'included_segments' => array('All'),                                            
				'data' => array("foo" => "bar"),
				'headings'=> array("en" => $_POST['title']),
				'contents' => $content,
				'big_picture' =>$file_path                    
			);
		}else
		{
			$file_path = '';  
			$fields = array(
				'app_id' => $ONESIGNAL_APP_ID,
				'included_segments' => array('All'),   
				'data' => array("foo" => "bar"),
				'headings'=> array("en" => $_POST['title']),
				'contents' => $content,
			);
		}

		$fields = json_encode($fields);    

		$ch = curl_init();
		curl_setopt($ch, CURLOPT_URL, "https://onesignal.com/api/v1/notifications");
		curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json; charset=utf-8',
			'Authorization: Basic '.$ONESIGNAL_REST_KEY));
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
		curl_setopt($ch, CURLOPT_HEADER, FALSE);
		curl_setopt($ch, CURLOPT_POST, TRUE);
		curl_setopt($ch, CURLOPT_POSTFIELDS, $fields);
		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, FALSE);

		$response = curl_exec($ch);

		curl_close($ch);
		print_r($response);

	}

	public function imageupload($imageName,$imgname, $uploadpath){

		if(empty($imageName['name'])){
			$res=array('status'=>'400','msg'=>'Please Upload Image first.');
			echo json_encode($res);exit;
		}
		if(!empty($imageName['name']) && ($imageName['error']==1 || $imageName['size']>2215000)){
			$res=array('status'=>'400','msg'=>'Max 2MB file is allowed for image.');
			echo json_encode($res);exit;
		}else{
			list($width, $height) = getimagesize($imageName['tmp_name']);
			if($width>1000 || $height >1000){
				$res=array('status'=>'400','msg'=>'Image height and width must be less than 1000px.');
				echo json_encode($res);exit;
			}else{

				$catImg = $imageName['name'];
				$ext = pathinfo($catImg);
				$catImages = str_replace(' ', '_', $ext['filename']);
				$category_image =$catImages.time().'.'.$ext['extension'];				
				$config = array(
					'allowed_types' => 'jpg|jpeg|gif|png',
					'upload_path' => $uploadpath,
					'file_name' => $category_image
				);
				$this->load->library('upload');
				$this->upload->initialize($config);
				$this->upload->do_upload($imgname);
				return $category_image;
			}

		}
	}

	public function fileupload($imageName,$imgname, $uploadpath){

		if(empty($imageName['name'])){
			$res=array('status'=>'400','msg'=>'Please Upload file first.');
			echo json_encode($res);exit;
		}
		if(!empty($imageName['name']) && ($imageName['error']==1 || $imageName['size']>103790053)){
			$res=array('status'=>'400','msg'=>'Max 10MB file is allowed for image.');
			echo json_encode($res);exit;
		}else{
			
			$catImg = $imageName['name'];
			$ext = pathinfo($catImg);
			$catImages = str_replace(' ', '_', $ext['filename']);
			$category_image =$catImages.time().'.'.$ext['extension'];				
			$config = array(
				'allowed_types' => 'pdf|epub',
				'upload_path' => $uploadpath,
				'file_name' => $category_image
			);
			$this->load->library('upload');
			$this->upload->initialize($config);
			$this->upload->do_upload($imgname);
			return $category_image;
		}
	}

	public function status_change(){
		$id=$_POST['id'];
		$tablename=$_POST['tablename'];
		$status=$_POST['status'];
		if($status=='enable'){
			$status1='disable';
		}else{
			$status1='enable';
		}
		if($tablename=='user'){
			$data1=array('status' => $status1);
			$this->Adminmodel->user_status_change($id,$data1);
		}elseif ($tablename=='category') {
			$data1=array('cat_status' => $status1);
			$this->Adminmodel->update_status_category($id,$data1);
		}else{
			$data1=array('cat_status' => $status1);
			$this->Adminmodel->update_status_album($id,$data1);
		}
		return true;	
	}
}
