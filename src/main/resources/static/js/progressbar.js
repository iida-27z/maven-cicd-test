document.addEventListener('DOMContentLoaded', () => {
    const containers = document.querySelectorAll('.js-bar-fill');
    containers.forEach(container => {
        const progressValue = container.dataset.progress;
        if (progressValue && !isNaN(parseInt(progressValue))) {
            container.style.setProperty('--progress-width', `${progressValue}%`);
        }
    });
});