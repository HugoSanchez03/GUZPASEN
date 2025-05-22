const form = document.getElementById("modificarTutoriaForm");
const idTutoriaInput = document.getElementById("idTutoria");
const motivoInput = document.getElementById("motivo");
const urgenciaSelect = document.getElementById("urgencia");
const asignaturaInput = document.getElementById("asignatura");
const alumnoInput = document.getElementById("alumno");
const profesoresInput = document.getElementById("profesores");
const notificarProfesoresInput = document.getElementById("notificarProfesores");
const cancelarBtn = document.getElementById("cancelarBtn");

form.addEventListener("submit", async (event) => {
  event.preventDefault();

  const id = idTutoriaInput.value.trim();
  const motivo = motivoInput.value.trim();
  const urgencia = urgenciaSelect.value;
  const asignatura = asignaturaInput.value.trim();
  const alumno = alumnoInput.value.trim();
  const profesores = profesoresInput.value.trim();
  const notificarProfesores = notificarProfesoresInput.checked;

  if (!id) {
    alert("Introduce un ID válido");
    return;
  }

  const idNum = Number(id);
  if (isNaN(idNum)) {
    alert("El ID debe ser un número válido");
    return;
  }

  const tutoria = {
    idTutoria: idNum,
    motivo,
    urgencia,
    asignatura,
    alumno: { dni: alumno },
    profesores,
    notificarProfesores
  };

  try {
    const response = await fetch("http://localhost:8080/guzpasen/modificarTutoria", {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(tutoria),
    });

    if (!response.ok) {
      const errorData = await response.json();
      alert(`Error: ${errorData.message || "Error al modificar tutoría"}`);
      return;
    }

    const data = await response.json();
    alert("Tutoría modificada correctamente");
    form.reset();
  } catch (error) {
    alert("Error en la comunicación con el servidor");
    console.error(error);
  }
});

cancelarBtn.addEventListener("click", () => {
  form.reset();
});
