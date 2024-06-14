<template>
  <div v-if="type == 'date' && error"
    name="div"
    class="input-group"
    :class="`input-group-${variant} ${getStatusDate(error, success)}`"
  >
    <label :class="variant === 'static' ? '' : 'form-label'">{{ label }}</label>
    <div class="icon-wrapper">
      <i class="material-icons date-icon-error error">date_range</i>
      <input
        :id="id"
        :type="type"
        class="form-control"
        :class="getClasses(size)"
        style="border: none !important;"
        :name="name"
        :value="value"
        :placeholder="placeholder"
        :isRequired="isRequired"
        :disabled="disabled"
        @input="$emit('update:value', $event.target.value)"
        @focus="$emit('focus', $event)"
        @blur="$emit('blur', $event)"
      />
    </div>
  </div>

  <div v-else
    name="div"
    class="input-group"
    :class="`input-group-${variant} ${getStatus(error, success)}`"
  >
    <label :class="variant === 'static' ? '' : 'form-label'">{{ label }}</label>
    <input
      :id="id"
      :type="type"
      class="form-control"
      :class="getClasses(size)"
      :name="name"
      :value="value"
      :min="0"
      :placeholder="placeholder"
      :isRequired="isRequired"
      :disabled="disabled"
      @input="$emit('update:value', $event.target.value)"
      @focus="$emit('focus', $event)"
      @blur="$emit('blur', $event)"
    />
  </div>
  
</template>

<script>
import setMaterialInput from "@/assets/js/material-input.js";

export default {
  name: "MaterialInput",
  props: {
    variant: {
      type: String,
      default: "outline",
    },
    label: {
      type: String,
      default: "",
    },
    size: {
      type: String,
      default: "default",
    },
    success: {
      type: Boolean,
      default: false,
    },
    error: {
      type: Boolean,
      default: false,
    },
    disabled: {
      type: Boolean,
      default: false,
    },
    name: {
      type: String,
      default: "",
    },
    id: {
      type: String,
      required: true,
    },
    value: {
      type: String,
      default: "",
    },
    placeholder: {
      type: String,
      default: "",
    },
    type: {
      type: String,
      default: "text",
    },
    isRequired: {
      type: Boolean,
      default: false,
    },
  },
  emits: ["update:value"],
  mounted() {
    setMaterialInput();
  },
  watch: {
    value(newValue) {
      if (newValue === '') {
        this.removeEmptyClass();
      }
    }
  },
  methods: {
    getClasses: (size) => {
      let sizeValue;

      sizeValue = size ? `form-control-${size}` : null;

      return sizeValue;
    },
    getStatus: (error, success) => {
      let isValidValue;

      if (success) {
        isValidValue = "is-valid";
      } else if (error) {
        isValidValue = "is-invalid";
      } else {
        isValidValue = null;
      }

      return isValidValue;
    },
    getStatusDate: (error, success) => {
      let isValidValue;

      if (success) {
        isValidValue = "is-valid";
      } else if (error) {
        isValidValue = "is-invalid-date";
      } else {
        isValidValue = null;
      }

      return isValidValue;
    },
    removeEmptyClass() {
      const elements = document.getElementsByName('div');

      for (let i = 0; i < elements.length; i++) {
        elements[i].classList.remove('is-filled');
      }
    }
  },
};
</script>

<style>
[type="date"] {
  background: #fff url(https://cdn1.iconfinder.com/data/icons/cc_mono_icon_set/blacks/16x16/calendar_2.png) 97% 50% no-repeat;
  color: #495057;
}

[type="date"]::-webkit-inner-spin-button {
  display: none;
}

[type="date"]::-webkit-calendar-picker-indicator {
  opacity: 0;
}

.icon-wrapper {
  position: relative;
  width: 100%;
}

.date-icon-error {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  right: 10px;
  pointer-events: none;
  color: #fd5c6f;
}

.input-group input[type="date"] {
  width: 100%; /* Manter o tamanho padrão */
  padding-right: 30px; /* Ajuste o espaçamento para acomodar o ícone */
}

input[type=date] { color: transparent !important; }

.is-focused input[type=date] { color: inherit !important; }

.is-filled input[type=date] { color: inherit !important; }


</style>