package demo.databindingexample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import demo.databindingexample.databinding.ActivityMainBinding;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    GitPojoObs gitPojoObs;
    public ObservableUser myOUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setClickevent(new MyHandler());


        gitPojoObs = new GitPojoObs("Android", "Authority", 22, "Corporate body");;
        myOUser = new ObservableUser("Android", "Authority", 22, "Corporate body");
        binding.setUser(myOUser);
        binding.setGitpojoobs(gitPojoObs);
        binding.setClickevent1(this);
        binding.setClickevent2(this);
        ApiCalls apiCalls = RetrofitClient.getClient().create(ApiCalls.class);
        apiCalls.getUserDetails("saransh9").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<GitPojo>() {
            @Override
            public void accept(GitPojo gitPojo) throws Exception {

                binding.setGitpojo(gitPojo);

            }
        });

    }


    public void clickOn(View view)
    {
        myOUser.setFirstname("adsad");
    }
    public void clickOccured(View view) {
        gitPojoObs.firstname.set("asdsad");
        Toast.makeText(view.getContext(), "click occured", Toast.LENGTH_SHORT).show();
    }


}
