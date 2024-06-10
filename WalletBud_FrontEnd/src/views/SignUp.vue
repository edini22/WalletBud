<template>
  <div class="bg-white">
    
    <main class="mt-0 main-content">
      <section>
        <div class="page-header min-vh-100">
          <div class="container">
            <div class="row">
              <div
                class="col-6 d-lg-flex d-none h-100 my-auto pe-0 ps-0 position-absolute top-0 start-0 text-center justify-content-center flex-column"
              >
                <div
                  class="position-relative h-100 m-3 px-7 border-radius-lg d-flex flex-column justify-content-center"
                  :style="{
                    backgroundImage:
                      'url(' +
                      require('@/assets/img/illustrations/illustration-signin.jpg') +
                      ')',
                  }"
                ></div>
              </div>
              
              <div
                class="col-xl-4 col-lg-5 col-md-7 d-flex flex-column ms-auto me-auto ms-lg-auto me-lg-5"
              >
                <div class="card card-plain">
                  <div class="pb-0 card-header bg-transparent mb-4">
                    <h4 class="font-weight-bolder">{{ $t('Regista-te') }}!</h4>
                    
                    <p class="mb-0">
                      {{ $t('Insere o teu email e password para registar') }}
                    </p>
                  </div>
                  <div class="card-body">
                    <form role="form">
                      <!--Nome-->
                      <div v-if="nameError === true" class="mb-3">
                        <material-input
                          id="name"
                          type="text"
                          :label="$t ('Nome não pode estar vazio')"
                          labelColor="red"
                          name="name"
                          size="lg"
                          @update:value="name = $event"
                          error
                        />
                      </div>
                      <div v-if="nameError === false" class="mb-3">
                        <material-input
                          id="name"
                          type="text"
                          
                          name="name"
                          size="lg"
                          :value="name"
                          @update:value="name = $event"
                          success
                        />
                      </div>
                      <div v-if="nameError === null" class="mb-3">
                        <material-input
                          id="name"
                          type="text"
                          :label="$t ('Nome')"
                          name="name"
                          size="lg"
                          @update:value="name = $event"
                        />
                      </div>
                      
                      <!--Email-->
                      <div v-if="emailError === true && emailErrorStore === null" class="mb-3">
                        <material-input
                          
                          id="email"
                          type="email"
                          label="Email"
                          name="email"
                          size="lg"
                          @update:value="email = $event"
                          error
                        />
                      </div>
                      <div v-if="emailError === true && emailErrorStore !== null " class="mb-3">
                        <material-input
                          
                          id="email"
                          type="email"
                          :label= emailErrorStore
                          name="email"
                          size="lg"
                          @update:value="email = $event"
                          error
                          
                        />
                      </div>
                      <div v-if="emailError === false " class="mb-3">
                        <material-input
                          id="email"
                          type="email"
  
                          name="email"
                          size="lg"
                          :value="email"
                          @update:value="email = $event"
                          success
                        />
                      </div>
                      <div v-if="emailError === null " class="mb-3">
                        <material-input
                          id="email"
                          type="email"
                          label = "Email"
                          name="email"
                          size="lg"
                          @update:value="email = $event"
                        />
                      </div>

                      <!--Password-->
                      
                      
                      <div v-if= "passwordError===true" class="mb-3">
                        <material-input
                          id="password"
                          type="password"
                          :label= passwordErrorMessage
                          name="password"
                          size="lg"
                          @update:value="password = $event"
                          error
                        />
                      </div>
                      <div v-if ="passwordError===false" class="mb-3">
                        <material-input
                          id="password"
                          type="password"
                          name="password"
                          size="lg"
                          :value="password"
                          @update:value="password = $event"
                          success
                        />
                      </div>
                      <div v-if="passwordError===null" class="mb-3">
                        <material-input
                          id="password"
                          type="password"
                          label="Password"
                          name="password"
                          size="lg"
                          @update:value="password = $event"
                        />
                      </div>
                      

                      <material-checkbox
                        id="flexCheckDefault"
                        class="font-weight-light"
                        @update:checked="termsAccepted = $event"
                      >
                        <span v-if="checkedError === true" style="text-decoration: underline; "> {{ $t('Eu concordo com os ') }} </span>
                        <span v-if="checkedError === false">{{ $t('Eu concordo com os ') }}  </span>
                        <a v-if="checkedError === true"
                          href="terms-and-conditions"
                          :style =  "{color: 'red', fontWeight: 'bold', textDecoration: 'underline'}"
                          >{{$t('Termos e Condições')}}</a
                          
                        >
                        <a v-if="checkedError === false"
                          href="terms-and-conditions"
                          class="text-dark font-weight-bolder"
                          >{{$t('Termos e Condições')}}</a
                          
                        >
                      </material-checkbox>
                      <div class="text-center">
                        <material-button
                          class="mt-4"
                          variant="gradient"
                          color="info"
                          fullWidth
                          size="lg"
                          @click.prevent="userSignUp"
                          >{{$t('Registar')}}</material-button
                        >
                      </div>
                    </form>
                  </div>
                  <div class="px-1 pt-0 text-center card-footer px-lg-2">
                    <p class="mx-auto mb-4 text-sm">
                      {{$t('Já tens conta')}}?
                      <router-link
                        :to="{ name: 'SignIn' }"
                        class="text-info text-gradient font-weight-bold"
                        >{{$t('Iniciar ')}}{{$t('Sessão')}}</router-link
                      >
                    </p>
                  </div>
                </div>
                
              </div>
            </div>
          </div>
        </div>
        
      </section>
    </main>
  </div>
