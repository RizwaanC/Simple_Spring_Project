document.addEventListener('DOMContentLoaded', function () {
    const apiUrl = '/api/books';

    const bookForm = document.getElementById('book-form');
    const bookIdInput = document.getElementById('book-id');
    const titleInput = document.getElementById('title');
    const authorInput = document.getElementById('author');
    const isbnInput = document.getElementById('isbn');
    const saveBtn = document.getElementById('save-btn');
    const bookList = document.getElementById('books');

    function renderBookList(books) {
        bookList.innerHTML = '';
        books.forEach(book => {
            const li = document.createElement('li');
            li.innerHTML = `<strong>${book.title}</strong> by ${book.author} (ISBN: ${book.isbn})
            <button onclick="editBook('${book._id}', '${book.title}', '${book.author}', '${book.isbn}')">Edit</button>
            <button onclick="deleteBook('${book._id}')">Delete</button>`;
            bookList.appendChild(li);
        });
    }

    function clearForm() {
        bookIdInput.value = '';
        titleInput.value = '';
        authorInput.value = '';
        isbnInput.value = '';
    }

    function fetchBooks() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => renderBookList(data))
            .catch(error => console.error('Error fetching books:', error));
    }

    function addBook() {
        const book = {
            title: titleInput.value,
            author: authorInput.value,
            isbn: isbnInput.value
        };

        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(book)
        })
        .then(response => response.json())
        .then(data => {
            fetchBooks();
            clearForm();
        })
        .catch(error => console.error('Error adding book:', error));
    }

    function updateBook() {
        const book = {
            title: titleInput.value,
            author: authorInput.value,
            isbn: isbnInput.value
        };

        const bookId = bookIdInput.value;

        fetch(`${apiUrl}/${bookId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(book)
        })
        .then(response => response.json())
        .then(data => {
            fetchBooks();
            clearForm();
        })
        .catch(error => console.error('Error updating book:', error));
    }

    function deleteBook(bookId) {
        fetch(`${apiUrl}/${bookId}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                fetchBooks();
            }
        })
        .catch(error => console.error('Error deleting book:', error));
    }

    function editBook(id, title, author, isbn) {
        bookIdInput.value = id;
        titleInput.value = title;
        authorInput.value = author;
        isbnInput.value = isbn;
    }

    saveBtn.addEventListener('click', function () {
        const bookId = bookIdInput.value;
        if (bookId) {
            updateBook();
        } else {
            addBook();
        }
    });

    fetchBooks();
});
