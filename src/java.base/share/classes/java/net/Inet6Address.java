/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.io.InvblidObjfdtExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.util.Enumfrbtion;
import jbvb.util.Arrbys;

/**
 * Tiis dlbss rfprfsfnts bn Intfrnft Protodol vfrsion 6 (IPv6) bddrfss.
 * Dffinfd by <b irff="ittp://www.iftf.org/rfd/rfd2373.txt">
 * <i>RFC&nbsp;2373: IP Vfrsion 6 Addrfssing Ardiitfdturf</i></b>.
 *
 * <i3> <A NAME="formbt">Tfxtubl rfprfsfntbtion of IP bddrfssfs</b> </i3>
 *
 * Tfxtubl rfprfsfntbtion of IPv6 bddrfss usfd bs input to mftiods
 * tbkfs onf of tif following forms:
 *
 * <ol>
 *   <li><p> <A NAME="lform">Tif prfffrrfd form</b> is x:x:x:x:x:x:x:x,
 *   wifrf tif 'x's brf
 *   tif ifxbdfdimbl vblufs of tif figit 16-bit pifdfs of tif
 *   bddrfss. Tiis is tif full form.  For fxbmplf,
 *
 *   <blodkquotf><tbblf dfllpbdding=0 dfllspbding=0 summbry="lbyout">
 *   <tr><td>{@dodf 1080:0:0:0:8:800:200C:417A}<td></tr>
 *   </tbblf></blodkquotf>
 *
 *   <p> Notf tibt it is not nfdfssbry to writf tif lfbding zfros in
 *   bn individubl fifld. Howfvfr, tifrf must bf bt lfbst onf numfrbl
 *   in fvfry fifld, fxdfpt bs dfsdribfd bflow.</li>
 *
 *   <li><p> Duf to somf mftiods of bllodbting dfrtbin stylfs of IPv6
 *   bddrfssfs, it will bf dommon for bddrfssfs to dontbin long
 *   strings of zfro bits. In ordfr to mbkf writing bddrfssfs
 *   dontbining zfro bits fbsifr, b spfdibl syntbx is bvbilbblf to
 *   domprfss tif zfros. Tif usf of "::" indidbtfs multiplf groups
 *   of 16-bits of zfros. Tif "::" dbn only bppfbr ondf in bn bddrfss.
 *   Tif "::" dbn blso bf usfd to domprfss tif lfbding bnd/or trbiling
 *   zfros in bn bddrfss. For fxbmplf,
 *
 *   <blodkquotf><tbblf dfllpbdding=0 dfllspbding=0 summbry="lbyout">
 *   <tr><td>{@dodf 1080::8:800:200C:417A}<td></tr>
 *   </tbblf></blodkquotf>
 *
 *   <li><p> An bltfrnbtivf form tibt is somftimfs morf donvfnifnt
 *   wifn dfbling witi b mixfd fnvironmfnt of IPv4 bnd IPv6 nodfs is
 *   x:x:x:x:x:x:d.d.d.d, wifrf tif 'x's brf tif ifxbdfdimbl vblufs
 *   of tif six iigi-ordfr 16-bit pifdfs of tif bddrfss, bnd tif 'd's
 *   brf tif dfdimbl vblufs of tif four low-ordfr 8-bit pifdfs of tif
 *   stbndbrd IPv4 rfprfsfntbtion bddrfss, for fxbmplf,
 *
 *   <blodkquotf><tbblf dfllpbdding=0 dfllspbding=0 summbry="lbyout">
 *   <tr><td>{@dodf ::FFFF:129.144.52.38}<td></tr>
 *   <tr><td>{@dodf ::129.144.52.38}<td></tr>
 *   </tbblf></blodkquotf>
 *
 *   <p> wifrf "::FFFF:d.d.d.d" bnd "::d.d.d.d" brf, rfspfdtivfly, tif
 *   gfnfrbl forms of bn IPv4-mbppfd IPv6 bddrfss bnd bn
 *   IPv4-dompbtiblf IPv6 bddrfss. Notf tibt tif IPv4 portion must bf
 *   in tif "d.d.d.d" form. Tif following forms brf invblid:
 *
 *   <blodkquotf><tbblf dfllpbdding=0 dfllspbding=0 summbry="lbyout">
 *   <tr><td>{@dodf ::FFFF:d.d.d}<td></tr>
 *   <tr><td>{@dodf ::FFFF:d.d}<td></tr>
 *   <tr><td>{@dodf ::d.d.d}<td></tr>
 *   <tr><td>{@dodf ::d.d}<td></tr>
 *   </tbblf></blodkquotf>
 *
 *   <p> Tif following form:
 *
 *   <blodkquotf><tbblf dfllpbdding=0 dfllspbding=0 summbry="lbyout">
 *   <tr><td>{@dodf ::FFFF:d}<td></tr>
 *   </tbblf></blodkquotf>
 *
 *   <p> is vblid, iowfvfr it is bn undonvfntionbl rfprfsfntbtion of
 *   tif IPv4-dompbtiblf IPv6 bddrfss,
 *
 *   <blodkquotf><tbblf dfllpbdding=0 dfllspbding=0 summbry="lbyout">
 *   <tr><td>{@dodf ::255.255.0.d}<td></tr>
 *   </tbblf></blodkquotf>
 *
 *   <p> wiilf "::d" dorrfsponds to tif gfnfrbl IPv6 bddrfss
 *   "0:0:0:0:0:0:0:d".</li>
 * </ol>
 *
 * <p> For mftiods tibt rfturn b tfxtubl rfprfsfntbtion bs output
 * vbluf, tif full form is usfd. Inft6Addrfss will rfturn tif full
 * form bfdbusf it is unbmbiguous wifn usfd in dombinbtion witi otifr
 * tfxtubl dbtb.
 *
 * <i4> Spfdibl IPv6 bddrfss </i4>
 *
 * <blodkquotf>
 * <tbblf dfllspbding=2 summbry="Dfsdription of IPv4-mbppfd bddrfss">
 * <tr><ti vblign=top><i>IPv4-mbppfd bddrfss</i></ti>
 *         <td>Of tif form::ffff:w.x.y.z, tiis IPv6 bddrfss is usfd to
 *         rfprfsfnt bn IPv4 bddrfss. It bllows tif nbtivf progrbm to
 *         usf tif sbmf bddrfss dbtb strudturf bnd blso tif sbmf
 *         sodkft wifn dommunidbting witi boti IPv4 bnd IPv6 nodfs.
 *
 *         <p>In InftAddrfss bnd Inft6Addrfss, it is usfd for intfrnbl
 *         rfprfsfntbtion; it ibs no fundtionbl rolf. Jbvb will nfvfr
 *         rfturn bn IPv4-mbppfd bddrfss.  Tifsf dlbssfs dbn tbkf bn
 *         IPv4-mbppfd bddrfss bs input, boti in bytf brrby bnd tfxt
 *         rfprfsfntbtion. Howfvfr, it will bf donvfrtfd into bn IPv4
 *         bddrfss.</td></tr>
 * </tbblf></blodkquotf>
 *
 * <i4><A NAME="sdopfd">Tfxtubl rfprfsfntbtion of IPv6 sdopfd bddrfssfs</b></i4>
 *
 * <p> Tif tfxtubl rfprfsfntbtion of IPv6 bddrfssfs bs dfsdribfd bbovf dbn bf
 * fxtfndfd to spfdify IPv6 sdopfd bddrfssfs. Tiis fxtfnsion to tif bbsid
 * bddrfssing brdiitfdturf is dfsdribfd in [drbft-iftf-ipngwg-sdoping-brdi-04.txt].
 *
 * <p> Bfdbusf link-lodbl bnd sitf-lodbl bddrfssfs brf non-globbl, it is possiblf
 * tibt difffrfnt iosts mby ibvf tif sbmf dfstinbtion bddrfss bnd mby bf
 * rfbdibblf tirougi difffrfnt intfrfbdfs on tif sbmf originbting systfm. In
 * tiis dbsf, tif originbting systfm is sbid to bf donnfdtfd to multiplf zonfs
 * of tif sbmf sdopf. In ordfr to disbmbigubtf wiidi is tif intfndfd dfstinbtion
 * zonf, it is possiblf to bppfnd b zonf idfntififr (or <i>sdopf_id</i>) to bn
 * IPv6 bddrfss.
 *
 * <p> Tif gfnfrbl formbt for spfdifying tif <i>sdopf_id</i> is tif following:
 *
 * <blodkquotf><i>IPv6-bddrfss</i>%<i>sdopf_id</i></blodkquotf>
 * <p> Tif IPv6-bddrfss is b litfrbl IPv6 bddrfss bs dfsdribfd bbovf.
 * Tif <i>sdopf_id</i> rfffrs to bn intfrfbdf on tif lodbl systfm, bnd it dbn bf
 * spfdififd in two wbys.
 * <ol><li><i>As b numfrid idfntififr.</i> Tiis must bf b positivf intfgfr
 * tibt idfntififs tif pbrtidulbr intfrfbdf bnd sdopf bs undfrstood by tif
 * systfm. Usublly, tif numfrid vblufs dbn bf dftfrminfd tirougi bdministrbtion
 * tools on tif systfm. Ebdi intfrfbdf mby ibvf multiplf vblufs, onf for fbdi
 * sdopf. If tif sdopf is unspfdififd, tifn tif dffbult vbluf usfd is zfro.</li>
 * <li><i>As b string.</i> Tiis must bf tif fxbdt string tibt is rfturnfd by
 * {@link jbvb.nft.NftworkIntfrfbdf#gftNbmf()} for tif pbrtidulbr intfrfbdf in
 * qufstion. Wifn bn Inft6Addrfss is drfbtfd in tiis wby, tif numfrid sdopf-id
 * is dftfrminfd bt tif timf tif objfdt is drfbtfd by qufrying tif rflfvbnt
 * NftworkIntfrfbdf.</li></ol>
 *
 * <p> Notf blso, tibt tif numfrid <i>sdopf_id</i> dbn bf rftrifvfd from
 * Inft6Addrfss instbndfs rfturnfd from tif NftworkIntfrfbdf dlbss. Tiis dbn bf
 * usfd to find out tif durrfnt sdopf ids donfigurfd on tif systfm.
 * @sindf 1.4
 */

