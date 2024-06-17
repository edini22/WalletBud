<template>
  <div class="year-picker">
    <!-- Botão para o ano anterior -->
    <button class="nav-button" @click="goToPreviousYear" :disabled="currentYear === minYear">
      &lt; <!-- Seta para esquerda -->
    </button>

    <!-- Botão para o ano atual -->
    <button class="current-year" @click="showYearGrid">{{ currentYear }}</button>

    <!-- Botão para o próximo ano -->
    <button class="nav-button info" @click="goToNextYear" :disabled="currentYear === maxYear">
      &gt; <!-- Seta para direita -->
    </button>

    <!-- Grid para escolher o ano como dropdown -->
    <div v-if="showGrid" class="year-grid-dropdown">
      <button v-for="year in yearArray" :key="year" @click="selectYear(year)">
        {{ year }}
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'year-picker',
  data() {
    return {
      currentYear: new Date().getFullYear(),
      showGrid: false,
      minYear: 2020, // user min active year on platform 
      maxYear: new Date().getFullYear(), // Defina o ano máximo permitido para seleção
    };
  },
  computed: {
    yearArray() {
      const years = [];
      for (let year = this.minYear; year <= this.maxYear; year++) {
        years.push(year);
      }
      return years;
    },
  },
  methods: {
    showYearGrid() {
      this.showGrid = !this.showGrid;
    },
    selectYear(year) {
      this.currentYear = year;
      this.showGrid = false;
      // Aqui você pode emitir um evento ou fazer outra ação com o ano selecionado
    },
    goToPreviousYear() {
      if (this.currentYear > this.minYear) {
        this.currentYear--;
      }
    },
    goToNextYear() {
      if (this.currentYear < this.maxYear) {
        this.currentYear++;
      }
    },
  },
};
</script>

<style scoped>
.year-picker {
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative; /* Necessário para o posicionamento absoluto do dropdown */
}

.current-year {
  background: none;
  border: none;
  cursor: pointer;
  background: none;
  border-radius: 0.375rem;
  border-top-left-radius: 0.375rem !important;
  border-bottom-left-radius: 0.375rem !important;
  padding: 0.625rem 0.75rem !important;
  line-height: 1.3 !important;
  background-color: #1a73e8;
  color: white;
  font-size: 0.875rem;
  font-weight: 400;
  height: 40px;
  width: 110px;
  background-image: linear-gradient(195deg, #49a3f1 0%, #1a73e8 100%);
}
.nav-button {
  font-size: 16px;
  padding: 8px;
  margin: 0 5px;
  cursor: pointer;
  border-radius: 0.375rem;
  border-top-left-radius: 0.375rem !important;
  border-bottom-left-radius: 0.375rem !important;
  padding: 0.625rem 0.75rem !important;
  line-height: 1.3 !important;
  background-color: #1a73e8;
  background-image: linear-gradient(195deg, #49a3f1 0%, #1a73e8 100%);
}

.year-grid-dropdown {
  display: flex;
  flex-wrap: wrap;
  margin-top: 5px;
  justify-content: center;
  position: absolute;
  top: 100%; /* Posiciona logo abaixo do botão */
  left: 50%;
  transform: translateX(-50%);
  background-color: white;
  border: 1px solid #ccc;
  padding: 10px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  z-index: 6000; /* Garante que o dropdown fique acima de outros elementos */
  width: 250px; /* Largura do dropdown */
  border-radius: 0.375rem;
}

.year-grid-dropdown button {
  font-size: 14px;
  padding: 5px 10px;
  margin: 5px;
  cursor: pointer;
  width: 70px; /* Largura dos botões */
}
</style>
