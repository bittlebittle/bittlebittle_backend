<script>
import { ref } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const replies = ref([]);
    const boardNo = ref(null);

    const getReplyList = (boardNo) => {
      axios
        .get(`/reply/`, { params: { boardNo } })
        .then((response) => {
          replies.value = response.data;
        })
        .catch((error) => {
          console.error('Error getting reply list:', error);
        });
    };

    const addReply = (reply, boardNo) => {
      axios
        .post('/reply/', reply, { params: { boardNo } })
        .then((response) => {
          console.log('Reply added:', response.data);
        })
        .catch((error) => {
          console.error('Error adding reply:', error);
        });
    };

    const deleteReply = (replyNo) => {
      axios
        .delete(`/reply/${replyNo}`)
        .then((response) => {
          console.log('Reply deleted:', response.data);
        })
        .catch((error) => {
          console.error('Error deleting reply:', error);
        });
    };

    return {
      replies,
      boardNo,
      getReplyList,
      addReply,
      deleteReply,
    };
  },
};
</script>