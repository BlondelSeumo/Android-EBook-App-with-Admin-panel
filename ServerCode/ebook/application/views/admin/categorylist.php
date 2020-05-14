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
		    <h4 class="page-title">Category List</h4>
		    <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="javaScript:void();">Dashboard</a></li>
            <li class="breadcrumb-item"><a href="javaScript:void();">Category</a></li>
            <li class="breadcrumb-item active" aria-current="page">Category List</li>
         </ol>
	   </div>
     <div class="col-sm-3">
       <div class="btn-group float-sm-right">
           <a href="<?php echo base_url();?>index.php/admin/addcategory" class="btn btn-outline-primary waves-effect waves-light">Add Category</a>
      </div>
     </div>
     </div>
    <!-- End Breadcrumb-->
      <div class="row">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-header"><i class="fa fa-table"></i> Category Record</div>
            <div class="card-body">
              <div class="table-responsive">
              <table id="default-datatable" class="table table-bordered">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Created date</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                     <?php $i=1;foreach($categorydata as $cat){ ?>
                    <tr>
                        <td><?php echo $i++; ?></td>
                        <td><img src="<?php echo base_url().'assets/images/category/'.$cat->cat_image; ?>" height="100px;" width="100px;"></td>
                        <td><?php echo $cat->cat_name; ?></td>
                        <td><?php echo $cat->cat_date; ?></td>
                        <td><a href="javaScript:void(0)"  onclick="status_change('<?php echo $cat->cat_id; ?>','category')" id="category<?php echo $cat->cat_id; ?>"><?php echo $cat->cat_status; ?></a>
                        <input type="hidden" name="status_change" id="status_change_category<?php echo $cat->cat_id; ?>" value="<?php echo $cat->cat_status; ?>">
                      </td>
                      <td><a href="<?php echo base_url()?>index.php/admin/editCategory?id=<?php echo $cat->cat_id; ?>">Edit</a> | <a href="javaScript:void(0)" onclick="delete_record('<?php echo $cat->cat_id; ?>','category')">Delete</a></td>
                    </tr>
                <?php $i++;} ?>
 
                </tbody>
                <tfoot>
                    <tr>
                        <th>Id</th>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Created date</th>
                        <th>Status</th>
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
