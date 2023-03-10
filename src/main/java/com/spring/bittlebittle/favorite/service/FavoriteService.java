package com.spring.bittlebittle.favorite.service;

import com.spring.bittlebittle.favorite.vo.Favorite;

public interface FavoriteService {

	int addFavorite(Favorite favorite);

	int checkFavorite(Favorite favorite);

	int deleteFavorite(Favorite favorite);

}
