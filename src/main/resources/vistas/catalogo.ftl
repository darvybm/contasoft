<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catalogos de Cuenta</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="/css/menu.css">
    <script src="https://kit.fontawesome.com/41bcea2ae3.js" crossorigin="anonymous"></script>

    <style>
        .modal-dialog {
            display: flex;
            align-items: center;
            min-height: calc(100% - 3.5rem);
        }
        .modal-content {
            margin: auto;
        }
    </style>
</head>

<body id="body">

<header>
    <div class="icon__menu">
        <i class="fas fa-bars" id="btn_open"></i>
    </div>
    <h2>Catalogo de Cuentas</h2>
</header>

<div class="decorationR" style="background-image:linear-gradient(rgb(46,82,73),rgb(77,143,119)); margin-top: 0rem; height: 100px">
</div>

<div class="menu__side" id="menu_side">
    <div class="name__page">
        <i class="fas fa-building" title="Company"></i>
        <h4>Jayaos Por ahi SRL</h4>
    </div>
    <div class="options__menu">
        <a href="/librodiario?cicloContable=${cicloContable.uuid}">
            <div class="option">
                <i class="fas fa-book" title="Libro Diario"></i>
                <h4>Libro Diario</h4>
            </div>
        </a>
        <a href="/libromayor?cicloContable=${cicloContable.uuid}">
            <div class="option">
                <i class="fas fa-book-open" title="Libro Mayor"></i>
                <h4>Libro Mayor</h4>
            </div>
        </a>
        <a href="/balanza?cicloContable=${cicloContable.uuid}">
            <div class="option">
                <i class="fas fa-balance-scale" title="Balanza de Comprobación"></i>
                <h4>Balanza de Comprobacion</h4>
            </div>
        </a>
        <a href="/estados?cicloContable=${cicloContable.uuid}">
            <div class="option">
                <i class="fas fa-chart-bar" title="Estados Financieros"></i>
                <h4>Estados Financieros</h4>
            </div>
        </a>
        <a href="/catalogo?cicloContable=${cicloContable.uuid}" class="selected">
            <div class="option">
                <i class="fas fa-th-list" title="Catálogo de Cuentas"></i>
                <h4>Catalogo de Cuentas</h4>
            </div>
        </a>
        <a data-toggle="modal" data-target="#confiModal">
            <div class="option">
                <i class="fas fa-gear" title="Configuracion"></i>
                <h4>Configuracion</h4>
            </div>
        </a>
        <a href="/home">
            <div class="option">
                <i class="fas fa-sign-out-alt" title="Salir"></i>
                <h4>Salir</h4>
            </div>
        </a>
    </div>
</div>

<div class="modal fade" id="confiModal" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title mx-auto" style="color: #2e5249;">Actualizar Informacion del Ciclo Contable</h4>
            </div>
            <div class="modal-body">
                <form action="/home/modificar/${cicloContable.uuid}" method="post">
                    <input type="hidden" value="${company.uuid}" name="company">
                    <div class="mb-3">
                        <label for="titulo" class="form-label">titulo</label>
                        <input required type="text" class="form-control" id="titulo" name="titulo">
                    </div>
                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripcion</label>
                        <input type="text" class="form-control" id="descripcion" name="descripcion">
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

<main>
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <#list datos as cuentas>
                <div class="col">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <table class="table">
                                <h2 class="text-center" style="color: #2e5249;">${cuentas[0].tipo}</h2>
                                <tbody>
                                <#list cuentas as cuenta>
                                    <tr>
                                        <td>${cuenta.codigo}</td>
                                        <td>${cuenta.nombre}</td>
                                    </tr>
                                </#list>
                                    <tr>
                                        <td colspan="2">
                                            <#if cuentas[0].tipo == "CAPITAL">
                                                <button disabled type="button" class="btn" style="width: 100%; background-color: #3d7362; color: white;" data-toggle="modal" data-target="#myModal-${cuentas?index}">
                                                    Crear nueva cuenta
                                                </button>
                                            <#elseif cuentas[0].tipo == "RETIRO">
                                                <button disabled type="button" class="btn" style="width: 100%; background-color: #3d7362; color: white;" data-toggle="modal" data-target="#myModal-${cuentas?index}">
                                                    Crear nueva cuenta
                                                </button>
                                            <#else>
                                                <#if cicloContable.archivado == false>
                                                    <button type="button" class="btn" style="width: 100%; background-color: #3d7362; color: white;" data-toggle="modal" data-target="#myModal-${cuentas?index}">
                                                        Crear nueva cuenta
                                                    </button>
                                                <#else>
                                                    <button disabled type="button" class="btn" style="width: 100%; background-color: #3d7362; color: white;" data-toggle="modal" data-target="#myModal-${cuentas?index}">
                                                        Crear nueva cuenta
                                                    </button>
                                                </#if>
                                            </#if>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="modal fade" id="myModal-${cuentas?index}" data-backdrop="static">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header text-center">
                                <h4 class="modal-title mx-auto" style="color: #2e5249;">Nueva Cuenta: ${cuentas[0].tipo}</h4>
                            </div>
                            <div class="modal-body">
                                <form action="/catalogo/agregar?cicloContable=${cicloContable.uuid}" method="post">
                                    <input type="hidden" value="${cuentas[0].tipo}" name="tipo">
                                    <div class="mb-3">
                                        <label for="codigo" class="form-label">Codigo Referencia</label>
                                        <input required type="number" class="form-control" id="codigo" name="codigo" value="100">
                                    </div>
                                    <div class="mb-3">
                                        <label for="nombre" class="form-label">Nombre</label>
                                        <input required type="text" class="form-control" id="nombre" name="nombre">
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
            </#list>
        </div>
    </div>
</main>

<script src="/js/menu.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>