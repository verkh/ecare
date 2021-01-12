function modifyText() {
    let search = document.getElementById("search");
    let searchButton = document.getElementById("doSearch");
    let clearButton = document.getElementById("clearSearch");
    if (search.value.length > 0) {
        searchButton.setAttribute("href", "?search="+search.value);
        clearButton.disabled = false;
    } else {
        searchButton.setAttribute("href", "");
        clearButton.disabled = true;
    }
}

