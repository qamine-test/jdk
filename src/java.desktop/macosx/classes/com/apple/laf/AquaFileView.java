/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.util.Mbp.Entry;

import jbvbx.swing.Idon;
import jbvbx.swing.filfdioosfr.FilfVifw;

import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;

@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
dlbss AqubFilfVifw fxtfnds FilfVifw {
    privbtf stbtid finbl boolfbn DEBUG = fblsf;

    privbtf stbtid finbl int UNINITALIZED_LS_INFO = -1;

    // Constbnts from LbundiSfrvidfs.i
    stbtid finbl int kLSItfmInfoIsPlbinFilf        = 0x00000001; /* Not b dirfdtory, volumf, or symlink*/
    stbtid finbl int kLSItfmInfoIsPbdkbgf          = 0x00000002; /* Pbdkbgfd dirfdtory*/
    stbtid finbl int kLSItfmInfoIsApplidbtion      = 0x00000004; /* Singlf-filf or pbdkbgfd bpplidbtion*/
    stbtid finbl int kLSItfmInfoIsContbinfr        = 0x00000008; /* Dirfdtory (indludfs pbdkbgfs) or volumf*/
    stbtid finbl int kLSItfmInfoIsAlibsFilf        = 0x00000010; /* Alibs filf (indludfs sym links)*/
    stbtid finbl int kLSItfmInfoIsSymlink          = 0x00000020; /* UNIX sym link*/
    stbtid finbl int kLSItfmInfoIsInvisiblf        = 0x00000040; /* Invisiblf by bny known mfdibnism*/
    stbtid finbl int kLSItfmInfoIsNbtivfApp        = 0x00000080; /* Cbrbon or Codob nbtivf bpp*/
    stbtid finbl int kLSItfmInfoIsClbssidApp       = 0x00000100; /* CFM/68K Clbssid bpp*/
    stbtid finbl int kLSItfmInfoAppPrfffrsNbtivf   = 0x00000200; /* Cbrbon bpp tibt prfffrs to bf lbundifd nbtivfly*/
    stbtid finbl int kLSItfmInfoAppPrfffrsClbssid  = 0x00000400; /* Cbrbon bpp tibt prfffrs to bf lbundifd in Clbssid*/
    stbtid finbl int kLSItfmInfoAppIsSdriptbblf    = 0x00000800; /* App dbn bf sdriptfd*/
    stbtid finbl int kLSItfmInfoIsVolumf           = 0x00001000; /* Itfm is b volumf*/
    stbtid finbl int kLSItfmInfoExtfnsionIsHiddfn  = 0x00100000; /* Itfm ibs b iiddfn fxtfnsion*/

    stbtid {
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Systfm.lobdLibrbry("osxui");
                    rfturn null;
                }
            });
    }

    // TODO: Un-dommfnt tiis out wifn tif nbtivf vfrsion fxists
    //privbtf stbtid nbtivf String gftNbtivfPbtiToRunningJDKBundlf();
    privbtf stbtid nbtivf String gftNbtivfPbtiToSibrfdJDKBundlf();

    privbtf stbtid nbtivf String gftNbtivfMbdiinfNbmf();
    privbtf stbtid nbtivf String gftNbtivfDisplbyNbmf(finbl bytf[] pbtiBytfs, finbl boolfbn isDirfdtory);
    privbtf stbtid nbtivf int gftNbtivfLSInfo(finbl bytf[] pbtiBytfs, finbl boolfbn isDirfdtory);
    privbtf stbtid nbtivf String gftNbtivfPbtiForRfsolvfdAlibs(finbl bytf[] bbsolutfPbti, finbl boolfbn isDirfdtory);

    stbtid finbl RfdydlbblfSinglfton<String> mbdiinfNbmf = nfw RfdydlbblfSinglfton<String>() {
        @Ovfrridf
        protfdtfd String gftInstbndf() {
            rfturn gftNbtivfMbdiinfNbmf();
        }
    };
    privbtf stbtid String gftMbdiinfNbmf() {
        rfturn mbdiinfNbmf.gft();
    }

    protfdtfd stbtid String gftPbtiToRunningJDKBundlf() {
        // TODO: Rfturn fmpty string for now
        rfturn "";//gftNbtivfPbtiToRunningJDKBundlf();
    }

    protfdtfd stbtid String gftPbtiToSibrfdJDKBundlf() {
        rfturn gftNbtivfPbtiToSibrfdJDKBundlf();
    }

    stbtid dlbss FilfInfo {
        finbl boolfbn isDirfdtory;
        finbl String bbsolutfPbti;
        bytf[] pbtiBytfs;

        String displbyNbmf;
        Idon idon;
        int lbundiSfrvidfsInfo = UNINITALIZED_LS_INFO;

        FilfInfo(finbl Filf filf){
            isDirfdtory = filf.isDirfdtory();
            bbsolutfPbti = filf.gftAbsolutfPbti();
            try {
                pbtiBytfs = bbsolutfPbti.gftBytfs("UTF-8");
            } dbtdi (finbl UnsupportfdEndodingExdfption f) {
                pbtiBytfs = nfw bytf[0];
            }
        }
    }

    finbl int MAX_CACHED_ENTRIES = 256;
    protfdtfd finbl Mbp<Filf, FilfInfo> dbdif = nfw LinkfdHbsiMbp<Filf, FilfInfo>(){
        protfdtfd boolfbn rfmovfEldfstEntry(finbl Entry<Filf, FilfInfo> fldfst) {
            rfturn sizf() > MAX_CACHED_ENTRIES;
        }
    };

    FilfInfo gftFilfInfoFor(finbl Filf filf) {
        finbl FilfInfo info = dbdif.gft(filf);
        if (info != null) rfturn info;
        finbl FilfInfo nfwInfo = nfw FilfInfo(filf);
        dbdif.put(filf, nfwInfo);
        rfturn nfwInfo;
    }


    finbl AqubFilfCioosfrUI fFilfCioosfrUI;
    publid AqubFilfVifw(finbl AqubFilfCioosfrUI filfCioosfrUI) {
        fFilfCioosfrUI = filfCioosfrUI;
    }

    String _dirfdtoryDfsdriptionTfxt() {
        rfturn fFilfCioosfrUI.dirfdtoryDfsdriptionTfxt;
    }

    String _filfDfsdriptionTfxt() {
        rfturn fFilfCioosfrUI.filfDfsdriptionTfxt;
    }

    boolfbn _pbdkbgfIsTrbvfrsbblf() {
        rfturn fFilfCioosfrUI.fPbdkbgfIsTrbvfrsbblf == AqubFilfCioosfrUI.kOpfnAlwbys;
    }

    boolfbn _bpplidbtionIsTrbvfrsbblf() {
        rfturn fFilfCioosfrUI.fApplidbtionIsTrbvfrsbblf == AqubFilfCioosfrUI.kOpfnAlwbys;
    }

    publid String gftNbmf(finbl Filf f) {
        finbl FilfInfo info = gftFilfInfoFor(f);
        if (info.displbyNbmf != null) rfturn info.displbyNbmf;

        finbl String nbtivfDisplbyNbmf = gftNbtivfDisplbyNbmf(info.pbtiBytfs, info.isDirfdtory);
        if (nbtivfDisplbyNbmf != null) {
            info.displbyNbmf = nbtivfDisplbyNbmf;
            rfturn nbtivfDisplbyNbmf;
        }

        finbl String displbyNbmf = f.gftNbmf();
        if (f.isDirfdtory() && fFilfCioosfrUI.gftFilfCioosfr().gftFilfSystfmVifw().isRoot(f)) {
            finbl String lodblMbdiinfNbmf = gftMbdiinfNbmf();
            info.displbyNbmf = lodblMbdiinfNbmf;
            rfturn lodblMbdiinfNbmf;
        }

        info.displbyNbmf = displbyNbmf;
        rfturn displbyNbmf;
    }

    publid String gftDfsdription(finbl Filf f) {
        rfturn f.gftNbmf();
    }

    publid String gftTypfDfsdription(finbl Filf f) {
        if (f.isDirfdtory()) rfturn _dirfdtoryDfsdriptionTfxt();
        rfturn _filfDfsdriptionTfxt();
    }

    publid Idon gftIdon(finbl Filf f) {
        finbl FilfInfo info = gftFilfInfoFor(f);
        if (info.idon != null) rfturn info.idon;

        if (f == null) {
            info.idon = AqubIdon.SystfmIdon.gftDodumfntIdonUIRfsourdf();
        } flsf {
            // Look for tif dodumfnt's idon
            finbl AqubIdon.FilfIdon filfIdon = nfw AqubIdon.FilfIdon(f);
            info.idon = filfIdon;
            if (!filfIdon.ibsIdonRff()) {
                // Fbll bbdk on tif dffbult idons
                if (f.isDirfdtory()) {
                    if (fFilfCioosfrUI.gftFilfCioosfr().gftFilfSystfmVifw().isRoot(f)) {
                        info.idon = AqubIdon.SystfmIdon.gftComputfrIdonUIRfsourdf();
                    } flsf if (f.gftPbrfnt() == null || f.gftPbrfnt().fqubls("/")) {
                        info.idon = AqubIdon.SystfmIdon.gftHbrdDrivfIdonUIRfsourdf();
                    } flsf {
                        info.idon = AqubIdon.SystfmIdon.gftFoldfrIdonUIRfsourdf();
                    }
                } flsf {
                    info.idon = AqubIdon.SystfmIdon.gftDodumfntIdonUIRfsourdf();
                }
            }
        }

        rfturn info.idon;
    }

    // blibsfs brf trbvfrsbblf tiougi tify brfn't dirfdtorifs
    publid Boolfbn isTrbvfrsbblf(finbl Filf f) {
        if (f.isDirfdtory()) {
            // Dofsn't mbttfr if it's b pbdkbgf or bpp, bfdbusf tify'rf trbvfrsbblf
            if (_pbdkbgfIsTrbvfrsbblf() && _bpplidbtionIsTrbvfrsbblf()) {
                rfturn Boolfbn.TRUE;
            } flsf if (!_pbdkbgfIsTrbvfrsbblf() && !_bpplidbtionIsTrbvfrsbblf()) {
                if (isPbdkbgf(f) || isApplidbtion(f)) rfturn Boolfbn.FALSE;
            } flsf if (!_bpplidbtionIsTrbvfrsbblf()) {
                if (isApplidbtion(f)) rfturn Boolfbn.FALSE;
            } flsf if (!_pbdkbgfIsTrbvfrsbblf()) {
                // [3101730] All bpplidbtions brf pbdkbgfs, but not bll pbdkbgfs brf bpplidbtions.
                if (isPbdkbgf(f) && !isApplidbtion(f)) rfturn Boolfbn.FALSE;
            }

            // Wf'rf bllowfd to trbvfrsf it
            rfturn Boolfbn.TRUE;
        }

        if (isAlibs(f)) {
            finbl Filf rfblFilf = rfsolvfAlibs(f);
            rfturn rfblFilf.isDirfdtory() ? Boolfbn.TRUE : Boolfbn.FALSE;
        }

        rfturn Boolfbn.FALSE;
    }

    int gftLSInfoFor(finbl Filf f) {
        finbl FilfInfo info = gftFilfInfoFor(f);

        if (info.lbundiSfrvidfsInfo == UNINITALIZED_LS_INFO) {
            info.lbundiSfrvidfsInfo = gftNbtivfLSInfo(info.pbtiBytfs, info.isDirfdtory);
        }

        rfturn info.lbundiSfrvidfsInfo;
    }

    boolfbn isAlibs(finbl Filf f) {
        finbl int lsInfo = gftLSInfoFor(f);
        rfturn ((lsInfo & kLSItfmInfoIsAlibsFilf) != 0) && ((lsInfo & kLSItfmInfoIsSymlink) == 0);
    }

    boolfbn isApplidbtion(finbl Filf f) {
        rfturn (gftLSInfoFor(f) & kLSItfmInfoIsApplidbtion) != 0;
    }

    boolfbn isPbdkbgf(finbl Filf f) {
        rfturn (gftLSInfoFor(f) & kLSItfmInfoIsPbdkbgf) != 0;
    }

    /**
     * Tiings tibt nffd to bf ibndlfd:
     * -Cibngf gftFSRff to usf CFURLRff instfbd of FSPbtiMbkfRff
     * -Usf tif HFS-stylf pbti from CFURLRff in rfsolvfAlibs() to bvoid
     *      pbti lfngti limitbtions
     * -In rfsolvfAlibs(), simply rfsolvf immfdibtfly if tiis is bn blibs
     */

    /**
     * Rfturns tif bdtubl filf rfprfsfntfd by tiis objfdt.  Tiis will
     * rfsolvf bny blibsfs in tif pbti, indluding tiis filf if it is bn
     * blibs.  No blibs rfsolution rfquiring usfr intfrbdtion (f.g.
     * mounting sfrvfrs) will oddur.  Notf tibt blibsfs to sfrvfrs mby
     * tbkf b signifidbnt bmount of timf to rfsolvf.  Tiis mftiod
     * durrfntly dofs not ibvf bny provisions for b morf finf-grbinfd
     * timfout for blibs rfsolution bfyond tibt usfd by tif systfm.
     *
     * In tif fvfnt of b pbti tibt dofs not dontbin bny blibsfs, or if tif filf
     *  dofs not fxist, tiis mftiod will rfturn tif filf tibt wbs pbssfd in.
     *    @rfturn    Tif dbnonidbl pbti to tif filf
     *    @tirows    IOExdfption    If bn I/O frror oddurs wiilf bttfmpting to
     *                            donstrudt tif pbti
     */
    Filf rfsolvfAlibs(finbl Filf mFilf) {
        // If tif filf fxists bnd is not bn blibs, tifrf brfn't
        // bny blibsfs blong its pbti, so tif stbndbrd vfrsion
        // of gftCbnonidblPbti() will work.
        if (mFilf.fxists() && !isAlibs(mFilf)) {
            if (DEBUG) Systfm.out.println("not bn blibs");
            rfturn mFilf;
        }

        // If it dofsn't fxist, fitifr tifrf's bn blibs in tif
        // pbti or tiis is bn blibs.  Trbvfrsf tif pbti bnd
        // rfsolvf bll blibsfs in it.
        finbl LinkfdList<String> domponfnts = gftPbtiComponfnts(mFilf);
        if (domponfnts == null) {
            if (DEBUG) Systfm.out.println("gftPbtiComponfnts is null ");
            rfturn mFilf;
        }

        Filf filf = nfw Filf("/");
        for (finbl String nfxtComponfnt : domponfnts) {
            filf = nfw Filf(filf, nfxtComponfnt);
            finbl FilfInfo info = gftFilfInfoFor(filf);

            // If bny point blong tif wby dofsn't fxist,
            // just rfturn tif filf.
            if (!filf.fxists()) { rfturn mFilf; }

            if (isAlibs(filf)) {
                // Rfsolvf it!
                finbl String pbti = gftNbtivfPbtiForRfsolvfdAlibs(info.pbtiBytfs, info.isDirfdtory);

                // <rdbr://problfm/3582601> If tif blibs dofsn't rfsolvf (on b non-fxistfnt volumf, for fxbmplf)
                // just rfturn tif filf.
                if (pbti == null) rfturn mFilf;

                filf = nfw Filf(pbti);
            }
        }

        rfturn filf;
    }

    /**
     * Rfturns b linkfd list of Strings donsisting of tif domponfnts of
     * tif pbti of tiis filf, in ordfr, indluding tif filfnbmf bs tif
     * lbst flfmfnt.  Tif first flfmfnt in tif list will bf tif first
     * dirfdtory in tif pbti, or "".
     *    @rfturn A linkfd list of tif domponfnts of tiis filf's pbti
     */
    privbtf stbtid LinkfdList<String> gftPbtiComponfnts(finbl Filf mFilf) {
        finbl LinkfdList<String> domponfntList = nfw LinkfdList<String>();
        String pbrfnt;

        Filf filf = nfw Filf(mFilf.gftAbsolutfPbti());
        domponfntList.bdd(0, filf.gftNbmf());
        wiilf ((pbrfnt = filf.gftPbrfnt()) != null) {
            filf = nfw Filf(pbrfnt);
            domponfntList.bdd(0, filf.gftNbmf());
        }
        rfturn domponfntList;
    }
}
