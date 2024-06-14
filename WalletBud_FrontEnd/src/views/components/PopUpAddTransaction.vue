<template>
    <div class="modal fade" id="transactionModal" tabindex="-1" aria-labelledby="transactionModalLabel"
    aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="categoryModalLabel">{{ $t('Adicionar Movimento') }}</h5>
                </div>

                <div class="modal-body">
                    <div v-if="Type == 'Despesa'" class="nav-wrapper position-relative end-0 mb-4">
                        <ul class="nav nav-pills nav-fill p-1" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active tab-button" @click="showTab(0)">{{ $t('Informação') }}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link tab-button" @click="showTab(1)">{{ $t('Dividir Despesa') }}</a>
                            </li>
                        </ul>
                    </div>

                    <div class="tab tab-content active" id="tab-0">

                        <!-- Descrição -->
                        <div class="form-group form-row">
                            <label for="description" class="form-label">{{ $t('Nome') }}</label>
                            <div v-if="descriptionError === true" class="form-input mb-3">
                                <material-input class="material-input" id="description" type="text"
                                    :label="$t('Indique o nome')" name="description"
                                    @update:value="Description = $event" :value="Description" error />
                            </div>
                            <div v-if="descriptionError === false" class="form-input mb-3">
                                <material-input class="material-input" id="description" type="text" name="description"
                                    :value="Description" @update:value="Description = $event" success />
                            </div>
                            <div v-if="descriptionError === null" class="form-input mb-3">
                                <material-input class="material-input" id="description" type="text" :value="Description"
                                    :label="$t('Indique o nome')" name="description"
                                    @update:value="Description = $event" />
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
                                <material-input class="material-input" id="value" type="number" name="value"
                                    :value="Value" @update:value="Value = $event" success />
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
                            <div v-if="DateError === true" class="form-input mb-3 date-input-wrapper"
                                :class="{ 'dropdown-focused-error': isDateFocused, 'border': SetBorder }"
                                style="position: relative;">
                                <material-input class="material-input" id="date" type="date" name="date" :value="DateM"
                                    @update:value="DateM = $event" error @focus="handleDateFocus"
                                    @blur="handleDateBlur" />
                            </div>
                            <div v-if="DateError === false" class="form-input mb-3">
                                <material-input class="material-input" :class="{ 'is-focused': isFocused }" id="date"
                                    type="date" name="date" :value="DateM" @update:value="DateM = $event" success
                                    @focus="handleFocus" @blur="handleBlur" label="Indique a data" />
                            </div>
                            <div v-if="DateError === null" class="form-input mb-3  date-input-wrapper">
                                <material-input class="material-input" :class="{ 'is-focused': isFocused }" id="date"
                                    type="date" name="date" @update:value="DateM = $event" :value="DateM"
                                    label="Indique a data" @focus="handleFocus" @blur="handleBlur" />
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
                                    <button v-else
                                        class="cursor-pointer form-control form-control-default material-input"
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
                                    <button v-else
                                        class="cursor-pointer form-control form-control-default material-input"
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

                        <MaterialAlert v-if="showAlert" color="danger" class="font-weight-light" dismissible
                            @click="alert">
                            <span class="text-sm">{{ $t('Escolha o tipo de movimento') }}</span>
                        </MaterialAlert>

                        <!-- Local -->
                        <div class="form-group form-row">
                            <label for="place" class="form-label">{{ $t('Local') }}</label>
                            <div v-if="PlaceError === true" class="form-input mb-3">
                                <material-input class="material-input" id="place" type="text"
                                    :label="$t('Indique o local')" name="place" :value="Place"
                                    @update:value="Place = $event" error />
                            </div>
                            <div v-if="PlaceError === false" class="form-input mb-3">
                                <material-input class="material-input" id="place" type="text" name="place"
                                    :value="Place" @update:value="Place = $event" success />
                            </div>
                            <div v-if="PlaceError === null" class="form-input mb-3">
                                <material-input class="material-input" id="place" type="text"
                                    :label="$t('Indique o local')" name="place" :value="Place"
                                    @update:value="Place = $event" />
                            </div>
                        </div>

                        <div class="modal-footer d-block justify-content-center">
                            <div class="align-items-center text-center">
                                <i class="material-icons align-self-center comment"
                                    style="max-width: 24px; color: #344767">insert_comment_outlined</i>
                                <h5>{{ $t('Comentário') }}</h5>

                                <div class="form-input mb-3">
                                    <material-input class="material-input" id="comment" type="text" :value="Comment"
                                        :label="$t('Escreva o seu comentário')" @update:value="Comment = $event" />
                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="tab tab-content" id="tab-1">

                        <div class="mt-2 d-flex">
                            <label for="sharedExpense" class="mb-0 form-label">{{ $t('Partilhar Despesa') }}</label>
                            <div class="form-check form-switch ps-0 ms-auto my-auto">
                                <input class="form-check-input mt-1 ms-auto" type="checkbox" v-model="ShareExpense" />
                            </div>
                        </div>
                        <hr class="horizontal dark my-sm-4" />

                        <div v-if="ShareExpense">
                            <!-- Shared expense -->
                            <div class="form-group">
                                <label for="sharedExpense" class="form-label mb-3">{{ $t('Partilhar despesa com:')
                                    }}</label>
                                <div class="form-input mb-3">
                                    <div v-if="emailError === true && emailErrorStore === null" class="mb-3">
                                        <material-input class="material-input mb-3" id="email" type="email" :label="$t('Indique o email do utilizador')" name="email"
                                        :value="newUserEmail" @update:value="newUserEmail = $event" error />
                                    </div>

                                    <div v-if="emailError === true && emailErrorStore !== null" class="mb-3">
                                        <material-input class="material-input mb-3" id="email" type="email" :label= emailErrorStore name="email"
                                        :value="newUserEmail" @update:value="newUserEmail = $event" error />
                                    </div>
                                    
                                    <div v-if="emailError === false" class="mb-3">
                                        <material-input class="material-input mb-3" id="email" type="email" name="email"
                                        :value="newUserEmail" @update:value="newUserEmail = $event" success />
                                    </div>

                                    <div v-if="emailError === null" class="mb-3">
                                        <material-input class="material-input mb-3" id="email" type="email" name="email"
                                        :value="newUserEmail" @update:value="newUserEmail = $event" :label="$t('Indique o email do utilizador')"/>
                                    </div>

                                    <p class="btn btn-default bg-gradient-info mb-1" 
                                    @click="addUser">{{ $t('Adicionar Utilizador') }}</p>
                                </div>
                            </div>
                            <hr class="horizontal dark my-sm-4" />
                            <MaterialAlert v-if="showAlertUsers" color="danger" @click="hideAlertUsers" class="font-weight-light" dismissible>
                                <span class="text-sm">{{ $t('Adicione utilizadores para partilhar a despesa') }}</span>
                            </MaterialAlert>
                            <div v-if="sharedUsers.length > 0">
                            <div class="align-items-center text-center">
                                <label class="form-label align-self-center mb-3"
                                    style="font-size: large; font-weight: 600;">{{ Value }}€ a dividir com:</label>
                            </div>
                            <div class="form-row" v-for="(user, index) in sharedUsers" :key="index">
                                <div class="form-row">
                                <material-checkbox :id="'checkbox_' + index" class="font-weight-light" checked
                                    @click="removeUser(index)">
                                </material-checkbox>
                                <span style="font-size: 16px; padding-left: 10px;">{{ user }} </span>
                                </div>
                                <span style="font-size: 16px; padding-right: 10px;">{{ calculateShare() }}</span>
                            </div>
                        </div>
                        </div>

                    </div>
                </div>


                <div class="modal-footer mt-0">
                    <p v-if="Type == 'Despesa' && showShareTab == false" class="btn btn-default bg-gradient-info mb-1"
                        @click="showTab(1)">{{ $t('Próximo') }}</p>
                    <p v-else class="btn btn-default bg-gradient-info mb-1" @click="add">{{ $t('Adicionar') }}</p>
                    <p class="btn btn-default bg-gradient-primary mb-1" data-bs-dismiss="modal" @click="cancel">{{
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
import MaterialCheckbox from "@/components/MaterialCheckbox.vue";
//import NavPill from './NavPill.vue';
//import { transactionStore } from "@/store/transactionStore.js";
import { ref, reactive } from 'vue';


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
        const newUserEmail = ref('');
        const sharedUsers = reactive([]);
        const shareValue = ref(null);
        const emailError = ref(null);
        const emailErrorStore = ref(null);
        const resetTab = ref(false);
        const ShareExpense = ref(false);
        const showAlertUsers = ref(false);
        let timestamp = ref(null);
        let isValid = true;

        function validarEmail(email) {
            const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

            if (!emailRegex.test(email.value)) {
                emailError.value = true;
                newUserEmail.value = '';
                emailErrorStore.value = `${t('Email inválido')}`;
                isValid = false;
            } else {
                emailError.value = false;
                isValid = true;
            }
        }

        function convertToTimestamp(dateString) {
            const dateObject = new Date(dateString);

            return dateObject.getTime();
        }

        const addUser = async () => {

            validarEmail(newUserEmail);

            if(isValid == true){

                
                //fazer post do email para ver se existe
                /* try {

                } catch (error) {
                    // Tratamento de erro específico para " ... "
                    if (error.message === " error message ... ") {
                        emailErrorStore.value = `${t('Email não registado na plataforma.')}`;
                        emailError.value = true;
                        state = true;
                        alert("Não é possível partilhar despesas com utilizadores não registados!");
                        return;
                    } else {
                        alert("Erro ao registar: " + error.message);
                    }
                }

                if(!existe) {

                }else{
                    sharedUsers.push(newUserEmail);
                    newUserEmail.value = '';
                    emailError.value = null;
                }*/

                sharedUsers.push(newUserEmail.value);
                newUserEmail.value = '';
                emailError.value = null;
            }
        }

        const checkInputs = function () {
            if (!Description.value.trim())
                descriptionError.value = true;
            else
                descriptionError.value = false;

            if (!Value.value)
                valueError.value = true;
            else if (Value.value < 0) {
                valueError.value = true;
                ValueLabel.value = 'Indique um montante positivo'
            }
            else
                valueError.value = false;

            if (!DateM.value)
                DateError.value = true;
            else
                DateError.value = false;

            if (!Place.value.trim())
                PlaceError.value = true;
            else
                PlaceError.value = false;

            if (!Category.value)
                CategoryError.value = true;
            else
                CategoryError.value = false;

            if (!Type.value)
                TypeError.value = true;
            else
                TypeError.value = false;

        }

        const goToShare = function () {
            checkInputs();
            if (TypeError.value == false && descriptionError.value == false && DateError.value == false
                && valueError.value == false && PlaceError.value == false && CategoryError.value == false)
                return true;
            else
                return false;
        }

        const add = function () {

            if(Type.value == 'Receita' || Type.value == ''){
                checkInputs();

                if (TypeError.value == false && descriptionError.value == false && DateError.value == false
                && valueError.value == false && PlaceError.value == false && CategoryError.value == false){
                    
                    timestamp.value = convertToTimestamp(DateM.value);
                    console.log("data: " + DateM.value);
                    console.log("timestamp: " + timestamp.value);
                    //POST da Receita
                }

            }else{
                if(showAlertUsers.value == true)
                    showAlertUsers.value = false;

                if (ShareExpense.value == true && sharedUsers.length === 0) {
                    console.log("sharedExpense:" + ShareExpense.value)
                    console.log("sharedUsers length:" + sharedUsers.length)
                    showAlertUsers.value = true;
                }else{
                    //date to timestamp
                    //POST da Despesa
                }

            }
        }

        const clearSharedUsers = function() {
            if (sharedUsers.length > 0) {
                console.log('Before clearing:', sharedUsers.value);
                sharedUsers.splice(0, sharedUsers.length); // Esvaziando o array reativamente
                console.log('After clearing:', sharedUsers.value);
            } else {
                console.error("sharedUsers is undefined");
            }
        };

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

            resetTab.value = true;

            if(ShareExpense.value == true){
                ShareExpense.value = false;
                console.log(sharedUsers.value);
                clearSharedUsers();
                console.log("after cleared:" + sharedUsers.value);
                newUserEmail.value = '';


            }
        }

        return {
            t,
            add,
            cancel,
            checkInputs,
            goToShare,
            addUser,
            clearSharedUsers,
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
            ShareExpense,
            newUserEmail,
            sharedUsers,
            shareValue,
            emailError,
            emailErrorStore,
            showAlertUsers,
            resetTab
        };
    },
    data() {
        return {
            expenseCategories: ['Pessoal', 'Alimentação', 'Familiar', 'Casa', 'Entretenimento'],
            incomeCategories: ['Rendas', 'Salários'],
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
            showAlert: false,
            showShareTab: false,
            Tab: 0,
        };
    },
    components: {
        MaterialAlert,
        MaterialInput,
        MaterialCheckbox,
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
        resetTab() {
            if(this.Tab !== 0)
            this.showTab(0);
            this.resetTab = false;
        }
    },
    methods: {
        hideAlertUsers(){
            this.showAlertUsers = false;
        },
        calculateShare() {
            if (this.sharedUsers === 0) return ''; // Evita divisão por zero
            this.shareValue = this.Value / (this.sharedUsers.length + 1);
            return `${this.shareValue.toFixed(2)} €`; // Formata o valor com duas casas decimais
        },
        removeUser(index) {
            this.sharedUsers.splice(index, 1);
        },
        showTab(index) {
            this.Tab = index;
            const tabs = document.querySelectorAll('.tab');
            const buttons = document.querySelectorAll('.tab-button');

            if (index == 1) {
                if (this.goToShare() == true) {
                    tabs.forEach((tab, i) => {
                        tab.classList.toggle('active', i === index);
                        buttons[i].classList.toggle('active', i === index);
                    });
                    this.showShareTab = !this.showShareTab;
                    console.log("showShare index 1:" + this.showShareTab);
                }
            } else {
                tabs.forEach((tab, i) => {
                    tab.classList.toggle('active', i === index);
                    buttons[i].classList.toggle('active', i === index);
                });
                if (this.showShareTab == true)
                    this.showShareTab = false;
                console.log("showShare else:" + this.showShareTab);
            }
        },
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
        handleDateFocus() {
            this.isDateFocused = true;
            this.SetBorder = false;
        },
        handleDateBlur() {
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
