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

