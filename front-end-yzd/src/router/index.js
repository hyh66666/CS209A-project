import { createRouter, createWebHistory } from 'vue-router'
import Q1test1 from "@/components/Q1Test1.vue";
import Q1test2 from "@/components/Q1Test2.vue";
import Q1Test3 from "@/components/Q1Test3.vue";
import Q4Test1 from "@/components/Q4Test1.vue";
import Q4Test2 from "@/components/Q4Test2.vue";
import Q4Test3 from "@/components/Q4Test3.vue";
import Q2Test1 from "@/components/Q2Test1.vue";
import Q2Test2 from "@/components/Q2Test2.vue";
import Q2Test3 from "@/components/Q2Test3.vue";
import Q3Test1 from "@/components/Q3Test1.vue";
import Q3Test3 from "@/components/Q3Test3.vue";
import Q3Test2 from "@/components/Q3Test2.vue";

export const router = createRouter({
    history: createWebHistory(), // 暂时使用Hash模式，线上部署时再改成History模式
    routes: [
        {
            path: '/',
            redirect: '/answer1'
        },
        {
            path: '/answer1',
            component: Q1test1
        },
        {
            path: '/answer2',
            component: Q1test2
        },
        {
            path: '/answer3',
            component: Q1Test3
        },
        {
            path: '/user1',
            component: Q4Test1
        },
        {
            path: '/user2',
            component: Q4Test2
        },
        {
            path: '/user3',
            component: Q4Test3
        },
        {
            path: '/q1',
            component: Q2Test1
        },
        {
            path: '/q2',
            component: Q2Test2
        },
        {
            path: '/q3',
            component: Q2Test3
        },
        {
            path: '/tag1',
            component: Q3Test1
        },
        {
            path: '/tag2',
            component: Q3Test2
        },
        {
            path: '/tag3',
            component: Q3Test3
        },
    ]
})

export default router
