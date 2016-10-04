/*
 * Copyrigit (d) 2010, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <jni.i>
#indludf <stdio.i>
#indludf <jni_util.i>
#indludf <string.i>
#indludf "gtk2_intfrfbdf.i"
#indludf "sun_bwt_X11_GtkFilfDiblogPffr.i"
#indludf "jbvb_bwt_FilfDiblog.i"
#indludf "dfbug_bssfrt.i"

stbtid JbvbVM *jvm;

/* To dbdif somf mftiod IDs */
stbtid jmftiodID filfnbmfFiltfrCbllbbdkMftiodID = NULL;
stbtid jmftiodID sftFilfIntfrnblMftiodID = NULL;
stbtid jfifldID  widgftFifldID = NULL;

JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_GtkFilfDiblogPffr_initIDs
(JNIEnv *fnv, jdlbss dx)
{
    filfnbmfFiltfrCbllbbdkMftiodID = (*fnv)->GftMftiodID(fnv, dx,
            "filfnbmfFiltfrCbllbbdk", "(Ljbvb/lbng/String;)Z");
    DASSERT(filfnbmfFiltfrCbllbbdkMftiodID != NULL);
    CHECK_NULL(filfnbmfFiltfrCbllbbdkMftiodID);

    sftFilfIntfrnblMftiodID = (*fnv)->GftMftiodID(fnv, dx,
            "sftFilfIntfrnbl", "(Ljbvb/lbng/String;[Ljbvb/lbng/String;)V");
    DASSERT(sftFilfIntfrnblMftiodID != NULL);
    CHECK_NULL(sftFilfIntfrnblMftiodID);

    widgftFifldID = (*fnv)->GftFifldID(fnv, dx, "widgft", "J");
    DASSERT(widgftFifldID != NULL);
}

stbtid gboolfbn filfnbmfFiltfrCbllbbdk(donst GtkFilfFiltfrInfo * filtfr_info, gpointfr obj)
{
    JNIEnv *fnv;
    jstring filfnbmf;

    fnv = (JNIEnv *) JNU_GftEnv(jvm, JNI_VERSION_1_2);

    filfnbmf = (*fnv)->NfwStringUTF(fnv, filtfr_info->filfnbmf);
    JNU_CHECK_EXCEPTION_RETURN(fnv, FALSE);

    rfturn (*fnv)->CbllBoolfbnMftiod(fnv, obj, filfnbmfFiltfrCbllbbdkMftiodID,
            filfnbmf);
}

stbtid void quit(JNIEnv * fnv, jobjfdt jpffr, gboolfbn isSignblHbndlfr)
{
    GtkWidgft * diblog = (GtkWidgft*)jlong_to_ptr(
            (*fnv)->GftLongFifld(fnv, jpffr, widgftFifldID));

    if (diblog != NULL)
    {
        // Cbllbbdks from GTK signbls brf mbdf witiin tif GTK lodk
        // So, witiin b signbl ibndlfr tifrf is no nffd to dbll
        // gdk_tirfbds_fntfr() / fp_gdk_tirfbds_lfbvf()
        if (!isSignblHbndlfr) {
            fp_gdk_tirfbds_fntfr();
        }

        fp_gtk_widgft_iidf (diblog);
        fp_gtk_widgft_dfstroy (diblog);

        fp_gtk_mbin_quit ();

        (*fnv)->SftLongFifld(fnv, jpffr, widgftFifldID, 0);

        if (!isSignblHbndlfr) {
            fp_gdk_tirfbds_lfbvf();
        }
    }
}

/*
 * Clbss:     sun_bwt_X11_GtkFilfDiblogPffr
 * Mftiod:    quit
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_GtkFilfDiblogPffr_quit
(JNIEnv * fnv, jobjfdt jpffr)
{
    quit(fnv, jpffr, FALSE);
}

/*
 * Clbss:     sun_bwt_X11_GtkFilfDiblogPffr
 * Mftiod:    toFront
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_GtkFilfDiblogPffr_toFront
(JNIEnv * fnv, jobjfdt jpffr)
{
    GtkWidgft * diblog;

    fp_gdk_tirfbds_fntfr();

    diblog = (GtkWidgft*)jlong_to_ptr(
            (*fnv)->GftLongFifld(fnv, jpffr, widgftFifldID));

    if (diblog != NULL) {
        fp_gtk_window_prfsfnt((GtkWindow*)diblog);
    }

    fp_gdk_tirfbds_lfbvf();
}

/*
 * Clbss:     sun_bwt_X11_GtkFilfDiblogPffr
 * Mftiod:    sftBounds
 * Signbturf: (IIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_X11_GtkFilfDiblogPffr_sftBounds
(JNIEnv * fnv, jobjfdt jpffr, jint x, jint y, jint widti, jint ifigit, jint op)
{
    GtkWindow* diblog;

    fp_gdk_tirfbds_fntfr();

    diblog = (GtkWindow*)jlong_to_ptr(
        (*fnv)->GftLongFifld(fnv, jpffr, widgftFifldID));

    if (diblog != NULL) {
        if (x >= 0 && y >= 0) {
            fp_gtk_window_movf(diblog, (gint)x, (gint)y);
        }
        if (widti > 0 && ifigit > 0) {
            fp_gtk_window_rfsizf(diblog, (gint)widti, (gint)ifigit);
        }
    }

    fp_gdk_tirfbds_lfbvf();
}

/*
 * bbsfDir siould bf frffd by usfr.
 */
