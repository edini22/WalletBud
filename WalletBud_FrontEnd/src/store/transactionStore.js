import { defineStore } from "pinia";

export const useTransactionStore = defineStore('transaction', {
    state: () => ({
        transactions: [
            {
                id: 1,
                name: 'Compra de Livro',
                value: 20.50,
                date: '27 Abril 2024',
                description: 'Livro sobre JavaScript',
                local: 'LivroStore',
                tipo: 'Despesa',
                categoria: 'Alimentação'
            },
            {
                id: 2,
                name: 'Pagamento de Conta',
                value: 50,
                date: '23 Abril 2024',
                description: 'Conta de luz',
                local: 'Empresa de Energia',
                tipo: 'Receita',
                categoria: 'Saúde'
            },
            {
                id: 3,
                name: 'Depósito Bancário',
                value: 1000,
                date: '20 Abril 2024',
                description: 'Depósito salário',
                local: 'Banco XPTO',
                tipo: 'Despesa',
                categoria: 'Pessoal'
            },
            {
                id: 4,
                name: 'Compra de Roupas',
                value: 80,
                date: '15 Abril 2024',
                description: 'Roupas de verão',
                local: 'Fashion Store',
                tipo: 'Despesa',
                categoria: 'Pessoal'
            },
            {
                id: 5,
                name: 'Salário',
                value: 2000,
                date: '30 Maio 2024',
                description: 'Salário mensal',
                local: 'Empresa XYZ',
                tipo: 'Receita',
                categoria: 'Renda'
            },
            {
                id: 6,
                name: 'Aluguer',
                value: 800,
                date: '05 Maio 2024',
                description: 'Aluguel do apartamento',
                local: 'Imobiliária ABC',
                tipo: 'Despesa',
                categoria: 'Pessoal'
            }
        ]
    }),
    actions: {
        addTransaction(newTransaction) {

            const newt = {
                id: this.transactions.map(transaction => transaction.id).reduce((a, b) => Math.max(a, b), 0) + 1,
                name: newTransaction.name,
                value: newTransaction.year,
                date: newTransaction.platform,
                description: newTransaction.descricao,
                local: newTransaction.local
            };

            this.transactions.push(newt);

            const newtJSON = JSON.stringify(newt, (k, v) => (k === "id") ? new String(v) : v); // Convert id to string
            alert(newtJSON);

            const url = "";
            const request = {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: newtJSON
            };
            fetch(url, request)
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Network response not ok");
                    }
                    return response.json();
                })
                .then(data => this.transactions.push(data))
                .catch(error => console.error("Error adding transaction", error));
        },
        removeTransaction(transactionID) {
            console.log(this.transactions);

            const url = `http://localhost:3000/transactions/${transactionID}`;
            const request = {
                method: "DELETE"
            };
            fetch(url, request)
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Network response not ok");
                    }
                    return response.json();
                })
                .then(() => this.transactions = this.transactions.filter(transaction => transaction.id !== transactionID))
                .catch(error => console.error("Error deleting transaction", error));

            console.log(this.transactions);
        },
        load() {
            fetch("")
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Network response not ok");
                    }
                    return response.json();
                })
                .then(data => {
                    this.transactions = data;
                })
                .catch(error => console.error("Error loading transactions", error));
        },
        getTransactionsByID(id) {
            return this.transactions.find(transaction => transaction.id === id);
        }
    }
});