jQuery.extend(jQuery.validator.messages, {
    required: "Campo requerido.",
    remote: "Corrija este valor.",
    email: "Capture un e-mail válido.",
    url: "Capture una URL válida.",
    date: "Capture una fecha válida.",
    dateISO: "Capture una fecha (ISO) válida.",
    number: "Capture un número.",
    digits: "Capture solo dígitos.",
    creditcard: "Capture un número válido de tarjeta.",
    equalTo: "Capture el mismo valor.",
    accept: "Capture un dato válido.",
    maxlength: jQuery.validator.format("No se permiten más de {0} caracteres."),
    minlength: jQuery.validator.format("No se permiten menos de {0} caracteres."),
    rangelength: jQuery.validator.format("Capture un texto entre {0} y {1} caracteres de longitud."),
    range: jQuery.validator.format("Capture un dato entre {0} y {1}."),
    max: jQuery.validator.format("Capture un valor menor o igual a {0}."),
    min: jQuery.validator.format("Capture un valor mayor o igual a {0}.")
});