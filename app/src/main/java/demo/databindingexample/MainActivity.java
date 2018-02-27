package demo.databindingexample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import demo.databindingexample.databinding.ActivityMainBinding;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final View.OnLongClickListener mButtonClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(MainActivity.this, "タップされたよ", Toast.LENGTH_SHORT).show();
            return true;
        }


    };

    ActivityMainBinding binding;
    GitPojoObs gitPojoObs;
    public ObservableUser myOUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        gitPojoObs = new GitPojoObs("Android", "Authority", 22, "Corporate body");
        myOUser = new ObservableUser("", "Authority", 22, "Corporate body");
        binding.setUser(myOUser);
        binding.setGitpojoobs(gitPojoObs);
        binding.setClickevent1(this);
        binding.setClickevent2(this);
        binding.setActivity(this);
        binding.setClickevent(new MyHandler());
        binding.setPresenter(new Presenter());
        ApiCalls apiCalls = RetrofitClient.getClient().create(ApiCalls.class);
        apiCalls.getUserDetails("saransh9").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<GitPojo>() {
            @Override
            public void accept(GitPojo gitPojo) throws Exception {

                binding.setGitpojo(gitPojo);

            }
        });

    }

    public View.OnLongClickListener getButtonClickListener() {
        return mButtonClickListener;
    }

    public void clickOn(View view) {
        myOUser.setFirstname(String.valueOf(Math.random()));
    }

    /// lamda expression should return same type as the method , here boolean
    public boolean longClick(View view) {
        Toast.makeText(view.getContext(), "lamda on click", Toast.LENGTH_SHORT).show();
        return true;
    }

    public void clickOccured(View view) {
        gitPojoObs.firstname.set(String.valueOf(Math.random()));
        Toast.makeText(view.getContext(), "click occured", Toast.LENGTH_SHORT).show();
    }


}
