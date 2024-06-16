<template>
  <div class="modal fade" id="categoryModal" tabindex="-1" aria-labelledby="categoryModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="categoryModalLabel">{{ $t('Gerir Categorias de Movimentos') }}</h5>
        </div>
        <div class="modal-body">
          <div class="category-section">
            <h6>{{ $t('Categorias para Despesas') }}</h6>
            <ul class="category-list" v-if="store.CategoriesExpense && store.CategoriesExpense.length > 0">
              <li v-for="(category, index) in store.CategoriesExpense" :key="index">
                <span class="category" v-if="!store.CategoriesExpense[index].isedit">{{ category.name }}</span>
                <input class="input-group input-group-outline custom" v-else v-model="category.name" />
                <!-- @blur="saveCategory(index)" /> -->
                <div class="buttons">
                  <button @click="toggleExpenseEditMode(index, category.name)" style="background-color: white;">
                    <i class="material-icons" style="color: #344767;"
                      v-if="store.CategoriesExpense[index].isedit">save</i>
                    <i class="material-icons" style="color: #344767;" v-else>edit</i>
                  </button>
                  <button @click="deleteCategory(index,'expense')" style="background-color: white;">
                    <i class="material-icons" style="color: #344767;">delete</i>
                  </button>
                </div>
              </li>
              <MaterialAlert v-if="showErrorExpense" color="danger" class="font-weight-light">
                <span class="text-sm">{{ $t('Nomes de Categorias não podem ser nulos') }}</span>
              </MaterialAlert>
            </ul>
            <p class="message" v-else>{{ $t('Adicione Categorias!') }}</p>
          </div>
          <div class="category-section">
            <h6 class="custom-title">{{ $t('Categorias para Receitas') }}</h6>
            <ul class="category-list" v-if="store.CategoriesIncome && store.CategoriesIncome.length > 0">
              <li v-for="(category, index) in store.CategoriesIncome" :key="index">
                <span class="category" v-if="!store.CategoriesIncome[index].isedit">{{ category.name }}</span>
                <input class="input-group input-group-outline custom" v-else v-model="category.name" />
                <!-- @blur="saveIncomeCategory(index)" /> -->
                <div class="buttons">
                  <button @click="toggleIncomeEditMode(index, category.name)" style="background-color: white;">
                    <i class="material-icons" style="color: #344767; font-size: 20px;"
                      v-if="store.CategoriesIncome[index].isedit">save</i>
                    <i class="material-icons" style="color: #344767; font-size: 20px;" v-else>edit</i>
                  </button>
                  <button @click="deleteCategory(index,'income')" style="background-color: white;">
                    <i class="material-icons" style="color: #344767; font-size: 20px;">delete</i>
                  </button>
                </div>
              </li>
              <MaterialAlert v-if="showErrorIncome" color="danger" class="font-weight-light">
                <span class="text-sm">{{ $t('Nomes de Categorias não podem ser nulos') }}</span>
              </MaterialAlert>
            </ul>
            <p class="message" v-else>{{ $t('Adicione Categorias!') }}</p>
          </div>
        </div>
        <div class="modal-footer">
          <div class="modal-body">
            <h5>{{ $t('Adicionar Categoria') }}</h5>
            <label for="categoryType" style="color: black;">{{ $t('Selecionar o tipo de movimento') }}:</label>

            <!-- Tipo -->
            <div class="dropdown" ref="typeDropdown">
              <div class="form-group form-row">
                <div v-if="TypeError === null || TypeError === false"
                  class="input-group input-group-outline form-input mb-3" style="border-radius: 0.375rem;">
                  <button class="cursor-pointer form-control form-control-default material-input"
                    :class="{ 'dropdown-focused-null': isTypeFocused }" id="dropdownTable" data-bs-toggle="dropdown"
                    style="text-align:left; color: #7b809a" @focus="handleTypeFocus">
                    {{ newCategoryType || $t('Selecione o tipo') }}
                  </button>
                  <i class="material-icons arrow-icon">keyboard_arrow_down</i>
                  <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5" aria-labelledby="dropdownTable">
                    <li>
                      <a class="dropdown-item border-radius-md" href="javascript:;" @click="selectType('Despesa')">
                        {{ $t('Despesa') }}
                      </a>
                    </li>
                    <li> <a class="dropdown-item border-radius-md" href="javascript:;" @click="selectType('Receita')">
                        {{ $t('Receita') }}
                      </a>
                    </li>
                  </ul>
                </div>

                <div v-if="TypeError === true" class="input-group input-group-outline form-input mb-3 is-invalid"
                  style="border-radius: 0.375rem;">
                  <button class="cursor-pointer form-control form-control-default material-input"
                    :class="{ 'dropdown-focused-error': isTypeFocused }" id="dropdownTable" data-bs-toggle="dropdown"
                    style="text-align:left; color: #7b809a" @focus="handleTypeFocus">
                    {{ newCategoryType || $t('Selecione o tipo') }}
                  </button>
                  <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5" aria-labelledby="dropdownTable">
                    <li>
                      <a class="dropdown-item border-radius-md" href="javascript:;" @click="selectType('Despesa')">
                        {{ $t('Despesa') }}
                      </a>
                    </li>
                    <li> <a class="dropdown-item border-radius-md" href="javascript:;" @click="selectType('Receita')">
                        {{ $t('Receita') }}
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>


            <div class="input-group input-group-outline mb-3" :class="{ 'is-focused': isFocused }">
              <label class="form-label">{{ $t('Escreva uma categoria') }}</label>
              <input class="form-control form-control-default" type="text" v-model="newCategoryName"
                @focus="handleFocus" @blur="handleBlur">
            </div>
            <MaterialAlert v-if="showErrorAdd" color="danger" class="font-weight-light">
              <span class="text-sm">{{ $t('Indica um nome para a categoria!') }}</span>
            </MaterialAlert>
            <p class="btn btn-default bg-gradient-info mb-0" @click="addCategory">{{ $t('Adicionar') }}</p>
          </div>
        </div>
        <div class="modal-footer mt-0">
          <p class="btn btn-default bg-gradient-primary mb-1" data-bs-dismiss="modal">{{ $t('Fechar') }}</p>
        </div>
      </div>
    </div>
  </div>
  <div class="position-fixed top-1 end-1 z-index-1051">
      <!-- error snackbar -->
      <material-snackbar v-if="snackbar === 'error'" :title="$t('Adicionar Categoria')" date="now"
        :description="$t('Não foi possível adicionar a categoria. Tente novamente.')"
        :icon="{ component: 'campaign', color: 'white' }" color="danger" :close-handler="closeSnackbar" />
        <material-snackbar v-if="snackbar === 'exists'" :title="$t('Adicionar Categoria')" date="now"
        :description="$t('Já existe um categoria com esse nome!')"
        :icon="{ component: 'campaign', color: 'white' }" color="danger" :close-handler="closeSnackbar" />
        <material-snackbar v-if="snackbar === 'unknown'" :title="$t('Adicionar Categoria')" date="now"
        :description="$t('Erro ao adicionar a categoria. Tente novamente.')"
        :icon="{ component: 'campaign', color: 'white' }" color="danger" :close-handler="closeSnackbar" />
    </div>
