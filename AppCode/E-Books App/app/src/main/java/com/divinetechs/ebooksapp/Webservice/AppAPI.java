package com.divinetechs.ebooksapp.Webservice;


import com.divinetechs.ebooksapp.Model.AuthorModel.AuthorModel;
import com.divinetechs.ebooksapp.Model.BookModel.BookModel;
import com.divinetechs.ebooksapp.Model.CategoryModel.CategoryModel;
import com.divinetechs.ebooksapp.Model.CommentModel.CommentModel;
import com.divinetechs.ebooksapp.Model.FreeBookModel.FreeBookModel;
import com.divinetechs.ebooksapp.Model.GeneralSettings.GeneralSettings;
import com.divinetechs.ebooksapp.Model.LoginRegister.LoginRegiModel;
import com.divinetechs.ebooksapp.Model.ProfileModel.ProfileModel;
import com.divinetechs.ebooksapp.Model.ReadDowncntModel.ReadDowncntModel;
import com.divinetechs.ebooksapp.Model.SuccessModel.SuccessModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AppAPI {


    @FormUrlEncoded
    @POST("login")
    Call<LoginRegiModel> login(@Field("email") String email_id,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST("registration")
    Call<LoginRegiModel> Registration(@Field("fullname") String full_name,
                                      @Field("email") String email_id,
                                      @Field("password") String password,
                                      @Field("mobile_number") String phone);

    @GET("general_setting")
    Call<GeneralSettings> general_settings();

    @GET("categorylist")
    Call<CategoryModel> categorylist();

    @FormUrlEncoded
    @POST("books_by_category")
    Call<BookModel> books_by_category(@Field("cat_id") String cat_id);

    @GET("newarriaval")
    Call<BookModel> newarriaval();

    @GET("feature_item")
    Call<BookModel> feature_item();

    @GET("popularbooklist")
    Call<BookModel> popularbooklist();

    @GET("autherlist")
    Call<AuthorModel> autherlist();

    @FormUrlEncoded
    @POST("books_by_author")
    Call<BookModel> books_by_author(@Field("a_id") String a_id);

    @FormUrlEncoded
    @POST("bookdetails")
    Call<BookModel> bookdetails(@Field("b_id") String b_id,
                                @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("add_purchase")
    Call<SuccessModel> add_purchase(@Field("fb_id") String fb_id,
                                    @Field("user_id") String user_id,
                                    @Field("amount") String amount,
                                    @Field("currency_code") String currency_code,
                                    @Field("short_description") String short_description,
                                    @Field("payment_id") String payment_id,
                                    @Field("state") String state,
                                    @Field("create_time") String create_time);

    @FormUrlEncoded
    @POST("purchaselist")
    Call<BookModel> purchaselist(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("alldownload")
    Call<BookModel> alldownload(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("related_item")
    Call<BookModel> related_item(@Field("fcat_id") String fcat_id);

    @FormUrlEncoded
    @POST("add_download")
    Call<SuccessModel> add_download(@Field("user_id") String user_id,
                                    @Field("b_id") String b_id);

    @FormUrlEncoded
    @POST("profile")
    Call<ProfileModel> profile(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("view_add_by_user")
    Call<SuccessModel> view_add_by_user(@Field("id") String id);

    @FormUrlEncoded
    @POST("download_add_by_user")
    Call<SuccessModel> download_add_by_user(@Field("id") String id);

    @FormUrlEncoded
    @POST("continue_read")
    Call<BookModel> continue_read(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("add_comment")
    Call<SuccessModel> add_comment(@Field("b_id") String b_id,
                                   @Field("user_id") String user_id,
                                   @Field("comment") String comment);

    @FormUrlEncoded
    @POST("view_comment")
    Call<CommentModel> view_comment(@Field("b_id") String b_id);

    @FormUrlEncoded
    @POST("add_bookmark")
    Call<SuccessModel> add_bookmark(@Field("user_id") String user_id,
                                    @Field("b_id") String b_id);

    @FormUrlEncoded
    @POST("allBookmark")
    Call<BookModel> allBookmark(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("checkbookmark")
    Call<SuccessModel> checkbookmark(@Field("user_id") String user_id,
                                     @Field("b_id") String b_id);

    @FormUrlEncoded
    @POST("give_rating")
    Call<SuccessModel> give_rating(@Field("user_id") String user_id,
                                   @Field("book_id") String book_id,
                                   @Field("rating") String rating);

    @FormUrlEncoded
    @POST("readcnt_by_author")
    Call<ReadDowncntModel> readcnt_by_author(@Field("a_id") String a_id);

    @FormUrlEncoded
    @POST("free_paid_booklist")
    Call<FreeBookModel> free_paid_booklist(@Field("is_paid") String is_paid);


}
