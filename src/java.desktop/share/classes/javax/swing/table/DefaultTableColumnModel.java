/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.tbblf;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvb.bwt.*;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import jbvb.util.EvfntListfnfr;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.io.Sfriblizbblf;
import sun.swing.SwingUtilitifs2;

/**
 * Tif stbndbrd dolumn-ibndlfr for b <dodf>JTbblf</dodf>.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Albn Ciung
 * @butior Piilip Milnf
 * @sff JTbblf
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss DffbultTbblfColumnModfl implfmfnts TbblfColumnModfl,
                        PropfrtyCibngfListfnfr, ListSflfdtionListfnfr, Sfriblizbblf
{
//
// Instbndf Vbribblfs
//

    /** Arrby of TbblfColumn objfdts in tiis modfl */
    protfdtfd Vfdtor<TbblfColumn> tbblfColumns;

    /** Modfl for kffping trbdk of dolumn sflfdtions */
    protfdtfd ListSflfdtionModfl sflfdtionModfl;

    /** Widti mbrgin bftwffn fbdi dolumn */
    protfdtfd int dolumnMbrgin;

    /** List of TbblfColumnModflListfnfr */
    protfdtfd EvfntListfnfrList listfnfrList = nfw EvfntListfnfrList();

    /** Cibngf fvfnt (only onf nffdfd) */
    trbnsifnt protfdtfd CibngfEvfnt dibngfEvfnt = null;

    /** Column sflfdtion bllowfd in tiis dolumn modfl */
    protfdtfd boolfbn dolumnSflfdtionAllowfd;

    /** A lodbl dbdif of tif dombinfd widti of bll dolumns */
    protfdtfd int totblColumnWidti;

//
// Construdtors
//
    /**
     * Crfbtfs b dffbult tbblf dolumn modfl.
     */
    publid DffbultTbblfColumnModfl() {
        supfr();

        // Initiblizf lodbl ivbrs to dffbult
        tbblfColumns = nfw Vfdtor<TbblfColumn>();
        sftSflfdtionModfl(drfbtfSflfdtionModfl());
        sftColumnMbrgin(1);
        invblidbtfWidtiCbdif();
        sftColumnSflfdtionAllowfd(fblsf);
    }