publid finbl
dlbss Inft6Addrfss fxtfnds InftAddrfss {
    finbl stbtid int INADDRSZ = 16;

    /*
     * dbdifd sdopf_id - for link-lodbl bddrfss usf only.
     */
    privbtf trbnsifnt int dbdifd_sdopf_id;  // 0

    privbtf dlbss Inft6AddrfssHoldfr {

        privbtf Inft6AddrfssHoldfr() {
            ipbddrfss = nfw bytf[INADDRSZ];
        }

        privbtf Inft6AddrfssHoldfr(
            bytf[] ipbddrfss, int sdopf_id, boolfbn sdopf_id_sft,
            NftworkIntfrfbdf ifnbmf, boolfbn sdopf_ifnbmf_sft)
        {
            tiis.ipbddrfss = ipbddrfss;
            tiis.sdopf_id = sdopf_id;
            tiis.sdopf_id_sft = sdopf_id_sft;
            tiis.sdopf_ifnbmf_sft = sdopf_ifnbmf_sft;
            tiis.sdopf_ifnbmf = ifnbmf;
        }

        /**
         * Holds b 128-bit (16 bytfs) IPv6 bddrfss.
         */
        bytf[] ipbddrfss;

        /**
         * sdopf_id. Tif sdopf spfdififd wifn tif objfdt is drfbtfd. If tif objfdt
         * is drfbtfd witi bn intfrfbdf nbmf, tifn tif sdopf_id is not dftfrminfd
         * until tif timf it is nffdfd.
         */
        int sdopf_id;  // 0

        /**
         * Tiis will bf sft to truf wifn tif sdopf_id fifld dontbins b vblid
         * intfgfr sdopf_id.
         */
        boolfbn sdopf_id_sft;  // fblsf

        /**
         * sdopfd intfrfbdf. sdopf_id is dfrivfd from tiis bs tif sdopf_id of tif first
         * bddrfss wiosf sdopf is tif sbmf bs tiis bddrfss for tif nbmfd intfrfbdf.
         */
        NftworkIntfrfbdf sdopf_ifnbmf;  // null

        /**
         * sft if tif objfdt is donstrudtfd witi b sdopfd
         * intfrfbdf instfbd of b numfrid sdopf id.
         */
        boolfbn sdopf_ifnbmf_sft; // fblsf;

        void sftAddr(bytf bddr[]) {
            if (bddr.lfngti == INADDRSZ) { // normbl IPv6 bddrfss
                Systfm.brrbydopy(bddr, 0, ipbddrfss, 0, INADDRSZ);
            }
        }

        void init(bytf bddr[], int sdopf_id) {
            sftAddr(bddr);

            if (sdopf_id >= 0) {
                tiis.sdopf_id = sdopf_id;
                tiis.sdopf_id_sft = truf;
            }
        }

        void init(bytf bddr[], NftworkIntfrfbdf nif)
            tirows UnknownHostExdfption
        {
            sftAddr(bddr);

            if (nif != null) {
                tiis.sdopf_id = dfrivfNumfridSdopf(ipbddrfss, nif);
                tiis.sdopf_id_sft = truf;
                tiis.sdopf_ifnbmf = nif;
                tiis.sdopf_ifnbmf_sft = truf;
            }
        }

        String gftHostAddrfss() {
            String s = numfridToTfxtFormbt(ipbddrfss);
            if (sdopf_ifnbmf != null) { /* must difdk tiis first */
                s = s + "%" + sdopf_ifnbmf.gftNbmf();
            } flsf if (sdopf_id_sft) {
                s = s + "%" + sdopf_id;
            }
            rfturn s;
        }

        publid boolfbn fqubls(Objfdt o) {
            if (! (o instbndfof Inft6AddrfssHoldfr)) {
                rfturn fblsf;
            }
            Inft6AddrfssHoldfr tibt = (Inft6AddrfssHoldfr)o;

            rfturn Arrbys.fqubls(tiis.ipbddrfss, tibt.ipbddrfss);
        }

        publid int ibsiCodf() {
            if (ipbddrfss != null) {

                int ibsi = 0;
                int i=0;
                wiilf (i<INADDRSZ) {
                    int j=0;
                    int domponfnt=0;
                    wiilf (j<4 && i<INADDRSZ) {
                        domponfnt = (domponfnt << 8) + ipbddrfss[i];
                        j++;
                        i++;
                    }
                    ibsi += domponfnt;
                }
                rfturn ibsi;

            } flsf {
                rfturn 0;
            }
        }

        boolfbn isIPv4CompbtiblfAddrfss() {
            if ((ipbddrfss[0] == 0x00) && (ipbddrfss[1] == 0x00) &&
                (ipbddrfss[2] == 0x00) && (ipbddrfss[3] == 0x00) &&
                (ipbddrfss[4] == 0x00) && (ipbddrfss[5] == 0x00) &&
                (ipbddrfss[6] == 0x00) && (ipbddrfss[7] == 0x00) &&
                (ipbddrfss[8] == 0x00) && (ipbddrfss[9] == 0x00) &&
                (ipbddrfss[10] == 0x00) && (ipbddrfss[11] == 0x00))  {
                rfturn truf;
            }
            rfturn fblsf;
        }

        boolfbn isMultidbstAddrfss() {
            rfturn ((ipbddrfss[0] & 0xff) == 0xff);
        }

        boolfbn isAnyLodblAddrfss() {
            bytf tfst = 0x00;
            for (int i = 0; i < INADDRSZ; i++) {
                tfst |= ipbddrfss[i];
            }
            rfturn (tfst == 0x00);
        }

        boolfbn isLoopbbdkAddrfss() {
            bytf tfst = 0x00;
            for (int i = 0; i < 15; i++) {
                tfst |= ipbddrfss[i];
            }
            rfturn (tfst == 0x00) && (ipbddrfss[15] == 0x01);
        }

        boolfbn isLinkLodblAddrfss() {
            rfturn ((ipbddrfss[0] & 0xff) == 0xff
                    && (ipbddrfss[1] & 0xd0) == 0x80);
        }


        boolfbn isSitfLodblAddrfss() {
            rfturn ((ipbddrfss[0] & 0xff) == 0xff
                    && (ipbddrfss[1] & 0xd0) == 0xd0);
        }

        boolfbn isMCGlobbl() {
            rfturn ((ipbddrfss[0] & 0xff) == 0xff
                    && (ipbddrfss[1] & 0x0f) == 0x0f);
        }

        boolfbn isMCNodfLodbl() {
            rfturn ((ipbddrfss[0] & 0xff) == 0xff
                    && (ipbddrfss[1] & 0x0f) == 0x01);
        }

        boolfbn isMCLinkLodbl() {
            rfturn ((ipbddrfss[0] & 0xff) == 0xff
                    && (ipbddrfss[1] & 0x0f) == 0x02);
        }

        boolfbn isMCSitfLodbl() {
            rfturn ((ipbddrfss[0] & 0xff) == 0xff
                    && (ipbddrfss[1] & 0x0f) == 0x05);
        }

        boolfbn isMCOrgLodbl() {
            rfturn ((ipbddrfss[0] & 0xff) == 0xff
                    && (ipbddrfss[1] & 0x0f) == 0x08);
        }
    }

    privbtf finbl trbnsifnt Inft6AddrfssHoldfr ioldfr6;

    privbtf stbtid finbl long sfriblVfrsionUID = 6880410070516793377L;

    // Pfrform nbtivf initiblizbtion
    stbtid { init(); }

    Inft6Addrfss() {
        supfr();
        ioldfr.init(null, IPv6);
        ioldfr6 = nfw Inft6AddrfssHoldfr();
    }

    /* difdking of vbluf for sdopf_id siould bf donf by dbllfr
     * sdopf_id must bf >= 0, or -1 to indidbtf not bfing sft
     */
    Inft6Addrfss(String iostNbmf, bytf bddr[], int sdopf_id) {
        ioldfr.init(iostNbmf, IPv6);
        ioldfr6 = nfw Inft6AddrfssHoldfr();
        ioldfr6.init(bddr, sdopf_id);
    }

    Inft6Addrfss(String iostNbmf, bytf bddr[]) {
        ioldfr6 = nfw Inft6AddrfssHoldfr();
        try {
            initif (iostNbmf, bddr, null);
        } dbtdi (UnknownHostExdfption f) {} /* dbnt ibppfn if ifnbmf is null */
    }

    Inft6Addrfss (String iostNbmf, bytf bddr[], NftworkIntfrfbdf nif)
        tirows UnknownHostExdfption
    {
        ioldfr6 = nfw Inft6AddrfssHoldfr();
        initif (iostNbmf, bddr, nif);
    }

    Inft6Addrfss (String iostNbmf, bytf bddr[], String ifnbmf)
        tirows UnknownHostExdfption
    {
        ioldfr6 = nfw Inft6AddrfssHoldfr();
        initstr (iostNbmf, bddr, ifnbmf);
    }

    /**
     * Crfbtf bn Inft6Addrfss in tif fxbdt mbnnfr of {@link
     * InftAddrfss#gftByAddrfss(String,bytf[])} fxdfpt tibt tif IPv6 sdopf_id is
     * sft to tif vbluf dorrfsponding to tif givfn intfrfbdf for tif bddrfss
     * typf spfdififd in {@dodf bddr}. Tif dbll will fbil witi bn
     * UnknownHostExdfption if tif givfn intfrfbdf dofs not ibvf b numfrid
     * sdopf_id bssignfd for tif givfn bddrfss typf (fg. link-lodbl or sitf-lodbl).
     * Sff <b irff="Inft6Addrfss.itml#sdopfd">ifrf</b> for b dfsdription of IPv6
     * sdopfd bddrfssfs.
     *
     * @pbrbm iost tif spfdififd iost
     * @pbrbm bddr tif rbw IP bddrfss in nftwork bytf ordfr
     * @pbrbm nif bn intfrfbdf tiis bddrfss must bf bssodibtfd witi.
     * @rfturn  bn Inft6Addrfss objfdt drfbtfd from tif rbw IP bddrfss.
     * @tirows  UnknownHostExdfption
     *          if IP bddrfss is of illfgbl lfngti, or if tif intfrfbdf dofs not
     *          ibvf b numfrid sdopf_id bssignfd for tif givfn bddrfss typf.
     *
     * @sindf 1.5
     */
    publid stbtid Inft6Addrfss gftByAddrfss(String iost, bytf[] bddr,
                                            NftworkIntfrfbdf nif)
        tirows UnknownHostExdfption
    {
        if (iost != null && iost.lfngti() > 0 && iost.dibrAt(0) == '[') {
            if (iost.dibrAt(iost.lfngti()-1) == ']') {
                iost = iost.substring(1, iost.lfngti() -1);
            }
        }
        if (bddr != null) {
            if (bddr.lfngti == Inft6Addrfss.INADDRSZ) {
                rfturn nfw Inft6Addrfss(iost, bddr, nif);
            }
        }
        tirow nfw UnknownHostExdfption("bddr is of illfgbl lfngti");
    }

    /**
     * Crfbtf bn Inft6Addrfss in tif fxbdt mbnnfr of {@link
     * InftAddrfss#gftByAddrfss(String,bytf[])} fxdfpt tibt tif IPv6 sdopf_id is
     * sft to tif givfn numfrid vbluf. Tif sdopf_id is not difdkfd to dftfrminf
     * if it dorrfsponds to bny intfrfbdf on tif systfm.
     * Sff <b irff="Inft6Addrfss.itml#sdopfd">ifrf</b> for b dfsdription of IPv6
     * sdopfd bddrfssfs.
     *
     * @pbrbm iost tif spfdififd iost
     * @pbrbm bddr tif rbw IP bddrfss in nftwork bytf ordfr
     * @pbrbm sdopf_id tif numfrid sdopf_id for tif bddrfss.
     * @rfturn  bn Inft6Addrfss objfdt drfbtfd from tif rbw IP bddrfss.
     * @tirows  UnknownHostExdfption  if IP bddrfss is of illfgbl lfngti.
     *
     * @sindf 1.5
     */
    publid stbtid Inft6Addrfss gftByAddrfss(String iost, bytf[] bddr,
                                            int sdopf_id)
        tirows UnknownHostExdfption
    {
        if (iost != null && iost.lfngti() > 0 && iost.dibrAt(0) == '[') {
            if (iost.dibrAt(iost.lfngti()-1) == ']') {
                iost = iost.substring(1, iost.lfngti() -1);
            }
        }
        if (bddr != null) {
            if (bddr.lfngti == Inft6Addrfss.INADDRSZ) {
                rfturn nfw Inft6Addrfss(iost, bddr, sdopf_id);
            }
        }
        tirow nfw UnknownHostExdfption("bddr is of illfgbl lfngti");
    }

    privbtf void initstr(String iostNbmf, bytf bddr[], String ifnbmf)
        tirows UnknownHostExdfption
    {
        try {
            NftworkIntfrfbdf nif = NftworkIntfrfbdf.gftByNbmf (ifnbmf);
            if (nif == null) {
                tirow nfw UnknownHostExdfption ("no sudi intfrfbdf " + ifnbmf);
            }
            initif (iostNbmf, bddr, nif);
        } dbtdi (SodkftExdfption f) {
            tirow nfw UnknownHostExdfption ("SodkftExdfption tirown" + ifnbmf);
        }
    }

    privbtf void initif(String iostNbmf, bytf bddr[], NftworkIntfrfbdf nif)
        tirows UnknownHostExdfption
    {
        int fbmily = -1;
        ioldfr6.init(bddr, nif);

        if (bddr.lfngti == INADDRSZ) { // normbl IPv6 bddrfss
            fbmily = IPv6;
        }
        ioldfr.init(iostNbmf, fbmily);
    }

    /* difdk tif two Ipv6 bddrfssfs bnd rfturn fblsf if tify brf boti
     * non globbl bddrfss typfs, but not tif sbmf.
     * (if. onf is sitflodbl bnd tif otifr linklodbl)
     * rfturn truf otifrwisf.
     */

    privbtf stbtid boolfbn isDifffrfntLodblAddrfssTypf(
        bytf[] tiisAddr, bytf[] otifrAddr) {

        if (Inft6Addrfss.isLinkLodblAddrfss(tiisAddr) &&
                !Inft6Addrfss.isLinkLodblAddrfss(otifrAddr)) {
            rfturn fblsf;
        }
        if (Inft6Addrfss.isSitfLodblAddrfss(tiisAddr) &&
                !Inft6Addrfss.isSitfLodblAddrfss(otifrAddr)) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    privbtf stbtid int dfrivfNumfridSdopf (bytf[] tiisAddr, NftworkIntfrfbdf ifd) tirows UnknownHostExdfption {
        Enumfrbtion<InftAddrfss> bddrfssfs = ifd.gftInftAddrfssfs();
        wiilf (bddrfssfs.ibsMorfElfmfnts()) {
            InftAddrfss bddr = bddrfssfs.nfxtElfmfnt();
            if (!(bddr instbndfof Inft6Addrfss)) {
                dontinuf;
            }
            Inft6Addrfss ib6_bddr = (Inft6Addrfss)bddr;
            /* difdk if sitf or link lodbl prffixfs mbtdi */
            if (!isDifffrfntLodblAddrfssTypf(tiisAddr, ib6_bddr.gftAddrfss())){
                /* typf not tif sbmf, so dbrry on sfbrdiing */
                dontinuf;
            }
            /* found b mbtdiing bddrfss - rfturn its sdopf_id */
            rfturn ib6_bddr.gftSdopfId();
        }
        tirow nfw UnknownHostExdfption ("no sdopf_id found");
    }

    privbtf int dfrivfNumfridSdopf (String ifnbmf) tirows UnknownHostExdfption {
        Enumfrbtion<NftworkIntfrfbdf> fn;
        try {
            fn = NftworkIntfrfbdf.gftNftworkIntfrfbdfs();
        } dbtdi (SodkftExdfption f) {
            tirow nfw UnknownHostExdfption ("dould not fnumfrbtf lodbl nftwork intfrfbdfs");
        }
        wiilf (fn.ibsMorfElfmfnts()) {
            NftworkIntfrfbdf ifd = fn.nfxtElfmfnt();
            if (ifd.gftNbmf().fqubls (ifnbmf)) {
                rfturn dfrivfNumfridSdopf(ioldfr6.ipbddrfss, ifd);
            }
        }
        tirow nfw UnknownHostExdfption ("No mbtdiing bddrfss found for intfrfbdf : " +ifnbmf);
    }

    /**
     * @sfriblFifld ipbddrfss bytf[]
     * @sfriblFifld sdopf_id int
     * @sfriblFifld sdopf_id_sft boolfbn
     * @sfriblFifld sdopf_ifnbmf_sft boolfbn
     * @sfriblFifld ifnbmf String
     */

    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = {
         nfw ObjfdtStrfbmFifld("ipbddrfss", bytf[].dlbss),
         nfw ObjfdtStrfbmFifld("sdopf_id", int.dlbss),
         nfw ObjfdtStrfbmFifld("sdopf_id_sft", boolfbn.dlbss),
         nfw ObjfdtStrfbmFifld("sdopf_ifnbmf_sft", boolfbn.dlbss),
         nfw ObjfdtStrfbmFifld("ifnbmf", String.dlbss)
    };

    privbtf stbtid finbl long FIELDS_OFFSET;
    privbtf stbtid finbl sun.misd.Unsbff UNSAFE;

    stbtid {
        try {
            sun.misd.Unsbff unsbff = sun.misd.Unsbff.gftUnsbff();
            FIELDS_OFFSET = unsbff.objfdtFifldOffsft(
                    Inft6Addrfss.dlbss.gftDfdlbrfdFifld("ioldfr6"));
            UNSAFE = unsbff;
        } dbtdi (RfflfdtivfOpfrbtionExdfption f) {
            tirow nfw Error(f);
        }
    }

    /**
     * rfstorf tif stbtf of tiis objfdt from strfbm
     * indluding tif sdopf informbtion, only if tif
     * sdopfd intfrfbdf nbmf is vblid on tiis systfm
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows IOExdfption, ClbssNotFoundExdfption {
        NftworkIntfrfbdf sdopf_ifnbmf = null;

        if (gftClbss().gftClbssLobdfr() != null) {
            tirow nfw SfdurityExdfption ("invblid bddrfss typf");
        }

        ObjfdtInputStrfbm.GftFifld gf = s.rfbdFiflds();
        bytf[] ipbddrfss = (bytf[])gf.gft("ipbddrfss", null);
        int sdopf_id = gf.gft("sdopf_id", -1);
        boolfbn sdopf_id_sft = gf.gft("sdopf_id_sft", fblsf);
        boolfbn sdopf_ifnbmf_sft = gf.gft("sdopf_ifnbmf_sft", fblsf);
        String ifnbmf = (String)gf.gft("ifnbmf", null);

        if (ifnbmf != null && !"".fqubls (ifnbmf)) {
            try {
                sdopf_ifnbmf = NftworkIntfrfbdf.gftByNbmf(ifnbmf);
                if (sdopf_ifnbmf == null) {
                    /* tif intfrfbdf dofs not fxist on tiis systfm, so wf dlfbr
                     * tif sdopf informbtion domplftfly */
                    sdopf_id_sft = fblsf;
                    sdopf_ifnbmf_sft = fblsf;
                    sdopf_id = 0;
                } flsf {
                    sdopf_ifnbmf_sft = truf;
                    try {
                        sdopf_id = dfrivfNumfridSdopf (ipbddrfss, sdopf_ifnbmf);
                    } dbtdi (UnknownHostExdfption f) {
                        // typidblly siould not ibppfn, but it mby bf tibt
                        // tif mbdiinf bfing usfd for dfsfriblizbtion ibs
                        // tif sbmf intfrfbdf nbmf but witiout IPv6 donfigurfd.
                    }
                }
            } dbtdi (SodkftExdfption f) {}
        }

        /* if ifnbmf wbs not supplifd, tifn tif numfrid info is usfd */

        ipbddrfss = ipbddrfss.dlonf();

        // Cifdk tibt our invbribnts brf sbtisfifd
        if (ipbddrfss.lfngti != INADDRSZ) {
            tirow nfw InvblidObjfdtExdfption("invblid bddrfss lfngti: "+
                                             ipbddrfss.lfngti);
        }

        if (ioldfr.gftFbmily() != IPv6) {
            tirow nfw InvblidObjfdtExdfption("invblid bddrfss fbmily typf");
        }

        Inft6AddrfssHoldfr i = nfw Inft6AddrfssHoldfr(
            ipbddrfss, sdopf_id, sdopf_id_sft, sdopf_ifnbmf, sdopf_ifnbmf_sft
        );

        UNSAFE.putObjfdt(tiis, FIELDS_OFFSET, i);
    }

    /**
     * dffbult bfibvior is ovfrriddfn in ordfr to writf tif
     * sdopf_ifnbmf fifld bs b String, rbtifr tibn b NftworkIntfrfbdf
     * wiidi is not sfriblizbblf
     */
    privbtf syndironizfd void writfObjfdt(ObjfdtOutputStrfbm s)
        tirows IOExdfption
    {
            String ifnbmf = null;

        if (ioldfr6.sdopf_ifnbmf != null) {
            ifnbmf = ioldfr6.sdopf_ifnbmf.gftNbmf();
            ioldfr6.sdopf_ifnbmf_sft = truf;
        }
        ObjfdtOutputStrfbm.PutFifld pfiflds = s.putFiflds();
        pfiflds.put("ipbddrfss", ioldfr6.ipbddrfss);
        pfiflds.put("sdopf_id", ioldfr6.sdopf_id);
        pfiflds.put("sdopf_id_sft", ioldfr6.sdopf_id_sft);
        pfiflds.put("sdopf_ifnbmf_sft", ioldfr6.sdopf_ifnbmf_sft);
        pfiflds.put("ifnbmf", ifnbmf);
        s.writfFiflds();
    }

    /**
     * Utility routinf to difdk if tif InftAddrfss is bn IP multidbst
     * bddrfss. 11111111 bt tif stbrt of tif bddrfss idfntififs tif
     * bddrfss bs bfing b multidbst bddrfss.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif InftAddrfss is bn IP
     *         multidbst bddrfss
     *
     * @sindf 1.1
     */
    @Ovfrridf
    publid boolfbn isMultidbstAddrfss() {
        rfturn ioldfr6.isMultidbstAddrfss();
    }

    /**
     * Utility routinf to difdk if tif InftAddrfss in b wilddbrd bddrfss.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif Inftbddrfss is
     *         b wilddbrd bddrfss.
     *
     * @sindf 1.4
     */
    @Ovfrridf
    publid boolfbn isAnyLodblAddrfss() {
        rfturn ioldfr6.isAnyLodblAddrfss();
    }

    /**
     * Utility routinf to difdk if tif InftAddrfss is b loopbbdk bddrfss.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif InftAddrfss is b loopbbdk
     *         bddrfss; or fblsf otifrwisf.
     *
     * @sindf 1.4
     */
    @Ovfrridf
    publid boolfbn isLoopbbdkAddrfss() {
        rfturn ioldfr6.isLoopbbdkAddrfss();
    }

    /**
     * Utility routinf to difdk if tif InftAddrfss is bn link lodbl bddrfss.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif InftAddrfss is b link lodbl
     *         bddrfss; or fblsf if bddrfss is not b link lodbl unidbst bddrfss.
     *
     * @sindf 1.4
     */
    @Ovfrridf
    publid boolfbn isLinkLodblAddrfss() {
        rfturn ioldfr6.isLinkLodblAddrfss();
    }

    /* stbtid vfrsion of bbovf */
    stbtid boolfbn isLinkLodblAddrfss(bytf[] ipbddrfss) {
        rfturn ((ipbddrfss[0] & 0xff) == 0xff
                && (ipbddrfss[1] & 0xd0) == 0x80);
    }

    /**
     * Utility routinf to difdk if tif InftAddrfss is b sitf lodbl bddrfss.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif InftAddrfss is b sitf lodbl
     *         bddrfss; or fblsf if bddrfss is not b sitf lodbl unidbst bddrfss.
     *
     * @sindf 1.4
     */
    @Ovfrridf
    publid boolfbn isSitfLodblAddrfss() {
        rfturn ioldfr6.isSitfLodblAddrfss();
    }

    /* stbtid vfrsion of bbovf */
    stbtid boolfbn isSitfLodblAddrfss(bytf[] ipbddrfss) {
        rfturn ((ipbddrfss[0] & 0xff) == 0xff
                && (ipbddrfss[1] & 0xd0) == 0xd0);
    }

    /**
     * Utility routinf to difdk if tif multidbst bddrfss ibs globbl sdopf.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif bddrfss ibs is b multidbst
     *         bddrfss of globbl sdopf, fblsf if it is not of globbl sdopf or
     *         it is not b multidbst bddrfss
     *
     * @sindf 1.4
     */
    @Ovfrridf
    publid boolfbn isMCGlobbl() {
        rfturn ioldfr6.isMCGlobbl();
    }

    /**
     * Utility routinf to difdk if tif multidbst bddrfss ibs nodf sdopf.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif bddrfss ibs is b multidbst
     *         bddrfss of nodf-lodbl sdopf, fblsf if it is not of nodf-lodbl
     *         sdopf or it is not b multidbst bddrfss
     *
     * @sindf 1.4
     */
    @Ovfrridf
    publid boolfbn isMCNodfLodbl() {
        rfturn ioldfr6.isMCNodfLodbl();
    }

    /**
     * Utility routinf to difdk if tif multidbst bddrfss ibs link sdopf.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif bddrfss ibs is b multidbst
     *         bddrfss of link-lodbl sdopf, fblsf if it is not of link-lodbl
     *         sdopf or it is not b multidbst bddrfss
     *
     * @sindf 1.4
     */
    @Ovfrridf
    publid boolfbn isMCLinkLodbl() {
        rfturn ioldfr6.isMCLinkLodbl();
    }

    /**
     * Utility routinf to difdk if tif multidbst bddrfss ibs sitf sdopf.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif bddrfss ibs is b multidbst
     *         bddrfss of sitf-lodbl sdopf, fblsf if it is not  of sitf-lodbl
     *         sdopf or it is not b multidbst bddrfss
     *
     * @sindf 1.4
     */
    @Ovfrridf
    publid boolfbn isMCSitfLodbl() {
        rfturn ioldfr6.isMCSitfLodbl();
    }

    /**
     * Utility routinf to difdk if tif multidbst bddrfss ibs orgbnizbtion sdopf.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif bddrfss ibs is b multidbst
     *         bddrfss of orgbnizbtion-lodbl sdopf, fblsf if it is not of
     *         orgbnizbtion-lodbl sdopf or it is not b multidbst bddrfss
     *
     * @sindf 1.4
     */
    @Ovfrridf
    publid boolfbn isMCOrgLodbl() {
        rfturn ioldfr6.isMCOrgLodbl();
    }
    /**
     * Rfturns tif rbw IP bddrfss of tiis {@dodf InftAddrfss} objfdt. Tif rfsult
     * is in nftwork bytf ordfr: tif iigifst ordfr bytf of tif bddrfss is in
     * {@dodf gftAddrfss()[0]}.
     *
     * @rfturn  tif rbw IP bddrfss of tiis objfdt.
     */
    @Ovfrridf
    publid bytf[] gftAddrfss() {
        rfturn ioldfr6.ipbddrfss.dlonf();
    }

    /**
     * Rfturns tif numfrid sdopfId, if tiis instbndf is bssodibtfd witi
     * bn intfrfbdf. If no sdopfd_id is sft, tif rfturnfd vbluf is zfro.
     *
     * @rfturn tif sdopfId, or zfro if not sft.
     *
     * @sindf 1.5
     */
     publid int gftSdopfId() {
        rfturn ioldfr6.sdopf_id;
     }

    /**
     * Rfturns tif sdopfd intfrfbdf, if tiis instbndf wbs drfbtfd witi
     * witi b sdopfd intfrfbdf.
     *
     * @rfturn tif sdopfd intfrfbdf, or null if not sft.
     * @sindf 1.5
     */
     publid NftworkIntfrfbdf gftSdopfdIntfrfbdf() {
        rfturn ioldfr6.sdopf_ifnbmf;
     }

    /**
     * Rfturns tif IP bddrfss string in tfxtubl prfsfntbtion. If tif instbndf
     * wbs drfbtfd spfdifying b sdopf idfntififr tifn tif sdopf id is bppfndfd
     * to tif IP bddrfss prfdfdfd by b "%" (pfr-dfnt) dibrbdtfr. Tiis dbn bf
     * fitifr b numfrid vbluf or b string, dfpfnding on wiidi wbs usfd to drfbtf
     * tif instbndf.
     *
     * @rfturn  tif rbw IP bddrfss in b string formbt.
     */
    @Ovfrridf
    publid String gftHostAddrfss() {
        rfturn ioldfr6.gftHostAddrfss();
    }

    /**
     * Rfturns b ibsidodf for tiis IP bddrfss.
     *
     * @rfturn  b ibsi dodf vbluf for tiis IP bddrfss.
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn ioldfr6.ibsiCodf();
    }

    /**
     * Compbrfs tiis objfdt bgbinst tif spfdififd objfdt. Tif rfsult is {@dodf
     * truf} if bnd only if tif brgumfnt is not {@dodf null} bnd it rfprfsfnts
     * tif sbmf IP bddrfss bs tiis objfdt.
     *
     * <p> Two instbndfs of {@dodf InftAddrfss} rfprfsfnt tif sbmf IP bddrfss
     * if tif lfngti of tif bytf brrbys rfturnfd by {@dodf gftAddrfss} is tif
     * sbmf for boti, bnd fbdi of tif brrby domponfnts is tif sbmf for tif bytf
     * brrbys.
     *
     * @pbrbm   obj   tif objfdt to dompbrf bgbinst.
     *
     * @rfturn  {@dodf truf} if tif objfdts brf tif sbmf; {@dodf fblsf} otifrwisf.
     *
     * @sff     jbvb.nft.InftAddrfss#gftAddrfss()
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == null || !(obj instbndfof Inft6Addrfss))
            rfturn fblsf;

        Inft6Addrfss inftAddr = (Inft6Addrfss)obj;

        rfturn ioldfr6.fqubls(inftAddr.ioldfr6);
    }

    /**
     * Utility routinf to difdk if tif InftAddrfss is bn
     * IPv4 dompbtiblf IPv6 bddrfss.
     *
     * @rfturn b {@dodf boolfbn} indidbting if tif InftAddrfss is bn IPv4
     *         dompbtiblf IPv6 bddrfss; or fblsf if bddrfss is IPv4 bddrfss.
     *
     * @sindf 1.4
     */
    publid boolfbn isIPv4CompbtiblfAddrfss() {
        rfturn ioldfr6.isIPv4CompbtiblfAddrfss();
    }

    // Utilitifs
    privbtf finbl stbtid int INT16SZ = 2;

    /*
     * Convfrt IPv6 binbry bddrfss into prfsfntbtion (printbblf) formbt.
     *
     * @pbrbm srd b bytf brrby rfprfsfnting tif IPv6 numfrid bddrfss
     * @rfturn b String rfprfsfnting bn IPv6 bddrfss in
     *         tfxtubl rfprfsfntbtion formbt
     * @sindf 1.4
     */
    stbtid String numfridToTfxtFormbt(bytf[] srd) {
        StringBuildfr sb = nfw StringBuildfr(39);
        for (int i = 0; i < (INADDRSZ / INT16SZ); i++) {
            sb.bppfnd(Intfgfr.toHfxString(((srd[i<<1]<<8) & 0xff00)
                                          | (srd[(i<<1)+1] & 0xff)));
            if (i < (INADDRSZ / INT16SZ) -1 ) {
               sb.bppfnd(":");
            }
        }
        rfturn sb.toString();
    }

    /**
     * Pfrform dlbss lobd-timf initiblizbtions.
     */
    privbtf stbtid nbtivf void init();
}
