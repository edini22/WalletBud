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
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                        Despesa
                      </th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                        Periodicidade
                      </th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                        Próximo Pagamento
                      </th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                        Valor
                      </th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
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
                              <h6 class="mb-0 text-sm">{{ p.name }}</h6>
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
                          <span class="text-md font-weight-bold">{{ p.shareValue }}€</span>
                        </td>
                        <td>
                          <material-button variant="gradient" color="secondary" class="btn btn-sm small-button"
                            @click="openPopup(p.id, p.date)">
                            {{ $t("Abrir") }}
                          </material-button>
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
                          <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                            Despesa
                          </th>
                          <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                            Periodicidade
                          </th>
                          <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                            Data de Pagamento
                          </th>
                          <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
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
                                  <h6 class="mb-0 text-sm">{{ p.name }}</h6>
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
                              <span class="text-md font-weight-bold">{{ p.shareValue }}€</span>
                            </td>
                          </tr>
                        </template>
                        <template v-else>
                          <tr>
                            <td colspan="5" class="text-center">
                              <p class="message">
                                {{ $t("Sem pagamentos em atraso!") }}
                              </p>
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
                          <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                            Despesa
                          </th>
                          <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                            Periodicidade
                          </th>
                          <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                            Próximo Pagamento
                          </th>
                          <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                            Valor
                          </th>
                          <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
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
                                  <h6 class="mb-0 text-sm">{{ p.name }}</h6>
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
                              <span class="text-md font-weight-bold">{{ p.shareValue }}€</span>
                            </td>
                            <td>
                              <material-button variant="gradient" color="secondary" class="btn btn-sm small-button"
                                @click="openDetailsPopup(p.id)">
                                {{ $t("Detalhes") }}
                              </material-button>
                            </td>
                          </tr>
                        </template>
                        <template v-else>
                          <tr>
                            <td colspan="5" class="text-center">
                              <p class="message">
                                {{ $t("Sem pedidos pendentes!") }}
                              </p>
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
  <div v-if="popup" class="modal fade show" style="display: block">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header d-flex justify-content-between align-items-center">
          <h5 class="modal-title">Informações da Transacao</h5>
          <material-button variant="gradient" color="secondary" class="btn btn-sm small-button" @click="popup = false">
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
              <h6>Descrição: {{ transa.descricao }}</h6>
              <h6>Valor Total: {{ transa.value }}€</h6>
              <h6 v-if="transa.value != transa.shareValue">
                Valor Partilhado: {{ transa.shareValue }}€
              </h6>
              <h6>Periodicidade: {{ getRepetitionText(transa.repeticao) }}</h6>
              <h6>Data Criação: {{ transa.date }}</h6>
              <h6>Data: {{ dateP }}</h6>
              <h6>Categoria: {{ transa.categoria }}</h6>
              <h6>Status: {{ transa.status }}</h6>
              <h6>Tipo: {{ transa.tipo }}</h6>
              <h6>Local: {{ transa.local }}</h6>

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
                  <h6>
                    {{ c.user_email + " " + c.timestamp + " " + c.descricao }}
                  </h6>
                </div>
                <div class="row mb-3">
                  <div class="col-9">
                    <material-input class="material-input w-100" id="comment" type="text" :value="Comment"
                      :label="$t('Escreva o seu comentário')" @update:value="Comment = $event" />
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

            <div v-if="participantes">
              <div v-for="(u, index) in transa.users" :key="index">
                <div class="d-flex align-items-center justify-content-between">
                  <div>
                    <h6>Utilizador: {{ u.name }}</h6>
                    <h6>Email: {{ u.email }}</h6>
                    <h6 v-if="u.confirma === 1">Estado: Aceite</h6>
                    <h6 v-if="u.confirma === 0">Estado: Em espera</h6>
                  </div>
                  <div v-if="transa.users[0].id === user.id">
                    <div>
                      <!--remove user-->
                      <material-button v-if="transa.users[0].id != u.id" variant="gradient" color="danger"
                        class="btn btn-sm small-button" @click="
                          popupKickInfo(u);
                        popupKick = true;
                        popup = false;
                        typeKick = 0;
                        ">
                        {{ $t("Remover") }}
                      </material-button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- Shared expense -->
              <div v-if="transa.users[0].id === user.id">
                <hr class="horizontal dark my-sm-4" />
                <div class="form-group">
                  <label for="sharedExpense" class="form-label mb-3">{{ $t('Adicionar novos membros:')
                    }}</label>
                  <div class="form-input mb-3">
                    <div v-if="emailError === true && emailErrorStore === null" class="mb-3">
                      <material-input class="material-input mb-3" id="email" type="email"
                        :label="$t('Indique o email do utilizador')" name="email" :value="newUserEmail"
                        @update:value="newUserEmail = $event" error />
                    </div>

                    <div v-if="emailError === true && emailErrorStore !== null" class="mb-3">
                      <material-input class="material-input mb-3" id="email" type="email" :label=emailErrorStore
                        name="email" :value="newUserEmail" @update:value="newUserEmail = $event" error />
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

                    <p class="btn btn-default bg-gradient-info mb-1" @click="addUser">{{ $t('Adicionar Utilizador') }}
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div v-if="transa && transa.users && transa.users.length > 0">
          <div v-if="transa.users[0].id == user.id" class="modal-footer d-flex justify-content-between">
            <material-button variant="gradient" color="danger" class="btn btn-md" @click="
              popupReject = true;
            popup = false;
            ">
              {{ $t("Eliminar") }}
            </material-button>
            <material-button variant="gradient" color="info" class="btn btn-md" @click="
              popupAccept = true;
            popup = false;
            ">
              {{ $t("Pagar") }}
            </material-button>
          </div>
          <div v-else class="modal-footer d-flex justify-content-end">
            <material-button variant="gradient" color="danger" class="btn btn-md" @click="
              popupReject = true;
            popup = false;
            leave = true;
            ">
              {{ $t("Sair") }}
            </material-button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!--PopUp Eliminar-->
  <div v-if="popupReject" class="modal fade show" style="display: block">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header d-flex justify-content-between align-items-center">
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
          <material-button variant="gradient" color="secondary" class="btn btn-md" @click="
            popupReject = false;
          popup = true;
          leave = false;
          ">
            {{ $t("Voltar") }}
          </material-button>
          <material-button variant="gradient" color="info" class="btn btn-md" @click="
            deleteTransacao();
          popupReject = false;
          popup = false;
          leave = false;
          ">
            {{ $t("Confirmar") }}
          </material-button>
        </div>
      </div>
    </div>
  </div>

  <!--PopUp Aceitar-->
  <div v-if="popupAccept" class="modal fade show" style="display: block">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header d-flex justify-content-between align-items-center">
          <h5 class="modal-title">Deseja proceder com o pagamento?</h5>
        </div>
        <div class="modal-body text-center">
          <p>A sua ação é irreversível!</p>
        </div>
        <div class="modal-footer d-flex justify-content-between">
          <material-button variant="gradient" color="secondary" class="btn btn-md" @click="
            popupAccept = false;
          popup = true;
          ">
            {{ $t("Voltar") }}
          </material-button>
          <material-button variant="gradient" color="info" class="btn btn-md" @click="
            payTransaction();
          popupAccept = false;
          popup = false;
          ">
            {{ $t("Confirmar") }}
          </material-button>
        </div>
      </div>
    </div>
  </div>

  <!--PopUp Detalhes-->
  <div v-if="popupDetails" class="modal fade show" style="display: block">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header d-flex justify-content-between align-items-center">
          <h5 class="modal-title">Informações do pedido de adesão</h5>
          <material-button variant="gradient" color="secondary" class="btn btn-sm small-button"
            @click="popupDetails = false">
            {{ $t("Voltar") }}
          </material-button>
        </div>
        <div class="modal-body">
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
          <div v-if="infos">
            <h6>Descrição: {{ selectedPendente.name }}</h6>
            <h6>Valor Total: {{ selectedPendente.value }}€</h6>
            <h6 v-if="selectedPendente.value != selectedPendente.shareValue">
              Valor Partilhado: {{ selectedPendente.shareValue }}€
            </h6>
            <h6>
              Periodicidade: {{ getRepetitionText(selectedPendente.repeticao) }}
            </h6>
            <h6>Data: {{ selectedPendente.date }}</h6>
            <h6>Categoria: {{ selectedPendente.categoria }}</h6>
            <h6>Status: {{ selectedPendente.status }}</h6>
            <h6>Tipo: {{ selectedPendente.tipo }}</h6>
            <h6>Local: {{ selectedPendente.local }}</h6>
          </div>
          <div v-if="participantes">
            <div v-for="(u, index) in selectedPendente.users" :key="index">
              <div class="d-flex align-items-center justify-content-between">
                <div>
                  <h6>Utilizador: {{ u.name }}</h6>
                  <h6>Email: {{ u.email }}</h6>
                  <h6 v-if="u.confirma === 1">Estado: Aceite</h6>
                  <h6 v-if="u.confirma === 0">Estado: Em espera</h6>
                </div>
                <div v-if="selectedPendente.users[0].id === user.id">
                  <div>
                    <!--remove user-->
                    <material-button v-if="selectedPendente.users[0].id != u.id" variant="gradient" color="danger"
                      class="btn btn-sm small-button" @click="
                        popupKickInfo(u);
                      popupKick = true;
                      popupDetails = false;
                      typeKick = 1;
                      ">
                      {{ $t("Remover") }}
                    </material-button>
                  </div>
                </div>
              </div>
            </div>
            <!-- Shared expense -->
            <div v-if="selectedPendente.users[0].id === user.id">
              <hr class="horizontal dark my-sm-4" />
              <div class="form-group">
                <label for="sharedExpense" class="form-label mb-3">{{ $t('Adicionar novos membros:')
                  }}</label>
                <div class="form-input mb-3">
                  <div v-if="emailError === true && emailErrorStore === null" class="mb-3">
                    <material-input class="material-input mb-3" id="email" type="email"
                      :label="$t('Indique o email do utilizador')" name="email" :value="newUserEmail"
                      @update:value="newUserEmail = $event" error />
                  </div>

                  <div v-if="emailError === true && emailErrorStore !== null" class="mb-3">
                    <material-input class="material-input mb-3" id="email" type="email" :label=emailErrorStore
                      name="email" :value="newUserEmail" @update:value="newUserEmail = $event" error />
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
            </div>
          </div>

        </div>
        <div>

          <div v-if="acceptorRejectPendenteButton() === true">
            <div v-if="selectedPendente.users[0].id != user.id" class="modal-footer d-flex justify-content-between">
              <material-button variant="gradient" color="danger" class="btn btn-md" @click="
                popupReject2 = true;
              popupDetails = false;
              ">
                {{ $t("Rejeitar") }}
              </material-button>
              <material-button variant="gradient" color="info" class="btn btn-md" @click="
                popupAccept2 = true;
              popupDetails = false;
              ">
                {{ $t("Aceitar") }}
              </material-button>
            </div>
            <div v-else class="modal-footer d-flex justify-content-end">
              <material-button variant="gradient" color="danger" class="btn btn-md" @click="deletePendente()">
                {{ $t("Eliminar") }}
              </material-button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!--PopUp Detalhes Rejeitar-->
  <div v-if="popupReject2" class="modal fade show" style="display: block">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header d-flex justify-content-between align-items-center">
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
          ">
            {{ $t("Voltar") }}
          </material-button>
          <material-button variant="gradient" color="info" class="btn btn-md" @click="
            acceptorReject(-1);
          popupReject2 = false;
          snackbar = 'successReject2';
          popupDetails = false;
          ">
            {{ $t("Confirmar") }}
          </material-button>
        </div>
      </div>
    </div>
  </div>

  <!--PopUp Detalhes Aceitar-->
  <div v-if="popupAccept2" class="modal fade show" style="display: block">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header d-flex justify-content-between align-items-center">
          <h5 class="modal-title">Deseja proceder a entrada no grupo?</h5>
        </div>
        <div class="modal-body text-center">
          <p>A sua ação é irreversível!</p>
        </div>
        <div class="modal-footer d-flex justify-content-between">
          <material-button variant="gradient" color="secondary" class="btn btn-md" @click="
            popupAccept2 = false;
          popupDetails = true;
          ">
            {{ $t("Voltar") }}
          </material-button>
          <material-button variant="gradient" color="info" class="btn btn-md" @click="
            acceptorReject(1);
          popupAccept2 = false;
          snackbar = 'successAccept2';
          popupDetails = false;
          ">
            {{ $t("Confirmar") }}
          </material-button>
        </div>
      </div>
    </div>
  </div>

  <!--PopUp Detalhes Aceitar-->
  <div v-if="popupKick" class="modal fade show" style="display: block">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header d-flex justify-content-between align-items-center">
          <h5 class="modal-title">Deseja proceder a expulsao do elemento {{ userKick.name }}?</h5>
        </div>
        <div class="modal-body text-center">
          <p>A sua ação é irreversível!</p>
        </div>
        <div class="modal-footer d-flex justify-content-between">
          <div v-if="typeKick === 1">
            <material-button variant="gradient" color="secondary" class="btn btn-md" @click="
              popupKick = false;
              popupDetails = true;
            ">
              {{ $t("Voltar") }}
            </material-button>
          </div>
          <div v-else>
            <material-button variant="gradient" color="secondary" class="btn btn-md" @click="
              popupKick = false;
              popup = true;
            ">
              {{ $t("Voltar") }}
            </material-button>
          </div>
          <material-button variant="gradient" color="danger" class="btn btn-sm small-button" @click="kickUser()">
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
      description="Adesão a grupo efetuada com sucesso!" :icon="{ component: 'done', color: 'white' }" color="success"
      :close-handler="closeSnackbar" />

    <material-snackbar v-if="snackbar === 'successReject2'" title="Adesão" date="now"
      description="Adesão a grupo rejeitada com sucesso!" :icon="{ component: 'done', color: 'white' }" color="success"
      :close-handler="closeSnackbar" />
  </div>
