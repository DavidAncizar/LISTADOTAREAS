<%-- 
    Document   : style
    Created on : 18 oct 2023, 10:58:01
    Author     : usuario
--%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous"><!-- comment -->

<style>
    .divider:after,
    .divider:before {
        content: "";
        flex: 1;
        height: 1px;
        background: #eee;
    }
    .h-custom {
        height: calc(100% - 73px);
    }
    @media (max-width: 450px) {
        .h-custom {
            height: 100%;
        }
    }
    .presentacion
    {
        position: absolute;
        top: 50%;
        left: 50%;
        width: 75%;
        height: 75%;
        transform: translate(-50%, -50%);
        background-image: url('imagenesstyle/FONDOINDEX.jpg');
        background-position: center;
        background-size: cover;
        display: flex;
        margin-top: 10px;
        border: 1px solid black;
        border-radius: 10px;
        border: none;
    }

</style>
