document.addEventListener('DOMContentLoaded', () => {
    const navbar = document.querySelector('.navbar');
    const fullscreenMenu = document.getElementById('fullscreenMenu');
    const menuIcon = document.getElementById('menuIcon');
    const subMenus = document.querySelectorAll('.sub-menu');
    let currentMenu = null;

    // Función para ajustar la altura del menú
    const adjustMenuHeight = () => {
        const navbarHeight = navbar.offsetHeight;
        fullscreenMenu.style.top = `${navbarHeight}px`;
        subMenus.forEach(menu => {
            menu.style.top = `${navbarHeight}px`;
        });
    };

    // Función para cerrar submenús
    const closeSubMenus = () => {
        if (currentMenu) {
            currentMenu.classList.add('slide-out');
            setTimeout(() => {
                currentMenu.classList.remove('active', 'slide-in', 'slide-out');
                currentMenu = null;
            }, 500);
        }
    };

    // Manejo del menú móvil principal con transición suave
    menuIcon.addEventListener('click', () => {
        adjustMenuHeight();
        
        // Cerrar submenús primero
        closeSubMenus();
        
        // Agregar transición suave al abrir/cerrar menú principal
        requestAnimationFrame(() => {
            fullscreenMenu.classList.toggle('active');
            menuIcon.classList.toggle('active');
        });
        
        if (!fullscreenMenu.classList.contains('active')) {
            subMenus.forEach(menu => {
                menu.classList.remove('active');
                setTimeout(() => {
                    menu.classList.remove('slide-in');
                }, 500);
            });
            currentMenu = null;
        }
    });

    // Configurar enlaces del menú principal con transición mejorada
    const menuItems = document.querySelectorAll('.menu-item');
    menuItems.forEach(item => {
        item.addEventListener('click', (e) => {
            e.preventDefault();
            const targetSection = document.getElementById(item.getAttribute('data-section'));
            
            if (targetSection) {
                adjustMenuHeight();
                
                // Agregar transición suave al abrir submenú
                requestAnimationFrame(() => {
                    targetSection.classList.add('active');
                    setTimeout(() => {
                        targetSection.classList.add('slide-in');
                    }, 50);
                });
                
                currentMenu = targetSection;
            }
        });
    });

    // Función mejorada para volver atrás
    window.goBack = function() {
        if (currentMenu) {
            currentMenu.classList.add('slide-out');
            
            setTimeout(() => {
                currentMenu.classList.remove('active', 'slide-in');
                currentMenu.classList.remove('slide-out');
                currentMenu = null;
            }, 500);
        } else {
            fullscreenMenu.classList.remove('active');
            menuIcon.classList.remove('active');
        }
    };

    // Manejo de redimensionamiento de ventana
    let resizeTimeout;
    window.addEventListener('resize', () => {
        clearTimeout(resizeTimeout);
        resizeTimeout = setTimeout(() => {
            adjustMenuHeight();
            
            if (window.innerWidth > 768) {
                fullscreenMenu.classList.remove('active');
                menuIcon.classList.remove('active');
                subMenus.forEach(menu => {
                    menu.classList.remove('active', 'slide-in', 'slide-out');
                });
                currentMenu = null;
            }
        }, 250);
    });

    // Inicialización
    adjustMenuHeight();
    // Manejo del submenú con animaciones mejoradas
    const links = {
        'reservarLink': 'reservarSection',
        'offersLink': 'offersSection',
        'miReservaLink': 'miReservaSection',
        'ayudaLink': 'ayudaSection',
        'lealtadLink': 'lealtadSection'
    };

    const menuContainer = document.getElementById('menuContainer');
    const overlay = document.getElementById('overlay');

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

    // Responsividad del submenú
    function handleSubmenuResponsive() {
        if (window.innerWidth <= 768) {
            if (menuContainer.classList.contains('active')) {
                menuContainer.classList.remove('active');
                overlay.style.opacity = '0';
                overlay.style.display = 'none';

                Object.values(links).forEach(sectionId => {
                    const section = document.getElementById(sectionId);
                    if (section) {
                        section.style.display = 'none';
                        section.style.opacity = '0';
                        section.style.transform = 'translateY(-20px)';
                    }
                });

                currentSection = null;
            }
        }
    }

    window.addEventListener('resize', handleSubmenuResponsive);

    // Manejo de los radio buttons para tipo de viaje
    const tripOptions = document.querySelectorAll('.trip-option');
    
    tripOptions.forEach(option => {
        option.addEventListener('click', () => {
            tripOptions.forEach(opt => opt.classList.remove('active'));
            option.classList.add('active');
        });
    });

    // Manejo del selector de pasajeros
    const passengersSelect = document.querySelector('.passengers-select');
    const passengersInput = passengersSelect.querySelector('.input-field');
    const passengersDropdown = passengersSelect.querySelector('.passengers-dropdown');
    const passengerCountDisplay = document.querySelector('.passenger-count');
    const confirmButton = document.querySelector('.confirm-btn');

    let passengerCount = 1;

    passengersSelect.addEventListener('click', (e) => {
        passengersDropdown.classList.toggle('show');
        e.stopPropagation();
    });

    document.querySelector('.increase-btn').addEventListener('click', () => {
        passengerCount++;
        passengerCountDisplay.textContent = passengerCount;
        passengersInput.value = `${passengerCount} pasajero${passengerCount > 1 ? 's' : ''}`;
    });

    document.querySelector('.decrease-btn').addEventListener('click', () => {
        if (passengerCount > 1) {
            passengerCount--;
            passengerCountDisplay.textContent = passengerCount;
            passengersInput.value = `${passengerCount} pasajero${passengerCount > 1 ? 's' : ''}`;
        }
    });

    confirmButton.addEventListener('click', () => {
        passengersDropdown.classList.remove('show');
    });

    document.addEventListener('click', (e) => {
        if (!passengersSelect.contains(e.target)) {
            passengersDropdown.classList.remove('show');
        }
    });

    // Modales de Login y Registro
    const accountBtn = document.querySelector('.account-btn');
    const loginModal = document.getElementById('loginModal');
    const closeLogin = document.getElementById('closeLogin');
    const closeRegister = document.getElementById('closeRegister');
    const loginContainer = document.querySelector('.login-container');
    const registerContainer = document.getElementById('registerContainer');
    const showRegister = document.getElementById('showRegister');
    const backToLogin = document.getElementById('backToLogin');
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');

    function switchToRegister() {
        loginContainer.classList.add('slide-out');
        registerContainer.style.display = 'flex';

        setTimeout(() => {
            registerContainer.classList.add('slide-in');
            registerContainer.classList.remove('slide-out');
        }, 50);

        setTimeout(() => {
            loginContainer.style.display = 'none';
        }, 500);
    }

    function switchToLogin() {
        loginContainer.style.display = 'flex';
        loginContainer.classList.remove('slide-out');
        
        registerContainer.classList.remove('slide-in');
        registerContainer.classList.add('slide-out');
        
        setTimeout(() => {
            registerContainer.style.display = 'none';
        }, 500);
    }

    function openLoginModal() {
        loginModal.classList.add('active');
        overlay.style.display = 'block';
        overlay.style.opacity = '1';
        loginContainer.style.display = 'flex';
        loginContainer.classList.remove('slide-out');
        registerContainer.style.display = 'none';
        registerContainer.classList.remove('slide-in');
    }

    function openRegisterModal() {
        loginModal.classList.add('active');
        overlay.style.display = 'block';
        overlay.style.opacity = '1';
        switchToRegister();
    }

    function closeLoginModal() {
        loginModal.classList.remove('active');
        overlay.style.display = 'none';

        setTimeout(() => {
            loginContainer.style.display = 'none';
            registerContainer.style.display = 'none';
            loginContainer.classList.remove('slide-out');
            registerContainer.classList.remove('slide-in', 'slide-out');
        }, 300);
    }

    accountBtn?.addEventListener('click', (e) => {
        e.preventDefault();
        openLoginModal();
    });

    closeLogin?.addEventListener('click', closeLoginModal);
    closeRegister?.addEventListener('click', closeLoginModal);

    showRegister?.addEventListener('click', (e) => {
        e.preventDefault();
        switchToRegister();
    });

    backToLogin?.addEventListener('click', (e) => {
        e.preventDefault();
        switchToLogin();
    });

    loginForm?.addEventListener('submit', (e) => {
        e.preventDefault();
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        console.log('Intento de login con:', { username, password });
    });

    registerForm?.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = {
            name: document.getElementById('name').value,
            email: document.getElementById('email').value,
            phone: document.getElementById('phone').value,
            password: document.getElementById('newPassword').value,
            notification: document.getElementById('notification')?.value || '',
            cedula: document.getElementById('cedula').value
        };
        console.log('Intento de registro con:', formData);
    });

    overlay?.addEventListener('click', closeLoginModal);

    document.addEventListener('keydown', (e) => {
        if (e.key === 'Escape') {
            closeLoginModal();
        }
    });

    loginModal?.addEventListener('click', (e) => {
        if (e.target === loginModal) {
            closeLoginModal();
        }
    });
});

