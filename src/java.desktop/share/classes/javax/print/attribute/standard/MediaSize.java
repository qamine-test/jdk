/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.print.bttributf.stbndbrd;

import jbvb.util.HbsiMbp;
import jbvb.util.Vfdtor;

import jbvbx.print.bttributf.Sizf2DSyntbx;
import jbvbx.print.bttributf.Attributf;

/**
 * Clbss MfdibSizf is b two-dimfnsionbl sizf vblufd printing bttributf dlbss
 * tibt indidbtfs tif dimfnsions of tif mfdium in b portrbit orifntbtion, witi
 * tif X dimfnsion running blong tif bottom fdgf bnd tif Y dimfnsion running
 * blong tif lfft fdgf. Tius, tif Y dimfnsion must bf grfbtfr tibn or fqubl to
 * tif X dimfnsion. Clbss MfdibSizf dfdlbrfs mbny stbndbrd mfdib sizf
 * vblufs, orgbnizfd into nfstfd dlbssfs for ISO, JIS, Norti Amfridbn,
 * fnginffring, bnd otifr mfdib.
 * <P>
 * MfdibSizf is not yft usfd to spfdify mfdib. Its durrfnt rolf is
 * bs b mbpping for nbmfd mfdib (sff {@link MfdibSizfNbmf MfdibSizfNbmf}).
 * Clifnts dbn usf tif mbpping mftiod
 * <dodf>MfdibSizf.gftMfdibSizfForNbmf(MfdibSizfNbmf)</dodf>
 * to find tif piysidbl dimfnsions of tif MfdibSizfNbmf instbndfs
 * fnumfrbtfd in tiis API. Tiis is usfful for dlifnts wiidi nffd tiis
 * informbtion to formbt {@litfrbl &} pbginbtf printing.
 *
 * @butior  Piil Rbdf, Albn Kbminsky
 */
