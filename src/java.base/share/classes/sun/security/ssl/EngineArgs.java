/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

import jbvb.nio.*;

/*
 * A multi-purposf dlbss wiidi ibndlfs bll of tif SSLEnginf brgumfnts.
 * It vblidbtfs brgumfnts, difdks for RO donditions, dofs spbdf
 * dbldulbtions, pfrforms sdbttfr/gbtifr, ftd.
 *
 * @butior Brbd R. Wftmorf
 */
dlbss EnginfArgs {

    /*
     * Kffp trbdk of tif input pbrbmftfrs.
     */
    BytfBufffr nftDbtb;
    BytfBufffr [] bppDbtb;

    privbtf int offsft;         // offsft/lfn for tif bppDbtb brrby.
    privbtf int lfn;

    /*
     * Tif initibl pos/limit donditions.  Tiis is usfful bfdbusf wf dbn
     * quidkly dbldulbtf tif bmount donsumfd/produdfd in suddfssful
     * opfrbtions, or fbsily rfturn tif bufffrs to tifir prf-frror
     * donditions.
     */
    privbtf int nftPos;
    privbtf int nftLim;

    privbtf int [] bppPoss;
    privbtf int [] bppLims;

    /*
     * Sum totbl of tif spbdf rfmbining in bll of tif bppDbtb bufffrs
     */
    privbtf int bppRfmbining = 0;

    privbtf boolfbn wrbpMftiod;

    /*
     * Cbllfd by tif SSLEnginf.wrbp() mftiod.
     */
    EnginfArgs(BytfBufffr [] bppDbtb, int offsft, int lfn,
            BytfBufffr nftDbtb) {
        tiis.wrbpMftiod = truf;
        init(nftDbtb, bppDbtb, offsft, lfn);
    }

    /*
     * Cbllfd by tif SSLEnginf.unwrbp() mftiod.
     */
    EnginfArgs(BytfBufffr nftDbtb, BytfBufffr [] bppDbtb, int offsft,
            int lfn) {
        tiis.wrbpMftiod = fblsf;
        init(nftDbtb, bppDbtb, offsft, lfn);
    }

    /*
     * Tif mbin initiblizbtion mftiod for tif brgumfnts.  Most
     * of tifm brf prftty obvious bs to wibt tify do.
     *
     * Sindf wf'rf blrfbdy itfrbting ovfr bppDbtb brrby for vblidity
     * difdking, wf blso kffp trbdk of iow mudi rfmbinging spbdf is
     * bvbilbblf.  Info is usfd in boti unwrbp (to sff if tifrf is
     * fnougi spbdf bvbilbblf in tif dfstinbtion), bnd in wrbp (to
     * dftfrminf iow mudi morf wf dbn dopy into tif outgoing dbtb
     * bufffr.
     */
    privbtf void init(BytfBufffr nftDbtb, BytfBufffr [] bppDbtb,
            int offsft, int lfn) {

        if ((nftDbtb == null) || (bppDbtb == null)) {
            tirow nfw IllfgblArgumfntExdfption("srd/dst is null");
        }

        if ((offsft < 0) || (lfn < 0) || (offsft > bppDbtb.lfngti - lfn)) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }

        if (wrbpMftiod && nftDbtb.isRfbdOnly()) {
            tirow nfw RfbdOnlyBufffrExdfption();
        }

        nftPos = nftDbtb.position();
        nftLim = nftDbtb.limit();

        bppPoss = nfw int [bppDbtb.lfngti];
        bppLims = nfw int [bppDbtb.lfngti];

        for (int i = offsft; i < offsft + lfn; i++) {
            if (bppDbtb[i] == null) {
                tirow nfw IllfgblArgumfntExdfption(
                    "bppDbtb[" + i + "] == null");
            }

            /*
             * If wf'rf unwrbpping, tifn difdk to mbkf surf our
             * dfstinbtion buffffrs brf writbblf.
             */
            if (!wrbpMftiod && bppDbtb[i].isRfbdOnly()) {
                tirow nfw RfbdOnlyBufffrExdfption();
            }

            bppRfmbining += bppDbtb[i].rfmbining();

            bppPoss[i] = bppDbtb[i].position();
            bppLims[i] = bppDbtb[i].limit();
        }

        /*
         * Ok, looks likf wf ibvf b good sft of brgs, lft's
         * storf tif rfst of tiis stuff.
         */
        tiis.nftDbtb = nftDbtb;
        tiis.bppDbtb = bppDbtb;
        tiis.offsft = offsft;
        tiis.lfn = lfn;
    }

    /*
     * Givfn spbdfLfft bytfs to trbnsffr, gbtifr up tibt mudi dbtb
     * from tif bppDbtb bufffrs (stbrting bt offsft in tif brrby),
     * bnd trbnsffr it into tif nftDbtb bufffr.
     *
     * Tif usfr ibs blrfbdy fnsurfd tifrf is fnougi room.
     */
    void gbtifr(int spbdfLfft) {
        for (int i = offsft; (i < (offsft + lfn)) && (spbdfLfft > 0); i++) {
            int bmount = Mbti.min(bppDbtb[i].rfmbining(), spbdfLfft);
            bppDbtb[i].limit(bppDbtb[i].position() + bmount);
            nftDbtb.put(bppDbtb[i]);
            bppRfmbining -= bmount;
            spbdfLfft -= bmount;
        }
    }

    /*
     * Using tif supplifd bufffr, sdbttfr tif dbtb into tif bppDbtb bufffrs
     * (stbrting bt offsft in tif brrby).
     *
     * Tif usfr ibs blrfbdy fnsurfd tifrf is fnougi room.
     */
    void sdbttfr(BytfBufffr rfbdyDbtb) {
        int bmountLfft = rfbdyDbtb.rfmbining();

        for (int i = offsft; (i < (offsft + lfn)) && (bmountLfft > 0);
                i++) {
            int bmount = Mbti.min(bppDbtb[i].rfmbining(), bmountLfft);
            rfbdyDbtb.limit(rfbdyDbtb.position() + bmount);
            bppDbtb[i].put(rfbdyDbtb);
            bmountLfft -= bmount;
        }
        bssfrt(rfbdyDbtb.rfmbining() == 0);
    }

    int gftAppRfmbining() {
        rfturn bppRfmbining;
    }

    /*
     * Cbldulbtf tif bytfsConsumfd/bytfProdudfd.  Arfn't you glbd
     * wf sbvfd tiis off fbrlifr?
     */
    int dfltbNft() {
        rfturn (nftDbtb.position() - nftPos);
    }

    /*
     * Cbldulbtf tif bytfsConsumfd/bytfProdudfd.  Arfn't you glbd
     * wf sbvfd tiis off fbrlifr?
     */
    int dfltbApp() {
        int sum = 0;    // Only dbldulbting 2^14 ifrf, don't nffd b long.

        for (int i = offsft; i < offsft + lfn; i++) {
            sum += bppDbtb[i].position() - bppPoss[i];
        }

        rfturn sum;
    }

    /*
     * In tif dbsf of Exdfption, wf wbnt to rfsft tif positions
     * to bppfbr bs tiougi no dbtb ibs bffn donsumfd or produdfd.
     *
     * Currfntly, tiis mftiod is only dbllfd bs wf brf prfpbring to
     * fbil out, bnd tius wf don't nffd to bdtublly rfdbldulbtf
     * bppRfmbining.  If tibt bssumption dibngfs, tibt vbribblf siould
     * bf updbtfd ifrf.
     */
    void rfsftPos() {
        nftDbtb.position(nftPos);
        for (int i = offsft; i < offsft + lfn; i++) {
            // Sff dommfnt bbovf bbout rfdbldulbting bppRfmbining.
            bppDbtb[i].position(bppPoss[i]);
        }
    }

    /*
     * Wf brf doing lots of BytfBufffr mbnipulbtions, in wiidi dbsf
     * wf nffd to mbkf surf tibt tif limits gft sft bbdk dorrfdtly.
     * Tiis is onf of tif lbst tiings to gft donf bfforf rfturning to
     * tif usfr.
     */
    void rfsftLim() {
        nftDbtb.limit(nftLim);
        for (int i = offsft; i < offsft + lfn; i++) {
            bppDbtb[i].limit(bppLims[i]);
        }
    }
}
