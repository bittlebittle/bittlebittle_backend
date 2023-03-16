package com.spring.bittlebittle.favorite.service;

import com.spring.bittlebittle.favorite.vo.Favorite;

public interface FavoriteService {

	int addFavorite(Favorite favorite);

	int isFavorite(Favorite favorite);

	int removeFavorite(Favorite favorite);

}
