import { defineStore } from "pinia";

export const useTransactionStore = defineStore('transaction', {
    state: () => ({
        transactions: [
            {
                id: 1,
                name: 'Compra de Livro',
                valor: 20.50,
                date: '2022-05-23',
                description: 'Livro sobre JavaScript',
                local: 'LivroStore',
                tipo: 'Despesa',
                categoria: 'Entretenimento',
                repeticao: 1,
                comentario: 'bdsnaskdnd',
                users: [ 
                  {id: 1,
                  name: "inês"
                  }

                ]
            },
            {
                id: 2,
                name: 'Pagamento de Conta',
                valor: 50,
                date: '23 Abril 2024',
                description: null,
                local: 'Empresa de Energia',
                tipo: 'Receita',
                categoria: 'Saúde',
                repeticao: 0,
                comentario: null,
                users: [ 
                  {id: 1,
                  name: "inês"
                  }

                ]
            },
            {
                id: 3,
                name: 'Depósito Bancário',
                valor: 1000,
                date: '20 Abril 2024',
                description: 'Depósito salário',
                local: 'Banco XPTO',
                tipo: 'Despesa',
                categoria: 'Pessoal',
                repeticao: 3,
                comentario: 'ocsnjkancjasc',
                users: [ 

                ]
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
        async load(days) {
            const url = `http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/movimentos/${days}`;
            const token = localStorage.getItem('token');
          
            const request = {
              method: "GET",
              headers: {
                "Content-Type": "application/json",
                'Authorization': `Bearer ${token}`
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
              this.transactions = data.movimentos.map(cat => ({
                id: cat.id,
                id_tf: cat.id_tf ? cat.id_tf : -1,
                name: cat.name,
                value: parseFloat(cat.value.toFixed(2)),
                date: cat.date,
                description: cat.description,
                categoria: cat.categoria,
                tipo: cat.tipo,
                local: cat.local,
            }));
          
            } catch (error) {
              console.error('Erro ao carregar pendentes:', error.message);
              throw error; 
            }
          },
          async loadAll() {
            const url = `http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/movimentos`;
            const token = localStorage.getItem('token');
          
            const request = {
              method: "GET",
              headers: {
                "Content-Type": "application/json",
                'Authorization': `Bearer ${token}`
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
              this.transactions = data.movimentos.map(cat => ({
                id: cat.id,
                id_tf: cat.id_tf ? cat.id_tf : -1,
                name: cat.name,
                value: parseFloat(cat.value.toFixed(2)),
                date: cat.date,
                description: cat.description,
                categoria: cat.categoria,
                tipo: cat.tipo,
                local: cat.local,
            }));
          
            } catch (error) {
              console.error('Erro ao carregar pendentes:', error.message);
              throw error; 
            }
          },
        getTransactionsByID(id) {
            return this.transactions.find(transaction => transaction.id === id);
        }
    }
});