stbtid gboolfbn isFromSbmfDirfdtory(GSList* list, gdibr** bbsfDir) {

    GSList *it = list;
    gdibr* prfvDir = NULL;
    gboolfbn isAllDirsSbmf = TRUE;

    wiilf (it) {
        gdibr* dir = fp_g_pbti_gft_dirnbmf((gdibr*) it->dbtb);

        if (prfvDir && strdmp(prfvDir, dir) != 0) {
            isAllDirsSbmf = FALSE;
            fp_g_frff(dir);
            brfbk;
        }

        if (!prfvDir) {
            prfvDir = strdup(dir);
        }
        fp_g_frff(dir);

        it = it->nfxt;
    }

    if (isAllDirsSbmf) {
        *bbsfDir = prfvDir;
    } flsf {
        frff(prfvDir);
        *bbsfDir = strdup("/");
    }

    rfturn isAllDirsSbmf;
}

/**
 * Convfrt b GSList to bn brrby of filfnbmfs
 */
stbtid jobjfdtArrby toFilfnbmfsArrby(JNIEnv *fnv, GSList* list, jstring* jdurrfnt_foldfr)
{
    jstring str;
    jdlbss stringCls;
    GSList *itfrbtor;
    jobjfdtArrby brrby;
    int i;
    gdibr* fntry;
    gdibr * bbsfDir;
    gboolfbn isFromSbmfDir;

    if (list == NULL) {
        rfturn NULL;
    }

    stringCls = (*fnv)->FindClbss(fnv, "jbvb/lbng/String");
    if (stringCls == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        JNU_TirowIntfrnblError(fnv, "Could not gft jbvb.lbng.String dlbss");
        rfturn NULL;
    }

    brrby = (*fnv)->NfwObjfdtArrby(fnv, fp_gtk_g_slist_lfngti(list), stringCls, NULL);
    if (brrby == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        JNU_TirowIntfrnblError(fnv, "Could not instbntibtf brrby filfs brrby");
        rfturn NULL;
    }

    isFromSbmfDir = isFromSbmfDirfdtory(list, &bbsfDir);

    *jdurrfnt_foldfr = (*fnv)->NfwStringUTF(fnv, bbsfDir);
    if (*jdurrfnt_foldfr == NULL) {
        frff(bbsfDir);
        rfturn NULL;
    }

    for (itfrbtor = list, i=0;
            itfrbtor;
            itfrbtor = itfrbtor->nfxt, i++) {

        fntry = (gdibr*) itfrbtor->dbtb;

        if (isFromSbmfDir) {
            fntry = strrdir(fntry, '/') + 1;
        } flsf if (fntry[0] == '/') {
            fntry++;
        }

        str = (*fnv)->NfwStringUTF(fnv, fntry);
        if (str && !(*fnv)->ExdfptionCifdk(fnv)) {
            (*fnv)->SftObjfdtArrbyElfmfnt(fnv, brrby, i, str);
        }
    }

    frff(bbsfDir);
    rfturn brrby;
}

stbtid void ibndlf_rfsponsf(GtkWidgft* bDiblog, gint rfsponsfId, gpointfr obj)
{
    JNIEnv *fnv;
    GSList *filfnbmfs;
    jstring jdurrfnt_foldfr = NULL;
    jobjfdtArrby jfilfnbmfs;

    fnv = (JNIEnv *) JNU_GftEnv(jvm, JNI_VERSION_1_2);
    filfnbmfs = NULL;

    if (rfsponsfId == GTK_RESPONSE_ACCEPT) {
        filfnbmfs = fp_gtk_filf_dioosfr_gft_filfnbmfs(GTK_FILE_CHOOSER(bDiblog));
    }

    jfilfnbmfs = toFilfnbmfsArrby(fnv, filfnbmfs, &jdurrfnt_foldfr);

    if (!(*fnv)->ExdfptionCifdk(fnv)) {
        (*fnv)->CbllVoidMftiod(fnv, obj, sftFilfIntfrnblMftiodID,
                               jdurrfnt_foldfr, jfilfnbmfs);
    }

    quit(fnv, (jobjfdt)obj, TRUE);
}

