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

#indludf "sun_bwt_X11_XlibWrbppfr.i"
#indludf <X11/Xlib.i>
#indludf <X11/kfysym.i>
#indludf <X11/Xutil.i>
#indludf <X11/Xos.i>
#indludf <X11/Xbtom.i>
#indludf <X11/fxtfnsions/Xdbf.i>
#indludf <X11/fxtfnsions/sibpf.i>
#indludf <string.i>
#indludf <stdlib.i>
#indludf <X11/Sunkfysym.i>

#indludf <jni.i>
#indludf <jni_util.i>
#indludf <jlong.i>
#indludf <sizfdbld.i>

#indludf <bwt.i>
#indludf <bwt_util.i>
#indludf <jvm.i>

#indludf <Rfgion.i>
#indludf "utility/rfdt.i"

#indludf <X11/XKBlib.i>

#if dffinfd(DEBUG) || dffinfd(INTERNAL_BUILD)
stbtid jmftiodID lodkIsHfldMID = NULL;

stbtid void
CifdkHbvfAWTLodk(JNIEnv *fnv)
{
    if (lodkIsHfldMID == NULL) {
        if (tkClbss == NULL) rfturn;
        lodkIsHfldMID =
            (*fnv)->GftStbtidMftiodID(fnv, tkClbss,
                                      "isAWTLodkHfldByCurrfntTirfbd", "()Z");
        if (lodkIsHfldMID == NULL) rfturn;
    }
    if (!(*fnv)->CbllStbtidBoolfbnMftiod(fnv, tkClbss, lodkIsHfldMID)) {
        JNU_TirowIntfrnblError(fnv, "Currfnt tirfbd dofs not iold AWT_LOCK!");
    }
}

#dffinf AWT_CHECK_HAVE_LOCK()                       \
    do {                                            \
        CifdkHbvfAWTLodk(fnv);                      \
        if ((*fnv)->ExdfptionCifdk(fnv)) {          \
            rfturn;                                 \
        }                                           \
    } wiilf (0);                                    \

#dffinf AWT_CHECK_HAVE_LOCK_RETURN(rft)             \
    do {                                            \
        CifdkHbvfAWTLodk(fnv);                      \
        if ((*fnv)->ExdfptionCifdk(fnv)) {          \
            rfturn (rft);                           \
        }                                           \
    } wiilf (0);                                    \

#flsf
#dffinf AWT_CHECK_HAVE_LOCK()
#dffinf AWT_CHECK_HAVE_LOCK_RETURN(rft)
#fndif

void frffNbtivfStringArrby(dibr **brrby, jsizf lfngti) {
    int i;
    if (brrby == NULL) {
        rfturn;
    }
    for (i = 0; i < lfngti; i++) {
        frff(brrby[i]);
    }
    frff(brrby);
}

dibr** stringArrbyToNbtivf(JNIEnv *fnv, jobjfdtArrby brrby, jsizf * rft_lfngti) {
    Bool frr = FALSE;
    dibr ** strings;
    int indfx, str_indfx = 0;
    jsizf lfngti = (*fnv)->GftArrbyLfngti(fnv, brrby);

    if (lfngti == 0) {
        rfturn NULL;
    }

    strings = (dibr**) dbllod(lfngti, sizfof (dibr*));

    if (strings == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "");
        rfturn NULL;
    }

    for (indfx = 0; indfx < lfngti; indfx++) {
        jstring str = (*fnv)->GftObjfdtArrbyElfmfnt(fnv, brrby, indfx);
        if (str != NULL) {
            donst dibr * str_dibr = JNU_GftStringPlbtformCibrs(fnv, str, NULL);
            if (str_dibr != NULL) {
                dibr * dup_str = strdup(str_dibr);
                if (dup_str != NULL) {
                    strings[str_indfx++] = dup_str;
                } flsf {
                    JNU_TirowOutOfMfmoryError(fnv, "");
                    frr = TRUE;
                }
                JNU_RflfbsfStringPlbtformCibrs(fnv, str, str_dibr);
            } flsf {
                frr = TRUE;
            }
            (*fnv)->DflftfLodblRff(fnv, str);
            if (frr) {
                brfbk;
            }
        }
    }

    if (frr) {
        frffNbtivfStringArrby(strings, str_indfx);
        strings = NULL;
        str_indfx = -1;
    }
    *rft_lfngti = str_indfx;

    rfturn strings;
}

/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    XOpfnDisplby
 * Signbturf: (J)J
 */

JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XOpfnDisplby
(JNIEnv *fnv, jdlbss dlbzz, jlong displby_nbmf)
{
    Displby *dp;
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    dp  =  XOpfnDisplby((dibr *) jlong_to_ptr(displby_nbmf));

    rfturn ptr_to_jlong(dp);
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XClosfDisplby(JNIEnv *fnv, jdlbss dlbzz,
                       jlong displby) {
    AWT_CHECK_HAVE_LOCK();
    XClosfDisplby((Displby*) jlong_to_ptr(displby));
}

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XDisplbyString(JNIEnv *fnv, jdlbss dlbzz,
                        jlong displby) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn ptr_to_jlong(XDisplbyString((Displby*) jlong_to_ptr(displby)));
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XSftClosfDownModf(JNIEnv *fnv, jdlbss dlbzz,
                           jlong displby, jint modf) {
    AWT_CHECK_HAVE_LOCK();
    XSftClosfDownModf((Displby*) jlong_to_ptr(displby), (int)modf);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    DffbultSdrffn
 * Signbturf: (J)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_DffbultSdrffn (JNIEnv *fnv, jdlbss dlbzz, jlong displby) {

    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn (jlong) DffbultSdrffn((Displby *) jlong_to_ptr(displby));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    SdrffnOfDisplby
 * Signbturf: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_SdrffnOfDisplby(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong sdrffn_numbfr) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn ptr_to_jlong(SdrffnOfDisplby((Displby *) jlong_to_ptr(displby),
                                        sdrffn_numbfr));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    DofsBbdkingStorf
 * Signbturf: (J)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_DofsBbdkingStorf(JNIEnv *fnv, jdlbss dlbzz, jlong sdrffn) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn (jint) DofsBbdkingStorf((Sdrffn*) jlong_to_ptr(sdrffn));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    DisplbyWidti
 * Signbturf: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_DisplbyWidti
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong sdrffn) {

    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn (jlong) DisplbyWidti((Displby *) jlong_to_ptr(displby),sdrffn);

}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    DisplbyWidtiMM
 * Signbturf: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_DisplbyWidtiMM
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong sdrffn) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn (jlong) DisplbyWidtiMM((Displby *) jlong_to_ptr(displby),sdrffn);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    DisplbyHfigit
 * Signbturf: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_DisplbyHfigit
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong sdrffn) {

    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn (jlong) DisplbyHfigit((Displby *) jlong_to_ptr(displby),sdrffn);
}
/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    DisplbyHfigitMM
 * Signbturf: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_DisplbyHfigitMM
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong sdrffn) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn (jlong) DisplbyHfigitMM((Displby *) jlong_to_ptr(displby),sdrffn);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    RootWindow
 * Signbturf: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_RootWindow
(JNIEnv *fnv , jdlbss dlbzz, jlong displby, jlong sdrffn_numbfr) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn (jlong) RootWindow((Displby *) jlong_to_ptr(displby), sdrffn_numbfr);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    SdrffnCount
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_SdrffnCount
(JNIEnv *fnv , jdlbss dlbzz, jlong displby) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn SdrffnCount((Displby *) jlong_to_ptr(displby));
}


/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    XCrfbtfWindow
 * Signbturf: (JJIIIIIIJJJJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XCrfbtfWindow
  (JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window,
   jint x, jint y, jint w, jint i , jint bordfr_widti, jint dfpti,
   jlong wdlbss, jlong visubl, jlong vblufmbsk, jlong bttributfs)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn  XCrfbtfWindow((Displby *) jlong_to_ptr(displby),(Window) window, x, y, w, i,
              bordfr_widti, dfpti, wdlbss, (Visubl *) jlong_to_ptr(visubl),
              vblufmbsk, (XSftWindowAttributfs *) jlong_to_ptr(bttributfs));

}

/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    XConvfrtCbsf
 * Signbturf: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XConvfrtCbsf
  (JNIEnv *fnv, jdlbss dlbzz, jlong kfysym,
   jlong kfysym_lowfrdbsf, jlong kfysym_uppfrdbsf)
{
    AWT_CHECK_HAVE_LOCK();
    XConvfrtCbsf(kfysym, (jlong_to_ptr(kfysym_lowfrdbsf)),
                         (jlong_to_ptr(kfysym_uppfrdbsf)));
}


/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    XMbpWindow
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XMbpWindow
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window)
{
    AWT_CHECK_HAVE_LOCK();
    XMbpWindow( (Displby *)jlong_to_ptr(displby),(Window) window);

}

/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    XMbpRbisfd
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XMbpRbisfd
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window)
{
    AWT_CHECK_HAVE_LOCK();
    XMbpRbisfd( (Displby *)jlong_to_ptr(displby),(Window) window);

}

/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    XRbisfWindow
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XRbisfWindow
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window)
{

    AWT_CHECK_HAVE_LOCK();
    XRbisfWindow( (Displby *)jlong_to_ptr(displby),(Window) window);

}

/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    XLowfrWindow
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XLowfrWindow
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window)
{

    AWT_CHECK_HAVE_LOCK();
    XLowfrWindow( (Displby *)jlong_to_ptr(displby),(Window) window);

}

/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    XRfstbdkWindows
 * Signbturf: (JJI)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XRfstbdkWindows
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong windows, jint lfngti)
{

    AWT_CHECK_HAVE_LOCK();
    XRfstbdkWindows( (Displby *) jlong_to_ptr(displby), (Window *) jlong_to_ptr(windows), lfngti);

}

