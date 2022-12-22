<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>KeMUSSiT Admin Panel</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <!--<link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">-->

    <!-- Custom styles for this template-->
   <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <!-- <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="vendor/parsley/parsley.css"/>

    <link rel="stylesheet" type="text/css" href="vendor/bootstrap-select/bootstrap-select.min.css"/>

    <link rel="stylesheet" type="text/css" href="vendor/datepicker/bootstrap-datepicker.css"/>-->

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav sidebar sidebar-dark accordion" id="accordionSidebar" style="background-color: #950365;color:white">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index">
                <div class="sidebar-brand-icon rotate-n-15">
                    
                </div>
                <i class="fas fa-circle" style="color: #fff;"></i>
                <div class="sidebar-brand-text mx-3">KeMUSSIT</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - index -->
            <?php
            if(strlen($_SESSION['Babel'])!=0)
            {
            ?>
            <li class="nav-item">
                <a class="nav-link" href="index">
                    <i class="fas fa-tachometer-alt" style="color: #fff;"></i>
                    <span style="color: #fff;"><b>Dashboard</b></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="stud">
                    <i class="fas fa-user-md" style="color: #fff;"></i>
                    <span style="color: #fff;"><b>Students</b></span></a>
            </li>
            
            <li class="nav-item">
                <a class="nav-link" href="pat">
                    <i class="fas fa-certificate" style="color: #fff;"></i>
                    <span style="color: #fff;"><b>Patrons</b></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="off">
                    <i class="fas fa-users" style="color: #fff;"></i>
                    <span style="color: #fff;"><b>Officials</b></span></a>
            </li>
            <?php
            }
            ?>
            <li class="nav-item">
                <a class="nav-link" href="lec">
                    <i class="fas fa-chalkboard-teacher" style="color: #fff;"></i>
                    <span style="color: #fff;"><b>Lecturer</b></span></a>
            </li>
            
            <li class="nav-item">
                <a class="nav-link" href="summerApp">
                    <i class="fas fa-globe" style="color: #fff;"></i>
                    <span style="color: #fff;"><b>Mobile</b></span></a>
            </li>
            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle" style="color: #fff;"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <?php
                        $user_name = '';
                        $user_profile_image = '';

                        if(strlen($_SESSION['Babel'])!=0)
                        {
                                $profile = mysqli_fetch_assoc(mysqli_query($con, 'select * from admin where username="' . $_SESSION['Babel'] . '"'));
                            
                                $user_name = $profile['username'];
                                $user_profile_image = 'img/undraw_profile.svg';
                        }
                        ?>
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small" id="user_profile_name"><?php echo $user_name; ?></span>
                                <img class="img-profile rounded-circle"
                                    src="<?php echo $user_profile_image; ?>" id="user_profile_image">
                            </a>
                            <!-- Dropdown - User Information -->
                            <?php
                            if(strlen($_SESSION['Babel'])!=0)
                            {
                            ?>
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                            <?php
                            }
                            ?>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">