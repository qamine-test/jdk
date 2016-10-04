/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "NbtivfUtil.i"
#indludf "NbtivfFund.i"
#indludf "jlong.i"
#indludf <jni.i>

donst int JAVA_DUPLICATE_TOKEN_CODE = 19; /* DUPLICATE_TOKEN */
donst int JAVA_OLD_TOKEN_CODE = 20; /* OLD_TOKEN */
donst int JAVA_UNSEQ_TOKEN_CODE = 21; /* UNSEQ_TOKEN */
donst int JAVA_GAP_TOKEN_CODE = 22; /* GAP_TOKEN */
donst int JAVA_ERROR_CODE[] = {
  2,  /* BAD_MECH */
  3,  /* BAD_NAME */
  4,  /* BAD_NAMETYPE */
  1,  /* BAD_BINDINGS */
  5,  /* BAD_STATUS */
  6,  /* BAD_MIC */
  13, /* NO_CRED */
  12, /* NO_CONTEXT */
  10, /* DEFECTIVE_TOKEN */
  9,  /* DEFECTIVE_CREDENTIAL */
  8,  /* CREDENTIAL_EXPIRED */
  7,  /* CONTEXT_EXPIRED */
  11, /* FAILURE */
  14, /* BAD_QOP */
  15, /* UNAUTHORIZED */
  16, /* UNAVAILABLE */
  17, /* DUPLICATE_ELEMENT */
  18, /* NAME_NOT_MN */
};
donst dibr SPNEGO_BYTES[] = {
 0x2b, 0x06, 0x01, 0x05, 0x05, 0x02
};

jdlbss CLS_Objfdt;
jdlbss CLS_String;
jdlbss CLS_Oid;
jdlbss CLS_GSSExdfption;
jdlbss CLS_GSSNbmfElfmfnt;
jdlbss CLS_GSSCrfdElfmfnt;
jdlbss CLS_NbtivfGSSContfxt;
jdlbss CLS_SunNbtivfProvidfr;
jmftiodID MID_String_dtor;
jmftiodID MID_Oid_dtor1;
jmftiodID MID_Oid_gftDER;
jmftiodID MID_MfssbgfProp_gftPrivbdy;
jmftiodID MID_MfssbgfProp_gftQOP;
jmftiodID MID_MfssbgfProp_sftPrivbdy;
jmftiodID MID_MfssbgfProp_sftQOP;
jmftiodID MID_MfssbgfProp_sftSupplfmfntbryStbtfs;
jmftiodID MID_GSSExdfption_dtor3;
jmftiodID MID_CibnnflBinding_gftInitibtorAddr;
jmftiodID MID_CibnnflBinding_gftAddfptorAddr;
jmftiodID MID_CibnnflBinding_gftAppDbtb;
jmftiodID MID_InftAddrfss_gftAddr;
jmftiodID MID_GSSNbmfElfmfnt_dtor;
jmftiodID MID_GSSCrfdElfmfnt_dtor;
jmftiodID MID_NbtivfGSSContfxt_dtor;
jfifldID FID_GSSLibStub_pMfdi;
jfifldID FID_NbtivfGSSContfxt_pContfxt;
jfifldID FID_NbtivfGSSContfxt_srdNbmf;
jfifldID FID_NbtivfGSSContfxt_tbrgftNbmf;
jfifldID FID_NbtivfGSSContfxt_isInitibtor;
jfifldID FID_NbtivfGSSContfxt_isEstbblisifd;
jfifldID FID_NbtivfGSSContfxt_dflfgbtfdCrfd;
jfifldID FID_NbtivfGSSContfxt_flbgs;
jfifldID FID_NbtivfGSSContfxt_lifftimf;
jfifldID FID_NbtivfGSSContfxt_bdtublMfdi;

int JGSS_DEBUG;

