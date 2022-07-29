import {createRouter, createWebHistory} from 'vue-router'
import Layout from '../layout/Layout.vue'

const routes = [
    {
        path: '/',
        name: 'Layout',
        component: Layout,
        redirect: "/home",
        children: [
            {
                path: 'home',
                name: 'Home',
                component: () => import("@/views/Home"),
            },
            {
                path: 'webuser',
                name: 'WebUser',
                component: () => import("@/views/WebUser"),
            },
            {
                path: 'user',
                name: 'User',
                component: () => import("@/views/User"),
            },
            {
                path: 'news',
                name: 'News',
                component: () => import("@/views/News"),
            },
            {
                path: 'person',
                name: 'Person',
                component: () => import("@/views/Person"),
            },
            {
                path: 'bigphoto',
                name: 'BigPhoto',
                component: () => import("@/views/zhuyePhoto/BigPhoto"),
            },
            {
                path: 'smallphoto',
                name: 'SmallPhoto',
                component: () => import("@/views/zhuyePhoto/SmallPhoto"),
            },
            {
                path: 'lifu',
                name: 'Lifu',
                component: () => import("@/views/commodity/Lifu"),
            },
            {
                path: 'marry',
                name: 'Marry',
                component: () => import("@/views/commodity/Marry"),
            },
            {
                path: 'cos',
                name: 'Cos',
                component: () => import("@/views/commodity/Cos"),
            },
            {
                path: 'xiezhen',
                name: 'Xiezhen',
                component: () => import("@/views/commodity/Xiezhen"),
            },
            {
                path: 'doll',
                name: 'Doll',
                component: () => import("@/views/commodity/Doll"),
            },
            {
                path: 'webabout',
                name: 'Webabout',
                component: () => import("@/views/about/Webabout"),
            },
            {
                path: 'translate',
                name: 'Translate',
                component: () => import("@/views/about/Translate"),
            }

        ]
    },
    {
        path: '/about',
        name: 'about',
        component: () => import("@/views/User")
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import("@/views/Login")
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import("@/views/Register")
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

// 限制某些页面禁止未登录访问
let limitPagePath = ['/about']

router.beforeEach((to, from, next) => {
    if (limitPagePath.includes(to.path)) {
        // 判断sessionStorage是否保存了用户信息
        let userStr = sessionStorage.getItem("user") || "{}"
        let user = JSON.parse(userStr)
        if (!user.id) {
            // 跳转到登录页面
            next({path: "/login"})
        } else {
            next()
        }
    } else {
        next()
    }

})

export default router
