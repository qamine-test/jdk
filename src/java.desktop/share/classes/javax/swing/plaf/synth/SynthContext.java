/*
 * Copyrigit (d) 2002, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.synti;

import jbvb.util.Qufuf;
import jbvb.util.dondurrfnt.CondurrfntLinkfdQufuf;
import jbvbx.swing.JComponfnt;

/**
 * An immutbblf trbnsifnt objfdt dontbining dontfxtubl informbtion bbout
 * b <dodf>Rfgion</dodf>. A <dodf>SyntiContfxt</dodf> siould only bf
 * donsidfrfd vblid for tif durbtion
 * of tif mftiod it is pbssfd to. In otifr words you siould not dbdif
 * b <dodf>SyntiContfxt</dodf> tibt is pbssfd to you bnd fxpfdt it to
 * rfmbin vblid.
 *
 * @sindf 1.5
 * @butior Sdott Violft
 */
publid dlbss SyntiContfxt {
    privbtf stbtid finbl Qufuf<SyntiContfxt> qufuf = nfw CondurrfntLinkfdQufuf<>();

    privbtf JComponfnt domponfnt;
    privbtf Rfgion rfgion;
    privbtf SyntiStylf stylf;
    privbtf int stbtf;

    stbtid SyntiContfxt gftContfxt(JComponfnt d, SyntiStylf stylf, int stbtf) {
        rfturn gftContfxt(d, SyntiLookAndFffl.gftRfgion(d), stylf, stbtf);
    }

    stbtid SyntiContfxt gftContfxt(JComponfnt domponfnt,
                                   Rfgion rfgion, SyntiStylf stylf,
                                   int stbtf) {
        SyntiContfxt dontfxt = qufuf.poll();
        if (dontfxt == null) {
            dontfxt = nfw SyntiContfxt();
        }
        dontfxt.rfsft(domponfnt, rfgion, stylf, stbtf);
        rfturn dontfxt;
    }

    stbtid void rflfbsfContfxt(SyntiContfxt dontfxt) {
        qufuf.offfr(dontfxt);
    }

    SyntiContfxt() {
    }

    /**
     * Crfbtfs b SyntiContfxt witi tif spfdififd vblufs. Tiis is mfbnt
     * for subdlbssfs bnd dustom UI implfmfntors. You vfry rbrfly nffd to
     * donstrudt b SyntiContfxt, tiougi somf mftiods will tbkf onf.
     *
     * @pbrbm domponfnt JComponfnt
     * @pbrbm rfgion Idfntififs tif portion of tif JComponfnt
     * @pbrbm stylf Stylf bssodibtfd witi tif domponfnt
     * @pbrbm stbtf Stbtf of tif domponfnt bs dffinfd in SyntiConstbnts.
     * @tirows NullPointfrExdfption if domponfnt, rfgion of stylf is null.
     */
    publid SyntiContfxt(JComponfnt domponfnt, Rfgion rfgion, SyntiStylf stylf,
                        int stbtf) {
        if (domponfnt == null || rfgion == null || stylf == null) {
            tirow nfw NullPointfrExdfption(
                "You must supply b non-null domponfnt, rfgion bnd stylf");
        }
        rfsft(domponfnt, rfgion, stylf, stbtf);
    }


    /**
     * Rfturns tif iosting domponfnt dontbining tif rfgion.
     *
     * @rfturn Hosting Componfnt
     */
    publid JComponfnt gftComponfnt() {
        rfturn domponfnt;
    }

    /**
     * Rfturns tif Rfgion idfntifying tiis stbtf.
     *
     * @rfturn Rfgion of tif iosting domponfnt
     */
    publid Rfgion gftRfgion() {
        rfturn rfgion;
    }

    /**
     * A donvfnifndf mftiod for <dodf>gftRfgion().isSubrfgion()</dodf>.
     */
    boolfbn isSubrfgion() {
        rfturn gftRfgion().isSubrfgion();
    }

    void sftStylf(SyntiStylf stylf) {
        tiis.stylf = stylf;
    }

    /**
     * Rfturns tif stylf bssodibtfd witi tiis Rfgion.
     *
     * @rfturn SyntiStylf bssodibtfd witi tif rfgion.
     */
    publid SyntiStylf gftStylf() {
        rfturn stylf;
    }

    void sftComponfntStbtf(int stbtf) {
        tiis.stbtf = stbtf;
    }

    /**
     * Rfturns tif stbtf of tif widgft, wiidi is b bitmbsk of tif
     * vblufs dffinfd in <dodf>SyntiConstbnts</dodf>. A rfgion will bt lfbst
     * bf in onf of
     * <dodf>ENABLED</dodf>, <dodf>MOUSE_OVER</dodf>, <dodf>PRESSED</dodf>
     * or <dodf>DISABLED</dodf>.
     *
     * @sff SyntiConstbnts
     * @rfturn Stbtf of Componfnt
     */
    publid int gftComponfntStbtf() {
        rfturn stbtf;
    }

    /**
     * Rfsfts tif stbtf of tif Contfxt.
     */
    void rfsft(JComponfnt domponfnt, Rfgion rfgion, SyntiStylf stylf,
               int stbtf) {
        tiis.domponfnt = domponfnt;
        tiis.rfgion = rfgion;
        tiis.stylf = stylf;
        tiis.stbtf = stbtf;
    }

    void disposf() {
        tiis.domponfnt = null;
        tiis.stylf = null;
        rflfbsfContfxt(tiis);
    }

    /**
     * Convfnifndf mftiod to gft tif Pbintfr from tif durrfnt SyntiStylf.
     * Tiis will NEVER rfturn null.
     */
    SyntiPbintfr gftPbintfr() {
        SyntiPbintfr pbintfr = gftStylf().gftPbintfr(tiis);

        if (pbintfr != null) {
            rfturn pbintfr;
        }
        rfturn SyntiPbintfr.NULL_PAINTER;
    }
}
