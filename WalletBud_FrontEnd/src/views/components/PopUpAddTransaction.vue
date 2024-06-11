<template>
    <div class="modal fade" id="transactionModal" tabindex="-1" aria-labelledby="transactionModalLabel"
        aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="categoryModalLabel">{{ $t('Adicionar Movimento') }}</h5>
                </div>
                <div class="modal-body">

                    <!-- Descrição -->
                    <div class="form-group form-row">
                        <label for="description" class="form-label">{{ $t('Nome') }}</label>
                        <div v-if="descriptionError === true" class="form-input mb-3">
                            <material-input class="material-input" id="description" type="text" label="Indique o nome"
                                name="description" @update:value="Description = $event" error />
                        </div>
                        <div v-if="descriptionError === false" class="form-input mb-3">
                            <material-input class="material-input" id="description" type="text" name="description"
                                :value="Description" @update:value="Description = $event" success />
                        </div>
                        <div v-if="descriptionError === null" class="form-input mb-3">
                            <material-input class="material-input" id="description" type="text" label="Indique o nome"
                                name="description" @update:value="Description = $event" />
                        </div>
                    </div>

                    <!-- Montante -->
                    <div class="form-group form-row">
                        <label for="value" class="form-label">{{ $t('Montante') }}:
                        </label>
                        <div v-if="valueError === true" class="form-input mb-3">
                            <material-input class="material-input" id="value" type="number" label="Indique um montante"
                                name="value" @update:value="Value = $event" error />
                        </div>
                        <div v-if="valueError === false" class="form-input mb-3">
                            <material-input class="material-input" id="value" type="number" name="value" :value="Value"
                                @update:value="Value = $event" success />
                        </div>
                        <div v-if="valueError === null" class="form-input mb-3">
                            <material-input class="material-input" id="value" type="number" label="Indique um montante"
                                name="value" @update:value="Value = $event" />
                        </div>
                    </div>

                    <!-- Data -->
                    <div class="form-group form-row">
                        <label for="date" class="form-label">
                            {{ $t('Data do Movimento') }}:
                        </label>
                        <div v-if="DateError === true" class="form-input mb-3">
                            <material-input class="material-input" :class="{ 'is-focused': isFocused }" id="date"
                                type="date" name="date" @update:value="DateM = $event" error @focus="handleFocus"
                                @blur="handleBlur" />
                        </div>
                        <div v-if="DateError === false" class="form-input mb-3">
                            <material-input class="material-input" :class="{ 'is-focused': isFocused }" id="date"
                                type="date" name="date" :value="DateM" @update:value="DateM = $event" success
                                @focus="handleFocus" @blur="handleBlur" />
                        </div>
                        <div v-if="DateError === null" class="form-input mb-3  date-input-wrapper">
                            <material-input class="material-input" :class="{ 'is-focused': isFocused }" id="date"
                                type="text" name="date" label="Indique a data" @update:value="DateM = $event"
                                @focus="handleFocus" @blur="handleBlur" />
                            <i class="material-icons date-icon">date_range</i>
                        </div>
                    </div>

                    <!-- Tipo -->
                    <div class="form-group form-row">
                        <label for="categoryType" class="form-label">
                            {{ $t('Tipo de movimento') }}:
                        </label>
                        <div class="input-group input-group-outline form-input mb-3" style="border-radius: 0.375rem;">
                            <select v-model="Type" class="form-control form-control-default material-input"
                                id="categoryType">
                                <option value="Despesa">{{ $t('Despesa') }}</option>
                                <option value="Receita">{{ $t('Receita') }}</option>
                            </select>
                            <i class="material-icons arrow-icon">keyboard_arrow_down</i>
                        </div>
                    </div>

                    <!-- Categoria -->
                    <div class="form-group form-row">
                        <label for="category" class="form-label">
                            {{ $t('Categoria do Movimento') }}:
                        </label>
                        <div class="input-group input-group-outline form-input mb-3" style="border-radius: 0.375rem;">
                            <select v-model="Category" class="form-control form-control-default material-input"
                                id="category">
                                <option v-for='(category, index) in displayCategories' :key="index">{{ category }}
                                </option>
                            </select>
                            <i class="material-icons arrow-icon">keyboard_arrow_down</i>
                        </div>
                    </div>

                    <!-- Local -->
                    <div class="form-group form-row">
                        <label for="place" class="form-label">{{ $t('Local') }}</label>
                        <div v-if="PlaceError === true" class="form-input mb-3">
                            <material-input class="material-input" id="place" type="text" label="Indique o local"
                                name="place" @update:value="Place = $event" error />
                        </div>
                        <div v-if="PlaceError === false" class="form-input mb-3">
                            <material-input class="material-input" id="place" type="text" name="place" :value="Place"
                                @update:value="Place = $event" success />
                        </div>
                        <div v-if="PlaceError === null" class="form-input mb-3">
                            <material-input class="material-input" id="place" type="text" label="Indique o local"
                                name="place" @update:value="Place = $event" />
                        </div>
                    </div>

                    <div class="dropdown float-lg-end">
                        <div class="form-group form-row">
                            <label for="place" class="form-label">{{ $t('Local') }}</label>
                            <div class="input-group input-group-outline form-input mb-3"
                                style="border-radius: 0.375rem;">
                                <button class="cursor-pointer form-control form-control-default material-input"
                                    id="dropdownTable" data-bs-toggle="dropdown">
                                </button>
                                <i class="material-icons arrow-icon">keyboard_arrow_down</i>
                                <ul class="dropdown-menu px-2 py-3 ms-sm-n4 ms-n5" aria-labelledby="dropdownTable">
                                    <li v-for='(category, index) in displayCategories' :key="index">
                                        <a class="dropdown-item border-radius-md" href="javascript:;">
                                            {{ category }}
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>



                </div>
                <div class="modal-footer">
                    <div class="modal-body">
                        <h5>{{ $t('Adicionar Categoria') }}</h5>
                        <label for="categoryType" style="color: black;">{{ $t('Selecionar o tipo de movimento') }}:
                        </label>
                        <div class="input-group input-group-outline mb-2"
                            style="position: relative;  border-radius: 0.375rem;">
                            <select v-model="newCategoryType" class="form-control form-control-default"
                                id="categoryType">
                                <option value="Despesa">{{ $t('Despesa') }}</option>
                                <option value="Receita">{{ $t('Receita') }}</option>
                            </select>
                            <i class="material-icons arrow-icon">keyboard_arrow_down</i>
                        </div>

                        <div class="input-group input-group-outline mb-3" :class="{ 'is-focused': isFocused }">
                            <label class="form-label">{{ $t('Escreva uma categoria') }}</label>
                            <input class="form-control form-control-default" type="text" v-model="newCategoryName"
                                @focus="handleFocus" @blur="handleBlur">
                        </div>
                        <MaterialAlert v-if="showErrorAdd" color="danger" class="font-weight-light">
                            <span class="text-sm">{{ $t('Indica um nome para a categoria!') }}</span>
                        </MaterialAlert>

                    </div>
                </div>
                <div class="modal-footer mt-0">
                    <p class="btn btn-default bg-gradient-info mb-1" @click="add">{{ $t('Adicionar') }}</p>
                    <p class="btn btn-default bg-gradient-primary mb-1" data-bs-dismiss="modal">{{ $t('Cancelar') }}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import { useI18n } from 'vue-i18n';
