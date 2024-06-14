<template>
    <div class="container-fluid">
        <div class="row">
            <div class="col">
                <!--timeline next days-->
                <div class = "col pt-0">
                    <div class="card my-4">
                        <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                            <div class="bg-gradient-info shadow-info border-radius-lg pt-3 pb-2">
                                <h6 class="text-white text-capitalize ps-3">
                                    Transações Fixas - Pagamentos
                                </h6>
                            </div>
                        </div>
                        <div class=" px-0 pb-2">
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
                                        <tr v-for="n in 20" :key="n" class="padding-row">
                                            <td>
                                                <div class="d-flex px-3">
                                                    <div class="my-auto">
                                                        <h6 class="mb-0 text-sm">
                                                            Github
                                                        </h6>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <p class="text-xs font-weight-bold mb-0">
                                                    Anual
                                                </p>
                                            </td>
                                            <td>
                                                <span class="text-xs font-weight-bold">
                                                    20/06/2024
                                                </span>
                                            </td>
                                            <td>
                                                <span class="text-md font-weight-bold">
                                                    13.9€
                                                </span>
                                            </td>
                                            <td>
                                                <material-button
                                                    variant="gradient"
                                                    color="secondary"
                                                    class="btn btn-sm small-button"
                                                    @click="popup = true">
                                                    {{$t('Abrir')}}
                                                </material-button>

                                                
                                            </td>
                                        </tr>
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
                                        <h6 class="text-white text-capitalize ps-3">
                                            Por Pagar
                                        </h6>
                                    </div>
                                </div>
                                <div class=" px-0 pb-2">
                                    <div class="table-responsive p-0 scroll-container2">
                                        <table class="table align-items-center justify-content-center mb-0 ">
                                            <thead class="table-head-fixed">
                                                <tr>
                                                  <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Despesa</th>
                                                  <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Periodicidade</th>
                                                  <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Data de Pagamento</th>
                                                  <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Valor</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                              <template v-if="store && store.porPagar && store.porPagar.length > 0">
                                                <tr v-for="(p, index) in store.porPagar" :key="index">
                                                  <td>
                                                  <div class="d-flex px-3">
                                                      <div class="my-auto">
                                                      <h6 class="mb-0 text-sm">{{ p.name }}</h6>
                                                      </div>
                                                  </div>
                                                  </td>
                                                  <td>
                                                    <p class="text-xs font-weight-bold mb-0">{{ getRepetitionText(p.repeticao) }}</p>
                                                  </td>
                                                  <td>
                                                    <span class="text-xs font-weight-bold">{{ p.date }}</span>
                                                  </td>
                                                  <td>
                                                    <span class="text-md font-weight-bold">{{ p.shareValue }}€</span>
                                                  </td>
                                                </tr>
                                              </template>
                                              <template v-else>
                                                <tr>
                                                  <td colspan="5" class="text-center">
                                                    <p class="message">{{ $t('Sem pagamentos em atraso!') }}</p>
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
                                <div class=" px-0 pb-2">
                                  <div class="table-responsive p-0 scroll-container2">
                                    <table class="table align-items-center justify-content-center mb-0">
                                      <thead class="table-head-fixed">
                                        <tr>
                                          <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Despesa</th>
                                          <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Periodicidade</th>
                                          <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Próximo Pagamento</th>
                                          <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Valor</th>
                                          <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Detalhes</th>
                                        </tr>
                                      </thead>
                                      <tbody>
                                        <template v-if="store && store.pendentes && store.pendentes.length > 0">
                                          <tr v-for="(p, index) in store.pendentes" :key="index">
                                            <td>
                                              <div class="d-flex px-3">
                                                <div class="my-auto">
                                                  <h6 class="mb-0 text-sm">{{ p.name }}</h6>
                                                </div>
                                              </div>
                                            </td>
                                            <td>
                                              <p class="text-xs font-weight-bold mb-0">{{ getRepetitionText(p.repeticao) }}</p>
                                            </td>
                                            <td>
                                              <span class="text-xs font-weight-bold">{{ p.date }}</span>
                                            </td>
                                            <td>
                                              <span class="text-md font-weight-bold">{{ p.shareValue }}€</span>
                                            </td>
                                            <td>
                                              <material-button
                                                variant="gradient"
                                                color="secondary"
                                                class="btn btn-sm small-button"
                                                @click="openDetailsPopup(p.id)"
                                              >
                                                {{ $t('Detalhes') }}
                                              </material-button>
                                            </td>
                                          </tr>
                                        </template>
                                        <template v-else>
                                          <tr>
                                            <td colspan="5" class="text-center">
                                              <p class="message">{{ $t('Sem pedidos pendentes!') }}</p>
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
    <div v-if="popup" class="modal fade show"   style="display: block;">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header d-flex justify-content-between align-items-center">
                    <h5 class="modal-title">Deseja proceder com o pagamento?</h5>
                    <material-button
                        variant="gradient"
                        color="secondary"
                        class="btn btn-sm small-button"
                        @click="popup = false">
                        {{$t('Voltar')}}
                    </material-button>
                </div>
                <div class="modal-body">
                    <h6>Descrição:</h6>
                    <h6>Valor: </h6>
                    <h6>Data expiração: </h6>
                </div>
                <div class="modal-footer d-flex justify-content-between">
                    <material-button
                        variant="gradient"
                        color="danger"
                        class="btn btn-md "
                        @click="popupReject = true; popup = false">
                        {{$t('Eliminar')}}
                    </material-button>
                    <material-button
                        variant="gradient"
                        color="info"
                        class="btn btn-md "
                        @click="popupAccept = true; popup = false">
                        {{$t('Pagar')}}
                    </material-button>
                </div>
            </div>
        </div>
    </div>

    <!--PopUp Rejeitar-->
    <div v-if="popupReject" class="modal fade show" style="display: block;">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header d-flex justify-content-between align-items-center">
                    <h5 class="modal-title">Tem a certeza que deseja eliminar o grupo?</h5>
                </div>
                <div class="modal-body text-center">
                    <p>A sua ação é irreversível!</p>
                </div>
                <div class="modal-footer d-flex justify-content-between">
                    <material-button
                        variant="gradient"
                        color="secondary"
                        class="btn btn-md "
                        @click="popupReject = false; popup = true">
                        {{$t('Voltar')}}
                    </material-button>
                    <material-button
                        variant="gradient"
                        color="info"
                        class="btn btn-md "
                        @click= "popupReject = false; snackbar = 'successReject'; popup = false;">
                        {{$t('Confirmar')}}
                    </material-button>
                </div>
            </div>
        </div>
    </div>

    <!--PopUp Aceitar-->
    <div v-if="popupAccept" class="modal fade show" style="display: block;">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header d-flex justify-content-between align-items-center">
                    <h5 class="modal-title">Deseja proceder com o pagamento?</h5>
                </div>
                <div class="modal-body text-center">
                    <p>A sua ação é irreversível!</p>
                </div>
                <div class="modal-footer d-flex justify-content-between">
                    <material-button
                        variant="gradient"
                        color="secondary"
                        class="btn btn-md "
                        @click="popupAccept = false; popup = true">
                        {{$t('Voltar')}}
                    </material-button>
                    <material-button
                        variant="gradient"
                        color="info"
                        class="btn btn-md "
                        @click= "popupAccept = false; snackbar = 'successAccept'; popup = false;">
                        {{$t('Confirmar')}}
                    </material-button>
                </div>
            </div>
        </div>
    </div>

    <!--PopUp Detalhes-->
    <div v-if="popupDetails" class="modal fade show"   style="display: block;">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header d-flex justify-content-between align-items-center">
                    <h5 class="modal-title">Informações do pedido de adesão</h5>
                    <material-button
                        variant="gradient"
                        color="secondary"
                        class="btn btn-sm small-button"
                        @click="popupDetails = false">
                        {{$t('Voltar')}}
                    </material-button>
                </div>
                <div class="modal-body">
                  <h6>Descrição: {{ selectedPendente.descricao }}</h6>
                  <h6>Valor Total: {{ selectedPendente.value }}€</h6>
                  <h6 v-if="selectedPendente.value != selectedPendente.shareValue">Valor Partilhado: {{ selectedPendente.shareValue }}€</h6>
                  <h6>Periodicidade: {{ getRepetitionText(selectedPendente.repeticao) }}</h6>
                  <h6>Data: {{ selectedPendente.date }}</h6>
                  <h6>Categoria: {{ selectedPendente.categoria }}</h6>
                  <h6>Status: {{ selectedPendente.status }}</h6>
                  <h6>Tipo: {{ selectedPendente.tipo }}</h6>
                  <h6>Local: {{ selectedPendente.local }}</h6>
                  <h6>Utilizadores: {{ selectedPendente.users }}</h6>
                </div>
                <div v-if="acceptorRejectPendenteButton() === true" class="modal-footer d-flex justify-content-between">
                    <div v-if="selectedPendente.users[0].id != user.id">
                      <material-button
                          variant="gradient"
                          color="danger"
                          class="btn btn-md "
                          @click="popupReject2 = true; popupDetails = false">
                          {{$t('Rejeitar')}}
                      </material-button>
                      <material-button 
                          variant="gradient"
                          color="info"
                          class="btn btn-md "
                          @click="popupAccept2 = true; popupDetails = false">
                          {{$t('Aceitar')}}
                      </material-button>
                    </div>
                    <div v-else>
                      <material-button
                          variant="gradient"
                          color="danger"
                          class="btn btn-md "
                          @click="deletePendente()">
                          {{$t('Eliminar')}}
                      </material-button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--PopUp Detalhes Rejeitar-->
    <div v-if="popupReject2" class="modal fade show" style="display: block;">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header d-flex justify-content-between align-items-center">
                    <h5 class="modal-title">Tem a certeza que não deseja participar no grupo?</h5>
                </div>
                <div class="modal-body text-center">
                    <p>A sua ação é irreversível!</p>
                </div>
                <div class="modal-footer d-flex justify-content-between">
                    <material-button
                        variant="gradient"
                        color="secondary"
                        class="btn btn-md "
                        @click="popupReject2 = false; popupDetails = true">
                        {{$t('Voltar')}}
                    </material-button>
                    <material-button
                        variant="gradient"
                        color="info"
                        class="btn btn-md "
                        @click= "acceptorReject(-1);popupReject2 = false; snackbar = 'successReject2'; popupDetails = false;">
                        {{$t('Confirmar')}}
                    </material-button>
                </div>
            </div>
        </div>
    </div>

    <!--PopUp Detalhes Aceitar-->
    <div v-if="popupAccept2" class="modal fade show" style="display: block;">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header d-flex justify-content-between align-items-center">
                    <h5 class="modal-title">Deseja proceder a entrada no grupo?</h5>
                </div>
                <div class="modal-body text-center">
                    <p>A sua ação é irreversível!</p>
                </div>
                <div class="modal-footer d-flex justify-content-between">
                    <material-button
                        variant="gradient"
                        color="secondary"
                        class="btn btn-md "
                        @click="popupAccept2 = false; popupDetails = true">
                        {{$t('Voltar')}}
                    </material-button>
                    <material-button
                        variant="gradient"
                        color="info"
                        class="btn btn-md "
                        @click= "acceptorReject(1);popupAccept2 = false; snackbar = 'successAccept2'; popupDetails = false;">
                        {{$t('Confirmar')}}
                    </material-button>
                </div>
            </div>
        </div>
    </div>


    <div class="position-fixed top-1 end-1 z-index-2">
        <material-snackbar
            v-if="snackbar === 'successReject'"
            title="Pagamento"
            date="now"
            description="Grupo de Pagamento removido com sucesso!"
            :icon="{ component: 'done', color: 'white' }"
            color="success"
            :close-handler="closeSnackbar"
        />

        <material-snackbar
            v-if="snackbar === 'successAccept'"
            title="Pagamento"
            date="now"
            description="Pagamento efetuado com sucesso!"
            :icon="{ component: 'done', color: 'white' }"
            color="success"
            :close-handler="closeSnackbar"
        />

        <material-snackbar
            v-if="snackbar === 'successAccept2'"
            title="Adesão"
            date="now"
            description="Adesão a grupo efetuada com sucesso!"
            :icon="{ component: 'done', color: 'white' }"
            color="success"
            :close-handler="closeSnackbar"
        />

        <material-snackbar
            v-if="snackbar === 'successReject2'"
            title="Adesão"
            date="now"
            description="Adesão a grupo rejeitada com sucesso!"
            :icon="{ component: 'done', color: 'white' }"
            color="success"
            :close-handler="closeSnackbar"
        />
    </div>
