<template>
    <div class="chart-container">
        <canvas ref="pieChart"></canvas>
    </div>
</template>

<script>
import {
    Chart as ChartJS,
    Title,
    Tooltip,
    Legend,
    ArcElement,
    CategoryScale,
    LinearScale,
} from 'chart.js';

ChartJS.register(Title, Tooltip, Legend, ArcElement, CategoryScale, LinearScale);

export default {
    props: {
        categories: {
            type: Object, // Alterado para tipo Object
            required: true,
        },
    },
    data() {
        return {
            chartInstance: null,
            datacollection: {
                labels: [],
                datasets: [
                    {
                        label: 'Gasto por Categoria',
                        backgroundColor: [],
                        data: [],
                    },
                ],
            },
            chartOptions: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        labels: {
                            color: 'white', // Cor das legendas
                            font: {
                                size: 14,
                            },
                        },
                    },
                },
            },
        };
    },
    mounted() {
        this.prepareData(); // Chama prepareData para configurar os dados
        this.renderChart(); // Chama renderChart para renderizar o gráfico
    },
    watch: {
        categories: {
            handler(newCategories) {
                // Limpa dados antigos
                this.datacollection.labels = [];
                this.datacollection.datasets[0].data = [];
                this.datacollection.datasets[0].backgroundColor = [];

                // Adiciona novos dados
                Object.keys(newCategories).forEach(key => {
                    this.datacollection.labels.push(key);
                    this.datacollection.datasets[0].data.push(newCategories[key]);
                    this.datacollection.datasets[0].backgroundColor.push(this.getRandomColor());
                });

                // Atualiza o gráfico
                if (this.chartInstance) {
                    this.chartInstance.data = this.datacollection;
                    this.chartInstance.update();
                }
            },
            deep: true // Observa mudanças profundas no objeto categories
        },
    },
    methods: {
        prepareData() {
            // Itera sobre as chaves do objeto categories
            Object.keys(this.categories).forEach(key => {
                this.datacollection.labels.push(key); // Adiciona a chave como label
                this.datacollection.datasets[0].data.push(this.categories[key]); // Adiciona o valor correspondente como dado
                this.datacollection.datasets[0].backgroundColor.push(this.getRandomColor()); // Gera uma cor aleatória para o fundo
            });
        },
        renderChart() {
            this.$nextTick(() => {
                const ctx = this.$refs.pieChart.getContext('2d');
                if (ctx) {
                    new ChartJS(ctx, {
                        type: 'pie',
                        data: this.datacollection,
                        options: this.chartOptions,
                    });
                }
            });
        },
        getRandomColor() {
            // Função para gerar cor aleatória
            return `hsl(${Math.floor(Math.random() * 360)}, 70%, 50%)`;
        },
    },
};
</script>

<style scoped>
.chart-container {
    position: relative;
    width: 100%;
    height: 200px;
}
</style>