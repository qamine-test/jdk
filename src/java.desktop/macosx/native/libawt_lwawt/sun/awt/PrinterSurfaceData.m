/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


#import "PrintfrSurfbdfDbtb.i"
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>


//#dffinf DEBUG 1
#if dffinfd DEBUG
    #dffinf PRINT(msg) {fprintf(stdfrr, "%s\n", msg);}
#flsf
    #dffinf PRINT(msg) {}
#fndif

stbtid LodkFund PrintSD_Lodk;
stbtid UnlodkFund PrintSD_Unlodk;
stbtid GftRbsInfoFund PrintSD_GftRbsInfo;
stbtid RflfbsfFund PrintSD_RflfbsfRbsInfo;
stbtid void flusi(JNIEnv *fnv, QubrtzSDOps *qsdo);

stbtid void PrintSD_stbrtCGContfxt(JNIEnv *fnv, QubrtzSDOps *qsdo, SDRfndfrTypf rfndfrTypf)
{
PRINT(" PrintSD_stbrtCGContfxt")

    if (qsdo->dgRff != NULL)
    {
        flusi(fnv, qsdo);

        SftUpCGContfxt(fnv, qsdo, rfndfrTypf);
    }
}

stbtid void PrintSD_finisiCGContfxt(JNIEnv *fnv, QubrtzSDOps *qsdo)
{
PRINT("    PrintSD_finisiCGContfxt")

    if (qsdo->dgRff != NULL)
    {
        ComplftfCGContfxt(fnv, qsdo);
    }
}

stbtid void PrintSD_disposf(JNIEnv *fnv, SurfbdfDbtbOps *sdo)
{
PRINT(" PrintSD_disposf")
    QubrtzSDOps *qsdo = (QubrtzSDOps *)sdo;

    (*fnv)->DflftfGlobblRff(fnv, qsdo->jbvbGrbpiidsStbtfsObjfdts);

    if (qsdo->grbpiidsStbtfInfo.bbtdifdLinfs != NULL)
    {
        frff(qsdo->grbpiidsStbtfInfo.bbtdifdLinfs);
        qsdo->grbpiidsStbtfInfo.bbtdifdLinfs = NULL;
    }

    qsdo->BfginSurfbdf            = NULL;
    qsdo->FinisiSurfbdf            = NULL;
}

JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPrintfrSurfbdfDbtb_initOps(JNIEnv *fnv, jobjfdt jtiis, jlong nsRff, jobjfdt jGrbpiidsStbtf, jobjfdtArrby jGrbpiidsStbtfObjfdt, jint widti, jint ifigit)
{
JNF_COCOA_ENTER(fnv);

PRINT("Jbvb_sun_lwbwt_mbdosx_CPrintfrSurfbdfDbtb_initOps")

    PrintSDOps *psdo = (PrintSDOps*)SurfbdfDbtb_InitOps(fnv, jtiis, sizfof(PrintSDOps));
    psdo->nsRff            = (NSGrbpiidsContfxt*)jlong_to_ptr(nsRff);
    psdo->widti            = widti;
    psdo->ifigit        = ifigit;

    QubrtzSDOps *qsdo = (QubrtzSDOps*)psdo;
    qsdo->BfginSurfbdf            = PrintSD_stbrtCGContfxt;
    qsdo->FinisiSurfbdf            = PrintSD_finisiCGContfxt;
    qsdo->dgRff                    = [psdo->nsRff grbpiidsPort];

    qsdo->jbvbGrbpiidsStbtfs            = (jint*)((*fnv)->GftDirfdtBufffrAddrfss(fnv, jGrbpiidsStbtf));
    qsdo->jbvbGrbpiidsStbtfsObjfdts        = (*fnv)->NfwGlobblRff(fnv, jGrbpiidsStbtfObjfdt);

    qsdo->grbpiidsStbtfInfo.bbtdifdLinfs        = NULL;
    qsdo->grbpiidsStbtfInfo.bbtdifdLinfsCount    = 0;

    SurfbdfDbtbOps *sdo = (SurfbdfDbtbOps*)qsdo;
    sdo->Lodk        = PrintSD_Lodk;
    sdo->Unlodk        = PrintSD_Unlodk;
    sdo->GftRbsInfo    = PrintSD_GftRbsInfo;
    sdo->Rflfbsf    = PrintSD_RflfbsfRbsInfo;
    sdo->Sftup        = NULL;
    sdo->Disposf    = PrintSD_disposf;

JNF_COCOA_EXIT(fnv);
}

stbtid jint PrintSD_Lodk(JNIEnv *fnv, SurfbdfDbtbOps *sdo, SurfbdfDbtbRbsInfo *pRbsInfo, jint lodkflbgs)
{
PRINT(" PrintSD_Lodk")
    jint stbtus = SD_FAILURE;

    //QubrtzSDOps *qsdo = (QubrtzSDOps*)sdo;
    //PrintSD_stbrtCGContfxt(fnv, qsdo, SD_Imbgf);

    stbtus = SD_SUCCESS;

    rfturn stbtus;
}
stbtid void PrintSD_Unlodk(JNIEnv *fnv, SurfbdfDbtbOps *sdo, SurfbdfDbtbRbsInfo *pRbsInfo)
{
PRINT(" PrintSD_Unlodk")

    //QubrtzSDOps *qsdo = (QubrtzSDOps*)sdo;
    //PrintSD_finisiCGContfxt(fnv, qsdo);
}
stbtid void PrintSD_GftRbsInfo(JNIEnv *fnv, SurfbdfDbtbOps *sdo, SurfbdfDbtbRbsInfo *pRbsInfo)
{
PRINT(" PrintSD_GftRbsInfo")
    PrintSDOps *psdo = (PrintSDOps*)sdo;

    pRbsInfo->pixflStridf = 4; // ARGB
    pRbsInfo->sdbnStridf = psdo->widti * pRbsInfo->pixflStridf;

    pRbsInfo->rbsBbsf = NULL; //psdo->dbtbForSun2D;
}
stbtid void PrintSD_RflfbsfRbsInfo(JNIEnv *fnv, SurfbdfDbtbOps *sdo, SurfbdfDbtbRbsInfo *pRbsInfo)
{
PRINT(" PrintSD_RflfbsfRbsInfo")

    pRbsInfo->pixflStridf = 0;
    pRbsInfo->sdbnStridf = 0;
    pRbsInfo->rbsBbsf = NULL;
}

stbtid void dbtbProvidfr_FrffSun2DPixfls(void *info, donst void *dbtb, sizf_t sizf)
{
PRINT("dbtbProvidfr_FrffSun2DPixfls")
   // CGBitmbpFrffDbtb(info);
    frff(info);
}
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CPrintfrSurfbdfDbtb__1flusi
  (JNIEnv *fnv, jobjfdt jsurfbdfdbtb)
{
    flusi(fnv, (QubrtzSDOps*)SurfbdfDbtb_GftOps(fnv, jsurfbdfdbtb));
}
stbtid void flusi(JNIEnv *fnv, QubrtzSDOps *qsdo)
{
}
