/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Clbss Sidfs is b printing bttributf dlbss, bn fnumfrbtion, tibt spfdififs
 * iow print-strfbm pbgfs brf to bf imposfd upon tif sidfs of bn instbndf of b
 * sflfdtfd mfdium, i.f., bn imprfssion.
 * <P>
 * Tif ffffdt of b Sidfs bttributf on b multidod print job (b job witi multiplf
 * dodumfnts) dfpfnds on wiftifr bll tif dods ibvf tif sbmf sidfs vblufs
 * spfdififd or wiftifr difffrfnt dods ibvf difffrfnt sidfs vblufs spfdififd,
 * bnd on tif (pfribps dffbultfd) vbluf of tif {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} bttributf.
 * <UL>
 * <LI>
 * If bll tif dods ibvf tif sbmf sidfs vbluf <I>n</I> spfdififd, tifn bny vbluf
 * of {@link MultiplfDodumfntHbndling MultiplfDodumfntHbndling} mbkfs sfnsf,
 * bnd tif printfr's prodfssing dfpfnds on tif {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} vbluf:
 * <UL>
 * <LI>
 * SINGLE_DOCUMENT -- All tif input dods will bf dombinfd togftifr into onf
 * output dodumfnt. Ebdi mfdib sifft will donsist of <I>n</I> imprfssions from
 * tif output dodumfnt.
 *
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET -- All tif input dods will bf dombinfd togftifr
 * into onf output dodumfnt. Ebdi mfdib sifft will donsist of <I>n</I>
 * imprfssions from tif output dodumfnt. Howfvfr, tif first imprfssion of fbdi
 * input dod will blwbys stbrt on b nfw mfdib sifft; tiis mfbns tif lbst mfdib
 * sifft of bn input dod mby ibvf only onf imprfssion on it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- Tif input dods will rfmbin sfpbrbtf.
 * Ebdi mfdib sifft will donsist of <I>n</I> imprfssions from tif input dod.
 * Sindf tif input dods brf sfpbrbtf, tif first imprfssion of fbdi input dod
 * will blwbys stbrt on b nfw mfdib sifft; tiis mfbns tif lbst mfdib sifft of
 * bn input dod mby ibvf only onf imprfssion on it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- Tif input dods will rfmbin sfpbrbtf.
 * Ebdi mfdib sifft will donsist of <I>n</I> imprfssions from tif input dod.
 * Sindf tif input dods brf sfpbrbtf, tif first imprfssion of fbdi input dod
 * will blwbys stbrt on b nfw mfdib sifft; tiis mfbns tif lbst mfdib sifft of
 * bn input dod mby ibvf only onf imprfssion on it.
 * </UL>
 *
 * <UL>
 * <LI>
 * SINGLE_DOCUMENT -- All tif input dods will bf dombinfd togftifr into onf
 * output dodumfnt. Ebdi mfdib sifft will donsist of <I>n<SUB>i</SUB></I>
 * imprfssions from tif output dodumfnt, wifrf <I>i</I> is tif numbfr of tif
 * input dod dorrfsponding to tibt point in tif output dodumfnt. Wifn tif nfxt
 * input dod ibs b difffrfnt sidfs vbluf from tif prfvious input dod, tif first
 * print-strfbm pbgf of tif nfxt input dod gofs bt tif stbrt of tif nfxt mfdib
 * sifft, possibly lfbving only onf imprfssion on tif prfvious mfdib sifft.
 *
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET -- All tif input dods will bf dombinfd togftifr
 * into onf output dodumfnt. Ebdi mfdib sifft will donsist of <I>n</I>
 * imprfssions from tif output dodumfnt. Howfvfr, tif first imprfssion of fbdi
 * input dod will blwbys stbrt on b nfw mfdib sifft; tiis mfbns tif lbst
 * imprfssion of bn input dod mby ibvf only onf imprfssion on it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- Tif input dods will rfmbin sfpbrbtf.
 * For input dod <I>i,</I> fbdi mfdib sifft will donsist of <I>n<SUB>i</SUB></I>
 * imprfssions from tif input dod. Sindf tif input dods brf sfpbrbtf, tif first
 * imprfssion of fbdi input dod will blwbys stbrt on b nfw mfdib sifft; tiis
 * mfbns tif lbst mfdib sifft of bn input dod mby ibvf only onf imprfssion on
 * it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- Tif input dods will rfmbin sfpbrbtf.
 * For input dod <I>i,</I> fbdi mfdib sifft will donsist of <I>n<SUB>i</SUB></I>
 * imprfssions from tif input dod. Sindf tif input dods brf sfpbrbtf, tif first
 * imprfssion of fbdi input dod will blwbys stbrt on b nfw mfdib sifft; tiis
 * mfbns tif lbst mfdib sifft of bn input dod mby ibvf only onf imprfssion on
 * it.
 * </UL>
 * </UL>
 * <P>
 * <B>IPP Compbtibility:</B> Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> is tif IPP bttributf nbmf.  Tif fnumfrbtion's
 * intfgfr vbluf is tif IPP fnum vbluf.  Tif <dodf>toString()</dodf> mftiod
 * rfturns tif IPP string rfprfsfntbtion of tif bttributf vbluf.
 *
 * @butior  Albn Kbminsky
 */