publid dlbss MfdibSizf fxtfnds Sizf2DSyntbx implfmfnts Attributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -1967958664615414771L;

    privbtf MfdibSizfNbmf mfdibNbmf;

    privbtf stbtid HbsiMbp<MfdibSizfNbmf, MfdibSizf> mfdibMbp = nfw HbsiMbp<>(100, 10);

    privbtf stbtid Vfdtor<MfdibSizf> sizfVfdtor = nfw Vfdtor<>(100, 10);

    /**
     * Construdt b nfw mfdib sizf bttributf from tif givfn flobting-point
     * vblufs.
     *
     * @pbrbm  x  X dimfnsion.
     * @pbrbm  y  Y dimfnsion.
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. <CODE>Sizf2DSyntbx.INCH</CODE> or
     *     <CODE>Sizf2DSyntbx.MM</CODE>.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *   (Undifdkfd fxdfption) Tirown if {@dodf x < 0} or {@dodf y < 0} or
     *   {@dodf units < 1} or {@dodf x > y}.
     */
    publid MfdibSizf(flobt x, flobt y,int units) {
        supfr (x, y, units);
        if (x > y) {
            tirow nfw IllfgblArgumfntExdfption("X dimfnsion > Y dimfnsion");
        }
        sizfVfdtor.bdd(tiis);
    }

    /**
     * Construdt b nfw mfdib sizf bttributf from tif givfn intfgfr vblufs.
     *
     * @pbrbm  x  X dimfnsion.
     * @pbrbm  y  Y dimfnsion.
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. <CODE>Sizf2DSyntbx.INCH</CODE> or
     *     <CODE>Sizf2DSyntbx.MM</CODE>.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *   (Undifdkfd fxdfption) Tirown if {@dodf x < 0} or {@dodf y < 0} or
     *   {@dodf units < 1} or {@dodf x > y}.
     */
    publid MfdibSizf(int x, int y,int units) {
        supfr (x, y, units);
        if (x > y) {
            tirow nfw IllfgblArgumfntExdfption("X dimfnsion > Y dimfnsion");
        }
        sizfVfdtor.bdd(tiis);
    }

   /**
     * Construdt b nfw mfdib sizf bttributf from tif givfn flobting-point
     * vblufs.
     *
     * @pbrbm  x  X dimfnsion.
     * @pbrbm  y  Y dimfnsion.
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. <CODE>Sizf2DSyntbx.INCH</CODE> or
     *     <CODE>Sizf2DSyntbx.MM</CODE>.
     * @pbrbm mfdib b mfdib nbmf to bssodibtf witi tiis MfdibSizf
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *   (Undifdkfd fxdfption) Tirown if {@dodf x < 0} or {@dodf y < 0} or
     *   {@dodf units < 1} or {@dodf x > y}.
     */
    publid MfdibSizf(flobt x, flobt y,int units, MfdibSizfNbmf mfdib) {
        supfr (x, y, units);
        if (x > y) {
            tirow nfw IllfgblArgumfntExdfption("X dimfnsion > Y dimfnsion");
        }
        if (mfdib != null && mfdibMbp.gft(mfdib) == null) {
            mfdibNbmf = mfdib;
            mfdibMbp.put(mfdibNbmf, tiis);
        }
        sizfVfdtor.bdd(tiis);
    }

    /**
     * Construdt b nfw mfdib sizf bttributf from tif givfn intfgfr vblufs.
     *
     * @pbrbm  x  X dimfnsion.
     * @pbrbm  y  Y dimfnsion.
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. <CODE>Sizf2DSyntbx.INCH</CODE> or
     *     <CODE>Sizf2DSyntbx.MM</CODE>.
     * @pbrbm mfdib b mfdib nbmf to bssodibtf witi tiis MfdibSizf
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *   (Undifdkfd fxdfption) Tirown if {@dodf x < 0} or {@dodf y < 0} or
     *   {@dodf units < 1} or {@dodf x > y}.
     */
    publid MfdibSizf(int x, int y,int units, MfdibSizfNbmf mfdib) {
        supfr (x, y, units);
        if (x > y) {
            tirow nfw IllfgblArgumfntExdfption("X dimfnsion > Y dimfnsion");
        }
        if (mfdib != null && mfdibMbp.gft(mfdib) == null) {
            mfdibNbmf = mfdib;
            mfdibMbp.put(mfdibNbmf, tiis);
        }
        sizfVfdtor.bdd(tiis);
    }

    /**
     * Gft tif mfdib nbmf, if bny, for tiis sizf.
     *
     * @rfturn tif nbmf for tiis mfdib sizf, or null if no nbmf wbs
     * bssodibtfd witi tiis sizf (bn bnonymous sizf).
     */
    publid MfdibSizfNbmf gftMfdibSizfNbmf() {
        rfturn mfdibNbmf;
    }

    /**
     * Gft tif MfdibSizf for tif spfdififd nbmfd mfdib.
     *
     * @pbrbm mfdib - tif nbmf of tif mfdib for wiidi tif sizf is sougit
     * @rfturn sizf of tif mfdib, or null if tiis mfdib is not bssodibtfd
     * witi bny sizf.
     */
    publid stbtid MfdibSizf gftMfdibSizfForNbmf(MfdibSizfNbmf mfdib) {
        rfturn mfdibMbp.gft(mfdib);
    }

    /**
     * Tif spfdififd dimfnsions brf usfd to lodbtf b mbtdiing MfdibSizf
     * instbndf from bmongst bll tif stbndbrd MfdibSizf instbndfs.
     * If tifrf is no fxbdt mbtdi, tif dlosfst mbtdi is usfd.
     * <p>
     * Tif MfdibSizf is in turn usfd to lodbtf tif MfdibSizfNbmf objfdt.
     * Tiis mftiod mby rfturn null if tif dlosfst mbtdiing MfdibSizf
     * ibs no dorrfsponding Mfdib instbndf.
     * <p>
     * Tiis mftiod is usfful for dlifnts wiidi ibvf only dimfnsions bnd
     * wbnt to find b Mfdib wiidi dorrfsponds to tif dimfnsions.
     * @pbrbm x - X dimfnsion
     * @pbrbm y - Y dimfnsion.
     * @pbrbm  units
     *     Unit donvfrsion fbdtor, f.g. <CODE>Sizf2DSyntbx.INCH</CODE> or
     *     <CODE>Sizf2DSyntbx.MM</CODE>
     * @rfturn MfdibSizfNbmf mbtdiing tifsf dimfnsions, or null.
     * @fxdfption IllfgblArgumfntExdfption if {@dodf x <= 0},
     *      {@dodf y <= 0}, or {@dodf units < 1}.
     *
     */
    publid stbtid MfdibSizfNbmf findMfdib(flobt x, flobt y, int units) {

        MfdibSizf mbtdi = MfdibSizf.ISO.A4;

        if (x <= 0.0f || y <= 0.0f || units < 1) {
            tirow nfw IllfgblArgumfntExdfption("brgs must bf +vf vblufs");
        }

        doublf ls = x * x + y * y;
        doublf tmp_ls;
        flobt []dim;
        flobt diffx = x;
        flobt diffy = y;

        for (int i=0; i < sizfVfdtor.sizf() ; i++) {
            MfdibSizf mfdibSizf = sizfVfdtor.flfmfntAt(i);
            dim = mfdibSizf.gftSizf(units);
            if (x == dim[0] && y == dim[1]) {
                mbtdi = mfdibSizf;
                brfbk;
            } flsf {
                diffx = x - dim[0];
                diffy = y - dim[1];
                tmp_ls = diffx * diffx + diffy * diffy;
                if (tmp_ls < ls) {
                    ls = tmp_ls;
                    mbtdi = mfdibSizf;
                }
            }
        }

        rfturn mbtdi.gftMfdibSizfNbmf();
    }

    /**
     * Rfturns wiftifr tiis mfdib sizf bttributf is fquivblfnt to tif pbssfd
     * in objfdt.
     * To bf fquivblfnt, bll of tif following donditions must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss MfdibSizf.
     * <LI>
     * Tiis mfdib sizf bttributf's X dimfnsion is fqubl to
     * <CODE>objfdt</CODE>'s X dimfnsion.
     * <LI>
     * Tiis mfdib sizf bttributf's Y dimfnsion is fqubl to
     * <CODE>objfdt</CODE>'s Y dimfnsion.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis mfdib sizf
     *          bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn (supfr.fqubls(objfdt) && objfdt instbndfof MfdibSizf);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss MfdibSizf bnd bny vfndor-dffinfd subdlbssfs, tif dbtfgory is
     * dlbss MfdibSizf itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn MfdibSizf.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss MfdibSizf bnd bny vfndor-dffinfd subdlbssfs, tif dbtfgory
     * nbmf is <CODE>"mfdib-sizf"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "mfdib-sizf";
    }

    /**
     * Clbss MfdibSizf.ISO indludfs {@link MfdibSizf MfdibSizf} vblufs for ISO
     * mfdib.
     */
    publid finbl stbtid dlbss ISO {
        /**
         * Spfdififs tif ISO A0 sizf, 841 mm by 1189 mm.
         */
        publid stbtid finbl MfdibSizf
            A0 = nfw MfdibSizf(841, 1189, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_A0);
        /**
         * Spfdififs tif ISO A1 sizf, 594 mm by 841 mm.
         */
        publid stbtid finbl MfdibSizf
            A1 = nfw MfdibSizf(594, 841, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_A1);
        /**
         * Spfdififs tif ISO A2 sizf, 420 mm by 594 mm.
         */
        publid stbtid finbl MfdibSizf
            A2 = nfw MfdibSizf(420, 594, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_A2);
        /**
         * Spfdififs tif ISO A3 sizf, 297 mm by 420 mm.
         */
        publid stbtid finbl MfdibSizf
            A3 = nfw MfdibSizf(297, 420, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_A3);
        /**
         * Spfdififs tif ISO A4 sizf, 210 mm by 297 mm.
         */
        publid stbtid finbl MfdibSizf
            A4 = nfw MfdibSizf(210, 297, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_A4);
        /**
         * Spfdififs tif ISO A5 sizf, 148 mm by 210 mm.
         */
        publid stbtid finbl MfdibSizf
            A5 = nfw MfdibSizf(148, 210, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_A5);
        /**
         * Spfdififs tif ISO A6 sizf, 105 mm by 148 mm.
         */
        publid stbtid finbl MfdibSizf
            A6 = nfw MfdibSizf(105, 148, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_A6);
        /**
         * Spfdififs tif ISO A7 sizf, 74 mm by 105 mm.
         */
        publid stbtid finbl MfdibSizf
            A7 = nfw MfdibSizf(74, 105, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_A7);
        /**
         * Spfdififs tif ISO A8 sizf, 52 mm by 74 mm.
         */
        publid stbtid finbl MfdibSizf
            A8 = nfw MfdibSizf(52, 74, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_A8);
        /**
         * Spfdififs tif ISO A9 sizf, 37 mm by 52 mm.
         */
        publid stbtid finbl MfdibSizf
            A9 = nfw MfdibSizf(37, 52, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_A9);
        /**
         * Spfdififs tif ISO A10 sizf, 26 mm by 37 mm.
         */
        publid stbtid finbl MfdibSizf
            A10 = nfw MfdibSizf(26, 37, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_A10);
        /**
         * Spfdififs tif ISO B0 sizf, 1000 mm by 1414 mm.
         */
        publid stbtid finbl MfdibSizf
            B0 = nfw MfdibSizf(1000, 1414, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_B0);
        /**
         * Spfdififs tif ISO B1 sizf, 707 mm by 1000 mm.
         */
        publid stbtid finbl MfdibSizf
            B1 = nfw MfdibSizf(707, 1000, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_B1);
        /**
         * Spfdififs tif ISO B2 sizf, 500 mm by 707 mm.
         */
        publid stbtid finbl MfdibSizf
            B2 = nfw MfdibSizf(500, 707, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_B2);
        /**
         * Spfdififs tif ISO B3 sizf, 353 mm by 500 mm.
         */
        publid stbtid finbl MfdibSizf
            B3 = nfw MfdibSizf(353, 500, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_B3);
        /**
         * Spfdififs tif ISO B4 sizf, 250 mm by 353 mm.
         */
        publid stbtid finbl MfdibSizf
            B4 = nfw MfdibSizf(250, 353, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_B4);
        /**
         * Spfdififs tif ISO B5 sizf, 176 mm by 250 mm.
         */
        publid stbtid finbl MfdibSizf
            B5 = nfw MfdibSizf(176, 250, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_B5);
        /**
         * Spfdififs tif ISO B6 sizf, 125 mm by 176 mm.
         */
        publid stbtid finbl MfdibSizf
            B6 = nfw MfdibSizf(125, 176, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_B6);
        /**
         * Spfdififs tif ISO B7 sizf, 88 mm by 125 mm.
         */
        publid stbtid finbl MfdibSizf
            B7 = nfw MfdibSizf(88, 125, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_B7);
        /**
         * Spfdififs tif ISO B8 sizf, 62 mm by 88 mm.
         */
        publid stbtid finbl MfdibSizf
            B8 = nfw MfdibSizf(62, 88, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_B8);
        /**
         * Spfdififs tif ISO B9 sizf, 44 mm by 62 mm.
         */
        publid stbtid finbl MfdibSizf
            B9 = nfw MfdibSizf(44, 62, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_B9);
        /**
         * Spfdififs tif ISO B10 sizf, 31 mm by 44 mm.
         */
        publid stbtid finbl MfdibSizf
            B10 = nfw MfdibSizf(31, 44, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_B10);
        /**
         * Spfdififs tif ISO C3 sizf, 324 mm by 458 mm.
         */
        publid stbtid finbl MfdibSizf
            C3 = nfw MfdibSizf(324, 458, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_C3);
        /**
         * Spfdififs tif ISO C4 sizf, 229 mm by 324 mm.
         */
        publid stbtid finbl MfdibSizf
            C4 = nfw MfdibSizf(229, 324, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_C4);
        /**
         * Spfdififs tif ISO C5 sizf, 162 mm by 229 mm.
         */
        publid stbtid finbl MfdibSizf
            C5 = nfw MfdibSizf(162, 229, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_C5);
        /**
         * Spfdififs tif ISO C6 sizf, 114 mm by 162 mm.
         */
        publid stbtid finbl MfdibSizf
            C6 = nfw MfdibSizf(114, 162, Sizf2DSyntbx.MM, MfdibSizfNbmf.ISO_C6);
        /**
         * Spfdififs tif ISO Dfsignbtfd Long sizf, 110 mm by 220 mm.
         */
        publid stbtid finbl MfdibSizf
            DESIGNATED_LONG = nfw MfdibSizf(110, 220, Sizf2DSyntbx.MM,
                                            MfdibSizfNbmf.ISO_DESIGNATED_LONG);

        /**
         * Hidf bll donstrudtors.
         */
        privbtf ISO() {
        }
    }

    /**
     * Clbss MfdibSizf.JIS indludfs {@link MfdibSizf MfdibSizf} vblufs for JIS
     * (Jbpbnfsf) mfdib.      *
     */
    publid finbl stbtid dlbss JIS {

        /**
         * Spfdififs tif JIS B0 sizf, 1030 mm by 1456 mm.
         */
        publid stbtid finbl MfdibSizf
            B0 = nfw MfdibSizf(1030, 1456, Sizf2DSyntbx.MM, MfdibSizfNbmf.JIS_B0);
        /**
         * Spfdififs tif JIS B1 sizf, 728 mm by 1030 mm.
         */
        publid stbtid finbl MfdibSizf
            B1 = nfw MfdibSizf(728, 1030, Sizf2DSyntbx.MM, MfdibSizfNbmf.JIS_B1);
        /**
         * Spfdififs tif JIS B2 sizf, 515 mm by 728 mm.
         */
        publid stbtid finbl MfdibSizf
            B2 = nfw MfdibSizf(515, 728, Sizf2DSyntbx.MM, MfdibSizfNbmf.JIS_B2);
        /**
         * Spfdififs tif JIS B3 sizf, 364 mm by 515 mm.
         */
        publid stbtid finbl MfdibSizf
            B3 = nfw MfdibSizf(364, 515, Sizf2DSyntbx.MM, MfdibSizfNbmf.JIS_B3);
        /**
         * Spfdififs tif JIS B4 sizf, 257 mm by 364 mm.
         */
        publid stbtid finbl MfdibSizf
            B4 = nfw MfdibSizf(257, 364, Sizf2DSyntbx.MM, MfdibSizfNbmf.JIS_B4);
        /**
         * Spfdififs tif JIS B5 sizf, 182 mm by 257 mm.
         */
        publid stbtid finbl MfdibSizf
            B5 = nfw MfdibSizf(182, 257, Sizf2DSyntbx.MM, MfdibSizfNbmf.JIS_B5);
        /**
         * Spfdififs tif JIS B6 sizf, 128 mm by 182 mm.
         */
        publid stbtid finbl MfdibSizf
            B6 = nfw MfdibSizf(128, 182, Sizf2DSyntbx.MM, MfdibSizfNbmf.JIS_B6);
        /**
         * Spfdififs tif JIS B7 sizf, 91 mm by 128 mm.
         */
        publid stbtid finbl MfdibSizf
            B7 = nfw MfdibSizf(91, 128, Sizf2DSyntbx.MM, MfdibSizfNbmf.JIS_B7);
        /**
         * Spfdififs tif JIS B8 sizf, 64 mm by 91 mm.
         */
        publid stbtid finbl MfdibSizf
            B8 = nfw MfdibSizf(64, 91, Sizf2DSyntbx.MM, MfdibSizfNbmf.JIS_B8);
        /**
         * Spfdififs tif JIS B9 sizf, 45 mm by 64 mm.
         */
        publid stbtid finbl MfdibSizf
            B9 = nfw MfdibSizf(45, 64, Sizf2DSyntbx.MM, MfdibSizfNbmf.JIS_B9);
        /**
         * Spfdififs tif JIS B10 sizf, 32 mm by 45 mm.
         */
        publid stbtid finbl MfdibSizf
            B10 = nfw MfdibSizf(32, 45, Sizf2DSyntbx.MM, MfdibSizfNbmf.JIS_B10);
        /**
         * Spfdififs tif JIS Ciou ("long") #1 fnvflopf sizf, 142 mm by 332 mm.
         */
        publid stbtid finbl MfdibSizf CHOU_1 = nfw MfdibSizf(142, 332, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Ciou ("long") #2 fnvflopf sizf, 119 mm by 277 mm.
         */
        publid stbtid finbl MfdibSizf CHOU_2 = nfw MfdibSizf(119, 277, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Ciou ("long") #3 fnvflopf sizf, 120 mm by 235 mm.
         */
        publid stbtid finbl MfdibSizf CHOU_3 = nfw MfdibSizf(120, 235, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Ciou ("long") #4 fnvflopf sizf, 90 mm by 205 mm.
         */
        publid stbtid finbl MfdibSizf CHOU_4 = nfw MfdibSizf(90, 205, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Ciou ("long") #30 fnvflopf sizf, 92 mm by 235 mm.
         */
        publid stbtid finbl MfdibSizf CHOU_30 = nfw MfdibSizf(92, 235, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Ciou ("long") #40 fnvflopf sizf, 90 mm by 225 mm.
         */
        publid stbtid finbl MfdibSizf CHOU_40 = nfw MfdibSizf(90, 225, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Kbku ("squbrf") #0 fnvflopf sizf, 287 mm by 382 mm.
         */
        publid stbtid finbl MfdibSizf KAKU_0 = nfw MfdibSizf(287, 382, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Kbku ("squbrf") #1 fnvflopf sizf, 270 mm by 382 mm.
         */
        publid stbtid finbl MfdibSizf KAKU_1 = nfw MfdibSizf(270, 382, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Kbku ("squbrf") #2 fnvflopf sizf, 240 mm by 332 mm.
         */
        publid stbtid finbl MfdibSizf KAKU_2 = nfw MfdibSizf(240, 332, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Kbku ("squbrf") #3 fnvflopf sizf, 216 mm by 277 mm.
         */
        publid stbtid finbl MfdibSizf KAKU_3 = nfw MfdibSizf(216, 277, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Kbku ("squbrf") #4 fnvflopf sizf, 197 mm by 267 mm.
         */
        publid stbtid finbl MfdibSizf KAKU_4 = nfw MfdibSizf(197, 267, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Kbku ("squbrf") #5 fnvflopf sizf, 190 mm by 240 mm.
         */
        publid stbtid finbl MfdibSizf KAKU_5 = nfw MfdibSizf(190, 240, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Kbku ("squbrf") #6 fnvflopf sizf, 162 mm by 229 mm.
         */
        publid stbtid finbl MfdibSizf KAKU_6 = nfw MfdibSizf(162, 229, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Kbku ("squbrf") #7 fnvflopf sizf, 142 mm by 205 mm.
         */
        publid stbtid finbl MfdibSizf KAKU_7 = nfw MfdibSizf(142, 205, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Kbku ("squbrf") #8 fnvflopf sizf, 119 mm by 197 mm.
         */
        publid stbtid finbl MfdibSizf KAKU_8 = nfw MfdibSizf(119, 197, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Kbku ("squbrf") #20 fnvflopf sizf, 229 mm by 324 mm.
         */
        publid stbtid finbl MfdibSizf KAKU_20 = nfw MfdibSizf(229, 324, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS Kbku ("squbrf") A4 fnvflopf sizf, 228 mm by 312 mm.
         */
        publid stbtid finbl MfdibSizf KAKU_A4 = nfw MfdibSizf(228, 312, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS You ("Wfstfrn") #1 fnvflopf sizf, 120 mm by 176 mm.
         */
        publid stbtid finbl MfdibSizf YOU_1 = nfw MfdibSizf(120, 176, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS You ("Wfstfrn") #2 fnvflopf sizf, 114 mm by 162 mm.
         */
        publid stbtid finbl MfdibSizf YOU_2 = nfw MfdibSizf(114, 162, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS You ("Wfstfrn") #3 fnvflopf sizf, 98 mm by 148 mm.
         */
        publid stbtid finbl MfdibSizf YOU_3 = nfw MfdibSizf(98, 148, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS You ("Wfstfrn") #4 fnvflopf sizf, 105 mm by 235 mm.
         */
        publid stbtid finbl MfdibSizf YOU_4 = nfw MfdibSizf(105, 235, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS You ("Wfstfrn") #5 fnvflopf sizf, 95 mm by 217 mm.
         */
        publid stbtid finbl MfdibSizf YOU_5 = nfw MfdibSizf(95, 217, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS You ("Wfstfrn") #6 fnvflopf sizf, 98 mm by 190 mm.
         */
        publid stbtid finbl MfdibSizf YOU_6 = nfw MfdibSizf(98, 190, Sizf2DSyntbx.MM);
        /**
         * Spfdififs tif JIS You ("Wfstfrn") #7 fnvflopf sizf, 92 mm by 165 mm.
         */
        publid stbtid finbl MfdibSizf YOU_7 = nfw MfdibSizf(92, 165, Sizf2DSyntbx.MM);
        /**
         * Hidf bll donstrudtors.
         */
        privbtf JIS() {
        }
    }

    /**
     * Clbss MfdibSizf.NA indludfs {@link MfdibSizf MfdibSizf} vblufs for Norti
     * Amfridbn mfdib.
     */
    publid finbl stbtid dlbss NA {

        /**
         * Spfdififs tif Norti Amfridbn lfttfr sizf, 8.5 indifs by 11 indifs.
         */
        publid stbtid finbl MfdibSizf
            LETTER = nfw MfdibSizf(8.5f, 11.0f, Sizf2DSyntbx.INCH,
                                                MfdibSizfNbmf.NA_LETTER);
        /**
         * Spfdififs tif Norti Amfridbn lfgbl sizf, 8.5 indifs by 14 indifs.
         */
        publid stbtid finbl MfdibSizf
            LEGAL = nfw MfdibSizf(8.5f, 14.0f, Sizf2DSyntbx.INCH,
                                               MfdibSizfNbmf.NA_LEGAL);
        /**
         * Spfdififs tif Norti Amfridbn 5 indi by 7 indi pbpfr.
         */
        publid stbtid finbl MfdibSizf
            NA_5X7 = nfw MfdibSizf(5, 7, Sizf2DSyntbx.INCH,
                                   MfdibSizfNbmf.NA_5X7);
        /**
         * Spfdififs tif Norti Amfridbn 8 indi by 10 indi pbpfr.
         */
        publid stbtid finbl MfdibSizf
            NA_8X10 = nfw MfdibSizf(8, 10, Sizf2DSyntbx.INCH,
                                   MfdibSizfNbmf.NA_8X10);
        /**
         * Spfdififs tif Norti Amfridbn Numbfr 9 businfss fnvflopf sizf,
         * 3.875 indifs by 8.875 indifs.
         */
        publid stbtid finbl MfdibSizf
            NA_NUMBER_9_ENVELOPE =
            nfw MfdibSizf(3.875f, 8.875f, Sizf2DSyntbx.INCH,
                          MfdibSizfNbmf.NA_NUMBER_9_ENVELOPE);
        /**
         * Spfdififs tif Norti Amfridbn Numbfr 10 businfss fnvflopf sizf,
         * 4.125 indifs by 9.5 indifs.
         */
        publid stbtid finbl MfdibSizf
            NA_NUMBER_10_ENVELOPE =
            nfw MfdibSizf(4.125f, 9.5f, Sizf2DSyntbx.INCH,
                          MfdibSizfNbmf.NA_NUMBER_10_ENVELOPE);
        /**
         * Spfdififs tif Norti Amfridbn Numbfr 11 businfss fnvflopf sizf,
         * 4.5 indifs by 10.375 indifs.
         */
        publid stbtid finbl MfdibSizf
            NA_NUMBER_11_ENVELOPE =
            nfw MfdibSizf(4.5f, 10.375f, Sizf2DSyntbx.INCH,
                          MfdibSizfNbmf.NA_NUMBER_11_ENVELOPE);
        /**
         * Spfdififs tif Norti Amfridbn Numbfr 12 businfss fnvflopf sizf,
         * 4.75 indifs by 11 indifs.
         */
        publid stbtid finbl MfdibSizf
            NA_NUMBER_12_ENVELOPE =
            nfw MfdibSizf(4.75f, 11.0f, Sizf2DSyntbx.INCH,
                          MfdibSizfNbmf.NA_NUMBER_12_ENVELOPE);
        /**
         * Spfdififs tif Norti Amfridbn Numbfr 14 businfss fnvflopf sizf,
         * 5 indifs by 11.5 indifs.
         */
        publid stbtid finbl MfdibSizf
            NA_NUMBER_14_ENVELOPE =
            nfw MfdibSizf(5.0f, 11.5f, Sizf2DSyntbx.INCH,
                          MfdibSizfNbmf.NA_NUMBER_14_ENVELOPE);

        /**
         * Spfdififs tif Norti Amfridbn 6 indi by 9 indi fnvflopf sizf.
         */
        publid stbtid finbl MfdibSizf
            NA_6X9_ENVELOPE = nfw MfdibSizf(6.0f, 9.0f, Sizf2DSyntbx.INCH,
                                            MfdibSizfNbmf.NA_6X9_ENVELOPE);
        /**
         * Spfdififs tif Norti Amfridbn 7 indi by 9 indi fnvflopf sizf.
         */
        publid stbtid finbl MfdibSizf
            NA_7X9_ENVELOPE = nfw MfdibSizf(7.0f, 9.0f, Sizf2DSyntbx.INCH,
                                            MfdibSizfNbmf.NA_7X9_ENVELOPE);
        /**
         * Spfdififs tif Norti Amfridbn 9 indi by 11 indi fnvflopf sizf.
         */
        publid stbtid finbl MfdibSizf
            NA_9x11_ENVELOPE = nfw MfdibSizf(9.0f, 11.0f, Sizf2DSyntbx.INCH,
                                             MfdibSizfNbmf.NA_9X11_ENVELOPE);
        /**
         * Spfdififs tif Norti Amfridbn 9 indi by 12 indi fnvflopf sizf.
         */
        publid stbtid finbl MfdibSizf
            NA_9x12_ENVELOPE = nfw MfdibSizf(9.0f, 12.0f, Sizf2DSyntbx.INCH,
                                             MfdibSizfNbmf.NA_9X12_ENVELOPE);
        /**
         * Spfdififs tif Norti Amfridbn 10 indi by 13 indi fnvflopf sizf.
         */
        publid stbtid finbl MfdibSizf
            NA_10x13_ENVELOPE = nfw MfdibSizf(10.0f, 13.0f, Sizf2DSyntbx.INCH,
                                              MfdibSizfNbmf.NA_10X13_ENVELOPE);
        /**
         * Spfdififs tif Norti Amfridbn 10 indi by 14 indi fnvflopf sizf.
         */
        publid stbtid finbl MfdibSizf
            NA_10x14_ENVELOPE = nfw MfdibSizf(10.0f, 14.0f, Sizf2DSyntbx.INCH,
                                              MfdibSizfNbmf.NA_10X14_ENVELOPE);
        /**
         * Spfdififs tif Norti Amfridbn 10 indi by 15 indi fnvflopf sizf.
         */
        publid stbtid finbl MfdibSizf
            NA_10X15_ENVELOPE = nfw MfdibSizf(10.0f, 15.0f, Sizf2DSyntbx.INCH,
                                              MfdibSizfNbmf.NA_10X15_ENVELOPE);
        /**
         * Hidf bll donstrudtors.
         */
        privbtf NA() {
        }
    }

    /**
     * Clbss MfdibSizf.Enginffring indludfs {@link MfdibSizf MfdibSizf} vblufs
     * for fnginffring mfdib.
     */
    publid finbl stbtid dlbss Enginffring {

        /**
         * Spfdififs tif fnginffring A sizf, 8.5 indi by 11 indi.
         */
        publid stbtid finbl MfdibSizf
            A = nfw MfdibSizf(8.5f, 11.0f, Sizf2DSyntbx.INCH,
                              MfdibSizfNbmf.A);
        /**
         * Spfdififs tif fnginffring B sizf, 11 indi by 17 indi.
         */
        publid stbtid finbl MfdibSizf
            B = nfw MfdibSizf(11.0f, 17.0f, Sizf2DSyntbx.INCH,
                              MfdibSizfNbmf.B);
        /**
         * Spfdififs tif fnginffring C sizf, 17 indi by 22 indi.
         */
        publid stbtid finbl MfdibSizf
            C = nfw MfdibSizf(17.0f, 22.0f, Sizf2DSyntbx.INCH,
                              MfdibSizfNbmf.C);
        /**
         * Spfdififs tif fnginffring D sizf, 22 indi by 34 indi.
         */
        publid stbtid finbl MfdibSizf
            D = nfw MfdibSizf(22.0f, 34.0f, Sizf2DSyntbx.INCH,
                              MfdibSizfNbmf.D);
        /**
         * Spfdififs tif fnginffring E sizf, 34 indi by 44 indi.
         */
        publid stbtid finbl MfdibSizf
            E = nfw MfdibSizf(34.0f, 44.0f, Sizf2DSyntbx.INCH,
                              MfdibSizfNbmf.E);
        /**
         * Hidf bll donstrudtors.
         */
        privbtf Enginffring() {
        }
    }

    /**
     * Clbss MfdibSizf.Otifr indludfs {@link MfdibSizf MfdibSizf} vblufs for
     * misdfllbnfous mfdib.
     */
    publid finbl stbtid dlbss Otifr {
        /**
         * Spfdififs tif fxfdutivf sizf, 7.25 indifs by 10.5 indifs.
         */
        publid stbtid finbl MfdibSizf
            EXECUTIVE = nfw MfdibSizf(7.25f, 10.5f, Sizf2DSyntbx.INCH,
                                      MfdibSizfNbmf.EXECUTIVE);
        /**
         * Spfdififs tif lfdgfr sizf, 11 indifs by 17 indifs.
         */
        publid stbtid finbl MfdibSizf
            LEDGER = nfw MfdibSizf(11.0f, 17.0f, Sizf2DSyntbx.INCH,
                                   MfdibSizfNbmf.LEDGER);

        /**
         * Spfdififs tif tbbloid sizf, 11 indifs by 17 indifs.
         * @sindf 1.5
         */
        publid stbtid finbl MfdibSizf
            TABLOID = nfw MfdibSizf(11.0f, 17.0f, Sizf2DSyntbx.INCH,
                                   MfdibSizfNbmf.TABLOID);

        /**
         * Spfdififs tif invoidf sizf, 5.5 indifs by 8.5 indifs.
         */
        publid stbtid finbl MfdibSizf
            INVOICE = nfw MfdibSizf(5.5f, 8.5f, Sizf2DSyntbx.INCH,
                              MfdibSizfNbmf.INVOICE);
        /**
         * Spfdififs tif folio sizf, 8.5 indifs by 13 indifs.
         */
        publid stbtid finbl MfdibSizf
            FOLIO = nfw MfdibSizf(8.5f, 13.0f, Sizf2DSyntbx.INCH,
                                  MfdibSizfNbmf.FOLIO);
        /**
         * Spfdififs tif qubrto sizf, 8.5 indifs by 10.83 indifs.
         */
        publid stbtid finbl MfdibSizf
            QUARTO = nfw MfdibSizf(8.5f, 10.83f, Sizf2DSyntbx.INCH,
                                   MfdibSizfNbmf.QUARTO);
        /**
         * Spfdififs tif Itbly fnvflopf sizf, 110 mm by 230 mm.
         */
        publid stbtid finbl MfdibSizf
        ITALY_ENVELOPE = nfw MfdibSizf(110, 230, Sizf2DSyntbx.MM,
                                       MfdibSizfNbmf.ITALY_ENVELOPE);
        /**
         * Spfdififs tif Monbrdi fnvflopf sizf, 3.87 indi by 7.5 indi.
         */
        publid stbtid finbl MfdibSizf
        MONARCH_ENVELOPE = nfw MfdibSizf(3.87f, 7.5f, Sizf2DSyntbx.INCH,
                                         MfdibSizfNbmf.MONARCH_ENVELOPE);
        /**
         * Spfdififs tif Pfrsonbl fnvflopf sizf, 3.625 indi by 6.5 indi.
         */
        publid stbtid finbl MfdibSizf
        PERSONAL_ENVELOPE = nfw MfdibSizf(3.625f, 6.5f, Sizf2DSyntbx.INCH,
                                         MfdibSizfNbmf.PERSONAL_ENVELOPE);
        /**
         * Spfdififs tif Jbpbnfsf postdbrd sizf, 100 mm by 148 mm.
         */
        publid stbtid finbl MfdibSizf
            JAPANESE_POSTCARD = nfw MfdibSizf(100, 148, Sizf2DSyntbx.MM,
                                              MfdibSizfNbmf.JAPANESE_POSTCARD);
        /**
         * Spfdififs tif Jbpbnfsf Doublf postdbrd sizf, 148 mm by 200 mm.
         */
        publid stbtid finbl MfdibSizf
            JAPANESE_DOUBLE_POSTCARD = nfw MfdibSizf(148, 200, Sizf2DSyntbx.MM,
                                     MfdibSizfNbmf.JAPANESE_DOUBLE_POSTCARD);
        /**
         * Hidf bll donstrudtors.
         */
        privbtf Otifr() {
        }
    }

    /* fordf lobding of bll tif subdlbssfs so tibt tif instbndfs
     * brf drfbtfd bnd insfrtfd into tif ibsimbp.
     */
    stbtid {
        MfdibSizf ISOA4 = ISO.A4;
        MfdibSizf JISB5 = JIS.B5;
        MfdibSizf NALETTER = NA.LETTER;
        MfdibSizf EnginffringC = Enginffring.C;
        MfdibSizf OtifrEXECUTIVE = Otifr.EXECUTIVE;
    }
}
