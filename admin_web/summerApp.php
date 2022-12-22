<?php
include 'database/connection.php';
error_reporting(0);

include('header.php');

?>

    <!-- Page Heading -->
    <h1 class="h3 mb-4 text-gray-800">Application Interface</h1>

    <!-- Content Row -->
    <div class="row row-cols-5">
        
        <div class="col mb-4">
        <a href="cours" style="text-decoration:none"><div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"> <?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM course")));?></div>
                        
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                            <b><strong>Courses</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>

        <div class="col mb-4">
        <a href="sess" style="text-decoration:none"><div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM session")))?></div>
                        
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                            <b><strong>Sessions</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="anno" style="text-decoration:none"><div class="card border-left-danger shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM announce"))) ?></div>
                        
                            <div class="text-xs font-weight-bold text-danger text-uppercase mb-1">
                            <b><strong>Announcements</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="card" style="text-decoration:none"><div class="card border-left-secondary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM cardy"))) ?></div>
                        
                            <div class="text-xs font-weight-bold text-secondary text-uppercase mb-1">
                            <b><strong>cards</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        
        <div class="col mb-4">
        <a href="event" style="text-decoration:none"><div class="card border-left-danger shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM event"))) ?></div>
                        
                            <div class="text-xs font-weight-bold text-danger text-uppercase mb-1">
                            <b><strong>Events</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="rpt" style="text-decoration:none"><div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"> <?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM report")));?></div>
                        
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                            <b><strong>Session_Reports</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="clubs" style="text-decoration:none"><div class="card border-left-secondary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM club"))) ?></div>
                        
                            <div class="text-xs font-weight-bold text-secondary text-uppercase mb-1">
                            <b><strong>clubs</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="ecpe" style="text-decoration:none"><div class="card border-left-danger shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM mpesa"))) ?></div>
                        
                            <div class="text-xs font-weight-bold text-danger text-uppercase mb-1">
                                <b><strong>Expenses</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="part" style="text-decoration:none"><div class="card border-left-secondary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM summer"))) ?></div>
                        
                            <div class="text-xs font-weight-bold text-secondary text-uppercase mb-1">
                            <b><strong>Participation</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="contri" style="text-decoration:none"><div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM contribution")))?></div>
                        
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                            <b><strong>Event_Payments</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="payment" style="text-decoration:none"><div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"> <?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM payment")));?></div>
                        
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                            <b><strong>Card_Payments</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="fresh" style="text-decoration:none"><div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM cardy_one where deter='F'")))?></div>
                        
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                            <b><strong>Fresh_Applications</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="renew" style="text-decoration:none"><div class="card border-left-danger shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM cardy_one where deter='R'")))?></div>
                        
                            <div class="text-xs font-weight-bold text-danger text-uppercase mb-1">
                                <b><strong>Renewal_Applications</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="upgraded" style="text-decoration:none"><div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM cardy_one where deter='U'")))?></div>
                        
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                            <b><strong>Upgrade_Applications</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="issued" style="text-decoration:none"><div class="card border-left-secondary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM issued where expiry=0"))) ?></div>
                        
                            <div class="text-xs font-weight-bold text-secondary text-uppercase mb-1">
                            <b><strong>Issued_Cards</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="expired" style="text-decoration:none"><div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"> <?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM expired")));?></div>
                        
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                            <b><strong>EXPIRED_CARDS</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="cash" style="text-decoration:none"><div class="card border-left-danger shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM account"))) ?>
                        </div>
                        
                            <div class="text-xs font-weight-bold text-danger text-uppercase mb-1">
                                <b><strong>Cash_Flow</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="eventTra" style="text-decoration:none"><div class="card border-left-secondary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"><?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM mover"))) ?></div>
                        
                            <div class="text-xs font-weight-bold text-secondary text-uppercase mb-1">
                            <b><strong>Event_Track</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
        <div class="col mb-4">
        <a href="justPose" style="text-decoration:none"><div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="h5 mb-0 font-weight-bold text-gray-800"> <?php echo htmlentities(mysqli_num_rows(mysqli_query($con,"SELECT* FROM contri")));?></div>
                        
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                            <b><strong>Contributions</strong></b></div></div>
                        <div class="col-auto">
                            <i class="fas fa-globe fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div></a>
        </div>
    </div>
<?php
include('footer.php');
?>