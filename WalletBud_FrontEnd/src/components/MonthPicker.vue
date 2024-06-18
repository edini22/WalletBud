<template>
  <div class="year-picker">
    <div class="current-year bg-gradient-info shadow-info" @click="showYearGrid">
      {{ currentMonth }}
      <i class="material-icons sm">date_range</i>
    </div>
    <div v-if="showGrid" class="card year-grid-dropdown">

      <div class="dropdown-header">
        <a class="nav-button" @click="goToPreviousYear" :disabled="currentYear === minYear">
          
          <i class="material-icons">chevron_left</i>
        </a>
        <span class="text">{{ currentYear }}</span>
        <a class="nav-button" @click="goToNextYear" :disabled="currentYear === maxYear">
          
          <i class="material-icons">chevron_right</i>
        </a>
      </div>
      <hr class="horizontal dark my-sm-4" style="margin: 0 !important;"/>
      <div class="dropdown-body mt-1">
      <button 
        class="year-grid-dropdown-button"
        v-for="year in monthArray" 
        :key="year" 
        @click="selectYear(year)"
        :class="{ 'selected-year': year == currentYear }"
      >
        {{ year }}
      </button>
    </div>
    </div>
  </div>
</template>

<script>
import { useI18n } from 'vue-i18n';

export default {
  name: 'month-picker',
  setup() {
    const { t } = useI18n();
    return { t };
  },
  data() {
    return {
      currentMonth: new Date().getMonth(),
      showGrid: false,
      minYear: 2020, // user min active year on platform 
      maxYear: new Date().getMonth(), // Defina o ano máximo permitido para seleção
      monthArray: [
                  this.$t('Jan'),
                  this.$t('Fev'),
                  this.$t('Mar'),
                  this.$t('Abr'),
                  this.$t('Mai'),
                  this.$t('Jun'),
                  this.$t('Jul'),
                  this.$t('Ago'),
                  this.$t('Set'),
                  this.$t('Out'),
                  this.$t('Nov'),
                  this.$t('Dez'),
                ]
    };
  },
  computed: {
  },
  methods: {
    showYearGrid() {
      this.showGrid = !this.showGrid;
    },
    selectYear(year) {
      this.currentMonth = year;
      this.showGrid = false;
      // Aqui você pode emitir um evento ou fazer outra ação com o ano selecionado
    },
    goToPreviousYear() {
      if (this.currentMonth > this.minYear) {
        this.currentMonth--;
      }
    },
    goToNextYear() {
      if (this.currentMonth < this.maxYear) {
        this.currentMonth++;
      }
    },
  },
};
</script>

<style scoped>
.year-picker {
  display: flex;
  position: relative; /* Necessário para o posicionamento absoluto do dropdown */
  width: 50%;
}

.current-year {
  border: none;
  cursor: pointer;
  border-radius: 0.375rem;
  color: rgb(255, 255, 255);
  font-weight: 400;
  display: flex;
  justify-content: space-between;
  padding: 10px;
  padding-left: 20px;
  padding-right: 20px;
  font-size: 16px;
  width: 100%;
}

.sm{
  font-size: 18px;
  margin-top: 4px;
  pointer-events: none;
}
.nav-button {
  font-size: 18px;
  cursor: pointer;
  margin-top: 3px;
}

.selected-year{
  background-color: #f7f7f7 !important;
  color: #1a73e8 !important;
}

.year-grid-dropdown {
  display: flex;
  flex-wrap: wrap;
  margin-top: 5px;
  justify-content: space-around;
  position: absolute;
  top: 100%; /* Posiciona logo abaixo do botão */
  left: 0;
  background-color: white;
  border: 1px solid #ebebeb;
  padding: 3px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  z-index: 6000; /* Garante que o dropdown fique acima de outros elementos */
  width: 100%; /* Largura do dropdown */
  border-radius: 0.375rem;
}

.year-grid-dropdown-button {
  font-size: 16px;
  padding: 5px 5px;
  margin: 5px;
  cursor: pointer;
  width: 70px;
  background-color: transparent;
  color: #868686;
  font-weight: 300;
  line-height: 1.5;
}

.dropdown-header {
  justify-content: space-around;
  text-align: center;
  display: flex;
}
.text {
  line-height: 1.5;
  font-size: 18px;
  text-align: center;
  font-weight: 200;
}

</style>
