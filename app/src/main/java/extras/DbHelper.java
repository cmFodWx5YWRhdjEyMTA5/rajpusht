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
    private static final int DATABASE_VERSION = 20;

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
    private static final String PW_TRACKING="pw_tracking";
    private static final String DIET="diet";

//Family memebr table
    // Contacts Table Columns names
    private static final String FAMILYTABLE_ID="familytableId";
    private static final String FAMILY_ID = "family_id";
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
//    private static final String IS_EDITED= "is_edited";
    private static final String IS_NEW_FAMILY= "is_new";
    private static final String IS_APPROVED_FAMILY = "is_approved";

    //MEMBER _ BASIC

    private static final String MEMBERS_ID= "Members_id";

    private static final String NAME = "name";
    private static final String DOR = "dor";
    private static final String DOENRTY = "doentry";
    private static final String DOEXIT = "doexit";
    private static final String DOB = "dob";
    private static final String AGE = "age";
    private static final String IF_DOB_ASSUMED="if_dob_assumed";
    private static final String DODEATH="dodeath";
    private static final String AADDHAR="aaddhar";
    private static final String AADDHAR_ENROL_NO="aaddhar_enrol_no";
    private static final String AADDHAR_DATE_STAMP="aaddhar_date_stamp";
    private static final String AADDHAR_TIME_STAMP="aaddhar_time_stamp";
    private static final String BHAMASHA="bhamasha";
    private static final String MOBILE="mobile";
    private static final String RELATION="relation";
    private static final String SEX="sex";
    private static final String HANDICAP="handicap";
    private static final String IF_MARRIED = "if_married";
    private static final String MOTHER_ID = "mother_id";
    private static final String STATUS = "status";
    private static final String STAGE = "stage";
    private static final String SUB_STAGE = "sub_stage";
    private static final String IS_TO_TRACK = "IS_TO_TRACK";
//    private static final String SURVAYORR_ID = "survayorr_id";
    private static final String TIME_STAMP = "time_stamp";
    private static final String SOURCE = "source";
    private static final String IS_EDITED_MEMBER= "is_edited";
    private static final String IS_NEW_MEMBER= "is_new";
    private static final String IS_APPROVED_MEMBER = "is_approved";

    //



    private static final String WOMENUID = "womenuid";
    private static final String SURVEYY_ID = "surveyY_id";
    private static final String EDUCATION = "education";
    private static final String COOKING_FUEL = "cooking_fuel";
    private static final String DECISIONMAKER_OWN_HEALTH = "decisionmaker_own_health";
    private static final String DECISIONMAKER_CHILD_HEALTH = "decisionmaker_child_health";
    private static final String IF_BANK_ACCONT = "if_bank_account";
    private static final String AC_HOLDER_NAME = "ac_holder_name";
    private static final String BANK_NAME = "bank_name";
    private static final String BRANCH = "branch";
    private static final String AC_NO = "ac_no";
    private static final String IFSC_CODE = "ifsc_code";
    private static final String BANK_DISTANCE = "bank_distance";
    private static final String BANK_ATM_DISTANCE = "bank_atm_distance";
    private static final String POSTOFFICE_NAME = "postoffice_name";
    private static final String POSTOFFICE_ADDRESS = "postoffice_address";
    private static final String PIN_CODE = "pin_code";
    private static final String POST_OFFICE_AC = "post_office_ac";
    private static final String HOEMO_CODE = "hoemo_code";
//    private static final String IS_EDITED= "is_edited";
//    private static final String IS_NEW= "is_new";
//    private static final String IS_APPROVED = "is_approved";



    private static final String CHILDUID = "childuid";
    private static final String SURVEYYY_ID = "SURVEYYYid";
    private static final String DODELIVERY = "dodelivery";
    private static final String DELIVERY_PLACE = "delivery_place";
    private static final String CHILD_ORDER = "child_order";
    private static final String BIRTH_WT = "birth_wt";
    private static final String FULL_TERM = "full_term";
    private static final String WHEN_FIRST_BF = "when_first_bf";
    private static final String IF_FEED_KHEES = "if_feed_khees";
    private static final String CURRENTLY_BF = "currently_bf";
    private static final String WHEN_STOP_BF = "when_stop_bf";
    private static final String ANYTHING_BEFORE_BF = "anything_before_bf";
    private static final String IF_STARTED_SOLID_FOOD = "if_started_solid_food";
    private static final String WHICH_MONTH_SOLID_FOOD = "which_month_solid_food";
    private static final String CHILD_IMMUNIZATION_STATUS = "child_immunization_status";
