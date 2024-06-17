<template>
    <!-- Tipo -->
    <div class="dropdown" ref="Dropdown">
        <div class="form-group form-row">
            <div class="input-group input-group-outline form-input mb-3" style="border-radius: 0.375rem;">
                <button 
                    class="cursor-pointer form-control form-control-default material-input"
                    :class="{ 'dropdown-focused-null': isFocused }" 
                    id="dropdownTab" 
                    data-bs-toggle="dropdown"
                    style="text-align:left; color: #7b809a" 
                    @focus="handleFocus"
                    >
                    {{ selected || inputText }}
                </button>
                <i class="material-icons arrow-icon">keyboard_arrow_down</i>
                <ul class="dropdown-menu px-2 py-3 ms-sm-n1 ms-n5" aria-labelledby="dropdownTab">
                    <li v-for="(option, index) in options" :key="index">
                        <a class="dropdown-item border-radius-md" href="javascript:;" @click="selectOption(option)">
                            {{ option }}
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "MaterialDropdown",
    props: {
        options: {
            type: Array,
            required: true
        },
        inputText: {
            type: String,
            default: ''
        },
        selected: {
            type: String,
            default: ''
        }
    },
    data() {
        return {
            isFocused: false
        };
    },
    methods: {
        handleFocus() {
            this.isFocused = true;
        },
        selectOption(option) {
            this.$emit('update:selected', option);
            this.isFocused = false;
            document.getElementById('dropdownTab').click();
        }
    },
};
</script>

<style>
.form-group {
  margin-bottom: 10px;
  text-align: left;
  width: 100%;
  padding: 0;
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
  box-shadow: inset 0 1px #1a73e8,
    inset 1px 0 #1a73e8,
    inset -1px 0 #1a73e8,
    inset 0 -1px #1a73e8;
  outline: none;
  /* Remove o outline padrão */
}

.input-group {
  display: flex;
  align-items: center;
  position: relative;
}

button {
  padding: 5px 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
}

.arrow-icon {
  position: absolute;
  right: 10px;
  top: 50%;
  transform: translateY(-50%);
  pointer-events: none;
  color: #344767;
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
  color: #3d96ef !important;
}

.dropdown-item {
  margin-top: 3px;
  color: #eeeeee !important;
  background-color: #ffffff14;
}

.dropdown-item:hover {
  background-color: #ffffff;
  color: #495057 !important;
}

.arrow-icon {
  position: absolute;
  right: 10px;
  pointer-events: none;
  color: #344767;
}
</style>