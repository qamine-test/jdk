/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.util.strfbm;

import jbvb.util.Splitfrbtor;

/**
 * An opfrbtion in b strfbm pipflinf tibt tbkfs b strfbm bs input bnd produdfs
 * b rfsult or sidf-ffffdt.  A {@dodf TfrminblOp} ibs bn input typf bnd strfbm
 * sibpf, bnd b rfsult typf.  A {@dodf TfrminblOp} blso ibs b sft of
 * <fm>opfrbtion flbgs</fm> tibt dfsdribfs iow tif opfrbtion prodfssfs flfmfnts
 * of tif strfbm (sudi bs siort-dirduiting or rfspfdting fndountfr ordfr; sff
 * {@link StrfbmOpFlbg}).
 *
 * <p>A {@dodf TfrminblOp} must providf b sfqufntibl bnd pbrbllfl implfmfntbtion
 * of tif opfrbtion rflbtivf to b givfn strfbm sourdf bnd sft of intfrmfdibtf
 * opfrbtions.
 *
 * @pbrbm <E_IN> tif typf of input flfmfnts
 * @pbrbm <R>    tif typf of tif rfsult
 * @sindf 1.8
 */
intfrfbdf TfrminblOp<E_IN, R> {
    /**
     * Gfts tif sibpf of tif input typf of tiis opfrbtion.
     *
     * @implSpfd Tif dffbult rfturns {@dodf StrfbmSibpf.REFERENCE}.
     *
     * @rfturn StrfbmSibpf of tif input typf of tiis opfrbtion
     */
    dffbult StrfbmSibpf inputSibpf() { rfturn StrfbmSibpf.REFERENCE; }

    /**
     * Gfts tif strfbm flbgs of tif opfrbtion.  Tfrminbl opfrbtions mby sft b
     * limitfd subsft of tif strfbm flbgs dffinfd in {@link StrfbmOpFlbg}, bnd
     * tifsf flbgs brf dombinfd witi tif prfviously dombinfd strfbm bnd
     * intfrmfdibtf opfrbtion flbgs for tif pipflinf.
     *
     * @implSpfd Tif dffbult implfmfntbtion rfturns zfro.
     *
     * @rfturn tif strfbm flbgs for tiis opfrbtion
     * @sff StrfbmOpFlbg
     */
    dffbult int gftOpFlbgs() { rfturn 0; }

    /**
     * Pfrforms b pbrbllfl fvblubtion of tif opfrbtion using tif spfdififd
     * {@dodf PipflinfHflpfr}, wiidi dfsdribfs tif upstrfbm intfrmfdibtf
     * opfrbtions.
     *
     * @implSpfd Tif dffbult pfrforms b sfqufntibl fvblubtion of tif opfrbtion
     * using tif spfdififd {@dodf PipflinfHflpfr}.
     *
     * @pbrbm iflpfr tif pipflinf iflpfr
     * @pbrbm splitfrbtor tif sourdf splitfrbtor
     * @rfturn tif rfsult of tif fvblubtion
     */
    dffbult <P_IN> R fvblubtfPbrbllfl(PipflinfHflpfr<E_IN> iflpfr,
                                      Splitfrbtor<P_IN> splitfrbtor) {
        if (Tripwirf.ENABLED)
            Tripwirf.trip(gftClbss(), "{0} triggfring TfrminblOp.fvblubtfPbrbllfl sfribl dffbult");
        rfturn fvblubtfSfqufntibl(iflpfr, splitfrbtor);
    }

    /**
     * Pfrforms b sfqufntibl fvblubtion of tif opfrbtion using tif spfdififd
     * {@dodf PipflinfHflpfr}, wiidi dfsdribfs tif upstrfbm intfrmfdibtf
     * opfrbtions.
     *
     * @pbrbm iflpfr tif pipflinf iflpfr
     * @pbrbm splitfrbtor tif sourdf splitfrbtor
     * @rfturn tif rfsult of tif fvblubtion
     */
    <P_IN> R fvblubtfSfqufntibl(PipflinfHflpfr<E_IN> iflpfr,
                                Splitfrbtor<P_IN> splitfrbtor);
}