//    private static final String IS_EDITED= "is_edited";
//    private static final String IS_NEW= "is_new";
//    private static final String IS_APPROVED = "is_approved";




    private static final String PREGNANCY_ID = "pregnancy_id";
    private static final String SURVEYYYY_ID = "surveyyyy_id";
    private static final String ORDER_OF_PREGNANCY = "order_of_pregnancy";
    private static final String LMP_DATE = "lmp_date";
    private static final String IS_ACTIVE = "Is_active";
    private static final String SURVEYORR_ID = "surveyorr_id";
    private static final String TIMEE_STAMP = "timee_stamp";
    private static final String SOURCEE = "sourcee";
    private static final String IS_EDITED= "is_edited";
    private static final String IS_NEW= "is_new";
    private static final String IS_APPROVED = "is_approved";


//pw  track table
    private  static final String IS_AVAILBLE="is_available";
    private static final String NA_REASON="na_reason";
    private static final String IS_ANC="is_anc";
    private static final String ANC_DATE="anc_date";
    private static final String IF_COUNSEL_ON_SELFFEED="if_counsel_on_selffeed";
    private static final String IF_COUNSEL_ON_BF="if_counsel_on_bf";
    private static final String SPEND_ON_FOOD="spend_on_food";
    private static final String HEIGHT="height";
    private static final String WEIGHT="weight";

//diet table
private static final String FEED_A="feed_a";
private static final String FEED_B="feed_b";
private static final String FEED_C="feed_c";
private static final String FEED_D="feed_d";
private static final String FEED_E="feed_e";
private static final String FEED_F="feed_f";
private static final String FEED_G="feed_g";
private static final String FEED_H="feed_h";
private static final String FEED_I="feed_I";
private static final String FEED_J="feed_j";
private static final String FEED_K="feed_k";
private static final String FEED_L="feed_l";
private static final String FEED_M="feed_m";

private static final String FEED_A_NOS="feed_a_nos";
private static final String FEED_B_NOS="feed_b_nos";
private static final String FEED_C_NOS="feed_c_nos";
private static final String FEED_D_NOS="feed_d_nos";
private static final String FEED_E_NOS="feed_e_nos";
private static final String FEED_F_NOS="feed_f_nos";
private static final String FEED_G_NOS="feed_g_nos";
private static final String FEED_H_NOS="feed_h_nos";
private static final String FEED_I_NOS="feed_i_nos";
private static final String FEED_J_NOS="feed_j_nos";
private static final String FEED_K_NOS="feed_k_nos";
private static final String FEED_L_NOS="feed_l_nos";
private static final String FEED_M_NOS="feed_m_nos";









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
            + IS_EDITED + " VARCHAR, "
            + IS_NEW + " VARCHAR, "
            + IS_APPROVED + " VARCHAR, "
            + SURVEYOR_ID + " VARCHAR)";

