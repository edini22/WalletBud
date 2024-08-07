import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import Schedule from "../views/Schedule.vue";
import Dashboard from "../views/Dashboard.vue";
import Notifications from "../views/Notifications.vue";
import Profile from "../views/Profile.vue";
import SignIn from "../views/SignIn.vue";
import SignUp from "../views/SignUp.vue";
import TermsAndConditions from "../views/TermsAndConditions.vue";
import Payments from "../views/Payments.vue";
import Recuperacao from "../views/Recuperacao.vue";

/* Bloqueamento de Acessos
1) Não retirar os comentários, apenas estão para não obrigar a ter que fazer login sempre
2) Verificar o que é a página "rtl-page"
*/

const routes = [
  {
    path: "/",
    name: "/",
    redirect: "/home",
    meta: { requiresAuth: true },
  },
  {
    path: "/home",
    name: "Home",
    component: Home,
    meta: { requiresAuth: true },
  },
  {
    path: "/schedule",
    name: "Agenda",
    component: Schedule,
    meta: { requiresAuth: true },
  },
  {
    path: "/statistics",
    name: "Estatísticas",
    component: Dashboard,
    meta: { requiresAuth: true },
  },
  {
    path: "/notifications",
    name: "Notificações",
    component: Notifications,
    meta: { requiresAuth: true },
  },
  {
    path: "/profile",
    name: "Perfil",
    component: Profile,
    meta: { requiresAuth: true },
  },
  {
    path: "/sign-in",
    name: "SignIn",
    component: SignIn,
  },
  {
    path: "/sign-up",
    name: "SignUp",
    component: SignUp,
  },
  {
    path: "/terms-and-conditions",
    name: "Terms&Conditions",
    component: TermsAndConditions,
  },
  {
    path: "/dashboard",
    name: "Painel de Controlo",
    component: Payments,
    meta: { requiresAuth: true },
  },
  {
    path: "/recuperacao/:token/:email",
    name: "Recuperacao",
    component: Recuperacao,
    parameters: true,
  },
];

const isAuthenticated = () => {
  return localStorage.getItem("token") !== null;
};

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkActiveClass: "active",
});

// Global navigation guard

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // Check if user is authenticated
    if (!isAuthenticated()) {
      // If not authenticated, redirect to login page
      next('/sign-in');
    } else {
      // If authenticated, proceed with navigation
      next();
    }
  } else {
    // If the route doesn't require authentication, proceed with navigation
    next();
  }
});

export default router;
