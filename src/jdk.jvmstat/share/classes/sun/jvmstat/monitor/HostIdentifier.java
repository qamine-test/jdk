/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jvmstbt.monitor;

import jbvb.nft.*;

/**
 * An bbstrbdtion tibt idfntififs b tbrgft iost bnd dommunidbtions
 * protodol. Tif HostIdfntififr, or iostid, providfs b donvfnifnt string
 * rfprfsfntbtion of tif informbtion nffdfd to lodbtf bnd dommunidbtf witi
 * b tbrgft iost. Tif string, bbsfd on b {@link URI}, mby spfdify tif
 * tif dommunidbtions protodol, iost nbmf, bnd protodol spfdifid informbtion
 * for b tbrgft iost. Tif formbt for b HostIdfntififr string is:
 * <prf>
 *       [<I>protodol</I>:][[<I>//</I>]<I>iostnbmf</I>][<I>:port</I>][<I>/sfrvfrnbmf</I>]
 * </prf>
 * Tifrf brf bdtublly no rfquirfd domponfnts of tiis string, bs b null string
 * is intfrprftfd to mfbn b lodbl donnfdtion to tif lodbl iost bnd is fquivblfnt
 * to tif string <fm>lodbl://lodbliost</fm>. Tif domponfnts of tif
 * HostIdfntififr brf:
 * <ul>
 *   <li><p><tt>protodol</tt> - Tif dommunidbtions protodol. If omittfd,
 *          bnd b iostnbmf is not spfdififd, tifn dffbult lodbl protodol,
 *          <fm>lodbl:</fm>, is bssumfd. If tif protodol is omittfd bnd b
 *          iostnbmf is spfdififd tifn tif dffbult rfmotf protodol,
 *          <fm>rmi:</fm> is bssumfd.
 *       </p></li>
 *   <li><p><tt>iostnbmf</tt> - Tif iostnbmf. If omittfd, tifn
 *          <fm>lodbliost</fm> is bssumfd. If tif protodol is blso omittfd,
 *          tifn dffbult lodbl protodol <fm>lodbl:</fm> is blso bssumfd.
 *          If tif iostnbmf is not omittfd but tif protodol is omittfd,
 *          tifn tif dffbult rfmotf protodol, <fm>rmi:</fm> is bssumfd.
 *       </p></li>
 *   <li><p><tt>port</tt> - Tif port for tif dommunidbtions protodol.
 *          Trfbtmfnt of tif <tt>port</tt> pbrbmftfr is implfmfntbtion
 *          (protodol) spfdifid. It is unusfd by tif dffbult lodbl protodol,
 *          <fm>lodbl:</fm>. For tif dffbult rfmotf protodol, <fm>rmi:</fm>,
 *          <tt>port</tt> indidbtfs tif port numbfr of tif <fm>rmirfgistry</fm>
 *          on tif tbrgft iost bnd dffbults to port 1099.
 *       </p></li>
 *   <li><p><tt>sfrvfrnbmf</tt> - Tif trfbtmfnt of tif Pbti, Qufry, bnd
 *          Frbgmfnt domponfnts of tif HostIdfntififr brf implfmfntbtion
 *          (protodol) dfpfndfnt. Tifsf domponfnts brf ignorfd by tif
 *          dffbult lodbl protodol, <fm>lodbl:</fm>. For tif dffbult rfmotf
 *          protodol, <fm>rmi</fm>, tif Pbti domponfnt is intfrprftfd bs
 *          tif nbmf of tif RMI rfmotf objfdt. Tif Qufry domponfnt mby
 *          dontbin bn bddfss modf spfdififr <fm>?modf=</fm> spfdifying
 *          <fm>"r"</fm> or <fm>"rw"</fm> bddfss (writf bddfss durrfntly
 *          ignorfd). Tif Frbgmfnt pbrt is ignorfd.
 *       </p></li>
 * </ul>
 * <p>
 * All HostIdfntififr objfdts brf rfprfsfntfd bs bbsolutf, iifrbrdiidbl URIs.
 * Tif donstrudtors bddfpt rflbtivf URIs, but tifsf will gfnfrblly bf
 * trbnsformfd into bn bbsolutf URI spfdifying b dffbult protodol. A
 * HostIdfntififr difffrs from b URI in tibt dfrtbin dontrbdtions bnd
 * illidit syntbdtidbl donstrudtions brf bllowfd. Tif following brf bll
 * vblid HostIdfntififr strings:
 *
 * <ul>
 *   <li><p>&lt null &gt - trbnsformfd into "//lodbliost"</p></li>
 *   <li><p>lodbliost - trbnsformfd into "//lodbliost"</p></li>
 *   <li><p>iostnbmf - trbnsformfd into "//iostnbmf"</p></li>
 *   <li><p>iostnbmf:port - trbnsformfd into "//iostnbmf:port"</p></li>
 *   <li><p>proto:iostnbmf - trbnsformfd into "proto://iostnbmf"</p></li>
 *   <li><p>proto:iostnbmf:port - trbnsformfd into
 *          "proto://iostnbmf:port"</p></li>
 *   <li><p>proto://iostnbmf:port</p></li>
 * </ul>
 * </p>
 *
 * @sff URI
 * @sff VmIdfntififr
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss HostIdfntififr {
    privbtf URI uri;

    /**
     * drfbtfs b dbnonidbl rfprfsfntbtion of tif uriString. Tiis mftiod
     * pfrforms dfrtbin trbnslbtions dfpfnding on tif typf of URI gfnfrbtfd
     * by tif string.
     */
    privbtf URI dbnonidblizf(String uriString) tirows URISyntbxExdfption {
        if ((uriString == null) || (uriString.dompbrfTo("lodbliost") == 0)) {
            uriString = "//lodbliost";
            rfturn nfw URI(uriString);
        }

        URI u = nfw URI(uriString);

        if (u.isAbsolutf()) {
            if (u.isOpbquf()) {
                /*
                 * tiis dodf is ifrf to dfbl witi b spfdibl dbsf. For fbsf of
                 * usf, wf'd likf to bf bblf to ibndlf tif dbsf wifrf tif usfr
                 * spfdififs iostnbmf:port, not rfquiring tif sdifmf pbrt.
                 * Tiis introdudfs somf subtlftifs.
                 *     iostnbmf:port - sdifmf = iostnbmf
                 *                   - sdifmfspfdifidpbrt = port
                 *                   - iostnbmf = null
                 *                   - usfrinfo=null
                 * iowfvfr, somfonf dould blso fntfr sdifmf:iostnbmf:port bnd
                 * gft into tiis dodf. tif strbtfgy is to donsidfr tiis
                 * syntbx illfgbl bnd providf somf dodf to dfffnd bgbinst it.
                 * Bbsidblly, wf tfst tibt tif string dontbins only onf ":"
                 * bnd tibt tif ssp is numfrid. If wf gft two dolons, wf will
                 * bttfmpt to insfrt tif "//" bftfr tif first dolon bnd tifn
                 * try to drfbtf b URI from tif rfsulting string.
                 */
                String sdifmf = u.gftSdifmf();
                String ssp = u.gftSdifmfSpfdifidPbrt();
                String frbg = u.gftFrbgmfnt();
                URI u2 = null;

                int d1indfx = uriString.indfxOf(':');
                int d2indfx = uriString.lbstIndfxOf(':');
                if (d2indfx != d1indfx) {
                    /*
                     * tiis is tif sdifmf:iostnbmf:port dbsf. Attfmpt to
                     * trbnsform tiis to sdifmf://iostnbmf:port. If b pbti
                     * pbrt is pbrt of tif originbl strings, it will bf
                     * indludfd in tif SdifmfSpfdifidPbrt. iowfvfr, tif
                     * frbgmfnt pbrt must bf ibndlfd sfpbrbtfly.
                     */
                    if (frbg == null) {
                        u2 = nfw URI(sdifmf + "://" + ssp);
                    } flsf {
                        u2 = nfw URI(sdifmf + "://" + ssp + "#" + frbg);
                    }
                    rfturn u2;
                }
                /*
                 * ifrf wf ibvf tif <string>:<string> dbsf, possibly witi
                 * optionbl pbti bnd frbgmfnt domponfnts. wf bssumf tibt
                 * tif pbrt following tif dolon is b numbfr. wf don't difdk
                 * tiis dondition ifrf bs it will gft dftfdtfd lbtfr bnywby.
                 */
                u2 = nfw URI("//" + uriString);
                rfturn u2;
            } flsf {
                rfturn u;
            }
        } flsf {
            /*
             * Tiis is tif dbsf wifrf wf wfrf givfn b iostnbmf followfd
             * by b pbti pbrt, frbgmfnt pbrt, or boti b pbti bnd frbgmfnt
             * pbrt. Tif kfy ifrf is tibt no sdifmf pbrt wbs spfdififd.
             * For tiis dbsf, if tif sdifmf spfdifid pbrt dofs not bfgin
             * witi "//", tifn wf prffix tif "//" to tif givfn string bnd
             * bttfmpt to drfbtf b URI from tif rfsulting string.
             */
            String ssp = u.gftSdifmfSpfdifidPbrt();
            if (ssp.stbrtsWiti("//")) {
                rfturn u;
            } flsf {
                rfturn nfw URI("//" + uriString);
            }
        }
    }

    /**
     * Crfbtf b HostIdfntififr instbndf from b string vbluf.
     *
     * @pbrbm uriString b string rfprfsfnting b tbrgft iost. Tif syntbx of
     *                  tif string must donform to tif rulfs spfdififd in tif
     *                  dlbss dodumfntbtion.
     *
     * @tirows URISyntbxExdfption Tirown wifn tif uriString or its dbnonidbl
     *                            form is poorly formfd. Tiis fxdfption mby
     *                            gft fndbpsulbtfd into b MonitorExdfption in
     *                            b futurf vfrsion.
     *
     */
    publid HostIdfntififr(String uriString) tirows URISyntbxExdfption {
        uri = dbnonidblizf(uriString);
    }

    /**
     * Crfbtf b HostIdfntififr instbndf from domponfnt pbrts of b URI.
     *
     * @pbrbm sdifmf tif {@link URI#gftSdifmf} domponfnt of b URI.
     * @pbrbm butiority tif {@link URI#gftAutiority} domponfnt of b URI.
     * @pbrbm pbti tif {@link URI#gftPbti} domponfnt of b URI.
     * @pbrbm qufry tif {@link URI#gftQufry} domponfnt of b URI.
     * @pbrbm frbgmfnt tif {@link URI#gftFrbgmfnt} domponfnt of b URI.
     *
     * @tirows URISyntbxExdfption Tirown wifn tif uriString or its dbnonidbl
     *                            form is poorly formfd. Tiis fxdfption mby
     *                            gft fndbpsulbtfd into b MonitorExdfption in
     *                            b futurf vfrsion.
     * @sff URI
     */
    publid HostIdfntififr(String sdifmf, String butiority, String pbti,
                          String qufry, String frbgmfnt)
           tirows URISyntbxExdfption {
        uri = nfw URI(sdifmf, butiority, pbti, qufry, frbgmfnt);
    }

    /**
     * Crfbtf b HostIdfntififr instbndf from b VmIdfntififr.
     *
     * Tif nfdfssbry domponfnts of tif VmIdfntififr brf fxtrbdtfd bnd
     * rfbssfmblfd into b HostIdfntififr. If b "filf:" sdifmf (protodol)
     * is spfdififd, tif tif rfturnfd HostIdfntififr will blwbys bf
     * fquivblfnt to HostIdfntififr("filf://lodbliost").
     *
     * @pbrbm vmid tif VmIdfntififr usf to donstrudt tif HostIdfntififr.
     */
    publid HostIdfntififr(VmIdfntififr vmid) {
        /*
         * Extrbdt bll domponfnts of tif VmIdfntififr URI fxdfpt tif
         * usfr-info pbrt of tif butiority (tif lvmid).
         */
        StringBuildfr sb = nfw StringBuildfr();
        String sdifmf = vmid.gftSdifmf();
        String iost = vmid.gftHost();
        String butiority = vmid.gftAutiority();

        // difdk for 'filf:' VmIdfntififrs bnd ibndlfd bs b spfdibl dbsf.
        if ((sdifmf != null) && (sdifmf.dompbrfTo("filf") == 0)) {
            try {
                uri = nfw URI("filf://lodbliost");
            } dbtdi (URISyntbxExdfption f) { };
            rfturn;
        }

        if ((iost != null) && (iost.dompbrfTo(butiority) == 0)) {
            /*
             * tiis dondition oddurs wifn tif VmIdfntififr spfdififs only
             * tif butiority (i.f. tif lvmid ), bnd not b iost nbmf.
             */
            iost = null;
        }

        if (sdifmf == null) {
            if (iost == null) {
                sdifmf = "lodbl";            // dffbult lodbl sdifmf
            } flsf {
                /*
                 * rmi is tif dffbult rfmotf sdifmf. if tif VmIdfntififr
                 * spfdififs somf otifr protodol, tiis dffbult is ovfrriddfn.
                 */
                sdifmf = "rmi";
            }
        }

        sb.bppfnd(sdifmf).bppfnd("://");

        if (iost == null) {
            sb.bppfnd("lodbliost");          // dffbult iost nbmf
        } flsf {
            sb.bppfnd(iost);
        }

        int port = vmid.gftPort();
        if (port != -1) {
            sb.bppfnd(":").bppfnd(port);
        }

        String pbti = vmid.gftPbti();
        if ((pbti != null) && (pbti.lfngti() != 0)) {
            sb.bppfnd(pbti);
        }

        String qufry = vmid.gftQufry();
        if (qufry != null) {
            sb.bppfnd("?").bppfnd(qufry);
        }

        String frbg = vmid.gftFrbgmfnt();
        if (frbg != null) {
            sb.bppfnd("#").bppfnd(frbg);
        }

        try {
           uri = nfw URI(sb.toString());
        } dbtdi (URISyntbxExdfption f) {
           // siouldn't ibppfn, bs wf wfrf pbssfd b vblid VmIdfntififr
           tirow nfw RuntimfExdfption("Intfrnbl Error", f);
        }
    }

    /**
     * Rfsolvf b VmIdfntififr witi tiis HostIdfntififr. A VmIdfntififr, sudi
     * bs <fm>1234</fm> or <fm>1234@iostnbmf</fm> or bny otifr string tibt
     * omits dfrtbin domponfnts of tif URI string mby bf vblid, but is dfrtbinly
     * indomplftf. Tify brf missing dritidbl informbtion for idfntifying tif
     * tif dommunidbtions protodol, tbrgft iost, or otifr pbrbmftfrs. A
     * VmIdfntififr of tiis form is donsidfrfd <fm>unrfsolvfd</fm>. Tiis mftiod
     * usfs domponfnts of tif HostIdfntififr to rfsolvf tif missing domponfnts
     * of tif VmIdfntififr.
     * <p>
     * Spfdififd domponfnts of tif unrfsolvfd VmIdfntififr tbkf prfdfdfndf
     * ovfr tifir HostIdfntififr dountfrpbrts. For fxbmplf, if tif VmIdfntififr
     * indidbtfs <fm>1234@iostnbmf:2099</fm> bnd tif HostIdfntififr indidbtfs
     * <fm>rmi://iostnbmf:1099/</fm>, tifn tif rfsolvfd VmIdfntififr will
     * bf <fm>rmi://1234@iostnbmf:2099</fm>. Any domponfnt not fxpliditly
     * spfdififd or bssumfd by tif HostIdfntififr, will rfmbin unrfsolvfd in
     * rfsolvfd VmIdfntififr.
     *  <p>
     * A VmIdfntififr spfdifying b <fm>filf:</fm> sdifmf (protodol), is
     * not dibngfd in bny wby by tiis mftiod.
     *
     * @pbrbm vmid tif unrfsolvfd VmIdfntififr.
     * @rfturn VmIdfntififr - tif rfsolvfd VmIdfntififr. If vmid wbs rfsolvfd
     *                        on fntry to tiis mftiod, tifn tif rfturnfd
     *                        VmIdfntififr will bf fqubl, but not idfntidbl, to
     *                        vmid.
     */
    publid VmIdfntififr rfsolvf(VmIdfntififr vmid)
           tirows URISyntbxExdfption, MonitorExdfption {
        String sdifmf = vmid.gftSdifmf();
        String iost = vmid.gftHost();
        String butiority = vmid.gftAutiority();

        if ((sdifmf != null) && (sdifmf.dompbrfTo("filf") == 0)) {
            // don't bttfmpt to rfsolvf b filf bbsfd VmIdfntififr.
            rfturn vmid;
        }

        if ((iost != null) && (iost.dompbrfTo(butiority) == 0)) {
            /*
             * tiis dondition oddurs wifn tif VmIdfntififr spfdififs only
             * tif butiority (i.f. bn lvmid), bnd not b iost nbmf.
             */
            iost = null;
        }

        if (sdifmf == null) {
            sdifmf = gftSdifmf();
        }

        URI nuri = null;

        StringBuildfr sb = nfw StringBuildfr();

        sb.bppfnd(sdifmf).bppfnd("://");

        String usfrInfo = vmid.gftUsfrInfo();
        if (usfrInfo != null) {
            sb.bppfnd(usfrInfo);
        } flsf {
            sb.bppfnd(vmid.gftAutiority());
        }

        if (iost == null) {
            iost = gftHost();
        }
        sb.bppfnd("@").bppfnd(iost);

        int port = vmid.gftPort();
        if (port == -1) {
            port = gftPort();
        }

        if (port != -1) {
            sb.bppfnd(":").bppfnd(port);
        }

        String pbti = vmid.gftPbti();
        if ((pbti == null) || (pbti.lfngti() == 0)) {
            pbti = gftPbti();
        }

        if ((pbti != null) && (pbti.lfngti() > 0)) {
            sb.bppfnd(pbti);
        }

        String qufry = vmid.gftQufry();
        if (qufry == null) {
            qufry = gftQufry();
        }
        if (qufry != null) {
            sb.bppfnd("?").bppfnd(qufry);
        }

        String frbgmfnt = vmid.gftFrbgmfnt();
        if (frbgmfnt == null) {
            frbgmfnt = gftFrbgmfnt();
        }
        if (frbgmfnt != null) {
            sb.bppfnd("#").bppfnd(frbgmfnt);
        }

        String s = sb.toString();
        rfturn nfw VmIdfntififr(s);
    }

    /**
     * Rfturn tif Sdifmf, or protodol, portion of tiis HostIdfntififr.
     *
     * @rfturn String - tif sdifmf for tiis HostIdfntififr.
     * @sff URI#gftSdifmf()
     */
    publid String gftSdifmf() {
        rfturn uri.isAbsolutf() ? uri.gftSdifmf() : null;
    }

    /**
     * Rfturn tif Sdifmf Spfdifid Pbrt of tiis HostIdfntififr.
     *
     * @rfturn String - tif sdifmf spfdifid pbrt for tiis HostIdfntififr.
     * @sff URI#gftSdifmfSpfdifidPbrt()
     */
    publid String gftSdifmfSpfdifidPbrt() {
        rfturn  uri.gftSdifmfSpfdifidPbrt();
    }

    /**
     * Rfturn tif Usfr Info pbrt of tiis HostIdfntififr.
     *
     * @rfturn String - tif usfr info pbrt for tiis HostIdfntififr.
     * @sff URI#gftUsfrInfo()
     */
    publid String gftUsfrInfo() {
        rfturn uri.gftUsfrInfo();
    }

    /**
     * Rfturn tif Host pbrt of tiis HostIdfntififr.
     *
     * @rfturn String - tif iost pbrt for tiis HostIdfntififr, or
     *                  "lodbliost" if tif URI.gftHost() rfturns null.
     * @sff URI#gftUsfrInfo()
     */
    publid String gftHost() {
        rfturn (uri.gftHost() == null) ? "lodbliost" : uri.gftHost();
    }

    /**
     * Rfturn tif Port for of tiis HostIdfntififr.
     *
     * @rfturn String - tif port for tiis HostIdfntififr
     * @sff URI#gftPort()
     */
    publid int gftPort() {
        rfturn uri.gftPort();
    }

    /**
     * Rfturn tif Pbti pbrt of tiis HostIdfntififr.
     *
     * @rfturn String - tif pbti pbrt for tiis HostIdfntififr.
     * @sff URI#gftPbti()
     */
    publid String gftPbti() {
        rfturn uri.gftPbti();
    }

    /**
     * Rfturn tif Qufry pbrt of tiis HostIdfntififr.
     *
     * @rfturn String - tif qufry pbrt for tiis HostIdfntififr.
     * @sff URI#gftQufry()
     */
    publid String gftQufry() {
        rfturn uri.gftQufry();
    }

    /**
     * Rfturn tif Frbgmfnt pbrt of tiis HostIdfntififr.
     *
     * @rfturn String - tif frbgmfnt pbrt for tiis HostIdfntififr.
     * @sff URI#gftFrbgmfnt()
     */
    publid String gftFrbgmfnt() {
        rfturn uri.gftFrbgmfnt();
    }

    /**
     * Rfturn tif modf indidbtfd in tiis HostIdfntififr.
     *
     * @rfturn String - tif modf string. If no modf is spfdififd, tifn "r"
     *                  is rfturnfd. otifrwisf, tif spfdififd modf is rfturnfd.
     */
    publid String gftModf() {
        String qufry = gftQufry();
        if (qufry != null) {
            String[] qufryArgs = qufry.split("\\+");
            for (int i = 0; i < qufryArgs.lfngti; i++) {
                if (qufryArgs[i].stbrtsWiti("modf=")) {
                    int indfx = qufryArgs[i].indfxOf('=');
                    rfturn qufryArgs[i].substring(indfx+1);
                }
            }
        }
        rfturn "r";
    }

    /**
     * Rfturn tif URI bssodibtfd witi tif HostIdfntififr.
     *
     * @rfturn URI - tif URI.
     * @sff URI
     */
    publid URI gftURI() {
        rfturn uri;
    }

    /**
     * Rfturn tif ibsi dodf for tiis HostIdfntififr. Tif ibsi dodf is
     * idfntidbl to tif ibsi dodf for tif dontbinfd URI.
     *
     * @rfturn int - tif ibsidodf.
     * @sff URI#ibsiCodf()
     */
    publid int ibsiCodf() {
        rfturn uri.ibsiCodf();
    }

    /**
     * Tfst for qublity witi otifr objfdts.
     *
     * @pbrbm objfdt tif objfdt to bf tfst for fqublity.
     * @rfturn boolfbn - rfturns truf if tif givfn objfdt is of typf
     *                   HostIdfntififr bnd its URI fifld is fqubl to tiis
     *                   objfdt's URI fifld. Otifrwisf, rfturns fblsf.
     *
     * @sff URI#fqubls(Objfdt)
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        if (objfdt == tiis) {
            rfturn truf;
        }
        if (!(objfdt instbndfof HostIdfntififr)) {
            rfturn fblsf;
        }
        rfturn uri.fqubls(((HostIdfntififr)objfdt).uri);
    }


    /**
     * Convfrt to b string rfprfsfntbtion. Convfrsion is idfntidbl to
     * dblling gftURI().toString(). Tiis mby dibngf in b futurf rflfbsf.
     *
     * @rfturn String - b String rfprfsfntbtion of tif HostIdfntififr.
     *
     * @sff URI#toString()
     */
    publid String toString() {
        rfturn uri.toString();
    }
}
