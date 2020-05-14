
<?php $setn=array(); 
foreach($settinglist as $set){
 $setn[$set->key]=$set->value;
}
?> 
<!-- Start wrapper-->
<div id="wrapper">

  <!--Start sidebar-wrapper-->
  <div id="sidebar-wrapper" data-simplebar="" data-simplebar-auto-hide="true">
   <div class="brand-logo">
    <a href="<?php echo base_url();?>index.php/admin">
     <img src="<?php echo base_url().'assets/images/app/'.$setn['app_logo']; ?>" class="logo-icon" >
     <h5 class="logo-text"><?php echo $setn['app_name'];?></h5>
   </a>
 </div>

 <ul class="sidebar-menu do-nicescrol">

  <li>
    <a href="<?php echo base_url();?>index.php/admin/dashboard" class="waves-effect">
      <i class="icon-home"></i><span>Dashboard</span><i class="fa fa-angle-right pull-right"></i>
    </a>
  </li>

  <li>
    <a href="<?php echo base_url();?>index.php/admin/categorylist" class="waves-effect">
      <i class="icon-pie-chart"></i><span>Category</span>
      <i class="fa fa-angle-right float-right"></i>
    </a>
    <ul class="sidebar-submenu">
      <li><a href="<?php echo base_url();?>index.php/admin/addcategory"><i class="fa fa-long-arrow-right"></i> Add Category</a></li>
      <li><a href="<?php echo base_url();?>index.php/admin/categorylist"><i class="fa fa-long-arrow-right"></i> List Category</a></li>

    </ul>
  </li>

  <li>
    <a href="<?php echo base_url();?>index.php/admin/userlist" class="waves-effect">
      <i class="fa fa-user"></i><span>User</span>
      <i class="fa fa-angle-right float-right"></i>
    </a>
    <ul class="sidebar-submenu">
       <li><a href="<?php echo base_url();?>index.php/admin/adduser"><i class="fa fa-long-arrow-right"></i> Add User</a></li>
      <li><a href="<?php echo base_url();?>index.php/admin/userlist"><i class="fa fa-long-arrow-right"></i> List User</a></li>
    </ul>
  </li> 

  <li>
    <a href="<?php echo base_url();?>index.php/admin/authorlist" class="waves-effect">
      <i class="fa fa-edit"></i><span>Author</span>
      <i class="fa fa-angle-right float-right"></i>
    </a>
    <ul class="sidebar-submenu">
      <li><a href="<?php echo base_url();?>index.php/admin/addauthor"><i class="fa fa-long-arrow-right"></i> Add Author </a></li>
      <li><a href="<?php echo base_url();?>index.php/admin/authorlist"><i class="fa fa-long-arrow-right"></i> Author List</a></li>
    </ul>
  </li>

  <li>
    <a href="<?php echo base_url();?>index.php/admin/booklist" class="waves-effect">
      <i class="fa fa-book"></i><span>Books</span>
      <i class="fa fa-angle-right float-right"></i>
    </a>
    <ul class="sidebar-submenu">
      <li><a href="<?php echo base_url();?>index.php/admin/addbook"><i class="fa fa-long-arrow-right"></i> Add Book </a></li>
      <li><a href="<?php echo base_url();?>index.php/admin/booklist"><i class="fa fa-long-arrow-right"></i> Books List</a></li>
    </ul>
  </li>

  <li>
    <a href="<?php echo base_url();?>index.php/admin/notification" class="waves-effect">
      <i class="fa fa-bell"></i><span>Notification</span>
    </a>
  </li>
  <li>
    <a href="<?php echo base_url();?>index.php/admin/settingpage" class="waves-effect">
      <i class="fa fa-cogs"></i><span>Setting</span>
    </a>
  </li>

  <li>
    <a href="<?php echo base_url();?>index.php/admin/logout" class="waves-effect">
      <i class="icon-power mr-2"></i><span>Logout</span>
    </a>
  </li>
</ul>

</div>
<!--End sidebar-wrapper-->
