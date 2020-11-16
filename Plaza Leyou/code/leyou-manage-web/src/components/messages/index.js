import {Message, MessageBox} from 'element-ui';

const m = {
  info(msg) {
    Message({
      showClose: true,
      message: msg,
      type: 'info'
    });
  },
  error(msg) {
    Message({
      showClose: true,
      message: msg,
      type: 'error'
    });
  },
  success(msg) {
    Message({
      showClose: true,
      message: msg,
      type: 'success'
    });
  },
  warning(msg) {
    Message({
      showClose: true,
      message: msg,
      type: 'warning'
    });
  },
  confirm(msg) {
    return new Promise((resolve, reject) => {
      MessageBox.confirm(msg, 'Recordatorio', {
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cancelar',
        type: 'warning'
      }).then(() => {
        resolve()
      })
        .catch(() => {
          reject()
        });
    })
  },
  prompt(msg) {
    return new Promise((resolve, reject) => {
      MessageBox.prompt(msg, 'Recordatorio', {
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cancelar'
      }).then(({value}) => {
        resolve(value)
      }).catch(() => {
        reject()
      });
    })
  }
}

export default m;
