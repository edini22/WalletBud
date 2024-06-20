<template>
  <div class="container-fluid py-2">
    <div class="row">
      <div class="col-md-8 mx-auto">
        <div class=" mb-4">
          <h4>Notificações</h4>
        </div>
        <div class="card mt-1">
          <div v-for="(notif, index) in store.permaNotifs" :key="index" class="card-body p-3 pb-0">
            <material-alert class="font-weight-bold" color="info" dismissible>
              <div class="d-flex flex-column justify-content-center">
                <!-- <h6 class="mb-1 text-sm font-weight-normal"> -->
                <span class="font-weight-bold">{{ notif.descricao }}</span>
                <!-- </h6> -->
                <p class="mb-0 text-xs text-secondary">
                  <i class="fa fa-clock me-1"></i>
                  {{ notif.date }}
                </p>
              </div>
            </material-alert>

          </div>

        </div>
      </div>
    </div>
    <div class="position-fixed bottom-1 end-1 z-index-2">
      <material-snackbar v-if="snackbar === 'success'" title="Material Dashboard" date="11 mins ago"
        description="Hello, world! This is a notification message." :icon="{ component: 'done', color: 'white' }"
        color="success" :close-handler="closeSnackbar" />
      <material-snackbar v-if="snackbar === 'info'" title="Material Dashboard" date="11 mins ago"
        description="Hello, world! This is a notification message."
        :icon="{ component: 'notifications', color: 'white' }" color="info" :close-handler="closeSnackbar" />
      <material-snackbar v-if="snackbar === 'warning'" title="Material Dashboard" date="11 mins ago"
        description="Hello, world! This is a notification message."
        :icon="{ component: 'travel_explore', color: 'white' }" color="warning" :close-handler="closeSnackbar" />
      <material-snackbar v-if="snackbar === 'danger'" title="Material Dashboard" date="11 mins ago"
        description="Hello, world! This is a notification message." :icon="{ component: 'campaign', color: 'white' }"
        color="danger" :close-handler="closeSnackbar" />
    </div>
  </div>
</template>

<script>
import MaterialAlert from "@/components/MaterialAlert.vue";
import MaterialSnackbar from "@/components/MaterialSnackbar.vue";

import { onMounted } from "vue";
import { userStore } from "@/store/userStore";
import { useRouter } from 'vue-router';

export default {
  name: "Notifications",
  components: {
    MaterialAlert,
    MaterialSnackbar,
  },
  setup() {
    const store = userStore();
    const router = useRouter();

    onMounted(() => {
      loadNotifications();
    });

    const loadNotifications = async () => {
      try {
        await store.getPermaNotifs();
        // console.log("Notificacoes carregadas:", store.permaNotifs);
      } catch (err) {
        if (err.message.includes('token')) {
          alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

          localStorage.clear();
          sessionStorage.clear();

          router.push('/sign-in');
        }
      }
    };

    return {
      store,
      loadNotifications,
    };

  },
  data() {
    return {
      snackbar: null,
    };
  },
  methods: {
    closeSnackbar() {
      this.snackbar = null;
    },
  },
};
</script>
<style scoped>
.text-secondary {
  color: white !important;
}
</style>