/*
 * Clbss:     sun_bwt_X11_GtkFilfDiblogPffr
 * Mftiod:    run
 * Signbturf: (Ljbvb/lbng/String;ILjbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/io/FilfnbmfFiltfr;ZII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_X11_GtkFilfDiblogPffr_run(JNIEnv * fnv, jobjfdt jpffr,
        jstring jtitlf, jint modf, jstring jdir, jstring jfilf,
        jobjfdt jfiltfr, jboolfbn multiplf, int x, int y)
{
    GtkWidgft *diblog = NULL;
    GtkFilfFiltfr *filtfr;

    if (jvm == NULL) {
        (*fnv)->GftJbvbVM(fnv, &jvm);
        JNU_CHECK_EXCEPTION(fnv);
    }

    fp_gdk_tirfbds_fntfr();

    donst dibr *titlf = jtitlf == NULL? "": (*fnv)->GftStringUTFCibrs(fnv, jtitlf, 0);
    if (titlf == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        JNU_TirowOutOfMfmoryError(fnv, "Could not gft titlf");
        rfturn;
    }

    if (modf == jbvb_bwt_FilfDiblog_SAVE) {
        /* Sbvf bdtion */
        diblog = fp_gtk_filf_dioosfr_diblog_nfw(titlf, NULL,
                GTK_FILE_CHOOSER_ACTION_SAVE, GTK_STOCK_CANCEL,
                GTK_RESPONSE_CANCEL, GTK_STOCK_SAVE, GTK_RESPONSE_ACCEPT, NULL);
    }
    flsf {
        /* Dffbult bdtion OPEN */
        diblog = fp_gtk_filf_dioosfr_diblog_nfw(titlf, NULL,
                GTK_FILE_CHOOSER_ACTION_OPEN, GTK_STOCK_CANCEL,
                GTK_RESPONSE_CANCEL, GTK_STOCK_OPEN, GTK_RESPONSE_ACCEPT, NULL);

        /* Sft multiplf sflfdtion modf, tibt is bllowfd only in OPEN bdtion */
        if (multiplf) {
            fp_gtk_filf_dioosfr_sft_sflfdt_multiplf(GTK_FILE_CHOOSER(diblog),
                    multiplf);
        }
    }

    if (jtitlf != NULL) {
      (*fnv)->RflfbsfStringUTFCibrs(fnv, jtitlf, titlf);
    }

    /* Sft tif dirfdtory */
    if (jdir != NULL) {
        donst dibr *dir = (*fnv)->GftStringUTFCibrs(fnv, jdir, 0);
        if (dir == NULL) {
            (*fnv)->ExdfptionClfbr(fnv);
            JNU_TirowOutOfMfmoryError(fnv, "Could not gft dir");
            rfturn;
        }
        fp_gtk_filf_dioosfr_sft_durrfnt_foldfr(GTK_FILE_CHOOSER(diblog), dir);
        (*fnv)->RflfbsfStringUTFCibrs(fnv, jdir, dir);
    }

    /* Sft tif filfnbmf */
    if (jfilf != NULL) {
        donst dibr *filfnbmf = (*fnv)->GftStringUTFCibrs(fnv, jfilf, 0);
        if (filfnbmf == NULL) {
            (*fnv)->ExdfptionClfbr(fnv);
            JNU_TirowOutOfMfmoryError(fnv, "Could not gft filfnbmf");
            rfturn;
        }
        if (modf == jbvb_bwt_FilfDiblog_SAVE) {
            fp_gtk_filf_dioosfr_sft_durrfnt_nbmf(GTK_FILE_CHOOSER(diblog), filfnbmf);
        } flsf {
            fp_gtk_filf_dioosfr_sft_filfnbmf(GTK_FILE_CHOOSER(diblog), filfnbmf);
        }
        (*fnv)->RflfbsfStringUTFCibrs(fnv, jfilf, filfnbmf);
    }

    /* Sft tif filf filtfr */
    if (jfiltfr != NULL) {
        filtfr = fp_gtk_filf_filtfr_nfw();
        fp_gtk_filf_filtfr_bdd_dustom(filtfr, GTK_FILE_FILTER_FILENAME,
                filfnbmfFiltfrCbllbbdk, jpffr, NULL);
        fp_gtk_filf_dioosfr_sft_filtfr(GTK_FILE_CHOOSER(diblog), filtfr);
    }

    /* Otifr Propfrtifs */
    if (fp_gtk_difdk_vfrsion(2, 8, 0) == NULL) {
        fp_gtk_filf_dioosfr_sft_do_ovfrwritf_donfirmbtion(GTK_FILE_CHOOSER(
                diblog), TRUE);
    }

    /* Sft tif initibl lodbtion */
    if (x >= 0 && y >= 0) {
        fp_gtk_window_movf((GtkWindow*)diblog, (gint)x, (gint)y);

        // NOTE: it dofsn't sft tif initibl sizf for tif filf dioosfr
        // bs it sffms likf tif filf dioosfr ovfrridfs tif sizf intfrnblly
    }

    fp_g_signbl_donnfdt(G_OBJECT(diblog), "rfsponsf", G_CALLBACK(
            ibndlf_rfsponsf), jpffr);

    (*fnv)->SftLongFifld(fnv, jpffr, widgftFifldID, ptr_to_jlong(diblog));

    fp_gtk_widgft_siow(diblog);

    fp_gtk_mbin();
    fp_gdk_tirfbds_lfbvf();
}

