<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Libro Diario</title>

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
    <h2>Libro Diario</h2>
</header>

<div class="decorationR" style="background-image:linear-gradient(rgb(46,82,73),rgb(77,143,119)); margin-top: 0rem; height: 100px">
</div>

<div class="menu__side" id="menu_side">
    <div class="name__page">
        <i class="fas fa-building" title="Company"></i>
        <h4>Jayaos Por ahi SRL</h4>
    </div>
    <div class="options__menu">
        <a href="/librodiario?cicloContable=${cicloContable.uuid}" class="selected">
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
        <a href="/catalogo?cicloContable=${cicloContable.uuid}">
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
                        <input type="text" class="form-control" id="titulo" name="titulo">
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
        <div class="row row-cols-1 row-cols-sm-1 row-cols-md-1 g-3">
            <div class="col">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <table class="table table-bordered">
                            <thead class="text-center">
                            <tr>
                                <td style="width: 14%">Fecha</td>
                                <td style="width: 38%">Descripcion</td>
                                <td style="width: 14%">Ref.</td>
                                <td style="width: 14%">Debito</td>
                                <td style="width: 14%">Credito</td>
                                <td style="width: 3%"></td>
                                <td style="width: 3%"></td>
                            </tr>
                            </thead>
                            <tbody>
                            <#if datosTemporales??>
                                <#list datosTemporales as transaccion>
                                    <tr>
                                        <td></td>
                                        <#if transaccion.tipo == "D">
                                            <td>${transaccion.cuenta.nombre}</td>
                                        <#else>
                                            <td class="text-end">${transaccion.cuenta.nombre}</td>
                                        </#if>
                                        <td class="text-center">${transaccion.cuenta.codigo}</td>
                                        <#if transaccion.tipo == "D">
                                            <td class="text-end">${transaccion.monto}</td>
                                            <td></td>
                                        <#else>
                                            <td></td>
                                            <td class="text-end">${transaccion.monto}</td>
                                        </#if>
                                        <td>
                                            <a data-toggle="modal" data-target="#editTrans-${transaccion?index}">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                                </svg>
                                            </a>
                                        </td>
                                        <td>
                                            <a href="/librodiario/eliminar/${transaccion.uuid}?cicloContable=${cicloContable.uuid}" style="color: inherit; text-decoration: none;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-square" viewBox="0 0 16 16">
                                                    <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
                                                    <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                                </svg>
                                            </a>
                                        </td>
                                    </tr>
                                    <div class="modal fade" id="editTrans-${transaccion?index}" data-backdrop="static">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header text-center">
                                                    <h4 class="modal-title mx-auto" style="color: #2e5249;">Editar Transaccion
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
                                                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                                            <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                                        </svg>
                                                    </h4>
                                                </div>
                                                <div class="modal-body">
                                                    <form action="/librodiario/actualizar?cicloContable=${cicloContable.uuid}" method="post">
                                                        <input type="hidden" value="${transaccion.uuid}" name="codigo">
                                                        <div class="form-group mb-3">
                                                            <label for="cuenta">Cuenta</label>
                                                            <select class="form-control" id="cuenta" name="cuenta">
                                                                <#list cuentas as cuenta>
                                                                    <#if cuenta.nombre == transaccion.cuenta.nombre>
                                                                        <option selected value="${cuenta.codigo}">${cuenta.nombre}</option>
                                                                    <#else>
                                                                        <option value="${cuenta.codigo}">${cuenta.nombre}</option>
                                                                    </#if>
                                                                </#list>
                                                            </select>
                                                        </div>
                                                        <div class="form-group mb-3">
                                                            <#if transaccion.tipo == "D">
                                                                <div class="form-check">
                                                                    <input checked class="form-check-input" type="radio" name="tipo" id="debito" value="D">
                                                                    <label class="form-check-label" for="debito">Debitar</label>
                                                                </div>
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="radio" name="tipo" id="credito" value="C">
                                                                    <label class="form-check-label" for="credito">Acreditar</label>
                                                                </div>
                                                            <#else>
                                                                <div class="form-check">
                                                                    <input class="form-check-input" type="radio" name="tipo" id="tipo" value="D">
                                                                    <label class="form-check-label" for="debito">Debitar</label>
                                                                </div>
                                                                <div class="form-check">
                                                                    <input checked class="form-check-input" type="radio" name="tipo" id="tipo" value="C">
                                                                    <label class="form-check-label" for="credito">Acreditar</label>
                                                                </div>
                                                            </#if>
                                                        </div>
                                                        <label for="monto" class="form-label">Monto</label>
                                                        <div class="input-group mb-3">
                                                            <input required type="number" step="0.01" class="form-control" id="monto" name="monto" value="${transaccion.monto}">
                                                        </div>
                                                        <div class="btn-group" style="width: 100%">
                                                            <button type="submit" class="btn" style="background-color: #3d7362; color: white;">Confirmar</button>
                                                            <button type="button" class="btn btn-outline" data-dismiss="modal" style="background-color: #c74b4b; color: white;">Cancelar</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            </#if>
                            <tr>
                                <td colspan="7">
                                    <#if cicloContable.archivado == false>
                                        <button  type="button" class="btn" style="width: 100%; background-color: #3d7362; color: white;" data-toggle="modal" data-target="#addTrans">
                                            Crear Transaccion
                                        </button>
                                    <#else>
                                        <button disabled type="button" class="btn" style="width: 100%; background-color: #3d7362; color: white;" data-toggle="modal" data-target="#addTrans">
                                            Crear Transaccion
                                        </button>
                                    </#if>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <table style="width: 100%">
                            <tr>
                                <td>
                                    <#assign montoD = 0>
                                    <#assign montoC = 0>
                                    <#if cicloContable.archivado == false>
                                        <#if datosTemporales??>
                                            <#list datosTemporales as transaccion>
                                                <#if transaccion.tipo == "D">
                                                    <#assign montoD = montoD + transaccion.monto>
                                                <#else>
                                                    <#assign montoC = montoC + transaccion.monto>
                                                </#if>
                                            </#list>
                                            <#if montoD == montoC && montoD != 0 && montoC != 0>
                                                <button type="button" class="btn" style="width: 100%; background-color: #224f72; color: white;" data-toggle="modal" data-target="#regAsi">
                                                    Registrar Asiento
                                                </button>
                                            <#else>
                                                <button disabled type="button" class="btn" style="width: 100%; background-color: #224f72; color: white;" data-toggle="modal" data-target="#regAsi">
                                                    Registrar Asiento
                                                </button>
                                            </#if>
                                        <#else>
                                            <button disabled type="button" class="btn" style="width: 100%; background-color: #224f72; color: white;" data-toggle="modal" data-target="#regAsi">
                                                Registrar Asiento
                                            </button>
                                        </#if>
                                    <#else>
                                        <button disabled type="button" class="btn" style="width: 100%; background-color: #224f72; color: white;" data-toggle="modal" data-target="#regAsi">
                                            Registrar Asiento
                                        </button>
                                    </#if>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="addTrans" data-backdrop="static">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header text-center">
                            <h4 class="modal-title mx-auto" style="color: #2e5249;">Nueva Transaccion
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
                                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                </svg>
                            </h4>
                        </div>
                        <div class="modal-body">
                            <form action="/librodiario/crear?cicloContable=${cicloContable.uuid}" method="post">
                                <div class="form-group mb-3">
                                    <label for="gender">Cuenta</label>
                                    <select class="form-control" id="cuenta" name="cuenta">
                                        <#list cuentas as cuenta>
                                            <option value="${cuenta.codigo}">${cuenta.nombre}</option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="form-group mb-3">
                                    <div class="form-check">
                                        <input checked required class="form-check-input" type="radio" name="tipo" id="debito" value="D">
                                        <label class="form-check-label" for="debito">Debitar</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="tipo" id="credito" value="C">
                                        <label class="form-check-label" for="credito">Acreditar</label>
                                    </div>
                                </div>
                                <label for="monto"  class="form-label">Monto</label>
                                <div class="input-group mb-3">
                                    <input required type="number" step="0.01" class="form-control" id="monto" name="monto" value="0">
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
            <div class="modal fade" id="regAsi" data-backdrop="static">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header text-center">
                            <h4 class="modal-title mx-auto" style="color: #2e5249;">Nuevo Asiento
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
                                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                </svg>
                            </h4>
                        </div>
                        <div class="modal-body">
                            <form action="/librodiario/publicar?cicloContable=${cicloContable.uuid}" method="post">
                                <div class="mb-3">
                                    <label for="fecha" class="form-label">Fecha</label>
                                    <input required type="date" class="form-control" id="fecha" name="fecha">
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
        </div>
        <h2 style="color: #2e5249; margin-top: 2rem; margin-bottom: 1rem;">Asientos</h2>
        <#list datos as asiento>
            <div class="col" style="margin-bottom: 0.5rem">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <table class="table table-bordered">
                            <tbody>
                            <#assign x = false>
                            <#list asiento.transacciones as transaccion>
                                <#if transaccion.tipo == "D">
                                    <tr>
                                        <#if x == false>
                                            <td style="width: 15%; vertical-align: middle;" class="text-center" rowspan="${asiento.getSize()}">${asiento.fecha}</td>
                                            <td style="width: 40%">${transaccion.cuenta.nombre}</td>
                                            <td style="width: 15%" class="text-center">${transaccion.cuenta.codigo}</td>
                                            <td style="width: 15%" class="text-end">${transaccion.monto}</td>
                                            <td style="width: 15%"></td>
                                            <#assign x = true>
                                        <#else>
                                            <td style="width: 40%">${transaccion.cuenta.nombre}</td>
                                            <td style="width: 15%" class="text-center">${transaccion.cuenta.codigo}</td>
                                            <td style="width: 15%" class="text-end">${transaccion.monto}</td>
                                            <td style="width: 15%"></td>
                                        </#if>
                                    </tr>
                                <#else>
                                    <tr>
                                        <td class="text-center">${transaccion.cuenta.nombre}</td>
                                        <td class="text-center">${transaccion.cuenta.codigo}</td>
                                        <td></td>
                                        <td class="text-end">${transaccion.monto}</td>
                                    </tr>
                                </#if>
                            </#list>
                            <tr>
                                <td></td>
                                <td class="text-center" colspan="2"><em>${asiento.descripcion}</em></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</main>

<script src="/js/menu.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>