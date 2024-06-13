<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col ">

        <div class="col">

          <div class="row">
            <div class="timeline-header">
              <h4>Hoje</h4>
            </div>
            <div class="col">

              <timeline-list >
                <div class="row">
                  <div class="col">
                    <timeline-item
                      color="warning"
                      :icon="{ component: 'notifications', class: 'text-white text-sm' }"
                      title="Pagar Spotify"
                      value="2400€"
                      date-time="22 DEC 7:20 PM"
                    />
                  </div>
                  
                  <div class="col">
                    <timeline-item
                      color="warning"
                      :icon="{ component: 'notifications', class: 'text-white text-sm' }"
                      title="Pagar Spotify"
                      value="2400€"
                      date-time="22 DEC 7:20 PM"
                    />
                  </div>
                  <div class="col">
                    <timeline-item
                      color="warning"
                      :icon="{ component: 'notifications', class: 'text-white text-sm' }"
                      title="Pagar Spotify"
                      value="2400€"
                      date-time="22 DEC 7:20 PM"
                    />
                  </div>
                  
                </div>
              </timeline-list>
            </div>
          </div>
        </div>
        
        <!--timeline next days-->
        <div class = "col pt-4">
          <div class="timeline-header">
            <h5>Próximos Eventos</h5>
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
            <div class="col-12">
              <div class="card my-4">
                <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                  <div
                    class="bg-gradient-info shadow-info border-radius-lg pt-3 pb-2"
                  >
                    <h6 class="text-white text-capitalize ps-3">Transações Fixas</h6>
                  </div>
                </div>
                <div class=" px-0 pb-2">
                  <div class="table-responsive p-0 scroll-container2">
                    <table
                      class="table align-items-center justify-content-center mb-0"
                    >
                      <thead class="table-head-fixed">
                        <tr>
                          <th
                            class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
                          >
                            Despesa
                          </th>
                          <th
                            class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2"
                          >
                            Periodicidade
                          </th>
                          <th
                            class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2"
                          >
                            Próximo Pagamento
                          </th>
                          <th
                            class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2"
                          >
                            Valor
                          </th>
                          
                        </tr>
                      </thead>
                      <tbody >
                          
                        <tr v-for="n in 10" :key="n">
                          <td>
                            <div class="d-flex px-3">
                              <div class="my-auto">
                                <h6 class="mb-0 text-sm">Github</h6>
                              </div>
                            </div>
                          </td>
                          <td>
                            <p class="text-xs font-weight-bold mb-0">Anual</p>
                          </td>
                          <td>
                            <span class="text-xs font-weight-bold">20/06/2024</span>
                          </td>
                          <td>
                            <span class="text-md font-weight-bold">13.9€</span>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="container-fluid py-3">
          <div class="row">
            <div class="col-12">
              <div class="card my-4">
                <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                  <div
                    class="bg-gradient-danger shadow-danger border-radius-lg pt-3 pb-2"
                  >
                    <h6 class="text-white text-capitalize ps-3">Por Pagar</h6>
                  </div>
                </div>
                <div class=" px-0 pb-2">
                  <div class="table-responsive p-0 scroll-container2">
                    <table
                      class="table align-items-center justify-content-center mb-0 "
                    >
                      <thead class="table-head-fixed">
                        <tr>
                          <th
                            class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
                          >
                            Despesa
                          </th>
                          <th
                            class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2"
                          >
                            Periodicidade
                          </th>
                          <th
                            class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2"
                          >
                            Próximo Pagamento
                          </th>
                          <th
                            class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2"
                          >
                            Valor
                          </th>
                          
                        </tr>
                      </thead>
                      <tbody >
                          
                        <tr v-for="n in 10" :key="n" >
                          <td>
                            <div class="d-flex px-3">
                              <div class="my-auto">
                                <h6 class="mb-0 text-sm">Github</h6>
                              </div>
                            </div>
                          </td>
                          <td>
                            <p class="text-xs font-weight-bold mb-0">Anual</p>
                          </td>
                          <td>
                            <span class="text-xs font-weight-bold">20/06/2024</span>
                          </td>
                          <td>
                            <span class="text-md font-weight-bold">13.9€</span>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
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
    max-height: 57vh; 
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