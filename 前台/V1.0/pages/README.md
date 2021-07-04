# CSS命名规范

考虑到CSS为全局响应，为了避免各层级之间的相互干扰，我们对CSS命名进行以下的规范：

## 局部作用域 scope

采用局部作用域符号 ##scope## 对页面内CSS的内容进行限定，保证各页面之间的CSS样式不会互相影响。

```css
<style scope>
</style>
```

## page前缀

由于 ##Nuxt.JS## 框架，页面文件名，即路由和页面名。页面内的CSS，采用页面名作为前缀说明。这里以首页为例：

```css
.index-xxx {

}
```

## 二级CSS

依据组件化思想，页面内CSS根据功能的不同采用二级CSS的对其进行命名。

```css
.index-xxx .title {

}

.index-xxx .text {

}
```

## 总体与个体

页面编写中，或许会出现列表重复渲染单个模块的情况，这里根据情况采用如下关键字，对其进行命名：

```css
.index-xxx-list {

}

.index-xxx-single {

}

.index-xxx-block {

}
```

## 层叠布局

由于特效动画的原因，或许出现层叠布局的情况出现，此时我们将使用 `relative` 对其进行修饰，并加上合适的后缀，以及使用 `z-index` 来处理命名和实际的使用问题。

```css
.index-xxx .relative-up {

}

.index-xxx .relative-down {

}
```

## 注释功能分区

这里我们将与之前提到的 **二级CSS** 共同来处理页面内组件化功能，使用注释将同一功能的CSS进行分块，来提高代码的可读性。

```css
/*===== 菜单栏按钮功能 =====*/

/*==========*/
```