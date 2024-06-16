<template>
    <div class="modal fade" id="popupteste" tabindex="-1" aria-labelledby="transactionModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="categoryModalLabel">{{ $t('Informações da Transação') }}</h5>
                </div>
                <h5>{{ id }}</h5>
                <div class="modal-body">
                    <!-- Montante -->
                    <div class="form-group form-row">
                        <label for="value" class="form-label">{{ $t('Objetivo Poupança') }}:
                        </label>
                        <div v-if="valueError === true" class="form-input mb-3">
                            <material-input class="material-input" id="value" type="number" :value="Value"
                                :label=ValueLabel name="value" @update:value="Value = $event" error />
                        </div>
                        <div v-if="valueError === false" class="form-input mb-3">
                            <material-input class="material-input" id="value" type="number" name="value" :value="Value"
                                @update:value="Value = $event" success />
                        </div>
                        <div v-if="valueError === null" class="form-input mb-3">
                            <material-input class="material-input" id="value" type="number" :value="Value"
                                :label=ValueLabel name="value" @update:value="Value = $event" />
                        </div>
                    </div>

                    <MaterialAlert v-if="showAlert" color="danger" class="font-weight-light" dismissible @click="alert">
                        <span class="text-sm">{{ $t('Escolha o tipo de movimento') }}</span>
                    </MaterialAlert>
                </div>


                <div class="modal-footer mt-0">
                    <p v-if="Type == 'Despesa' && showShareTab == false" class="btn btn-default bg-gradient-info mb-1">
                        {{ $t('Próximo') }}</p>
                    <p v-else class="btn btn-default bg-gradient-info mb-1" @click="add">{{ $t('Adicionar') }}</p>
                    <p id="cancelButton" class="btn btn-default bg-gradient-primary mb-1" data-bs-dismiss="modal">{{
                        $t('Cancelar') }}
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import { useI18n } from 'vue-i18n';
import MaterialAlert from "@/components/MaterialAlert.vue";
import MaterialInput from "@/components/MaterialInput.vue";
//import NavPill from './NavPill.vue';
//import { transactionStore } from "@/store/transactionStore.js";
import { ref } from 'vue';
import { userStore } from "@/store/userStore";


export default {
    components: {
        MaterialAlert,
        MaterialInput,
    },
    props: {
        id: {
            type: Number,
            default: 0
        }
    },
    setup() {
        const { t } = useI18n();
        
        const ValueLabel = ref('Indique um valor')
        const showAlertUsers = ref(false);
        const Value = ref('');
        const valueError = ref(null);

        const userS = userStore();

        const checkInput = function () {

            if (!Value.value)
                valueError.value = true;
            else if (Value.value < 0) {
                valueError.value = true;
                ValueLabel.value = 'Indique um valor positivo'
            }
            else
                valueError.value = false;
        }

        const cancel = function () {

            valueError.value = null;
            Value.value = '';
            
        }

        const updateObj = async (user) => {
            try {
                await userS.updateEditedUser(user);
                await userS.getUser();

            } catch (err) {
                valueError.value = true;
            }
        };

        const add = function () {

            checkInput();

            if (valueError.value === false) {
                const user = {
                    objetivo: Value.value,
                };
                updateObj(user);
            }
        }

        return {
            t,
            add,
            cancel,
            checkInput,
            Value,
            valueError,
            ValueLabel,
            showAlertUsers
        };
    },
    data() {
        return {
            showAlert: false,
        };
    },
    mounted() {
    },
    computed: {
        
    },
    watch: {
    },
    methods: {
        hideAlertUsers(){
            this.showAlertUsers = false;
        },
        alert() {
            this.showAlert = !this.showAlert;
        },
    }
};
</script>

<style scoped>
.modal-content {
    width: 100%;
}

.modal {
    z-index: 1050;
}

.input-group {
    display: flex;
    align-items: center;
    position: relative;
    flex: 1;
}

.arrow-icon {
    position: absolute;
    right: 10px;
    pointer-events: none;
    color: #344767;
}

.dropdown-focused-null {
    border-color: #1a73e8 !important;
    box-shadow: inset 0 1px #1a73e8,
        inset 1px 0 #1a73e8,
        inset -1px 0 #1a73e8,
        inset 0 -1px #1a73e8 !important;
}

.dropdown-focused-error {
    border: 1px solid #f44335 !important;
    box-shadow: inset 0 1px #f44335,
        inset 1px 0 #f44335,
        inset -1px 0 #f44335,
        inset 0 -1px #f44335 !important;
    border-radius: 0.375rem;
}

.dropdown-menu {
    background-image: linear-gradient(195deg, #49a3f1 0%, #1a73e8 100%);
    color: white;
}

.dropdown .dropdown-menu:before {
    color: #3d96ef;
}

.dropdown-item {
    margin-top: 3px;
    color: #eeeeee;
    background-color: #ffffff14;
}

.dropdown-item:hover {
    background-color: #ffffff;
    color: #495057
}

.form-row {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.form-label {
    flex: 1;
    color: #344767;
    margin-right: 10px;
    text-align: left;
}

.form-input {
    flex: 2;
}

.material-input {
    width: 100%;
    position: relative;
}

.material-input input {
    width: 100%;
    padding: 0.5rem;
    font-size: 0.875rem;
    font-weight: 400;
    line-height: 1.5rem;
    color: #495057;
    background-color: transparent;
    background-clip: padding-box;
    border: 1px solid #d2d6da;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    border-radius: 0.375rem;
    transition: 0.2s ease;
}

.form-control-date {
    width: 100%;
    padding-right: 2.5rem;
    /* Espaço para o ícone */
}

/* Estilo para o ícone de calendário */
.date-input-wrapper {
    position: relative;
}

.date-icon {
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
    pointer-events: none;
    color: #344767;
    z-index: 2;
}

.border {
    border: 1px solid #d2d6da;
    border-radius: 0.375rem;
}

.tab {
    display: none;
}

.tab.active {
    display: block;
}

.nav-pills .nav-link.active,
.nav-pills .show>.nav-link {
    color: #344767;
    background-color: #fff;
    animation: 0.2s ease;
}

.nav-link {
    display: block;
    padding: 0.5rem 1rem;
    transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out;
}

.tab-content {
    min-height: 400px;
    /* Define uma altura mínima para as abas */
}

.ms-auto {
    margin-left: auto !important;
    margin-right: 10px;
}
</style>
