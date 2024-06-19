<template>
  <div class="container-fluid py-2">
    <div class="row">
      <div class="col-md-8 mx-auto">
        <div class=" mb-4">
          <h4>Notificações</h4>
        </div>
        <div class="card mt-1">
          <div v-for="(notif, index) in store.permaNotifs" :key="index" class="card-body p-3 pb-0">
            <material-alert class="font-weight-light" color="primary" dismissible>
              <span class="text-sm">{{ notif.descricao }}</span>
            </material-alert>
            <!-- <material-alert class="font-weight-light" color="secondary" dismissible>
              <span class="text-sm"
                >A simple secondary alert with
                <a href="javascript:;" class="alert-link text-white"
                  >an example link</a
                >. Give it a click if you like.</span
              >
            </material-alert>
            <material-alert class="font-weight-light" color="success" dismissible>
              <span class="text-sm"
                >A simple success alert with
                <a href="javascript:;" class="alert-link text-white"
                  >an example link</a
                >. Give it a click if you like.</span
              >
            </material-alert>
            <material-alert class="font-weight-light" color="danger" dismissible>
              <span class="text-sm"
                >A simple danger alert with
                <a href="javascript:;" class="alert-link text-white"
                  >an example link</a
                >. Give it a click if you like.</span
              >
            </material-alert>
            <material-alert class="font-weight-light" color="warning" dismissible>
              <span class="text-sm"
                >A simple warning alert with
                <a href="javascript:;" class="alert-link text-white"
                  >an example link</a
                >. Give it a click if you like.</span
              >
            </material-alert>
            <material-alert class="font-weight-light" color="info" dismissible>
              <span class="text-sm"
                >A simple info alert with
                <a href="javascript:;" class="alert-link text-white"
                  >an example link</a
                >. Give it a click if you like.</span
              >
            </material-alert>
            <material-alert class="font-weight-light" color="light" dismissible>
              <span class="text-sm"
                >A simple light alert with
                <a href="javascript:;" class="alert-link text-white"
                  >an example link</a
                >. Give it a click if you like.</span
              >
            </material-alert>
            <material-alert color="dark" dismissible>
              <span class="text-sm"
                >A simple dark alert with
                <a href="javascript:;" class="alert-link text-white"
                  >an example link</a
                >. Give it a click if you like.</span
              >
            </material-alert> -->
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
        console.log("Notificacoes carregadas:", store.permaNotifs);
      } catch (err) {
        if (err.message.includes('token')) {
          alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

          localStorage.clear();
          sessionStorage.clear();

          router.push('/sign-in');
        } else {
          alert("Erro -> " + err.message);
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
