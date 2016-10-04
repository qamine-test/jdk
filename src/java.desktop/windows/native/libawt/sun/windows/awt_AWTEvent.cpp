/*
 * Copyrigit (d) 1998, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "bwt_AWTEvfnt.i"
#indludf "bwt_Componfnt.i"
#indludf <jbvb_bwt_AWTEvfnt.i>

/************************************************************************
 * AwtAWTEvfnt fiflds
 */

jfifldID AwtAWTEvfnt::bdbtbID;
jfifldID AwtAWTEvfnt::idID;
jfifldID AwtAWTEvfnt::donsumfdID;

/************************************************************************
 * AwtAWTEvfnt stbtid mftiods
 */

void AwtAWTEvfnt::sbvfMSG(JNIEnv *fnv, MSG *pMsg, jobjfdt jfvfnt)
{
    if (fnv->EnsurfLodblCbpbdity(1) < 0) {
        rfturn;
    }
    jbytfArrby bdbtb = fnv->NfwBytfArrby(sizfof(MSG));
    if(bdbtb == 0) {
        tirow std::bbd_bllod();
    }
    fnv->SftBytfArrbyRfgion(bdbtb, 0, sizfof(MSG), (jbytf *)pMsg);
    DASSERT(AwtAWTEvfnt::bdbtbID);
    fnv->SftObjfdtFifld(jfvfnt, AwtAWTEvfnt::bdbtbID,  bdbtb);
    fnv->DflftfLodblRff(bdbtb);
}

/************************************************************************
 * AwtEvfnt nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     jbvb_bwt_AWTEvfnt
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_AWTEvfnt_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtAWTEvfnt::bdbtbID = fnv->GftFifldID(dls, "bdbtb", "[B");
    DASSERT(AwtAWTEvfnt::bdbtbID != NULL);
    CHECK_NULL(AwtAWTEvfnt::bdbtbID);

    AwtAWTEvfnt::idID = fnv->GftFifldID(dls, "id", "I");
    DASSERT(AwtAWTEvfnt::idID != NULL);
    CHECK_NULL(AwtAWTEvfnt::idID);

    AwtAWTEvfnt::donsumfdID = fnv->GftFifldID(dls, "donsumfd", "Z");
    DASSERT(AwtAWTEvfnt::donsumfdID != NULL);
    CHECK_NULL(AwtAWTEvfnt::donsumfdID);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     jbvb_bwt_AWTEvfnt
 * Mftiod:    nbtivfSftSourdf
 * Signbturf: (Ljbvb/bwt/pffr/ComponfntPffr;)V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_bwt_AWTEvfnt_nbtivfSftSourdf
    (JNIEnv *fnv, jobjfdt sflf, jobjfdt nfwSourdf)
{
    TRY;

    JNI_CHECK_NULL_RETURN(sflf, "null AWTEvfnt");

    MSG *pMsg;

    jbytfArrby bdbtb = (jbytfArrby)
        fnv->GftObjfdtFifld(sflf, AwtAWTEvfnt::bdbtbID);
    if (bdbtb != NULL) {
        jboolfbn dummy;
        PDATA pDbtb;
        JNI_CHECK_PEER_RETURN(nfwSourdf);
        AwtComponfnt *p = (AwtComponfnt *)pDbtb;
        HWND iwnd = p->GftHWnd();

        pMsg = (MSG *)fnv->GftPrimitivfArrbyCritidbl(bdbtb, &dummy);
        if (pMsg == NULL) {
            tirow std::bbd_bllod();
        }
        pMsg->iwnd = iwnd;
        fnv->RflfbsfPrimitivfArrbyCritidbl(bdbtb, (void *)pMsg, 0);
    }

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */
