document.addEventListener('DOMContentLoaded', function() {
    const modal = document.getElementById('addContactModal');
    const modalForm = document.getElementById('modalForm');
    const modalTitle = document.getElementById('modalTitle');
    const modalId = document.getElementById('modalId');
    const modalName = document.getElementById('modalName');
    const modalEmail = document.getElementById('modalEmail');
    const modalPhone = document.getElementById('modalPhone');
    const modalCompanyId = document.getElementById('modalCompanyId');
    const openModalButtons = document.querySelectorAll('.open-modal-button');
    const closeModalButton = document.getElementById('closeModal');
    const cancelButton = document.getElementById('cancelButton');

    openModalButtons.forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault();

            const id = button.getAttribute('data-id');
            const name = button.getAttribute('data-name');
            const email = button.getAttribute('data-email');
            const phone = button.getAttribute('data-phone');
            const companyId = button.getAttribute('data-company-id');
            const action = button.getAttribute('data-action');
            console.log(action)
            // Если кнопка для добавления нового контакта
            if (!id) {
                // Очищаем значения полей
                modalId.value = '';
                modalName.value = '';
                modalEmail.value = '';
                modalPhone.value = '';
                modalCompanyId.value = '';
                modalForm.action = action || '/contacts/add'; // Устанавливаем путь для добавления
                modalTitle.textContent = 'Add New Contact';
            } else {
                // Если кнопка для редактирования контакта
                modalId.value = id || '';
                modalName.value = name || '';
                modalEmail.value = email || '';
                modalPhone.value = phone || '';
                modalCompanyId.value = companyId || '';
                modalForm.action = action || '';
                modalTitle.textContent = 'Edit Contact';
            }

            modal.style.display = 'block'; // Открыть модальное окно
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
