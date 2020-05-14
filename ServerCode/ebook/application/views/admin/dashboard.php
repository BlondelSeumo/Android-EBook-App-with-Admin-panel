<?php
$this->load->view('admin/comman/header');
?>

<?php $setn=array(); 
foreach($settinglist as $set){
 $setn[$set->key]=$set->value;
}
?> 

<div class="clearfix"></div>

<div class="content-wrapper">
  <div class="container-fluid">

    <!--Start Dashboard Content-->
    <?php $vid=$wallpaper_down[0]; ?>
    <div class="row mt-4">
      <div class="col-12 col-lg-6 col-xl-3">
        <div class="card gradient-knight">
          <div class="card-body">
            <div class="media">
              <span class="text-white" style="font-size:30px;"><i class="fa fa-home"></i></span>
              <div class="media-body text-left" style="margin-left: 10px">
                <a href="<?php echo base_url();?>index.php/admin/booklist">
                  <h4 class="text-white"><?php echo $wallpaper;?></h4>
                  <h5 class="text-white">Total Books</<h5>
                </div>
                <div class="align-self-center"><span id="dash-chart-1"></span></div>
              </div>
            </div>
          </div>
        </div>

        <div class="col-12 col-lg-6 col-xl-3">
          <div class="card gradient-scooter">
            <div class="card-body">
              <div class="media">
                <span class="text-white" style="font-size:30px;"><i class="fa fa-book"></i></span>
               <div class="media-body text-left" style="margin-left: 10px">
                <a href="<?php echo base_url();?>index.php/admin/categorylist">
                  <h4 class="text-white"><?php echo $category;?></h4>
                  <h5 class="text-white">Category</h5>
                </div>
                <div class="align-self-center"><span id="dash-chart-3"></span></div>
              </div>
            </div>
          </div>
        </div>

        <div class="col-12 col-lg-6 col-xl-3">
          <div class="card gradient-blooker">
            <div class="card-body">
              <div class="media">
                <span class="text-white" style="font-size:30px;"><i class="fa fa-download"></i></span>
                <div class="media-body text-left" style="margin-left: 10px">
                  <a href="<?php echo base_url();?>index.php/admin/booklist">
                    <h4 class="text-white"><?php echo $vid->downcount;?></h4>
                    <h5 class="text-white">Download Book</h5>
                  </div>
                  <div class="align-self-center"><span id="dash-chart-1"></span></div>
                </div>
              </div>
            </div>
          </div>

        </div><!--End Row-->

        <?php $vid=$book_view[0]; ?>
        <div class="row mt-4">

         <div class="col-12 col-lg-6 col-xl-3">
          <div class="card gradient-quepal">
            <div class="card-body">
              <div class="media">
                 <span class="text-white" style="font-size:30px;"><i class="fa fa-user"></i></span>
                <div class="media-body text-left" style="margin-left: 10px">
                  <a href="<?php echo base_url();?>index.php/admin/userlist">
                    <h4 class="text-white"><?php echo $register_user;?></h4>
                    <h5 class="text-white">Active User</h5>
                  </div>
                  <div class="align-self-center"><span id="dash-chart-3"></span></div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-12 col-lg-6 col-xl-3">
            <div class="card gradient-meridian">
              <div class="card-body">
                <div class="media">
                  <span class="text-white" style="font-size:30px;"><i class="fa fa-list-alt"></i></span>
                  <div class="media-body text-left" style="margin-left: 10px">
                    <a href="<?php echo base_url();?>index.php/admin/booklist">
                      <h4 class="text-white"><?php echo $vid->readcount;?></h4>
                      <h5 class="text-white">Book Read Count</h5>
                    </div>
                    <div class="align-self-center"><span id="dash-chart-1"></span></div>
                  </div>
                </div>
              </div>
            </div>

            <?php $vid=$book_earn[0]; ?>
            <div class="col-12 col-lg-6 col-xl-3">
              <div class="card gradient-redmist">
                <div class="card-body">
                  <div class="media">
                    <span class="text-white" style="font-size:30px;"><i class="fa fa-money"></i></span>
                    <div class="media-body text-left" style="margin-left: 10px">
                      <a href="<?php echo base_url();?>index.php/admin/booklist">
                        <h4 class="text-white"><?php echo $vid->earncount;?></h4>
                        <h5 class="text-white">Book Sell Income</h5>
                      </div>
                      <div class="align-self-center"><span id="dash-chart-3"></span></div>
                    </div>
                  </div>
                </div>
              </div>
            </div><!--End Row-->

            <!--End Dashboard Content-->

          </div>
          <!-- End container-fluid-->

        </div><!--End content-wrapper-->
        <?php
        $this->load->view('admin/comman/footerpage');
        ?>

