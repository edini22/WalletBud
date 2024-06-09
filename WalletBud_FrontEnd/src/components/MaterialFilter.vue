<template>
    <div>
        <div class="custom-dropdown" :class="{ 'dropdown-focused': showMenu }">
            <button class="custom-dropdown-btn" @click="toggleShow">
                <i class="material-icons">filter_alt</i> Filtrar
            </button>


            <div v-if="showMenu" class="custom-dropdown-menu">

                <div class="row align-items-center">
                    <div class="col-md-6 d-flex">
                        <div class="flex-grow-1 me-2">
                            <div class="filter-section">
                                <p class="title-filter">Categorias</p>
                                <div class="category-checkboxes">
                                    <material-checkbox v-for="(category, index) in categories"
                                        :key="'category_' + index" :id="'category_' + index" :name="'category_' + index"
                                        :checked="selectedCategories.includes(category)"
                                        @change="toggleCategory(category)" class="checkbox-item-1">
                                        {{ category }}
                                    </material-checkbox>
                                </div>
                            </div>
                        </div>
                        <div class="flex-grow-1">
                            <div class="filter-section">
                                <p class="title-filter">Tipos</p>
                                <div class="type-checkboxes">
                                    <material-radio v-for="(type, index) in types" :key="'type_' + index"
                                        :id="'type_' + index" :name="'type_' + index"
                                        :checked="selectedType == type" @change="toggleType(type)"
                                        class="checkbox-item-2">
                                        {{ type }}
                                    </material-radio>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>
</template>

<script>
import MaterialCheckbox from "@/components/MaterialCheckbox.vue";
import MaterialRadio from "@/components/MaterialRadio.vue";

export default {
    name: "FilterDropdown",
    components: {
        MaterialCheckbox,
        MaterialRadio,
    },
    data() {
        return {
            showMenu: false,
            categories: [
                "Pessoal", "Familiar", "Alimentação", "Casa", "Entretenimento",
                "Rendas", "Salário"
            ],
            types: ["Receita", "Despesa"],
            selectedCategories: [],
            selectedType: '',
        };
    },
    methods: {
        toggleShow() {
            this.showMenu = !this.showMenu;
        },
        toggleCategory(category) {
            if (this.selectedCategories.includes(category)) {
                this.selectedCategories = this.selectedCategories.filter(
                    (c) => c !== category
                );
            } else {
                this.selectedCategories.push(category);
            }
            this.emitChanges();
        },
        toggleType(type) {
            // Limpa todas as seleções anteriores antes de definir o novo tipo selecionado
            this.selectedType = '';
            // Adiciona o tipo selecionado
            this.selectedType = type;
            this.emitChanges();
        },
        emitChanges() {
            this.$emit("change", {
                categories: this.selectedCategories,
                type: this.selectedType,
            });
        },
    },
};
</script>

<style scoped>
.category-checkboxes,
.type-checkboxes {
    display: flex;
    flex-wrap: wrap;
}

.checkbox-item-1 {
    min-width: 130px;
    /* Define a minimum width for checkboxes */
    flex: 1 0 45%;
    /* Adjust the percentage to fit more/less items per row */
}

.checkbox-item-2 {
    min-width: 110px;
    /* Define a minimum width for checkboxes */
    flex: 1 0 45%;
    /* Adjust the percentage to fit more/less items per row */
}

.custom-dropdown {
    position: relative;
    display: inline-block;
}

.custom-dropdown-btn {
    background: none;
    border: none;
    cursor: pointer;
    background: none;
    border-radius: 0.375rem;
    border-top-left-radius: 0.375rem !important;
    border-bottom-left-radius: 0.375rem !important;
    padding: 0.625rem 0.75rem !important;
    line-height: 1.3 !important;
    background-color: #1a73e8;
    color: white;
    font-size: 0.875rem;
    font-weight: 400;
    height: 40px;
    width: 110px;
    background-image: linear-gradient(195deg, #49a3f1 0%, #1a73e8 100%);
}

.custom-dropdown-menu {
    position: absolute;
    top: calc(100% + 5px);
    right: 0;
    /* Ajuste para alinhar com o lado direito do botão */
    background-color: #fff;
    border: 1px solid rgba(0, 0, 0, 0.15);
    border-radius: 0.25rem;
    padding: 0.5rem;
    z-index: 999;
}

.dropdown-focused {
    box-shadow: inset 0 1px #1a73e8, inset 1px 0 #1a73e8, inset -1px 0 #1a73e8,
        inset 0 -1px #1a73e8;
    border-radius: 0.375rem;
}

.material-icons {
    vertical-align: middle;
    color: white;
}

.title-filter {
    font-size: 0.875rem;
    font-weight: 600;
    color: #344767;
}
</style>