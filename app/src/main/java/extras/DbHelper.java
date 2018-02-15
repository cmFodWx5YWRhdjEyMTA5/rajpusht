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
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "rajpustData";

    // Contacts table name
    private static final String TABLE_FAMILYDATA = "familydata";
    private static final String MEMBER_BASIC = "memberbasic";
    private static final String WOMEN_EXTRA = "womenextra";
    private static final String CHILD_EXTRA = "childextra";
    private static final String PREGNANT = "pregnant";


    // Contacts Table Columns names
    private static final String FAMILY_ID = "familyid";
    private static final String DIST_CODE = "dist_code";
    private static final String PROJECT_CODE = "project_code";
    private static final String SECTOR_CODE = "sector_code";
    private static final String AWC_CODE = "awc_code";
    private static final String RELIGION = "religion";
    private static final String CASTE = "caste";
    private static final String RCARD = "rcard";
    private static final String FAMILY_TYPE = "family_type";
    private static final String SURVEYOR_ID = "surveyor_id";

    private static final String SURVEY_ID = "survey_id";
    private static final String FAMILYY_ID = "familyy_id";
    private static final String NAME = "name";
    private static final String DOR = "dor";
    private static final String DOENRTY = "doentry";
    private static final String DOEXIT = "doexit";
    private static final String DOB = "dob";
    private static final String AGE = "age";
    private static final String IF_DOB_ASSUMED = "if_dob";
    private static final String DODEATH = "dodeath";
    private static final String AADHAR = "aadhar";
    private static final String AADHAR_ENROLMENT = "aadhar_enrolment";
    private static final String AADHAR_DATE = "aadhar_date";
    private static final String AADHAR_TIME = "aadhar_time";
    private static final String BHAMASHA = "bhamasha";
    private static final String MOBILE = "mobile";
    private static final String RELATION = "relation";
    private static final String SEX = "sex";
    private static final String HANDICAP = "handicap";
    private static final String IF_MARRIED = "if_married";
    private static final String MOTHER_ID = "mothet_id";
    private static final String STATUS = "status";
    private static final String STAGE = "stage";
    private static final String SUB_STAGE = "sbu_stage";
    private static final String TRACK_STATUS = "track_status";
    private static final String SURVAYORR_ID = "survayorr_id";
    private static final String TIME_STAMP = "time_stamp";
    private static final String SOURCE = "source";

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

    private static final String PREGNENTUID = "pregnantuid";
    private static final String PREGNANCY_ID = "pregnancy_id";
    private static final String SURVEYYYY_ID = "surveyyyy_id";
    private static final String ORDEROF_PREGNANCY = "orderof_pregnancy";
    private static final String LMP_DATE = "lmp_date";
    private static final String SURVEYORR_ID = "surveyorr_id";
    private static final String TIMEE_STAMP = "timee_stamp";
    private static final String SOURCEE = "sourcee";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAMILYDATA_TABLE = "CREATE TABLE " + TABLE_FAMILYDATA + "(" + FAMILY_ID + " INTEGER PRIMARY KEY," + DIST_CODE + " TEXT," + PROJECT_CODE + " TEXT," + SECTOR_CODE + " TEXT," + AWC_CODE + " TEXT," + RELIGION + " INT," + CASTE + " INT," + RCARD + " INT," + FAMILY_TYPE + " INT," + SURVEYOR_ID + " TEXT," + ")";
        db.execSQL(CREATE_FAMILYDATA_TABLE);

        String CREATE_MEMBERBASIC_TABLE = "CREATE TABLE " + MEMBER_BASIC + "(" + SURVEY_ID + " INTEGER PRIMARY KEY," + FAMILYY_ID + " TEXT," + NAME + " TEXT," + DOR + " TEXT," + DOENRTY + " TEXT," + DOEXIT + " TEXT," + DOB + " TEXT," + AGE + " TEXT," + IF_DOB_ASSUMED + " TEXT," + DODEATH + " TEXT," + AADHAR + " TEXT," + AADHAR_ENROLMENT + " TEXT," + AADHAR_DATE + " TEXT," + AADHAR_TIME + " TEXT," + BHAMASHA + " TEXT," + MOBILE + " TEXT," + RELATION + " TEXT," + SEX + " TEXT," + HANDICAP + " TEXT," + IF_MARRIED + " TEXT," + MOTHER_ID + " TEXT," + STATUS + " TEXT," + STAGE + " TEXT," + SUB_STAGE + " TEXT," + TRACK_STATUS + " TEXT," + SURVAYORR_ID + " TEXT," + TIME_STAMP + " TEXT," + SOURCE + " TEXT," + ")";
        db.execSQL(CREATE_MEMBERBASIC_TABLE);

        String CREATE_WOMEN_EXTRA = "CREATE TABLE " + WOMEN_EXTRA + "(" + WOMENUID + " INTEGER PRIMARY KEY," + SURVEYY_ID + " TEXT," + EDUCATION + " TEXT," + COOKINGFUEL + " TEXT," + DECISIONMAKER_OWNHEALTH + " TEXT," + DECISIONMAKER_CHILDHEALTH + " TEXT," + IF_BANK_ACCOUNT + " TEXT," + AC_HOLDER_NAME + " TEXT," + BANK_NAME + " TEXT," + BRANCH + " TEXT," + AC_NO + " TEXT," + IFSC_CODE + " TEXT," + BANK_DISTANCE + " TEXT," + POSTOFFICE_NAME + " TEXT," + POSTOFFICE_ADDRESS + " TEXT," + PINCODE + " TEXT," + POSTOFFICE_AC + " TEXT," + HOEMO_CODE + " TEXT," + ")";
        db.execSQL(CREATE_WOMEN_EXTRA);

        String CREATE_CHILD_EXTRA = "CREATE TABLE " + CHILD_EXTRA + "(" + CHILDUID + " INTEGER PRIMARY KEY," + SURVEYYY_ID + " TEXT," + DO_DELIVERY + " TEXT," + DELIVERY_PLACE + " TEXT," + CHILD_ORDER + " TEXT," + BIRTH_WT + " TEXT," + FULL_TERM + " TEXT," + WHENFIRST_BF + " TEXT," + IFFEED_KHEES + " TEXT," + CURRENTLY_BF + " TEXT," + WHENSTOP_BF + " TEXT," + ANYTHINGBEFORE_BF + " TEXT," + IFSTARTED_SOLIDFOOD + " TEXT," + WHICHMONTH_SOLIDFOOD + " TEXT," + CHILDIMMUNIZATION_STATUS + " TEXT," + ")";
        db.execSQL(CREATE_CHILD_EXTRA);

        String CREATE_PREGNANT = "CREATE TABLE " + PREGNANT + "(" + PREGNENTUID + " INTEGER PRIMARY KEY," + PREGNANCY_ID + " TEXT," + SURVEYYYY_ID + " TEXT," + ORDEROF_PREGNANCY + " TEXT," + LMP_DATE + " TEXT," + SURVEYORR_ID + " TEXT," + TIMEE_STAMP + " TEXT," + SOURCEE + " TEXT," + ")";
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
    public void addFamilyData(FamilyDetailGetSet familyDetailGetSet) {
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
        db.insert(TABLE_FAMILYDATA, null, values);
        db.close(); // Closing database connection
    }

    public void addMemberBasic(MemberBasicGetSet memberBasicGetSet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SURVEY_ID, memberBasicGetSet.getSurvey_id());
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
        values.put(TRACK_STATUS, memberBasicGetSet.getTrackstatus());
        values.put(SURVAYORR_ID, memberBasicGetSet.getSurveyor_id());
        values.put(TIME_STAMP, memberBasicGetSet.getTimestamp());
        values.put(SOURCE, memberBasicGetSet.getSource());

        // Inserting Row
        db.insert(MEMBER_BASIC, null, values);
        db.close(); // Closing database connection
    }

    public void addWomenExtra(WomenBasicGetSet womenBasicGetSet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SURVEYY_ID, womenBasicGetSet.getSurvey_id());
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

        // Inserting Row
        db.insert(WOMEN_EXTRA, null, values);
        db.close(); // Closing database connection
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
        db.close(); // Closing database connection
    }

    public void addPregnant(PregnantGetSet pregnantGetSet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PREGNANCY_ID, pregnantGetSet.getPregnancyid());
        values.put(SURVEYYYY_ID, pregnantGetSet.getSurveyid());
        values.put(ORDEROF_PREGNANCY, pregnantGetSet.getOrderofpregnancy());
        values.put(LMP_DATE, pregnantGetSet.getLmpdate());
        values.put(SURVEYORR_ID, pregnantGetSet.getSurveyorid());
        values.put(TIMEE_STAMP, pregnantGetSet.getTimestamp());
        values.put(SOURCEE, pregnantGetSet.getSource());

        // Inserting Row
        db.insert(PREGNANT, null, values);
        db.close(); // Closing database connection
    }

//advdag
}
