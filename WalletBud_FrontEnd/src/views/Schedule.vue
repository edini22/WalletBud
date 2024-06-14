<template>

  <div class="container">
    <div class="row">
      <div class="col">
      </div>
      <div class="col-6">
        <div class="timeline-header">
            <h4>Agenda</h4>
          </div>
          <timeline-list class="scroll-container">
            <timeline-item
              color="success"
              :icon="{ component: 'notifications', class: 'text-white text-sm' }"
              title="Design changes"
              value="2400€"
              date-time="22 DEC 7:20 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="danger"
              :icon="{ component: 'code', class: 'text-white text-sm' }"
              title="Design changes"
              value="2400€"
              date-time="21 DEC 11 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="info"
              :icon="{ component: 'shopping_cart', class: 'text-white text-sm' }"
              title="Design changes"
              value="2400€"
              date-time="21 DEC 9:34 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="info"
              :icon="{ component: 'shopping_cart', class: 'text-white text-sm' }"
              title="Design changes"
              value="2400€"
              date-time="21 DEC 9:34 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="info"
              :icon="{ component: 'shopping_cart', class: 'text-white text-sm' }"
              title="Design changes"
              value="2400€"
              date-time="21 DEC 9:34 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
          </timeline-list>
      </div>
      <div class="col">
      </div>
    </div>
  </div>



  <div class="container-fluid">
    <div class="row">
      <div class="col ">        
        <!--timeline next days-->
        <div class = "col pt-0">
          <div class="timeline-header">
            <h4>Agenda</h4>
          </div>
          <timeline-list class="scroll-container">
            <timeline-item
              color="success"
              :icon="{ component: 'notifications', class: 'text-white text-sm' }"
              title="Design changes"
              value="2400€"
              date-time="22 DEC 7:20 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="danger"
              :icon="{ component: 'code', class: 'text-white text-sm' }"
              title="Design changes"
              value="2400€"
              date-time="21 DEC 11 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="info"
              :icon="{ component: 'shopping_cart', class: 'text-white text-sm' }"
              title="Design changes"
              value="2400€"
              date-time="21 DEC 9:34 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="info"
              :icon="{ component: 'shopping_cart', class: 'text-white text-sm' }"
              title="Design changes"
              value="2400€"
              date-time="21 DEC 9:34 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="info"
              :icon="{ component: 'shopping_cart', class: 'text-white text-sm' }"
              title="Design changes"
              value="2400€"
              date-time="21 DEC 9:34 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
          </timeline-list>
        </div>
      </div>
      <div class="col">
        <div class="container-fluid py-0">
          <div class="row">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TimelineList from "@/examples/Cards/TimelineList.vue";
import TimelineItem from "@/examples/Cards/TimelineItem.vue";

export default {
  name: "schedule",
  components: { TimelineList, TimelineItem },
};
</script>

<style scoped>
  .scroll-container {
    max-height: 80vh; 
    overflow-y: auto;
  }

.scroll-container::-webkit-scrollbar, .scroll-container2::-webkit-scrollbar{
  width: 8px;
}

.scroll-container::-webkit-scrollbar-track, .scroll-container2::-webkit-scrollbar-track{
  border-radius: 10px;
}

.scroll-container::-webkit-scrollbar-thumb, .scroll-container2::-webkit-scrollbar-thumb{
  background: #888;
  border-radius: 10px;
}

  .scroll-container2 {
    max-height: 31vh;
    overflow-y: auto;
  }

  .table-head-fixed {
    position: sticky;
    top: 0;
    background-color: white;
  }
</style>