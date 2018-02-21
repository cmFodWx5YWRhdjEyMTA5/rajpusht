package extras;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ranjeet on 2/7/2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 3;

    SQLiteDatabase db;

    // Database Name
    private static final String DATABASE_NAME = "ranjeettest";

    private Context context;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;

db=getWritableDatabase();
    }


    // Contacts table name
    private static final String TABLE_FAMILYDATA ="familydata";
    private static final String MEMBER_BASIC ="memberbasic";
    private static final String WOMEN_EXTRA = "womenextra";
    private static final String CHILD_EXTRA = "childextra";
    private static final String PREGNANT = "pregnant";

//Family memebr table
    // Contacts Table Columns names
    private static final String FAMILYTABLE_ID="familytableId";
    private static final String FAMILY_ID = "familyid";
    private static final String DIST_CODE = "dist_code";
    private static final String PROJECT_CODE = "project_code";
    private static final String SECTOR_CODE = "sector_code";
    private static final String AWC_CODE = "awc_code";
    private static final String VILLAGE_CODE="village_code";
    private static final String RELIGION = "religion";
    private static final String CASTE = "caste";
    private static final String RCARD = "rcard";
    private static final String FAMILY_TYPE = "family_type";
    private static final String SURVEYOR_ID = "surveyor_id";
    private static final String IS_EDITED_FAMILY= "is_edited";
    private static final String IS_NEW_FAMILY= "is_new";
    private static final String IS_APPROVED_FAMILY = "is_approved";

    //MEMBER _ BASIC

    private static final String MEMBER_ID= "MemberId";
    private static final String FAMILYY_ID = "familyy_id";
    private static final String NAME = "name";
    private static final String DOR = "dor";
    private static final String DOENRTY = "doentry";
    private static final String DOEXIT = "doexit";
    private static final String DOB = "dob";
    private static final String AGE = "age";
    private static final String IF_DOB_ASSUMED="if_dob";
    private static final String DODEATH="dodeath";
    private static final String AADHAR="aadhar";
    private static final String AADHAR_ENROLMENT="aadhar_enrolment";
    private static final String AADHAR_DATE="aadhar_date";
    private static final String AADHAR_TIME="aadhar_time";
    private static final String BHAMASHA="bhamasha";
    private static final String MOBILE="mobile";
    private static final String RELATION="relation";
    private static final String SEX="sex";
    private static final String HANDICAP="handicap";
    private static final String IF_MARRIED = "if_married";
    private static final String MOTHER_ID = "mothet_id";
    private static final String STATUS = "status";
    private static final String STAGE = "stage";
    private static final String SUB_STAGE = "sbu_stage";
    private static final String IS_TO_TRACK = "IS_TO_TRACK";
    private static final String SURVAYORR_ID = "survayorr_id";
    private static final String TIME_STAMP = "time_stamp";
    private static final String SOURCE = "source";
    private static final String IS_EDITED_MEMBER= "is_edited";
    private static final String IS_NEW_MEMBER= "is_new";
    private static final String IS_APPROVED_MEMBER = "is_approved";

    //



    private static final String WOMENUID = "womenuid";
    private static final String SURVEYY_ID = "surveyY_id";
    private static final String EDUCATION = "education";
    private static final String COOKINGFUEL = "cookingfuel";
    private static final String DECISIONMAKER_OWNHEALTH = "decision_ownhealth";
    private static final String DECISIONMAKER_CHILDHEALTH = "decision_childhealth";
    private static final String IF_BANK_ACCOUNT = "if_bankaccount";
    private static final String AC_HOLDER_NAME = "acholder_name";
    private static final String BANK_NAME = "bank_name";
    private static final String BRANCH = "branch";
    private static final String AC_NO = "ac_no";
    private static final String IFSC_CODE = "ifsc";
    private static final String BANK_DISTANCE = "bank_distance";
    private static final String POSTOFFICE_NAME = "postoffice_name";
    private static final String POSTOFFICE_ADDRESS = "postoffice_address";
    private static final String PINCODE = "pincode";
    private static final String POSTOFFICE_AC = "postoffice_ac";
    private static final String HOEMO_CODE = "hoemo_code";
