<template>
    <div class="container d-flex flex-column" style="height: 100vh;">
        <div class="row justify-content-center">
            <div class="timeline-header mb-4 d-flex justify-content-between align-items-center">
                <h4 class="mb-0">{{ $t('Agenda') }}</h4>
                <div class="d-flex align-items-center">
                    <!-- time period -->
                    <MaterialDropdown :options="months" v-model:selected="selectedMonth"
                    class="me-2" style="width: 110px;" />
                    <MaterialDropdown :options="years" v-model:selected="selectedYear"
                        class="me-2" style="width: 80px;" />
                </div>
            </div>
        </div>

        <div class="scroll-container flex-grow-1">
            <div v-if="store.timeline.length > 0">
                <timeline-list class="cuter">
                    <timeline-item v-for="(p, index) in store.timeline" :key="index"
                        :color="p.tipo === 'receita' ? 'success' : 'danger'"
                        :icon="{ component: p.tipo === 'receita' ? 'notifications' : 'shopping_cart', class: 'text-white text-sm' }"
                        :title="p.name"
                        :value="p.tipo === 'receita' ? (p.value + ' ' + user.moeda) : ('-' + p.value + ' ' + user.moeda)"
                        :date-time="p.date" :description="p.descricao"
                        :date-color="p.tipo === 'receita' ? 'success' : 'danger'" />
                </timeline-list>
            </div>
            <div v-else class="d-flex justify-content-center align-items-center" style="height: 70vh;">
                <h5 class="text-secondary">{{ $t('Sem eventos agendados') }}</h5>
            </div>
        </div>
    </div>
</template>


<script>
import TimelineList from "@/examples/Cards/TimelineList.vue";
import TimelineItem from "@/examples/Cards/TimelineItem.vue";
import MaterialDropdown from "@/components/MaterialDropdown.vue";
import { ref, onMounted, watch } from 'vue';
import { fixaStore } from "@/store/fixaStore";
import { userStore } from "@/store/userStore";
import { useRouter } from 'vue-router';
import { useI18n } from "vue-i18n";

export default {
    name: "schedule",
    components: { TimelineList, TimelineItem, MaterialDropdown },
    setup() {
        const { t, locale } = useI18n();

        const snackbar = ref(false);
        const store = fixaStore();
        const user = userStore();
        const router = useRouter();

        // Array de meses inicial
        let months = [
            t('Janeiro'),
            t('Fevereiro'),
            t('Março'),
            t('Abril'),
            t('Maio'),
            t('Junho'),
            t('Julho'),
            t('Agosto'),
            t('Setembro'),
            t('Outubro'),
            t('Novembro'),
            t('Dezembro')
        ];

        // Observa mudanças no locale
        watch(locale, () => {
            // Atualiza cada elemento do array individualmente
            months.forEach((_, index) => {
                months[index] = t(months[index]);
            });

            // Opcional: Atualiza o mês selecionado
            selectedMonth.value = months[new Date().getMonth()];
        });

        // Variável reativa para o mês selecionado
        const selectedMonth = ref(months[new Date().getMonth()]);


        const years = Array.from({ length: 10 }, (_, i) => new Date().getFullYear() - 5 + i);
        const selectedYear = ref(new Date().getFullYear());

        const loadTimeline = async () => {
            try {
                let month = months.indexOf(selectedMonth.value);
                console.log("Carregando timeline...");
                console.log("Ano: " + selectedYear.value);
                console.log("Mês: " + month);
                await store.loadTimeline(selectedYear.value, month + 1);
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

        // Watchers para observar mudanças nos dropdowns
        watch(selectedMonth, () => {
            console.log('Selected month changed:', selectedMonth.value);
            loadTimeline();
        });

        watch(selectedYear, () => {
            console.log('Selected year changed (watch):', selectedYear.value);
            loadTimeline();
        });

        onMounted(() => {
            loadTimeline();
        });

        return {
            loadTimeline,
            snackbar,
            store,
            user,
            selectedMonth,
            selectedYear,
            months,
            years,
            t,
        };
    },
};
</script>

<style scoped>
.scroll-container {
    max-height: 80vh;
    overflow-y: auto;
}

.scroll-container::-webkit-scrollbar,
.scroll-container2::-webkit-scrollbar {
    width: 8px;
}

.scroll-container::-webkit-scrollbar-track,
.scroll-container2::-webkit-scrollbar-track {
    border-radius: 10px;
}

.scroll-container::-webkit-scrollbar-thumb,
.scroll-container2::-webkit-scrollbar-thumb {
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
