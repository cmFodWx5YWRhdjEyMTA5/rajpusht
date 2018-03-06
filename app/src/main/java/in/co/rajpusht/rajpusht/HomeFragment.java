package in.co.rajpusht.rajpusht;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import Adpter.DetailsAdapter;
import extras.BeneficiaryList;
import extras.RecyclerTouchListener;

import static android.content.Context.MODE_PRIVATE;




/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    ArrayList<BeneficiaryList> arraybeneficiary = new ArrayList<BeneficiaryList>();
    private SearchView searchView;

//    String beneficiaryQuery="select a.Members_id ,a.family_id,a.name,b.members_id as mother_id,b.name as mother,ifnull(a.status,b.status)status,ifnull(a.stage,b.stage)as stage,ifnull(a.sub_stage,b.sub_stage)as sub_stage,strftime('%d/%m/%Y', lmp_date)lmp_date,strftime('%d/%m/%Y', dodelivery)dodelivery,p.PREGNANCY_ID,is_anc,case\n" +
//            "when  julianday('now') - julianday(lmp_date)<=98 then 'PW1'\n" +
//            "when  julianday('now') - julianday(lmp_date)<=196 then 'PW2'\n" +
//            "when  julianday('now') - julianday(lmp_date)<=252 then 'PW3'\n" +
//            "when  julianday('now') - julianday(lmp_date)<=280 then 'PW4'\n" +
//            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+3 month',cast(strftime('%d',DODELIVERY)as text) ||' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM1'\n" +
//            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+6 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM2'\n" +
//            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+12 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM3'\n" +
//            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+18 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY1'\n" +
//            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+24 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY2'\n" +
//            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+30 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY3'\n" +
//            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+36 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY4'\n" +
//            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+38 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY5'\n" +
//            "END\n as " +
//            "current_sub_stage \n" +
//            "FROM memberbasic a left join memberbasic b on a.mother_id=b.members_id left join (select * from pregnant where is_active='Y') p on a.members_id=p.MEMBERS_ID left join childextra c on a.MEMBERS_ID=c.MEMBERS_ID" +
//            " left join (select * from pw_tracking) t on p.PREGNANCY_ID=t.PREGNANCY_ID and t.SUB_STAGE=current_sub_stage where a.is_to_track='Y' and current_sub_stage<>'' order by a.sub_stage asc,a.name desc";
//
String beneficiaryQuery="select a.Members_id ,a.family_id,a.name,b.members_id as mother_id,b.name as mother,ifnull(a.status,b.status)status,ifnull(a.stage,b.stage)as stage,ifnull(a.sub_stage,b.sub_stage)as sub_stage,strftime('%d/%m/%Y', lmp_date)lmp_date,strftime('%d/%m/%Y', dodelivery)dodelivery,p.PREGNANCY_ID,is_anc,(case\n" +
        "when  julianday('now') - julianday(lmp_date)<=98 then 'PW1'\n" +
        "when  julianday('now') - julianday(lmp_date)<=196 then 'PW2'\n" +
        "when  julianday('now') - julianday(lmp_date)<=252 then 'PW3'\n" +
        "when  julianday('now') - julianday(lmp_date)<=280 then 'PW4'\n" +
        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+3 month',cast(strftime('%d',DODELIVERY)as text) ||' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM1'\n" +
        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+6 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM2'\n" +
        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+12 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM3'\n" +
        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+18 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY1'\n" +
        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+24 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY2'\n" +
        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+30 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY3'\n" +
        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+36 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY4'\n" +
        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+38 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY5'\n" +
        "END\n) as " +
        "current_sub_stage \n" +
        "FROM memberbasic a left join memberbasic b on a.mother_id=b.members_id left join (select * from pregnant where is_active='Y') p on a.members_id=p.MEMBERS_ID left join childextra c on a.MEMBERS_ID=c.MEMBERS_ID" +
        " left join (select * from pw_tracking) t on p.PREGNANCY_ID=t.PREGNANCY_ID and t.SUB_STAGE=current_sub_stage where a.is_to_track='Y'  order by a.sub_stage asc,a.name desc";




    public HomeFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.beneficairyList);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {


                BeneficiaryList beneficiaryList= arraybeneficiary.get(position);

                if(beneficiaryList.getStage().equalsIgnoreCase("pw")){

                    Intent intetPw1= new Intent(getActivity().getApplicationContext(),PregantWomenFooter.class);
                    intetPw1.putExtra("pregnancy_id",beneficiaryList.getPregnancy_id());
                    intetPw1.putExtra("current_sub_stage",beneficiaryList.getCurrent_sub_stage());
                    intetPw1.putExtra("name",beneficiaryList.getName());
                    intetPw1.putExtra("members_id",beneficiaryList.getMembers_id());
                    intetPw1.putExtra("lmpDate",beneficiaryList.getLmp_date());
                    intetPw1.putExtra("getstage","pw");
                    intetPw1.putExtra("familyId",beneficiaryList.getFamily_id());
                    startActivity(intetPw1);
                    getActivity().finish();
                }

                if(beneficiaryList.getStage().equalsIgnoreCase("LM")){

                    Intent intentLm = new Intent(getActivity().getApplicationContext(),LM_actvity.class);
                    intentLm.putExtra("name",beneficiaryList.getName());
                    startActivity(intentLm);
                    getActivity().finish();
                }



                if(beneficiaryList.getStage().equalsIgnoreCase("MY")){

                    Intent intentLm = new Intent(getActivity().getApplicationContext(),MyActivity.class);
                    intentLm.putExtra("name",beneficiaryList.getName());
                    startActivity(intentLm);
                    getActivity().finish();
                }
                Log.d("HomeREceycler"," "+position);





//                startActivity(intetPw1);




//                Toast.makeText(getApplicationContext(), movie.getDropLocation() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));




        getallBeneficairyList();
//
//        Button pw = (Button) v.findViewById(R.id.pww1);
//        pw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), PregnantWomenForm.class);
//                i.putExtra("button","PW1");
//                i.putExtra("name","Bimala Agarwal");
//                startActivity(i);
//            }
//        });
//
//        Button pw2 = (Button) v.findViewById(R.id.pw2);
//        pw2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i2 = new Intent(getActivity(), PregnantWomenForm.class);
//                i2.putExtra("button","PW2");
//                i2.putExtra("name","Sonam Agarwal");
//                startActivity(i2);
//            }
//        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_settings)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                movieAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
//                movieAdapter.getFilter().filter(query);
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:

                //       onCall();   //your logic

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void getallBeneficairyList(){

        SQLiteDatabase dbs = getActivity().openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor c = dbs.rawQuery(beneficiaryQuery , null);
        Log.d("beneficiaryQuery",beneficiaryQuery);

        int total = c.getCount();

        if(total>=1) {

            if (c.moveToFirst()) {
                do {

                    BeneficiaryList benefic = new BeneficiaryList(c.getString(c.getColumnIndex("Members_id")), c.getString(c.getColumnIndex("family_id")), c.getString(c.getColumnIndex("name")), c.getString(c.getColumnIndex("mother_id")),
                            c.getString(c.getColumnIndex("mother")), c.getString(c.getColumnIndex("status")),
                                    c.getString(c.getColumnIndex("stage")), c.getString(c.getColumnIndex("sub_stage")),
                                            c.getString(c.getColumnIndex("lmp_date")), c.getString(c.getColumnIndex("dodelivery")), c.getString(c.getColumnIndex("current_sub_stage")),c.getString(c.getColumnIndex("pregnancy_id")),c.getString(c.getColumnIndex("is_anc")));
                    arraybeneficiary.add(benefic);


//                          Log.d("awcrecords",c.getString(c.getColumnIndex("awc_code")));
//                          Log.d("awcrecords",c.getString(c.getColumnIndex("dist_code")));
//                          Log.d("awcrecords",c.getString(c.getColumnIndex("familyid")));

                } while (c.moveToNext());
            }
        }




        DetailsAdapter adapter = new DetailsAdapter(arraybeneficiary);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linear= new LinearLayoutManager(getActivity().getApplication());
        linear.setReverseLayout(true);
        linear.setStackFromEnd(true);
        recyclerView.setLayoutManager(linear);

    }

}
