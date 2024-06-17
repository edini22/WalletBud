<template>
  <div class="card">
    <div
      class="card-header p-0 position-relative mt-n4 mx-3 bg-transparent"
    >
      <div
        class="border-radius-lg py-3 pe-1"
        :class="`bg-gradient-${color} shadow-${color}`"
      >
        <slot />
      </div>
    </div>
    <div class="card-body">
      <h6 class="mb-0">{{ title }}</h6>
      <!--  eslint-disable-next-line vue/no-v-html -->
      <p class="text-sm" v-html="subtitle"></p>
      <hr class="dark horizontal" />
      <div class="align">
        <div v-if="$slots.detail">
          <slot name="detail"></slot>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ChartHolderCard",
  props: {
    title: {
      type: String,
      default: "",
    },
    subtitle: {
      type: String,
      default: "",
    },
    week: {
      type: String,
      default: "",
    },
    color: {
      type: String,
      default: "primary",
    },
  },
};
</script>

<style>
.align {
  left: 0;
}
</style>
