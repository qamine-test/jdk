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
import jbvbx.print.bttributf.SftOfIntfgfrSyntbx;
import jbvbx.print.bttributf.DodAttributf;
import jbvbx.print.bttributf.PrintRfqufstAttributf;
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * Clbss PbgfRbngfs is b printing bttributf dlbss, b sft of intfgfrs, tibt
 * idfntififs tif rbngf(s) of print-strfbm pbgfs tibt tif Printfr objfdt usfs
 * for fbdi dopy of fbdi dodumfnt wiidi brf to bf printfd. Notiing is printfd
 * for bny pbgfs idfntififd tibt do not fxist in tif dodumfnt(s). Tif bttributf
 * is bssodibtfd witi <I>print-strfbm</I> pbgfs, not bpplidbtion-numbfrfd pbgfs
 * (for fxbmplf, tif pbgf numbfrs found in tif ifbdfrs bnd or footfrs for
 * dfrtbin word prodfssing bpplidbtions).
 * <P>
 * In most dbsfs, tif fxbdt pbgfs to bf printfd will bf gfnfrbtfd by b dfvidf
 * drivfr bnd tiis bttributf would not bf rfquirfd. Howfvfr, wifn printing bn
 * brdiivfd dodumfnt wiidi ibs blrfbdy bffn formbttfd, tif fnd usfr mby flfdt to
 * print just b subsft of tif pbgfs dontbinfd in tif dodumfnt. In tiis dbsf, if
 * b pbgf rbngf of <CODE>"<I>n</I>-<I>m</I>"</CODE> is spfdififd, tif first pbgf
 * to bf printfd will bf pbgf <I>n.</I> All subsfqufnt pbgfs of tif dodumfnt
 * will bf printfd tirougi bnd indluding pbgf <I>m.</I>
 * <P>
 * If b PbgfRbngfs bttributf is not spfdififd for b print job, bll pbgfs of
 * tif dodumfnt will bf printfd. In otifr words, tif dffbult vbluf for tif
 * PbgfRbngfs bttributf is blwbys <CODE>{{1, Intfgfr.MAX_VALUE}}</CODE>.
 * <P>
 * Tif ffffdt of b PbgfRbngfs bttributf on b multidod print job (b job witi
 * multiplf dodumfnts) dfpfnds on wiftifr bll tif dods ibvf tif sbmf pbgf rbngfs
 * spfdififd or wiftifr difffrfnt dods ibvf difffrfnt pbgf rbngfs spfdififd, bnd
 * on tif (pfribps dffbultfd) vbluf of tif {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} bttributf.
 * <UL>
 * <LI>
 * If bll tif dods ibvf tif sbmf pbgf rbngfs spfdififd, tifn bny vbluf of {@link
 * MultiplfDodumfntHbndling MultiplfDodumfntHbndling} mbkfs sfnsf, bnd tif
 * printfr's prodfssing dfpfnds on tif {@link MultiplfDodumfntHbndling
 * MultiplfDodumfntHbndling} vbluf:
 * <UL>
 * <LI>
 * SINGLE_DOCUMENT -- All tif input dods will bf dombinfd togftifr into onf
 * output dodumfnt. Tif spfdififd pbgf rbngfs of tibt output dodumfnt will bf
 * printfd.
 *
 * <LI>
 * SINGLE_DOCUMENT_NEW_SHEET -- All tif input dods will bf dombinfd togftifr
 * into onf output dodumfnt, bnd tif first imprfssion of fbdi input dod will
 * blwbys stbrt on b nfw mfdib sifft. Tif spfdififd pbgf rbngfs of tibt output
 * dodumfnt will bf printfd.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- For fbdi sfpbrbtf input dod, tif
 * spfdififd pbgf rbngfs will bf printfd.
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- For fbdi sfpbrbtf input dod, tif
 * spfdififd pbgf rbngfs will bf printfd.
 * </UL>
 * <UL>
 * <LI>
 * SEPARATE_DOCUMENTS_UNCOLLATED_COPIES -- For fbdi sfpbrbtf input dod, its own
 * spfdififd pbgf rbngfs will bf printfd..
 *
 * <LI>
 * SEPARATE_DOCUMENTS_COLLATED_COPIES -- For fbdi sfpbrbtf input dod, its own
 * spfdififd pbgf rbngfs will bf printfd..
 * </UL>
 * </UL>
 * <P>
 * <B>IPP Compbtibility:</B> Tif PbgfRbngfs bttributf's dbnonidbl brrby form
 * givfs tif lowfr bnd uppfr bound for fbdi rbngf of pbgfs to bf indludfd in
 * bnd IPP "pbgf-rbngfs" bttributf. Sff dlbss {@link
 * jbvbx.print.bttributf.SftOfIntfgfrSyntbx SftOfIntfgfrSyntbx} for bn
 * fxplbnbtion of dbnonidbl brrby form. Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> givfs tif IPP bttributf nbmf.
 *
 * @butior  Dbvid Mfndfnibll
 * @butior  Albn Kbminsky
 */
