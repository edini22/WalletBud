<template>
  <div class="card h-100">
    <div class="card-header pb-0 px-3">
      <div class="d-flex flex-wrap align-items-center">
        <div class="me-2 mb-2">
          <!-- time period-->
          <MaterialDropdown :on-click='changeFilter' :items='time_periods' v-model:selected="activeFilter" />
        </div>
        <div class="me-2 mb-2">
          <!-- filter by category or/and type -->
          <MaterialFilter @change="handleFilterChange" />
        </div>
        <div class="me-2 mb-2">
          <!-- search movements -->
          <div class="input-group input-group-outline" 
              :class="{'is-focused': isFocused}"
              >
            <label class="form-label">Procurar movimentos</label>
            <input class="form-control form-control-default" id="search"
              style='width: 170px;' v-model="searchQuery"
              @focus="handleFocus" @blur="handleBlur"/>
          </div>

        </div>
        <div class="me-2 mt-2" v-tooltip="'Exportar'">
          <!-- download transactions file -->
          <ExportData />
        </div>
      </div>
      <div class="row mt-3 mb-0">
        <div class="col-md-6">
          <h6 class="mb-0">Movimentos</h6>
        </div>
        <div class="col-md-6 d-flex justify-content-end align-items-center">
          <i class="far fa-calendar-alt me-2" aria-hidden="true"></i>
          <small>01 - 30 Abril 2024</small>
        </div>
      </div>
    </div>
    <div class="card-body pt-2 p-3 pb-1">
      <ul class="list-group">
        <li class="list-group-item border-0 d-flex justify-content-between ps-0 mb-2 border-radius-lg"
          v-for="t in paginatedTransactions" :key="t.id" :tID="t.id">
          <div class="d-flex align-items-center">
            <material-button v-if="t.tipo == 'Despesa'" color="danger" variant="outline" size="sm"
              class="btn-icon-only btn-rounded mb-0 me-3 d-flex align-items-center justify-content-center">
              <i class="fas fa-arrow-down" aria-hidden="true"></i>
            </material-button>
            <material-button v-if="t.tipo == 'Receita'" color="success" variant="outline" size="sm"
              class="btn-icon-only btn-rounded mb-0 me-3 d-flex align-items-center justify-content-center">
              <i class="fas fa-arrow-up" aria-hidden="true"></i>
            </material-button>
            <div class="d-flex flex-column">
              <h6 class="mb-1 text-dark text-sm">{{ t.name }}</h6>
              <span class="text-xs">{{ t.date }}</span>
            </div>
          </div>
          <div v-if="t.tipo == 'Despesa'"
            class="d-flex align-items-center text-danger text-gradient text-sm font-weight-bold">
            - {{ t.value }} EUR
          </div>
          <div v-else class="d-flex align-items-center text-success text-gradient text-sm font-weight-bold">
            + {{ t.value }} EUR
          </div>
        </li>
      </ul>
    </div>
    <!-- pagination -->
    <div class="card-footer p-3 size ">
      <material-pagination color="info" size="sm">
        <material-pagination-item prev :disabled="currentPage === 1" @click="prevPage" />
        <material-pagination-item v-for="page in totalPages" :key="page" :label="page" :active="page === currentPage"
          @click="goToPage(page)" />
        <material-pagination-item next :disabled="currentPage === totalPages" @click="nextPage" />
      </material-pagination>
    </div>
  </div>
</template>

<script>
import MaterialButton from "@/components/MaterialButton.vue";
import MaterialPagination from "@/components/MaterialPagination.vue";
import MaterialPaginationItem from "@/components/MaterialPaginationItem.vue";
import { useTransactionStore } from '@/store/transactionStore.js';
import MaterialDropdown from "@/components/MaterialDropdown.vue";
import MaterialFilter from "@/components/MaterialFilter.vue";
import ExportData from '@/components/ExportData.vue';

export default {
  name: "transaction-card",
  components: {
    MaterialButton,
    MaterialPagination,
    MaterialPaginationItem,
    MaterialDropdown,
    MaterialFilter,
    ExportData,
  },
  data() {
    return {
      currentPage: 1,
      itemsPerPage: 4,
      activeFilter: 'Últimos 30 dias',
      searchQuery: '',
      isFocused: false,
      time_periods: [
        'Últimos 30 dias',
        'Últimos 60 dias',
        'Últimos 90 dias'
      ],
      selectedCategories: [],
      selectedType: '',
    };
  },
  computed: {
    transactions() {
      const tStore = useTransactionStore();
      return tStore.transactions;
    },
    filteredTransactions() {
      console.log('Search Query:', this.searchQuery);
      return this.transactions.filter(t => {
        const search = this.searchQuery.toLowerCase();
        return (
          t.name.toLowerCase().includes(search) ||
          t.date.toLowerCase().includes(search) ||
          t.tipo.toLowerCase().includes(search) ||
          t.value.toString().toLowerCase().includes(search)
        );
      });
    },
    totalPages() {
      return Math.ceil(this.filteredTransactions.length / this.itemsPerPage);
    },
    paginatedTransactions() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.filteredTransactions.slice(start, end);
    },
  },
  watch: {
    searchQuery() {
      this.currentPage = 1; // Reset pagination when search query changes
    }
  },
  methods: {
    handleFocus() {
      this.isFocused = true;
    },
    handleBlur() {
      // Método chamado quando o input perde o foco (quando se clica fora dele)
      this.isFocused = false; // Atualiza a variável para exibir a mensagem
    },
    goToPage(page) {
      this.currentPage = page;
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
    changeFilter: function (filter) {
      this.activeFilter = filter;
    },
    handleFilterChange(filterData) {
      this.selectedCategories = filterData.categories;
      this.selectedType = filterData.type;
      console.log("Categorias selecionadas:", this.selectedCategories);
      console.log("Tipo selecionadas:", this.selectedType);
    },
  },
};
</script>

<style scoped>
.flex-item {
  flex: 0 1 auto;
  min-width: 200px;
  /* Define a largura mínima para cada item */
}

@media (max-width: 768px) {
  .flex-item {
    flex: 1 1 100%;
    /* Em telas menores, os itens ocupam 100% da largura */
  }
}

.input-group.input-group-outline .form-control {
  border: 1px solid #1a73e8 !important;
}
</style>