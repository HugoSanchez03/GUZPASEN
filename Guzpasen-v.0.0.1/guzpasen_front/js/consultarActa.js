document.addEventListener("DOMContentLoaded", () => {
  const consultarBtn = document.getElementById("consultar");
  const idInput = document.getElementById("id-tutoria");
  const actaTextarea = document.getElementById("contenido-acta");

  consultarBtn.addEventListener("click", async () => {
    const idTutoria = idInput.value.trim();
    if (!idTutoria) {
      alert("Introduce un ID de tutoría válido.");
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/guzpasen/tutoria/${idTutoria}/acta`);
      if (!response.ok) {
        throw new Error("No se encontró acta para esta tutoría.");
      }

      const acta = await response.json();
      const contenido = `Puntos tratados:\n${acta.puntosTratados}\n\nObservaciones:\n${acta.observaciones}\n\nFecha: ${acta.fecha}`;
      actaTextarea.value = contenido;
    } catch (error) {
      console.error("Error al obtener el acta:", error);
      actaTextarea.value = "Acta no disponible o error en la consulta.";
    }
  });

  document.getElementById("cancelar").addEventListener("click", () => {
    document.getElementById("form-acta").reset();
    actaTextarea.value = "";
  });
});
