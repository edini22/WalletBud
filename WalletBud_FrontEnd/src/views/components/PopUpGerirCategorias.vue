<template>
    <div class="modal fade" id="categoryModal" tabindex="-1" aria-labelledby="categoryModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="categoryModalLabel">{{ $t ('Gerir Categorias de Movimentos') }}</h5>
                </div>
                <div class="modal-body">
                    <div class="category-section">
                        <h4>{{ $t ('Categorias para Despesas') }}</h4>
                        <ul class="category-list">
                            <p class="message" v-if="expenseCategories.length === 0">{{ $t('Adicione Categorias!')}}</p>
                            <li v-for="(category, index) in expenseCategories" :key="index">
                                <span v-if="!isEditing[index]">{{ category }}</span>
                                <input v-else v-model="editedCategory" @blur="saveCategory(index)" />
                                <div class="buttons">
                                    <button @click="toggleEditMode(index)">
                                        <i v-if="isEditing[index]">✅</i>
                                        <i v-else>✏️</i>
                                    </button>
                                    <button @click="deleteCategory('Despesa', index)">🗑️</button>
                                </div>
                            </li>
                            <p v-if="showErrorExpense && !isEditing[index]" class="error-category">{{ $t('Nomes de Categorias não podem ser nulos') }}</p>
                        </ul>
                    </div>
                    <div class="category-section">
                        <h4>{{ $t ('Categorias para Receitas')}}</h4>
                        
                        <!-- Código existente ... -->
                        <ul class="category-list">
                            <p class="message" v-if="incomeCategories.length === 0">{{ $t('Adicione Categorias!')}}</p>
                            <li v-for="(category, index) in incomeCategories" :key="index">
                            <span v-if="!isEditingIncome[index]">{{ category }}</span>
                            <input v-else v-model="editedIncomeCategory" @blur="saveIncomeCategory(index)" />
                            <div class="buttons">
                                <button @click="toggleIncomeEditMode(index)">
                                    <i v-if="isEditingIncome[index]">✅</i>
                                    <i v-else>✏️</i>
                                </button>
                                <button @click="deleteCategory('Receita', index)">🗑️</button>
                            </div>
                            </li>
                            <p v-if="showErrorIncome && !isEditingIncome[index]" class="error-category">{{ $t('Nomes de Categorias não podem ser nulos') }}</p>
                        </ul>
                    </div>
                    <div class="add-category-section">
                        <h4>{{ $t ('Adicionar Categoria')}}</h4>
                        <div class="form-group">
                            <label for="categoryType" style="color: black;">{{ $t('Selecionar o tipo de movimento')}}:</label>
                            <select v-model="newCategoryType" class="small-select" id="categoryType">
                                <option value="Despesa">{{ $t ('Despesa')}}</option>
                                <option value="Receita">{{ $t ('Receita')}}</option>
                            </select>
                        </div>
                        <input type="text" v-model="newCategoryName" :placeholder=" $t ('Escreva uma categoria')" class="category-input">
                        <p v-if="showErrorAdd" class="error-category">{{ $t('Nomes de Categorias não podem ser nulos') }}</p>
                        <button @click="addCategory">{{ $t ('Adicionar')}}</button>
                        
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">{{ $t ('Fechar')}}</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import { useI18n } from 'vue-i18n';

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
        errorTimeout: null
        };
    },
    methods: {
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
    color: black;
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

.add-category-section {
    text-align: center;
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
</style>
