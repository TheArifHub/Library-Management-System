const books = document.querySelector(".floating-books");
const bookIcons = ["📚", "📖", "📕", "📗", "📘", "📙"];

for (let i = 0; i < 15; i++) {
  const book = document.createElement("div");
  book.classList.add("book");
  book.innerHTML = bookIcons[Math.floor(Math.random() * bookIcons.length)];
  book.style.fontSize = Math.random() * 30 + 20 + "px";
  book.style.left = Math.random() * 100 + "%";
  book.style.animationDelay = Math.random() * 15 + "s";
  books.appendChild(book);
}

document.addEventListener("DOMContentLoaded", function () {
  const message = document.querySelector(".message");
  if (message) {
    setTimeout(function () {
      message.style.display = "none";
    }, 2000);
  }
});
