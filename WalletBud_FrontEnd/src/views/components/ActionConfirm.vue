<template>
    <div v-if="isVisible" class="modal fade show" aria-hidden="true" data-bs-backdrop="static" >
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="popupLabel">{{ message }}</h5>
          </div>
          <div class="modal-body d-flex justify-content-center">
            {{ $t('Confirme a sua ação') }}.
          </div>
          <div class="modal-footer d-flex justify-content-between">
            <button class="btn btn-secondary" @click="cancelAction">{{ $t('Voltar') }}</button>
            <button class="btn btn-danger" @click="confirmAction">{{ $t('Terminar Sessão') }}</button>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      isVisible: {
        type: Boolean,
        default: false
      },
      message: {
        type: String,
        default: ''
      }
    },
    methods: {
      cancelAction() {
        this.$emit('cancel');
      },
      confirmAction() {
        this.$emit('confirm');
      }
    }
  };
  </script>
  
  <style scoped>
  .modal.fade.show {
    display: block;
  }
  .modal-dialog {
    transition: transform 0.3s ease-out;
  }
  .modal.fade .modal-dialog {
    transform: translateY(-100%);
  }
  .modal.fade.show .modal-dialog {
    transform: translateY(0);
  }

  .modal-dialog{
    max-width: 400px;
  }
  .modal{
    z-index: 9999;
    backdrop-filter: blur(1px);
    background: rgba(0, 0, 0, 0.5);
  }
  </style>
  