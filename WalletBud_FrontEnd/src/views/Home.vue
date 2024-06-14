<template>
  <div class="py-4 container-fluid">
    <div class="row mb-4">
      <div class="col-lg-12 position-relative z-index-2">
        <div class="row">
          <div class="col-lg-3 col-md-6 col-sm-6">
            <mini-statistics-card
              :title="{ text: 'Saldo Atual', value: user.saldo + ' €' }"
              :icon="{
                name: 'account_balance_wallet',
                color: 'text-white',
                background: 'success',
              }"
            />
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 mt-lg-0">
            <mini-statistics-card
              :title="{ text: 'Objetivo Poupança', value: '2,300' }"
              detail="<button class='btn mb-0 btn-sm null null btn-outline-primary' data-bs-toggle='modal' data-bs-target='#categoryModal'>Definir objetivo</button>"
              :icon="{
                name: 'savings',
                color: 'text-white',
                background: 'primary',
              }"
            />
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 mt-lg-0 mt-4">
            <mini-statistics-card
              :title="{ text: 'Movimentos' }"
              detail="<button class='btn mb-0 btn-sm null null bg-gradient-info' data-bs-toggle='modal' data-bs-target='#categoryModal'>Gerir Categorias</button>"
              :icon="{
                name: 'edit_note',
                color: 'text-white',
                background: 'info',
              }"
            />
            
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 mt-lg-0 mt-4">
            <mini-statistics-card
              :title="{ text: 'Movimentos' }"
              detail="<button class='btn mb-0 btn-sm null null bg-gradient-dark' data-bs-toggle='modal' data-bs-target='#transactionModal'>Adicionar Movimento</button>"
              :icon="{
                name: 'post_add',
                color: 'text-white',
                background: 'dark',
              }"
            />
          </div>
        </div>
        <div class="table-responsive p-0 mt-4">
          <!--<div class="col-lg-4 col-md-6 mt-4">
            <chart-holder-card
              title="Gasto Semanal"
              subtitle="Gasto por dia na semana passada"
              week="14 a 21 abril"
            >
              <reports-bar-chart
                :chart="{
                  labels: ['Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab', 'Dom'],
                  datasets: {
                    label: 'Gasto Semanal',
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
    <PopUpGerirCategorias :key="componentKey" @forceRerender="forceRerender"/>
    <PopUpAddTransaction />
  </div>
</template>

<script>
import MiniStatisticsCard from "./components/MiniStatisticsCard.vue";
import TransactionCard from "./components/TransactionCard.vue";
import { userStore } from '@/store/userStore.js';
import PopUpGerirCategorias from "./components/PopUpGerirCategorias.vue";
import PopUpAddTransaction from "./components/PopUpAddTransaction.vue";

export default {
  name: "home",
  data() {
    return {
      componentKey: 0,
    };
  },
  methods: {
    forceRerender() {
      this.componentKey += 1;
    },
    fetchUserData() {
      // Chamada para a action getUser da store
      this.user.getUser(); // Supondo que a action na store seja user.getUser
    }
  },
  components: {
    MiniStatisticsCard,
    TransactionCard,
    PopUpGerirCategorias,
    PopUpAddTransaction
  },
  computed: {
    user() {
      const uStore = userStore();
      return uStore;
    }
  },
  // computed: {
  //   user() {
  //     return this.$store.state.user; // Acesse o estado do usuário diretamente
  //   }
  // },
  mounted() {
  //   // Assim que o componente for montado, verifique se o usuário está carregado
  //   // if (!this.user.loaded) {
      this.fetchUserData(); // Chame a função para carregar os dados do usuário
  //   // }
  }
};
</script>
