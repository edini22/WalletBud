<template>
    <div class="modal fade" id="categoryModal" tabindex="-1" aria-labelledby="categoryModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="categoryModalLabel">{{ $t ('Gerir Categorias de Movimentos') }}</h5>
                </div>
                <div class="modal-body">
                    <div class="category-section">
                        <h6>{{ $t ('Categorias para Despesas') }}</h6>
                        <ul class="category-list">
                            <p class="message" v-if="expenseCategories.length === 0">{{ $t('Adicione Categorias!')}}</p>
                            <li v-for="(category, index) in expenseCategories" :key="index">
                                <span class='category' v-if="!isEditing[index]">{{ category }}</span>
                                <input class="input-group input-group-outline custom" v-else v-model="editedCategory" @blur="saveCategory(index)" />
                                <div class="buttons">
                                    <button @click="toggleEditMode(index)" style="background-color: white;">
                                        <i class="material-icons" style="color: #344767;" v-if="isEditing[index]">save</i>
                                        <i class="material-icons" style="color: #344767;" v-else>edit</i>
                                    </button>
                                    <button @click="deleteCategory('Despesa', index)" style="background-color: white;">
                                        <i class="material-icons" style="color: #344767;">delete</i>
                                    </button>
                                </div>
                            </li>
                            <p v-if="showErrorExpense && !isEditing[index]" class="error-category">{{ $t('Nomes de Categorias não podem ser nulos') }}</p>
                        </ul>
                    </div>
                    <div class="category-section">
                        <h6 class="custom-title">{{ $t ('Categorias para Receitas')}}</h6>
                        
                        <!-- Código existente ... -->
                        <ul class="category-list">
                            <p class="message" v-if="incomeCategories.length === 0">{{ $t('Adicione Categorias!')}}</p>
                            <li v-for="(category, index) in incomeCategories" :key="index">
                            <span class='category' v-if="!isEditingIncome[index]">{{ category }}</span>
                            <input class="input-group input-group-outline custom" v-else v-model="editedIncomeCategory" @blur="saveIncomeCategory(index)" />
                            <div class="buttons">
                                <button @click="toggleIncomeEditMode(index)" style="background-color: white;">
                                    <i class="material-icons" style="color: #344767;" v-if="isEditingIncome[index]">save</i>
                                    <i class="material-icons" style="color: #344767;" v-else>edit</i>
                                </button>
                                <button @click="deleteCategory('Receita', index)" style="background-color: white;">
                                    <i class="material-icons" style="color: #344767;">delete</i>
                                </button>
                            </div>
                            </li>
                            <p v-if="showErrorIncome && !isEditingIncome[index]" class="error-category">{{ $t('Nomes de Categorias não podem ser nulos') }}</p>
                        </ul>
                    </div>

                </div>
                <div class="modal-footer">
                    <div class="modal-body">
                        <h5>{{ $t ('Adicionar Categoria')}}</h5>
                        <label for="categoryType" style="color: black;">{{ $t('Selecionar o tipo de movimento')}}:</label>
                        <div class="input-group input-group-outline mb-2" style="position: relative;  border-radius: 0.375rem;">
                            <select v-model="newCategoryType" class="form-control form-control-default" id="categoryType">
                                <option value="Despesa">{{ $t ('Despesa')}}</option>
                                <option value="Receita">{{ $t ('Receita')}}</option>
                            </select>
                            <i class="material-icons arrow-icon">keyboard_arrow_down</i>
                        </div>

                        <div class="input-group input-group-outline mb-3" 
                                :class="{'is-focused': isFocused}"
                            >
                            <label class="form-label">{{ $t('Escreva uma categoria') }}</label>
                            <input class="form-control form-control-default" type="text" v-model="newCategoryName" @focus="handleFocus" @blur="handleBlur">
                        </div>
                        <MaterialAlert v-if="showErrorAdd" color="danger" class="font-weight-light">
                            <span class="text-sm">{{ $t('Indica um nome para a categoria!') }}</span>
                        </MaterialAlert>
                        <p class="btn btn-default bg-gradient-info mb-0" @click="addCategory">{{ $t ('Adicionar')}}</p>
                    </div>
                </div>
                <div class="modal-footer mt-0">
                    <p class="btn btn-default bg-gradient-primary mb-1" data-bs-dismiss="modal">{{ $t ('Fechar')}}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import { useI18n } from 'vue-i18n';
import MaterialAlert from "@/components/MaterialAlert.vue";

export default {
    setup() {
        const { t } = useI18n();
        return { t };
    },
    data() {
        return {
            expenseCategories: ['Pessoal', 'Alimentação'],
            incomeCategories: [],
            newCategoryType: 'Despesa',
            newCategoryName: '',
            isEditing: [],
            isEditingIncome: [],
            editedCategory: '',
            editedIncomeCategory: '',
            showErrorExpense: false,
            showErrorIncome: false,
            showErrorAdd: false,
            errorTimeout: null,
            isFocused: false
        };
    },
    components: {
        MaterialAlert,
    },
    methods: {
        handleFocus() {
            this.isFocused = true;
        },
        handleBlur() {
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

.message {
    text-align: center;
    color: #495057;
    font-weight: 400;
}

.category {
    color: #15181a;
    font-weight: 400;
}

.error-category {
    text-align: center;
    font-weight: bold;
    background-color: red;
    color: white;
    padding: 5px;
    border-radius: 8px;
    font-size: 17px;
}

.category-input {
    width: 100%; 
    padding: 5px;
    margin-bottom: 10px;
    border-radius: 5px;
    border: 1px solid ;
}

.form-group {
    margin-bottom: 10px;
    text-align: left;
    width: 100%; 
    padding: 0; 
}

.small-select {
    width: 100%; 
    padding: 5px;
    border-radius: 5px;
}

.modal-content {
    width: 100%;
}

.modal {
  z-index: 1050; /* padrão do Bootstrap para modais */
}

.container {
    background: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin: 0 auto;
}

h2, h4 {
    text-align: left;
}

.category-section {
    margin-bottom: 20px;
    font-weight: bold;
    color: black;
}

.category-list {
    list-style: none;
    padding-left: 0; 
    margin-left: 0;
}

ul li {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 5px 0;
}

ul li .buttons {
    margin-left: auto;
}

ul li button {
    border: none;
    background: none;
    margin-left: 5px;
}

button {
    padding: 5px 10px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
}

button:hover {
    background-color: #0056b3;
}

button.btn-secondary {
    background-color: red;
}

button.btn-close {
    background-color: rgb(223, 76, 76);
}


.custom {
    background: none;
    border: 1px solid #1a73e8;
    border-radius: 0.375rem;
    border-top-left-radius: 0.375rem !important;
    border-bottom-left-radius: 0.375rem !important;
    padding: 0.625rem 0.75rem !important;
    line-height: 1.3 !important;
    font-size: 0.875rem;
    font-weight: 400;
    width: 50%;
}

.input-group-outline.custom:focus {
    border-color: #1a73e8 !important;
    box-shadow:  inset 0 1px #1a73e8, 
        inset 1px 0 #1a73e8, 
        inset -1px 0 #1a73e8,
        inset 0 -1px #1a73e8;
    outline: none; /* Remove o outline padrão */
}

.input-group {
    display: flex;
    align-items: center;
    position: relative;
}

.arrow-icon {
    position: absolute;
    right: 10px;  /* Ajuste conforme necessário */
    pointer-events: none;
    color: #000; /* Ajuste a cor conforme necessário */
}
    
</style>
