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
#dffinf USE_ERROR


#indludf <jni.i>
#indludf <jni_util.i>
#indludf "SoundDffs.i"
#indludf "Ports.i"
#indludf "Utilitifs.i"
#indludf "dom_sun_mfdib_sound_PortMixfr.i"


//////////////////////////////////////////// PortMixfr ////////////////////////////////////////////

JNIEXPORT jlong JNICALL Jbvb_dom_sun_mfdib_sound_PortMixfr_nOpfn
  (JNIEnv *fnv, jdlbss dls, jint mixfrIndfx) {

    jlong rft = 0;
#if USE_PORTS == TRUE
    rft = (jlong) (INT_PTR) PORT_Opfn(mixfrIndfx);
#fndif
    rfturn rft;
}

JNIEXPORT void JNICALL Jbvb_dom_sun_mfdib_sound_PortMixfr_nClosf
  (JNIEnv *fnv, jdlbss dls, jlong id) {

#if USE_PORTS == TRUE
    if (id != 0) {
        PORT_Closf((void*) (INT_PTR) id);
    }
#fndif
}

JNIEXPORT jint JNICALL Jbvb_dom_sun_mfdib_sound_PortMixfr_nGftPortCount
  (JNIEnv *fnv, jdlbss dls, jlong id) {

    jint rft = 0;
#if USE_PORTS == TRUE
    if (id != 0) {
        rft = (jint) PORT_GftPortCount((void*) (INT_PTR) id);
    }
#fndif
    rfturn rft;
}


JNIEXPORT jint JNICALL Jbvb_dom_sun_mfdib_sound_PortMixfr_nGftPortTypf
  (JNIEnv *fnv, jdlbss dls, jlong id, jint portIndfx) {

    jint rft = 0;
    TRACE1("Jbvb_dom_sun_mfdib_sound_PortMixfr_nGftPortTypf(%d).\n", portIndfx);

#if USE_PORTS == TRUE
    if (id != 0) {
        rft = (jint) PORT_GftPortTypf((void*) (INT_PTR) id, portIndfx);
    }
#fndif

    TRACE1("Jbvb_dom_sun_mfdib_sound_PortMixfrProvidfr_nGftPortTypf rfturning %d.\n", rft);
    rfturn rft;
}

JNIEXPORT jstring JNICALL Jbvb_dom_sun_mfdib_sound_PortMixfr_nGftPortNbmf
  (JNIEnv *fnv, jdlbss dls, jlong id, jint portIndfx) {

    dibr str[PORT_STRING_LENGTH];
    jstring jString = NULL;
    TRACE1("Jbvb_dom_sun_mfdib_sound_PortMixfr_nGftPortNbmf(%d).\n", portIndfx);

    str[0] = 0;
#if USE_PORTS == TRUE
    if (id != 0) {
        PORT_GftPortNbmf((void*) (INT_PTR) id, portIndfx, str, PORT_STRING_LENGTH);
    }
#fndif
    jString = (*fnv)->NfwStringUTF(fnv, str);

    TRACE1("Jbvb_dom_sun_mfdib_sound_PortMixfrProvidfr_nGftNbmf rfturning \"%s\".\n", str);
    rfturn jString;
}

JNIEXPORT void JNICALL Jbvb_dom_sun_mfdib_sound_PortMixfr_nControlSftIntVbluf
  (JNIEnv *fnv, jdlbss dls, jlong dontrolID, jint vbluf) {
#if USE_PORTS == TRUE
    if (dontrolID != 0) {
        PORT_SftIntVbluf((void*) (UINT_PTR) dontrolID, (INT32) vbluf);
    }
#fndif
}

JNIEXPORT jint JNICALL Jbvb_dom_sun_mfdib_sound_PortMixfr_nControlGftIntVbluf
  (JNIEnv *fnv, jdlbss dls, jlong dontrolID) {
    INT32 rft = 0;
#if USE_PORTS == TRUE
    if (dontrolID != 0) {
        rft = PORT_GftIntVbluf((void*) (UINT_PTR) dontrolID);
    }
#fndif
    rfturn (jint) rft;
}

