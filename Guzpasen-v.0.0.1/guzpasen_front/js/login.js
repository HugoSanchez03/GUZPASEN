document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('loginForm');
    const errorMensaje = document.getElementById('errorMensaje');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const nick = document.getElementById('nick').value;
        const clave = document.getElementById('clave').value;

        const params = new URLSearchParams();
        params.append('nick', nick);
        params.append('clave', clave);

        try {
            const response = await fetch('http://localhost:8080/guzpasen/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: params.toString()
            });

            if (response.ok) {
                localStorage.setItem('nickUsuario', nick);
                window.location.href = '/index.html';            
            } else if (response.status === 401) {
                const texto = await response.text();
                errorMensaje.style.display = 'block';
                errorMensaje.textContent = texto;
            } else {
                errorMensaje.style.display = 'block';
                errorMensaje.textContent = 'Credenciales no válidas. Inténtalo de nuevo.';
            }
        } catch (error) {
            errorMensaje.style.display = 'block';
            errorMensaje.textContent = 'Error de conexión. Intente más tarde.';
        }
    });
});
