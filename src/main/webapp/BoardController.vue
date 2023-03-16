<script>
import { ref } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const boards = ref([]);
    const board = ref({});
    const boardNo = ref(null);
    const userNo = ref(null);

    const getBoardList = () => {
      axios
        .get('/freeboard/')
        .then((response) => {
          boards.value = response.data;
        })
        .catch((error) => {
          console.error('Error getting board list:', error);
        });
    };

    const getBoardDetail = (boardNo) => {
      axios
        .get(`/freeboard/${boardNo}`)
        .then((response) => {
          board.value = response.data;
        })
        .catch((error) => {
          console.error('Error getting board detail:', error);
        });
    };

    const addBoard = (board) => {
      axios
        .post('/freeboard/', board)
        .then((response) => {
          console.log('Board added:', response.data);
        })
        .catch((error) => {
          console.error('Error adding board:', error);
        });
    };

    const updateBoard = (boardNo, board) => {
      axios
        .put(`/freeboard/${boardNo}`, board)
        .then((response) => {
          console.log('Board updated:', response.data);
        })
        .catch((error) => {
          console.error('Error updating board:', error);
        });
    };

    const deleteBoard = (boardNo, userNo) => {
      axios
        .delete(`/freeboard/${boardNo}`, { params: { userNo } })
        .then((response) => {
          console.log('Board deleted');
        })
        .catch((error) => {
          console.error('Error deleting board:', error);
        });
    };

    return {
      boards,
      board,
      boardNo,
      userNo,
      getBoardList,
      getBoardDetail,
      addBoard,
      updateBoard,
      deleteBoard,
    };
  },
};
</script>