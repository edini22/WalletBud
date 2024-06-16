
import { defineStore } from "pinia";

export const categoriesStore = defineStore('Category', {
    state: () => ({
        CategoriesIncome: [],
        CategoriesExpense: []
    }),
    persist: true,
    actions: {
        resetCategories() {
          this.CategoriesIncome = [];
          this.CategoriesExpense = [];
        },

        async addCategory(newCategory) {

            const newt = {
                name: newCategory.name,
                tipo: newCategory.tipo,
            };

            const newtJSON = JSON.stringify(newt); 

            const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/categoria/add";
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
        async removeCategory(CategoryID) {

            const url = `http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/categoria/${CategoryID}`;
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
        async load() {
            const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/categoria/list";
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
          
              // Mapear os dados recebidos para o formato de categorias desejado
              this.CategoriesIncome = data.categorias.filter(cat => cat.tipo === 'receita').map(cat => ({
                id: cat.id,
                name: cat.name,
                tipo: cat.tipo,
                isedit : false
              }));
              this.CategoriesExpense = data.categorias.filter(cat => cat.tipo === 'despesa').map(cat => ({
                  id: cat.id,
                  name: cat.name,
                  tipo: cat.tipo,
                  isedit : false
              }));
          
            } catch (error) {
              console.error('Erro ao carregar categorias:', error.message);
              throw error; 
            }
          },
        async editCategory(editedCategory,type) {
          let cate;
          if(type === 'income'){
            cate = this.CategoriesIncome.find(Category => Category.id === editedCategory.id);
          } else {
            cate = this.CategoriesExpense.find(Category => Category.id === editedCategory.id);
          }

          if(cate.name === editedCategory.name){
            return;
          }
          alert("passou");
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
    }
});