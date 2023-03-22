package com.spring.bittlebittle.user.service;

import com.spring.bittlebittle.reply.vo.Reply;
import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;

import java.util.List;


public interface UserService {

    // selectList
    List<User> getUsers();

    // selectOne
    User getUser(User user);

    User loginUser(User user);
    // insert
    int registerUser(User user);

    // update
    User editUser(User user);

    // delete
    int removeUser(User user);

    // Boolean registerJwtWithIdx(UserAuthentication userAuthentication);

    UserJwt getUserJwt(UserJwt userJwt);

    UserJwt createUserJwt(UserJwt userJwt);

    UserJwt editUserJwt(UserJwt build);

    UserJwt getUserJwtBySubject(UserJwt build);

    int removeUserJwt(UserJwt userJwt);

	void addUserTags(int userNo, List<Integer> tagNoList) throws Exception;

	void deleteUserTags(int userNo, List<Integer> tagNoList) throws Exception;

	boolean isUsernameDuplicate(String userId);

	boolean sendEmailAuth(String email);

	List<Review> getUserReviews(int userNo);

	List<Reply> getUserComments(int userNo);
	
	void withdrawUser(int userNo);
	
	public List<User> findAllUsers(int userNo);
	
	public List<User> searchUsers(String searchCriteria, String searchKeyword);
	
	public int updateStatusToN(List<Long> userNos);
	
	public int updateUsermodal(User user);
	
	
	

}
