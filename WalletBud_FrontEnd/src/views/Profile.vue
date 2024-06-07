<template>
  <div class="container-fluid">
    <div
      class="page-header min-height-300 border-radius-xl mt-4"
      style="
        background-image: url('https://images.unsplash.com/photo-1531512073830-ba890ca4eba2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1920&q=80');
      "
    >
      <span class="mask bg-gradient-info opacity-0"></span>
    </div>
    <div class="card card-body mx-3 mx-md-4 mt-n6">
      <div class="row gx-4">
        <div class="col-auto">
          <div class="avatar avatar-xl position-relative">
            <img
              src="@/assets/img/icons/user.png"
              alt="profile_image"
              class="shadow-sm w-100 border-radius-lg"
            />
          </div>
        </div>
        <div class="col-auto my-auto">
          <div class="h-100">
            <h5 class="mb-1"> {{ profileInfo.nome }} </h5>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="mt-3 row">  
          
          
          <div class="col-12 col-md-6 col-xl-4 mt-md-0 mt-4 position-relative">
            
            <profile-info-card 
              :info="profileInfo" 
              @saveProfile="saveProfile" 
            />

            <hr class="vertical dark" />
          </div>
          

          <div class="col-12 col-md-6 col-xl-4 position-relative">
            
              <div class="p-3 pb-0 card-header">
                <h6 class="mb-0">{{ $t ('Configurações de Movimentos') }}</h6>
              </div>
              <div class="p-3 card-body">
                <button class="btn" data-bs-toggle="modal" data-bs-target="#categoryModal" style="background-color: #007bff; color: white;">
                  {{ $t ('Gerir Categorias')}}
                </button>
                <PopUpGerirCategorias />
              </div>
            <hr class="vertical dark" />
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProfileInfoCard from "./components/ProfileInfoCard.vue";
import PopUpGerirCategorias from "./components/PopUpGerirCategorias.vue";

import setNavPills from "@/assets/js/nav-pills.js";
import setTooltip from "@/assets/js/tooltip.js";

import { useI18n } from 'vue-i18n';



export default {
  name: "profile-overview",
  components: {
    ProfileInfoCard,
    PopUpGerirCategorias,
  },
  setup() {
    const { t } = useI18n();
    return { t };
  },
  data() {
    return {
      showMenu: false,
      showPopup: false,
      showMessage: false,
      //Estão por default para teste
      profileInfo: {
        nome: 'Alec M. Thompson',
        email: 'alecthompson@gmail.com',
        senha: '123456',
      },
    };
  },
  mounted() {
    this.$store.state.isAbsolute = true;
    setNavPills();
    setTooltip();
  },
  beforeUnmount() {
    this.$store.state.isAbsolute = false;
  },
  methods: {
    openPopup() {
      this.showPopup = true;
    },
    closePopup() {
      this.showPopup = false;
    },
    handleEditProfile() {
      this.showMessage = true;
    },
    saveProfile(newProfile){
      console.log('Novo perfil recebido:', newProfile);
      this.profileInfo = {...newProfile};
    
    },
  },
};
</script>
