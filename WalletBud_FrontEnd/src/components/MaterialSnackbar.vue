<template>
  <div class="toast fade show p-0 mt-2" :class="getColor(color)">
    <div class="toast-header bg-transparent border-0">
      <i class="material-icons me-2" :class="getIcon(icon.color)">
        {{ icon.component }}
      </i>
      <span class="me-auto font-weight-bold" :class="getTextColor(color)">
        {{ title }}
      </span>
      <small :class="getTextColor(color)">
        {{ date }}
      </small>
      <i
        class="fas fa-times text-md ms-3 cursor-pointer"
        :class="getTextColor(color)"
        @click="closeHandler"
      />
    </div>
    <hr class="horizontal dark m-0" :class="getHrColor(color)" />
    <div class="toast-body toast-description" :class="getTextColor(color)">
      {{ description }}
    </div>
    <div class="progress-bar" :style="{ width: progressWidth + '%' }"></div>
  </div>
</template>

<script>
export default {
  name: "MaterialSnackbar",
  props: {
    title: {
      type: String,
      required: true,
    },
    date: {
      type: String,
      default: "",
    },
    description: {
      type: String,
      default: "",
    },
    icon: {
      type: Object,
      component: String,
      color: String,
      default: () => {},
    },
    color: {
      type: String,
      default: "success",
    },
    closeHandler: {
      type: Function,
      default: () => {},
    },
  },
  data() {
    return {
      progressWidth: 100,
    };
  },
  mounted() {
    this.startProgress();
  },
  methods: {
    startProgress() {
      let duration = 2000;
      let interval = 50;
      let decrement = 100 / (duration / interval);

      let timer = setInterval(() => {
        this.progressWidth -= decrement;
        if (this.progressWidth <= 0) {
          clearInterval(timer);
          this.progressWidth = 0;
          this.closeHandler();
        }
      }, interval);
    },
    getColor: (color) => {
      let colorValue;

      if (color === "white") {
        colorValue = "bg-white";
      } else {
        colorValue = `bg-gradient-${color}`;
      }
      return colorValue;
    },
    getIcon: (iconColor) => (iconColor ? `text-${iconColor}` : null),
    getTextColor: (color) => (color === "white" ? "text-dark" : "text-white"),
    getHrColor: (color) => (color === "white" ? "dark" : "light"),
  },
};
</script>

<style scoped>
  .toast-description {
    margin-top: -1rem;
  }
  .progress-bar {
    height: 6px;
    background-color: white; 
    transition: width 0.05s linear;
  }
</style>