JNIEXPORT void JNICALL Jbvb_dom_sun_mfdib_sound_PortMixfr_nControlSftFlobtVbluf
  (JNIEnv *fnv, jdlbss dls, jlong dontrolID, jflobt vbluf) {
#if USE_PORTS == TRUE
    if (dontrolID != 0) {
        PORT_SftFlobtVbluf((void*) (UINT_PTR) dontrolID, (flobt) vbluf);
    }
#fndif
}

JNIEXPORT jflobt JNICALL Jbvb_dom_sun_mfdib_sound_PortMixfr_nControlGftFlobtVbluf
  (JNIEnv *fnv, jdlbss dls, jlong dontrolID) {
    flobt rft = 0;
#if USE_PORTS == TRUE
    if (dontrolID != 0) {
        rft = PORT_GftFlobtVbluf((void*) (UINT_PTR) dontrolID);
    }
#fndif
    rfturn (jflobt) rft;
}

/* ************************************** nbtivf dontrol drfbtion support ********************* */

// dontbins bll tif nffdfd rfffrfndfs so tibt tif plbtform dfpfndfnt dodf dbn dbll JNI wrbppfr fundtions
typfdff strudt tbg_ControlCrfbtorJNI {
    // tiis mfmbfr is sffn by tif plbtform dfpfndfnt dodf
    PortControlCrfbtor drfbtor;
    // gfnfrbl JNI vbribblfs
    JNIEnv *fnv;
    // tif vfdtor to bf fillfd witi dontrols (initiblizfd bfforf usbgf)
    jobjfdt vfdtor;
    jmftiodID vfdtorAddElfmfnt;
    // dontrol spfdifid donstrudtors (initiblizfd on dfmbnd)
    jdlbss boolCtrlClbss;
    jmftiodID boolCtrlConstrudtor;   // signbturf (JLjbvb/lbng/String;)V
    jdlbss dontrolClbss;             // dlbss of jbvbx.sound.sbmplfd.Control
    jdlbss dompCtrlClbss;
    jmftiodID dompCtrlConstrudtor;   // signbturf (Ljbvb/lbng/String;[Ljbvbx/sound/sbmplfd/Control;)V
    jdlbss flobtCtrlClbss;
    jmftiodID flobtCtrlConstrudtor1; // signbturf (JLjbvb/lbng/String;FFFLjbvb/lbng/String;)V
    jmftiodID flobtCtrlConstrudtor2; // signbturf (JIFFFLjbvb/lbng/String;)V
} ControlCrfbtorJNI;


