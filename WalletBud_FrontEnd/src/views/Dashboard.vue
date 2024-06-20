<template>
    <div class="py-4 container-fluid">
        <div class="row mb-4">
            <div class="col-lg-12 position-relative">
                <div class="row">
                    <div class="col-lg-4 col-md-6 col-sm-12">
                        <mini-statistics-card :title="{ text: $t('Orçamento Disponível'), value: budget + ' ' + store.moeda }"
                            :detail="`<span class='text-sm'>${$t('Objetivo poupança descontado no saldo')}</span>`"
                            :icon="{
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
                        <mini-statistics-card :title="{ text: $t('Gasto Total Anual'), value: anualSpend + ' ' + store.moeda }"
                            :icon="{
                                name: 'receipt_long',
                                color: 'text-white',
                                background: 'dark',
                            }">
                            <template #detail>
                                <material-dropdown :options="userActiveYears" :selected="selectedYear"
                                    @update:selected="SelectYear" />
                            </template>
                        </mini-statistics-card>
                    </div>
                </div>
                <div class="row mt-4">

                    <!-- Weekly spend chart -->
                    <div class="col-12 col-md-6 col-lg-6 mt-4">
                        <chart-holder-card :title="$t('Gasto Semanal')"
                            :subtitle="$t('Gasto total por dia na semana anterior')" :week="pastWeekDate">

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
                    <div v-if="!isCategoriesEmpty" class="col-12 col-md-6 col-lg-6 mt-4 mt-lg-4 mt-sm-5 mt-md-4">
                        <chart-holder-card :title="$t('Gastos Por Categoria')"
                            :subtitle="$t('Gasto total por categoria por mês')" :week="pastWeekDate" color="dark">

                            <PieChart :categories="categories" />
                            <template #detail>
                                <div class="d-flex">
                                    <month-picker-input v-if="idioma !== ''" :default-month="currentMonth"
                                        :default-year="currentYear" show-year :lang=idioma
                                        @input="CategoriesMonthYearChange"></month-picker-input>
                                </div>
                            </template>
                        </chart-holder-card>
                    </div>
                </div>

                <div class="row mt-lg-6 mt-5 mt-md-6 mt-lg-4">

                    <!-- GASTOS POR MÊS -->
                    <div v-if="monthSpend !== null">
                        <chart-holder-card title='Gasto por mês'
                            :subtitle="'Gasto total por cada mês no ano ' + monthSelectedYear" update="just updated"
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
                                    label: 'Gasto por mês',
                                    data: monthSpend,
                                },
                            }" />
                            <template #detail>
                                <div class="d-flex">
                                    <month-picker-input v-if="idioma !== ''" default-month=""
                                        :default-year="currentYear" show-year :lang=idioma @change="monthChange"
                                        year-only></month-picker-input>
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
import { MonthPickerInput } from 'vue-month-picker';

import router from "@/router";

export default {
    name: "Dashboard",
    setup() {
    const store = userStore();
    return { store };
  },
    data() {
        return {
            budget: '',
            balance: 0,
            pastWeekDate: '14 a 21 abril',
            weekDailySpend: [0, 0, 0, 0, 0, 0, 0],
            userActiveYears: [2020, 2021, 2022, 2023, 2024],
            selectedYear: 0,
            anualSpend: 0,
            currentMonth: (new Date().getMonth()) + 1,
            currentYear: new Date().getFullYear(),
            categories: {},
            monthSpend: null,
            monthSelectedYear: new Date().getFullYear(),
            idioma: '',
            getCategory: '',
            mesesParaNumeros: {
  Janeiro: 1,
  Fevereiro: 2,
  Março: 3,
  Abril: 4,
  Maio: 5,
  Junho: 6,
  Julho: 7,
  Agosto: 8,
  Setembro: 9,
  Outubro: 10,
  Novembro: 11,
  Dezembro: 12,
}
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
      mesParaNumero(mes) {
      return this.mesesParaNumeros[mes] || null; // retorna null se o mês não for encontrado
    },
        CategoriesMonthYearChange(value) {
            // 'value' will contain an object with 'month' and 'year' properties
            console.log(this.currentMonth);
            this.categories = { };
            this.statsCategory(value.month);
        },
        async monthChange(value) {
            this.monthSelectedYear = value.year;
            //GET GASTO POR MÊS
            const url =
                "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/gastosmes/" + this.monthSelectedYear;
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

                let arrayMeses = Array.from({ length: 12 }, () => 0);

                Object.entries(data).forEach(([chave, valor]) => {
                    let mes = parseInt(chave);
                    if (!isNaN(mes) && mes >= 1 && mes <= 12) {
                        arrayMeses[mes - 1] = valor;
                    }
                });

                this.monthSpend = arrayMeses;

            } catch (error) {
                if (error.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                }
            }
        },
        async anualSpendChange() {
            //GET GASTO POR MÊS
            const url =
                "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/gastosano/" + this.selectedYear;
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
                
                this.anualSpend = data.balanco.toFixed(2);

            } catch (error) {
                this.anualSpend = 0;
                if (error.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                }
            }
        },
        async statsCategory(mes) {
          console.log(mes);
            if(mes == null)
              this.getCategory = this.currentMonth;
            else
              this.getCategory = this.mesParaNumero(mes);
            //GET GASTO POR MÊS
            const url =
                "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/gastoscategoria/" + this.selectedYear + "/" + this.getCategory;
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
                this.categories = data;

                console.log(data);

            } catch (error) {
                if (error.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                }
            }
        },
        getFormattedDate(date) {
            const year = date.getFullYear();
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            return `${year}-${month}-${day}`;
        },
        async statsWeek() {
            //GET GASTO POR MÊS
            // YYYY-MM-DD
            const today = new Date();
            const formattedDate = this.getFormattedDate(today);

            const url =
                "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/gastossemana/" + formattedDate;
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

                const daysOrder = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];

                const sortedData = data.semana.sort((a, b) => {
                    return daysOrder.indexOf(a.DiaDaSemana) - daysOrder.indexOf(b.DiaDaSemana);
                });

                const costs = sortedData.map(entry => parseFloat(entry.TotalCost));

                for (let i = 0; i < costs.length; i++) {
                    this.weekDailySpend[i] = costs[i];
                }

                this.weekDailySpend = costs;

            } catch (error) {
                if (error.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                }
                this.weekDailySpend = [0, 0, 0, 0, 0, 0, 0];
            }
        },
        async balanco() {
            //GET GASTO POR MÊS

            const url =
                "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/balanco";
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

                this.balance = data.balanco.toFixed(2);

            } catch (error) {
                if (error.message.includes('token')) {
                    alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

                    localStorage.clear();
                    sessionStorage.clear();

                    router.push('/sign-in');
                }
                this.weekDailySpend = [0, 0, 0, 0, 0, 0, 0];
            }
        },
        getBudget() {
            this.budget = parseFloat((this.user.saldo - this.user.objetivo).toFixed(2));
        },
        SelectYear(option) {
            this.selectedYear = option;
            this.anualSpendChange();
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
        this.idioma = this.user.idioma;
        this.getBudget();
        //GET GASTO POR MÊS NO ANO ATUAL 
        this.monthChange({
            year: this.currentYear
        });
        this.selectedYear = this.currentYear;
        this.anualSpendChange();
        this.categories = { 
            "Sem Gastos": "0.00",
        };
        this.statsCategory();

        this.balanco();

        this.statsWeek();
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

.month-picker__month.selected {
    background-image: linear-gradient(195deg, #49a3f1 0%, #1a73e8 100%);
    border-radius: 0px;
}
</style>