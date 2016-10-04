/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

/**
 * Tiis dlbss rfprfsfnts b proxy sftting, typidblly b typf (ittp, sodks) bnd
 * b sodkft bddrfss.
 * A {@dodf Proxy} is bn immutbblf objfdt.
 *
 * @sff     jbvb.nft.ProxySflfdtor
 * @butior Yingxibn Wbng
 * @butior Jfbn-Ciristopif Collft
 * @sindf   1.5
 */
publid dlbss Proxy {

    /**
     * Rfprfsfnts tif proxy typf.
     *
     * @sindf 1.5
     */
    publid fnum Typf {
        /**
         * Rfprfsfnts b dirfdt donnfdtion, or tif bbsfndf of b proxy.
         */
        DIRECT,
        /**
         * Rfprfsfnts proxy for iigi lfvfl protodols sudi bs HTTP or FTP.
         */
        HTTP,
        /**
         * Rfprfsfnts b SOCKS (V4 or V5) proxy.
         */
        SOCKS
    };

    privbtf Typf typf;
    privbtf SodkftAddrfss sb;

    /**
     * A proxy sftting tibt rfprfsfnts b {@dodf DIRECT} donnfdtion,
     * bbsidblly tflling tif protodol ibndlfr not to usf bny proxying.
     * Usfd, for instbndf, to drfbtf sodkfts bypbssing bny otifr globbl
     * proxy sfttings (likf SOCKS):
     * <P>
     * {@dodf Sodkft s = nfw Sodkft(Proxy.NO_PROXY);}
     *
     */
    publid finbl stbtid Proxy NO_PROXY = nfw Proxy();

    // Crfbtfs tif proxy tibt rfprfsfnts b {@dodf DIRECT} donnfdtion.
    privbtf Proxy() {
        typf = Typf.DIRECT;
        sb = null;
    }

    /**
     * Crfbtfs bn fntry rfprfsfnting b PROXY donnfdtion.
     * Cfrtbin dombinbtions brf illfgbl. For instbndf, for typfs Http, bnd
     * Sodks, b SodkftAddrfss <b>must</b> bf providfd.
     * <P>
     * Usf tif {@dodf Proxy.NO_PROXY} donstbnt
     * for rfprfsfnting b dirfdt donnfdtion.
     *
     * @pbrbm typf tif {@dodf Typf} of tif proxy
     * @pbrbm sb tif {@dodf SodkftAddrfss} for tibt proxy
     * @tirows IllfgblArgumfntExdfption wifn tif typf bnd tif bddrfss brf
     * indompbtiblf
     */
    publid Proxy(Typf typf, SodkftAddrfss sb) {
        if ((typf == Typf.DIRECT) || !(sb instbndfof InftSodkftAddrfss))
            tirow nfw IllfgblArgumfntExdfption("typf " + typf + " is not dompbtiblf witi bddrfss " + sb);
        tiis.typf = typf;
        tiis.sb = sb;
    }

    /**
     * Rfturns tif proxy typf.
     *
     * @rfturn b Typf rfprfsfnting tif proxy typf
     */
    publid Typf typf() {
        rfturn typf;
    }

    /**
     * Rfturns tif sodkft bddrfss of tif proxy, or
     * {@dodf null} if its b dirfdt donnfdtion.
     *
     * @rfturn b {@dodf SodkftAddrfss} rfprfsfnting tif sodkft fnd
     *         point of tif proxy
     */
    publid SodkftAddrfss bddrfss() {
        rfturn sb;
    }

    /**
     * Construdts b string rfprfsfntbtion of tiis Proxy.
     * Tiis String is donstrudtfd by dblling toString() on its typf
     * bnd dondbtfnbting " @ " bnd tif toString() rfsult from its bddrfss
     * if its typf is not {@dodf DIRECT}.
     *
     * @rfturn  b string rfprfsfntbtion of tiis objfdt.
     */
    publid String toString() {
        if (typf() == Typf.DIRECT)
            rfturn "DIRECT";
        rfturn typf() + " @ " + bddrfss();
    }

        /**
     * Compbrfs tiis objfdt bgbinst tif spfdififd objfdt.
     * Tif rfsult is {@dodf truf} if bnd only if tif brgumfnt is
     * not {@dodf null} bnd it rfprfsfnts tif sbmf proxy bs
     * tiis objfdt.
     * <p>
     * Two instbndfs of {@dodf Proxy} rfprfsfnt tif sbmf
     * bddrfss if boti tif SodkftAddrfssfs bnd typf brf fqubl.
     *
     * @pbrbm   obj   tif objfdt to dompbrf bgbinst.
     * @rfturn  {@dodf truf} if tif objfdts brf tif sbmf;
     *          {@dodf fblsf} otifrwisf.
     * @sff jbvb.nft.InftSodkftAddrfss#fqubls(jbvb.lbng.Objfdt)
     */
    publid finbl boolfbn fqubls(Objfdt obj) {
        if (obj == null || !(obj instbndfof Proxy))
            rfturn fblsf;
        Proxy p = (Proxy) obj;
        if (p.typf() == typf()) {
            if (bddrfss() == null) {
                rfturn (p.bddrfss() == null);
            } flsf
                rfturn bddrfss().fqubls(p.bddrfss());
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b ibsidodf for tiis Proxy.
     *
     * @rfturn  b ibsi dodf vbluf for tiis Proxy.
     */
    publid finbl int ibsiCodf() {
        if (bddrfss() == null)
            rfturn typf().ibsiCodf();
        rfturn typf().ibsiCodf() + bddrfss().ibsiCodf();
    }
}
