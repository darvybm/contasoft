<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Login/Register</title>

	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

	<!-- Estilos personalizados -->
	<style>
		body {
			background-image:linear-gradient(rgba(46, 82, 73, 0.95) 0%, rgba(46, 82, 73, 0.95)), url(https://www.teknei.com/wp-content/uploads/2021/07/automatizacion-de-procesos-contables.jpg);
			object-fit: cover;
			background-repeat: repeat;
		}
		.form {
			background-color: #ffffff;
			border-radius: 10px;
			padding: 20px;
			max-width: 60%;
			margin: 5rem auto;
			box-shadow: 10px 0 60px rgba(0,0,0,0.6);
		}
		.form-signin {
			border-radius: 10px; /* Ajusta el valor según tus preferencias */
			border: 1px solid #2e5249; /* Borde sólido con un color gris claro */
			padding: 1rem;
			margin-bottom: 2rem;
		}
		.form-signup {

		}
		.logo {
			width: 8rem;
		}
		.btn {
			background-color: #2e5249;
			color: white;
			width: 100%
		}
	</style>
</head>
<body>
<div class="form">
	<img class = "logo mb-2" src="/img/contaSoftLogo.png" alt="Logo ContaSoft">
	<p>Sistema web basico de contabilidad con fines educativos. <b>Darvy Betances y Marcos Blanco</b></p>
	<div class="row">
		<div class="col-md-6">
			<form class="form-signin" method="post" action="/login/signin">
				<div class="text-center mb-4">
					<h1 class="h3 mb-3 font-weight-normal">Inicio de sesion</h1>
				</div>
				<div class="form-group mb-2">
					<label for="signinInputEmail">Email</label>
					<input type="email" id="signinInputEmail" class="form-control" name="email" required autofocus placeholder="You-mail@gmail.com">
				</div>
				<div class="form-group mb-4">
					<label for="signinInputPassword">Password</label>
					<input type="password" id="signinInputPassword" class="form-control" name="password" required placeholder="123456789">
				</div>
				<button class="btn btn-block py-2" type="submit">Iniciar sesion</button>
			</form>
			<p style="color: #ff7274">
				<#if mensaje??>
					${mensaje}
				</#if>
			</p>
		</div>
		<div class="col-md-6">
			<form class="form-signup" method="post" action="/login/signup">
				<div class="text-center mb-4">
					<h1 class="h3 mb-3 font-weight-normal">Registro</h1>
				</div>
				<div class="form-group mb-2">
					<label for="signupCompanyName">Company Name</label>
					<input type="text" id="signupCompanyName" class="form-control" name="name" required autofocus placeholder="Hipoteca Surprise SRL...">
				</div>
				<div class="form-group mb-2">
					<label for="signupInputEmail">Email</label>
					<input type="email" id="signupInputEmail" class="form-control" name="email" required autofocus placeholder="You-mail@gmail.com">
				</div>
				<div class="form-group mb-4">
					<label for="signupInputPassword">Password</label>
					<input type="password" id="signupInputPassword" class="form-control" name="password" required placeholder="123456789">
				</div>
				<button class="btn py-2" type="submit">Registrarse</button>
			</form>
			<p style="color: #ff7274">
				<#if mensajeR??>
					${mensajeR}
				</#if>
			</p>
		</div>
	</div>
</div>
</body>
</html>
