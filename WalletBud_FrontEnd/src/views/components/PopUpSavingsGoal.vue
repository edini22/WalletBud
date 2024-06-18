<template>
    <div class="modal fade" id="savingsGoalModal" tabindex="-1" aria-labelledby="transactionModalLabel"
    aria-hidden="true" data-bs-backdrop='static' data-bs-keyboard='false'>
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">{{ $t('Definir Objetivo Poupança') }}</h5>
                </div>

                <div class="modal-body">
                    <!-- Valor objetivo poupança -->
                    <div class="form-group form-row ">
                        <label for="value" class="form-label fs-6 text-center">{{ $t('Objetivo Poupança') }}: </label>

                        <div v-if="valueError === true && valueNegative === null" class="form-input mb-2">
                            <material-input class="material-input" id="value" type="number" :value="Value"
                                :label="$t('Indique um valor')" name="value" @update:value="Value = $event" error />
                        </div>
                        <div v-if="valueError === true && valueNegative === true" class="form-input mb-2">
                            <material-input class="material-input" id="value" type="number" :value="Value"
                                :label="$t('Indique um valor positivo')" name="value" @update:value="Value = $event" error />
                        </div>
                        <div v-if="valueError === false" class="form-input mb-2">
                            <material-input class="material-input" id="value" type="number" name="value"
                                :value="Value" @update:value="Value = $event" success />
                        </div>
                        <div v-if="valueError === null" class="form-input mb-2">
                            <material-input class="material-input" id="value" type="number" :value="Value"
                                :label="$t('Indique um valor')" name="value" @update:value="Value = $event" />
                        </div>
                    </div>
                </div>

                <div class="modal-footer mt-0 d-flex justify-content-between">
                    <button id="cancelSavings" class="btn btn-default bg-gradient-primary mb-1" :disabled="isLoading" data-bs-dismiss="modal" @click="cancel">{{
                        $t('Cancelar') }}
                    </button>
                    <button class="btn btn-default bg-gradient-info mb-1" :disabled="isLoading" @click="add">{{ $t('Definir') }}</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import { useI18n } from 'vue-i18n';
import MaterialInput from "@/components/MaterialInput.vue";
import { ref, nextTick  } from 'vue';
import { userStore } from "@/store/userStore";
import { useRouter } from 'vue-router';


export default {
    components: {
        MaterialInput,
    },
    setup() {
        const { t } = useI18n();
        
        const Value = ref('');
        const valueError = ref(null);
        const valueNegative = ref(null);
        const isLoading = ref(false);
        const userS = userStore();

        const router = useRouter(); 

        const checkInput = function () {

            if (!Value.value)
                valueError.value = true;
            else if (Value.value < 0) {
                valueError.value = true;
                valueNegative.value = true;
                Value.value = '';
            }
            else{
                valueError.value = false;
                valueNegative.value = null;
            }  
        }

        const cancel = function () {

            valueError.value = null;
            Value.value = '';
            
        }

        const updateObj = async (user) => {
            isLoading.value = true;
            try {
                await userS.updateEditedUser(user);
                await userS.getUser();

                const event = new CustomEvent('show-snackbar', { detail: 'successSavings' });
                document.dispatchEvent(event);

            } catch (err) {
                if (err.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                } else {
                    const event = new CustomEvent('show-snackbar', { detail: 'errorSavings' });
                    document.dispatchEvent(event);
                }
                

            } finally {
                isLoading.value = false;
                await nextTick();

                const cancelButton = document.getElementById('cancelSavings');
                cancelButton.click();
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
            isLoading,
            valueNegative
        };
    },
    data() {
        return {
            
        };
    },
};
</script>

<style scoped>
.modal-content {
    width: 100%;
}

.modal {
    z-index: 9999;
}

.input-group {
    display: flex;
    align-items: center;
    position: relative;
    flex: 1;
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
</style>
