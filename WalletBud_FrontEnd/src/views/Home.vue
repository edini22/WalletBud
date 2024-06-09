<template>
  <div class="py-4 container-fluid">
    <div class="row mb-4">
      <div class="col-lg-12 position-relative z-index-2">
        <div class="row">
          <div class="col-lg-3 col-md-4 col-sm-4 col-12">
            <mini-statistics-card
              :title="{ text: 'Saldo Atual', value: user.saldo + ' €' }"
              :icon="{
                name: 'account_balance_wallet',
                color: 'text-white',
                background: 'success',
              }"
            />
          </div>
          <div class="col-lg-3 col-md-4 col-sm-4 col-12 mt-md-0 mt-4">
            <mini-statistics-card
              :title="{ text: 'Objetivo Poupança', value: '2,300' }"
              show="true"
              btn_color="btn-outline-primary"
              btn_text="Definir objetivo"
              :icon="{
                name: 'savings',
                color: 'text-white',
                background: 'primary',
              }"
            />
          </div>
          <div class="col-lg-3 col-md-4 col-sm-4 col-12 mt-md-0 mt-4">
            <mini-statistics-card
              :title="{ text: 'Movimentos' }"
              show="true"
              btn_color="bg-gradient-dark"
              btn_text="+ Adicionar Movimento"
              :icon="{
                name: 'post_add',
                color: 'text-white',
                background: 'dark',
              }"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-lg-4 col-md-12 col-sm-12 mt-5">
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
          </div>

          <div class="col-lg-8 col-md-12 col-sm-12 mt-4">
            <transaction-card />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import ChartHolderCard from "./components/ChartHolderCard.vue";
import ReportsBarChart from "@/examples/Charts/ReportsBarChart.vue";
import MiniStatisticsCard from "./components/MiniStatisticsCard.vue";
import TransactionCard from "./components/TransactionCard.vue";
import { userStore } from '@/store/userStore.js';

export default {
  name: "home",
  data() {
    return {
    };
  },
  components: {
    ChartHolderCard,
    ReportsBarChart,
    MiniStatisticsCard,
    TransactionCard,
  },
  computed: {
    user(){
      const uStore = userStore();
      return uStore;
    }
  },
};

</script>
