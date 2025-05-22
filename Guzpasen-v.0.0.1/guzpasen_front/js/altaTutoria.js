document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("form-tutoria");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

const tutoriaData = {
    motivo: document.getElementById("motivo").value,
    urgencia: document.getElementById("urgencia").value,
    asignatura: document.getElementById("asignatura").value,
    alumno: {
        dni: document.getElementById("alumno-dni").value
    },
    usuario: {
        idUsuario: parseInt(document.getElementById("id-usuario").value)
    },
    observaciones: document.getElementById("observaciones").value,
    fecha: new Date().toISOString().split("T")[0],
    estado: "PENDIENTE",
    acta: null
};



        try {
            const response = await fetch("http://localhost:8080/guzpasen/anadirTutoria", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(tutoriaData)
            });

            if (response.ok) {
                alert("Tutoría registrada correctamente.");
                form.reset();
            } else {
                alert("Error al registrar la tutoría.");
            }
        } catch (error) {
            console.error("Error al enviar la solicitud:", error);
            alert("Fallo de conexión con el servidor.");
        }
    });

    document.getElementById("cancelar-btn").addEventListener("click", () => {
        if (confirm("¿Deseas cancelar? Se borrarán los campos.")) {
            form.reset();
        }
    });
});
