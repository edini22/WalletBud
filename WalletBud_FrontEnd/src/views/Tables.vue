<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <div class = "col scroll-container">
          <timeline-list title="Timeline">
            <timeline-item
              color="success"
              :icon="{ component: 'notifications', class: 'text-white text-sm' }"
              title="$2400 Design changes"
              date-time="22 DEC 7:20 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="danger"
              :icon="{ component: 'code', class: 'text-white text-sm' }"
              title="New order #1832412"
              date-time="21 DEC 11 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="info"
              :icon="{ component: 'shopping_cart', class: 'text-white text-sm' }"
              title="Server payments for April"
              date-time="21 DEC 9:34 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="info"
              :icon="{ component: 'shopping_cart', class: 'text-white text-sm' }"
              title="Server payments for April"
              date-time="21 DEC 9:34 PM"
              description="People care about how you see the world, how you think, what motivates you, what you’re struggling with or afraid of."
            />
            <TimelineItem
              color="info"
              :icon="{ component: 'shopping_cart', class: 'text-white text-sm' }"
              title="Server payments for April"
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
                          
                        <tr v-for="n in 5" :key="n">
                          <td>
                            <div class="d-flex px-2">
                              <div>
                                <img
                                  src="../assets/img/small-logos/github.svg"
                                  class="avatar avatar-sm rounded-circle me-2"
                                  alt="invision"
                                />
                              </div>
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
                            <span class="text-sm font-weight-bold">13.9€</span>
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
                            <div class="d-flex px-2">
                              <div>
                                <img
                                  src="../assets/img/small-logos/github.svg"
                                  class="avatar avatar-sm rounded-circle me-2"
                                  alt="invision"
                                />
                              </div>
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
                            <span class="text-sm font-weight-bold">13.9€</span>
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
  name: "tables",
  components: { TimelineList, TimelineItem },
};
</script>

<style scoped>
  .scroll-container {
    max-height: 86vh; 
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