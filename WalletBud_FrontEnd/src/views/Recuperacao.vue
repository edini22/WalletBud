<template>
    <div
      class="page-header align-items-start min-vh-100"
      style="
        background-image: url('https://images.unsplash.com/photo-1497294815431-9365093b7331?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1950&q=80');
      ">

<span class="mask bg-gradient-dark opacity-0"></span>
    <div class="container my-auto">
      <div class="row">
        <div class="col-lg-4 col-md-8 col-12 mx-auto">
          <div class="card z-index-0 fadeIn3 fadeInBottom">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
              <div
                class="bg-gradient-info shadow-success border-radius-lg py-3 pe-1"
              >
                
                <h4  class="text-white font-weight-bolder text-center mt-2 mb-0">
                  {{$t('Recuperação de Password')}}
                </h4>
              </div>
            </div>
            <div class="card-body">
              <form role="form" class="text-start mt-3">
                <!--Password1---->
                <div v-if="passwordError === true" class="mb-3">
                  <material-input
                    id="password"
                    type="password"
                    :label= "passwordErrorMessages"
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

                <!--Password2---->
                <div v-if="passwordError === true" class="mb-3">
                  <material-input
                    id="password2"
                    type="password"
                    :label= "passwordErrorMessages"
                    name="password2"
                    @update:value="password2 = $event"
                    error
                  />
                </div>
                <div v-if="passwordError === false" class="mb-3">
                  <material-input
                    id="password2"
                    type="password"
                    name="password2"
                    :value="password2"
                    @update:value="password2 = $event"
                    success
                  />
                </div>
                <div v-if="passwordError === null" class="mb-3">
                  <material-input
                    id="password2"
                    type="password"
                    :label="t('Confirmar Password')"
                    name="password2"
                    @update:value="password2 = $event"
                  />
                </div>

                <div class="text-center d-flex justify-content-between">
                    <material-button
                        class="btn btn-md"
                        variant="gradient"
                        color="secondary"
                        @click = "router.push('/sign-in')"
                        >{{$t('Cancelar')}}
                    </material-button>
                    

                    <material-button
                        class="btn btn-md"
                        variant="gradient"
                        color="info"
                        @click.prevent = "verificarPasswords"
                        >{{$t('Recuperar')}}
                    </material-button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="position-fixed top-1 end-1 z-index-2">
        <material-snackbar
            v-if="snackbar === 'error'"
            :title="t('Recuperação de Password')"
            date="now"
            :description = "passwordErrorMessages"
            :icon="{ component: 'campaign', color: 'white' }"
            color="danger"
            :close-handler="closeSnackbar"
        />
        <material-snackbar
            v-if="snackbar === 'success'"
            :title="t('Recuperação de Password')"
            date="now"
            :description="t('Password recuperada com sucesso')"
            :icon="{ component: 'done', color: 'white' }"
            color="success"
            :close-handler="closeSnackbar"
        />
    </div>
    </div>
</template>
  
<script>
  import MaterialInput from "@/components/MaterialInput.vue";
  import MaterialButton from "@/components/MaterialButton.vue";
  import MaterialSnackbar from "@/components/MaterialSnackbar.vue";
  import { mapMutations } from "vuex";
  import { ref } from 'vue';
  import { useRouter, useRoute } from "vue-router";
  import { useI18n } from "vue-i18n";

  
  export default {
    name: "Recuperacao",
    setup(){
      const router = useRouter(); 
      const route = useRoute(); 
      const { t } = useI18n();
      const password = ref("");
      const password2 = ref("");
      const passwordError = ref(null);
      const passwordErrorMessages = ref("");
      const snackbar = ref(null);

      const closeSnackbar = () => {
          snackbar.value = false;
      };

      const resetPassword = async () => {

        const url =
          "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/reset_password/set";
        
        const request = {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            email: route.params.email,
            token: route.params.token,
            password: password.value,
          }),
        };

     
        const response = await fetch(url, request);

        if (!response.ok) {
          const errorData = await response.json();
          throw new Error(errorData.message);
        }

        const data = await response.json();
        console.log(data);


        

      };

        const verificarPasswords = () => {
            if (password.value === password2.value) {
                if (password.value === "" || password2.value === "") {
                    passwordError.value = true; 
                    passwordErrorMessages.value = `${t('Password não pode ser nula')}`;

                    snackbar.value = 'error';
                    setTimeout(() => {
                        snackbar.value = null;
                    }, 2000);
                    return;
                }else if(password.value.length < 6){
                    passwordError.value = true;
                    passwordErrorMessages.value = `${t('Passwords devem ter no mínimo')}` + " 6 " +  `${t('caracteres')}`;

                    snackbar.value = 'error';
                    setTimeout(() => {
                        snackbar.value = null;
                    }, 2000);
                    return;
                } else {

                    try {
                      resetPassword();
                    } catch (error) {
                      passwordError.value = true;
                      passwordErrorMessages.value = "Recuperação de password falhou";

                      snackbar.value = 'error';
                      setTimeout(() => {
                          snackbar.value = null;
                      }, 2000);
                      return;
                    }
                    snackbar.value = 'success';
                    setTimeout(() => {
                        snackbar.value = null;
                        router.push('/sign-in');
                    }, 2000);
                    passwordError.value = false;
                }
            } else {
                passwordError.value = true;
                passwordErrorMessages.value = `${t('Passwords não coincidem')}`;

                snackbar.value = 'error';
                setTimeout(() => {
                    snackbar.value = null;
                }, 2000);
                return;
            }
        }
        
        return {
          t,
          router,
          route,
          password,
          password2,
          passwordError,
          resetPassword,
          verificarPasswords,
          passwordErrorMessages,
          snackbar,
          closeSnackbar,
        };
    },
    components: {
      MaterialInput,
      MaterialButton,
      MaterialSnackbar,
    
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
  
<!--Inserir 2 vezes a password-->