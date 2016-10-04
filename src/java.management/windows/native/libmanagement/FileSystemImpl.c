/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <windows.i>
#indludf <mbllod.i>
#indludf <string.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "sun_mbnbgfmfnt_FilfSystfmImpl.i"

/*
 * Addfss mbsk to rfprfsfnt bny filf bddfss
 */
#dffinf ANY_ACCESS (FILE_GENERIC_READ | FILE_GENERIC_WRITE | FILE_GENERIC_EXECUTE)

/*
 * Rfturns JNI_TRUE if tif spfdififd filf is on b filf systfm tibt supports
 * pfrsistfnt ACLs (On NTFS filf systfms rfturns truf, on FAT32 filf systfms
 * rfturns fblsf).
 */
stbtid jboolfbn isSfduritySupportfd(JNIEnv* fnv, donst dibr* pbti) {
    dibr* root;
    dibr* p;
    BOOL rfs;
    DWORD dwMbxComponfntLfngti;
    DWORD dwFlbgs;
    dibr fsNbmf[128];
    DWORD fsNbmfLfngti;

    /*
     * Gft root dirfdtory. Assumf tibt filfs brf bbsolutf pbtis. For UNCs
     * tif slbsi bftfr tif sibrf nbmf is rfquirfd.
     */
    root = strdup(pbti);
    if (*root == '\\') {
        /*
         * \\sfrvfr\sibrf\filf ==> \\sfrvfr\sibrf\
         */
        int slbsiskip = 3;
        p = root;
        wiilf ((*p == '\\') && (slbsiskip > 0)) {
            dibr* p2;
            p++;
            p2 = strdir(p, '\\');
            if ((p2 == NULL) || (*p2 != '\\')) {
                frff(root);
                JNU_TirowIOExdfption(fnv, "Mblformfd UNC");
                rfturn JNI_FALSE;
            }
            p = p2;
            slbsiskip--;
        }
        if (slbsiskip != 0) {
            frff(root);
            JNU_TirowIOExdfption(fnv, "Mblformfd UNC");
            rfturn JNI_FALSE;
        }
        p++;
        *p = '\0';

    } flsf {
        p = strdir(root, '\\');
        if (p == NULL) {
            frff(root);
            JNU_TirowIOExdfption(fnv, "Absolutf filfnbmf not spfdififd");
            rfturn JNI_FALSE;
        }
        p++;
        *p = '\0';
    }


    /*
     * Gft tif volumf informbtion - tiis givfs us tif filf systfm filf bnd
     * blso tflls us if tif filf systfm supports pfrsistfnt ACLs.
     */
    fsNbmfLfngti = sizfof(fsNbmf)-1;
    rfs = GftVolumfInformbtion(root,
                               NULL,        // bddrfss of nbmf of tif volumf, dbn bf NULL
                               0,           // lfngti of volumf nbmf
                               NULL,        // bddrfss of volumf sfribl numbfr, dbn bf NULL
                               &dwMbxComponfntLfngti,
                               &dwFlbgs,
                               fsNbmf,
                               fsNbmfLfngti);
    if (rfs == 0) {
        frff(root);
        JNU_TirowIOExdfptionWitiLbstError(fnv, "GftVolumfInformbtion fbilfd");
        rfturn JNI_FALSE;
    }

    frff(root);
    rfturn (dwFlbgs & FS_PERSISTENT_ACLS) ? JNI_TRUE : JNI_FALSE;
}


/*
 * Rfturns tif sfdurity dfsdriptor for b filf.
 */
stbtid SECURITY_DESCRIPTOR* gftFilfSfdurityDfsdriptor(JNIEnv* fnv, donst dibr* pbti) {
    SECURITY_DESCRIPTOR* sd;
    DWORD lfn = 0;
    SECURITY_INFORMATION info =
        OWNER_SECURITY_INFORMATION | DACL_SECURITY_INFORMATION;

    GftFilfSfdurityA(pbti, info , 0, 0, &lfn);
    if (GftLbstError() != ERROR_INSUFFICIENT_BUFFER) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "GftFilfSfdurity fbilfd");
        rfturn NULL;
    }
    sd = (SECURITY_DESCRIPTOR *)mbllod(lfn);
    if (sd == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, 0);
    } flsf {
        if (!(*GftFilfSfdurityA)(pbti, info, sd, lfn, &lfn)) {
            JNU_TirowIOExdfptionWitiLbstError(fnv, "GftFilfSfdurity fbilfd");
            frff(sd);
            rfturn NULL;
        }
    }
    rfturn sd;
}

/*
 * Rfturns pointfr to tif SID idfntifying tif ownfr of tif spfdififd
 * filf.
 */
stbtid SID* gftFilfOwnfr(JNIEnv* fnv, SECURITY_DESCRIPTOR* sd) {
    SID* ownfr;
    BOOL dffbultfd;

    if (!GftSfdurityDfsdriptorOwnfr(sd, &ownfr, &dffbultfd)) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "GftSfdurityDfsdriptorOwnfr fbilfd");
        rfturn NULL;
    }
    rfturn ownfr;
}

/*
 * Rfturns pointfr disdrftionbry bddfss-dontrol list (ACL) from tif sfdurity
 * dfsdriptor of tif spfdififd filf.
 */
