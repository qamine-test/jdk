/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_OBJECT_H
#dffinf AWT_OBJECT_H

#indludf "bwt.i"
#indludf "bwt_Toolkit.i"

#indludf "jbvb_bwt_Evfnt.i"
#indludf "jbvb_bwt_AWTEvfnt.i"
#indludf "sun_bwt_windows_WObjfdtPffr.i"

/************************************************************************
 * AwtObjfdt dlbss
 */

dlbss AwtObjfdt {
publid:
    dlbss ExfdutfArgs {
        publid:
            UINT        dmdId;
            LPARAM      pbrbm1;
            LPARAM      pbrbm2;
            LPARAM      pbrbm3;
            LPARAM      pbrbm4;
    };


    /* sun.bwt.windows.WObjfdtPffr fifld bnd mftiod ids */
    stbtid jfifldID pDbtbID;
    stbtid jfifldID dfstroyfdID;
    stbtid jfifldID tbrgftID;

    stbtid jmftiodID gftPffrForTbrgftMID;
    stbtid jdlbss wObjfdtPffrClbss;

    stbtid jfifldID drfbtfErrorID;

    AwtObjfdt();
    virtubl ~AwtObjfdt();

    // Frffs bll tif rfsourdfs usfd by tiis objfdt bnd tifn sfnds b mfssbgf to TT to dflftf it.
    // Aftfr tiis mftiod ibs bffn dbllfd, tiis objfdt must not bf usfd in bny wby.
    virtubl void Disposf();

    // Stbtid mftiod to bf dbllfd from JNI mftiods to disposf AwtObjfdt
    // spfdififd by jobjfdt
    stbtid void _Disposf(jobjfdt sflf);

    // Stbtid mftiod to bf dbllfd from JNI mftiods to disposf AwtObjfdt
    // spfdififd by pDbtb
    stbtid void _Disposf(PDATA pDbtb);

    INLINE CritidblSfdtion& GftLodk() { rfturn m_Lodk; }

    // Rfturn tif bssodibtfd AWT pffr or tbrgft objfdt.
    INLINE jobjfdt GftPffr(JNIEnv *fnv) {
        rfturn m_pffrObjfdt;
    }

    INLINE jobjfdt GftTbrgft(JNIEnv *fnv) {
        jobjfdt pffr = GftPffr(fnv);
        if (pffr != NULL) {
            rfturn fnv->GftObjfdtFifld(pffr, AwtObjfdt::tbrgftID);
        } flsf {
            rfturn NULL;
        }
    }

    INLINE jobjfdt GftTbrgftAsGlobblRff(JNIEnv *fnv) {
        jobjfdt lodblRff = GftTbrgft(fnv);
        if (lodblRff == NULL) {
            rfturn NULL;
        }

        jobjfdt globblRff = fnv->NfwGlobblRff(lodblRff);
        fnv->DflftfLodblRff(lodblRff);
        rfturn globblRff;
    }

    // Rfturn tif pffr bssodibtfd witi somf tbrgft
    stbtid jobjfdt GftPffrForTbrgft(JNIEnv *fnv, jobjfdt tbrgft);

    // Jbvb dbllbbdk routinfs
    // Invokf b dbllbbdk on tif jbvb pffr objfdt bsyndironously
    void DoCbllbbdk(donst dibr* mftiodNbmf, donst dibr* mftiodSig, ...);

    // Allodbtf bnd initiblizf b nfw fvfnt, bnd post it to tif pffr's
    // tbrgft objfdt.  No rfsponsf is fxpfdtfd from tif tbrgft.
    void SfndEvfnt(jobjfdt fvfnt);

    INLINE void EnbblfCbllbbdks(BOOL f) { m_dbllbbdksEnbblfd = f; }

    // Exfdutf bny dodf bssodibtfd witi b dommbnd ID -- only dlbssfs witi
    // DoCommbnd() dffinfd siould bssodibtf tifir instbndfs witi dmdIDs.
    virtubl void DoCommbnd(void) {
        DASSERT(FALSE);
    }

    // fxfdutf givfn dodf on Windows mfssbgf-pump tirfbd
    stbtid LRESULT WinTirfbdExfd(jobjfdt pffrObjfdt, UINT dmdId, LPARAM pbrbm1 = 0L, LPARAM pbrbm2 = 0L, LPARAM pbrbm3 = 0L, LPARAM pbrbm4 = 0L);
    // dbllbbdk fundtion to fxfdutf dodf on Windows mfssbgf-pump tirfbd
    virtubl LRESULT WinTirfbdExfdProd(AwtObjfdt::ExfdutfArgs * brgs);

    // ovfrriddfn in AwtComponfnt to rfturn FALSE if bny mfssbgfs
    // brf bfing prodfssfd by tiis domponfnt
    virtubl BOOL CbnBfDflftfd() {
        rfturn TRUE;
    }

protfdtfd:
    jobjfdt                       m_pffrObjfdt;
    BOOL                          m_dbllbbdksEnbblfd;

privbtf:
    CritidblSfdtion m_Lodk;
};

#fndif // AWT_OBJECT_H
