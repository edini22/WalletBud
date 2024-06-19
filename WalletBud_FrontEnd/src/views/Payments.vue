<template>
    <div class="container-fluid">
        <div class="row">
            <div class="col">
                <div class="col pt-0">
                    <div class="card my-4">
                        <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                            <div class="bg-gradient-info shadow-info border-radius-lg pt-3 pb-2">
                                <h6 class="text-white text-capitalize ps-3">
                                    Transações Fixas - Pagamentos
                                </h6>
                            </div>
                        </div>
                        <div class="px-0 pb-2">
                            <div class="table-responsive p-0 scroll-container">
                                <table class="table align-items-center justify-content-center mb-0">
                                    <thead class="table-head-fixed">
                                        <tr>
                                            <th class="text-uppercase text-xxs font-weight-bolder opacity-7">
                                                Despesa
                                            </th>
                                            <th class="text-uppercase text-xxs font-weight-bolder opacity-7 ps-2">
                                                Periodicidade
                                            </th>
                                            <th class="text-uppercase text-xxs font-weight-bolder opacity-7 ps-2">
                                                Próximo Pagamento
                                            </th>
                                            <th class="text-uppercase text-xxs font-weight-bolder opacity-7 ps-2">
                                                Valor
                                            </th>
                                            <th class="text-uppercase text-xxs font-weight-bolder opacity-7 ps-2">
                                                Ação
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <template v-if="
                                            store &&
                                            store.proximosPagamentos &&
                                            store.proximosPagamentos.length > 0
                                        ">
                                            <tr v-for="(p, index) in store.proximosPagamentos" :key="index">
                                                <td>
                                                    <div class="d-flex px-3">
                                                        <div class="my-auto">
                                                            <h6 class="mb-0 text-sm cuter">{{ p.name }}</h6>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <p class="text-xs font-weight-bold mb-0">
                                                        {{ getRepetitionText(p.repeticao) }}
                                                    </p>
                                                </td>
                                                <td>
                                                    <span class="text-xs font-weight-bold">{{
                                                        p.date
                                                    }}</span>
                                                </td>
                                                <td>
                                                    <span class="text-md font-weight-bold">{{ p.shareValue }}
                                                        {{ user.moeda }}</span>
                                                </td>
                                                <td>
                                                    <material-button variant="gradient" color="secondary"
                                                        data-bs-toggle='modal' data-bs-target='#Pop1Modal'
                                                        class="btn btn-sm small-button" @click="openPopup(p.id)">
                                                        {{ $t("Abrir") }}
                                                    </material-button>
                                                </td>
                                            </tr>
                                        </template>
                                        <template v-else>
                                            <tr>
                                                <td colspan="5" class="text-center">
                                                    <h6 class=" message">{{ $t("Sem pagamentos em atraso!") }}</h6>
                                                </td>
                                            </tr>
                                        </template>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="container-fluid py-0">
                    <div class="row">
                        <div class="col-12">
                            <div class="card my-4">
                                <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                                    <div class="bg-gradient-danger shadow-danger border-radius-lg pt-3 pb-2">
                                        <h6 class="text-white text-capitalize ps-3">Por Pagar</h6>
                                    </div>
                                </div>
                                <div class="px-0 pb-2">
                                    <div class="table-responsive p-0 scroll-container2">
                                        <table class="table align-items-center justify-content-center mb-0">
                                            <thead class="table-head-fixed">
                                                <tr>
                                                    <th class="text-uppercase text-xxs font-weight-bolder opacity-7">
                                                        Despesa
                                                    </th>
                                                    <th
                                                        class="text-uppercase text-xxs font-weight-bolder opacity-7 ps-2">
                                                        Periodicidade
                                                    </th>
                                                    <th
                                                        class="text-uppercase text-xxs font-weight-bolder opacity-7 ps-2">
                                                        Data de Pagamento
                                                    </th>
                                                    <th
                                                        class="text-uppercase text-xxs font-weight-bolder opacity-7 ps-2">
                                                        Valor
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <template v-if="
                                                    store && store.porPagar && store.porPagar.length > 0
                                                ">
                                                    <tr v-for="(p, index) in store.porPagar" :key="index">
                                                        <td>
                                                            <div class="d-flex px-3">
                                                                <div class="my-auto">
                                                                    <h6 class="mb-0 text-sm cuter">{{ p.name }}</h6>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <p class="text-xs font-weight-bold mb-0">
                                                                {{ getRepetitionText(p.repeticao) }}
                                                            </p>
                                                        </td>
                                                        <td>
                                                            <span class="text-xs font-weight-bold">{{
                                                                p.date
                                                            }}</span>
                                                        </td>
                                                        <td>
                                                            <span class="text-md font-weight-bold">{{ p.shareValue
                                                                }} {{ user.moeda }}</span>
                                                        </td>
                                                    </tr>
                                                </template>
                                                <template v-else>
                                                    <tr>
                                                        <td colspan="5" class="text-center">
                                                            <h6 class="message">{{ $t("Sem pagamentos em atraso!") }}
                                                            </h6>
                                                        </td>
                                                    </tr>
                                                </template>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="card my-4">
                                <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                                    <div class="bg-gradient-warning shadow-warning border-radius-lg pt-3 pb-2">
                                        <h6 class="text-white text-capitalize ps-3">
                                            Pedidos Pendentes
                                        </h6>
                                    </div>
                                </div>
                                <div class="px-0 pb-2">
                                    <div class="table-responsive p-0 scroll-container2">
                                        <table class="table align-items-center justify-content-center mb-0">
                                            <thead class="table-head-fixed">
                                                <tr>
                                                    <th class="text-uppercase text-xxs font-weight-bolder opacity-7">
                                                        Despesa
                                                    </th>
                                                    <th
                                                        class="text-uppercase text-xxs font-weight-bolder opacity-7 ps-2">
                                                        Periodicidade
                                                    </th>
                                                    <th
                                                        class="text-uppercase text-xxs font-weight-bolder opacity-7 ps-2">
                                                        Próximo Pagamento
                                                    </th>
                                                    <th
                                                        class="text-uppercase text-xxs font-weight-bolder opacity-7 ps-2">
                                                        Valor
                                                    </th>
                                                    <th
                                                        class="text-uppercase text-xxs font-weight-bolder opacity-7 ps-2">
                                                        Detalhes
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <template v-if="
                                                    store &&
                                                    store.pendentes &&
                                                    store.pendentes.length > 0
                                                ">
                                                    <tr v-for="(p, index) in store.pendentes" :key="index">
                                                        <td>
                                                            <div class="d-flex px-3">
                                                                <div class="my-auto">
                                                                    <h6 class="mb-0 text-sm cuter">{{ p.name }}</h6>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <p class="text-xs font-weight-bold mb-0">
                                                                {{ getRepetitionText(p.repeticao) }}
                                                            </p>
                                                        </td>
                                                        <td>
                                                            <span class="text-xs font-weight-bold">{{
                                                                p.date
                                                            }}</span>
                                                        </td>
                                                        <td>
                                                            <span class="text-md font-weight-bold">{{ p.shareValue
                                                                }} {{ user.moeda }}</span>
                                                        </td>
                                                        <td>
                                                            <material-button variant="gradient" color="secondary"
                                                                class="btn btn-sm small-button"
                                                                @click="openDetailsPopup(p.id)" data-bs-toggle='modal'
                                                                data-bs-target='#Pop1Modal'>
                                                                {{ $t("Detalhes") }}
                                                            </material-button>
                                                        </td>
                                                    </tr>
                                                </template>
                                                <template v-else>
                                                    <tr>
                                                        <td colspan="5" class="text-center">
                                                            <h6 class=" message ">{{ $t("Sem pedidos pendentes!") }}
                                                            </h6>
                                                        </td>
                                                    </tr>
                                                </template>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--PopUp 1-->
    <div class="modal fade" id="Pop1Modal" tabindex="-1" aria-labelledby="transactionModalLabel" aria-hidden="true"
        data-bs-backdrop='static' data-bs-keyboard='false'>
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <div v-if="popup">
                        <h5 class="modal-title">Informações da Transacao</h5>
                    </div>
                    <div v-if="popupDetails">
                        <h5 class="modal-title">Informações do pedido de adesão</h5>
                    </div>
                    <material-button id="cancelButton" variant="gradient" color="secondary"
                        class="btn btn-sm small-button" data-bs-dismiss="modal"
                        @click="popup = false; popupDetails = false; isEditing = false; currentEditIndex = null; infos = true; participantes = false; emailError = null; newUserEmail = '', emailErrorStore = null;">
                        {{ $t("Voltar") }}
                    </material-button>
                </div>
                <div class="modal-body scroll-container3">
                    <div class="nav-wrapper position-relative end-0 mb-4">
                        <ul class="nav nav-pills nav-fill p-1" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link tab-button" :class="{ active: infos }" @click="
                                    infos = true;
                                participantes = false;
                                ">{{ $t("Informação") }}</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link tab-button" :class="{ active: participantes }" @click="
                                    participantes = true;
                                infos = false;
                                ">{{ $t("Participantes") }}</a>
                            </li>
                        </ul>
                    </div>
                    <div v-if="transa">
                        <div v-if="infos">
                            <div v-if="isEditing">
                                <div class="tab tab-content active" id="tab-0">

                                    <h6>Tipo: <span class="text-info">&nbsp;&nbsp;{{ transa.tipo }}</span></h6>

                                    <!-- Name -->
                                    <div class="form-group form-row">
                                        <label for="description" class="form-label">{{ $t('Nome') }}
                                            <p class="required text-danger text-sm"> *</p>
                                        </label>
                                        <div v-if="nameError === true" class="form-input mb-1">
                                            <material-input class="material-input" id="description" type="text"
                                                :label="$t('Indique o nome')" name="description"
                                                @update:value="Name = $event" :value="Name" error />
                                        </div>
                                        <div v-if="nameError === false" class="form-input mb-1">
                                            <material-input class="material-input" id="description" type="text"
                                                name="description" :value="Name" @update:value="Name = $event"
                                                success />
                                        </div>
                                        <div v-if="nameError === null" class="form-input mb-1">
                                            <material-input class="material-input" id="description" type="text"
                                                :value="Name" name="description" @update:value="Name = $event" />
                                        </div>
                                    </div>

                                    <!-- Descrição -->
                                    <div class="form-group form-row">
                                        <label for="place" class="form-label">{{ $t('Descrição') }}</label>
                                        <div class="form-input mb-1">
                                            <material-input class="material-input" id="place" type="text" name="place"
                                                :value="Description" @update:value="Description = $event" />
                                        </div>
                                    </div>

                                    <!-- Montante -->
                                    <div class="form-group form-row">
                                        <label for="value" class="form-label">{{ $t('Montante') }}
                                            <p class="required text-danger text-sm"> *</p>
                                        </label>
                                        <div v-if="valueError === true" class="form-input mb-1">
                                            <material-input class="material-input" id="value" type="number"
                                                :value="Value" :label="$t('Indique um montante válido')" name="value"
                                                @update:value="Value = $event" error />
                                        </div>
                                        <div v-if="valueError === false" class="form-input mb-1">
                                            <material-input class="material-input" id="value" type="number" name="value"
                                                :value="Value" @update:value="Value = $event" success />
                                        </div>
                                        <div v-if="valueError === null" class="form-input mb-1">
                                            <material-input class="material-input" id="value" type="number"
                                                :value="Value" name="value" @update:value="Value = $event" />
                                        </div>
                                    </div>

                                    <!-- Repetição -->
                                    <div v-if="Recorrence != 'Unica'" class="dropdown" ref="repeatDropdown">
                                        <div class="form-group form-row">
                                            <label for="Repetition" class="form-label">{{ $t('Repetição') }}
                                                <p class="required text-danger text-sm"> *</p>
                                            </label>
                                            <div v-if="repetitionError === null || repetitionError === false"
                                                class="input-group input-group-outline form-input mb-1"
                                                style="border-radius: 0.375rem;">
                                                <button
                                                    class="cursor-pointer form-control form-control-default material-input"
                                                    :class="{ 'dropdown-focused-null': isRepetitionFocused }"
                                                    id="dropdownTable" data-bs-toggle="dropdown"
                                                    style="text-align:left; color: #7b809a"
                                                    @focus="handleRepetitionFocus">
                                                    {{ Repetition || $t('Selecione a repetição') }}
                                                </button>
                                                <i class="material-icons arrow-icon">repeat</i>
                                                <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5"
                                                    aria-labelledby="dropdownTable">
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
                                                <button
                                                    class="cursor-pointer form-control form-control-default material-input"
                                                    :class="{ 'dropdown-focused-error': isRepetitionFocused }"
                                                    id="dropdownTable" data-bs-toggle="dropdown"
                                                    style="text-align:left; color: #7b809a"
                                                    @focus="handleRepetitionFocus">
                                                    {{ Repetition || $t('Selecione a repetição') }}
                                                </button>

                                                <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5"
                                                    aria-labelledby="dropdownTable">
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
                                                <p class="required text-danger text-sm "> *</p>
                                            </label>
                                            <div v-if="CategoryError === null || CategoryError === false"
                                                class="input-group input-group-outline form-input mb-1"
                                                style="border-radius: 0.375rem;">
                                                <button v-if="displayCategories.length > 0"
                                                    class="cursor-pointer form-control form-control-default material-input"
                                                    :class="{ 'dropdown-focused-null': isCategoryFocused }"
                                                    id="dropdownTable" data-bs-toggle="dropdown"
                                                    style="text-align:left; color: #7b809a"
                                                    @focus="handleCategoryFocus">
                                                    {{ Category.name || $t('Selecione a categoria') }}
                                                </button>
                                                <!-- show this button if type has not been chosen -->
                                                <button v-else
                                                    class="cursor-pointer form-control form-control-default material-input"
                                                    :class="{ 'dropdown-focused-null': isCategoryFocused }"
                                                    id="dropdownTable" style="text-align:left; color: #7b809a"
                                                    @click="alert">
                                                    {{ Category.name || $t('Selecione a categoria') }}
                                                </button>
                                                <i class="material-icons arrow-icon">keyboard_arrow_down</i>
                                                <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5"
                                                    aria-labelledby="dropdownTable">
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
                                                    :class="{ 'dropdown-focused-error': isCategoryFocused }"
                                                    id="dropdownTable" data-bs-toggle="dropdown"
                                                    style="text-align:left; color: #7b809a"
                                                    @focus="handleCategoryFocus">
                                                    {{ Category.name || $t('Selecione a categoria') }}
                                                </button>
                                                <!-- show this button if type has not been chosen -->
                                                <button v-else
                                                    class="cursor-pointer form-control form-control-default material-input"
                                                    :class="{ 'dropdown-focused-error': isCategoryFocused }"
                                                    id="dropdownTable" style="text-align:left; color: #7b809a"
                                                    @click="alert">
                                                    {{ Category.name || $t('Selecione a categoria') }}
                                                </button>
                                                <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5"
                                                    aria-labelledby="dropdownTable">
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


                                    <!-- Local -->
                                    <div class="form-group form-row">
                                        <label for="place" class="form-label">{{ $t('Local') }}</label>
                                        <div class="form-input mb-1">
                                            <material-input class="material-input" id="place" type="text" name="place"
                                                :value="Place" @update:value="Place = $event" />
                                        </div>
                                    </div>

                                    <label class="form-label mb-1">
                                        <p class="required text-danger text-xs"> * {{ $t('Campo obrigatório') }}
                                        </p>
                                    </label>

                                </div>
                            </div>
                            <div v-else>
                                <div class="form-group form-row">
                                    <label for="description" class="form-label">{{ $t('Nome') }} </label>
                                    <p class="detail form-label cuter">{{ transa.name }}</p>
                                </div>
                                <div class="form-group form-row">
                                    <label for="description" class="form-label">{{ $t('Descrição') }} </label>
                                    <p class="detail form-label cuter">{{ transa.descricao }}</p>
                                </div>
                                <div class="form-group form-row">
                                    <label for="description" class="form-label">{{ $t('Valor Total') }} </label>
                                    <p class="detail form-label">{{ transa.value }}</p>
                                </div>
                                <div v-if="transa.value != transa.shareValue" class="form-group form-row">
                                    <label for="description" class="form-label">{{ $t('Valor Partilhado') }} </label>
                                    <p class="detail form-label">{{ transa.shareValue }}</p>
                                </div>
                                <div class="form-group form-row">
                                    <label for="description" class="form-label">{{ $t('Periodicidade') }} </label>
                                    <p class="detail form-label">{{ getRepetitionText(transa.repeticao) }}</p>
                                </div>
                                <div class="form-group form-row">
                                    <label for="description" class="form-label">{{ $t('Data Criação') }} </label>
                                    <p class="detail form-label">{{ transa.date }}</p>
                                </div>
                                <div v-if="popup" class="form-group form-row">
                                    <label for="description" class="form-label">{{ $t('Próximo Pagamento') }} </label>
                                    <p class="detail form-label">{{ dateP }}</p>
                                </div>
                                <div class="form-group form-row">
                                    <label for="description" class="form-label">{{ $t('Categoria') }} </label>
                                    <p class="detail form-label">{{ transa.categoria }}</p>
                                </div>
                                <div class="form-group form-row">
                                    <label for="description" class="form-label">{{ $t('Tipo') }} </label>
                                    <p class="detail form-label">{{ transa.tipo }}</p>
                                </div>
                                <div v-if="transa.local != ''" class="form-group form-row">
                                    <label for="description" class="form-label">{{ $t('Local') }} </label>
                                    <p class="detail form-label cuter">{{ transa.local }}</p>
                                </div>


                                <!-- <div v-if="comments && comments.length > 0" class="modal-footer d-block justify-content-center"> -->
                                <div v-if="comments" class="modal-footer d-block justify-content-center">
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
                                                <p class="detail form-label cuter">{{ c.user_email }}</p>
                                            </div>
                                            <div class="form-group form-row">
                                                <label for="description" class="form-label">{{ $t('Data') }} </label>
                                                <p class="detail form-label cuter">{{ c.timestamp }}</p>
                                            </div>
                                            <div class="form-group form-row ">
                                                <label for="description" class="form-label cuter">{{ $t('Comentáio') }}
                                                </label>
                                                <p class="detail form-label cuter">{{ c.descricao }}</p>
                                            </div>

                                        </h6>
                                        <input v-else class="input-group input-group-outline custom"
                                            style="border-radius: 6px;border: 2px solid #344767;"
                                            v-model="editComment" />
                                        <div class="buttons" v-if="currentEditIndex === index">
                                            <button @click="saveComment(index)" style="background-color: white; ">
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
                                                <i class="material-icons "
                                                    style="color: #344767; font-size: 18px; ">edit</i>
                                            </button>
                                            <button v-if="user.email === c.user_email || user.id === transa.users[0].id"
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
                                                class="btn btn-md d-flex justify-content-center align-items-center"
                                                @click="addComment(transa.id)">
                                                {{ $t("Comentar") }}
                                            </material-button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div v-if="participantes">
                            <div v-for="(u, index) in transa.users" :key="index">
                                <div class="">
                                    <div class="form-group form-row">
                                        <label for="description" class="form-label">{{ $t('Utilizador') }} </label>
                                        <p class="detail form-label cuter">{{ u.name }}</p>
                                    </div>
                                    <div class="form-group form-row">
                                        <label for="description" class="form-label">{{ $t('Email') }} </label>
                                        <p class="detail form-label cuter">{{ u.email }}</p>
                                    </div>
                                    <div v-if="u.confirma === 1" class="form-group form-row">
                                        <label for="description" class="form-label">{{ $t('Estado') }} </label>
                                        <p class="detail form-label cuter text-success">Aceite</p>
                                    </div>
                                    <div v-if="u.confirma === 0" class="form-group form-row">
                                        <label for="description" class="form-label">{{ $t('Estado') }} </label>
                                        <p class="detail form-label cuter text-warning">Em espera</p>
                                    </div>

                                    <div v-if="transa.users[0].id === user.id">
                                        <!--remove user-->
                                        <material-button v-if="transa.users[0].id != u.id && popup" variant="gradient"
                                            color="danger" class="btn btn-sm small-button" @click="
                                                popupKickInfo(u);
                                            popupKick = true;
                                            popup = false;
                                            popupDetails = false;
                                            typeKick = 0;
                                            " data-bs-dismiss="modal" data-bs-toggle='modal'
                                            data-bs-target='#kickModal'>
                                            {{ $t("Remover") }}
                                        </material-button>
                                        <material-button v-if="transa.users[0].id != u.id && popupDetails"
                                            variant="gradient" color="danger" class="btn btn-sm small-button" @click="
                                                popupKickInfo(u);
                                            popupKick = true;
                                            popup = false;
                                            popupDetails = false;
                                            typeKick = 1;
                                            " data-bs-dismiss="modal" data-bs-toggle='modal'
                                            data-bs-target='#kickModal'>
                                            {{ $t("Remover") }}
                                        </material-button>
                                    </div>
                                    <hr class="horizontal dark" />
                                </div>
                            </div>
                            <!-- Shared expense -->
                            <div v-if="transa.users[0].id === user.id && transa.tipo == 'despesa'">
                                <hr class="horizontal dark my-sm-4" />
                                <div class="form-group">
                                    <label for="sharedExpense" class="form-label mb-3">{{ $t('Adicionar novos membros:')
                                        }}</label>
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
                                            <material-input class="material-input mb-3" id="email" type="email"
                                                name="email" :value="newUserEmail" @update:value="newUserEmail = $event"
                                                success />
                                        </div>

                                        <div v-if="emailError === null" class="mb-3">
                                            <material-input class="material-input mb-3" id="email" type="email"
                                                name="email" :value="newUserEmail" @update:value="newUserEmail = $event"
                                                :label="$t('Indique o email do utilizador')" />
                                        </div>

                                        <p class="btn btn-default bg-gradient-info mb-1" @click="addUser()">{{
                                            $t('Adicionar Utilizador') }}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div v-if="transa && transa.users && transa.users.length > 0">
                    <div v-if="transa.users[0].id == user.id">
                        <div class="modal-footer d-flex justify-content-between" v-if="isEditing === false">
                            <material-button variant="gradient" color="danger" class="btn btn-md" @click="
                                toggleToDelete();
                            " data-bs-dismiss="modal" data-bs-toggle='modal' data-bs-target='#PopEliminarModal'>
                                {{ $t("Eliminar") }}
                            </material-button>
                            <material-button v-if="infos === true" variant="gradient" color="warning" class="btn btn-md"
                                @click="
                                    toggleToEdit();
                                isEditing = true;
                                currentEditIndex = null;
                                infos = true;
                                participantes = false;
                                emailError = null; newUserEmail = '', emailErrorStore = null;
                                ">
                                {{ $t("Editar") }}
                            </material-button>
                            <material-button v-if="popup" variant="gradient" color="info" class="btn btn-md" @click="
                                isEditing = false;
                            popupAccept = true;
                            popup = false;
                            popupDetails = false;
                            currentEditIndex = null;
                            infos = true;
                            participantes = false;
                            emailError = null; newUserEmail = '', emailErrorStore = null;
                            " data-bs-dismiss="modal" data-bs-toggle='modal' data-bs-target='#PayModal'>
                                {{ $t("Pagar") }}
                            </material-button>
                        </div>
                        <div class="modal-footer d-flex justify-content-between" v-else>
                            <material-button variant="gradient" color="danger" class="btn btn-md" @click="
                                isEditing = false;
                            currentEditIndex = null;
                            infos = true;
                            participantes = false;
                            emailError = null; newUserEmail = '', emailErrorStore = null;
                            ">
                                {{ $t("Cancelar") }}
                            </material-button>
                            <material-button variant="gradient" color="info" class="btn btn-md"
                                @click="editTransaction()">
                                {{ $t("Guardar") }}
                            </material-button>
                        </div>
                    </div>
                    <div v-else>
                        <div v-if="popup" class="modal-footer d-flex justify-content-end">
                            <material-button variant="gradient" color="danger" class="btn btn-md" @click="
                                toggleToDelete();
                            leave = true;
                            " data-bs-dismiss="modal">
                                {{ $t("Sair") }}
                            </material-button>
                        </div>
                        <div v-else>
                            <div v-if="acceptorRejectPendenteButton() === true" class="modal-footer d-flex justify-content-between">
                                    <material-button variant="gradient" color="danger" class="btn btn-md" @click="
                                        popupReject2 = true;
                                        popupDetails = false;
                                        currentEditIndex = null;
                                        infos = true;
                                        participantes = false;
                                        emailError = null; newUserEmail = '', emailErrorStore = null;
                                        isEditing = false;
                                        " data-bs-dismiss="modal" data-bs-toggle='modal' data-bs-target='#rejectModal'>
                                        {{ $t("Rejeitar") }}
                                    </material-button>

                                    <material-button variant="gradient" color="info" class="btn btn-md" @click="
                                        popupAccept2 = true;
                                        popupDetails = false;
                                        currentEditIndex = null;
                                        infos = true;
                                        participantes = false;
                                        emailError = null; newUserEmail = '', emailErrorStore = null;
                                        isEditing = false;
                                        " data-bs-dismiss="modal" data-bs-toggle='modal' data-bs-target='#acceptModal'>
                                        {{ $t("Aceitar") }}
                                    </material-button>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--PopUp Eliminar-->
    <div class="modal fade" id="PopEliminarModal" tabindex="-1" aria-hidden="true"
        aria-labelledby="transactionModalLabel" data-bs-backdrop='static' data-bs-keyboard='false'>
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <div v-if="leave === true">
                        <h5 class="modal-title">Tem a certeza que deseja sair do grupo?</h5>
                    </div>
                    <div v-else>
                        <h5 class="modal-title">
                            Tem a certeza que deseja eliminar o grupo?
                        </h5>
                    </div>
                </div>
                <div class="modal-body text-center">
                    <p>A sua ação é irreversível!</p>
                </div>
                <div class="modal-footer d-flex justify-content-between">
                    <material-button v-if="auxDelete" variant="gradient" color="secondary" class="btn btn-md" @click="
                        popupReject = false;
                    popup = true;
                    leave = false;
                    " data-bs-dismiss="modal" data-bs-toggle='modal' data-bs-target='#Pop1Modal'>
                        {{ $t("Voltar") }}
                    </material-button>

                    <material-button v-else variant="gradient" color="secondary" class="btn btn-md" @click="
                        popupReject = false;
                    popupDetails = true;
                    leave = false;
                    " data-bs-dismiss="modal" data-bs-toggle='modal' data-bs-target='#Pop1Modal'>
                        {{ $t("Voltar") }}
                    </material-button>

                    <material-button variant="gradient" color="info" class="btn btn-md" @click="
                        deleteTransacao();
                    popupReject = false;
                    popup = false;
                    leave = false;
                    " data-bs-dismiss="modal">
                        {{ $t("Confirmar") }}
                    </material-button>
                </div>
            </div>
        </div>
    </div>

    <!--PopUp Aceitar-->
    <div class="modal fade" id="PayModal" tabindex="-1" aria-hidden="true" data-bs-backdrop='static'
        data-bs-keyboard='false'>
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Deseja proceder com o pagamento?</h5>
                </div>
                <div class="modal-body text-center">
                    <p>A sua ação é irreversível!</p>
                </div>
                <div class="modal-footer d-flex justify-content-between">
                    <material-button variant="gradient" color="secondary" class="btn btn-md" @click="
                        popupAccept = false;
                    popup = true;
                    " data-bs-dismiss="modal" data-bs-toggle='modal' data-bs-target='#Pop1Modal'>
                        {{ $t("Voltar") }}
                    </material-button>
                    <material-button variant="gradient" color="info" class="btn btn-md" @click="
                        payTransaction();
                    popupAccept = false;
                    popup = false;
                    " data-bs-dismiss="modal">
                        {{ $t("Confirmar") }}
                    </material-button>
                </div>
            </div>
        </div>
    </div>

    <!--PopUp Detalhes Rejeitar-->
    <div id="rejectModal" class="modal fade" tabindex="-1" aria-hidden="true" data-bs-backdrop='static'
        data-bs-keyboard='false'>
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">
                        Tem a certeza que não deseja participar no grupo?
                    </h5>
                </div>
                <div class="modal-body text-center">
                    <p>A sua ação é irreversível!</p>
                </div>
                <div class="modal-footer d-flex justify-content-between">
                    <material-button variant="gradient" color="secondary" class="btn btn-md" @click="
                        popupReject2 = false;
                    popupDetails = true;
                    " data-bs-dismiss="modal" data-bs-toggle='modal' data-bs-target='#Pop1Modal'>
                        {{ $t("Voltar") }}
                    </material-button>
                    <material-button variant="gradient" color="info" class="btn btn-md" @click="
                        acceptorReject(-1);
                    popupReject2 = false;
                    snackbar = 'successReject2';
                    popupDetails = false;
                    " data-bs-dismiss="modal">
                        {{ $t("Confirmar") }}
                    </material-button>
                </div>
            </div>
        </div>
    </div>

    <!--PopUp Detalhes Aceitar-->
    <div class="modal fade" id="acceptModal" tabindex="-1" aria-hidden="true" data-bs-backdrop='static'
        data-bs-keyboard='false'>
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Deseja proceder a entrada no grupo?</h5>
                </div>
                <div class="modal-body text-center">
                    <p>A sua ação é irreversível!</p>
                </div>
                <div class="modal-footer d-flex justify-content-between">
                    <material-button variant="gradient" color="secondary" class="btn btn-md" @click="
                        popupAccept2 = false;
                    popupDetails = true;
                    " data-bs-dismiss="modal" data-bs-toggle='modal' data-bs-target='#Pop1Modal'>
                        {{ $t("Voltar") }}
                    </material-button>
                    <material-button variant="gradient" color="info" class="btn btn-md" @click="
                        acceptorReject(1);
                    popupAccept2 = false;
                    snackbar = 'successAccept2';
                    popupDetails = false;
                    " data-bs-dismiss="modal">
                        {{ $t("Confirmar") }}
                    </material-button>
                </div>
            </div>
        </div>
    </div>

    <!-- PopUp kick user -->
    <div class="modal fade" id="kickModal" tabindex="-1" aria-hidden="true" data-bs-backdrop='static'
        data-bs-keyboard='false'>
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 v-if="userKick !== null" class="modal-title">Deseja deixar de partilhar a despesa com {{
                        userKick.name }}?</h5>
                </div>
                <div class="modal-body text-center">
                    <p>A sua ação é irreversível!</p>
                </div>
                <div class="modal-footer d-flex justify-content-between">
                    <div v-if="typeKick === 1">
                        <material-button variant="gradient" color="secondary" class="btn btn-md" @click="
                            popupKick = false;
                        popupDetails = true;
                        " data-bs-dismiss="modal" data-bs-toggle='modal' data-bs-target='#Pop1Modal'>
                            {{ $t("Voltar") }}
                        </material-button>
                    </div>
                    <div v-else>
                        <material-button variant="gradient" color="secondary" class="btn btn-md" @click="
                            popupKick = false;
                        popup = true;
                        " data-bs-dismiss="modal" data-bs-toggle='modal' data-bs-target='#Pop1Modal'>
                            {{ $t("Voltar") }}
                        </material-button>
                    </div>
                    <material-button v-if="transa && transa.users.length == 2 && transa.transacao && transa.transacao == 'unica'" variant="gradient" color="danger" class="btn btn-sm small-button"
                        @click="kickUser()"
                        data-bs-dismiss="modal"
                        >
                        {{ $t("Remover") }}
                    </material-button>
                    <material-button v-else variant="gradient" color="danger" class="btn btn-sm small-button"
                        @click="kickUser()"
                        data-bs-dismiss="modal" data-bs-toggle='modal' data-bs-target='#Pop1Modal'
                        >
                        {{ $t("Remover") }}
                    </material-button>
                </div>
            </div>
        </div>
    </div>

    <div class="position-fixed top-1 end-1 z-index-2">
        <material-snackbar v-if="snackbar === 'successReject'" title="Pagamento" date="now"
            description="Grupo de Pagamento removido com sucesso!" :icon="{ component: 'done', color: 'white' }"
            color="success" :close-handler="closeSnackbar" />

        <material-snackbar v-if="snackbar === 'successAccept'" title="Pagamento" date="now"
            description="Pagamento efetuado com sucesso!" :icon="{ component: 'done', color: 'white' }" color="success"
            :close-handler="closeSnackbar" />

        <material-snackbar v-if="snackbar === 'successAccept2'" title="Adesão" date="now"
            description="Adesão a grupo efetuada com sucesso!" :icon="{ component: 'done', color: 'white' }"
            color="success" :close-handler="closeSnackbar" />

        <material-snackbar v-if="snackbar === 'successReject2'" title="Adesão" date="now"
            description="Adesão a grupo rejeitada com sucesso!" :icon="{ component: 'done', color: 'white' }"
            color="success" :close-handler="closeSnackbar" />
    </div>
