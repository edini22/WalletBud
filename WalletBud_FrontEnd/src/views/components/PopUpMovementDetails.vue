<template>
    <div class="modal fade" id="detailsModal" tabindex="-1" aria-labelledby="transactionModalLabel" aria-hidden="true"
        data-bs-backdrop='static' data-bs-keyboard='false'>
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="transactionModalLabel">{{ $t('Detalhes do Movimento') }}</h5>
                </div>

                <div class="modal-body">
                    <div v-if="transaction !== null && transaction.tipo == 'Despesa'"
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
                            <p class="detail form-label" v-if="transaction !== null && edit == false">{{
                                transaction.name }}</p>
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
                                    :class="{'is-focused': transaction.name !== null && edit == true}"
                                    @update:value="Name = $event" />
                            </div>
                        </div>

                        <!-- Descrição -->
                        <div v-if="transaction !== null && transaction.description !== null"
                            class="form-group form-row">
                            <label for="place" class="form-label">{{ $t('Descrição') }}</label>
                            <p class="detail form-label" v-if="transaction !== null && edit == false">{{
                                transaction.description }}</p>
                            <div v-if="edit == true" class="form-input mb-1">
                                <material-input class="material-input" id="description" type="text"
                                    :class="{'is-focused': transaction.description !== null && edit == true}"
                                    name="description" :value="transaction.description" @update:value="Description = $event"
                                    :label="$t('Indique a descrição')" />
                            </div>
                        </div>
                        <div v-if="transaction !== null && transaction.description === null && edit"
                            class="form-group form-row">
                            <label v-if="edit" for="place" class="form-label">{{ $t('Descrição') }}</label>
                            <p class="detail form-label" v-if="transaction !== null && edit == false">{{
                                transaction.description }}</p>

                            <div class="form-input mb-1">
                                <material-input class="material-input" id="place" type="text"
                                    :label="$t('Indique a descrição')" name="place" :value="transaction.description" 
                                    @update:value="Description = $event"/>
                            </div>
                        </div>

                        <!-- Montante -->
                        <div v-if="transaction !== null" class="form-group form-row">
                            <label for="value" class="form-label">{{ $t('Montante') }}
                                <p v-if="edit" class="required"> *</p>
                            </label>
                            <p class="detail form-label" v-if="transaction !== null && edit == false">{{
                                transaction.valor}}</p>

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
                                    :value="transaction.valor" :label="$t('Indique um montante')" name="value"
                                    :class="{'is-focused': transaction.valor !== null && edit == true}"
                                    @update:value="Value = $event" />
                            </div>
                        </div>

                        <!-- Data -->
                        <div v-if="transaction !== null" class="form-group form-row">
                            <label for="date" class="form-label">
                                {{ $t('Data do Movimento') }}
                                <p v-if="edit" class="required"> *</p>
                            </label>
                            <p class="detail form-label" v-if="edit == false">{{
                                transaction.date }}</p>

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
                                    :class="{'is-focused': transaction.date !== null && edit == true}" id="date"
                                    type="date" name="date" :value="transaction.date" :label="$t('Indique a data')"
                                    @focus="handleFocus" @blur="handleBlur" @update:value="DateM = $event" />
                                <i class="material-icons date-icon">date_range</i>
                            </div>
                        </div>

                        <!-- Tipo -->
                        <div v-if="transaction !== null" class="dropdown" ref="typeDropdown">
                            <div class="form-group form-row">
                                <label for="Type" class="form-label">{{ $t('Tipo de movimento') }}
                                    <p v-if="edit" class="required"> *</p>
                                </label>
                                <p class="detail form-label" v-if="edit == false">{{
                                    transaction.tipo }}</p>
                                <div v-if="TypeError === null && edit == true || TypeError === false && edit == true"
                                    class="input-group input-group-outline form-input mb-1"
                                    style="border-radius: 0.375rem;">
                                    <button class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-null': transaction.tipo !== null && edit == true }"
                                        id="dropdownTable" data-bs-toggle="dropdown"
                                        style="text-align:left; color: #7b809a">
                                        {{ transaction.tipo || $t('Selecione o tipo') }}
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

                                <div v-if="TypeError === true && edit == true"
                                    class="input-group input-group-outline form-input mb-1 is-invalid"
                                    style="border-radius: 0.375rem;">
                                    <button class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-error': transaction.tipo !== null && edit == true }"
                                        id="dropdownTable" data-bs-toggle="dropdown"
                                        style="text-align:left; color: #7b809a" @focus="handleTypeFocus">
                                        {{ transaction.tipo || $t('Selecione o tipo') }}
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
                        <div v-if="transaction !== null && transaction.repeticao !== null" class="dropdown"
                            ref="recorrenceDropdown">
                            <div class="form-group form-row">
                                <label for="recorrence" class="form-label">{{ $t('Recorrência') }}
                                    <p v-if="edit" class="required"> *</p>
                                </label>
                                <p class="detail form-label"
                                    v-if="transaction !== null && edit == false && transaction.repeticao == 0">{{
                                    $t('Única (Não Fixa)') }}</p>
                                <p class="detail form-label"
                                    v-if="transaction !== null && edit == false && transaction.repeticao > 0">{{
                                    $t('Recorrente (Fixa)') }}</p>

                                <div v-if="recorrenceError === null && edit == true || recorrenceError === false && edit == true"
                                    class="input-group input-group-outline form-input mb-1"
                                    style="border-radius: 0.375rem;">
                                    <button v-if="transaction !== null && transaction.repeticao == 0"
                                        class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-null': transaction.repeticao !== null && edit == true }"
                                        id="dropdownTable" data-bs-toggle="dropdown"
                                        style="text-align:left; color: #7b809a">
                                        {{ Recorrence || $t('Única (Não Fixa)') }}
                                    </button>
                                    <button v-if="transaction !== null && transaction.repeticao > 0"
                                        class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-null': transaction.repeticao !== null && edit == true }"
                                        id="dropdownTable" data-bs-toggle="dropdown"
                                        style="text-align:left; color: #7b809a">
                                        {{ Recorrence || $t('Recorrente (Fixa)') }}
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
                            </div>
                        </div>

                        <!-- Repetição -->
                        <div v-if="transaction !== null && transaction.repeticao > 0 || transaction !== null && Recorrence === 'Recorrente (Fixa)'"
                            class="dropdown" ref="repeatDropdown">
                            <div class="form-group form-row">
                                <label for="Repetition" class="form-label">{{ $t('Repetição') }}
                                    <p v-if="edit" class="required"> *</p>
                                </label>
                                <p class="detail form-label" v-if="transaction !== null && edit == false">
                                    {{ transaction.repeticao == 1 ? 'Todos os dias' :
                                    transaction.repeticao == 2 ? 'Todas as semanas' :
                                    transaction.repeticao == 3 ? 'Todos os meses' :
                                    transaction.repeticao == 4 ? 'Todos os anos' :
                                    '' }}
                                </p>
                                <div v-if="repetitionError === null && edit == true || repetitionError === false && edit == true"
                                    class="input-group input-group-outline form-input mb-1"
                                    style="border-radius: 0.375rem;">
                                    <button class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-null': transaction.repeticao !== 0 && edit == true }"
                                        id="dropdownTable" data-bs-toggle="dropdown"
                                        style="text-align:left; color: #7b809a">
                                        {{ transaction.repeticao == 1 ? 'Todos os dias' :
                                        transaction.repeticao == 2 ? 'Todas as semanas' :
                                        transaction.repeticao == 3 ? 'Todos os meses' :
                                        transaction.repeticao == 4 ? 'Todos os anos' :
                                        ''
                                        }}
                                    </button>
                                    <i class="material-icons arrow-icon">repeat</i>
                                    <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5" aria-labelledby="dropdownTable">
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition(1)">
                                                {{ $t('Todos os dias') }}
                                            </a>
                                        </li>
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition(2)">
                                                {{ $t('Todas as semanas') }}
                                            </a>
                                        </li>
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition(3)">
                                                {{ $t('Todos os meses') }}
                                            </a>
                                        </li>
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition(4)">
                                                {{ $t('Todos os anos') }}
                                            </a>
                                        </li>
                                    </ul>
                                </div>

                                <div v-if="repetitionError === true && edit == true"
                                    class="input-group input-group-outline form-input mb-1 is-invalid"
                                    style="border-radius: 0.375rem;">
                                    <button class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-error': isRepetitionFocused }" id="dropdownTable"
                                        data-bs-toggle="dropdown" style="text-align:left; color: #7b809a"
                                        @focus="handleRepetitionFocus">
                                        {{ transaction.repeticao || $t('Selecione a repetição') }}
                                    </button>

                                    <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5" aria-labelledby="dropdownTable">
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition(1)">
                                                {{ $t('Todos os dias') }}
                                            </a>
                                        </li>
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition(2)">
                                                {{ $t('Todas as semanas') }}
                                            </a>
                                        </li>
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition(3)">
                                                {{ $t('Todos os meses') }}
                                            </a>
                                        </li>
                                        <li> <a class="dropdown-item border-radius-md" href="javascript:;"
                                                @click="selectRepetition(4)">
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
                                    <p v-if="edit" class="required"> *</p>
                                </label>
                                <p class="detail form-label" v-if="transaction !== null && edit == false">{{
                                    transaction.categoria }}</p>
                                <div v-if="CategoryError === null && edit == true || CategoryError === false && edit == true"
                                    class="input-group input-group-outline form-input mb-1"
                                    style="border-radius: 0.375rem;">
                                    <button v-if="displayCategories.length > 0"
                                        class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-null': isCategoryFocused }" id="dropdownTable"
                                        data-bs-toggle="dropdown" style="text-align:left; color: #7b809a"
                                        @focus="handleCategoryFocus">
                                        {{ transaction.categoria || $t('Selecione a categoria') }}
                                    </button>
                                    <!-- show this button if type has not been chosen -->
                                    <button v-else
                                        class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-null': isCategoryFocused }" id="dropdownTable"
                                        style="text-align:left; color: #7b809a" @click="alert">
                                        {{ transaction.categoria || $t('Selecione a categoria') }}
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
                                        {{ transaction.categoria || $t('Selecione a categoria') }}
                                    </button>
                                    <!-- show this button if type has not been chosen -->
                                    <button v-else
                                        class="cursor-pointer form-control form-control-default material-input"
                                        :class="{ 'dropdown-focused-error': isCategoryFocused }" id="dropdownTable"
                                        style="text-align:left; color: #7b809a" @click="alert">
                                        {{ transaction.categoria || $t('Selecione a categoria') }}
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
                            <p class="detail form-label" v-if="transaction !== null && edit == false">{{
                                transaction.local }}</p>
                            <div v-if="edit == true" class="form-input mb-1">
                                <material-input class="material-input" id="place" type="text"
                                    :label="$t('Indique o local')" name="place" :value="transaction.local"
                                    :class="{'is-focused': transaction.local !== null && edit == true}"
                                    @update:value="Place = $event" />
                            </div>
                        </div>
                        <div v-if="transaction !== null && transaction.local === null && edit"
                            class="form-group form-row">
                            <label v-if="edit" for="place" class="form-label">{{ $t('Local') }}</label>
                            <p class="detail form-label" v-if="transaction !== null && edit == false">{{
                                transaction.local }}</p>

                            <div class="form-input mb-1">
                                <material-input class="material-input" id="place" type="text"
                                    :label="$t('Indique a descrição')" name="place" :value="transaction.local" 
                                    @update:value="Place = $event"/>
                            </div>
                        </div>

                        <label v-if="edit == true" class="form-label mb-1">
                            <p class="required" style="font-weight: 400;"> * {{ $t('Campo obrigatório') }}</p>
                        </label>

                        <div v-if="transaction !== null && transaction.comentario !== null"
                            class="modal-footer d-block justify-content-center">
                            <div class="align-items-center text-center">
                                <i class="material-icons align-self-center comment"
                                    style="max-width: 24px; color: #344767">insert_comment_outlined</i>
                                <h5>{{ $t('Comentário') }}</h5>
                                <p class="detail form-label" v-if="transaction !== null && edit === false">{{
                                    transaction.comentario }}</p>
                                <div v-if="edit == true" class="form-input">
                                    <material-input class="material-input"
                                        :class="{'is-focused': transaction.comentario !== null && edit == true}"
                                        id="comment" type="text" :value="transaction.comentario"
                                        @update:value="Comment = $event"
                                        :label="$t('Escreva o seu comentário')" />
                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="tab-details tab-content" id="tab-1">

                        <div v-if="transaction && transaction.users && transaction.users.length > 0">
                            <!-- Shared expense -->
                            <div class="form-group">
                                <label for="sharedExpense" class="form-label mb-3">{{ $t('Despesa partilhada com:')
                                    }}</label>
                                
                                
                            <MaterialAlert v-if="showAlertUsers" color="danger" @click="hideAlertUsers"
                                class="font-weight-light" dismissible>
                                <span class="text-sm">{{ $t('Adicione utilizadores para partilhar a despesa') }}</span>
                            </MaterialAlert>
                            <div v-if="transaction.users.length > 0">
                                <div class="align-items-center text-center">
                                    <label v-if="transaction != null" class="form-label align-self-center mb-3"
                                        style="font-size: large; font-weight: 600;"
                                        >{{ transaction.valor + $t('€ a dividir com:')}}</label>
                                </div>
                                <div class="form-row" v-for="(user, index) in transaction.users" :key="index">
                                    <div class="form-row">
                                        <material-checkbox :id="'checkbox_' + index" class="font-weight-light" checked
                                            @click="removeUser(index)">
                                        </material-checkbox>
                                        <span style="font-size: 16px; padding-left: 10px;">{{ user.name }} </span>
                                    </div>
                                    <span style="font-size: 16px; padding-right: 10px;">{{ calculateShare() }}</span>
                                </div>
                            </div>
                            <hr class="horizontal dark my-sm-4" />

                            <label for="sharedExpense" class="form-label mb-3">{{ $t('Adicione mais utilizadores para partilha da despesa:')
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

                                    <p class="btn btn-default bg-gradient-info mb-1" 
                                    @click="addUser">{{ $t('Adicionar Utilizador') }}</p>
                                </div>
                            </div>
                            
                        </div>
                        
                        <!-- TRANSAÇÃO NÃO PARTILHADA -->
                        <div v-if="transaction !== null && transaction.users !== null && transaction.users.length == 0">
                            
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
                            <div v-if="transaction && transaction.users && transaction.users.length > 0">
                                <div class="align-items-center text-center">
                                    <label class="form-label align-self-center mb-3"
                                        style="font-size: large; font-weight: 600;">{{ Value + $t('€ a dividir com:')}}</label>
                                </div>
                                <div class="form-row" v-for="(user, index) in transaction.users" :key="index">
                                    <div class="form-row">
                                        <material-checkbox :id="'checkbox_' + index" class="font-weight-light" checked
                                            @click="removeUser(index)">
                                        </material-checkbox>
                                        <span style="font-size: 16px; padding-left: 10px;">{{ user.name }} </span>
                                    </div>
                                    <span style="font-size: 16px; padding-right: 10px;">{{ calculateShare() }}</span>
                                </div>
                            </div>
                        </div>
                        </div>

                        

                    </div>
                </div>


                <div class="modal-footer mt-0">
                    <p v-if="edit == false" class="btn btn-default bg-gradient-info mb-1"
                        @click="editTransaction(); edit = true;">{{ $t('Editar') }}</p>
                    <p v-if="edit" class="btn btn-default bg-gradient-info mb-1"
                        @click="saveEditedTransact();">{{ $t('Guardar') }}</p>
                    <p id="cancelButton" class="btn btn-default bg-gradient-primary mb-1" data-bs-dismiss="modal"
                        @click="cancel(); edit = false;">{{
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
import { ref, reactive, toRefs, onMounted } from 'vue';
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

        const edit = ref(false);
        

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
                sharedUsers.splice(0, sharedUsers.length); // Esvaziando o array reativamente
                console.log('After clearing:', sharedUsers.value);
            } else {
                console.error("sharedUsers is undefined");
            }
        };

        const selectRecorrence = function(recorrence) {
            if(recorrence == 'Recorrente (Fixa)'){
                Recorrence.value = 'Recorrente (Fixa)';
                transaction.value.repeticao = 1;
            }
            else{
                Recorrence.value = 'Única (Não Fixa)';
                transaction.value.repeticao = 0;
                console.log(transaction.value);
            }
                
        }

        const selectRepetition = function(repetition) {
            transaction.value.repeticao = repetition;
            console.log(transaction.value.repeticao);
        }

        const selectType = function(type) {
            transaction.value.tipo = type;
            transaction.value.categoria = null;
        }

        const selectCategory = function(category) {
            transaction.value.categoria = category.name;
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
                    alert(err.message);
                }
            }
        };

        onMounted(() => {
            loadCategories();
        });

        
        function editTransaction(){
            Name.value = transaction.value.name;
            Value.value = transaction.value.valor;
            DateM.value = transaction.value.date;
            Description.value = transaction.value.description;
            Place.value = transaction.value.local;
        }

        function saveEditedTransact(){
            checkInputs();
            if(nameError.value == false && DateError.value == false
            && valueError.value == false && CategoryError.value == false){

            
            transaction.value.name = Name.value;
            transaction.value.description = Description.value;
            transaction.value.valor = Value.value;
            timestamp.value = convertToTimestamp(DateM.value);
            transaction.value.date = timestamp.value;
            transaction.value.local = Place.value;

            console.log("Saved transaction: ", transaction.value);

            edit.value = false;

            }
        }

        return {
            t,
            editTransaction,
            saveEditedTransact,
            cancel,
            checkInputs,
            goToShare,
            addUser,
            clearSharedUsers,
            selectRecorrence,
            selectRepetition,
            selectType,
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
            edit
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
        document.addEventListener('click', this.handleClickOutsideType);
        document.addEventListener('click', this.handleClickOutsideRecorrence);
    },
    computed: {
        displayCategories() {
            if (this.transaction.tipo == 'Despesa')
                return this.categories.CategoriesExpense;
            else
                return this.categories.CategoriesIncome;
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
            if (this.transaction.users === 0) return ''; // Evita divisão por zero
            this.shareValue = this.transaction.valor / (this.transaction.users.length + 1);
            return `${this.shareValue.toFixed(2)} €`; // Formata o valor com duas casas decimais
        },
        removeUser(index) {
            this.sharedUsers.splice(index, 1);
        },
        showTab(index) {

            this.Tab = index;
            const tabs = document.querySelectorAll('.tab-details');
            const buttons = document.querySelectorAll('.tab-button-details');
            if (index == 1) {
                if (this.transaction.tipo == 'Despesa' && this.edit == false) {
                    tabs.forEach((tab, i) => {
                        tab.classList.toggle('active', i === index);
                        buttons[i].classList.toggle('active', i === index);
                    });
                    this.showShareTab = !this.showShareTab;
                    console.log("showShare index 1:" + this.showShareTab);
                }else if(this.transaction.tipo == 'Despesa' && this.edit == true){
                    this.checkInputs();
                    if(this.goToShare() == true){
                        tabs.forEach((tab, i) => {
                        tab.classList.toggle('active', i === index);
                        buttons[i].classList.toggle('active', i === index);
                        });
                        this.showShareTab = !this.showShareTab;
                        console.log("showShare index 1:" + this.showShareTab);
                    }
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
</style>
