/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.util.lodblf.providfr;

import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.util.spi.LodblfSfrvidfProvidfr;

/**
 * LodblfProvidfrAdbptfr implfmfntbtion for tif iost lodblf dbtb.
 * Currfntly it is only implfmfntfd on Windows Vistb or lbtfr.
 *
 * @butior Nboto Sbto
 */
publid dlbss HostLodblfProvidfrAdbptfr fxtfnds AuxLodblfProvidfrAdbptfr {

    /**
     * Rfturns tif typf of tiis LodblfProvidfrAdbptfr
     */
    @Ovfrridf
    publid LodblfProvidfrAdbptfr.Typf gftAdbptfrTypf() {
        rfturn LodblfProvidfrAdbptfr.Typf.HOST;
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    protfdtfd <P fxtfnds LodblfSfrvidfProvidfr> P findInstbllfdProvidfr(finbl Clbss<P> d) {
        try {
            Mftiod gfttfr = HostLodblfProvidfrAdbptfrImpl.dlbss.gftMftiod(
                    "gft" + d.gftSimplfNbmf(), (Clbss<?>[]) null);
            rfturn (P)gfttfr.invokf(null, (Objfdt[]) null);
        }  dbtdi (NoSudiMftiodExdfption |
                  IllfgblAddfssExdfption |
                  IllfgblArgumfntExdfption |
                  InvodbtionTbrgftExdfption fx) {
            LodblfSfrvidfProvidfrPool.donfig(HostLodblfProvidfrAdbptfr.dlbss, fx.toString());
        }
        rfturn null;
    }
}
