<template>
  <div class="h-100">
    <div class="p-3 pb-0 card-header">
      <div class="row">
        <div class="col-md-8 d-flex align-items-center">
          <h6 class="mb-0">{{ $t('Informação do Perfil') }}</h6>
        </div>
        <div class="col-md-4 d-flex justify-content-end">
          <button class="btn-edit" v-if="!editMode" @click="editProfile">
            <i class="text-sm fas fa-user-edit text-secondary" data-bs-toggle="tooltip" data-bs-placement="top" :title="action.tooltip"></i>
          </button>
          <button class="btn-edit" v-else @click="saveChanges">
            💾
          </button>
        </div>
      </div>
    </div>
    <div class="p-3 card-body">
      <ul class="list-group">

        <li class="pt-0 text-sm border-0 list-group-item ps-0">
          <div v-if="showErrorNome">
            <p class="error-pass">{{ $t('Nome não pode ser nulo') }}</p>
          </div>

          <strong class="text-dark">{{ $t('Nome') }}:</strong>
          <span v-if="!editMode">{{ name }} </span>
          <input v-else-if="showErrorNome" class="input-error" v-model="editedInfo.name" />
          <input v-else v-model="editedInfo.name" class="full_width1"/>
        </li>

        <li class="text-sm border-0 list-group-item ps-0">
          <div v-if="showErrorEmail">
            <p class="error-pass">{{ $t('Email não pode ser nulo') }}</p>
          </div>
          
          <strong class="text-dark">{{ $t('Email') }}:</strong>
          <span v-if="!editMode">{{ email }}</span>
          <input v-else-if="showErrorEmail" class="input-error" v-model="editedInfo.email" />
          <input v-else v-model="editedInfo.email" class="full_width2" />
        </li>
        
        <li class="text-sm border-0 list-group-item ps-0">
          <div v-if="showError">
            <p class="error-pass">{{ $t('Senhas não coincidem') }}</p>
          </div>
          <div v-if="showError2">
            <p class="error-pass">{{ $t('Passwords devem ter no mínimo') }} 6 {{ $t('caracteres') }}</p>
          </div>
          <strong class="text-dark">{{ $t('Senha') }}:</strong>
          <span v-if="!editMode">{{ maskPassword }}</span>
          <input v-else-if="showError" class="input-error" v-model="editedInfo.senha1" />
          <input v-else-if="showError2" class="input-error" v-model="editedInfo.senha1" />
          <input v-else v-model="editedInfo.senha1" class="full_width3"/>
        </li>
        
        <li class="text-sm border-0 list-group-item ps-0" v-if="editMode">
          <strong class="text-dark">{{ $t('Confirmar Senha') }}:</strong>
          <input v-if="showError" class="input-error" v-model="editedInfo.senha2" />
          <input v-else-if="showError2" class="input-error" v-model="editedInfo.senha2" />
          <input v-else v-model="editedInfo.senha2" class="full_width4"/>
        </li>

      </ul>
    </div>
  </div>
</template>

<!--Popups necessários-->
<script>
import { userStore } from "@/store/userStore";
import { ref, computed } from "vue";

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
  setup(){
    const store = userStore();

    // Definindo as propriedades da store como ref
    const name = ref('');
    const email = ref('');
    const senha = ref('');

    // Verificar se a store está definida antes de atribuir seus valores
    if (store) {
      name.value = store.name;
      email.value = store.email;
      senha.value = store.senha;
    }

    const editMode = ref(false);
    const showError = ref(false);
    const showError2 = ref(false);
    const showErrorNome = ref(false);
    const showErrorEmail = ref(false);
    const editedInfo = ref({ name: '' , email: '', senha: ''});

    const maskPassword = computed(() => '*'.repeat(senha.value.length));

    const editProfile = () => {
      editMode.value = true;
      editedInfo.value.senha1 = maskPassword.value;
      editedInfo.value.senha2 = maskPassword.value;
      editedInfo.value.name = name.value;
      editedInfo.value.email = email.value;
    };

    const saveChanges = () => {
      if(checkUsername() && checkEmail() && checkPasswordsMatch()){
        senha.value = editedInfo.value.senha1;
        editedInfo.value.senha1 = '';
        editedInfo.value.senha2 = '';

        email.value = editedInfo.value.email;
        name.value = editedInfo.value.name;
        
        store.updateUser(editedInfo.value);
        editMode.value = false; 
      }
    };

    const checkEmail = () => {
      if(editedInfo.value.email.length == 0){
        showErrorEmail.value = true;
        return false;
      }
      showErrorEmail.value = false;
      return true;
    };

    const checkUsername = () => {
      if(editedInfo.value.name.length == 0){
        showErrorNome.value = true;
        return false;
      }
      showErrorNome.value = false;
      return true;
    };

    const checkPasswordsMatch = () => {
      if(editedInfo.value.senha1 !== editedInfo.value.senha2){
        showError.value = true;
        showError2.value = false;
        return false;
      }else if(editedInfo.value.senha1 == '' || editedInfo.value.senha2 == ''){
        showError.value = false;
        showError2.value = true;
        return false;
      }else if(editedInfo.value.senha1.length < 6){
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
      showErrorNome,
      editedInfo,
      maskPassword,
      editProfile,
      saveChanges,
      name,
      email,
    };
  },
 
};
</script>


<style scoped>
.full_width1{
  width: 14.9rem;
}

.full_width2{
  width: 15rem;
}

.full_width3{
  width: 14.7rem;
}

.full_width4{
  width: 10.5rem;
}

.btn-edit {
  background-color: lightblue;
  color: white;
  border: none;
  border-radius: 4px;  
}

.btn-edit:hover {
  background-color: #0056b3;
}

input{
  margin-left: auto;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.text-dark {
  margin-right: 10px;
}

.error-pass{
  color: red;
}

.input-error{
  border: 1px solid red;
}
</style>