<template>
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
                    :label= emailErrorMessages
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
                <div class="text-center">
                  <router-link
                    :to="{ name: 'Home' }"
                    class="mt-4 text-sm text-info text-gradient"
                    >{{$t('Esqueceu-se da password')}}?</router-link
                  >
                </div>
                <p class="mt-2 text-sm text-center">
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
    <div class="position-fixed top-1 end-1 z-index-2">
      <material-snackbar
        v-if="snackbar === 'success'"
        title="Iniciar Sessão"
        date="now"
        description="LogIn efetuado com sucesso!"
        :icon="{ component: 'done', color: 'white' }"
        color="success"
        :close-handler="closeSnackbar"/>
      <material-snackbar
        v-if="snackbar === 'error'"
        title="Iniciar Sessão"
        date="now"
        description="Erro ao efetuar o logIn!"
        :icon="{ component: 'campaign', color: 'white' }"
        color="danger"
        :close-handler="closeSnackbar"/>
    </div>
  </div>
</template>

<script>
import MaterialInput from "@/components/MaterialInput.vue";
import MaterialButton from "@/components/MaterialButton.vue";
import MaterialSnackbar from "@/components/MaterialSnackbar.vue";
import { mapMutations } from "vuex";
import { userStore } from "@/store/userStore";
import { ref, watch } from 'vue';
import { useRouter } from "vue-router";
import { useI18n } from "vue-i18n";

export default {
  name: "sign-in",
  components: {
    MaterialInput,
    MaterialButton,
    MaterialSnackbar,
  },
  setup() {
    const { t, locale } = useI18n();

    const router = useRouter(); 
    const store = userStore();
    const email = ref('');
    const password = ref('');
    const error = ref(null);
    const emailError = ref(null);
    const emailErrorMessages = ref(null);
    const passwordError = ref(null);
    const passwordErrorMessages = ref(null);

    const snackbar = ref(null);
    
    let state = false;

    function validarEmail(email) {
      const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
      return emailRegex.test(email);
    }

    watch(locale, () => {
      emailErrorMessages.value = `${t('Email inválido')}`;
      if (state){
        emailErrorMessages.value = `${t('Email não existe')}`;
        passwordErrorMessages.value = `${t('Password incorreta')}`;
      }
    });

    const login = async () => {
  
      let isValid = true;
      
      if (!validarEmail(email.value)) {
        emailError.value = true;
        emailErrorMessages.value = `${t('Email inválido')}`;
        isValid = false;
      } else {
        emailError.value = false;
      }
      

      if (!password.value) {
        passwordError.value = true;
        isValid = false;
      } else {
        passwordError.value = false;
      }

      if (!isValid) {
        snackbar.value = 'error';
        setTimeout(() => {
          snackbar.value = null;
        }, 2000);
        return;
      }

      const credentials = {
        email: email.value,
        password: password.value,
      };

      try {
        await store.logUser(credentials);
        await store.getUser();
        
        snackbar.value = 'success';
        
        setTimeout(() => {
          router.push({ name: "Home" });
        }, 2000);
    
      } catch (err) {
        if (err.message === "Email does not exist!"){
          emailError.value = true;
          emailErrorMessages.value = `${t('Email não existe')}`;
          
          snackbar.value = 'error';
          setTimeout(() => {
            snackbar.value = null;
          }, 2000);

          state = true;
          return;
        } else if (err.message === "password does not match!"){
          passwordError.value = true;
          passwordErrorMessages.value = `${t('Password incorreta')}`;
          
          snackbar.value = 'error';
          setTimeout(() => {
            snackbar.value = null;
          }, 2000);
          
          state = true;
          return;
        }
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
      emailErrorMessages,
      state,
      snackbar,
    }
  },
  beforeMount() {
    this.toggleEveryDisplay();
  },
  beforeUnmount() {
    this.toggleEveryDisplay();
  },
  methods: {
    ...mapMutations(["toggleEveryDisplay"]),
  },
};
</script>
