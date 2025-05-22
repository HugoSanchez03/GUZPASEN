document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("form-acta");
  const puntosInput = document.getElementById("puntosTratados");
  const observacionesInput = document.getElementById("observaciones");
  const cancelarBtn = document.getElementById("cancelar-btn");

  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const actaData = {
      puntosTratados: puntosInput.value,
      observaciones: observacionesInput.value
    };

    try {
      const response = await fetch("http://localhost:8080/guzpasen/anadirActa", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(actaData)
      });

      if (response.ok) {
        alert("Acta guardada correctamente.");
        form.reset();
      } else {
        alert("Error al guardar el acta.");
      }
    } catch (error) {
      console.error("Error al enviar la solicitud:", error);
      alert("Fallo de conexión con el servidor.");
    }
  });

  cancelarBtn.addEventListener("click", () => {
    if (confirm("¿Deseas cancelar? Se borrarán los campos.")) {
    document.getElementById('puntos-tratados').value = '';
    document.getElementById('observaciones').value = '';    
    }
  });
});