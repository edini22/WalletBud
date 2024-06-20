<template>
  <nav class="shadow-none navbar navbar-main navbar-expand-lg border-radius-xl" v-bind="$attrs" id="navbarBlur"
    data-scroll="true" :class="isAbsolute ? 'mt-4' : 'mt-0'">
    <div class="px-3 py-1 container-fluid">
      <breadcrumbs :currentPage="currentRouteName" :color="color" />
      <div class="mt-2 collapse navbar-collapse mt-sm-0 me-md-0 me-sm-4 px-0" id="navbar">
        <div class="pe-md-3 d-flex align-items-center ms-md-auto">
        </div>
        <ul class="navbar-nav justify-content-end">
          <li class="nav-item d-flex align-items-center">
            <router-link :to="{ name: 'Perfil' }" class="px-0 nav-link font-weight-bold lh-1 d-flex align-items-center"
              :class="color ? color : 'text-body'">
              <span class="username px-1 ">{{ store.username }}</span>
              <i class="material-icons me-sm-1 ">
                account_circle
              </i>
            </router-link>
          </li>
          <li class="nav-item d-xl-none ps-2 d-flex align-items-center">
            <a href="#" @click="toggleSidebar" class="p-0 nav-link text-body lh-1" id="iconNavbarSidenav">
              <div class="sidenav-toggler-inner">
                <i class="sidenav-toggler-line"></i>
                <i class="sidenav-toggler-line"></i>
                <i class="sidenav-toggler-line"></i>
              </div>
            </a>
          </li>


          <li class="nav-item dropdown d-flex align-items-center pe-3 px-2">
            <a href="#" class="p-0 nav-link lh-1" :class="[color ? color : 'text-body', showMenu ? 'show' : '']"
              id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false"
              @click="refresh(); showMenu = !showMenu;">
              <i class="material-icons cursor-pointer "> notifications </i>
            </a>
            <ul class=" px-2 py-3 dropdown-menu dropdown-menu-end
                me-sm-n4 secondary" :class="showMenu ? 'show' : ''" aria-labelledby="dropdownMenuButton">
              <div v-if="notifs && notifs.length > 0">
                <li v-for="(notif, index) in notifs" :key="index" class="mb-2">
                  <a class="dropdown-item border-radius-md" href="javascript:;">
                    <div class="py-1 d-flex">
                      <div class="my-auto">
                        <img src="../../assets/img/icons/user_1.png" class="avatar avatar-sm me-3" alt="user image" />
                      </div>
                      <div class="d-flex flex-column justify-content-center">
                        <h6 class="mb-1 text-sm font-weight-normal">
                          <span class="font-weight-bold">{{ notif.descricao }}</span> from
                          {{ notif.email }}
                        </h6>
                        <p class="mb-0 text-xs text-secondary">
                          <i class="fa fa-clock me-1"></i>
                          {{ notif.data }}
                        </p>
                      </div>
                      <!-- <button type="button" class="btn-close d-flex justify-content-center align-items-center"
                        data-bs-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true" class="text-lg font-weight-bold">&times;</span>
                      </button> -->
                    </div>
                  </a>
                </li>
              </div>
              <div v-else>
                <span id="sem" class="font-weight-bold"> Sem notificações </span>
              </div>
            </ul>
          </li>
          <li class="nav-item d-flex align-items-center ">
            <a href="#" class="p-0 nav-link lh-1" :class="color ? color : 'text-body'" @click="showModal">
              <i class="material-icons red-icon text-danger font-weight-bold me-sm-1">
                power_settings_new
              </i>
            </a>
          </li>


          <li v-if="isModalVisible">
            <confirm :isVisible="isModalVisible" :message="$t('Tem a certeza?')" @confirm="logOut"
              @cancel="handleCancel" />
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <div class="position-fixed top-1 end-1 z-index-8000">
    <!-- <div class="d-flex justify-content-start"> -->
    <!-- <material-snackbar v-if="snackbar === 'error'" :title="$t('Adicionar Categoria')" date="now"
      :description="$t('Não foi possível adicionar a categoria. Tente novamente.')"
      :icon="{ component: 'campaign', color: 'white' }" color="danger" :close-handler="closeSnackbar" />
    <material-snackbar v-if="snackbar === 'exists'" :title="$t('Adicionar Categoria')" date="now"
      :description="$t('Já existe um categoria com esse nome!')" :icon="{ component: 'campaign', color: 'white' }"
      color="danger" :close-handler="closeSnackbar" /> -->
    <material-snackbar v-if="snackbar === 'success'" :title="$t('Adicionar Categoria')" date="now"
      :description="tempnotif.descricao" :icon="{ component: 'notifications', color: 'white' }" color="info"
      :close-handler="closeSnackbar" :duration="3500" />
  </div>
