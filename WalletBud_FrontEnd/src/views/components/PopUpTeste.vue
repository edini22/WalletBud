<template>
    <div class="modal fade" id="popupteste" tabindex="-1" aria-labelledby="transactionModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <p>id : {{ id }}</p>
                <p>popupFixas : {{ popupFixas }}</p>
                <div v-if="transaction">
                    <p>transaction : {{ transaction }}</p>
                </div>
                <div v-if="popupFixas" class="modal-header d-flex justify-content-between align-items-center">
                    <!-- Transações fixas -->

                    <h5 class="modal-title">{{ $t("Informações da Transação") }}</h5>
                    <material-button id="cancelButton" variant="gradient" color="secondary"
                        class="btn btn-sm small-button" data-bs-dismiss="modal" @click="handleCancelClick">
                        {{ $t("Voltar") }}
                    </material-button>
                </div>

                <div class="modal-body scroll-container">

                    <!-- Transações fixas -->
                    <div v-if="popupFixas">
                        <div class="nav-wrapper position-relative end-0 mb-4">
                            <ul class="nav nav-pills nav-fill p-1" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link tab-button" :class="{ active: infos }" @click="
                                        infos = true;
                                    participantes = false;
                                    ">{{ $t("Informação") }}</a>
                                </li>

                                <li class="nav-item">
                                    <a class="nav-link tab-button" :class="{ active: participantes }" @click="
                                        participantes = true;
                                    infos = false;
                                    ">{{ $t("Participantes") }}</a>
                                </li>
                            </ul>
                        </div>

                        <div v-if="transaction.value">
                            <div v-if="infos">
                                <h6>Descrição: {{ transaction.value.descricao }}</h6>
                                <h6>Valor Total: {{ transaction.value.value }}€</h6>
                                <h6 v-if="transaction.value.value != transaction.value.shareValue">Valor Partilhado: {{
                                    transaction.value.shareValue }}€</h6>
                                <h6>Periodicidade: {{ getRepetitionText(transaction.value.repeticao) }}</h6>
                                <h6>Data Criação: {{ transaction.value.date }}</h6>
                                <h6>Data: {{ dateP }}</h6>
                                <h6>Categoria: {{ transaction.value.categoria }}</h6>
                                <h6>Status: {{ transaction.value.status }}</h6>
                                <h6>Tipo: {{ transaction.value.tipo }}</h6>


                                <div class="modal-footer d-block justify-content-center">
                                    <div class="align-items-center text-center">
                                        <i class="material-icons align-self-center comment"
                                            style="max-width: 24px; color: #344767">insert_comment_outlined</i>
                                        <h5>{{ $t("Comentários") }}</h5>
                                    </div>
                                </div>
                                <div class="justify-content-left">
                                    <div v-for="(c, index) in comments" :key="index" class="mb-3">
                                        <h6 v-if="currentEditIndex !== index">
                                            {{ c.user_email + " " + c.timestamp + " " + c.descricao }}
                                        </h6>
                                        <input v-else class="form-control" v-model="editComment" />
                                        <div class="buttons" v-if="currentEditIndex === index">
                                            <button @click="saveComment(index)" style="background-color: white;">
                                                <i class="material-icons"
                                                    style="color: #344767; font-size: 18px;">save</i>
                                            </button>
                                            <button @click="cancelEdit()" style="background-color: white;">
                                                <i class="material-icons"
                                                    style="color: #344767; font-size: 18px;">cancel</i>
                                            </button>
                                        </div>
                                        <div class="buttons" v-else>
                                            <button @click="editCommentMode(index, c.descricao)"
                                                style="background-color: white;">
                                                <i class="material-icons"
                                                    style="color: #344767; font-size: 18px;">edit</i>
                                            </button>
                                            <button @click="deleteComment(index)" style="background-color: white;">
                                                <i class="material-icons"
                                                    style="color: #344767; font-size: 18px;">delete</i>
                                            </button>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-9">
                                            <material-input class="material-input w-100" id="comment" type="text"
                                                :value="Comment" :label="$t('Escreva o seu comentário')"
                                                @update:value="Comment = $event" />
                                        </div>
                                        <div class="col-3 d-flex align-items-center justify-content-end">
                                            <material-button variant="gradient" color="info"
                                                class="btn btn-md d-flex justify-content-center align-items-center"
                                                @click="addComment(transa.id)">
                                                {{ $t("Comentar") }}
                                            </material-button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>

                <div class="modal-footer mt-0">
                    <p v-if="Type == 'Despesa' && showShareTab == false" class="btn btn-default bg-gradient-info mb-1">
                        {{ $t("Próximo") }}
                    </p>
                    <p v-else class="btn btn-default bg-gradient-info mb-1" @click="add">
                        {{ $t("Adicionar") }}
                    </p>
                    <p id="cancelButton" class="btn btn-default bg-gradient-primary mb-1" data-bs-dismiss="modal">
                        {{ $t("Cancelar") }}
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { useI18n } from "vue-i18n";
//import MaterialAlert from "@/components/MaterialAlert.vue";
import MaterialInput from "@/components/MaterialInput.vue";
import MaterialButton from "@/components/MaterialButton.vue";
//import NavPill from './NavPill.vue';
import { ref } from "vue";

