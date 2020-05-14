<?php
$this->load->view('admin/comman/header');
?>

<div class="clearfix"></div>

<div class="content-wrapper">
	<div class="container-fluid">
		<!-- Breadcrumb-->
		<div class="row pt-2 pb-2">
			<div class="col-sm-9">
				<h4 class="page-title">Add User</h4>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="javaScript:void();">Dashboard</a></li>
					<li class="breadcrumb-item"><a href="javaScript:void();">Users</a></li>
					<li class="breadcrumb-item active" aria-current="page">Add User</li>
				</ol>
			</div>
			<div class="col-sm-3">
				<div class="btn-group float-sm-right">
					<a href="<?php echo base_url();?>index.php/admin/userlist" class="btn btn-outline-primary waves-effect waves-light">UserList</a>
				</div>
			</div>
		</div>
		<!-- End Breadcrumb-->
		<div class="row">
			<div class="col-lg-10 mx-auto">
				<div class="card">
					<div class="card-body">
						<div class="card-title">Add User
							<form id="user_form"  enctype="multipart/form-data">

								<div class="form-group">
									<label for="input-1">FullName</label>
									<input type="text" required value="" class="form-control" name="input_name" id="input-1" >
								</div>

								<div class="form-group">
									<label for="input-1">Email</label>
									<input type="text" required value="" class="form-control" name="input_email" id="input-1" >
								</div>

								<div class="form-group">
									<label for="input-1">Mobile Number</label>
									<input type="Number" required value="" class="form-control" name="input_mobile" id="input-1" >
								</div>

								<div class="form-group">
									<label for="input-1">Password</label>
									<input type="Password" required value="" class="form-control" name="input_password" id="input-1" >
								</div>


								<div class="form-group">
									<button type="button" onclick="saveuser()" class="btn btn-primary shadow-primary px-5">Save</button>
								</div>

							</form>
						</div>
					</div>


				</div></div></div>
			</div>

			<?php
			$this->load->view('admin/comman/footerpage');
			?>
			<script type="text/javascript">

				function saveuser(){

					$("#dvloader").show();
					var formData = new FormData($("#user_form")[0]);
					$.ajax({
						type:'POST',
						url:'<?php echo base_url(); ?>index.php/admin/saveuser',
						data:formData,
						cache:false,
						contentType: false,
						processData: false,
						dataType: "json",
						success:function(resp){
							$("#dvloader").hide();
							if(resp.status=='200'){
								document.getElementById("user_form").reset();
								toastr.success(resp.msg);
								// window.location.replace('<?php echo base_url(); ?>index.php/admin/userlist');
							}else{
								toastr.error(resp.msg);
							}
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) {
							$("#dvloader").hide();
							toastr.error(errorThrown.msg,'failed');         
						}
					});
				}
			</script>