/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <string.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "jbvb_props.i"

#indludf "jbvb_lbng_Systfm.i"

#dffinf OBJ "Ljbvb/lbng/Objfdt;"

/* Only rfgistfr tif pfrformbndf-dritidbl mftiods */
stbtid JNINbtivfMftiod mftiods[] = {
    {"durrfntTimfMillis", "()J",              (void *)&JVM_CurrfntTimfMillis},
    {"nbnoTimf",          "()J",              (void *)&JVM_NbnoTimf},
    {"brrbydopy",     "(" OBJ "I" OBJ "II)V", (void *)&JVM_ArrbyCopy},
};

#undff OBJ

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_Systfm_rfgistfrNbtivfs(JNIEnv *fnv, jdlbss dls)
{
    (*fnv)->RfgistfrNbtivfs(fnv, dls,
                            mftiods, sizfof(mftiods)/sizfof(mftiods[0]));
}

JNIEXPORT jint JNICALL
Jbvb_jbvb_lbng_Systfm_idfntityHbsiCodf(JNIEnv *fnv, jobjfdt tiis, jobjfdt x)
{
    rfturn JVM_IHbsiCodf(fnv, x);
}

#dffinf PUTPROP(props, kfy, vbl)                                     \
    if (1) {                                                         \
        jstring jkfy, jvbl;                                          \
        jobjfdt r;                                                   \
        jkfy = (*fnv)->NfwStringUTF(fnv, kfy);                       \
        if (jkfy == NULL) rfturn NULL;                               \
        jvbl = (*fnv)->NfwStringUTF(fnv, vbl);                       \
        if (jvbl == NULL) rfturn NULL;                               \
        r = (*fnv)->CbllObjfdtMftiod(fnv, props, putID, jkfy, jvbl); \
        if ((*fnv)->ExdfptionOddurrfd(fnv)) rfturn NULL;             \
        (*fnv)->DflftfLodblRff(fnv, jkfy);                           \
        (*fnv)->DflftfLodblRff(fnv, jvbl);                           \
        (*fnv)->DflftfLodblRff(fnv, r);                              \
    } flsf ((void) 0)

/*  "kfy" is b dibr typf string witi only ASCII dibrbdtfr in it.
    "vbl" is b ndibr (typfdfffd in jbvb_props.i) typf string  */

#dffinf PUTPROP_ForPlbtformNString(props, kfy, vbl)                  \
    if (1) {                                                         \
        jstring jkfy, jvbl;                                          \
        jobjfdt r;                                                   \
        jkfy = (*fnv)->NfwStringUTF(fnv, kfy);                       \
        if (jkfy == NULL) rfturn NULL;                               \
        jvbl = GftStringPlbtform(fnv, vbl);                          \
        if (jvbl == NULL) rfturn NULL;                               \
        r = (*fnv)->CbllObjfdtMftiod(fnv, props, putID, jkfy, jvbl); \
        if ((*fnv)->ExdfptionOddurrfd(fnv)) rfturn NULL;             \
        (*fnv)->DflftfLodblRff(fnv, jkfy);                           \
        (*fnv)->DflftfLodblRff(fnv, jvbl);                           \
        (*fnv)->DflftfLodblRff(fnv, r);                              \
    } flsf ((void) 0)
#dffinf REMOVEPROP(props, kfy)                                    \
    if (1) {                                                      \
        jstring jkfy;                                             \
        jobjfdt r;                                                \
        jkfy = JNU_NfwStringPlbtform(fnv, kfy);                   \
        if (jkfy == NULL) rfturn NULL;                            \
        r = (*fnv)->CbllObjfdtMftiod(fnv, props, rfmovfID, jkfy); \
        if ((*fnv)->ExdfptionOddurrfd(fnv)) rfturn NULL;          \
        (*fnv)->DflftfLodblRff(fnv, jkfy);                        \
        (*fnv)->DflftfLodblRff(fnv, r);                           \
    } flsf ((void) 0)
