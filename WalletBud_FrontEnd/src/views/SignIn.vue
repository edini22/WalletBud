<template>
  <div
    class="page-header align-items-start min-vh-100"
    style="
      background-image: url('https://images.unsplash.com/photo-1497294815431-9365093b7331?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1950&q=80');
    "
  >
    <span class="mask bg-gradient-dark opacity-0"></span>
    <div class="container my-auto">
      <div class="row">
        <div class="col-lg-4 col-md-8 col-12 mx-auto">
          <div class="card z-index-0 fadeIn3 fadeInBottom">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
              <div
                class="bg-gradient-info shadow-success border-radius-lg py-3 pe-1"
              >
                <h4 v-if="recuperarPassword === false" class="text-white font-weight-bolder text-center mt-2 mb-0">
                  {{$t('Iniciar ')}}{{$t('Sessão')}}
                </h4>
                <h4 v-if="recuperarPassword === true" class="text-white font-weight-bolder text-center mt-2 mb-0">
                  {{$t('Recuperar Password')}}
                </h4>
              </div>
            </div>
            <div class="card-body">

              <!--Email---->
              <form v-if="recuperarPassword === false " role="form" class="text-start mt-3">
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
                  <span
                    class="mt-4 text-sm text-info text-gradient"
                    @click="recuperarPassword = true"
                    style="cursor: pointer;">
                    {{$t('Esqueceu-se da sua password?')}}
                  </span>
                </div>

                <div v-if="recuperarPassword === true" class="mb-3">
                  <material-input
                    id="email"
                    type="email"
                    label="Email"
                    name="email"
                    @update:value="email = $event"
                  />
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
              <form v-if="recuperarPassword === true" role="form" class="text-start mt-3">
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
                    :label="t('Email de recuperação')"
                    name="email"
                    @update:value="email = $event"
                  />
                </div>

                <div class="text-center d-flex justify-content-between">
                  <material-button
                    class="btn btn-md "
                    variant="gradient"
                    color="secondary"
                    
                    @click ="voltar"
                    >{{$t('Voltar')}}</material-button
                  >

                  <material-button
                    v-if="!isButtonDisabled"
                    class="btn btn-md"
                    variant="gradient"
                    color="info"
                    @click.prevent="isButtonDisabled = true; recuperar();"
                    >{{$t('Recuperar')}}</material-button
                  >
                  <material-button
                    v-else
                    class="btn btn-md"
                    variant="gradient"
                    color="secondary"
                    >{{$t('Recuperar')}}</material-button
                  >

                </div>

                
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="position-fixed top-1 end-1 z-index-2">
      <material-snackbar
        v-if="snackbar === 'success'"
        :title="$t('Início de ') + ' ' + $t('Sessão')"
        date="now"
        :description= "$t('Início de sessão efetuado com sucesso')"
        :icon="{ component: 'done', color: 'white' }"
        color="success"
        duration= 900
        :close-handler="closeSnackbar"/>
      <material-snackbar
        v-if="snackbar === 'error'"
        :title="$t('Início de ') + ' ' + $t('Sessão')"
        date="now"
        :description= "$t('Erro ao iniciar sessão')"
        :icon="{ component: 'campaign', color: 'white' }"
        color="danger"
        :close-handler="closeSnackbar"/>
      <material-snackbar
        v-if="snackbar === 'success_recuperar'"
        :title="$t('Recuperação de Password')"
        date="now"
        :description= "$t('Email de recuperação enviado com sucesso! Verifique a sua caixa de correio!')"
        :icon="{ component: 'done', color: 'white' }"
        color="success"
        :close-handler="closeSnackbar"/>
      <material-snackbar
        v-if="snackbar === 'error2'"
        :title="$t('Recuperação de Password')"
        date="now"
        :description= "$t('Email inválido')"
        :icon="{ component: 'done', color: 'white' }"
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

    const recuperarPassword = ref(false);
    const isButtonDisabled = ref(false);

    const snackbar = ref(null);
    
    let state = false;



    const voltar = () => {
      router.push({ name: "SignIn" });
    }

    const closeSnackbar = () => {
      snackbar.value = false;
    };

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

    const recuperar = async () => {

      if (!validarEmail(email.value)) {
        recuperarPassword.value = true;
        emailErrorMessages.value = `${t('Email inválido')}`;
        emailError.value = true;

        snackbar.value = 'error2';
        setTimeout(() => {
          snackbar.value = null;
        }, 2000);
        return;
      } else {

        const url =
          "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/reset_password/send";

        const request = {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            email: email.value,
          }),
        };

        try {
          const response = await fetch(url, request);

          if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message);
          }

          
        } catch (error) {
          snackbar.value = 'error2';
          setTimeout(() => {
            snackbar.value = null;
          }, 2000);
          return;
        }

        store.email = email.value;
        
        recuperarPassword.value = false;
        emailError.value = false;
        snackbar.value = 'success_recuperar';

        setTimeout(() => {
          snackbar.value = null;
          router.push({ name: "SignIn" });
        }, 2000);
        
      }
      
    }
    

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
        }, 900);
    
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
      isButtonDisabled,
      error,
      emailError,
      passwordError,
      t,
      emailErrorMessages,
      state,
      snackbar,
      recuperarPassword,
      recuperar,
      closeSnackbar,
      voltar,
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
