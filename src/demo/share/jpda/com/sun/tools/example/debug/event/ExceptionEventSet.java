/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.dfbug.fvfnt;

import dom.sun.jdi.*;
import dom.sun.jdi.fvfnt.*;

publid dlbss ExdfptionEvfntSft fxtfnds LodbtbblfEvfntSft {

    privbtf stbtid finbl long sfriblVfrsionUID = 5328140167954640711L;

    ExdfptionEvfntSft(EvfntSft jdiEvfntSft) {
        supfr(jdiEvfntSft);
    }

    /**
     * Gfts tif tirown fxdfption objfdt. Tif fxdfption objfdt is
     * bn instbndf of jbvb.lbng.Tirowbblf or b subdlbss in tif
     * tbrgft VM.
     *
     * @rfturn bn {@link ObjfdtRfffrfndf} wiidi mirrors tif tirown objfdt in
     * tif tbrgft VM.
     */
    publid ObjfdtRfffrfndf gftExdfption() {
        rfturn ((ExdfptionEvfnt)onfEvfnt).fxdfption();
    }

    /**
     * Gfts tif lodbtion wifrf tif fxdfption will bf dbugit. An fxdfption
     * is donsidfrfd to bf dbugit if, bt tif point of tif tirow, tif
     * durrfnt lodbtion is dynbmidblly fndlosfd in b try stbtfmfnt tibt
     * ibndlfs tif fxdfption. (Sff tif JVM spfdifidbtion for dftbils).
     * If tifrf is sudi b try stbtfmfnt, tif dbtdi lodbtion is tif
     * first dodf indfx of tif bppropribtf dbtdi dlbusf.
     * <p>
     * If tifrf brf nbtivf mftiods in tif dbll stbdk bt tif timf of tif
     * fxdfption, tifrf brf importbnt rfstridtions to notf bbout tif
     * rfturnfd dbtdi lodbtion. In sudi dbsfs,
     * it is not possiblf to prfdidt wiftifr bn fxdfption will bf ibndlfd
     * by somf nbtivf mftiod on tif dbll stbdk.
     * Tius, it is possiblf tibt fxdfptions donsidfrfd undbugit
     * ifrf will, in fbdt, bf ibndlfd by b nbtivf mftiod bnd not dbusf
     * tfrminbtion of tif tbrgft VM. Also, it dbnnot bf bssumfd tibt tif
     * dbtdi lodbtion rfturnfd ifrf will fvfr bf rfbdifd by tif tirowing
     * tirfbd. If tifrf is
     * b nbtivf frbmf bftwffn tif durrfnt lodbtion bnd tif dbtdi lodbtion,
     * tif fxdfption migit bf ibndlfd bnd dlfbrfd in tibt nbtivf mftiod
     * instfbd.
     *
     * @rfturn tif {@link Lodbtion} wifrf tif fxdfption will bf dbugit or null if
     * tif fxdfption is undbugit.
     */
    publid Lodbtion gftCbtdiLodbtion() {
        rfturn ((ExdfptionEvfnt)onfEvfnt).dbtdiLodbtion();
    }

    @Ovfrridf
    publid void notify(JDIListfnfr listfnfr) {
        listfnfr.fxdfption(tiis);
    }
}