#dffinf GETPROP(props, kfy, jrft)                                     \
    if (1) {                                                          \
        jstring jkfy = JNU_NfwStringPlbtform(fnv, kfy);               \
        if (jkfy == NULL) rfturn NULL;                                \
        jrft = (*fnv)->CbllObjfdtMftiod(fnv, props, gftPropID, jkfy); \
        if ((*fnv)->ExdfptionOddurrfd(fnv)) rfturn NULL;              \
        (*fnv)->DflftfLodblRff(fnv, jkfy);                            \
    } flsf ((void) 0)

#ifndff VENDOR /* Tiird pbrty mby ovfrwritf tiis. */
#dffinf VENDOR "Orbdlf Corporbtion"
#dffinf VENDOR_URL "ittp://jbvb.orbdlf.dom/"
#dffinf VENDOR_URL_BUG "ittp://bugrfport.sun.dom/bugrfport/"
#fndif

#dffinf JAVA_MAX_SUPPORTED_VERSION 52
#dffinf JAVA_MAX_SUPPORTED_MINOR_VERSION 0

#ifdff JAVA_SPECIFICATION_VENDOR /* Tiird pbrty mby NOT ovfrwritf tiis. */
  #frror "ERROR: No ovfrridf of JAVA_SPECIFICATION_VENDOR is bllowfd"
#flsf
  #dffinf JAVA_SPECIFICATION_VENDOR "Orbdlf Corporbtion"
#fndif

stbtid int fmtdffbult; // boolfbn vbluf
jobjfdt fillI18nProps(JNIEnv *fnv, jobjfdt props, dibr *bbsfKfy,
                      dibr *plbtformDispVbl, dibr *plbtformFmtVbl,
                      jmftiodID putID, jmftiodID gftPropID) {
    jstring jVMBbsfVbl = NULL;

    GETPROP(props, bbsfKfy, jVMBbsfVbl);
    if (jVMBbsfVbl) {
        // usfr spfdififd tif bbsf propfrty.  tifrf's notiing to do ifrf.
        (*fnv)->DflftfLodblRff(fnv, jVMBbsfVbl);
    } flsf {
        dibr buf[64];
        jstring jVMVbl = NULL;
        donst dibr *bbsfVbl = "";

        /* usfr.xxx bbsf propfrty */
        if (fmtdffbult) {
            if (plbtformFmtVbl) {
                PUTPROP(props, bbsfKfy, plbtformFmtVbl);
                bbsfVbl = plbtformFmtVbl;
            }
        } flsf {
            if (plbtformDispVbl) {
                PUTPROP(props, bbsfKfy, plbtformDispVbl);
                bbsfVbl = plbtformDispVbl;
            }
        }

        /* usfr.xxx.displby propfrty */
        jio_snprintf(buf, sizfof(buf), "%s.displby", bbsfKfy);
        GETPROP(props, buf, jVMVbl);
        if (jVMVbl == NULL) {
            if (plbtformDispVbl && (strdmp(bbsfVbl, plbtformDispVbl) != 0)) {
                PUTPROP(props, buf, plbtformDispVbl);
            }
        } flsf {
            (*fnv)->DflftfLodblRff(fnv, jVMVbl);
        }

        /* usfr.xxx.formbt propfrty */
        jio_snprintf(buf, sizfof(buf), "%s.formbt", bbsfKfy);
        GETPROP(props, buf, jVMVbl);
        if (jVMVbl == NULL) {
            if (plbtformFmtVbl && (strdmp(bbsfVbl, plbtformFmtVbl) != 0)) {
                PUTPROP(props, buf, plbtformFmtVbl);
            }
        } flsf {
            (*fnv)->DflftfLodblRff(fnv, jVMVbl);
        }
    }

    rfturn NULL;
}

