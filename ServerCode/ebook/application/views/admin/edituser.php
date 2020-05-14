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
         <li class="breadcrumb-item"><a href="javascript:void(0)">Dashboard</a></li>
         <li class="breadcrumb-item"><a href="javascript:void(0)">Users</a></li>
         <li class="breadcrumb-item active"><a href="javascript:void(0)">Update User</a></li>
       </ol>
     </div>
     <div class="col-sm-3">
      <div class="btn-group float-sm-right">
        <a href="<?php echo base_url();?>index.php/admin/userlist" class="btn btn-outline-primary waves-effect waves-light">User List</a>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="container-fluid">
      <div class="row">
       <div class="col-lg-10 mx-auto">
        <div class="card">
         <div class="card-body">
          <div class="card-title">Update User</div>
          <hr>
          <form id="user_form"  enctype="multipart/form-data">
           <div class="form-group">
            <label for="input-1">FullName</label>
            <input type="text" required value="<?php echo $userlist[0]->fullname?>" name="user_name" class="form-control" id="name" placeholder="Enter Name">
          </div>

          <input type="hidden" name="id" value="<?PHP echo $userlist[0]->id; ?>">

          <div class="form-group">
            <label for="input-1">Email</label>
            <input type="text" required value="<?php echo $userlist[0]->email?>" name="user_email" class="form-control" id="name" placeholder="Enter Email">
          </div>

          <div class="form-group">
            <label for="input-1">Passoword</label>
            <input type="password" required value="<?php echo $userlist[0]->password?>" name="user_password" class="form-control" id="name" placeholder="Enter Password">
          </div>

          <div class="form-group">
            <label for="input-1">Mobile Number</label>
            <input type="text" required value="<?php echo $userlist[0]->mobile_number?>" name="user_number" class="form-control" id="name" placeholder="Enter Mobile Number">
          </div>

          <div class="form-group">
            <button type="button" onclick="updateuser()" class="btn btn-primary shadow-primary px-5">Save</button>
          </div>
        </form>
      </div>
    </div>
  </div></div>
</div></div></div>
</div>

<?php
$this->load->view('admin/comman/footerpage');
?>
<script type="text/javascript">

  function updateuser(){

    $("#dvloader").show();

    var formData = new FormData($("#user_form")[0]);
    $.ajax({
      type:'POST',
      url:'<?php echo base_url(); ?>index.php/admin/updateuser',
      data:formData,
      cache:false,
      contentType: false,
      processData: false,
      dataType: "json",
      success:function(resp){
        if(resp.status=='200'){
          $("#dvloader").hide();
          document.getElementById("user_form").reset();
          toastr.success(resp.msg,'success');                 
          setTimeout(function(){ 
                      window.location.replace('<?php echo base_url(); ?>index.php/admin/userlist');
                    }, 500);
        }else{
          $("#dvloader").hide();
          toastr.error(resp.msg);
        }
      }
    });

  }

</script>