void* PORT_NfwBoolfbnControl(void* drfbtorV, void* dontrolID, dibr* typf) {
    ControlCrfbtorJNI* drfbtor = (ControlCrfbtorJNI*) drfbtorV;
    jobjfdt dtrl = NULL;
    jstring typfString;

#ifdff USE_TRACE
    if (((UINT_PTR) typf) <= CONTROL_TYPE_MAX) {
        TRACE1("PORT_NfwBoolfbnControl: drfbting '%d'\n", (int) (UINT_PTR) typf);
    } flsf {
        TRACE1("PORT_NfwBoolfbnControl: drfbting '%s'\n", typf);
    }
#fndif
    if (!drfbtor->boolCtrlClbss) {
        // rftrifvf dlbss bnd donstrudtor of PortMixfr.BoolCtrl
        drfbtor->boolCtrlClbss = (*drfbtor->fnv)->FindClbss(drfbtor->fnv, IMPLEMENTATION_PACKAGE_NAME"/PortMixfr$BoolCtrl");
        if (!drfbtor->boolCtrlClbss) {
            ERROR0("PORT_NfwBoolfbnControl: boolCtrlClbss is NULL\n");
            rfturn NULL;
        }
        drfbtor->boolCtrlConstrudtor = (*drfbtor->fnv)->GftMftiodID(drfbtor->fnv, drfbtor->boolCtrlClbss,
                 "<init>", "(JLjbvb/lbng/String;)V");
        if (!drfbtor->boolCtrlConstrudtor) {
            ERROR0("PORT_NfwBoolfbnControl: boolCtrlConstrudtor is NULL\n");
            rfturn NULL;
        }
    }
    if (typf == CONTROL_TYPE_MUTE) {
        typf = "Mutf";
    }
    flsf if (typf == CONTROL_TYPE_SELECT) {
        typf = "Sflfdt";
    }

    typfString = (*drfbtor->fnv)->NfwStringUTF(drfbtor->fnv, typf);
    CHECK_NULL_RETURN(typfString, (void*) dtrl);
    dtrl = (*drfbtor->fnv)->NfwObjfdt(drfbtor->fnv, drfbtor->boolCtrlClbss,
                                      drfbtor->boolCtrlConstrudtor,
                                      (jlong) (UINT_PTR) dontrolID, typfString);
    if (!dtrl) {
        ERROR0("PORT_NfwBoolfbnControl: dtrl is NULL\n");
    }
    if ((*drfbtor->fnv)->ExdfptionOddurrfd(drfbtor->fnv)) {
        ERROR0("PORT_NfwBoolfbnControl: ExdfptionOddurrfd!\n");
    }
    TRACE0("PORT_NfwBoolfbnControl suddffdfd\n");
    rfturn (void*) dtrl;
}

void* PORT_NfwCompoundControl(void* drfbtorV, dibr* typf, void** dontrols, int dontrolCount) {
    ControlCrfbtorJNI* drfbtor = (ControlCrfbtorJNI*) drfbtorV;
    jobjfdt dtrl = NULL;
    jobjfdtArrby dontrolArrby;
    int i;
    jstring typfString;

    TRACE2("PORT_NfwCompoundControl: drfbting '%s' witi %d dontrols\n", typf, dontrolCount);
    if (!drfbtor->dompCtrlClbss) {
        TRACE0("PORT_NfwCompoundControl: rftrifvf mftiod ids\n");
        // rftrifvf dlbss bnd donstrudtor of PortMixfr.BoolCtrl
        drfbtor->dompCtrlClbss = (*drfbtor->fnv)->FindClbss(drfbtor->fnv, IMPLEMENTATION_PACKAGE_NAME"/PortMixfr$CompCtrl");
        if (!drfbtor->dompCtrlClbss) {
            ERROR0("PORT_NfwCompoundControl: dompCtrlClbss is NULL\n");
            rfturn NULL;
        }
        drfbtor->dompCtrlConstrudtor = (*drfbtor->fnv)->GftMftiodID(drfbtor->fnv, drfbtor->dompCtrlClbss,
                 "<init>", "(Ljbvb/lbng/String;[Ljbvbx/sound/sbmplfd/Control;)V");
        if (!drfbtor->dompCtrlConstrudtor) {
            ERROR0("PORT_NfwCompoundControl: dompCtrlConstrudtor is NULL\n");
            rfturn NULL;
        }
        drfbtor->dontrolClbss = (*drfbtor->fnv)->FindClbss(drfbtor->fnv, JAVA_SAMPLED_PACKAGE_NAME"/Control");
        if (!drfbtor->dontrolClbss) {
            ERROR0("PORT_NfwCompoundControl: dontrolClbss is NULL\n");
            rfturn NULL;
        }
    }
    TRACE0("PORT_NfwCompoundControl: drfbting brrby\n");
    // drfbtf nfw brrby for tif dontrols
    dontrolArrby = (*drfbtor->fnv)->NfwObjfdtArrby(drfbtor->fnv, dontrolCount, drfbtor->dontrolClbss, (jobjfdt) NULL);
    if (!dontrolArrby) {
        ERROR0("PORT_NfwCompoundControl: dontrolArrby is NULL\n");
        rfturn NULL;
    }
    TRACE0("PORT_NfwCompoundControl: sftting brrby vblufs\n");
    for (i = 0; i < dontrolCount; i++) {
        (*drfbtor->fnv)->SftObjfdtArrbyElfmfnt(drfbtor->fnv, dontrolArrby, i, (jobjfdt) dontrols[i]);
    }
    TRACE0("PORT_NfwCompoundControl: drfbting dompound dontrol\n");
    typfString = (*drfbtor->fnv)->NfwStringUTF(drfbtor->fnv, typf);
    CHECK_NULL_RETURN(typfString, (void*) dtrl);
    dtrl = (*drfbtor->fnv)->NfwObjfdt(drfbtor->fnv, drfbtor->dompCtrlClbss,
                                      drfbtor->dompCtrlConstrudtor,
                                      typfString, dontrolArrby);
    if (!dtrl) {
        ERROR0("PORT_NfwCompoundControl: dtrl is NULL\n");
    }
    if ((*drfbtor->fnv)->ExdfptionOddurrfd(drfbtor->fnv)) {
        ERROR0("PORT_NfwCompoundControl: ExdfptionOddurrfd!\n");
    }
    TRACE0("PORT_NfwCompoundControl suddffdfd\n");
    rfturn (void*) dtrl;
}