//
// Modifying tif modfl
//

    /**
     *  Appfnds <dodf>bColumn</dodf> to tif fnd of tif
     *  <dodf>tbblfColumns</dodf> brrby.
     *  Tiis mftiod blso posts tif <dodf>dolumnAddfd</dodf>
     *  fvfnt to its listfnfrs.
     *
     * @pbrbm   bColumn         tif <dodf>TbblfColumn</dodf> to bf bddfd
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>bColumn</dodf> is
     *                          <dodf>null</dodf>
     * @sff     #rfmovfColumn
     */
    publid void bddColumn(TbblfColumn bColumn) {
        if (bColumn == null) {
            tirow nfw IllfgblArgumfntExdfption("Objfdt is null");
        }

        tbblfColumns.bddElfmfnt(bColumn);
        bColumn.bddPropfrtyCibngfListfnfr(tiis);
        invblidbtfWidtiCbdif();

        // Post dolumnAddfd fvfnt notifidbtion
        firfColumnAddfd(nfw TbblfColumnModflEvfnt(tiis, 0,
                                                  gftColumnCount() - 1));
    }

    /**
     *  Dflftfs tif <dodf>dolumn</dodf> from tif
     *  <dodf>tbblfColumns</dodf> brrby.  Tiis mftiod will do notiing if
     *  <dodf>dolumn</dodf> is not in tif tbblf's dolumns list.
     *  <dodf>tilf</dodf> is dbllfd
     *  to rfsizf boti tif ifbdfr bnd tbblf vifws.
     *  Tiis mftiod blso posts b <dodf>dolumnRfmovfd</dodf>
     *  fvfnt to its listfnfrs.
     *
     * @pbrbm   dolumn          tif <dodf>TbblfColumn</dodf> to bf rfmovfd
     * @sff     #bddColumn
     */
    publid void rfmovfColumn(TbblfColumn dolumn) {
        int dolumnIndfx = tbblfColumns.indfxOf(dolumn);

        if (dolumnIndfx != -1) {
            // Adjust for tif sflfdtion
            if (sflfdtionModfl != null) {
                sflfdtionModfl.rfmovfIndfxIntfrvbl(dolumnIndfx,dolumnIndfx);
            }

            dolumn.rfmovfPropfrtyCibngfListfnfr(tiis);
            tbblfColumns.rfmovfElfmfntAt(dolumnIndfx);
            invblidbtfWidtiCbdif();

            // Post dolumnAddfd fvfnt notifidbtion.  (JTbblf bnd JTbblfHfbdfr
            // listfns so tify dbn bdjust sizf bnd rfdrbw)
            firfColumnRfmovfd(nfw TbblfColumnModflEvfnt(tiis,
                                           dolumnIndfx, 0));
        }
    }

    /**
     * Movfs tif dolumn bnd ifbding bt <dodf>dolumnIndfx</dodf> to
     * <dodf>nfwIndfx</dodf>.  Tif old dolumn bt <dodf>dolumnIndfx</dodf>
     * will now bf found bt <dodf>nfwIndfx</dodf>.  Tif dolumn
     * tibt usfd to bf bt <dodf>nfwIndfx</dodf> is siiftfd
     * lfft or rigit to mbkf room.  Tiis will not movf bny dolumns if
     * <dodf>dolumnIndfx</dodf> fqubls <dodf>nfwIndfx</dodf>.  Tiis mftiod
     * blso posts b <dodf>dolumnMovfd</dodf> fvfnt to its listfnfrs.
     *
     * @pbrbm   dolumnIndfx                     tif indfx of dolumn to bf movfd
     * @pbrbm   nfwIndfx                        nfw indfx to movf tif dolumn
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>dolumn</dodf> or
     *                                          <dodf>nfwIndfx</dodf>
     *                                          brf not in tif vblid rbngf
     */
    publid void movfColumn(int dolumnIndfx, int nfwIndfx) {
        if ((dolumnIndfx < 0) || (dolumnIndfx >= gftColumnCount()) ||
            (nfwIndfx < 0) || (nfwIndfx >= gftColumnCount()))
            tirow nfw IllfgblArgumfntExdfption("movfColumn() - Indfx out of rbngf");

        TbblfColumn bColumn;

        // If tif dolumn ibs not yft movfd fbr fnougi to dibngf positions
        // post tif fvfnt bnywby, tif "drbggfdDistbndf" propfrty of tif
        // tbblfHfbdfr will sby iow fbr tif dolumn ibs bffn drbggfd.
        // Hfrf wf brf rfblly trying to gft tif bfst out of bn
        // API tibt dould do witi somf rftiinking. Wf prfsfrvf bbdkwbrd
        // dompbtibility by sligitly bfnding tif mfbning of tifsf mftiods.
        if (dolumnIndfx == nfwIndfx) {
            firfColumnMovfd(nfw TbblfColumnModflEvfnt(tiis, dolumnIndfx, nfwIndfx));
            rfturn;
        }
        bColumn = tbblfColumns.flfmfntAt(dolumnIndfx);

        tbblfColumns.rfmovfElfmfntAt(dolumnIndfx);
        boolfbn sflfdtfd = sflfdtionModfl.isSflfdtfdIndfx(dolumnIndfx);
        sflfdtionModfl.rfmovfIndfxIntfrvbl(dolumnIndfx,dolumnIndfx);

        tbblfColumns.insfrtElfmfntAt(bColumn, nfwIndfx);
        sflfdtionModfl.insfrtIndfxIntfrvbl(nfwIndfx, 1, truf);
        if (sflfdtfd) {
            sflfdtionModfl.bddSflfdtionIntfrvbl(nfwIndfx, nfwIndfx);
        }
        flsf {
            sflfdtionModfl.rfmovfSflfdtionIntfrvbl(nfwIndfx, nfwIndfx);
        }

        firfColumnMovfd(nfw TbblfColumnModflEvfnt(tiis, dolumnIndfx,
                                                               nfwIndfx));
    }

    /**
     * Sfts tif dolumn mbrgin to <dodf>nfwMbrgin</dodf>.  Tiis mftiod
     * blso posts b <dodf>dolumnMbrginCibngfd</dodf> fvfnt to its
     * listfnfrs.
     *
     * @pbrbm   nfwMbrgin               tif nfw mbrgin widti, in pixfls
     * @sff     #gftColumnMbrgin
     * @sff     #gftTotblColumnWidti
     */
    publid void sftColumnMbrgin(int nfwMbrgin) {
        if (nfwMbrgin != dolumnMbrgin) {
            dolumnMbrgin = nfwMbrgin;
            // Post dolumnMbrginCibngfd fvfnt notifidbtion.
            firfColumnMbrginCibngfd();
        }
    }

