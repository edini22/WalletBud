<template>
    <transition name="modal-fade">
        <div v-if="isVisible" class="modal-overlay ">
            <div class="card modal-content ">
                <div class="card-header card-header-primary text-center">
                    <h5 class="card-title">{{ message }}</h5>
                    <p class="card-category">{{ $t('Confirme a sua ação') }}.</p>
                </div>
                <div class="card-footer d-flex justify-content-between ">
                    <MaterialButton
                        color="info" 
                        size="sm" 
                        @click="cancelAction">
                        {{ $t('Voltar') }}</MaterialButton>
                    <MaterialButton
                        color="primary" 
                        @click="confirmAction">
                        {{ $t('Sair') }}</MaterialButton>
                </div>
            </div>
        </div>
    </transition>
</template>

<script>
import MaterialButton from "@/components/MaterialButton.vue";
import { useI18n } from "vue-i18n";

export default {
    components: {
        MaterialButton
    },
    setup() {
        const { t } = useI18n();
        return { t };
    },
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
        confirmAction() {
        this.$emit('confirm');
        },
        cancelAction() {
        this.$emit('cancel');
        }
    }
};
</script>
  
<style scoped>
  .modal-content {
    width: 85%;
    margin:5px auto;
    margin-bottom: 7%;
    border-color: white;
  }
  
  .card-footer{
    margin-top: -45px;
    margin-bottom: -5px;
  }
</style>