publid finbl dlbss Sidfs fxtfnds EnumSyntbx
    implfmfnts DodAttributf, PrintRfqufstAttributf, PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -6890309414893262822L;

    /**
     * Imposfs fbdi donsfdutivf print-strfbm pbgf upon tif sbmf sidf of
     * donsfdutivf mfdib siffts.
     */
    publid stbtid finbl Sidfs ONE_SIDED = nfw Sidfs(0);

    /**
     * Imposfs fbdi donsfdutivf pbir of print-strfbm pbgfs upon front bnd bbdk
     * sidfs of donsfdutivf mfdib siffts, sudi tibt tif orifntbtion of fbdi
     * pbir of print-strfbm pbgfs on tif mfdium would bf dorrfdt for tif
     * rfbdfr bs if for binding on tif long fdgf. Tiis imposition is blso
     * known bs "duplfx" (sff {@link #DUPLEX DUPLEX}).
     */
    publid stbtid finbl Sidfs TWO_SIDED_LONG_EDGE = nfw Sidfs(1);

    /**
     * Imposfs fbdi donsfdutivf pbir of print-strfbm pbgfs upon front bnd bbdk
     * sidfs of donsfdutivf mfdib siffts, sudi tibt tif orifntbtion of fbdi
     * pbir of print-strfbm pbgfs on tif mfdium would bf dorrfdt for tif
     * rfbdfr bs if for binding on tif siort fdgf. Tiis imposition is blso
     * known bs "tumblf" (sff {@link #TUMBLE TUMBLE}).
     */
    publid stbtid finbl Sidfs TWO_SIDED_SHORT_EDGE = nfw Sidfs(2);

    /**
     * An blibs for "two sidfd long fdgf" (sff {@link #TWO_SIDED_LONG_EDGE
     * TWO_SIDED_LONG_EDGE}).
     */
    publid stbtid finbl Sidfs DUPLEX = TWO_SIDED_LONG_EDGE;

    /**
     * An blibs for "two sidfd siort fdgf" (sff {@link #TWO_SIDED_SHORT_EDGE
     * TWO_SIDED_SHORT_EDGE}).
     */
    publid stbtid finbl Sidfs TUMBLE = TWO_SIDED_SHORT_EDGE;

    /**
     * Construdt b nfw sidfs fnumfrbtion vbluf witi tif givfn intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd Sidfs(int vbluf) {
        supfr (vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf = {
        "onf-sidfd",
        "two-sidfd-long-fdgf",
        "two-sidfd-siort-fdgf"
    };

    privbtf stbtid finbl Sidfs[] myEnumVblufTbblf = {
        ONE_SIDED,
        TWO_SIDED_LONG_EDGE,
        TWO_SIDED_SHORT_EDGE
    };

    /**
     * Rfturns tif string tbblf for dlbss Sidfs.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf;
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss Sidfs.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn myEnumVblufTbblf;
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss Sidfs, tif dbtfgory is dlbss Sidfs itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn Sidfs.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss Sidfs, tif dbtfgory nbmf is <CODE>"sidfs"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "sidfs";
    }

}
