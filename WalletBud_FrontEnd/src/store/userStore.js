// userStore.js
import { defineStore } from "pinia";

export const userStore = defineStore('user', {
  state: () => ({
    //user que está logged
    name: 'teste',
    email: 'teste@teste.com',
    senha: '123456',
  }),

  actions: {
    updateUser(editedUser) {
        this.name = editedUser.name;
        this.email = editedUser.email;
        this.senha = editedUser.senha;
    },
    async Registo(){
        const response = await fetch('http://localhost:3000/sign-up', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                name: this.name,
                email: this.email,
                senha: this.senha,
            }),
        });
        const data = await response.json();
        this.name = data.name;
        this.email = data.email;
        this.senha = data.senha;
    },
  },
});
