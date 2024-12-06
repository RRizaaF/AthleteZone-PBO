document.addEventListener('DOMContentLoaded', function() {
    const dashboardLinks = document.querySelectorAll('.dashboard-link')
    const contentDashboard = document.querySelectorAll('.content-dashboard')

    contentDashboard.forEach(content => {
        content.style.display = 'none';
    });

    const defaultDashboard = 'dashboard';

    const defaultLink = document.querySelector(`.dashboard-link[menu-dashboard="${defaultDashboard}"]`);
    const defaultContent = document.querySelector(`.${defaultDashboard}`);

    if (defaultLink) {
        defaultLink.classList.add('active');
    }
    if (defaultContent) {
        defaultContent.style.display = 'block';
    }

    dashboardLinks.forEach(link => {
        link.addEventListener('click', () => {
            const selectedMenu = link.getAttribute('menu-dashboard');

            dashboardLinks.forEach(link => link.classList.remove('active'))

            link.classList.add('active');

            contentDashboard.forEach(content => {
                content.style.display = 'none'
            });

            const activeContent = document.querySelector(`.${selectedMenu}`)
            if (activeContent) {
                activeContent.style.display = 'block'
            }
        })
    });
})

// Ketika klik save pada form add product, maka akan kembali ke halaman library
document.addEventListener("DOMContentLoaded", () => {
    // Ambil hash dari URL (misalnya #library)
    const hash = window.location.hash;

    // Sembunyikan semua bagian dashboard
    const dashboardContents = document.querySelectorAll(".content-dashboard");
    dashboardContents.forEach(content => content.style.display = "none");

    // Tampilkan bagian sesuai hash
    if (hash) {
        const targetContent = document.querySelector(hash);
        if (targetContent) {
            targetContent.style.display = "block";
        }
    } else {
        // Default: Tampilkan dashboard
        document.querySelector(".content-dashboard.dashboard").style.display = "block";
    }
});
