// Sticky Navbar
window.addEventListener("scroll", function() {
    var nav = document.querySelector("nav");
    nav.classList.toggle("sticky", window.scrollY > 0);
})

// Toggle Chatbox
function toggleChat() {
    var chatbox = document.getElementById("chatbox");
    chatbox.classList.toggle("chatbot-button");
}

// Send Message and Get Bot Response
function sendMessage() {
    var inputField = document.getElementById("chat-input");
    var chatBody = document.getElementById("chat-body");
    var message = inputField.value.trim();

    if (message !== "") {
        // User Message
        var userMessage = document.createElement("p");
        userMessage.style.textAlign = "right";
        userMessage.style.background = "#4e55e0";
        userMessage.style.padding = "5px";
        userMessage.style.borderRadius = "5px";
        userMessage.textContent = "You: " + message;
        chatBody.appendChild(userMessage);

        inputField.value = "";
        chatBody.scrollTop = chatBody.scrollHeight; // Auto-scroll down

        // Get bot response
        setTimeout(() => {
            var botMessage = document.createElement("p");
            botMessage.style.textAlign = "left";
            botMessage.style.background = "#f1f1f1";
            botMessage.style.padding = "5px";
            botMessage.style.borderRadius = "5px";
            botMessage.textContent = "CyberGuardian: " + getBotResponse(message);
            chatBody.appendChild(botMessage);

            chatBody.scrollTop = chatBody.scrollHeight; // Auto-scroll down
        }, 1000); // Delay for a more human-like response
    }
}

// Predefined Bot Responses
function getBotResponse(input) {
    input = input.toLowerCase();

    if (input.includes("hello") || input.includes("hi")) {
		return "Hello! How can I assist you with cybersecurity today? 😊";
    } else if (input.includes("password")) {
		return "Always use strong passwords with a mix of letters, numbers, and symbols! 🔐";
    } else if (input.includes("cyber attack")) {
        return "Cyber attacks can be prevented with strong security measures. Do you need tips? 🔍";
    } else if (input.includes("hacking")) {
        return "Hacking without permission is illegal. Ethical hacking is a better approach! ⚖️";
    } else if (input.includes("quiz")) {
        return "You can take a cybersecurity quiz to test your knowledge. Would you like the link? 📝";
    } else if (input.includes("How can I create a strong password?")) {
		return "A strong password should be: At least 12-16 characters long Contain uppercase & lowercase letters, numbers, and symbols Avoid common words like password123 Use a passphrase like: M0nkeysLove$Bananas!";
	} else if (input.includes("How do I recognize a phishing email?")) {
		return "Look for these signs: Suspicious email addresses (e.g., support@bank-login-secure.com) Urgent language like Your account will be suspended! Links that don’t match (hover over links before clicking) Unexpected attachments—never open unknown files! If you're unsure, contact the sender through official channels.";
	} else if (input.includes("Why should I enable 2FA?")) {
		return "Two-Factor Authentication (2FA) adds an extra security layer. Even if someone steals your password, they can’t log in without the second factor (like an OTP or authentication app). Enable 2FA on your bank, email, and social media accounts.";
	} else {
        return "I'm still learning! Can you rephrase or ask something else? 🤖";
    }
}

// Send message on Enter key press
function handleKeyPress(event) {
    if (event.key === "Enter") {
        sendMessage();
    }
}