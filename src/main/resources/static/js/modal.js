document.addEventListener('DOMContentLoaded', function() {
    const modal = document.getElementById('modal');
    const modalForm = document.getElementById('modalForm');
    const modalTitle = document.getElementById('modalTitle');
    const modalId = document.getElementById('modalId');
    const modalName = document.getElementById('modalName');
    const modalIndustry = document.getElementById('modalIndustry');
    const openModalButtons = document.querySelectorAll('.open-modal-button');
    const closeModalButton = document.getElementById('closeModal');
    const cancelButton = document.getElementById('cancelButton');

    openModalButtons.forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault();

            const id = button.getAttribute('data-id');
            const name = button.getAttribute('data-name');
            const industry = button.getAttribute('data-industry');
            const action = button.getAttribute('data-action');

            modalId.value = id || '';
            console.log("ID: ", modalId.value);
            modalName.value = name || '';
            modalIndustry.value = industry || '';
            modalForm.action = action || '';

            modalTitle.textContent = id ? 'Edit Company' : 'Add New Company';

            modal.style.display = 'block';
        });
    });

    closeModalButton.addEventListener('click', function() {
        modal.style.display = 'none';
    });

    cancelButton.addEventListener('click', function() {
        modal.style.display = 'none';
    });

    window.addEventListener('click', function(event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });
});
