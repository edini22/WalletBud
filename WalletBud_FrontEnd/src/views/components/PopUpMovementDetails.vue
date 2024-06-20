<template>
    <div class="modal fade" id="detailsModal" tabindex="-1" aria-labelledby="transactionModalLabel" aria-hidden="true"
        data-bs-backdrop='static' data-bs-keyboard='false'>
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="transactionModalLabel">{{ $t('Detalhes do Movimento') }}</h5>
                </div>

                <div class="modal-body scroll-container">
                    <div v-if="transaction !== null && transaction.tipo == 'despesa'"
                        class="nav-wrapper position-relative end-0 mb-4">
                        <ul class="nav nav-pills nav-fill p-1" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active tab-button-details" @click="showTab(0)">{{ $t('Informação')
                                    }}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link tab-button-details" @click="showTab(1)">{{ $t('Dividir Despesa')
                                    }}</a>
                            </li>
                        </ul>
                    </div>

                    <div class="tab-details tab-content active" id="tab-0">

                        <!-- Name -->
                        <div class="form-group form-row">
                            <label for="description" class="form-label">{{ $t('Nome') }}
                                <p v-if="edit" class="required"> *</p>
                            </label>
                            <label class="detail form-label cuter" v-if="transaction !== null && edit == false">{{
                                transaction.name }}</label>
                            <div v-if="nameError === true && edit == true" class="form-input mb-1">
                                <material-input class="material-input" id="description" type="text"
                                    :label="$t('Indique o nome')" name="description" :value="Name"
                                    @update:value="Name = $event" error />
                            </div>
                            <div v-if="nameError === false && edit == true" class="form-input mb-1">
                                <material-input class="material-input" id="description" type="text" name="description"
                                    :value="Name" @update:value="Name = $event" success />
                            </div>

                            <div v-if="nameError === null && edit == true" class="form-input mb-1">
                                <material-input class="material-input" id="description" type="text"
                                    :value="transaction.name" :label="$t('Indique o nome')" name="description"
                                    :class="{ 'is-focused': transaction.name !== null && edit == true }"
                                    @update:value="Name = $event" />
                            </div>
                        </div>

                        <!-- Descrição -->
                        <div v-if="transaction !== null && transaction.descricao !== null" class="form-group form-row">
                            <label for="place" class="form-label">{{ $t('Descrição') }}</label>
                            <label class="detail form-label cuter" v-if="transaction !== null && edit == false">{{
                                transaction.descricao }}</label>
                            <div v-if="edit == true" class="form-input mb-1">
                                <material-input class="material-input" id="description" type="text"
                                    :class="{ 'is-focused': transaction.descricao !== null && edit == true }"
                                    name="description" :value="transaction.descricao"
                                    @update:value="Description = $event" :label="$t('Indique a descrição')" />
                            </div>
                        </div>
                        <div v-if="transaction !== null && transaction.descricao === null && edit"
                            class="form-group form-row">
                            <label v-if="edit" for="place" class="form-label">{{ $t('Descrição') }}</label>
                            <label class="detail form-label" v-if="transaction !== null && edit == false">{{
                                transaction.descricao }}</label>

                            <div class="form-input mb-1">
                                <material-input class="material-input" id="place" type="text"
                                    :label="$t('Indique a descrição')" name="place" :value="transaction.descricao"
                                    @update:value="Description = $event" />
                            </div>
                        </div>

                        <!-- Montante -->
                        <div v-if="transaction !== null" class="form-group form-row">
                            <label for="value" class="form-label">{{ $t('Montante') }}
                                <p v-if="edit" class="required"> *</p>
                            </label>
                            <label class="detail form-label" v-if="transaction !== null && edit == false">{{
                                transaction.value }}</label>

                            <div v-if="valueError === true && valueNegative === null && edit === true"
                                class="form-input mb-1">
                                <material-input class="material-input" id="value" type="number" :value="Value"
                                    :label="$t('Indique um montante válido')" name="value" error
                                    @update:value="Value = $event" />
                            </div>
                            <div v-if="valueError === true && valueNegative === true && edit === true"
                                class="form-input mb-1">
                                <material-input class="material-input" id="value" type="number" :value="Value"
                                    :label="$t('Indique um valor maior que zero')" name="value" error
                                    @update:value="Value = $event" />
                            </div>
                            <div v-if="valueError === false && edit === true" class="form-input mb-1">
                                <material-input class="material-input" id="value" type="number" name="value"
                                    :value="Value" success @update:value="Value = $event" />
                            </div>
                            <div v-if="valueError === null && edit === true" class="form-input mb-1">
                                <material-input class="material-input" id="value" type="number"
                                    :value="transaction.value" :label="$t('Indique um montante')" name="value"
                                    :class="{ 'is-focused': transaction.value !== null && edit == true }"
                                    @update:value="Value = $event" />
                            </div>
                        </div>

                        <!-- Data -->
                        <div v-if="transaction !== null" class="form-group form-row">
                            <label for="date" class="form-label">
                                {{ $t('Data do Movimento') }}
                                <p v-if="edit" class="required"> *</p>
                            </label>
                            <label class="detail form-label" v-if="edit == false">{{ transaction.date }}</label>

                            <div v-if="DateError === true && edit == true" class="form-input mb-1 date-input-wrapper"
                                :class="{ 'dropdown-focused-error': isDateFocused, 'border': SetBorder }"
                                style="position: relative;">
                                <material-input class="material-input" id="date" type="date" name="date" :value="DateM"
                                    error @update:value="DateM = $event" @focus="handleDateFocus"
                                    @blur="handleDateBlur" />
                            </div>
                            <div v-if="DateError === false && edit == true" class="form-input mb-1">
                                <material-input class="material-input" :class="{ 'is-focused': isFocused }" id="date"
                                    type="text" name="date" :value="DateM" success @focus="handleFocus"
                                    @update:value="DateM = $event" @blur="handleBlur" />
                            </div>
                            <div v-if="DateError === null && edit == true" class="form-input mb-1  date-input-wrapper">
                                <material-input class="material-input"
                                    :class="{ 'is-focused': transaction.date !== null && edit == true }" id="date"
                                    type="date" name="date" :value="transaction.date" :label="$t('Indique a data')"
                                    @focus="handleFocus" @blur="handleBlur" @update:value="DateM = $event" />
                                <i class="material-icons date-icon">date_range</i>
                            </div>
                        </div>

                        <!-- Tipo -->
                        <div v-if="transaction !== null" class="dropdown" ref="typeDropdown">
                            <div class="form-group form-row">
                                <label for="Type" class="form-label">{{ $t('Tipo de movimento') }}
                                </label>
                                <label class="detail form-label">{{
                                    transaction.tipo }}</label>
                            </div>
                        </div>

                        <!-- Recorrência -->
                        <div v-if="transaction !== null && transaction.repeticao !== null" class="dropdown"
                            ref="recorrenceDropdown">
                            <div class="form-group form-row">
                                <label for="place" class="form-label">{{ $t('Recorrência') }}
                                </label>
                                <label class="detail form-label" v-if="transaction !== null && transaction.repeticao == 0">
                                    {{
                                        $t('Única (Não Fixa)') }}</label>
                                <label class="detail form-label" v-if="transaction !== null && transaction.repeticao > 0">{{
                                    $t('Recorrente (Fixa)') }}</label>

                            </div>
                        </div>

                        <!-- Repetição -->
                        <div v-if="transaction !== null && transaction.repeticao > 0 || transaction !== null && Recorrence === 'Recorrente (Fixa)'"
                            class="dropdown" ref="repeatDropdown">
                            <div class="form-group form-row">
                                <label for="Type" class="form-label">{{ $t('Periodicidade') }}</label>
                                <label class="detail form-label" v-if="transaction !== null && edit == false">
                                    {{ transaction.repeticao == 1 ? 'Todos os dias' :
                                        transaction.repeticao == 2 ? 'Todas as semanas' :
                                        transaction.repeticao == 3 ? 'Todos os meses' :
                                        transaction.repeticao == 4 ? 'Todos os anos' :
                                    '' }}
                                </label>
                            </div>
                        </div>

                        <!-- Categoria -->
                        <div class="dropdown" ref="categoryDropdown">
                            <div class="form-group form-row">
                                <label for="category" class="form-label">{{ $t('Categoria') }}
                                    <p v-if="edit" class="required"> *</p>
                                </label>
                                <label class="detail form-label" v-if="transaction !== null && edit == false">{{
                                    transaction.categoria }}</label>
                                <div v-if="CategoryError === null && edit == true || CategoryError === false && edit == true"
                                    class="input-group input-group-outline form-input mb-1"
                                    style="border-radius: 0.375rem;">
                                    <button v-if="displayCategories.length > 0"
                                        class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-null': isCategoryFocused }" id="dropdownTable"
                                        data-bs-toggle="dropdown" style="text-align:left; color: #7b809a"
                                        @focus="handleCategoryFocus">
                                        {{ Category.name || $t('Selecione a categoria') }}
                                    </button>
                                    <!-- show this button if type has not been chosen -->
                                    <button v-else
                                        class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-null': isCategoryFocused }" id="dropdownTable"
                                        style="text-align:left; color: #7b809a" @click="alert">
                                        {{ Category.name || $t('Selecione a categoria') }}
                                    </button>
                                    <i class="material-icons arrow-icon">keyboard_arrow_down</i>
                                    <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5" aria-labelledby="dropdownTable">
                                        <li v-for='(category, index) in displayCategories' :key="index">
                                            <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectCategory(category)">
                                                {{ category.name }}
                                            </a>
                                        </li>
                                    </ul>
                                </div>

                                <div v-if="CategoryError === true && edit == true"
                                    class="input-group input-group-outline form-input mb-1 is-invalid"
                                    style="border-radius: 0.375rem;">
                                    <button v-if="displayCategories.length > 0"
                                        class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-error': isCategoryFocused }" id="dropdownTable"
                                        data-bs-toggle="dropdown" style="text-align:left; color: #7b809a"
                                        @focus="handleCategoryFocus">
                                        {{ Category.name || $t('Selecione a categoria') }}
                                    </button>
                                    <!-- show this button if type has not been chosen -->
                                    <button v-else
                                        class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-error': isCategoryFocused }" id="dropdownTable"
                                        style="text-align:left; color: #7b809a" @click="alert">
                                        {{ Category.name || $t('Selecione a categoria') }}
                                    </button>
                                    <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5" aria-labelledby="dropdownTable">
                                        <li v-for='(category, index) in displayCategories' :key="index">
                                            <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectCategory(category)">
                                                {{ category.name }}
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
                        <div v-if="transaction !== null && transaction.local !== null" class="form-group form-row">
                            <label for="place" class="form-label">{{ $t('Local') }}</label>
                            <label class="detail form-label" v-if="transaction !== null && edit == false">{{
                                transaction.local }}</label>
                            <div v-if="edit == true" class="form-input mb-1">
                                <material-input class="material-input" id="place" type="text"
                                    :label="$t('Indique o local')" name="place" :value="transaction.local"
                                    :class="{ 'is-focused': transaction.local !== null && edit == true }"
                                    @update:value="Place = $event" />
                            </div>
                        </div>
                        <div v-if="transaction !== null && transaction.local === null && edit"
                            class="form-group form-row">
                            <label v-if="edit" for="place" class="form-label">{{ $t('Local') }}</label>
                            <label class="detail form-label" v-if="transaction !== null && edit == false">{{
                                transaction.local }}</label>

                            <div class="form-input mb-1">
                                <material-input class="material-input" id="place" type="text"
                                    :label="$t('Indique a descrição')" name="place" :value="transaction.local"
                                    @update:value="Place = $event" />
                            </div>
                        </div>

                        <label v-if="edit == true" class="form-label mb-1">
                            <p class="required" style="font-weight: 400;"> * {{ $t('Campo obrigatório') }}</p>
                        </label>

                        <div v-if="edit == false">

                        
                            <div  class="modal-footer d-block justify-content-center">
                                <div class="align-items-center text-center">
                                    <i class="material-icons align-self-center comment"
                                        style="max-width: 24px; color: #344767">insert_comment_outlined</i>
                                    <h5>{{ $t("Comentários") }}</h5>
                                </div>
                            </div>
                            <div class="justify-content-left">
                                <div v-for="(c, index) in comments" :key="index" class="mb-3">

                                    <h6 v-if="currentEditIndex !== index">
                                        <div class="form-group form-row">
                                                <label for="description" class="form-label">{{ $t('Nome') }} </label>
                                                <label class="detail form-label cuter">{{ c.user_email }}</label>
                                            </div>
                                            <div class="form-group form-row">
                                                <label for="description" class="form-label">{{ $t('Data') }} </label>
                                                <label class="detail form-label cuter">{{ c.timestamp }}</label>
                                            </div>
                                            <div class="form-group form-row ">
                                                <label for="description" class="form-label cuter">{{ $t('Comentáio') }}
                                                </label>
                                                <label class="detail form-label cuter">{{ c.descricao }}</label>
                                            </div>
                                    </h6>


                                    <input v-else class="input-group input-group-outline custom"
                                        style="border-radius: 6px;border: 2px solid #344767;"
                                        v-model="editComment" />
                                    <div class="buttons" v-if="currentEditIndex === index">
                                        <button @click="saveComment(index)" style="background-color: white;">
                                            <i class="material-icons"
                                                style="color: #344767; font-size: 18px;">save</i>
                                        </button>
                                        <button @click="cancelEdit()" style="background-color: white;">
                                            <i class="material-icons"
                                                style="color: #344767; font-size: 18px;">cancel</i>
                                        </button>
                                    </div>
                                    <div class="buttons" v-else>
                                        <button v-if="user.email === c.user_email"
                                            @click="editCommentMode(index, c.descricao)"
                                            style="background-color: white;">
                                            <i class="material-icons"
                                                style="color: #344767; font-size: 18px;">edit</i>
                                        </button>
                                        <button v-if="user.email === c.user_email || user.id === transaction.users[0].id"
                                            @click="deleteComment(index)" style="background-color: white;">
                                            <i class="material-icons"
                                                style="color: #344767; font-size: 18px;">delete</i>
                                        </button>
                                        <hr class="horizontal dark" />
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-9">
                                        <material-input class="material-input w-100" id="comment" type="text"
                                            :value="Comment" :label="$t('Escreva o seu comentário')"
                                            @update:value="Comment = $event" />
                                    </div>
                                    <div class="col-3 d-flex align-items-center justify-content-end">
                                        <material-button variant="gradient" color="info"
                                            class="btn btn-md bg-gradient-info d-flex justify-content-center align-items-center "
                                            @click="addComment(transaction.id)">
                                            {{ $t("Comentar") }}
                                        </material-button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="tab-details tab-content" id="tab-1">
                        <div v-if="transaction !== null && transaction.users !== null">
                            <div v-if="transaction.repeticao === 0  && user.id === transaction.users[0].id" class="mt-2 d-flex">
                                <label for="sharedExpense" class="mb-0 form-label">{{ $t('Partilhar Despesa') }}</label>
                                <div class="form-check form-switch ps-0 ms-auto my-auto">
                                    <input class="form-check-input mt-1 ms-auto" type="checkbox"
                                        v-model="ShareExpense" />
                                </div>
                            </div>
                            <hr class="horizontal dark my-sm-4" />
                            <div v-if="ShareExpense && transaction.repeticao === 0  && user.id === transaction.users[0].id">
                                <!-- Shared expense -->
                                <div class="form-group">
                                    <label for="sharedExpense" class="form-label mb-3">{{ $t('Partilhar despesa com:')
                                        }}
                                        <p class="required"> *</p>
                                    </label>
                                    <div class="form-input mb-3">
                                        <div v-if="emailError === true && emailErrorStore === null" class="mb-3">
                                            <material-input class="material-input mb-3" id="email" type="email"
                                                :label="$t('Indique o email do utilizador')" name="email"
                                                :value="newUserEmail" @update:value="newUserEmail = $event" error />
                                        </div>

                                        <div v-if="emailError === true && emailErrorStore !== null" class="mb-3">
                                            <material-input class="material-input mb-3" id="email" type="email"
                                                :label=emailErrorStore name="email" :value="newUserEmail"
                                                @update:value="newUserEmail = $event" error />
                                        </div>

                                        <div v-if="emailError === false" class="mb-3">
                                            <material-input class="material-input mb-3" id="email" type="email" name="email"
                                                :value="newUserEmail" @update:value="newUserEmail = $event" success />
                                        </div>

                                        <div v-if="emailError === null" class="mb-3">
                                            <material-input class="material-input mb-3" id="email" type="email" name="email"
                                                :value="newUserEmail" @update:value="newUserEmail = $event"
                                                :label="$t('Indique o email do utilizador')" />
                                        </div>

                                        <p class="btn btn-default bg-gradient-info mb-1" @click="addUser">{{ $t('Adicionar Utilizador') }}</p>
                                    </div>
                                </div>
                                <hr class="horizontal dark my-sm-4" />
                                <MaterialAlert v-if="showAlertUsers" color="danger" @click="hideAlertUsers"
                                    class="font-weight-light" dismissible>
                                    <span class="text-sm">{{ $t('Adicione utilizadores para partilhar a despesa') }}</span>
                                </MaterialAlert>
                                <div class="align-items-center text-center">
                                    <label class="form-label align-self-center mb-3"
                                        style="font-size: large; font-weight: 600;">{{ Value + $t('€ a dividir com:')}}</label>
                                </div>
                                <div class="form-row">
                                    <span style="font-size: 16px; padding-left: 35px;">{{ transaction.users[0].email }} </span>
                                    <span style="font-size: 16px; padding-right: 10px;">{{ calculateShare() }}</span>
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
                            <div v-else >
                                <MaterialAlert v-if="showAlertUsers" color="danger" @click="hideAlertUsers"
                                    class="font-weight-light" dismissible>
                                    <span class="text-sm">{{ $t('Adicione utilizadores para partilhar a despesa') }}</span>
                                </MaterialAlert>
                                <div class="align-items-center text-center">
                                    <label class="form-label align-self-center mb-3"
                                        style="font-size: large; font-weight: 600;">{{ transaction.value + $t('€ a dividir com:')}}</label>
                                </div>
                                <div class="form-row" v-for="(user, index) in transaction.users" :key="index">
                                    <span style="font-size: 16px; padding-left: 35px;">{{ user.email }} </span>
                                    <span style="font-size: 16px; padding-right: 10px;">{{ transaction.shareValue }} EUR</span>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>


                <div class="modal-footer mt-0 d-flex justify-content-between"> 
                    <p id="cancelButton2" class="btn btn-default bg-gradient-primary mb-1" data-bs-dismiss="modal"
                        @click="cancel;edit = false;">{{
                            $t('Cancelar') }}
                    </p>
                    <p v-if="edit" class="btn btn-default bg-gradient-info mb-1" @click="saveTransaction();">{{
                        $t('Guardar') }}</p>
                    <p v-if="edit == false && transaction && (!transaction.repeticao || transaction.repeticao==0) && user.id === transaction.users[0].id"
                        class="btn btn-default bg-gradient-warning mb-1" @click="editTransaction(); edit = true;">{{
                        $t('Editar') }}</p>
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
import { useTransactionStore } from "@/store/transactionStore";
import { ref, reactive, toRefs, onMounted, watch } from 'vue';
import { categoriesStore } from "@/store/categoriesStore";
import { userStore } from "@/store/userStore";
import { useRouter } from 'vue-router';

