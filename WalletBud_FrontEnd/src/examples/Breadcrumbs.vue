<template>
  <nav aria-label="breadcrumb">
    <ol class="px-0 pt-1 pb-0 mb-0 bg-transparent breadcrumb">
      <li class="text-sm breadcrumb-item" :class="color">
        <a :class="color" class="opacity-8" href="/">Home</a>
      </li>
      <li
        class="text-sm breadcrumb-item active"
        :class="color"
        aria-current="page">
        <a :class="color" class="opacity-8">{{ currentPage }}</a>
      </li>
    </ol>
  </nav>
</template>

<script>
export default {
  name: "breadcrumbs",
  props: {
    currentPage: {
      type: String,
      required: "",
    },
    color: {
      type: String,
      default: "text-dark",
    },
  },
};
</script>
