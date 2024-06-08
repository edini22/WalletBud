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

    async registUser(newUser){
      
      const newUserJSON = JSON.stringify(newUser);
      //alert(newUserJSON);

      const url = "http://localhost:3000/sign-up";
      const request = {
          method: "POST",
          headers: {
              "Content-Type": "application/json"
          },
          body: newUserJSON
      };
      fetch(url, request)
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response not ok");
            }
            return response.json();
        })
        .then(data => this.users.push(data))
        .catch(error => console.error("Error adding user", error));
    },

    async logUser(user){
      const userJSON = JSON.stringify(user);
      //alert(userJSON);

      const url = "http://localhost:3000/sign-in";
      const request = {
          method: "POST",
          headers: {
              "Content-Type": "application/json"
          },
          body: userJSON
      };
      fetch(url, request)
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response not ok");
            }
            return response.json();
        })
        .then(data => this.users.push(data))
        .catch(error => console.error("Error adding user", error));
    },

  },
});
