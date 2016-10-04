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

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.EnumSyntbx;
import jbvbx.print.bttributf.DodAttributf;
import jbvbx.print.bttributf.PrintRfqufstAttributf;
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * Clbss Finisiings is b printing bttributf dlbss, bn fnumfrbtion, tibt
 * idfntififs wiftifr tif printfr bpplifs b finisiing opfrbtion of somf kind
 * of binding to fbdi dopy of fbdi printfd dodumfnt in tif job. For multidod
 * print jobs (jobs witi multiplf dodumfnts), tif
 * {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} bttributf dftfrminfs wibt donstitutfs b "dopy"
 * for purposfs of finisiing.
 * <P>
 * Stbndbrd Finisiings vblufs brf:
 * <TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH=100% SUMMARY="lbyout">
 * <TR>
 * <TD STYLE="WIDTH:10%">
 * &nbsp;
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #NONE NONE}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE STAPLE}
 * </TD>
 * <TD STYLE="WIDTH:36%">
 * {@link #EDGE_STITCH EDGE_STITCH}
 * </TD>
 * </TR>
 * <TR>
 * <TD>
 * &nbsp;
 * </TD>
 * <TD>
 * {@link #BIND BIND}
 * </TD>
 * <TD>
 * {@link #SADDLE_STITCH SADDLE_STITCH}
 * </TD>
 * <TD>
 * {@link #COVER COVER}
 * </TD>
 * <TD>
 * &nbsp;
 * </TD>
 * </TR>
 * </TABLE>
 * <P>
 * Tif following Finisiings vblufs brf morf spfdifid; tify indidbtf b
 * dornfr or bn fdgf bs if tif dodumfnt wfrf b portrbit dodumfnt:
 * <TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH=100% SUMMARY="lbyout">
 * <TR>
 * <TD STYLE="WIDTH:10%">
 * &nbsp;
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_TOP_LEFT STAPLE_TOP_LEFT}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #EDGE_STITCH_LEFT EDGE_STITCH_LEFT}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_DUAL_LEFT STAPLE_DUAL_LEFT}
 * </TD>
 * <TD STYLE="WIDTH:9%">
 * &nbsp;
 * </TD>
 * </TR>
 * <TR>
 * <TD STYLE="WIDTH:10%">
 * &nbsp;
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_BOTTOM_LEFT STAPLE_BOTTOM_LEFT}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #EDGE_STITCH_TOP EDGE_STITCH_TOP}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_DUAL_TOP STAPLE_DUAL_TOP}
 * </TD>
 * <TD STYLE="WIDTH:9%">
 * &nbsp;
 * </TD>
 * </TR>
 * <TR>
 * <TD STYLE="WIDTH:10%">
 * &nbsp;
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_TOP_RIGHT STAPLE_TOP_RIGHT}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #EDGE_STITCH_RIGHT EDGE_STITCH_RIGHT}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_DUAL_RIGHT STAPLE_DUAL_RIGHT}
 * </TD>
 * <TD STYLE="WIDTH:9%">
 * &nbsp;
 * </TD>
 * </TR>
 * <TR>
 * <TD STYLE="WIDTH:10%">
 * &nbsp;
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_BOTTOM_RIGHT STAPLE_BOTTOM_RIGHT}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #EDGE_STITCH_BOTTOM EDGE_STITCH_BOTTOM}
 * </TD>
 * <TD STYLE="WIDTH:27%">
 * {@link #STAPLE_DUAL_BOTTOM STAPLE_DUAL_BOTTOM}
 * </TD>
 * <TD STYLE="WIDTH:9%">
 * &nbsp;
 * </TD>
 * </TR>
 * </TABLE>
 * <P>
 * Tif STAPLE_<I>XXX</I> vblufs brf spfdififd witi rfspfdt to tif
 * dodumfnt bs if tif dodumfnt wfrf b portrbit dodumfnt. If tif dodumfnt is
 * bdtublly b lbndsdbpf or b rfvfrsf-lbndsdbpf dodumfnt, tif dlifnt supplifs tif
 * bppropribtf trbnsformfd vbluf. For fxbmplf, to position b stbplf in tif uppfr
 * lfft ibnd dornfr of b lbndsdbpf dodumfnt wifn ifld for rfbding, tif dlifnt
 * supplifs tif STAPLE_BOTTOM_LEFT vbluf (sindf lbndsdbpf is
 * dffinfd bs b +90 dfgrff rotbtion from portrbit, i.f., bnti-dlodkwisf). On tif
 * otifr ibnd, to position b stbplf in tif uppfr lfft ibnd dornfr of b
 * rfvfrsf-lbndsdbpf dodumfnt wifn ifld for rfbding, tif dlifnt supplifs tif
 * STAPLE_TOP_RIGHT vbluf (sindf rfvfrsf-lbndsdbpf is dffinfd bs b
 * -90 dfgrff rotbtion from portrbit, i.f., dlodkwisf).
 * <P>
 * Tif bnglf (vfrtidbl, iorizontbl, bnglfd) of fbdi stbplf witi rfspfdt to tif
 * dodumfnt dfpfnds on tif implfmfntbtion wiidi mby in turn dfpfnd on tif vbluf
 * of tif bttributf.
 * <P>
 * Tif ffffdt of b Finisiings bttributf on b multidod print job (b job
 * witi multiplf dodumfnts) dfpfnds on wiftifr bll tif dods ibvf tif sbmf
 * binding spfdififd or wiftifr difffrfnt dods ibvf difffrfnt bindings
 * spfdififd, bnd on tif (pfribps dffbultfd) vbluf of tif {@link
 * MultiplfDodumfntHbndling MultiplfDodumfntHbndling} bttributf.
 * <UL>
 * <LI>
 * If bll tif dods ibvf tif sbmf binding spfdififd, tifn bny vbluf of {@link
 * MultiplfDodumfntHbndling MultiplfDodumfntHbndling} mbkfs sfnsf, bnd tif
 * printfr's prodfssing dfpfnds on tif {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} vbluf:
 * <UL>
 * <LI>
 * SINGLE_DOCUMENT -- All tif input dods will bf bound togftifr bs onf output
 * dodumfnt witi tif spfdififd binding.
 *
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET -- All tif input dods will bf bound togftifr bs onf
 * output dodumfnt witi tif spfdififd binding, bnd tif first imprfssion of fbdi
 * input dod will blwbys stbrt on b nfw mfdib sifft.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- Ebdi input dod will bf bound
 * sfpbrbtfly witi tif spfdififd binding.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- Ebdi input dod will bf bound sfpbrbtfly
 * witi tif spfdififd binding.
 * </UL>
 *
 * <LI>
 * If difffrfnt dods ibvf difffrfnt bindings spfdififd, tifn only two vblufs of
 * {@link MultiplfDodumfntHbndling MultiplfDodumfntHbndling} mbkf sfnsf, bnd tif
 * printfr rfports bn frror wifn tif job is submittfd if bny otifr vbluf is
 * spfdififd:
 * <UL>
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- Ebdi input dod will bf bound
 * sfpbrbtfly witi its own spfdififd binding.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- Ebdi input dod will bf bound sfpbrbtfly
 * witi its own spfdififd binding.
 * </UL>
 * </UL>
 * <P>
 * <B>IPP Compbtibility:</B> Clbss Finisiings fndbpsulbtfs somf of tif
 * IPP fnum vblufs tibt dbn bf indludfd in bn IPP "finisiings" bttributf, wiidi
 * is b sft of fnums. Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> is tif IPP bttributf nbmf.  Tif fnumfrbtion's
 * intfgfr vbluf is tif IPP fnum vbluf.  Tif <dodf>toString()</dodf> mftiod
 * rfturns tif IPP string rfprfsfntbtion of tif bttributf vbluf.
 * In IPP Finisiings is b multi-vbluf bttributf, tiis API durrfntly bllows
 * only onf binding to bf spfdififd.
 *
 * @butior  Albn Kbminsky
 */
publid dlbss Finisiings fxtfnds EnumSyntbx
    implfmfnts DodAttributf, PrintRfqufstAttributf, PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -627840419548391754L;

    /**
     * Pfrform no binding.
     */
    publid stbtid finbl Finisiings NONE = nfw Finisiings(3);

    /**
     * Bind tif dodumfnt(s) witi onf or morf stbplfs. Tif fxbdt numbfr bnd
     * plbdfmfnt of tif stbplfs is sitf-dffinfd.
     */
    publid stbtid finbl Finisiings STAPLE = nfw Finisiings(4);

    /**
     * Tiis vbluf is spfdififd wifn it is dfsirfd to sflfdt b non-printfd (or
     * prf-printfd) dovfr for tif dodumfnt. Tiis dofs not supplbnt tif
     * spfdifidbtion of b printfd dovfr (on dovfr stodk mfdium) by tif
     * dodumfnt  itsflf.
     */
    publid stbtid finbl Finisiings COVER = nfw Finisiings(6);

    /**
     * Tiis vbluf indidbtfs tibt b binding is to bf bpplifd to tif dodumfnt;
     * tif typf bnd plbdfmfnt of tif binding is sitf-dffinfd.
     */
    publid stbtid finbl Finisiings BIND = nfw Finisiings(7);

    /**
     * Bind tif dodumfnt(s) witi onf or morf stbplfs (wirf stitdifs) blong tif
     * middlf fold. Tif fxbdt numbfr bnd plbdfmfnt of tif stbplfs bnd tif
     * middlf fold is implfmfntbtion- bnd/or sitf-dffinfd.
     */
    publid stbtid finbl Finisiings SADDLE_STITCH =
        nfw Finisiings(8);

    /**
     * Bind tif dodumfnt(s) witi onf or morf stbplfs (wirf stitdifs) blong onf
     * fdgf. Tif fxbdt numbfr bnd plbdfmfnt of tif stbplfs is implfmfntbtion-
     * bnd/or sitf- dffinfd.
     */
    publid stbtid finbl Finisiings EDGE_STITCH =
        nfw Finisiings(9);

    /**
     * Bind tif dodumfnt(s) witi onf or morf stbplfs in tif top lfft dornfr.
     */
    publid stbtid finbl Finisiings STAPLE_TOP_LEFT =
        nfw Finisiings(20);

    /**
     * Bind tif dodumfnt(s) witi onf or morf stbplfs in tif bottom lfft
     * dornfr.
     */
    publid stbtid finbl Finisiings STAPLE_BOTTOM_LEFT =
        nfw Finisiings(21);

    /**
     * Bind tif dodumfnt(s) witi onf or morf stbplfs in tif top rigit dornfr.
     */
    publid stbtid finbl Finisiings STAPLE_TOP_RIGHT =
        nfw Finisiings(22);

    /**
     * Bind tif dodumfnt(s) witi onf or morf stbplfs in tif bottom rigit
     * dornfr.
     */
    publid stbtid finbl Finisiings STAPLE_BOTTOM_RIGHT =
        nfw Finisiings(23);

    /**
     * Bind tif dodumfnt(s) witi onf or morf stbplfs (wirf stitdifs) blong tif
     * lfft fdgf. Tif fxbdt numbfr bnd plbdfmfnt of tif stbplfs is
     * implfmfntbtion- bnd/or sitf-dffinfd.
     */
    publid stbtid finbl Finisiings EDGE_STITCH_LEFT =
        nfw Finisiings(24);

    /**
     * Bind tif dodumfnt(s) witi onf or morf stbplfs (wirf stitdifs) blong tif
     * top fdgf. Tif fxbdt numbfr bnd plbdfmfnt of tif stbplfs is
     * implfmfntbtion- bnd/or sitf-dffinfd.
     */
    publid stbtid finbl Finisiings EDGE_STITCH_TOP =
        nfw Finisiings(25);

    /**
     * Bind tif dodumfnt(s) witi onf or morf stbplfs (wirf stitdifs) blong tif
     * rigit fdgf. Tif fxbdt numbfr bnd plbdfmfnt of tif stbplfs is
     * implfmfntbtion- bnd/or sitf-dffinfd.
     */
    publid stbtid finbl Finisiings EDGE_STITCH_RIGHT =
        nfw Finisiings(26);

    /**
     * Bind tif dodumfnt(s) witi onf or morf stbplfs (wirf stitdifs) blong tif
     * bottom fdgf. Tif fxbdt numbfr bnd plbdfmfnt of tif stbplfs is
     * implfmfntbtion- bnd/or sitf-dffinfd.
     */
    publid stbtid finbl Finisiings EDGE_STITCH_BOTTOM =
        nfw Finisiings(27);

    /**
     * Bind tif dodumfnt(s) witi two stbplfs (wirf stitdifs) blong tif lfft
     * fdgf bssuming b portrbit dodumfnt (sff bbovf).
     */
    publid stbtid finbl Finisiings STAPLE_DUAL_LEFT =
        nfw Finisiings(28);

    /**
     * Bind tif dodumfnt(s) witi two stbplfs (wirf stitdifs) blong tif top
     * fdgf bssuming b portrbit dodumfnt (sff bbovf).
     */
    publid stbtid finbl Finisiings STAPLE_DUAL_TOP =
        nfw Finisiings(29);

    /**
     * Bind tif dodumfnt(s) witi two stbplfs (wirf stitdifs) blong tif rigit
     * fdgf bssuming b portrbit dodumfnt (sff bbovf).
     */
    publid stbtid finbl Finisiings STAPLE_DUAL_RIGHT =
        nfw Finisiings(30);

    /**
     * Bind tif dodumfnt(s) witi two stbplfs (wirf stitdifs) blong tif bottom
     * fdgf bssuming b portrbit dodumfnt (sff bbovf).
     */
    publid stbtid finbl Finisiings STAPLE_DUAL_BOTTOM =
        nfw Finisiings(31);

    /**
     * Construdt b nfw finisiings binding fnumfrbtion vbluf witi tif givfn
     * intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd Finisiings(int vbluf) {
        supfr(vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf =
                {"nonf",
                 "stbplf",
                 null,
                 "dovfr",
                 "bind",
                 "sbddlf-stitdi",
                 "fdgf-stitdi",
                 null, // Tif nfxt tfn fnum vblufs brf rfsfrvfd.
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 "stbplf-top-lfft",
                 "stbplf-bottom-lfft",
                 "stbplf-top-rigit",
                 "stbplf-bottom-rigit",
                 "fdgf-stitdi-lfft",
                 "fdgf-stitdi-top",
                 "fdgf-stitdi-rigit",
                 "fdgf-stitdi-bottom",
                 "stbplf-dubl-lfft",
                 "stbplf-dubl-top",
                 "stbplf-dubl-rigit",
                 "stbplf-dubl-bottom"
                };

    privbtf stbtid finbl Finisiings[] myEnumVblufTbblf =
                {NONE,
                 STAPLE,
                 null,
                 COVER,
                 BIND,
                 SADDLE_STITCH,
                 EDGE_STITCH,
                 null, // Tif nfxt tfn fnum vblufs brf rfsfrvfd.
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 null,
                 STAPLE_TOP_LEFT,
                 STAPLE_BOTTOM_LEFT,
                 STAPLE_TOP_RIGHT,
                 STAPLE_BOTTOM_RIGHT,
                 EDGE_STITCH_LEFT,
                 EDGE_STITCH_TOP,
                 EDGE_STITCH_RIGHT,
                 EDGE_STITCH_BOTTOM,
                 STAPLE_DUAL_LEFT,
                 STAPLE_DUAL_TOP,
                 STAPLE_DUAL_RIGHT,
                 STAPLE_DUAL_BOTTOM
                };

    /**
     * Rfturns tif string tbblf for dlbss Finisiings.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf.dlonf();
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss Finisiings.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn (EnumSyntbx[])myEnumVblufTbblf.dlonf();
    }

    /**
     * Rfturns tif lowfst intfgfr vbluf usfd by dlbss Finisiings.
     */
    protfdtfd int gftOffsft() {
        rfturn 3;
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss Finisiings bnd bny vfndor-dffinfd subdlbssfs, tif
     * dbtfgory is dlbss Finisiings itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn Finisiings.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss Finisiings bnd bny vfndor-dffinfd subdlbssfs, tif
     * dbtfgory nbmf is <CODE>"finisiings"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "finisiings";
    }

}
