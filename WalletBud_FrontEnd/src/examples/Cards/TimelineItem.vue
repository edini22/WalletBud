<template>
  <div class="mb-3 timeline-block">
    <span class="timeline-step p-3 " :class="`bg-${color}`">
      <i class="material-icons white-icon" :color="white" :class="icon.class">
        {{ icon.component }}
      </i>
    </span>
    <div class="timeline-content" style="max-width: 100%;">
      <h5 class="mb-0 text-s font-weight-bold justify-content-between d-flex"
        :class="$parent.darkMode ? 'text-white' : 'text-dark'">
        {{ title }} &nbsp;&nbsp;&nbsp;&nbsp;
        <h6 class="mb-0 text-s font-weight-bold">{{dateTime}}</h6>
      </h5>
      <p class="mt-2 mb-0 text-sm  font-weight-bold">
        {{ value }}
      </p>
      <p v-if="description" class="mt-1 mb-0 text-sm" :class="$parent.darkMode ? 'text-white' : 'text-dark'">
        {{ description }}
      </p>
    </div>
  </div>
</template>
<script>
export default {
  name: "TimelineItem",
  props: {
    color: {
      type: String,
      default: "",
    },
    icon: {
      type: Object,
      component: String,
      class: String,
      default: () => {},
    },
    title: {
      type: String,
      default: "",
    },
    dateTime: {
      type: String,
      default: "",
    },
    description: {
      type: String,
      default: "",
    },
    value: {
      type: String,
      default: "",
    },
  },
};
</script>

<style scoped>
.white-icon {
  color: white !important;
}
</style>