public static final String CREATE_DIET_TABLE= "CREATE TABLE " + DIET + " ( "
        + PREGNANCY_ID + " VARCHAR, "
        + MEMBERS_ID + " VARCHAR, "
        + STAGE + " VARCHAR, "
        + SUB_STAGE + " VARCHAR, "
        + FEED_A + " VARCHAR, "
        + FEED_A_NOS + " INTEGER, "
        + FEED_B + " VARCHAR, "
        + FEED_B_NOS + " INTEGER, "
        + FEED_C + " VARCHAR, "
        + FEED_C_NOS + " INTEGER, "
        + FEED_D + " VARCHAR, "
        + FEED_D_NOS + " INTEGER, "
        + FEED_E + " VARCHAR, "
        + FEED_E_NOS + " INTEGER, "
        + FEED_F + " VARCHAR, "
        + FEED_F_NOS + " INTEGER, "
        + FEED_G + " VARCHAR, "
        + FEED_G_NOS + " INTEGER, "
        + FEED_H + " VARCHAR, "
        + FEED_H_NOS + " INTEGER, "
        + FEED_I + " VARCHAR, "
        + FEED_I_NOS + " INTEGER, "
        + FEED_J + " VARCHAR, "
        + FEED_J_NOS + " INTEGER, "
        + FEED_K + " VARCHAR, "
        + FEED_K_NOS + " INTEGER, "
        + FEED_L + " VARCHAR, "
        + FEED_L_NOS + " INTEGER, "
        + FEED_M + " VARCHAR, "
        + FEED_M_NOS + " INTEGER, "
        + SOURCE + " VARCHAR, "
        + IS_NEW + " VARCHAR, "
        + IS_EDITED + " VARCHAR, "
        + IS_APPROVED + " VARCHAR)";

    public static final String TRACKING_TABLE= "CREATE TABLE " + PW_TRACKING + " ( "
            + PREGNANCY_ID + " VARCHAR, "
            + MEMBERS_ID + " VARCHAR, "
            + STAGE + " VARCHAR, "
            + SUB_STAGE + " VARCHAR, "
            + IS_AVAILBLE + " VARCHAR, "
            + NA_REASON + " INTEGER, "
            + IS_ANC + " VARCHAR, "
            + ANC_DATE + " DATE, "
            + IF_COUNSEL_ON_SELFFEED + " VARCHAR, "
            + IF_COUNSEL_ON_BF + " VARCHAR, "
            + SPEND_ON_FOOD + " VARCHAR, "
            + HEIGHT + "  FLOAT, "
            + WEIGHT + " FLOAT, "
            + SURVEYOR_ID + " INTEGER, "
            + SOURCE + " VARCHAR, "
            + TIME_STAMP + " DATETIME, "
            + IS_NEW + " VARCHAR, "
            + IS_EDITED + " VARCHAR, "
            + IS_APPROVED + " VARCHAR)";






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
                MEMBERS_ID+ " VARCHAR, "
                + FAMILY_ID + " VARCHAR, "
                + NAME + " VARCHAR,"
                + DOR + " DATE, "
                + DOENRTY + " DATE, "
                + DOEXIT + " DATE, "
                + DOB + " DATE, "
                + AGE + " FLOAT, "
                + IF_DOB_ASSUMED + " VARCHAR, "
                + DODEATH + " DATE, "
                + AADDHAR + " VARCHAR, "
                + AADDHAR_ENROL_NO + " VARCHAR, "
                + AADDHAR_DATE_STAMP + " DATE, "
                + AADDHAR_TIME_STAMP + " TEXT, "
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
                + SURVEYOR_ID + " VARCHAR, "
                + TIME_STAMP + " DATETIME, "
                + IS_EDITED + " VARCHAR, "
                + IS_NEW + " VARCHAR, "
                + IS_APPROVED + " TEXT, "
                + SOURCE + " TEXT)";
        db.execSQL(CREATE_MEMBERBASIC_TABLE);

        String CREATE_WOMEN_EXTRA = "CREATE TABLE " + WOMEN_EXTRA + "("
//                + WOMENUID + " INTEGER PRIMARY KEY,"
                + MEMBERS_ID + " VARCHAR, "
                + EDUCATION + " INTEGER, "
                + COOKING_FUEL + " INTEGER, "
                + DECISIONMAKER_OWN_HEALTH + " INTEGER, "
                + DECISIONMAKER_CHILD_HEALTH + " INTEGER, "
                + IF_BANK_ACCONT + " VARCHAR, "
                + AC_HOLDER_NAME + " VARCHAR, "
                + BANK_NAME + " VARCHAR, "
                + BRANCH + " VARCHAR, "
                + AC_NO + " VARCHAR, "
                + IFSC_CODE + " VARCHAR, "
                + BANK_DISTANCE + " FLOAT, "
                + BANK_ATM_DISTANCE + " FLOAT, "
                + POSTOFFICE_NAME + " VARCHAR, "
                + POSTOFFICE_ADDRESS + " VARCHAR, "
                + PIN_CODE + " VARCHAR, "
                + POST_OFFICE_AC + " VARCHAR, "
                + HOEMO_CODE + " VARCHAR, "
                + IS_EDITED + " VARCHAR, "
                + IS_NEW + " VARCHAR, "
                + IS_APPROVED + " VARCHAR)";
        db.execSQL(CREATE_WOMEN_EXTRA);

        String CREATE_CHILD_EXTRA = "CREATE TABLE " + CHILD_EXTRA + "("

                + MEMBERS_ID + " VARCHAR, "
                + DODELIVERY + " DATE, "
                + DELIVERY_PLACE + " INTEGER, "
                + CHILD_ORDER + " INTEGER, "
                + BIRTH_WT + " FLOAT,"
                + FULL_TERM + " VARCHAR, "
                + WHEN_FIRST_BF + " INTEGER, "
                + IF_FEED_KHEES + " VARCHAR, "
                + CURRENTLY_BF + " VARCHAR, "
                + WHEN_STOP_BF + " INTEGER, "
                + ANYTHING_BEFORE_BF + " VARCHAR, "
                + IF_STARTED_SOLID_FOOD + " VARCHAR, "
                + WHICH_MONTH_SOLID_FOOD + " FLOAT, "
                + CHILD_IMMUNIZATION_STATUS + " VARCHAR, "
                + IS_EDITED + " VARCHAR, "
                + IS_NEW + " VARCHAR, "
                + IS_APPROVED + " VARCHAR)";
        db.execSQL(CREATE_CHILD_EXTRA);

        String CREATE_PREGNANT = "CREATE TABLE " + PREGNANT + "("