</template>

<script>
import { useI18n } from 'vue-i18n';
import MaterialAlert from "@/components/MaterialAlert.vue";
import { categoriesStore } from "@/store/categoriesStore";
import MaterialSnackbar from "@/components/MaterialSnackbar.vue";
import { ref, onMounted} from 'vue';


export default {

  setup(props, { emit }) {
  // setup() {
    const { t } = useI18n();
    const store = categoriesStore();
    const newCategoryType = ref('');
    const newCategoryName = ref('');
    const isEditing = ref([]);
    const isEditingIncome = ref([]);
    const editedCategory = ref('');
    const editedIncomeCategory = ref('');
    const showErrorExpense = ref(false);
    const showErrorIncome = ref(false);
    const showErrorAdd = ref(false);
    const errorTimeout = ref(0);
    const isFocused = ref(false);
    const TypeError = ref(null);
    const snackbar = ref('');

    const addCategory = async () => {
      if(!newCategoryType.value)
        TypeError.value = true;
      if (newCategoryName.value.trim() === '') {
        showErrorAdd.value = true;
        clearTimeout(errorTimeout.value);
        errorTimeout.value = setTimeout(() => {
          showErrorAdd.value = false;
        }, 2000);
        return;
      }
      try {
        await store.addCategory({ name: newCategoryName.value, tipo: newCategoryType.value.toLowerCase() });
        alert("Categoria adicionada com sucesso!");
        await store.load();
        
        newCategoryName.value = '';
        newCategoryType.value = '';
        TypeError.value = null;
        snackbar.value = '';

      } catch (err) {
        if(err.message == 'Failed to fetch'){
          //não foi possível adicionar a categoria, tente novamente
          snackbar.value = 'error';
        }else if(err.message == 'Categoria ja existe com esse nome!'){
          newCategoryName.value = '';
          console.log("repetida")
          snackbar.value = 'exists';
          console.log(snackbar)
        }else{
          snackbar.value = 'unknown';
        }
      }
      
    };

    const editCategory = async (id,name,type) => {
      try {
        await store.editCategory({name: name, id: id},type);
        await store.load();
      } catch (err) {
        alert(err.message);
      }
    };

    const loadCategories = async () => {
      try {
        await store.load();
      } catch (err) {
        alert("Erro -> " + err.message);
      }
    };

    const deleteCategory = async (index, flag) => {
      try {
				if (flag === 'income') {
					await store.removeCategory(store.CategoriesIncome[index].id);
				} else if (flag === 'expense') {
					await store.removeCategory(store.CategoriesExpense[index].id);
				}
				await store.load();
      } catch (err) {
        alert(err.message);
      }
    };

    const toggleExpenseEditMode = (index,name) => {
      if (name.trim() === '') {
        showErrorExpense.value = true;
        clearTimeout(errorTimeout.value);
        errorTimeout.value = setTimeout(() => {
          showErrorExpense.value = false;
        }, 2000);
        return;
      }
      if (store.CategoriesExpense && store.CategoriesExpense.length > index) {
        if (store.CategoriesExpense[index].isedit) {
          editCategory(store.CategoriesExpense[index].id,name,'expense');
        } else{
            store.CategoriesExpense[index].isedit = !store.CategoriesExpense[index].isedit;	
        }
      }
    };

    const toggleIncomeEditMode = (index,name) => {
      if (name.trim() === '') {
        showErrorIncome.value = true;
        clearTimeout(errorTimeout.value);
        errorTimeout.value = setTimeout(() => {
          showErrorIncome.value = false;
        }, 2000);
        return;
      }
      if (store.CategoriesIncome && store.CategoriesIncome.length > index) {
        if (store.CategoriesIncome[index].isedit) {
          editCategory(store.CategoriesIncome[index].id,name,'income');
        } else{
          store.CategoriesIncome[index].isedit = !store.CategoriesIncome[index].isedit;	
        }
      }
    };

		const forceRerender = () => {
      emit('forceRerender');
    };

		onMounted(() => {
      const modal = document.getElementById('categoryModal');
      modal.addEventListener('hidden.bs.modal', forceRerender);
			
			loadCategories();
    });

    return {
      t,
      store,
      newCategoryType,
      newCategoryName,
      isEditing,
      isEditingIncome,
      editedCategory,
      editedIncomeCategory,
      showErrorExpense,
      showErrorIncome,
      showErrorAdd,
      errorTimeout,
      isFocused,
      addCategory,
      loadCategories,
      deleteCategory,
      editCategory,
      toggleExpenseEditMode,
      toggleIncomeEditMode,
      TypeError,
      snackbar
    };
  },

  components: {
    MaterialAlert,
    MaterialSnackbar
  },

  methods: {
    handleFocus() {
      this.isFocused = true;
    },
    handleBlur() {
      this.isFocused = false;
    },
    selectType(type) {
      this.newCategoryType = type;
    },
    closeSnackbar() {
      this.snackbar = null;
    },
  }
};
</script>

