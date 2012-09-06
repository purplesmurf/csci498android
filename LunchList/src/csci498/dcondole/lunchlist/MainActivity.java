package csci498.dcondole.lunchlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

	class RestaurantAdapter extends ArrayAdapter<Restaurant> {
		RestaurantAdapter(){
			super(MainActivity.this,
					android.R.layout.simple_list_item_1,
					model);
		}

		public View getView(int position, View convertView,
				ViewGroup parent){
			View row = convertView;

			if ( row == null ) {
				LayoutInflater inflater = getLayoutInflater();
			}

			Restaurant r = model.get(position);

			((TextView)row.findViewById(R.id.title)).setText(r.getName());
			((TextView)row.findViewById(R.id.address)).setText(r.getAddress());
			
			ImageView icon=(ImageView)row.findViewById(R.id.icon);

			if (r.getType().equals("sit_down")) {
				icon.setImageResource(R.drawable.ball_red);
			}
			else if (r.getType().equals("take_out")) {
				icon.setImageResource(R.drawable.ball_yellow);
			}
			else {
				icon.setImageResource(R.drawable.ball_green);
			}
			return(row);

		}

	}
	List<Restaurant> model=new ArrayList<Restaurant>();
	ArrayAdapter<Restaurant> adapter=null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button save=(Button)findViewById(R.id.save);

		save.setOnClickListener(onSave);

		ListView list=(ListView)findViewById(R.id.restaurants);

		adapter=new ArrayAdapter<Restaurant>(this,
				android.R.layout.simple_list_item_1,
				model);
		list.setAdapter(adapter);
	}
	private View.OnClickListener onSave=new View.OnClickListener() {
		public void onClick(View v) {
			Restaurant r=new Restaurant();

			EditText name=(EditText)findViewById(R.id.name);
			EditText address=(EditText)findViewById(R.id.addr);

			r.setName(name.getText().toString());
			r.setAddress(address.getText().toString());

			RadioGroup types=(RadioGroup)findViewById(R.id.types);

			switch (types.getCheckedRadioButtonId()) {
			case R.id.sit_down:
				r.setType("sit_down");
				break;
			case R.id.take_out:
				r.setType("take_out");
				break;
			case R.id.delivery:
				r.setType("delivery");
				break;
			}

			adapter.add(r);
		}
	};
}

