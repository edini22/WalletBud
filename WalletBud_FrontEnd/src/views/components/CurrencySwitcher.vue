<template>
    <form>
      <fieldset>
        <select @change="currencySelected" class="selector">
          <option v-for="currency in currencies" :key="currency">{{ currency }}</option>
        </select>
      </fieldset>
    </form>
</template>

<script>

import { userStore } from '@/store/userStore';
import { useRouter } from 'vue-router';

export default {
  name: "CurrencySwitcher",
  setup() {
    const store = userStore(); //guardar a moeda
    const router = useRouter(); //navegar entre as páginas
    //const currency = store.$state.moeda;

    const changeCurrency = (currency) => {
      
      const user = {
        moeda: currency,
      };

      updateMoeda(user);
      store.moeda = currency;
    };

    const updateMoeda = async (user) => {
      try {
        await store.updateEditedUser(user);
        await store.getUser();
        // if (store){
        //   currency.value = store.$state.moeda;
        // }
      } catch (err) {
        if (err.message.includes('token')) {
            alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

            localStorage.clear();
            sessionStorage.clear();

            router.push('/sign-in');
        } else {
          alert("Erro -> " + err.message);
        }
      }
    };
      const getCurrencies = () => {
        if (store.$state.moeda === 'EUR') {
          return ['EUR', 'USD'];
        } else {
          return ['USD', 'EUR'];
        }
      };

      const currencies = getCurrencies();

    return {
      changeCurrency,
      currencies,
    };
  },
  methods: {
    currencySelected(event) {
      const selected = event.target.value;
      this.$emit('currencyChange', selected);
      this.changeCurrency(selected);
    }
  }
};
</script>

  
  <style scoped>
  .selector {
    font-weight: bold;
    padding: 0.5rem;
    font-size: 0.8rem;
    border: 1px solid #344767; /* Cor da borda */
    color: #344767;
    border-radius: 0.5rem; /* Borda arredondada */
    cursor: pointer;
  }
  </style>