import MaterialAlert from "@/components/MaterialAlert.vue";
import MaterialInput from "@/components/MaterialInput.vue";
import { ref } from 'vue';


export default {
    setup() {
        const { t } = useI18n();
        const Description = ref('');
        const descriptionError = ref(null);
        const Value = ref('');
        const valueError = ref(null);
        const DateM = ref('');
        const DateError = ref(null);
        const Category = ref('');
        const CategoryError = ref(null);
        const Place = ref('');
        const PlaceError = ref(null);

        const add = function () {
            if (!Description.value) {
                descriptionError.value = true;

            } else {
                descriptionError.value = false;
            }

            if (!Value.value) {
                valueError.value = true;

            } else {
                valueError.value = false;
            }

            if (!DateM.value) {
                DateError.value = true;

            } else {
                DateError.value = false;
            }

            if (!Category.value) {
                CategoryError.value = true;

            } else {
                CategoryError.value = false;
            }

            if (!Place.value) {
                PlaceError.value = true;

            } else {
                PlaceError.value = false;
            }
        }

        return {
            t,
            add,
            descriptionError,
            Description,
            Value,
            valueError,
            DateM,
            DateError,
            Category,
            CategoryError,
            Place,
            PlaceError
        };
    },
    data() {
        return {
            expenseCategories: ['Pessoal', 'Alimentação', 'Familiar', 'Casa', 'Entretenimento'],
            incomeCategories: ['Rendas', 'Salários'],
            Type: 'Despesa',
            Shared: false,
            Coment: '',
            isEditing: [],
            isEditingIncome: [],
            editedCategory: '',
            editedIncomeCategory: '',
            showErrorExpense: false,
            showErrorIncome: false,
            showErrorAdd: false,
            errorTimeout: null,
            isFocused: false,
        };
    },
    components: {
        MaterialAlert,
        MaterialInput,
    },
    computed: {
        displayCategories() {
            if (this.Type == 'Despesa')
                return this.expenseCategories;
            else
                return this.incomeCategories;
        },
    },
    methods: {
        handleFocus(event) {
            event.target.type = 'date';
            this.isFocused = true;
            if (this.DateError !== null)
                this.DateError = null;
        },
        handleBlur(event) {
            event.target.type = 'text';
            if (this.DateM == '')
                this.isFocused = false;
        },
        addCategory() {
            if (this.newCategoryName.trim() === '') {
                this.showErrorAdd = true;
                clearTimeout(this.errorTimeout);
                this.errorTimeout = setTimeout(() => {
                    this.showErrorAdd = false;
                }, 2000);
                return;
            }
            if (this.newCategoryType === 'Despesa') {
                this.expenseCategories.push(this.newCategoryName);
            } else {
                this.incomeCategories.push(this.newCategoryName);
            }
            this.newCategoryName = '';
        },
        deleteCategory(type, index) {
            if (type === 'Despesa') {
                this.expenseCategories.splice(index, 1);
            } else {
                this.incomeCategories.splice(index, 1);
            }
        },
        changeFilter: function (filter) {
            this.activeFilter = filter;
        },
        toggleEditMode(index) {
            this.isEditing[index] = !this.isEditing[index];
            this.editedCategory = this.expenseCategories[index];
        },

        saveCategory(index) {
            if (this.editedCategory.trim() === '') {
                this.showErrorExpense = true;
                clearTimeout(this.errorTimeout);
                this.errorTimeout = setTimeout(() => {
                    this.showErrorExpense = false;
                }, 2000);
                return;
            }
            this.expenseCategories[index] = this.editedCategory;
            this.isEditing[index] = false;

        },

        toggleIncomeEditMode(index) {
            this.isEditingIncome[index] = !this.isEditingIncome[index];
            this.editedIncomeCategory = this.incomeCategories[index];
        },

        saveIncomeCategory(index) {
            if (this.editedIncomeCategory.trim() === '') {
                this.showErrorIncome = true;
                clearTimeout(this.errorTimeout);
                this.errorIncome = setTimeout(() => {
                    this.showErrorIncome = false;
                }, 2000);
                return;
            }
            this.incomeCategories[index] = this.editedIncomeCategory;
            this.isEditingIncome[index] = false;
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
    /* padrão do Bootstrap para modais */
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
    /* Ajuste conforme necessário */
    pointer-events: none;
    color: #1a73e8;
    /* Ajuste a cor conforme necessário */
}

.form-row {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.form-label {
    flex: 1;
    color: black;
    margin-right: 10px;
    text-align: left;
    /* Alinha as labels à direita */
}

.form-input {
    flex: 2;
}

.material-input {
    width: 100%;
}

/* Estilização adicional para garantir que os inputs tenham a mesma altura e aparência */
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
    /* Garante que o ícone não bloqueie a interação com o campo */
    color: #1a73e8;
    /* Cor do ícone */
}
</style>
