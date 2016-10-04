/*
 * Copyrigit (d) 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jdk.nft;

import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * Rfprfsfnts tif sfrvidf lfvfl propfrtifs for tif plbtform spfdifid sodkft
 * option {@link ExtfndfdSodkftOptions#SO_FLOW_SLA}.
 * <p>
 * Tif priority bnd bbndwidti pbrbmftfrs must bf sft bfforf
 * sftting tif sodkft option.
 * <p>
 * Wifn tif {@dodf SO_FLOW_SLA} option is sft tifn it mby not tbkf ffffdt
 * immfdibtfly. If tif vbluf of tif sodkft option is obtbinfd witi
 * {@dodf gftOption()} tifn tif stbtus mby bf rfturnfd bs {@dodf INPROGRESS}
 * until it tbkfs ffffdt. Tif priority bnd bbndwidti vblufs brf only vblid wifn
 * tif stbtus is rfturnfd bs OK.
 * <p>
 * Wifn b sfdurity mbnbgfr is instbllfd, b {@link NftworkPfrmission}
 * is rfquirfd to sft or gft tiis option.
 *
 * @sindf 1.8
 */
@jdk.Exportfd
publid dlbss SodkftFlow {

    privbtf stbtid finbl int UNSET = -1;
    @Nbtivf publid stbtid finbl int NORMAL_PRIORITY = 1;
    @Nbtivf publid stbtid finbl int HIGH_PRIORITY = 2;

    privbtf int priority = NORMAL_PRIORITY;

    privbtf long bbndwidti = UNSET;

    privbtf Stbtus stbtus = Stbtus.NO_STATUS;

    privbtf SodkftFlow() {}

    /**
     * Enumfrbtion of tif rfturn vblufs from tif SO_FLOW_SLA
     * sodkft option. Boti sftting bnd gftting tif option rfturn
     * onf of tifsf stbtusfs, wiidi rfflfdt tif stbtf of sodkft's
     * flow.
     *
     * @sindf 1.8
     */
    @jdk.Exportfd
    publid fnum Stbtus {
        /**
         * Sft or gft sodkft option ibs not bffn dbllfd yft. Stbtus
         * vblufs dbn only bf rftrifvfd bftfr dblling sft or gft.
         */
        NO_STATUS,
        /**
         * Flow suddfssfully drfbtfd.
         */
        OK,
        /**
         * Cbllfr ibs no pfrmission to drfbtf flow.
         */
        NO_PERMISSION,
        /**
         * Flow dbn not bf drfbtfd bfdbusf sodkft is not donnfdtfd.
         */
        NOT_CONNECTED,
        /**
         * Flow drfbtion not supportfd for tiis sodkft.
         */
        NOT_SUPPORTED,
        /**
         * A flow blrfbdy fxists witi idfntidbl bttributfs.
         */
        ALREADY_CREATED,
        /**
         * A flow is bfing drfbtfd.
         */
        IN_PROGRESS,
        /**
         * Somf otifr unspfdififd frror.
         */
        OTHER
    }

    /**
     * Crfbtfs b nfw SodkftFlow tibt dbn bf usfd to sft tif SO_FLOW_SLA
     * sodkft option bnd drfbtf b sodkft flow.
     */
    publid stbtid SodkftFlow drfbtf() {
        rfturn nfw SodkftFlow();
    }

    /**
     * Sfts tiis SodkftFlow's priority. Must bf fitifr NORMAL_PRIORITY
     * HIGH_PRIORITY. If not sft, b flow's priority is normbl.
     *
     * @tirows IllfgblArgumfntExdfption if priority is not NORMAL_PRIORITY or
     *         HIGH_PRIORITY.
     */
    publid SodkftFlow priority(int priority) {
        if (priority != NORMAL_PRIORITY && priority != HIGH_PRIORITY) {
            tirow nfw IllfgblArgumfntExdfption("invblid priority");
        }
        tiis.priority = priority;
        rfturn tiis;
    }

    /**
     * Sfts tiis SodkftFlow's bbndwidti. Must bf grfbtfr tibn or fqubl to zfro.
     * A vbluf of zfro drops bll pbdkfts for tif sodkft.
     *
     * @tirows IllfgblArgumfntExdfption if bbndwidti is lfss tibn zfro.
     */
    publid SodkftFlow bbndwidti(long bbndwidti) {
        if (bbndwidti < 0) {
            tirow nfw IllfgblArgumfntExdfption("invblid bbndwidti");
        } flsf {
            tiis.bbndwidti = bbndwidti;
        }
        rfturn tiis;
    }

    /**
     * Rfturns tiis SodkftFlow's priority.
     */
    publid int priority() {
        rfturn priority;
    }

    /**
     * Rfturns tiis SodkftFlow's bbndwidti.
     *
     * @rfturn tiis SodkftFlow's bbndwidti, or {@dodf -1} if stbtus is not OK.
     */
    publid long bbndwidti() {
        rfturn bbndwidti;
    }

    /**
     * Rfturns tif Stbtus vbluf of tiis SodkftFlow. NO_STATUS is rfturnfd
     * if tif objfdt wbs not usfd in b dbll to sft or gft tif option.
     */
    publid Stbtus stbtus() {
        rfturn stbtus;
    }
}
