<template>
  <div class="h-100">
    <div class="p-3 pb-0 card-header">
      <div class="row align-items-center">

        

        <div class="col-md-8 d-flex align-items-center ">
          <h4 class="mb-0">{{ $t('Informação do Perfil') }}</h4>
        </div>
      </div>
    </div>
    <div class="p-3 card-body">
      <ul class="list-group">
        <div v-if="!editPassword">
          <li class="pt-0 text-md border-0 list-group-item ps-0">
            <div v-if="showErrorNome">
              <p class="error-pass">{{ $t('Nome não pode ser nulo') }}</p>
            </div>

            <strong v-if="!editMode || !editPassword" class="text-secondary">{{ $t('Nome') }}:  </strong>
            <span v-if="!editMode">{{ username }} </span>
            <input v-else-if="showErrorNome" class="input-error" v-model="editedInfo.username" />
            <input v-else-if="editMode && !editPassword" v-model="editedInfo.username" class="full_width1" />
          </li>

          <li class="text-md border-0 list-group-item ps-0">
            <div v-if="showErrorEmail">
              <p class="error-pass">{{ $t('Email não pode ser nulo') }}</p>
            </div>
            <div v-if="showErrorEmail2">
              <p class="error-pass">{{ $t('Email inválido') }}</p>
            </div>

            <strong v-if="!editMode || !editPassword" class="text-secondary">{{ $t('Email') }}:  </strong>
            <span v-if="!editMode">{{ email }}</span>
            <input v-else-if="showErrorEmail" class="input-error" v-model="editedInfo.email" />
            <input v-else-if="editMode && !editPassword" v-model="editedInfo.email" class="full_width2" />
          </li>
        </div>


        <li class="text-md border-0 list-group-item ps-0">
          <div v-if="showError">
            <p class="error-pass">{{ $t('Passwords não coincidem') }}</p>
          </div>
          <div v-if="showError2">
            <p class="error-pass">{{ $t('Passwords devem ter no mínimo') }} 6 {{ $t('caracteres') }}</p>
          </div>
          <strong v-if="!editMode" class="text-secondary">Password:  </strong>
          <strong v-if="editMode && editPassword" class="text-secondary">{{ $t('Nova') }} Password:  </strong>
          <span v-if="!editMode">*********</span>
          <input v-else-if="showError" class="input-error" v-model="editedInfo.senha1" type="password" />
          <input v-else-if="showError2" class="input-error" v-model="editedInfo.senha1" type="password" />
          <input v-else-if="editMode && editPassword" v-model="editedInfo.senha1" type="password" class="full_width3" />
        </li>

        <li class="text-md border-0 list-group-item ps-0" v-if="editMode && editPassword">
          <strong class="text-secondary">{{ $t('Confirmar Password') }}:  </strong>
          <input v-if="showError" class="input-error" v-model="editedInfo.senha2" type="password" />
          <input v-else-if="showError2" class="input-error" v-model="editedInfo.senha2" type="password" />
          <input v-else-if="editMode && editPassword" v-model="editedInfo.senha2" type="password" class="full_width4" />
        </li>


      </ul>
      <div class="d-flex justify-content-between" v-if="editMode && editPassword">
        <material-button class="mt-4" variant="gradient" color="primary" size="md" @click="goBack">
          {{ $t('Voltar') }}
        </material-button>

        <material-button class="mt-4" variant="gradient" color="info" size="md" @click="saveNewPassword">
          {{ $t('Guardar') }}
        </material-button>
      </div>

      <div class="d-flex justify-content-between" v-if="editMode && !editPassword">
        <material-button class="mt-4" variant="gradient" color="primary" size="md" @click="goBack">
          {{ $t('Voltar') }}
        </material-button>

        <material-button class="mt-4" variant="gradient" color="info" size="md" @click="saveChanges">
          {{ $t('Guardar') }}
        </material-button>
      </div>
    </div>
    <div class="mb-4">
          <material-button v-if="!editMode" class="mb-2" variant="gradient" color="info" size="sm" @click="editProfile">
            {{ $t('Editar Perfil') }}
            <i data-bs-toggle="tooltip" data-bs-placement="top" :title="action.tooltip"></i>
          </material-button>
          &nbsp;
          <material-button v-if="!editMode" class="mb-2" variant="gradient" color="info" size="sm"
            @click="editPasswordFunc">
            {{ $t('Alterar Password') }}
            <i data-bs-toggle="tooltip" data-bs-placement="top" :title="action.tooltip"></i>
          </material-button>
        </div>

    <div class="position-fixed top-1 end-1 z-index-2">
      <material-snackbar v-if="snackbar === 'success'" title="Editar Perfil" date="now"
        description="Alterações guardadas com sucesso!" :icon="{ component: 'done', color: 'white' }" color="success"
        :close-handler="closeSnackbar" />
      <material-snackbar v-if="snackbar === 'error'" title="Editar Perfil" date="now"
        description="Erro a guardar alterações!" :icon="{ component: 'campaign', color: 'white' }" color="danger"
        :close-handler="closeSnackbar" />
    </div>
  </div>
</template>

<script>
import { userStore } from "@/store/userStore";
import { ref } from "vue";
import MaterialButton from "@/components/MaterialButton.vue";
import MaterialSnackbar from "@/components/MaterialSnackbar.vue";

