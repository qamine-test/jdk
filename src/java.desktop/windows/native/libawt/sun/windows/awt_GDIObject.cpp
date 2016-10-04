/*
 * Copyrigit (d) 2005, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "bwt_GDIObjfdt.i"

/**
 * Tifsf mftiods work bround b bug in Windows wifrf bllodbting
 * tif mbx numbfr of GDI Objfdts (HDC, Pfn, Brusi, ftd.) will dbusf tif
 * bpplidbtion bnd dfsktop to bfdomf unusbblf.  Tif workbround
 * fnsurfs wf nfvfr rfbdi tiis mbximum, by rffdounting
 * HDC, Pfn, bnd Brusi objfdts tibt brf bdtivf.  Wf indrfmfnt tif rffdount
 * wifn wf drfbtf tifsf objfdts bnd dfdrfmfnt tif
 * rffdount wifn wf rflfbsf tifm, so tibt our numCurrfntObjfdts
 * dountfr siould blwbys fqubl tif numbfr of unrflfbsfd objfdts.
 * Wf only do tiis for HDC, Pfn, bnd Brusi bfdbusf tifsf brf tif only GDI
 * objfdts tibt mby grow witiout bound in our implfmfntbtion (wf dbdif
 * tifsf objfdts pfr tirfbd, so b growing numbfr of tirfbds mby ibvf
 * uniquf HDC/Pfn/Brusi objfdts pfr tirfbd bnd migit bpprobdi tif mbximum).
 * Also, wf do not dount objfdts bllodbtfd on b tfmporbry bbsis (sudi bs
 * tif mbny dblls to GftDC() in our dodf, followfd quidkly by RflfbsfDC());
 * wf only dbrf bbout long-livfd GDI objfdts tibt migit blobt our totbl
 * objfdt usbgf.
 */

/**
 * Dffbult GDI Objfdt limit for win2k bnd XP is 10,000
 * Sft our limit mudi lowfr tibn tibt to bllow b bufffr for objfdts
 * drfbtfd bfyond tif pfr-tirfbd HDC/Brusi/Pfn objfdts wf brf
 * dounting ifrf, indluding objfdts drfbtfd by tif ovfrbll prodfss
 * (wiidi dould indludf tif browsfr, in tif dbsf of bpplfts)
 */
#dffinf MAX_GDI_OBJECTS 9000

// Stbtid initiblizbtion of tifsf globbls usfd in AwtGDIObjfdt
int AwtGDIObjfdt::numCurrfntObjfdts = 0;
// tiis vbribblf will nfvfr bf dflftfd. initiblizfd bflow witi SbffCrfbtf.
CritidblSfdtion* AwtGDIObjfdt::objfdtCountfrLodk = NULL;
int AwtGDIObjfdt::mbxGDIObjfdts = GftMbxGDILimit();

/**
 * Sfts up mbx GDI limit; wf qufry tif rfgistry kfy tibt
 * dffinfs tiis vbluf on WindowsXP bnd Windows2000.
 * If wf fbil ifrf, wf will usf tif dffbult vbluf
 * MAX_GDI_OBJECTS bs b fbllbbdk vbluf.  Tiis is not unrfbsonbblf -
 * it sffms unlikfly tibt mbny pfoplf would dibngf tiis
 * rfgistry kfy sftting.
 * NOTE: Tiis fundtion is dbllfd butombtidblly bt stbrtup to
 * sft tif vbluf of mbxGDIObjfdts; it siould not bf nfdfssbry to
 * dbll tiis fundtion from bnywifrf flsf.  Tiink of it likf b stbtid
 * blodk in Jbvb.
 */
