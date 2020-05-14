<?php
$this->load->view('admin/comman/header');
?>

<div class="clearfix"></div>

<div class="content-wrapper">
	<div class="container-fluid">
		<!-- Breadcrumb-->
		<div class="row pt-2 pb-2">
			<div class="col-sm-9">
				<h4 class="page-title">Add Category</h4>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="javaScript:void();">Dashboard</a></li>
					<li class="breadcrumb-item"><a href="javaScript:void();">Category</a></li>
					<li class="breadcrumb-item active" aria-current="page">Update Category</li>
				</ol>
			</div>
			<div class="col-sm-3">
				<div class="btn-group float-sm-right">
					<a href="<?php echo base_url();?>index.php/admin/categorylist" class="btn btn-outline-primary waves-effect waves-light">Category List</a>
				</div>
			</div>
		</div>
		<!-- End Breadcrumb-->
		<?php // print_r($category);?>
		<div class="row">
			<div class="col-lg-10 mx-auto">
				<div class="card">
					<div class="card-body">
						<div class="card-title">Update Category</div>
						<hr>
						<form id="edit_category_form"  enctype="multipart/form-data">
							<div class="form-group">
								<label for="input-1">Category Name</label>
								<input type="text" name="category_name" class="form-control" id="input-1" placeholder="Enter Your Category Name" value="<?php echo $category[0]->cat_name?>">
							</div>
							<input type="hidden" name="id" value="<?PHP echo $category[0]->cat_id; ?>">

							<div class="form-group">
								<label for="input-2">Category Image</label>
								<input type="file" name="category_image" class="form-control" id="input-2" onchange="readURL(this,'showImage')">
								<input type="hidden" name="categoryimage" value="<?php echo $category[0]->cat_image; ?>">
								<p class="noteMsg">Note: Image Size must be lessthan 2MB.Image Height and Width less than 1000px.</p>
								<div><img id="showImage" src="<?php echo base_url().'assets/images/category/'.$category[0]->cat_image; ?>" height="100px;" width="100px;"></div>
							</div>
							<div class="form-group">
								<button type="button" onclick="updateCategory()" class="btn btn-primary shadow-primary px-5">Update</button>
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
			function updateCategory(){
				var category_name=jQuery('input[name=category_name]').val();
				if(category_name==''){
					toastr.error('Please enter category name','failed');
					return false;
				}
				$("#dvloader").show();

				var formData = new FormData($("#edit_category_form")[0]);
				$.ajax({
					type:'POST',
					url:'<?php echo base_url(); ?>index.php/admin/updatecategory',
					data:formData,
					cache:false,
					contentType: false,
					processData: false,
					dataType: 'json', 
					success:function(resp){
						$("#dvloader").hide();
						if(resp.status=='200'){
							document.getElementById("edit_category_form").reset();
							toastr.success(resp.msg);
							window.location.replace('<?php echo base_url(); ?>index.php/admin/categorylist');
						}else{
							toastr.error(resp.msg);
						}
					}
				});
			}
		</script>