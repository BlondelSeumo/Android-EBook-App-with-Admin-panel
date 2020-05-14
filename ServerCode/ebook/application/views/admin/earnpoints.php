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
					<li class="breadcrumb-item active" aria-current="page">Earn Points</li>
				</ol>
			</div>
			<div class="col-sm-3">
				<div class="btn-group float-sm-right">
					<!--   <a href="<?php echo base_url();?>index.php/admin/addcategory" class="btn btn-outline-primary waves-effect waves-light"> Userlist</a> -->
				</div>
			</div>
		</div>
		<!-- End Breadcrumb-->
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-header"><i class="fa fa-table"></i>User Earn Points</div>
					<div class="card-body">
						<div class="table-responsive">
							<table id="default-datatable" class="table table-bordered">
								<thead>
									<tr>

										<th>Id</th>
										<th>FullName</th>
										<th>Earn Points</th>
										<!-- <th>Email</th>
										<th>Mobile Number</th>
										<th>Created Date</th> -->

									</tr>
								</thead>
								<tbody>
									<?php  $i=1;foreach($userlist as $use){ ?>
										<tr>
											<td><?php echo $i; ?></td>
											<td><?php echo $use->fullname; ?></td>
											<td><?php echo $use->points; ?></td>
											<!-- <td><?php echo "xxxxxxxxxx" ?></td>
											<td><?php echo "xxxxxxxxxx" ?></td>
											<td><?php echo $use->c_date; ?></td> -->
										</tr>
										<?php $i++;} ?>

									</tbody>
									<tfoot>

										<tr>
											<th>Id</th>
											<th>FullName</th>
											<th>Earn Points</th>
											<!-- <th>Email</th>
											<th>Mobile Number</th>
											<th>Created Date</th> -->
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
      //Default data table
      $('#default-datatable').DataTable();
  } );

</script>