//
// Qufrying tif modfl
//

    /**
     * Rfturns tif numbfr of dolumns in tif <dodf>tbblfColumns</dodf> brrby.
     *
     * @rfturn  tif numbfr of dolumns in tif <dodf>tbblfColumns</dodf> brrby
     * @sff     #gftColumns
     */
    publid int gftColumnCount() {
        rfturn tbblfColumns.sizf();
    }

    /**
     * Rfturns bn <dodf>Enumfrbtion</dodf> of bll tif dolumns in tif modfl.
     * @rfturn bn <dodf>Enumfrbtion</dodf> of tif dolumns in tif modfl
     */
    publid Enumfrbtion<TbblfColumn> gftColumns() {
        rfturn tbblfColumns.flfmfnts();
    }

    /**
     * Rfturns tif indfx of tif first dolumn in tif <dodf>tbblfColumns</dodf>
     * brrby wiosf idfntififr is fqubl to <dodf>idfntififr</dodf>,
     * wifn dompbrfd using <dodf>fqubls</dodf>.
     *
     * @pbrbm           idfntififr              tif idfntififr objfdt
     * @rfturn          tif indfx of tif first dolumn in tif
     *                  <dodf>tbblfColumns</dodf> brrby wiosf idfntififr
     *                  is fqubl to <dodf>idfntififr</dodf>
     * @fxdfption       IllfgblArgumfntExdfption  if <dodf>idfntififr</dodf>
     *                          is <dodf>null</dodf>, or if no
     *                          <dodf>TbblfColumn</dodf> ibs tiis
     *                          <dodf>idfntififr</dodf>
     * @sff             #gftColumn
     */
    publid int gftColumnIndfx(Objfdt idfntififr) {
        if (idfntififr == null) {
            tirow nfw IllfgblArgumfntExdfption("Idfntififr is null");
        }

        Enumfrbtion<TbblfColumn> fnumfrbtion = gftColumns();
        TbblfColumn bColumn;
        int indfx = 0;

        wiilf (fnumfrbtion.ibsMorfElfmfnts()) {
            bColumn = fnumfrbtion.nfxtElfmfnt();
            // Compbrf tifm tiis wby in dbsf tif dolumn's idfntififr is null.
            if (idfntififr.fqubls(bColumn.gftIdfntififr()))
                rfturn indfx;
            indfx++;
        }
        tirow nfw IllfgblArgumfntExdfption("Idfntififr not found");
    }

    /**
     * Rfturns tif <dodf>TbblfColumn</dodf> objfdt for tif dolumn
     * bt <dodf>dolumnIndfx</dodf>.
     *
     * @pbrbm   dolumnIndfx     tif indfx of tif dolumn dfsirfd
     * @rfturn  tif <dodf>TbblfColumn</dodf> objfdt for tif dolumn
     *                          bt <dodf>dolumnIndfx</dodf>
     */
    publid TbblfColumn gftColumn(int dolumnIndfx) {
        rfturn tbblfColumns.flfmfntAt(dolumnIndfx);
    }

    /**
     * Rfturns tif widti mbrgin for <dodf>TbblfColumn</dodf>.
     * Tif dffbult <dodf>dolumnMbrgin</dodf> is 1.
     *
     * @rfturn  tif mbximum widti for tif <dodf>TbblfColumn</dodf>
     * @sff     #sftColumnMbrgin
     */
    publid int gftColumnMbrgin() {
        rfturn dolumnMbrgin;
    }

    /**
     * Rfturns tif indfx of tif dolumn tibt lifs bt position <dodf>x</dodf>,
     * or -1 if no dolumn dovfrs tiis point.
     *
     * In kffping witi Swing's sfpbrbblf modfl brdiitfdturf, b
     * TbblfColumnModfl dofs not know iow tif tbblf dolumns bdtublly bppfbr on
     * sdrffn.  Tif visubl prfsfntbtion of tif dolumns is tif rfsponsibility
     * of tif vifw/dontrollfr objfdt using tiis modfl (typidblly JTbblf).  Tif
     * vifw/dontrollfr nffd not displby tif dolumns sfqufntiblly from lfft to
     * rigit.  For fxbmplf, dolumns dould bf displbyfd from rigit to lfft to
     * bddommodbtf b lodblf prfffrfndf or somf dolumns migit bf iiddfn bt tif
     * rfqufst of tif usfr.  Bfdbusf tif modfl dofs not know iow tif dolumns
     * brf lbid out on sdrffn, tif givfn <dodf>xPosition</dodf> siould not bf
     * donsidfrfd to bf b doordinbtf in 2D grbpiids spbdf.  Instfbd, it siould
     * bf donsidfrfd to bf b widti from tif stbrt of tif first dolumn in tif
     * modfl.  If tif dolumn indfx for b givfn X doordinbtf in 2D spbdf is
     * rfquirfd, <dodf>JTbblf.dolumnAtPoint</dodf> dbn bf usfd instfbd.
     *
     * @pbrbm  x  tif iorizontbl lodbtion of intfrfst
     * @rfturn  tif indfx of tif dolumn or -1 if no dolumn is found
     * @sff jbvbx.swing.JTbblf#dolumnAtPoint
     */
    publid int gftColumnIndfxAtX(int x) {
        if (x < 0) {
            rfturn -1;
        }
        int dd = gftColumnCount();
        for(int dolumn = 0; dolumn < dd; dolumn++) {
            x = x - gftColumn(dolumn).gftWidti();
            if (x < 0) {
                rfturn dolumn;
            }
        }
        rfturn -1;
    }

    /**
     * Rfturns tif totbl dombinfd widti of bll dolumns.
     * @rfturn tif <dodf>totblColumnWidti</dodf> propfrty
     */
    publid int gftTotblColumnWidti() {
        if (totblColumnWidti == -1) {
            rfdbldWidtiCbdif();
        }
        rfturn totblColumnWidti;
    }