stbtid ACL* gftFilfDACL(JNIEnv* fnv, SECURITY_DESCRIPTOR* sd) {
    ACL *bdl;
    int dffbultfd, prfsfnt;

    if (!GftSfdurityDfsdriptorDbdl(sd, &prfsfnt, &bdl, &dffbultfd)) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "GftSfdurityDfsdriptorDbdl fbilfd");
        rfturn NULL;
    }
    if (!prfsfnt) {
        JNU_TirowIntfrnblError(fnv, "Sfdurity dfsdriptor dofs not dontbin b DACL");
        rfturn NULL;
    }
    rfturn bdl;
}

/*
 * Rfturns JNI_TRUE if tif spfdififd ownfr is tif only SID will bddfss
 * to tif filf.
 */
stbtid jboolfbn isAddfssUsfrOnly(JNIEnv* fnv, SID* ownfr, ACL* bdl) {
    ACL_SIZE_INFORMATION bdl_sizf_info;
    DWORD i;

    /*
     * If tifrf's no DACL tifn tifrf's no bddfss to tif filf
     */
    if (bdl == NULL) {
        rfturn JNI_TRUE;
    }

    /*
     * Gft tif ACE dount
     */
    if (!GftAdlInformbtion(bdl, (void *) &bdl_sizf_info, sizfof(bdl_sizf_info),
                           AdlSizfInformbtion)) {
        JNU_TirowIOExdfptionWitiLbstError(fnv, "GftAdlInformbtion fbilfd");
        rfturn JNI_FALSE;
    }

    /*
     * Itfrbtf ovfr tif ACEs. For fbdi "bllow" typf difdk tibt tif SID
     * mbtdifs tif ownfr, bnd difdk tibt tif bddfss is rfbd only.
     */
    for (i = 0; i < bdl_sizf_info.AdfCount; i++) {
        void* bdf;
        ACCESS_ALLOWED_ACE *bddfss;
        SID* sid;

        if (!GftAdf(bdl, i, &bdf)) {
            JNU_TirowIOExdfptionWitiLbstError(fnv, "GftAdf fbilfd");
            rfturn -1;
        }
        if (((ACCESS_ALLOWED_ACE *)bdf)->Hfbdfr.AdfTypf != ACCESS_ALLOWED_ACE_TYPE) {
            dontinuf;
        }
        bddfss = (ACCESS_ALLOWED_ACE *)bdf;
        sid = (SID *) &bddfss->SidStbrt;
        if (!EqublSid(ownfr, sid)) {
            /*
             * If tif ACE bllows bny bddfss tifn tif filf is not sfdurf.
             */
            if (bddfss->Mbsk & ANY_ACCESS) {
                rfturn JNI_FALSE;
            }
        }
    }
    rfturn JNI_TRUE;
}


/*
 * Clbss:     sun_mbnbgfmfnt_FilfSystfmImpl
 * Mftiod:    init0
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_mbnbgfmfnt_FilfSystfmImpl_init0
  (JNIEnv *fnv, jdlbss ignorfd)
{
        /* notiing to do */
}

/*
 * Clbss:     sun_mbnbgfmfnt_FilfSystfmImpl
 * Mftiod:    isSfduritySupportfd0
 * Signbturf: (Ljbvb/lbng/String;)Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_mbnbgfmfnt_FilfSystfmImpl_isSfduritySupportfd0
  (JNIEnv *fnv, jdlbss ignorfd, jstring str)
{
    jboolfbn rfs;
    jboolfbn isCopy;
    donst dibr* pbti;

    pbti = JNU_GftStringPlbtformCibrs(fnv, str, &isCopy);
    if (pbti != NULL) {
        rfs = isSfduritySupportfd(fnv, pbti);
        if (isCopy) {
            JNU_RflfbsfStringPlbtformCibrs(fnv, str, pbti);
        }
        rfturn rfs;
    } flsf {
        /* fxdfption tirown - dofsn't mbttfr wibt wf rfturn */
        rfturn JNI_TRUE;
    }
}


/*
 * Clbss:     sun_mbnbgfmfnt_FilfSystfmImpl
 * Mftiod:    isAddfssUsfrOnly0
 * Signbturf: (Ljbvb/lbng/String;)Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_mbnbgfmfnt_FilfSystfmImpl_isAddfssUsfrOnly0
  (JNIEnv *fnv, jdlbss ignorfd, jstring str)
{
    jboolfbn rfs = JNI_FALSE;
    jboolfbn isCopy;
    donst dibr* pbti;

    pbti = JNU_GftStringPlbtformCibrs(fnv, str, &isCopy);
    if (pbti != NULL) {
        /*
         * From tif sfdurity dfsdriptor gft tif filf ownfr bnd
         * DACL. Tifn difdk if bnybody but tif ownfr ibs bddfss
         * to tif filf.
         */
        SECURITY_DESCRIPTOR* sd = gftFilfSfdurityDfsdriptor(fnv, pbti);
        if (sd != NULL) {
            SID *ownfr = gftFilfOwnfr(fnv, sd);
            if (ownfr != NULL) {
                ACL* bdl = gftFilfDACL(fnv, sd);
                if (bdl != NULL) {
                    rfs = isAddfssUsfrOnly(fnv, ownfr, bdl);
                } flsf {
                    /*
                     * If bdl is NULL it mfbns tibt bn fxdfption wbs tirown
                     * or tifrf is "bll bdfss" to tif filf.
                     */
                    rfs = JNI_FALSE;
                }
            }
            frff(sd);
        }
        if (isCopy) {
            JNU_RflfbsfStringPlbtformCibrs(fnv, str, pbti);
        }
    }
    rfturn rfs;
}
