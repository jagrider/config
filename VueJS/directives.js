import Vue from 'vue';
import App from './App.vue'

// Detect when a click occurs outside of the given element
// Source: https://stackoverflow.com/questions/36170425/detect-click-outside-element
Vue.directive('click-outside', {
  bind: function (el, binding, vnode) {
    el.clickOutsideEvent = function (event) {
      // Check that click was outside the el and its children
      if (!(el == event.target || el.contains(event.target))) {
        //If it did, call method provided in attribute value
        vnode.context[binding.expression](event);
      }
    };
    document.body.addEventListener('click', el.clickOutsideEvent)
  },
  unbind: function (el) {
    document.body.removeEventListener('click', el.clickOutsideEvent)
  },
});


// Focus an element after it is created
// Useful for adding details to forms (I.e., input fields)
// Source: https://stackoverflow.com/questions/39525457/focus-input-of-freshly-added-item
Vue.directive('focus-on-create', {
  bind: function(el) {
    Vue.nextTick(() => {
      el.focus();
    })
  }
});

// NOTE: The following can also be done by accessing the element via a `ref`, I.e., 
this.$refs.myItemList[index].select();