//                + PREGNENTUID + " INTEGER PRIMARY KEY,"
                + PREGNANCY_ID + " VARCHAR, "
                + MEMBERS_ID + " VARCHAR, "
                + ORDER_OF_PREGNANCY + " INTEGER, "
                + LMP_DATE + " DATE,"
                + IS_ACTIVE + " VARCHAR, "
                + SURVEYOR_ID + " VARCHAR, "
                + TIME_STAMP + " DATETIME,"
                + SOURCE + " VARCHAR, "
                + IS_EDITED + " VARCHAR, "
                + IS_NEW + " VARCHAR, "
                + IS_APPROVED + " VARCHAR)";
                db.execSQL(CREATE_PREGNANT);
                db.execSQL(CREATE_DIET_TABLE);
                db.execSQL(TRACKING_TABLE);
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
        db.execSQL("DROP TABLE IF EXISTS " + DIET);
        db.execSQL("DROP TABLE IF EXISTS " + PW_TRACKING);

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
        values.put(VILLAGE_CODE, familyDetailGetSet.getVillage_code());
        values.put(IS_NEW, familyDetailGetSet.getIs_new());

        // Inserting Row
     long id=   db.insert(TABLE_FAMILYDATA, null, values);
//        db.close(); // Closing database connection
        return id;
    }

    public long addMemberBasic(MemberBasicGetSet memberBasicGetSet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MEMBERS_ID, memberBasicGetSet.getmemberId());
        values.put(FAMILY_ID, memberBasicGetSet.getFamily_id());
        values.put(NAME, memberBasicGetSet.getName());
        values.put(DOR, memberBasicGetSet.getDor());
        values.put(DOENRTY, memberBasicGetSet.getDoentry());
        values.put(DOEXIT, memberBasicGetSet.getDoexit());
        values.put(DOB, memberBasicGetSet.getDob());
        values.put(AGE, memberBasicGetSet.getAge());
        values.put(IF_DOB_ASSUMED, memberBasicGetSet.getIfdobasum());
        values.put(DODEATH, memberBasicGetSet.getDod());
        values.put(AADDHAR, memberBasicGetSet.getAadhar());
        values.put(AADDHAR_ENROL_NO, memberBasicGetSet.getAadharenrol());
        values.put(AADDHAR_DATE_STAMP, memberBasicGetSet.getAadhardate());
        values.put(AADDHAR_TIME_STAMP, memberBasicGetSet.getAadhartime());
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
        values.put(SURVEYOR_ID, memberBasicGetSet.getSurveyor_id());
        values.put(TIME_STAMP, memberBasicGetSet.getTimestamp());
        values.put(SOURCE, memberBasicGetSet.getSource());
        values.put(IS_EDITED, memberBasicGetSet.getIsEditedMember());
        values.put(IS_NEW, memberBasicGetSet.getIsNew());
        values.put(IS_APPROVED, memberBasicGetSet.getIsApprovedMember());

        // Inserting Row
       long id = db.insert(MEMBER_BASIC, null, values);
