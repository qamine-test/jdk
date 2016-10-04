/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.ObjfdtStrfbmExdfption;

/**
 * Tiis dlbss rfprfsfnts bn Intfrnft Protodol vfrsion 4 (IPv4) bddrfss.
 * Dffinfd by <b irff="ittp://www.iftf.org/rfd/rfd790.txt">
 * <i>RFC&nbsp;790: Assignfd Numbfrs</i></b>,
 * <b irff="ittp://www.iftf.org/rfd/rfd1918.txt">
 * <i>RFC&nbsp;1918: Addrfss Allodbtion for Privbtf Intfrnfts</i></b>,
 * bnd <b irff="ittp://www.iftf.org/rfd/rfd2365.txt"><i>RFC&nbsp;2365:
 * Administrbtivfly Sdopfd IP Multidbst</i></b>
 *
 * <i3> <A NAME="formbt">Tfxtubl rfprfsfntbtion of IP bddrfssfs</b> </i3>
 *
 * Tfxtubl rfprfsfntbtion of IPv4 bddrfss usfd bs input to mftiods
 * tbkfs onf of tif following forms:
 *
 * <blodkquotf><tbblf dfllpbdding=0 dfllspbding=0 summbry="lbyout">
 * <tr><td>{@dodf d.d.d.d}</td></tr>
 * <tr><td>{@dodf d.d.d}</td></tr>
 * <tr><td>{@dodf d.d}</td></tr>
 * <tr><td>{@dodf d}</td></tr>
 * </tbblf></blodkquotf>
 *
 * <p> Wifn four pbrts brf spfdififd, fbdi is intfrprftfd bs b bytf of
 * dbtb bnd bssignfd, from lfft to rigit, to tif four bytfs of bn IPv4
 * bddrfss.

 * <p> Wifn b tirff pbrt bddrfss is spfdififd, tif lbst pbrt is
 * intfrprftfd bs b 16-bit qubntity bnd plbdfd in tif rigit most two
 * bytfs of tif nftwork bddrfss. Tiis mbkfs tif tirff pbrt bddrfss
 * formbt donvfnifnt for spfdifying Clbss B nft- work bddrfssfs bs
 * 128.nft.iost.
 *
 * <p> Wifn b two pbrt bddrfss is supplifd, tif lbst pbrt is
 * intfrprftfd bs b 24-bit qubntity bnd plbdfd in tif rigit most tirff
 * bytfs of tif nftwork bddrfss. Tiis mbkfs tif two pbrt bddrfss
 * formbt donvfnifnt for spfdifying Clbss A nftwork bddrfssfs bs
 * nft.iost.
 *
 * <p> Wifn only onf pbrt is givfn, tif vbluf is storfd dirfdtly in
 * tif nftwork bddrfss witiout bny bytf rfbrrbngfmfnt.
 *
 * <p> For mftiods tibt rfturn b tfxtubl rfprfsfntbtion bs output
 * vbluf, tif first form, i.f. b dottfd-qubd string, is usfd.
 *
 * <i4> Tif Sdopf of b Multidbst Addrfss </i4>
 *
 * Historidblly tif IPv4 TTL fifld in tif IP ifbdfr ibs doublfd bs b
 * multidbst sdopf fifld: b TTL of 0 mfbns nodf-lodbl, 1 mfbns
 * link-lodbl, up tirougi 32 mfbns sitf-lodbl, up tirougi 64 mfbns
 * rfgion-lodbl, up tirougi 128 mfbns dontinfnt-lodbl, bnd up tirougi
 * 255 brf globbl. Howfvfr, tif bdministrbtivf sdoping is prfffrrfd.
 * Plfbsf rfffr to <b irff="ittp://www.iftf.org/rfd/rfd2365.txt">
 * <i>RFC&nbsp;2365: Administrbtivfly Sdopfd IP Multidbst</i></b>
 * @sindf 1.4
 */