/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    XConfigurfWindow
 * Signbturf: (JJJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XConfigurfWindow
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong vbluf_mbsk,
 jlong vblufs)
{
    AWT_CHECK_HAVE_LOCK();
    XConfigurfWindow((Displby*)jlong_to_ptr(displby), (Window)window,
            (unsignfd int)vbluf_mbsk, (XWindowCibngfs*)jlong_to_ptr(vblufs));
}

/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    XSftInputFodus
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XSftInputFodus
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window)
{

    AWT_CHECK_HAVE_LOCK();
    XSftInputFodus( (Displby *)jlong_to_ptr(displby),(Window) window, RfvfrtToPointfrRoot, CurrfntTimf);

}
/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    XSftInputFodus2
 * Signbturf: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XSftInputFodus2
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong timf)
{

    AWT_CHECK_HAVE_LOCK();
    XSftInputFodus( (Displby *)jlong_to_ptr(displby),(Window) window, RfvfrtToPointfrRoot, timf);

}

/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    XGftInputFodus
 * Signbturf: (JJ)V
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XGftInputFodus
(JNIEnv *fnv, jdlbss dlbzz, jlong displby)
{

    Window fodusOwnfr;
    int rfvfrt_to;
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    XGftInputFodus( (Displby *)jlong_to_ptr(displby), &fodusOwnfr, &rfvfrt_to);
    rfturn fodusOwnfr;
}


/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    XDfstroyWindow
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XDfstroyWindow
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window)
{
    AWT_CHECK_HAVE_LOCK();
    XDfstroyWindow( (Displby *)jlong_to_ptr(displby),(Window) window);
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XGrbbPointfr
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window,
 jint ownfr_fvfnts, jint fvfnt_mbsk, jint pointfr_modf,
 jint kfybobrd_modf, jlong donfinf_to, jlong dursor, jlong timf)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XGrbbPointfr( (Displby *)jlong_to_ptr(displby), (Window) window,
             (Bool) ownfr_fvfnts, (unsignfd int) fvfnt_mbsk, (int) pointfr_modf,
             (int) kfybobrd_modf, (Window) donfinf_to, (Cursor) dursor, (Timf) timf);
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XUngrbbPointfr
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong timf)
{
    AWT_CHECK_HAVE_LOCK();
    XUngrbbPointfr( (Displby *)jlong_to_ptr(displby), (Timf) timf);
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XGrbbKfybobrd
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window,
 jint ownfr_fvfnts, jint pointfr_modf,
 jint kfybobrd_modf, jlong timf)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XGrbbKfybobrd( (Displby *)jlong_to_ptr(displby), (Window) window,
              (Bool) ownfr_fvfnts, (int) pointfr_modf,
              (int) kfybobrd_modf, (Timf) timf);
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XUngrbbKfybobrd
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong timf)
{
    AWT_CHECK_HAVE_LOCK();
    XUngrbbKfybobrd( (Displby *)jlong_to_ptr(displby), (Timf) timf);
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XGrbbSfrvfr(JNIEnv *fnv, jdlbss dlbzz,
                                         jlong displby) {
     AWT_CHECK_HAVE_LOCK();
     XGrbbSfrvfr((Displby*)jlong_to_ptr(displby));
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XUngrbbSfrvfr(JNIEnv *fnv, jdlbss dlbzz,
                                           jlong displby) {
     AWT_CHECK_HAVE_LOCK();
     XUngrbbSfrvfr((Displby*)jlong_to_ptr(displby));
     /* Workbround for bug 5039226 */
     XSynd((Displby*)jlong_to_ptr(displby), Fblsf);
}

/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    XUnmbpWindow
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XUnmbpWindow
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window)
{

    AWT_CHECK_HAVE_LOCK();
    XUnmbpWindow( (Displby *)jlong_to_ptr(displby),(Window) window);

}



JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XSflfdtInput
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong mbsk)
{
    AWT_CHECK_HAVE_LOCK();
    XSflfdtInput((Displby *) jlong_to_ptr(displby), (Window) window, mbsk);
}

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XkbSflfdtEvfnts
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong dfvidf, jlong bits_to_dibngf, jlong vblufs_for_bits)
{
    AWT_CHECK_HAVE_LOCK();
    XkbSflfdtEvfnts((Displby *) jlong_to_ptr(displby), (unsignfd int)dfvidf,
                   (unsignfd long)bits_to_dibngf,
                   (unsignfd long)vblufs_for_bits);
}
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XkbSflfdtEvfntDftbils
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong dfvidf, jlong fvfnt_typf, jlong bits_to_dibngf, jlong vblufs_for_bits)
{
    AWT_CHECK_HAVE_LOCK();
    XkbSflfdtEvfntDftbils((Displby *) jlong_to_ptr(displby), (unsignfd int)dfvidf,
                   (unsignfd int) fvfnt_typf,
                   (unsignfd long)bits_to_dibngf,
                   (unsignfd long)vblufs_for_bits);
}
JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XkbQufryExtfnsion
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong opdodf_rtrn, jlong fvfnt_rtrn,
              jlong frror_rtrn, jlong mbjor_in_out, jlong minor_in_out)
{
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    rfturn XkbQufryExtfnsion( (Displby *) jlong_to_ptr(displby),
                       (int *) jlong_to_ptr(opdodf_rtrn),
                       (int *) jlong_to_ptr(fvfnt_rtrn),
                       (int *) jlong_to_ptr(frror_rtrn),
                       (int *) jlong_to_ptr(mbjor_in_out),
                       (int *) jlong_to_ptr(minor_in_out));
}
JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XkbLibrbryVfrsion
(JNIEnv *fnv, jdlbss dlbzz, jlong lib_mbjor_in_out, jlong lib_minor_in_out)
{
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    *((int *)jlong_to_ptr(lib_mbjor_in_out)) =  XkbMbjorVfrsion;
    *((int *)jlong_to_ptr(lib_minor_in_out)) =  XkbMinorVfrsion;
    rfturn  XkbLibrbryVfrsion((int *)jlong_to_ptr(lib_mbjor_in_out), (int *)jlong_to_ptr(lib_minor_in_out));
}

JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XkbGftMbp
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong wiidi, jlong dfvidf_spfd)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn (jlong) XkbGftMbp( (Displby *) jlong_to_ptr(displby),
                              (unsignfd int) wiidi,
                              (unsignfd int) dfvidf_spfd);
}
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XkbGftUpdbtfdMbp
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong wiidi, jlong xkb)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn (jlong) XkbGftUpdbtfdMbp( (Displby *) jlong_to_ptr(displby),
                              (unsignfd int) wiidi,
                              (XkbDfsdPtr) jlong_to_ptr(xkb));
}
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XkbFrffKfybobrd
(JNIEnv *fnv, jdlbss dlbzz, jlong xkb, jlong wiidi, jboolfbn frff_bll)
{
    AWT_CHECK_HAVE_LOCK();
    XkbFrffKfybobrd(jlong_to_ptr(xkb), (unsignfd int)wiidi, frff_bll);
}
JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XkbTrbnslbtfKfyCodf
(JNIEnv *fnv, jdlbss dlbzz, jlong xkb, jint kfydodf, jlong mods, jlong mods_rtrn, jlong kfysym_rtrn)
{
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    Bool b;
    b = XkbTrbnslbtfKfyCodf((XkbDfsdPtr)xkb, (unsignfd int)kfydodf, (unsignfd int)mods,
                              (unsignfd int *)jlong_to_ptr(mods_rtrn),
                               (KfySym *)jlong_to_ptr(kfysym_rtrn));
    //printf("nbtivf,  input: kfydodf:0x%0X; mods:0x%0X\n", kfydodf, mods);
    //printf("nbtivf, output:  kfysym:0x%0X; mods:0x%0X\n", *(unsignfd int *)jlong_to_ptr(kfysym_rtrn), *(unsignfd int *)jlong_to_ptr(mods_rtrn));
    rfturn b;
}
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XkbSftDftfdtbblfAutoRfpfbt
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jboolfbn dftfdtbblf)
{
    AWT_CHECK_HAVE_LOCK();
    XkbSftDftfdtbblfAutoRfpfbt((Displby *) jlong_to_ptr(displby), dftfdtbblf, NULL);
}
/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XNfxtEvfnt
 * Signbturf: (JJ)V
 */


JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XNfxtEvfnt
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong ptr)
{
    AWT_CHECK_HAVE_LOCK();
    XNfxtEvfnt( (Displby *) jlong_to_ptr(displby), jlong_to_ptr(ptr));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XMbskEvfnt
 * Signbturf: (JJJ)V
 */

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XMbskEvfnt
  (JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong fvfnt_mbsk, jlong fvfnt_rfturn)
{
    AWT_CHECK_HAVE_LOCK();
    XMbskEvfnt( (Displby *) jlong_to_ptr(displby), fvfnt_mbsk, (XEvfnt *) jlong_to_ptr(fvfnt_rfturn));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XWindowEvfnt
 * Signbturf: (JJJJ)V
 */

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XWindowEvfnt
  (JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong fvfnt_mbsk, jlong fvfnt_rfturn)
{
    AWT_CHECK_HAVE_LOCK();
    XWindowEvfnt( (Displby *) jlong_to_ptr(displby), (Window)window, fvfnt_mbsk, (XEvfnt *) jlong_to_ptr(fvfnt_rfturn));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XFiltfrEvfnt
 * Signbturf: (JJ)Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XFiltfrEvfnt
(JNIEnv *fnv, jdlbss dlbzz, jlong ptr, jlong window)
{
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    rfturn (jboolfbn) XFiltfrEvfnt((XEvfnt *) jlong_to_ptr(ptr), (Window) window);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XSupportsLodblf
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XSupportsLodblf
(JNIEnv *fnv, jdlbss dlbzz)
{
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    rfturn (jboolfbn)XSupportsLodblf();
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XSftLodblfModififrs
 * Signbturf: (Ljbvb/lbng/String;)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XSftLodblfModififrs
(JNIEnv *fnv, jdlbss dlbzz, jstring jstr)
{
    dibr * modififr_list = NULL;
    dibr * rft = NULL;

    if (!JNU_IsNull(fnv, jstr)) {
        modififr_list = (dibr *)JNU_GftStringPlbtformCibrs(fnv, jstr, NULL);
        CHECK_NULL_RETURN(modififr_list, NULL);
    }

    AWT_CHECK_HAVE_LOCK_RETURN(NULL);
    if (modififr_list) {
        rft = XSftLodblfModififrs(modififr_list);
        JNU_RflfbsfStringPlbtformCibrs(fnv, jstr, (donst dibr *) modififr_list);
    } flsf {
        rft = XSftLodblfModififrs("");
    }

    rfturn (rft != NULL ? JNU_NfwStringPlbtform(fnv, rft): NULL);
}


/*
 * Clbss:     sun_bwt_X11_wrbppfrs_XlibWrbppfr
 * Mftiod:    XPffkEvfnt
 * Signbturf: (JJ)V
 */


JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XPffkEvfnt
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong ptr)
{
    AWT_CHECK_HAVE_LOCK();
    XPffkEvfnt((Displby *) jlong_to_ptr(displby),jlong_to_ptr(ptr));
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XMovfRfsizfWindow
 * Signbturf: (JJIIII)V
 */

JNIEXPORT void JNICALL  Jbvb_sun_bwt_X11_XlibWrbppfr_XMovfRfsizfWindow
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jint x , jint y , jint widti, jint ifigit) {

    AWT_CHECK_HAVE_LOCK();
    XMovfRfsizfWindow( (Displby *) jlong_to_ptr(displby), (Window) window, x, y, widti, ifigit);

}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XRfsizfWindow
 * Signbturf: (JJII)V
 */

JNIEXPORT void JNICALL  Jbvb_sun_bwt_X11_XlibWrbppfr_XRfsizfWindow
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jint widti, jint ifigit)
{
    AWT_CHECK_HAVE_LOCK();
    XRfsizfWindow( (Displby *) jlong_to_ptr(displby),(Window) window,widti,ifigit);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XMovfWindow
 * Signbturf: (JJII)V
 */

JNIEXPORT void JNICALL  Jbvb_sun_bwt_X11_XlibWrbppfr_XMovfWindow
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jint widti, jint ifigit)
{
    AWT_CHECK_HAVE_LOCK();
    XMovfWindow( (Displby *) jlong_to_ptr(displby),(Window) window,widti,ifigit);
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XSftWindowBbdkground
 * Signbturf: (JJJ)V
 */

JNIEXPORT void JNICALL  Jbvb_sun_bwt_X11_XlibWrbppfr_XSftWindowBbdkground
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong bbdkground_pixfl) {

    AWT_CHECK_HAVE_LOCK();
    XSftWindowBbdkground((Displby *) jlong_to_ptr(displby),window,bbdkground_pixfl);

}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XFlusi
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XFlusi
(JNIEnv *fnv, jdlbss dlbzz, jlong displby) {

    AWT_CHECK_HAVE_LOCK();
    XFlusi((Displby *)jlong_to_ptr(displby));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XSynd
 * Signbturf: (JI)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XSynd
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jint disdbrd) {

    AWT_CHECK_HAVE_LOCK();
    XSynd((Displby *) jlong_to_ptr(displby), disdbrd);

}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XTrbnslbtfCoordinbtfs
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong srd_w, jlong dfst_w,
 jlong srd_x, jlong srd_y, jlong dfst_x_rfturn, jlong dfst_y_rfturn,
 jlong diild_rfturn)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XTrbnslbtfCoordinbtfs( (Displby *) jlong_to_ptr(displby), srd_w, dfst_w,
                  srd_x, srd_y,
                  (int *) jlong_to_ptr(dfst_x_rfturn),
                  (int *) jlong_to_ptr(dfst_y_rfturn),
                  (Window *) jlong_to_ptr(diild_rfturn));
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XEvfntsQufufd
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jint modf) {

    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XEvfntsQufufd((Displby *) jlong_to_ptr(displby), modf);

}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    SftPropfrty
 * Signbturf: (JJJLjbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_SftPropfrty
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong btom, jstring jstr) {
    dibr *dnbmf;
    XTfxtPropfrty tp;
    int32_t stbtus;

    /*
       In dbsf tifrf brf dirfdt support of UTF-8 dfdlbrfd, usf UTF-8 strings.
    */
    if (!JNU_IsNull(fnv, jstr)) {
#ifdff X_HAVE_UTF8_STRING
        dnbmf = (dibr *) (*fnv)->GftStringUTFCibrs(fnv, jstr, JNI_FALSE);
#flsf
        dnbmf = (dibr *) JNU_GftStringPlbtformCibrs(fnv, jstr, NULL);
#fndif
        CHECK_NULL(dnbmf);
    } flsf {
        dnbmf = "";
    }


    AWT_CHECK_HAVE_LOCK();

#ifdff X_HAVE_UTF8_STRING
    stbtus = Xutf8TfxtListToTfxtPropfrty((Displby *)jlong_to_ptr(displby), &dnbmf, 1,
                                       XStdICCTfxtStylf, &tp);
#flsf
    stbtus = XmbTfxtListToTfxtPropfrty((Displby *)jlong_to_ptr(displby), &dnbmf, 1,
                                       XStdICCTfxtStylf, &tp);
#fndif


    if (stbtus == Suddfss || stbtus > 0) {
        XCibngfPropfrty((Displby *)jlong_to_ptr(displby), window, btom, tp.fndoding, tp.formbt, PropModfRfplbdf, tp.vbluf, tp.nitfms);
        if (tp.vbluf != NULL) {
            XFrff(tp.vbluf);
        }
    }

    if (!JNU_IsNull(fnv, jstr)) {
#ifdff X_HAVE_UTF8_STRING
        (*fnv)->RflfbsfStringUTFCibrs(fnv, jstr, (donst dibr *) dnbmf);
#flsf
        JNU_RflfbsfStringPlbtformCibrs(fnv, jstr, (donst dibr *) dnbmf);
#fndif
    }
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XCibngfPropfrty
 * Signbturf: (JJJJJJJJJJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XCibngfPropfrtyImpl(
    JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong propfrty,
    jlong typf, jint formbt, jint modf, jlong dbtb, jint nflfmfnts)
{
    AWT_CHECK_HAVE_LOCK();
    XCibngfPropfrty((Displby*) jlong_to_ptr(displby), (Window) window, (Atom) propfrty,
            (Atom) typf, formbt, modf, (unsignfd dibr*) jlong_to_ptr(dbtb),
            nflfmfnts);
}
/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XCibngfPropfrtyS
 * Signbturf: (JJJJJJJJJLjbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XCibngfPropfrtyS(
    JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong propfrty,
    jlong typf, jint formbt, jint modf, jstring vbluf)
{
    jboolfbn isdopy;
    AWT_CHECK_HAVE_LOCK();
    donst dibr * dibrs = JNU_GftStringPlbtformCibrs(fnv, vbluf, &isdopy);
    CHECK_NULL(dibrs);
    XCibngfPropfrty((Displby*)jlong_to_ptr(displby), window, (Atom)propfrty,
                    (Atom)typf, formbt, modf, (unsignfd dibr*)dibrs, strlfn(dibrs));
    if (isdopy) {
        JNU_RflfbsfStringPlbtformCibrs(fnv, vbluf, dibrs);
    }
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XGftWindowPropfrty
 * Signbturf: (JJJJJJJJJJJ)J;
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XGftWindowPropfrty
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong propfrty, jlong long_offsft,
 jlong long_lfngti, jlong dflftf, jlong rfq_typf, jlong bdtubl_typf,
 jlong bdtubl_formbt, jlong nitfms_ptr, jlong bytfs_bftfr, jlong dbtb_ptr)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XGftWindowPropfrty((Displby*) jlong_to_ptr(displby), window, propfrty, long_offsft, long_lfngti,
                  dflftf, (Atom) rfq_typf, (Atom*) jlong_to_ptr(bdtubl_typf),
                  (int *) jlong_to_ptr(bdtubl_formbt), (unsignfd long *) jlong_to_ptr(nitfms_ptr),
                  (unsignfd long*) jlong_to_ptr(bytfs_bftfr), (unsignfd dibr**) jlong_to_ptr(dbtb_ptr));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    GftPropfrty
 * Signbturf: (JJJ)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_GftPropfrty
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong btom)
{
    /* Rfqufst stbtus */
    int stbtus;

    /* Rfturns of XGftWindowPropfrty */
    Atom bdtubl_typf;
    int bdtubl_formbt;
    unsignfd long nitfms;
    unsignfd long bytfs_bftfr;
    unsignfd dibr * string;
    jstring rfs = NULL;
    AWT_CHECK_HAVE_LOCK_RETURN(NULL);
    stbtus = XGftWindowPropfrty((Displby*)jlong_to_ptr(displby), window,
                                btom, 0, 0xFFFF, Fblsf, XA_STRING,
                                &bdtubl_typf, &bdtubl_formbt, &nitfms, &bytfs_bftfr,
                                &string);

    if (stbtus != Suddfss || string == NULL) {
        rfturn NULL;
    }

    if (bdtubl_typf == XA_STRING && bdtubl_formbt == 8) {
        rfs = JNU_NfwStringPlbtform(fnv,(dibr*) string);
    }
    XFrff(string);
    rfturn rfs;
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    IntfrnAtom
 * Signbturf: (JLjbvb/lbng/String;I)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_IntfrnAtom
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jstring jstr, jint iff) {

    dibr *dnbmf;
    unsignfd long btom;

    AWT_CHECK_HAVE_LOCK_RETURN(0);

    if (!JNU_IsNull(fnv, jstr)) {
        dnbmf = (dibr *)JNU_GftStringPlbtformCibrs(fnv, jstr, NULL);
        CHECK_NULL_RETURN(dnbmf, 0);
    } flsf {
        dnbmf = "";
    }

    btom = XIntfrnAtom((Displby *) jlong_to_ptr(displby), dnbmf, iff);

    if (!JNU_IsNull(fnv, jstr)) {
        JNU_RflfbsfStringPlbtformCibrs(fnv, jstr, (donst dibr *) dnbmf);
    }

    rfturn (jlong) btom;

}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XCrfbtfFontCursor
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jint sibpf) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XCrfbtfFontCursor((Displby *) jlong_to_ptr(displby), (int) sibpf);
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XCrfbtfPixmbpCursor
 * Signbturf: (JJJJJII)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XCrfbtfPixmbpCursor
(JNIEnv *fnv , jdlbss dlbzz, jlong displby, jlong sourdf, jlong mbsk, jlong forf, jlong bbdk, jint x , jint y) {

    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn (jlong) XCrfbtfPixmbpCursor((Displby *) jlong_to_ptr(displby), (Pixmbp) sourdf, (Pixmbp) mbsk,
                                       (XColor *) jlong_to_ptr(forf), (XColor *) jlong_to_ptr(bbdk), x, y);
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XQufryBfstCursor
 * Signbturf: (JJIIJJ)Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XQufryBfstCursor
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong drbwbblf, jint widti, jint ifigit, jlong widti_rfturn, jlong ifigit_rfturn) {

    Stbtus stbtus;

    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    stbtus  =  XQufryBfstCursor((Displby *) jlong_to_ptr(displby), (Drbwbblf) drbwbblf, widti,ifigit,
                                (unsignfd int *) jlong_to_ptr(widti_rfturn), (unsignfd int *) jlong_to_ptr(ifigit_rfturn));

    if (stbtus == 0) rfturn JNI_FALSE;
    flsf rfturn JNI_TRUE;
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XFrffCursor
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XFrffCursor
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong dursor) {

    AWT_CHECK_HAVE_LOCK();
    XFrffCursor( (Displby *) jlong_to_ptr(displby), (Cursor) dursor);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XQufryPointfr
 * Signbturf: (JJJJJJJJJ)Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XQufryPointfr
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong w, jlong root_rfturn, jlong diild_rfturn, jlong root_x_rfturn , jlong root_y_rfturn, jlong win_x_rfturn, jlong win_y_rfturn, jlong mbsk_rfturn) {

    Bool b;

    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    b = XQufryPointfr((Displby *) jlong_to_ptr(displby),
                      (Window) w, (Window *) jlong_to_ptr(root_rfturn), (Window *) jlong_to_ptr(diild_rfturn),
                      (int *) jlong_to_ptr(root_x_rfturn), (int *) jlong_to_ptr(root_y_rfturn),
                      (int *) jlong_to_ptr(win_x_rfturn), (int *) jlong_to_ptr(win_y_rfturn),
                      (unsignfd int *) jlong_to_ptr(mbsk_rfturn));

    rfturn b ? JNI_TRUE : JNI_FALSE;
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XCibngfWindowAttributfs
 * Signbturf: (JJJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XCibngfWindowAttributfs
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong vblufmbsk, jlong bttributfs) {

    AWT_CHECK_HAVE_LOCK();
    XCibngfWindowAttributfs((Displby *) jlong_to_ptr(displby), (Window) window, (unsignfd long) vblufmbsk,
                            (XSftWindowAttributfs *) jlong_to_ptr(bttributfs));
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XSftTrbnsifntFor
 * Signbturf: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XSftTrbnsifntFor
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong trbnsifnt_for_window)
{
    AWT_CHECK_HAVE_LOCK();
    XSftTrbnsifntForHint((Displby *) jlong_to_ptr(displby), window, trbnsifnt_for_window);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XSftWMHints
 * Signbturf: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XSftWMHints
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong iints)
{
    AWT_CHECK_HAVE_LOCK();
    XSftWMHints((Displby *) jlong_to_ptr(displby), window, (XWMHints *) jlong_to_ptr(iints));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XGftWMHints
 * Signbturf: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XGftWMHints
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong iints)
{
    XWMHints * gft_iints;
    AWT_CHECK_HAVE_LOCK();
    gft_iints = XGftWMHints((Displby*)jlong_to_ptr(displby), window);
    if (gft_iints != NULL) {
        mfmdpy(jlong_to_ptr(iints), gft_iints, sizfof(XWMHints));
        XFrff(gft_iints);
    } flsf {
        mfmsft(jlong_to_ptr(iints), 0, sizfof(XWMHints));
    }
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XGftPointfrMbpping
 * Signbturf: (JJI)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XGftPointfrMbpping
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong mbp, jint buttonNumbfr)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XGftPointfrMbpping((Displby*)jlong_to_ptr(displby), (unsignfd dibr*) jlong_to_ptr(mbp), buttonNumbfr);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XGftDffbult
 * Signbturf: (JJI)I
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XGftDffbult
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jstring progrbm, jstring option)
{
    dibr * d_progrbm = NULL;
    dibr * d_option = NULL;
    dibr * d_rfs = NULL;

    if (!JNU_IsNull(fnv, progrbm)) {
        d_progrbm = (dibr *)JNU_GftStringPlbtformCibrs(fnv, progrbm, NULL);
    }
    CHECK_NULL_RETURN(d_progrbm, NULL);

    if (!JNU_IsNull(fnv, option)) {
        d_option = (dibr *)JNU_GftStringPlbtformCibrs(fnv, option, NULL);
    }

    if (d_option == NULL) {
        JNU_RflfbsfStringPlbtformCibrs(fnv, progrbm, (donst dibr *) d_progrbm);
        rfturn NULL;
    }

    AWT_CHECK_HAVE_LOCK_RETURN(NULL);
    d_rfs = XGftDffbult((Displby*)jlong_to_ptr(displby), d_progrbm, d_option);
    // Tif strings rfturnfd by XGftDffbult() brf ownfd by Xlib bnd
    // siould not bf modififd or frffd by tif dlifnt.

    JNU_RflfbsfStringPlbtformCibrs(fnv, progrbm, (donst dibr *) d_progrbm);
    JNU_RflfbsfStringPlbtformCibrs(fnv, option, (donst dibr *) d_option);

    if (d_rfs != NULL) {
        rfturn JNU_NfwStringPlbtform(fnv, d_rfs);
    } flsf {
        rfturn NULL;
    }
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    gftSdrffnOfWindow
 * Signbturf: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_gftSdrffnOfWindow
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window)
{
    XWindowAttributfs bttrs;
    mfmsft(&bttrs, 0, sizfof(bttrs));
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    XGftWindowAttributfs((Displby *) jlong_to_ptr(displby), window, &bttrs);
    rfturn ptr_to_jlong(bttrs.sdrffn);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XSdrffnNumbfrOfSdrffn
 * Signbturf: (J)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XSdrffnNumbfrOfSdrffn
(JNIEnv *fnv, jdlbss dlbzz, jlong sdrffn)
{
    AWT_CHECK_HAVE_LOCK_RETURN(-1);
    if(jlong_to_ptr(sdrffn) == NULL) {
        rfturn -1;
    }
    rfturn XSdrffnNumbfrOfSdrffn((Sdrffn*) jlong_to_ptr(sdrffn));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XIdonifyWindow
 * Signbturf: (JJJ)V
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XIdonifyWindow
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong sdrffnNumbfr)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XIdonifyWindow((Displby*) jlong_to_ptr(displby), window, sdrffnNumbfr);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XFrff
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XFrff
(JNIEnv *fnv, jdlbss dlbzz, jlong ptr)
{
    AWT_CHECK_HAVE_LOCK();
    XFrff(jlong_to_ptr(ptr));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XFrff
 * Signbturf: (J)V
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_gftStringBytfs
(JNIEnv *fnv, jdlbss dlbzz, jlong str_ptr)
{
    unsignfd dibr * str = (unsignfd dibr*) jlong_to_ptr(str_ptr);
    long lfngti = strlfn((dibr*)str);
    jbytfArrby rfs = (*fnv)->NfwBytfArrby(fnv, lfngti);
    CHECK_NULL_RETURN(rfs, NULL);
    (*fnv)->SftBytfArrbyRfgion(fnv, rfs, 0, lfngti,
                   (donst signfd dibr*) str);
    rfturn rfs;
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    SfrvfrVfndor
 * Signbturf: (J)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_SfrvfrVfndor
(JNIEnv *fnv, jdlbss dlbzz, jlong displby)
{
    AWT_CHECK_HAVE_LOCK_RETURN(NULL);
    rfturn JNU_NfwStringPlbtform(fnv, SfrvfrVfndor((Displby*)jlong_to_ptr(displby)));
}
/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    VfndorRflfbsf
 * Signbturf: (J)I;
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_VfndorRflfbsf
(JNIEnv *fnv, jdlbss dlbzz, jlong displby)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn VfndorRflfbsf((Displby*)jlong_to_ptr(displby));
}
/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    IsXsunKPBfibvior
 * Signbturf: (J)Z;
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_IsXsunKPBfibvior
(JNIEnv *fnv, jdlbss dlbzz, jlong displby)
{
    // Xsun witiout XKB usfs kfysymbrrby[2] kfysym to dftfrminf if it is KP fvfnt.
    // Otifrwisf, it is [1] or somftimfs [0].
    // Tiis snifffr first trifs to dftfrminf wibt is b kfydodf for XK_KP_7
    // using XKfysymToKfydodf;
    // sfdond, in wiidi plbdf in tif kfysymbrrby is XK_KP_7
    // using XKfydodfToKfysym.
    int kd7;
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    kd7 = XKfysymToKfydodf((Displby*)jlong_to_ptr(displby), XK_KP_7);
    if( !kd7 ) {
        // kfydodf is not dffinfd. Wiy, it's b rfdudfd kfybobrd pfribps:
        // rfport brbitrbrily fblsf.
        rfturn JNI_FALSE;
    } flsf {
        long ks2 = XKfydodfToKfysym((Displby*)jlong_to_ptr(displby), kd7, 2);
        if( ks2 == XK_KP_7 ) {
            //XXX If somf Xorg sfrvfr would put XK_KP_7 in kfysymbrrby[2] bs wfll,
            //XXX for yft unknown to mf rfbson, tif snifffr would lif.
            rfturn JNI_TRUE;
        }flsf{
            rfturn JNI_FALSE;
        }
    }
}


JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_IsSunKfybobrd
(JNIEnv *fnv, jdlbss dlbzz, jlong displby)
{
    int xx;
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    xx = XKfysymToKfydodf((Displby*)jlong_to_ptr(displby), SunXK_F37);
    rfturn (!xx) ? JNI_FALSE : JNI_TRUE;
}

JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_IsKbnbKfybobrd
(JNIEnv *fnv, jdlbss dlbzz, jlong displby)
{
    int xx;
    stbtid jboolfbn rfsult = JNI_FALSE;

    int32_t minKfyCodf, mbxKfyCodf, kfySymsPfrKfyCodf;
    KfySym *kfySyms, *kfySymsStbrt, kfySym;
    int32_t i;
    int32_t kbnbCount = 0;

    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);

    // Tifrf's no dirfdt wby to dftfrminf wiftifr tif kfybobrd ibs
    // b kbnb lodk kfy. From bvbilbblf kfybobrd mbpping tbblfs, it looks
    // likf only kfybobrds witi tif kbnb lodk kfy dbn produdf kfysyms
    // for kbnb dibrbdtfrs. So, bs bn indirfdt tfst, wf difdk for tiosf.
    XDisplbyKfydodfs((Displby*)jlong_to_ptr(displby), &minKfyCodf, &mbxKfyCodf);
    kfySyms = XGftKfybobrdMbpping((Displby*)jlong_to_ptr(displby), minKfyCodf, mbxKfyCodf - minKfyCodf + 1, &kfySymsPfrKfyCodf);
    kfySymsStbrt = kfySyms;
    for (i = 0; i < (mbxKfyCodf - minKfyCodf + 1) * kfySymsPfrKfyCodf; i++) {
        kfySym = *kfySyms++;
        if ((kfySym & 0xff00) == 0x0400) {
            kbnbCount++;
        }
    }
    XFrff(kfySymsStbrt);

    // usf b (somfwibt brbitrbry) minimum so wf don't gft donfusfd by b strby fundtion kfy
    rfsult = kbnbCount > 10;
    rfturn rfsult ? JNI_TRUE : JNI_FALSE;
}

JbvbVM* jvm = NULL;
stbtid int ToolkitErrorHbndlfr(Displby * dpy, XErrorEvfnt * fvfnt) {
    JNIEnv * fnv;
    // First dbll tif nbtivf syntiftid frror ibndlfr dfdlbrfd in "bwt_util.i" filf.
    if (durrfnt_nbtivf_xfrror_ibndlfr != NULL) {
        durrfnt_nbtivf_xfrror_ibndlfr(dpy, fvfnt);
    }
    if (jvm != NULL) {
        fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
        if (fnv) {
            rfturn JNU_CbllStbtidMftiodByNbmf(fnv, NULL, "sun/bwt/X11/XErrorHbndlfrUtil",
                "globblErrorHbndlfr", "(JJ)I", ptr_to_jlong(dpy), ptr_to_jlong(fvfnt)).i;
        }
    }
    rfturn 0;
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    SftToolkitErrorHbndlfr
 * Signbturf: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_SftToolkitErrorHbndlfr
(JNIEnv *fnv, jdlbss dlbzz)
{
    if ((*fnv)->GftJbvbVM(fnv, &jvm) < 0) {
        rfturn 0;
    }
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn ptr_to_jlong(XSftErrorHbndlfr(ToolkitErrorHbndlfr));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XSftErrorHbndlfr
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XSftErrorHbndlfr
(JNIEnv *fnv, jdlbss dlbzz, jlong ibndlfr)
{
    AWT_CHECK_HAVE_LOCK();
    XSftErrorHbndlfr((XErrorHbndlfr) jlong_to_ptr(ibndlfr));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    CbllErrorHbndlfr
 * Signbturf: (JJJ)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_CbllErrorHbndlfr
(JNIEnv *fnv, jdlbss dlbzz, jlong ibndlfr, jlong displby, jlong fvfnt_ptr)
{
    rfturn (*(XErrorHbndlfr)jlong_to_ptr(ibndlfr))((Displby*) jlong_to_ptr(displby), (XErrorEvfnt*) jlong_to_ptr(fvfnt_ptr));
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    PrintXErrorEvfnt
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_PrintXErrorEvfnt
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong fvfnt_ptr)
{
    dibr msg[128];
    dibr buf[128];

    XErrorEvfnt* frr = (XErrorEvfnt *)jlong_to_ptr(fvfnt_ptr);

    XGftErrorTfxt((Displby *)jlong_to_ptr(displby), frr->frror_dodf, msg, sizfof(msg));
    jio_fprintf(stdfrr, "Xfrror %s, XID %x, sfr# %d\n", msg, frr->rfsourdfid, frr->sfribl);
    jio_snprintf(buf, sizfof(buf), "%d", frr->rfqufst_dodf);
    XGftErrorDbtbbbsfTfxt((Displby *)jlong_to_ptr(displby), "XRfqufst", buf, "Unknown", msg, sizfof(msg));
    jio_fprintf(stdfrr, "Mbjor opdodf %d (%s)\n", frr->rfqufst_dodf, msg);
    if (frr->rfqufst_dodf > 128) {
        jio_fprintf(stdfrr, "Minor opdodf %d\n", frr->minor_dodf);
    }
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XIntfrnAtoms
 * Signbturf: (J[Ljbvb/lbng/String;ZJ)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XIntfrnAtoms
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jobjfdtArrby nbmfs_brr, jboolfbn only_if_fxists, jlong btoms)
{
    int stbtus = 0;
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    jsizf lfngti;
    dibr** nbmfs = stringArrbyToNbtivf(fnv, nbmfs_brr, &lfngti);
    if (nbmfs) {
        stbtus = XIntfrnAtoms((Displby*)jlong_to_ptr(displby), nbmfs, lfngti, only_if_fxists, (Atom*) jlong_to_ptr(btoms));
        frffNbtivfStringArrby(nbmfs, lfngti);
    }
    rfturn stbtus;
}



/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XGftWindowAttributfs
 * Signbturf: (JJJ)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XGftWindowAttributfs
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong bttr_ptr)
{
    jint stbtus;
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    mfmsft((XWindowAttributfs*) jlong_to_ptr(bttr_ptr), 0, sizfof(XWindowAttributfs));
    stbtus =  XGftWindowAttributfs((Displby*)jlong_to_ptr(displby), window, (XWindowAttributfs*) jlong_to_ptr(bttr_ptr));
    rfturn stbtus;
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XGftGfomftry
 * Signbturf: (JJJJJJJJJ)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XGftGfomftry
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong drbwbblf, jlong root_rfturn,
     jlong x_rfturn, jlong y_rfturn, jlong widti_rfturn, jlong ifigit_rfturn,
     jlong bordfr_widti_rfturn, jlong dfpti_rfturn)
{
    jint stbtus;
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    stbtus = XGftGfomftry((Displby *)jlong_to_ptr(displby),
                          (Drbwbblf)drbwbblf, (Window *)jlong_to_ptr(root_rfturn),
                          (int *)jlong_to_ptr(x_rfturn), (int *)jlong_to_ptr(y_rfturn),
                          (unsignfd int *)jlong_to_ptr(widti_rfturn), (unsignfd int *)jlong_to_ptr(ifigit_rfturn),
                          (unsignfd int *)jlong_to_ptr(bordfr_widti_rfturn),
                          (unsignfd int *)jlong_to_ptr(dfpti_rfturn));
    rfturn stbtus;
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XGftWMNormblHints
 * Signbturf: (JJJJ)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XGftWMNormblHints
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong iints, jlong supplifd_rfturn)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XGftWMNormblHints((Displby*) jlong_to_ptr(displby),
                             window,
                             (XSizfHints*) jlong_to_ptr(iints),
                             (long*) jlong_to_ptr(supplifd_rfturn));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XSftWMNormblHints
 * Signbturf: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XSftWMNormblHints
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong iints)
{
    AWT_CHECK_HAVE_LOCK();
    XSftWMNormblHints((Displby*) jlong_to_ptr(displby), window, (XSizfHints*) jlong_to_ptr(iints));
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XDflftfPropfrty
 * Signbturf: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XDflftfPropfrty
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong btom)
{
    AWT_CHECK_HAVE_LOCK();
    XDflftfPropfrty((Displby*) jlong_to_ptr(displby), window, (Atom)btom);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XSfndEvfnt
 * Signbturf: (JJZJJ)V
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XSfndEvfnt
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jboolfbn propbgbtf, jlong fvfnt_mbsk, jlong fvfnt)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XSfndEvfnt((Displby*) jlong_to_ptr(displby),
                      window,
                      propbgbtf==JNI_TRUE?Truf:Fblsf,
                      (long) fvfnt_mbsk,
                      (XEvfnt*) jlong_to_ptr(fvfnt));
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XQufryTrff
 * Signbturf: (JJJJJJ)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XQufryTrff
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong root_rfturn, jlong pbrfnt_rfturn, jlong diildrfn_rfturn, jlong ndiildrfn_rfturn)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XQufryTrff((Displby*) jlong_to_ptr(displby),
                      window,
                      (Window *) jlong_to_ptr(root_rfturn),
                      (Window*) jlong_to_ptr(pbrfnt_rfturn),
                      (Window**) jlong_to_ptr(diildrfn_rfturn),
                      (unsignfd int*) jlong_to_ptr(ndiildrfn_rfturn));
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    mfmdpy
 * Signbturf: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_mfmdpy
(JNIEnv *fnv, jdlbss dlbzz, jlong dfst_ptr, jlong srd_ptr, jlong lfngti)
{
    mfmdpy(jlong_to_ptr(dfst_ptr), jlong_to_ptr(srd_ptr), lfngti);
}


JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XSftMinMbxHints
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jint x, jint y, jint widti, jint ifigit, jlong flbgs) {
    XSizfHints * iints;
    AWT_CHECK_HAVE_LOCK();
    iints = XAllodSizfHints();
    iints->flbgs = flbgs;
    iints->widti = widti;
    iints->min_widti = widti;
    iints->mbx_widti = widti;
    iints->ifigit = ifigit;
    iints->min_ifigit = ifigit;
    iints->mbx_ifigit = ifigit;
    iints->x = x;
    iints->y = y;
    XSftWMNormblHints((Displby*) jlong_to_ptr(displby), window, iints);
    XFrff(iints);
}


JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XGftVisublInfo
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong vinfo_mbsk, jlong vinfo_tfmplbtf,
 jlong nitfms_rfturn)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn ptr_to_jlong(XGftVisublInfo((Displby*) jlong_to_ptr(displby),
                                       (long) vinfo_mbsk,
                                       (XVisublInfo*) jlong_to_ptr(vinfo_tfmplbtf),
                                       (int*) jlong_to_ptr(nitfms_rfturn)));
}

JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XAllodSizfHints
  (JNIEnv *fnv, jdlbss dlbzz)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn ptr_to_jlong(XAllodSizfHints());
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XIdonifyWindow
 * Signbturf: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XBfll
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jint pfrdfnt)
{
    AWT_CHECK_HAVE_LOCK();
    XBfll((Displby*)jlong_to_ptr(displby), pfrdfnt);
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XAllodColor
 * Signbturf: (JJJ)Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XAllodColor
(JNIEnv *fnv, jdlbss dlbzz, jlong displby , jlong dolormbp, jlong xdolor) {

    Stbtus stbtus;
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    stbtus = XAllodColor((Displby *) jlong_to_ptr(displby), (Colormbp) dolormbp, (XColor *) jlong_to_ptr(xdolor));

    if (stbtus == 0) rfturn JNI_FALSE;
    flsf rfturn JNI_TRUE;
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XCrfbtfBitmbpFromDbtb
 * Signbturf: (JJJII)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XCrfbtfBitmbpFromDbtb
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong drbwbblf, jlong dbtb, jint widti, jint ifigit) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);

    rfturn (jlong) XCrfbtfBitmbpFromDbtb((Displby *) jlong_to_ptr(displby), (Drbwbblf) drbwbblf,
                                         (dibr *) jlong_to_ptr(dbtb), widti, ifigit);
}