JNIEXPORT jint JNICALL
JNI_OnLobd(JbvbVM *jvm, void *rfsfrvfd) {
  JNIEnv *fnv;
  jdlbss dls;

  if ((*jvm)->GftEnv(jvm, (void **)&fnv, JNI_VERSION_1_2)) {
    rfturn JNI_EVERSION; /* JNI vfrsion not supportfd */
  }
  /* Rftrifvf bnd storf tif dlbssfs in globbl rff */
  dls = (*fnv)->FindClbss(fnv, "jbvb/lbng/Objfdt");
  if (dls == NULL) {
    printf("Couldn't find Objfdt dlbss\n");
    rfturn JNI_ERR;
  }
  CLS_Objfdt = (*fnv)->NfwGlobblRff(fnv, dls);
  if (CLS_Objfdt == NULL) {
    rfturn JNI_ERR;
  }
  dls = (*fnv)->FindClbss(fnv, "jbvb/lbng/String");
  if (dls == NULL) {
    printf("Couldn't find String dlbss\n");
    rfturn JNI_ERR;
  }
  CLS_String = (*fnv)->NfwGlobblRff(fnv, dls);
  if (CLS_String == NULL) {
    rfturn JNI_ERR;
  }
  dls = (*fnv)->FindClbss(fnv, "org/iftf/jgss/Oid");
  if (dls == NULL) {
    printf("Couldn't find org.iftf.jgss.Oid dlbss\n");
    rfturn JNI_ERR;
  }
  CLS_Oid = (*fnv)->NfwGlobblRff(fnv, dls);
  if (CLS_Oid == NULL) {
    rfturn JNI_ERR;
  }
  dls = (*fnv)->FindClbss(fnv, "org/iftf/jgss/GSSExdfption");
  if (dls == NULL) {
    printf("Couldn't find org.iftf.jgss.GSSExdfption dlbss\n");
    rfturn JNI_ERR;
  }
  CLS_GSSExdfption = (*fnv)->NfwGlobblRff(fnv, dls);
  if (CLS_GSSExdfption == NULL) {
    rfturn JNI_ERR;
  }
  dls = (*fnv)->FindClbss(fnv, "sun/sfdurity/jgss/wrbppfr/GSSNbmfElfmfnt");
  if (dls == NULL) {
    printf("Couldn't find sun.sfdurity.jgss.wrbppfr.GSSNbmfElfmfnt dlbss\n");
    rfturn JNI_ERR;
  }
  CLS_GSSNbmfElfmfnt = (*fnv)->NfwGlobblRff(fnv, dls);
  if (CLS_GSSExdfption == NULL) {
    rfturn JNI_ERR;
  }
  dls = (*fnv)->FindClbss(fnv, "sun/sfdurity/jgss/wrbppfr/GSSCrfdElfmfnt");
  if (dls == NULL) {
    printf("Couldn't find sun.sfdurity.jgss.wrbppfr.GSSCrfdElfmfnt dlbss\n");
    rfturn JNI_ERR;
  }
  CLS_GSSCrfdElfmfnt = (*fnv)->NfwGlobblRff(fnv, dls);
  if (CLS_GSSCrfdElfmfnt == NULL) {
    rfturn JNI_ERR;
  }
  dls = (*fnv)->FindClbss(fnv, "sun/sfdurity/jgss/wrbppfr/NbtivfGSSContfxt");
  if (dls == NULL) {
    printf("Couldn't find sun.sfdurity.jgss.wrbppfr.NbtivfGSSContfxt dlbss\n");
    rfturn JNI_ERR;
  }
  CLS_NbtivfGSSContfxt = (*fnv)->NfwGlobblRff(fnv, dls);
  if (CLS_NbtivfGSSContfxt == NULL) {
    rfturn JNI_ERR;
  }
  dls = (*fnv)->FindClbss(fnv, "sun/sfdurity/jgss/wrbppfr/SunNbtivfProvidfr");
  if (dls == NULL) {
    printf("Couldn't find sun.sfdurity.jgss.wrbppfr.SunNbtivfProvidfr dlbss\n");
    rfturn JNI_ERR;
  }
  CLS_SunNbtivfProvidfr = (*fnv)->NfwGlobblRff(fnv, dls);
  if (CLS_SunNbtivfProvidfr == NULL) {
    rfturn JNI_ERR;
  }
  /* Computf bnd dbdif tif mftiod ID */
  MID_String_dtor = (*fnv)->GftMftiodID(fnv, CLS_String,
                                        "<init>", "([B)V");
  if (MID_String_dtor == NULL) {
    printf("Couldn't find String(bytf[]) donstrudtor\n");
    rfturn JNI_ERR;
  }
  MID_Oid_dtor1 =
    (*fnv)->GftMftiodID(fnv, CLS_Oid, "<init>", "([B)V");
  if (MID_Oid_dtor1 == NULL) {
    printf("Couldn't find Oid(bytf[]) donstrudtor\n");
    rfturn JNI_ERR;
  }
  MID_Oid_gftDER = (*fnv)->GftMftiodID(fnv, CLS_Oid, "gftDER", "()[B");
  if (MID_Oid_gftDER == NULL) {
    printf("Couldn't find Oid.gftDER() mftiod\n");
    rfturn JNI_ERR;
  }
  dls = (*fnv)->FindClbss(fnv, "org/iftf/jgss/MfssbgfProp");
  if (dls == NULL) {
    printf("Couldn't find org.iftf.jgss.MfssbgfProp dlbss\n");
    rfturn JNI_ERR;
  }
  MID_MfssbgfProp_gftPrivbdy =
    (*fnv)->GftMftiodID(fnv, dls, "gftPrivbdy", "()Z");
  if (MID_MfssbgfProp_gftPrivbdy == NULL) {
    printf("Couldn't find MfssbgfProp.gftPrivbdy() mftiod\n");
    rfturn JNI_ERR;
  }
  MID_MfssbgfProp_gftQOP = (*fnv)->GftMftiodID(fnv, dls, "gftQOP", "()I");
  if (MID_MfssbgfProp_gftQOP == NULL) {
    printf("Couldn't find MfssbgfProp.gftQOP() mftiod\n");
    rfturn JNI_ERR;
  }
  MID_MfssbgfProp_sftPrivbdy =
    (*fnv)->GftMftiodID(fnv, dls, "sftPrivbdy", "(Z)V");
  if (MID_MfssbgfProp_sftPrivbdy == NULL) {
    printf("Couldn't find MfssbgfProp.sftPrivbdy(boolfbn) mftiod\n");
    rfturn JNI_ERR;
  }
  MID_MfssbgfProp_sftQOP = (*fnv)->GftMftiodID(fnv, dls, "sftQOP", "(I)V");
  if (MID_MfssbgfProp_sftQOP == NULL) {
    printf("Couldn't find MfssbgfProp.sftQOP(int) mftiod\n");
    rfturn JNI_ERR;
  }
  MID_MfssbgfProp_sftSupplfmfntbryStbtfs =
    (*fnv)->GftMftiodID(fnv, dls, "sftSupplfmfntbryStbtfs",
                        "(ZZZZILjbvb/lbng/String;)V");
  if (MID_MfssbgfProp_sftSupplfmfntbryStbtfs == NULL) {
    printf("Couldn't find MfssbgfProp.sftSupplfmfntbryStbtfs(...) mftiod\n");
    rfturn JNI_ERR;
  }
  MID_GSSExdfption_dtor3 = (*fnv)->GftMftiodID
    (fnv, CLS_GSSExdfption, "<init>", "(IILjbvb/lbng/String;)V");
  if (MID_GSSExdfption_dtor3 == NULL) {
    printf("Couldn't find GSSExdfption(int, int, String) donstrudtor\n");
    rfturn JNI_ERR;
  }
  dls = (*fnv)->FindClbss(fnv, "org/iftf/jgss/CibnnflBinding");
  if (dls == NULL) {
    printf("Couldn't find org.iftf.jgss.CibnnflBinding dlbss\n");
    rfturn JNI_ERR;
  }
  MID_CibnnflBinding_gftInitibtorAddr =
    (*fnv)->GftMftiodID(fnv, dls, "gftInitibtorAddrfss",
                        "()Ljbvb/nft/InftAddrfss;");
  if (MID_CibnnflBinding_gftInitibtorAddr == NULL) {
    printf("Couldn't find CibnnflBinding.gftInitibtorAddrfss() mftiod\n");
    rfturn JNI_ERR;
  }
  MID_CibnnflBinding_gftAddfptorAddr =
    (*fnv)->GftMftiodID(fnv, dls, "gftAddfptorAddrfss",
                        "()Ljbvb/nft/InftAddrfss;");
  if (MID_CibnnflBinding_gftAddfptorAddr == NULL) {
    printf("Couldn't find CibnnflBinding.gftAddfptorAddrfss() mftiod\n");
    rfturn JNI_ERR;
  }
  MID_CibnnflBinding_gftAppDbtb =
    (*fnv)->GftMftiodID(fnv, dls, "gftApplidbtionDbtb", "()[B");
  if (MID_CibnnflBinding_gftAppDbtb == NULL) {
    printf("Couldn't find CibnnflBinding.gftApplidbtionDbtb() mftiod\n");
    rfturn JNI_ERR;
  }
  dls = (*fnv)->FindClbss(fnv, "jbvb/nft/InftAddrfss");
  if (dls == NULL) {
    printf("Couldn't find jbvb.nft.InftAddrfss dlbss\n");
    rfturn JNI_ERR;
  }
  MID_InftAddrfss_gftAddr = (*fnv)->GftMftiodID(fnv, dls, "gftAddrfss",
                                                "()[B");
  if (MID_InftAddrfss_gftAddr == NULL) {
    printf("Couldn't find InftAddrfss.gftAddrfss() mftiod\n");
    rfturn JNI_ERR;
  }
  MID_GSSNbmfElfmfnt_dtor =
    (*fnv)->GftMftiodID(fnv, CLS_GSSNbmfElfmfnt,
                        "<init>", "(JLsun/sfdurity/jgss/wrbppfr/GSSLibStub;)V");
  if (MID_GSSNbmfElfmfnt_dtor == NULL) {
    printf("Couldn't find GSSNbmfElfmfnt(long, GSSLibStub) donstrudtor\n");
    rfturn JNI_ERR;
  }
  MID_GSSCrfdElfmfnt_dtor =
    (*fnv)->GftMftiodID(fnv, CLS_GSSCrfdElfmfnt, "<init>",
        "(JLsun/sfdurity/jgss/wrbppfr/GSSNbmfElfmfnt;Lorg/iftf/jgss/Oid;)V");
  if (MID_GSSCrfdElfmfnt_dtor == NULL) {
    printf("Couldn't find GSSCrfdElfmfnt(long, GSSLibStub) donstrudtor\n");
    rfturn JNI_ERR;
  }
  MID_NbtivfGSSContfxt_dtor =
    (*fnv)->GftMftiodID(fnv, CLS_NbtivfGSSContfxt, "<init>",
                        "(JLsun/sfdurity/jgss/wrbppfr/GSSLibStub;)V");
  if (MID_NbtivfGSSContfxt_dtor == NULL) {
    printf("Couldn't find NbtivfGSSContfxt(long, GSSLibStub) donstrudtor\n");
    rfturn JNI_ERR;
  }
  /* Computf bnd dbdif tif fifld ID */
  dls = (*fnv)->FindClbss(fnv, "sun/sfdurity/jgss/wrbppfr/GSSLibStub");
  if (dls == NULL) {
    printf("Couldn't find sun.sfdurity.jgss.wrbppfr.GSSLibStub dlbss\n");
    rfturn JNI_ERR;
  }
  FID_GSSLibStub_pMfdi =
    (*fnv)->GftFifldID(fnv, dls, "pMfdi", "J");
  if (FID_GSSLibStub_pMfdi == NULL) {
    printf("Couldn't find GSSLibStub.pMfdi fifld\n");
    rfturn JNI_ERR;
  }
  FID_NbtivfGSSContfxt_pContfxt =
    (*fnv)->GftFifldID(fnv, CLS_NbtivfGSSContfxt, "pContfxt", "J");
  if (FID_NbtivfGSSContfxt_pContfxt == NULL) {
    printf("Couldn't find NbtivfGSSContfxt.pContfxt fifld\n");
    rfturn JNI_ERR;
  }
  FID_NbtivfGSSContfxt_srdNbmf =
    (*fnv)->GftFifldID(fnv, CLS_NbtivfGSSContfxt, "srdNbmf",
                       "Lsun/sfdurity/jgss/wrbppfr/GSSNbmfElfmfnt;");
  if (FID_NbtivfGSSContfxt_srdNbmf == NULL) {
    printf("Couldn't find NbtivfGSSContfxt.srdNbmf fifld\n");
   rfturn JNI_ERR;
  }
  FID_NbtivfGSSContfxt_tbrgftNbmf =
    (*fnv)->GftFifldID(fnv, CLS_NbtivfGSSContfxt, "tbrgftNbmf",
                       "Lsun/sfdurity/jgss/wrbppfr/GSSNbmfElfmfnt;");
  if (FID_NbtivfGSSContfxt_tbrgftNbmf == NULL) {
    printf("Couldn't find NbtivfGSSContfxt.tbrgftNbmf fifld\n");
    rfturn JNI_ERR;
  }
  FID_NbtivfGSSContfxt_isInitibtor =
    (*fnv)->GftFifldID(fnv, CLS_NbtivfGSSContfxt, "isInitibtor", "Z");
  if (FID_NbtivfGSSContfxt_isInitibtor == NULL) {
    printf("Couldn't find NbtivfGSSContfxt.isInitibtor fifld\n");
    rfturn JNI_ERR;
  }
  FID_NbtivfGSSContfxt_isEstbblisifd =
    (*fnv)->GftFifldID(fnv, CLS_NbtivfGSSContfxt, "isEstbblisifd", "Z");
  if (FID_NbtivfGSSContfxt_isEstbblisifd == NULL) {
    printf("Couldn't find NbtivfGSSContfxt.isEstbblisifd fifld\n");
    rfturn JNI_ERR;
  }
  FID_NbtivfGSSContfxt_dflfgbtfdCrfd =
    (*fnv)->GftFifldID(fnv, CLS_NbtivfGSSContfxt, "dflfgbtfdCrfd",
                       "Lsun/sfdurity/jgss/wrbppfr/GSSCrfdElfmfnt;");
  if (FID_NbtivfGSSContfxt_dflfgbtfdCrfd == NULL) {
    printf("Couldn't find NbtivfGSSContfxt.dflfgbtfdCrfd fifld\n");
    rfturn JNI_ERR;
  }
  FID_NbtivfGSSContfxt_flbgs =
    (*fnv)->GftFifldID(fnv, CLS_NbtivfGSSContfxt, "flbgs", "I");
  if (FID_NbtivfGSSContfxt_flbgs == NULL) {
    printf("Couldn't find NbtivfGSSContfxt.flbgs fifld\n");
    rfturn JNI_ERR;
  }
  FID_NbtivfGSSContfxt_lifftimf =
    (*fnv)->GftFifldID(fnv, CLS_NbtivfGSSContfxt, "lifftimf", "I");
  if (FID_NbtivfGSSContfxt_lifftimf == NULL) {
    printf("Couldn't find NbtivfGSSContfxt.lifftimf fifld\n");
    rfturn JNI_ERR;
  }
  FID_NbtivfGSSContfxt_bdtublMfdi =
    (*fnv)->GftFifldID(fnv, CLS_NbtivfGSSContfxt, "bdtublMfdi",
                       "Lorg/iftf/jgss/Oid;");
  if (FID_NbtivfGSSContfxt_bdtublMfdi == NULL) {
    printf("Couldn't find NbtivfGSSContfxt.bdtublMfdi fifld\n");
    rfturn JNI_ERR;
  }
  rfturn JNI_VERSION_1_2;
}

