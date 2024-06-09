<template>
    <div>
        <button class="btn btn-default bg-gradient-info"
            style="padding: 10px 10px; line-height: 19px; border-radius: 0.375rem; height: 40px;" @click="triggerDownload">
            <i class="material-icons">file_download</i>
        </button>
    </div>
</template>

<script>
import { useTransactionStore } from '@/store/transactionStore.js';

export default {
    data() {
        return {
            csvFields: {
                id: 'Id',
                name: 'Nome',
                value: 'Valor',
                date: 'Data',
                description: 'Descrição',
                local: 'Local',
                tipo: 'Tipo',
                categoria: 'Categoria'
            }
        };
    },
    computed: {
        jsonData() {
            const tStore = useTransactionStore();
            return tStore.transactions;
        },
    },
    methods: {
        triggerDownload() {
            // Converter dados em CSV
            const csvData = this.convertToCSV(this.jsonData, this.csvFields);
            this.downloadCSV(csvData, 'Movimentos.csv');
        },
        convertToCSV(data, fields) {
            const csvRows = [];
            const headers = Object.values(fields);

            csvRows.push(headers.join(';'));

            for (const item of data) {
                const row = Object.keys(fields).map(field => JSON.stringify(item[field], (key, value) => (value === null ? '' : value)));
                csvRows.push(row.join(';'));
            }
            return csvRows.join('\n');
        },
        downloadCSV(csvData, filename) {
            const blob = new Blob([csvData], { type: 'text/csv;charset=utf-8;' });
            const link = document.createElement('a');
            const url = URL.createObjectURL(blob);

            link.setAttribute('href', url);
            link.setAttribute('download', filename);
            link.style.visibility = 'hidden';
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        }
    }
};
</script>