</template>

<script>
import MaterialInput from "@/components/MaterialInput.vue";
import MaterialCheckbox from "@/components/MaterialCheckbox.vue";
import MaterialButton from "@/components/MaterialButton.vue";
const body = document.getElementsByTagName("body")[0];
import { mapMutations } from "vuex";

import { userStore } from "@/store/userStore";
import { ref, watch } from 'vue';
import { useRouter } from "vue-router";
import { useI18n } from 'vue-i18n';



export default {
  name: "sign-up",
  components: {
    MaterialInput,
    MaterialCheckbox,
    MaterialButton,
  },
  setup() {
    const { t , locale } = useI18n();

    const router = useRouter(); 
    const store = userStore();
    const email = ref('');
    const password = ref('');
    const name = ref('');
    const termsAccepted = ref(false);
    const error = ref(null);
    const nameError = ref(null);
    const emailError = ref(null);
    const passwordError = ref(null);
    const passwordErrorMessage = ref(null);
    const emailErrorStore = ref(null);
    const checkedError = ref(false);

    watch(locale, () => {
      passwordErrorMessage.value =  `${t('Passwords devem ter no mínimo')} 6 ${t('caracteres')}`;
    });

    const userSignUp = async () => {
      //teste
      //router.push({ name: "SignIn" });

      //alert("email: " + email.value + " password: " + password.value + " name: " + name.value + " termsAccepted: " + termsAccepted.value)

      let isValid = true;

      if (!termsAccepted.value) {
        checkedError.value = true;
        isValid = false;
      }else{
        checkedError.value = false;
      }

      if (!name.value) {
        nameError.value = true;
        isValid = false;
      } else {
        nameError.value = false;
      }

      if (!email.value) {
        emailError.value = true;
        isValid = false;
      } else {
        emailError.value = false;
      }

      if (!password.value) {
        passwordError.value = true;
        passwordErrorMessage.value =  `${t('Passwords devem ter no mínimo')} 6 ${t('caracteres')}`;
        isValid = false;
      } else {
        passwordError.value = false;
      }
      alert("FALTA AQUI UMA COISA!")
      emailErrorStore.value = "Erro de email";

      if (!isValid) {
        //error.value = "Preencha todos os campos";
        //alert(error.value);
        return;
      }



      const newUser = {
        email: email.value,
        password: password.value,  
        username: name.value,
      };

      try {
        await store.registUser(newUser);
        // Se o registro for bem-sucedido, você pode mostrar uma mensagem ou redirecionar se necessário
        alert("Registado com sucesso");
        router.push({ name: "SignIn" }); // Redireciona para a página de login após registro
      } catch (error) {
        // Tratamento de erro específico para "Email Já registado!"
        if (error.message === "Email Já registado!") {
          alert("Este email já está registado. Utilize outro email.");
          emailErrorStore.value = "Este email já está registado";
          // Lógica adicional para manter o usuário na mesma página ou mostrar mensagem de erro
        } else if (error.message === "Email address is invalid") {
          alert("Email address is invalid");
          emailErrorStore.value = "Endereço de email inválido";
          // Lógica adicional para manter o usuário na mesma página ou mostrar mensagem de erro
        } else {
          alert("Erro ao registar: " + error.message);
        }
      }
    };

    return {
      email,
      password,
      name,
      termsAccepted,
      error,
      userSignUp,
      nameError,
      emailError,
      passwordError,
      passwordErrorMessage,
      emailErrorStore,
      checkedError,
      t,
    };
  },
  beforeMount() {
    this.toggleEveryDisplay();
    body.classList.remove("bg-gray-100");
  },
  beforeUnmount() {
    this.toggleEveryDisplay();
    //this.toggleHideConfig();
    body.classList.add("bg-gray-100");
  },
  methods: {
    ...mapMutations(["toggleEveryDisplay"]),
  },
};
</script>

<style scoped>
.term-error {
  color: red;
}
</style>