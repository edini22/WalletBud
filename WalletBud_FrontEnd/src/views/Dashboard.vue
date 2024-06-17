<template>
  <div class="py-4 container-fluid">
    <div class="row mb-4">
      <div class="col-lg-12 position-relative">
        <div class="row">
          <div class="col-lg-4 col-md-6 col-sm-12">
            <mini-statistics-card :title="{ text: $t('Orçamento Disponível'), value: budget + ' €' }"
              :detail="`<span class='text-sm'>${$t('Objetivo poupança descontado no saldo')}</span>`" :icon="{
                name: 'account_balance_wallet',
                color: 'text-white',
                background: 'info',
              }" />
          </div>
          <div class="col-lg-4 col-md-6 col-sm-12 mt-lg-0 mt-md-0 mt-4">
            <mini-statistics-card :title="{ text: $t('Balanço Mensal'), value: balance }" :icon="{
              name: 'leaderboard',
              color: 'text-white',
              background: 'primary',
            }" />
          </div>
          <div class="col-lg-4 col-md-6 col-sm-12 mt-lg-0 mt-4">
            <mini-statistics-card :title="{ text: $t('Gasto Total Anual'), value: anualSpend + ' €' }" :icon="{
              name: 'receipt_long',
              color: 'text-white',
              background: 'dark',
            }">
              <template #detail>
                <material-dropdown :options="userActiveYears" :selected="selectedYear" @update:selected="SelectYear" />
              </template>
            </mini-statistics-card>
          </div>
        </div>
        <div class="row mt-4">

          <!-- Weekly spend chart -->
          <div class="col-lg-4 col-md-6 mt-4">
            <chart-holder-card :title="$t('Gasto Semanal')" :subtitle="$t('Gasto total por dia numa semana')"
              :week="pastWeekDate">

              <reports-bar-chart :chart="{
                labels: [$t('Seg'), $t('Ter'), $t('Qua'), $t('Qui'), $t('Sex'), $t('Sáb'), $t('Dom')],
                datasets: {
                  label: $t('Gasto diário'),
                  data: weekDailySpend,
                  },
                }" />
            </chart-holder-card>
          </div>

          <!-- Month spend chart on chosen year -->
          <div class="col-lg-4 col-md-6 mt-5 mt-md-4">
            <chart-holder-card title="Daily Sales"
              subtitle="(<span class='font-weight-bolder'>+15%</span>) increase in today sales."
              color="success"
              >
              <reports-line-chart :chart="{
                labels: [
                  $t('Jan'),
                  $t('Fev'),
                  $t('Mar'),
                  $t('Abr'),
                  $t('Mai'),
                  $t('Jun'),
                  $t('Jul'),
                  $t('Ago'),
                  $t('Set'),
                  $t('Out'),
                  $t('Nov'),
                  $t('Dez'),
                ],
                datasets: {
                  label: 'Gasto total',
                  data: [800, 500, 1200, 900, 1100, 600, 910, 1000, 500, 1000, 690, 901],
                },
              }" />
              <template #detail>
                <year-picker ></year-picker>
              </template>
            </chart-holder-card>
          </div>
          <div class="col-lg-4 mt-5 mt-md-6 mt-lg-4">
            <chart-holder-card title="Completed Tasks" subtitle="Last Campaign Performance" update="just updated"
              color="dark">
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
import YearPicker from "@/components/YearPicker.vue"
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
    MaterialDropdown,
    YearPicker
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