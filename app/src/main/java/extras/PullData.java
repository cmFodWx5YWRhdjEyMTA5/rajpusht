package extras;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ranjeet on 3/6/2018.
 */

public  class PullData {
    Context context;
    SessionManager session = new SessionManager(context);

//    public static String village_code="101";


//    DbHelper db = new DbHelper(Context);

public String  locationcheck(){
    String status;

    String checkQuery="SELECT * FROM ASSIGNED_LOCATION WHERE LOGIN='Y'";
    SQLiteDatabase dbs = context.openOrCreateDatabase("ranjeettest", context.MODE_PRIVATE, null);

    Cursor c = dbs.rawQuery(checkQuery,null);
    if(c.getCount()>0){

        if (c.moveToFirst()) {
            do {

                session.setAwcCode(c.getString(c.getColumnIndex("awc_code")));
                session.setDistCode(c.getString(c.getColumnIndex("dist_code")));
                session.setProjectCode(c.getString(c.getColumnIndex("project_code")));
                session.setSectorCode(c.getString(c.getColumnIndex("sector_code")));
                session.setVillageCode(c.getString(c.getColumnIndex("village_code")));
                session.setVillageEng(c.getString(c.getColumnIndex("village_eng")));
                session.setVillageHindi(c.getString(c.getColumnIndex("village_hindi")));
                session.setSurveyorName(c.getString(c.getColumnIndex("surveyor_name")));
                session.setSurveyorId(c.getString(c.getColumnIndex("surveyor_id")));


            } while (c.moveToNext());
        }

      status="true";
    }else{
status="false";

    }

return status;
}


}
