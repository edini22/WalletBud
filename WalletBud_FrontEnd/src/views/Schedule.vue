<template>

  <div class="container">
    <div class="row">
      <div class="col">
      </div>
      <div class="col-6">
        <div class="timeline-header">
            <h4>Agenda</h4>
          </div>
          <timeline-list class="scroll-container">
            <timeline-item
              v-for="(p, index) in store.timeline"
              :key="index"
              color="success"
              :icon="{ component: 'notifications', class: 'text-white text-sm' }"
              :title="p.name"
              :value="p.value + ' €'"
              :date-time="p.date"
              :description="p.descricao"
            />
          </timeline-list>
      </div>
      <div class="col">
      </div>
    </div>
  </div>



  <div class="container-fluid">
    <div class="row">
      <div class="col ">        
        <!--timeline next days-->
        <div class = "col pt-0">
          <div class="timeline-header">
            <h4>Agenda</h4>
          </div>
          <timeline-list class="scroll-container">
            <timeline-item
              color="success"
              :icon="{ component: 'notifications', class: 'text-white text-sm' }"
              title="Design changes"
              value="2400€"
              date-time="22 DEC 7:20 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="danger"
              :icon="{ component: 'code', class: 'text-white text-sm' }"
              title="Design changes"
              value="2400€"
              date-time="21 DEC 11 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="info"
              :icon="{ component: 'shopping_cart', class: 'text-white text-sm' }"
              title="Design changes"
              value="2400€"
              date-time="21 DEC 9:34 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="info"
              :icon="{ component: 'shopping_cart', class: 'text-white text-sm' }"
              title="Design changes"
              value="2400€"
              date-time="21 DEC 9:34 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="info"
              :icon="{ component: 'shopping_cart', class: 'text-white text-sm' }"
              title="Design changes"
              value="2400€"
              date-time="21 DEC 9:34 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
          </timeline-list>
        </div>
      </div>
      <div class="col">
        <div class="container-fluid py-0">
          <div class="row">
          </div>
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

export default {
  name: "schedule",
  components: { TimelineList, TimelineItem },
  setup() {
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

    const loadTimeline = async () => {
      try {
        await store.loadTimeline(2024, 6);
        console.log("Timeline carregada:", store.timeline);
      } catch (err) {
        alert("Erro -> " + err.message);
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
    };
  },
};
</script>

<style scoped>
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
    background-color: white;
  }
</style>