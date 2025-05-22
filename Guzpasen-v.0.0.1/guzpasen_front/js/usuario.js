// usuario.js
document.addEventListener('DOMContentLoaded', () => {
  const nick = localStorage.getItem('nickUsuario');
  if (nick) {
    const botonUsuario = document.getElementById('btnUsuario');
    if (botonUsuario) {
      botonUsuario.textContent = nick;
    }
  }
});