//    private static final String IS_EDITED= "is_edited";
//    private static final String IS_NEW= "is_new";
//    private static final String IS_APPROVED = "is_approved";



    private static final String CHILDUID = "childuid";
    private static final String SURVEYYY_ID = "SURVEYYYid";
    private static final String DO_DELIVERY = "dodelivery";
    private static final String DELIVERY_PLACE = "deliveryplace";
    private static final String CHILD_ORDER = "childorder";
    private static final String BIRTH_WT = "birthwt";
    private static final String FULL_TERM = "fullterm";
    private static final String WHENFIRST_BF = "whenfirstbf";
    private static final String IFFEED_KHEES = "iffeedkhees";
    private static final String CURRENTLY_BF = "currentlybf";
    private static final String WHENSTOP_BF = "whenstopbf";
    private static final String ANYTHINGBEFORE_BF = "anythingbeforebf";
    private static final String IFSTARTED_SOLIDFOOD = "ifstartedsolidfood";
    private static final String WHICHMONTH_SOLIDFOOD = "whichmonthsolidfood";
    private static final String CHILDIMMUNIZATION_STATUS = "childimmunizationstatus";
//    private static final String IS_EDITED= "is_edited";
//    private static final String IS_NEW= "is_new";
//    private static final String IS_APPROVED = "is_approved";




    private static final String PREGNANCY_ID = "pregnancy_id";
    private static final String SURVEYYYY_ID = "surveyyyy_id";
    private static final String ORDEROF_PREGNANCY = "orderof_pregnancy";
    private static final String LMP_DATE = "lmp_date";
    private static final String IS_ACTIVE = "Is_active";
    private static final String SURVEYORR_ID = "surveyorr_id";
    private static final String TIMEE_STAMP = "timee_stamp";
    private static final String SOURCEE = "sourcee";
    private static final String IS_EDITED= "is_edited";
    private static final String IS_NEW= "is_new";
    private static final String IS_APPROVED = "is_approved";



public static final String CREATE_FAMILYDATA_TABLE = "CREATE TABLE " + TABLE_FAMILYDATA + " ( "
//            + FAMILYTABLE_ID + " INTEGER PRIMARY KEY, "
            + FAMILY_ID + " VARCHAR, "
            + DIST_CODE + " VARCHAR, "
            + PROJECT_CODE + " VARCHAR, "
            + SECTOR_CODE + " VARCHAR, "
            + AWC_CODE + " VARCHAR, "
            + VILLAGE_CODE + " VARCHAR, "
            + RELIGION + " INTEGER, "
            + CASTE + " INTEGER, "
            + RCARD + " INTEGER, "
            + FAMILY_TYPE + " INTEGER, "
            + IS_EDITED_FAMILY + " VARCHAR, "
            + IS_NEW_FAMILY + " VARCHAR, "
            + IS_APPROVED_FAMILY + " VARCHAR, "
            + SURVEYOR_ID + " VARCHAR)";



