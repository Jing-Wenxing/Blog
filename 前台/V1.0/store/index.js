export const state = () => ({
  navbar_istop: true,
  navbar_statu_islight: true,
  navbar_font: true,
  navbar_exist: true,
})

export const mutations = {
  // 状态栏是否在顶部
  ChangeTopFalse(state, key) {
    this.state.navbar_istop = false
  },
  ChangeTopTrue(state, key) {
    this.state.navbar_istop = true
  },
  // 状态栏的主题状态
  ChangeStatuFalse(state, key) {
    this.state.navbar_statu_islight = false
  },
  ChangeStatuTrue(state, key) {
    this.state.navbar_statu_islight = true
  },
  // 状态栏的字体自由状态
  ChangeFontFalse(state, key) {
    this.state.navbar_font = false
  },
  ChangeFontTrue(state, key) {
    this.state.navbar_font = true
  },
  // 状态栏是否存在
  ChangeExistFalse(state, key) {
    this.state.navbar_exist = false
  },
  ChangeExistTrue(state, key) {
    this.state.navbar_exist = true
  }
}