//        db.close(); // Closing database connection
        return  id;
    }

    public long addWomenExtra(WomenBasicGetSet womenBasicGetSet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MEMBERS_ID, womenBasicGetSet.getSurvey_id());
        values.put(EDUCATION, womenBasicGetSet.getEducation());
        values.put(COOKING_FUEL, womenBasicGetSet.getCookingfuel());
        values.put(DECISIONMAKER_OWN_HEALTH, womenBasicGetSet.getDecicion_own());
        values.put(DECISIONMAKER_CHILD_HEALTH, womenBasicGetSet.getDecision_child());
        values.put(IF_BANK_ACCONT, womenBasicGetSet.getIf_bankaccount());
        values.put(AC_HOLDER_NAME, womenBasicGetSet.getAcholder_name());
        values.put(BANK_NAME, womenBasicGetSet.getBank_name());
        values.put(BRANCH, womenBasicGetSet.getBranch());
        values.put(AC_NO, womenBasicGetSet.getAc_num());
        values.put(IFSC_CODE, womenBasicGetSet.getIfsc());
        values.put(BANK_DISTANCE, womenBasicGetSet.getBank_distsnce());
        values.put(POSTOFFICE_NAME, womenBasicGetSet.getPostoffice_name());
        values.put(POSTOFFICE_ADDRESS, womenBasicGetSet.getPostoffice_address());
        values.put(PIN_CODE, womenBasicGetSet.getPincode());
        values.put(POST_OFFICE_AC, womenBasicGetSet.getPostoffice_ac());
        values.put(HOEMO_CODE, womenBasicGetSet.getHoemo_code());
        values.put(IS_EDITED, womenBasicGetSet.getIsedited());
        values.put(IS_NEW, womenBasicGetSet.getIsNew());
        values.put(IS_APPROVED, womenBasicGetSet.getIsApproved());
//        values.put(HOEMO_CODE, womenBasicGetSet.getHoemo_code());

        // Inserting Row
      long id =  db.insert(WOMEN_EXTRA, null, values);
//        db.close(); // Closing database connection
        return id;
    }

    public void addChildExtra(ChildExtraGetSet childExtraGetSet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MEMBERS_ID, childExtraGetSet.getSurvey_id());
        values.put(DODELIVERY, childExtraGetSet.getDo_delivery());
        values.put(DELIVERY_PLACE, childExtraGetSet.getDelivery_place());
        values.put(CHILD_ORDER, childExtraGetSet.getChild_order());
        values.put(BIRTH_WT, childExtraGetSet.getBirth_wl());
        values.put(FULL_TERM, childExtraGetSet.getFull_term());
        values.put(WHEN_FIRST_BF, childExtraGetSet.getWhenfirst_bf());
        values.put(IF_FEED_KHEES, childExtraGetSet.getIffeed_khees());
        values.put(CURRENTLY_BF, childExtraGetSet.getCurrently_bf());
        values.put(WHEN_STOP_BF, childExtraGetSet.getWhenstop_bf());
        values.put(ANYTHING_BEFORE_BF, childExtraGetSet.getAnythingbefore_bf());
        values.put(IF_STARTED_SOLID_FOOD, childExtraGetSet.getIfstarted_solidfood());
        values.put(WHICH_MONTH_SOLID_FOOD, childExtraGetSet.getWhichmonth_solidfood());
        values.put(CHILD_IMMUNIZATION_STATUS, childExtraGetSet.getChildimmunization_status());
        values.put(IS_APPROVED, childExtraGetSet.getIsApproved());
        values.put(IS_NEW, childExtraGetSet.getIsNew());
        values.put(IS_EDITED, childExtraGetSet.getISEdited());

        // Inserting Row
        db.insert(CHILD_EXTRA, null, values);
//        db.close(); // Closing database connection
    }

    public long addPregnant(PregnantGetSet pregnantGetSet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PREGNANCY_ID, pregnantGetSet.getPregnancyid());
        values.put(MEMBERS_ID, pregnantGetSet.getSurveyid());
        values.put(ORDER_OF_PREGNANCY, pregnantGetSet.getOrderofpregnancy());
        values.put(LMP_DATE, pregnantGetSet.getLmpdate());
        values.put(SURVEYOR_ID, pregnantGetSet.getSurveyorid());
        values.put(TIME_STAMP, pregnantGetSet.getTimestamp());

        values.put(IS_ACTIVE, "Y");
        values.put(IS_EDITED, "");
        values.put(IS_NEW,"Y");
        values.put(IS_APPROVED, "");
        values.put(SOURCE, pregnantGetSet.getSource());

        // Inserting Row
      long id = db.insert(PREGNANT, null, values);
//        db.close(); // Closing database connection
        return  id;
    }

//advdag

    //TRACKING INSERT


}