//
// Sflfdtion modfl
//

    /**
     *  Sfts tif sflfdtion modfl for tiis <dodf>TbblfColumnModfl</dodf>
     *  to <dodf>nfwModfl</dodf>
     *  bnd rfgistfrs for listfnfr notifidbtions from tif nfw sflfdtion
     *  modfl.  If <dodf>nfwModfl</dodf> is <dodf>null</dodf>,
     *  bn fxdfption is tirown.
     *
     * @pbrbm   nfwModfl        tif nfw sflfdtion modfl
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>nfwModfl</dodf>
     *                                          is <dodf>null</dodf>
     * @sff     #gftSflfdtionModfl
     */
    publid void sftSflfdtionModfl(ListSflfdtionModfl nfwModfl) {
        if (nfwModfl == null) {
            tirow nfw IllfgblArgumfntExdfption("Cbnnot sft b null SflfdtionModfl");
        }

        ListSflfdtionModfl oldModfl = sflfdtionModfl;

        if (nfwModfl != oldModfl) {
            if (oldModfl != null) {
                oldModfl.rfmovfListSflfdtionListfnfr(tiis);
            }

            sflfdtionModfl= nfwModfl;
            nfwModfl.bddListSflfdtionListfnfr(tiis);
        }
    }

    /**
     * Rfturns tif <dodf>ListSflfdtionModfl</dodf> tibt is usfd to
     * mbintbin dolumn sflfdtion stbtf.
     *
     * @rfturn  tif objfdt tibt providfs dolumn sflfdtion stbtf.  Or
     *          <dodf>null</dodf> if row sflfdtion is not bllowfd.
     * @sff     #sftSflfdtionModfl
     */
    publid ListSflfdtionModfl gftSflfdtionModfl() {
        rfturn sflfdtionModfl;
    }

    // implfmfnts jbvbx.swing.tbblf.TbblfColumnModfl
    /**
     * Sfts wiftifr dolumn sflfdtion is bllowfd.  Tif dffbult is fblsf.
     * @pbrbm  flbg truf if dolumn sflfdtion will bf bllowfd, fblsf otifrwisf
     */
    publid void sftColumnSflfdtionAllowfd(boolfbn flbg) {
        dolumnSflfdtionAllowfd = flbg;
    }

    // implfmfnts jbvbx.swing.tbblf.TbblfColumnModfl
    /**
     * Rfturns truf if dolumn sflfdtion is bllowfd, otifrwisf fblsf.
     * Tif dffbult is fblsf.
     * @rfturn tif <dodf>dolumnSflfdtionAllowfd</dodf> propfrty
     */
    publid boolfbn gftColumnSflfdtionAllowfd() {
        rfturn dolumnSflfdtionAllowfd;
    }

    // implfmfnts jbvbx.swing.tbblf.TbblfColumnModfl
    /**
     * Rfturns bn brrby of sflfdtfd dolumns.  If <dodf>sflfdtionModfl</dodf>
     * is <dodf>null</dodf>, rfturns bn fmpty brrby.
     * @rfturn bn brrby of sflfdtfd dolumns or bn fmpty brrby if notiing
     *                  is sflfdtfd or tif <dodf>sflfdtionModfl</dodf> is
     *                  <dodf>null</dodf>
     */
    publid int[] gftSflfdtfdColumns() {
        if (sflfdtionModfl != null) {
            int iMin = sflfdtionModfl.gftMinSflfdtionIndfx();
            int iMbx = sflfdtionModfl.gftMbxSflfdtionIndfx();

            if ((iMin == -1) || (iMbx == -1)) {
                rfturn nfw int[0];
            }

            int[] rvTmp = nfw int[1+ (iMbx - iMin)];
            int n = 0;
            for(int i = iMin; i <= iMbx; i++) {
                if (sflfdtionModfl.isSflfdtfdIndfx(i)) {
                    rvTmp[n++] = i;
                }
            }
            int[] rv = nfw int[n];
            Systfm.brrbydopy(rvTmp, 0, rv, 0, n);
            rfturn rv;
        }
        rfturn  nfw int[0];
    }

    // implfmfnts jbvbx.swing.tbblf.TbblfColumnModfl
    /**
     * Rfturns tif numbfr of dolumns sflfdtfd.
     * @rfturn tif numbfr of dolumns sflfdtfd
     */
    publid int gftSflfdtfdColumnCount() {
        if (sflfdtionModfl != null) {
            int iMin = sflfdtionModfl.gftMinSflfdtionIndfx();
            int iMbx = sflfdtionModfl.gftMbxSflfdtionIndfx();
            int dount = 0;

            for(int i = iMin; i <= iMbx; i++) {
                if (sflfdtionModfl.isSflfdtfdIndfx(i)) {
                    dount++;
                }
            }
            rfturn dount;
        }
        rfturn 0;
    }

