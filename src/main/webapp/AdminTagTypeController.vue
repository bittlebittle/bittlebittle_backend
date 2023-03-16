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
      const getTagTypes = async () => {
        const response = await axios.get('/api/admin/tagtypes');
        return response.data;
      };
  
      const addTagType = async (tagTypeName) => {
        const response = await axios.post('/api/admin/tagtypes', tagTypeName);
        return response.data;
      };
  
      const editTagType = async (tagType) => {
        const response = await axios.post('/api/admin/tagtypes/set-data', tagType);
        return response.data;
      };
  
      const removeTagType = async (tagTypeNo) => {
        const response = await axios.get(`/api/admin/tagtypes/${tagTypeNo}/deletion`);
        return response.data;
      };
  
      return {
        getTagTypes,
        addTagType,
        editTagType,
        removeTagType
      };
    },
  };
  </script>
  