//    public DbHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
        {
//            super.onCreate(db);
//            Log.d("Sqlite","CREATE_FAMILYDATA_TABLE");


//        Log.d("Sqlite",CREATE_FAMILYDATA_TABLE);
        db.execSQL(CREATE_FAMILYDATA_TABLE);

        String CREATE_MEMBERBASIC_TABLE = "CREATE TABLE " + MEMBER_BASIC + "(" +
                MEMBER_ID+ " VARCHAR, "
                + FAMILYY_ID + " VARCHAR, "
                + NAME + " VARCHAR,"
                + DOR + " DATE, "
                + DOENRTY + " DATE, "
                + DOEXIT + " DATE, "
                + DOB + " DATE, "
                + AGE + " FLOAT, "
                + IF_DOB_ASSUMED + " VARCHAR, "
                + DODEATH + " DATE, "
                + AADHAR + " VARCHAR, "
                + AADHAR_ENROLMENT + " VARCHAR, "
                + AADHAR_DATE + " DATE, "
                + AADHAR_TIME + " TEXT, "
                + BHAMASHA + " TEXT, "
                + MOBILE + " TEXT, "
                + RELATION + " VARCHAR, "
                + SEX + " VARCHAR,"
                + HANDICAP + " VARCHAR, "
                + IF_MARRIED + " VARCHAR, "
                + MOTHER_ID + " VARCHAR,"
                + STATUS + " VARCHAR,"
                + STAGE + " VARCHAR,"
                + SUB_STAGE + " VARCHAR, "
                + IS_TO_TRACK + " VARCHAR, "
                + SURVAYORR_ID + " VARCHAR, "
                + TIME_STAMP + " DATETIME, "
                + IS_EDITED_MEMBER + " VARCHAR, "
                + IS_NEW_MEMBER + " VARCHAR, "
                + IS_APPROVED_MEMBER + " TEXT, "
                + SOURCE + " TEXT)";
        db.execSQL(CREATE_MEMBERBASIC_TABLE);

        String CREATE_WOMEN_EXTRA = "CREATE TABLE " + WOMEN_EXTRA + "("
//                + WOMENUID + " INTEGER PRIMARY KEY,"
                + MEMBER_ID + " VARCHAR, "
                + EDUCATION + " INTEGER, "
                + COOKINGFUEL + " INTEGER, "
                + DECISIONMAKER_OWNHEALTH + " INTEGER, "
                + DECISIONMAKER_CHILDHEALTH + " INTEGER, "
                + IF_BANK_ACCOUNT + " VARCHAR, "
                + AC_HOLDER_NAME + " VARCHAR, "
                + BANK_NAME + " VARCHAR, "
                + BRANCH + " VARCHAR, "
                + AC_NO + " VARCHAR, "
                + IFSC_CODE + " VARCHAR, "
                + BANK_DISTANCE + " FLOAT, "
                + POSTOFFICE_NAME + " VARCHAR, "
                + POSTOFFICE_ADDRESS + " VARCHAR, "
                + PINCODE + " VARCHAR, "
                + POSTOFFICE_AC + " VARCHAR, "
                + HOEMO_CODE + " VARCHAR, "
                + IS_EDITED + " VARCHAR, "
                + IS_NEW + " VARCHAR, "
                + IS_APPROVED + " VARCHAR)";
        db.execSQL(CREATE_WOMEN_EXTRA);

        String CREATE_CHILD_EXTRA = "CREATE TABLE " + CHILD_EXTRA + "("

                + MEMBER_ID + " VARCHAR, "
                + DO_DELIVERY + " DATE, "
                + DELIVERY_PLACE + " INTEGER, "
                + CHILD_ORDER + " INTEGER, "
                + BIRTH_WT + " FLOAT,"
                + FULL_TERM + " VARCHAR, "
                + WHENFIRST_BF + " INTEGER, "
                + IFFEED_KHEES + " VARCHAR, "
                + CURRENTLY_BF + " VARCHAR, "
                + WHENSTOP_BF + " INTEGER, "
                + ANYTHINGBEFORE_BF + " VARCHAR, "
                + IFSTARTED_SOLIDFOOD + " VARCHAR, "
                + WHICHMONTH_SOLIDFOOD + " FLOAT, "
                + CHILDIMMUNIZATION_STATUS + " VARCHAR, "
                + IS_EDITED + " VARCHAR, "
               + IS_NEW + " VARCHAR, "
              + IS_APPROVED + " VARCHAR)";
        db.execSQL(CREATE_CHILD_EXTRA);

        String CREATE_PREGNANT = "CREATE TABLE " + PREGNANT + "("
//                + PREGNENTUID + " INTEGER PRIMARY KEY,"
                + PREGNANCY_ID + " VARCHAR, "
                + MEMBER_ID + " VARCHAR, "
                + ORDEROF_PREGNANCY + " INTEGER, "
                + LMP_DATE + " DATE,"
                + IS_ACTIVE + " VARCHAR, "
                + SURVEYORR_ID + " VARCHAR, "
                + TIMEE_STAMP + " DATETIME,"
                + SOURCEE + " VARCHAR, "
                + IS_EDITED + " VARCHAR, "
                + IS_NEW + " VARCHAR, "
                + IS_APPROVED + " VARCHAR)";
              db.execSQL(CREATE_PREGNANT);
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAMILYDATA);
        db.execSQL("DROP TABLE IF EXISTS " + MEMBER_BASIC);
        db.execSQL("DROP TABLE IF EXISTS " + WOMEN_EXTRA);
        db.execSQL("DROP TABLE IF EXISTS " + CHILD_EXTRA);
        db.execSQL("DROP TABLE IF EXISTS " + PREGNANT);

        // Create tables again
        onCreate(db);
    }


    // Adding new contact
    public long addFamilyData(FamilyDetailGetSet familyDetailGetSet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FAMILY_ID, familyDetailGetSet.getFamilyid());
        values.put(DIST_CODE, familyDetailGetSet.getDist_code());
        values.put(PROJECT_CODE, familyDetailGetSet.getProject_code());
        values.put(SECTOR_CODE, familyDetailGetSet.getSector_code());
        values.put(AWC_CODE, familyDetailGetSet.getAwc_code());
        values.put(RELIGION, familyDetailGetSet.getReligion());
        values.put(CASTE, familyDetailGetSet.getCast());
        values.put(RCARD, familyDetailGetSet.getRcard());
        values.put(FAMILY_TYPE, familyDetailGetSet.getFamilytype());
        values.put(SURVEYOR_ID, familyDetailGetSet.getSurveyor_id());

        // Inserting Row
     long id=   db.insert(TABLE_FAMILYDATA, null, values);