import { useRouter } from 'vue-router';

export default {
  name: "ProfileInfoCard",
  props: {
    action: {
      type: Object,
      default: () => ({
        tooltip: '',
        route: 'javascript:;'
      })
    }
  },
  components: {
    MaterialButton,
    MaterialSnackbar,
  },
  setup() {
    const store = userStore();
    const username = ref('');
    const email = ref('');

    if (store) {
      username.value = store.$state.username;
      email.value = store.$state.email;
    }

    const editMode = ref(false);
    const showError = ref(false);
    const showError2 = ref(false);
    const showErrorNome = ref(false);
    const showErrorEmail = ref(false);
    const showErrorEmail2 = ref(false);
    const editedInfo = ref({ username: '', email: '', password: '' });
    const editPassword = ref(false);

    const router = useRouter(); 

    const snackbar = ref(null);

    function validarEmail(email) {
      const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
      return emailRegex.test(email);
    }

    const editPasswordFunc = () => {
      editPassword.value = true;
      editMode.value = true;
      editedInfo.value.senha1 = '';
      editedInfo.value.senha2 = '';
    };

    const editProfile = () => {
      editMode.value = true;
      editPassword.value = false;

      editedInfo.value.username = username.value;
      editedInfo.value.email = email.value;
    };

    const goBack = () => {
      showError.value = false;
      showError2.value = false;
      showErrorEmail.value = false;
      showErrorEmail2.value = false;
      showErrorNome.value = false;

      editMode.value = false;
      editPassword.value = false;
    };

    const saveNewPassword = async () => {
      if (checkPasswordsMatch()) {
        editedInfo.value.password = editedInfo.value.senha1;
        editedInfo.value.senha1 = '';
        editedInfo.value.senha2 = '';
        editedInfo.value.username = username.value;
        editedInfo.value.email = email.value;

        const user = {
          password: editedInfo.value.password,
        };

        try {
          await store.updateEditedUser(user);

          snackbar.value = 'success';
          setTimeout(() => {
            snackbar.value = null;
          }, 2000);

          if (store) {
            username.value = store.$state.username;
            email.value = store.$state.email;
          }
        } catch (error) {
          if (error.message.includes('token')) {
            alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

            localStorage.clear();
            sessionStorage.clear();

            router.push('/sign-in');
        } else {
          alert("Erro: " + error.message);
        }
        }

        editMode.value = false;
        editPassword.value = false;
      } else {
        snackbar.value = 'error';
        setTimeout(() => {
          snackbar.value = null;
        }, 2000);
      }
    };

    const saveChanges = async () => {
      if (checkUsername() && checkEmail()) {

        const user = {
          username: editedInfo.value.username,
          email: editedInfo.value.email,
        };

        try {
          await store.updateEditedUser(user);

          snackbar.value = 'success';
          setTimeout(() => {
            snackbar.value = 'null';
          }, 2000);
          if (store) {
            username.value = store.$state.username;
            email.value = store.$state.email;
          }
        } catch (error) {
          if (error.message.includes('token')) {
            alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

            localStorage.clear();
            sessionStorage.clear();

            router.push('/sign-in');
        } else {
          alert("Erro: " + error.message);
        }
        }

        editMode.value = false;
      } else {
        snackbar.value = 'error';
        setTimeout(() => {
          snackbar.value = null;
        }, 2000);
      }
    };

    const checkEmail = () => {
      if (!validarEmail(editedInfo.value.email)) {
        showErrorEmail2.value = true;
        return false;
      }
      if (editedInfo.value.email.length == 0) {
        showErrorEmail.value = true;
        return false;
      }
      showErrorEmail2.value = false;
      showErrorEmail.value = false;
      return true;
    };

    const checkUsername = () => {
      if (editedInfo.value.username.length == 0) {
        showErrorNome.value = true;
        return false;
      }
      showErrorNome.value = false;
      return true;
    };

    const checkPasswordsMatch = () => {
      if (editedInfo.value.senha1 !== editedInfo.value.senha2) {
        showError.value = true;
        showError2.value = false;
        return false;
      } else if (editedInfo.value.senha1 == '' || editedInfo.value.senha2 == '') {
        showError.value = false;
        showError2.value = true;
        return false;
      } else if (editedInfo.value.senha1.length < 6) {
        showError.value = false;
        showError2.value = true;
        return false;
      }
      showError.value = false;
      showError2.value = false;
      return true;
    };

    return {
      editMode,
      showError,
      showError2,
      showErrorEmail,
      showErrorEmail2,
      showErrorNome,
      editedInfo,
      editProfile,
      saveChanges,
      username,
      email,
      goBack,
      saveNewPassword,
      checkPasswordsMatch,
      checkEmail,
      checkUsername,
      editPasswordFunc,
      editPassword,
      snackbar,
    };
  },
};
</script>

<style scoped>
.full_width1 {
  width: 14.9rem;
}

.full_width2 {
  width: 15rem;
}

.full_width3 {
  width: 11.2rem;
}

.full_width4 {
  width: 9.3rem;
}


input {
  margin-left: auto;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.error-pass {
  color: red;
}

.dark-version .card p, .dark-version .swal2-popup p, .dark-version .dropdown .dropdown-menu p, .dark-version .kanban-board p {
    color: red !important;
    opacity: 1;
}

.input-error {
  border: 1px solid red;
}


</style>