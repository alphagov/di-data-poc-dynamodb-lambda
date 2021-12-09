function setCookiePolicy(val) {
  return {
    essential: true,
    settings: val,
    usage: val,
    campaigns: val
  }
}

var gACookie = getCookie('_ga')
var getCookiesPolicy = getCookie('cookies_policy')
var getCookiesPreferences = getCookie('cookies_preferences_set')
var cookieBanner = document.getElementById("js-cookie-banner");
var cookieConfirmationBanner = document.getElementById("js-cookie-confirmation-banner");
var cookieConfirmationContent = document.getElementById("js-cookie-confirmation-content");
var cookieConfirmationSettings = document.getElementById("js-cookie-settings-confirmation");

if (gACookie === null) {
  deleteCookie('_ga_VEJ9HFN93E')
}

function getCookie(name) {
  var cookieArr = document.cookie.split(";");
  for (var i = 0; i < cookieArr.length; i++) {
    var cookiePair = cookieArr[i].split("=");

    if (name == cookiePair[0].trim()) {
      return decodeURIComponent(cookiePair[1]);
    }
  }
  return null;
}

function deleteCookie(name) {
  createCookie(name, '', -1)
}

function createCookie(name, value, days) {
  if (days) {
    var date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    var expires = "; expires=" + date.toGMTString();
  }
  else var expires = "";
  var hostName = window.location.hostname
  document.cookie = name + "=" + value + expires + ";" + 'path=/; domain=' + hostName + '; SameSite=None;Secure;';
}

if (getCookiesPolicy === null) {
  createCookie('cookies_policy', JSON.stringify(setCookiePolicy(false)), 365)
}

if (getCookiesPreferences === null) {
  cookieBanner.classList.remove('hide');
}


function cookiesAccepted() {
  createCookie('cookies_preferences_set', JSON.stringify(true), 365)
  createCookie('cookies_policy', JSON.stringify(setCookiePolicy(true)), 365)
  addGaCookie()

  var content = 'You’ve accepted additional cookies. You can ' + '<a class="govuk-link" href="/cookies">change your cookie settings</a>' + ' at any time.';
  cookieConfirmationContent.insertAdjacentHTML('beforeend', content);

  cookieBanner.classList.add('hide');
  cookieConfirmationBanner.classList.remove('hide');
}

function cookiesRejected() {
  createCookie('cookies_preferences_set', JSON.stringify(true), 365)
  var content = 'You’ve rejected additional cookies. You can ' + '<a class="govuk-link" href="/cookies">change your cookie settings</a>' + ' at any time.';
  cookieConfirmationContent.insertAdjacentHTML('beforeend', content);
  cookieBanner.classList.add('hide');
  cookieConfirmationBanner.classList.remove('hide');
}

function hideConfirmationBanner() {
  cookieConfirmationBanner.classList.add('hide');
  return false
}

function addGaCookie() {
  window.dataLayer = window.dataLayer || [];
  function gtag() {
    dataLayer.push(arguments);
  }
  gtag("js", new Date());
  gtag("config", "G-VEJ9HFN93E");
}

function removeGoogleAnalytics() {
  deleteCookie('_ga')
  deleteCookie('_ga_VEJ9HFN93E')
  createCookie('cookies_policy', JSON.stringify(setCookiePolicy(false)), 365)
  createCookie('cookies_preferences_set', JSON.stringify(true), 365)
}

function acceptGoogleAnalytics() {
  createCookie('cookies_preferences_set', JSON.stringify(true), 365)
  createCookie('cookies_policy', JSON.stringify(setCookiePolicy(true)), 365)
  addGaCookie()
}

function saveCookieSetting() {
  var cookiesSet = document.getElementsByName('functional-cookies')
  Array.from(cookiesSet).map(function (radio) {
    if (radio.checked) {
      radio.value === 'yes' ? acceptGoogleAnalytics() : removeGoogleAnalytics()
    }
  });
  document.body.scrollTop = 0;
  document.documentElement.scrollTop = 0;
  cookieConfirmationSettings.classList.remove('hide')
}



  // hitting reject we set cookies_preferences_set true keeping cookies_policy the same
  // hitting accept we set cookies_preferences_set true making all cookies_policy true showing GA