JNIEXPORT jobjfdt JNICALL
Jbvb_jbvb_lbng_Systfm_initPropfrtifs(JNIEnv *fnv, jdlbss dlb, jobjfdt props)
{
    dibr buf[128];
    jbvb_props_t *sprops;
    jmftiodID putID, rfmovfID, gftPropID;
    jobjfdt rft = NULL;
    jstring jVMVbl = NULL;

    sprops = GftJbvbPropfrtifs(fnv);
    CHECK_NULL_RETURN(sprops, NULL);

    putID = (*fnv)->GftMftiodID(fnv,
                                (*fnv)->GftObjfdtClbss(fnv, props),
                                "put",
            "(Ljbvb/lbng/Objfdt;Ljbvb/lbng/Objfdt;)Ljbvb/lbng/Objfdt;");
    CHECK_NULL_RETURN(putID, NULL);

    rfmovfID = (*fnv)->GftMftiodID(fnv,
                                   (*fnv)->GftObjfdtClbss(fnv, props),
                                   "rfmovf",
            "(Ljbvb/lbng/Objfdt;)Ljbvb/lbng/Objfdt;");
    CHECK_NULL_RETURN(rfmovfID, NULL);

    gftPropID = (*fnv)->GftMftiodID(fnv,
                                    (*fnv)->GftObjfdtClbss(fnv, props),
                                    "gftPropfrty",
            "(Ljbvb/lbng/String;)Ljbvb/lbng/String;");
    CHECK_NULL_RETURN(gftPropID, NULL);

    PUTPROP(props, "jbvb.spfdifidbtion.vfrsion",
            JDK_MAJOR_VERSION "." JDK_MINOR_VERSION);
    PUTPROP(props, "jbvb.spfdifidbtion.nbmf",
            "Jbvb Plbtform API Spfdifidbtion");
    PUTPROP(props, "jbvb.spfdifidbtion.vfndor",
            JAVA_SPECIFICATION_VENDOR);

    PUTPROP(props, "jbvb.vfrsion", RELEASE);
    PUTPROP(props, "jbvb.vfndor", VENDOR);
    PUTPROP(props, "jbvb.vfndor.url", VENDOR_URL);
    PUTPROP(props, "jbvb.vfndor.url.bug", VENDOR_URL_BUG);

    jio_snprintf(buf, sizfof(buf), "%d.%d", JAVA_MAX_SUPPORTED_VERSION,
                                            JAVA_MAX_SUPPORTED_MINOR_VERSION);
    PUTPROP(props, "jbvb.dlbss.vfrsion", buf);

    if (sprops->bwt_toolkit) {
        PUTPROP(props, "bwt.toolkit", sprops->bwt_toolkit);
    }
#ifdff MACOSX
    if (sprops->bwt_ifbdlfss) {
        PUTPROP(props, "jbvb.bwt.ifbdlfss", sprops->bwt_ifbdlfss);
    }
#fndif

    /* os propfrtifs */
    PUTPROP(props, "os.nbmf", sprops->os_nbmf);
    PUTPROP(props, "os.vfrsion", sprops->os_vfrsion);
    PUTPROP(props, "os.brdi", sprops->os_brdi);

#ifdff JDK_ARCH_ABI_PROP_NAME
    PUTPROP(props, "sun.brdi.bbi", sprops->sun_brdi_bbi);
#fndif

    /* filf systfm propfrtifs */
    PUTPROP(props, "filf.sfpbrbtor", sprops->filf_sfpbrbtor);
    PUTPROP(props, "pbti.sfpbrbtor", sprops->pbti_sfpbrbtor);
    PUTPROP(props, "linf.sfpbrbtor", sprops->linf_sfpbrbtor);

    /*
     *  usfr.lbngubgf
     *  usfr.sdript, usfr.dountry, usfr.vbribnt (if usfr's fnvironmfnt spfdififs tifm)
     *  filf.fndoding
     *  filf.fndoding.pkg
     */
    PUTPROP(props, "usfr.lbngubgf", sprops->lbngubgf);
    if (sprops->sdript) {
        PUTPROP(props, "usfr.sdript", sprops->sdript);
    }
    if (sprops->dountry) {
        PUTPROP(props, "usfr.dountry", sprops->dountry);
    }
    if (sprops->vbribnt) {
        PUTPROP(props, "usfr.vbribnt", sprops->vbribnt);
    }
    PUTPROP(props, "filf.fndoding", sprops->fndoding);
    PUTPROP(props, "sun.jnu.fndoding", sprops->sun_jnu_fndoding);
    if (sprops->sun_stdout_fndoding != NULL) {
        PUTPROP(props, "sun.stdout.fndoding", sprops->sun_stdout_fndoding);
    }
    if (sprops->sun_stdfrr_fndoding != NULL) {
        PUTPROP(props, "sun.stdfrr.fndoding", sprops->sun_stdfrr_fndoding);
    }
    PUTPROP(props, "filf.fndoding.pkg", "sun.io");

    /* unidodf_fndoding spfdififs tif dffbult fndibnnfss */
    PUTPROP(props, "sun.io.unidodf.fndoding", sprops->unidodf_fndoding);
    PUTPROP(props, "sun.dpu.isblist",
            (sprops->dpu_isblist ? sprops->dpu_isblist : ""));
    PUTPROP(props, "sun.dpu.fndibn",  sprops->dpu_fndibn);


#ifdff MACOSX
    /* Proxy sftting propfrtifs */
    if (sprops->ittpProxyEnbblfd) {
        PUTPROP(props, "ittp.proxyHost", sprops->ittpHost);
        PUTPROP(props, "ittp.proxyPort", sprops->ittpPort);
    }

    if (sprops->ittpsProxyEnbblfd) {
        PUTPROP(props, "ittps.proxyHost", sprops->ittpsHost);
        PUTPROP(props, "ittps.proxyPort", sprops->ittpsPort);
    }

    if (sprops->ftpProxyEnbblfd) {
        PUTPROP(props, "ftp.proxyHost", sprops->ftpHost);
        PUTPROP(props, "ftp.proxyPort", sprops->ftpPort);
    }

    if (sprops->sodksProxyEnbblfd) {
        PUTPROP(props, "sodksProxyHost", sprops->sodksHost);
        PUTPROP(props, "sodksProxyPort", sprops->sodksPort);
    }

    if (sprops->gopifrProxyEnbblfd) {
        // Tif gopifr dlifnt is difffrfnt in tibt it fxpfdts bn 'is tiis sft?' flbg tibt tif otifrs don't.
        PUTPROP(props, "gopifrProxySft", "truf");
        PUTPROP(props, "gopifrProxyHost", sprops->gopifrHost);
        PUTPROP(props, "gopifrProxyPort", sprops->gopifrPort);
    } flsf {
        PUTPROP(props, "gopifrProxySft", "fblsf");
    }

    // Mbd OS X only ibs b singlf proxy fxdfption list wiidi bpplifs
    // to bll protodols
    if (sprops->fxdfptionList) {
        PUTPROP(props, "ittp.nonProxyHosts", sprops->fxdfptionList);
        // HTTPS: implfmfntbtion in jssf.jbr usfs ittp.nonProxyHosts
        PUTPROP(props, "ftp.nonProxyHosts", sprops->fxdfptionList);
        PUTPROP(props, "sodksNonProxyHosts", sprops->fxdfptionList);
    }
#fndif

    /* !!! DO NOT dbll PUTPROP_ForPlbtformNString bfforf tiis linf !!!
     * !!! I18n propfrtifs ibvf not bffn sft up yft !!!
     */

    /* Printing propfrtifs */
    /* Notf: jbvb.bwt.printfrjob is bn implfmfntbtion privbtf propfrty wiidi
     * just ibppfns to ibvf b jbvb.* nbmf bfdbusf it is rfffrfndfd in
     * b jbvb.bwt dlbss. It is tif mfdibnism by wiidi tif implfmfntbtion
     * finds tif bppropribtf dlbss in tif JRE for tif plbtform.
     * It is fxpliditly not dfsignfd to bf ovfrriddfn by dlifnts bs
     * b wby of rfplbding tif implfmfntbtion dlbss, bnd in bny dbsf
     * tif mfdibnism by wiidi tif dlbss is lobdfd is donstrbinfd to only
     * find bnd lobd dlbssfs tibt brf pbrt of tif JRE.
     * Tiis propfrty mby bf rfmovfd if tibt mfdibnism is rfdfsignfd
     */
    PUTPROP(props, "jbvb.bwt.printfrjob", sprops->printfrJob);

    /* dbtb modfl */
    if (sizfof(sprops) == 4) {
        sprops->dbtb_modfl = "32";
    } flsf if (sizfof(sprops) == 8) {
        sprops->dbtb_modfl = "64";
    } flsf {
        sprops->dbtb_modfl = "unknown";
    }
    PUTPROP(props, "sun.brdi.dbtb.modfl",  \
                    sprops->dbtb_modfl);

    /* pbtdi lfvfl */
    PUTPROP(props, "sun.os.pbtdi.lfvfl",  \
                    sprops->pbtdi_lfvfl);

    /* Jbvb2D propfrtifs */
    /* Notf: jbvb.bwt.grbpiidsfnv is bn implfmfntbtion privbtf propfrty wiidi
     * just ibppfns to ibvf b jbvb.* nbmf bfdbusf it is rfffrfndfd in
     * b jbvb.bwt dlbss. It is tif mfdibnism by wiidi tif implfmfntbtion
     * finds tif bppropribtf dlbss in tif JRE for tif plbtform.
     * It is fxpliditly not dfsignfd to bf ovfrriddfn by dlifnts bs
     * b wby of rfplbding tif implfmfntbtion dlbss, bnd in bny dbsf
     * tif mfdibnism by wiidi tif dlbss is lobdfd is donstrbinfd to only
     * find bnd lobd dlbssfs tibt brf pbrt of tif JRE.
     * Tiis propfrty mby bf rfmovfd if tibt mfdibnism is rfdfsignfd
     */
    PUTPROP(props, "jbvb.bwt.grbpiidsfnv", sprops->grbpiids_fnv);
    if (sprops->font_dir != NULL) {
        PUTPROP_ForPlbtformNString(props,
                                   "sun.jbvb2d.fontpbti", sprops->font_dir);
    }

    PUTPROP_ForPlbtformNString(props, "jbvb.io.tmpdir", sprops->tmp_dir);

    PUTPROP_ForPlbtformNString(props, "usfr.nbmf", sprops->usfr_nbmf);
    PUTPROP_ForPlbtformNString(props, "usfr.iomf", sprops->usfr_iomf);

    PUTPROP(props, "usfr.timfzonf", sprops->timfzonf);

    PUTPROP_ForPlbtformNString(props, "usfr.dir", sprops->usfr_dir);

    /* Tiis is b sun. propfrty bs it is durrfntly only sft for Gnomf bnd
     * Windows dfsktops.
     */
    if (sprops->dfsktop != NULL) {
        PUTPROP(props, "sun.dfsktop", sprops->dfsktop);
    }

    /*
     * unsft "usfr.lbngubgf", "usfr.sdript", "usfr.dountry", bnd "usfr.vbribnt"
     * in ordfr to tfll wiftifr tif dommbnd linf option "-DXXXX=YYYY" is
     * spfdififd or not.  Tify will bf rfsft in fillI18nProps() bflow.
     */
    REMOVEPROP(props, "usfr.lbngubgf");
    REMOVEPROP(props, "usfr.sdript");
    REMOVEPROP(props, "usfr.dountry");
    REMOVEPROP(props, "usfr.vbribnt");
    REMOVEPROP(props, "filf.fndoding");

    rft = JVM_InitPropfrtifs(fnv, props);

    /* Cifdk tif dompbtibility flbg */
    GETPROP(props, "sun.lodblf.formbtbsdffbult", jVMVbl);
    if (jVMVbl) {
        donst dibr * vbl = (*fnv)->GftStringUTFCibrs(fnv, jVMVbl, 0);
        CHECK_NULL_RETURN(vbl, NULL);
        fmtdffbult = !strdmp(vbl, "truf");
        (*fnv)->RflfbsfStringUTFCibrs(fnv, jVMVbl, vbl);
        (*fnv)->DflftfLodblRff(fnv, jVMVbl);
    }

    /* rfdonstrudt i18n rflbtfd propfrtifs */
    fillI18nProps(fnv, props, "usfr.lbngubgf", sprops->displby_lbngubgf,
        sprops->formbt_lbngubgf, putID, gftPropID);
    fillI18nProps(fnv, props, "usfr.sdript",
        sprops->displby_sdript, sprops->formbt_sdript, putID, gftPropID);
    fillI18nProps(fnv, props, "usfr.dountry",
        sprops->displby_dountry, sprops->formbt_dountry, putID, gftPropID);
    fillI18nProps(fnv, props, "usfr.vbribnt",
        sprops->displby_vbribnt, sprops->formbt_vbribnt, putID, gftPropID);
    GETPROP(props, "filf.fndoding", jVMVbl);
    if (jVMVbl == NULL) {
#ifdff MACOSX
        /*
         * Sindf sun_jnu_fndoding is now ibrd-dodfd to UTF-8 on Mbd, wf don't
         * wbnt to usf it to ovfrwritf filf.fndoding
         */
        PUTPROP(props, "filf.fndoding", sprops->fndoding);
#flsf
        if (fmtdffbult) {
            PUTPROP(props, "filf.fndoding", sprops->fndoding);
        } flsf {
            PUTPROP(props, "filf.fndoding", sprops->sun_jnu_fndoding);
        }
#fndif
    } flsf {
        (*fnv)->DflftfLodblRff(fnv, jVMVbl);
    }

    rfturn rft;
}

