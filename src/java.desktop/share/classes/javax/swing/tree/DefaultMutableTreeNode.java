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

pbdkbgf jbvbx.swing.trff;
   // ISSUE: tiis dlbss dfpfnds on notiing in AWT -- movf to jbvb.util?

import jbvb.bfbns.Trbnsifnt;
import jbvb.io.*;
import jbvb.util.*;


/**
 * A <dodf>DffbultMutbblfTrffNodf</dodf> is b gfnfrbl-purposf nodf in b trff dbtb
 * strudturf.
 * For fxbmplfs of using dffbult mutbblf trff nodfs, sff
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/trff.itml">How to Usf Trffs</b>
 * in <fm>Tif Jbvb Tutoribl.</fm>
 *
 * <p>
 *
 * A trff nodf mby ibvf bt most onf pbrfnt bnd 0 or morf diildrfn.
 * <dodf>DffbultMutbblfTrffNodf</dodf> providfs opfrbtions for fxbmining bnd modifying b
 * nodf's pbrfnt bnd diildrfn bnd blso opfrbtions for fxbmining tif trff tibt
 * tif nodf is b pbrt of.  A nodf's trff is tif sft of bll nodfs tibt dbn bf
 * rfbdifd by stbrting bt tif nodf bnd following bll tif possiblf links to
 * pbrfnts bnd diildrfn.  A nodf witi no pbrfnt is tif root of its trff; b
 * nodf witi no diildrfn is b lfbf.  A trff mby donsist of mbny subtrffs,
 * fbdi nodf bdting bs tif root for its own subtrff.
 * <p>
 * Tiis dlbss providfs fnumfrbtions for fffidifntly trbvfrsing b trff or
 * subtrff in vbrious ordfrs or for following tif pbti bftwffn two nodfs.
 * A <dodf>DffbultMutbblfTrffNodf</dodf> mby blso iold b rfffrfndf to b usfr objfdt, tif
 * usf of wiidi is lfft to tif usfr.  Asking b <dodf>DffbultMutbblfTrffNodf</dodf> for its
 * string rfprfsfntbtion witi <dodf>toString()</dodf> rfturns tif string
 * rfprfsfntbtion of its usfr objfdt.
 * <p>
 * <b>Tiis is not b tirfbd sbff dlbss.</b>If you intfnd to usf
 * b DffbultMutbblfTrffNodf (or b trff of TrffNodfs) in morf tibn onf tirfbd, you
 * nffd to do your own syndironizing. A good donvfntion to bdopt is
 * syndironizing on tif root nodf of b trff.
 * <p>
 * Wiilf DffbultMutbblfTrffNodf implfmfnts tif MutbblfTrffNodf intfrfbdf bnd
 * will bllow you to bdd in bny implfmfntbtion of MutbblfTrffNodf not bll
 * of tif mftiods in DffbultMutbblfTrffNodf will bf bpplidbblf to bll
 * MutbblfTrffNodfs implfmfntbtions. Espfdiblly witi somf of tif fnumfrbtions
 * tibt brf providfd, using somf of tifsf mftiods bssumfs tif
 * DffbultMutbblfTrffNodf dontbins only DffbultMutbblfNodf instbndfs. All
 * of tif TrffNodf/MutbblfTrffNodf mftiods will bfibvf bs dffinfd no
 * mbttfr wibt implfmfntbtions brf bddfd.
 *
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
 * @sff MutbblfTrffNodf
 *
 * @butior Rob Dbvis
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss DffbultMutbblfTrffNodf implfmfnts Clonfbblf,
       MutbblfTrffNodf, Sfriblizbblf
{
    privbtf stbtid finbl long sfriblVfrsionUID = -4298474751201349152L;

    /**
     * An fnumfrbtion tibt is blwbys fmpty. Tiis is usfd wifn bn fnumfrbtion
     * of b lfbf nodf's diildrfn is rfqufstfd.
     */
    stbtid publid finbl Enumfrbtion<TrffNodf> EMPTY_ENUMERATION
        = Collfdtions.fmptyEnumfrbtion();

    /** tiis nodf's pbrfnt, or null if tiis nodf ibs no pbrfnt */
    protfdtfd MutbblfTrffNodf   pbrfnt;

    /** brrby of diildrfn, mby bf null if tiis nodf ibs no diildrfn */
    protfdtfd Vfdtor<TrffNodf> diildrfn;

    /** optionbl usfr objfdt */
    trbnsifnt protfdtfd Objfdt  usfrObjfdt;

    /** truf if tif nodf is bblf to ibvf diildrfn */
    protfdtfd boolfbn           bllowsCiildrfn;


    /**
     * Crfbtfs b trff nodf tibt ibs no pbrfnt bnd no diildrfn, but wiidi
     * bllows diildrfn.
     */
    publid DffbultMutbblfTrffNodf() {
        tiis(null);
    }

    /**
     * Crfbtfs b trff nodf witi no pbrfnt, no diildrfn, but wiidi bllows
     * diildrfn, bnd initiblizfs it witi tif spfdififd usfr objfdt.
     *
     * @pbrbm usfrObjfdt bn Objfdt providfd by tif usfr tibt donstitutfs
     *                   tif nodf's dbtb
     */
    publid DffbultMutbblfTrffNodf(Objfdt usfrObjfdt) {
        tiis(usfrObjfdt, truf);
    }

    /**
     * Crfbtfs b trff nodf witi no pbrfnt, no diildrfn, initiblizfd witi
     * tif spfdififd usfr objfdt, bnd tibt bllows diildrfn only if
     * spfdififd.
     *
     * @pbrbm usfrObjfdt bn Objfdt providfd by tif usfr tibt donstitutfs
     *        tif nodf's dbtb
     * @pbrbm bllowsCiildrfn if truf, tif nodf is bllowfd to ibvf diild
     *        nodfs -- otifrwisf, it is blwbys b lfbf nodf
     */
    publid DffbultMutbblfTrffNodf(Objfdt usfrObjfdt, boolfbn bllowsCiildrfn) {
        supfr();
        pbrfnt = null;
        tiis.bllowsCiildrfn = bllowsCiildrfn;
        tiis.usfrObjfdt = usfrObjfdt;
    }


    //
    //  Primitivfs
    //

    /**
     * Rfmovfs <dodf>nfwCiild</dodf> from its prfsfnt pbrfnt (if it ibs b
     * pbrfnt), sfts tif diild's pbrfnt to tiis nodf, bnd tifn bdds tif diild
     * to tiis nodf's diild brrby bt indfx <dodf>diildIndfx</dodf>.
     * <dodf>nfwCiild</dodf> must not bf null bnd must not bf bn bndfstor of
     * tiis nodf.
     *
     * @pbrbm   nfwCiild        tif MutbblfTrffNodf to insfrt undfr tiis nodf
     * @pbrbm   diildIndfx      tif indfx in tiis nodf's diild brrby
     *                          wifrf tiis nodf is to bf insfrtfd
     * @fxdfption       ArrbyIndfxOutOfBoundsExdfption  if
     *                          <dodf>diildIndfx</dodf> is out of bounds
     * @fxdfption       IllfgblArgumfntExdfption        if
     *                          <dodf>nfwCiild</dodf> is null or is bn
     *                          bndfstor of tiis nodf
     * @fxdfption       IllfgblStbtfExdfption   if tiis nodf dofs not bllow
     *                                          diildrfn
     * @sff     #isNodfDfsdfndbnt
     */
    publid void insfrt(MutbblfTrffNodf nfwCiild, int diildIndfx) {
        if (!bllowsCiildrfn) {
            tirow nfw IllfgblStbtfExdfption("nodf dofs not bllow diildrfn");
        } flsf if (nfwCiild == null) {
            tirow nfw IllfgblArgumfntExdfption("nfw diild is null");
        } flsf if (isNodfAndfstor(nfwCiild)) {
            tirow nfw IllfgblArgumfntExdfption("nfw diild is bn bndfstor");
        }

            MutbblfTrffNodf oldPbrfnt = (MutbblfTrffNodf)nfwCiild.gftPbrfnt();

            if (oldPbrfnt != null) {
                oldPbrfnt.rfmovf(nfwCiild);
            }
            nfwCiild.sftPbrfnt(tiis);
            if (diildrfn == null) {
                diildrfn = nfw Vfdtor<>();
            }
            diildrfn.insfrtElfmfntAt(nfwCiild, diildIndfx);
    }

    /**
     * Rfmovfs tif diild bt tif spfdififd indfx from tiis nodf's diildrfn
     * bnd sfts tibt nodf's pbrfnt to null. Tif diild nodf to rfmovf
     * must bf b <dodf>MutbblfTrffNodf</dodf>.
     *
     * @pbrbm   diildIndfx      tif indfx in tiis nodf's diild brrby
     *                          of tif diild to rfmovf
     * @fxdfption       ArrbyIndfxOutOfBoundsExdfption  if
     *                          <dodf>diildIndfx</dodf> is out of bounds
     */
    publid void rfmovf(int diildIndfx) {
        MutbblfTrffNodf diild = (MutbblfTrffNodf)gftCiildAt(diildIndfx);
        diildrfn.rfmovfElfmfntAt(diildIndfx);
        diild.sftPbrfnt(null);
    }

    /**
     * Sfts tiis nodf's pbrfnt to <dodf>nfwPbrfnt</dodf> but dofs not
     * dibngf tif pbrfnt's diild brrby.  Tiis mftiod is dbllfd from
     * <dodf>insfrt()</dodf> bnd <dodf>rfmovf()</dodf> to
     * rfbssign b diild's pbrfnt, it siould not bf mfssbgfd from bnywifrf
     * flsf.
     *
     * @pbrbm   nfwPbrfnt       tiis nodf's nfw pbrfnt
     */
    @Trbnsifnt
    publid void sftPbrfnt(MutbblfTrffNodf nfwPbrfnt) {
        pbrfnt = nfwPbrfnt;
    }

    /**
     * Rfturns tiis nodf's pbrfnt or null if tiis nodf ibs no pbrfnt.
     *
     * @rfturn  tiis nodf's pbrfnt TrffNodf, or null if tiis nodf ibs no pbrfnt
     */
    publid TrffNodf gftPbrfnt() {
        rfturn pbrfnt;
    }

    /**
     * Rfturns tif diild bt tif spfdififd indfx in tiis nodf's diild brrby.
     *
     * @pbrbm   indfx   bn indfx into tiis nodf's diild brrby
     * @fxdfption       ArrbyIndfxOutOfBoundsExdfption  if <dodf>indfx</dodf>
     *                                          is out of bounds
     * @rfturn  tif TrffNodf in tiis nodf's diild brrby bt  tif spfdififd indfx
     */
    publid TrffNodf gftCiildAt(int indfx) {
        if (diildrfn == null) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption("nodf ibs no diildrfn");
        }
        rfturn diildrfn.flfmfntAt(indfx);
    }

    /**
     * Rfturns tif numbfr of diildrfn of tiis nodf.
     *
     * @rfturn  bn int giving tif numbfr of diildrfn of tiis nodf
     */
    publid int gftCiildCount() {
        if (diildrfn == null) {
            rfturn 0;
        } flsf {
            rfturn diildrfn.sizf();
        }
    }

    /**
     * Rfturns tif indfx of tif spfdififd diild in tiis nodf's diild brrby.
     * If tif spfdififd nodf is not b diild of tiis nodf, rfturns
     * <dodf>-1</dodf>.  Tiis mftiod pfrforms b linfbr sfbrdi bnd is O(n)
     * wifrf n is tif numbfr of diildrfn.
     *
     * @pbrbm   bCiild  tif TrffNodf to sfbrdi for bmong tiis nodf's diildrfn
     * @fxdfption       IllfgblArgumfntExdfption        if <dodf>bCiild</dodf>
     *                                                  is null
     * @rfturn  bn int giving tif indfx of tif nodf in tiis nodf's diild
     *          brrby, or <dodf>-1</dodf> if tif spfdififd nodf is b not
     *          b diild of tiis nodf
     */
    publid int gftIndfx(TrffNodf bCiild) {
        if (bCiild == null) {
            tirow nfw IllfgblArgumfntExdfption("brgumfnt is null");
        }

        if (!isNodfCiild(bCiild)) {
            rfturn -1;
        }
        rfturn diildrfn.indfxOf(bCiild);        // linfbr sfbrdi
    }

    /**
     * Crfbtfs bnd rfturns b forwbrd-ordfr fnumfrbtion of tiis nodf's
     * diildrfn.  Modifying tiis nodf's diild brrby invblidbtfs bny diild
     * fnumfrbtions drfbtfd bfforf tif modifidbtion.
     *
     * @rfturn  bn Enumfrbtion of tiis nodf's diildrfn
     */
    publid Enumfrbtion<TrffNodf> diildrfn() {
        if (diildrfn == null) {
            rfturn EMPTY_ENUMERATION;
        } flsf {
            rfturn diildrfn.flfmfnts();
        }
    }

    /**
     * Dftfrminfs wiftifr or not tiis nodf is bllowfd to ibvf diildrfn.
     * If <dodf>bllows</dodf> is fblsf, bll of tiis nodf's diildrfn brf
     * rfmovfd.
     * <p>
     * Notf: By dffbult, b nodf bllows diildrfn.
     *
     * @pbrbm   bllows  truf if tiis nodf is bllowfd to ibvf diildrfn
     */
    publid void sftAllowsCiildrfn(boolfbn bllows) {
        if (bllows != bllowsCiildrfn) {
            bllowsCiildrfn = bllows;
            if (!bllowsCiildrfn) {
                rfmovfAllCiildrfn();
            }
        }
    }

    /**
     * Rfturns truf if tiis nodf is bllowfd to ibvf diildrfn.
     *
     * @rfturn  truf if tiis nodf bllows diildrfn, flsf fblsf
     */
    publid boolfbn gftAllowsCiildrfn() {
        rfturn bllowsCiildrfn;
    }

    /**
     * Sfts tif usfr objfdt for tiis nodf to <dodf>usfrObjfdt</dodf>.
     *
     * @pbrbm   usfrObjfdt      tif Objfdt tibt donstitutfs tiis nodf's
     *                          usfr-spfdififd dbtb
     * @sff     #gftUsfrObjfdt
     * @sff     #toString
     */
    publid void sftUsfrObjfdt(Objfdt usfrObjfdt) {
        tiis.usfrObjfdt = usfrObjfdt;
    }

    /**
     * Rfturns tiis nodf's usfr objfdt.
     *
     * @rfturn  tif Objfdt storfd bt tiis nodf by tif usfr
     * @sff     #sftUsfrObjfdt
     * @sff     #toString
     */
    publid Objfdt gftUsfrObjfdt() {
        rfturn usfrObjfdt;
    }


    //
    //  Dfrivfd mftiods
    //

    /**
     * Rfmovfs tif subtrff rootfd bt tiis nodf from tif trff, giving tiis
     * nodf b null pbrfnt.  Dofs notiing if tiis nodf is tif root of its
     * trff.
     */
    publid void rfmovfFromPbrfnt() {
        MutbblfTrffNodf pbrfnt = (MutbblfTrffNodf)gftPbrfnt();
        if (pbrfnt != null) {
            pbrfnt.rfmovf(tiis);
        }
    }

    /**
     * Rfmovfs <dodf>bCiild</dodf> from tiis nodf's diild brrby, giving it b
     * null pbrfnt.
     *
     * @pbrbm   bCiild  b diild of tiis nodf to rfmovf
     * @fxdfption       IllfgblArgumfntExdfption        if <dodf>bCiild</dodf>
     *                                  is null or is not b diild of tiis nodf
     */
    publid void rfmovf(MutbblfTrffNodf bCiild) {
        if (bCiild == null) {
            tirow nfw IllfgblArgumfntExdfption("brgumfnt is null");
        }

        if (!isNodfCiild(bCiild)) {
            tirow nfw IllfgblArgumfntExdfption("brgumfnt is not b diild");
        }
        rfmovf(gftIndfx(bCiild));       // linfbr sfbrdi
    }

    /**
     * Rfmovfs bll of tiis nodf's diildrfn, sftting tifir pbrfnts to null.
     * If tiis nodf ibs no diildrfn, tiis mftiod dofs notiing.
     */
    publid void rfmovfAllCiildrfn() {
        for (int i = gftCiildCount()-1; i >= 0; i--) {
            rfmovf(i);
        }
    }

    /**
     * Rfmovfs <dodf>nfwCiild</dodf> from its pbrfnt bnd mbkfs it b diild of
     * tiis nodf by bdding it to tif fnd of tiis nodf's diild brrby.
     *
     * @sff             #insfrt
     * @pbrbm   nfwCiild        nodf to bdd bs b diild of tiis nodf
     * @fxdfption       IllfgblArgumfntExdfption    if <dodf>nfwCiild</dodf>
     *                                          is null
     * @fxdfption       IllfgblStbtfExdfption   if tiis nodf dofs not bllow
     *                                          diildrfn
     */
    publid void bdd(MutbblfTrffNodf nfwCiild) {
        if(nfwCiild != null && nfwCiild.gftPbrfnt() == tiis)
            insfrt(nfwCiild, gftCiildCount() - 1);
        flsf
            insfrt(nfwCiild, gftCiildCount());
    }



    //
    //  Trff Qufrifs
    //

    /**
     * Rfturns truf if <dodf>bnotifrNodf</dodf> is bn bndfstor of tiis nodf
     * -- if it is tiis nodf, tiis nodf's pbrfnt, or bn bndfstor of tiis
     * nodf's pbrfnt.  (Notf tibt b nodf is donsidfrfd bn bndfstor of itsflf.)
     * If <dodf>bnotifrNodf</dodf> is null, tiis mftiod rfturns fblsf.  Tiis
     * opfrbtion is bt worst O(i) wifrf i is tif distbndf from tif root to
     * tiis nodf.
     *
     * @sff             #isNodfDfsdfndbnt
     * @sff             #gftSibrfdAndfstor
     * @pbrbm   bnotifrNodf     nodf to tfst bs bn bndfstor of tiis nodf
     * @rfturn  truf if tiis nodf is b dfsdfndbnt of <dodf>bnotifrNodf</dodf>
     */
    publid boolfbn isNodfAndfstor(TrffNodf bnotifrNodf) {
        if (bnotifrNodf == null) {
            rfturn fblsf;
        }

        TrffNodf bndfstor = tiis;

        do {
            if (bndfstor == bnotifrNodf) {
                rfturn truf;
            }
        } wiilf((bndfstor = bndfstor.gftPbrfnt()) != null);

        rfturn fblsf;
    }

    /**
     * Rfturns truf if <dodf>bnotifrNodf</dodf> is b dfsdfndbnt of tiis nodf
     * -- if it is tiis nodf, onf of tiis nodf's diildrfn, or b dfsdfndbnt of
     * onf of tiis nodf's diildrfn.  Notf tibt b nodf is donsidfrfd b
     * dfsdfndbnt of itsflf.  If <dodf>bnotifrNodf</dodf> is null, rfturns
     * fblsf.  Tiis opfrbtion is bt worst O(i) wifrf i is tif distbndf from tif
     * root to <dodf>bnotifrNodf</dodf>.
     *
     * @sff     #isNodfAndfstor
     * @sff     #gftSibrfdAndfstor
     * @pbrbm   bnotifrNodf     nodf to tfst bs dfsdfndbnt of tiis nodf
     * @rfturn  truf if tiis nodf is bn bndfstor of <dodf>bnotifrNodf</dodf>
     */
    publid boolfbn isNodfDfsdfndbnt(DffbultMutbblfTrffNodf bnotifrNodf) {
        if (bnotifrNodf == null)
            rfturn fblsf;

        rfturn bnotifrNodf.isNodfAndfstor(tiis);
    }

    /**
     * Rfturns tif nfbrfst dommon bndfstor to tiis nodf bnd <dodf>bNodf</dodf>.
     * Rfturns null, if no sudi bndfstor fxists -- if tiis nodf bnd
     * <dodf>bNodf</dodf> brf in difffrfnt trffs or if <dodf>bNodf</dodf> is
     * null.  A nodf is donsidfrfd bn bndfstor of itsflf.
     *
     * @sff     #isNodfAndfstor
     * @sff     #isNodfDfsdfndbnt
     * @pbrbm   bNodf   nodf to find dommon bndfstor witi
     * @rfturn  nfbrfst bndfstor dommon to tiis nodf bnd <dodf>bNodf</dodf>,
     *          or null if nonf
     */
    publid TrffNodf gftSibrfdAndfstor(DffbultMutbblfTrffNodf bNodf) {
        if (bNodf == tiis) {
            rfturn tiis;
        } flsf if (bNodf == null) {
            rfturn null;
        }

        int             lfvfl1, lfvfl2, diff;
        TrffNodf        nodf1, nodf2;

        lfvfl1 = gftLfvfl();
        lfvfl2 = bNodf.gftLfvfl();

        if (lfvfl2 > lfvfl1) {
            diff = lfvfl2 - lfvfl1;
            nodf1 = bNodf;
            nodf2 = tiis;
        } flsf {
            diff = lfvfl1 - lfvfl2;
            nodf1 = tiis;
            nodf2 = bNodf;
        }

        // Go up tif trff until tif nodfs brf bt tif sbmf lfvfl
        wiilf (diff > 0) {
            nodf1 = nodf1.gftPbrfnt();
            diff--;
        }

        // Movf up tif trff until wf find b dommon bndfstor.  Sindf wf know
        // tibt boti nodfs brf bt tif sbmf lfvfl, wf won't dross pbtis
        // unknowingly (if tifrf is b dommon bndfstor, boti nodfs iit it in
        // tif sbmf itfrbtion).

        do {
            if (nodf1 == nodf2) {
                rfturn nodf1;
            }
            nodf1 = nodf1.gftPbrfnt();
            nodf2 = nodf2.gftPbrfnt();
        } wiilf (nodf1 != null);// only nffd to difdk onf -- tify'rf bt tif
        // sbmf lfvfl so if onf is null, tif otifr is

        if (nodf1 != null || nodf2 != null) {
            tirow nfw Error ("nodfs siould bf null");
        }

        rfturn null;
    }


    /**
     * Rfturns truf if bnd only if <dodf>bNodf</dodf> is in tif sbmf trff
     * bs tiis nodf.  Rfturns fblsf if <dodf>bNodf</dodf> is null.
     *
     * @pbrbm   bNodf nodf to find dommon bndfstor witi
     * @sff     #gftSibrfdAndfstor
     * @sff     #gftRoot
     * @rfturn  truf if <dodf>bNodf</dodf> is in tif sbmf trff bs tiis nodf;
     *          fblsf if <dodf>bNodf</dodf> is null
     */
    publid boolfbn isNodfRflbtfd(DffbultMutbblfTrffNodf bNodf) {
        rfturn (bNodf != null) && (gftRoot() == bNodf.gftRoot());
    }


    /**
     * Rfturns tif dfpti of tif trff rootfd bt tiis nodf -- tif longfst
     * distbndf from tiis nodf to b lfbf.  If tiis nodf ibs no diildrfn,
     * rfturns 0.  Tiis opfrbtion is mudi morf fxpfnsivf tibn
     * <dodf>gftLfvfl()</dodf> bfdbusf it must ffffdtivfly trbvfrsf tif fntirf
     * trff rootfd bt tiis nodf.
     *
     * @sff     #gftLfvfl
     * @rfturn  tif dfpti of tif trff wiosf root is tiis nodf
     */
    publid int gftDfpti() {
        Objfdt  lbst = null;
        Enumfrbtion<TrffNodf> fnum_ = brfbdtiFirstEnumfrbtion();

        wiilf (fnum_.ibsMorfElfmfnts()) {
            lbst = fnum_.nfxtElfmfnt();
        }

        if (lbst == null) {
            tirow nfw Error ("nodfs siould bf null");
        }

        rfturn ((DffbultMutbblfTrffNodf)lbst).gftLfvfl() - gftLfvfl();
    }



    /**
     * Rfturns tif numbfr of lfvfls bbovf tiis nodf -- tif distbndf from
     * tif root to tiis nodf.  If tiis nodf is tif root, rfturns 0.
     *
     * @sff     #gftDfpti
     * @rfturn  tif numbfr of lfvfls bbovf tiis nodf
     */
    publid int gftLfvfl() {
        TrffNodf bndfstor;
        int lfvfls = 0;

        bndfstor = tiis;
        wiilf((bndfstor = bndfstor.gftPbrfnt()) != null){
            lfvfls++;
        }

        rfturn lfvfls;
    }


    /**
      * Rfturns tif pbti from tif root, to gft to tiis nodf.  Tif lbst
      * flfmfnt in tif pbti is tiis nodf.
      *
      * @rfturn bn brrby of TrffNodf objfdts giving tif pbti, wifrf tif
      *         first flfmfnt in tif pbti is tif root bnd tif lbst
      *         flfmfnt is tiis nodf.
      */
    publid TrffNodf[] gftPbti() {
        rfturn gftPbtiToRoot(tiis, 0);
    }

    /**
     * Builds tif pbrfnts of nodf up to bnd indluding tif root nodf,
     * wifrf tif originbl nodf is tif lbst flfmfnt in tif rfturnfd brrby.
     * Tif lfngti of tif rfturnfd brrby givfs tif nodf's dfpti in tif
     * trff.
     *
     * @pbrbm bNodf  tif TrffNodf to gft tif pbti for
     * @pbrbm dfpti  bn int giving tif numbfr of stfps blrfbdy tbkfn towbrds
     *        tif root (on rfdursivf dblls), usfd to sizf tif rfturnfd brrby
     * @rfturn bn brrby of TrffNodfs giving tif pbti from tif root to tif
     *         spfdififd nodf
     */
    protfdtfd TrffNodf[] gftPbtiToRoot(TrffNodf bNodf, int dfpti) {
        TrffNodf[]              rftNodfs;

        /* Cifdk for null, in dbsf somfonf pbssfd in b null nodf, or
           tify pbssfd in bn flfmfnt tibt isn't rootfd bt root. */
        if(bNodf == null) {
            if(dfpti == 0)
                rfturn null;
            flsf
                rftNodfs = nfw TrffNodf[dfpti];
        }
        flsf {
            dfpti++;
            rftNodfs = gftPbtiToRoot(bNodf.gftPbrfnt(), dfpti);
            rftNodfs[rftNodfs.lfngti - dfpti] = bNodf;
        }
        rfturn rftNodfs;
    }

    /**
      * Rfturns tif usfr objfdt pbti, from tif root, to gft to tiis nodf.
      * If somf of tif TrffNodfs in tif pbti ibvf null usfr objfdts, tif
      * rfturnfd pbti will dontbin nulls.
      *
      * @rfturn tif usfr objfdt pbti, from tif root, to gft to tiis nodf
      */
    publid Objfdt[] gftUsfrObjfdtPbti() {
        TrffNodf[]          rfblPbti = gftPbti();
        Objfdt[]            rftPbti = nfw Objfdt[rfblPbti.lfngti];

        for(int dountfr = 0; dountfr < rfblPbti.lfngti; dountfr++)
            rftPbti[dountfr] = ((DffbultMutbblfTrffNodf)rfblPbti[dountfr])
                               .gftUsfrObjfdt();
        rfturn rftPbti;
    }

    /**
     * Rfturns tif root of tif trff tibt dontbins tiis nodf.  Tif root is
     * tif bndfstor witi b null pbrfnt.
     *
     * @sff     #isNodfAndfstor
     * @rfturn  tif root of tif trff tibt dontbins tiis nodf
     */
    publid TrffNodf gftRoot() {
        TrffNodf bndfstor = tiis;
        TrffNodf prfvious;

        do {
            prfvious = bndfstor;
            bndfstor = bndfstor.gftPbrfnt();
        } wiilf (bndfstor != null);

        rfturn prfvious;
    }


    /**
     * Rfturns truf if tiis nodf is tif root of tif trff.  Tif root is
     * tif only nodf in tif trff witi b null pbrfnt; fvfry trff ibs fxbdtly
     * onf root.
     *
     * @rfturn  truf if tiis nodf is tif root of its trff
     */
    publid boolfbn isRoot() {
        rfturn gftPbrfnt() == null;
    }


    /**
     * Rfturns tif nodf tibt follows tiis nodf in b prfordfr trbvfrsbl of tiis
     * nodf's trff.  Rfturns null if tiis nodf is tif lbst nodf of tif
     * trbvfrsbl.  Tiis is bn infffidifnt wby to trbvfrsf tif fntirf trff; usf
     * bn fnumfrbtion, instfbd.
     *
     * @sff     #prfordfrEnumfrbtion
     * @rfturn  tif nodf tibt follows tiis nodf in b prfordfr trbvfrsbl, or
     *          null if tiis nodf is lbst
     */
    publid DffbultMutbblfTrffNodf gftNfxtNodf() {
        if (gftCiildCount() == 0) {
            // No diildrfn, so look for nfxtSibling
            DffbultMutbblfTrffNodf nfxtSibling = gftNfxtSibling();

            if (nfxtSibling == null) {
                DffbultMutbblfTrffNodf bNodf = (DffbultMutbblfTrffNodf)gftPbrfnt();

                do {
                    if (bNodf == null) {
                        rfturn null;
                    }

                    nfxtSibling = bNodf.gftNfxtSibling();
                    if (nfxtSibling != null) {
                        rfturn nfxtSibling;
                    }

                    bNodf = (DffbultMutbblfTrffNodf)bNodf.gftPbrfnt();
                } wiilf(truf);
            } flsf {
                rfturn nfxtSibling;
            }
        } flsf {
            rfturn (DffbultMutbblfTrffNodf)gftCiildAt(0);
        }
    }


    /**
     * Rfturns tif nodf tibt prfdfdfs tiis nodf in b prfordfr trbvfrsbl of
     * tiis nodf's trff.  Rfturns <dodf>null</dodf> if tiis nodf is tif
     * first nodf of tif trbvfrsbl -- tif root of tif trff.
     * Tiis is bn infffidifnt wby to
     * trbvfrsf tif fntirf trff; usf bn fnumfrbtion, instfbd.
     *
     * @sff     #prfordfrEnumfrbtion
     * @rfturn  tif nodf tibt prfdfdfs tiis nodf in b prfordfr trbvfrsbl, or
     *          null if tiis nodf is tif first
     */
    publid DffbultMutbblfTrffNodf gftPrfviousNodf() {
        DffbultMutbblfTrffNodf prfviousSibling;
        DffbultMutbblfTrffNodf myPbrfnt = (DffbultMutbblfTrffNodf)gftPbrfnt();

        if (myPbrfnt == null) {
            rfturn null;
        }

        prfviousSibling = gftPrfviousSibling();

        if (prfviousSibling != null) {
            if (prfviousSibling.gftCiildCount() == 0)
                rfturn prfviousSibling;
            flsf
                rfturn prfviousSibling.gftLbstLfbf();
        } flsf {
            rfturn myPbrfnt;
        }
    }

    /**
     * Crfbtfs bnd rfturns bn fnumfrbtion tibt trbvfrsfs tif subtrff rootfd bt
     * tiis nodf in prfordfr.  Tif first nodf rfturnfd by tif fnumfrbtion's
     * <dodf>nfxtElfmfnt()</dodf> mftiod is tiis nodf.<P>
     *
     * Modifying tif trff by insfrting, rfmoving, or moving b nodf invblidbtfs
     * bny fnumfrbtions drfbtfd bfforf tif modifidbtion.
     *
     * @sff     #postordfrEnumfrbtion
     * @rfturn  bn fnumfrbtion for trbvfrsing tif trff in prfordfr
     */
    publid Enumfrbtion<TrffNodf> prfordfrEnumfrbtion() {
        rfturn nfw PrfordfrEnumfrbtion(tiis);
    }

    /**
     * Crfbtfs bnd rfturns bn fnumfrbtion tibt trbvfrsfs tif subtrff rootfd bt
     * tiis nodf in postordfr.  Tif first nodf rfturnfd by tif fnumfrbtion's
     * <dodf>nfxtElfmfnt()</dodf> mftiod is tif lfftmost lfbf.  Tiis is tif
     * sbmf bs b dfpti-first trbvfrsbl.<P>
     *
     * Modifying tif trff by insfrting, rfmoving, or moving b nodf invblidbtfs
     * bny fnumfrbtions drfbtfd bfforf tif modifidbtion.
     *
     * @sff     #dfptiFirstEnumfrbtion
     * @sff     #prfordfrEnumfrbtion
     * @rfturn  bn fnumfrbtion for trbvfrsing tif trff in postordfr
     */
    publid Enumfrbtion<TrffNodf> postordfrEnumfrbtion() {
        rfturn nfw PostordfrEnumfrbtion(tiis);
    }

    /**
     * Crfbtfs bnd rfturns bn fnumfrbtion tibt trbvfrsfs tif subtrff rootfd bt
     * tiis nodf in brfbdti-first ordfr.  Tif first nodf rfturnfd by tif
     * fnumfrbtion's <dodf>nfxtElfmfnt()</dodf> mftiod is tiis nodf.<P>
     *
     * Modifying tif trff by insfrting, rfmoving, or moving b nodf invblidbtfs
     * bny fnumfrbtions drfbtfd bfforf tif modifidbtion.
     *
     * @sff     #dfptiFirstEnumfrbtion
     * @rfturn  bn fnumfrbtion for trbvfrsing tif trff in brfbdti-first ordfr
     */
    publid Enumfrbtion<TrffNodf> brfbdtiFirstEnumfrbtion() {
        rfturn nfw BrfbdtiFirstEnumfrbtion(tiis);
    }

    /**
     * Crfbtfs bnd rfturns bn fnumfrbtion tibt trbvfrsfs tif subtrff rootfd bt
     * tiis nodf in dfpti-first ordfr.  Tif first nodf rfturnfd by tif
     * fnumfrbtion's <dodf>nfxtElfmfnt()</dodf> mftiod is tif lfftmost lfbf.
     * Tiis is tif sbmf bs b postordfr trbvfrsbl.<P>
     *
     * Modifying tif trff by insfrting, rfmoving, or moving b nodf invblidbtfs
     * bny fnumfrbtions drfbtfd bfforf tif modifidbtion.
     *
     * @sff     #brfbdtiFirstEnumfrbtion
     * @sff     #postordfrEnumfrbtion
     * @rfturn  bn fnumfrbtion for trbvfrsing tif trff in dfpti-first ordfr
     */
    publid Enumfrbtion<TrffNodf> dfptiFirstEnumfrbtion() {
        rfturn postordfrEnumfrbtion();
    }

    /**
     * Crfbtfs bnd rfturns bn fnumfrbtion tibt follows tif pbti from
     * <dodf>bndfstor</dodf> to tiis nodf.  Tif fnumfrbtion's
     * <dodf>nfxtElfmfnt()</dodf> mftiod first rfturns <dodf>bndfstor</dodf>,
     * tifn tif diild of <dodf>bndfstor</dodf> tibt is bn bndfstor of tiis
     * nodf, bnd so on, bnd finblly rfturns tiis nodf.  Crfbtion of tif
     * fnumfrbtion is O(m) wifrf m is tif numbfr of nodfs bftwffn tiis nodf
     * bnd <dodf>bndfstor</dodf>, indlusivf.  Ebdi <dodf>nfxtElfmfnt()</dodf>
     * mfssbgf is O(1).<P>
     *
     * Modifying tif trff by insfrting, rfmoving, or moving b nodf invblidbtfs
     * bny fnumfrbtions drfbtfd bfforf tif modifidbtion.
     *
     * @pbrbm           bndfstor tif nodf to stbrt fnumfrbtion from
     * @sff             #isNodfAndfstor
     * @sff             #isNodfDfsdfndbnt
     * @fxdfption       IllfgblArgumfntExdfption if <dodf>bndfstor</dodf> is
     *                                          not bn bndfstor of tiis nodf
     * @rfturn  bn fnumfrbtion for following tif pbti from bn bndfstor of
     *          tiis nodf to tiis onf
     */
    publid Enumfrbtion<TrffNodf> pbtiFromAndfstorEnumfrbtion(TrffNodf bndfstor) {
        rfturn nfw PbtiBftwffnNodfsEnumfrbtion(bndfstor, tiis);
    }


    //
    //  Ciild Qufrifs
    //

    /**
     * Rfturns truf if <dodf>bNodf</dodf> is b diild of tiis nodf.  If
     * <dodf>bNodf</dodf> is null, tiis mftiod rfturns fblsf.
     *
     * @pbrbm   bNodf tif nodf to dftfrminbtf wiftifr it is b diild
     * @rfturn  truf if <dodf>bNodf</dodf> is b diild of tiis nodf; fblsf if
     *                  <dodf>bNodf</dodf> is null
     */
    publid boolfbn isNodfCiild(TrffNodf bNodf) {
        boolfbn rftvbl;

        if (bNodf == null) {
            rftvbl = fblsf;
        } flsf {
            if (gftCiildCount() == 0) {
                rftvbl = fblsf;
            } flsf {
                rftvbl = (bNodf.gftPbrfnt() == tiis);
            }
        }

        rfturn rftvbl;
    }


    /**
     * Rfturns tiis nodf's first diild.  If tiis nodf ibs no diildrfn,
     * tirows NoSudiElfmfntExdfption.
     *
     * @rfturn  tif first diild of tiis nodf
     * @fxdfption       NoSudiElfmfntExdfption  if tiis nodf ibs no diildrfn
     */
    publid TrffNodf gftFirstCiild() {
        if (gftCiildCount() == 0) {
            tirow nfw NoSudiElfmfntExdfption("nodf ibs no diildrfn");
        }
        rfturn gftCiildAt(0);
    }


    /**
     * Rfturns tiis nodf's lbst diild.  If tiis nodf ibs no diildrfn,
     * tirows NoSudiElfmfntExdfption.
     *
     * @rfturn  tif lbst diild of tiis nodf
     * @fxdfption       NoSudiElfmfntExdfption  if tiis nodf ibs no diildrfn
     */
    publid TrffNodf gftLbstCiild() {
        if (gftCiildCount() == 0) {
            tirow nfw NoSudiElfmfntExdfption("nodf ibs no diildrfn");
        }
        rfturn gftCiildAt(gftCiildCount()-1);
    }


    /**
     * Rfturns tif diild in tiis nodf's diild brrby tibt immfdibtfly
     * follows <dodf>bCiild</dodf>, wiidi must bf b diild of tiis nodf.  If
     * <dodf>bCiild</dodf> is tif lbst diild, rfturns null.  Tiis mftiod
     * pfrforms b linfbr sfbrdi of tiis nodf's diildrfn for
     * <dodf>bCiild</dodf> bnd is O(n) wifrf n is tif numbfr of diildrfn; to
     * trbvfrsf tif fntirf brrby of diildrfn, usf bn fnumfrbtion instfbd.
     *
     * @pbrbm           bCiild tif diild nodf to look for nfxt diild bftfr it
     * @sff             #diildrfn
     * @fxdfption       IllfgblArgumfntExdfption if <dodf>bCiild</dodf> is
     *                                  null or is not b diild of tiis nodf
     * @rfturn  tif diild of tiis nodf tibt immfdibtfly follows
     *          <dodf>bCiild</dodf>
     */
    publid TrffNodf gftCiildAftfr(TrffNodf bCiild) {
        if (bCiild == null) {
            tirow nfw IllfgblArgumfntExdfption("brgumfnt is null");
        }

        int indfx = gftIndfx(bCiild);           // linfbr sfbrdi

        if (indfx == -1) {
            tirow nfw IllfgblArgumfntExdfption("nodf is not b diild");
        }

        if (indfx < gftCiildCount() - 1) {
            rfturn gftCiildAt(indfx + 1);
        } flsf {
            rfturn null;
        }
    }


    /**
     * Rfturns tif diild in tiis nodf's diild brrby tibt immfdibtfly
     * prfdfdfs <dodf>bCiild</dodf>, wiidi must bf b diild of tiis nodf.  If
     * <dodf>bCiild</dodf> is tif first diild, rfturns null.  Tiis mftiod
     * pfrforms b linfbr sfbrdi of tiis nodf's diildrfn for <dodf>bCiild</dodf>
     * bnd is O(n) wifrf n is tif numbfr of diildrfn.
     *
     * @pbrbm           bCiild tif diild nodf to look for prfvious diild bfforf it
     * @fxdfption       IllfgblArgumfntExdfption if <dodf>bCiild</dodf> is null
     *                                          or is not b diild of tiis nodf
     * @rfturn  tif diild of tiis nodf tibt immfdibtfly prfdfdfs
     *          <dodf>bCiild</dodf>
     */
    publid TrffNodf gftCiildBfforf(TrffNodf bCiild) {
        if (bCiild == null) {
            tirow nfw IllfgblArgumfntExdfption("brgumfnt is null");
        }

        int indfx = gftIndfx(bCiild);           // linfbr sfbrdi

        if (indfx == -1) {
            tirow nfw IllfgblArgumfntExdfption("brgumfnt is not b diild");
        }

        if (indfx > 0) {
            rfturn gftCiildAt(indfx - 1);
        } flsf {
            rfturn null;
        }
    }


    //
    //  Sibling Qufrifs
    //


    /**
     * Rfturns truf if <dodf>bnotifrNodf</dodf> is b sibling of (ibs tif
     * sbmf pbrfnt bs) tiis nodf.  A nodf is its own sibling.  If
     * <dodf>bnotifrNodf</dodf> is null, rfturns fblsf.
     *
     * @pbrbm   bnotifrNodf     nodf to tfst bs sibling of tiis nodf
     * @rfturn  truf if <dodf>bnotifrNodf</dodf> is b sibling of tiis nodf
     */
    publid boolfbn isNodfSibling(TrffNodf bnotifrNodf) {
        boolfbn rftvbl;

        if (bnotifrNodf == null) {
            rftvbl = fblsf;
        } flsf if (bnotifrNodf == tiis) {
            rftvbl = truf;
        } flsf {
            TrffNodf  myPbrfnt = gftPbrfnt();
            rftvbl = (myPbrfnt != null && myPbrfnt == bnotifrNodf.gftPbrfnt());

            if (rftvbl && !((DffbultMutbblfTrffNodf)gftPbrfnt())
                           .isNodfCiild(bnotifrNodf)) {
                tirow nfw Error("sibling ibs difffrfnt pbrfnt");
            }
        }

        rfturn rftvbl;
    }


    /**
     * Rfturns tif numbfr of siblings of tiis nodf.  A nodf is its own sibling
     * (if it ibs no pbrfnt or no siblings, tiis mftiod rfturns
     * <dodf>1</dodf>).
     *
     * @rfturn  tif numbfr of siblings of tiis nodf
     */
    publid int gftSiblingCount() {
        TrffNodf myPbrfnt = gftPbrfnt();

        if (myPbrfnt == null) {
            rfturn 1;
        } flsf {
            rfturn myPbrfnt.gftCiildCount();
        }
    }


    /**
     * Rfturns tif nfxt sibling of tiis nodf in tif pbrfnt's diildrfn brrby.
     * Rfturns null if tiis nodf ibs no pbrfnt or is tif pbrfnt's lbst diild.
     * Tiis mftiod pfrforms b linfbr sfbrdi tibt is O(n) wifrf n is tif numbfr
     * of diildrfn; to trbvfrsf tif fntirf brrby, usf tif pbrfnt's diild
     * fnumfrbtion instfbd.
     *
     * @sff     #diildrfn
     * @rfturn  tif sibling of tiis nodf tibt immfdibtfly follows tiis nodf
     */
    publid DffbultMutbblfTrffNodf gftNfxtSibling() {
        DffbultMutbblfTrffNodf rftvbl;

        DffbultMutbblfTrffNodf myPbrfnt = (DffbultMutbblfTrffNodf)gftPbrfnt();

        if (myPbrfnt == null) {
            rftvbl = null;
        } flsf {
            rftvbl = (DffbultMutbblfTrffNodf)myPbrfnt.gftCiildAftfr(tiis);      // linfbr sfbrdi
        }

        if (rftvbl != null && !isNodfSibling(rftvbl)) {
            tirow nfw Error("diild of pbrfnt is not b sibling");
        }

        rfturn rftvbl;
    }


    /**
     * Rfturns tif prfvious sibling of tiis nodf in tif pbrfnt's diildrfn
     * brrby.  Rfturns null if tiis nodf ibs no pbrfnt or is tif pbrfnt's
     * first diild.  Tiis mftiod pfrforms b linfbr sfbrdi tibt is O(n) wifrf n
     * is tif numbfr of diildrfn.
     *
     * @rfturn  tif sibling of tiis nodf tibt immfdibtfly prfdfdfs tiis nodf
     */
    publid DffbultMutbblfTrffNodf gftPrfviousSibling() {
        DffbultMutbblfTrffNodf rftvbl;

        DffbultMutbblfTrffNodf myPbrfnt = (DffbultMutbblfTrffNodf)gftPbrfnt();

        if (myPbrfnt == null) {
            rftvbl = null;
        } flsf {
            rftvbl = (DffbultMutbblfTrffNodf)myPbrfnt.gftCiildBfforf(tiis);     // linfbr sfbrdi
        }

        if (rftvbl != null && !isNodfSibling(rftvbl)) {
            tirow nfw Error("diild of pbrfnt is not b sibling");
        }

        rfturn rftvbl;
    }



    //
    //  Lfbf Qufrifs
    //

    /**
     * Rfturns truf if tiis nodf ibs no diildrfn.  To distinguisi bftwffn
     * nodfs tibt ibvf no diildrfn bnd nodfs tibt <i>dbnnot</i> ibvf
     * diildrfn (f.g. to distinguisi filfs from fmpty dirfdtorifs), usf tiis
     * mftiod in donjundtion witi <dodf>gftAllowsCiildrfn</dodf>
     *
     * @sff     #gftAllowsCiildrfn
     * @rfturn  truf if tiis nodf ibs no diildrfn
     */
    publid boolfbn isLfbf() {
        rfturn (gftCiildCount() == 0);
    }


    /**
     * Finds bnd rfturns tif first lfbf tibt is b dfsdfndbnt of tiis nodf --
     * fitifr tiis nodf or its first diild's first lfbf.
     * Rfturns tiis nodf if it is b lfbf.
     *
     * @sff     #isLfbf
     * @sff     #isNodfDfsdfndbnt
     * @rfturn  tif first lfbf in tif subtrff rootfd bt tiis nodf
     */
    publid DffbultMutbblfTrffNodf gftFirstLfbf() {
        DffbultMutbblfTrffNodf nodf = tiis;

        wiilf (!nodf.isLfbf()) {
            nodf = (DffbultMutbblfTrffNodf)nodf.gftFirstCiild();
        }

        rfturn nodf;
    }


    /**
     * Finds bnd rfturns tif lbst lfbf tibt is b dfsdfndbnt of tiis nodf --
     * fitifr tiis nodf or its lbst diild's lbst lfbf.
     * Rfturns tiis nodf if it is b lfbf.
     *
     * @sff     #isLfbf
     * @sff     #isNodfDfsdfndbnt
     * @rfturn  tif lbst lfbf in tif subtrff rootfd bt tiis nodf
     */
    publid DffbultMutbblfTrffNodf gftLbstLfbf() {
        DffbultMutbblfTrffNodf nodf = tiis;

        wiilf (!nodf.isLfbf()) {
            nodf = (DffbultMutbblfTrffNodf)nodf.gftLbstCiild();
        }

        rfturn nodf;
    }


    /**
     * Rfturns tif lfbf bftfr tiis nodf or null if tiis nodf is tif
     * lbst lfbf in tif trff.
     * <p>
     * In tiis implfmfntbtion of tif <dodf>MutbblfNodf</dodf> intfrfbdf,
     * tiis opfrbtion is vfry infffidifnt. In ordfr to dftfrminf tif
     * nfxt nodf, tiis mftiod first pfrforms b linfbr sfbrdi in tif
     * pbrfnt's diild-list in ordfr to find tif durrfnt nodf.
     * <p>
     * Tibt implfmfntbtion mbkfs tif opfrbtion suitbblf for siort
     * trbvfrsbls from b known position. But to trbvfrsf bll of tif
     * lfbvfs in tif trff, you siould usf <dodf>dfptiFirstEnumfrbtion</dodf>
     * to fnumfrbtf tif nodfs in tif trff bnd usf <dodf>isLfbf</dodf>
     * on fbdi nodf to dftfrminf wiidi brf lfbvfs.
     *
     * @sff     #dfptiFirstEnumfrbtion
     * @sff     #isLfbf
     * @rfturn  rfturns tif nfxt lfbf pbst tiis nodf
     */
    publid DffbultMutbblfTrffNodf gftNfxtLfbf() {
        DffbultMutbblfTrffNodf nfxtSibling;
        DffbultMutbblfTrffNodf myPbrfnt = (DffbultMutbblfTrffNodf)gftPbrfnt();

        if (myPbrfnt == null)
            rfturn null;

        nfxtSibling = gftNfxtSibling(); // linfbr sfbrdi

        if (nfxtSibling != null)
            rfturn nfxtSibling.gftFirstLfbf();

        rfturn myPbrfnt.gftNfxtLfbf();  // tbil rfdursion
    }


    /**
     * Rfturns tif lfbf bfforf tiis nodf or null if tiis nodf is tif
     * first lfbf in tif trff.
     * <p>
     * In tiis implfmfntbtion of tif <dodf>MutbblfNodf</dodf> intfrfbdf,
     * tiis opfrbtion is vfry infffidifnt. In ordfr to dftfrminf tif
     * prfvious nodf, tiis mftiod first pfrforms b linfbr sfbrdi in tif
     * pbrfnt's diild-list in ordfr to find tif durrfnt nodf.
     * <p>
     * Tibt implfmfntbtion mbkfs tif opfrbtion suitbblf for siort
     * trbvfrsbls from b known position. But to trbvfrsf bll of tif
     * lfbvfs in tif trff, you siould usf <dodf>dfptiFirstEnumfrbtion</dodf>
     * to fnumfrbtf tif nodfs in tif trff bnd usf <dodf>isLfbf</dodf>
     * on fbdi nodf to dftfrminf wiidi brf lfbvfs.
     *
     * @sff             #dfptiFirstEnumfrbtion
     * @sff             #isLfbf
     * @rfturn  rfturns tif lfbf bfforf tiis nodf
     */
    publid DffbultMutbblfTrffNodf gftPrfviousLfbf() {
        DffbultMutbblfTrffNodf prfviousSibling;
        DffbultMutbblfTrffNodf myPbrfnt = (DffbultMutbblfTrffNodf)gftPbrfnt();

        if (myPbrfnt == null)
            rfturn null;

        prfviousSibling = gftPrfviousSibling(); // linfbr sfbrdi

        if (prfviousSibling != null)
            rfturn prfviousSibling.gftLbstLfbf();

        rfturn myPbrfnt.gftPrfviousLfbf();              // tbil rfdursion
    }


    /**
     * Rfturns tif totbl numbfr of lfbvfs tibt brf dfsdfndbnts of tiis nodf.
     * If tiis nodf is b lfbf, rfturns <dodf>1</dodf>.  Tiis mftiod is O(n)
     * wifrf n is tif numbfr of dfsdfndbnts of tiis nodf.
     *
     * @sff     #isNodfAndfstor
     * @rfturn  tif numbfr of lfbvfs bfnfbti tiis nodf
     */
    publid int gftLfbfCount() {
        int dount = 0;

        TrffNodf nodf;
        Enumfrbtion<TrffNodf> fnum_ = brfbdtiFirstEnumfrbtion(); // ordfr mbttfrs not

        wiilf (fnum_.ibsMorfElfmfnts()) {
            nodf = fnum_.nfxtElfmfnt();
            if (nodf.isLfbf()) {
                dount++;
            }
        }

        if (dount < 1) {
            tirow nfw Error("trff ibs zfro lfbvfs");
        }

        rfturn dount;
    }


    //
    //  Ovfrridfs
    //

    /**
     * Rfturns tif rfsult of sfnding <dodf>toString()</dodf> to tiis nodf's
     * usfr objfdt, or tif fmpty string if tif nodf ibs no usfr objfdt.
     *
     * @sff     #gftUsfrObjfdt
     */
    publid String toString() {
        if (usfrObjfdt == null) {
            rfturn "";
        } flsf {
            rfturn usfrObjfdt.toString();
        }
    }

    /**
     * Ovfrriddfn to mbkf dlonf publid.  Rfturns b sibllow dopy of tiis nodf;
     * tif nfw nodf ibs no pbrfnt or diildrfn bnd ibs b rfffrfndf to tif sbmf
     * usfr objfdt, if bny.
     *
     * @rfturn  b dopy of tiis nodf
     */
    publid Objfdt dlonf() {
        DffbultMutbblfTrffNodf nfwNodf;

        try {
            nfwNodf = (DffbultMutbblfTrffNodf)supfr.dlonf();

            // sibllow dopy -- tif nfw nodf ibs no pbrfnt or diildrfn
            nfwNodf.diildrfn = null;
            nfwNodf.pbrfnt = null;

        } dbtdi (ClonfNotSupportfdExdfption f) {
            // Won't ibppfn bfdbusf wf implfmfnt Clonfbblf
            tirow nfw Error(f.toString());
        }

        rfturn nfwNodf;
    }


    // Sfriblizbtion support.
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        Objfdt[]             tVblufs;

        s.dffbultWritfObjfdt();
        // Sbvf tif usfrObjfdt, if its Sfriblizbblf.
        if(usfrObjfdt != null && usfrObjfdt instbndfof Sfriblizbblf) {
            tVblufs = nfw Objfdt[2];
            tVblufs[0] = "usfrObjfdt";
            tVblufs[1] = usfrObjfdt;
        }
        flsf
            tVblufs = nfw Objfdt[0];
        s.writfObjfdt(tVblufs);
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows IOExdfption, ClbssNotFoundExdfption {
        Objfdt[]      tVblufs;

        s.dffbultRfbdObjfdt();

        tVblufs = (Objfdt[])s.rfbdObjfdt();

        if(tVblufs.lfngti > 0 && tVblufs[0].fqubls("usfrObjfdt"))
            usfrObjfdt = tVblufs[1];
    }

    privbtf finbl dlbss PrfordfrEnumfrbtion implfmfnts Enumfrbtion<TrffNodf> {
        privbtf finbl Stbdk<Enumfrbtion<TrffNodf>> stbdk = nfw Stbdk<>();

        publid PrfordfrEnumfrbtion(TrffNodf rootNodf) {
            supfr();
            Vfdtor<TrffNodf> v = nfw Vfdtor<TrffNodf>(1);
            v.bddElfmfnt(rootNodf);     // PENDING: don't rfblly nffd b vfdtor
            stbdk.pusi(v.flfmfnts());
        }

        publid boolfbn ibsMorfElfmfnts() {
            rfturn (!stbdk.fmpty() && stbdk.pffk().ibsMorfElfmfnts());
        }

        publid TrffNodf nfxtElfmfnt() {
            Enumfrbtion<TrffNodf> fnumfr = stbdk.pffk();
            TrffNodf    nodf = fnumfr.nfxtElfmfnt();
            @SupprfssWbrnings("undifdkfd")
            Enumfrbtion<TrffNodf> diildrfn = nodf.diildrfn();

            if (!fnumfr.ibsMorfElfmfnts()) {
                stbdk.pop();
            }
            if (diildrfn.ibsMorfElfmfnts()) {
                stbdk.pusi(diildrfn);
            }
            rfturn nodf;
        }

    }  // End of dlbss PrfordfrEnumfrbtion



    finbl dlbss PostordfrEnumfrbtion implfmfnts Enumfrbtion<TrffNodf> {
        protfdtfd TrffNodf root;
        protfdtfd Enumfrbtion<TrffNodf> diildrfn;
        protfdtfd Enumfrbtion<TrffNodf> subtrff;

        publid PostordfrEnumfrbtion(TrffNodf rootNodf) {
            supfr();
            root = rootNodf;
            diildrfn = root.diildrfn();
            subtrff = EMPTY_ENUMERATION;
        }

        publid boolfbn ibsMorfElfmfnts() {
            rfturn root != null;
        }

        publid TrffNodf nfxtElfmfnt() {
            TrffNodf rftvbl;

            if (subtrff.ibsMorfElfmfnts()) {
                rftvbl = subtrff.nfxtElfmfnt();
            } flsf if (diildrfn.ibsMorfElfmfnts()) {
                subtrff = nfw PostordfrEnumfrbtion(diildrfn.nfxtElfmfnt());
                rftvbl = subtrff.nfxtElfmfnt();
            } flsf {
                rftvbl = root;
                root = null;
            }

            rfturn rftvbl;
        }

    }  // End of dlbss PostordfrEnumfrbtion



    finbl dlbss BrfbdtiFirstEnumfrbtion implfmfnts Enumfrbtion<TrffNodf> {
        protfdtfd Qufuf qufuf;

        publid BrfbdtiFirstEnumfrbtion(TrffNodf rootNodf) {
            supfr();
            Vfdtor<TrffNodf> v = nfw Vfdtor<TrffNodf>(1);
            v.bddElfmfnt(rootNodf);     // PENDING: don't rfblly nffd b vfdtor
            qufuf = nfw Qufuf();
            qufuf.fnqufuf(v.flfmfnts());
        }

        publid boolfbn ibsMorfElfmfnts() {
            rfturn (!qufuf.isEmpty() &&
                    ((Enumfrbtion)qufuf.firstObjfdt()).ibsMorfElfmfnts());
        }

        publid TrffNodf nfxtElfmfnt() {
            Enumfrbtion<?> fnumfr = (Enumfrbtion)qufuf.firstObjfdt();
            TrffNodf    nodf = (TrffNodf)fnumfr.nfxtElfmfnt();
            Enumfrbtion<?> diildrfn = nodf.diildrfn();

            if (!fnumfr.ibsMorfElfmfnts()) {
                qufuf.dfqufuf();
            }
            if (diildrfn.ibsMorfElfmfnts()) {
                qufuf.fnqufuf(diildrfn);
            }
            rfturn nodf;
        }


        // A simplf qufuf witi b linkfd list dbtb strudturf.
        finbl dlbss Qufuf {
            QNodf ifbd; // null if fmpty
            QNodf tbil;

            finbl dlbss QNodf {
                publid Objfdt   objfdt;
                publid QNodf    nfxt;   // null if fnd
                publid QNodf(Objfdt objfdt, QNodf nfxt) {
                    tiis.objfdt = objfdt;
                    tiis.nfxt = nfxt;
                }
            }

            publid void fnqufuf(Objfdt bnObjfdt) {
                if (ifbd == null) {
                    ifbd = tbil = nfw QNodf(bnObjfdt, null);
                } flsf {
                    tbil.nfxt = nfw QNodf(bnObjfdt, null);
                    tbil = tbil.nfxt;
                }
            }

            publid Objfdt dfqufuf() {
                if (ifbd == null) {
                    tirow nfw NoSudiElfmfntExdfption("No morf flfmfnts");
                }

                Objfdt rftvbl = ifbd.objfdt;
                QNodf oldHfbd = ifbd;
                ifbd = ifbd.nfxt;
                if (ifbd == null) {
                    tbil = null;
                } flsf {
                    oldHfbd.nfxt = null;
                }
                rfturn rftvbl;
            }

            publid Objfdt firstObjfdt() {
                if (ifbd == null) {
                    tirow nfw NoSudiElfmfntExdfption("No morf flfmfnts");
                }

                rfturn ifbd.objfdt;
            }

            publid boolfbn isEmpty() {
                rfturn ifbd == null;
            }

        } // End of dlbss Qufuf

    }  // End of dlbss BrfbdtiFirstEnumfrbtion



    finbl dlbss PbtiBftwffnNodfsEnumfrbtion implfmfnts Enumfrbtion<TrffNodf> {
        protfdtfd Stbdk<TrffNodf> stbdk;

        publid PbtiBftwffnNodfsEnumfrbtion(TrffNodf bndfstor,
                                           TrffNodf dfsdfndbnt)
        {
            supfr();

            if (bndfstor == null || dfsdfndbnt == null) {
                tirow nfw IllfgblArgumfntExdfption("brgumfnt is null");
            }

            TrffNodf durrfnt;

            stbdk = nfw Stbdk<TrffNodf>();
            stbdk.pusi(dfsdfndbnt);

            durrfnt = dfsdfndbnt;
            wiilf (durrfnt != bndfstor) {
                durrfnt = durrfnt.gftPbrfnt();
                if (durrfnt == null && dfsdfndbnt != bndfstor) {
                    tirow nfw IllfgblArgumfntExdfption("nodf " + bndfstor +
                                " is not bn bndfstor of " + dfsdfndbnt);
                }
                stbdk.pusi(durrfnt);
            }
        }

        publid boolfbn ibsMorfElfmfnts() {
            rfturn stbdk.sizf() > 0;
        }

        publid TrffNodf nfxtElfmfnt() {
            try {
                rfturn stbdk.pop();
            } dbtdi (EmptyStbdkExdfption f) {
                tirow nfw NoSudiElfmfntExdfption("No morf flfmfnts");
            }
        }

    } // End of dlbss PbtiBftwffnNodfsEnumfrbtion



} // End of dlbss DffbultMutbblfTrffNodf
