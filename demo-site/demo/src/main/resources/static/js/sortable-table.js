function SortableTable(table) {

  var table = table;
  var status;

  var options = {};
  options.statusMessage = 'Sort by %heading% (%direction%)';
  options.ascendingText = 'ascending';
  options.descendingText = 'descending';

  createHeadingButtons();
  createStatusBox();

  function createHeadingButtons() {
    var headings = table.querySelectorAll('thead th');
    var heading;

    for (var i = 0; i < headings.length; i++) {
      heading = headings[i];
      if (heading.getAttribute('aria-sort')) {
        createHeadingButton(heading, i);
      }
    }
  };


  function createHeadingButton(heading, i) {
    var text = heading.textContent;
    var button = document.createElement('button')
    button.setAttribute('type', 'button')
    button.setAttribute('data-index', i)
    button.textContent = text
    button.addEventListener('click', sortButtonClicked)
    heading.textContent = '';
    heading.appendChild(button);

    if (text.indexOf("Provider") !== -1) {
      var p = document.createElement("p");
      p.className = "govuk-body-s";
      p.innerText = "(opens in new tab)";
      heading.appendChild(p);
    }
  };


  function sortButtonClicked(event) {

    var sortDirection = event.target.parentElement.getAttribute('aria-sort')
    var newSortDirection;
    if (sortDirection === 'none' || sortDirection === 'ascending') {
      newSortDirection = 'descending';
    } else {
      newSortDirection = 'ascending';
    }

    document.getElementById("sortType").value = event.target.getAttribute("data-index") == 0 ? "NAME" : "PRICE"
    document.getElementById("sortDirection").value = newSortDirection.toUpperCase();

    event.target.form.submit();


  }

  function createStatusBox() {

    status = document.createElement('div')
    status.setAttribute('aria-live', 'polite')
    status.setAttribute('role', 'status')
    status.setAttribute('aria-atomic', 'true')
    status.setAttribute('class', 'sortable-table-status')

    table.parentElement.insertBefore(status, table.nextSibling);
  };

};