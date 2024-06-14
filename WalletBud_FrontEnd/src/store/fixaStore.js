
import { defineStore } from "pinia";

export const fixaStore = defineStore('fixa', {
    state: () => ({
      pendentes: [],
      porPagar: [],
      proximosPagamentos: [],
    }),
    persist: true,
    actions: {
        resetFixas() {
          this.pendentes = [];
          this.porPagar = [];
          this.proximosPagamentos = [];
        },

        async deletePendente(id) {

            let transaction = this.pendentes.find(transaction => transaction.id === id);

            const url = `http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/${transaction.transacao}/${id}`;
            const token = localStorage.getItem('token');
            
            const request = {
                method: "DELETE",
                headers: {
                  "Content-Type": "application/json",
                  'Authorization': `Bearer ${token}`
                },
            };
            const response = await fetch(url, request);

            // Verifica se a resposta não é OK
            if (!response.ok) {
              const errorData = await response.json();
              throw new Error(errorData.message);
            }
        },
        async acceptorRejectPendente(id,state) {

          console.log(state);

            let transaction = this.pendentes.find(transaction => transaction.id === id);

            const req = {
              IdTransacao: id,
              option: state,
          };

          const newtJSON = JSON.stringify(req); 

          const url = `http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/${transaction.transacao}/confirm`;
          const token = localStorage.getItem('token');
          const request = {
              method: "POST",
              headers: {
                  "Content-Type": "application/json",
                  'Authorization': `Bearer ${token}`
                },
              body: newtJSON
          };
          const response = await fetch(url, request);

            // Verifica se a resposta não é OK
            if (!response.ok) {
              const errorData = await response.json();
              throw new Error(errorData.message);
            }
        },
        async loadPendentes() {
            const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/pendentes";
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
              this.pendentes = data.pendentes.map(cat => ({
                id: cat.id,
                name: cat.name,
                value: cat.value,
                shareValue: parseFloat(cat.shareValue.toFixed(2)), // Arredonda para duas casas decimais
                date: cat.date,
                descricao: cat.descricao,
                categoria: cat.categoria,
                status: cat.status,
                tipo: cat.tipo,
                local: cat.local,
                transacao: cat.transacao,
                users: cat.users,
                repeticao: cat.transacao === 'fixa' ? cat.repeticao : undefined
            }));
          
            } catch (error) {
              console.error('Erro ao carregar pendentes:', error.message);
              throw error; 
            }
          },

          async loadPorPagar() {
            const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/fixa/transacoesAtraso";
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
              this.porPagar = data.atrasos.map(cat => ({
                id: cat.id,
                name: cat.name,
                value: cat.value,
                shareValue: parseFloat(cat.shareValue.toFixed(2)),
                date: cat.date,
                categoria: cat.categoria,
                tipo: cat.tipo,
                repeticao: cat.repeticao
              }));
          
            } catch (error) {
              console.error('Erro ao carregar pendentes:', error.message);
              throw error; 
            }
          },

          async loadProximosPagamentos() {
            const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/fixa/pagamentos";
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
              this.proximosPagamentos = data.proximos_pagamentos.map(cat => ({
                id: cat.id,
                name: cat.name,
                value: cat.value,
                shareValue: parseFloat(cat.sharevalue.toFixed(2)),
                date: cat.date,
                categoria: cat.categoria,
                tipo: cat.tipo,
                repeticao: cat.repeticao
              })); 
          
            } catch (error) {
              console.error('Erro ao carregar pendentes:', error.message);
              throw error; 
            }
          },

        async editCategory(editedCategory) {

          const newt = {
              name: editedCategory.name,
              id: editedCategory.id,
          };
          const newtJSON = JSON.stringify(newt);

          const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/categoria/set";
          const token = localStorage.getItem('token');
          const request = {
              method: "POST",
              headers: {
                  "Content-Type": "application/json",
                  'Authorization': `Bearer ${token}`
                },
              body: newtJSON
          };
          const response = await fetch(url, request);

          // Verifica se a resposta não é OK
          if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message);
          }
        },
        // getCategoriesByID(id) {
        //     return this.Categories.find(Category => Category.id === id);
        // }
    }
});