</template>

<script>
import MaterialButton from "@/components/MaterialButton.vue";
import MaterialSnackbar from "@/components/MaterialSnackbar.vue";
import MaterialInput from "@/components/MaterialInput.vue";

import { ref, onMounted } from "vue";
import { fixaStore } from "@/store/fixaStore";
import { userStore } from "@/store/userStore";

import { useRouter } from "vue-router";

import { useI18n } from 'vue-i18n';

import { categoriesStore } from "@/store/categoriesStore";

export default {
    name: "payments",
    components: {
        MaterialButton,
        MaterialSnackbar,
        MaterialInput,
    },
    setup() {
        const { t } = useI18n();
        const snackbar = ref(false);
        const popup = ref(false);
        const popupReject = ref(false);
        const popupAccept = ref(false);
        const popupDetails = ref(false);
        const popupReject2 = ref(false);
        const popupAccept2 = ref(false);

        const popupKick = ref(false);
        const userKick = ref(null);
        const typeKick = ref(null);

        const infos = ref(true);
        const participantes = ref(false);
        const leave = ref(false);


        const store = fixaStore();
        const user = userStore();
        const transa = ref(null);
        const dateP = ref(null);
        const comments = ref(null);
        const Comment = ref('');

        const emailError = ref(null);

        const transa_id = ref(0);

        const auxDelete = ref(false);

        const router = useRouter();

        const currentEditIndex = ref(null);
        const editComment = ref(null);

        let isValid = true;
        const newUserEmail = ref('');
        const emailErrorStore = ref(null);

        const isEditing = ref(false);

        const categories = categoriesStore();

        //===================
        const Name = ref('');
        const nameError = ref(null);
        const Description = ref('');
        const Value = ref('');
        const valueError = ref(null);
        const Place = ref('');
        const Type = ref('');
        const Category = ref('');
        const CategoryError = ref(null);
        const Repetition = ref('');
        const SendRepetition = ref(0);
        const repetitionError = ref(null);
        const Recorrence = ref('');
        const recorrenceError = ref(null);

        const checkInputs = function () {
            if (!Name.value.trim())
                nameError.value = true;
            else
                nameError.value = false;

            if (!Value.value)
                valueError.value = true;
            else if (Value.value < 0) {
                valueError.value = true;
            }
            else
                valueError.value = false;

            if (!Category.value)
                CategoryError.value = true;
            else
                CategoryError.value = false;

            if (!Recorrence.value)
                recorrenceError.value = true;
            else
                recorrenceError.value = false;

            if (Recorrence.value != 'Unica') {
                if (!Repetition.value)
                    repetitionError.value = true;
                else
                    repetitionError.value = false;
            }
        }

        const cancel = function () {
            console.log(Description.value);

            nameError.value = null;
            Name.value = '';

            Description.value = '';

            valueError.value = null;
            Value.value = '';

            recorrenceError.value = null;
            Recorrence.value = '';

            repetitionError.value = null;
            Repetition.value = '';

            CategoryError.value = null;
            Category.value = '';

            Place.value = '';
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

        function transformInvRepetition(repetition) {
            if (repetition == 1) {
                return 'Todos os dias';
            } else if (repetition == 2) {
                return 'Todas as semanas';
            } else if (repetition == 3) {
                return 'Todos os meses';
            } else if (repetition == 4) {
                return 'Todos os anos';
            }
        }

        function dataTransform() {
            if (Recorrence.value == 'Unica') {
                if (transa.value.name != Name.value || transa.value.descricao != Description.value || transa.value.value != Value.value || transa.value.local != Place.value || transa.value.categoria != Category.value.name) {
                    return false;
                }
            } else {
                if (transa.value.name != Name.value || transa.value.descricao != Description.value || transa.value.value != Value.value || transa.value.local != Place.value || transa.value.repeticao != transformRepetition(Repetition.value.trim()) || transa.value.categoria != Category.value.name) {
                    return false;
                }
            }
            return true;
        }

        const editTransaction = async function () {
            if (Type.value == 'receita' || Type.value == '') {
                checkInputs();
                //verifica se os campos estao alterados
                if (dataTransform()) {
                    isEditing.value = false;
                    console.log(transa.value);
                    if (transa.value.status == true) {
                        popup.value = true;
                        popupDetails.value = false;
                    } else {
                        popup.value = false;
                        popupDetails.value = true;
                    }
                    return;
                }


                if (nameError.value == false && valueError.value == false && CategoryError.value == false && recorrenceError.value == false) {
                    if (Recorrence.value != 'Unica') {
                        if (repetitionError.value == false) {
                            //faz post de receita fixa
                            SendRepetition.value = transformRepetition(Repetition.value.trim());

                            console.log(Repetition.value + ": " + SendRepetition.value);
                            //POST da Receita Não Fixa
                            const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/fixa/receita/set";

                            const token = localStorage.getItem("token");

                            const request = {
                                method: "POST",
                                headers: {
                                    "Content-Type": "application/json",
                                    Authorization: "Bearer " + token,
                                },
                                body: JSON.stringify({
                                    IdTransacao: transa.value.id,
                                    name: Name.value,
                                    value: Value.value,
                                    descricao: Description.value,
                                    IdCategoria: Category.value.id,
                                    local: Place.value,
                                    repeticao: SendRepetition.value
                                }),

                            };

                            try {
                                const response = await fetch(url, request);

                                if (!response.ok) {
                                    const errorData = await response.json();
                                    throw new Error(errorData.message);
                                }
                                console.log("post receita fixa");
                                isEditing.value = false;

                                //sucesso
                                const event = new CustomEvent('show-snackbar', { detail: 'success' });
                                document.dispatchEvent(event);
                                console.log('PopUp emitiu evento');
                                loadPendentes();
                                loadPorPagar();
                                loadProximosPagamentos();

                                popup.value = false;
                                popupDetails.value = false;

                                const cancelButton = document.getElementById('cancelButton');
                                cancelButton.click();

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
                        //POST da Receita Não Fixa
                        const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/unica/receita/set";

                        const token = localStorage.getItem("token");

                        const request = {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json",
                                Authorization: "Bearer " + token,
                            },
                            body: JSON.stringify({
                                IdTransacao: transa.value.id,
                                name: Name.value,
                                value: Value.value,
                                descricao: Description.value,
                                IdCategoria: Category.value.id,
                                local: Place.value,
                            }),

                        };

                        try {
                            const response = await fetch(url, request);

                            if (!response.ok) {
                                const errorData = await response.json();
                                throw new Error(errorData.message);
                            }
                            console.log("post receita não fixa");

                            //sucesso
                            const event = new CustomEvent('show-snackbar', { detail: 'successAddMove' });
                            document.dispatchEvent(event);
                            console.log('PopUp emitiu evento');

                            isEditing.value = false;
                            loadPendentes();
                            loadPorPagar();
                            loadProximosPagamentos();

                            popup.value = false;
                            popupDetails.value = false;

                            const cancelButton = document.getElementById('cancelButton');
                            cancelButton.click();

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
                checkInputs();

                if (dataTransform()) {
                    isEditing.value = false;
                    console.log(transa.value);
                    if (transa.value.status == true) {
                        popup.value = true;
                        popupDetails.value = false;
                    } else {
                        popup.value = false;
                        popupDetails.value = true;
                    }
                    return;
                }

                if (Recorrence.value != 'Unica') {
                    if (repetitionError.value == false) {
                        SendRepetition.value = transformRepetition(Repetition.value.trim());
                        console.log(Repetition.value + ": " + SendRepetition.value);

                        console.log("post despesa fixa e partilhada");

                        const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/fixa/despesa/set";

                        const token = localStorage.getItem("token");

                        const request = {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json",
                                Authorization: "Bearer " + token,
                            },
                            body: JSON.stringify({
                                IdTransacao: transa.value.id,
                                name: Name.value,
                                value: Value.value,
                                descricao: Description.value,
                                IdCategoria: Category.value.id,
                                local: Place.value,
                                repeticao: SendRepetition.value
                            }),
                        };

                        try {
                            const response = await fetch(url, request);

                            if (!response.ok) {
                                const errorData = await response.json();
                                throw new Error(errorData.message);
                            }

                            //POST da Despesa

                            //sucesso
                            const event = new CustomEvent('show-snackbar', { detail: 'success' });
                            document.dispatchEvent(event);
                            console.log('PopUp emitiu evento');


                            isEditing.value = false;
                            loadPendentes();
                            loadPorPagar();
                            loadProximosPagamentos();

                            popup.value = false;
                            popupDetails.value = false;

                            const cancelButton = document.getElementById('cancelButton');
                            cancelButton.click();

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

                    const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/unica/despesa/set";

                    const token = localStorage.getItem("token");

                    const request = {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                            Authorization: "Bearer " + token,
                        },
                        body: JSON.stringify({
                            IdTransacao: transa.value.id,
                            name: Name.value,
                            value: Value.value,
                            descricao: Description.value,
                            IdCategoria: Category.value.id,
                            local: Place.value,
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

                        //sucesso
                        const event = new CustomEvent('show-snackbar', { detail: 'successAddMove' });
                        document.dispatchEvent(event);
                        console.log('PopUp emitiu evento');

                        isEditing.value = false;
                        loadPendentes();
                        loadPorPagar();
                        loadProximosPagamentos();

                        popup.value = false;
                        popupDetails.value = false;

                        const cancelButton = document.getElementById('cancelButton');
                        cancelButton.click();

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

        const toggleToDelete = () => { //adicionar este ao return 
            if (popup.value == true) {
                auxDelete.value = true; //se estiver nos proximos pagamentos
            } else {
                auxDelete.value = false; //se ainda estiver nos pendentes
            }

            isEditing.value = false;
            popupReject.value = true;
            popup.value = false;
            popupDetails.value = false;
            currentEditIndex.value = null;
            infos.value = true;
            participantes.value = false;
            emailError.value = null;
            newUserEmail.value = '',
                emailErrorStore.value = null;
        };

        const toggleToEdit = () => {
            Name.value = transa.value.name;
            Description.value = transa.value.descricao;
            Value.value = transa.value.value;
            Repetition.value = transformInvRepetition(transa.value.repeticao);
            Type.value = transa.value.tipo;

            if (transa.value.repeticao) {
                Recorrence.value = 'Fixa';
            } else {
                Recorrence.value = 'Unica';
            }
            if (Type.value == 'despesa') {
                Category.value = categories.CategoriesExpense.find((c) => c.name === transa.value.categoria);
            } else {
                Category.value = categories.CategoriesIncome.find((c) => c.name === transa.value.categoria);
            }
            Place.value = transa.value.local;

            isEditing.value = true;
            currentEditIndex.value = null;
            infos.value = true;
            participantes.value = false;
            emailError.value = null;
            newUserEmail.value = '';
            emailErrorStore.value = null;
        };

        const loadPendentes = async () => {
            try {
                await store.loadPendentes();
                console.log("Pendentes carregados:", store.pendentes);
            } catch (err) {
                alert("Erro loadPendentes-> " + err.message);
            }
        };

        const loadPorPagar = async () => {
            try {
                await store.loadPorPagar();
                console.log("Por pagar carregados:", store.porPagar);
            } catch (err) {
                if (err.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                } else {
                    alert("Erro loadPorPagar-> " + err.message);
                }
            }
        };
        const loadProximosPagamentos = async () => {
            try {
                await store.loadProximosPagamentos();
                console.log("Proximos pagamentos :", store.proximosPagamentos);
            } catch (err) {
                if (err.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                } else {
                    alert("Erro loadProximosPagamentos-> " + err.message);
                }
            }
        };

        const getRepetitionText = (repeticao) => {
            if (repeticao === undefined) {
                return "Unica";
            }
            switch (repeticao) {
                case 1:
                    return "Diariamente";
                case 2:
                    return "Semanalmente";
                case 3:
                    return "Mensalmente";
                case 4:
                    return "Anualmente";
                default:
                    return "Unica";
            }
        };

        const openDetailsPopup = async (id) => {
            transa.value = store.pendentes.find((p) => p.id === id);
            const url_comments =
                "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/comment/list/" + id;
            const token = localStorage.getItem("token");

            const request = {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + token,
                },
            };
            try {
                const response_comments = await fetch(url_comments, request);

                if (!response_comments.ok) {
                    const errorData = await response_comments.json();
                    throw new Error(errorData.message);
                }

                const data_comments = await response_comments.json();
                console.log(data_comments);

                comments.value = data_comments.comentarios;


            } catch (error) {
                if (error.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                } else {
                    alert("Erro ao carregar comentários:", error.message);
                }
            }
            popup.value = false;
            popupDetails.value = true;
        };

        const acceptorRejectPendenteButton = () => {
            for (let i = 0; i < transa.value.users.length; i++) {
                if (
                    transa.value.users[i].id === user.id &&
                    user.id != transa.value.users[0].id
                ) {
                    if (!transa.value.users[i].confirma) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (
                    transa.value.users[i].id === user.id &&
                    user.id === transa.value.users[0].id
                ) {
                    return true;
                }
            }
            return true;
        };

        const deleteTransacao = async () => { // esta aqui e so substituir pela que estava
            try {
                if (auxDelete.value) {
                    await store.deleteOrGiveUp(transa.value.id, "fixa");
                    snackbar.value = "successReject";
                    loadPendentes();
                    loadPorPagar();
                    loadProximosPagamentos();
                } else {
                    await store.deleteOrGiveUp(
                        transa.value.id,
                        transa.value.transacao
                    );
                    snackbar.value = "successReject";//aqui sera outra cena
                    popupDetails.value = false;
                    loadPendentes();
                }
            } catch (err) {
                if (err.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                } else {
                    alert("Erro DeleteTransacao-> " + err.message);
                }
            }
        };

        //aceitar ou recusar
        const acceptorReject = async (state) => {
            try {
                await store.acceptorRejectPendente(transa.value.id, state);
                popupDetails.value = false;
                loadPendentes();
                loadPorPagar();
                loadProximosPagamentos();
            } catch (err) {
                if (err.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                } else {
                    alert("Erro acceptReject-> " + err.message);
                }
            }
        };

        //aceitar ou recusar
        const payTransaction = async () => {
            try {
                await store.payTransaction(transa.value.id, dateP.value);
                popupDetails.value = false;
                loadPendentes();
                loadPorPagar();
                loadProximosPagamentos();
                snackbar.value = "successAccept";
            } catch (err) {
                if (err.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                } else {
                    alert("Erro payTransaction-> " + err.message);
                }
            }
        };

        const popupKickInfo = (user) => {
            userKick.value = user;
        };

        const kickUser = async () => {
            try {
                if (transa.value.repeticao) {
                    await store.kickUser(userKick.value.email, transa.value.id, "fixa");
                } else {
                    await store.kickUser(userKick.value.email, transa.value.id, "unica");
                }

                await store.loadPendentes();
                await store.loadPorPagar();
                await store.loadProximosPagamentos();

                if(transa.value.users.length == 2 && transa.value.transacao == 'unica'){
                    popupKick.value = false;
                    popupDetails.value = false;
                    popup.value = false;
                } else if (transa.value.users.length == 2) {
                    openPopup(transa.value.id);
                    popupKick.value = false;
                } else {
                    openDetailsPopup(transa.value.id);
                    popupKick.value = false;
                }

            } catch (err) {
                popupKick.value = false;
                popupDetails.value = false;
                popup.value = false;

                //emitir evento de fechar modal

                if (err.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                } else {
                    alert("Erro kickUser-> " + err.message);
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
                if (popupDetails.value) {
                    openDetailsPopup(id);
                } else {
                    openPopup(id);
                }
            } catch (error) {
                if (error.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                } else {
                    alert("Erro ao adicionar comentario:", error.message);
                }
            }
        };

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
                    alert("Erro ao editar comentario:", error.message);
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
                    alert("Erro ao eliminar comentario:", error.message);
                }
            }
        };

        const openPopup = async (id) => {
            const t = store.proximosPagamentos.find((p) => p.id === id);
            dateP.value = t.date;

            const url =
                "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/fixa/" +
                t.tipo +
                "/get/" +
                t.id;
            const url_comments =
                "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/comment/list/" + t.id;
            const token = localStorage.getItem("token");

            const request = {
                method: "GET",
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

                // Mapear os dados recebidos para o formato de categorias desejado
                transa.value = {
                    id: data.id,
                    name: data.name,
                    descricao: data.descricao,
                    value: data.value,
                    local: data.local,
                    repeticao: data.repeticao,
                    tipo: data.tipo,
                    categoria: data.categoria,
                    users: data.users,
                    status: data.status,
                    shareValue: parseFloat(data.shareValue.toFixed(2)),
                    date: store.formatTimestampToDate(data.date),

                };



                const response_comments = await fetch(url_comments, request);

                if (!response_comments.ok) {
                    const errorData = await response_comments.json();
                    throw new Error(errorData.message);
                }

                const data_comments = await response_comments.json();
                console.log(data_comments);

                comments.value = data_comments.comentarios;



            } catch (error) {
                if (error.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                } else {
                    alert("Erro ao carregar transacao:", error.message);
                }
            }
            popupDetails.value = false;
            popup.value = true;
        };

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

        function validarEmailTransacao(email) {
            for (let i = 0; i < transa.value.users.length; i++) {
                if (transa.value.users[i].email === email) {
                    emailError.value = true;
                    emailErrorStore.value = `${t('Email já registado na transação!')}`;
                    isValid = false;
                    return;
                }
            }
        }

        const addUser = async () => {

            validarEmail(newUserEmail);

            if (isValid == true) {
                validarEmailTransacao(newUserEmail.value);
                if (isValid == true) {
                    try {
                        if (user.email == newUserEmail.value) {
                            emailErrorStore.value = `${t('Não pode partilhar despesas consigo mesmo!')}`;
                            emailError.value = true;
                            newUserEmail.value = '';
                            return;
                        }

                        if (transa.value.repeticao) {
                            await store.ShareTransactionWithUser(newUserEmail.value, transa.value.id, "fixa");
                        } else {
                            await store.ShareTransactionWithUser(newUserEmail.value, transa.value.id, "unica");
                        }

                        await store.loadPendentes();
                        await store.loadPorPagar();
                        await store.loadProximosPagamentos();

                        openDetailsPopup(transa.value.id);

                        newUserEmail.value = '';
                        emailError.value = null;
                    } catch (err) {
                        if (err.message.includes('token')) {
                            alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                            localStorage.clear();
                            sessionStorage.clear();

                            router.push('/sign-in');
                        } else if (err.message === "Email nao registado!") {
                            emailErrorStore.value = `${t('Email não registado na plataforma.')}`;
                            emailError.value = true;
                            alert("Não é possível partilhar despesas com utilizadores não registados!");
                            return;
                        } else if (err.message === "Email ja registado na transacao!") {
                            emailErrorStore.value = `${t('Email não registado na plataforma.')}`;
                            emailError.value = true;
                            alert("Não é possível partilhar despesas com utilizadores não registados!");
                            return;
                        } else {
                            alert("Erro ao adicionar User: " + err.message);
                        }
                    }
                }
            }
        }

        const closeSnackbar = () => {
            snackbar.value = false;
        };

        onMounted(() => {
            loadPendentes();
            loadPorPagar();
            loadProximosPagamentos();
        });

        return {
            snackbar,
            closeSnackbar,
            popup,
            transa_id,
            popupReject,
            popupAccept,
            popupDetails,
            popupReject2,
            popupAccept2,
            loadPendentes,
            loadPorPagar,
            getRepetitionText,
            store,
            user,
            popupKick,
            dateP,
            transa,
            comments,
            Comment,
            userKick,
            deleteTransacao,
            openDetailsPopup,
            acceptorRejectPendenteButton,
            acceptorReject,
            loadProximosPagamentos,
            payTransaction,
            openPopup,
            infos,
            participantes,
            leave,
            addComment,
            emailError,
            kickUser,
            typeKick,
            popupKickInfo,
            currentEditIndex,
            editComment,
            editCommentMode,
            saveComment,
            cancelEdit,
            deleteComment,
            addUser,
            validarEmailTransacao,
            newUserEmail,
            emailErrorStore,
            isEditing,
            categories,
            t,
            cancel,
            checkInputs,
            Name,
            nameError,
            Description,
            Value,
            valueError,
            Place,
            Type,
            Category,
            CategoryError,
            Recorrence,
            recorrenceError,
            Repetition,
            repetitionError,
            toggleToEdit,
            toggleToDelete,
            editTransaction,
            auxDelete,
        };
    },
    data() {
        return {
            isCategoryFocused: false,
            isRepetitionFocused: false,
        };
    },
    mounted() {
        document.addEventListener('click', this.handleClickOutsideCategory);
        document.addEventListener('click', this.handleClickOutsideType);
        document.addEventListener('click', this.handleClickOutsideRecorrence);
    },
    computed: {
        displayCategories() {
            if (this.Type == 'despesa') {
                return this.categories.CategoriesExpense;
            }
            else if (this.Type == 'receita') {
                return this.categories.CategoriesIncome;
            }
            else {
                return '';
            }
        },
    },
    watch: {
        Type() {
            this.Type = this.transa.tipo;
            this.isCategoryFocused = false;
        },
    },
    methods: {
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
        handleRepetitionFocus() {
            this.isRepetitionFocused = true;
        },
    }
};
</script>

<style scoped>
.modal {
    z-index: 9999;
}

.scroll-container {
    height: 77vh;
    overflow-y: auto;
}

.scroll-container::-webkit-scrollbar,
.scroll-container2::-webkit-scrollbar,
.scroll-container3::-webkit-scrollbar {
    width: 8px;
}

.scroll-container::-webkit-scrollbar-track,
.scroll-container2::-webkit-scrollbar-track,
.scroll-container3::-webkit-scrollbar-track {
    border-radius: 10px;
}

.scroll-container::-webkit-scrollbar-thumb,
.scroll-container2::-webkit-scrollbar-thumb,
.scroll-container3::-webkit-scrollbar-thumb {
    background: #888;
    border-radius: 10px;
}

.scroll-container2 {
    height: 32vh;
    overflow-y: auto;
}

.scroll-container3 {
    height: 70vh;
    overflow-y: auto;
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

.table-head-fixed {
    position: sticky;
    top: 0;
}

.small-button {
    padding: 0.3rem 0.6rem;
    font-size: 0.7rem;
}

.padding-row td {
    padding-top: 10px;
    padding-bottom: 10px;
}

.modal-body {
    overflow-y: auto;
    /* Ativa a rolagem vertical */
    max-height: 70vh;
    /* Altura máxima do corpo do modal */
}

.tab {
    display: none;
}

.tab.active {
    display: block;
}

.nav-pills .nav-link.active {
    color: #344767;
    background-color: #fff;
    animation: 0.2s ease;
}

.nav-link {
    display: block;
    padding: 0.5rem 1rem;
    transition:
        color 0.15s ease-in-out,
        background-color 0.15s ease-in-out,
        border-color 0.15s ease-in-out;
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

.required {
    display: inline;
    color: red;
    font-weight: 500;
    font-size: 12px;
}
</style>