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

export default {
  setup() {
    const { locale, t } = useI18n();

    locale.value = 'pt'; // Idioma padrão

    const changeLanguage = (language) => {
      locale.value = language;
    };

    return {
      t,
      changeLanguage
    };
  },

  computed: {
    languages() {
      return ['Português', 'English']; // Lista de idiomas disponíveis
    }
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