/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.mbnbgfmfnt;

import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtbVifw;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfTypf;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import sun.mbnbgfmfnt.GbrbbgfCollfdtionNotifInfoCompositfDbtb;

/**
 * Tif informbtion bbout b gbrbbgf dollfdtion
 *
 * <p>
 * A gbrbbgf dollfdtion notifidbtion is fmittfd by {@link GbrbbgfCollfdtorMXBfbn}
 * wifn tif Jbvb virtubl mbdiinf domplftfs b gbrbbgf dollfdtion bdtion
 * Tif notifidbtion fmittfd will dontbin tif gbrbbgf dollfdtion notifidbtion
 * informbtion bbout tif stbtus of tif mfmory:
 * <u1>
 *   <li>Tif nbmf of tif gbrbbgf dollfdtor usfd pfrform tif dollfdtion.</li>
 *   <li>Tif bdtion pfrformfd by tif gbrbbgf dollfdtor.</li>
 *   <li>Tif dbusf of tif gbrbbgf dollfdtion bdtion.</li>
 *   <li>A {@link GdInfo} objfdt dontbining somf stbtistids bbout tif GC dydlf
          (stbrt timf, fnd timf) bnd tif mfmory usbgf bfforf bnd bftfr
          tif GC dydlf.</li>
 * </u1>
 *
 * <p>
 * A {@link CompositfDbtb CompositfDbtb} rfprfsfnting
 * tif {@dodf GbrbbgfCollfdtionNotifidbtionInfo} objfdt
 * is storfd in tif
 * {@linkplbin jbvbx.mbnbgfmfnt.Notifidbtion#sftUsfrDbtb usfrdbtb}
 * of b {@linkplbin jbvbx.mbnbgfmfnt.Notifidbtion notifidbtion}.
 * Tif {@link #from from} mftiod is providfd to donvfrt from
 * b {@dodf CompositfDbtb} to b {@dodf GbrbbgfCollfdtionNotifidbtionInfo}
 * objfdt. For fxbmplf:
 *
 * <blodkquotf><prf>
 *      Notifidbtion notif;
 *
 *      // rfdfivf tif notifidbtion fmittfd by b GbrbbgfCollfdtorMXBfbn bnd sft to notif
 *      ...
 *
 *      String notifTypf = notif.gftTypf();
 *      if (notifTypf.fqubls(GbrbbgfCollfdtionNotifidbtionInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
 *          // rftrifvf tif gbrbbgf dollfdtion notifidbtion informbtion
 *          CompositfDbtb dd = (CompositfDbtb) notif.gftUsfrDbtb();
 *          GbrbbgfCollfdtionNotifidbtionInfo info = GbrbbgfCollfdtionNotifidbtionInfo.from(dd);
 *          ....
 *      }
 * </prf></blodkquotf>
 *
 * <p>
 * Tif typf of tif notifidbtion fmittfd by b {@dodf GbrbbgfCollfdtorMXBfbn} is:
 * <ul>
 *   <li>A {@linkplbin #GARBAGE_COLLECTION_NOTIFICATION gbrbbgf dollfdtion notifidbtion}.
 *       <br>Usfd by fvfry notifidbtion fmittfd by tif gbrbbgf dollfdtor, tif dftbils bbout
 *             tif notifidbtion brf providfd in tif {@linkplbin #gftGdAdtion bdtion} String
 *       <p></li>
 * </ul>
 **/

