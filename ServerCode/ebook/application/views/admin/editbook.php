<?php
$this->load->view('admin/comman/header');
?>



<div class="clearfix"></div>

<div class="content-wrapper">
	<div class="container-fluid">
		<!-- Breadcrumb-->
		<div class="row pt-2 pb-2">
			<div class="col-sm-9">
				<h4 class="page-title">Books Update</h4>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="javaScript:void();">Dashboard</a></li>
					<li class="breadcrumb-item"><a href="javaScript:void();">Books</a></li>
					<li class="breadcrumb-item active" aria-current="page">update Book</li>
				</ol>
			</div>
			<div class="col-sm-3">
				<div class="btn-group float-sm-right">
					<a href="<?php echo base_url();?>index.php/admin/booklist" class="btn btn-outline-primary waves-effect waves-light">Books List</a>
				</div>
			</div>
		</div>
		<!-- End Breadcrumb-->
		<?php $vid=$booklist[0]; ?>
		<div class="row">
			<div class="col-lg-10 mx-auto">
				<div class="card">
					<div class="card-body">
						<div class="card-title">Update Book</div>
						<hr>
						<form id="edit_video_form"  enctype="multipart/form-data">
							
							<div class="form-group">
								<label for="input-1">Book Name</label>
								<input type="text" required value="<?php echo $vid->b_title; ?>" class="form-control" name="input_name" id="input-1" placeholder="Enter Book Name">
							</div>

							<input type="hidden" name="id" value="<?PHP echo $vid->b_id; ?>">

							<div class="form-group">
								<label for="input-3">Book Cost</label>
								<select name="select_cost" required  class="form-control" id="purpose">
									<option value="0" <?php if ($vid->is_paid == "0" ) echo 'selected' ; ?>>Free</option>
									<option value="1" <?php if ($vid->is_paid == "1" ) echo 'selected' ; ?>>Paid</option>
								</select>
							</div>

							<div class="form-group" id="business" >
								<label for="input-1">Book Price</label>
								<input type="text" required value="<?php echo $vid->b_price; ?>" class="form-control" name="input_price" id="input-1" placeholder="Enter Book Price">
							</div>

							<div class="form-group">
								<label for="input-2">Book Category</label>

								<select name="select_category" required  class="form-control">
									<option value="">Select Category</option>
									<?php foreach($categorylist as $cat){ ?>
										<option value="<?php echo $cat->cat_id; ?>" <?php if($cat->cat_id==$vid->fcat_id ){ echo 'selected="selected"'; } ?> ><?php echo $cat->cat_name?></option>
									<?php } ?>
								</select>
							</div>

							<div class="form-group">
								<label for="input-2">Book Author</label>
								<!-- DropDown -->
								<select name="select_author" required  class="form-control">
									<option value="">Select Author</option>
									<?php $i=1;foreach($authorlist as $auth){ ?>
										<option value="<?php echo $auth->a_id; ?>" <?php if($auth->a_id==$vid->fa_id ){ echo 'selected="selected"'; } ?> ><?php echo $auth->a_title?></option>        
										<?php $i++;} ?>
									</select>
								</div>

								<div class="form-group">
									<label for="input-1"> Book Cover Poster</label>
									<input type="file" required  class="form-control" name="input_bookcover" id="input-1" value="<?PHP echo $vid->b_image; ?>" onchange="readURL(this,'showImage')">
									<input type="hidden" name="inputbookcover" value="<?PHP echo $vid->b_image; ?>">
									<p class="noteMsg">Note: Image Size must be less than 2MB.Image Height and Width less than 1000px.</p>
									<img id="showImage" src="<?php echo base_url().'assets/images/book/'.$vid->b_image; ?>" height="100" width="100"/>
								</div>


								<div class="form-group">
									<label for="input-1"> Upload Sample Book</label>
									<input type="file" required  class="form-control" name="input_sample_book" id="input-1" placeholder="select Sample Book" value="<?PHP echo $vid->sample_b_url; ?>">
									<input type="hidden" name="inputsamplebook" value="<?PHP echo $vid->sample_b_url; ?>">
									<div><label for="input-1" value="<?PHP echo $vid->sample_b_url; ?>">Sample Book :- <?PHP echo $vid->sample_b_url; ?></label></div>

								</div>

								<div class="form-group">
									<label for="input-1"> Upload Full Book</label>
									<input type="file" required  class="form-control" name="input_full_book" id="input-1" placeholder="select Full Book" value="<?PHP echo $vid->b_url; ?>">
									<input type="hidden" name="inputfullbook" value="<?PHP echo $vid->b_url; ?>">
									<div><label for="input-1" value="<?PHP echo $vid->b_url; ?>">Full Book :- <?PHP echo $vid->b_url; ?></label></div>
								</div>

								<div class="form-group">
									<label for="input-1">Book Description</label>
									<textarea cols="40" rows="5" style="height: 150px" type="text" required value="" class="form-control" name="input_description" id="input-1" ><?php echo $vid->b_description; ?></textarea>
								</div>

								<div class="form-group">
									<button type="button" onclick="update_book()" class="btn btn-primary shadow-primary px-5"> Update</button>
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

				function update_book(){
					
					$("#dvloader").show();

					var formData = new FormData($("#edit_video_form")[0]);
					$.ajax({
						type:'POST',
						url:'<?php echo base_url(); ?>index.php/admin/update_book',
						data:formData,
						cache:false,
						contentType: false,
						processData: false,
						dataType: "json",
						success:function(resp){
							if(resp.status=='200'){
								$("#dvloader").hide();
								document.getElementById("edit_video_form").reset();
								toastr.success(resp.msg,'success');                 
								setTimeout(function(){ location.reload(); }, 500);
							}else{
								$("#dvloader").hide();
								toastr.error(resp.msg);
							}


						}
					});

				}
				function checkVtype(server){
					if(server!='Server Video'){
						$('#videoLink').html('<label for="input-1">Video url</label><input type="text" required  class="form-control" name="video_url" id="input-1" placeholder="Enter Video url">');
					}else{
						$('#videoLink').html('<label for="input-1">Upload Video</label><input type="file" required  class="form-control" name="video_upload" id="input-1" placeholder="Enter Video Name">');
					}
				}
			</script>