<style scoped>

.message {
    text-align: center;
    color: #495057;
    font-weight: 400;
}

.category {
    color: #15181a;
    font-weight: 400;
    font-size: 14px;
}

.error-category {
    text-align: center;
    font-weight: bold;
    background-color: red;
    color: white;
    padding: 5px;
    border-radius: 8px;
    font-size: 17px;
}

.category-input {
    width: 100%; 
    padding: 5px;
    margin-bottom: 10px;
    border-radius: 5px;
    border: 1px solid ;
}

.form-group {
    margin-bottom: 10px;
    text-align: left;
    width: 100%; 
    padding: 0; 
}

.small-select {
    width: 100%; 
    padding: 5px;
    border-radius: 5px;
}

.modal-content {
    width: 100%;
}

.modal {
  z-index: 1050; /* padrão do Bootstrap para modais */
}

h2, h4 {
    text-align: left;
}

.category-section {
    margin-bottom: 20px;
    font-weight: bold;
    color: black;
}

.category-list {
    list-style: none;
    padding-left: 0; 
    margin-left: 0;
}

ul li {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

ul li .buttons {
    margin-left: auto;
}

ul li button {
    border: none;
    background: none;
    margin-left: 5px;
}

button {
    padding: 5px 10px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
}

button:hover {
    background-color: #0056b3;
}

button.btn-secondary {
    background-color: red;
}

button.btn-close {
    background-color: rgb(223, 76, 76);
}

.z-index-1051 {
  z-index: 1051;
}

.custom {
    background: none;
    border: 1px solid #1a73e8;
    border-radius: 0.375rem;
    border-top-left-radius: 0.375rem !important;
    border-bottom-left-radius: 0.375rem !important;
    padding: 0.625rem 0.75rem !important;
    line-height: 1.3 !important;
    font-size: 0.875rem;
    font-weight: 400;
    width: 50%;
}

.input-group-outline.custom:focus {
    border-color: #1a73e8 !important;
    box-shadow:  inset 0 1px #1a73e8, 
        inset 1px 0 #1a73e8, 
        inset -1px 0 #1a73e8,
        inset 0 -1px #1a73e8;
    outline: none; /* Remove o outline padrão */
}

.input-group {
    display: flex;
    align-items: center;
    position: relative;
}

.arrow-icon {
    position: absolute;
    right: 10px;  /* Ajuste conforme necessário */
    pointer-events: none;
    color: #000; /* Ajuste a cor conforme necessário */
}

.dropdown-focused-null {
    border-color: #1a73e8 !important;
    box-shadow: inset 0 1px #1a73e8,
        inset 1px 0 #1a73e8,
        inset -1px 0 #1a73e8,
        inset 0 -1px #1a73e8 !important;
}

.dropdown-focused-error {
    border: 1px solid #f44335 !important;
    box-shadow: inset 0 1px #f44335,
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
    color: #495057
}

.arrow-icon {
    position: absolute;
    right: 10px;
    pointer-events: none;
    color: #344767;
}
    
</style>
