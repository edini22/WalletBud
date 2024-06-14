<template>
  <div
    class="w-auto h-auto collapse navbar-collapse max-height-vh-100 h-100"
    id="sidenav-collapse-main"
  >
    <ul class="navbar-nav">
      <li class="nav-item">
        <sidenav-collapse
          url="#"
          :aria-controls="''"
          v-bind:collapse="false"
          collapseRef="home"
          navText="Home"
        >
          <template v-slot:icon>
            <i class="material-icons-round opacity-10 fs-5">home</i>
          </template>
        </sidenav-collapse>
      </li>
      <li class="nav-item">
        <sidenav-collapse
          url="#"
          :aria-controls="''"
          v-bind:collapse="false"
          collapseRef="schedule"
          :navText= "$t('Agenda')"
        >
          <template v-slot:icon>
            <i class="material-icons-round opacity-10 fs-5">table_view</i>
          </template>
        </sidenav-collapse>
      </li>
      <li class="nav-item">
        <sidenav-collapse
          url="#"
          :aria-controls="''"
          v-bind:collapse="false"
          collapseRef="payments"
          :navText= "$t('Pagamentos')"
        >
          <template v-slot:icon>
            <i class="material-icons-round opacity-10 fs-5">table_view</i>
          </template>
        </sidenav-collapse>
      </li>
      <li class="nav-item">
        <sidenav-collapse
          url="#"
          :aria-controls="''"
          v-bind:collapse="false"
          collapseRef="billing"
          :navText="$t('Estatísticas')"
        >
          <template v-slot:icon>
            <i class="material-icons-round opacity-10 fs-5">receipt_long</i>
          </template>
        </sidenav-collapse>
      </li>
      <li class="nav-item">
        <sidenav-collapse
          url="#"
          :aria-controls="''"
          v-bind:collapse="false"
          collapseRef="notifications"
          :navText= "$t('Notificações')"
        >
          <template v-slot:icon>
            <i class="material-icons-round opacity-10 fs-5">notifications</i>
          </template>
        </sidenav-collapse>
      </li>
      <li class="mt-3 nav-item">
        <h6
          class="text-xs ps-4 text-uppercase font-weight-bolder text-white"
          :class="$store.state.isRTL ? 'me-4' : 'ms-2'"
        >
        {{ $t('Páginas da Conta') }}
        </h6>
      </li>
      <li class="nav-item">
        <sidenav-collapse
          url="#"
          :aria-controls="''"
          v-bind:collapse="false"
          collapseRef="profile"
          :navText= "$t('Perfil')"
        >
          <template v-slot:icon>
            <i class="material-icons-round opacity-10 fs-5">person</i>
          </template>
        </sidenav-collapse>
      </li>
    </ul>
    
    <div class="sidenav-footer position-absolute w-100 bottom-0">
      <div class="mx-3" v-if="!isModalVisible">
        <a
          class="btn mt-4 w-100"
          :class="`bg-gradient-${this.$store.state.color}`"
          @click="showModal"
          >{{ $t('Terminar') }} {{ $t('Sessão ') }}</a
        >
      </div>
      <div>
        <confirm 
        :isVisible="isModalVisible" 
        :message="$t('Tem a certeza?')"
        @confirm="logOut"
        @cancel="handleCancel"/>
      </div>

      

    </div>
  </div>
</template>

<script>
import SidenavCollapse from "./SidenavCollapse.vue";
import { userStore } from "@/store/userStore";
import { categoriesStore } from "@/store/categoriesStore";
import { useRouter } from "vue-router";
import confirm from "@/views/components/ActionConfirm.vue";
import { useI18n } from "vue-i18n";

export default {
  name: "SidenavList",
  props: {
    cardBg: String,
  },
  setup(){
    const store = userStore();
    const router = useRouter();
    const { t } = useI18n();

    const logOut = async () => {
      await store.logOut();
      await categoriesStore().resetCategories();
      router.push({ name: "SignIn" });
    };
    return {
      logOut,
      t,
    };

  },
  
  data() {
    return {
      title: "Soft UI Dashboard PRO",
      controls: "dashboardsExamples",
      isActive: "active",
      isModalVisible: false,
    };
  },
  components: {
    SidenavCollapse,
    confirm,
  },
  methods: {
    showModal() {
      this.isModalVisible = true;
    },
    handleCancel() {
      this.isModalVisible = false;
    },
  },
};
</script>
