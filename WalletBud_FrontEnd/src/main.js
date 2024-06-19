import { createApp } from "vue";
import { createPinia }  from 'pinia';
import App from "./App.vue";
import store from "./store";
import router from "./router";
import "./assets/css/nucleo-icons.css";
import "./assets/css/nucleo-svg.css";
import MaterialDashboard from "./material-dashboard";
import i18n from './i18n';
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate';
import CanvasJSChart from '@canvasjs/vue-charts';





const appInstance = createApp(App);
const pinia = createPinia();

pinia.use(piniaPluginPersistedstate);
appInstance.use(store);
appInstance.use(pinia);
appInstance.use(router);
appInstance.use(CanvasJSChart);
appInstance.use(MaterialDashboard);
appInstance.use(i18n);


appInstance.mount("#app");