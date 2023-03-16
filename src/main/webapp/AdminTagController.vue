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
      const getTags = async () => {
        const response = await axios.get('/api/admin/tags');
        return response.data;
      };
  
      const addTagType = async (tag) => {
        const response = await axios.post('/api/admin/tags', tag);
        return response.data;
      };
  
      const editTag = async (tag) => {
        const response = await axios.post('/api/admin/tags/set-data', tag);
        return response.data;
      };
  
      const removeTagType = async (tagNo) => {
        const response = await axios.get(`/api/admin/tags/${tagNo}/deletion`);
        return response.data;
      };
  
      return {
        getTags,
        addTagType,
        editTag,
        removeTagType
      };
    },
  };
  </script>