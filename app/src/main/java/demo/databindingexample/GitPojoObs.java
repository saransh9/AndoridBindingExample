package demo.databindingexample;

import android.databinding.ObservableField;

/**
 * Created by saransh on 20/02/18.
 */

public class GitPojoObs {
    public final ObservableField<String> firstname = new ObservableField<>();
    public String lastname;
    public final ObservableField<Integer> age = new ObservableField<>();
    public String gender;

    public GitPojoObs(String firstname, String lastname, int age, String gender){
        this.firstname.set(firstname);
        this.lastname = lastname;
        this.age.set(age);
        this.gender = gender;
    }
}