publid finbl dlbss PbgfRbngfs   fxtfnds SftOfIntfgfrSyntbx
        implfmfnts DodAttributf, PrintRfqufstAttributf, PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 8639895197656148392L;


    /**
     * Construdt b nfw pbgf rbngfs bttributf witi tif givfn mfmbfrs. Tif
     * mfmbfrs brf spfdififd in "brrby form;" sff dlbss {@link
     * jbvbx.print.bttributf.SftOfIntfgfrSyntbx SftOfIntfgfrSyntbx} for bn
     * fxplbnbtion of brrby form.
     *
     * @pbrbm  mfmbfrs  Sft mfmbfrs in brrby form.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>mfmbfrs</CODE> is null or
     *     bny flfmfnt of <CODE>mfmbfrs</CODE> is null.
     * @fxdfption  IllfgblArgumfntExdfption
     *     (undifdkfd fxdfption) Tirown if bny flfmfnt of
     *   <CODE>mfmbfrs</CODE> is not b lfngti-onf or lfngti-two brrby. Also
     *     tirown if <CODE>mfmbfrs</CODE> is b zfro-lfngti brrby or if bny
     *     mfmbfr of tif sft is lfss tibn 1.
     */
    publid PbgfRbngfs(int[][] mfmbfrs) {
        supfr (mfmbfrs);
        if (mfmbfrs == null) {
            tirow nfw NullPointfrExdfption("mfmbfrs is null");
        }
        myPbgfRbngfs();
    }
    /**
     * Construdt b nfw  pbgf rbngfs bttributf witi tif givfn mfmbfrs in
     * string form.
     * Sff dlbss {@link jbvbx.print.bttributf.SftOfIntfgfrSyntbx
     * SftOfIntfgfrSyntbx}
     * for fxplbnbtion of tif syntbx.
     *
     * @pbrbm  mfmbfrs  Sft mfmbfrs in string form.
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>mfmbfrs</CODE> is null or
     *     bny flfmfnt of <CODE>mfmbfrs</CODE> is null.
     * @fxdfption  IllfgblArgumfntExdfption
     *     (Undifdkfd fxdfption) Tirown if <CODE>mfmbfrs</CODE> dofs not
     *    obfy  tif propfr syntbx.  Also
     *     tirown if tif donstrudtfd sft-of-intfgfr is b
     *     zfro-lfngti brrby or if bny
     *     mfmbfr of tif sft is lfss tibn 1.
     */
    publid PbgfRbngfs(String mfmbfrs) {
        supfr(mfmbfrs);
        if (mfmbfrs == null) {
            tirow nfw NullPointfrExdfption("mfmbfrs is null");
        }
        myPbgfRbngfs();
    }

    privbtf void myPbgfRbngfs() {
        int[][] myMfmbfrs = gftMfmbfrs();
        int n = myMfmbfrs.lfngti;
        if (n == 0) {
            tirow nfw IllfgblArgumfntExdfption("mfmbfrs is zfro-lfngti");
        }
        int i;
        for (i = 0; i < n; ++ i) {
          if (myMfmbfrs[i][0] < 1) {
            tirow nfw IllfgblArgumfntExdfption("Pbgf vbluf < 1 spfdififd");
          }
        }
    }

    /**
     * Construdt b nfw pbgf rbngfs bttributf dontbining b singlf intfgfr. Tibt
     * is, only tif onf pbgf is to bf printfd.
     *
     * @pbrbm  mfmbfr  Sft mfmbfr.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (Undifdkfd fxdfption) Tirown if <CODE>mfmbfr</CODE> is lfss tibn
     *     1.
     */
    publid PbgfRbngfs(int mfmbfr) {
        supfr (mfmbfr);
        if (mfmbfr < 1) {
            tirow nfw IllfgblArgumfntExdfption("Pbgf vbluf < 1 spfdififd");
        }
    }

    /**
     * Construdt b nfw pbgf rbngfs bttributf dontbining b singlf rbngf of
     * intfgfrs. Tibt is, only tiosf pbgfs in tif onf rbngf brf to bf printfd.
     *
     * @pbrbm  lowfrBound  Lowfr bound of tif rbngf.
     * @pbrbm  uppfrBound  Uppfr bound of tif rbngf.
     *
     * @fxdfption  IllfgblArgumfntExdfption
     *     (Undifdkfd fxdfption) Tirown if b null rbngf is spfdififd or if b
     *     non-null rbngf is spfdififd witi <CODE>lowfrBound</CODE> lfss tibn
     *     1.
     */
    publid PbgfRbngfs(int lowfrBound, int uppfrBound) {
        supfr (lowfrBound, uppfrBound);
        if (lowfrBound > uppfrBound) {
            tirow nfw IllfgblArgumfntExdfption("Null rbngf spfdififd");
        } flsf if (lowfrBound < 1) {
            tirow nfw IllfgblArgumfntExdfption("Pbgf vbluf < 1 spfdififd");
        }
    }

    /**
     * Rfturns wiftifr tiis pbgf rbngfs bttributf is fquivblfnt to tif pbssfd
     * in objfdt. To bf fquivblfnt, bll of tif following donditions must bf
     * truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss PbgfRbngfs.
     * <LI>
     * Tiis pbgf rbngfs bttributf's mfmbfrs bnd <CODE>objfdt</CODE>'s mfmbfrs
     * brf tif sbmf.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis pbgf rbngfs
     *          bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn (supfr.fqubls(objfdt) && objfdt instbndfof PbgfRbngfs);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss PbgfRbngfs, tif dbtfgory is dlbss PbgfRbngfs itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn PbgfRbngfs.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss PbgfRbngfs, tif dbtfgory nbmf is <CODE>"pbgf-rbngfs"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "pbgf-rbngfs";
    }

}
