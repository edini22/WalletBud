// userStore.js
import { defineStore } from "pinia";

export const userStore = defineStore('user', {
  state: () => ({
    //user que está logged
    username: '',
    email: '',
    password: '',
  }),

  actions: {
    updateUser(editedUser) {
      this.username = editedUser.name;
      this.email = editedUser.email;
      this.password = editedUser.senha;
    },

    setUser(user){
      this.username = user.name;
      this.email = user.email;
      this.password = user.senha;
    },

    async registUser(newUser){

      const newUserJSON = JSON.stringify(newUser);
      const url = "http://localhost:8080/WalletBud-1.0-SNAPSHOT/api/register";
      const request = {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: newUserJSON
      };

      const response = await fetch(url, request);
      
      // Verifica se a resposta não é OK
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Network response was not ok');
      }

      const data = await response.json();
      if (data.message === "Email Já registado!") {
        throw new Error("Email Já registado!");
      } else if (data.message === "Email address is invalid") {
        throw new Error("Email address is invalid");
      }

      this.setUser(data); // Adiciona o novo usuário aos dados do store
      
    },

    async logUser(user) {

    const newUserJSON = JSON.stringify(user);
      const url = "http://localhost:8080/WalletBud-1.0-SNAPSHOT/api/login";
      const request = {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: newUserJSON
      };

      const response = await fetch(url, request);
      
      // Verifica se a resposta não é OK
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message );
      }

      const data = await response.json();
      // if (data.message === "password does not match!") {
      //   throw new Error("password does not match!");
      // } else if (data.message === "Email does not exist!") {
      //   throw new Error("Email does not exist!");
      // }

      if(data.token){
        localStorage.setItem('token', data.token);
      }

      this.setUser(data); // Adiciona o novo usuário aos dados do store
      
    },

  },
});
