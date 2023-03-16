<script>
  import axios from 'axios';

export default {
name: 'BottleController',

setup() {
// bottleList, selectedBottle, favoriteList 변수 초기값 설정
const bottleList = ref([]);
const selectedBottle = ref({});
const favoriteList = ref([]);

// axios를 통해 서버로부터 bottleList를 받아와 bottleList 변수에 저장하는 함수
const getBottleList = async () => {
  try {
    const response = await axios.get('/api/bottles/all');
    bottleList.value = response.data;
  } catch (error) {
    console.error(error);
  }
};

// axios를 통해 서버로부터 선택한 bottle 정보를 받아와 selectedBottle 변수에 저장하는 함수
const getSelectedBottle = async (bottleNo) => {
  try {
    const response = await axios.get(`/api/bottles/${bottleNo}`);
    selectedBottle.value = response.data;
  } catch (error) {
    console.error(error);
  }
};

// axios를 통해 서버로부터 사용자의 favorite 정보를 받아와 favoriteList 변수에 저장하는 함수
const getFavoriteList = async (bottleNo) => {
  try {
    const response = await axios.get(`/api/bottles/${bottleNo}/favorite`);
    favoriteList.value = response.data;
  } catch (error) {
    console.error(error);
  }
};

// axios를 통해 서버로부터 최신 순으로 정렬된 bottleList를 받아와 bottleList 변수에 저장하는 함수
const getNewBottleList = async () => {
  try {
    const response = await axios.get('/api/bottles?sorted=new');
    bottleList.value = response.data;
  } catch (error) {
    console.error(error);
  }
};

// axios를 통해 서버로부터 인기 순으로 정렬된 bottleList를 받아와 bottleList 변수에 저장하는 함수
const getBestBottleList = async () => {
  try {
    const response = await axios.get('/api/bottles?sorted=best');
    bottleList.value = response.data;
  } catch (error) {
    console.error(error);
  }
};

// axios를 통해 서버로부터 관심있는 bottle 정보가 들어있는 bottleList를 받아와 bottleList 변수에 저장하는 함수
const getRelatedFavoriteBottleList = async () => {
  try {
    const response = await axios.get('/api/bottles?sorted=relatedFavorite');
    bottleList.value = response.data;
  } catch (error) {
    console.error(error);
  }
};

// axios를 통해 서버로부터 메인 페이지에 표시할 bottle 정보가 들어있는 객체를 받아와 반환하는 함수
const getMainBottle = async () => {
  try {
    const response = await axios.get('/api/bottles');
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

// axios를 통해 서버로 사용자가 선택한 bottle을 favorite에 추가 또는 삭제하는 함수
const toggleFavorite = async (bottleNo) => {
  try {
    const response = await axios.get(`/api/bottles/${bottleNo}/favorite`);
    favoriteList.value = response.data;
  }catch (error) {
    console.error(error);
  }

  </script>