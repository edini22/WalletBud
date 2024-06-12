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
                            <material-input class="material-input" id="description" type="text"
                                :label="$t('Indique o nome')" name="description" @update:value="Description = $event" 
                                :value="Description" error />
                        </div>
                        <div v-if="descriptionError === false" class="form-input mb-3">
                            <material-input class="material-input" id="description" type="text" name="description"
                                :value="Description" @update:value="Description = $event" success />
                        </div>
                        <div v-if="descriptionError === null" class="form-input mb-3">
                            <material-input class="material-input" id="description" type="text" :value="Description"
                                :label="$t('Indique o nome')" name="description" @update:value="Description = $event" />
                        </div>
                    </div>

                    <!-- Montante -->
                    <div class="form-group form-row">
                        <label for="value" class="form-label">{{ $t('Montante') }}:
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

                    <!-- Data -->
                    <div class="form-group form-row">
                        <label for="date" class="form-label">
                            {{ $t('Data do Movimento') }}:
                        </label>
                        <div v-if="DateError === true" class="form-input mb-3 date-input-wrapper" :class="{ 'dropdown-focused-error': isDateFocused, 'border': SetBorder }" style="position: relative;">
                            <material-input 
                                class="material-input"
                                id="date"
                                type="date" 
                                name="date"
                                :value="DateM"
                                @update:value="DateM = $event" error
                                @focus="handleDateFocus"
                                @blur="handleDateBlur"
                            />
                        </div>
                        <div v-if="DateError === false" class="form-input mb-3">
                            <material-input class="material-input" :class="{ 'is-focused': isFocused }" id="date"
                                type="date" name="date" :value="DateM" @update:value="DateM = $event" success
                                @focus="handleFocus" @blur="handleBlur" />
                        </div>
                        <div v-if="DateError === null" class="form-input mb-3  date-input-wrapper">
                            <material-input class="material-input" :class="{ 'is-focused': isFocused }" id="date"
                                type="date" name="date" @update:value="DateM = $event" :value="DateM"
                                @focus="handleFocus" @blur="handleBlur" />
                            <i class="material-icons date-icon">date_range</i>
                        </div>
                    </div>

                    <!-- Tipo -->
                    <div class="dropdown" ref="typeDropdown">
                        <div class="form-group form-row">
                            <label for="categoryType" class="form-label">{{ $t('Tipo de movimento:') }}</label>

                            <div v-if="TypeError === null || TypeError === false"
                                class="input-group input-group-outline form-input mb-3"
                                style="border-radius: 0.375rem;">
                                <button class="cursor-pointer form-control form-control-default material-input"
                                    :class="{ 'dropdown-focused-null': isTypeFocused }" id="dropdownTable"
                                    data-bs-toggle="dropdown" style="text-align:left; color: #7b809a"
                                    @focus="handleTypeFocus">
                                    {{ Type || $t('Selecione o tipo') }}
                                </button>
                                <i class="material-icons arrow-icon">keyboard_arrow_down</i>
                                <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5" aria-labelledby="dropdownTable">
                                    <li>
                                        <a class="dropdown-item border-radius-md" href="javascript:;"
                                            @click="selectType('Despesa')">
                                            {{ $t('Despesa') }}
                                        </a>
                                    </li>
                                    <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                            @click="selectType('Receita')">
                                            {{ $t('Receita') }}
                                        </a>
                                    </li>
                                </ul>
                            </div>

                            <div v-if="TypeError === true"
                                class="input-group input-group-outline form-input mb-3 is-invalid"
                                style="border-radius: 0.375rem;">
                                <button class="cursor-pointer form-control form-control-default material-input"
                                    :class="{ 'dropdown-focused-error': isTypeFocused }" id="dropdownTable"
                                    data-bs-toggle="dropdown" style="text-align:left; color: #7b809a"
                                    @focus="handleTypeFocus">
                                    {{ Type || $t('Selecione o tipo') }}
                                </button>
                                <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5" aria-labelledby="dropdownTable">
                                    <li>
                                        <a class="dropdown-item border-radius-md" href="javascript:;"
                                            @click="selectType('Despesa')">
                                            {{ $t('Despesa') }}
                                        </a>
                                    </li>
                                    <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                            @click="selectType('Receita')">
                                            {{ $t('Receita') }}
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <!-- Categoria -->
                    <div class="dropdown" ref="categoryDropdown">
                        <div class="form-group form-row">
                            <label for="category" class="form-label">{{ $t('Categoria') }}</label>
                            <div v-if="CategoryError === null || CategoryError === false"
                                class="input-group input-group-outline form-input mb-3"
                                style="border-radius: 0.375rem;">
                                <button v-if="displayCategories.length > 0"
                                    class="cursor-pointer form-control form-control-default material-input"
                                    :class="{ 'dropdown-focused-null': isCategoryFocused }" id="dropdownTable"
                                    data-bs-toggle="dropdown" style="text-align:left; color: #7b809a"
                                    @focus="handleCategoryFocus">
                                    {{ Category || $t('Selecione a categoria') }}
                                </button>
                                <!-- show this button if type has not been chosen -->
                                <button v-else class="cursor-pointer form-control form-control-default material-input"
                                    :class="{ 'dropdown-focused-null': isCategoryFocused }" id="dropdownTable"
                                    style="text-align:left; color: #7b809a" @click="alert">
                                    {{ Category || $t('Selecione a categoria') }}
                                </button>
                                <i class="material-icons arrow-icon">keyboard_arrow_down</i>
                                <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5" aria-labelledby="dropdownTable">
                                    <li v-for='(category, index) in displayCategories' :key="index">
                                        <a class="dropdown-item border-radius-md" href="javascript:;"
                                            @click="selectCategory(category)">
                                            {{ category }}
                                        </a>
                                    </li>
                                </ul>
                            </div>

                            <div v-if="CategoryError === true"
                                class="input-group input-group-outline form-input mb-3 is-invalid"
                                style="border-radius: 0.375rem;">
                                <button v-if="displayCategories.length > 0"
                                    class="cursor-pointer form-control form-control-default material-input"
                                    :class="{ 'dropdown-focused-error': isCategoryFocused }" id="dropdownTable"
                                    data-bs-toggle="dropdown" style="text-align:left; color: #7b809a"
                                    @focus="handleCategoryFocus">
                                    {{ Category || $t('Selecione a categoria') }}
                                </button>
                                <!-- show this button if type has not been chosen -->
                                <button v-else class="cursor-pointer form-control form-control-default material-input"
                                    :class="{ 'dropdown-focused-error': isCategoryFocused }" id="dropdownTable"
                                    style="text-align:left; color: #7b809a" @click="alert">
                                    {{ Category || $t('Selecione a categoria') }}
                                </button>
                                <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5" aria-labelledby="dropdownTable">
                                    <li v-for='(category, index) in displayCategories' :key="index">
                                        <a class="dropdown-item border-radius-md" href="javascript:;"
                                            @click="selectCategory(category)">
                                            {{ category }}
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <MaterialAlert v-if="showAlert" color="danger" class="font-weight-light" dismissible @click="alert">
                        <span class="text-sm">{{ $t('Escolha o tipo de movimento') }}</span>
                    </MaterialAlert>

                    <!-- Local -->
                    <div class="form-group form-row">
                        <label for="place" class="form-label">{{ $t('Local') }}</label>
                        <div v-if="PlaceError === true" class="form-input mb-3">
                            <material-input class="material-input" id="place" type="text" :label="$t('Indique o local')"
                                name="place" :value="Place" @update:value="Place = $event" error />
                        </div>
                        <div v-if="PlaceError === false" class="form-input mb-3">
                            <material-input class="material-input" id="place" type="text" name="place" :value="Place"
                                @update:value="Place = $event" success />
                        </div>
                        <div v-if="PlaceError === null" class="form-input mb-3">
                            <material-input class="material-input" id="place" type="text" :label="$t('Indique o local')"
                                name="place" :value="Place" @update:value="Place = $event" />
                        </div>
                    </div>

                    <!-- Shared expense -->
                    <div v-if="Type == 'Despesa'" class="form-group form-row">
                        <label for="sharedExpense" class="form-label">{{ $t('Partilhar Despesa') }}</label>
                        <div class="form-input mb-3">
                            <material-input class="material-input" id="sharedExp" type="text" :label="$t('Indique o email do utilizador')"
                                name="sharedExp" :value="ShareUser" @update:value="ShareUser = $event" />
                        </div>
                    </div>
                    
                </div>
                <div class="modal-footer d-block justify-content-center">
                    <div class="align-items-center text-center">
                        <i class="material-icons align-self-center" style="max-width: 24px; color: #344767">insert_comment_outlined</i>
                        <h5>{{ $t('Comentário') }}</h5>

                            <div class="form-input mb-3">
                            <material-input class="material-input" id="comment" type="text" :value="Comment"
                            :label="$t('Escreva o seu comentário')" @update:value="Comment = $event" />
                        </div>
                    </div>
                </div>

                <div class="modal-footer mt-0">
                    <p class="btn btn-default bg-gradient-info mb-1" @click="add">{{ $t('Adicionar') }}</p>
                    <p class="btn btn-default bg-gradient-primary mb-1" data-bs-dismiss="modal" @click="cancel"
                        >{{ $t('Cancelar') }}
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
//import { transactionStore } from "@/store/transactionStore.js";
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
        const ValueLabel = ref('Indique um montante')
        const Place = ref('');
        const PlaceError = ref(null);
        const Category = ref('');
        const CategoryError = ref(null);
        const Type = ref('');
        const TypeError = ref(null);
        const Comment = ref(null);
        const ShareUser = ref(null);

        const add = function () {
            if (!Description.value) {
                descriptionError.value = true;

            } else {
                descriptionError.value = false;
            }

            if (!Value.value) {
                valueError.value = true;

            } else if(Value.value < 0){
                valueError.value = true;
                ValueLabel.value = 'Indique um montante positivo'
            }
            else {
                valueError.value = false;
            }

            if (!DateM.value) {
                DateError.value = true;

            } else {
                DateError.value = false;
            }

            if (!Place.value) {
                PlaceError.value = true;

            } else {
                PlaceError.value = false;
            }

            if (!Category.value) {
                CategoryError.value = true;

            } else {
                CategoryError.value = false;
            }

            if (!Type.value) {
                TypeError.value = true;

            } else {
                TypeError.value = false;
            }
        }

        const cancel = function () {
            console.log(Description.value);
            
            descriptionError.value = null;
            Description.value = '';

            valueError.value = null;
            Value.value = '';

            DateError.value = null;
            DateM.value = '';

            TypeError.value = null;
            Type.value = '';

            CategoryError.value = null;
            Category.value = '';

            PlaceError.value = null;
            Place.value = '';
        }

        return {
            t,
            add,
            descriptionError,
            Description,
            Comment,
            Value,
            valueError,
            DateM,
            DateError,
            Place,
            PlaceError,
            Category,
            CategoryError,
            Type,
            TypeError,
            ValueLabel,
            ShareUser,
            cancel
        };
    },
    data() {
        return {
            expenseCategories: ['Pessoal', 'Alimentação', 'Familiar', 'Casa', 'Entretenimento'],
            incomeCategories: ['Rendas', 'Salários'],
            Shared: false,
            isEditing: [],
            isEditingIncome: [],
            editedCategory: '',
            editedIncomeCategory: '',
            showErrorExpense: false,
            showErrorIncome: false,
            showErrorAdd: false,
            errorTimeout: null,
            isFocused: false,
            isCategoryFocused: false,
            isTypeFocused: false,
            isDateFocused: false,
            SetBorder: true,
            showAlert: false
        };
    },
    components: {
        MaterialAlert,
        MaterialInput,
    },
    mounted() {
        document.addEventListener('click', this.handleClickOutsideCategory);
        document.addEventListener('click', this.handleClickOutsideType);
    },
    computed: {
        displayCategories() {
            if (this.Type == 'Despesa')
                return this.expenseCategories;
            else if (this.Type == 'Receita')
                return this.incomeCategories;
            else
                return '';
        },
    },
    watch: {
        Type() {
            this.Category = '';
            this.isCategoryFocused = false;
        },
    },
    methods: {
        alert() {
            this.showAlert = !this.showAlert;
        },
        selectType(type) {
            this.Type = type;
        },
        selectCategory(category) {
            this.Category = category;
        },
        handleCategoryFocus() {
            console.log("antes" + this.isCategoryFocused);
            this.isCategoryFocused = true;
            console.log("depois" + this.isCategoryFocused);
        },
        handleClickOutsideCategory(event) {
            const dropdown = this.$refs.categoryDropdown;
            if (dropdown && !dropdown.contains(event.target)) {
                if (this.Category == '')
                    this.isCategoryFocused = false;
            }
        },
        handleClickOutsideType(event) {
            const dropdown = this.$refs.typeDropdown;
            if (dropdown && !dropdown.contains(event.target)) {
                if (this.Type == '')
                    this.isTypeFocused = false;
            }
        },
        handleTypeFocus() {
            this.isTypeFocused = true;
        },
        handleDateFocus(){
            this.isDateFocused = true;
            this.SetBorder = false;
        },
        handleDateBlur(){
            this.isDateFocused = false;
            this.SetBorder = true;
        },
        handleFocus() {
            //event.target.type = 'date';
            this.isFocused = true;
            if (this.DateError !== null)
                this.DateError = null;
        },
        handleBlur() {
            //event.target.type = 'text';
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

.invalid-icon {
    position: absolute;
    right: 10px;
    /* Ajuste conforme necessário */
    pointer-events: none;
    color: #fd5c6f;
    font-size: medium;
    /* Ajuste a cor conforme necessário */
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
    /* Alinha as labels à direita */
}

.form-input {
    flex: 2;
}

.material-input {
    width: 100%;
    position: relative;
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
    color: #1a73e8;
    z-index: 2;
    background-color: white !important;
}

.row {
    align-content: center;
}

.border {
    border: 1px solid #d2d6da;
    border-radius: 0.375rem;
}
</style>