//        db.close(); // Closing database connection
        return id;
    }

    public long addMemberBasic(MemberBasicGetSet memberBasicGetSet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MEMBER_ID, memberBasicGetSet.getmemberId());
        values.put(FAMILYY_ID, memberBasicGetSet.getFamily_id());
        values.put(NAME, memberBasicGetSet.getName());
        values.put(DOR, memberBasicGetSet.getDor());
        values.put(DOENRTY, memberBasicGetSet.getDoentry());
        values.put(DOEXIT, memberBasicGetSet.getDoexit());
        values.put(DOB, memberBasicGetSet.getDob());
        values.put(AGE, memberBasicGetSet.getAge());
        values.put(IF_DOB_ASSUMED, memberBasicGetSet.getIfdobasum());
        values.put(DODEATH, memberBasicGetSet.getDod());
        values.put(AADHAR, memberBasicGetSet.getAadhar());
        values.put(AADHAR_ENROLMENT, memberBasicGetSet.getAadharenrol());
        values.put(AADHAR_DATE, memberBasicGetSet.getAadhardate());
        values.put(AADHAR_TIME, memberBasicGetSet.getAadhartime());
        values.put(BHAMASHA, memberBasicGetSet.getBhamasha());
        values.put(MOBILE, memberBasicGetSet.getMobile());
        values.put(RELATION, memberBasicGetSet.getRelation());
        values.put(SEX, memberBasicGetSet.getSex());
        values.put(HANDICAP, memberBasicGetSet.getHandicap());
        values.put(IF_MARRIED, memberBasicGetSet.getIfmarried());
        values.put(MOTHER_ID, memberBasicGetSet.getMotherid());
        values.put(STATUS, memberBasicGetSet.getStatus());
        values.put(STAGE, memberBasicGetSet.getStage());
        values.put(SUB_STAGE, memberBasicGetSet.getSubstage());
        values.put(IS_TO_TRACK, memberBasicGetSet.getTrackstatus());
        values.put(SURVAYORR_ID, memberBasicGetSet.getSurveyor_id());
        values.put(TIME_STAMP, memberBasicGetSet.getTimestamp());
        values.put(SOURCE, memberBasicGetSet.getSource());

        // Inserting Row
       long id = db.insert(MEMBER_BASIC, null, values);
