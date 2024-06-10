<template>
    <div class="container-fluid py-4">
    <div class="row">
      <div class="col-md-8 mx-auto">
        <div class="card mt-4">
          <div class="card-header p-3">
            <h2 class="mb-0">{{ $t('Termos e Condições')}}</h2>
          </div>
          <div class="card-body p-3 pb-0">
            <p>
                {{ $t('Estes Termos e Condições regem o uso deste Serviço fornecido pelo Grupo de Trabalho')}} nº7.
            <br>
                {{ $t('Por favor, leia estes Termos cuidadosamente antes de utilizar o Serviço')}}.
            </p>

            <h4>1. {{$t('Aceitação destes Termos')}}</h4>
            <p><span class="font-weight-bold text-dark">1.1</span>
                {{ $t ('Ao aceder ou ao usar o Serviço de qualquer forma, o utilizador concorda em ficar vinculado por estes Termos. Caso não concorde com qualquer parte dos Termos, o utilizador não está autorizado a utilizar o Serviço') }}.
            </p>

            <h4>2. {{ $t ('Uso do Serviço') }}</h4>
            <p>
                <span class="font-weight-bold text-dark">2.1</span> {{ $t ('O utilizador deve ter pelo menos 12 anos de idade para utilizar este Serviço') }}.
            <br>
                <span class="font-weight-bold text-dark">2.2</span> {{ $t ('O utilizador concorda em utilizar o Serviço apenas para fins legais e de acordo com estes Termos') }}.
            </p>

            <h4>3. {{ $t ('Responsabilidades do Utilizador') }}</h4>
            <span class="font-weight-bold text-dark">3.1 {{ $t ('O utilizador concorda em não fazer o seguinte') }}:</span>
            <p><span class="font-weight-bold text-dark">3.1.1</span>
                {{ $t ('Publicar, enviar por e-mail ou transmitir de outra forma qualquer conteúdo que seja ilegal, prejudicial, difamatório, abusivo, ameaçador, assediante, obsceno, vulgar, invasivo da privacidade de outra pessoa, odioso ou racialmente, etnicamente ou de outra forma ofensivo') }}.
            <br>
                <span class="font-weight-bold text-dark">3.1.2</span>
                {{ $t ('Praticar qualquer atividade que interfira ou interrompa a funcionalidade do Serviço ou de qualquer servidor ou rede conectada ao Serviço') }}.
            </p>
            
            <h4>4. {{ $t ('Modificações nos Termos') }}</h4>
            <p><span class="font-weight-bold text-dark">4.1</span>
                {{ $t ('Reservamo-nos o direito, a nosso critério exclusivo, de modificar ou substituir estes Termos a qualquer momento. Se uma revisão fôr significativa, tentaremos fornecer um aviso com pelo menos cinco dias de antecedência antes que os novos termos entrem em vigor') }}.
            </p>

            <h4>5. {{ $t ('Contacto') }}</h4>
            <p>
                {{ $t ('Se tiver alguma dúvida sobre estes Termos, pode entrar em contato conosco através do e-mail') }}: <span class="font-weight-bold text-dark">walletbud@gmail.com</span>.
            <br>
                {{ $t ('Estes Termos e Condições de Uso foram atualizados pela última vez a') }} <span class="font-weight-bold text-dark">{{ $t('17 de junho de 2024') }}</span>.
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
  
<script>
import { useI18n } from 'vue-i18n';

export default {
    name: "Terms&Conditions",
    setup() {
        const { t } = useI18n();
        return { t };
    }
};
</script>

<style scoped>
p {
    margin-left: 0.5rem;
}
</style>