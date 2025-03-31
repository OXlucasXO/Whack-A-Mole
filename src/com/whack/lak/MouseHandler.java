public class MouseHandler {
    public static void main(String[] args) {
        document.addEventListener("click", function(event) {
            let mouseX = event.clientX; // X position of the click
            let mouseY = event.clientY; // Y position of the click
        
            console.log("Mouse clicked at:", mouseX, mouseY);
        
            checkMoleHit(mouseX, mouseY);
        });
        
        function checkMoleHit(x, y) {
            let moles = document.querySelectorAll(".mole"); // Get all moles
        
            moles.forEach(mole => {
                let rect = mole.getBoundingClientRect(); // Get mole position
                if (x >= rect.left && x <= rect.right && y >= rect.top && y <= rect.bottom) {
                    console.log("Mole hit!");
                    mole.style.display = "none"; // Hide the mole
                }
            });
        }
}
}
