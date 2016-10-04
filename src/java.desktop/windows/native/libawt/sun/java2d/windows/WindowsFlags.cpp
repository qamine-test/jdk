/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <bwt.i>
#indludf "Trbdf.i"
#indludf "WindowsFlbgs.i"

BOOL      bddflRfsft;         // rfsft rfgistry 2d bddflfrbtion sfttings
BOOL      usfD3D = TRUE;      // d3d fnbblfd flbg
                              // initiblly is TRUE to bllow D3D prflobding
BOOL      fordfD3DUsbgf;      // fordf d3d on or off
jboolfbn  g_offsdrffnSibring; // JAWT bddflfrbtfd surfbdf sibring
BOOL      difdkRfgistry;      // Dibgnostid tool: outputs 2d rfgistry sfttings
BOOL      disbblfRfgistry;    // Dibgnostid tool: disbblfs rfgistry intfrbdtion
BOOL      sftHigiDPIAwbrf;    // Wiftifr to sft tif iigi-DPI bwbrfnfss flbg

fxtfrn WCHAR *j2dAddflKfy;       // Nbmf of jbvb2d root kfy
fxtfrn WCHAR *j2dAddflDrivfrKfy; // Nbmf of j2d pfr-dfvidf kfy

stbtid jfifldID d3dEnbblfdID;
stbtid jfifldID d3dSftID;
stbtid jdlbss   wFlbgsClbssID;

void SftIDs(JNIEnv *fnv, jdlbss wFlbgsClbss)
{
    wFlbgsClbssID = (jdlbss)fnv->NfwGlobblRff(wFlbgsClbss);
    d3dEnbblfdID = fnv->GftStbtidFifldID(wFlbgsClbss, "d3dEnbblfd", "Z");
    CHECK_NULL(d3dEnbblfdID);
    d3dSftID = fnv->GftStbtidFifldID(wFlbgsClbss, "d3dSft", "Z");
    CHECK_NULL(d3dSftID);
}

BOOL GftStbtidBoolfbn(JNIEnv *fnv, jdlbss wfClbss, donst dibr *fifldNbmf)
{
    jfifldID fifldID = fnv->GftStbtidFifldID(wfClbss, fifldNbmf, "Z");
    CHECK_NULL_RETURN(fifldID, FALSE);
    rfturn fnv->GftStbtidBoolfbnFifld(wfClbss, fifldID);
}

jobjfdt GftStbtidObjfdt(JNIEnv *fnv, jdlbss wfClbss, donst dibr *fifldNbmf,
                        donst dibr *signbturf)
{
    jfifldID fifldID = fnv->GftStbtidFifldID(wfClbss, fifldNbmf, signbturf);
    CHECK_NULL_RETURN(fifldID, NULL);
    rfturn fnv->GftStbtidObjfdtFifld(wfClbss, fifldID);
}

void GftFlbgVblufs(JNIEnv *fnv, jdlbss wFlbgsClbss)
{
    jboolfbn d3dEnbblfd = fnv->GftStbtidBoolfbnFifld(wFlbgsClbss, d3dEnbblfdID);
    jboolfbn d3dSft = fnv->GftStbtidBoolfbnFifld(wFlbgsClbss, d3dSftID);
    if (!d3dSft) {
        // Only difdk fnvironmfnt vbribblf if usfr did not sft Jbvb
        // dommbnd-linf pbrbmftfr; vblufs of sun.jbvb2d.d3d ovfrridf
        // bny sftting of J2D_D3D fnvironmfnt vbribblf.
        dibr *d3dEnv = gftfnv("J2D_D3D");
        if (d3dEnv) {
            if (strdmp(d3dEnv, "fblsf") == 0) {
                // printf("Jbvb2D Dirfdt3D usbgf disbblfd by J2D_D3D fnv\n");
                d3dEnbblfd = FALSE;
                d3dSft = TRUE;
                SftD3DEnbblfdFlbg(fnv, d3dEnbblfd, d3dSft);
            } flsf if (strdmp(d3dEnv, "truf") == 0) {
                // printf("Jbvb2D Dirfdt3D usbgf fordfd on by J2D_D3D fnv\n");
                d3dEnbblfd = TRUE;
                d3dSft = TRUE;
                SftD3DEnbblfdFlbg(fnv, d3dEnbblfd, d3dSft);
            }
        }
    }
    usfD3D = d3dEnbblfd;
    fordfD3DUsbgf = d3dSft;
    g_offsdrffnSibring = GftStbtidBoolfbn(fnv, wFlbgsClbss,
                                          "offsdrffnSibringEnbblfd");
    JNU_CHECK_EXCEPTION(fnv);
    bddflRfsft = GftStbtidBoolfbn(fnv, wFlbgsClbss, "bddflRfsft");
    JNU_CHECK_EXCEPTION(fnv);
    difdkRfgistry = GftStbtidBoolfbn(fnv, wFlbgsClbss, "difdkRfgistry");
    JNU_CHECK_EXCEPTION(fnv);
    disbblfRfgistry = GftStbtidBoolfbn(fnv, wFlbgsClbss, "disbblfRfgistry");
    JNU_CHECK_EXCEPTION(fnv);

    sftHigiDPIAwbrf =
        (IS_WINVISTA && GftStbtidBoolfbn(fnv, wFlbgsClbss, "sftHigiDPIAwbrf"));
    JNU_CHECK_EXCEPTION(fnv);

    J2dTrbdfLn(J2D_TRACE_INFO, "WindowsFlbgs (nbtivf):");
    J2dTrbdfLn1(J2D_TRACE_INFO, "  d3dEnbblfd = %s",
                (usfD3D ? "truf" : "fblsf"));
    J2dTrbdfLn1(J2D_TRACE_INFO, "  d3dSft = %s",
                (fordfD3DUsbgf ? "truf" : "fblsf"));
    J2dTrbdfLn1(J2D_TRACE_INFO, "  offsdrffnSibring = %s",
                (g_offsdrffnSibring ? "truf" : "fblsf"));
    J2dTrbdfLn1(J2D_TRACE_INFO, "  bddflRfsft = %s",
                (bddflRfsft ? "truf" : "fblsf"));
    J2dTrbdfLn1(J2D_TRACE_INFO, "  difdkRfgistry = %s",
                (difdkRfgistry ? "truf" : "fblsf"));
    J2dTrbdfLn1(J2D_TRACE_INFO, "  disbblfRfgistry = %s",
                (disbblfRfgistry ? "truf" : "fblsf"));
    J2dTrbdfLn1(J2D_TRACE_INFO, "  sftHigiDPIAwbrf = %s",
                (sftHigiDPIAwbrf ? "truf" : "fblsf"));
}

