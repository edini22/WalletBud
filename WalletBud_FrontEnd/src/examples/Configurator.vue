<template>
  <div class="fixed-plugin">
    <a
      class="px-3 py-2 fixed-plugin-button text-dark position-fixed"
      @click="toggle"
    >
      <i class="material-icons py-2">settings</i>
    </a>
    <div class="shadow-lg card">
      <div class="pt-3 pb-0 card-header">
        <div class="float-start">
          <h5 class="mt-3 mb-0">Configurações</h5>
          <p>Personaliza a plataforma.</p>
        </div>
        <div class="mt-4 float-end" @click="toggle">
          <button class="p-0 btn btn-link text-dark fixed-plugin-close-button">
            <i class="material-icons">clear</i>
          </button>
        </div>
        <!-- End Toggle Button -->
      </div>
      <hr class="my-1 horizontal dark" />
      <div class="pt-0 card-body pt-sm-3">
        <!-- Sidebar Backgrounds -->
        <div>
          <h6 class="mb-0">Cores da Sidebar</h6>
        </div>
        <a href="#" class="switch-trigger background-color">
          <div
            class="my-2 badge-colors"
          >
            <span
              class="badge filter bg-gradient-primary"
              data-color="primary"
              @click="sidebarColor('primary')"
            ></span>
            <span
              class="badge filter bg-gradient-dark"
              data-color="dark"
              @click="sidebarColor('dark')"
            ></span>
            <span
              class="badge filter bg-gradient-info"
              data-color="info"
              @click="sidebarColor('info')"
            ></span>
            <span
              class="badge filter bg-gradient-success"
              data-color="success"
              @click="sidebarColor('success')"
            ></span>
            <span
              class="badge filter bg-gradient-warning"
              data-color="warning"
              @click="sidebarColor('warning')"
            ></span>
            <span
              class="badge filter bg-gradient-danger"
              data-color="danger"
              @click="sidebarColor('danger')"
            ></span>
          </div>
        </a>
        <!-- Sidenav Type -->
        <div class="mt-3">
          <h6 class="mb-0">Fundo da Aba de Vavegação</h6>
          <p class="text-sm">Escolhe entre 2 tipos.</p>
        </div>
        <div class="d-flex">
          <button
            id="btn-dark"
            class="px-3 mb-2 btn bg-gradient-dark"
            :class="sidebarType === 'bg-gradient-dark' ? 'active' : ''"
            @click="sidebar('bg-gradient-dark')"
          >
            Escura
          </button>
          <button
            id="btn-white"
            class="px-3 mb-2 btn bg-gradient-dark ms-2"
            :class="sidebarType === 'bg-white' ? 'active' : ''"
            @click="sidebar('bg-white')"
          >
            Branca
          </button>
        </div>
        <p class="text-sm d-xl-none d-block mt-2">
          Só podes alterar o tipo de Aba em vista desktop.
        </p>

        <!-- Navbar Fixed -->
        <hr class="horizontal dark my-3" />
        <div class="mt-2 d-flex">
          <h6 class="mb-0">Modo Claro / Modo Escuro</h6>
          <div class="form-check form-switch ps-0 ms-auto my-auto">
            <input
              class="form-check-input mt-1 ms-auto"
              type="checkbox"
              :checked="$store.state.isDarkMode"
              @click="darkMode"
            />
          </div>
        </div>
        <hr class="horizontal dark my-sm-4" />

        <!-- Change idiom -->
        <div class="mt-3">
          <h6 class="mb-0">Escolhe o Idioma</h6>
          <p class="text-sm"></p>
        </div>
        <div class="d-flex">
          <!-- dropdown menu -->
        </div>
        <hr class="horizontal dark my-sm-4" />
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState, mapActions } from "vuex";
import { activateDarkMode, deactivateDarkMode } from "@/assets/js/dark-mode";

export default {
  name: "configurator",
  props: ["toggle"],
  methods: {
    ...mapMutations(["navbarMinimize", "navbarFixed"]),
    ...mapActions(["setColor"]),

    sidebarColor(color = "success") {
      document.querySelector("#sidenav-main").setAttribute("data-color", color);
      this.setColor(color);
    },

    sidebar(type) {
      this.$store.state.sidebarType = type;
    },

    setNavbarFixed() {
      if (
        this.$route.name !== "Profile" ||
        this.$route.name !== "All Projects"
      ) {
        this.$store.state.isNavFixed = !this.$store.state.isNavFixed;
      }
    },

    darkMode() {
      if (this.$store.state.isDarkMode) {
        this.$store.state.isDarkMode = false;
        deactivateDarkMode();
        return;
      } else {
        this.$store.state.isDarkMode = true;
        activateDarkMode();
      }
    },

    sidenavTypeOnResize() {
      let white = document.querySelector("#btn-white");
      if (window.innerWidth < 1200) {
        white.classList.add("disabled");
      } else {
        white.classList.remove("disabled");
      }
    },
  },
  computed: {
    ...mapState(["sidebarType"]),
    sidenavResponsive() {
      return this.sidenavTypeOnResize;
    },
  },
  beforeMount() {
    window.addEventListener("resize", this.sidenavTypeOnResize);
    window.addEventListener("load", this.sidenavTypeOnResize);
  },
};
</script>
