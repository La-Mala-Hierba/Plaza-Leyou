var menus = [
  {
    action: "home",
    title: "Homepage",
    path:"/index",
    items: [{ title: "Estadística", path: "/dashboard" }]
  },
  {
    action: "apps",
    title: "Gestión de productos",
    path:"/item",
    items: [
      { title: "Gestión de Catálogo", path: "/category" },
      { title: "Gestión de Marcas", path: "/brand" },
      { title: "Listado de Productos", path: "/list" },
      { title: "Especificaciones", path: "/specification" }
    ]
  },
  {
    action: "people",
    title: "Gestión de Usuarios",
    path:"/user",
    items: [
      { title: "Estadística de Usuarios", path: "/statistics" },
      { title: "Listado de Usuarios", path: "/list" }
    ]
  },
  {
    action: "attach_money",
    title: "Gestión de Ventas",
    path:"/trade",
    items: [
      { title: "Estadística de Ventas", path: "/statistics" },
      { title: "Gestión de Pedidos", path: "/order" },
      { title: "Gestión de Logística", path: "/logistics" },
      { title: "Gestión de Promociones", path: "/promotion" }
    ]
  },
  {
    action: "settings",
    title: "Gestión de Autorización",
    path:"/authority",
    items: [
      { title: "Listado de Autorización", path: "/list" },
      { title: "Gestión de Rol", path: "/role" },
      { title: "Autorización de Miembros", path: "/member" }
    ]
  }
]

export default menus;
