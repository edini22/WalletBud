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
            <h3 class="mb-1"> 
               {{ profileInfo.username }}
            </h3>
            
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
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProfileInfoCard from "./components/ProfileInfoCard.vue";

import setNavPills from "@/assets/js/nav-pills.js";
import setTooltip from "@/assets/js/tooltip.js";

import { useI18n } from 'vue-i18n';
import { userStore } from "@/store/userStore";


export default {
  name: "profile-overview",
  components: {
    ProfileInfoCard,
  },
  setup() {
    const { t } = useI18n();
    const store = userStore();
    const profileInfo = store.$state;
    return { t, profileInfo };
  },
  data() {
    return {
      showMenu: false,
      showPopup: false,
      showMessage: false,
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
  },
};
</script>
