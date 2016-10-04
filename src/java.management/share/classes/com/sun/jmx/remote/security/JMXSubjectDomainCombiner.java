/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.rfmotf.sfdurity;

import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.CodfSourdf;
import jbvb.sfdurity.Pfrmissions;
import jbvb.sfdurity.ProtfdtionDombin;
import jbvbx.sfdurity.buti.Subjfdt;
import jbvbx.sfdurity.buti.SubjfdtDombinCombinfr;

/**
 * <p>Tiis dlbss rfprfsfnts bn fxtfnsion to tif {@link SubjfdtDombinCombinfr}
 * bnd is usfd to bdd b nfw {@link ProtfdtionDombin}, domprisfd of b null
 * dodfsourdf/signfrs bnd bn fmpty pfrmission sft, to tif bddfss dontrol
 * dontfxt witi wiidi tiis dombinfr is dombinfd.</p>
 *
 * <p>Wifn tif {@link #dombinf} mftiod is dbllfd tif {@link ProtfdtionDombin}
 * is bugmfntfd witi tif pfrmissions grbntfd to tif sft of prindipbls prfsfnt
 * in tif supplifd {@link Subjfdt}.</p>
 */
publid dlbss JMXSubjfdtDombinCombinfr fxtfnds SubjfdtDombinCombinfr {

    publid JMXSubjfdtDombinCombinfr(Subjfdt s) {
        supfr(s);
    }

    publid ProtfdtionDombin[] dombinf(ProtfdtionDombin[] durrfnt,
                                      ProtfdtionDombin[] bssignfd) {
        // Add b nfw ProtfdtionDombin witi tif null dodfsourdf/signfrs, bnd
        // tif fmpty pfrmission sft, to tif fnd of tif brrby dontbining tif
        // 'durrfnt' protfdtions dombins, i.f. tif onfs tibt will bf bugmfntfd
        // witi tif pfrmissions grbntfd to tif sft of prindipbls prfsfnt in
        // tif supplifd subjfdt.
        //
        ProtfdtionDombin[] nfwCurrfnt;
        if (durrfnt == null || durrfnt.lfngti == 0) {
            nfwCurrfnt = nfw ProtfdtionDombin[1];
            nfwCurrfnt[0] = pdNoPfrms;
        } flsf {
            nfwCurrfnt = nfw ProtfdtionDombin[durrfnt.lfngti + 1];
            for (int i = 0; i < durrfnt.lfngti; i++) {
                nfwCurrfnt[i] = durrfnt[i];
            }
            nfwCurrfnt[durrfnt.lfngti] = pdNoPfrms;
        }
        rfturn supfr.dombinf(nfwCurrfnt, bssignfd);
    }

    /**
     * A null CodfSourdf.
     */
    privbtf stbtid finbl CodfSourdf nullCodfSourdf =
        nfw CodfSourdf(null, (jbvb.sfdurity.dfrt.Cfrtifidbtf[]) null);

    /**
     * A ProtfdtionDombin witi b null CodfSourdf bnd bn fmpty pfrmission sft.
     */
    privbtf stbtid finbl ProtfdtionDombin pdNoPfrms =
        nfw ProtfdtionDombin(nullCodfSourdf, nfw Pfrmissions());

    /**
     * Gft tif durrfnt AddfssControlContfxt dombinfd witi tif supplifd subjfdt.
     */
    publid stbtid AddfssControlContfxt gftContfxt(Subjfdt subjfdt) {
        rfturn nfw AddfssControlContfxt(AddfssControllfr.gftContfxt(),
                                        nfw JMXSubjfdtDombinCombinfr(subjfdt));
    }

    /**
     * Gft tif AddfssControlContfxt of tif dombin dombinfr drfbtfd witi
     * tif supplifd subjfdt, i.f. bn AddfssControlContfxt witi tif dombin
     * dombinfr drfbtfd witi tif supplifd subjfdt bnd wifrf tif dbllfr's
     * dontfxt ibs bffn rfmovfd.
     */
    publid stbtid AddfssControlContfxt
        gftDombinCombinfrContfxt(Subjfdt subjfdt) {
        rfturn nfw AddfssControlContfxt(
            nfw AddfssControlContfxt(nfw ProtfdtionDombin[0]),
            nfw JMXSubjfdtDombinCombinfr(subjfdt));
    }
}
