let categoriaActual = 'todas';

function filtrarProductos() {
    const query = document.getElementById('searchInput').value.toLowerCase();
    const cards = document.querySelectorAll('.product-card');

    cards.forEach(card => {
        const nombre = card.querySelector('.product-name').textContent.toLowerCase();
        const desc = card.querySelector('.product-desc').textContent.toLowerCase();
        const catCard = (card.getAttribute('data-categoria') || '').toLowerCase();
        
        const coincideTexto = nombre.includes(query) || desc.includes(query);
        
        const coincideCategoria = (categoriaActual === 'todas' || catCard.includes(categoriaActual.toLowerCase()));

        if (coincideTexto && coincideCategoria) {
            card.style.display = ''; 
        } else {
            card.style.display = 'none';
        }
    });
}

function filtrarCategoria(categoria, btn) {
    document.querySelectorAll('.filter-badge').forEach(b => b.classList.remove('active'));
    if (btn) btn.classList.add('active');

    categoriaActual = categoria.toLowerCase();

    filtrarProductos();
}

function filtrarCategoriaPorBoton(btn) {
    const catName = btn.getAttribute('data-catname') || 'todas';
    filtrarCategoria(catName, btn);
}