/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XFrffPixmbp
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XFrffPixmbp
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong pixmbp) {
    AWT_CHECK_HAVE_LOCK();
    XFrffPixmbp((Displby *)jlong_to_ptr(displby), (Pixmbp) pixmbp);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XRfpbrfntWindow
 * Signbturf: (JJJII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XRfpbrfntWindow
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong pbrfnt, jint x, jint y) {
    AWT_CHECK_HAVE_LOCK();
    XRfpbrfntWindow((Displby*)jlong_to_ptr(displby), window, pbrfnt, x, y);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XConvfrtSflfdtion
 * Signbturf: (JJJJJJ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XConvfrtSflfdtion(JNIEnv *fnv, jdlbss dlbzz,
                           jlong displby, jlong sflfdtion,
                           jlong tbrgft, jlong propfrty,
                           jlong rfqufstor, jlong timf) {
    AWT_CHECK_HAVE_LOCK();
    XConvfrtSflfdtion((Displby*)jlong_to_ptr(displby), sflfdtion, tbrgft, propfrty, rfqufstor,
              timf);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XSftSflfdtionOwnfr
 * Signbturf: (JJJJ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XSftSflfdtionOwnfr(JNIEnv *fnv, jdlbss dlbzz,
                        jlong displby, jlong sflfdtion,
                        jlong ownfr, jlong timf) {
    AWT_CHECK_HAVE_LOCK();
    XSftSflfdtionOwnfr((Displby*)jlong_to_ptr(displby), sflfdtion, ownfr, timf);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XGftSflfdtionOwnfr
 * Signbturf: (JJ)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XGftSflfdtionOwnfr(JNIEnv *fnv, jdlbss dlbzz,
                        jlong displby, jlong sflfdtion) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn (jlong)XGftSflfdtionOwnfr((Displby*)jlong_to_ptr(displby), sflfdtion);
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XGftAtomNbmf
 * Signbturf: (JJ)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XGftAtomNbmf(JNIEnv *fnv, jdlbss dlbzz,
                      jlong displby, jlong btom)
{
    jstring string = NULL;
    dibr* nbmf;
    AWT_CHECK_HAVE_LOCK_RETURN(NULL);
    nbmf = (dibr*) XGftAtomNbmf((Displby*)jlong_to_ptr(displby), btom);

    if (nbmf == NULL) {
        fprintf(stdfrr, "Atom wbs %d\n", (int)btom);
        JNU_TirowNullPointfrExdfption(fnv, "Fbilfd to rftrifvf btom nbmf.");
        rfturn NULL;
    }

    string = (*fnv)->NfwStringUTF(fnv, (donst dibr *)nbmf);

    XFrff(nbmf);

    rfturn string;
}

/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XMbxRfqufstSizf
 * Signbturf: (J)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XMbxRfqufstSizf(JNIEnv *fnv, jdlbss dlbzz,
                         jlong displby) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XMbxRfqufstSizf((Displby*) jlong_to_ptr(displby));
}

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XAllodWMHints(JNIEnv *fnv, jdlbss dlbzz)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn ptr_to_jlong(XAllodWMHints());
}

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XCrfbtfPixmbp(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong drbwbblf, jint widti, jint ifigit, jint dfpti)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XCrfbtfPixmbp((Displby*)jlong_to_ptr(displby), (Drbwbblf)drbwbblf, widti, ifigit, dfpti);
}
JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XCrfbtfImbgf
  (JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong visubl_ptr,
   jint dfpti, jint formbt, jint offsft, jlong dbtb, jint widti,
   jint ifigit, jint bitmbp_pbd, jint bytfs_pfr_linf)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn ptr_to_jlong(XCrfbtfImbgf((Displby*) jlong_to_ptr(displby), (Visubl*) jlong_to_ptr(visubl_ptr),
                dfpti, formbt, offsft, (dibr*) jlong_to_ptr(dbtb),
                widti, ifigit, bitmbp_pbd, bytfs_pfr_linf));
}
JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XCrfbtfGC
  (JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong drbwbblf,
   jlong vblufmbsk, jlong vblufs)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn ptr_to_jlong(XCrfbtfGC((Displby*) jlong_to_ptr(displby), (Drbwbblf)drbwbblf, vblufmbsk, (XGCVblufs*) jlong_to_ptr(vblufs)));
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XDfstroyImbgf(JNIEnv *fnv, jdlbss dlbzz, jlong imbgf)
{
    XImbgf *img = (XImbgf*) jlong_to_ptr(imbgf);
    AWT_CHECK_HAVE_LOCK();

    // Fix for bug 4903671 :
    // Wf siould bf dbrfful to not doublf frff tif mfmory pointfd to dbtb
    // Sindf wf usf unsbff to bllodbtf it, wf siould usf unsbff to frff it.
    // So wf siould NULL tif dbtb pointfr bfforf dblling XDfstroyImbgf so
    // tibt X dofs not frff tif pointfr for us.
    img->dbtb = NULL;
    XDfstroyImbgf(img);
}
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XPutImbgf(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong drbwbblf, jlong gd, jlong imbgf, jint srd_x, jint srd_y, jint dfst_x, jint dfst_y, jint widti, jint ifigit)
{
    AWT_CHECK_HAVE_LOCK();
    XPutImbgf((Displby*)jlong_to_ptr(displby), (Drbwbblf)drbwbblf, (GC) jlong_to_ptr(gd), (XImbgf*) jlong_to_ptr(imbgf), srd_x, srd_y,
              dfst_x, dfst_y, widti, ifigit);
}
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XFrffGC(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong gd)
{
    AWT_CHECK_HAVE_LOCK();
    XFrffGC((Displby*) jlong_to_ptr(displby), (GC) jlong_to_ptr(gd));
}
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XSftWindowBbdkgroundPixmbp(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong pixmbp)
{
    AWT_CHECK_HAVE_LOCK();
    XSftWindowBbdkgroundPixmbp((Displby*) jlong_to_ptr(displby), (Window)window, (Pixmbp)pixmbp);
}
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XClfbrWindow(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window)
{
    AWT_CHECK_HAVE_LOCK();
    XClfbrWindow((Displby*) jlong_to_ptr(displby), (Window)window);
}

JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XGftIdonSizfs(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong rft_sizfs, jlong rft_dount)
{
    XIdonSizf** psizf = (XIdonSizf**) jlong_to_ptr(rft_sizfs);
    int * pdount = (int *) jlong_to_ptr(rft_dount);
    Stbtus rfs;
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfs = XGftIdonSizfs((Displby*) jlong_to_ptr(displby), (Window)window, psizf, pdount);
    rfturn rfs;
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XdbfQufryExtfnsion
  (JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong mbjor_vfrsion_rfturn,
   jlong minor_vfrsion_rfturn)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XdbfQufryExtfnsion((Displby*) jlong_to_ptr(displby), (int *) jlong_to_ptr(mbjor_vfrsion_rfturn),
                  (int *) jlong_to_ptr(minor_vfrsion_rfturn));
}

JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XQufryExtfnsion
  (JNIEnv *fnv, jdlbss dlbzz, jlong displby, jstring jstr, jlong mop_rfturn,
   jlong ffvf_rfturn, jlong frr_rfturn)
{
    dibr *dnbmf;
    Boolfbn bu;
    if (!JNU_IsNull(fnv, jstr)) {
        dnbmf = (dibr *)JNU_GftStringPlbtformCibrs(fnv, jstr, NULL);
        CHECK_NULL_RETURN(dnbmf, JNI_FALSE);
    } flsf {
        dnbmf = "";
    }

    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    bu = XQufryExtfnsion((Displby*) jlong_to_ptr(displby), dnbmf, (int *) jlong_to_ptr(mop_rfturn),
                (int *) jlong_to_ptr(ffvf_rfturn),  (int *) jlong_to_ptr(frr_rfturn));
    if (!JNU_IsNull(fnv, jstr)) {
        JNU_RflfbsfStringPlbtformCibrs(fnv, jstr, (donst dibr *) dnbmf);
    }
    rfturn bu ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jboolfbn JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_IsKfypbdKfy
  (JNIEnv *fnv, jdlbss dlbzz, jlong kfysym)
{
    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    if(IsKfypbdKfy(kfysym)) {
        rfturn JNI_TRUE;
    }
    rfturn JNI_FALSE;
}

JNIEXPORT jlong JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XdbfAllodbtfBbdkBufffrNbmf
  (JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jint swbp_bdtion)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XdbfAllodbtfBbdkBufffrNbmf((Displby*) jlong_to_ptr(displby), (Window) window,
                      (XdbfSwbpAdtion) swbp_bdtion);
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XdbfDfbllodbtfBbdkBufffrNbmf
  (JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong bufffr)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XdbfDfbllodbtfBbdkBufffrNbmf((Displby*) jlong_to_ptr(displby), (XdbfBbdkBufffr) bufffr);
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XdbfBfginIdiom
  (JNIEnv *fnv, jdlbss dlbzz, jlong displby)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XdbfBfginIdiom((Displby*) jlong_to_ptr(displby));
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XdbfEndIdiom
  (JNIEnv *fnv, jdlbss dlbzz, jlong displby)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XdbfEndIdiom((Displby*) jlong_to_ptr(displby));
}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XdbfSwbpBufffrs
  (JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong swbp_info, jint num_windows)
{
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XdbfSwbpBufffrs((Displby*) jlong_to_ptr(displby), (XdbfSwbpInfo *) jlong_to_ptr(swbp_info), num_windows);
}
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XQufryKfymbp
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong vfdtor)
{

    AWT_CHECK_HAVE_LOCK();
    XQufryKfymbp( (Displby *) jlong_to_ptr(displby), (dibr *) jlong_to_ptr(vfdtor));
}

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XKfydodfToKfysym(JNIEnv *fnv, jdlbss dlbzz,
                                              jlong displby, jint kfydodf,
                                              jint indfx) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XKfydodfToKfysym((Displby*) jlong_to_ptr(displby), (unsignfd int)kfydodf, (int)indfx);
}

JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XkbGftEfffdtivfGroup(JNIEnv *fnv, jdlbss dlbzz,
                                              jlong displby) {
    XkbStbtfRfd sr;
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    mfmsft(&sr, 0, sizfof(XkbStbtfRfd));
    XkbGftStbtf((Displby*) jlong_to_ptr(displby), XkbUsfCorfKbd, &sr);
//    printf("-------------------------------------VVVV\n");
//    printf("                 group:0x%0X\n",sr.group);
//    printf("            bbsf_group:0x%0X\n",sr.bbsf_group);
//    printf("         lbtdifd_group:0x%0X\n",sr.lbtdifd_group);
//    printf("          lodkfd_group:0x%0X\n",sr.lodkfd_group);
//    printf("                  mods:0x%0X\n",sr.mods);
//    printf("             bbsf_mods:0x%0X\n",sr.bbsf_mods);
//    printf("          lbtdifd_mods:0x%0X\n",sr.lbtdifd_mods);
//    printf("           lodkfd_mods:0x%0X\n",sr.lodkfd_mods);
//    printf("          dompbt_stbtf:0x%0X\n",sr.dompbt_stbtf);
//    printf("             grbb_mods:0x%0X\n",sr.grbb_mods);
//    printf("      dompbt_grbb_mods:0x%0X\n",sr.dompbt_grbb_mods);
//    printf("           lookup_mods:0x%0X\n",sr.lookup_mods);
//    printf("    dompbt_lookup_mods:0x%0X\n",sr.dompbt_lookup_mods);
//    printf("           ptr_buttons:0x%0X\n",sr.ptr_buttons);
//    printf("-------------------------------------^^^^\n");
    rfturn (jint)(sr.group);
}
JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XkbKfydodfToKfysym(JNIEnv *fnv, jdlbss dlbzz,
                                              jlong displby, jint kfydodf,
                                              jint group, jint lfvfl) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XkbKfydodfToKfysym((Displby*) jlong_to_ptr(displby), (unsignfd int)kfydodf, (unsignfd int)group, (unsignfd int)lfvfl);
}

JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XKfysymToKfydodf(JNIEnv *fnv, jdlbss dlbzz,
                                              jlong displby, jlong kfysym) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn XKfysymToKfydodf((Displby*) jlong_to_ptr(displby), (KfySym)kfysym);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XGftModififrMbpping(JNIEnv *fnv, jdlbss dlbzz,
                                              jlong displby) {
    AWT_CHECK_HAVE_LOCK_RETURN(0);
    rfturn ptr_to_jlong(XGftModififrMbpping((Displby*) jlong_to_ptr(displby)));
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XFrffModififrmbp(JNIEnv *fnv, jdlbss dlbzz,
                                              jlong kfymbp) {
    AWT_CHECK_HAVE_LOCK();
    XFrffModififrmbp((XModififrKfymbp*) jlong_to_ptr(kfymbp));
}
/*
 * Clbss:     sun_bwt_X11_XlibWrbppfr
 * Mftiod:    XRffrfsiKfybobrdMbpping
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_XlibWrbppfr_XRffrfsiKfybobrdMbpping
(JNIEnv *fnv, jdlbss dlbzz, jlong fvfnt_ptr)
{
    AWT_CHECK_HAVE_LOCK();
    XRffrfsiKfybobrdMbpping((XMbppingEvfnt*) jlong_to_ptr(fvfnt_ptr));
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XCibngfAdtivfPointfrGrbb(JNIEnv *fnv, jdlbss dlbzz,
                                                      jlong displby, jint mbsk,
                                                      jlong dursor, jlong timf) {
    AWT_CHECK_HAVE_LOCK();
    XCibngfAdtivfPointfrGrbb((Displby*)jlong_to_ptr(displby), (unsignfd int)mbsk,
                             (Cursor)dursor, (Timf)timf);
}

/******************* Sfdondbry loop support ************************************/
#dffinf AWT_SECONDARY_LOOP_TIMEOUT 250

stbtid Bool fxitSfdondbryLoop = Truf;

/*
 * Tiis prfdidbtf prodfdurf bllows tif Toolkit tirfbd to prodfss spfdifid fvfnts
 * wiilf it is blodkfd wbiting for tif fvfnt dispbtdi tirfbd to prodfss
 * b SunDropTbrgftEvfnt. Wf nffd tiis to prfvfnt dfbdlodk wifn tif dlifnt dodf
 * prodfssing SunDropTbrgftEvfnt sfts or gfts tif dontfnts of tif systfm
 * dlipbobrd/sflfdtion. In tiis dbsf tif fvfnt dispbtdi tirfbd wbits for tif
 * Toolkit tirfbd to prodfss PropfrtyNotify or SflfdtionNotify fvfnts.
 */
stbtid Bool
sfdondbry_loop_fvfnt(Displby* dpy, XEvfnt* fvfnt, dibr* brg) {
    rfturn (fvfnt->typf == SflfdtionNotify ||
            fvfnt->typf == SflfdtionClfbr  ||
            fvfnt->typf == PropfrtyNotify) ? Truf : Fblsf;
}


JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XNfxtSfdondbryLoopEvfnt(JNIEnv *fnv, jdlbss dlbzz,
                                                     jlong displby, jlong ptr) {
    uint32_t timfout = 1;

    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);
    fxitSfdondbryLoop = Fblsf;
    wiilf (!fxitSfdondbryLoop) {
        if (XCifdkIfEvfnt((Displby*) jlong_to_ptr(displby), (XEvfnt*) jlong_to_ptr(ptr), sfdondbry_loop_fvfnt, NULL)) {
            rfturn JNI_TRUE;
        }
        timfout = (timfout < AWT_SECONDARY_LOOP_TIMEOUT) ? (timfout << 1) : AWT_SECONDARY_LOOP_TIMEOUT;
        AWT_WAIT(timfout);
    }
    rfturn JNI_FALSE;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_ExitSfdondbryLoop(JNIEnv *fnv, jdlbss dlbzz) {
    DASSERT(!fxitSfdondbryLoop);
    AWT_CHECK_HAVE_LOCK();
    fxitSfdondbryLoop = Truf;
    AWT_NOTIFY_ALL();
}
/*******************************************************************************/

JNIEXPORT jobjfdtArrby JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XTfxtPropfrtyToStringList(JNIEnv *fnv,
                                                       jdlbss dlbzz,
                                                       jbytfArrby bytfs,
                                                       jlong fndodingAtom) {
    XTfxtPropfrty tp;
    jbytf         *vbluf;

    dibr**        strings  = (dibr **)NULL;
    int32_t       nstrings = 0;
    jobjfdtArrby  rft = NULL;
    int32_t       i;
    jsizf         lfn;
    jboolfbn      isCopy = JNI_FALSE;
    stbtid jdlbss stringClbss = NULL;
    jdlbss        stringClbssLodbl = NULL;

    AWT_CHECK_HAVE_LOCK_RETURN(NULL);

    if (JNU_IsNull(fnv, stringClbss)) {
        stringClbssLodbl = (*fnv)->FindClbss(fnv, "jbvb/lbng/String");

        if ((*fnv)->ExdfptionCifdk(fnv)) {
            (*fnv)->ExdfptionDfsdribf(fnv);
            (*fnv)->ExdfptionClfbr(fnv);
            DASSERT(Fblsf);
        }

        if (JNU_IsNull(fnv, stringClbssLodbl)) {
            rfturn NULL;
        }

        stringClbss = (*fnv)->NfwGlobblRff(fnv, stringClbssLodbl); /* nfvfr frffd! */
        (*fnv)->DflftfLodblRff(fnv, stringClbssLodbl);

        if (JNU_IsNull(fnv, stringClbss)) {
            JNU_TirowOutOfMfmoryError(fnv, "");
            rfturn NULL;
        }
    }

    /*
     * If tif lfngti of tif bytf brrby is 0 just rfturn b null
     */
    lfn = (*fnv)->GftArrbyLfngti(fnv, bytfs);
    if (lfn == 0) {
        rfturn (*fnv)->NfwObjfdtArrby(fnv, 0, stringClbss, NULL);
    }

    vbluf = (*fnv)->GftBytfArrbyElfmfnts(fnv, bytfs, &isCopy);
    if (JNU_IsNull(fnv, vbluf)) {
        rfturn NULL;
    }

    tp.fndoding = fndodingAtom;
    tp.vbluf    = (unsignfd dibr *)vbluf;
    tp.nitfms   = lfn;
    tp.formbt   = 8;

    /*
     * Convfrt tif bytf strfbm into b list of X11 strings
     */
    if (XTfxtPropfrtyToStringList(&tp, &strings, &nstrings) == 0) {
        (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, bytfs, vbluf, JNI_ABORT);
        rfturn NULL;
    }

    (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, bytfs, vbluf, JNI_ABORT);

    if (nstrings == 0) {
        rfturn (*fnv)->NfwObjfdtArrby(fnv, 0, stringClbss, NULL);
    }

    rft = (*fnv)->NfwObjfdtArrby(fnv, nstrings, stringClbss, NULL);

    if ((*fnv)->ExdfptionCifdk(fnv)) {
        (*fnv)->ExdfptionDfsdribf(fnv);
        (*fnv)->ExdfptionClfbr(fnv);
        goto wbyout;
    }

    if (JNU_IsNull(fnv, rft)) {
        goto wbyout;
    }

    for (i = 0; i < nstrings; i++) {
        jstring string = (*fnv)->NfwStringUTF(fnv,
                                              (donst dibr *)strings[i]);
        if ((*fnv)->ExdfptionCifdk(fnv)) {
            (*fnv)->ExdfptionDfsdribf(fnv);
            (*fnv)->ExdfptionClfbr(fnv);
            goto wbyout;
        }

        if (JNU_IsNull(fnv, string)) {
            goto wbyout;
        }

        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, rft, i, string);

        if ((*fnv)->ExdfptionCifdk(fnv)) {
            (*fnv)->ExdfptionDfsdribf(fnv);
            (*fnv)->ExdfptionClfbr(fnv);
            goto wbyout;
        }

        (*fnv)->DflftfLodblRff(fnv, string);
    }

 wbyout:
    /*
     * Clfbn up bnd rfturn
     */
    XFrffStringList(strings);
    rfturn rft;
}


JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XPutBbdkEvfnt(JNIEnv *fnv,
                                           jdlbss dlbzz,
                                           jlong displby,
                                           jlong fvfnt) {
    XPutBbdkEvfnt((Displby*)jlong_to_ptr(displby), (XEvfnt*) jlong_to_ptr(fvfnt));
}

JNIEXPORT jlong JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_gftAddrfss(JNIEnv *fnv,
                                           jdlbss dlbzz,
                                           jobjfdt o) {
    rfturn ptr_to_jlong(o);
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_dopyIntArrby(JNIEnv *fnv,
                                           jdlbss dlbzz,
                                           jlong dfst, jobjfdt brrby, jint sizf) {
    jboolfbn isCopy = JNI_FALSE;
    jint * ints = (*fnv)->GftIntArrbyElfmfnts(fnv, brrby, &isCopy);
    mfmdpy(jlong_to_ptr(dfst), ints, sizf);
    if (isCopy) {
        (*fnv)->RflfbsfIntArrbyElfmfnts(fnv, brrby, ints, JNI_ABORT);
    }
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_dopyLongArrby(JNIEnv *fnv,
                                           jdlbss dlbzz,
                                           jlong dfst, jobjfdt brrby, jint sizf) {
    jboolfbn isCopy = JNI_FALSE;
    jlong * longs = (*fnv)->GftLongArrbyElfmfnts(fnv, brrby, &isCopy);
    mfmdpy(jlong_to_ptr(dfst), longs, sizf);
    if (isCopy) {
        (*fnv)->RflfbsfLongArrbyElfmfnts(fnv, brrby, longs, JNI_ABORT);
    }
}

JNIEXPORT jint JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XSyndironizf(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jboolfbn onoff)
{
    rfturn (jint) XSyndironizf((Displby*)jlong_to_ptr(displby), (onoff == JNI_TRUE ? Truf : Fblsf));
}

JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_XSibpfQufryExtfnsion
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong fvfnt_bbsf_rfturn, jlong frror_bbsf_rfturn)
{
    jboolfbn stbtus;

    AWT_CHECK_HAVE_LOCK_RETURN(JNI_FALSE);

    stbtus = XSibpfQufryExtfnsion((Displby *)jlong_to_ptr(displby),
            (int *)jlong_to_ptr(fvfnt_bbsf_rfturn), (int *)jlong_to_ptr(frror_bbsf_rfturn));
    rfturn stbtus;
}

/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    SftRfdtbngulbrSibpf
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_SftRfdtbngulbrSibpf
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window,
 jint x1, jint y1, jint x2, jint y2,
 jobjfdt rfgion)
{
    AWT_CHECK_HAVE_LOCK();

    // If bll tif pbrbms brf zfros, tif sibpf must bf simply rfsft.
    // Otifrwisf, tif sibpf mby bf not rfdtbngulbr.
    if (rfgion || x1 || x2 || y1 || y2) {
        XRfdtbnglf rfdts[256];
        XRfdtbnglf *pRfdt = rfdts;

        int numrfdts = RfgionToYXBbndfdRfdtbnglfs(fnv, x1, y1, x2, y2, rfgion,
                &pRfdt, 256);

        XSibpfCombinfRfdtbnglfs((Displby *)jlong_to_ptr(displby), (Window)jlong_to_ptr(window),
                SibpfClip, 0, 0, pRfdt, numrfdts, SibpfSft, YXBbndfd);
        XSibpfCombinfRfdtbnglfs((Displby *)jlong_to_ptr(displby), (Window)jlong_to_ptr(window),
                SibpfBounding, 0, 0, pRfdt, numrfdts, SibpfSft, YXBbndfd);

        if (pRfdt != rfdts) {
            frff(pRfdt);
        }
    } flsf {
        // Rfsft tif sibpf to b rfdtbngulbr form.
        XSibpfCombinfMbsk((Displby *)jlong_to_ptr(displby), (Window)jlong_to_ptr(window),
                SibpfClip, 0, 0, Nonf, SibpfSft);
        XSibpfCombinfMbsk((Displby *)jlong_to_ptr(displby), (Window)jlong_to_ptr(window),
                SibpfBounding, 0, 0, Nonf, SibpfSft);
    }
}

