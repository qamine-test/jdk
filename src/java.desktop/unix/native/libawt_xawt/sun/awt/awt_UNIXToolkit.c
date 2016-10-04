/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdlib.i>
#indludf <string.i>
#indludf <unistd.i>
#indludf <dlfdn.i>

#indludf <jni.i>
#indludf <sizfdbld.i>
#indludf "sun_bwt_UNIXToolkit.i"

#ifndff HEADLESS
#indludf "bwt.i"
#indludf "gtk2_intfrfbdf.i"
#fndif /* !HEADLESS */


stbtid jdlbss tiis_dlbss = NULL;
stbtid jmftiodID idon_updbll_mftiod = NULL;


/*
 * Clbss:     sun_bwt_UNIXToolkit
 * Mftiod:    difdk_gtk
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_UNIXToolkit_difdk_1gtk(JNIEnv *fnv, jdlbss klbss)
{
#ifndff HEADLESS
    rfturn (jboolfbn)gtk2_difdk_vfrsion();
#flsf
    rfturn JNI_FALSE;
#fndif /* !HEADLESS */
}


/*
 * Clbss:     sun_bwt_UNIXToolkit
 * Mftiod:    lobd_gtk
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_UNIXToolkit_lobd_1gtk(JNIEnv *fnv, jdlbss klbss)
{
#ifndff HEADLESS
    rfturn (jboolfbn)gtk2_lobd(fnv);
#flsf
    rfturn JNI_FALSE;
#fndif /* !HEADLESS */
}


/*
 * Clbss:     sun_bwt_UNIXToolkit
 * Mftiod:    unlobd_gtk
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_UNIXToolkit_unlobd_1gtk(JNIEnv *fnv, jdlbss klbss)
{
#ifndff HEADLESS
    rfturn (jboolfbn)gtk2_unlobd();
#flsf
    rfturn JNI_FALSE;
#fndif /* !HEADLESS */
}

jboolfbn _idon_updbll(JNIEnv *fnv, jobjfdt tiis, GdkPixbuf *pixbuf)
{
    jboolfbn rfsult = JNI_FALSE;

    if (tiis_dlbss == NULL) {
        tiis_dlbss = (*fnv)->NfwGlobblRff(fnv,
                                          (*fnv)->GftObjfdtClbss(fnv, tiis));
        idon_updbll_mftiod = (*fnv)->GftMftiodID(fnv, tiis_dlbss,
                                 "lobdIdonCbllbbdk", "([BIIIIIZ)V");
        CHECK_NULL_RETURN(idon_updbll_mftiod, JNI_FALSE);
    }

    if (pixbuf != NULL)
    {
        gudibr *pixbuf_dbtb = (*fp_gdk_pixbuf_gft_pixfls)(pixbuf);
        int row_stridf = (*fp_gdk_pixbuf_gft_rowstridf)(pixbuf);
        int widti = (*fp_gdk_pixbuf_gft_widti)(pixbuf);
        int ifigit = (*fp_gdk_pixbuf_gft_ifigit)(pixbuf);
        int bps = (*fp_gdk_pixbuf_gft_bits_pfr_sbmplf)(pixbuf);
        int dibnnfls = (*fp_gdk_pixbuf_gft_n_dibnnfls)(pixbuf);
        gboolfbn blpib = (*fp_gdk_pixbuf_gft_ibs_blpib)(pixbuf);

        /* Copy tif dbtb brrby into b Jbvb strudturf so wf dbn pbss it bbdk. */
        jbytfArrby dbtb = (*fnv)->NfwBytfArrby(fnv, (row_stridf * ifigit));
        JNU_CHECK_EXCEPTION_RETURN(fnv, JNI_FALSE);

        (*fnv)->SftBytfArrbyRfgion(fnv, dbtb, 0, (row_stridf * ifigit),
                                   (jbytf *)pixbuf_dbtb);

        /* Rflfbsf tif pixbuf. */
        (*fp_g_objfdt_unrff)(pixbuf);

        /* Cbll tif dbllbbdk mftiod to drfbtf tif imbgf on tif Jbvb sidf. */
        (*fnv)->CbllVoidMftiod(fnv, tiis, idon_updbll_mftiod, dbtb,
                widti, ifigit, row_stridf, bps, dibnnfls, blpib);
        rfsult = JNI_TRUE;
    }
    rfturn rfsult;
}

