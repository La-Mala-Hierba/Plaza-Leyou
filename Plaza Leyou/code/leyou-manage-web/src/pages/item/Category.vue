<template>
  <v-card style="background-color: #FFFBF0">
      <v-flex xs12 sm10>
        <v-tree url="/item/category/list" style="background-color: #FFFBF0"
                :isEdit="isEdit"
                @handleAdd="handleAdd"
                @handleEdit="handleEdit"
                @handleDelete="handleDelete"
                @handleClick="handleClick"
        />
      </v-flex>
  </v-card>
</template>

<script>
  export default {
    name: "category",
    data() {
      return {
        isEdit:true,
        categoryName: ""
      }
    },
    methods: {
      handleAdd(node) {
        this.$http.post("/item/category", node)
          .then(() => {
            this.$message.success("¡Categoría añadida!");
          })
          .catch();
      },
      handleEdit(id, name) {
       // console.log("edit... id: " + id + ", name: " + name)
        this.$http.put("/item/category/"+ id+"/"+ name)
          .then(() => {
            this.$message.success("Editado con éxito!");
          })
          .catch(() => {
            this.$message.error("Fallido!");
          });
      },
      handleDelete(id) {
        console.log("delete ... " + id)
        this.$http.delete("/item/category/cid/"+ id)
          .then(() => {this.$message.success("Eliminado con éxito!")})
          .catch(() => this.$message.error("Fallido!"));
      },
      handleClick(node) {
        console.log(node)
      },
      reload(){
        this.$http.get("http://api.leyou.com/api/item/category/list")
      }
    }
  };
</script>

<style scoped>

</style>
