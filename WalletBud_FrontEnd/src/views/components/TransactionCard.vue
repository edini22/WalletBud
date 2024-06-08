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
          <material-input id="search" label="Procurar movimentos" style='width: 170px;' />
        </div>
        <div class="me-2 mb-2" v-tooltip="'Descarregar'">
          <!-- download transactions file -->
          <MaterialButton
            style="padding: 10px 10px; line-height: 19px;"
            color="info"
            variant="outline"
            size="md"
            :active="true"
            @click="downloadTransactions"
            
          >
          <i class="material-icons">file_download</i>
          </MaterialButton>
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
import MaterialInput from "@/components/MaterialInput.vue";
import MaterialDropdown from "@/components/MaterialDropdown.vue";
import MaterialFilter from "@/components/MaterialFilter.vue";

export default {
  name: "transaction-card",
  components: {
    MaterialButton,
    MaterialPagination,
    MaterialPaginationItem,
    MaterialInput,
    MaterialDropdown,
    MaterialFilter,
  },
  data() {
    return {
      currentPage: 1,
      itemsPerPage: 4,
      activeFilter: 'Últimos 30 dias',
      time_periods: [
        'Últimos 30 dias',
        'Últimos 60 dias',
        'Últimos 90 dias'
      ]
    };
  },
  computed: {
    transactions() {
      const tStore = useTransactionStore();
      return tStore.transactions;
    },
    totalPages() {
      return Math.ceil(this.transactions.length / this.itemsPerPage);
    },
    paginatedTransactions() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.transactions.slice(start, end);
    },
  },
  methods: {
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
      console.log("Filtro mudou:", filterData);
      // Aqui você pode realizar a lógica para aplicar os filtros à sua tabela
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
</style>