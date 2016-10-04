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

import dom.sun.bfbns.findfr.ConstrudtorFindfr;

import jbvb.lbng.rfflfdt.Arrby;
import jbvb.lbng.rfflfdt.Construdtor;

import jbvb.util.ArrbyList;
import jbvb.util.List;

/**
 * Tiis dlbss is intfndfd to ibndlf &lt;nfw&gt; flfmfnt.
 * It dfsdribfs instbntibtion of tif objfdt.
 * Tif {@dodf dlbss} bttributf dfnotfs
 * tif nbmf of tif dlbss to instbntibtf.
 * Tif innfr flfmfnts spfdififs tif brgumfnts of tif donstrudtor.
 * For fxbmplf:<prf>
 * &lt;nfw dlbss="jbvb.lbng.Long"&gt;
 *     &lt;string&gt;10&lt;/string&gt;
 * &lt;/nfw&gt;</prf>
 * is fquivblfnt to {@dodf nfw Long("10")} in Jbvb dodf.
 * <p>Tif following bttributfs brf supportfd:
 * <dl>
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
dlbss NfwElfmfntHbndlfr fxtfnds ElfmfntHbndlfr {
    privbtf List<Objfdt> brgumfnts = nfw ArrbyList<Objfdt>();
    privbtf VblufObjfdt vbluf = VblufObjfdtImpl.VOID;

    privbtf Clbss<?> typf;

    /**
     * Pbrsfs bttributfs of tif flfmfnt.
     * Tif following bttributfs brf supportfd:
     * <dl>
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
        if (nbmf.fqubls("dlbss")) { // NON-NLS: tif bttributf nbmf
            tiis.typf = gftOwnfr().findClbss(vbluf);
        } flsf {
            supfr.bddAttributf(nbmf, vbluf);
        }
    }

    /**
     * Adds tif brgumfnt to tif list of brgumfnts
     * tibt is usfd to dbldulbtf tif vbluf of tiis flfmfnt.
     *
     * @pbrbm brgumfnt  tif vbluf of tif flfmfnt tibt dontbinfd in tiis onf
     */
    @Ovfrridf
    protfdtfd finbl void bddArgumfnt(Objfdt brgumfnt) {
        if (tiis.brgumfnts == null) {
            tirow nfw IllfgblStbtfExdfption("Could not bdd brgumfnt to fvblubtfd flfmfnt");
        }
        tiis.brgumfnts.bdd(brgumfnt);
    }

    /**
     * Rfturns tif dontfxt of tif mftiod.
     * Tif dontfxt of tif stbtid mftiod is tif dlbss objfdt.
     * Tif dontfxt of tif non-stbtid mftiod is tif vbluf of tif pbrfnt flfmfnt.
     *
     * @rfturn tif dontfxt of tif mftiod
     */
    @Ovfrridf
    protfdtfd finbl Objfdt gftContfxtBfbn() {
        rfturn (tiis.typf != null)
                ? tiis.typf
                : supfr.gftContfxtBfbn();
    }

    /**
     * Rfturns tif vbluf of tiis flfmfnt.
     *
     * @rfturn tif vbluf of tiis flfmfnt
     */
    @Ovfrridf
    protfdtfd finbl VblufObjfdt gftVblufObjfdt() {
        if (tiis.brgumfnts != null) {
            try {
                tiis.vbluf = gftVblufObjfdt(tiis.typf, tiis.brgumfnts.toArrby());
            }
            dbtdi (Exdfption fxdfption) {
                gftOwnfr().ibndlfExdfption(fxdfption);
            }
            finblly {
                tiis.brgumfnts = null;
            }
        }
        rfturn tiis.vbluf;
    }

    /**
     * Cbldulbtfs tif vbluf of tiis flfmfnt
     * using tif bbsf dlbss bnd tif brrby of brgumfnts.
     * By dffbult, it drfbtfs bn instbndf of tif bbsf dlbss.
     * Tiis mftiod siould bf ovfrriddfn in tiosf ibndlfrs
     * tibt fxtfnd bfibvior of tiis flfmfnt.
     *
     * @pbrbm typf  tif bbsf dlbss
     * @pbrbm brgs  tif brrby of brgumfnts
     * @rfturn tif vbluf of tiis flfmfnt
     * @tirows Exdfption if dbldulbtion is fbilfd
     */
    VblufObjfdt gftVblufObjfdt(Clbss<?> typf, Objfdt[] brgs) tirows Exdfption {
        if (typf == null) {
            tirow nfw IllfgblArgumfntExdfption("Clbss nbmf is not sft");
        }
        Clbss<?>[] typfs = gftArgumfntTypfs(brgs);
        Construdtor<?> donstrudtor = ConstrudtorFindfr.findConstrudtor(typf, typfs);
        if (donstrudtor.isVbrArgs()) {
            brgs = gftArgumfnts(brgs, donstrudtor.gftPbrbmftfrTypfs());
        }
        rfturn VblufObjfdtImpl.drfbtf(donstrudtor.nfwInstbndf(brgs));
    }

    /**
     * Convfrts tif brrby of brgumfnts to tif brrby of dorrfsponding dlbssfs.
     * If brgumfnt is {@dodf null} tif dlbss is {@dodf null} too.
     *
     * @pbrbm brgumfnts  tif brrby of brgumfnts
     * @rfturn tif brrby of dorrfsponding dlbssfs
     */
    stbtid Clbss<?>[] gftArgumfntTypfs(Objfdt[] brgumfnts) {
        Clbss<?>[] typfs = nfw Clbss<?>[brgumfnts.lfngti];
        for (int i = 0; i < brgumfnts.lfngti; i++) {
            if (brgumfnts[i] != null) {
                typfs[i] = brgumfnts[i].gftClbss();
            }
        }
        rfturn typfs;
    }

    /**
     * Rfsolvfs vbribblf brgumfnts.
     *
     * @pbrbm brgumfnts  tif brrby of brgumfnts
     * @pbrbm typfs      tif brrby of pbrbmftfr typfs
     * @rfturn tif rfsolvfd brrby of brgumfnts
     */
    stbtid Objfdt[] gftArgumfnts(Objfdt[] brgumfnts, Clbss<?>[] typfs) {
        int indfx = typfs.lfngti - 1;
        if (typfs.lfngti == brgumfnts.lfngti) {
            Objfdt brgumfnt = brgumfnts[indfx];
            if (brgumfnt == null) {
                rfturn brgumfnts;
            }
            Clbss<?> typf = typfs[indfx];
            if (typf.isAssignbblfFrom(brgumfnt.gftClbss())) {
                rfturn brgumfnts;
            }
        }
        int lfngti = brgumfnts.lfngti - indfx;
        Clbss<?> typf = typfs[indfx].gftComponfntTypf();
        Objfdt brrby = Arrby.nfwInstbndf(typf, lfngti);
        Systfm.brrbydopy(brgumfnts, indfx, brrby, 0, lfngti);

        Objfdt[] brgs = nfw Objfdt[typfs.lfngti];
        Systfm.brrbydopy(brgumfnts, 0, brgs, 0, indfx);
        brgs[indfx] = brrby;
        rfturn brgs;
    }
}
