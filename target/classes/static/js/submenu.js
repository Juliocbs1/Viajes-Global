document.addEventListener('DOMContentLoaded', () => {
    const navItems = document.querySelectorAll('.nav-item');
    const submenu = document.getElementById('submenu');
    let activeItem = null;

    // Mostrar/ocultar el submenu al hacer clic en un elemento del menú
    navItems.forEach(item => {
        item.addEventListener('click', () => {
            if (activeItem === item) {
                hideSubmenu(); // Ocultar si se vuelve a hacer clic en el mismo ítem
                activeItem = null;
            } else {
                activeItem = item;
                showSubmenu(item); // Mostrar el submenu
            }
        });
    });

    // Ocultar el submenu cuando el mouse sale de la zona del submenu
    submenu.addEventListener('mouseleave', () => {
        hideSubmenu();
        activeItem = null;
    });

    // Función para mostrar el submenu
    function showSubmenu(item) {
        submenu.classList.add('active');

        // Ajustar la posición del submenu basado en el nav-item activo
        const rect = item.getBoundingClientRect();
        submenu.style.left = `${rect.left}px`; // Ajusta la posición horizontal
        submenu.style.top = `${rect.bottom}px`; // Ajustar posición vertical según sea necesario
    }

    // Función para ocultar el submenu
    function hideSubmenu() {
        submenu.classList.remove('active');
    }

    // Cerrar el submenú al hacer clic fuera de él
    document.addEventListener('click', (event) => {
        if (!event.target.closest('.submenu') && !event.target.closest('.nav-item')) {
            hideSubmenu();
            activeItem = null;
        }
    });
});
