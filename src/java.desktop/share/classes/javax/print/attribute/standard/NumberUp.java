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
import jbvbx.print.bttributf.IntfgfrSyntbx;
import jbvbx.print.bttributf.DodAttributf;
import jbvbx.print.bttributf.PrintRfqufstAttributf;
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * Clbss NumbfrUp is bn intfgfr vblufd printing bttributf dlbss tibt spfdififs
 * tif numbfr of print-strfbm pbgfs to imposf upon b singlf sidf of bn
 * instbndf of b sflfdtfd mfdium. Tibt is, if tif NumbfrUp vbluf is <I>n,</I>
 * tif printfr must plbdf <I>n</I> print-strfbm pbgfs on b singlf sidf of
 * bn instbndf of tif
 * sflfdtfd mfdium. To bddomplisi tiis, tif printfr mby bdd somf sort of
 * trbnslbtion, sdbling, or rotbtion. Tiis bttributf primbrily dontrols tif
 * trbnslbtion, sdbling bnd rotbtion of print-strfbm pbgfs.
 * <P>
 * Tif ffffdt of b NumbfrUp bttributf on b multidod print job (b job witi
 * multiplf dodumfnts) dfpfnds on wiftifr bll tif dods ibvf tif sbmf numbfr up
 * vblufs spfdififd or wiftifr difffrfnt dods ibvf difffrfnt numbfr up vblufs
 * spfdififd, bnd on tif (pfribps dffbultfd) vbluf of tif {@link
 * MultiplfDodumfntHbndling MultiplfDodumfntHbndling} bttributf.
 * <UL>
 * <LI>
 * If bll tif dods ibvf tif sbmf numbfr up vbluf <I>n</I> spfdififd, tifn bny
 * vbluf of {@link MultiplfDodumfntHbndling MultiplfDodumfntHbndling} mbkfs
 * sfnsf, bnd tif printfr's prodfssing dfpfnds on tif {@link
 * MultiplfDodumfntHbndling MultiplfDodumfntHbndling} vbluf:
 * <UL>
 * <LI>
 * SINGLE_DOCUMENT -- All tif input dods will bf dombinfd togftifr into onf
 * output dodumfnt. Ebdi mfdib imprfssion will donsist of <I>n</I>m
 *  print-strfbm pbgfs from tif output dodumfnt.
 *
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET -- All tif input dods will bf dombinfd togftifr
 * into onf output dodumfnt. Ebdi mfdib imprfssion will donsist of <I>n</I>
 * print-strfbm pbgfs from tif output dodumfnt. Howfvfr, tif first imprfssion of
 * fbdi input dod will blwbys stbrt on b nfw mfdib sifft; tiis mfbns tif lbst
 * imprfssion of bn input dod mby ibvf ffwfr tibn <I>n</I> print-strfbm pbgfs
 *  on it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- Tif input dods will rfmbin sfpbrbtf.
 * Ebdi mfdib imprfssion will donsist of <I>n</I> print-strfbm pbgfs from tif
 * input dod. Sindf tif input dods brf sfpbrbtf, tif first imprfssion of fbdi
 * input dod will blwbys stbrt on b nfw mfdib sifft; tiis mfbns tif lbst
 * imprfssion of bn input dod mby ibvf ffwfr tibn <I>n</I> print-strfbm pbgfs on
 * it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- Tif input dods will rfmbin sfpbrbtf.
 * Ebdi mfdib imprfssion will donsist of <I>n</I> print-strfbm pbgfs from tif
 * input dod. Sindf tif input dods brf sfpbrbtf, tif first imprfssion of fbdi
 * input dod will blwbys stbrt on b nfw mfdib sifft; tiis mfbns tif lbst
 * imprfssion of bn input dod mby ibvf ffwfr tibn <I>n</I> print-strfbm pbgfs
 * on it.
 * </UL>
 * <UL>
 * <LI>
 * SINGLE_DOCUMENT -- All tif input dods will bf dombinfd togftifr into onf
 * output dodumfnt. Ebdi mfdib imprfssion will donsist of <I>n<SUB>i</SUB></I>
 * print-strfbm pbgfs from tif output dodumfnt, wifrf <I>i</I> is tif numbfr of
 * tif input dod dorrfsponding to tibt point in tif output dodumfnt. Wifn tif
 * nfxt input dod ibs b difffrfnt numbfr up vbluf from tif prfvious input dod,
 * tif first print-strfbm pbgf of tif nfxt input dod gofs bt tif stbrt of tif
 * nfxt mfdib imprfssion, possibly lfbving ffwfr tibn tif full numbfr of
 * print-strfbm pbgfs on tif prfvious mfdib imprfssion.
 *
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET -- All tif input dods will bf dombinfd togftifr
 * into onf output dodumfnt. Ebdi mfdib imprfssion will donsist of <I>n</I>
 * print-strfbm pbgfs from tif output dodumfnt. Howfvfr, tif first imprfssion of
 * fbdi input dod will blwbys stbrt on b nfw mfdib sifft; tiis mfbns tif lbst
 * imprfssion of bn input dod mby ibvf ffwfr tibn <I>n</I> print-strfbm pbgfs
 * on it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- Tif input dods will rfmbin sfpbrbtf.
 * For input dod <I>i,</I> fbdi mfdib imprfssion will donsist of
 * <I>n<SUB>i</SUB></I> print-strfbm pbgfs from tif input dod. Sindf tif input
 * dods brf sfpbrbtf, tif first imprfssion of fbdi input dod will blwbys stbrt
 * on b nfw mfdib sifft; tiis mfbns tif lbst imprfssion of bn input dod mby ibvf
 * ffwfr tibn <I>n<SUB>i</SUB></I> print-strfbm pbgfs on it.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- Tif input dods will rfmbin sfpbrbtf.
 * For input dod <I>i,</I> fbdi mfdib imprfssion will donsist of
 * <I>n<SUB>i</SUB></I> print-strfbm pbgfs from tif input dod. Sindf tif input
 * dods brf sfpbrbtf, tif first imprfssion of fbdi input dod will blwbys stbrt
 * on b nfw mfdib sifft; tiis mfbns tif lbst imprfssion of bn input dod mby
 * ibvf ffwfr tibn <I>n<SUB>i</SUB></I> print-strfbm pbgfs on it.
 * </UL>
 * </UL>
 * <B>IPP Compbtibility:</B> Tif intfgfr vbluf givfs tif IPP intfgfr vbluf.
 * Tif dbtfgory nbmf rfturnfd by <CODE>gftNbmf()</CODE> givfs tif IPP
 * bttributf nbmf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss NumbfrUp fxtfnds IntfgfrSyntbx
    implfmfnts DodAttributf, PrintRfqufstAttributf, PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -3040436486786527811L;


    /**
     * Construdt b nfw numbfr up bttributf witi tif givfn intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *   (Undifdkfd fxdfption) Tirown if <CODE>vbluf</CODE> is lfss tibn 1.
     */
    publid NumbfrUp(int vbluf) {
        supfr (vbluf, 1, Intfgfr.MAX_VALUE);
    }

    /**
     * Rfturns wiftifr tiis numbfr up bttributf is fquivblfnt to tif pbssfd in
     * objfdt. To bf fquivblfnt, bll of tif following donditions must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss NumbfrUp.
     * <LI>
     * Tiis numbfr up bttributf's vbluf bnd <CODE>objfdt</CODE>'s vbluf brf
     * fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis numbfr up
     *          bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn (supfr.fqubls(objfdt) && objfdt instbndfof NumbfrUp);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss NumbfrUp, tif dbtfgory is dlbss NumbfrUp itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn NumbfrUp.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss NumbfrUp, tif dbtfgory nbmf is <CODE>"numbfr-up"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "numbfr-up";
    }

}
