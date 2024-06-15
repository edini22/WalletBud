<template>
  <div class="py-4 container-fluid">
    <div class="row mb-4">
      <div class="col-lg-12 position-relative z-index-2">
        <div class="row">
          <div class="col-lg-3 col-md-6 col-sm-6">
            <mini-statistics-card :title="{ text: $t('Saldo Atual'), value: user.saldo + ' €' }" :icon="{
                name: 'account_balance_wallet',
                color: 'text-white',
                background: 'success',
              }" />
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 mt-lg-0">
            <mini-statistics-card :title="{ text: $t('Objetivo Poupança'), value: user.objetivo + ' €' }"
              :detail="`<button class='btn mb-0 btn-sm null null btn-outline-primary' data-bs-toggle='modal' data-bs-target='#savingsGoalModal'>${$t('Definir Objetivo')}</button>`"
              :icon="{
                name: 'savings',
                color: 'text-white',
                background: 'primary',
              }" />
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 mt-lg-0 mt-4">
            <mini-statistics-card :title="{ text: $t('Movimentos') }"
              :detail="`<button class='btn mb-0 btn-sm null null bg-gradient-info' data-bs-toggle='modal' data-bs-target='#categoryModal'>${$t('Gerir Categorias')}</button>`"
              :icon="{
                name: 'edit_note',
                color: 'text-white',
                background: 'info',
              }" />
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 mt-lg-0 mt-4">
            <mini-statistics-card :title="{ text: $t('Movimentos') }"
              :detail="`<button class='btn mb-0 btn-sm null null bg-gradient-dark' data-bs-toggle='modal' data-bs-target='#transactionModal'>${$t('Adicionar Movimento')}</button>`"
              :icon="{
                name: 'post_add',
                color: 'text-white',
                background: 'dark',
              }" />
          </div>
        </div>
        <div class="table-responsive p-0 mt-4">
          <!--<div class="col-lg-4 col-md-6 mt-4">
            <chart-holder-card
              :title="$t('Gasto Semanal')"
              :subtitle="$t('Gasto por dia na semana passada')"
              week="14 a 21 abril"
            >
              <reports-bar-chart
                :chart="{
                  labels: [$t('Seg'), $t('Ter'), $t('Qua'), $t('Qui'), $t('Sex'), $t('Sáb'), $t('Dom')],
                  datasets: {
                    label: $t('Gasto Semanal'),
                    data: [100, 20, 10, 40, 50, 10, 40],
                  },
                }"
              />
            </chart-holder-card>
          </div>-->

          <!--<div class="col-lg-8 col-md-6 mb-md-0 mb-4 mt-4">-->
          <transaction-card />
          <!--</div>-->
        </div>
      </div>
    </div>
    <PopUpGerirCategorias :key="componentKey" @forceRerender="forceRerender" />
    <PopUpAddTransaction />
    <PopUpSavingsGoal />
    <div class="position-fixed bottom-1 end-1 z-index-991">
      <material-snackbar v-if="snackbar === 'success'" :title="$t('Movimento')" date="now"
        :description="$t('Movimento adicionado com sucesso!')" :icon="{ component: 'done', color: 'white' }"
        color="success" :close-handler="closeSnackbar" />
      <material-snackbar v-if="snackbar === 'error'" :title="$t('Movimento')" date="now"
        :description="$t('Não foi possível adicionar o movimento! Tente novamente.')"
        :icon="{ component: 'campaign', color: 'white' }" color="danger" :close-handler="closeSnackbar" />
    </div>
  </div>
</template>

<script>
import MiniStatisticsCard from "./components/MiniStatisticsCard.vue";
import TransactionCard from "./components/TransactionCard.vue";
import { userStore } from '@/store/userStore.js';
import PopUpGerirCategorias from "./components/PopUpGerirCategorias.vue";
import PopUpAddTransaction from "./components/PopUpAddTransaction.vue";
import PopUpSavingsGoal from "./components/PopUpSavingsGoal.vue";
import MaterialSnackbar from "@/components/MaterialSnackbar.vue";

export default {
  name: "home",
  data() {
    return {
      componentKey: 0,
      snackbar: null,
    };
  },
  mounted() {
    // Adiciona um listener para o evento customizado 'my-document-event'
    document.addEventListener('show-snackbar', this.showSnackbar);
    this.fetchUserData();
  },
  methods: {
    forceRerender() {
      this.componentKey += 1;
    },
    fetchUserData() {
      // Chamada para a action getUser da store
      this.user.getUser(); // Supondo que a action na store seja user.getUser
    },
    showSnackbar(event) {
      this.snackbar = event.detail;
    },
    closeSnackbar() {
      this.snackbar = null;
    },
  },
  components: {
    MiniStatisticsCard,
    TransactionCard,
    PopUpGerirCategorias,
    PopUpAddTransaction,
    PopUpSavingsGoal,
    MaterialSnackbar
  },
  computed: {
    user() {
      const uStore = userStore();
      return uStore;
    }
  },
};
</script>

<style>
.z-index-991{
  z-index: 991;
}
</style>