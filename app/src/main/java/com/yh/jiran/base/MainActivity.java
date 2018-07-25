package com.yh.jiran.base;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yh.core.app.BaseActivity;
import com.yh.jiran.R;
import com.yh.jiran.module.test.model.api.GankService;
import com.yh.jiran.module.test.model.api.QueryService;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author: 闫昊
 * @date: 2018/7/18
 * @function: 主界面，测试代码，待删除
 */
public class MainActivity extends BaseActivity {

    private RetrofitHandler mHandler = new RetrofitHandler(this);

    @BindView(R.id.tv_test)
    AppCompatTextView tvTest;
    @BindView(R.id.tv_test_1)
    AppCompatTextView tvTest1;
    @BindView(R.id.tv_test_2)
    AppCompatTextView tvTest2;
    @BindView(R.id.btn_retrofit)
    Button btnRetrofit;
    @BindView(R.id.tv_gank)
    TextView tvGank;
    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.tv_Query)
    TextView tvQuery;

    @Override
    protected int setContent() {
        return R.layout.activity_main_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        String html = "<p><a href=\"arouter://jiran/com/URLActivity1\">arouter://jiran/com/URLActivity1 </a></p>";
    }

    @OnClick(R.id.btn_test)
    public void jump() {
        ARouter.getInstance().build("/test/CourierActivity").navigation();
    }

    @OnClick(R.id.btn_router)
    public void router() {
        ARouter.getInstance()
                .build("/test/router.activity")
                .withBoolean("success", true)
                .withString("text","跳转成功")
                .navigation();
    }

    @OnClick(R.id.btn_retrofit)
    public void onGet() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("http://gank.io/api/")
                .build();
        GankService gank = retrofit.create(GankService.class);
        Call<String> call = gank.getGank();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.body();
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    /**
     * "http://www.imooc.com/api/teacher?type=4&num=30"
     */
    @OnClick(R.id.btn_query)
    public void onQuery() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .build();
        QueryService queryService = retrofit.create(QueryService.class);
        WeakHashMap<String, Object> params = new WeakHashMap<>();
        params.put("type", "4");
        params.put("num", "30");
        Call<ResponseBody> call = queryService.queryInfo("http://www.imooc.com/api/", params);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    Message msg = Message.obtain();
                    msg.what = 2;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    private static class RetrofitHandler extends Handler {
        WeakReference<BaseActivity> reference;

        private RetrofitHandler(BaseActivity activity) {
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = (MainActivity) reference.get();
            switch (msg.what) {
                case 1:
                    activity.tvGank.setText((String) msg.obj);
                    break;
                case 2:
                    activity.tvQuery.setText((String) msg.obj);
                    break;
                default:
                    break;
            }
        }
    }
}