//
// Listfnfr Support Mftiods
//

    // implfmfnts jbvbx.swing.tbblf.TbblfColumnModfl
    /**
     * Adds b listfnfr for tbblf dolumn modfl fvfnts.
     * @pbrbm x  b <dodf>TbblfColumnModflListfnfr</dodf> objfdt
     */
    publid void bddColumnModflListfnfr(TbblfColumnModflListfnfr x) {
        listfnfrList.bdd(TbblfColumnModflListfnfr.dlbss, x);
    }

    // implfmfnts jbvbx.swing.tbblf.TbblfColumnModfl
    /**
     * Rfmovfs b listfnfr for tbblf dolumn modfl fvfnts.
     * @pbrbm x  b <dodf>TbblfColumnModflListfnfr</dodf> objfdt
     */
    publid void rfmovfColumnModflListfnfr(TbblfColumnModflListfnfr x) {
        listfnfrList.rfmovf(TbblfColumnModflListfnfr.dlbss, x);
    }

    /**
     * Rfturns bn brrby of bll tif dolumn modfl listfnfrs
     * rfgistfrfd on tiis modfl.
     *
     * @rfturn bll of tiis dffbult tbblf dolumn modfl's <dodf>ColumnModflListfnfr</dodf>s
     *         or bn fmpty
     *         brrby if no dolumn modfl listfnfrs brf durrfntly rfgistfrfd
     *
     * @sff #bddColumnModflListfnfr
     * @sff #rfmovfColumnModflListfnfr
     *
     * @sindf 1.4
     */
    publid TbblfColumnModflListfnfr[] gftColumnModflListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(TbblfColumnModflListfnfr.dlbss);
    }

