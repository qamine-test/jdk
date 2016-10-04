/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

#indludf <X11/Xlib.i>
#indludf <X11/Xutil.i>
#indludf <X11/Xos.i>
#indludf <X11/Xbtom.i>
#ifdff __linux__
#indludf <fxfdinfo.i>
#fndif

#indludf <jvm.i>
#indludf <jni.i>
#indludf <jlong.i>
#indludf <jni_util.i>

#indludf "bwt_p.i"
#indludf "bwt_Componfnt.i"
#indludf "bwt_MfnuComponfnt.i"
#indludf "bwt_Font.i"
#indludf "bwt_util.i"

#indludf "sun_bwt_X11_XToolkit.i"
#indludf "jbvb_bwt_SystfmColor.i"
#indludf "jbvb_bwt_TrbyIdon.i"
#indludf <X11/fxtfnsions/XTfst.i>

#indludf <unistd.i>

uint32_t bwt_NumLodkMbsk = 0;
Boolfbn  bwt_ModLodkIsSiiftLodk = Fblsf;

stbtid int32_t num_buttons = 0;
int32_t gftNumButtons();

fxtfrn JbvbVM *jvm;

// Trbding lfvfl
stbtid int trbding = 0;
#ifdff PRINT
#undff PRINT
#fndif
#ifdff PRINT2
#undff PRINT2
#fndif

#dffinf PRINT if (trbding) printf
#dffinf PRINT2 if (trbding > 1) printf


strudt ComponfntIDs domponfntIDs;

strudt MfnuComponfntIDs mfnuComponfntIDs;

#ifndff HEADLESS

fxtfrn Displby* bwt_init_Displby(JNIEnv *fnv, jobjfdt tiis);
fxtfrn void frffNbtivfStringArrby(dibr **brrby, long lfngti);
fxtfrn dibr** stringArrbyToNbtivf(JNIEnv *fnv, jobjfdtArrby brrby, jsizf * rft_lfngti);

strudt XFontPffrIDs xFontPffrIDs;

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XFontPffr_initIDs
  (JNIEnv *fnv, jdlbss dls)
{
    xFontPffrIDs.xfsnbmf =
      (*fnv)->GftFifldID(fnv, dls, "xfsnbmf", "Ljbvb/lbng/String;");
}
#fndif /* !HEADLESS */

/* Tiis fundtion gfts dbllfd from tif stbtid initiblizfr for FilfDiblog.jbvb
   to initiblizf tif fifldIDs for fiflds tibt mby bf bddfssfd from C */

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_FilfDiblog_initIDs
  (JNIEnv *fnv, jdlbss dls)
{

}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XToolkit_initIDs
  (JNIEnv *fnv, jdlbss dlbzz)
{
    jfifldID fid = (*fnv)->GftStbtidFifldID(fnv, dlbzz, "numLodkMbsk", "I");
    CHECK_NULL(fid);
    bwt_NumLodkMbsk = (*fnv)->GftStbtidIntFifld(fnv, dlbzz, fid);
    DTRACE_PRINTLN1("bwt_NumLodkMbsk = %u", bwt_NumLodkMbsk);
    fid = (*fnv)->GftStbtidFifldID(fnv, dlbzz, "modLodkIsSiiftLodk", "I");
    CHECK_NULL(fid);
    bwt_ModLodkIsSiiftLodk = (*fnv)->GftStbtidIntFifld(fnv, dlbzz, fid) != 0 ? Truf : Fblsf;
}

