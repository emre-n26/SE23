<template>
  <div>
    <admin-table
      title="Hier kannst Du Sprüche hinzufügen, bewerten und sortieren"
    ></admin-table>
  </div>
</template>

<script>
import AdminTable from "@/components/AdminTable";

export default {
  name: "AdminConsole",
  components: {
    AdminTable,
  },
};
</script>