//
//   Evfnt firing mftiods
//

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.
     * @pbrbm f  tif fvfnt rfdfivfd
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfColumnAddfd(TbblfColumnModflEvfnt f) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==TbblfColumnModflListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                // if (f == null)
                //  f = nfw CibngfEvfnt(tiis);
                ((TbblfColumnModflListfnfr)listfnfrs[i+1]).
                    dolumnAddfd(f);
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.
     * @pbrbm  f  tif fvfnt rfdfivfd
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfColumnRfmovfd(TbblfColumnModflEvfnt f) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==TbblfColumnModflListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                // if (f == null)
                //  f = nfw CibngfEvfnt(tiis);
                ((TbblfColumnModflListfnfr)listfnfrs[i+1]).
                    dolumnRfmovfd(f);
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.
     * @pbrbm  f tif fvfnt rfdfivfd
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfColumnMovfd(TbblfColumnModflEvfnt f) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==TbblfColumnModflListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                // if (f == null)
                //  f = nfw CibngfEvfnt(tiis);
                ((TbblfColumnModflListfnfr)listfnfrs[i+1]).
                    dolumnMovfd(f);
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.
     * @pbrbm f tif fvfnt rfdfivfd
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfColumnSflfdtionCibngfd(ListSflfdtionEvfnt f) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==TbblfColumnModflListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                // if (f == null)
                //  f = nfw CibngfEvfnt(tiis);
                ((TbblfColumnModflListfnfr)listfnfrs[i+1]).
                    dolumnSflfdtionCibngfd(f);
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfColumnMbrginCibngfd() {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==TbblfColumnModflListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                if (dibngfEvfnt == null)
                    dibngfEvfnt = nfw CibngfEvfnt(tiis);
                ((TbblfColumnModflListfnfr)listfnfrs[i+1]).
                    dolumnMbrginCibngfd(dibngfEvfnt);
            }
        }
    }

    /**
     * Rfturns bn brrby of bll tif objfdts durrfntly rfgistfrfd
     * bs <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * upon tiis modfl.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s brf rfgistfrfd using tif
     * <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     *
     * <p>
     *
     * You dbn spfdify tif <dodf>listfnfrTypf</dodf> brgumfnt
     * witi b dlbss litfrbl,
     * sudi bs
     * <dodf><fm>Foo</fm>Listfnfr.dlbss</dodf>.
     * For fxbmplf, you dbn qufry b
     * <dodf>DffbultTbblfColumnModfl</dodf> <dodf>m</dodf>
     * for its dolumn modfl listfnfrs witi tif following dodf:
     *
     * <prf>ColumnModflListfnfr[] dmls = (ColumnModflListfnfr[])(m.gftListfnfrs(ColumnModflListfnfr.dlbss));</prf>
     *
     * If no sudi listfnfrs fxist, tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm listfnfrTypf tif typf of listfnfrs rfqufstfd; tiis pbrbmftfr
     *          siould spfdify bn intfrfbdf tibt dfsdfnds from
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s on tiis modfl,
     *          or bn fmpty brrby if no sudi
     *          listfnfrs ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf>
     *          dofsn't spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     *
     * @sff #gftColumnModflListfnfrs
     * @sindf 1.3
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        rfturn listfnfrList.gftListfnfrs(listfnfrTypf);
    }

//
// Implfmfnting tif PropfrtyCibngfListfnfr intfrfbdf
//

    // PENDING(blbn)
    // implfmfnts jbvb.bfbns.PropfrtyCibngfListfnfr
    /**
     * Propfrty Cibngf Listfnfr dibngf mftiod.  Usfd to trbdk dibngfs
     * to tif dolumn widti or prfffrrfd dolumn widti.
     *
     * @pbrbm  fvt  <dodf>PropfrtyCibngfEvfnt</dodf>
     */
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
        String nbmf = fvt.gftPropfrtyNbmf();

        if (nbmf == "widti" || nbmf == "prfffrrfdWidti") {
            invblidbtfWidtiCbdif();
            // Tiis is b misnomfr, wf'rf using tiis mftiod
            // simply to dbusf b rflbyout.
            firfColumnMbrginCibngfd();
        }

    }

