document.addEventListener("DOMContentLoaded", () => {
  const consultarBtn = document.querySelector(".green-button");  // Botón Buscar
  const cancelarBtn = document.querySelector(".yellow-button");  // Botón Cancelar
  const idInput = document.getElementById("tutoria-id");
  const tutoriaTextarea = document.getElementById("contenido-tutoria");

  consultarBtn.addEventListener("click", async () => {
    const idTutoria = idInput.value.trim();
    if (!idTutoria) {
      alert("Introduce un ID de tutoría válido.");
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/guzpasen/tutoriaPorId/${idTutoria}`);
      if (!response.ok) {
        throw new Error("Tutoría no encontrada.");
      }

      const tutoria = await response.json();

      // Preparar texto para mostrar
      const contenido = 
`Motivo: ${tutoria.motivo}
Urgencia: ${tutoria.urgencia}
Asignatura: ${tutoria.asignatura}
Fecha: ${tutoria.fecha}
Estado: ${tutoria.estado}
Observaciones: ${tutoria.observaciones}
Alumno: ${tutoria.alumno?.nombre || 'N/A'}
Profesor: ${tutoria.usuario?.nombre || 'N/A'}
Acta ID: ${tutoria.acta?.idActa || 'N/A'}`;

      tutoriaTextarea.value = contenido;
    } catch (error) {
      console.error("Error al obtener la tutoría:", error);
      tutoriaTextarea.value = "Tutoría no disponible o error en la consulta.";
    }
  });

  cancelarBtn.addEventListener("click", () => {
    idInput.value = "";
    tutoriaTextarea.value = "";
  });
});
