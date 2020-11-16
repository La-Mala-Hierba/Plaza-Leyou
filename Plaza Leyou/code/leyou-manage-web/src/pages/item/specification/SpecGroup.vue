<template>
    <div style="background-color: #FFFBF0">
      <v-btn color='lime lighten-1' @click="addGroup">Añadir grupo</v-btn>
        <v-data-table
        :headers="headers"
        :items="groups"
        hide-actions
        class="elevation-0"
        >
            <template slot="items" slot-scope="props">
                <tr @click="selectGroup(props.item)" style="background-color: #FFFBF0">
                    <td class="text-xs-center">{{ props.item.id }}</td>
                    <td class="text-xs-center">{{ props.item.name }}</td>
                    <td class="justify-center layout px-0">
                    <v-btn icon color="teal accent-4" @click.stop="editGroup(props.item)">
                        <i class="el-icon-edit"/>
                    </v-btn>
                    <v-btn icon color="light-blue darken-3" @click.stop="deleteGroup(props.item.id)">
                        <i class="el-icon-delete"/>
                    </v-btn>
                    </td>
                </tr>
            </template>
            <template slot="no-data">
                De momento esta categoría no dispone de grupo de especificaciones o la categoría no está seleccionada
            </template>
        </v-data-table>

        <v-dialog v-model="show" width="300" height="200">
        <v-card style="background-color: #FFFBF0">
            <v-card-text>
                <v-text-field label="Nombre de grupo：" v-model="group.name"  />
            </v-card-text>
            <v-card-actions>
                <v-spacer/>
                <v-btn color="primary" flat @click.native="show=false">Cancelar</v-btn>
                <v-btn color="primary" flat @click.native="save">Guardar</v-btn>
            </v-card-actions>
        </v-card>
        </v-dialog>
    </div>
</template>

<script>
export default {
  name: "spec-group",
  props: {
    cid: {
      type: Number,
      default:0,
    },
  },
  data() {
    return {
      groups:[],
      headers: [
        { text: "id", value: "id", align: "center", sortable: false },
        { text: "nombre de grupo", value: "name", align: "center", sortable: false },
        { text: "operación", align: "center", sortable: false }
      ],
      show: false, // 是否打开编辑窗口
      group:{name:''},
      isEdit: false, // 是否是编辑
    };
  },
  watch:{
      cid(){
          this.loadData();
      }
  },
  methods:{
      loadData(){
          this.$http.get("/item/spec/groups/" + this.cid)
          .then(({data}) => {
              this.groups = data;
          })
          .catch(() => {
              this.groups = [];
          })
      },
      editGroup(group){
          Object.assign(this.group, group);
          this.show = true;
          this.isEdit = true;
      },
      addGroup(){
          this.group = {cid:this.cid};
          this.show = true;
          this.isEdit = false;
          console.log(this.group)
      },
      save(){
           this.$http({
            method: this.isEdit ? 'put' : 'post',
            url: '/item/spec/group',
            data: this.group
          }).then(() => {
            // 关闭窗口
            this.show = false;
            this.$message.success("Guardado con éxito！");
            this.loadData();
          }).catch(() => {
              this.$message.error("Fallido！");
            });
      },
      deleteGroup(id){
          this.$message.confirm("¿Está seguro de cancelar el grupo?")
          .then(() => {
            this.$http.delete("/item/spec/group/" + id)
                .then(() => {
                    this.$message.success("Grupo cancelado");
                    this.loadData();
                })
          })
      },
      selectGroup(group){
          this.$emit("select", group);
      }
  }
};
</script>

<style>
  .backcolor{
    background-color: #FFFBF0;
  }
</style>
