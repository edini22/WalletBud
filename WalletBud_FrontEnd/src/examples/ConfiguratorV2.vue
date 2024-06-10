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
            <h5 class="mt-3 mb-0">{{$t ('Configurações')}}</h5>
            <p>{{$t ('Personaliza a plataforma')}}.</p>
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
          
          <!-- Sidenav Type -->
          
  
          <!-- Navbar Fixed -->
          <hr class="horizontal dark my-3" />
          <div class="mt-2 d-flex">
            <h6 class="mb-0">{{$t ('Modo Claro')}} / {{$t ('Modo Escuro')}}</h6>
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
          <div class="d-flex" style="align-items: center; justify-content: space-between;">
            <h6 class="mb-0" >{{$t ('Escolhe o Idioma')}}</h6>
            <!-- dropdown menu -->
            <LanguageSwitcher />
          </div>
          <hr class="horizontal dark my-sm-4" />
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import { mapMutations, mapState, mapActions } from "vuex";
  import { activateDarkMode, deactivateDarkMode } from "@/assets/js/dark-mode";
  import LanguageSwitcher from "../views/components/LanguageSwitcher.vue";
  
  
  export default {
    name: "configurator",
    props: ["toggle"],
    methods: {
      ...mapMutations(["navbarMinimize", "navbarFixed"]),
      ...mapActions(["setColor"]),
  
      
  
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
    components: {
      LanguageSwitcher,
    },
  };
  </script>
  