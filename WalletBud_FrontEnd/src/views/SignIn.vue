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
                  {{$t('Iniciar ')}}{{$t('Sessão')}}
                </h4>
              </div>
            </div>
            <div class="card-body">

              <!--Email---->
              <form  role="form" class="text-start mt-3">
                <div v-if="emailError === true" class="mb-3">
                  <material-input
                    id="email"
                    type="email"
                    label="Email incorreto"
                    name="email"
                    @update:value="email = $event"
                    error
                  />
                </div>
                <div v-if="emailError === false" class="mb-3">
                  <material-input
                    id="email"
                    type="email"
                    name="email"
                    :value="email"
                    @update:value="email = $event"
                    success
                  />
                </div>
                <div v-if="emailError === null" class="mb-3">
                  <material-input
                    id="email"
                    type="email"
                    label="Email"
                    name="email"
                    @update:value="email = $event"
                  />
                </div>

                <!--Password---->
                <div v-if="passwordError === true" class="mb-3">
                  <material-input
                    id="password"
                    type="password"
                    :label="t('Password incorreta')"
                    name="password"
                    @update:value="password = $event"
                    error
                  />
                </div>
                <div v-if="passwordError === false" class="mb-3">
                  <material-input
                    id="password"
                    type="password"
                    
                    name="password"
                    :value="password"
                    @update:value="password = $event"
                    success
                  />
                </div>
                <div v-if="passwordError === null" class="mb-3">
                  <material-input
                    id="password"
                    type="password"
                    label="Password"
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
                    @click.prevent="login"
                    >{{$t('Entrar')}}</material-button
                  >
                </div>
                <p class="mt-4 text-sm text-center">
                  {{$t('Ainda não tens conta')}}?
                  <router-link
                    :to="{ name: 'SignUp' }"
                    class="text-info text-gradient font-weight-bold"
                    >{{$t('Registar')}}</router-link
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

<!--Falta verificação de email inválido-->


<script>
import MaterialInput from "@/components/MaterialInput.vue";
import MaterialButton from "@/components/MaterialButton.vue";
import { mapMutations } from "vuex";

import { userStore } from "@/store/userStore";
import { ref } from 'vue';
import { useRouter } from "vue-router";
import { useI18n } from "vue-i18n";


export default {
  name: "sign-in",
  components: {
    MaterialInput,
    MaterialButton,
  },
  setup() {
    const { t } = useI18n();

    const router = useRouter(); 
    const store = userStore();
    const email = ref('');
    const password = ref('');
    const error = ref(null);
    const emailError = ref(null);
    const passwordError = ref(null);
    
    function validarEmail(email) {
      const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
      return emailRegex.test(email);
    }

    const login = async () => {
      //teste
      // router.push({ name: "Home" });
      let isValid = true;

      if (!email.value) {
        emailError.value = true;
        isValid = false;
      } else {
        if (!validarEmail(email.value)) {
          emailError.value = true;
          isValid = false;
        } else {
          emailError.value = false;
        }
      }

      if (!password.value) {
        passwordError.value = true;
        isValid = false;
      } else {
        passwordError.value = false;
      }

      if (!isValid) {
        return;
      }

      const credentials = {
        email: email.value,
        password: password.value,
      };

      try {
        await store.logUser(credentials);
        alert("Login efetuado com sucesso");
        // buscar os dados do user
        await store.getUser();
        router.push({ name: "Home" });
      } catch (err) {
        alert(err.message);
      }

    }
    return{
      email,
      password,
      login,
      error,
      emailError,
      passwordError,
      t,
    }
  },
  beforeMount() {
    this.toggleEveryDisplay();
    //this.toggleHideConfig();
  },
  beforeUnmount() {
    this.toggleEveryDisplay();
    //this.toggleHideConfig();
  },
  methods: {
    ...mapMutations(["toggleEveryDisplay"]),
  },
};
</script>
