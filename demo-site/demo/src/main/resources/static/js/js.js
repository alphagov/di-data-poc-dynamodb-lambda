
!!document.getElementById('js-clearFilters') && document.getElementById('js-clearFilters').addEventListener('click', function (e) {
	document.getElementsByName("selectedTestTypes").forEach( function(element, index, array) {
		element.removeAttribute("checked");
	  });
	  
	document.getElementById("testLocation").value = "";
	document.getElementById("testMethod").value = "DEFAULT";
	document.getElementById("testMethod").form.submit();
});

/////STICKY FOOTER STUFF///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

function returnToTable() {
	document.getElementById("providerDataTable").scrollIntoView({ behavior: 'smooth' });
}

const content = document.getElementById("contents");
const stickyElment = document.getElementById("stick-element");

if (content !== null && stickyElment !== null) {
	document.addEventListener("scroll", (e) => {

		var scrolled = document.scrollingElement.scrollTop;
		var position = content.offsetTop;

		if (scrolled > position) {
			stickyElment.classList.remove(
				'sticky-element--hidden');
		} else {
			stickyElment.classList.add(
				'sticky-element--hidden');
		}
	});
}

// get the element to animate
var element = document.getElementById("contents-link");

if (element !== null) {
	var elementHeight = element.clientHeight;
	document.addEventListener('scroll', animate);

	function inView() {
		var windowHeight = window.innerHeight;
		var scrollY = window.scrollY || window.pageYOffset;

		var scrollPosition = scrollY + windowHeight;
		var elementPosition = element.getBoundingClientRect().top + scrollY + elementHeight;

		if (scrollPosition > elementPosition) {
			return true;
		}
		return false;
	}

	function animate() {
		if (inView()) {
			stickyElment.classList.add(
				'sticky-element--hidden');
		}
	}
}


