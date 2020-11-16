<template>
  <v-app :dark="dark">
    <!-- navigation-drawer -->
    <v-navigation-drawer
      :dark="dark"
      persistent
      :mini-variant="miniVariant"
      v-model="drawer"
      enable-resize-watcher
      fixed
      app
      style="background-color: #F3F9F1"
    >
      <v-toolbar flat class="transparent" style="background-color: #F3F9F1">
        <v-list class="pa-0">
          <v-list-tile avatar>
            <v-list-tile-avatar>
              <img src="../assets/Perfil.jpg">
            </v-list-tile-avatar>
            <v-list-tile-content>
              <v-list-tile-title>Shu Shu</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </v-list>
      </v-toolbar>
      <v-divider/>
      <!-- Menu al lado izquierda -->
      <v-list class="pt-0" dense style="background-color: #F3F9F1">
        <v-list-group
          v-model="item.active"
          v-for="item in items"
          :key="item.title"
          :prepend-icon="item.action"
          no-action
        >
          <!--Menu 1st level -->
          <v-list-tile slot="activator">
            <v-list-tile-content>
              <v-list-tile-title>{{ item.title }}</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
          <!-- Menu 2nd level -->
          <v-list-tile v-for="subItem in item.items" :key="subItem.title" :to="item.path + subItem.path">
            <v-list-tile-content>
              <v-list-tile-title>{{ subItem.title }}</v-list-tile-title>
            </v-list-tile-content>
            <v-list-tile-action>
              <v-icon>{{ subItem.action }}</v-icon>
            </v-list-tile-action>
          </v-list-tile>
        </v-list-group>
      </v-list>
    </v-navigation-drawer>
    <!-- toolbar -->
    <v-toolbar
      app
      dark
          :color="dark ? 'secondary' : 'primary'"
    >
      <!-- ocultar el buton del menu izquierda-->
      <v-toolbar-side-icon @click.stop="drawer = !drawer"/>
      <!-- 收起左侧菜单的按钮-->
      <v-btn icon @click.stop="miniVariant = !miniVariant">
        <v-icon v-html="miniVariant ? 'chevron_right' : 'chevron_left'"/>
      </v-btn>
      <!-- cambiar el temario -->
      <v-flex xs1>
        <v-switch
          label="luminoso"
          color="primary"
          hide-details
        />
      </v-flex>
      <!-- Titile de toolbar -->
      <v-flex xs3></v-flex>
      <v-toolbar-title v-text="title"/>
      <v-spacer/>

      <!-- 调色板 -->
      <v-btn icon @click.stop="dark = !dark">
        <v-icon>invert_colors</v-icon>
      </v-btn>
      <!-- 顶部导航用户菜单 -->
      <v-btn icon @click.stop="dark = !dark">
        <v-icon>account_box</v-icon>
      </v-btn>
    </v-toolbar>
    <!--body-->
      <v-content style="background-color: #FFFBF0">
        <v-breadcrumbs>
          <v-icon slot="divider">chevron_right</v-icon>
          <v-breadcrumbs-item>{{item1}}</v-breadcrumbs-item>
          <v-breadcrumbs-item>{{item2}}</v-breadcrumbs-item>
        </v-breadcrumbs>
        <div>
        <!--定义一个路由锚点，Layout的子组件内容将在这里展示-->
        <router-view style="background-color: #FFFBF0"/>
      </div>
    </v-content>
  </v-app>
</template>

<script>
  import menus from "../menu";

  export default {
    data() {
      return {
        dark: false,// 是否暗黑主题
        drawer: true,// 左侧导航是否隐藏
        miniVariant: false,// 左侧导航是否收起
        title: 'Plaza Leyou Management',// 顶部导航条名称,
        menuMap: {}
      }
    },
    computed: {
      items() {
        return menus;
      },
      item1() {
        const arr = this.$route.path.split("/");
        return this.menuMap[arr[1]].name;
      },
      item2() {
        const arr = this.$route.path.split("/");
        return this.menuMap[arr[1]][arr[2]];
      }
    },
    name: 'App',
    watch: {},
    created() {
      menus.forEach(m => {
        const p1 = m.path.slice(1);
        this.menuMap[p1] = {name: m.title};
        m.items.forEach(i => {
          this.menuMap[p1][i.path.slice(1)] = i.title;
        })
      })
    }
  }
</script>

<style scoped>
  .box {
    width: 90%;
  }
</style>
