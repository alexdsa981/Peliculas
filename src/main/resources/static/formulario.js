let boton = document.getElementById("btnRegistrar");
boton.addEventListener("click", evento => {
    evento.preventDefault();
    registrarPelicula();
});

let registrarPelicula = async () => {

    let campos = {};
    campos.titulo = document.getElementById("titulo").value;
    campos.director = document.getElementById("director").value;
    campos.genero = document.getElementById("genero").value;

    const peticion = await fetch(
        "http://localhost:8080/api/peliculas",
        {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(campos)
        });

    if (peticion.ok) {
        const respuesta = await peticion.json();
        console.log("Película registrada:", respuesta);
    } else {
        console.error("Error al registrar la película:", peticion.statusText);
    }
}