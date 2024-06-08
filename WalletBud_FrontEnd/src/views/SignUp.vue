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
                    <h4 class="font-weight-bolder">Regista-te!</h4>
                    <Configurator />
                    <p class="mb-0">
                      Insere o teu email e password para registar
                    </p>
                  </div>
                  <div class="card-body">
                    <form role="form">
                      <!--Nome-->
                      <div v-if="nameError === true" class="mb-3">
                        <material-input
                          id="name"
                          type="text"
                          label="Nome"
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
                          label="Nome"
                          name="name"
                          size="lg"
                          @update:value="name = $event"
                        />
                      </div>
                      
                      <!--Email-->
                      <div v-if="emailError === true" class="mb-3">
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
                      <div v-if="emailError === false" class="mb-3">
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
                      <!--Posso por label-->
                      <div v-if="emailError === null" class="mb-3">
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
                          label="Senha"
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
                          label="Senha"
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
                        Eu concordo com os
                        <a
                          href="../../../pages/privacy.html"
                          class="text-dark font-weight-bolder"
                          >Termos e Condições</a
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
                          >Registar</material-button
                        >
                      </div>
                    </form>
                  </div>
                  <div class="px-1 pt-0 text-center card-footer px-lg-2">
                    <p class="mx-auto mb-4 text-sm">
                      Já tens conta?
                      <router-link
                        :to="{ name: 'SignIn' }"
                        class="text-info text-gradient font-weight-bold"
                        >LogIn</router-link
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
import { ref } from 'vue';
import { useRouter } from "vue-router";

import Configurator from "@/examples/ConfiguratorV2.vue";

export default {
  name: "sign-up",
  components: {
    MaterialInput,
    MaterialCheckbox,
    MaterialButton,
    Configurator,
  },
  setup() {
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

    const userSignUp = async () => {
      //teste
      //router.push({ name: "SignIn" });

      alert("email: " + email.value + " password: " + password.value + " name: " + name.value + " termsAccepted: " + termsAccepted.value)

      let isValid = true;

      if (!termsAccepted.value) {
        error.value = "Tem de aceitar os termos e condições";
        alert(error.value);
        return;
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
        isValid = false;
      } else {
        passwordError.value = false;
      }
      alert("passdone" + passwordError.value)

      if (!isValid) {
        error.value = "Preencha todos os campos";
        alert(error.value);
        return;
      }

      


      error.value = null;

      const newUser = {
        email: email.value,
        senha: password.value,  
        name: name.value,
      };

      try {
        await store.registUser(newUser);
        alert("Registado com sucesso");
        router.push({ name: "SignIn" });
      } catch (err) {
        error.value = "Erro ao registar";
        alert(error.value);
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
    };
  },
  beforeMount() {
    this.toggleEveryDisplay();
    this.toggleHideConfig();
    body.classList.remove("bg-gray-100");
  },
  beforeUnmount() {
    this.toggleEveryDisplay();
    this.toggleHideConfig();
    body.classList.add("bg-gray-100");
  },
  methods: {
    ...mapMutations(["toggleEveryDisplay", "toggleHideConfig"]),
  },
};
</script>