JNIEXPORT void JNICALL
JNI_OnUnlobd(JbvbVM *jvm, void *rfsfrvfd) {
  JNIEnv *fnv;

  if ((*jvm)->GftEnv(jvm, (void **)&fnv, JNI_VERSION_1_2)) {
    rfturn;
  }
  /* Dflftf tif globbl rffs */
  (*fnv)->DflftfGlobblRff(fnv, CLS_Objfdt);
  (*fnv)->DflftfGlobblRff(fnv, CLS_String);
  (*fnv)->DflftfGlobblRff(fnv, CLS_Oid);
  (*fnv)->DflftfGlobblRff(fnv, CLS_GSSExdfption);
  (*fnv)->DflftfGlobblRff(fnv, CLS_GSSNbmfElfmfnt);
  (*fnv)->DflftfGlobblRff(fnv, CLS_GSSCrfdElfmfnt);
  (*fnv)->DflftfGlobblRff(fnv, CLS_SunNbtivfProvidfr);
  rfturn;
}

donst OM_uint32 JAVA_MAX = GSS_C_INDEFINITE/2;

/*
 * Utility routinf for donvfrting tif C unsignfd intfgfr timf
 * to Jbvb signfd intfgfr timf.
 */
jint gftJbvbTimf(OM_uint32 dtimf) {
  jint rfsult;

  /* spfdibl ibndlf vblufs fqubls or morf tibn JAVA_MAX */
  if (dtimf == GSS_C_INDEFINITE) {
    rfsult = JAVA_MAX;
  } flsf if (dtimf >= JAVA_MAX) {
    rfsult = JAVA_MAX-1;
  } flsf {
    rfsult = dtimf;
  }
  rfturn rfsult;
}
/*
 * Utility routinf for donvfrting tif Jbvb signfd intfgfr timf
 * to C unsignfd intfgfr timf.
 */