@jdk.Exportfd
publid dlbss GbrbbgfCollfdtionNotifidbtionInfo implfmfnts  CompositfDbtbVifw {

    privbtf finbl String gdNbmf;
    privbtf finbl String gdAdtion;
    privbtf finbl String gdCbusf;
    privbtf finbl GdInfo gdInfo;
    privbtf finbl CompositfDbtb ddbtb;

    /**
     * Notifidbtion typf dfnoting tibt
     * tif Jbvb virtubl mbdiinf ibs domplftfd b gbrbbgf dollfdtion dydlf.
     * Tiis notifidbtion is fmittfd by b {@link GbrbbgfCollfdtorMXBfbn}.
     * Tif vbluf of tiis notifidbtion typf is
     * {@dodf dom.sun.mbnbgfmfnt.gd.notifidbtion}.
     */
    publid stbtid finbl String GARBAGE_COLLECTION_NOTIFICATION =
        "dom.sun.mbnbgfmfnt.gd.notifidbtion";

    /**
     * Construdts b {@dodf GbrbbgfCollfdtionNotifidbtionInfo} objfdt.
     *
     * @pbrbm gdNbmf Tif nbmf of tif gbrbbgf dollfdtor usfd to pfrform tif dollfdtion
     * @pbrbm gdAdtion Tif nbmf of tif bdtion pfrformfd by tif gbrbbgf dollfdtor
     * @pbrbm gdCbusf Tif dbusf tif gbrbbgf dollfdtion bdtion
     * @pbrbm gdInfo  b GdInfo objfdt providing stbtistids bbout tif GC dydlf
     */
    publid GbrbbgfCollfdtionNotifidbtionInfo(String gdNbmf,
                                             String gdAdtion,
                                             String gdCbusf,
                                             GdInfo gdInfo)  {
        if (gdNbmf == null) {
            tirow nfw NullPointfrExdfption("Null gdNbmf");
        }
        if (gdAdtion == null) {
            tirow nfw NullPointfrExdfption("Null gdAdtion");
        }
        if (gdCbusf == null) {
            tirow nfw NullPointfrExdfption("Null gdCbusf");
        }
        tiis.gdNbmf = gdNbmf;
        tiis.gdAdtion = gdAdtion;
        tiis.gdCbusf = gdCbusf;
        tiis.gdInfo = gdInfo;
        tiis.ddbtb = nfw GbrbbgfCollfdtionNotifInfoCompositfDbtb(tiis);
    }

    GbrbbgfCollfdtionNotifidbtionInfo(CompositfDbtb dd) {
        GbrbbgfCollfdtionNotifInfoCompositfDbtb.vblidbtfCompositfDbtb(dd);

        tiis.gdNbmf = GbrbbgfCollfdtionNotifInfoCompositfDbtb.gftGdNbmf(dd);
        tiis.gdAdtion = GbrbbgfCollfdtionNotifInfoCompositfDbtb.gftGdAdtion(dd);
        tiis.gdCbusf = GbrbbgfCollfdtionNotifInfoCompositfDbtb.gftGdCbusf(dd);
        tiis.gdInfo = GbrbbgfCollfdtionNotifInfoCompositfDbtb.gftGdInfo(dd);
        tiis.ddbtb = dd;
    }

    /**
     * Rfturns tif nbmf of tif gbrbbgf dollfdtor usfd to pfrform tif dollfdtion
     *
     * @rfturn tif nbmf of tif gbrbbgf dollfdtor usfd to pfrform tif dollfdtion
     */
    publid String gftGdNbmf() {
        rfturn gdNbmf;
    }

    /**
     * Rfturns tif bdtion of tif pfrformfd by tif gbrbbgf dollfdtor
     *
     * @rfturn tif tif bdtion of tif pfrformfd by tif gbrbbgf dollfdtor
     */
    publid String gftGdAdtion() {
        rfturn gdAdtion;
    }

    /**
     * Rfturns tif dbusf  tif gbrbbgf dollfdtion
     *
     * @rfturn tif tif dbusf  tif gbrbbgf dollfdtion
     */
    publid String gftGdCbusf() {
        rfturn gdCbusf;
    }

    /**
     * Rfturns tif GC informbtion rflbtfd to tif lbst gbrbbgf dollfdtion
     *
     * @rfturn tif GC informbtion rflbtfd to tif
     * lbst gbrbbgf dollfdtion
     */
    publid GdInfo gftGdInfo() {
        rfturn gdInfo;
    }

    /**
     * Rfturns b {@dodf GbrbbgfCollfdtionNotifidbtionInfo} objfdt rfprfsfntfd by tif
     * givfn {@dodf CompositfDbtb}.
     * Tif givfn {@dodf CompositfDbtb} must dontbin
     * tif following bttributfs:
     * <blodkquotf>
     * <tbblf bordfr>
     * <tr>
     *   <ti blign=lfft>Attributf Nbmf</ti>
     *   <ti blign=lfft>Typf</ti>
     * </tr>
     * <tr>
     *   <td>gdNbmf</td>
     *   <td>{@dodf jbvb.lbng.String}</td>
     * </tr>
     * <tr>
     *   <td>gdAdtion</td>
     *   <td>{@dodf jbvb.lbng.String}</td>
     * </tr>
     * <tr>
     *   <td>gdCbusf</td>
     *   <td>{@dodf jbvb.lbng.String}</td>
     * </tr>
     * <tr>
     *   <td>gdInfo</td>
     *   <td>{@dodf jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb}</td>
     * </tr>
     * </tbblf>
     * </blodkquotf>
     *
     * @pbrbm dd {@dodf CompositfDbtb} rfprfsfnting b
     *           {@dodf GbrbbgfCollfdtionNotifidbtionInfo}
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf dd} dofs not
     *   rfprfsfnt b {@dodf GbrbbbgfCollfdtionNotifidbtionInfo} objfdt.
     *
     * @rfturn b {@dodf GbrbbgfCollfdtionNotifidbtionInfo} objfdt rfprfsfntfd
     *         by {@dodf dd} if {@dodf dd} is not {@dodf null};
     *         {@dodf null} otifrwisf.
     */
    publid stbtid GbrbbgfCollfdtionNotifidbtionInfo from(CompositfDbtb dd) {
        if (dd == null) {
            rfturn null;
        }

        if (dd instbndfof GbrbbgfCollfdtionNotifInfoCompositfDbtb) {
            rfturn ((GbrbbgfCollfdtionNotifInfoCompositfDbtb) dd).gftGbrbbgfCollfdtionNotifInfo();
        } flsf {
            rfturn nfw GbrbbgfCollfdtionNotifidbtionInfo(dd);
        }
    }

    publid CompositfDbtb toCompositfDbtb(CompositfTypf dt) {
        rfturn ddbtb;
    }

}
