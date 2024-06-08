import { createApp } from "vue";
import { createPinia }  from 'pinia';
import App from "./App.vue";
import store from "./store";
import router from "./router";
import "./assets/css/nucleo-icons.css";
import "./assets/css/nucleo-svg.css";
import MaterialDashboard from "./material-dashboard";
import i18n from './i18n';


const appInstance = createApp(App);
const pinia = createPinia();
appInstance.use(store);
appInstance.use(pinia);
appInstance.use(router);
appInstance.use(MaterialDashboard);
appInstance.use(i18n);

appInstance.mount("#app");