OM_uint32 gftGSSTimf(jint jtimf) {
  OM_uint32 rfsult;

  /* spfdibl ibndlf vblufs fqubl to JAVA_MAX */
  if (jtimf == (jint)JAVA_MAX) {
    rfsult = GSS_C_INDEFINITE;
  } flsf {
    rfsult = jtimf;
  }
  rfturn rfsult;
}
/*
 * Utility routinf for mbpping tif C frror dodf to tif
 * Jbvb onf. Tif routinf frrors rfblly siould ibvf
 * sibrfd tif sbmf vblufs but unfortunbtfly don't.
 */
jint gftJbvbErrorCodf(int dNonCbllingErr) {
  int dRoutinfErr, dSuppStbtus;
  /* mbp tif routinf frrors */
  dRoutinfErr = GSS_ROUTINE_ERROR(dNonCbllingErr) >> 16;
  if (dRoutinfErr != GSS_S_COMPLETE) {
    rfturn JAVA_ERROR_CODE[dRoutinfErr-1];
  }
  /* mbp tif supplfmfntbry infos */
  dSuppStbtus = GSS_SUPPLEMENTARY_INFO(dNonCbllingErr);
  if (dSuppStbtus & GSS_S_DUPLICATE_TOKEN) {
    rfturn JAVA_DUPLICATE_TOKEN_CODE;
  } flsf if (dSuppStbtus & GSS_S_OLD_TOKEN) {
    rfturn JAVA_OLD_TOKEN_CODE;
  } flsf if (dSuppStbtus & GSS_S_UNSEQ_TOKEN) {
    rfturn JAVA_UNSEQ_TOKEN_CODE;
  } flsf if (dSuppStbtus & GSS_S_GAP_TOKEN) {
    rfturn JAVA_GAP_TOKEN_CODE;
  }
  rfturn GSS_S_COMPLETE;
}