export default {
    name: "PopUpMovementDetails",
    props: {
        transaction: {
            type: Object,
            required: true
        }
    },
    components: {
        MaterialAlert,
        MaterialInput,
        MaterialCheckbox
    },
    setup(props) {
        const { transaction } = toRefs(props);

        const { t } = useI18n();
        const Name = ref('');
        const nameError = ref(null);
        const Description = ref('');
        const Value = ref(null);
        const valueError = ref(null);
        const valueNegative = ref(null);
        const DateM = ref('');
        const DateError = ref(null);
        const Place = ref('');
        const Category = ref('');
        const CategoryError = ref(null);
        const Type = ref('');
        const TypeError = ref(null);
        const Repetition = ref('');
        const repetitionError = ref(null);
        const Recorrence = ref(null);
        const recorrenceError = ref(null);
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
        const currentEditIndex = ref(null);
        const editComment = ref(null);
        const comments = ref(null);
        const Comment = ref('');

        const addUsers = reactive([]);
        const removeUsers = reactive([]);

        const edit = ref(false);

        const store = useTransactionStore();


        // dinamica
        const categories = categoriesStore();
        const user = userStore();
        const router = useRouter();

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

        function padNumber(num) {
            return num.toString().padStart(2, '0');
        }

        function convertToTimestamp(dateString) {
            const date = new Date(dateString);

            const formattedDateTime = `${date.getFullYear()}-${padNumber(date.getMonth() + 1)}-${padNumber(date.getDate())} ${padNumber(date.getHours())}:${padNumber(date.getMinutes())}:${padNumber(date.getSeconds())}`;

            return formattedDateTime
        }

        const addUser = async () => {

            validarEmail(newUserEmail);

            if (isValid == true) {


                //fazer post do email para ver se existe
                try {
                    const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/user/get/" + newUserEmail.value;
                    const token = localStorage.getItem('token');
                    const request = {
                        method: "GET",
                        headers: {
                            "Content-Type": "application/json",
                            'Authorization': "Bearer " + token,
                        },
                    };

                    const response = await fetch(url, request);

                    // Verifica se a resposta não é OK
                    if (!response.ok) {
                        const errorData = await response.json();
                        throw new Error(errorData.message);
                    }

                    if (user.email == newUserEmail.value) {
                        emailErrorStore.value = `${t('Não pode partilhar despesas consigo mesmo!')}`;
                        emailError.value = true;
                        newUserEmail.value = '';
                        return;
                    } else {
                        if (removeUsers.some(user => user === newUserEmail.value)){
                            sharedUsers.push(newUserEmail.value);
                            removeUsers.splice(removeUsers.indexOf(newUserEmail.value), 1);
                            newUserEmail.value = '';
                            emailError.value = null;
                            return;
                        }

                        if( !sharedUsers  || sharedUsers.length == 0){
                            sharedUsers.push(newUserEmail.value);
                            addUsers.push(newUserEmail.value);
                            newUserEmail.value = '';
                            emailError.value = null;
                            return;
                        }
                        let isEmailPresent = sharedUsers.some(user => user === newUserEmail.value);
                        if(!isEmailPresent ){
                            sharedUsers.push(newUserEmail.value);
                            addUsers.push(newUserEmail.value);
                        }
                        else{
                            emailErrorStore.value = `${t('Não pode partilhar novamente com este utilizador!')}`;
                            emailError.value = true;
                            newUserEmail.value = '';
                            return;
                        }
                
                        newUserEmail.value = '';
                        emailError.value = null;
                    }

                } catch (error) {
                    // Tratamento de erro específico para " ... "
                    if (error.message.includes('token')) {
                        alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                        localStorage.clear();
                        sessionStorage.clear();

                        router.push('/sign-in');
                    } else if (error.message === "Nenhum utilizador encontrado!") {
                        emailErrorStore.value = `${t('Email não registado na plataforma.')}`;
                        emailError.value = true;
                        alert("Não é possível partilhar despesas com utilizadores não registados!");
                        return;
                    } else {
                        console.error(error.message);
                    }
                }

            }
        }

        const checkInputs = function () {
            console.log("Checking inputs");
            console.log(transaction.value);
            console.log(transaction.value.users);

            if (!Name.value.trim())
                nameError.value = true;
            else
                nameError.value = false;

            if (!Value.value)
                valueError.value = true;
            else if (Value.value <= 0) {
                valueError.value = true;
                valueNegative.value = true;
                Value.value = '';
            }
            else {
                valueError.value = false;
                valueNegative.value = null;
            }

            if (!DateM.value)
                DateError.value = true;
            else
                DateError.value = false;

            if (!transaction.value.categoria)
                CategoryError.value = true;
            else
                CategoryError.value = false;

            if (!transaction.value.tipo)
                TypeError.value = true;
            else
                TypeError.value = false;
        }

        const goToShare = function () {
            checkInputs();
            if (TypeError.value == false && nameError.value == false && DateError.value == false
                && valueError.value == false && CategoryError.value == false) {
                return true;
            }
            else
                return false;
        }


        const clearSharedUsers = function () {
            if (sharedUsers.length > 0) {
                console.log('Before clearing:', sharedUsers.value);
                sharedUsers.splice(0, sharedUsers.length); 
                console.log('After clearing:', sharedUsers.value);
            } else {
                console.error("sharedUsers is undefined");
            }
        };

        const selectRecorrence = function (recorrence) {
            if (recorrence == 'Recorrente (Fixa)') {
                Recorrence.value = 'Recorrente (Fixa)';
                transaction.value.repeticao = 1;
            }
            else {
                Recorrence.value = 'Única (Não Fixa)';
                transaction.value.repeticao = 0;
                console.log(transaction.value);
            }

        }

        const selectRepetition = function (repetition) {
            transaction.value.repeticao = repetition;
            console.log(transaction.value.repeticao);
        }

        const selectCategory = function (category) {
            Category.value = category;
        }

        const cancel = function () {
            console.log(Description.value);

            nameError.value = null;
            Name.value = '';

            Description.value = '';

            valueError.value = null;
            Value.value = '';

            DateError.value = null;
            DateM.value = '';

            TypeError.value = null;
            Type.value = '';

            recorrenceError.value = null;
            Recorrence.value = '';

            repetitionError.value = null;
            Repetition.value = '';

            CategoryError.value = null;
            Category.value = '';

            Place.value = '';

            resetTab.value = true;

            newUserEmail.value = '';
        }

        const loadCategories = async () => {
            try {
                await categories.load();
            } catch (err) {
                if (err.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                } else {
                    console.error(err.message);
                }
            }
        };

        onMounted(() => {
            loadCategories();
        });
        watch(() => transaction.value, () => {
            Value.value = transaction.value.value;
        });

        const getComments = async () => {
            const url_comments =
                "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/comment/list/" + transaction.value.id;
            const token = localStorage.getItem("token");

            const request = {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + token,
                },
            };
            try{
                const response_comments = await fetch(url_comments, request);

                if(response_comments.status == 401) {
                    alert("Nao tem permissoes para comentar pois ja saiu desta transacao");
                }

                if (!response_comments.ok) {
                    const errorData = await response_comments.json();
                    throw new Error(errorData.message);
                }

                const data_comments = await response_comments.json();
                console.log(data_comments);
                console.log(transaction.value)
                comments.value = data_comments.comentarios;
            } catch (error) {
                if (error.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                } else {
                    console.error(error.message);
                }
            }
        }

        watch(() => transaction.value, () => {
            getComments();
        });

        function formatDate(timestamp) {
            const date = new Date(timestamp);
            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0'); // Mês começa em 0
            const day = String(date.getDate()).padStart(2, '0');
            return `${year}-${month}-${day}`;
        }

        const editTransaction = () => {
            sharedUsers.splice(0, sharedUsers.length);
            addUsers.splice(0, addUsers.length);
            removeUsers.splice(0, removeUsers.length);
            Name.value = transaction.value.name;
            Value.value = transaction.value.value;
            //formatar a data para o formato do input
            

            DateM.value = formatDate(transaction.value.date);
            Description.value = transaction.value.descricao;
            Place.value = transaction.value.local;

            if (transaction.value.tipo == 'despesa') {
                Category.value = categories.CategoriesExpense.find((c) => c.name === transaction.value.categoria);
            } else {
                Category.value = categories.CategoriesIncome.find((c) => c.name === transaction.value.categoria);
            }

            for(let i = 1; i < transaction.value.users.length; i++){
                sharedUsers.push(transaction.value.users[i].email);
            }
        }

        const saveTransaction = async () => {
            checkInputs();
            if (nameError.value == false && DateError.value == false
                && valueError.value == false && CategoryError.value == false) {
                
                timestamp.value = convertToTimestamp(DateM.value);
                
                const sendaddUsers = addUsers.map(email => ({ email }));
                const sendDeleteUsers = removeUsers.map(email => ({ email }));
                let url;
                if(transaction.value.repeticao == 0){
                    transaction.value.repeticao = null;
                    url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/unica/" + transaction.value.tipo + "/set";
                } else {
                    url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/fixa/" + transaction.value.tipo + "/set";
                }

                console.log(url);
                const token = localStorage.getItem("token");

                // Objeto para armazenar os campos que serão enviados no corpo da requisição
                const requestBody = {};
                let diff = false;

                // Adicionar campos ao requestBody conforme as condições
                if (Name.value !== transaction.value.name) {
                    diff = true;
                    requestBody.name = Name.value;
                }
                if (Value.value !== transaction.value.value) {
                    diff = true;
                    requestBody.value = Value.value;
                }
                if (Description.value !== transaction.value.descricao) {
                    diff = true;
                    requestBody.date = timestamp.value;
                }
                if (DateM.value !== formatDate(transaction.value.date)) {
                    diff = true;
                    requestBody.descricao = Description.value;
                }
                let IdCategoriaC;
                if (transaction.value.tipo == 'despesa') {
                    IdCategoriaC = categories.CategoriesExpense.find((c) => c.name === transaction.value.categoria).id;
                } else {
                    IdCategoriaC = categories.CategoriesIncome.find((c) => c.name === transaction.value.categoria).id;
                }
                if (Category.value.id !== IdCategoriaC) {
                    diff = true;
                    requestBody.IdCategoria = Category.value.id;
                }
                if (Place.value !== transaction.value.local) {
                    diff = true;
                    requestBody.local = Place.value;
                }
                if (sendaddUsers && sendaddUsers.length > 0) {
                    diff = true;
                    requestBody.sendaddUsers = sendaddUsers;
                }
                if (sendDeleteUsers && sendDeleteUsers.length > 0) {
                    diff = true;
                    requestBody.sendDeleteUsers = sendDeleteUsers;
                }

                // Convertendo o requestBody para JSON e atribuindo ao corpo da requisição
                if(diff){
                    requestBody.IdTransacao = transaction.value.id;
                } else {
                    edit.value = false;
                    const cancelButton = document.getElementById('cancelButton2');
                    cancelButton.click();
                    //window.location.reload();
                    store.load(30);
                    cancel();
                    return;
                }

                const body = JSON.stringify(requestBody);

                const request = {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: "Bearer " + token,
                    },
                    body: body,
                };

                console.log("Request ->" + request.body);

                try {
                    const response = await fetch(url, request);

                    if (!response.ok) {
                        const errorData = await response.json();
                        throw new Error(errorData.message);
                    }
                    edit.value = false;

                    //sucesso
                    const event = new CustomEvent('show-snackbar', { detail: 'successAddMove' });
                    document.dispatchEvent(event);
                    console.log('PopUp emitiu evento');
                    
                    user.getUser(); //atualiza o saldo da homepage
                    const cancelButton = document.getElementById('cancelButton2');
                    cancelButton.click();
                    window.location.reload(); //TODO: DAR APENAS RELOAD DA DOS DADOS



                } catch (error) {
                    const cancelButton = document.getElementById('cancelButton2');
                    cancelButton.click();
                    
                    store.stateReload = !store.stateReload;
                    if (error.message.includes('token')) {
                        alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                        localStorage.clear();
                        sessionStorage.clear();

                        router.push('/sign-in');
                    } else {
                        console.error(error.message);
                        const event = new CustomEvent('show-snackbar', { detail: 'error' });
                        document.dispatchEvent(event);
                        console.log('PopUp emitiu evento');
                    }
                    window.location.reload(); //TODO: DAR APENAS RELOAD DA DOS DADOS
                }

            }
        }

        const editCommentMode = (index, descricao) => {
            currentEditIndex.value = index;
            editComment.value = descricao;
        };

        const saveComment = async (index) => {
            if (editComment.value.trim() === '') {
                alert('O comentário não pode estar vazio.');
                editComment.value = comments.value[index].descricao;
                return;
            }

            const com = comments.value[index];

            const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/comment/edit";
            const token = localStorage.getItem("token");

            const request = {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + token,
                },
                body: JSON.stringify({
                    IdComentario: com.id,
                    descricao: editComment.value,
                }),
            };

            try {
                const response = await fetch(url, request);

                if (!response.ok) {
                    const errorData = await response.json();
                    throw new Error(errorData.message);
                }

                const data = await response.json();
                console.log(data);

                comments.value[index].descricao = editComment.value;
                currentEditIndex.value = null;
                editComment.value = '';

            } catch (error) {
                if (error.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                } else {
                    console.error(error.message);
                }
            }
        };

        const cancelEdit = () => {
            currentEditIndex.value = null;
            editComment.value = '';
        };

        const deleteComment = async (index) => {
            const com = comments.value[index];

            const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/comment/remove/" + com.id;
            const token = localStorage.getItem("token");

            const request = {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + token,
                },
            };

            try {
                const response = await fetch(url, request);

                if (!response.ok) {
                    const errorData = await response.json();
                    throw new Error(errorData.message);
                }

                const data = await response.json();
                console.log(data);

                comments.value.splice(index, 1);

            } catch (error) {
                if (error.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                } else {
                    console.error(error.message);
                }
            }
        };
        const addComment = async (id) => {
            if (Comment.value.trim() === '') {
                alert('O comentário não pode estar vazio.');
                Comment.value = '';
                return;
            }
            const url =
                "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/comment/add";
            const token = localStorage.getItem("token");

            const request = {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + token,
                },
                body: JSON.stringify({
                    idTransacao: id,
                    comentario: Comment.value,
                }),
            };

            try {
                const response = await fetch(url, request);

                if (!response.ok) {
                    const errorData = await response.json();
                    throw new Error(errorData.message);
                }

                const data = await response.json();
                console.log(data);

                Comment.value = "";

                getComments();
            } catch (error) {
                if (error.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                } else {
                    console.error(error.message);
                }
            }
        };

        return {
            t,
            editTransaction,
            saveTransaction,
            cancel,
            checkInputs,
            goToShare,
            addUser,
            clearSharedUsers,
            selectRecorrence,
            selectRepetition,
            // selectType,
            selectCategory,
            Name,
            nameError,
            Description,
            Comment,
            Value,
            valueError,
            DateM,
            DateError,
            Place,
            Category,
            CategoryError,
            Type,
            TypeError,
            Recorrence,
            recorrenceError,
            Repetition,
            repetitionError,
            ShareExpense,
            newUserEmail,
            sharedUsers,
            shareValue,
            emailError,
            emailErrorStore,
            showAlertUsers,
            resetTab,
            categories,
            valueNegative,
            edit,
            addUsers,
            removeUsers,
            user,
            editCommentMode,
            saveComment,
            comments,
            currentEditIndex,
            editComment,
            cancelEdit,
            deleteComment,
            addComment
        };
    },
    data() {
        return {
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
            isRecorrenceFocused: false,
            isRepetitionFocused: false,
            isDateFocused: false,
            SetBorder: true,
            showAlert: false,
            showShareTab: false,
            Tab: 0
        };
    },
    mounted() {
        document.addEventListener('click', this.handleClickOutsideCategory);
    },
    computed: {
        displayCategories() {
            if (this.transaction.tipo == 'despesa')
                return this.categories.CategoriesExpense;
            else
                return this.categories.CategoriesIncome;
        },
    },
    watch: {
        resetTab() {
            if (this.Tab !== 0)
                this.showTab(0);
            this.resetTab = false;
        },
        ShareExpense(newVal){
            if ( newVal && this.edit == false) {
                this.showTab(1);
            }
        }
    },
    methods: {
        hideAlertUsers() {
            this.showAlertUsers = false;
        },
        calculateShare() {
            if(this.sharedUsers.length == 0 && this.transaction.users.length == 1)
                return `${this.Value} €`;
            this.shareValue = this.Value / (this.sharedUsers.length + 1);
            return `${this.shareValue.toFixed(2)} €`; // Formata o valor com duas casas decimais
        },
        removeUser(index) {
            let isEmailPresent = this.transaction.users.some(user => user.email === this.sharedUsers[index]);
            if(isEmailPresent){
                this.removeUsers.push(this.sharedUsers[index]);
            }
            if(this.addUsers.some(user => user === this.sharedUsers[index])){
                this.addUsers.splice(this.sharedUsers[index], 1);
            }
            this.sharedUsers.splice(index, 1);
        },
        showTab(index) {
            this.Tab = index;
            const tabs = document.querySelectorAll('.tab-details');
            const buttons = document.querySelectorAll('.tab-button-details');
            if (index == 1) {
                if (this.transaction.tipo == 'despesa' && this.edit == false) {
                    tabs.forEach((tab, i) => {
                        tab.classList.toggle('active', i === index);
                        buttons[i].classList.toggle('active', i === index);
                    });
                    this.showShareTab = !this.showShareTab;
                    console.log("showShare index 1:" + this.showShareTab);
                } else if (this.transaction.tipo == 'despesa' && this.edit == true) {
                    this.checkInputs();
                    if (this.goToShare() == true) {
                        tabs.forEach((tab, i) => {
                            tab.classList.toggle('active', i === index);
                            buttons[i].classList.toggle('active', i === index);
                        });
                        this.showShareTab = !this.showShareTab;
                        console.log("showShare index 1:" + this.showShareTab);
                    }
                }

                if(this.ShareExpense == true && this.edit == false){
                    this.edit = true;
                    this.editTransaction();
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
        handleTypeFocus() {
            this.isTypeFocused = true;
        },
        handleRecorrenceFocus() {
            this.isRecorrenceFocused = true;
        },
        handleRepetitionFocus() {
            this.isRepetitionFocused = true;
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
    }
};
</script>

<style scoped>
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

.required {
    display: inline;
    color: #e91e63;
    font-weight: 500;
    font-size: 12px;
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

.tab-details {
    display: none;
}

.tab-details.active {
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

.cuter {
    word-wrap: break-word;
    word-break: break-all;
    white-space: normal;
    overflow-wrap: break-word;
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

.scroll-container {
    max-height: 77vh;
    overflow-y: auto;
}

.scroll-container::-webkit-scrollbar {
    width: 8px;
}

.scroll-container::-webkit-scrollbar-track {
    border-radius: 10px;
}

.scroll-container::-webkit-scrollbar-thumb {
    background-color: #888;
    border-radius: 10px;
}
</style>
