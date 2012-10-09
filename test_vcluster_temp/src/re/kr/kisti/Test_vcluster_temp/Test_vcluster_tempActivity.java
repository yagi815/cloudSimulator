package re.kr.kisti.Test_vcluster_temp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Test_vcluster_tempActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        
        
        
        Button btn1 = (Button)findViewById(R.id.btn1);
        final TextView text1 = (TextView)findViewById(R.id.text1);
        
        
        btn1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				text1.setText("hello \n");
				text1.append("hello- \n");
				Log.d("btn1", "btn1 is clicked.");
				
			}
		});
        
        
        
    }
}