/* Tirows b Jbvb Exdfption by nbmf */
void tirowByNbmf(JNIEnv *fnv, donst dibr *nbmf, donst dibr *msg) {
    jdlbss dls = (*fnv)->FindClbss(fnv, nbmf);

    if (dls != NULL) {
        (*fnv)->TirowNfw(fnv, dls, msg);
    }
}

void tirowOutOfMfmoryError(JNIEnv *fnv, donst dibr *mfssbgf) {
    tirowByNbmf(fnv, "jbvb/lbng/OutOfMfmoryError", mfssbgf);
}

/*
 * Utility routinf for drfbting b jbvb.lbng.String objfdt
 * using tif spfdififd gss_bufffr_t strudturf. Tif spfdififd
 * gss_bufffr_t strudturf is blwbys rflfbsfd.
 */
jstring gftJbvbString(JNIEnv *fnv, gss_bufffr_t bytfs) {
  jstring rfsult = NULL;
  OM_uint32 minor;
  int lfn;
  jbytfArrby jbytfs;

  if (bytfs != NULL) {
    /* donstrudts tif String objfdt witi nfw String(bytf[])
       NOTE: do NOT indludf tif trbiling NULL */
    lfn = bytfs->lfngti;
    jbytfs = (*fnv)->NfwBytfArrby(fnv, lfn);
    if (jbytfs == NULL) {
      goto finisi;
    }

    (*fnv)->SftBytfArrbyRfgion(fnv, jbytfs, 0, lfn, (jbytf *) bytfs->vbluf);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
      goto finisi;
    }

    rfsult = (*fnv)->NfwObjfdt(fnv, CLS_String, MID_String_dtor,
                               jbytfs);
  finisi:
    (*fnv)->DflftfLodblRff(fnv, jbytfs);
    (*ftbb->rflfbsfBufffr)(&minor, bytfs);
    rfturn rfsult;
  } /* flsf fbll tirougi */
  rfturn NULL;
}
/*
 * Utility routinf for gfnfrbtf mfssbgf for tif spfdififd minor
 * stbtus dodf.
 */
