<template>
    <div>
      <v-btn color='lime lighten-1' @click="addParam">Añadir especificación</v-btn>
        <v-data-table
            :headers="headers"
            :items="params"
            hide-actions
            class="elevation-0"
            >
            <template slot="items" slot-scope="props">
                <td class="text-xs-center" style="background-color: #FFFBF0">{{ props.item.id }}</td>
                <td class="text-xs-center" style="background-color: #FFFBF0">{{ props.item.name }}</td>
                <td class="text-xs-center" style="background-color: #FFFBF0">{{ formatBoolean(props.item.numeric) }}</td>
                <td class="text-xs-center" style="background-color: #FFFBF0">{{ props.item.unit || 'null' }}</td>
                <td class="text-xs-center" style="background-color: #FFFBF0">{{ formatBoolean(props.item.generic) }}</td>
                <td class="text-xs-center" style="background-color: #FFFBF0">{{ formatBoolean(props.item.searching) }}</td>
                <td class="justify-center layout px-0" style="background-color: #FFFBF0">
                <v-btn icon color="teal accent-4" @click="editParam(props.item)">
                    <i class="el-icon-edit"/>
                </v-btn>
                <v-btn icon color="light-blue darken-3" @click="deleteParam(props.item.id)">
                    <i class="el-icon-delete"/>
                </v-btn>
                </td>
            </template>
            <template slot="no-data">
                Esta categoría no dispone de especificaciones
            </template>
            </v-data-table>
            <v-dialog v-model="show" max-width="350px" scrollable>
            <v-card style="background-color: #FFFBF0">
                <v-card-text style="height: 300px;">
                    <v-flex class="px-3">
                    <v-text-field label="Nombre de ：" v-model="param.name"  />
                    <v-checkbox label="¿Es genérica?" v-model="param.generic" color="primary" hide-details/>
                    <v-checkbox label="¿Es numérica?" v-model="param.numeric" color="primary" hide-details/>
                    <v-text-field label="unidad：" v-model="param.unit" v-if="param.numeric"/>
                    <v-checkbox label="¿Es para búsqueda?" v-model="param.searching" color="primary" hide-details/>
                    <v-flex v-if="param.searching && param.numeric" class="px-2">
                        Intervalo de búsqueda y filtro：
                        <v-layout row wrap v-for="(s,i) in param.segments" :key="i">
                            <v-flex xs5>
                                <v-text-field prefix="From: " v-model="s[0]" single-line hide-details/>
                            </v-flex>
                            <v-spacer/>
                            <v-flex xs5>
                                <v-text-field prefix="To: " v-model="s[1]" single-line hide-details/>
                            </v-flex>
                            <v-flex xs1>
                                <v-btn icon @click="param.segments.splice(i,1)">
                                     <i class="el-icon-delete"/>
                                </v-btn>
                            </v-flex>
                        </v-layout>
                        <v-layout row>
                            <v-spacer/>
                            <v-flex xs1>
                                <v-tooltip left>
                                <v-btn slot="activator" icon @click="param.segments.push([0,0])"><v-icon>add</v-icon></v-btn>
                                <span>点击为数值类型的参数添加分段，便于搜索过滤</span>
                                </v-tooltip>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    </v-flex>
                </v-card-text>
                <v-card-actions>
                    <v-spacer/>
                    <v-btn color="blue darken-1" flat @click.native="show=false">Cancelar</v-btn>
                    <v-btn color="blue darken-1" flat @click.native="save">Guardar</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>
<script>
export default {
  name: "v-spec-param",
  props: {
    group: {
      type: Object
    }
  },
  data() {
    return {
      headers: [
        { text: "id", value: "id", align: "center", sortable: false },
        { text: "nombre de especificación", value: "name", align: "center", sortable: false },
        { text: "¿es númerica?", value: "numeric",
          align: "center",
          sortable: false
        },
        { text: "unidad", value: "unit", align: "center", sortable: false },
        {
          text: "¿es genérica?",
          value: "generic",
          align: "center",
          sortable: false
        },
        {
          text: "¿es para búsqueda?",
          value: "searching",
          align: "center",
          sortable: false
        },
        { text: "operación", align: "center", sortable: false }
      ],
      params: [], // 参数
      show: false,
      param: {},
      isEdit: false
    };
  },
  watch: {
    group:{
      deep:true,
      handler(val){
          if(val && val.id){ //判断选择的组是否为空，并且组的id不为空
            this.loadData();
          }
      }
    }
  },
  methods: {
    loadData() {
      this.$http
        .get("/item/spec/params?gid=" + this.group.id)
        .then(({ data }) => {
          data.forEach(p => {
              p.segments = p.segments ? p.segments.split(",").map(s => s.split("-")) : [];
          })
          this.params = data;
        })
        .catch(() => {
          this.params = [];
        });
    },
    editParam(param) {
        this.param = param;
        this.isEdit = false;
        this.show = true;
    },
    addParam() {
      this.param = {
          cid: this.group.cid,
          groupId: this.group.id,
          segments:[],
          numeric:false,
          searching:false,
          generic:false}
      this.show = true;
      this.isEdit = true;
    },
    deleteParam(id) {
        this.$message.confirm("¿Está seguro de cancelar esta especificación?")
        .then(() => {
            this.$http.delete("/item/spec/param/" + id)
            .then(() => {
              this.loadData();
                this.$message.success("Especificación cancelada");
            })
            .catch(() => {
                this.$message.error("Fallado");
            })
        })
    },
    formatBoolean(boo) {
      return boo ? "Sí" : "No";
    },
    save(){
        const p = {};
        Object.assign(p, this.param);
        p.segments = p.segments.map(s => s.join("-")).join(",")
        this.$http({
            method: this.isEdit ? 'put' : 'post',
            url: '/item/spec/param',
            data: p,
        }).then(() => {
            // 关闭窗口
            this.show = false;
            this.$message.success("¡Guardado con éxito！");
            this.loadData();
          }).catch(() => {
              this.$message.error("¡Fallado！");
            });
    }
  }
};
</script>
