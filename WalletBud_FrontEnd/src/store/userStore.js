// userStore.js
import { defineStore } from "pinia";

export const userStore = defineStore("user", {
  state: () => ({
    //user que está logged
    username: "",
    email: "",
    password: "",
    id: 0,
    saldo : 0,
    idioma: "",

  }),

  actions: {
    updateUser(editedUser) {
      this.username = editedUser.username;
      this.email = editedUser.email;
      this.password = editedUser.password;
      this.id = editedUser.id;
      this.saldo = editedUser.saldo;
      this.idioma = editedUser.idioma;
    },

    setUser(user) {
      this.username = user.username;
      this.email = user.email;
      this.password = user.password;
      this.id = user.id;
      this.saldo = user.saldo;
      this.idioma = user.idioma;
    },

    async registUser(newUser) {
      const newUserJSON = JSON.stringify(newUser);
      const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/register";
      const request = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: newUserJSON,
      };

      const response = await fetch(url, request);

      // Verifica se a resposta não é OK
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Network response was not ok");
      }

      const data = await response.json();
      if (data.message === "Email Já registado!") {
        throw new Error("Email Já registado!");
      } else if (data.message === "Email address is invalid") {
        throw new Error("Email address is invalid");
      }

      // this.setUser(data); // Adiciona o novo usuário aos dados do store
    },

    async logUser(user) {
      const newUserJSON = JSON.stringify(user);
      const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/login";
      const request = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: newUserJSON,
      };

      const response = await fetch(url, request);

      // Verifica se a resposta não é OK
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message);
      }

      const data = await response.json();
      // if (data.message === "password does not match!") {
      //   throw new Error("password does not match!");
      // } else if (data.message === "Email does not exist!") {
      //   throw new Error("Email does not exist!");
      // }

      if (data.token) {
        localStorage.setItem("token", data.token);
      }
      this.password = user.password;

      },

      // this.setUser(data); // Adiciona o novo usuário aos dados do store
    

    async getUser() {
      const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/user";
      const token = localStorage.getItem('token');
      const request = {
        method: "GET",
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

      const data = await response.json();
      
      const user = {
        username: data.name,
        email: data.email,
        id: data.id,
        saldo: data.balanco,
        idioma: data.idioma,
        password: this.password
      };

      this.setUser(user); // Adiciona o novo usuário aos dados do store
    },

    async updateEditedUser(editedUser){

      const newUserJSON = JSON.stringify(editedUser);
      alert(newUserJSON);
      const url = "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/user/set";
      const token = localStorage.getItem('token');
      const request = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          'Authorization': `Bearer ${token}`
        },
        body: newUserJSON,
      };


      const response = await fetch(url, request);

      // Verifica se a resposta não é OK
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message);
      }

      if(editedUser.email && editedUser.email != this.email){
        let user = {
          email: editedUser.email,
          password: this.password,
        };
        this.email = editedUser.email;
        this.logUser(user);
      }
      if(editedUser.password && editedUser.password != this.password){
        this.password = editedUser.password;
      }
      if(editedUser.idioma && editedUser.idioma != this.idioma){
        this.idioma = editedUser.idioma;
      }
      if(editedUser.username && editedUser.username != this.username){
        this.username = editedUser.username;
      }
    },

  },
  }
);
