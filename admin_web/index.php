
<?php
include 'database/connection.php';

error_reporting(0);
session_start();
if(strlen($_SESSION['Babel'])==0)
	{
header('location:login.php');
}
else{ 

include('header.php');

?>


    <!-- Page Heading -->
    <h1 class="h3 mb-4 text-gray-800">Dashboard</h1>

    <!-- Content Row -->
    <div class="row row-cols-5">
        
        <div class="col mb-4">
            <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"> <?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM student where status=0")));?></div>
                        
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                Students</div></div>
                        <div class="col-auto">
                            <i class="fas fa-user fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Earnings (Monthly) Card Example -->
        <div class="col mb-4">
            <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM official where role='Patron' and status=0")))?></div>
                        
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Patrons</div></div>
                        <div class="col-auto">
                            <i class="fas fa-certificate fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col mb-4">
            <div class="card border-left-danger shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM official where role!='Patron' and status=0"))) ?></div>
                        
                            <div class="text-xs font-weight-bold text-danger text-uppercase mb-1">
                                Officials</div></div>
                        <div class="col-auto">
                            <i class="fas fa-users fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col mb-4">
            <div class="card border-left-secondary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM lec where status=0"))) ?></div>
                        
                            <div class="text-xs font-weight-bold text-secondary text-uppercase mb-1">
                               Lecturer</div></div>
                        <div class="col-auto">
                            <i class="fas fa-chalkboard-teacher fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<?php
include('footer.php');
}
?>