</template>
  
<script>
import MaterialButton from "@/components/MaterialButton.vue";
import MaterialSnackbar from "@/components/MaterialSnackbar.vue";
import { ref, computed, onMounted } from 'vue';
import { fixaStore } from "@/store/fixaStore";
import { userStore } from "@/store/userStore";

export default {
  name: "payments",
  components: {
    MaterialButton,
    MaterialSnackbar,
  },
  setup() {
    const snackbar = ref(false);
    const popup = ref(false);
    const popupReject = ref(false);
    const popupAccept = ref(false);
    const popupDetails = ref(false);
    const popupReject2 = ref(false);
    const popupAccept2 = ref(false);
    const store = fixaStore();
    const user = userStore();
    const selectedPendenteId = ref(null);

    const loadPendentes = async () => {
      try {
        await store.loadPendentes();
        console.log("Pendentes carregados:", store.pendentes);
      } catch (err) {
        alert("Erro -> " + err.message);
      }
    };

    const loadPorPagar = async () => {
      try {
        await store.loadPorPagar();
        console.log("Por pagar carregados:", store.porPagar);
      } catch (err) {
        alert("Erro -> " + err.message);
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
      return store.pendentes.find(p => p.id === selectedPendenteId.value) || {};
    });

    const acceptorRejectPendenteButton = () => {
      for (let i = 0; i < selectedPendente.value.users.length; i++) {
        if (selectedPendente.value.users[i].id === user.id && user.id != selectedPendente.value.users[0].id ) {
          if(!selectedPendente.value.users[i].confirma){
            return true;
          } else {
              return false;
          }
        } else if (selectedPendente.value.users[i].id === user.id && user.id === selectedPendente.value.users[0].id) {
          return true;
        }
      }
      return true;
    };

    //eliminar
    const deletePendente = async () => {
      try {
        await store.deletePendente(selectedPendenteId.value);
        popupDetails.value = false;
        alert("Pedido eliminado com sucesso!");
        loadPendentes();
        
      } catch (err) {
        alert("Erro -> " + err.message);
      }
    };

    //aceitar ou recusar
    const acceptorReject = async (state) => {
      try {
        await store.acceptorRejectPendente(selectedPendenteId.value,state);
        popupDetails.value = false;
        loadPendentes();
      } catch (err) {
        alert("Erro -> " + err.message);
      }
    };


    const closeSnackbar = () => {
      snackbar.value = false;
    };

    onMounted(() => {
      // const modal = document.getElementById('categoryModal');
      // modal.addEventListener('hidden.bs.modal', forceRerender);
      
      loadPendentes();
      loadPorPagar(); //TODO: fazer isto tambem quando se pagar algo ao lado!!
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
      selectedPendenteId,
      openDetailsPopup,
      selectedPendente,
      acceptorRejectPendenteButton,
      deletePendente,
      acceptorReject,
    };
  },
};
</script>
  
<style scoped>
    .scroll-container {
      max-height: 77vh; 
      overflow-y: auto;
    }
  
    .scroll-container::-webkit-scrollbar, .scroll-container2::-webkit-scrollbar{
        width: 8px;
    }
    
    .scroll-container::-webkit-scrollbar-track, .scroll-container2::-webkit-scrollbar-track{
        border-radius: 10px;
    }
    
    .scroll-container::-webkit-scrollbar-thumb, .scroll-container2::-webkit-scrollbar-thumb{
        background: #888;
        border-radius: 10px;
    }
  
    .scroll-container2 {
      max-height: 32vh;
      overflow-y: auto;
    }
  
    .table-head-fixed {
      position: sticky;
      top: 0;
      background-color: white;
    }

    .small-button {
        padding: 0.30rem 0.6rem;
        font-size: 0.70rem; 
    }

    .padding-row td{
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
    }
</style>