export default {
    components: {
        //MaterialAlert,
        MaterialInput,
        MaterialButton,
    },
    props: {
        id: {
            type: Number,
            default: 0,
        },
        transaction: {
            type: Object,
            default: () => ({}), // Objeto vazio como padrão
        },
        comments: {
            type: Object,
            default: () => ({}), // Objeto vazio como padrão
        },
        type: {
            type: String,
            default: "",
        },
        popupFixas: {
            type: Boolean,
            default: false,
        },
    },
    setup(props) {

        const { t } = useI18n();

        const ValueLabel = ref("Indique um valor");
        const showAlertUsers = ref(false);
        const Value = ref("");
        const valueError = ref(null);

        // const popupFixas = ref(false);
        const infos = ref(true);
        const participantes = ref(false);

        // // const userS = userStore();
        // alert("props.popupFixas: " + props.popupFixas);

        console.log("props.popupFixas: " + props.popupFixas);

        const checkInput = function () {
            if (!Value.value) valueError.value = true;
            else if (Value.value < 0) {
                valueError.value = true;
                ValueLabel.value = "Indique um valor positivo";
            } else valueError.value = false;
        };

        const cancel = function () {
            valueError.value = null;
            Value.value = "";
        };

        const getRepetitionText = (repeticao) => {
            if (repeticao === undefined) {
                return "Unica";
            }
            switch (repeticao) {
                case 1:
                    return "Diariamente";
                case 2:
                    return "Semanalmente";
                case 3:
                    return "Mensalmente";
                case 4:
                    return "Anualmente";
                default:
                    return "Unica";
            }
        };
        // const addComment = async (id) => {
        //     const url =
        //         "http://localhost:8000/WalletBud-1.0-SNAPSHOT/api/comment/add";
        //     const token = localStorage.getItem("token");

        //     const request = {
        //         method: "POST",
        //         headers: {
        //             "Content-Type": "application/json",
        //             Authorization: "Bearer " + token,
        //         },
        //         body: JSON.stringify({
        //             idTransacao: id,
        //             comentario: Comment.value,
        //         }),
        //     };

        //     try {
        //         const response = await fetch(url, request);

        //         if (!response.ok) {
        //             const errorData = await response.json();
        //             throw new Error(errorData.message);
        //         }

        //         const data = await response.json();
        //         console.log(data);

        //         Comment.value = "";

        //         openPopup(id, dateP.value);
        //     } catch (error) {
        //         if (error.message.includes('token')) {
        //             alert('Token inválido ou inesperado. Você será redirecionado para a página de login.');

        //             localStorage.clear();
        //             sessionStorage.clear();

        //             router.push('/sign-in');
        //         } else {
        //             alert("Erro ao adicionar comentario:", error.message);
        //         }
        //     }
        // };

        return {
            t,
            cancel,
            checkInput,
            Value,
            valueError,
            ValueLabel,
            showAlertUsers,
            participantes,
            infos,
            getRepetitionText,
        };
    },
    data() {
        return {
            showAlert: false,
        };
    },
    mounted() { },
    computed: {},
    watch: {},
    methods: {
        hideAlertUsers() {
            this.showAlertUsers = false;
        },
        alert() {
            this.showAlert = !this.showAlert;
        },
    },
};
</script>

<style scoped>
.modal-content {
    width: 100%;
}

.modal {
    z-index: 1050;
}

.input-group {
    display: flex;
    align-items: center;
    position: relative;
    flex: 1;
}

.arrow-icon {
    position: absolute;
    right: 10px;
    pointer-events: none;
    color: #344767;
}

.dropdown-focused-null {
    border-color: #1a73e8 !important;
    box-shadow:
        inset 0 1px #1a73e8,
        inset 1px 0 #1a73e8,
        inset -1px 0 #1a73e8,
        inset 0 -1px #1a73e8 !important;
}

.dropdown-focused-error {
    border: 1px solid #f44335 !important;
    box-shadow:
        inset 0 1px #f44335,
        inset 1px 0 #f44335,
        inset -1px 0 #f44335,
        inset 0 -1px #f44335 !important;
    border-radius: 0.375rem;
}

.dropdown-menu {
    background-image: linear-gradient(195deg, #49a3f1 0%, #1a73e8 100%);
    color: white;
}

.dropdown .dropdown-menu:before {
    color: #3d96ef;
}

.dropdown-item {
    margin-top: 3px;
    color: #eeeeee;
    background-color: #ffffff14;
}

.dropdown-item:hover {
    background-color: #ffffff;
    color: #495057;
}

.form-row {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.form-label {
    flex: 1;
    color: #344767;
    margin-right: 10px;
    text-align: left;
}

.form-input {
    flex: 2;
}

.material-input {
    width: 100%;
    position: relative;
}

.material-input input {
    width: 100%;
    padding: 0.5rem;
    font-size: 0.875rem;
    font-weight: 400;
    line-height: 1.5rem;
    color: #495057;
    background-color: transparent;
    background-clip: padding-box;
    border: 1px solid #d2d6da;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    border-radius: 0.375rem;
    transition: 0.2s ease;
}

.form-control-date {
    width: 100%;
    padding-right: 2.5rem;
    /* Espaço para o ícone */
}

/* Estilo para o ícone de calendário */
.date-input-wrapper {
    position: relative;
}

.date-icon {
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
    pointer-events: none;
    color: #344767;
    z-index: 2;
}

.border {
    border: 1px solid #d2d6da;
    border-radius: 0.375rem;
}

.tab {
    display: none;
}

.tab.active {
    display: block;
}

.nav-pills .nav-link.active,
.nav-pills .show>.nav-link {
    color: #344767;
    background-color: #fff;
    animation: 0.2s ease;
}

.nav-link {
    display: block;
    padding: 0.5rem 1rem;
    transition:
        color 0.15s ease-in-out,
        background-color 0.15s ease-in-out,
        border-color 0.15s ease-in-out;
}

.tab-content {
    min-height: 400px;
    /* Define uma altura mínima para as abas */
}

.ms-auto {
    margin-left: auto !important;
    margin-right: 10px;
}



</style>
