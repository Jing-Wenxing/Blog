const components = require('prismjs/components');
const allLanguages = Object.keys(components.languages).filter((item) => item !== 'meta');

export default {
  // Target: https://go.nuxtjs.dev/config-target
  target: 'static',

  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title: 'JingWenxing Blog 后台管理面板',
    htmlAttrs: {
      lang: 'zh'
    },
    meta: [{
        charset: 'utf-8'
      },
      {
        name: 'viewport',
        content: 'width=device-width, initial-scale=1'
      },
      {
        hid: 'description',
        name: 'description',
        content: ''
      }
    ],
    link: [{
      rel: 'icon',
      type: 'image/x-icon',
      href: '/favicon.ico'
    }]
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [
    // Concise-CSS 框架
    '~/assets/css/concise.css',
    // 公共CSS
    '~/assets/css/public.css',
    // IconFont 资源引入
    '~/assets/iconfont/iconfont.css',
    'element-ui/lib/theme-chalk/index.css'
  ],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
    '@/plugins/element-ui',
    '@/plugins/vant',
    '@/plugins/markdown-show',
  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [],

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    // https://go.nuxtjs.dev/axios
    '@nuxtjs/axios',
  ],

  // Axios module configuration: https://go.nuxtjs.dev/config-axios
  axios: {
    proxy: true,
    credentials: true, //认证信息
    // baseURL: `https://${process.env.HOST || 'localhost'}:${process.env.PORT||3000}`,
    headers: {
      'Content-Type': 'application/json; charset=utf-8'
    },
    prefix: '/api', // baseURL
    credentials: true,
  },
  proxy: {
    '/api': {
      // target: 'http://jingwenxing.top:8083', // 代理地址
      target: 'http://localhost:8083', // 代理地址
      changeOrigin: true,
      pathRewrite: {
        '^/api': '', //将 /api 替换掉
      }
    }
  },

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {
    transpile: [/^element-ui/],
    babel: {
      plugins: [
        [
          'prismjs',
          {
            languages: allLanguages,
          }
        ]
      ]
    }
  }
}
