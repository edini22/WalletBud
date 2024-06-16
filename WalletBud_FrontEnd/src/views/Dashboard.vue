<template>
    <div class="py-4 container-fluid">
        <div class="row mb-4">
            <div class="col-lg-12 position-relative z-index-2">
                <div class="row">
                    <div class="col-lg-4 col-md-6 col-sm-12">
                        <mini-statistics-card :title="{ text: $t('Orçamento Disponível'), value: budget + ' €' }"
                            :detail="`<span class='text-sm'>${$t('Objetivo poupança descontado no saldo')}</span>`"
                            :icon="{
                  name: 'account_balance_wallet',
                  color: 'text-white',
                  background: 'info',
                }" />
                    </div>
                    <div class="col-lg-4 col-md-6 col-sm-12 mt-lg-0 mt-4">
                        <mini-statistics-card :title="{ text: $t('Balanço Mensal'), value: balance }" :icon="{
                  name: 'leaderboard',
                  color: 'text-white',
                  background: 'primary',
                }" />
                    </div>
                    <div class="col-lg-4 col-md-6 col-sm-12 mt-lg-0 mt-4">
                        <mini-statistics-card :title="{ text: $t('Gasto Total Anual'), value: anualSpend + ' €' }"
                            :icon="{
                                name: 'receipt_long',
                                color: 'text-white',
                                background: 'dark',
                            }">
                            <template #detail>
                                <material-dropdown 
                                    :options="userActiveYears"
                                    :selected="selectedYear"
                                    @update:selected="SelectYear"
                                />
                            </template>
                        </mini-statistics-card>
                    </div>
                </div>
                <div class="row mt-4">
                    <div class="col-lg-4 col-md-6 mt-4">
                        <chart-holder-card :title="$t('Gasto da Semana Anterior')"
                            :subtitle="$t('Gasto total por dia na semana passada')" :week="pastWeekDate">
                            <reports-bar-chart :chart="{
                    labels: [$t('Seg'), $t('Ter'), $t('Qua'), $t('Qui'), $t('Sex'), $t('Sáb'), $t('Dom')],
                    datasets: {
                      label: $t('Gasto diário'),
                      data: weekDailySpend,
                    },
                  }" />
                        </chart-holder-card>
                    </div>
                    <div class="col-lg-4 col-md-6 mt-4">
                        <chart-holder-card title="Daily Sales"
                            subtitle="(<span class='font-weight-bolder'>+15%</span>) increase in today sales."
                            update="updated 4 min ago" color="success">
                            <reports-line-chart :chart="{
                    labels: [
                      'Apr',
                      'May',
                      'Jun',
                      'Jul',
                      'Aug',
                      'Sep',
                      'Oct',
                      'Nov',
                      'Dec',
                    ],
                    datasets: {
                      label: 'Mobile apps',
                      data: [50, 40, 300, 320, 500, 350, 200, 230, 500],
                    },
                  }" />
                        </chart-holder-card>
                    </div>
                    <div class="col-lg-4 mt-4">
                        <chart-holder-card title="Completed Tasks" subtitle="Last Campaign Performance"
                            update="just updated" color="dark">
                            <reports-line-chart id="tasks-chart" :chart="{
                    labels: [
                      'Apr',
                      'May',
                      'Jun',
                      'Jul',
                      'Aug',
                      'Sep',
                      'Oct',
                      'Nov',
                      'Dec',
                    ],
                    datasets: {
                      label: 'Mobile apps',
                      data: [50, 40, 300, 220, 500, 250, 400, 230, 500],
                    },
                  }" />
                        </chart-holder-card>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
  
  <script>
  import ChartHolderCard from "./components/ChartHolderCard.vue";
  import ReportsBarChart from "@/examples/Charts/ReportsBarChart.vue";
  import ReportsLineChart from "@/examples/Charts/ReportsLineChart.vue";
  import MiniStatisticsCard from "./components/MiniStatisticsCard.vue";
  import MaterialDropdown from "@/components/MaterialDropdown.vue"
  import { userStore } from "@/store/userStore";
  
  export default {
    name: "Dashboard",
    data() {
      return {
        budget: '',
        balance: 0,
        pastWeekDate: '14 a 21 abril',
        weekDailySpend: [100, 20, 10, 40, 50, 10, 40],
        userActiveYears: [2020, 2021, 2022, 2023, 2024],
        selectedYear: 0,
        anualSpend: 0
      };
    },
    components: {
      ChartHolderCard,
      ReportsBarChart,
      ReportsLineChart,
      MiniStatisticsCard,
      MaterialDropdown
    },
    methods: {
      getBudget() {
        this.budget = this.user.saldo - this.user.objetivo;
      },
      SelectYear(option) {
        this.selectedYear = option;
      },
    },
    computed: {
      user() {
        const uStore = userStore();
        return uStore;
      }
    },
    mounted() {
      this.getBudget();
      //get current year
      this.selectedYear = 2024;
    },
  };
  </script>
  