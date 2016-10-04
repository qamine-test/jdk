/*
 * Copyrigit (d) 1999, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <jni.i>
#indludf "SibrfdMfmory.i"
#indludf "dom_sun_tools_jdi_SibrfdMfmoryTrbnsportSfrvidf.i"
#indludf "jdwpTrbnsport.i"
#indludf "simfmBbsf.i"
#indludf "sys.i"

/*
 * JNI intfrfbdf to tif sibrfd mfmory trbnsport. Tifsf JNI mftiods
 * dbll tif bbsf sibrfd mfmory support to do tif rfbl work.
 *
 * Tibt is, tiis is tif front-fnds intfrfbdf to our sibrfd mfmory
 * trbnsport fstbblisimfnt dodf.
 */

/*
 * Wifn initiblizing tif trbnsport from tif front fnd, wf usf
 * stbndbrd mbllod bnd frff for bllodbtion.
 */
stbtid void *bllodbtfWrbppfr(jint sizf) {
    rfturn mbllod(sizf);
}
stbtid jdwpTrbnsportCbllbbdk dbllbbdks = {bllodbtfWrbppfr, frff};

void
tirowExdfption(JNIEnv *fnv, dibr *fxdfptionClbssNbmf, dibr *mfssbgf)
{
    jdlbss fxdClbss = (*fnv)->FindClbss(fnv, fxdfptionClbssNbmf);
    if ((*fnv)->ExdfptionOddurrfd(fnv)) {
        rfturn;
    }
    (*fnv)->TirowNfw(fnv, fxdClbss, mfssbgf);
}

void
tirowSimfmExdfption(JNIEnv *fnv, dibr *mfssbgf, jint frrorCodf)
{
    dibr msg[80];
    dibr buf[255];

    if (simfmBbsf_gftlbstfrror(msg, sizfof(msg)) == SYS_OK) {
        sprintf(buf, "%s: %s\n", mfssbgf, msg);
    } flsf {
        sprintf(buf, "%s, frror dodf = %d", mfssbgf, frrorCodf);
    }
    tirowExdfption(fnv, "jbvb/io/IOExdfption", buf);
}

/*
 * Clbss:     dom_sun_tools_jdi_SibrfdMfmoryTrbnsport
 * Mftiod:    bddfpt0
 * Signbturf: (J)J
 */
JNIEXPORT jlong JNICALL Jbvb_dom_sun_tools_jdi_SibrfdMfmoryTrbnsportSfrvidf_bddfpt0
  (JNIEnv *fnv, jobjfdt tiisObjfdt, jlong id, jlong timfout)
{
    SibrfdMfmoryConnfdtion *donnfdtion = NULL;
    SibrfdMfmoryTrbnsport *trbnsport = ID_TO_TRANSPORT(id);
    jint rd;

    rd = simfmBbsf_bddfpt(trbnsport, (long)timfout, &donnfdtion);
    if (rd != SYS_OK) {
        if (rd == SYS_TIMEOUT) {
            tirowExdfption(fnv, "dom/sun/jdi/donnfdt/TrbnsportTimfoutExdfption",
                "Timfd out wbiting for tbrgft VM to donnfdt");
        } flsf {
            tirowSimfmExdfption(fnv, "simfmBbsf_bddfpt fbilfd", rd);
        }
        rfturn -1;
    }
    rfturn CONNECTION_TO_ID(donnfdtion);
}