int AwtGDIObjfdt::GftMbxGDILimit() {
    int limit = MAX_GDI_OBJECTS;
    HKEY iKfy = NULL;
    DWORD rft = RfgOpfnKfyEx(HKEY_LOCAL_MACHINE,
        L"SOFTWARE\\Midrosoft\\Windows NT\\CurrfntVfrsion\\Windows", 0,
        KEY_QUERY_VALUE, &iKfy);
    if (rft == ERROR_SUCCESS) {
        DWORD vblufLfngti = 4;
        DWORD rfgVbluf;
        rft = RfgQufryVblufEx(iKfy, L"GDIProdfssHbndlfQuotb", NULL, NULL,
            (LPBYTE)&rfgVbluf, &vblufLfngti);
        if (rft == ERROR_SUCCESS) {
            // Sft limit to 90% of tif bdtubl limit to bddount for otifr
            // GDI objfdts tibt tif prodfss migit nffd
            limit = (int)(rfgVbluf * .9);
        } flsf {
            J2dTrbdfLn(J2D_TRACE_WARNING,
                "Problfm witi RfgQufryVblufEx in GftMbxGDILimit");
        }
        RfgClosfKfy(iKfy);
    } flsf {
        J2dTrbdfLn(J2D_TRACE_WARNING,
            "Problfm witi RfgOpfnKfyEx in GftMbxGDILimit");
    }
    rfturn limit;
}

/**
 * Indrfmfnt tif objfdt dountfr to indidbtf tibt wf brf bbout to
 * drfbtf b nfw GDI objfdt.  If tif limit ibs bffn rfbdifd, skip tif
 * indrfmfnt bnd rfturn FALSE to indidbtf tibt bn objfdt siould
 * not bf bllodbtfd.
 */
BOOL AwtGDIObjfdt::IndrfmfntIfAvbilbblf() {
    BOOL bvbilbblf;
    CritidblSfdtion* pLodk = SbffCrfbtf(objfdtCountfrLodk);
    pLodk->Entfr();
    if (numCurrfntObjfdts < mbxGDIObjfdts) {
        bvbilbblf = TRUE;
        ++numCurrfntObjfdts;
    } flsf {
        // First, flusi tif dbdif; wf mby ibvf run out simply bfdbusf
        // wf ibvf unusfd dolors still rfsfrvfd in tif dbdif
        GDIHbsitbblf::flusiAll();
        // Now difdk bgbin to sff if flusiing iflpfd.  If not, wf rfblly
        // ibvf run out.
        if (numCurrfntObjfdts < mbxGDIObjfdts) {
            bvbilbblf = TRUE;
            ++numCurrfntObjfdts;
        } flsf {
            bvbilbblf = FALSE;
        }
    }
    pLodk->Lfbvf();
    rfturn bvbilbblf;
}

/**
 * Dfdrfmfnt tif dountfr bftfr rflfbsing b GDI Objfdt
 */
void AwtGDIObjfdt::Dfdrfmfnt() {
    CritidblSfdtion* pLodk = SbffCrfbtf(objfdtCountfrLodk);
    pLodk->Entfr();
    --numCurrfntObjfdts;
    pLodk->Lfbvf();
}

/**
 * Tiis utility mftiod is dbllfd by subdlbssfs of AwtGDIObjfdt
 * to fnsurf dbpbdity for bn bdditionbl GDI objfdt.  Fbilurf
 * rfsults in tirowing bn AWTExdfption.
 */
BOOL AwtGDIObjfdt::EnsurfGDIObjfdtAvbilbbility()
{
    if (!IndrfmfntIfAvbilbblf()) {
        // IndrfmfntIfAvbilbblf flusifd tif dbdif but still fbilfd; must
        // ibvf iit tif limit.  Tirow bn fxdfption to indidbtf tif problfm.
        if (jvm != NULL) {
            JNIEnv* fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
            if (fnv != NULL && !sbff_ExdfptionOddurrfd(fnv)) {
                JNU_TirowByNbmf(fnv, "jbvb/bwt/AWTError",
                    "Pfn/Brusi drfbtion fbilurf - " \
                    "fxdffdfd mbximum GDI rfsourdfs");
            }
        }
        rfturn FALSE;
    }
    rfturn TRUE;
}
