<?php
$this->load->view('admin/comman/header');
?>

<div class="clearfix"></div>

<div class="content-wrapper">
	<div class="container-fluid">
		<!-- Breadcrumb-->
		<div class="row pt-2 pb-2">
			<div class="col-sm-9">
				<h4 class="page-title">Notification</h4>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="javaScript:void();">Dashboard</a></li>
					<li class="breadcrumb-item"><a href="javaScript:void();">Notification</a></li>
					<li class="breadcrumb-item active" aria-current="page">Send Notification</li>
				</ol>
			</div>
			
		</div>
		<!-- End Breadcrumb-->
		
		<div class="row">
			<div class="col-lg-10 mx-auto">
				<div class="card">
					<div class="card-body">
						<div class="card-title">Send Notification</div>
						<hr>
						<form id="savenotifi"  enctype="multipart/form-data">
							<div class="form-group">
								<label for="input-1">Title</label>
								<input type="text" required  class="form-control" required="" name="title" id="input-1" placeholder="Enter title">
							</div>
							
							<div class="form-group">
								<label for="input-2">Message</label>
								<textarea name="message"  class="form-control"></textarea>
							</div>
							
							
							<div class="form-group">
								<label for="input-1">Image (Optional)</label>
								<input type="file" required  class="form-control" name="video_thumbnail" id="input-1" placeholder="Enter Video Name">
								<p class="noteMsg">Note: Image Size Minimum - 512x256 & Maximum - 2880x1440</p>
							</div>

							<div class="form-group">
								<button type="button" onclick="saveNotification()" class="btn btn-primary shadow-primary px-5"> Save</button>
							</div>
						</form>
					</div>
				</div>
				

			</div></div></div>
		</div>
		<?php
		$this->load->view('admin/comman/footerpage');
		?>
		<script>
			function saveNotification(){
				$("#dvloader").show();
				var formData = new FormData($("#savenotifi")[0]);
				$.ajax({
					type:'POST',
					url:'<?php echo base_url(); ?>index.php/admin/savenotification',
					data:formData,
					dataType: "json",
					cache:false,
					contentType: false,
					processData: false,
					success:function(resp){
							document.getElementById("savenotifi").reset();
							$("#dvloader").hide();
							toastr.success('Notification send successfully.');   	   
							setTimeout(function(){ location.reload(); }, 500);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
					     $("#dvloader").hide();
					     alert("".textStatus);
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