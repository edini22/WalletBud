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
            type: Object,
            required: true,
        },
    },
    data() {
        return {
            chartInstance: null,
            colors: [
                '#d63384', '#e91e63', '#7b809a', '#4caf50', '#1a73e8',
                '#fb8c00', '#f44335', '#0dcaf0', '#20c997', '#81e6d9',
                '#fbd38d', '#fd7e14', '#f56565', '#6f42c1', '#596cff',
                '#63b3ed'
            ],
            usedColors: [],
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
                            color: 'white',
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
        this.prepareData();
        this.renderChart();
    },
    beforeUnmount() {
        if (this.chartInstance) {
            this.chartInstance.destroy();
            this.chartInstance = null; // Certifique-se de que a instância é zerada
        }
    },
    watch: {
        categories: {
            handler(newCategories) {
                this.updateChart(newCategories);
            },
            deep: true
        },
    },
    methods: {
        prepareData() {
            this.datacollection.labels = [];
            this.datacollection.datasets[0].data = [];
            this.datacollection.datasets[0].backgroundColor = [];
            this.usedColors = [];

            Object.keys(this.categories).forEach(key => {
                this.datacollection.labels.push(key);
                this.datacollection.datasets[0].data.push(this.categories[key]);
                this.datacollection.datasets[0].backgroundColor.push(this.getColor());
            });
        },
        renderChart() {
            this.$nextTick(() => {
                const ctx = this.$refs.pieChart.getContext('2d');
                if (this.chartInstance) {
                    this.chartInstance.destroy(); // Destroi a instância do gráfico anterior
                }
                this.chartInstance = new ChartJS(ctx, {
                    type: 'pie',
                    data: this.datacollection,
                    options: this.chartOptions,
                });
            });
        },
        getColor() {
            if (this.usedColors.length < this.colors.length) {
                let color;
                do {
                    color = this.colors[Math.floor(Math.random() * this.colors.length)];
                } while (this.usedColors.includes(color));
                this.usedColors.push(color);
                return color;
            } else {
                let color;
                do {
                    color = this.getRandomColor();
                } while (this.usedColors.includes(color));
                this.usedColors.push(color);
                return color;
            }
        },
        getRandomColor() {
            return `hsl(${Math.floor(Math.random() * 360)}, 70%, 50%)`;
        },
        updateChart(newCategories) {
            this.datacollection.labels = [];
            this.datacollection.datasets[0].data = [];
            this.datacollection.datasets[0].backgroundColor = [];
            this.usedColors = [];

            Object.keys(newCategories).forEach(key => {
                this.datacollection.labels.push(key);
                this.datacollection.datasets[0].data.push(newCategories[key]);
                this.datacollection.datasets[0].backgroundColor.push(this.getColor());
            });

            this.renderChart(); // Renderiza um novo gráfico com os novos dados
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
