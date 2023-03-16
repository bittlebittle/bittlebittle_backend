<template>
    <div>
      <!-- Add your template code here -->
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  import axios from 'axios';
  
  export default {
    setup() {
      const getAllFoods = async () => {
        const response = await axios.get('/api/admin/foods');
        return response.data;
      };
  
      const addFood = async (newFood) => {
        const response = await axios.post('/api/admin/foods', newFood);
        return response.data;
      };
  
      const getFood = async (foodNo) => {
        const response = await axios.get(`/api/admin/foods/${foodNo}`);
        return response.data;
      };
  
      const editFood = async (editFood) => {
        const response = await axios.post('/api/admin/foods/set-data', editFood);
        return response.data;
      };
  
      const removeFood = async (foodNo) => {
        const response = await axios.get(`/api/admin/foods/${foodNo}/deletion`);
        return response.data;
      };
  
      return {
        getAllFoods,
        addFood,
        getFood,
        editFood,
        removeFood
      };
    },
  };
  </script>