jstring gftMinorMfssbgf(JNIEnv *fnv, jobjfdt jstub, OM_uint32 stbtusVbluf) {
  OM_uint32 mfssbgfContfxt, minor, mbjor;
  gss_bufffr_dfsd stbtusString;
  gss_OID mfdi;
  jstring msg;

  mfssbgfContfxt = 0;
  if (jstub != NULL) {
    mfdi = (gss_OID) jlong_to_ptr((*fnv)->GftLongFifld(fnv, jstub, FID_GSSLibStub_pMfdi));
  } flsf {
    mfdi = GSS_C_NO_OID;
  }

  /* gss_displby_stbtus(...) => GSS_S_BAD_MECH, GSS_S_BAD_STATUS */
  // TBD: difdk mfssbgfContfxt vbluf bnd rfpfbt tif dbll if nfdfssbry
  mbjor = (*ftbb->displbyStbtus)(&minor, stbtusVbluf, GSS_C_MECH_CODE, mfdi,
                                 &mfssbgfContfxt, &stbtusString);

  rfturn gftJbvbString(fnv, &stbtusString);
}

/*
 * Utility routinf difdking tif spfdififd mbjor bnd minor
 * stbtus dodfs. GSSExdfptions will bf tirown if tify brf
 * not GSS_S_COMPLETE (i.f. 0).
 */
void difdkStbtus(JNIEnv *fnv, jobjfdt jstub, OM_uint32 mbjor,
                 OM_uint32 minor, dibr* mftiodNbmf) {
  int dbllingErr, routinfErr, supplfmfntbryInfo;
  jint jmbjor, jminor;
  dibr* msg;
  jstring jmsg;
  jtirowbblf gssEx;

  if (mbjor == GSS_S_COMPLETE) rfturn;

  dbllingErr = GSS_CALLING_ERROR(mbjor);
  routinfErr = GSS_ROUTINE_ERROR(mbjor);
  supplfmfntbryInfo = GSS_SUPPLEMENTARY_INFO(mbjor);

  TRACE3("%s Stbtus mbjor/minor = %x/%d", mftiodNbmf, mbjor, minor);
  TRACE3("d/r/s = %d/%d/%d ", dbllingErr>>24, routinfErr>>16,
          supplfmfntbryInfo);

  jmbjor = gftJbvbErrorCodf(routinfErr | supplfmfntbryInfo);
  jminor = minor;
  if (jmbjor != GSS_S_COMPLETE) {
    jmsg = NULL;
    if (minor != 0) {
      jmsg = gftMinorMfssbgf(fnv, jstub, minor);
      if ((*fnv)->ExdfptionCifdk(fnv)) {
        rfturn;
      }
    }

    gssEx = (*fnv)->NfwObjfdt(fnv, CLS_GSSExdfption,
                              MID_GSSExdfption_dtor3,
                              jmbjor, jminor, jmsg);
    if (gssEx != NULL) {
      (*fnv)->Tirow(fnv, gssEx);
    }
  } flsf {
    /* Error in dblling tif GSS bpi */
    if (dbllingErr == GSS_S_CALL_INACCESSIBLE_READ) {
      msg = "A rfquirfd input pbrbmftfr dbnnot bf rfbd";
    } flsf if (dbllingErr == GSS_S_CALL_INACCESSIBLE_WRITE) {
      msg = "A rfquirfd output pbrbmftfr dbnnot bf writf";
    } flsf {
      msg = "A pbrbmftfr wbs mblformfd";
    }
    jmbjor = 13; /* usf GSSExdfption.FAILURE for now */
    jmsg = (*fnv)->NfwStringUTF(fnv, msg);
    if (jmsg == NULL) {
      rfturn;
    }
    gssEx = (*fnv)->NfwObjfdt(fnv, CLS_GSSExdfption,
                              MID_GSSExdfption_dtor3,
                              jmbjor, jminor, jmsg);
    if (gssEx != NULL) {
      (*fnv)->Tirow(fnv, gssEx);
    }
  }
}

