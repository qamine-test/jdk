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

//#dffinf USE_TRACE
//#dffinf USE_ERROR


#indludf <jni.i>
#indludf <jni_util.i>
#indludf "SoundDffs.i"
#indludf "DirfdtAudio.i"
#indludf "Utilitifs.i"
#indludf "dom_sun_mfdib_sound_DirfdtAudioDfvidfProvidfr.i"


//////////////////////////////////////////// DirfdtAudioDfvidfProvidfr ////////////////////////////////////////////

int gftDirfdtAudioDfvidfDfsdription(int mixfrIndfx, DirfdtAudioDfvidfDfsdription* dfsd) {
    dfsd->dfvidfID = 0;
    dfsd->mbxSimulLinfs = 0;
    strdpy(dfsd->nbmf, "Unknown Nbmf");
    strdpy(dfsd->vfndor, "Unknown Vfndor");
    strdpy(dfsd->dfsdription, "Unknown Dfsdription");
    strdpy(dfsd->vfrsion, "Unknown Vfrsion");
#if USE_DAUDIO == TRUE
    DAUDIO_GftDirfdtAudioDfvidfDfsdription(mixfrIndfx, dfsd);
#fndif // USE_DAUDIO
    rfturn TRUE;
}

JNIEXPORT jint JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidfProvidfr_nGftNumDfvidfs(JNIEnv *fnv, jdlbss dls) {
    INT32 numDfvidfs = 0;

    TRACE0("Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidfProvidfr_nGftNumDfvidfs.\n");

#if USE_DAUDIO == TRUE
    numDfvidfs = DAUDIO_GftDirfdtAudioDfvidfCount();
#fndif // USE_DAUDIO

    TRACE1("Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidfProvidfr_nGftNumDfvidfs rfturning %d.\n", (int) numDfvidfs);

    rfturn (jint)numDfvidfs;
}

JNIEXPORT jobjfdt JNICALL Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidfProvidfr_nNfwDirfdtAudioDfvidfInfo
    (JNIEnv *fnv, jdlbss dls, jint mixfrIndfx) {

    jdlbss dirfdtAudioDfvidfInfoClbss;
    jmftiodID dirfdtAudioDfvidfInfoConstrudtor;
    DirfdtAudioDfvidfDfsdription dfsd;
    jobjfdt info = NULL;
    jstring nbmf;
    jstring vfndor;
    jstring dfsdription;
    jstring vfrsion;

    TRACE1("Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidfProvidfr_nNfwDirfdtAudioDfvidfInfo(%d).\n", mixfrIndfx);

    // rftrifvf dlbss bnd donstrudtor of DirfdtAudioDfvidfProvidfr.DirfdtAudioDfvidfInfo
    dirfdtAudioDfvidfInfoClbss = (*fnv)->FindClbss(fnv, IMPLEMENTATION_PACKAGE_NAME"/DirfdtAudioDfvidfProvidfr$DirfdtAudioDfvidfInfo");
    if (dirfdtAudioDfvidfInfoClbss == NULL) {
        ERROR0("Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidfProvidfr_nNfwDirfdtAudioDfvidfInfo: dirfdtAudioDfvidfInfoClbss is NULL\n");
        rfturn NULL;
    }
    dirfdtAudioDfvidfInfoConstrudtor = (*fnv)->GftMftiodID(fnv, dirfdtAudioDfvidfInfoClbss, "<init>",
                  "(IIILjbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;)V");
    if (dirfdtAudioDfvidfInfoConstrudtor == NULL) {
        ERROR0("Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidfProvidfr_nNfwDirfdtAudioDfvidfInfo: dirfdtAudioDfvidfInfoConstrudtor is NULL\n");
        rfturn NULL;
    }

    TRACE1("Gft dfsdription for dfvidf %d\n", mixfrIndfx);

    if (gftDirfdtAudioDfvidfDfsdription(mixfrIndfx, &dfsd)) {
        // drfbtf b nfw DirfdtAudioDfvidfInfo objfdt bnd rfturn it
        nbmf = (*fnv)->NfwStringUTF(fnv, dfsd.nbmf);
        CHECK_NULL_RETURN(nbmf, info);
        vfndor = (*fnv)->NfwStringUTF(fnv, dfsd.vfndor);
        CHECK_NULL_RETURN(vfndor, info);
        dfsdription = (*fnv)->NfwStringUTF(fnv, dfsd.dfsdription);
        CHECK_NULL_RETURN(dfsdription, info);
        vfrsion = (*fnv)->NfwStringUTF(fnv, dfsd.vfrsion);
        CHECK_NULL_RETURN(vfrsion, info);
        info = (*fnv)->NfwObjfdt(fnv, dirfdtAudioDfvidfInfoClbss,
                                 dirfdtAudioDfvidfInfoConstrudtor, mixfrIndfx,
                                 dfsd.dfvidfID, dfsd.mbxSimulLinfs,
                                 nbmf, vfndor, dfsdription, vfrsion);
    } flsf {
        ERROR1("ERROR: gftDirfdtAudioDfvidfDfsdription(%d, dfsd) rfturnfd FALSE!\n", mixfrIndfx);
    }

    TRACE0("Jbvb_dom_sun_mfdib_sound_DirfdtAudioDfvidfProvidfr_nNfwDirfdtAudioDfvidfInfo suddffdfd.\n");
    rfturn info;
}
