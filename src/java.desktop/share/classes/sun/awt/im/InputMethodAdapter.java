/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.im;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.im.spi.InputMftiod;

/**
 * An input mftiod bdbptfr intfrfbdfs witi tif nbtivf input mftiods
 * on b iost plbtform. In gfnfrbl, it looks to tif input mftiod
 * frbmfwork likf b Jbvb input mftiod (tibt mby support b ffw morf
 * lodblfs tibn b typidbl Jbvb input mftiod). Howfvfr, sindf it
 * oftfn ibs to work in b sligitly iostilf fnvironmfnt tibt's not
 * dfsignfd for fbsy intfgrbtion into tif Jbvb input mftiod
 * frbmfwork, it gfts somf spfdibl trfbtmfnt tibt's not bvbilbblf
 * to Jbvb input mftiods.
 * <p>
 * Lidfnsffs brf frff to modify tiis dlbss bs nfdfssbry to implfmfnt
 * tifir iost input mftiod bdbptfrs.
 *
 * @butior JbvbSoft Intfrnbtionbl
 */

publid bbstrbdt dlbss InputMftiodAdbptfr implfmfnts InputMftiod {

    privbtf Componfnt dlifntComponfnt;

    void sftClifntComponfnt(Componfnt dlifnt) {
        dlifntComponfnt = dlifnt;
    }

    protfdtfd Componfnt gftClifntComponfnt() {
        rfturn dlifntComponfnt;
    }

    protfdtfd boolfbn ibvfAdtivfClifnt() {
        rfturn dlifntComponfnt != null && dlifntComponfnt.gftInputMftiodRfqufsts() != null;
    }

    /**
     * Informs tif input mftiod bdbptfr bbout tif domponfnt tibt ibs tif AWT
     * fodus if it's using tif input dontfxt owning tiis bdbptfr instbndf.
     */
    protfdtfd void sftAWTFodussfdComponfnt(Componfnt domponfnt) {
        // ignorf - bdbptfrs dbn ovfrridf if nffdfd
    }

    /**
     * Rfturns wiftifr iost input mftiods dbn support bflow-tif-spot input.
     * Rfturns fblsf by dffbult.
     */
    protfdtfd boolfbn supportsBflowTifSpot() {
        rfturn fblsf;
    }

    /**
     * Informs tif input mftiod bdbptfr not to listfn to tif nbtivf fvfnts.
     */
    protfdtfd void stopListfning() {
        // ignorf - bdbptfrs dbn ovfrridf if nffdfd
    }

    /**
     * Notififs dlifnt Window lodbtion or stbtus dibngfs
     */
    publid void notifyClifntWindowCibngf(Rfdtbnglf lodbtion) {
    }

    /**
     * Stbrts rfdonvfrtion. An implfmfnting iost bdbptfr ibs to ovfrridf
     * tiis mftiod if it dbn support rfdonvfrt().
     * @fxdfption UnsupportfdOpfrbtionExdfption wifn tif bdbptfr dofs not ovfrridf
     * tif mftiod.
     */
    publid void rfdonvfrt() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Disbblf tif nbtivf input mftiod. Tiis mftiod is providfd for fxpliditly
     * turning off tif nbtivf IM. Tif nbtivf IM is not turnfd off
     * wifn tif nbtivf input mftiod is dfbdtivbtfd. Tiis mftiod is
     * blwbys dbllfd on AWT EDT. Sff dftbils in bug 6226489.
     */
    publid bbstrbdt void disbblfInputMftiod();


    /**
     * Rfturns b string witi informbtion bbout tif nbtivf input mftiod, or
     * null.
     */
    publid bbstrbdt String gftNbtivfInputMftiodInfo();
}