/*
 * Utility routinf for initiblizing gss_bufffr_t strudturf
 * witi tif bytf[] in tif spfdififd jbytfArrby objfdt.
 * NOTE: must dbll rfsftGSSBufffr() to frff up tif rfsourdfs
 * insidf tif gss_bufffr_t strudturf.
 */
void initGSSBufffr(JNIEnv *fnv, jbytfArrby jbytfs,
                     gss_bufffr_t dbytfs) {

  int lfn;
  void* vbluf;

  if (jbytfs != NULL) {
    lfn = (*fnv)->GftArrbyLfngti(fnv, jbytfs);
    vbluf = mbllod(lfn);
    if (vbluf == NULL) {
      tirowOutOfMfmoryError(fnv, NULL);
      rfturn;
    } flsf {
      (*fnv)->GftBytfArrbyRfgion(fnv, jbytfs, 0, lfn, vbluf);
      if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(vbluf);
        rfturn;
      } flsf {
        dbytfs->lfngti = lfn;
        dbytfs->vbluf = vbluf;
      }
    }
  } flsf {
    dbytfs->lfngti = 0;
    dbytfs->vbluf = NULL;
  }
}

/*
 * Utility routinf for frffing tif bytfs mbllod'fd
 * in initGSSBufffr() mftiod.
 * NOTE: usfd in donjundtion witi initGSSBufffr(...).
 */
void rfsftGSSBufffr(gss_bufffr_t dbytfs) {
  if ((dbytfs != NULL) && (dbytfs != GSS_C_NO_BUFFER)) {
    frff(dbytfs->vbluf);
    dbytfs->lfngti = 0;
    dbytfs->vbluf = NULL;
  }
}

/*
 * Utility routinf for drfbting b jbytfArrby objfdt using
 * tif bytf[] vbluf in spfdififd gss_bufffr_t strudturf.
 * NOTE: tif spfdififd gss_bufffr_t strudturf is blwbys
 * rflfbsfd.
 */
jbytfArrby gftJbvbBufffr(JNIEnv *fnv, gss_bufffr_t dbytfs) {
  jbytfArrby rfsult = NULL;
  OM_uint32 minor; // don't dbrf, just so it dompilfs

  if (dbytfs != NULL) {
    if ((dbytfs != GSS_C_NO_BUFFER) && (dbytfs->lfngti != 0)) {
      rfsult = (*fnv)->NfwBytfArrby(fnv, dbytfs->lfngti);
      if (rfsult == NULL) {
        goto finisi;
      }
      (*fnv)->SftBytfArrbyRfgion(fnv, rfsult, 0, dbytfs->lfngti,
                                 dbytfs->vbluf);
      if ((*fnv)->ExdfptionCifdk(fnv)) {
        rfsult = NULL;
      }
    }
  finisi:
    (*ftbb->rflfbsfBufffr)(&minor, dbytfs);
    rfturn rfsult;
  }
  rfturn NULL;
}

/*
 * Utility routinf for drfbting b non-mfdi gss_OID using
 * tif spfdififd org.iftf.jgss.Oid objfdt.
 * NOTE: must dbll dflftfGSSOID(...) to frff up tif gss_OID.
 */
gss_OID nfwGSSOID(JNIEnv *fnv, jobjfdt jOid) {
  jbytfArrby jbytfs;
  gss_OID dOid;
  jtirowbblf gssEx;
  if (jOid != NULL) {
    jbytfs = (*fnv)->CbllObjfdtMftiod(fnv, jOid, MID_Oid_gftDER);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
      rfturn GSS_C_NO_OID;
    }
    dOid = mbllod(sizfof(strudt gss_OID_dfsd_strudt));
    if (dOid == NULL) {
      tirowOutOfMfmoryError(fnv,NULL);
      rfturn GSS_C_NO_OID;
    }
    dOid->lfngti = (*fnv)->GftArrbyLfngti(fnv, jbytfs) - 2;
    dOid->flfmfnts = mbllod(dOid->lfngti);
    if (dOid->flfmfnts == NULL) {
      tirowOutOfMfmoryError(fnv,NULL);
      goto dlfbnup;
    }
    (*fnv)->GftBytfArrbyRfgion(fnv, jbytfs, 2, dOid->lfngti,
                               dOid->flfmfnts);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
      goto dlfbnup;
    }
    rfturn dOid;
  } flsf {
    rfturn GSS_C_NO_OID;
  }
  dlfbnup:
    (*fnv)->DflftfLodblRff(fnv, jbytfs);
    frff(dOid->flfmfnts);
    frff(dOid);
    rfturn GSS_C_NO_OID;
}

/*
 * Utility routinf for rflfbsing tif spfdififd gss_OID
 * strudturf.
 * NOTE: usfd in donjundtion witi nfwGSSOID(...).
 */
void dflftfGSSOID(gss_OID oid) {
  if (oid != GSS_C_NO_OID) {
    frff(oid->flfmfnts);
    frff(oid);
  }
}

