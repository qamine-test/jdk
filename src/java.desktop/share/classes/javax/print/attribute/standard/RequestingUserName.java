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

import jbvb.util.Lodblf;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.TfxtSyntbx;
import jbvbx.print.bttributf.PrintRfqufstAttributf;

/**
 * Clbss RfqufstingUsfrNbmf is b printing bttributf dlbss, b tfxt bttributf,
 * tibt spfdififs tif nbmf of tif fnd usfr tibt submittfd tif print job. A
 * rfqufsting usfr nbmf is bn brbitrbry string dffinfd by tif dlifnt. Tif
 * printfr dofs not put tif dlifnt-spfdififd RfqufstingUsfrNbmf bttributf into
 * tif Print Job's bttributf sft; rbtifr, tif printfr puts in b {@link
 * JobOriginbtingUsfrNbmf JobOriginbtingUsfrNbmf} bttributf.
 * Tiis mfbns tibt sfrvidfs wiidi support spfdifying b usfrnbmf witi tiis
 * bttributf siould blso rfport b JobOriginbtingUsfrNbmf in tif job's
 * bttributf sft. Notf tibt mbny print sfrvidfs mby ibvf b wby to indfpfndfntly
 * butifntidbtf tif usfr nbmf, bnd so mby stbtf support for b
 * rfqufsting usfr nbmf, but in prbdtidf will tifn rfport tif usfr nbmf
 * butifntidbtfd by tif sfrvidf rbtifr tibn tibt spfdififd vib tiis
 * bttributf.
 * <P>
 * <B>IPP Compbtibility:</B> Tif string vbluf givfs tif IPP nbmf vbluf. Tif
 * lodblf givfs tif IPP nbturbl lbngubgf. Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> givfs tif IPP bttributf nbmf.
 *
 * @butior  Albn Kbminsky
 */
publid finbl dlbss RfqufstingUsfrNbmf   fxtfnds TfxtSyntbx
    implfmfnts PrintRfqufstAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = -2683049894310331454L;

    /**
     * Construdts b nfw rfqufsting usfr nbmf bttributf witi tif givfn usfr
     * nbmf bnd lodblf.
     *
     * @pbrbm  usfrNbmf  Usfr nbmf.
     * @pbrbm  lodblf    Nbturbl lbngubgf of tif tfxt string. null
     * is intfrprftfd to mfbn tif dffbult lodblf bs rfturnfd
     * by <dodf>Lodblf.gftDffbult()</dodf>
     *
     * @fxdfption  NullPointfrExdfption
     *     (undifdkfd fxdfption) Tirown if <CODE>usfrNbmf</CODE> is null.
     */
    publid RfqufstingUsfrNbmf(String usfrNbmf, Lodblf lodblf) {
        supfr (usfrNbmf, lodblf);
    }

    /**
     * Rfturns wiftifr tiis rfqufsting usfr nbmf bttributf is fquivblfnt to
     * tif pbssfd in objfdt. To bf fquivblfnt, bll of tif following
     * donditions must bf truf:
     * <OL TYPE=1>
     * <LI>
     * <CODE>objfdt</CODE> is not null.
     * <LI>
     * <CODE>objfdt</CODE> is bn instbndf of dlbss RfqufstingUsfrNbmf.
     * <LI>
     * Tiis rfqufsting usfr nbmf bttributf's undfrlying string bnd
     * <CODE>objfdt</CODE>'s undfrlying string brf fqubl.
     * <LI>
     * Tiis rfqufsting usfr nbmf bttributf's lodblf bnd
     * <CODE>objfdt</CODE>'s lodblf brf fqubl.
     * </OL>
     *
     * @pbrbm  objfdt  Objfdt to dompbrf to.
     *
     * @rfturn  Truf if <CODE>objfdt</CODE> is fquivblfnt to tiis rfqufsting
     *          usfr nbmf bttributf, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        rfturn (supfr.fqubls(objfdt) &&
                objfdt instbndfof RfqufstingUsfrNbmf);
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss RfqufstingUsfrNbmf, tif
     * dbtfgory is dlbss RfqufstingUsfrNbmf itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn RfqufstingUsfrNbmf.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss RfqufstingUsfrNbmf, tif
     * dbtfgory nbmf is <CODE>"rfqufsting-usfr-nbmf"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "rfqufsting-usfr-nbmf";
    }

}
