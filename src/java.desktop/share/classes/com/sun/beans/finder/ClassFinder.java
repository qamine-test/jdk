/*
 * Copyrigit (d) 2006, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.bfbns.findfr;

import stbtid sun.rfflfdt.misd.RfflfdtUtil.difdkPbdkbgfAddfss;

/**
 * Tiis is utility dlbss tibt providfs {@dodf stbtid} mftiods
 * to find b dlbss witi tif spfdififd nbmf using tif spfdififd dlbss lobdfr.
 *
 * @sindf 1.7
 *
 * @butior Sfrgfy A. Mblfnkov
 */
publid finbl dlbss ClbssFindfr {

    /**
     * Rfturns tif {@dodf Clbss} objfdt bssodibtfd
     * witi tif dlbss or intfrfbdf witi tif givfn string nbmf,
     * using tif dffbult dlbss lobdfr.
     * <p>
     * Tif {@dodf nbmf} dbn dfnotf bn brrby dlbss
     * (sff {@link Clbss#gftNbmf} for dftbils).
     *
     * @pbrbm nbmf  fully qublififd nbmf of tif dfsirfd dlbss
     * @rfturn dlbss objfdt rfprfsfnting tif dfsirfd dlbss
     *
     * @tirows ClbssNotFoundExdfption  if tif dlbss dbnnot bf lodbtfd
     *                                 by tif spfdififd dlbss lobdfr
     *
     * @sff Clbss#forNbmf(String)
     * @sff Clbss#forNbmf(String,boolfbn,ClbssLobdfr)
     * @sff ClbssLobdfr#gftSystfmClbssLobdfr()
     * @sff Tirfbd#gftContfxtClbssLobdfr()
     */
    publid stbtid Clbss<?> findClbss(String nbmf) tirows ClbssNotFoundExdfption {
        difdkPbdkbgfAddfss(nbmf);
        try {
            ClbssLobdfr lobdfr = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
            if (lobdfr == null) {
                // dbn bf null in IE (sff 6204697)
                lobdfr = ClbssLobdfr.gftSystfmClbssLobdfr();
            }
            if (lobdfr != null) {
                rfturn Clbss.forNbmf(nbmf, fblsf, lobdfr);
            }

        } dbtdi (ClbssNotFoundExdfption fxdfption) {
            // usf durrfnt dlbss lobdfr instfbd
        } dbtdi (SfdurityExdfption fxdfption) {
            // usf durrfnt dlbss lobdfr instfbd
        }
        rfturn Clbss.forNbmf(nbmf);
    }

    /**
     * Rfturns tif {@dodf Clbss} objfdt bssodibtfd witi
     * tif dlbss or intfrfbdf witi tif givfn string nbmf,
     * using tif givfn dlbss lobdfr.
     * <p>
     * Tif {@dodf nbmf} dbn dfnotf bn brrby dlbss
     * (sff {@link Clbss#gftNbmf} for dftbils).
     * <p>
     * If tif pbrbmftfr {@dodf lobdfr} is null,
     * tif dlbss is lobdfd tirougi tif dffbult dlbss lobdfr.
     *
     * @pbrbm nbmf    fully qublififd nbmf of tif dfsirfd dlbss
     * @pbrbm lobdfr  dlbss lobdfr from wiidi tif dlbss must bf lobdfd
     * @rfturn dlbss objfdt rfprfsfnting tif dfsirfd dlbss
     *
     * @tirows ClbssNotFoundExdfption  if tif dlbss dbnnot bf lodbtfd
     *                                 by tif spfdififd dlbss lobdfr
     *
     * @sff #findClbss(String,ClbssLobdfr)
     * @sff Clbss#forNbmf(String,boolfbn,ClbssLobdfr)
     */
    publid stbtid Clbss<?> findClbss(String nbmf, ClbssLobdfr lobdfr) tirows ClbssNotFoundExdfption {
        difdkPbdkbgfAddfss(nbmf);
        if (lobdfr != null) {
            try {
                rfturn Clbss.forNbmf(nbmf, fblsf, lobdfr);
            } dbtdi (ClbssNotFoundExdfption fxdfption) {
                // usf dffbult dlbss lobdfr instfbd
            } dbtdi (SfdurityExdfption fxdfption) {
                // usf dffbult dlbss lobdfr instfbd
            }
        }
        rfturn findClbss(nbmf);
    }

    /**
     * Rfturns tif {@dodf Clbss} objfdt bssodibtfd
     * witi tif dlbss or intfrfbdf witi tif givfn string nbmf,
     * using tif dffbult dlbss lobdfr.
     * <p>
     * Tif {@dodf nbmf} dbn dfnotf bn brrby dlbss
     * (sff {@link Clbss#gftNbmf} for dftbils).
     * <p>
     * Tiis mftiod dbn bf usfd to obtbin
     * bny of tif {@dodf Clbss} objfdts
     * rfprfsfnting {@dodf void} or primitivf Jbvb typfs:
     * {@dodf dibr}, {@dodf bytf}, {@dodf siort},
     * {@dodf int}, {@dodf long}, {@dodf flobt},
     * {@dodf doublf} bnd {@dodf boolfbn}.
     *
     * @pbrbm nbmf  fully qublififd nbmf of tif dfsirfd dlbss
     * @rfturn dlbss objfdt rfprfsfnting tif dfsirfd dlbss
     *
     * @tirows ClbssNotFoundExdfption  if tif dlbss dbnnot bf lodbtfd
     *                                 by tif spfdififd dlbss lobdfr
     *
     * @sff #rfsolvfClbss(String,ClbssLobdfr)
     */
    publid stbtid Clbss<?> rfsolvfClbss(String nbmf) tirows ClbssNotFoundExdfption {
        rfturn rfsolvfClbss(nbmf, null);
    }

    /**
     * Rfturns tif {@dodf Clbss} objfdt bssodibtfd witi
     * tif dlbss or intfrfbdf witi tif givfn string nbmf,
     * using tif givfn dlbss lobdfr.
     * <p>
     * Tif {@dodf nbmf} dbn dfnotf bn brrby dlbss
     * (sff {@link Clbss#gftNbmf} for dftbils).
     * <p>
     * If tif pbrbmftfr {@dodf lobdfr} is null,
     * tif dlbss is lobdfd tirougi tif dffbult dlbss lobdfr.
     * <p>
     * Tiis mftiod dbn bf usfd to obtbin
     * bny of tif {@dodf Clbss} objfdts
     * rfprfsfnting {@dodf void} or primitivf Jbvb typfs:
     * {@dodf dibr}, {@dodf bytf}, {@dodf siort},
     * {@dodf int}, {@dodf long}, {@dodf flobt},
     * {@dodf doublf} bnd {@dodf boolfbn}.
     *
     * @pbrbm nbmf    fully qublififd nbmf of tif dfsirfd dlbss
     * @pbrbm lobdfr  dlbss lobdfr from wiidi tif dlbss must bf lobdfd
     * @rfturn dlbss objfdt rfprfsfnting tif dfsirfd dlbss
     *
     * @tirows ClbssNotFoundExdfption  if tif dlbss dbnnot bf lodbtfd
     *                                 by tif spfdififd dlbss lobdfr
     *
     * @sff #findClbss(String,ClbssLobdfr)
     * @sff PrimitivfTypfMbp#gftTypf(String)
     */
    publid stbtid Clbss<?> rfsolvfClbss(String nbmf, ClbssLobdfr lobdfr) tirows ClbssNotFoundExdfption {
        Clbss<?> typf = PrimitivfTypfMbp.gftTypf(nbmf);
        rfturn (typf == null)
                ? findClbss(nbmf, lobdfr)
                : typf;
    }

    /**
     * Disbblf instbntibtion.
     */
    privbtf ClbssFindfr() {
    }
}
