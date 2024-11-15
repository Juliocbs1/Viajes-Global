document.addEventListener('DOMContentLoaded', () => {
    // Variables globales
    const navbar = document.querySelector('.navbar');
    const fullscreenMenu = document.getElementById('fullscreenMenu');
    const menuIcon = document.getElementById('menuIcon');
    const subMenus = document.querySelectorAll('.sub-menu');
    const modalRegistro = document.getElementById('modalRegistro');
    const cerrarRegistro = document.getElementById('cerrarRegistro');
    const overlay = document.getElementById('overlay');
    const formRegistro = document.getElementById('formRegistro');
    const btnAgregarCliente = document.getElementById('btnAgregarCliente'); // Botón para abrir el modal de registro
    let currentMenu = null;

    // Función para ajustar la altura del menú
    const adjustMenuHeight = () => {
        const navbarHeight = navbar.offsetHeight;
        fullscreenMenu.style.top = `${navbarHeight}px`;
        subMenus.forEach(menu => {
            menu.style.top = `${navbarHeight}px`;
        });
    };

    // Función para abrir el modal de registro
    function abrirModalRegistro() {
        if (modalRegistro && overlay) {
            modalRegistro.style.display = 'flex';
            modalRegistro.classList.add('activo');
            overlay.style.display = 'block';
        }
    }

    // Función para cerrar el modal de registro
    function cerrarModal() {
        if (modalRegistro && overlay) {
            modalRegistro.classList.remove('activo');
            setTimeout(() => {
                modalRegistro.style.display = 'none';
                overlay.style.display = 'none';
            }, 300);
        }
    }

    // Función para abrir el modal de registro desde el botón
    function openForm() {
        abrirModalRegistro();
    }

    // Evento para el botón de Agregar Nuevo Cliente
    btnAgregarCliente?.addEventListener('click', (e) => {
        e.preventDefault();
        openForm(); // Llama a la función para abrir el modal de registro
    });

    // Eventos para cerrar el modal de registro
    cerrarRegistro?.addEventListener('click', cerrarModal);
    overlay?.addEventListener('click', cerrarModal);

    // Cerrar el modal al presionar "Escape"
    document.addEventListener('keydown', (e) => {
        if (e.key === 'Escape') {
            cerrarModal();
        }
    });

    // Evento de envío del formulario de registro
    formRegistro?.addEventListener('submit', (e) => {
        e.preventDefault();
        const datosRegistro = {
            nombre: document.getElementById('nombre').value,
            correo: document.getElementById('correo').value,
            telefono: document.getElementById('telefono').value,
            contraseña: document.getElementById('contraseña').value,
            notificacion: Array.from(document.querySelectorAll('input[name="notificacion[]"]:checked')).map(checkbox => checkbox.value),
            cedula: document.getElementById('cedula').value
        };
        console.log('Intento de registro con:', datosRegistro);
        // Aquí puedes agregar la lógica para procesar el registro
        cerrarModal(); // Cerrar el modal después de procesar el registro
    });

    // Lógica para permitir solo una notificación seleccionada a la vez
    const notificacionesCheckboxes = document.querySelectorAll('input[name="notificacion[]"]');
    notificacionesCheckboxes.forEach(checkbox => {
        checkbox.addEventListener('change', (e) => {
            notificacionesCheckboxes.forEach(cb => {
                if (cb !== e.target) {
                    cb.checked = false;
                }
            });
        });
    });

    // Ajustar altura inicial del menú
    adjustMenuHeight();

    // Manejo del menú móvil principal
    menuIcon.addEventListener('click', () => {
        adjustMenuHeight();
        fullscreenMenu.classList.toggle('active');
        menuIcon.classList.toggle('active');
        
        // Si se está cerrando el menú principal, cerrar también los submenús
        if (!fullscreenMenu.classList.contains('active')) {
            subMenus.forEach(menu => {
                menu.classList.remove('active', 'slide-in');
            });
            currentMenu = null;
        }
    });

    // Configurar enlaces del menú principal
    const menuItems = document.querySelectorAll('.menu-item');
    menuItems.forEach(item => {
        item.addEventListener('click', (e) => {
            e.preventDefault();
            const targetSection = document.getElementById(item.getAttribute('data-section'));
            if (targetSection) {
                adjustMenuHeight();
                targetSection.classList.add('active');
                targetSection.classList.add('slide-in');
                currentMenu = targetSection;
            }
        });
    });

    // Función para volver atrás
    window.goBack = function() {
        if (currentMenu) {
            currentMenu.classList.remove('slide-in');
            currentMenu.classList.add('slide-out');
            
            setTimeout(() => {
                currentMenu.classList.remove('active', 'slide-out');
                currentMenu = null;
            }, 300);
        } else {
            fullscreenMenu.classList.remove('active');
            menuIcon.classList.remove('active');
        }
    };

    // Manejo de redimensionamiento de ventana
    window.addEventListener('resize', () => {
        adjustMenuHeight();

        if (window.innerWidth > 768) {
            fullscreenMenu.classList.remove('active');
            menuIcon.classList.remove('active');
            subMenus.forEach(menu => {
                menu.classList.remove('active', 'slide-in', 'slide-out');
            });
            currentMenu = null;
        }
    });

    // Cerrar menú cuando se hace clic fuera
    document.addEventListener('click', (e) => {
        if (e.target.classList.contains('fullscreen-menu')) {
            goBack();
        }
    });

    // Prevenir que los clics dentro del menú cierren el menú
    document.querySelector('.menu-content')?.addEventListener('click', (e) => {
        e.stopPropagation();
    });

    // Manejo del submenú con animaciones mejoradas
    const links = {
        'reservarLink': 'reservarSection',
        'offersLink': 'offersSection',
        'miReservaLink': 'miReservaSection',
        'ayudaLink': 'ayudaSection',
        'lealtadLink': 'lealtadSection'
    };

    const menuContainer = document.getElementById('menuContainer');
   

    let currentSection = null;
    let isAnimating = false;

    function hideAllSections() {
        Object.values(links).forEach(sectionId => {
            const section = document.getElementById(sectionId);
            section.style.display = 'none';
            section.style.opacity = '0';
            section.style.transform = 'translateY(-20px)';
        });
    }

    function animateSection(sectionId) {
        const section = document.getElementById(sectionId);
        section.style.display = 'block';
        section.offsetHeight; // Trigger reflow
        section.style.opacity = '1';
        section.style.transform = 'translateY(0)';
    }

    function closeCurrentSection(callback) {
        if (currentSection) {
            const currentSectionElement = document.getElementById(currentSection);
            currentSectionElement.style.opacity = '0';
            currentSectionElement.style.transform = 'translateY(-20px)';

            setTimeout(() => {
                currentSectionElement.style.display = 'none';
                currentSection = null;
                callback();
            }, 150);
        } else {
            callback();
        }
    }

    function openSection(sectionId) {
        if (isAnimating || window.innerWidth <= 768) return;
        isAnimating = true;

        overlay.style.display = 'block';
        overlay.style.opacity = '0';
        overlay.offsetHeight;
        overlay.style.opacity = '1';

        closeCurrentSection(() => {
            hideAllSections();
            menuContainer.classList.add('active');
            animateSection(sectionId);
            currentSection = sectionId;
            isAnimating = false;
        });
    }

    Object.keys(links).forEach(linkId => {
        document.getElementById(linkId)?.addEventListener('click', (event) => {
            event.preventDefault();
            openSection(links[linkId]);
        });
    });

    function closeMenu() {
        if (isAnimating) return;
        isAnimating = true;

        menuContainer.classList.remove('active');
        overlay.style.opacity = '0';

        closeCurrentSection(() => {
            overlay.style.display = 'none';
            isAnimating = false;
        });
    }

    overlay?.addEventListener('click', closeMenu);

    document.addEventListener('click', (event) => {
        const isNavItem = event.target.closest('.nav-item');
        const isMenuContainer = event.target.closest('#menuContainer');

        if (!isMenuContainer && !isNavItem) {
            closeMenu();
        }
    });

    // Manejo del modal de login
    const accountBtn = document.querySelector('.account-btn');
    const loginModal = document.getElementById('loginModal');
    const closeLogin = document.getElementById('closeLogin');

    function openLoginModal() {
        console.log('Abriendo modal de login...');
        if (loginModal) {
            loginModal.classList.add('active');
            overlay.style.display = 'block';
            overlay.style.opacity = '1';
        } else {
            console.error('Modal de login no encontrado.');
        }
    }

    function closeLoginModal() {
        console.log('Cerrando modal de login...');
        if (loginModal) {
            loginModal.classList.remove('active');
            overlay.style.opacity = '0';

            setTimeout(() => {
                overlay.style.display = 'none';
            }, 300);
        }
    }

    accountBtn?.addEventListener('click', (e) => {
        e.preventDefault();
        openLoginModal();
    });

    closeLogin?.addEventListener('click', closeLoginModal);

    overlay?.addEventListener('click', closeLoginModal);

    document.addEventListener('keydown', (e) => {
        if (e.key === 'Escape') {
            closeLoginModal();
        }
    });
});

function searchTable() {
    // Obtener el valor del input de búsqueda y convertirlo a minúsculas
    const searchText = document.getElementById('searchInput').value.toLowerCase();
    
    // Obtener todas las filas del cuerpo de la tabla
    const tableBody = document.getElementById('tableBody');
    const rows = tableBody.getElementsByTagName('tr');
    
    // Iterar sobre cada fila
    for (let row of rows) {
        let found = false;
        // Obtener todas las celdas de la fila, excepto la primera que contiene los botones
        const cells = row.getElementsByTagName('td');
        
        // Verificar el contenido de cada celda
        for (let i = 1; i < cells.length; i++) { // Empezamos desde 1 para saltar la celda de botones
            const cellText = cells[i].textContent || cells[i].innerText;
            
            if (cellText.toLowerCase().indexOf(searchText) > -1) {
                found = true;
                break;
            }
        }
        
        // Mostrar u ocultar la fila según si se encontró coincidencia
        if (found) {
            row.style.display = "";
        } else {
            row.style.display = "none";
        }
    }
}

// Agregar el evento al input de búsqueda (alternativa al onkeyup en el HTML)
document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('input', searchTable);
    }
});