</template>
<script>
import Breadcrumbs from "../Breadcrumbs.vue";
import { mapMutations, mapState } from "vuex";
import confirm from "@/views/components/ActionConfirm.vue";
import MaterialSnackbar from "@/components/MaterialSnackbar.vue";

import { useRouter } from "vue-router";
import { userStore } from "@/store/userStore";
import { useI18n } from "vue-i18n";
import { categoriesStore } from "@/store/categoriesStore";
import { fixaStore } from "@/store/fixaStore";

// import { WebSocketService } from "@/services/WebSocketService";
// import WebSocketService from '@/services/WebSocketService';

import { onMounted, ref } from "vue";


export default {
  name: "navbar",
  setup() {
    const store = userStore();
    const router = useRouter();
    const { t } = useI18n();
    // const websocketservice = ref(null);

    const notifs = ref(null);
    const snackbar = ref(null);

    const tempnotif = ref(null);

    const url = 'ws://localhost:8000/WalletBud-1.0-SNAPSHOT/notifs';
    let ws = null;
    let close = false;

    const closeSnackbar = () => {
      snackbar.value = 'false';
    };


    const connect = () => {
      ws = new WebSocket(url);

      ws.onopen = () => {
        // alert('WebSocket connected' + store.username);
        const token = localStorage.getItem('token');
        ws.send(token);
      };

      ws.onmessage = (event) => {
        const data = JSON.parse(event.data);
        // alert(JSON.stringify(data));

        tempnotif.value = data;

        store.addNotif(data);
        snackbar.value = 'success';
        // alert(store.notifs[store.notifs.length - 1].descricao);
        // this.onMessageCallback(data);
      };

      ws.onclose = () => {
        if (!close) {
          // alert('WebSocket disconnected');
          setTimeout(() => connect(), 500); // Reconnect after 1 second
        } else {
          // alert('WebSocket disconnected2');
        }
      };

      ws.onerror = (error) => {
        console.error('WebSocket error', error);
      };
    }

    const disconnect = () => {
      close = true;
      ws.close();
    }

    const initWebsocket = () => {
      // alert("init websocket")
      try {
        // websocketservice.value = new WebSocketService('ws://localhost:8000/WalletBud-1.0-SNAPSHOT/notifs', token);
        connect();
        notifs.value = store.notifs;
      } catch (err) {
        console.error(err);
      }
    };

    const refresh = () => {
      // alert("refresh")
      notifs.value = store.notifs;
    };

    onMounted(() => {
      if (store.email != "") {
        initWebsocket();
      }
    });

    const logOut = async () => {
      disconnect();
      await store.logOut();
      await categoriesStore().resetCategories();
      await fixaStore().resetFixas();
      router.push({ name: "SignIn" });
    };


    return {
      closeSnackbar,
      snackbar,
      connect,
      disconnect,
      url,
      ws,
      close,
      refresh,
      notifs,
      tempnotif,
      initWebsocket,
      logOut,
      t,
      store,
    };

  },
  data() {
    return {
      showMenu: false,
      isModalVisible: false,
    };
  },
  props: ["minNav", "color"],
  created() {
    this.minNav;
  },
  methods: {

    ...mapMutations(["navbarMinimize", "toggleConfigurator", "isDarkMode"]),

    toggleSidebar() {
      this.navbarMinimize();
    },

    showModal() {
      this.isModalVisible = true;
    },
    handleCancel() {
      this.isModalVisible = false;
    },

  },
  components: {
    Breadcrumbs,
    confirm,
    MaterialSnackbar,
  },
  computed: {
    ...mapState(["isAbsolute"]),

    currentRouteName() {
      const currentPage = this.$route.name;
      if (this.store.idioma === "pt") {
        return currentPage;
      } else if (this.store.idioma === "en") {
        if (currentPage !== undefined) {
          return this.t(currentPage);
        }
      }
      return currentPage;
    },
  },
};
</script>

<style scoped>
.red-icon {
  color: red !important;
}

/* .dropdown-menu{
  background-image: linear-gradient(195deg, #49a3f1 0%, red 100%);
}  */

h6,
.h6,
h5,
.h5,
h4,
.h4,
h3,
.h3,
h2,
.h2,
h1,
.h1 {
  margin-top: 0;
  margin-bottom: 0.5rem;
  font-weight: 400;
  line-height: 1.2;
  color: black;
}

.text-secondary {
  color: black !important;
}


#sem {
  color: black;
}

.z-index-8000 {
  z-index: 8000;
}
</style>