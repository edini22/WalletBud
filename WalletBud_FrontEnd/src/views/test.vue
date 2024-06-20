<template>
    <div>
      <button @click="connectWebSocket">Conectar WebSocket</button>
      <button @click="disconnectWebSocket">Desconectar WebSocket</button>
      <ul>
        <li v-for="notification in notifications" :key="notification.id">
          {{ notification.message }}
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import { mapActions, mapGetters } from 'vuex';
  
  export default {
    name: 'NotificationComponent',
    computed: {
      ...mapGetters('notifications', ['notifications']),
    },
    methods: {
      ...mapActions('notifications', ['connectWebSocket', 'disconnectWebSocket']),
    },
    created() {
      this.connectWebSocket(); // Conectar ao WebSocket quando o componente for criado
    },
    beforeUnmount() {
      this.disconnectWebSocket(); // Desconectar ao WebSocket quando o componente for destruído
    },
  };
  </script>
  
  <style scoped>
  /* Estilos opcionais */
  </style>
  