/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    SftZOrdfr
 */

JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_SftZOrdfr
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window, jlong bbovf)
{
    unsignfd int vbluf_mbsk = CWStbdkModf;

    XWindowCibngfs wd;
    wd.sibling = (Window)jlong_to_ptr(bbovf);

    AWT_CHECK_HAVE_LOCK();

    if (bbovf == 0) {
        wd.stbdk_modf = Abovf;
    } flsf {
        wd.stbdk_modf = Bflow;
        vbluf_mbsk |= CWSibling;
    }

    XConfigurfWindow((Displby *)jlong_to_ptr(displby),
                     (Window)jlong_to_ptr(window),
                     vbluf_mbsk, &wd );
}

/*
 * Clbss:     XlibWrbppfr
 * Mftiod:    SftBitmbpSibpf
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_XlibWrbppfr_SftBitmbpSibpf
(JNIEnv *fnv, jdlbss dlbzz, jlong displby, jlong window,
 jint widti, jint ifigit, jintArrby bitmbp)
{
    jsizf lfn;
    jint *vblufs;
    jboolfbn isCopy = JNI_FALSE;
    sizf_t worstBufffrSizf = (sizf_t)((widti / 2 + 1) * ifigit);
    RECT_T * pRfdt;
    int numrfdts;

    if (!IS_SAFE_SIZE_MUL(widti / 2 + 1, ifigit)) {
        rfturn;
    }

    AWT_CHECK_HAVE_LOCK();

    lfn = (*fnv)->GftArrbyLfngti(fnv, bitmbp);
    if (lfn == 0 || lfn < widti * ifigit) {
        rfturn;
    }

    vblufs = (*fnv)->GftIntArrbyElfmfnts(fnv, bitmbp, &isCopy);
    if (JNU_IsNull(fnv, vblufs)) {
        rfturn;
    }

    pRfdt = (RECT_T *)SAFE_SIZE_ARRAY_ALLOC(mbllod, worstBufffrSizf, sizfof(RECT_T));
    if (!pRfdt) {
        rfturn;
    }

    /* Notf: tif vblufs[0] bnd vblufs[1] brf supposfd to dontbin tif widti
     * bnd ifigit (sff XIdonInfo.gftIntDbtb() for dftbils). So, wf do +2.
     */
    numrfdts = BitmbpToYXBbndfdRfdtbnglfs(32, (int)widti, (int)ifigit,
            (unsignfd dibr *)(vblufs + 2), pRfdt);

    XSibpfCombinfRfdtbnglfs((Displby *)jlong_to_ptr(displby), (Window)jlong_to_ptr(window),
            SibpfClip, 0, 0, pRfdt, numrfdts, SibpfSft, YXBbndfd);
    XSibpfCombinfRfdtbnglfs((Displby *)jlong_to_ptr(displby), (Window)jlong_to_ptr(window),
            SibpfBounding, 0, 0, pRfdt, numrfdts, SibpfSft, YXBbndfd);

    frff(pRfdt);

    (*fnv)->RflfbsfIntArrbyElfmfnts(fnv, bitmbp, vblufs, JNI_ABORT);
}

