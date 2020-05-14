<?php
$this->load->view('admin/comman/header');
?>

<link href="<?php echo base_url();?>assets/plugins/bootstrap-datatable/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">
<link href="<?php echo base_url();?>assets/plugins/bootstrap-datatable/css/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css">

<div class="clearfix"></div>

<div class="content-wrapper">
  <div class="container-fluid">
    <!-- Breadcrumb-->
    <div class="row pt-2 pb-2">
      <div class="col-sm-9">
        <h4 class="page-title">Books list</h4>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="javaScript:void();">Dashboard</a></li>
          <li class="breadcrumb-item"><a href="javaScript:void();">Books</a></li>
          <li class="breadcrumb-item active" aria-current="page">Books list</li>
        </ol>
      </div>
      <div class="col-sm-3">
       <div class="btn-group float-sm-right">
        <a href="<?php echo base_url();?>index.php/admin/addbook" class="btn btn-outline-primary waves-effect waves-light">Add Book</a>

      </div>
    </div>
  </div>
  <!-- End Breadcrumb-->
  <div class="row">
    <div class="col-lg-12">
      <div class="card">
        <div class="card-header"><i class="fa fa-table"></i> Books Record</div>
        <div class="card-body">
          <div class="table-responsive">
            <table id="default-datatable" class="table table-bordered">
             <thead>
              <tr>
                <th>Id</th>
                <th>Image</th>
                <th>Name</th>
                <th>Category Name</th>
                <th>Author Name</th>
                <th>view</th>
                <th>Download</th>
                <th>Price</th>
                <th>Status</th>
                <!-- <th>Created date</th> -->
                <th>Action</th>

              </tr>
            </thead>
            <tbody>
             <?php $i=1;foreach($bookslist as $vid){ ?>
              <tr>
                <td><?php echo $i; ?></td>
                <td><img src="<?php echo base_url().'assets/images/book/'.$vid->b_image; ?>" height="100px;" width="100px;"></td>
                <td><?php echo $vid->b_title; ?></td>
                <td> <?php echo $vid->cat_name; ?>
                <td> <?php echo $vid->a_title; ?>
                <td><?php echo $vid->readcnt; ?></td>
                <td><?php echo $vid->download; ?></td>
                <td><?php echo $vid->b_price; ?></td>
                <td><?php echo $vid->b_status; ?></td>
               <!--  <td><?php echo $vid->b_date; ?></td> -->
              <td><a href="<?php echo base_url()?>index.php/admin/editbook?b_id=<?php echo $vid->b_id; ?>">Edit</a> | <a href="javaScript:void(0)" onclick="delete_record('<?php echo $vid->b_id; ?>','book')">Delete</a></td>
            </tr>
            <?php $i++;} ?>

          </tbody>
          <tfoot>

            <tr>
              <th>Id</th>
              <th>Image</th>
              <th>Name</th>
              <th>Category Name</th>
              <th>Author Name</th>
              <th>view</th>
              <th>Download</th>
              <th>Price</th>
              <th>Status</th>
              <!-- <th>Created date</th> -->
              <th>Action</th>
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