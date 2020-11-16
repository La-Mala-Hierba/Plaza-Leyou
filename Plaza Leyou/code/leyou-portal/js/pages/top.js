const lyTop = {
    template: "\
    <div class='nav-top'> \
     <shortcut/>\
        <!--头部--> \
        <div class='header' id='headApp'> \
            <div class='py-container'> \
                <div class='yui3-g Logo'> \
                    <div class='yui3-u Left logoArea'> \
                        <a class='logo-bd' title='Leyou' href='javascript:void(0);' @click='goToHomepage' target='_blank'></a> \
                    </div> \
                    <div class='yui3-u Center searchArea'> \
                        <div class='search'> \
                            <form action='' class='sui-form form-inline'> \
                                <!--searchAutoComplete--> \
                                <div class='input-append'> \
                                    <input type='text' id='autocomplete' v-model='key' \
                                           class='input-error input-xxlarge'/> \
                                    <button @click='search' class='sui-btn btn-xlarge btn-danger' type='button'>Búsqueda</button> \
                                </div> \
                            </form> \
                        </div> \
                        <div class='hotwords'> \
                            <ul> \
                                <li class='f-item'>Los más vendidos</li> \
                                <li class='f-item'>Novedades</li> \
                                <li class='f-item'>Cheques regalo</li> \
                                <li class='f-item'>Moda</li> \
                                <li class='f-item'>Electrónicos</li> \
                                <li class='f-item'>Hogar</li> \
                                 \
                            </ul> \
                        </div> \
                    </div> \
                    <div class='yui3-u Right shopArea'> \
                        <div class='fr shopcar'> \
                            <div class='show-shopcar' id='shopcar'> \
                                <span class='car'></span> \
                                <a class='sui-btn btn-default btn-xlarge' href='javascript:void(0)' @click='goToCart' target='_blank'> \
                                    <span>Mi Cesta</span> \
                                    <i class='shopnum'>0</i> \
                                </a> \
                                <div class='clearfix shopcarlist' id='shopcarlist' style='display:none'> \
                                    <p>'啊哦，你的购物车还没有商品哦！'</p> \
                                    <p>'啊哦，你的购物车还没有商品哦！'</p> \
                                </div> \
                            </div> \
                        </div> \
                    </div> \
                </div> \
                <div class='yui3-g NavList'> \
                    <div class='yui3-u Left all-sort'> \
                        <h4>Leyou recomendados</h4> \
                    </div> \
                    <div class='yui3-u Center navArea'> \
                        <ul class='nav' style='width: 900px'> \
                            <li class='f-item'>Destacados</li> \
                            <li class='f-item'>Leyou Business</li> \
                            <li class='f-item'>Leyou Basics</li> \
                            <li class='f-item'>Supermercado</li> \
                            <li class='f-item'>Atención al ciente</li> \
                            <li class='f-item'>Cine</li> \
                             \
                            </li> \
                        </ul> \
                    </div> \
                    <div class='yui3-u Right'></div> \
                </div> \
            </div> \
        </div>\
       </div> \
      ",
    name:'ly-top',
    data() {
        return {
            key: "",
            query: location.search
        }
    },
    methods: {
        search() {
            window.location = 'search.html?key=' + this.key;
        },
        getUrlParam: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return decodeURI(r[2]);
            }
            return null;
        },
        goToHomepage(){
            window.location = "http://www.leyou.com/index.html";
        },
        goToSecKill(){
            window.location = "http://www.leyou.com/seckill-index.html";
        },
        goToCart(){
            window.location = "http://www.leyou.com/cart.html";
        }

    },
    created() {
        this.key = this.getUrlParam("key");
    },
    components: {
        shortcut:() => import('./shortcut.js')
    }
}
export default lyTop;