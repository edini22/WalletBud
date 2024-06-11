<template>
  <div class="h-100">
    <div class="p-3 pb-0 card-header">
      <div class="row align-items-center">
        
        <div>
          <material-button v-if="!editMode"
            class="mt-0"
            variant="gradient"
            color="info"
            size="sm"
            @click="editProfile">
            {{$t('Editar Perfil')}}
            <i data-bs-toggle="tooltip" data-bs-placement="top" :title="action.tooltip"></i>
          </material-button>
          &nbsp;
          <material-button v-if="!editMode"
            class="mt-0"
            variant="gradient"
            color="info"
            size="sm"
            @click="editPasswordFunc">
            {{$t('Editar Password')}}
            <i data-bs-toggle="tooltip" data-bs-placement="top" :title="action.tooltip"></i>
          </material-button>


        </div>
        <p></p>
        <div class="col-md-8 d-flex align-items-center">
          <h6 class="mb-0">{{ $t('Informação do Perfil') }}</h6>
        </div>
      </div>
    </div>
    <div class="p-3 card-body">
      <ul class="list-group">
      <div v-if="!editPassword">
        <li class="pt-0 text-sm border-0 list-group-item ps-0">
          <div v-if="showErrorNome">
            <p class="error-pass">{{ $t('Nome não pode ser nulo') }}</p>
          </div>

          <strong v-if="!editMode || !editPassword" class="text-dark">{{ $t('Nome') }}:</strong>
          <span v-if="!editMode">{{ username }} </span>
          <input v-else-if="showErrorNome" class="input-error" v-model="editedInfo.username" />
          <input v-else-if="editMode && !editPassword" v-model="editedInfo.username" class="full_width1"/>
        </li>

        <li class="text-sm border-0 list-group-item ps-0">
          <div v-if="showErrorEmail">
            <p class="error-pass">{{ $t('Email não pode ser nulo') }}</p>
          </div>
          <div v-if="showErrorEmail2">
            <p class="error-pass">{{ $t('Email inválido') }}</p>
          </div>
          
          <strong v-if="!editMode || !editPassword" class="text-dark">{{ $t('Email') }}:</strong>
          <span v-if="!editMode ">{{ email }}</span>
          <input v-else-if="showErrorEmail" class="input-error" v-model="editedInfo.email" />
          <input v-else-if="editMode && !editPassword" v-model="editedInfo.email" class="full_width2" />
        </li>
      </div>
      
      
        <li class="text-sm border-0 list-group-item ps-0">
          <div v-if="showError">
            <p class="error-pass">{{ $t('Passwords não coincidem') }}</p>
          </div>
          <div v-if="showError2">
            <p class="error-pass">{{ $t('Passwords devem ter no mínimo') }} 6 {{ $t('caracteres') }}</p>
          </div>
          <strong v-if="!editMode" class="text-dark">Password:</strong>
          <strong v-if="editMode && editPassword" class="text-dark">{{ $t('Nova') }} Password:</strong>
          <span v-if="!editMode">*********</span>
          <input v-else-if="showError" class="input-error" v-model="editedInfo.senha1" />
          <input v-else-if="showError2" class="input-error" v-model="editedInfo.senha1" />
          <input v-else-if="editMode && editPassword" v-model="editedInfo.senha1" class="full_width3"/>
        </li>
        
        <li class="text-sm border-0 list-group-item ps-0" v-if="editMode && editPassword">
          <strong class="text-dark">{{ $t('Confirmar Password') }}:</strong>
          <input v-if="showError" class="input-error" v-model="editedInfo.senha2" />
          <input v-else-if="showError2" class="input-error" v-model="editedInfo.senha2" />
          <input v-else-if="editMode && editPassword" v-model="editedInfo.senha2" class="full_width4"/>
        </li>
      

      </ul>
      <div class="d-flex justify-content-between" v-if="editMode && editPassword">
        <material-button
          class="mt-4"
          variant="gradient"
          color="info"
          size="sm"
          @click="goBack"
        >
          {{$t('Voltar')}}
        </material-button>

        <material-button
          class="mt-4"
          variant="gradient"
          color="success"
          size="sm"
          @click="saveNewPassword"
        >
          {{$t('Guardar')}}
        </material-button>
      </div>
      <div class="d-flex justify-content-between" v-if="editMode && !editPassword">
        <material-button
          class="mt-4"
          variant="gradient"
          color="info"
          size="sm"
          @click="goBack"
        >
          {{$t('Voltar')}}
        </material-button>

        <material-button
        class="mt-4"
        variant="gradient"
        color="success"
        size="sm"
        @click= "saveChanges"
        >{{$t('Guardar')}}</material-button>
      </div>
    </div>
  </div>
</template>

<!--Popups necessários-->
<script>
import { userStore } from "@/store/userStore";
import { ref } from "vue";
import MaterialButton from "@/components/MaterialButton.vue";

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
    MaterialButton
  },
  setup(){
    const store = userStore();

    // Definindo as propriedades da store como ref
    const username = ref('');
    const email = ref('');

    // Verificar se a store está definida antes de atribuir seus valores
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
    const editedInfo = ref({ username: '' , email: '', password: ''});
    const editPassword = ref(false);

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
      if(checkPasswordsMatch()){
        editedInfo.value.password = editedInfo.value.senha1;
        editedInfo.value.senha1 = '';
        editedInfo.value.senha2 = '';

        editedInfo.value.username = username.value;
        editedInfo.value.email = email.value;
        
        const user = {
          password: editedInfo.value.password,
        };

        //mandar para a API
        try {
          await store.updateEditedUser(user);
          // Se o registro for bem-sucedido, você pode mostrar uma mensagem ou redirecionar se necessário
          alert("pass com sucesso");
          if (store) {
            username.value = store.$state.username;
            email.value = store.$state.email;
          }
        } catch (error) {
          alert("Erro: " + error.message);
        }

        // store.updateUser(editedInfo.value);
        alert(editedInfo.value.username + ' : ' + editedInfo.value.email + ' : ' + editedInfo.value.password)
        editMode.value = false;
        editPassword.value = false;
      }
    };
    
    const saveChanges = async () => {
     
      if(checkUsername() && checkEmail()){
        //email.value = editedInfo.value.email;
        //username.value = editedInfo.value.username;
        
        const user = {
          username: editedInfo.value.username,
          email: editedInfo.value.email,
        };

        //mandar para a API
        try {
          alert(editedInfo.value.username + ' : ' + editedInfo.value.email + ' : ' + editedInfo.value.password)
          await store.updateEditedUser(user);
          // Se o registro for bem-sucedido, você pode mostrar uma mensagem ou redirecionar se necessário
          alert("Editado com sucesso");
          if (store) {
            username.value = store.$state.username;
            email.value = store.$state.email;
          }
        } catch (error) {
          
          alert("Erro: " + error.message);
          
        }
        
        // store.updateUser(editedInfo.value);
        editMode.value = false; 
      }
    };

    const checkEmail = () => {
      if (!validarEmail(editedInfo.value.email)) {
        showErrorEmail2.value = true;
        return false;
      } 
      if(editedInfo.value.email.length == 0){
        showErrorEmail.value = true;
        return false;
      }
      showErrorEmail2.value = false;
      showErrorEmail.value = false;
      return true;
    };

    const checkUsername = () => {
      if(editedInfo.value.username.length == 0){
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
  width: 11.2rem;
}

.full_width4{
  width: 9.3rem;
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