</template>

<script>
import MaterialButton from "@/components/MaterialButton.vue";
import MaterialSnackbar from "@/components/MaterialSnackbar.vue";
import MaterialInput from "@/components/MaterialInput.vue";
import { ref, computed, onMounted } from "vue";
import { fixaStore } from "@/store/fixaStore";
import { userStore } from "@/store/userStore";

export default {
  name: "payments",
  components: {
    MaterialButton,
    MaterialSnackbar,
    MaterialInput,
  },
  setup() {
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
    const selectedPendenteId = ref(null);
    const transa = ref(null);
    const dateP = ref(null);
    const comments = ref(null);
    const Comment = ref(null);

    const emailError = ref(null);

    function validarEmail(email) {
      const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

      if (!emailRegex.test(email.value)) {
        emailError.value = true;
      } else {
        emailError.value = false;
      }
    }

    //validar e adicionar utilizador


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
        alert("Erro loadPorPagar-> " + err.message);
      }
    };
    const loadProximosPagamentos = async () => {
      try {
        await store.loadProximosPagamentos();
        console.log("Proximos pagamentos :", store.proximosPagamentos);
      } catch (err) {
        alert("Erro loadProximosPagamentos-> " + err.message);
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

    const openDetailsPopup = (id) => {
      selectedPendenteId.value = id;
      popupDetails.value = true;
    };

    const selectedPendente = computed(() => {
      return (
        store.pendentes.find((p) => p.id === selectedPendenteId.value) || {}
      );
    });

    const acceptorRejectPendenteButton = () => {
      for (let i = 0; i < selectedPendente.value.users.length; i++) {
        if (
          selectedPendente.value.users[i].id === user.id &&
          user.id != selectedPendente.value.users[0].id
        ) {
          if (!selectedPendente.value.users[i].confirma) {
            return true;
          } else {
            return false;
          }
        } else if (
          selectedPendente.value.users[i].id === user.id &&
          user.id === selectedPendente.value.users[0].id
        ) {
          return true;
        }
      }
      return true;
    };

    //eliminar
    const deletePendente = async () => {
      try {
        await store.deleteOrGiveUp(
          selectedPendenteId.value,
          selectedPendente.value.transacao
        );
        popupDetails.value = false;
        loadPendentes();
      } catch (err) {
        alert("Erro DeletePendente-> " + err.message);
      }
    };

    const deleteTransacao = async () => {
      try {
        await store.deleteOrGiveUp(transa.value.id, "fixa");
        snackbar.value = "successReject";
        loadPendentes();
        loadPorPagar();
        loadProximosPagamentos();
      } catch (err) {
        alert("Erro DeleteTransacao-> " + err.message);
        // snackbar.value = 'failureReject'; //TODO: VER ISTO
      }
    };

    //aceitar ou recusar
    const acceptorReject = async (state) => {
      try {
        await store.acceptorRejectPendente(selectedPendenteId.value, state);
        popupDetails.value = false;
        loadPendentes();
        loadPorPagar();
        loadProximosPagamentos();
      } catch (err) {
        alert("Erro acceptReject-> " + err.message);
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
        alert("Erro payTransaction-> " + err.message);
      }
    };

    const popupKickInfo = (user) => {
      userKick.value = user;
      selectedPendenteId.value = transa.value.id;
    };

    const kickUser = async () => {
      try {
        await store.kickUser(userKick.value.email, selectedPendenteId.value);
        popupDetails.value = false;
        loadPendentes();
        loadPorPagar();
        loadProximosPagamentos();
      } catch (err) {
        alert("Erro kickUser-> " + err.message);
      }
    };

    const addComment = async (id) => {
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

        openPopup(id, dateP.value);
      } catch (error) {
        alert("Erro ao adicionar comentario:", error.message);
      }
    };

    const openPopup = async (id, data) => {
      dateP.value = data;
      popup.value = true;
      const t = store.proximosPagamentos.find((p) => p.id === id);

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
        transa.value = data;

        const response_comments = await fetch(url_comments, request);

        if (!response_comments.ok) {
          const errorData = await response_comments.json();
          throw new Error(errorData.message);
        }

        const data_comments = await response_comments.json();
        console.log(data_comments);

        comments.value = data_comments.comentarios;
      } catch (error) {
        alert("Erro ao carregar transacao:", error.message);
      }
    };

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
      selectedPendenteId,
      openDetailsPopup,
      selectedPendente,
      acceptorRejectPendenteButton,
      deletePendente,
      acceptorReject,
      loadProximosPagamentos,
      payTransaction,
      openPopup,
      infos,
      participantes,
      leave,
      addComment,
      emailError,
      validarEmail,
      kickUser,
      typeKick,
      popupKickInfo,
    };
  },
};
</script>

<style scoped>
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
  background-color: white;
}

.small-button {
  padding: 0.3rem 0.6rem;
  font-size: 0.7rem;
}

.padding-row td {
  padding-top: 10px;
  padding-bottom: 10px;
}

.modal {
  display: none;
  position: fixed;
  z-index: 1050;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
  outline: 0;
  background-color: rgba(0, 0, 0, 0.5);
  transition-duration: 0.2s;
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

.nav-pills .nav-link.active,
.nav-pills .show>.nav-link {
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
</style>
