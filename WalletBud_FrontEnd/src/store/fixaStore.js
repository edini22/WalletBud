
import { defineStore } from "pinia";

export const fixaStore = defineStore('fixa', {
    state: () => ({
      pendentes: [],
      porPagar: [],
      proximosPagamentos: [],
      timeline: [],
    }),
    persist: true,
    actions: {
        resetFixas() {
          this.pendentes = [];
          this.porPagar = [];
          this.proximosPagamentos = [];
          this.timeline = [];
        },

        async deleteOrGiveUp(id,tipo) {

            const url = `http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/${tipo}/${id}`;
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

        formatTimestamp(timestamp) {
          const date = new Date(timestamp); // Cria um objeto Date com o timestamp
    
          // Função auxiliar para garantir dois dígitos (por exemplo, 09 em vez de 9)
          const pad = (num) => (num < 10 ? '0' : '') + num;
    
          const year = date.getFullYear();
          const month = pad(date.getMonth() + 1); // Meses em JavaScript são 0-11
          const day = pad(date.getDate());
    
          const hours = pad(date.getHours());
          const minutes = pad(date.getMinutes());
          const seconds = pad(date.getSeconds());
    
          return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
        },
        async payTransaction(id,date) {

          const req = {
            IdTransacao: id,
            date: this.formatTimestamp(date),
          };
          alert(req.date);
          alert(req.IdTransacao);
          const newtJSON = JSON.stringify(req); 

          const url = `http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/fixa/pay`;
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
          async loadTimeline(ano,mes) {
            const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/timeline/" + ano + "/" + mes;
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
              // name, sharevalue, data, descricao, tipo, rep
              //TODO: meter o shared value em vez do value PIKA PIKA PIKA PIKA PIKA
              this.timeline = data.timeline.map(cat => ({
                id: cat.id,
                name: cat.name,
                date: cat.date,
                value: cat.value,
                // shareValue: parseFloat(cat.shareValue.toFixed(2)),
                categoria: cat.categoria,
                tipo: cat.tipo,
                descricao: cat.descricao,
                repeticao: cat.repeticao,
                status: cat.status
              }));
          
            } catch (error) {
              console.error('Erro ao carregar pendentes:', error.message);
              throw error; 
            }
          },  

        async kickUser(mail,id,flag) {

          const newt = {
              IdTransacao: id,
              option: -1,
              email_shared: mail,
          };
          const newtJSON = JSON.stringify(newt);

          const url = `http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/${flag}/share`;
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
        async ShareTransactionWithUser(mail,id,tipo) {

          const newt = {
              IdTransacao: id,
              option: 1,
              email_shared: mail,
          };
          const newtJSON = JSON.stringify(newt);

          const url = `http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/${tipo}/share`;
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
    }
    
});