package root.iv.voting.network;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import root.iv.voting.data.Answer;

public interface AnswerAPI {
    @POST(value = "/api/answers")
    Single<Answer> create(@Body Answer answer);

    @PUT(value = "/api/answers")
    Single<Answer> update(@Body Answer answer);

    @GET(value = "/api/answers/all")
    Single<List<Answer>> getAllAnswers();

    @GET(value = "/api/answers")
    Single<Answer> getAnswer(@Query("id") Long id);

    @DELETE(value = "/api/answers")
    Single<Void> delete(@Query("id") Long id);
}
