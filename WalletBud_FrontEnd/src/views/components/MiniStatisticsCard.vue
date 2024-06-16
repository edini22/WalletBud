<template>
  <div class="card mb-2 mini-statistics-card" :class="directionReverse ? reverseDirection : ''">
    <div class="card-header p-3 pt-2">
      <div class="icon icon-lg icon-shape shadow text-center border-radius-xl mt-n4 position-absolute"
        :class="`bg-gradient-${icon.background} shadow-${icon.background}`">
        <i class="material-icons opacity-10" :class="icon.color" aria-hidden="true">{{ icon.name }}</i>
      </div>
      <div class="pt-1 text-end size">
        <p class="text-sm mb-0 text-capitalize">{{ title.text }}</p>
        <h4 class="mb-0">{{ title.value }}</h4>
      </div>
    </div>
    <div>
      <div class="card-footer p-3 size">
        <div v-if="$slots.detail">
          <slot name="detail"></slot>
        </div>
        <p v-else v-html="detail"></p>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "MiniStatisticsCard",
  data() {
    return {
      reverseDirection: "flex-row-reverse justify-content-between",
    };
  },
  props: {
    title: {
      type: Object,
      required: true,
      text: String,
      value: [Number, String],
    },
    detail: {
      type: String,
      default: "",
    },
    icon: {
      type: Object,
      required: true,
      name: String,
      color: String,
      background: String,
      default: () => ({
        color: "text-white",
        background: "success",
      }),
    },
    directionReverse: {
      type: Boolean,
      default: false,
    }
  },
};
</script>

<style scoped>
.size {
  height: 65px;
}

.mini-statistics-card {
  min-height: 120px;
}

@media (max-width: 768px) {
  .mini-statistics-card {
    min-height: 100px;
  }

  .mini-statistics-card .size {
    font-size: 12px;
  }

  .mini-statistics-card h4 {
    font-size: 18px;
  }
}
</style>