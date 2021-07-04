import Vue from 'vue'
import 'github-markdown-css/github-markdown.css'

import hljs from 'highlight.js'
import 'highlight.js/styles/atom-one-dark.css'
// 如果开启了typescript 需要额外安装 npm install @types/highlight.js
// 通过 import * as hljs from 'highlight.js' 引入
Vue.directive('highlight', function (el) {
  const blocks = el.querySelectorAll('pre code')
  blocks.forEach(block => {
    hljs.highlightBlock(block)
  })
})


import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';

import Prism from 'prismjs';
VueMarkdownEditor.use(vuepressTheme, {
  Prism,
  extend(md) {},
});

Vue.use(VueMarkdownEditor);

import createTipPlugin from '@kangc/v-md-editor/lib/plugins/tip/index';
import '@kangc/v-md-editor/lib/plugins/tip/tip.css';
VueMarkdownEditor.use(createTipPlugin());

import createEmojiPlugin from '@kangc/v-md-editor/lib/plugins/emoji/index';
import '@kangc/v-md-editor/lib/plugins/emoji/emoji.css';
VueMarkdownEditor.use(createEmojiPlugin());

import createTodoListPlugin from '@kangc/v-md-editor/lib/plugins/todo-list/index';
import '@kangc/v-md-editor/lib/plugins/todo-list/todo-list.css';
VueMarkdownEditor.use(createTodoListPlugin());

import createCopyCodePlugin from '@kangc/v-md-editor/lib/plugins/copy-code/index';
import '@kangc/v-md-editor/lib/plugins/copy-code/copy-code.css';
VueMarkdownEditor.use(createCopyCodePlugin());

import VMdPreviewHtml from '@kangc/v-md-editor/lib/preview-html';
import createCopyCodePreview from '@kangc/v-md-editor/lib/plugins/copy-code/preview';
import '@kangc/v-md-editor/lib/plugins/copy-code/copy-code.css';
VMdPreviewHtml.use(createCopyCodePreview());

import createAlignPlugin from '@kangc/v-md-editor/lib/plugins/align';
VueMarkdownEditor.use(createAlignPlugin());

import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';

// highlightjs
// VMdPreview.use(githubTheme, {
//   Hljs: hljs,
// });

VMdPreview.use(vuepressTheme, {
  Prism,
  extend(md) {},
});

Vue.use(VMdPreview);
