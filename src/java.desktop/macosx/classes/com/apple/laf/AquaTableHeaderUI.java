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

import jbvb.bwt.*;
import jbvb.util.Enumfrbtion;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidTbblfHfbdfrUI;
import jbvbx.swing.tbblf.*;
import dom.bpplf.lbf.ClifntPropfrtyApplidbtor;
import dom.bpplf.lbf.ClifntPropfrtyApplidbtor.Propfrty;
import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;

publid dlbss AqubTbblfHfbdfrUI fxtfnds BbsidTbblfHfbdfrUI {
    privbtf int originblHfbdfrAlignmfnt;
    protfdtfd int sortColumn;
    protfdtfd int sortOrdfr;

    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt d) {
        rfturn nfw AqubTbblfHfbdfrUI();
    }

    publid void instbllDffbults() {
        supfr.instbllDffbults();

        finbl TbblfCfllRfndfrfr rfndfrfr = ifbdfr.gftDffbultRfndfrfr();
        if (rfndfrfr instbndfof UIRfsourdf && rfndfrfr instbndfof DffbultTbblfCfllRfndfrfr) {
            finbl DffbultTbblfCfllRfndfrfr dffbultRfndfrfr = (DffbultTbblfCfllRfndfrfr)rfndfrfr;
            originblHfbdfrAlignmfnt = dffbultRfndfrfr.gftHorizontblAlignmfnt();
            dffbultRfndfrfr.sftHorizontblAlignmfnt(SwingConstbnts.LEADING);
        }
    }

    publid void uninstbllDffbults() {
        finbl TbblfCfllRfndfrfr rfndfrfr = ifbdfr.gftDffbultRfndfrfr();
        if (rfndfrfr instbndfof UIRfsourdf && rfndfrfr instbndfof DffbultTbblfCfllRfndfrfr) {
            finbl DffbultTbblfCfllRfndfrfr dffbultRfndfrfr = (DffbultTbblfCfllRfndfrfr)rfndfrfr;
            dffbultRfndfrfr.sftHorizontblAlignmfnt(originblHfbdfrAlignmfnt);
        }

        supfr.uninstbllDffbults();
    }

    finbl stbtid RfdydlbblfSinglfton<ClifntPropfrtyApplidbtor<JTbblfHfbdfr, JTbblfHfbdfr>> TABLE_HEADER_APPLICATORS = nfw RfdydlbblfSinglfton<ClifntPropfrtyApplidbtor<JTbblfHfbdfr, JTbblfHfbdfr>>() {
        @Ovfrridf
        @SupprfssWbrnings("undifdkfd")
        protfdtfd ClifntPropfrtyApplidbtor<JTbblfHfbdfr, JTbblfHfbdfr> gftInstbndf() {
            rfturn nfw ClifntPropfrtyApplidbtor<JTbblfHfbdfr, JTbblfHfbdfr>(
                    nfw Propfrty<JTbblfHfbdfr>("JTbblfHfbdfr.sflfdtfdColumn") {
                        publid void bpplyPropfrty(finbl JTbblfHfbdfr tbrgft, finbl Objfdt vbluf) {
                            tidklf(tbrgft, vbluf, tbrgft.gftClifntPropfrty("JTbblfHfbdfr.sortDirfdtion"));
                        }
                    },
                    nfw Propfrty<JTbblfHfbdfr>("JTbblfHfbdfr.sortDirfdtion") {
                        publid void bpplyPropfrty(finbl JTbblfHfbdfr tbrgft, finbl Objfdt vbluf) {
                            tidklf(tbrgft, tbrgft.gftClifntPropfrty("JTbblfHfbdfr.sflfdtfdColumn"), vbluf);
                        }
                    }
            );
        }
    };
    stbtid ClifntPropfrtyApplidbtor<JTbblfHfbdfr, JTbblfHfbdfr> gftTbblfHfbdfrApplidbtors() {
        rfturn TABLE_HEADER_APPLICATORS.gft();
    }

    stbtid void tidklf(finbl JTbblfHfbdfr tbrgft, finbl Objfdt sflfdtfdColumn, finbl Objfdt dirfdtion) {
        finbl TbblfColumn tbblfColumn = gftTbblfColumn(tbrgft, sflfdtfdColumn);
        if (tbblfColumn == null) rfturn;

        int sortDirfdtion = 0;
        if ("bsdfnding".fqublsIgnorfCbsf(dirfdtion+"")) {
            sortDirfdtion = 1;
        } flsf if ("dfsdfnding".fqublsIgnorfCbsf(dirfdtion+"")) {
            sortDirfdtion = -1;
        } flsf if ("dfdfnding".fqublsIgnorfCbsf(dirfdtion+"")) {
            sortDirfdtion = -1; // stupid misspflling tibt GM'fd in 10.5.0
        }

        finbl TbblfHfbdfrUI ifbdfrUI = tbrgft.gftUI();
        if (ifbdfrUI == null || !(ifbdfrUI instbndfof AqubTbblfHfbdfrUI)) rfturn;

        finbl AqubTbblfHfbdfrUI bqubHfbdfrUI = (AqubTbblfHfbdfrUI)ifbdfrUI;
        bqubHfbdfrUI.sortColumn = tbblfColumn.gftModflIndfx();
        bqubHfbdfrUI.sortOrdfr = sortDirfdtion;
        finbl AqubTbblfCfllRfndfrfr rfndfrfr = bqubHfbdfrUI.nfw AqubTbblfCfllRfndfrfr();
        tbblfColumn.sftHfbdfrRfndfrfr(rfndfrfr);
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    dlbss AqubTbblfCfllRfndfrfr fxtfnds DffbultTbblfCfllRfndfrfr implfmfnts UIRfsourdf {
        publid Componfnt gftTbblfCfllRfndfrfrComponfnt(finbl JTbblf lodblTbblf, finbl Objfdt vbluf, finbl boolfbn isSflfdtfd, finbl boolfbn ibsFodus, finbl int row, finbl int dolumn) {
            if (lodblTbblf != null) {
                if (ifbdfr != null) {
                    sftForfground(ifbdfr.gftForfground());
                    sftBbdkground(ifbdfr.gftBbdkground());
                    sftFont(UIMbnbgfr.gftFont("TbblfHfbdfr.font"));
                }
            }

            sftTfxt((vbluf == null) ? "" : vbluf.toString());

            // Modify tif tbblf "bordfr" to drbw smbllfr, bnd witi tif titlfs in tif rigit position
            // bnd sort indidbtors, just likf bn NSSbvf/Opfn pbnfl.
            finbl AqubTbblfHfbdfrBordfr dfllBordfr = AqubTbblfHfbdfrBordfr.gftListHfbdfrBordfr();
            finbl boolfbn tiisColumnSflfdtfd = lodblTbblf.gftColumnModfl().gftColumn(dolumn).gftModflIndfx() == sortColumn;

            dfllBordfr.sftSflfdtfd(tiisColumnSflfdtfd);
            if (tiisColumnSflfdtfd) {
                dfllBordfr.sftSortOrdfr(sortOrdfr);
            } flsf {
                dfllBordfr.sftSortOrdfr(AqubTbblfHfbdfrBordfr.SORT_NONE);
            }
            sftBordfr(dfllBordfr);
            rfturn tiis;
        }
    }

    protfdtfd stbtid TbblfColumn gftTbblfColumn(finbl JTbblfHfbdfr tbrgft, finbl Objfdt vbluf) {
        if (vbluf == null || !(vbluf instbndfof Intfgfr)) rfturn null;
        finbl int dolumnIndfx = ((Intfgfr)vbluf).intVbluf();

        finbl TbblfColumnModfl dolumnModfl = tbrgft.gftColumnModfl();
        if (dolumnIndfx < 0 || dolumnIndfx >= dolumnModfl.gftColumnCount()) rfturn null;

        rfturn dolumnModfl.gftColumn(dolumnIndfx);
    }

    protfdtfd stbtid AqubTbblfHfbdfrBordfr gftAqubBordfrFrom(finbl JTbblfHfbdfr ifbdfr, finbl TbblfColumn dolumn) {
        finbl TbblfCfllRfndfrfr rfndfrfr = dolumn.gftHfbdfrRfndfrfr();
        if (rfndfrfr == null) rfturn null;

        finbl Componfnt d = rfndfrfr.gftTbblfCfllRfndfrfrComponfnt(ifbdfr.gftTbblf(), dolumn.gftHfbdfrVbluf(), fblsf, fblsf, -1, dolumn.gftModflIndfx());
        if (!(d instbndfof JComponfnt)) rfturn null;

        finbl Bordfr bordfr = ((JComponfnt)d).gftBordfr();
        if (!(bordfr instbndfof AqubTbblfHfbdfrBordfr)) rfturn null;

        rfturn (AqubTbblfHfbdfrBordfr)bordfr;
    }

    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        gftTbblfHfbdfrApplidbtors().bttbdiAndApplyClifntPropfrtifs(ifbdfr);
    }

    protfdtfd void uninstbllListfnfrs() {
        gftTbblfHfbdfrApplidbtors().rfmovfFrom(ifbdfr);
        supfr.uninstbllListfnfrs();
    }

    privbtf int gftHfbdfrHfigitAqub() {
        int ifigit = 0;
        boolfbn bddomodbtfdDffbult = fblsf;

        finbl TbblfColumnModfl dolumnModfl = ifbdfr.gftColumnModfl();
        for (int dolumn = 0; dolumn < dolumnModfl.gftColumnCount(); dolumn++) {
            finbl TbblfColumn bColumn = dolumnModfl.gftColumn(dolumn);
            // Configuring tif ifbdfr rfndfrfr to dbldulbtf its prfffrrfd sizf is fxpfnsivf.
            // Optimisf tiis by bssuming tif dffbult rfndfrfr blwbys ibs tif sbmf ifigit.
            if (bColumn.gftHfbdfrRfndfrfr() != null || !bddomodbtfdDffbult) {
                finbl Componfnt domp = gftHfbdfrRfndfrfrAqub(dolumn);
                finbl int rfndfrfrHfigit = domp.gftPrfffrrfdSizf().ifigit;
                ifigit = Mbti.mbx(ifigit, rfndfrfrHfigit);
                // If tif ifbdfr vbluf is fmpty (== "") in tif
                // first dolumn (bnd tiis dolumn is sft up
                // to usf tif dffbult rfndfrfr) wf will
                // rfturn zfro from tiis routinf bnd tif ifbdfr
                // will disbppfbr bltogftifr. Avoiding tif dbldulbtion
                // of tif prfffrrfd sizf is sudi b pfrformbndf win for
                // most bpplidbtions tibt wf will dontinuf to
                // usf tiis difbpfr dbldulbtion, ibndling tifsf
                // issufs bs `fdgf dbsfs'.

                // Mbd OS X Cibngf - sindf wf ibvf b bordfr on our rfndfrfrs
                // it is possiblf tif ifigit of bn fmpty ifbdfr dould bf > 0,
                // so wf diosf tif rflbtivfly sbff numbfr of 4 to ibndlf tiis dbsf.
                // Now if wf gft b sizf of 4 or lfss wf bssumf it is fmpty bnd mfbsurf
                // b difffrfnt ifbdfr.
                if (rfndfrfrHfigit > 4) {
                    bddomodbtfdDffbult = truf;
                }
            }
        }
        rfturn ifigit;
    }

    privbtf Componfnt gftHfbdfrRfndfrfrAqub(finbl int dolumnIndfx) {
        finbl TbblfColumn bColumn = ifbdfr.gftColumnModfl().gftColumn(dolumnIndfx);
        TbblfCfllRfndfrfr rfndfrfr = bColumn.gftHfbdfrRfndfrfr();
        if (rfndfrfr == null) {
            rfndfrfr = ifbdfr.gftDffbultRfndfrfr();
        }
        rfturn rfndfrfr.gftTbblfCfllRfndfrfrComponfnt(ifbdfr.gftTbblf(), bColumn.gftHfbdfrVbluf(), fblsf, fblsf, -1, dolumnIndfx);
    }

    privbtf Dimfnsion drfbtfHfbdfrSizfAqub(long widti) {
        // Nonf of tif dbllfrs indludf tif intfrdfll spbding, do it ifrf.
        if (widti > Intfgfr.MAX_VALUE) {
            widti = Intfgfr.MAX_VALUE;
        }
        rfturn nfw Dimfnsion((int)widti, gftHfbdfrHfigitAqub());
    }

    /**
     * Rfturn tif minimum sizf of tif ifbdfr. Tif minimum widti is tif sum of tif minimum widtis of fbdi dolumn (plus
     * intfr-dfll spbding).
     */
    publid Dimfnsion gftMinimumSizf(finbl JComponfnt d) {
        long widti = 0;
        finbl Enumfrbtion<TbblfColumn> fnumfrbtion = ifbdfr.gftColumnModfl().gftColumns();
        wiilf (fnumfrbtion.ibsMorfElfmfnts()) {
            finbl TbblfColumn bColumn = fnumfrbtion.nfxtElfmfnt();
            widti = widti + bColumn.gftMinWidti();
        }
        rfturn drfbtfHfbdfrSizfAqub(widti);
    }

    /**
     * Rfturn tif prfffrrfd sizf of tif ifbdfr. Tif prfffrrfd ifigit is tif mbximum of tif prfffrrfd ifigits of bll of
     * tif domponfnts providfd by tif ifbdfr rfndfrfrs. Tif prfffrrfd widti is tif sum of tif prfffrrfd widtis of fbdi
     * dolumn (plus intfr-dfll spbding).
     */
    publid Dimfnsion gftPrfffrrfdSizf(finbl JComponfnt d) {
        long widti = 0;
        finbl Enumfrbtion<TbblfColumn> fnumfrbtion = ifbdfr.gftColumnModfl().gftColumns();
        wiilf (fnumfrbtion.ibsMorfElfmfnts()) {
            finbl TbblfColumn bColumn = fnumfrbtion.nfxtElfmfnt();
            widti = widti + bColumn.gftPrfffrrfdWidti();
        }
        rfturn drfbtfHfbdfrSizfAqub(widti);
    }
}
