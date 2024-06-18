<template>
  <div class="mb-3 timeline-block">
    <span class="timeline-step p-3 " :class="`bg-${color}`">
      <i class="material-icons white-icon" :color="white" :class="icon.class">
        {{ icon.component }}
      </i>
    </span>
    <div class="timeline-content" style="max-width: 100%;">
      
      <h5 class="mb-0 text-s font-weight-bold cuter"
          :class="$parent.darkMode ? 'text-white' : 'text-dark'">
        {{ title }}
      </h5>
      
      <h6 class="mb-0 text-sm"
        :class="dateColor === 'success' ? 'text-success' : 'text-danger'">
        {{ dateTime }}
      </h6>

      <p class="mt-2 mb-0 text-sm font-weight-bold">
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
    dateColor: {
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

.cuter{
  word-wrap: break-word;
  word-break: break-all;
  white-space: normal;
  overflow-wrap: break-word;
}

.text-success{
  color: #4caf50 !important;
}

.text-danger{
  color: #f44335 !important;
}

</style>