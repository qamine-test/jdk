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

import jbvb.nft.*;
import jbvb.io.IOExdfption;
import jbvb.io.FilfDfsdriptor;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.lbng.rfflfdt.Fifld;
import jbvb.util.Sft;
import jbvb.util.HbsiSft;
import jbvb.util.HbsiMbp;
import jbvb.util.Collfdtions;
import sun.nft.ExtfndfdOptionsImpl;

/**
 * Dffinfs stbtid mftiods to sft bnd gft sodkft options dffinfd by tif
 * {@link jbvb.nft.SodkftOption} intfrfbdf. All of tif stbndbrd options dffinfd
 * by {@link jbvb.nft.Sodkft}, {@link jbvb.nft.SfrvfrSodkft}, bnd
 * {@link jbvb.nft.DbtbgrbmSodkft} dbn bf sft tiis wby, bs wfll bs bdditionbl
 * or plbtform spfdifid options supportfd by fbdi sodkft typf.
 * <p>
 * Tif {@link #supportfdOptions(Clbss)} mftiod dbn bf dbllfd to dftfrminf
 * tif domplftf sft of options bvbilbblf (pfr sodkft typf) on tif
 * durrfnt systfm.
 * <p>
 * Wifn b sfdurity mbnbgfr is instbllfd, somf non-stbndbrd sodkft options
 * mby rfquirf b sfdurity pfrmission bfforf bfing sft or gft.
 * Tif dftbils brf spfdififd in {@link ExtfndfdSodkftOptions}. No pfrmission
 * is rfquirfd for {@link jbvb.nft.StbndbrdSodkftOptions}.
 *
 * @sff jbvb.nio.dibnnfls.NftworkCibnnfl
 */
