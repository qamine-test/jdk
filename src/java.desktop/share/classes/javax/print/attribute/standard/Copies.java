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
import jbvbx.print.bttributf.PrintRfqufstAttributf;
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * Clbss Copifs is bn intfgfr vblufd printing bttributf dlbss tibt spfdififs tif
 * numbfr of dopifs to bf printfd.
 * <P>
 * On mbny dfvidfs tif supportfd numbfr of dollbtfd dopifs will bf limitfd by
 * tif numbfr of piysidbl output bins on tif dfvidf, bnd mby bf difffrfnt from
 * tif numbfr of undollbtfd dopifs wiidi dbn bf supportfd.
 * <P>
 * Tif ffffdt of b Copifs bttributf witi b vbluf of <I>n</I> on b multidod print
 * job (b job witi multiplf dodumfnts) dfpfnds on tif (pfribps dffbultfd) vbluf
 * of tif {@link MultiplfDodumfntHbndling MultiplfDodumfntHbndling} bttributf:
 * <UL>
 * <LI>
 * SINGLE_DOCUMENT -- Tif rfsult will bf <I>n</I> dopifs of b singlf output
 * dodumfnt domprising bll tif input dods.
 *
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET -- Tif rfsult will bf <I>n</I> dopifs of b singlf
 * output dodumfnt domprising bll tif input dods, bnd tif first imprfssion of
 * fbdi input dod will blwbys stbrt on b nfw mfdib sifft.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- Tif rfsult will bf <I>n</I> dopifs of
 * tif first input dodumfnt, followfd by <I>n</I> dopifs of tif sfdond input
 * dodumfnt, . . . followfd by <I>n</I> dopifs of tif lbst input dodumfnt.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- Tif rfsult will bf tif first input
 * dodumfnt, tif sfdond input dodumfnt, . . . tif lbst input dodumfnt, tif group
 * of dodumfnts bfing rfpfbtfd <I>n</I> timfs.
 * </UL>
 * <P>
 * <B>IPP Compbtibility:</B> Tif intfgfr vbluf givfs tif IPP intfgfr vbluf. Tif
 * dbtfgory nbmf rfturnfd by <CODE>gftNbmf()</CODE> givfs tif IPP bttributf
 * nbmf.
 *
 * @butior  Dbvid Mfndfnibll
 * @butior  Albn Kbmiifnsky
 */
publid finbl dlbss Copifs fxtfnds IntfgfrSyntbx
        implfmfnts PrintRfqufstAttributf, PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -6426631521680023833L;

    /**
     * Construdt b nfw dopifs bttributf witi tif givfn intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *  (Undifdkfd fxdfption) Tirown if <CODE>vbluf</CODE> is lfss tibn 1.
     */
    publid Copifs(int vbluf) {
        supfr (vbluf, 1, Intfgfr.MAX_VALUE);
    }

    /**
     * Rfturns wiftifr tiis dopifs bttributf is fquivblfnt to tif pbssfd in
     * objfdt. To bf fquivblfnt, bll of tif following donditions must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss Copifs.
     * <LI>
     * Tiis dopifs bttributf's vbluf bnd <CODE>objfdt</CODE>'s vbluf brf
     * fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis dopifs
     *          bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn supfr.fqubls (objfdt) && objfdt instbndfof Copifs;
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss Copifs, tif dbtfgory is dlbss Copifs itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn Copifs.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss Copifs, tif dbtfgory nbmf is <CODE>"dopifs"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "dopifs";
    }

}
