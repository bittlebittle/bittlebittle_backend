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
      const getReviews = async (bottleNo) => {
        const response = await axios.get(`/api/bottles/${bottleNo}/reviews`);
        return response.data;
      };
  
      const getReview = async (reviewNo) => {
        const response = await axios.get(`/api/bottles/${reviewNo}/reviews/${reviewNo}`);
        return response.data;
      };
  
      const addReview = async (bottleNo, review) => {
        const response = await axios.post(`/api/bottles/${bottleNo}/reviews`, review);
        return response.data;
      };
  
      const editReview = async (bottleNo, review) => {
        const response = await axios.post(`/api/bottles/${bottleNo}/reviews/set-data`, review);
        return response.data;
      };
  
      const deleteReview = async (bottleNo, reviewNo) => {
        const response = await axios.get(`/api/bottles/${bottleNo}/reviews/${reviewNo}/deletion`);
        return response.data;
      };
  
      const getReplies = async (reviewNo) => {
        const response = await axios.get(`/api/bottles/${reviewNo}/reviews/${reviewNo}/replies`);
        return response.data;
      };
  
      const addReply = async (reviewNo, reply) => {
        const response = await axios.post(`/api/bottles/${reviewNo}/reviews/${reviewNo}/replies`, reply);
        return response.data;
      };
  
      const updateReply = async (reviewNo, reply) => {
        const response = await axios.post(`/api/bottles/${reviewNo}/reviews/${reviewNo}/replies/set-data`, reply);
        return response.data;
      };
  
      const deleteReply = async (reviewNo, replyNo) => {
        const response = await axios.get(`/api/bottles/${reviewNo}/reviews/${reviewNo}/replies/${replyNo}/deletion`);
        return response.data;
      };
  
      return {
        getReviews,
        getReview,
        addReview,
        editReview,
        deleteReview,
        getReplies,
        addReply,
        updateReply,
        deleteReply
      };
    },
  };
  </script>
  