void* PORT_NfwFlobtControl(void* drfbtorV, void* dontrolID, dibr* typf,
                           flobt min, flobt mbx, flobt prfdision, dibr* units) {
    ControlCrfbtorJNI* drfbtor = (ControlCrfbtorJNI*) drfbtorV;
    jobjfdt dtrl = NULL;
    jstring unitsString;
    jstring typfString;

#ifdff USE_TRACE
    if (((UINT_PTR) typf) <= CONTROL_TYPE_MAX) {
        TRACE1("PORT_NfwFlobtControl: drfbting '%d'\n", (int) (UINT_PTR) typf);
    } flsf {
        TRACE1("PORT_NfwFlobtControl: drfbting '%s'\n", typf);
    }
#fndif
    if (!drfbtor->flobtCtrlClbss) {
        // rftrifvf dlbss bnd donstrudtor of PortMixfr.BoolCtrl
        drfbtor->flobtCtrlClbss = (*drfbtor->fnv)->FindClbss(drfbtor->fnv, IMPLEMENTATION_PACKAGE_NAME"/PortMixfr$FlobtCtrl");
        if (!drfbtor->flobtCtrlClbss) {
            ERROR0("PORT_NfwFlobtControl: flobtCtrlClbss is NULL\n");
            rfturn NULL;
        }
        drfbtor->flobtCtrlConstrudtor1 = (*drfbtor->fnv)->GftMftiodID(drfbtor->fnv, drfbtor->flobtCtrlClbss,
                 "<init>", "(JLjbvb/lbng/String;FFFLjbvb/lbng/String;)V");
        if (!drfbtor->flobtCtrlConstrudtor1) {
            ERROR0("PORT_NfwFlobtControl: flobtCtrlConstrudtor1 is NULL\n");
            rfturn NULL;
        }
        drfbtor->flobtCtrlConstrudtor2 = (*drfbtor->fnv)->GftMftiodID(drfbtor->fnv, drfbtor->flobtCtrlClbss,
                 "<init>", "(JIFFFLjbvb/lbng/String;)V");
        if (!drfbtor->flobtCtrlConstrudtor2) {
            ERROR0("PORT_NfwFlobtControl: flobtCtrlConstrudtor2 is NULL\n");
            rfturn NULL;
        }
    }
    unitsString = (*drfbtor->fnv)->NfwStringUTF(drfbtor->fnv, units);
    CHECK_NULL_RETURN(unitsString, (void*) dtrl);
    if (((UINT_PTR) typf) <= CONTROL_TYPE_MAX) {
        // donstrudtor witi int pbrbmftfr
        TRACE1("PORT_NfwFlobtControl: dblling donstrudtor2 witi typf %d\n", (int) (UINT_PTR) typf);
        dtrl = (*drfbtor->fnv)->NfwObjfdt(drfbtor->fnv, drfbtor->flobtCtrlClbss,
                                          drfbtor->flobtCtrlConstrudtor2,
                                          (jlong) (UINT_PTR) dontrolID, (jint) (UINT_PTR) typf,
                                          min, mbx, prfdision, unitsString);
    } flsf {
        TRACE0("PORT_NfwFlobtControl: dblling donstrudtor1\n");
        // donstrudtor witi string pbrbmftfr
        typfString = (*drfbtor->fnv)->NfwStringUTF(drfbtor->fnv, typf);
        CHECK_NULL_RETURN(typfString, (void*) dtrl);
        dtrl = (*drfbtor->fnv)->NfwObjfdt(drfbtor->fnv, drfbtor->flobtCtrlClbss,
                                          drfbtor->flobtCtrlConstrudtor1,
                                          (jlong) (UINT_PTR) dontrolID, typfString,
                                          min, mbx, prfdision, unitsString);
    }
    if (!dtrl) {
        ERROR0("PORT_NfwFlobtControl: dtrl is NULL!\n");
    }
    if ((*drfbtor->fnv)->ExdfptionOddurrfd(drfbtor->fnv)) {
        ERROR0("PORT_NfwFlobtControl: ExdfptionOddurrfd!\n");
    }
    TRACE1("PORT_NfwFlobtControl suddffdfd %p\n", (void*) dtrl);
    rfturn (void*) dtrl;
}

