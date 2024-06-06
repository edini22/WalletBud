<template>
  <div class="card h-100 mb-4">
    <div class="card-header pb-0 px-3">
      <div class="row">
        <div class="col-md-6">
          <h6 class="mb-0">Movimentos</h6>
        </div>
        <div class="col-md-6 d-flex justify-content-end align-items-center">
          <i class="far fa-calendar-alt me-2" aria-hidden="true"></i>
          <small>01 - 30 Abril 2024</small>
        </div>
      </div>
    </div>
    <div class="card-body pt-4 p-3">
      <ul class="list-group">
        <li class="list-group-item border-0 d-flex justify-content-between ps-0 mb-2 border-radius-lg" 
          v-for="t in transactions" :key="t.id" :tID="t.id">
          <div class="d-flex align-items-center">
            <material-button v-if="t.tipo == 'Despesa'"
              color="danger"
              variant="outline"
              size="sm"
              class="btn-icon-only btn-rounded mb-0 me-3 d-flex align-items-center justify-content-center"
            >
              <i class="fas fa-arrow-down" aria-hidden="true"></i>
            </material-button>
            <material-button v-if="t.tipo == 'Receita'"
              color="success"
              variant="outline"
              size="sm"
              class="btn-icon-only btn-rounded mb-0 me-3 d-flex align-items-center justify-content-center"
            >
              <i class="fas fa-arrow-up" aria-hidden="true"></i>
            </material-button>
            <div class="d-flex flex-column">
              <h6 class="mb-1 text-dark text-sm">{{ t.name }}</h6>
              <span class="text-xs">{{ t.date  }}</span>
            </div>
          </div>
          <div v-if="t.tipo == 'Despesa'"
            class="d-flex align-items-center text-danger text-gradient text-sm font-weight-bold"
          >
            - {{ t.value }} EUR
          </div>
          <div v-else
            class="d-flex align-items-center text-danger text-gradient text-sm font-weight-bold"
          >
           + {{ t.value }} EUR
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import MaterialButton from "@/components/MaterialButton.vue";
import { useTransactionStore } from '@/store/transactionStore.js';

export default {
  name: "transaction-card",
  components: {
    MaterialButton,
  },
  computed: {
    transactions(){
      const tStore = useTransactionStore();
      return tStore.transactions;
    }
  }
};
</script>
