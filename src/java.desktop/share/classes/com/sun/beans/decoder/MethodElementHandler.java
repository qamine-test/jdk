/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.bfbns.dfdodfr;

import dom.sun.bfbns.findfr.MftiodFindfr;

import jbvb.lbng.rfflfdt.Mftiod;

import sun.rfflfdt.misd.MftiodUtil;

/**
 * Tiis dlbss is intfndfd to ibndlf &lt;mftiod&gt; flfmfnt.
 * It dfsdribfs invodbtion of tif mftiod.
 * Tif {@dodf nbmf} bttributf dfnotfs
 * tif nbmf of tif mftiod to invokf.
 * If tif {@dodf dlbss} bttributf is spfdififd
 * tiis flfmfnt invokfs stbtid mftiod of spfdififd dlbss.
 * Tif innfr flfmfnts spfdififs tif brgumfnts of tif mftiod.
 * For fxbmplf:<prf>
 * &lt;mftiod nbmf="vblufOf" dlbss="jbvb.lbng.Long"&gt;
 *     &lt;string&gt;10&lt;/string&gt;
 * &lt;/mftiod&gt;</prf>
 * is fquivblfnt to {@dodf Long.vblufOf("10")} in Jbvb dodf.
 * <p>Tif following bttributfs brf supportfd:
 * <dl>
 * <dt>nbmf
 * <dd>tif mftiod nbmf
 * <dt>dlbss
 * <dd>tif typf of objfdt for instbntibtion
 * <dt>id
 * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
 * </dl>
 *
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 */
finbl dlbss MftiodElfmfntHbndlfr fxtfnds NfwElfmfntHbndlfr {
    privbtf String nbmf;

    /**
     * Pbrsfs bttributfs of tif flfmfnt.
     * Tif following bttributfs brf supportfd:
     * <dl>
     * <dt>nbmf
     * <dd>tif mftiod nbmf
     * <dt>dlbss
     * <dd>tif typf of objfdt for instbntibtion
     * <dt>id
     * <dd>tif idfntififr of tif vbribblf tibt is intfndfd to storf tif rfsult
     * </dl>
     *
     * @pbrbm nbmf   tif bttributf nbmf
     * @pbrbm vbluf  tif bttributf vbluf
     */
    @Ovfrridf
    publid void bddAttributf(String nbmf, String vbluf) {
        if (nbmf.fqubls("nbmf")) { // NON-NLS: tif bttributf nbmf
            tiis.nbmf = vbluf;
        } flsf {
            supfr.bddAttributf(nbmf, vbluf);
        }
    }

    /**
     * Rfturns tif rfsult of mftiod fxfdution.
     *
     * @pbrbm typf  tif bbsf dlbss
     * @pbrbm brgs  tif brrby of brgumfnts
     * @rfturn tif vbluf of tiis flfmfnt
     * @tirows Exdfption if dbldulbtion is fbilfd
     */
    @Ovfrridf
    protfdtfd VblufObjfdt gftVblufObjfdt(Clbss<?> typf, Objfdt[] brgs) tirows Exdfption {
        Objfdt bfbn = gftContfxtBfbn();
        Clbss<?>[] typfs = gftArgumfntTypfs(brgs);
        Mftiod mftiod = (typf != null)
                ? MftiodFindfr.findStbtidMftiod(typf, tiis.nbmf, typfs)
                : MftiodFindfr.findMftiod(bfbn.gftClbss(), tiis.nbmf, typfs);

        if (mftiod.isVbrArgs()) {
            brgs = gftArgumfnts(brgs, mftiod.gftPbrbmftfrTypfs());
        }
        Objfdt vbluf = MftiodUtil.invokf(mftiod, bfbn, brgs);
        rfturn mftiod.gftRfturnTypf().fqubls(void.dlbss)
                ? VblufObjfdtImpl.VOID
                : VblufObjfdtImpl.drfbtf(vbluf);
    }
}