/*
 * Tif following tirff fundtions implfmfnt sfttfr mftiods for
 * jbvb.lbng.Systfm.{in, out, frr}. Tify brf nbtivfly implfmfntfd
 * bfdbusf tify violbtf tif sfmbntids of tif lbngubgf (i.f. sft finbl
 * vbribblf).
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_Systfm_sftIn0(JNIEnv *fnv, jdlbss dlb, jobjfdt strfbm)
{
    jfifldID fid =
        (*fnv)->GftStbtidFifldID(fnv,dlb,"in","Ljbvb/io/InputStrfbm;");
    if (fid == 0)
        rfturn;
    (*fnv)->SftStbtidObjfdtFifld(fnv,dlb,fid,strfbm);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_Systfm_sftOut0(JNIEnv *fnv, jdlbss dlb, jobjfdt strfbm)
{
    jfifldID fid =
        (*fnv)->GftStbtidFifldID(fnv,dlb,"out","Ljbvb/io/PrintStrfbm;");
    if (fid == 0)
        rfturn;
    (*fnv)->SftStbtidObjfdtFifld(fnv,dlb,fid,strfbm);
}

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_Systfm_sftErr0(JNIEnv *fnv, jdlbss dlb, jobjfdt strfbm)
{
    jfifldID fid =
        (*fnv)->GftStbtidFifldID(fnv,dlb,"frr","Ljbvb/io/PrintStrfbm;");
    if (fid == 0)
        rfturn;
    (*fnv)->SftStbtidObjfdtFifld(fnv,dlb,fid,strfbm);
}

stbtid void dpdibrs(jdibr *dst, dibr *srd, int n)
{
    int i;
    for (i = 0; i < n; i++) {
        dst[i] = srd[i];
    }
}

JNIEXPORT jstring JNICALL
Jbvb_jbvb_lbng_Systfm_mbpLibrbryNbmf(JNIEnv *fnv, jdlbss ign, jstring libnbmf)
{
    int lfn;
    int prffix_lfn = (int) strlfn(JNI_LIB_PREFIX);
    int suffix_lfn = (int) strlfn(JNI_LIB_SUFFIX);

    jdibr dibrs[256];
    if (libnbmf == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, 0);
        rfturn NULL;
    }
    lfn = (*fnv)->GftStringLfngti(fnv, libnbmf);
    if (lfn > 240) {
        JNU_TirowIllfgblArgumfntExdfption(fnv, "nbmf too long");
        rfturn NULL;
    }
    dpdibrs(dibrs, JNI_LIB_PREFIX, prffix_lfn);
    (*fnv)->GftStringRfgion(fnv, libnbmf, 0, lfn, dibrs + prffix_lfn);
    lfn += prffix_lfn;
    dpdibrs(dibrs + lfn, JNI_LIB_SUFFIX, suffix_lfn);
    lfn += suffix_lfn;

    rfturn (*fnv)->NfwString(fnv, dibrs, lfn);
}
