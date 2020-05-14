<?php
$this->load->view('admin/comman/header');
?>

<div class="clearfix"></div>

<div class="content-wrapper">
	<div class="container-fluid">
		<!-- Breadcrumb-->
		<div class="row pt-2 pb-2">
			<div class="col-sm-9">
				<h4 class="page-title">Add Book</h4>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="javaScript:void();">Dashboard</a></li>
					<li class="breadcrumb-item"><a href="javaScript:void();">Books</a></li>
					<li class="breadcrumb-item active" aria-current="page">Add Book</li>
				</ol>
			</div>
			<div class="col-sm-3">
				<div class="btn-group float-sm-right">
					<a href="<?php echo base_url();?>index.php/admin/booklist" class="btn btn-outline-primary waves-effect waves-light">Books List</a>
				</div>
			</div>
		</div>
		<!-- End Breadcrumb-->
		<div class="row">
			<div class="col-lg-10 mx-auto">
				<div class="card">
					<div class="card-body">
						<div class="card-title">Add Book
							<form id="edit_video_form"  enctype="multipart/form-data">

								<div class="form-group">
									<label for="input-1">Book Name</label>
									<input type="text" required value="" class="form-control" name="input_name" id="input-1" >
								</div>

								<input type="hidden" name="id" value="">

								<div class="form-group"> 
									<label for="input-3">Book Cost</label>
									<select name="select_cost" required  class="form-control" id="purpose">
										<option value="0">Free</option>
										<option value="1">Paid</option>
									</select>
								</div>

								<div class="form-group" id="business" style="display:none">
									<label for="input-1">Book price</label>
									<input type="text" required value="" class="form-control"  name="input_price" id="input-1" placeholder="Enter Book Price">
								</div>

								<div class="form-group">
									<label for="input-2">Book Category</label>
									<!-- DropDown -->
									<select name="select_category" required  class="form-control">
										<option value="">Select Category</option>
										<?php $i=1;foreach($categorylist as $cat){ ?>
											<option required value="<?php echo $cat->cat_id; ?>"><?php echo $cat->cat_name; ?></option>            
											<?php $i++;} ?>
										</select>
									</div>

									<div class="form-group">
										<label for="input-2">Book Author</label>
										<!-- DropDown -->
										<select name="select_author" required  class="form-control">
											<option value="">Select Author</option>
											<?php $i=1;foreach($authorlist as $cat){ ?>
												<option value="<?php echo $cat->a_id; ?>"><?php echo $cat->a_title; ?></option>            
												<?php $i++;} ?>
											</select>
										</div>

										<div class="form-group">
											<label for="input-1"> Book Cover Poster</label>
											<input type="file" required  class="form-control" name="input_bookcover" id="input-1" placeholder="select Book Cover image" onchange="readURL(this,'showImage')">
											<input type="hidden" name="input_bookcover" value="">
											<p class="noteMsg">Note: Image Size must be less than 2MB.Image Height and Width less than 1000px.</p>
											<img id="showImage" src="<?php echo base_url().'assets/images/placeholder.png';?>" height="100" width="100" alt="your image" />
										</div>


										<div class="form-group">
											<label for="input-1"> Upload Sample Book</label>
											<input type="file" required  class="form-control" name="input_sample_book" id="input-1" placeholder="select Sample Book">
											<input type="hidden" name="input_sample_book" value="">
										</div>

										<div class="form-group">
											<label for="input-1"> Upload Full Book</label>
											<input type="file" required  class="form-control" name="input_full_book" id="input-1" placeholder="select Full Book">
											<input type="hidden" name="input_full_book" value="">
										</div>

										<div class="form-group">
											<label for="input-1">Book Description</label>
											<textarea cols="40" rows="5" style="height: 150px" type="text" required value="" class="form-control" name="input_description" id="input-1" ></textarea>
										</div>

										<div class="form-group">
											<button type="button" onclick="savebook()" class="btn btn-primary shadow-primary px-5">Save</button>
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

						$('#purpose').on('change', function () {
							if (this.value === '1'){
								$("#business").show();
							} else {
								$("#business").hide();
							}
						});

						function savebook(){

							var wallpaper_title=jQuery('input[name=wallpaper_title]').val();
							if(wallpaper_title==''){
								toastr.error('Please enter Book Name','failed');
								return false;
							}
							$("#dvloader").show();
							var formData = new FormData($("#edit_video_form")[0]);
							$.ajax({
								type:'POST',
								url:'<?php echo base_url(); ?>index.php/admin/savebook',
								data:formData,
								cache:false,
								contentType: false,
								processData: false,
								dataType: "json",
								success:function(resp){
									$("#dvloader").hide();
									if(resp.status=='200'){
										document.getElementById("edit_video_form").reset();
										toastr.success(resp.msg,'success');
										setTimeout(function(){ location.reload(); }, 500);
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