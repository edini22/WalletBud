/* eslint-disable */

export function activateDarkMode() {
  const body = document.getElementsByTagName("body")[0];
  const modals = document.querySelectorAll(".modal-dialog.modal-dialog-centered");
  const values = document.querySelectorAll(".form-control");
  const configurators = document.querySelectorAll(".fixed-plugin-button");
  const icons = document.querySelectorAll(".material-icons", "comment");
  const inputGroups = document.querySelectorAll(".input-group");
  const hr = document.querySelectorAll("div:not(.sidenav) > hr");
  const hr_card = document.querySelectorAll("div:not(.bg-gradient-dark) hr");
  const text_btn = document.querySelectorAll("button:not(.btn) > .text-dark");
  const text_span = document.querySelectorAll(
    "span.text-dark, .breadcrumb .text-dark",
  );
  const text_strong = document.querySelectorAll("strong.text-dark");
  const text_nav_link = document.querySelectorAll("a.nav-link.text-dark");
  const secondary = document.querySelectorAll(".text-secondary");
  const bg_gray_100 = document.querySelectorAll(".bg-gray-100");
  const btn_text_dark = document.querySelectorAll(
    ".btn.btn-link.text-dark, .material-icons.text-dark",
  );
  const card_border = document.querySelectorAll(".card.border");
  const mainContent_blur = document.querySelectorAll(
    ".main-content .container-fluid .card",
  );
  const svg = document.querySelectorAll("g");

  body.classList.add("dark-version");
  
  modals.forEach(modal => {
    const modalContent = modal.querySelector(".modal-content");

    // Verifica se o modal tem a classe .modal-content
    if (modalContent) {
      modalContent.classList.add("text-white");
      modalContent.style.backgroundColor = "#202940";
    }
  });

  inputGroups.forEach(inputGroup => {
    const buttons = inputGroup.querySelectorAll("button");

    buttons.forEach(button => {
      button.style.color = "rgba(255, 255, 255, 0.8)";
    });
  });

  icons.forEach(icon => {
    icon.classList.add("text-white");
  });

  values.forEach(value => {
    value.classList.add("text-white");
  });

  configurators.forEach(configurator => {
    configurator.classList.add("bg-dark");
  });

  for (var i = 0; i < hr.length; i++) {
    if (hr[i].classList.contains("dark")) {
      hr[i].classList.remove("dark");
      hr[i].classList.add("light");
    }
  }
  for (var i = 0; i < mainContent_blur.length; i++) {
    if (mainContent_blur[i].classList.contains("blur")) {
      mainContent_blur[i].classList.remove("blur", "shadow-blur");
    }
  }
  for (var i = 0; i < hr_card.length; i++) {
    if (hr_card[i].classList.contains("dark")) {
      hr_card[i].classList.remove("dark");
      hr_card[i].classList.add("light");
    }
  }
  for (var i = 0; i < text_btn.length; i++) {
    if (text_btn[i].classList.contains("text-dark")) {
      text_btn[i].classList.remove("text-dark");
      text_btn[i].classList.add("text-white");
    }
  }
  for (var i = 0; i < text_span.length; i++) {
    if (text_span[i].classList.contains("text-dark")) {
      text_span[i].classList.remove("text-dark");
      text_span[i].classList.add("text-white");
    }
  }
  for (var i = 0; i < text_strong.length; i++) {
    if (text_strong[i].classList.contains("text-dark")) {
      text_strong[i].classList.remove("text-dark");
      text_strong[i].classList.add("text-white");
    }
  }
  for (var i = 0; i < text_nav_link.length; i++) {
    if (text_nav_link[i].classList.contains("text-dark")) {
      text_nav_link[i].classList.remove("text-dark");
      text_nav_link[i].classList.add("text-white");
    }
  }
  for (var i = 0; i < secondary.length; i++) {
    if (secondary[i].classList.contains("text-secondary")) {
      secondary[i].classList.remove("text-secondary");
      secondary[i].classList.add("text-white");
      secondary[i].classList.add("opacity-8");
    }
  }
  for (var i = 0; i < bg_gray_100.length; i++) {
    if (bg_gray_100[i].classList.contains("bg-gray-100")) {
      bg_gray_100[i].classList.remove("bg-gray-100");
      bg_gray_100[i].classList.add("bg-gray-600");
    }
  }
  for (var i = 0; i < btn_text_dark.length; i++) {
    btn_text_dark[i].classList.remove("text-dark");
    btn_text_dark[i].classList.add("text-white");
  }
  for (var i = 0; i < svg.length; i++) {
    if (svg[i].hasAttribute("fill")) {
      svg[i].setAttribute("fill", "#fff");
    }
  }
  for (var i = 0; i < card_border.length; i++) {
    card_border[i].classList.add("border-dark");
  }
}

