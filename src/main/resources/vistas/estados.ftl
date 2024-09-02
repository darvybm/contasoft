<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Estados Financieros</title>

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
    <h2>Estados Financieros</h2>
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
        <a href="/estados?cicloContable=${cicloContable.uuid}" class="selected">
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
        <a href="/home?cicloContable=${cicloContable.uuid}">
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
        <div class="row row-cols-1 row-cols-sm-1 row-cols-md-1 g-3">
            <div class="col">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <table class="table table-bordered">
                            <h2 class="text-center" style="color: #2e5249;">Estado de Resultados</h2>
                            <tbody>
                            <tr>
                                <td class="text-head" colspan="4"><b>Ingresos</b></td>
                            </tr>
                            <#list cuentasT as cuentaT>
                                <#if cuentaT.cuenta.tipo == "INGRESO">
                                    <!-- INGRESOS -->
                                    <tr>
                                        <td style="width: 10%;" class="text-center">${cuentaT.cuenta.codigo}</td>
                                        <td style="width: 40%;">${cuentaT.cuenta.nombre}</td>
                                        <td style="width: 25%;"></td>
                                        <td style="width: 25%;" class="text-end">${cuentaT.saldo}</td>
                                    </tr>
                                </#if>
                            </#list>

                            <tr>
                                <td style="width: 10%;" class="text-center"><b>Total</b></td>
                                <td style="width: 40%;"></td>
                                <td style="width: 25%;"></td>
                                <td style="width: 25%;" class="text-end"><b>${totalIngreso}</b></td>
                            </tr>

                            <#list cuentasT as cuentaT>
                                <#if cuentaT.cuenta.tipo == "GASTO">
                                    <!-- GASTOS -->
                                    <tr>
                                        <td class="text-center">${cuentaT.cuenta.codigo}</td>
                                        <td>${cuentaT.cuenta.nombre}</td>
                                        <td class="text-end">${cuentaT.saldo}</td>
                                        <td></td>
                                    </tr>
                                </#if>
                            </#list>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2"><b>Total Gastos</b></td>
                                    <td></td>
                                    <td class="text-end"><B>${totalGasto}</B></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><b>Utilidad Neta</b></td>
                                    <td></td>
                                    <td class="text-end">${utilidadNeta}</td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <table class="table table-bordered">
                            <h2 class="text-center" style="color: #2e5249;">Estado de Cambios en el Capital</h2>
                            <tbody>
                            <tr>
                                <td class="text-head" colspan="2"><b>Capital Contable al FECHA</b></td>
                                <td style="width: 25%;"></td>
                                <td style="width: 25%;" class="text-end">-</td>
                            </tr>
                            <!-- CAPITAL y RETIROS -->
                            <tr>
                                <td style="width: 10%;" class="text-center">311</td>
                                <td style="width: 40%;">Inversion aporte</td>
                                <td style="width: 25%;" class="text-end">${inversion}</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td class="text-center">312</td>
                                <td>Retiros</td>
                                <td class="text-end">${retiros}</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>Utilidad Neta</td>
                                <td class="text-end">${utilidadNeta}</td>
                                <td></td>
                            </tr>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="2"><b>Aumento en el Capital</b></td>
                                <td></td>
                                <td class="text-end">${aumentoCapital}</td>
                            </tr>
                            <tr>
                                <td colspan="2"><b>Capital Contable al FECHA</b></td>
                                <td></td>
                                <td class="text-end">${aumentoCapital}</td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <table class="table table-bordered">
                            <h2 class="text-center" style="color: #2e5249;">Estado de Situacion</h2>
                            <tbody>
                            <tr>
                                <td class="text-head" colspan="4"><b>Activo</b></td>
                            </tr>
                            <!-- ACTIVOS -->
                            <#list cuentasT as cuentaT>
                                <#if cuentaT.cuenta.tipo == "ACTIVO">
                                    <tr>
                                        <td style="width: 10%;" class="text-center">${cuentaT.cuenta.codigo}</td>
                                        <td style="width: 40%;">${cuentaT.cuenta.nombre}</td>
                                        <td style="width: 25%;" class="text-end">${cuentaT.saldo}</td>
                                        <td style="width: 25%;"></td>
                                    </tr>
                                </#if>
                            </#list>

                            <tr>
                                <td class="text-head" colspan="2"><b>Total Activo</b></td>
                                <td></td>
                                <td  class="text-end">${totalActivos}</td>
                            </tr>

                            <tr>
                                <td class="text-head" colspan="4"><b>Pasivo</b></td>
                            </tr>
                            <!-- PASIVOS -->
                            <#list cuentasT as cuentaT>
                                <#if cuentaT.cuenta.tipo == "PASIVO">
                                    <tr>
                                        <td style="width: 10%;" class="text-center">${cuentaT.cuenta.codigo}</td>
                                        <td style="width: 40%;">${cuentaT.cuenta.nombre}</td>
                                        <td style="width: 25%;" class="text-end">${cuentaT.saldo}</td>
                                        <td style="width: 25%;"></td>
                                    </tr>
                                </#if>
                            </#list>
                            <tr>
                                <td class="text-head" colspan="2"><b>Total Pasivo</b></td>
                                <td></td>
                                <td  class="text-end">${totalPasivos}</td>
                            </tr>
                            <tr>
                                <td class="text-head" colspan="4"><b>Capital Contable</b></td>
                            </tr>
                            <!-- CAPITAL -->
                            <tr>
                                <td class="text-center">311</td>
                                <td>Capital</td>
                                <td class="text-end">${aumentoCapital}</td>
                                <td></td>
                            </tr>

                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="2"><b>Total Pasivo y Capital Contable</b></td>
                                <td></td>
                                <td class="text-end">${totalPasivoCapital}</td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <table class="table table-bordered">
                            <h2 class="text-center" style="color: #2e5249;">Estado de Flujo de Efectivo</h2>
                            <!-- AQUI VA TODO LO QUE SE MANEJA CON EFECTIVO, ASIENTOS CON EFECTIVOS-->
                            <tbody>
                            <tr>
                                <td class="text-head" colspan="3"><b>Flujo de las Actividades Operativas</b></td>
                            </tr>
                            <tr>
                                <td style="width: 50%;">Efectivo recibido de los clientes</td>
                                <td style="width: 25%;" class="text-end">${ingresoEFE}</td>
                                <td style="width: 25%;"></td>
                            </tr>
                            <tr>
                                <td>Pagos a suplidores y empleados</td>
                                <td class="text-end">${pagosEFE}</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Diversos gastos</td>
                                <td class="text-end">${gastosEFE}</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td class="text-head"><b>Flujo Neto de las Actividades Operativas</b></td>
                                <td></td>
                                <td class="text-end">${ingresoEFE + pagosEFE + gastosEFE}</td>
                            </tr>

                            <tr>
                                <td class="text-head" colspan="3"><b>Flujo de las Actividades de Inversion</b></td>
                            </tr>
                            <tr>
                                <td>Venta de propiedades y equipos</td>
                                <td class="text-end">${inversionEFE}</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td class="text-head"><b>Flujo Neto de las Actividades de Inverseion</b></td>
                                <td></td>
                                <td class="text-end">${inversionEFE}</td>
                            </tr>

                            <tr>
                                <td class="text-head" colspan="3"><b>Flujo de las Actividades de Financiamiento</b></td>
                            </tr>
                            <tr>
                                <td>Inversion del Propietario</td>
                                <td class="text-end">${capitalEFE}</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Prestamos</td>
                                <td class="text-end">${prestamosEFE}</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Retiro del propietario</td>
                                <td class="text-end">${retiroEFE}</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td class="text-head"><b>Flujo Neto de las Actividades de Financiamiento</b></td>
                                <td></td>
                                <td class="text-end">${capitalEFE + prestamosEFE + retiroEFE}</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<script src="/js/menu.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>