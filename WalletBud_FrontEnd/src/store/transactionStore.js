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
                description: 'Aluguer do apartamento',
                local: 'Imobiliária ABC',
                tipo: 'Despesa',
                categoria: 'Pessoal'
            },
            {
                id: 7,
                name: "Consulta Médica",
                value: 120,
                date: "10 Maio 2024",
                description: "Consulta com o Dr. Silva",
                local: "Clínica São José",
                tipo: "Despesa",
                categoria: "Saúde"
            },
            {
                id: 8,
                name: "Supermercado",
                value: 250,
                date: "12 Maio 2024",
                description: "Compras do mês",
                local: "Supermercado XYZ",
                tipo: "Despesa",
                categoria: "Alimentação"
            },
            {
                id: 9,
                name: "Mensalidade pacote tv",
                value: 100,
                date: "15 Maio 2024",
                description: "Mensalidade de maio",
                local: "Vodafone",
                tipo: "Despesa",
                categoria: "Pessoal"
            },
            {
                id: 10,
                name: "Roupas",
                value: 200,
                date: "18 Maio 2024",
                description: "Compras de roupas",
                local: "Loja de Roupas Moda",
                tipo: "Despesa",
                categoria: "Familiar"
            },
            {
                id: 11,
                name: "Cinema",
                value: 50,
                date: "20 Maio 2024",
                description: "Ida ao cinema",
                local: "BragaParque",
                tipo: "Despesa",
                categoria: "Entretenimento"
            },
            {
                id: 12,
                name: "Remédios",
                value: 80,
                date: "27 Maio 2024",
                description: "Compra de remédios",
                local: "Farmácia ABC",
                tipo: "Despesa",
                categoria: "Saúde"
            },
            {
                id: 13,
                name: "Restaurante",
                value: 100,
                date: "30 Maio 2024",
                description: "Jantar fora",
                local: "Restaurante Bom Sabor",
                tipo: "Despesa",
                categoria: "Alimentação"
            },
            {
                id: 14,
                name: "Seguro de Vida",
                value: 200,
                date: "01 Junho 2024",
                description: "Pagamento do seguro",
                local: "Seguradora Vida",
                tipo: "Despesa",
                categoria: "Pessoal"
            },
            {
                id: 15,
                name: "Material Escolar",
                value: 150,
                date: "05 Junho 2024",
                description: "Compra de material escolar",
                local: "Papelaria Estudante",
                tipo: "Despesa",
                categoria: "Familiar"
            },
            {
                id: 16,
                name: "Assinatura Netflix",
                value: 40,
                date: "10 Junho 2024",
                description: "Assinatura mensal",
                local: "Netflix",
                tipo: "Despesa",
                categoria: "Entretenimento"
            },
            {
                id: 17,
                name: "Exames Médicos",
                value: 500,
                date: "20 Junho 2024",
                description: "Exames de rotina",
                local: "Laboratório São Paulo",
                tipo: "Despesa",
                categoria: "Saúde"
            },
            {
                id: 18,
                name: "Mercado",
                value: 300,
                date: "25 Junho 2024",
                description: "Compras de alimentos",
                local: "Mercado Central",
                tipo: "Despesa",
                categoria: "Alimentação"
            },
            {
                id: 19,
                name: "Cabeleireiro",
                value: 70,
                date: "30 Junho 2024",
                description: "Corte de cabelo",
                local: "Salão Beleza",
                tipo: "Despesa",
                categoria: "Pessoal"
            },
            {
                id: 20,
                name: "Parque de Diversões",
                value: 150,
                date: "05 Julho 2024",
                description: "Passeio no parque",
                local: "Parque Diversão Feliz",
                tipo: "Despesa",
                categoria: "Entretenimento"
            },
            {
                id: 21,
                name: "Jogo de Futebol",
                value: 80,
                date: "10 Julho 2024",
                description: "Bilhetess para o jogo",
                local: "Estádio Nacional",
                tipo: "Despesa",
                categoria: "Entretenimento"
            },
            {
                id: 22,
                name: "Salário",
                value: 3000,
                date: "30 Maio 2024",
                description: "Recebimento de salário",
                local: "Empresa ABC",
                tipo: "Receita",
                categoria: "Salário"
            },
            {
                id: 23,
                name: "Aluguer Recebido",
                value: 1500,
                date: "05 Junho 2024",
                description: "Recebimento de aluguer",
                local: "Inquilino João",
                tipo: "Receita",
                categoria: "Renda"
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
        getTransactionsByID(id) {
            return this.transactions.find(transaction => transaction.id === id);
        }
    }
});