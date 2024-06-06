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
          <strong class="text-dark">{{ $t('Nome') }}:</strong>
          <span v-if="!editMode">{{ info.nome }} </span>
          <input v-else v-model="editedInfo.nome" class="full_width1"/>
        </li>

        <li class="text-sm border-0 list-group-item ps-0">
          <strong class="text-dark">{{ $t('Email') }}:</strong>
          <span v-if="!editMode">{{ info.email }}</span>
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
export default {
  name: "ProfileInfoCard",
  props: {
    info: {
      type: Object,
      default: () => ({}),
    },
    action: {
      type: Object,
      default: () => ({
        route: "javascript:;",
        tooltip: "",
      }),
    },
  },
  data() {
    return {
      editMode: false,
      editedInfo: { ...this.info },
      showError: false,
      showError2:false,
    };
  },
  computed: {
    maskPassword() {
      return '*'.repeat(this.editedInfo.senha.length);
    },
  },
  
  methods: {
    editProfile() {
      this.editMode = true;
      this.editedInfo.senha1 = this.maskPassword;
      this.editedInfo.senha2 = this.maskPassword;
    },
    
    saveChanges() {
      if(this.checkPasswordsMatch()){
        this.editedInfo.senha = this.editedInfo.senha1;
        this.editedInfo.senha1 = undefined;
        this.editedInfo.senha2 = undefined;
        this.$emit("saveProfile", this.editedInfo);
        this.editMode = false; 
      }
    },

    checkPasswordsMatch(){
      if(this.editedInfo.senha1 !== this.editedInfo.senha2){
        this.showError= true;
        this.showError2= false;
        return false;
      }else if(this.editedInfo.senha1 == undefined || this.editedInfo.senha2 == undefined){
        this.showError= false;
        this.showError2= true;
        return false;
      }else if(this.editedInfo.senha1.length < 6){
        this.showError= false;
        this.showError2= true;
        return false;
      }
      this.showError= false;
      this.showError2= false;
      return true;
    },

    closeModal() {
      this.showModal = false;
    },
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