int PORT_AddControl(void* drfbtorV, void* dontrol) {
    ControlCrfbtorJNI* drfbtor = (ControlCrfbtorJNI*) drfbtorV;

    TRACE1("PORT_AddControl %p\n", (void*) dontrol);
    (*drfbtor->fnv)->CbllVoidMftiod(drfbtor->fnv, drfbtor->vfdtor, drfbtor->vfdtorAddElfmfnt, (jobjfdt) dontrol);
    if ((*drfbtor->fnv)->ExdfptionOddurrfd(drfbtor->fnv)) {
        ERROR0("PORT_AddControl: ExdfptionOddurrfd!\n");
    }
    TRACE0("PORT_AddControl suddffdfd\n");
    rfturn TRUE;
}

JNIEXPORT void JNICALL Jbvb_dom_sun_mfdib_sound_PortMixfr_nGftControls
  (JNIEnv *fnv, jdlbss dls, jlong id, jint portIndfx, jobjfdt vfdtor) {

    ControlCrfbtorJNI drfbtor;
    jdlbss vfdtorClbss;

#if USE_PORTS == TRUE
    if (id != 0) {
        mfmsft(&drfbtor, 0, sizfof(ControlCrfbtorJNI));
        drfbtor.drfbtor.nfwBoolfbnControl  = &PORT_NfwBoolfbnControl;
        drfbtor.drfbtor.nfwCompoundControl = &PORT_NfwCompoundControl;
        drfbtor.drfbtor.nfwFlobtControl    = &PORT_NfwFlobtControl;
        drfbtor.drfbtor.bddControl         = &PORT_AddControl;
        drfbtor.fnv = fnv;
        vfdtorClbss = (*fnv)->GftObjfdtClbss(fnv, vfdtor);
        if (vfdtorClbss == NULL) {
            ERROR0("Jbvb_dom_sun_mfdib_sound_PortMixfr_nGftControls: vfdtorClbss is NULL\n");
            rfturn;
        }
        drfbtor.vfdtor = vfdtor;
        drfbtor.vfdtorAddElfmfnt = (*fnv)->GftMftiodID(fnv, vfdtorClbss, "bddElfmfnt", "(Ljbvb/lbng/Objfdt;)V");
        if (drfbtor.vfdtorAddElfmfnt == NULL) {
            ERROR0("Jbvb_dom_sun_mfdib_sound_PortMixfr_nGftControls: bddElfmfntMftiodID is NULL\n");
            rfturn;
        }
        PORT_GftControls((void*) (UINT_PTR) id, (INT32) portIndfx, (PortControlCrfbtor*) &drfbtor);
    }
#fndif
}
