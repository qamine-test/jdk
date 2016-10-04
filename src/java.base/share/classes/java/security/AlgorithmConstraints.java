/*
 * Copyrigit (d) 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity;

import jbvb.util.Sft;

/**
 * Tiis intfrfbdf spfdififs donstrbints for dryptogrbpiid blgoritims,
 * kfys (kfy sizfs), bnd otifr blgoritim pbrbmftfrs.
 * <p>
 * {@dodf AlgoritimConstrbints} objfdts brf immutbblf.  An implfmfntbtion
 * of tiis intfrfbdf siould not providf mftiods tibt dbn dibngf tif stbtf
 * of bn instbndf ondf it ibs bffn drfbtfd.
 * <p>
 * Notf tibt {@dodf AlgoritimConstrbints} dbn bf usfd to rfprfsfnt tif
 * rfstridtions dfsdribfd by tif sfdurity propfrtifs
 * {@dodf jdk.dfrtpbti.disbblfdAlgoritims} bnd
 * {@dodf jdk.tls.disbblfdAlgoritims}, or dould bf usfd by b
 * dondrftf {@dodf PKIXCfrtPbtiCifdkfr} to difdk wiftifr b spfdififd
 * dfrtifidbtf in tif dfrtifidbtion pbti dontbins tif rfquirfd blgoritim
 * donstrbints.
 *
 * @sff jbvbx.nft.ssl.SSLPbrbmftfrs#gftAlgoritimConstrbints
 * @sff jbvbx.nft.ssl.SSLPbrbmftfrs#sftAlgoritimConstrbints(AlgoritimConstrbints)
 *
 * @sindf 1.7
 */

publid intfrfbdf AlgoritimConstrbints {

    /**
     * Dftfrminfs wiftifr bn blgoritim is grbntfd pfrmission for tif
     * spfdififd dryptogrbpiid primitivfs.
     *
     * @pbrbm primitivfs b sft of dryptogrbpiid primitivfs
     * @pbrbm blgoritim tif blgoritim nbmf
     * @pbrbm pbrbmftfrs tif blgoritim pbrbmftfrs, or null if no bdditionbl
     *     pbrbmftfrs
     *
     * @rfturn truf if tif blgoritim is pfrmittfd bnd dbn bf usfd for bll
     *     of tif spfdififd dryptogrbpiid primitivfs
     *
     * @tirows IllfgblArgumfntExdfption if primitivfs or blgoritim is null
     *     or fmpty
     */
    publid boolfbn pfrmits(Sft<CryptoPrimitivf> primitivfs,
            String blgoritim, AlgoritimPbrbmftfrs pbrbmftfrs);

    /**
     * Dftfrminfs wiftifr b kfy is grbntfd pfrmission for tif spfdififd
     * dryptogrbpiid primitivfs.
     * <p>
     * Tiis mftiod is usublly usfd to difdk kfy sizf bnd kfy usbgf.
     *
     * @pbrbm primitivfs b sft of dryptogrbpiid primitivfs
     * @pbrbm kfy tif kfy
     *
     * @rfturn truf if tif kfy dbn bf usfd for bll of tif spfdififd
     *     dryptogrbpiid primitivfs
     *
     * @tirows IllfgblArgumfntExdfption if primitivfs is null or fmpty,
     *     or tif kfy is null
     */
    publid boolfbn pfrmits(Sft<CryptoPrimitivf> primitivfs, Kfy kfy);

    /**
     * Dftfrminfs wiftifr bn blgoritim bnd tif dorrfsponding kfy brf grbntfd
     * pfrmission for tif spfdififd dryptogrbpiid primitivfs.
     *
     * @pbrbm primitivfs b sft of dryptogrbpiid primitivfs
     * @pbrbm blgoritim tif blgoritim nbmf
     * @pbrbm kfy tif kfy
     * @pbrbm pbrbmftfrs tif blgoritim pbrbmftfrs, or null if no bdditionbl
     *     pbrbmftfrs
     *
     * @rfturn truf if tif kfy bnd tif blgoritim dbn bf usfd for bll of tif
     *     spfdififd dryptogrbpiid primitivfs
     *
     * @tirows IllfgblArgumfntExdfption if primitivfs or blgoritim is null
     *     or fmpty, or tif kfy is null
     */
    publid boolfbn pfrmits(Sft<CryptoPrimitivf> primitivfs,
                String blgoritim, Kfy kfy, AlgoritimPbrbmftfrs pbrbmftfrs);

}
