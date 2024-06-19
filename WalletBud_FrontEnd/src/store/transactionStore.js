import { defineStore } from "pinia";

export const useTransactionStore = defineStore('transaction', {
    state: () => ({
        transactions: [],
        stateReload: false
    }),
    actions: {
        async load(days) {
            if(days === 'Últimos 30 dias') {
              days = 30;
            } else if(days === 'Últimos 60 dias') {
              days = 60;
            } else if(days === 'Últimos 90 dias') {
              days = 90;
            } else{
              days = 30;
            }
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
                name: cat.name,
                value: parseFloat(cat.value.toFixed(2)),
                shareValue: parseFloat((cat.value / cat.users.length).toFixed(2)),
                date: cat.date,
                descricao: cat.descricao,
                categoria: cat.categoria,
                tipo: cat.tipo,
                local: cat.local,
                users: cat.users,
                repeticao: cat.repeticao ?? 0
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
                name: cat.name,
                value: parseFloat(cat.value.toFixed(2)),
                date: cat.date,
                descricao: cat.descricao,
                categoria: cat.categoria,
                tipo: cat.tipo,
                local: cat.local,
                users: cat.users,
                repeticao: cat.repeticao ?? 0
            }));
          
            } catch (error) {
              console.error('Erro ao carregar pendentes:', error.message);
              throw error; 
            }
          },
    }
});