export function deactivateDarkMode() {
  const body = document.getElementsByTagName("body")[0];
  const modals = document.querySelectorAll(".modal-dialog.modal-dialog-centered");
  const icons = document.querySelectorAll(".material-icons", "comment");
  const values = document.querySelectorAll(".form-control");
  const configurators = document.querySelectorAll(".fixed-plugin-button");
  const inputGroups = document.querySelectorAll(".input-group");
  const hr = document.querySelectorAll("div:not(.sidenav) > hr");
  const hr_card = document.querySelectorAll("div:not(.bg-gradient-dark) hr");
  const text_btn = document.querySelectorAll("button:not(.btn) > .text-dark");
  const text_span_white = document.querySelectorAll(
    "span.text-white, .breadcrumb .text-white",
  );
  const text_strong_white = document.querySelectorAll("strong.text-white");
  const text_nav_link_white = document.querySelectorAll(
    "a.nav-link.text-white",
  );
  const secondary = document.querySelectorAll(".text-secondary");
  const bg_gray_600 = document.querySelectorAll(".bg-gray-600");
  const btn_text_white = document.querySelectorAll(
    ".btn.btn-link.text-white, .material-icons.text-white",
  );
  const card_border_dark = document.querySelectorAll(
    ".card.border.border-dark",
  );
  const mainContent_blur = document.querySelectorAll(
    ".main-content .container-fluid .card",
  );
  const svg = document.querySelectorAll("g");

  body.classList.remove("dark-version");

  modals.forEach(modal => {
    const modalContent = modal.querySelector(".modal-content");

    // Verifica se o modal tem a classe .modal-content
    if (modalContent) {
      modalContent.classList.remove("text-white");
      modalContent.style.backgroundColor = "#fff";
    }
  });

  inputGroups.forEach(inputGroup => {
    const buttons = inputGroup.querySelectorAll("button");

    buttons.forEach(button => {
      button.style.color = "rgb(123, 128, 154)";
    });
  });

  values.forEach(value => {
    value.classList.remove("text-white");
  });

  configurators.forEach(configurator => {
    configurator.classList.remove("bg-dark");
  });

  for (var i = 0; i < hr.length; i++) {
    if (hr[i].classList.contains("light")) {
      hr[i].classList.add("dark");
      hr[i].classList.remove("light");
    }
  }
  for (var i = 0; i < hr_card.length; i++) {
    if (hr_card[i].classList.contains("light")) {
      hr_card[i].classList.add("dark");
      hr_card[i].classList.remove("light");
    }
  }
  for (var i = 0; i < mainContent_blur.length; i++) {
    mainContent_blur[i].classList.add("blur", "shadow-blur");
  }
  for (var i = 0; i < text_btn.length; i++) {
    if (text_btn[i].classList.contains("text-white")) {
      text_btn[i].classList.remove("text-white");
      text_btn[i].classList.add("text-dark");
    }
  }
  for (var i = 0; i < text_span_white.length; i++) {
    if (
      text_span_white[i].classList.contains("text-white") &&
      !text_span_white[i].closest(".sidenav") &&
      !text_span_white[i].closest(".card.bg-gradient-dark")
    ) {
      text_span_white[i].classList.remove("text-white");
      text_span_white[i].classList.add("text-dark");
    }
  }
  for (var i = 0; i < text_strong_white.length; i++) {
    if (text_strong_white[i].classList.contains("text-white")) {
      text_strong_white[i].classList.remove("text-white");
      text_strong_white[i].classList.add("text-dark");
    }
  }
  for (var i = 0; i < text_nav_link_white.length; i++) {
    if (
      text_nav_link_white[i].classList.contains("text-white") &&
      !text_nav_link_white[i].closest(".sidenav")
    ) {
      text_nav_link_white[i].classList.remove("text-white");
      text_nav_link_white[i].classList.add("text-dark");
    }
  }
  for (var i = 0; i < secondary.length; i++) {
    if (secondary[i].classList.contains("text-white")) {
      secondary[i].classList.remove("text-white");
      secondary[i].classList.remove("opacity-8");
      secondary[i].classList.add("text-dark");
    }
  }
  for (var i = 0; i < bg_gray_600.length; i++) {
    if (bg_gray_600[i].classList.contains("bg-gray-600")) {
      bg_gray_600[i].classList.remove("bg-gray-600");
      bg_gray_600[i].classList.add("bg-gray-100");
    }
  }
  for (var i = 0; i < svg.length; i++) {
    if (svg[i].hasAttribute("fill")) {
      svg[i].setAttribute("fill", "#252f40");
    }
  }
  for (var i = 0; i < btn_text_white.length; i++) {
    if (!btn_text_white[i].closest(".card.bg-gradient-dark")) {
      btn_text_white[i].classList.remove("text-white");
      btn_text_white[i].classList.add("text-dark");
    }
  }
  for (var i = 0; i < card_border_dark.length; i++) {
    card_border_dark[i].classList.remove("border-dark");
  }

  icons.forEach(icon => {
    icon.classList.remove("text-dark");
  });
}