<template>
    <div>
      <!-- 議고쉶 -->
      <button @click="getBottle(bottleNo)">議고쉶</button>
  
      <!-- 異붽� -->
      <button @click="addBottle(bottle)">異붽�</button>
  
      <!-- �닔�젙 -->
      <button @click="editBottle(editBottle)">�닔�젙</button>
  
      <!-- �궘�젣 -->
      <button @click="removeBottle(bottleNo)">�궘�젣</button>
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  
  export default {
    name: 'AdminBottle',
    data() {
      return {
        bottleNo: 1,
        bottle: { /* bottle 객체 */ },
        editBottle: { /* 수정할 bottle 객체 */ },
      }
    },
    methods: {
      // 議고쉶
      async getBottle(bottleNo) {
        try {
          const response = await axios.get(`/api/admin/bottles/${bottleNo}`)
          this.bottle = response.data
          console.log(this.bottle)
        } catch (error) {
          console.error(error)
        }
      },
      // 異붽�
      async addBottle(bottle) {
        try {
          const response = await axios.post('/api/admin/bottles', bottle)
          console.log(response.data)
        } catch (error) {
          console.error(error)
        }
      },
      // �닔�젙
      async editBottle(editBottle) {
        try {
          const response = await axios.post('/api/admin/bottles/set-data', editBottle)
          console.log(response.data)
        } catch (error) {
          console.error(error)
        }
      },
      // �궘�젣
      async removeBottle(bottleNo) {
        try {
          const response = await axios.get(`/api/admin/bottles/${bottleNo}/deletion`)
          console.log(response.data)
        } catch (error) {
          console.error(error)
        }
      },
    },
  }
  </script>
  
  <!--------
위 코드에서 axios를 이용하여 HTTP 요청을 보내고, 
각 버튼 클릭 이벤트마다 async/await 구문을 사용하여 
비동기적으로 HTTP 요청을 처리하고 있습니다. 
각 버튼의 클릭 이벤트에서 HTTP 요청을 처리하고, 
요청에 대한 응답 결과는 console.log를 통해 콘솔에 출력하고 있습니다. 
이 코드를 적절한 컴포넌트에 적용하여 사용하시면 됩니다.

-->