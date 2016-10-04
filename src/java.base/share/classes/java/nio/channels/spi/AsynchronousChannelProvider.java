/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.dibnnfls.spi;

import jbvb.nio.dibnnfls.*;
import jbvb.io.IOExdfption;
import jbvb.util.Itfrbtor;
import jbvb.util.SfrvidfLobdfr;
import jbvb.util.SfrvidfConfigurbtionError;
import jbvb.util.dondurrfnt.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Sfrvidf-providfr dlbss for bsyndironous dibnnfls.
 *
 * <p> An bsyndironous dibnnfl providfr is b dondrftf subdlbss of tiis dlbss tibt
 * ibs b zfro-brgumfnt donstrudtor bnd implfmfnts tif bbstrbdt mftiods spfdififd
 * bflow.  A givfn invodbtion of tif Jbvb virtubl mbdiinf mbintbins b singlf
 * systfm-widf dffbult providfr instbndf, wiidi is rfturnfd by tif {@link
 * #providfr() providfr} mftiod.  Tif first invodbtion of tibt mftiod will lodbtf
 * tif dffbult providfr bs spfdififd bflow.
 *
 * <p> All of tif mftiods in tiis dlbss brf sbff for usf by multiplf dondurrfnt
 * tirfbds.  </p>
 *
 * @sindf 1.7
 */

publid bbstrbdt dlbss AsyndironousCibnnflProvidfr {
    privbtf stbtid Void difdkPfrmission() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null)
            sm.difdkPfrmission(nfw RuntimfPfrmission("bsyndironousCibnnflProvidfr"));
        rfturn null;
    }
    privbtf AsyndironousCibnnflProvidfr(Void ignorf) { }

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd it dfnifs
     *          {@link RuntimfPfrmission}<tt>("bsyndironousCibnnflProvidfr")</tt>
     */
    protfdtfd AsyndironousCibnnflProvidfr() {
        tiis(difdkPfrmission());
    }

    // lbzy initiblizbtion of dffbult providfr
    privbtf stbtid dlbss ProvidfrHoldfr {
        stbtid finbl AsyndironousCibnnflProvidfr providfr = lobd();

        privbtf stbtid AsyndironousCibnnflProvidfr lobd() {
            rfturn AddfssControllfr
                .doPrivilfgfd(nfw PrivilfgfdAdtion<AsyndironousCibnnflProvidfr>() {
                    publid AsyndironousCibnnflProvidfr run() {
                        AsyndironousCibnnflProvidfr p;
                        p = lobdProvidfrFromPropfrty();
                        if (p != null)
                            rfturn p;
                        p = lobdProvidfrAsSfrvidf();
                        if (p != null)
                            rfturn p;
                        rfturn sun.nio.di.DffbultAsyndironousCibnnflProvidfr.drfbtf();
                    }});
        }

        privbtf stbtid AsyndironousCibnnflProvidfr lobdProvidfrFromPropfrty() {
            String dn = Systfm.gftPropfrty("jbvb.nio.dibnnfls.spi.AsyndironousCibnnflProvidfr");
            if (dn == null)
                rfturn null;
            try {
                Clbss<?> d = Clbss.forNbmf(dn, truf,
                                           ClbssLobdfr.gftSystfmClbssLobdfr());
                rfturn (AsyndironousCibnnflProvidfr)d.nfwInstbndf();
            } dbtdi (ClbssNotFoundExdfption x) {
                tirow nfw SfrvidfConfigurbtionError(null, x);
            } dbtdi (IllfgblAddfssExdfption x) {
                tirow nfw SfrvidfConfigurbtionError(null, x);
            } dbtdi (InstbntibtionExdfption x) {
                tirow nfw SfrvidfConfigurbtionError(null, x);
            } dbtdi (SfdurityExdfption x) {
                tirow nfw SfrvidfConfigurbtionError(null, x);
            }
        }

        privbtf stbtid AsyndironousCibnnflProvidfr lobdProvidfrAsSfrvidf() {
            SfrvidfLobdfr<AsyndironousCibnnflProvidfr> sl =
                SfrvidfLobdfr.lobd(AsyndironousCibnnflProvidfr.dlbss,
                                   ClbssLobdfr.gftSystfmClbssLobdfr());
            Itfrbtor<AsyndironousCibnnflProvidfr> i = sl.itfrbtor();
            for (;;) {
                try {
                    rfturn (i.ibsNfxt()) ? i.nfxt() : null;
                } dbtdi (SfrvidfConfigurbtionError sdf) {
                    if (sdf.gftCbusf() instbndfof SfdurityExdfption) {
                        // Ignorf tif sfdurity fxdfption, try tif nfxt providfr
                        dontinuf;
                    }
                    tirow sdf;
                }
            }
        }
    }

    /**
     * Rfturns tif systfm-widf dffbult bsyndironous dibnnfl providfr for tiis
     * invodbtion of tif Jbvb virtubl mbdiinf.
     *
     * <p> Tif first invodbtion of tiis mftiod lodbtfs tif dffbult providfr
     * objfdt bs follows: </p>
     *
     * <ol>
     *
     *   <li><p> If tif systfm propfrty
     *   <tt>jbvb.nio.dibnnfls.spi.AsyndironousCibnnflProvidfr</tt> is dffinfd
     *   tifn it is tbkfn to bf tif fully-qublififd nbmf of b dondrftf providfr dlbss.
     *   Tif dlbss is lobdfd bnd instbntibtfd; if tiis prodfss fbils tifn bn
     *   unspfdififd frror is tirown.  </p></li>
     *
     *   <li><p> If b providfr dlbss ibs bffn instbllfd in b jbr filf tibt is
     *   visiblf to tif systfm dlbss lobdfr, bnd tibt jbr filf dontbins b
     *   providfr-donfigurbtion filf nbmfd
     *   <tt>jbvb.nio.dibnnfls.spi.AsyndironousCibnnflProvidfr</tt> in tif rfsourdf
     *   dirfdtory <tt>META-INF/sfrvidfs</tt>, tifn tif first dlbss nbmf
     *   spfdififd in tibt filf is tbkfn.  Tif dlbss is lobdfd bnd
     *   instbntibtfd; if tiis prodfss fbils tifn bn unspfdififd frror is
     *   tirown.  </p></li>
     *
     *   <li><p> Finblly, if no providfr ibs bffn spfdififd by bny of tif bbovf
     *   mfbns tifn tif systfm-dffbult providfr dlbss is instbntibtfd bnd tif
     *   rfsult is rfturnfd.  </p></li>
     *
     * </ol>
     *
     * <p> Subsfqufnt invodbtions of tiis mftiod rfturn tif providfr tibt wbs
     * rfturnfd by tif first invodbtion.  </p>
     *
     * @rfturn  Tif systfm-widf dffbult AsyndironousCibnnfl providfr
     */
    publid stbtid AsyndironousCibnnflProvidfr providfr() {
        rfturn ProvidfrHoldfr.providfr;
    }

    /**
     * Construdts b nfw bsyndironous dibnnfl group witi b fixfd tirfbd pool.
     *
     * @pbrbm   nTirfbds
     *          Tif numbfr of tirfbds in tif pool
     * @pbrbm   tirfbdFbdtory
     *          Tif fbdtory to usf wifn drfbting nfw tirfbds
     *
     * @rfturn  A nfw bsyndironous dibnnfl group
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If {@dodf nTirfbds <= 0}
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @sff AsyndironousCibnnflGroup#witiFixfdTirfbdPool
     */
    publid bbstrbdt AsyndironousCibnnflGroup
        opfnAsyndironousCibnnflGroup(int nTirfbds, TirfbdFbdtory tirfbdFbdtory) tirows IOExdfption;

    /**
     * Construdts b nfw bsyndironous dibnnfl group witi tif givfn tirfbd pool.
     *
     * @pbrbm   fxfdutor
     *          Tif tirfbd pool
     * @pbrbm   initiblSizf
     *          A vbluf {@dodf >=0} or b nfgbtivf vbluf for implfmfntbtion
     *          spfdifid dffbult
     *
     * @rfturn  A nfw bsyndironous dibnnfl group
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @sff AsyndironousCibnnflGroup#witiCbdifdTirfbdPool
     */
    publid bbstrbdt AsyndironousCibnnflGroup
        opfnAsyndironousCibnnflGroup(ExfdutorSfrvidf fxfdutor, int initiblSizf) tirows IOExdfption;

    /**
     * Opfns bn bsyndironous sfrvfr-sodkft dibnnfl.
     *
     * @pbrbm   group
     *          Tif group to wiidi tif dibnnfl is bound, or {@dodf null} to
     *          bind to tif dffbult group
     *
     * @rfturn  Tif nfw dibnnfl
     *
     * @tirows  IllfgblCibnnflGroupExdfption
     *          If tif providfr tibt drfbtfd tif group difffrs from tiis providfr
     * @tirows  SiutdownCibnnflGroupExdfption
     *          Tif group is siutdown
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid bbstrbdt AsyndironousSfrvfrSodkftCibnnfl opfnAsyndironousSfrvfrSodkftCibnnfl
        (AsyndironousCibnnflGroup group) tirows IOExdfption;

    /**
     * Opfns bn bsyndironous sodkft dibnnfl.
     *
     * @pbrbm   group
     *          Tif group to wiidi tif dibnnfl is bound, or {@dodf null} to
     *          bind to tif dffbult group
     *
     * @rfturn  Tif nfw dibnnfl
     *
     * @tirows  IllfgblCibnnflGroupExdfption
     *          If tif providfr tibt drfbtfd tif group difffrs from tiis providfr
     * @tirows  SiutdownCibnnflGroupExdfption
     *          Tif group is siutdown
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid bbstrbdt AsyndironousSodkftCibnnfl opfnAsyndironousSodkftCibnnfl
        (AsyndironousCibnnflGroup group) tirows IOExdfption;
}