//        db.close(); // Closing database connection
        return  id;
    }

    public long addWomenExtra(WomenBasicGetSet womenBasicGetSet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MEMBER_ID, womenBasicGetSet.getSurvey_id());
        values.put(EDUCATION, womenBasicGetSet.getEducation());
        values.put(COOKINGFUEL, womenBasicGetSet.getCookingfuel());
        values.put(DECISIONMAKER_OWNHEALTH, womenBasicGetSet.getDecicion_own());
        values.put(DECISIONMAKER_CHILDHEALTH, womenBasicGetSet.getDecision_child());
        values.put(IF_BANK_ACCOUNT, womenBasicGetSet.getIf_bankaccount());
        values.put(AC_HOLDER_NAME, womenBasicGetSet.getAcholder_name());
        values.put(BANK_NAME, womenBasicGetSet.getBank_name());
        values.put(BRANCH, womenBasicGetSet.getBranch());
        values.put(AC_NO, womenBasicGetSet.getAc_num());
        values.put(IFSC_CODE, womenBasicGetSet.getIfsc());
        values.put(BANK_DISTANCE, womenBasicGetSet.getBank_distsnce());
        values.put(POSTOFFICE_NAME, womenBasicGetSet.getPostoffice_name());
        values.put(POSTOFFICE_ADDRESS, womenBasicGetSet.getPostoffice_address());
        values.put(PINCODE, womenBasicGetSet.getPincode());
        values.put(POSTOFFICE_AC, womenBasicGetSet.getPostoffice_ac());
        values.put(HOEMO_CODE, womenBasicGetSet.getHoemo_code());
        values.put(IS_EDITED, womenBasicGetSet.getHoemo_code());
        values.put(IS_NEW, womenBasicGetSet.getHoemo_code());
        values.put(IS_APPROVED, womenBasicGetSet.getHoemo_code());
//        values.put(HOEMO_CODE, womenBasicGetSet.getHoemo_code());

        // Inserting Row
      long id =  db.insert(WOMEN_EXTRA, null, values);
//        db.close(); // Closing database connection
        return id;
    }

    public void addChildExtra(ChildExtraGetSet childExtraGetSet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SURVEYYY_ID, childExtraGetSet.getSurvey_id());
        values.put(DO_DELIVERY, childExtraGetSet.getDo_delivery());
        values.put(DELIVERY_PLACE, childExtraGetSet.getDelivery_place());
        values.put(CHILD_ORDER, childExtraGetSet.getChild_order());
        values.put(BIRTH_WT, childExtraGetSet.getBirth_wl());
        values.put(FULL_TERM, childExtraGetSet.getFull_term());
        values.put(WHENFIRST_BF, childExtraGetSet.getWhenfirst_bf());
        values.put(IFFEED_KHEES, childExtraGetSet.getIffeed_khees());
        values.put(CURRENTLY_BF, childExtraGetSet.getCurrently_bf());
        values.put(WHENSTOP_BF, childExtraGetSet.getWhenstop_bf());
        values.put(ANYTHINGBEFORE_BF, childExtraGetSet.getAnythingbefore_bf());
        values.put(IFSTARTED_SOLIDFOOD, childExtraGetSet.getIfstarted_solidfood());
        values.put(WHICHMONTH_SOLIDFOOD, childExtraGetSet.getWhichmonth_solidfood());
        values.put(CHILDIMMUNIZATION_STATUS, childExtraGetSet.getChildimmunization_status());

        // Inserting Row
        db.insert(CHILD_EXTRA, null, values);
//        db.close(); // Closing database connection
    }

    public long addPregnant(PregnantGetSet pregnantGetSet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PREGNANCY_ID, pregnantGetSet.getPregnancyid());
        values.put(MEMBER_ID, pregnantGetSet.getSurveyid());
        values.put(ORDEROF_PREGNANCY, pregnantGetSet.getOrderofpregnancy());
        values.put(LMP_DATE, pregnantGetSet.getLmpdate());
        values.put(SURVEYORR_ID, pregnantGetSet.getSurveyorid());
        values.put(TIMEE_STAMP, pregnantGetSet.getTimestamp());

        values.put(IS_ACTIVE, "Y");
        values.put(IS_EDITED, "");
        values.put(IS_NEW,"Y");
        values.put(IS_APPROVED, "");
        values.put(SOURCEE, pregnantGetSet.getSource());

        // Inserting Row
      long id = db.insert(PREGNANT, null, values);
//        db.close(); // Closing database connection
        return  id;
    }

//advdag
}

