<script>
import axios from 'axios';

export default {
  data() {
    return {
      boards: [],
      board: {},
      boardNo: null,
      userNo: null,
    };
  },
  methods: {
    getBoardList() {
      axios
        .get('/admin/freeboard/')
        .then((response) => {
          this.boards = response.data;
        })
        .catch((error) => {
          console.error('Error getting board list:', error);
        });
    },
    getBoardDetail(boardNo) {
      axios
        .get(`/admin/freeboard/${boardNo}`)
        .then((response) => {
          this.board = response.data;
        })
        .catch((error) => {
          console.error('Error getting board detail:', error);
        });
    },
    addBoard(board) {
      axios
        .post('/admin/freeboard/', board)
        .then((response) => {
          console.log('Board added:', response.data);
        })
        .catch((error) => {
          console.error('Error adding board:', error);
        });
    },
    updateBoard(boardNo, board) {
      axios
        .put(`/admin/freeboard/${boardNo}`, board)
        .then((response) => {
          console.log('Board updated:', response.data);
        })
        .catch((error) => {
          console.error('Error updating board:', error);
        });
    },
    deleteBoard(boardNo, userNo) {
      axios
        .delete(`/admin/freeboard/${boardNo}`, { params: { userNo } })
        .then((response) => {
          console.log('Board deleted');
        })
        .catch((error) => {
          console.error('Error deleting board:', error);
        });
    },
  },
};
</script>