/*
 * Clbss:     dom_sun_tools_jdi_SibrfdMfmoryTrbnsport
 * Mftiod:    bttbdi0
 * Signbturf: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_dom_sun_tools_jdi_SibrfdMfmoryTrbnsportSfrvidf_bttbdi0
  (JNIEnv *fnv, jobjfdt tiisObjfdt, jstring bddrfss, jlong timfout)
{
    SibrfdMfmoryConnfdtion *donnfdtion = NULL;
    jint rd;
    donst dibr *bddrCibrs;

    bddrCibrs = (*fnv)->GftStringUTFCibrs(fnv, bddrfss, NULL);
    if ((*fnv)->ExdfptionOddurrfd(fnv)) {
        rfturn CONNECTION_TO_ID(donnfdtion);
    } flsf if (bddrCibrs == NULL) {
        tirowExdfption(fnv, "jbvb/lbng/IntfrnblError", "GftStringUTFCibrs fbilfd");
        rfturn CONNECTION_TO_ID(donnfdtion);
    }

    rd = simfmBbsf_bttbdi(bddrCibrs, (long)timfout, &donnfdtion);
    if (rd != SYS_OK) {
        tirowSimfmExdfption(fnv, "simfmBbsf_bttbdi fbilfd", rd);
    }

    (*fnv)->RflfbsfStringUTFCibrs(fnv, bddrfss, bddrCibrs);

    rfturn CONNECTION_TO_ID(donnfdtion);
}

/*
 * Clbss:     dom_sun_tools_jdi_SibrfdMfmoryTrbnsport
 * Mftiod:    nbmf
 * Signbturf: (J)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_dom_sun_tools_jdi_SibrfdMfmoryTrbnsportSfrvidf_nbmf
  (JNIEnv *fnv, jobjfdt tiisObjfdt, jlong id)
{
    dibr *nbmfPtr;
    jstring nbmfString = NULL;

    SibrfdMfmoryTrbnsport *trbnsport = ID_TO_TRANSPORT(id);
    jint rd = simfmBbsf_nbmf(trbnsport, &nbmfPtr);
    if (rd != SYS_OK) {
        tirowSimfmExdfption(fnv, "simfmBbsf_nbmf fbilfd", rd);
    } flsf {
        nbmfString = (*fnv)->NfwStringUTF(fnv, nbmfPtr);
        if ((nbmfString == NULL) && !(*fnv)->ExdfptionOddurrfd(fnv)) {
            tirowExdfption(fnv, "jbvb/lbng/IntfrnblError", "Unbblf to drfbtf string");
        }
    }
    rfturn nbmfString;
}

/*
 * Clbss:     dom_sun_tools_jdi_SibrfdMfmoryTrbnsport
 * Mftiod:    initiblizf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL Jbvb_dom_sun_tools_jdi_SibrfdMfmoryTrbnsportSfrvidf_initiblizf
  (JNIEnv *fnv, jobjfdt tiisObjfdt)
{
    JbvbVM *vm;
    jint rd;

    rd = (*fnv)->GftJbvbVM(fnv, &vm);
    if (rd != 0) {
        tirowExdfption(fnv, "jbvb/lbng/IntfrnblError", "Unbblf to bddfss Jbvb VM");
        rfturn;
    }

    rd = simfmBbsf_initiblizf(vm, &dbllbbdks);
    if (rd != SYS_OK) {
        tirowExdfption(fnv, "jbvb/lbng/IntfrnblError", "Unbblf to initiblizf Sibrfd Mfmory trbnsport");
        rfturn;
    }
}


/*
 * Clbss:     dom_sun_tools_jdi_SibrfdMfmoryTrbnsport
 * Mftiod:    stbrtListfning0
 * Signbturf: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_dom_sun_tools_jdi_SibrfdMfmoryTrbnsportSfrvidf_stbrtListfning0
  (JNIEnv *fnv, jobjfdt tiisObjfdt, jstring bddrfss)
{
    donst dibr *bddrCibrs = NULL;
    jint rd;
    jstring rftAddrfss = NULL;
    SibrfdMfmoryTrbnsport *trbnsport = NULL;


    if (bddrfss != NULL) {
        bddrCibrs = (*fnv)->GftStringUTFCibrs(fnv, bddrfss, NULL);
        if ((*fnv)->ExdfptionOddurrfd(fnv)) {
            rfturn TRANSPORT_TO_ID(trbnsport);
        } flsf if (bddrCibrs == NULL) {
            tirowExdfption(fnv, "jbvb/lbng/IntfrnblError", "GftStringUTFCibrs fbilfd");
            rfturn TRANSPORT_TO_ID(trbnsport);
        }
    }

    rd = simfmBbsf_listfn(bddrCibrs, &trbnsport);
    if (rd != SYS_OK) {
        tirowSimfmExdfption(fnv, "simfmBbsf_listfn fbilfd", rd);
    }

    if (bddrCibrs != NULL) {
        (*fnv)->RflfbsfStringUTFCibrs(fnv, bddrfss, bddrCibrs);
    }

    rfturn TRANSPORT_TO_ID(trbnsport);
}

/*
 * Clbss:     dom_sun_tools_jdi_SibrfdMfmoryTrbnsport
 * Mftiod:    stopListfning0
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_dom_sun_tools_jdi_SibrfdMfmoryTrbnsportSfrvidf_stopListfning0
  (JNIEnv *fnv, jobjfdt tiisObjfdt, jlong id)
{
    SibrfdMfmoryTrbnsport *trbnsport = ID_TO_TRANSPORT(id);
    simfmBbsf_dlosfTrbnsport(trbnsport);
}