const notificationCheckboxes = document.querySelectorAll('input[name="notification[]"]');

notificationCheckboxes.forEach(checkbox => {
  checkbox.addEventListener('change', handleNotificationCheckboxChange);
});

function handleNotificationCheckboxChange(event) {
  notificationCheckboxes.forEach(checkbox => {
    if (checkbox !== event.target) {
      checkbox.checked = false;
    }
  });
}

// Manejo de los radio buttons para tipo de viaje
const tripOptions = document.querySelectorAll('.trip-option');
const returnDateInput = document.querySelector('.input-group:nth-child(4)'); // Asumiendo que es el cuarto input group (de 'Vuelta')

tripOptions.forEach(option => {
    option.addEventListener('click', () => {
        tripOptions.forEach(opt => opt.classList.remove('active'));
        option.classList.add('active');

        // Ocultar o mostrar el input de 'Vuelta' con animación
        if (option.textContent.trim() === 'Solo ida') {
            returnDateInput.classList.add('hidden');
            setTimeout(() => {
                returnDateInput.style.display = 'none';
            }, 400); // La duración de la animación CSS (en ms)
        } else {
            returnDateInput.style.display = 'block';
            setTimeout(() => {
                returnDateInput.classList.remove('hidden');
            }, 10); // Permitir el reflow antes de quitar la clase
        }
    });
});