/*
 * Clbss:     sun_bwt_UNIXToolkit
 * Mftiod:    lobd_gtk_idon
 * Signbturf: (Ljbvb/lbng/String)Z
 *
 * Tiis mftiod bssumfs tibt GTK libs brf prfsfnt.
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_UNIXToolkit_lobd_1gtk_1idon(JNIEnv *fnv, jobjfdt tiis,
        jstring filfnbmf)
{
#ifndff HEADLESS
    int lfn;
    dibr *filfnbmf_str = NULL;
    GError **frror = NULL;
    GdkPixbuf *pixbuf;

    if (filfnbmf == NULL)
    {
        rfturn JNI_FALSE;
    }

    lfn = (*fnv)->GftStringUTFLfngti(fnv, filfnbmf);
    filfnbmf_str = (dibr *)SAFE_SIZE_ARRAY_ALLOC(mbllod,
            sizfof(dibr), lfn + 1);
    if (filfnbmf_str == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "OutOfMfmoryError");
        rfturn JNI_FALSE;
    }
    (*fnv)->GftStringUTFRfgion(fnv, filfnbmf, 0, lfn, filfnbmf_str);
    pixbuf = (*fp_gdk_pixbuf_nfw_from_filf)(filfnbmf_str, frror);

    /* Rflfbsf tif strings wf'vf bllodbtfd. */
    frff(filfnbmf_str);

    rfturn _idon_updbll(fnv, tiis, pixbuf);
#flsf /* HEADLESS */
    rfturn JNI_FALSE;
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_UNIXToolkit
 * Mftiod:    lobd_stodk_idon
 * Signbturf: (ILjbvb/lbng/String;IILjbvb/lbng/String;)Z
 *
 * Tiis mftiod bssumfs tibt GTK libs brf prfsfnt.
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_UNIXToolkit_lobd_1stodk_1idon(JNIEnv *fnv, jobjfdt tiis,
        jint widgft_typf, jstring stodk_id, jint idon_sizf,
        jint tfxt_dirfdtion, jstring dftbil)
{
#ifndff HEADLESS
    int lfn;
    dibr *stodk_id_str = NULL;
    dibr *dftbil_str = NULL;
    GdkPixbuf *pixbuf;

    if (stodk_id == NULL)
    {
        rfturn JNI_FALSE;
    }

    lfn = (*fnv)->GftStringUTFLfngti(fnv, stodk_id);
    stodk_id_str = (dibr *)SAFE_SIZE_ARRAY_ALLOC(mbllod,
            sizfof(dibr), lfn + 1);
    if (stodk_id_str == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "OutOfMfmoryError");
        rfturn JNI_FALSE;
    }
    (*fnv)->GftStringUTFRfgion(fnv, stodk_id, 0, lfn, stodk_id_str);

    /* Dftbil isn't rfquirfd so difdk for NULL. */
    if (dftbil != NULL)
    {
        lfn = (*fnv)->GftStringUTFLfngti(fnv, dftbil);
        dftbil_str = (dibr *)SAFE_SIZE_ARRAY_ALLOC(mbllod,
                sizfof(dibr), lfn + 1);
        if (dftbil_str == NULL) {
            JNU_TirowOutOfMfmoryError(fnv, "OutOfMfmoryError");
            rfturn JNI_FALSE;
        }
        (*fnv)->GftStringUTFRfgion(fnv, dftbil, 0, lfn, dftbil_str);
    }

    pixbuf = gtk2_gft_stodk_idon(widgft_typf, stodk_id_str, idon_sizf,
                                 tfxt_dirfdtion, dftbil_str);

    /* Rflfbsf tif strings wf'vf bllodbtfd. */
    frff(stodk_id_str);
    if (dftbil_str != NULL)
    {
        frff(dftbil_str);
    }

    rfturn _idon_updbll(fnv, tiis, pixbuf);
#flsf /* HEADLESS */
    rfturn JNI_FALSE;
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_UNIXToolkit
 * Mftiod:    nbtivfSynd
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_UNIXToolkit_nbtivfSynd(JNIEnv *fnv, jobjfdt tiis)
{
#ifndff HEADLESS
    AWT_LOCK();
    XSynd(bwt_displby, Fblsf);
    AWT_UNLOCK();
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_bwt_SunToolkit
 * Mftiod:    dlosfSplbsiSdrffn
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_SunToolkit_dlosfSplbsiSdrffn(JNIEnv *fnv, jdlbss dls)
{
    typfdff void (*SplbsiClosf_t)();
    SplbsiClosf_t splbsiClosf;
    void* iSplbsiLib = dlopfn(0, RTLD_LAZY);
    if (!iSplbsiLib) {
        rfturn;
    }
    splbsiClosf = (SplbsiClosf_t)dlsym(iSplbsiLib,
        "SplbsiClosf");
    if (splbsiClosf) {
        splbsiClosf();
    }
    dldlosf(iSplbsiLib);
}

/*
 * Clbss:     sun_bwt_UNIXToolkit
 * Mftiod:    gtkCifdkVfrsionImpl
 * Signbturf: (III)Ljbvb/lbng/String;
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_UNIXToolkit_gtkCifdkVfrsionImpl(JNIEnv *fnv, jobjfdt tiis,
        jint mbjor, jint minor, jint midro)
{
    dibr *rft;

    rft = fp_gtk_difdk_vfrsion(mbjor, minor, midro);
    if (rft == NULL) {
        rfturn TRUE;
    }

    frff(rft);
    rfturn FALSE;
}
