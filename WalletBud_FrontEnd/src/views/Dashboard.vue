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
          <div v-if="!isCategoriesEmpty" class="col-12 col-md-6 col-lg-6 mt-4">
            <chart-holder-card :title="$t('Gasto Semanal')" :subtitle="$t('Gasto total por dia na semana anterior')"
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

          <!-- category spend chart -->
          <div class="col-12 col-md-6 col-lg-6 mt-4 mt-lg-4 mt-sm-5 mt-md-4">
            <chart-holder-card :title="$t('Gastos Por Categoria')" :subtitle="$t('Gasto total por categoria por mês') "
              :week="pastWeekDate" color="dark">

              <PieChart :categories="categories" />
              <template #detail>
                <div class="d-flex">
                <month-picker-input :default-month="currentMonth" :default-year="currentYear" show-year lang="pt"
                @input="CategoriesMonthYearChange"></month-picker-input>
              </div>
              </template>
            </chart-holder-card>
          </div>
        </div>

        <div class="row mt-lg-6 mt-5 mt-md-6 mt-lg-4">

          <!-- GASTOS POR MÊS -->
          <div v-if="monthSpend !== null">
            <chart-holder-card title='Gasto por mês' :subtitle="'Gasto total por cada mês no ano ' + monthSelectedYear" update="just updated"
              color="success">
              <reports-line-chart id="tasks-chart" :chart="{
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
                  label: 'Mobile apps',
                  data: monthSpend,
                },
              }" />
              <template #detail>
                <div class="d-flex">
                <month-picker-input :default-month="currentMonth" :default-year="currentYear" show-year lang="pt"
                @input="monthChange"></month-picker-input>
              </div>
              </template>
            </chart-holder-card>
          </div>
        </div>

      
      </div>
    </div>
  </div>
</template>

<script>
import ChartHolderCard from "./components/ChartHolderCard.vue";
import PieChart from "./components/PieChart.vue";
import ReportsBarChart from "@/examples/Charts/ReportsBarChart.vue";
import ReportsLineChart from "@/examples/Charts/ReportsLineChart.vue";
import MiniStatisticsCard from "./components/MiniStatisticsCard.vue";
import MaterialDropdown from "@/components/MaterialDropdown.vue"
import { userStore } from "@/store/userStore";
//import { MonthPicker } from 'vue-month-picker'
import { MonthPickerInput } from 'vue-month-picker'

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
      anualSpend: 0,
      currentMonth: new Date().getMonth(),
      currentYear: new Date().getFullYear(),
      categories: {},
      monthSpend: null,
      monthSelectedYear: new Date().getFullYear()
    };
  },
  components: {
    ChartHolderCard,
    ReportsBarChart,
    ReportsLineChart,
    MiniStatisticsCard,
    MaterialDropdown,
    //MonthPicker,
    MonthPickerInput,
    PieChart
  },
  methods: {
    CategoriesMonthYearChange(value) {
      // 'value' will contain an object with 'month' and 'year' properties
      console.log(value.month);
      console.log(value.year);
      this.categories =  {
        "ffffff": 123,
        "pessoal": 2312
      };
    },
    monthChange(value){
      this.monthSelectedYear = value.year;
      //GET GASTO POR MÊS
      this.monthSpend = [203, 3030, 404, 233, 230, 120, 333, 5556, 4554, 34, 424, 434];
    },
    getBudget() {
      this.budget = parseFloat((this.user.saldo - this.user.objetivo).toFixed(2));
    },
    SelectYear(option) {
      this.selectedYear = option;
    },
  },
  computed: {
    user() {
      const uStore = userStore();
      return uStore;
    },
    isCategoriesEmpty() {
      return Object.keys(this.categories).length === 0;
    },
  },
  mounted() {
    this.getBudget();
    //GET GASTO POR MÊS NO ANO ATUAL 
    this.monthSpend = [100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100];
    this.selectedYear = 2024;
    this.categories = {"renda": 123,
    "pessoal": 2312};
  },
};
</script>

<style>
.month-picker-input-container {
  z-index: 1000;
}

.month-picker__year button {
  background-color: transparent !important;
  background-color: none !important;
  border: none;
}

.month-picker__year p {
  cursor: default;
}

.month-picker__month.selected{
  background-image: linear-gradient(195deg, #49a3f1 0%, #1a73e8 100%);
  border-radius: 0px;
}
</style>