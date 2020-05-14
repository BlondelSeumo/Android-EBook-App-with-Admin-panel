<?php
$this->load->view('admin/comman/header');
?>

<link href="<?php echo base_url();?>/assets/plugins/bootstrap-datatable/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">
<link href="<?php echo base_url();?>/assets/plugins/bootstrap-datatable/css/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css">

<!-- UserList Data Show -->
<div class="clearfix"></div>

<div class="content-wrapper">
	<div class="container-fluid">
		<!-- Breadcrumb-->
		<div class="row pt-2 pb-2">
			<div class="col-sm-9">
				<h4 class="page-title">User List</h4>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="javaScript:void();">Dashboard</a></li>
					<li class="breadcrumb-item"><a href="javaScript:void();">User</a></li>
					<li class="breadcrumb-item active" aria-current="page">User List</li>
				</ol>
			</div>
			<div class="col-sm-3">
				<div class="btn-group float-sm-right">
					<a href="<?php echo base_url();?>index.php/admin/adduser" class="btn btn-outline-primary waves-effect waves-light">Add User</a>
				</div>
			</div>
		</div>
		<!-- End Breadcrumb-->
		<div class="row">
			<div class="col-lg-12">

			<!-- 	<div class="col-3">
					<?php  $i=1;foreach($userlist as $use){ ?>
					<div class="card">
						<div class="card-body">
							<div class="text-center">
								<img alt="" class="rounded-circle mt-4" src="<?php echo base_url().'assets/images/author/avatar-18.png'?>">
								<h4 class="card-widget__title text-dark mt-3"><?php echo $use->fullname; ?></h4>
								<a class="btn gradient-bloody text-white" href="javascript:void()">Folllow</a>
							</div>
						</div>
						<div class="card-footer border-0 bg-transparent">
							<div class="row">
								<div class="col-6 ">
									<a class="text-center d-block text-muted" href="javascript:void()">
										<i class="fa fa-mobile" aria-hidden="true" style="font-size:25px;"></i>
										<p class="text-muted" style="font-size:15px;"><?php echo $use->mobile_number; ?></p>
									</a>
								</div>
								
							<div class="col-6 "><a class="text-center d-block text-muted" href="javascript:void()">
								<i class="fa fa-envelope" style="font-size:25px;"></i>
								<p class="text-muted" style="font-size:15px;"><?php echo $use->email; ?></p>
							</a>
						</div>
					</div>
				</div>
			</div>
			<?php $i++;} ?>
		</div> -->

		<div class="card">
			<div class="card-header"><i class="fa fa-table"></i>Register User List</div>
			<div class="card-body">
				<div class="table-responsive">
					<table id="default-datatable" class="table table-bordered">
						<thead>
							<tr>
								<th>Id</th>
								<th>FullName</th>
								<th>Email</th>
								<th>Mobile Number</th>
								<th>Created Date</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<?php  $i=1;foreach($userlist as $use){ ?>
								<tr>
									<td><?php echo $i;?></td>
									<td><?php echo $use->fullname; ?></td>
									<td><?php echo $use->email; ?></td>
									<td><?php echo $use->mobile_number; ?></td>
									<td><?php echo $use->c_date; ?></td>
									<td><a href="javaScript:void(0)"  onclick="status_change('<?php echo $use->id; ?>','user')" id="user<?php echo $use->id; ?>"><?php echo $use->status; ?></a>
										<input type="hidden" name="status_change" id="status_change_user<?php echo $use->id; ?>" value="<?php echo $use->status; ?>">
									</td>
								</tr>
								<?php $i++;} ?>

							</tbody>
							<tfoot>
								<tr>
									<th>Id</th>
									<th>FullName</th>
									<th>Email</th>
									<th>Mobile Number</th>
									<th>Created Date</th>
									<th>Status</th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>

	</div><!-- End Row-->


	<?php
	$this->load->view('admin/comman/footerpage');
	?>

	<script>
		$(document).ready(function() {
			$('#default-datatable').DataTable();
		} );
	</script>
