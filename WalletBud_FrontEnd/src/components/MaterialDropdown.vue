<template>
    <div>
        <button @click='toggleShow' class='dropdown-c' :class="{ 'dropdown-focused': showMenu }">
            {{ selected }}
            <i class="material-icons arrow-icon">keyboard_arrow_down</i>
        </button>
        <div v-if='showMenu' class='menu'>
            <div class='menu-item' v-for='(item, index) in items' :key='index' @click='itemClicked(item)'>{{ item }}
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "MaterialDropdown",
    data() {
        return {
            showMenu: false
        }
    },
    props: {
        onClick: Function,
        items: {
            type: Object,
            default: () => ({})
        },
        selected: {
            type: String,
            default: ''
        }
    },
    methods: {
        toggleShow() {
            this.showMenu = !this.showMenu;
        },
        itemClicked(item) {
            this.$emit('update:selected', item);
            this.toggleShow();
            this.onClick(item);
        },
    },
};
</script>

<style>
.menu {
    position: absolute;
    background-color: #fff;
    border: 1px solid rgba(0, 0, 0, 0.15);
    border-radius: 0.25rem;
    z-index: 999;
    margin-top: 5px;
}

.menu-item {
    color: #212529;
    padding: 0.45rem 1.5rem;
    transition: color .15s ease-in-out, background-color .15s ease-in-out, border-color .15s ease-in-out, box-shadow .15s ease-in-out;
    font-size: 0.875rem;
    font-weight: 400;
    color: #495057;
    position: relative;
    color: #7b809a;
}

.menu-item:hover {
    background-color: #1a73e816;
    cursor: pointer;
}

.card {
    position: relative;
    /* Ensure relative positioning for the dropdown */
}

.dropdown-c {
    display: flex;
    align-items: center;
    justify-content: space-between;
    background: none;
    border: 1px solid #1a73e8;
    border-radius: 0.375rem;
    border-top-left-radius: 0.375rem !important;
    border-bottom-left-radius: 0.375rem !important;
    padding: 0.625rem 0.75rem !important;
    line-height: 1.3 !important;
    color: #1a73e8;
    font-size: 0.875rem;
    font-weight: 400;
    height: 40px;
    width: 100% !important;
}

.dropdown-focused {
    border-color: #1a73e8 !important;
    box-shadow:  inset 0 1px #1a73e8, 
        inset 1px 0 #1a73e8, 
        inset -1px 0 #1a73e8,
        inset 0 -1px #1a73e8;
}
</style>