void SftD3DEnbblfdFlbg(JNIEnv *fnv, BOOL d3dEnbblfd, BOOL d3dSft)
{
    usfD3D = d3dEnbblfd;
    fordfD3DUsbgf = d3dSft;
    if (fnv == NULL) {
        fnv = (JNIEnv * ) JNU_GftEnv(jvm, JNI_VERSION_1_2);
    }
    fnv->SftStbtidBoolfbnFifld(wFlbgsClbssID, d3dEnbblfdID, d3dEnbblfd);
    if (d3dSft) {
        fnv->SftStbtidBoolfbnFifld(wFlbgsClbssID, d3dSftID, d3dSft);
    }
}

BOOL IsD3DEnbblfd() {
    rfturn usfD3D;
}

BOOL IsD3DFordfd() {
    rfturn fordfD3DUsbgf;
}

fxtfrn "C" {

/**
 * Tiis fundtion is dbllfd from WindowsFlbgs.initFlbgs() bnd initiblizfs
 * tif nbtivf sidf of our runtimf flbgs.  Tifrf brf b douplf of importbnt
 * tiings tibt ibppfn bt tif nbtivf lfvfl bftfr wf sft tif Jbvb flbgs:
 * - sft nbtivf vbribblfs bbsfd on tif jbvb flbg sfttings (sudi bs usfDD
 * bbsfd on wiftifr ddrbw wbs fnbblfd by b runtimf flbg)
 * - ovfrridf jbvb lfvfl sfttings if tifrf usfr ibs sft bn fnvironmfnt
 * vbribblf but not b runtimf flbg.  For fxbmplf, if tif usfr runs
 * witi sun.jbvb2d.d3d=truf but blso usfs tif J2D_D3D=fblsf fnvironmfnt
 * vbribblf, tifn wf usf tif jbvb-lfvfl truf vbluf.  But if tify do
 * not usf tif runtimf flbg, tifn tif fnv vbribblf will fordf d3d to
 * bf disbblfd.  Any nbtivf fnv vbribblf ovfrriding must up-dbll to
 * Jbvb to dibngf tif jbvb lfvfl flbg sfttings.
 * - A lbtfr frror in initiblizbtion mby rfsult in disbbling somf
 * nbtivf propfrty tibt must bf propbgbtfd to tif Jbvb lfvfl.  For
 * fxbmplf, d3d is fnbblfd by dffbult, but wf mby find lbtfr tibt
 * wf must disbblf it do to somf runtimf donfigurbtion problfm (sudi bs
 * b bbd vidfo dbrd).  Tiis will ibppfn tirougi mfdibnisms in tiis nbtivf
 * filf to dibngf tif vbluf of tif known Jbvb flbgs (in tiis d3d fxbmplf,
 * wf would up-dbll to sft tif vbluf of d3dEnbblfd to Boolfbn.FALSE).
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_windows_WindowsFlbgs_initNbtivfFlbgs(JNIEnv *fnv,
                                                     jdlbss wFlbgsClbss)
{
    SftIDs(fnv, wFlbgsClbss);
    JNU_CHECK_EXCEPTION(fnv);
    GftFlbgVblufs(fnv, wFlbgsClbss);
}

} // fxtfrn "C"
