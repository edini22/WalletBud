<template>
  <form>
    <fieldset>
      <select @change="languageSelected" class="selector">
        <option v-for="lang in languages" :key="lang">{{ lang }}</option>
      </select>
    </fieldset>
  </form>
</template>

<script>
import { useI18n } from 'vue-i18n';
import { userStore } from '@/store/userStore';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const { locale, t } = useI18n();
    const store = userStore();

    const router = useRouter(); 
    
    //locale.value === Idioma padrão
    locale.value = store.idioma;
    
    const changeLanguage = (language) => {

      locale.value = language;
      const user = {
        idioma: language,
      };

      if(localStorage.getItem('token') !== null){
        updateLang(user);
        store.idioma = language;
      }else{
        store.idioma = language;
      }
    };

    const updateLang = async (user) => {
      try {
        await store.updateEditedUser(user);
        await store.getUser();
        if (store){
          locale.value = store.$state.idioma;
        }
      } catch (err) {
        if (err.message.includes('token')) {
            alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

            localStorage.clear();
            sessionStorage.clear();

            router.push('/sign-in');
        } else {
          console.error(err);
        }
      }
    };

    const getLanguages = () => {
      if (store.$state.idioma === 'en') {
        return ['English', 'Português'];
      } else {
        return ['Português', 'English'];
      }
    }

    const languages = getLanguages();

    return {
      t,
      changeLanguage,
      languages,
    };
  },
  methods: {
    languageSelected(event) {
      let selected = event.target.value;
      if (selected === 'Português') {
        selected = 'pt';
      } else if (selected === 'English') {
        selected = 'en';
      }
      this.$emit('language', selected);
      this.changeLanguage(selected);
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