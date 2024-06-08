<template>
  <!--<navbar btnBackground="bg-gradient-success" /> -->
  <div
    class="page-header align-items-start min-vh-100"
    style="
      background-image: url('https://images.unsplash.com/photo-1497294815431-9365093b7331?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1950&q=80');
    "
  >
    <span class="mask bg-gradient-dark opacity-6"></span>
    <div class="container my-auto">
      <div class="row">
        <div class="col-lg-4 col-md-8 col-12 mx-auto">
          <div class="card z-index-0 fadeIn3 fadeInBottom">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
              <div
                class="bg-gradient-info shadow-success border-radius-lg py-3 pe-1"
              >
                <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">
                  Entrar
                </h4>
              </div>
            </div>
            <div class="card-body">
              <form role="form" class="text-start mt-3">
                <div class="mb-3">
                  <material-input
                    id="email"
                    type="email"
                    label="Email"
                    name="email"
                    @update:value="email = $event"
                  />
                </div>
                <div class="mb-3">
                  <material-input
                    id="password"
                    type="password"
                    label="Senha"
                    name="password"
                    @update:value="password = $event"
                  />
                </div>
                
                <div class="text-center">
                  <material-button
                    class="my-4 mb-2"
                    variant="gradient"
                    color="info"
                    fullWidth
                    @click="login"
                    >Entrar</material-button
                  >
                </div>
                <p class="mt-4 text-sm text-center">
                  Ainda não tens conta?
                  <router-link
                    :to="{ name: 'SignUp' }"
                    class="text-info text-gradient font-weight-bold"
                    >Registar</router-link
                  >
                </p>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//import Navbar from "@/examples/PageLayout/Navbar.vue";
import MaterialInput from "@/components/MaterialInput.vue";
//import MaterialSwitch from "@/components/MaterialSwitch.vue";
import MaterialButton from "@/components/MaterialButton.vue";
import { mapMutations } from "vuex";

import { userStore } from "@/store/userStore";
import { ref } from 'vue';
import { useRouter } from "vue-router";

export default {
  name: "sign-in",
  components: {
    MaterialInput,
    MaterialButton,
  },
  setup() {
    const router = useRouter(); 
    const store = userStore();
    const email = ref('');
    const password = ref('');
    const error = ref(null);

    const login = async () => {
      //teste
      //router.push({ name: "Home" });

      const credentials = {
        email: email.value,
        password: password.value,
      };

      try {
        await store.login(credentials);
        router.push({ name: "Home" });
      } catch (err) {
        error.value = "Credenciais inválidas";
        console.log(error);
      }

    }
    return{
      email,
      password,
      login,
      error
    }
  },
  beforeMount() {
    this.toggleEveryDisplay();
    this.toggleHideConfig();
  },
  beforeUnmount() {
    this.toggleEveryDisplay();
    this.toggleHideConfig();
  },
  methods: {
    ...mapMutations(["toggleEveryDisplay", "toggleHideConfig"]),
  },
};
</script>
