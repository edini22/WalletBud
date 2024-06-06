import { createI18n } from 'vue-i18n';
import en from './languages/en.json';
import pt from './languages/pt.json';

const i18n = createI18n({
  legacy: false,
  locale: localStorage.getItem('lang') || 'pt',
  fallbackLocale: 'pt',
  messages: {
    en,
    pt,
  },
});

export default i18n;