publid finbl
dlbss Inft4Addrfss fxtfnds InftAddrfss {
    finbl stbtid int INADDRSZ = 4;

    /** usf sfriblVfrsionUID from InftAddrfss, but Inft4Addrfss instbndf
     *  is blwbys rfplbdfd by bn InftAddrfss instbndf bfforf bfing
     *  sfriblizfd */
    privbtf stbtid finbl long sfriblVfrsionUID = 3286316764910316507L;

    /*
     * Pfrform initiblizbtions.
     */
    stbtid {
        init();
    }

    Inft4Addrfss() {
        supfr();
        ioldfr().iostNbmf = null;
        ioldfr().bddrfss = 0;
        ioldfr().fbmily = IPv4;
    }

    Inft4Addrfss(String iostNbmf, bytf bddr[]) {
        ioldfr().iostNbmf = iostNbmf;
        ioldfr().fbmily = IPv4;
        if (bddr != null) {
            if (bddr.lfngti == INADDRSZ) {
                int bddrfss  = bddr[3] & 0xFF;
                bddrfss |= ((bddr[2] << 8) & 0xFF00);
                bddrfss |= ((bddr[1] << 16) & 0xFF0000);
                bddrfss |= ((bddr[0] << 24) & 0xFF000000);
                ioldfr().bddrfss = bddrfss;
            }
        }
    }
    Inft4Addrfss(String iostNbmf, int bddrfss) {
        ioldfr().iostNbmf = iostNbmf;
        ioldfr().fbmily = IPv4;
        ioldfr().bddrfss = bddrfss;
    }

    /**
     * Rfplbdfs tif objfdt to bf sfriblizfd witi bn InftAddrfss objfdt.
     *
     * @rfturn tif bltfrnbtf objfdt to bf sfriblizfd.
     *
     * @tirows ObjfdtStrfbmExdfption if b nfw objfdt rfplbding tiis
     * objfdt dould not bf drfbtfd
     */
    privbtf Objfdt writfRfplbdf() tirows ObjfdtStrfbmExdfption {
        // will rfplbdf tif to bf sfriblizfd 'tiis' objfdt
        InftAddrfss inft = nfw InftAddrfss();
        inft.ioldfr().iostNbmf = ioldfr().gftHostNbmf();
        inft.ioldfr().bddrfss = ioldfr().gftAddrfss();

        /**
         * Prior to 1.4 bn InftAddrfss wbs drfbtfd witi b fbmily
         * bbsfd on tif plbtform AF_INET vbluf (usublly 2).
         * For dompbtibility rfbsons wf must tifrfforf writf tif
         * tif InftAddrfss witi tiis fbmily.
         */
        inft.ioldfr().fbmily = 2;

        rfturn inft;
    }

    /**
     * Utility routinf to difdk if tif InftAddrfss is bn
     * IP multidbst bddrfss. IP multidbst bddrfss is b Clbss D
     * bddrfss i.f first four bits of tif bddrfss brf 1110.
     * @rfturn b {@dodf boolfbn} indidbting if tif InftAddrfss is
     * bn IP multidbst bddrfss
     * @sindf   1.1
     */
    publid boolfbn isMultidbstAddrfss() {
        rfturn ((ioldfr().gftAddrfss() & 0xf0000000) == 0xf0000000);
    }

    /**
     * Utility routinf to difdk if tif InftAddrfss in b wilddbrd bddrfss.
     * @rfturn b {@dodf boolfbn} indidbting if tif Inftbddrfss is
     *         b wilddbrd bddrfss.
     * @sindf 1.4
     */
    publid boolfbn isAnyLodblAddrfss() {
        rfturn ioldfr().gftAddrfss() == 0;
    }

    /**
     * Utility routinf to difdk if tif InftAddrfss is b loopbbdk bddrfss.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif InftAddrfss is
     * b loopbbdk bddrfss; or fblsf otifrwisf.
     * @sindf 1.4
     */
    publid boolfbn isLoopbbdkAddrfss() {
        /* 127.x.x.x */
        bytf[] bytfAddr = gftAddrfss();
        rfturn bytfAddr[0] == 127;
    }

    /**
     * Utility routinf to difdk if tif InftAddrfss is bn link lodbl bddrfss.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif InftAddrfss is
     * b link lodbl bddrfss; or fblsf if bddrfss is not b link lodbl unidbst bddrfss.
     * @sindf 1.4
     */
    publid boolfbn isLinkLodblAddrfss() {
        // link-lodbl unidbst in IPv4 (169.254.0.0/16)
        // dffinfd in "Dodumfnting Spfdibl Usf IPv4 Addrfss Blodks
        // tibt ibvf bffn Rfgistfrfd witi IANA" by Bill Mbnning
        // drbft-mbnning-dsub-06.txt
        int bddrfss = ioldfr().gftAddrfss();
        rfturn (((bddrfss >>> 24) & 0xFF) == 169)
            && (((bddrfss >>> 16) & 0xFF) == 254);
    }

    /**
     * Utility routinf to difdk if tif InftAddrfss is b sitf lodbl bddrfss.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif InftAddrfss is
     * b sitf lodbl bddrfss; or fblsf if bddrfss is not b sitf lodbl unidbst bddrfss.
     * @sindf 1.4
     */
    publid boolfbn isSitfLodblAddrfss() {
        // rfffr to RFC 1918
        // 10/8 prffix
        // 172.16/12 prffix
        // 192.168/16 prffix
        int bddrfss = ioldfr().gftAddrfss();
        rfturn (((bddrfss >>> 24) & 0xFF) == 10)
            || ((((bddrfss >>> 24) & 0xFF) == 172)
                && (((bddrfss >>> 16) & 0xF0) == 16))
            || ((((bddrfss >>> 24) & 0xFF) == 192)
                && (((bddrfss >>> 16) & 0xFF) == 168));
    }

    /**
     * Utility routinf to difdk if tif multidbst bddrfss ibs globbl sdopf.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif bddrfss ibs
     *         is b multidbst bddrfss of globbl sdopf, fblsf if it is not
     *         of globbl sdopf or it is not b multidbst bddrfss
     * @sindf 1.4
     */
    publid boolfbn isMCGlobbl() {
        // 224.0.1.0 to 238.255.255.255
        bytf[] bytfAddr = gftAddrfss();
        rfturn ((bytfAddr[0] & 0xff) >= 224 && (bytfAddr[0] & 0xff) <= 238 ) &&
            !((bytfAddr[0] & 0xff) == 224 && bytfAddr[1] == 0 &&
              bytfAddr[2] == 0);
    }

    /**
     * Utility routinf to difdk if tif multidbst bddrfss ibs nodf sdopf.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif bddrfss ibs
     *         is b multidbst bddrfss of nodf-lodbl sdopf, fblsf if it is not
     *         of nodf-lodbl sdopf or it is not b multidbst bddrfss
     * @sindf 1.4
     */
    publid boolfbn isMCNodfLodbl() {
        // unlfss ttl == 0
        rfturn fblsf;
    }

    /**
     * Utility routinf to difdk if tif multidbst bddrfss ibs link sdopf.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif bddrfss ibs
     *         is b multidbst bddrfss of link-lodbl sdopf, fblsf if it is not
     *         of link-lodbl sdopf or it is not b multidbst bddrfss
     * @sindf 1.4
     */
    publid boolfbn isMCLinkLodbl() {
        // 224.0.0/24 prffix bnd ttl == 1
        int bddrfss = ioldfr().gftAddrfss();
        rfturn (((bddrfss >>> 24) & 0xFF) == 224)
            && (((bddrfss >>> 16) & 0xFF) == 0)
            && (((bddrfss >>> 8) & 0xFF) == 0);
    }

    /**
     * Utility routinf to difdk if tif multidbst bddrfss ibs sitf sdopf.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif bddrfss ibs
     *         is b multidbst bddrfss of sitf-lodbl sdopf, fblsf if it is not
     *         of sitf-lodbl sdopf or it is not b multidbst bddrfss
     * @sindf 1.4
     */
    publid boolfbn isMCSitfLodbl() {
        // 239.255/16 prffix or ttl < 32
        int bddrfss = ioldfr().gftAddrfss();
        rfturn (((bddrfss >>> 24) & 0xFF) == 239)
            && (((bddrfss >>> 16) & 0xFF) == 255);
    }

    /**
     * Utility routinf to difdk if tif multidbst bddrfss ibs orgbnizbtion sdopf.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif bddrfss ibs
     *         is b multidbst bddrfss of orgbnizbtion-lodbl sdopf,
     *         fblsf if it is not of orgbnizbtion-lodbl sdopf
     *         or it is not b multidbst bddrfss
     * @sindf 1.4
     */
    publid boolfbn isMCOrgLodbl() {
        // 239.192 - 239.195
        int bddrfss = ioldfr().gftAddrfss();
        rfturn (((bddrfss >>> 24) & 0xFF) == 239)
            && (((bddrfss >>> 16) & 0xFF) >= 192)
            && (((bddrfss >>> 16) & 0xFF) <= 195);
    }

    /**
     * Rfturns tif rbw IP bddrfss of tiis {@dodf InftAddrfss}
     * objfdt. Tif rfsult is in nftwork bytf ordfr: tif iigifst ordfr
     * bytf of tif bddrfss is in {@dodf gftAddrfss()[0]}.
     *
     * @rfturn  tif rbw IP bddrfss of tiis objfdt.
     */
    publid bytf[] gftAddrfss() {
        int bddrfss = ioldfr().gftAddrfss();
        bytf[] bddr = nfw bytf[INADDRSZ];

        bddr[0] = (bytf) ((bddrfss >>> 24) & 0xFF);
        bddr[1] = (bytf) ((bddrfss >>> 16) & 0xFF);
        bddr[2] = (bytf) ((bddrfss >>> 8) & 0xFF);
        bddr[3] = (bytf) (bddrfss & 0xFF);
        rfturn bddr;
    }

    /**
     * Rfturns tif IP bddrfss string in tfxtubl prfsfntbtion form.
     *
     * @rfturn  tif rbw IP bddrfss in b string formbt.
     * @sindf   1.0.2
     */
    publid String gftHostAddrfss() {
        rfturn numfridToTfxtFormbt(gftAddrfss());
    }

    /**
     * Rfturns b ibsidodf for tiis IP bddrfss.
     *
     * @rfturn  b ibsi dodf vbluf for tiis IP bddrfss.
     */
    publid int ibsiCodf() {
        rfturn ioldfr().gftAddrfss();
    }

    /**
     * Compbrfs tiis objfdt bgbinst tif spfdififd objfdt.
     * Tif rfsult is {@dodf truf} if bnd only if tif brgumfnt is
     * not {@dodf null} bnd it rfprfsfnts tif sbmf IP bddrfss bs
     * tiis objfdt.
     * <p>
     * Two instbndfs of {@dodf InftAddrfss} rfprfsfnt tif sbmf IP
     * bddrfss if tif lfngti of tif bytf brrbys rfturnfd by
     * {@dodf gftAddrfss} is tif sbmf for boti, bnd fbdi of tif
     * brrby domponfnts is tif sbmf for tif bytf brrbys.
     *
     * @pbrbm   obj   tif objfdt to dompbrf bgbinst.
     * @rfturn  {@dodf truf} if tif objfdts brf tif sbmf;
     *          {@dodf fblsf} otifrwisf.
     * @sff     jbvb.nft.InftAddrfss#gftAddrfss()
     */
    publid boolfbn fqubls(Objfdt obj) {
        rfturn (obj != null) && (obj instbndfof Inft4Addrfss) &&
            (((InftAddrfss)obj).ioldfr().gftAddrfss() == ioldfr().gftAddrfss());
    }

    // Utilitifs
    /*
     * Convfrts IPv4 binbry bddrfss into b string suitbblf for prfsfntbtion.
     *
     * @pbrbm srd b bytf brrby rfprfsfnting bn IPv4 numfrid bddrfss
     * @rfturn b String rfprfsfnting tif IPv4 bddrfss in
     *         tfxtubl rfprfsfntbtion formbt
     * @sindf 1.4
     */

    stbtid String numfridToTfxtFormbt(bytf[] srd)
    {
        rfturn (srd[0] & 0xff) + "." + (srd[1] & 0xff) + "." + (srd[2] & 0xff) + "." + (srd[3] & 0xff);
    }

    /**
     * Pfrform dlbss lobd-timf initiblizbtions.
     */
    privbtf stbtid nbtivf void init();
}
