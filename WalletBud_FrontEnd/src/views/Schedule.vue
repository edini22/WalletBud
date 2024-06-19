<template>

<div class="container">
  <div class="row justify-content-center">
      <div class="timeline-header mb-4">
        <h4>{{ $t('Agenda') }}</h4>
      </div>
      <div class="scroll-container">
        <div v-if="store.timeline.length > 0">
          
          <timeline-list class="cuter">
            <timeline-item v-for="(p, index) in store.timeline" :key="index"
                 :color="p.tipo === 'receita' ? 'success' : 'danger'"
                 :icon="{ component: p.tipo === 'receita' ? 'notifications' : 'shopping_cart', class: 'text-white text-sm' }"
                 :title="p.name"
                 :value="p.tipo === 'receita' ? (p.value + ' ' + user.moeda) : ('-' + p.value + ' ' + user.moeda)"
                 :date-time="p.date"
                 :description="p.descricao"
                 :date-color="p.tipo === 'receita' ? 'success' : 'danger'" />
            </timeline-list>
          
          </div>
          <div v-else class="d-flex justify-content-center align-items-center" style="height: 70vh;">
            <h5 class="text-secondary">{{ $t ('Sem eventos agendados')}}!</h5>
          </div>
      </div>
  </div>
</div>


</template>

<script>
import TimelineList from "@/examples/Cards/TimelineList.vue";
import TimelineItem from "@/examples/Cards/TimelineItem.vue";
import { ref, computed, onMounted } from 'vue';
import { fixaStore } from "@/store/fixaStore";
import { userStore } from "@/store/userStore";
import { useRouter } from 'vue-router';
import { useI18n } from "vue-i18n";

export default {
  name: "schedule",
  components: { TimelineList, TimelineItem },
  setup() {
    const { t } = useI18n();

    const snackbar = ref(false);
    const popup = ref(false);
    const popupReject = ref(false);
    const popupAccept = ref(false);
    const popupDetails = ref(false);
    const popupReject2 = ref(false);
    const popupAccept2 = ref(false);
    const store = fixaStore();
    const user = userStore();
    const selectedPendenteId = ref(null);
    const transa = ref(null);
    const router = useRouter(); 

    const loadTimeline = async () => {
      try {
        await store.loadTimeline(2024, 6);
        console.log("Timeline carregada:", store.timeline);
      } catch (err) {
        if (err.message.includes('token')) {
            alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

            localStorage.clear();
            sessionStorage.clear();

            router.push('/sign-in');
        } else {
          alert("Erro -> " + err.message);
        }
      }
    };

    const getRepetitionText = (repeticao) => {
      if (repeticao === undefined) {
        return "Unica";
      }
      switch (repeticao) {
        case 1:
          return "Diariamente";
        case 2:
          return "Semanalmente";
        case 3:
          return "Mensalmente";
        case 4:
          return "Anualmente";
        default:
          return "Unica";
      }
    };

    const formattedDate = (timestamp) => {
      const date = new Date(timestamp.replace(' ', 'T'));
      const day = String(date.getDate()).padStart(2, '0');
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const year = date.getFullYear();
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');

      return `${day}-${month}-${year} ${hours}:${minutes}`;
    }

    const openDetailsPopup = (id) => {
      selectedPendenteId.value = id;
      popupDetails.value = true;
    };

    const selectedPendente = computed(() => {
      return store.pendentes.find(p => p.id === selectedPendenteId.value) || {};
    });


    const openPopup = async (id) => {
      popup.value = true;
      const t = store.proximosPagamentos.find(p => p.id === id);

      const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/fixa/"+ t.tipo + "/get/" + t.id;
      const token = localStorage.getItem('token');

      const request = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          'Authorization': "Bearer " + token
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

        // Mapear os dados recebidos para o formato de categorias desejado
        transa.value = data;

      } catch (error) {
        console.error('Erro ao carregar pendentes:', error.message);
        throw error;
      }
    };

    const closeSnackbar = () => {
      snackbar.value = false;
    };

    onMounted(() => {
      // const modal = document.getElementById('categoryModal');
      // modal.addEventListener('hidden.bs.modal', forceRerender);
      loadTimeline();
    });

    return {
      formattedDate,
      loadTimeline,
      snackbar,
      closeSnackbar,
      popup,
      popupReject,
      popupAccept,
      popupDetails,
      popupReject2,
      popupAccept2,
      getRepetitionText,
      store,
      user,
      transa,
      selectedPendenteId,
      openDetailsPopup,
      selectedPendente,
      openPopup,
      t,
    };
  },
};
</script>

<style scoped>

  /* .card {
    display: flow-root;
  } */

  .scroll-container {
    max-height: 80vh; 
    overflow-y: auto;
  }

.scroll-container::-webkit-scrollbar, .scroll-container2::-webkit-scrollbar{
  width: 8px;
}

.scroll-container::-webkit-scrollbar-track, .scroll-container2::-webkit-scrollbar-track{
  border-radius: 10px;
}

.scroll-container::-webkit-scrollbar-thumb, .scroll-container2::-webkit-scrollbar-thumb{
  background: #888;
  border-radius: 10px;
}

  .scroll-container2 {
    max-height: 31vh;
    overflow-y: auto;
  }

  .table-head-fixed {
    position: sticky;
    top: 0;
  }


  

</style>