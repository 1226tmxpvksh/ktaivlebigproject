import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../components/pages/Index.vue'),
    },
    {
      path: '/users',
      component: () => import('../components/ui/UserGrid.vue'),
    },
    {
      path: '/userReadModels',
      component: () => import('../components/UserReadModelView.vue'),
    },
    {
      path: '/auths',
      component: () => import('../components/ui/AuthGrid.vue'),
    },
    {
      path: '/authReadModels',
      component: () => import('../components/AuthReadModelView.vue'),
    },
    {
      path: '/boards',
      component: () => import('../components/ui/BoardGrid.vue'),
    },
    {
      path: '/boardReadModels',
      component: () => import('../components/BoardReadModelView.vue'),
    },
    {
      path: '/reports',
      component: () => import('../components/ui/ReportGrid.vue'),
    },
    {
      path: '/reportReadModels',
      component: () => import('../components/ReportReadModelView.vue'),
    },
  ],
})

export default router;
