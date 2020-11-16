const shortcut = {
    template: "\
    <div class='py-container'> \
        <div class='shortcut'> \
            <ul class='fl'> \
               <li class='f-item'>¡Bienvenida a Plaza Leyou! &nbsp;&nbsp;</li>     \
               <li class='f-item' v-if='user && user.username'>\
                <span style='color: red;'>{{user.username}}</span>\
               </li>\
               <li v-else class='f-item'> \
                   <a href='javascript:void(0)' @click='goToLogin'>Iniciar Sesión</a>　 \
                   <span><a href='javascript:void(0)' @click='goToRegister'>&nbsp; Regístrate</a></span> \
               </li> \
           </ul> \
           <ul class='fr'> \
               <li class='f-item' @click='goToMyOrders'>Mis pedidos</li> \
               <li class='f-item space'></li> \
               <li class='f-item'>Mi cuenta</a></li> \
               <li class='f-item space'></li> \
               <li class='f-item'>Leyou VIP</li> \
               <li class='f-item space'></li> \
                \
                \
                \
                \
               <li class='f-item' id='service'> \
                   <span>Atención al cliente</span> \
                   <ul class='service'> \
                       <li><a href='cooperation.html' target='_blank'>合作招商</a></li> \
                       <li><a href='shoplogin.html' target='_blank'>商家后台</a></li> \
                       <li><a href='cooperation.html' target='_blank'>合作招商</a></li> \
                       <li><a href='#'>商家后台</a></li> \
                   </ul> \
               </li> \
               <li class='f-item space'></li> \
               <li class='f-item'>Sobre Plaza Leyou</li> \
           </ul> \
       </div> \
    </div>\
    ",
    name: "shortcut",
    data() {
        return {
            user: null
        }
    },
    created() {
        ly.http("/auth/verify")
            .then(resp => {
                this.user = resp.data;
            })
    },
    methods: {
        goToLogin() {
            window.location = "http://www.leyou.com/login.html?returnUrl=" + window.location;
        },
        goToRegister(){
            window.location = "http://www.leyou.com/register.html";
        },
        goToMyOrders(){
            window.location = "http://www.leyou.com/home-index.html";
        }
    }
}
export default shortcut;