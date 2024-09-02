<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Estilos personalizados -->
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Ubuntu:ital@1&display=swap');
        .carousel-item  {
            height: 15rem;
            width: 100%;
            position: relative;
        }
        .carousel-item::before {
            content: '';
            display: block;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(30, 52, 46, 0.90);
        }
        .img {
            object-fit: cover;
            height: 100%;
            width: 100%;
        }
        .carousel-caption {
            text-align: center;
            opacity: 80%;
        }
        .logo {
            width: 15rem;
            margin: 1rem 0;
        }
        .barraTitulo {
            background-color: #fff;
            padding: 1rem;
            margin: 2rem 0;
            box-shadow: 0 0 20px rgba(46, 77, 69, 0.8);
        }
        .barraTitulo h1 {
            color: #2e5249;
            font-size: 2rem;
            font-family: sans-serif;
            font-weight: bold;
            text-align: left;
            margin-left: 2rem;
        }
        .card-container {
            margin: 1rem;
            max-width: 50rem;
            box-shadow: 5px 5px 10px #2e5249;
        }
        .card-left {
            background-image: linear-gradient(to bottom, #2e5249, #3d7362);
            max-width: 8rem;
        }
        .card-body .btn {
            width: 10rem;
            background-color: #43806c;
            color: white;
        }
        .add-card {
            color: #2e5249;
        }
        .add-card:hover {
            transform: scale(1.05);
            background-color: #2e5249;
            color: white;
            cursor: pointer;
        }

        .card-title {
            height: 45px;
            overflow: hidden;
        }
        .card-text-p {
            height: 60px;
            overflow: hidden;
        }
        .modal-dialog {
            display: flex;
            align-items: center;
            min-height: calc(100% - 3.5rem);
        }
        .modal-content {
            margin: auto;
        }
        .btn {
            background-color: #43806c;
            color: white;
            width: 100%
        }
    </style>
</head>
<body>
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="https://ubjonline.b-cdn.net/wp-content/uploads/2017/08/contabilidad-una-aproximacion-conceptual.jpg" alt="..." class="img">
            <div class="carousel-caption d-none d-md-block">
                <img class="logo mb-2" src="/img/contaSoftLogo-white.png" alt="Logo ContaSoft">
            </div>
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

<div class="barraTitulo position-relative">
    <div style="display: flex; align-items: center">
        <#if archivado>
            <h1>> Ciclos Contables Archivados</h1>
        <#else>
            <h1>> Ciclos Contables</h1>
        </#if>
        <h2 style="margin-left: 1rem; margin-right: 1rem; overflow: hidden; width: 30rem; font-size: 1.5rem">(${company.name})</h2>
        <a href="/home/archivado" class="btn btn-block py-2 position-absolute top-50 end-0 translate-middle-y" style="margin-right: 5rem; width: 50px">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-archive" viewBox="0 0 16 16">
                <path d="M0 2a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1v7.5a2.5 2.5 0 0 1-2.5 2.5h-9A2.5 2.5 0 0 1 1 12.5V5a1 1 0 0 1-1-1V2zm2 3v7.5A1.5 1.5 0 0 0 3.5 14h9a1.5 1.5 0 0 0 1.5-1.5V5H2zm13-3H1v2h14V2zM5 7.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z"/>
            </svg>
        </a>
        <a href="/login" class="btn btn-block py-2 position-absolute top-50 end-0 translate-middle-y" style="margin-right: 1rem; width: 50px">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-left" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M6 12.5a.5.5 0 0 0 .5.5h8a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-8a.5.5 0 0 0-.5.5v2a.5.5 0 0 1-1 0v-2A1.5 1.5 0 0 1 6.5 2h8A1.5 1.5 0 0 1 16 3.5v9a1.5 1.5 0 0 1-1.5 1.5h-8A1.5 1.5 0 0 1 5 12.5v-2a.5.5 0 0 1 1 0v2z"/>
                <path fill-rule="evenodd" d="M.146 8.354a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L1.707 7.5H10.5a.5.5 0 0 1 0 1H1.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3z"/>
            </svg>
        </a>
    </div>
</div>

<div class="container-fluid">
    <div class="row row-cols-1 row-cols-md-2 row-cols-xl-3">
        <#list company.ciclosContable as cicloContable>
            <#if cicloContable.archivado == archivado>
                <div class="col">
                    <div class="card card-container">
                        <div class="row">
                            <div class="card-left col">
                                <div class="text-center pt-4">
                                    <i class="fa fa-book fa-4x text-white" style="color: #ffffff;"></i>
                                </div>
                            </div>
                            <div class="col">
                                <div class="card-body">
                                    <h5 class="card-title">${cicloContable.titulo}</h5>
                                    <p>${company.name}</p>
                                    <p class="card-text card-text-p">${cicloContable.descripcion}</p>
                                    <a href="/librodiario?cicloContable=${cicloContable.uuid}" class="btn py-2 m-1" style="width: 120px">Abrir</a>
                                    <a href="/home/archivar/${cicloContable.uuid}" class="btn py-2 m-1" style="width: 120px">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-archive" viewBox="0 0 16 16">
                                            <path d="M0 2a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1v7.5a2.5 2.5 0 0 1-2.5 2.5h-9A2.5 2.5 0 0 1 1 12.5V5a1 1 0 0 1-1-1V2zm2 3v7.5A1.5 1.5 0 0 0 3.5 14h9a1.5 1.5 0 0 0 1.5-1.5V5H2zm13-3H1v2h14V2zM5 7.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z"/>
                                        </svg>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#if>
        </#list>
        <#if !archivado>
            <div class="col">
                <div class="card card-container add-card text-center">
                    <div style="height: 250px"  class="card-body" data-toggle="modal" data-target="#newLibro">
                        <i class="fa fa-plus fa-3x position-absolute top-50 start-50 translate-middle"></i>
                    </div>
                </div>
            </div>
        </#if>

        <div class="modal fade" id="newLibro" data-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h4 class="modal-title mx-auto" style="color: #2e5249;">Nuevo Libro</h4>
                    </div>
                    <div class="modal-body">
                        <form action="/home/agregar" method="post">
                            <input type="hidden" value="${company.uuid}" name="codigo">
                            <div class="mb-3">
                                <label for="titulo" class="form-label">Titulo</label>
                                <input required type="text" class="form-control" id="titulo"  name="titulo" required>
                            </div>
                            <div class="mb-3">
                                <label for="descripcion" class="form-label">Descripcion</label>
                                <input type="text" class="form-control" id="descripcion"  name="descripcion">
                            </div>
                            <div class="btn-group" style="width: 100%">
                                <button type="submit" class="btn" style="background-color: #3d7362; color: white;">Registrar</button>
                                <button type="button" class="btn btn-outline" data-dismiss="modal" style="background-color: #c74b4b; color: white;">Cancelar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Scripts para la funcionalidad de la vista -->
<script src="/js/menu.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>