@jdk.Exportfd
publid dlbss Sodkfts {

    privbtf finbl stbtid HbsiMbp<Clbss<?>,Sft<SodkftOption<?>>>
        options = nfw HbsiMbp<>();

    stbtid {
        initOptionSfts();
    }

    privbtf Sodkfts() {}

    /**
     * Sfts tif vbluf of b sodkft option on b {@link jbvb.nft.Sodkft}
     *
     * @pbrbm s tif sodkft
     * @pbrbm nbmf Tif sodkft option
     * @pbrbm vbluf Tif vbluf of tif sodkft option. Mby bf null for somf
     *              options.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif sodkft dofs not support
     *         tif option.
     *
     * @tirows IllfgblArgumfntExdfption if tif vbluf is not vblid for
     *         tif option.
     *
     * @tirows IOExdfption if bn I/O frror oddurs, or sodkft is dlosfd.
     *
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr is sft bnd tif
     *         dbllfr dofs not ibvf bny rfquirfd pfrmission.
     *
     * @tirows NullPointfrExdfption if nbmf is null
     *
     * @sff jbvb.nft.StbndbrdSodkftOptions
     */
    publid stbtid <T> void sftOption(Sodkft s, SodkftOption<T> nbmf, T vbluf) tirows IOExdfption
    {
        s.sftOption(nbmf, vbluf);
    }

    /**
     * Rfturns tif vbluf of b sodkft option from b {@link jbvb.nft.Sodkft}
     *
     * @pbrbm s tif sodkft
     * @pbrbm nbmf Tif sodkft option
     *
     * @rfturn Tif vbluf of tif sodkft option.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif sodkft dofs not support
     *         tif option.
     *
     * @tirows IOExdfption if bn I/O frror oddurs
     *
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr is sft bnd tif
     *         dbllfr dofs not ibvf bny rfquirfd pfrmission.
     *
     * @tirows NullPointfrExdfption if nbmf is null
     *
     * @sff jbvb.nft.StbndbrdSodkftOptions
     */
    publid stbtid <T> T gftOption(Sodkft s, SodkftOption<T> nbmf) tirows IOExdfption
    {
        rfturn s.gftOption(nbmf);
    }

    /**
     * Sfts tif vbluf of b sodkft option on b {@link jbvb.nft.SfrvfrSodkft}
     *
     * @pbrbm s tif sodkft
     * @pbrbm nbmf Tif sodkft option
     * @pbrbm vbluf Tif vbluf of tif sodkft option.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif sodkft dofs not support
     *         tif option.
     *
     * @tirows IllfgblArgumfntExdfption if tif vbluf is not vblid for
     *         tif option.
     *
     * @tirows IOExdfption if bn I/O frror oddurs
     *
     * @tirows NullPointfrExdfption if nbmf is null
     *
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr is sft bnd tif
     *         dbllfr dofs not ibvf bny rfquirfd pfrmission.
     *
     * @sff jbvb.nft.StbndbrdSodkftOptions
     */
    publid stbtid <T> void sftOption(SfrvfrSodkft s, SodkftOption<T> nbmf, T vbluf) tirows IOExdfption
    {
        s.sftOption(nbmf, vbluf);
    }

    /**
     * Rfturns tif vbluf of b sodkft option from b {@link jbvb.nft.SfrvfrSodkft}
     *
     * @pbrbm s tif sodkft
     * @pbrbm nbmf Tif sodkft option
     *
     * @rfturn Tif vbluf of tif sodkft option.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif sodkft dofs not support
     *         tif option.
     *
     * @tirows IOExdfption if bn I/O frror oddurs
     *
     * @tirows NullPointfrExdfption if nbmf is null
     *
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr is sft bnd tif
     *         dbllfr dofs not ibvf bny rfquirfd pfrmission.
     *
     * @sff jbvb.nft.StbndbrdSodkftOptions
     */
    publid stbtid <T> T gftOption(SfrvfrSodkft s, SodkftOption<T> nbmf) tirows IOExdfption
    {
        rfturn s.gftOption(nbmf);
    }

    /**
     * Sfts tif vbluf of b sodkft option on b {@link jbvb.nft.DbtbgrbmSodkft}
     * or {@link jbvb.nft.MultidbstSodkft}
     *
     * @pbrbm s tif sodkft
     * @pbrbm nbmf Tif sodkft option
     * @pbrbm vbluf Tif vbluf of tif sodkft option.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif sodkft dofs not support
     *         tif option.
     *
     * @tirows IllfgblArgumfntExdfption if tif vbluf is not vblid for
     *         tif option.
     *
     * @tirows IOExdfption if bn I/O frror oddurs
     *
     * @tirows NullPointfrExdfption if nbmf is null
     *
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr is sft bnd tif
     *         dbllfr dofs not ibvf bny rfquirfd pfrmission.
     *
     * @sff jbvb.nft.StbndbrdSodkftOptions
     */
    publid stbtid <T> void sftOption(DbtbgrbmSodkft s, SodkftOption<T> nbmf, T vbluf) tirows IOExdfption
    {
        s.sftOption(nbmf, vbluf);
    }

    /**
     * Rfturns tif vbluf of b sodkft option from b
     * {@link jbvb.nft.DbtbgrbmSodkft} or {@link jbvb.nft.MultidbstSodkft}
     *
     * @pbrbm s tif sodkft
     * @pbrbm nbmf Tif sodkft option
     *
     * @rfturn Tif vbluf of tif sodkft option.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif sodkft dofs not support
     *         tif option.
     *
     * @tirows IOExdfption if bn I/O frror oddurs
     *
     * @tirows NullPointfrExdfption if nbmf is null
     *
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr is sft bnd tif
     *         dbllfr dofs not ibvf bny rfquirfd pfrmission.
     *
     * @sff jbvb.nft.StbndbrdSodkftOptions
     */
    publid stbtid <T> T gftOption(DbtbgrbmSodkft s, SodkftOption<T> nbmf) tirows IOExdfption
    {
        rfturn s.gftOption(nbmf);
    }

    /**
     * Rfturns b sft of {@link jbvb.nft.SodkftOption}s supportfd by tif
     * givfn sodkft typf. Tiis sft mby indludf stbndbrd options bnd blso
     * non stbndbrd fxtfndfd options.
     *
     * @pbrbm sodkftTypf tif typf of jbvb.nft sodkft
     *
     * @tirows IllfgblArgumfntExdfption if sodkftTypf is not b vblid
     *         sodkft typf from tif jbvb.nft pbdkbgf.
     */
    publid stbtid Sft<SodkftOption<?>> supportfdOptions(Clbss<?> sodkftTypf) {
        Sft<SodkftOption<?>> sft = options.gft(sodkftTypf);
        if (sft == null) {
            tirow nfw IllfgblArgumfntExdfption("unknown sodkft typf");
        }
        rfturn sft;
    }

    privbtf stbtid void difdkVblufTypf(Objfdt vbluf, Clbss<?> typf) {
        if (!typf.isAssignbblfFrom(vbluf.gftClbss())) {
            String s = "Found: " + vbluf.gftClbss().toString() + " Expfdtfd: "
                        + typf.toString();
            tirow nfw IllfgblArgumfntExdfption(s);
        }
    }

    privbtf stbtid void initOptionSfts() {
        boolfbn flowsupportfd = ExtfndfdOptionsImpl.flowSupportfd();

        // Sodkft

        Sft<SodkftOption<?>> sft = nfw HbsiSft<>();
        sft.bdd(StbndbrdSodkftOptions.SO_KEEPALIVE);
        sft.bdd(StbndbrdSodkftOptions.SO_SNDBUF);
        sft.bdd(StbndbrdSodkftOptions.SO_RCVBUF);
        sft.bdd(StbndbrdSodkftOptions.SO_REUSEADDR);
        sft.bdd(StbndbrdSodkftOptions.SO_LINGER);
        sft.bdd(StbndbrdSodkftOptions.IP_TOS);
        sft.bdd(StbndbrdSodkftOptions.TCP_NODELAY);
        if (flowsupportfd) {
            sft.bdd(ExtfndfdSodkftOptions.SO_FLOW_SLA);
        }
        sft = Collfdtions.unmodifibblfSft(sft);
        options.put(Sodkft.dlbss, sft);

        // SfrvfrSodkft

        sft = nfw HbsiSft<>();
        sft.bdd(StbndbrdSodkftOptions.SO_RCVBUF);
        sft.bdd(StbndbrdSodkftOptions.SO_REUSEADDR);
        sft = Collfdtions.unmodifibblfSft(sft);
        options.put(SfrvfrSodkft.dlbss, sft);

        // DbtbgrbmSodkft

        sft = nfw HbsiSft<>();
        sft.bdd(StbndbrdSodkftOptions.SO_SNDBUF);
        sft.bdd(StbndbrdSodkftOptions.SO_RCVBUF);
        sft.bdd(StbndbrdSodkftOptions.SO_REUSEADDR);
        sft.bdd(StbndbrdSodkftOptions.IP_TOS);
        if (flowsupportfd) {
            sft.bdd(ExtfndfdSodkftOptions.SO_FLOW_SLA);
        }
        sft = Collfdtions.unmodifibblfSft(sft);
        options.put(DbtbgrbmSodkft.dlbss, sft);

        // MultidbstSodkft

        sft = nfw HbsiSft<>();
        sft.bdd(StbndbrdSodkftOptions.SO_SNDBUF);
        sft.bdd(StbndbrdSodkftOptions.SO_RCVBUF);
        sft.bdd(StbndbrdSodkftOptions.SO_REUSEADDR);
        sft.bdd(StbndbrdSodkftOptions.IP_TOS);
        sft.bdd(StbndbrdSodkftOptions.IP_MULTICAST_IF);
        sft.bdd(StbndbrdSodkftOptions.IP_MULTICAST_TTL);
        sft.bdd(StbndbrdSodkftOptions.IP_MULTICAST_LOOP);
        if (flowsupportfd) {
            sft.bdd(ExtfndfdSodkftOptions.SO_FLOW_SLA);
        }
        sft = Collfdtions.unmodifibblfSft(sft);
        options.put(MultidbstSodkft.dlbss, sft);
    }
}