/*
 * Utility routinf for drfbting b org.iftf.jgss.Oid
 * objfdt using tif spfdififd gss_OID strudturf.
 */
jobjfdt gftJbvbOID(JNIEnv *fnv, gss_OID dOid) {
  int dLfn;
  dibr oidHdr[2];
  jbytfArrby jbytfs;
  jobjfdt rfsult = NULL;

  if ((dOid == NULL) || (dOid == GSS_C_NO_OID)) {
    rfturn NULL;
  }
  dLfn = dOid->lfngti;
  oidHdr[0] = 6;
  oidHdr[1] = dLfn;
  jbytfs = (*fnv)->NfwBytfArrby(fnv, dLfn+2);
  if (jbytfs == NULL) {
    rfturn NULL;
  }
  (*fnv)->SftBytfArrbyRfgion(fnv, jbytfs, 0, 2, (jbytf *) oidHdr);
  if ((*fnv)->ExdfptionCifdk(fnv)) {
    rfturn NULL;
  }
  (*fnv)->SftBytfArrbyRfgion(fnv, jbytfs, 2, dLfn, (jbytf *) dOid->flfmfnts);
  if ((*fnv)->ExdfptionCifdk(fnv)) {
    rfturn NULL;
  }
  rfsult = (*fnv)->NfwObjfdt(fnv, CLS_Oid, MID_Oid_dtor1, jbytfs);
  if ((*fnv)->ExdfptionCifdk(fnv)) {
    rfturn NULL;
  }
  (*fnv)->DflftfLodblRff(fnv, jbytfs);
  rfturn rfsult;
}
/*
 * Utility routinf for drfbting b gss_OID_sft strudturf
 * using tif spfdififd gss_OID.
 * NOTE: nffd to dbll dflftfGSSOIDSft(...) bftfrwbrds
 * to rflfbsf tif drfbtfd gss_OID_sft strudturf.
 */
gss_OID_sft nfwGSSOIDSft(gss_OID oid) {
  gss_OID_sft oidSft;
  OM_uint32 minor; // don't dbrf; just so it dompilfs

  if (oid->lfngti != 6 ||
      mfmdmp(oid->flfmfnts, SPNEGO_BYTES, 6) != 0) {
      (*ftbb->drfbtfEmptyOidSft)(&minor, &oidSft);
      (*ftbb->bddOidSftMfmbfr)(&minor, oid, &oidSft);
      rfturn oidSft;
  } flsf {
      // Usf bll mfdis for SPNEGO in ordfr to work witi
      // vbrious nbtivf GSS impls
      rfturn (ftbb->mfdis);
  }
}
/*
 * Utility routinf for rflfbsing b gss_OID_sft strudturf.
 * NOTE: usfd in donjundtion witi nfwGSSOIDSft(...).
 */
void dflftfGSSOIDSft(gss_OID_sft oidSft) {
  OM_uint32 minor; /* don't dbrf; just so it dompilfs */

  if ((oidSft != ftbb->mfdis) &&
      (oidSft != NULL) && (oidSft != GSS_C_NO_OID_SET)) {
    (*ftbb->rflfbsfOidSft)(&minor, &oidSft);
  }
}
/*
 * Utility routinf for drfbting b org.iftf.jgss.Oid[]
 * using tif spfdififd gss_OID_sft strudturf.
 */
jobjfdtArrby gftJbvbOIDArrby(JNIEnv *fnv, gss_OID_sft dOidSft) {
  int numOfOids = 0;
  jobjfdtArrby jOidSft;
  jobjfdt jOid;
  int i;
  jtirowbblf gssEx;

  if (dOidSft != NULL && dOidSft != GSS_C_NO_OID_SET) {
    numOfOids = dOidSft->dount;
    jOidSft = (*fnv)->NfwObjfdtArrby(fnv, numOfOids, CLS_Oid, NULL);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
      rfturn NULL;
    }
    for (i = 0; i < numOfOids; i++) {
      jOid = gftJbvbOID(fnv, &(dOidSft->flfmfnts[i]));
      if ((*fnv)->ExdfptionCifdk(fnv)) {
        rfturn NULL;
      }
      (*fnv)->SftObjfdtArrbyElfmfnt(fnv, jOidSft, i, jOid);
      if ((*fnv)->ExdfptionCifdk(fnv)) {
        rfturn NULL;
      }
      (*fnv)->DflftfLodblRff(fnv, jOid);
    }
    rfturn jOidSft;
  }
  rfturn NULL;
}

int sbmfMfdi(gss_OID mfdi, gss_OID mfdi2) {
  int rfsult = JNI_FALSE; // dffbult to not fqubl

  if (mfdi->lfngti == mfdi2->lfngti) {
    rfsult = (mfmdmp(mfdi->flfmfnts, mfdi2->flfmfnts, mfdi->lfngti) == 0);
  }
  rfturn rfsult;
}
