<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+Knujsl7/1L_dstPt3HV5HzF6Gvk/e9T9hXmJ58bldgTk+" crossorigin="anonymous">
    <title>Home Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .bg-image {
            background-image: url('static/background.jpg');
            background-size: cover;
            background-position: center;
            height: 100vh;
        }

        .bg-text {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            text-align: center;
        }

        .btn-light {
            color: #000;
            background-color: #fff;
            border-color: #fff;
        }

        .btn-light:hover {
            color: #fff;
            background-color: #000;
            border-color: #000;
        }

        .card {
            margin: 10px;
            width: 300px;
            height: 200px;
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            transition: 0.3s;
            background-color: #fff;
            color: #000;
        }

        .card:hover {
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
        }

        .card-title {
            margin-top: 30px;
            font-size: 24px;
            font-weight: bold;
        }

        .card-text {
            margin-top: 20px;
            font-size: 18px;
        }

        .card-link {
            margin-top: 30px;
            font-size: 18px;
            font-weight: bold;
            text-decoration: none;
            color: #000;
        }

        .card-link:hover {
            color: #007bff;
        }

        .logout-link {
            margin-top: 30px;
            font-size: 18px;
            font-weight: bold;
            text-decoration: none;
            color: #000;
            display: block;
            text-align: center;
        }

        .logout-link:hover {
            color: #007bff;
        }
    </style>
</head>
<body>
<div class="container-fluid p-0">
    <div class="bg-image"></div>
    <div class="bg-text">
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <h1 class="text-white text-center">Welcome to the Home Page!</h1>
                    <% Boolean isAdmin = (Boolean) session.getAttribute("is_admin"); %>
                    <% if (isAdmin != null && isAdmin) { %>
                    <p class="text-white text-center">This is your application's main page after a successful login.</p>
                    <% } else { %>
                    <div class="d-flex justify-content-center">
                        <div class="card">
                            <div class="card-body">
                                <h2 class="card-title">Company</h2>
                                <p class="card-text">View company information.</p>
                                <a href="company" class="card-link">Go to Company</a>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-body">
                                <h2 class="card-title">Flights</h2>
                                <p class="card-text">View flight information.</p>
                                <a href="flight" class="card-link">Go to Flights</a>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-body">
                                <h2 class="card-title">Tickets</h2>
                                <p class="card-text">View ticket information.</p>
                                <a href="ticket" class="card-link">Go to Tickets</a>
                            </div>
                        </div>
                    </div>
                    <a href="logout" class="logout-link">Logout</a>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gybBudMz5l/6en8XCp+HHAAK5GSLf2xlYtvJ8U2Q4U+9cuEnJoa3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>