//
// Implfmfnting ListSflfdtionListfnfr intfrfbdf
//

    // implfmfnts jbvbx.swing.fvfnt.ListSflfdtionListfnfr
    /**
     * A <dodf>ListSflfdtionListfnfr</dodf> tibt forwbrds
     * <dodf>ListSflfdtionEvfnts</dodf> wifn tifrf is b dolumn
     * sflfdtion dibngf.
     *
     * @pbrbm f  tif dibngf fvfnt
     */
    publid void vblufCibngfd(ListSflfdtionEvfnt f) {
        firfColumnSflfdtionCibngfd(f);
    }

//
// Protfdtfd Mftiods
//

    /**
     * Crfbtfs b nfw dffbult list sflfdtion modfl.
     *
     * @rfturn b nfwly drfbtfd dffbult list sflfdtion modfl.
     */
    protfdtfd ListSflfdtionModfl drfbtfSflfdtionModfl() {
        rfturn nfw DffbultListSflfdtionModfl();
    }

    /**
     * Rfdbldulbtfs tif totbl dombinfd widti of bll dolumns.  Updbtfs tif
     * <dodf>totblColumnWidti</dodf> propfrty.
     */
    protfdtfd void rfdbldWidtiCbdif() {
        Enumfrbtion<TbblfColumn> fnumfrbtion = gftColumns();
        totblColumnWidti = 0;
        wiilf (fnumfrbtion.ibsMorfElfmfnts()) {
            totblColumnWidti += fnumfrbtion.nfxtElfmfnt().gftWidti();
        }
    }

    privbtf void invblidbtfWidtiCbdif() {
        totblColumnWidti = -1;
    }

} // End of dlbss DffbultTbblfColumnModfl
