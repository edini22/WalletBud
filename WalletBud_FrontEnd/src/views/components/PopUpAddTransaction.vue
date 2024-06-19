<template>
    <div class="modal fade" id="transactionModal" tabindex="-1" aria-labelledby="transactionModalLabel"
        aria-hidden="true" data-bs-backdrop='static' data-bs-keyboard='false'>
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">{{ $t('Adicionar Movimento') }}</h5>
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

                        <!-- Name -->
                        <div class="form-group form-row">
                            <label for="description" class="form-label">{{ $t('Nome') }}
                                <p class="required"> *</p>
                            </label>
                            <div v-if="nameError === true" class="form-input mb-1">
                                <material-input class="material-input" id="description" type="text"
                                    :label="$t('Indique o nome')" name="description" @update:value="Name = $event"
                                    :value="Name" error />
                            </div>
                            <div v-if="nameError === false" class="form-input mb-1">
                                <material-input class="material-input" id="description" type="text" name="description"
                                    :value="Name" @update:value="Name = $event" success />
                            </div>
                            <div v-if="nameError === null" class="form-input mb-1">
                                <material-input class="material-input" id="description" type="text" :value="Name"
                                    :label="$t('Indique o nome')" name="description" @update:value="Name = $event" />
                            </div>
                        </div>

                        <!-- Descrição -->
                        <div class="form-group form-row">
                            <label for="place" class="form-label">{{ $t('Descrição') }}</label>
                            <div class="form-input mb-1">
                                <material-input class="material-input" id="place" type="text"
                                    :label="$t('Indique a descrição')" name="place" :value="Description"
                                    @update:value="Description = $event" />
                            </div>
                        </div>

                        <!-- Montante -->
                        <div class="form-group form-row">
                            <label for="value" class="form-label">{{ $t('Montante') }}
                                <p class="required"> *</p>
                            </label>
                            <div v-if="valueError === true && valueNegative === null" class="form-input mb-1">
                                <material-input class="material-input" id="value" type="number" :value="Value"
                                    :label="$t('Indique um montante válido')" name="value"
                                    @update:value="Value = $event" error />
                            </div>
                            <div v-if="valueError === true && valueNegative === true" class="form-input mb-1">
                                <material-input class="material-input" id="value" type="number" :value="Value"
                                    :label="$t('Indique um valor maior que zero')" name="value" @update:value="Value = $event" error />
                            </div>
                            <div v-if="valueError === false" class="form-input mb-1">
                                <material-input class="material-input" id="value" type="number" name="value"
                                    :value="Value" @update:value="Value = $event" success />
                            </div>
                            <div v-if="valueError === null" class="form-input mb-1">
                                <material-input class="material-input" id="value" type="number" :value="Value"
                                    :label="$t('Indique um montante')" name="value" @update:value="Value = $event" />
                            </div>
                        </div>

                        <!-- Data -->
                        <div class="form-group form-row">
                            <label for="date" class="form-label">
                                {{ $t('Data do Movimento') }}
                                <p class="required"> *</p>
                            </label>
                            <div v-if="DateError === true" class="form-input mb-1 date-input-wrapper"
                                :class="{ 'dropdown-focused-error': isDateFocused, 'border': SetBorder }"
                                style="position: relative;">
                                <material-input class="material-input" id="date" type="date" name="date" :value="DateM"
                                    @update:value="DateM = $event" error @focus="handleDateFocus"
                                    @blur="handleDateBlur" />
                            </div>
                            <div v-if="DateError === false" class="form-input mb-1">
                                <material-input class="material-input" :class="{ 'is-focused': isFocused }" id="date"
                                    type="date" name="date" :value="DateM" @update:value="DateM = $event" success
                                    @focus="handleFocus" @blur="handleBlur" :label="$t('Indique a data')" />
                            </div>
                            <div v-if="DateError === null" class="form-input mb-1  date-input-wrapper">
                                <material-input class="material-input" :class="{ 'is-focused': isFocused }" id="date"
                                    type="date" name="date" @update:value="DateM = $event" :value="DateM"
                                    :label="$t('Indique a data')" @focus="handleFocus" @blur="handleBlur" />
                                <i class="material-icons date-icon">date_range</i>
                            </div>
                        </div>

                        <!-- Tipo -->
                        <div class="dropdown" ref="typeDropdown">
                            <div class="form-group form-row">
                                <label for="Type" class="form-label">{{ $t('Tipo de movimento') }}
                                    <p class="required"> *</p>
                                </label>

                                <div v-if="TypeError === null || TypeError === false"
                                    class="input-group input-group-outline form-input mb-1"
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
                                    class="input-group input-group-outline form-input mb-1 is-invalid"
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

                        <!-- Recorrência -->
                        <div class="dropdown" ref="recorrenceDropdown">
                            <div class="form-group form-row">
                                <label for="recorrence" class="form-label">{{ $t('Recorrência') }}
                                    <p class="required"> *</p>
                                </label>

                                <div v-if="recorrenceError === null || recorrenceError === false"
                                    class="input-group input-group-outline form-input mb-1"
                                    style="border-radius: 0.375rem;">
                                    <button class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-null': isRecorrenceFocused }" id="dropdownTable"
                                        data-bs-toggle="dropdown" style="text-align:left; color: #7b809a"
                                        @focus="handleRecorrenceFocus">
                                        {{ Recorrence || $t('Selecione a recorrência do movimento') }}
                                    </button>
                                    <i class="material-icons arrow-icon">keyboard_arrow_down</i>
                                    <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5" aria-labelledby="dropdownTable">
                                        <li>
                                            <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRecorrence('Única (Não Fixa)')">
                                                {{ $t('Única (Não Fixa)') }}
                                            </a>
                                        </li>
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRecorrence('Recorrente (Fixa)')">
                                                {{ $t('Recorrente (Fixa)') }}
                                            </a>
                                        </li>
                                    </ul>
                                </div>

                                <div v-if="recorrenceError === true"
                                    class="input-group input-group-outline form-input mb-1 is-invalid"
                                    style="border-radius: 0.375rem;">
                                    <button class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-error': isRecorrenceFocused }" id="dropdownTable"
                                        data-bs-toggle="dropdown" style="text-align:left; color: #7b809a"
                                        @focus="handleRecorrenceFocus">
                                        {{ Recorrence || $t('Selecione a recorrência do movimento') }}
                                    </button>
                                    <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5" aria-labelledby="dropdownTable">
                                        <li>
                                            <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRecorrence('Única (Não Fixa)')">
                                                {{ $t('Única (Não Fixa)') }}
                                            </a>
                                        </li>
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRecorrence('Recorrente (Fixa)')">
                                                {{ $t('Recorrente (Fixa)') }}
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <!-- Repetição -->
                        <div v-if="Recorrence == 'Recorrente (Fixa)'" class="dropdown" ref="repeatDropdown">
                            <div class="form-group form-row">
                                <label for="Repetition" class="form-label">{{ $t('Repetição') }}
                                    <p class="required"> *</p>
                                </label>
                                <div v-if="repetitionError === null || repetitionError === false"
                                    class="input-group input-group-outline form-input mb-1"
                                    style="border-radius: 0.375rem;">
                                    <button class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-null': isRepetitionFocused }" id="dropdownTable"
                                        data-bs-toggle="dropdown" style="text-align:left; color: #7b809a"
                                        @focus="handleRepetitionFocus">
                                        {{ Repetition || $t('Selecione a repetição') }}
                                    </button>
                                    <i class="material-icons arrow-icon">repeat</i>
                                    <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5" aria-labelledby="dropdownTable">
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition('Todos os dias')">
                                                {{ $t('Todos os dias') }}
                                            </a>
                                        </li>
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition('Todas as semanas')">
                                                {{ $t('Todas as semanas') }}
                                            </a>
                                        </li>
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition('Todos os meses')">
                                                {{ $t('Todos os meses') }}
                                            </a>
                                        </li>
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition('Todos os anos')">
                                                {{ $t('Todos os anos') }}
                                            </a>
                                        </li>
                                    </ul>
                                </div>

                                <div v-if="repetitionError === true"
                                    class="input-group input-group-outline form-input mb-1 is-invalid"
                                    style="border-radius: 0.375rem;">
                                    <button class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-error': isRepetitionFocused }" id="dropdownTable"
                                        data-bs-toggle="dropdown" style="text-align:left; color: #7b809a"
                                        @focus="handleRepetitionFocus">
                                        {{ Repetition || $t('Selecione a repetição') }}
                                    </button>

                                    <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5" aria-labelledby="dropdownTable">
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition('Todos os dias')">
                                                {{ $t('Todos os dias') }}
                                            </a>
                                        </li>
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition('Todas as semanas')">
                                                {{ $t('Todas as semanas') }}
                                            </a>
                                        </li>
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition('Todos os meses')">
                                                {{ $t('Todos os meses') }}
                                            </a>
                                        </li>
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition('Todos os anos')">
                                                {{ $t('Todos os anos') }}
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <!-- Categoria -->
                        <div class="dropdown" ref="categoryDropdown">
                            <div class="form-group form-row">
                                <label for="category" class="form-label">{{ $t('Categoria') }}
                                    <p class="required"> *</p>
                                </label>
                                <div v-if="CategoryError === null || CategoryError === false"
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

                                <div v-if="CategoryError === true"
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
                        <div class="form-group form-row">
                            <label for="place" class="form-label">{{ $t('Local') }}</label>
                            <div class="form-input mb-1">
                                <material-input class="material-input" id="place" type="text"
                                    :label="$t('Indique o local')" name="place" :value="Place"
                                    @update:value="Place = $event" />
                            </div>
                        </div>

                        <label class="form-label mb-1">
                            <p class="required" style="font-weight: 400;"> * {{ $t('Campo obrigatório') }}</p>
                        </label>

                        <div class="modal-footer d-block justify-content-center">
                            <div class="align-items-center text-center">
                                <i class="material-icons align-self-center comment"
                                    style="max-width: 24px; color: #344767">insert_comment_outlined</i>
                                <h5>{{ $t('Comentário') }}</h5>

                                <div class="form-input">
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
                            <div v-if="sharedUsers.length > 0">
                                <div class="align-items-center text-center">
                                    <label class="form-label align-self-center mb-3"
                                        style="font-size: large; font-weight: 600;">{{ Value + $t('€ a dividir com:')}}</label>
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
                    <p id="cancelButton" class="btn btn-default bg-gradient-primary mb-1" data-bs-dismiss="modal"
                        @click="cancel">{{
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

import { categoriesStore } from "@/store/categoriesStore";
import { userStore } from "@/store/userStore";
import { useRouter } from 'vue-router';

export default {
    name: "add-transactions",
    components: {
        MaterialAlert,
        MaterialInput,
        MaterialCheckbox
    },
    setup() {
        const { t } = useI18n();
        const Name = ref('');
        const nameError = ref(null);
        const Description = ref('');
        const Value = ref('');
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
        const SendRepetition = ref(0);
        const repetitionError = ref(null);
        const Recorrence = ref('');
        const recorrenceError = ref(null);
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
                        alert(newUserEmail.value);
                        sharedUsers.push(newUserEmail.value);
                        alert("Utilizador adicionado com sucesso!");
                        for (let i = 0; i < sharedUsers.length; i++) {
                            alert(sharedUsers[i]);
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
                        alert("Erro ao registar: " + error.message);
                    }
                }

            }
        }

        const checkInputs = function () {
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
            else{
                valueError.value = false;
                valueNegative.value = null;
            }
                

            if (!DateM.value)
                DateError.value = true;
            else
                DateError.value = false;

            if (!Category.value)
                CategoryError.value = true;
            else
                CategoryError.value = false;

            if (!Type.value)
                TypeError.value = true;
            else
                TypeError.value = false;

            if (!Recorrence.value)
                recorrenceError.value = true;
            else
                recorrenceError.value = false;

            if (Recorrence.value == 'Recorrente (Fixa)') {
                if (!Repetition.value)
                    repetitionError.value = true;
                else
                    repetitionError.value = false;
            }
        }

        const goToShare = function () {
            checkInputs();
            if (TypeError.value == false && nameError.value == false && DateError.value == false
                && valueError.value == false && CategoryError.value == false && recorrenceError.value == false) {
                if (Recorrence.value == 'Recorrente (Fixa)') {
                    if (repetitionError.value == false)
                        return true;
                    else
                        return false
                } else {
                    return true;
                }
            } else
                return false;
        }


        const clearSharedUsers = function () {
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

            if (ShareExpense.value == true) {
                ShareExpense.value = false;
                console.log(sharedUsers.value);
                clearSharedUsers();
                console.log("after cleared:" + sharedUsers.value);
                newUserEmail.value = '';
            }
        }

        function transformRepetition(repetition) {
            if (repetition == 'Todos os dias') {
                return 1;
            } else if (repetition == 'Todas as semanas') {
                return 2;
            } else if (repetition == 'Todos os meses') {
                return 3;
            } else if (repetition == 'Todos os anos') {
                return 4;
            }
        }

        const add = async function () {

            if (Type.value == 'Receita' || Type.value == '') {
                checkInputs();

                if (TypeError.value == false && nameError.value == false && DateError.value == false
                    && valueError.value == false && CategoryError.value == false && recorrenceError.value == false) {

                    if (Recorrence.value == 'Recorrente (Fixa)') {
                        if (repetitionError.value == false) {
                            //faz post de receita fixa
                            timestamp.value = convertToTimestamp(DateM.value);
                            SendRepetition.value = transformRepetition(Repetition.value.trim());

                            console.log("data: " + DateM.value);
                            console.log("timestamp: " + timestamp.value);
                            console.log(Repetition.value + ": " + SendRepetition.value);
                            //POST da Receita Não Fixa
                            const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/fixa/receita/add";

                            const token = localStorage.getItem("token");

                            const request = {
                                method: "POST",
                                headers: {
                                    "Content-Type": "application/json",
                                    Authorization: "Bearer " + token,
                                },
                                body: JSON.stringify({
                                    name: Name.value,
                                    value: Value.value,
                                    descricao: Description.value,
                                    IdCategoria: Category.value.id,
                                    local: Place.value,
                                    date: timestamp.value,
                                    repeticao: SendRepetition.value,
                                    users: [],
                                    comentario: Comment.value
                                }),

                            };

                            try {
                                const response = await fetch(url, request);

                                if (!response.ok) {
                                    const errorData = await response.json();
                                    throw new Error(errorData.message);
                                }
                                console.log("post receita fixa");

                                const cancelButton = document.getElementById('cancelButton');
                                cancelButton.click();

                                //sucesso
                                const event = new CustomEvent('show-snackbar', { detail: 'success' });
                                document.dispatchEvent(event);
                                console.log('PopUp emitiu evento');
                                user.getUser(); //atualiza o saldo da homepage

                            } catch (error) {
                                if (error.message.includes('token')) {
                                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                                    localStorage.clear();
                                    sessionStorage.clear();

                                    router.push('/sign-in');
                                } else {
                                    alert("Erro ao adicionar Recorrente (Fixa):", error.message);
                                    const event = new CustomEvent('show-snackbar', { detail: 'error' });
                                    document.dispatchEvent(event);
                                    console.log('PopUp emitiu evento');
                                }
                            }

                        }
                    } else {
                        //post de receita não fixa
                        timestamp.value = convertToTimestamp(DateM.value);
                        console.log("data: " + DateM.value);
                        console.log("timestamp: " + timestamp.value);

                        console.log("repetição: " + SendRepetition.value);
                        //POST da Receita Não Fixa
                        const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/unica/receita/add";

                        const token = localStorage.getItem("token");

                        const request = {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json",
                                Authorization: "Bearer " + token,
                            },
                            body: JSON.stringify({
                                name: Name.value,
                                value: Value.value,
                                descricao: Description.value,
                                IdCategoria: Category.value.id,
                                local: Place.value,
                                date: timestamp.value,
                                repeticao: SendRepetition.value,
                                users: [],
                                comentario: Comment.value
                            }),

                        };

                        try {
                            const response = await fetch(url, request);

                            if (!response.ok) {
                                const errorData = await response.json();
                                throw new Error(errorData.message);
                            }
                            console.log("post receita não fixa");

                            const cancelButton = document.getElementById('cancelButton');
                            cancelButton.click();

                            //sucesso
                            const event = new CustomEvent('show-snackbar', { detail: 'successAddMove' });
                            document.dispatchEvent(event);
                            console.log('PopUp emitiu evento');
                            user.getUser(); //atualiza o saldo da homepage

                        } catch (error) {
                            if (error.message.includes('token')) {
                                alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                                localStorage.clear();
                                sessionStorage.clear();

                                router.push('/sign-in');
                            } else {
                                alert("Erro ao adicionar Receita nao fixa:", error.message);
                                const event = new CustomEvent('show-snackbar', { detail: 'error' });
                                document.dispatchEvent(event);
                                console.log('PopUp emitiu evento');
                            }
                        }
                    }
                }

            } else {
                if (showAlertUsers.value == true)
                    showAlertUsers.value = false;

                if (ShareExpense.value == true && sharedUsers.length === 0) {
                    console.log("sharedExpense:" + ShareExpense.value)
                    console.log("sharedUsers length:" + sharedUsers.length)
                    showAlertUsers.value = true;
                } else {

                    if (Recorrence.value == 'Recorrente (Fixa)') {
                        if (repetitionError.value == false) {

                            timestamp.value = convertToTimestamp(DateM.value);
                            SendRepetition.value = transformRepetition(Repetition.value.trim());

                            console.log("data: " + DateM.value);
                            console.log("timestamp: " + timestamp.value);
                            console.log(Repetition.value + ": " + SendRepetition.value);

                            console.log("post despesa fixa e partilhada");

                            const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/fixa/despesa/add";

                            const token = localStorage.getItem("token");

                            const usersArray = sharedUsers.map(email => ({ email }));

                            for (let i = 0; i < usersArray.length; i++) {
                                alert(usersArray[i]);
                            }

                            const request = {
                                method: "POST",
                                headers: {
                                    "Content-Type": "application/json",
                                    Authorization: "Bearer " + token,
                                },
                                body: JSON.stringify({
                                    name: Name.value,
                                    value: Value.value,
                                    descricao: Description.value,
                                    IdCategoria: Category.value.id,
                                    local: Place.value,
                                    date: timestamp.value,
                                    repeticao: SendRepetition.value,
                                    users: usersArray,
                                    comentario: Comment.value
                                }),
                            };

                            try {
                                const response = await fetch(url, request);

                                if (!response.ok) {
                                    const errorData = await response.json();
                                    throw new Error(errorData.message);
                                }

                                //POST da Despesa
                                const cancelButton = document.getElementById('cancelButton');
                                cancelButton.click();

                                //sucesso
                                const event = new CustomEvent('show-snackbar', { detail: 'success' });
                                document.dispatchEvent(event);
                                console.log('PopUp emitiu evento');
                                user.getUser(); //atualiza o saldo da homepage

                            } catch (error) {
                                if (error.message.includes('token')) {
                                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                                    localStorage.clear();
                                    sessionStorage.clear();

                                    router.push('/sign-in');
                                } else {
                                    const event = new CustomEvent('show-snackbar', { detail: 'error' });
                                    document.dispatchEvent(event);
                                    console.log('PopUp emitiu evento');
                                }
                            }
                        }
                    } else {

                        timestamp.value = convertToTimestamp(DateM.value);

                        console.log("data: " + DateM.value);
                        console.log("timestamp: " + timestamp.value);

                        const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/unica/despesa/add";

                        const token = localStorage.getItem("token");

                        const usersArray = sharedUsers.map(email => ({ email }));

                        const request = {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json",
                                Authorization: "Bearer " + token,
                            },
                            body: JSON.stringify({
                                name: Name.value,
                                value: Value.value,
                                descricao: Description.value,
                                IdCategoria: Category.value.id,
                                local: Place.value,
                                date: timestamp.value,
                                repeticao: SendRepetition.value,
                                users: usersArray,
                                comentario: Comment.value
                            }),
                        };

                        try {
                            const response = await fetch(url, request);

                            if (!response.ok) {
                                const errorData = await response.json();
                                throw new Error(errorData.message);
                            }
                            //POST da Despesa
                            console.log("post despesa não fixa e partilhada");

                            const cancelButton = document.getElementById('cancelButton');
                            cancelButton.click();

                            //sucesso
                            const event = new CustomEvent('show-snackbar', { detail: 'successAddMove' });
                            document.dispatchEvent(event);
                            console.log('PopUp emitiu evento');
                            user.getUser(); //atualiza o saldo da homepage


                        } catch (error) {
                            if (error.message.includes('token')) {
                                alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                                localStorage.clear();
                                sessionStorage.clear();

                                router.push('/sign-in');
                            } else {
                                const event = new CustomEvent('show-snackbar', { detail: 'error' });
                                document.dispatchEvent(event);
                                console.log('PopUp emitiu evento');
                            }
                        }
                    }
                }
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
            valueNegative
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
            isRecorrenceFocused: false,
            isRepetitionFocused: false,
            isDateFocused: false,
            SetBorder: true,
            showAlert: false,
            showShareTab: false,
            Tab: 0,
        };
    },
    mounted() {
        document.addEventListener('click', this.handleClickOutsideCategory);
        document.addEventListener('click', this.handleClickOutsideType);
        document.addEventListener('click', this.handleClickOutsideRecorrence);
    },
    computed: {
        displayCategories() {
            if (this.Type == 'Despesa')
                return this.categories.CategoriesExpense;
            else if (this.Type == 'Receita')
                return this.categories.CategoriesIncome;
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
            if (this.Tab !== 0)
                this.showTab(0);
            this.resetTab = false;
        }
    },
    methods: {
        hideAlertUsers() {
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
        selectRecorrence(recorrence) {
            this.Recorrence = recorrence;
        },
        selectRepetition(repetition) {
            this.Repetition = repetition;
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
        handleClickOutsideRecorrence(event) {
            const dropdown = this.$refs.recorrenceDropdown;
            if (dropdown && !dropdown.contains(event.target)) {
                if (this.Recorrence == '')
                    this.isRecorrenceFocused = false;
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
