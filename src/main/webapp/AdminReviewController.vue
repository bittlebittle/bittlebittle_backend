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
      const getReviewList = async (bottleNo) => {
        const response = await axios.get(`/api/admin/bottles/${bottleNo}/reviews`);
        return response.data;
      };
  
      const getReview = async (reviewNo) => {
        const response = await axios.get(`/api/admin/bottles/${reviewNo}/reviews/${reviewNo}`);
        return response.data;
      };
  
      const removeReview = async (bottleNo, reviewNo) => {
        const response = await axios.get(`/api/admin/bottles/${bottleNo}/reviews/${reviewNo}/deletion`);
        return response.data;
      };
  
      const getReplies = async (reviewNo) => {
        const response = await axios.get(`/api/admin/bottles/${reviewNo}/reviews/${reviewNo}/replies`);
        return response.data;
      };
  
      const removeReply = async (reviewNo, replyNo) => {
        const response = await axios.get(`/api/admin/bottles/${reviewNo}/reviews/${reviewNo}/replies/${replyNo}/deletion`);
        return response.data;
      };
  
      return {
        getReviewList,
        getReview,
        removeReview,
        getReplies,
        removeReply
      };
    },
  };
  </script>
  