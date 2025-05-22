const form = document.getElementById("form-acta");
const idActaInput = document.getElementById("id-acta");
const puntosTratadosInput = document.getElementById("puntos-tratados");
const observacionesInput = document.getElementById("observaciones");
const cancelarBtn = document.getElementById("cancelar");

form.addEventListener("submit", async (event) => {
  event.preventDefault();

  const id = idActaInput.value.trim();
  const puntosTratados = puntosTratadosInput.value.trim();
  const observaciones = observacionesInput.value.trim();

  if (!id) {
    alert("Introduce un ID válido");
    return;
  }

  // Convertimos id a número
  const idNum = Number(id);
  if (isNaN(idNum)) {
    alert("El ID debe ser un número válido");
    return;
  }

  const acta = {
    idActa: idNum,
    puntosTratados,
    observaciones,
  };

  try {
    const response = await fetch("http://localhost:8080/guzpasen/modificarActa", {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(acta),
    });

    if (!response.ok) {
      const errorData = await response.json();
      alert(`Error: ${errorData.message || "Error al modificar acta"}`);
      return;
    }

    const data = await response.json();
    alert("Acta modificada correctamente");
    form.reset();
  } catch (error) {
    alert("Error en la comunicación con el servidor");
    console.error(error);
  }
});

cancelarBtn.addEventListener("click", () => {
  form.reset();
});