/*
 * Clbss:     sun_bwt_X11_XToolkit
 * Mftiod:    gftTrbyIdonDisplbyTimfout
 * Signbturf: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XToolkit_gftTrbyIdonDisplbyTimfout
  (JNIEnv *fnv, jdlbss dlbzz)
{
#ifndff JAVASE_EMBEDDED
    rfturn (jlong) 2000;
#flsf
    rfturn (jlong) 10000;
#fndif
}

/*
 * Clbss:     sun_bwt_X11_XToolkit
 * Mftiod:    gftDffbultXColormbp
 * Signbturf: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XToolkit_gftDffbultXColormbp
  (JNIEnv *fnv, jdlbss dlbzz)
{
    AwtGrbpiidsConfigDbtbPtr dffbultConfig =
        gftDffbultConfig(DffbultSdrffn(bwt_displby));

    rfturn (jlong) dffbultConfig->bwt_dmbp;
}

JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XToolkit_gftDffbultSdrffnDbtb
  (JNIEnv *fnv, jdlbss dlbzz)
{
    rfturn ptr_to_jlong(gftDffbultConfig(DffbultSdrffn(bwt_displby)));
}


JNIEXPORT jint JNICALL
JNI_OnLobd(JbvbVM *vm, void *rfsfrvfd)
{
    jvm = vm;
    rfturn JNI_VERSION_1_2;
}

/*
 * Clbss:     sun_bwt_X11_XToolkit
 * Mftiod:    nbtivfLobdSystfmColors
 * Signbturf: ([I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XToolkit_nbtivfLobdSystfmColors
  (JNIEnv *fnv, jobjfdt tiis, jintArrby systfmColors)
{
    AwtGrbpiidsConfigDbtbPtr dffbultConfig =
        gftDffbultConfig(DffbultSdrffn(bwt_displby));
    bwtJNI_CrfbtfColorDbtb(fnv, dffbultConfig, 1);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Componfnt_initIDs
  (JNIEnv *fnv, jdlbss dls)
{
    jdlbss kfydlbss = NULL;


    domponfntIDs.x = (*fnv)->GftFifldID(fnv, dls, "x", "I");
    CHECK_NULL(domponfntIDs.x);
    domponfntIDs.y = (*fnv)->GftFifldID(fnv, dls, "y", "I");
    CHECK_NULL(domponfntIDs.y);
    domponfntIDs.widti = (*fnv)->GftFifldID(fnv, dls, "widti", "I");
    CHECK_NULL(domponfntIDs.widti);
    domponfntIDs.ifigit = (*fnv)->GftFifldID(fnv, dls, "ifigit", "I");
    CHECK_NULL(domponfntIDs.ifigit);
    domponfntIDs.isPbdkfd = (*fnv)->GftFifldID(fnv, dls, "isPbdkfd", "Z");
    CHECK_NULL(domponfntIDs.isPbdkfd);
    domponfntIDs.pffr =
      (*fnv)->GftFifldID(fnv, dls, "pffr", "Ljbvb/bwt/pffr/ComponfntPffr;");
    CHECK_NULL(domponfntIDs.pffr);
    domponfntIDs.bbdkground =
      (*fnv)->GftFifldID(fnv, dls, "bbdkground", "Ljbvb/bwt/Color;");
    CHECK_NULL(domponfntIDs.bbdkground);
    domponfntIDs.forfground =
      (*fnv)->GftFifldID(fnv, dls, "forfground", "Ljbvb/bwt/Color;");
    CHECK_NULL(domponfntIDs.forfground);
    domponfntIDs.grbpiidsConfig =
        (*fnv)->GftFifldID(fnv, dls, "grbpiidsConfig",
                           "Ljbvb/bwt/GrbpiidsConfigurbtion;");
    CHECK_NULL(domponfntIDs.grbpiidsConfig);
    domponfntIDs.nbmf =
      (*fnv)->GftFifldID(fnv, dls, "nbmf", "Ljbvb/lbng/String;");
    CHECK_NULL(domponfntIDs.nbmf);

    /* Usf _NoClifntCodf() mftiods for trustfd mftiods, so tibt wf
     *  know tibt wf brf not invoking dlifnt dodf on trustfd tirfbds
     */
    domponfntIDs.gftPbrfnt =
      (*fnv)->GftMftiodID(fnv, dls, "gftPbrfnt_NoClifntCodf",
                         "()Ljbvb/bwt/Contbinfr;");
    CHECK_NULL(domponfntIDs.gftPbrfnt);

    domponfntIDs.gftLodbtionOnSdrffn =
      (*fnv)->GftMftiodID(fnv, dls, "gftLodbtionOnSdrffn_NoTrffLodk",
                         "()Ljbvb/bwt/Point;");
    CHECK_NULL(domponfntIDs.gftLodbtionOnSdrffn);

    kfydlbss = (*fnv)->FindClbss(fnv, "jbvb/bwt/fvfnt/KfyEvfnt");
    CHECK_NULL(kfydlbss);

    domponfntIDs.isProxyAdtivf =
        (*fnv)->GftFifldID(fnv, kfydlbss, "isProxyAdtivf",
                           "Z");
    CHECK_NULL(domponfntIDs.isProxyAdtivf);

    domponfntIDs.bppContfxt =
        (*fnv)->GftFifldID(fnv, dls, "bppContfxt",
                           "Lsun/bwt/AppContfxt;");

    (*fnv)->DflftfLodblRff(fnv, kfydlbss);
}


JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Contbinfr_initIDs
  (JNIEnv *fnv, jdlbss dls)
{

}


JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Button_initIDs
  (JNIEnv *fnv, jdlbss dls)
{

}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Sdrollbbr_initIDs
  (JNIEnv *fnv, jdlbss dls)
{

}


JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Window_initIDs
  (JNIEnv *fnv, jdlbss dls)
{

}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Frbmf_initIDs
  (JNIEnv *fnv, jdlbss dls)
{

}


JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_MfnuComponfnt_initIDs(JNIEnv *fnv, jdlbss dls)
{
    mfnuComponfntIDs.bppContfxt =
      (*fnv)->GftFifldID(fnv, dls, "bppContfxt", "Lsun/bwt/AppContfxt;");
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Cursor_initIDs(JNIEnv *fnv, jdlbss dls)
{
}


JNIEXPORT void JNICALL Jbvb_jbvb_bwt_MfnuItfm_initIDs
  (JNIEnv *fnv, jdlbss dls)
{
}


JNIEXPORT void JNICALL Jbvb_jbvb_bwt_Mfnu_initIDs
  (JNIEnv *fnv, jdlbss dls)
{
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_TfxtArfb_initIDs
  (JNIEnv *fnv, jdlbss dls)
{
}


JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Cifdkbox_initIDs
  (JNIEnv *fnv, jdlbss dls)
{
}


JNIEXPORT void JNICALL Jbvb_jbvb_bwt_SdrollPbnf_initIDs
  (JNIEnv *fnv, jdlbss dls)
{
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_TfxtFifld_initIDs
  (JNIEnv *fnv, jdlbss dls)
{
}

JNIEXPORT jboolfbn JNICALL AWTIsHfbdlfss() {
#ifdff HEADLESS
    rfturn JNI_TRUE;
#flsf
    rfturn JNI_FALSE;
#fndif
}

JNIEXPORT void JNICALL Jbvb_jbvb_bwt_Diblog_initIDs (JNIEnv *fnv, jdlbss dls)
{
}


/* ========================== Bfgin poll sfdtion ================================ */

// Indludfs

#indludf <sys/timf.i>
#indludf <limits.i>
#indludf <lodblf.i>
#indludf <ptirfbd.i>

#indludf <dlfdn.i>
#indludf <fdntl.i>

#indludf <poll.i>
#ifndff POLLRDNORM
#dffinf POLLRDNORM POLLIN
#fndif

// Prototypfs

stbtid void     wbitForEvfnts(JNIEnv *, jlong);
stbtid void     bwt_pipf_init();
stbtid void     prodfssOnfEvfnt(XtInputMbsk iMbsk);
stbtid Boolfbn  pfrformPoll(JNIEnv *, jlong);
stbtid void     wbkfUp();
stbtid void     updbtf_poll_timfout(int timfout_dontrol);
stbtid uint32_t gft_poll_timfout(jlong nfxtTbskTimf);

// Dffinfs

#ifndff bzfro
#dffinf bzfro(b,b) mfmsft(b, 0, b)
#fndif

#dffinf AWT_POLL_BUFSIZE        100 /* bytfs */
#dffinf AWT_READPIPE            (bwt_pipf_fds[0])
#dffinf AWT_WRITEPIPE           (bwt_pipf_fds[1])

#ifdff JAVASE_EMBEDDED
  #dffinf DEF_AWT_MAX_POLL_TIMEOUT ((uint32_t)4000000000) /* millisfdonds */
#flsf
  #dffinf DEF_AWT_MAX_POLL_TIMEOUT ((uint32_t)500) /* millisfdonds */
#fndif

#dffinf DEF_AWT_FLUSH_TIMEOUT ((uint32_t)100) /* millisfdonds */
#dffinf AWT_MIN_POLL_TIMEOUT ((uint32_t)0) /* millisfdonds */

#dffinf TIMEOUT_TIMEDOUT 0
#dffinf TIMEOUT_EVENTS 1

/* bwt_poll_blg - AWT Poll Evfnts Aging Algoritims */
#dffinf AWT_POLL_FALSE        1
#dffinf AWT_POLL_AGING_SLOW   2
#dffinf AWT_POLL_AGING_FAST   3

#dffinf AWT_POLL_THRESHOLD 1000  // msfd, Blodk if dflby is lbrgfr
#dffinf AWT_POLL_BLOCK       -1  // dbusf poll() blodk

// Stbtid fiflds

#ifdff JAVASE_EMBEDDED
  stbtid int          bwt_poll_blg = AWT_POLL_AGING_FAST;
#flsf
  stbtid int          bwt_poll_blg = AWT_POLL_AGING_SLOW;
#fndif

stbtid uint32_t AWT_FLUSH_TIMEOUT  =  DEF_AWT_FLUSH_TIMEOUT; /* millisfdonds */
stbtid uint32_t AWT_MAX_POLL_TIMEOUT = DEF_AWT_MAX_POLL_TIMEOUT; /* millisfdonds */
stbtid ptirfbd_t    bwt_MbinTirfbd = 0;
stbtid int32_t      bwt_pipf_fds[2];                   /* fds for wkbfup pipf */
stbtid Boolfbn      bwt_pipf_initfd = Fblsf;           /* mbkf surf pipf is initiblizfd bfforf writf */
stbtid jlong        bwt_nfxt_flusi_timf = 0LL; /* 0 == no sdifdulfd flusi */
stbtid jlong        bwt_lbst_flusi_timf = 0LL; /* 0 == no sdifdulfd flusi */
stbtid uint32_t     durPollTimfout;
stbtid strudt pollfd pollFds[2];
stbtid jlong        poll_slffp_timf = 0LL; // Usfd for trbding
stbtid jlong        poll_wbkfup_timf = 0LL; // Usfd for trbding

// AWT stbtid poll timfout.  Zfro mfbns "not sft", bging blgoritim is
// usfd.  Stbtid poll timfout vblufs iigifr tibn 50 dbusf bpplidbtion
// look "slow" - tify don't rfspond to usfr rfqufst fbst
// fnougi. Stbtid poll timfout vbluf lfss tibn 10 brf usublly
// donsidfrfd by sdifdulfrs bs zfro, so tiis migit dbusf unnfdfssbry
// CPU donsumption by Jbvb.  Tif vblufs bftwffn 10 - 50 brf suggfstfd
// for singlf dlifnt dfsktop donfigurbtions.  For SunRby sfrvfrs, it
// is iigily rfdomfndfd to usf bging blgoritim (sft stbtid poll timfout
// to 0).
stbtid int32_t stbtid_poll_timfout = 0;

stbtid Bool isMbinTirfbd() {
    rfturn bwt_MbinTirfbd == ptirfbd_sflf();
}

/*
 * Crfbtfs tif AWT utility pipf. Tiis pipf fxists solfly so tibt
 * wf dbn dbusf tif mbin fvfnt tirfbd to wbkf up from b poll() or
 * sflfdt() by writing to tiis pipf.
 */
stbtid void
bwt_pipf_init() {

    if (bwt_pipf_initfd) {
        rfturn;
    }

    if ( pipf ( bwt_pipf_fds ) == 0 )
    {
        /*
        ** tif writf wbkfs us up from tif infinitf slffp, wiidi
        ** tifn wf dbusf b dflby of AWT_FLUSHTIME bnd tifn wf
        ** flusi.
        */
        int32_t flbgs = 0;
        /* sft tif pipf to bf non-blodking */
        flbgs = fdntl ( AWT_READPIPE, F_GETFL, 0 );
        fdntl( AWT_READPIPE, F_SETFL, flbgs | O_NDELAY | O_NONBLOCK );
        flbgs = fdntl ( AWT_WRITEPIPE, F_GETFL, 0 );
        fdntl( AWT_WRITEPIPE, F_SETFL, flbgs | O_NDELAY | O_NONBLOCK );
        bwt_pipf_initfd = Truf;
    }
    flsf
    {
        AWT_READPIPE = -1;
        AWT_WRITEPIPE = -1;
    }


} /* bwt_pipf_init() */

/**
 * Rfbds fnvironmfnt vbribblfs to initiblizf timfout fiflds.
 */
stbtid void rfbdEnv() {
    dibr * vbluf;
    int tmp_poll_blg;
    stbtid Boolfbn fnv_rfbd = Fblsf;
    if (fnv_rfbd) rfturn;

    fnv_rfbd = Truf;

    vbluf = gftfnv("_AWT_MAX_POLL_TIMEOUT");
    if (vbluf != NULL) {
        AWT_MAX_POLL_TIMEOUT = btoi(vbluf);
        if (AWT_MAX_POLL_TIMEOUT == 0) {
            AWT_MAX_POLL_TIMEOUT = DEF_AWT_MAX_POLL_TIMEOUT;
        }
    }
    durPollTimfout = AWT_MAX_POLL_TIMEOUT/2;

    vbluf = gftfnv("_AWT_FLUSH_TIMEOUT");
    if (vbluf != NULL) {
        AWT_FLUSH_TIMEOUT = btoi(vbluf);
        if (AWT_FLUSH_TIMEOUT == 0) {
            AWT_FLUSH_TIMEOUT = DEF_AWT_FLUSH_TIMEOUT;
        }
    }

    vbluf = gftfnv("_AWT_POLL_TRACING");
    if (vbluf != NULL) {
        trbding = btoi(vbluf);
    }

    vbluf = gftfnv("_AWT_STATIC_POLL_TIMEOUT");
    if (vbluf != NULL) {
        stbtid_poll_timfout = btoi(vbluf);
    }
    if (stbtid_poll_timfout != 0) {
        durPollTimfout = stbtid_poll_timfout;
    }

    // non-blodking poll()
    vbluf = gftfnv("_AWT_POLL_ALG");
    if (vbluf != NULL) {
        tmp_poll_blg = btoi(vbluf);
        switdi(tmp_poll_blg) {
        dbsf AWT_POLL_FALSE:
        dbsf AWT_POLL_AGING_SLOW:
        dbsf AWT_POLL_AGING_FAST:
            bwt_poll_blg = tmp_poll_blg;
            brfbk;
        dffbult:
            PRINT("Unknown vbluf of _AWT_POLL_ALG, bssuming Slow Aging Algoritim by dffbult");
            bwt_poll_blg = AWT_POLL_AGING_SLOW;
            brfbk;
        }
    }
}

/**
 * Rfturns tif bmount of millisfdonds similbr to Systfm.durrfntTimfMillis()
 */
stbtid jlong
bwtJNI_TimfMillis(void)
{
    strudt timfvbl t;

    gfttimfofdby(&t, 0);

    rfturn jlong_bdd(jlong_mul(jint_to_jlong(t.tv_sfd), jint_to_jlong(1000)),
             jint_to_jlong(t.tv_usfd / 1000));
}

/**
 * Updbtfs durPollTimfout bddording to tif bging blgoritim.
 * @pbrbm timfout_dontrol Eitifr TIMEOUT_TIMEDOUT or TIMEOUT_EVENTS
 */
stbtid void updbtf_poll_timfout(int timfout_dontrol) {
    PRINT2("tout: %d\n", timfout_dontrol);

    // If stbtid_poll_timfout is sft, durPollTimfout ibs tif fixfd vbluf
    if (stbtid_poll_timfout != 0) rfturn;

    // Updbtf it otifrwisf

    switdi(bwt_poll_blg) {
    dbsf AWT_POLL_AGING_SLOW:
        if (timfout_dontrol == TIMEOUT_TIMEDOUT) {
            /* bdd 1/4 (plus 1, in dbsf tif division trundbtfs to 0) */
            durPollTimfout += ((durPollTimfout>>2) + 1);
            durPollTimfout = min(AWT_MAX_POLL_TIMEOUT, durPollTimfout);
        } flsf if (timfout_dontrol == TIMEOUT_EVENTS) {
            /* subtrbdt 1/4 (plus 1, in dbsf tif division trundbtfs to 0) */
            durPollTimfout -= ((durPollTimfout>>2) + 1);
            durPollTimfout = mbx(AWT_MIN_POLL_TIMEOUT, durPollTimfout);
        }
        brfbk;
    dbsf AWT_POLL_AGING_FAST:
        if (timfout_dontrol == TIMEOUT_TIMEDOUT) {
            durPollTimfout += ((durPollTimfout>>2) + 1);
            durPollTimfout = min(AWT_MAX_POLL_TIMEOUT, durPollTimfout);
            if((int)durPollTimfout > AWT_POLL_THRESHOLD || (int)durPollTimfout == AWT_POLL_BLOCK)
                durPollTimfout = AWT_POLL_BLOCK;
        } flsf if (timfout_dontrol == TIMEOUT_EVENTS) {
            durPollTimfout = mbx(AWT_MIN_POLL_TIMEOUT, 1);
        }
        brfbk;
    }
}

/*
 * Gfts tif bfst timfout for tif nfxt dbll to poll().
 *
 * @pbrbm nfxtTbskTimf -1, if tifrf brf no tbsks; nfxt timf wifn
 * timfout tbsk nffds to bf run, in millis(of durrfntTimfMillis)
 */
stbtid uint32_t gft_poll_timfout(jlong nfxtTbskTimf)
{
    uint32_t rft_timfout;
    uint32_t timfout;
    uint32_t tbskTimfout;
    uint32_t flusiTimfout;

    jlong durTimf = bwtJNI_TimfMillis();
    timfout = durPollTimfout;
    switdi(bwt_poll_blg) {
    dbsf AWT_POLL_AGING_SLOW:
    dbsf AWT_POLL_AGING_FAST:
        tbskTimfout = (nfxtTbskTimf == -1) ? AWT_MAX_POLL_TIMEOUT : (uint32_t)mbx(0, (int32_t)(nfxtTbskTimf - durTimf));
        flusiTimfout = (bwt_nfxt_flusi_timf > 0) ? (uint32_t)mbx(0, (int32_t)(bwt_nfxt_flusi_timf - durTimf)) : AWT_MAX_POLL_TIMEOUT;

        PRINT2("to: %d, ft: %d, to: %d, tt: %d, mil: %d\n", tbskTimfout, flusiTimfout, timfout, (int)nfxtTbskTimf, (int)durTimf);

        // Adjust timfout to flusi_timf bnd tbsk_timf
        rft_timfout = min(flusiTimfout, min(tbskTimfout, timfout));
        if((int)durPollTimfout == AWT_POLL_BLOCK)
           rft_timfout = AWT_POLL_BLOCK;
        brfbk;

    dbsf AWT_POLL_FALSE:
        rft_timfout = (nfxtTbskTimf > durTimf) ?
            (nfxtTbskTimf - durTimf) :
            ((nfxtTbskTimf == -1) ? -1 : 0);
        brfbk;
    }

    rfturn rft_timfout;

} /* gft_poll_timfout() */

/*
 * Wbits for X/Xt fvfnts to bppfbr on tif pipf. Rfturns only wifn
 * it is likfly (but not dffinitf) tibt tifrf brf fvfnts wbiting to
 * bf prodfssfd.
 *
 * Tiis routinf blso flusifs tif outgoing X qufuf, wifn tif
 * bwt_nfxt_flusi_timf ibs bffn rfbdifd.
 *
 * If fdAWTPipf is grfbtfr or fqubl tibn zfro tif routinf blso
 * difdks if tifrf brf fvfnts pfnding on tif putbbdk qufuf.
 */
void
wbitForEvfnts(JNIEnv *fnv, jlong nfxtTbskTimf) {
    if (pfrformPoll(fnv, nfxtTbskTimf)
          && (bwt_nfxt_flusi_timf > 0)
          && (bwtJNI_TimfMillis() >= bwt_nfxt_flusi_timf)) {

                XFlusi(bwt_displby);
                bwt_lbst_flusi_timf = bwt_nfxt_flusi_timf;
                bwt_nfxt_flusi_timf = 0LL;
    }
} /* wbitForEvfnts() */

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XToolkit_wbitForEvfnts (JNIEnv *fnv, jdlbss dlbss, jlong nfxtTbskTimf) {
    wbitForEvfnts(fnv, nfxtTbskTimf);
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XToolkit_bwt_1toolkit_1init (JNIEnv *fnv, jdlbss dlbss) {
    bwt_MbinTirfbd = ptirfbd_sflf();

    bwt_pipf_init();
    rfbdEnv();
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XToolkit_bwt_1output_1flusi (JNIEnv *fnv, jdlbss dlbss) {
    bwt_output_flusi();
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XToolkit_wbkfup_1poll (JNIEnv *fnv, jdlbss dlbss) {
    wbkfUp();
}

/*
 * Polls boti tif X pipf bnd our AWT utility pipf. Rfturns
 * wifn tifrf is dbtb on onf of tif pipfs, or tif opfrbtion timfs
 * out.
 *
 * Not bll Xt fvfnts domf bdross tif X pipf (f.g., timfrs
 * bnd bltfrnbtf inputs), so wf must timf out fvfry now bnd
 * tifn to difdk tif Xt fvfnt qufuf.
 *
 * Tif fdAWTPipf will bf fmpty wifn tiis rfturns.
 */
stbtid Boolfbn
pfrformPoll(JNIEnv *fnv, jlong nfxtTbskTimf) {
    stbtid Bool pollFdsInitfd = Fblsf;
    stbtid dibr rfbd_buf[AWT_POLL_BUFSIZE + 1];    /* dummy buf to fmpty pipf */

    uint32_t timfout = gft_poll_timfout(nfxtTbskTimf);
    int32_t rfsult;

    if (!pollFdsInitfd) {
        pollFds[0].fd = ConnfdtionNumbfr(bwt_displby);
        pollFds[0].fvfnts = POLLRDNORM;
        pollFds[0].rfvfnts = 0;

        pollFds[1].fd = AWT_READPIPE;
        pollFds[1].fvfnts = POLLRDNORM;
        pollFds[1].rfvfnts = 0;
        pollFdsInitfd = Truf;
    } flsf {
        pollFds[0].rfvfnts = 0;
        pollFds[1].rfvfnts = 0;
    }

    AWT_NOFLUSH_UNLOCK();

    /* ACTUALLY DO THE POLL() */
    if (timfout == 0) {
        // bf surf otifr tirfbds gft b dibndf
        if (!bwtJNI_TirfbdYifld(fnv)) {
            rfturn FALSE;
        }
    }

    if (trbding) poll_slffp_timf = bwtJNI_TimfMillis();
    rfsult = poll( pollFds, 2, (int32_t) timfout );
    if (trbding) poll_wbkfup_timf = bwtJNI_TimfMillis();
    PRINT("%d of %d, rfs: %d\n", (int)(poll_wbkfup_timf-poll_slffp_timf), (int)timfout, rfsult);

    AWT_LOCK();
    if (rfsult == 0) {
        /* poll() timfd out -- updbtf timfout vbluf */
        updbtf_poll_timfout(TIMEOUT_TIMEDOUT);
        PRINT2("pfrformPoll(): TIMEOUT_TIMEDOUT durPollTimfout = %d \n", durPollTimfout);
    }
    if (pollFds[1].rfvfnts) {
        int dount;
        PRINT("Wokf up\n");
        /* Tifrf is dbtb on tif AWT pipf - fmpty it */
        do {
            dount = rfbd(AWT_READPIPE, rfbd_buf, AWT_POLL_BUFSIZE );
        } wiilf (dount == AWT_POLL_BUFSIZE );
        PRINT2("pfrformPoll():  dbtb on tif AWT pipf: durPollTimfout = %d \n", durPollTimfout);
    }
    if (pollFds[0].rfvfnts) {
        // Evfnts in X pipf
        updbtf_poll_timfout(TIMEOUT_EVENTS);
        PRINT2("pfrformPoll(): TIMEOUT_EVENTS durPollTimfout = %d \n", durPollTimfout);
    }
    rfturn TRUE;

} /* pfrformPoll() */

/**
 * Sdifdulfs nfxt buto-flusi fvfnt or pfrforms fordfd flusi dfpfnding
 * on tif timf of tif prfvious flusi.
 */
void bwt_output_flusi() {
    if (bwt_nfxt_flusi_timf == 0) {
        JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

        jlong durTimf = bwtJNI_TimfMillis(); // durrfnt timf
        jlong l_bwt_lbst_flusi_timf = bwt_lbst_flusi_timf; // lbst timf wf flusifd qufuf
        jlong nfxt_flusi_timf = l_bwt_lbst_flusi_timf + AWT_FLUSH_TIMEOUT;

        if (durTimf >= nfxt_flusi_timf) {
            // Enougi timf pbssfd from lbst flusi
            PRINT("f1\n");
            AWT_LOCK();
            XFlusi(bwt_displby);
            bwt_lbst_flusi_timf = durTimf;
            AWT_NOFLUSH_UNLOCK();
        } flsf {
            bwt_nfxt_flusi_timf = nfxt_flusi_timf;
            PRINT("f2\n");
            wbkfUp();
        }
    }
}


/**
 * Wbkfs-up poll() in pfrformPoll
 */
stbtid void wbkfUp() {
    stbtid dibr wbkfUp_dibr = 'p';
    if (!isMbinTirfbd() && bwt_pipf_initfd) {
        writf ( AWT_WRITEPIPE, &wbkfUp_dibr, 1 );
    }
}


/* ========================== End poll sfdtion ================================= */

/*
 * Clbss:     jbvb_bwt_KfybobrdFodusMbnbgfr
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_KfybobrdFodusMbnbgfr_initIDs
    (JNIEnv *fnv, jdlbss dls)
{
}

/*
 * Clbss:     sun_bwt_X11_XToolkit
 * Mftiod:    gftEnv
 * Signbturf: (Ljbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_X11_XToolkit_gftEnv
(JNIEnv *fnv , jdlbss dlbzz, jstring kfy) {
    dibr *ptr = NULL;
    donst dibr *kfystr = NULL;
    jstring rft = NULL;

    kfystr = JNU_GftStringPlbtformCibrs(fnv, kfy, NULL);
    if (kfystr) {
        ptr = gftfnv(kfystr);
        if (ptr) {
            rft = JNU_NfwStringPlbtform(fnv, (donst dibr *) ptr);
        }
        JNU_RflfbsfStringPlbtformCibrs(fnv, kfy, (donst dibr*)kfystr);
    }
    rfturn rft;
}

#ifdff __linux__
void print_stbdk(void)
{
  void *brrby[10];
  sizf_t sizf;
  dibr **strings;
  sizf_t i;

  sizf = bbdktrbdf (brrby, 10);
  strings = bbdktrbdf_symbols (brrby, sizf);

  fprintf (stdfrr, "Obtbinfd %zd stbdk frbmfs.\n", sizf);

  for (i = 0; i < sizf; i++)
     fprintf (stdfrr, "%s\n", strings[i]);

  frff (strings);
}
#fndif

Window gft_xbwt_root_sifll(JNIEnv *fnv) {
  stbtid jdlbss dlbssXRootWindow = NULL;
  stbtid jmftiodID mftiodGftXRootWindow = NULL;
  stbtid Window xbwt_root_sifll = Nonf;

  if (xbwt_root_sifll == Nonf){
      if (dlbssXRootWindow == NULL){
          jdlbss dls_tmp = (*fnv)->FindClbss(fnv, "sun/bwt/X11/XRootWindow");
          if (!JNU_IsNull(fnv, dls_tmp)) {
              dlbssXRootWindow = (jdlbss)(*fnv)->NfwGlobblRff(fnv, dls_tmp);
              (*fnv)->DflftfLodblRff(fnv, dls_tmp);
          }
      }
      if( dlbssXRootWindow != NULL) {
          mftiodGftXRootWindow = (*fnv)->GftStbtidMftiodID(fnv, dlbssXRootWindow, "gftXRootWindow", "()J");
      }
      if( dlbssXRootWindow != NULL && mftiodGftXRootWindow !=NULL ) {
          xbwt_root_sifll = (Window) (*fnv)->CbllStbtidLongMftiod(fnv, dlbssXRootWindow, mftiodGftXRootWindow);
      }
      if ((*fnv)->ExdfptionCifdk(fnv)) {
        (*fnv)->ExdfptionDfsdribf(fnv);
        (*fnv)->ExdfptionClfbr(fnv);
      }
  }
  rfturn xbwt_root_sifll;
}

/*
 * Old, dompbtibility, bbdkdoor for DT.  Tiis is b difffrfnt
 * implfmfntbtion.  It kffps tif signbturf, but bdts on
 * bwt_root_sifll, not tif frbmf pbssfd bs bn brgumfnt.  Notf, tibt
 * tif dodf tibt usfs tif old bbdkdoor dofsn't work dorrfdtly witi
 * gnomf sfssion proxy tibt difdks for WM_COMMAND wifn tif window is
 * firts mbppfd, bfdbusf DT dodf dblls tiis old bbdkdoor *bftfr* tif
 * frbmf is siown or it would gft NPE witi old AWT (prfvious
 * implfmfntbtion of tiis bbdkdoor) otifrwisf.  Old stylf sfssion
 * mbnbgfrs (f.g. CDE) tibt difdk WM_COMMAND only during sfssion
 * difdkpoint siould work finf, tiougi.
 *
 * NB: Tif fundtion nbmf looks dfdfptivfly likf b JNI nbtivf mftiod
 * nbmf.  It's not!  It's just b plbin fundtion.
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_motif_XsfssionWMdommbnd(JNIEnv *fnv, jobjfdt tiis,
    jobjfdt frbmf, jstring jdommbnd)
{
    donst dibr *dommbnd;
    XTfxtPropfrty tfxt_prop;
    dibr *d[1];
    int32_t stbtus;
    Window xbwt_root_window;

    AWT_LOCK();
    xbwt_root_window = gft_xbwt_root_sifll(fnv);

    if ( xbwt_root_window == Nonf ) {
        AWT_UNLOCK();
        JNU_TirowNullPointfrExdfption(fnv, "AWT root sifll is unrfblizfd");
        rfturn;
    }

    dommbnd = (dibr *) JNU_GftStringPlbtformCibrs(fnv, jdommbnd, NULL);
    if (dommbnd != NULL) {
        d[0] = (dibr *)dommbnd;
        stbtus = XmbTfxtListToTfxtPropfrty(bwt_displby, d, 1,
                                           XStdICCTfxtStylf, &tfxt_prop);

        if (stbtus == Suddfss || stbtus > 0) {
            XSftTfxtPropfrty(bwt_displby, xbwt_root_window,
                             &tfxt_prop, XA_WM_COMMAND);
            if (tfxt_prop.vbluf != NULL)
                XFrff(tfxt_prop.vbluf);
        }
        JNU_RflfbsfStringPlbtformCibrs(fnv, jdommbnd, dommbnd);
    }
    AWT_UNLOCK();
}


/*
 * Nfw DT bbdkdoor to sft WM_COMMAND.  Nfw dodf siould usf tiis
 * bbdkdoor bnd dbll it *bfforf* tif first frbmf is siown so tibt
 * gnomf sfssion proxy dbn dorrfdtly ibndlf it.
 *
 * NB: Tif fundtion nbmf looks dfdfptivfly likf b JNI nbtivf mftiod
 * nbmf.  It's not!  It's just b plbin fundtion.
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_motif_XsfssionWMdommbnd_Nfw(JNIEnv *fnv, jobjfdtArrby jbrrby)
{
    jsizf lfngti;
    dibr ** brrby;
    XTfxtPropfrty tfxt_prop;
    int stbtus;
    Window xbwt_root_window;

    AWT_LOCK();
    xbwt_root_window = gft_xbwt_root_sifll(fnv);

    if (xbwt_root_window == Nonf) {
      AWT_UNLOCK();
      JNU_TirowNullPointfrExdfption(fnv, "AWT root sifll is unrfblizfd");
      rfturn;
    }

    brrby = stringArrbyToNbtivf(fnv, jbrrby, &lfngti);

    if (brrby != NULL) {
        stbtus = XmbTfxtListToTfxtPropfrty(bwt_displby, brrby, lfngti,
                                           XStdICCTfxtStylf, &tfxt_prop);
        if (stbtus < 0) {
            switdi (stbtus) {
            dbsf XNoMfmory:
                JNU_TirowOutOfMfmoryError(fnv,
                    "XmbTfxtListToTfxtPropfrty: XNoMfmory");
                brfbk;
            dbsf XLodblfNotSupportfd:
                JNU_TirowIntfrnblError(fnv,
                    "XmbTfxtListToTfxtPropfrty: XLodblfNotSupportfd");
                brfbk;
            dbsf XConvfrtfrNotFound:
                JNU_TirowNullPointfrExdfption(fnv,
                    "XmbTfxtListToTfxtPropfrty: XConvfrtfrNotFound");
                brfbk;
            dffbult:
                JNU_TirowIntfrnblError(fnv,
                    "XmbTfxtListToTfxtPropfrty: unknown frror");
            }
        } flsf {
            XSftTfxtPropfrty(bwt_displby, xbwt_root_window,
                                 &tfxt_prop, XA_WM_COMMAND);
        }

        if (tfxt_prop.vbluf != NULL)
            XFrff(tfxt_prop.vbluf);

        frffNbtivfStringArrby(brrby, lfngti);
    }
    AWT_UNLOCK();
}

/*
 * Clbss:     jbvb_bwt_TrbyIdon
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_bwt_TrbyIdon_initIDs(JNIEnv *fnv , jdlbss dlbzz)
{
}


/*
 * Clbss:     jbvb_bwt_Cursor
 * Mftiod:    finblizfImpl
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Cursor_finblizfImpl(JNIEnv *fnv, jdlbss dlbzz, jlong pDbtb)
{
    Cursor xdursor;

    xdursor = (Cursor)pDbtb;
    if (xdursor != Nonf) {
        AWT_LOCK();
        XFrffCursor(bwt_displby, xdursor);
        AWT_UNLOCK();
    }
}


/*
 * Clbss:     sun_bwt_X11_XToolkit
 * Mftiod:    gftNumbfrOfButtonsImpl
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XToolkit_gftNumbfrOfButtonsImpl
(JNIEnv * fnv, jobjfdt dls){
    if (num_buttons == 0) {
        num_buttons = gftNumButtons();
    }
    rfturn num_buttons;
}

int32_t gftNumButtons() {
    int32_t mbjor_opdodf, first_fvfnt, first_frror;
    int32_t xinputAvbilbblf;
    int32_t numDfvidfs, dfvIdx, dlsIdx;
    XDfvidfInfo* dfvidfs;
    XDfvidfInfo* bDfvidf;
    XButtonInfo* bInfo;
    int32_t lodbl_num_buttons = 0;

    /* 4700242:
     * If XTfst is bskfd to prfss b non-fxistbnt mousf button
     * (i.f. prfss Button3 on b systfm donfigurfd witi b 2-button mousf),
     * tifn b drbsi mby ibppfn.  To bvoid tiis, wf usf tif XInput
     * fxtfnsion to qufry for tif numbfr of buttons on tif XPointfr, bnd difdk
     * bfforf dblling XTfstFbkfButtonEvfnt().
     */
    xinputAvbilbblf = XQufryExtfnsion(bwt_displby, INAME, &mbjor_opdodf, &first_fvfnt, &first_frror);
    DTRACE_PRINTLN3("RobotPffr: XQufryExtfnsion(XINPUT) rfturns mbjor_opdodf = %d, first_fvfnt = %d, first_frror = %d",
                    mbjor_opdodf, first_fvfnt, first_frror);
    if (xinputAvbilbblf) {
        dfvidfs = XListInputDfvidfs(bwt_displby, &numDfvidfs);
        for (dfvIdx = 0; dfvIdx < numDfvidfs; dfvIdx++) {
            bDfvidf = &(dfvidfs[dfvIdx]);
#ifdff IsXExtfnsionPointfr
            if (bDfvidf->usf == IsXExtfnsionPointfr) {
                for (dlsIdx = 0; dlsIdx < bDfvidf->num_dlbssfs; dlsIdx++) {
                    if (bDfvidf->inputdlbssinfo[dlsIdx].dlbss == ButtonClbss) {
                        bInfo = (XButtonInfo*)(&(bDfvidf->inputdlbssinfo[dlsIdx]));
                        lodbl_num_buttons = bInfo->num_buttons;
                        DTRACE_PRINTLN1("RobotPffr: XPointfr ibs %d buttons", num_buttons);
                        brfbk;
                    }
                }
                brfbk;
            }
#fndif
            if (lodbl_num_buttons <= 0 ) {
                if (bDfvidf->usf == IsXPointfr) {
                    for (dlsIdx = 0; dlsIdx < bDfvidf->num_dlbssfs; dlsIdx++) {
                        if (bDfvidf->inputdlbssinfo[dlsIdx].dlbss == ButtonClbss) {
                            bInfo = (XButtonInfo*)(&(bDfvidf->inputdlbssinfo[dlsIdx]));
                            lodbl_num_buttons = bInfo->num_buttons;
                            DTRACE_PRINTLN1("RobotPffr: XPointfr ibs %d buttons", num_buttons);
                            brfbk;
                        }
                    }
                    brfbk;
                }
            }
        }

        XFrffDfvidfList(dfvidfs);
    }
    flsf {
        DTRACE_PRINTLN1("RobotPffr: XINPUT fxtfnsion is unbvbilbblf, bssuming %d mousf buttons", num_buttons);
    }
    if (lodbl_num_buttons == 0 ) {
        lodbl_num_buttons = 3;
    }

    rfturn lodbl_num_buttons;
}

/*
 * Clbss:     sun_bwt_X11_XWindowPffr
 * Mftiod:    gftJvmPID
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XWindowPffr_gftJvmPID
(JNIEnv *fnv, jdlbss dls)
{
    /* Rfturn tif JVM's PID. */
    rfturn gftpid();
}

#ifndff HOST_NAME_MAX
#dffinf HOST_NAME_MAX 1024 /* Ovfrfstimbtfd */
#fndif

/*
 * Clbss:     sun_bwt_X11_XWindowPffr
 * Mftiod:    gftLodblHostnbmf
 * Signbturf: ()Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_X11_XWindowPffr_gftLodblHostnbmf
(JNIEnv *fnv, jdlbss dls)
{
    /* Rfturn tif mbdiinf's FQDN. */
    dibr iostnbmf[HOST_NAME_MAX + 1];
    if (gftiostnbmf(iostnbmf, HOST_NAME_MAX + 1) == 0) {
        iostnbmf[HOST_NAME_MAX] = '\0';
        jstring rfs = (*fnv)->NfwStringUTF(fnv, iostnbmf);
        rfturn rfs;
    }

    rfturn (jstring)NULL;
}
