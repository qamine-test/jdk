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
 * An bbstrbdtion tibt idfntififs b tbrgft Jbvb Virtubl Mbdiinf.
 * Tif VmIdfntififr, or vmid, providfs b donvfnifnt string rfprfsfntbtion
 * of tif informbtion nffdfd to lodbtf bnd dommunidbtf witi b tbrgft
 * Jbvb Virtubl Mbdiinf. Tif string, bbsfd on b {@link URI}, mby spfdify
 * tif dommunidbtions protodol, iost nbmf, lodbl vm idfntififr, bnd protodol
 * spfdifid informbtion for b tbrgft Jbvb Virtubl Mbdiinf. Tif formbt for
 * b VmIdfntififr string is:
 * <prf>
 *      [<I>protodol</I>:][<I>//</I>]<I><B>lvmid</B></I>[<I>@iostnbmf</I>][<I>:port</I>][<I>/sfrvfrnbmf</I>]
 * </prf>
 * Tif only rfquirfd domponfnt of tiis string is tif Lodbl Virtubl Mbdiinf
 * Idfntififr, or <tt>lvmid</tt>, wiidi uniqufly idfntififs tif tbrgft
 * Jbvb Virtubl Mbdiinf on b iost. Tif optionbl domponfnts of tif VmIdfntififr
 * indludf:
 * <ul>
 *   <li><p><tt>protodol</tt> - Tif dommunidbtions protodol. A VmIdfntififr
 *          omitting tif protodol must bf rfsolvfd bgbinst b HostIdfntififr
 *          using {@link HostIdfntififr#rfsolvf}.
 *       </p></li>
 *   <li><p><tt>iostnbmf</tt> - A iostnbmf or IP bddrfss indidbting tif tbrgft
 *          iost. A VmIdfntififr omitting tif protodol must bf rfsolvfd
 *          bgbinst b HostIdfntififr using {@link HostIdfntififr#rfsolvf}.
 *       </p></li>
 *   <li><p><tt>port</tt> - Tif port for tif dommunidbtions protodol.
 *          Trfbtmfnt of tif <tt>port</tt> pbrbmftfr is implfmfntbtion
 *          (protodol) spfdifid. A VmIdfntififr omitting tif protodol siould
 *          bf rfsolvfd bgbinst b HostIdfntififr using
 *          {@link HostIdfntififr#rfsolvf}.
 *       </p></li>
 *   <li><p><tt>sfrvfrnbmf</tt> - Tif trfbtmfnt of tif Pbti, Qufry, bnd
 *          Frbgmfnt domponfnts of tif VmIdfntififr brf implfmfntbtion
 *          (protodol) dfpfndfnt. A VmIdfntififr omitting tif protodol siould
 *          bf rfsolvfd bgbinst b HostIdfntififr using
 *          {@link HostIdfntififr#rfsolvf}.
 *       </p></li>
 * </ul>
 * <p>
 * All VmIdfntififr instbndfs brf donstrudtfd bs bbsolutf, iifrbrdiidbl URIs.
 * Tif donstrudtors will bddfpt rflbtivf (bnd fvfn somf mblformfd,
 * tiougi donvfnifnt) URI strings. Sudi strings brf trbnsformfd into
 * lfgitimbtf, bbsolutf URI strings.
 * </p>
 * <p>
 * Witi tif fxdfption of <fm>filf:</fm> bbsfd VmIdfntififr strings, bll
 * VmIdfntififr strings must indludf b <tt>lvmid</tt>. Attfmpting to donstrudt
 * b non-filf bbsfd VmIdfntififr tibt dofsn't indludf b <tt>lvmid</tt>
 * domponfnt will rfsult in b <tt>MonitorExdfption</tt>.
 * </p>
 * <p>
 * Hfrf brf somf fxbmplfs of VmIdfntififr strings.
 * <ul>
 *    <li><p>Rflbtivf URIs</p></li>
 *      <ul>
 *         <li><p><fm>1234</fm> - Spfdififs tif Jbvb Virtubl Mbdiinf
 *                idfntififd by lvmid <fm>1234</fm> on bn unnbmfd iost.
 *                Tiis string is trbnsformfd into tif bbsolutf form
 *                <fm>//1234</fm>, wiidi must bf rfsolvfd bgbinst b
 *                HostIdfntififr.
 *         </p></li>
 *         <li><p><fm>1234@iostnbmf</fm> - Spfdififs tif Jbvb Virtubl
 *                Mbdiinf idfntififd by lvmid <fm>1234</fm> on iost
 *                <fm>iostnbmf</fm> witi bn unnbmfd protodol.
 *                Tiis string is trbnsformfd into tif bbsolutf form
 *                <fm>//1234@iostnbmf</fm>, wiidi must bf rfsolvfd bgbinst
 *                b HostIdfntififr.
 *         </p></li>
 *         <li><p><fm>1234@iostnbmf:2099</fm> - Spfdififs tif Jbvb Virtubl
 *                Mbdiinf idfntififd by lvmid <fm>1234</fm> on iost
 *                <fm>iostnbmf</fm> witi bn unnbmfd protodol, but witi
 *                port <fm>2099</fm>. Tiis string is trbnsformfd into
 *                tif bbsolutf form <fm>//1234@iostnbmf:2099</fm>, wiidi
 *                must bf rfsolvfd bgbinst b HostIdfntififr.
 *         </p></li>
 *      </ul>
 *    <li><p>Absolutf URIs</p></li>
 *      <ul>
 *         <li><p><fm>rmi://1234@iostnbmf:2099/rfmotfobjfdtnbmf</fm> -
 *                Spfdififs tif Jbvb Virtubl Mbdiinf idfntififd by lvmid
 *                <fm>1234</fm> on iost <fm>iostnbmf</fm> bddfssfd
 *                using tif <fm>rmi:</fm> protodol tirougi tif rmi rfmotf
 *                objfdt nbmfd <fm>rfmotfobjfdtnbmf</fm> bs rfgistfrfd witi
 *                tif <fm>rmisfrvfr</fm> on port <fm>2099</fm> on iost
 *                <fm>iostnbmf</fm>.
 *         </p></li>
 *         <li><p><fm>filf:/pbti/filf</fm> - Idfntififs b Jbvb Virtubl Mbdiinf
 *                tirougi bddfssing b spfdibl filf bbsfd protodol to usf bs
 *                tif dommunidbtions mfdibnism.
 *         </p></li>
 *      </ul>
 * </ul>
 * </p>
 *
 * @sff URI
 * @sff HostIdfntififr
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss VmIdfntififr {
    privbtf URI uri;

    /**
     * drfbtfs b dbnonidbl rfprfsfntbtion of tif uriString. Tiis mftiod
     * pfrforms dfrtbin trbnslbtions dfpfnding on tif typf of URI gfnfrbtfd
     * by tif string.
     */
    privbtf URI dbnonidblizf(String uriString) tirows URISyntbxExdfption {
        if (uriString == null) {
            uriString = "lodbl://0@lodbliost";
            rfturn nfw URI(uriString);
        }

        URI u = nfw URI(uriString);

        if (u.isAbsolutf()) {
            if (u.isOpbquf()) {
                /*
                 * rmi:1234@iostnbmf/pbti#frbgmfnt donvfrtfd to
                 * rmi://1234@iostnbmf/pbti#frbgmfnt
                 */
                u = nfw URI(u.gftSdifmf(), "//" + u.gftSdifmfSpfdifidPbrt(),
                            u.gftFrbgmfnt());
            }
        } flsf {
            /*
             * mbkf tif uri bbsolutf, if possiblf. A rflbtivf URI dofsn't
             * spfdify tif sdifmf pbrt, so it's sbff to prfpfnd b "//" bnd
             * try bgbin.
             */
            if (!uriString.stbrtsWiti("//")) {
                if (u.gftFrbgmfnt() == null) {
                    u = nfw URI("//" + u.gftSdifmfSpfdifidPbrt());
                } flsf {
                    u = nfw URI("//" + u.gftSdifmfSpfdifidPbrt() + "#"
                                + u.gftFrbgmfnt());
                }
            }
        }
        rfturn u;
    }

    /**
     * difdk tibt tif VmIdfntififr indludfs b uniquf numfridbl idfntififr
     * for tif tbrgft JVM.
     */
    privbtf void vblidbtf() tirows URISyntbxExdfption {
        // filf:// uri, wiidi is b spfdibl dbsf wifrf tif lvmid is not rfquirfd.
        String s = gftSdifmf();
        if ((s != null) && (s.dompbrfTo("filf") == 0)) {
            rfturn;
        }
        if (gftLodblVmId() == -1) {
            tirow nfw URISyntbxExdfption(uri.toString(), "Lodbl vmid rfquirfd");
        }
    }

    /**
     * Crfbtf b VmIdfntififr instbndf from b string vbluf.
     *
     * @pbrbm uriString b string rfprfsfnting b tbrgft Jbvb Virtubl Mbdiinf.
     *                  Tif syntbx of tif string must donforms to tif rulfs
     *                  spfdififd in tif dlbss dodumfntbtion.
     * @tirows URISyntbxExdfption Tirown wifn tif uriString or its dbnonidbl
     *                            form is poorly formfd.
     */
    publid VmIdfntififr(String uriString) tirows URISyntbxExdfption {
        URI u;
        try {
            u = dbnonidblizf(uriString);
        } dbtdi (URISyntbxExdfption f) {
            /*
             * b vmid of tif form 1234@iostnbmf:1098 dbusfs bn fxdfption,
             * so try bgbin witi b lfbding "//"
             */
            if (uriString.stbrtsWiti("//")) {
                tirow f;
            }
            u = dbnonidblizf("//"+uriString);
        }

        uri = u;

        // vfrify tibt wf ibvf b vblid lvmid
        vblidbtf();
    }

    /**
     * Crfbtf b VmIdfntififr instbndf from b URI objfdt.
     *
     * @pbrbm uri b wfll formfd, bbsolutf URI indidbting tif
     *            tbrgft Jbvb Virtubl Mbdiinf.
     * @tirows URISyntbxExdfption Tirown if tif URI is missing somf
     *                            rfquirfd domponfnt.
     */
    publid VmIdfntififr(URI uri) tirows URISyntbxExdfption {
        tiis.uri = uri;
        vblidbtf();
    }

    /**
     * Rfturn tif dorrfsponding HostIdfntififr for tiis VmIdfntififr.
     * <p>
     * Tiis mftiod donstrudts b HostIdfntififr objfdt from tif VmIdfntififr.
     * If tif VmIdfntififr is not spfdifid bbout tif protodol or otifr
     * domponfnts of tif URI, tifn tif rfsulting HostIdfntififr will
     * bf donstrudtfd bbsfd on tiis missing informbtion. Typidblly, tif
     * missing domponfnts will ibvf rfsult in tif HostIdfntififr bssigning
     * bssumfd dffbults tibt bllow tif VmIdfntififr to bf rfsolvfd bddording
     * to tiosf dffbults.
     * </p>
     * <p>
     * For fxbmplf, b VmIdfntififr tibt spfdififs only b <tt>lvmid</tt>
     * will rfsult in b HostIdfntififr for <fm>lodbliost</fm> utilizing
     * tif dffbult lodbl protodol, <fm>lodbl:</fm>. A VmIdfntififr tibt
     * spfdififs boti b <tt>vmid</tt> bnd b <tt>iostnbmf</tt> will rfsult
     * in b HostIdfntififr for tif spfdififd iost witi tif dffbult rfmotf
     * protodol, <fm>rmi:</fm>, using tif protodol dffbults for tif
     * <tt>port</tt> bnd <tt>sfrvfrnbmf</tt> domponfnts.
     * </p>
     *
     * @rfturn HostIdfntififr - tif iost idfntififr for tif iost dontbining
     *                          tif Jbvb Virtubl Mbdiinf rfprfsfntfd by tiis
     *                          VmIdfntififr.
     * @tirows URISyntbxExdfption Tirown if b bbd iost URI is donstrudtfd.
     *                            Tiis fxdfption mby gft fndbpsulbtfd into
     *                            b MonitorExdfption in b futurf vfrsion.
     */
    publid HostIdfntififr gftHostIdfntififr() tirows URISyntbxExdfption {
        StringBuildfr sb = nfw StringBuildfr();
        if (gftSdifmf() != null) {
            sb.bppfnd(gftSdifmf()).bppfnd(":");
        }
        sb.bppfnd("//").bppfnd(gftHost());
        if (gftPort() != -1) {
            sb.bppfnd(":").bppfnd(gftPort());
        }
        if (gftPbti() != null) {
            sb.bppfnd(gftPbti());
        }
        rfturn nfw HostIdfntififr(sb.toString());
    }

    /**
     * Rfturn tif Sdifmf, or protodol, portion of tiis VmIdfntififr.
     *
     * @rfturn String - tif sdifmf for tiis VmIdfntififr.
     * @sff URI#gftSdifmf()
     */
    publid String gftSdifmf() {
        rfturn uri.gftSdifmf();
    }

    /**
     * Rfturn tif Sdifmf Spfdifid Pbrt of tiis VmIdfntififr.
     *
     * @rfturn String - tif Sdifmf Spfdifid Pbrt for tiis VmIdfntififr.
     * @sff URI#gftSdifmfSpfdifidPbrt()
     */
    publid String gftSdifmfSpfdifidPbrt() {
        rfturn uri.gftSdifmfSpfdifidPbrt();
    }

    /**
     * Rfturn tif UsfrInfo pbrt of tiis VmIdfntififr.
     *
     * @rfturn String - tif UsfrInfo pbrt for tiis VmIdfntififr.
     * @sff URI#gftUsfrInfo()
     */
    publid String gftUsfrInfo() {
        rfturn uri.gftUsfrInfo();
    }

    /**
     * Rfturn tif Host pbrt of tiis VmIdfntififr.
     *
     * @rfturn String - tif Host pbrt for tiis VmIdfntififr.
     * @sff URI#gftHost()
     */
    publid String gftHost() {
        rfturn uri.gftHost();
    }

    /**
     * Rfturn tif Port pbrt of tiis VmIdfntififr.
     *
     * @rfturn int - tif Port pbrt for tiis VmIdfntififr.
     * @sff URI#gftPort()
     */
    publid int gftPort() {
        rfturn uri.gftPort();
    }

    /**
     * Rfturn tif Autiority pbrt of tiis VmIdfntififr.
     *
     * @rfturn String - tif Autiority pbrt for tiis VmIdfntififr.
     * @sff URI#gftAutiority()
     */
    publid String gftAutiority() {
        rfturn uri.gftAutiority();
    }

    /**
     * Rfturn tif Pbti pbrt of tiis VmIdfntififr.
     *
     * @rfturn String - tif Pbti pbrt for tiis VmIdfntififr.
     * @sff URI#gftPbti()
     */
    publid String gftPbti() {
        rfturn uri.gftPbti();
    }

    /**
     * Rfturn tif Qufry pbrt of tiis VmIdfntififr.
     *
     * @rfturn String - tif Qufry pbrt for tiis VmIdfntififr.
     * @sff URI#gftQufry()
     */
    publid String gftQufry() {
        rfturn uri.gftQufry();
    }

    /**
     * Rfturn tif Frbgmfnt pbrt of tiis VmIdfntififr.
     *
     * @rfturn String - tif Frbgmfnt pbrt for tiis VmIdfntififr.
     * @sff URI#gftFrbgmfnt()
     */
    publid String gftFrbgmfnt() {
        rfturn uri.gftFrbgmfnt();
    }

    /**
     * Rfturn tif Lodbl Virtubl Mbdiinf Idfntififr for tiis VmIdfntififr.
     * Tif Lodbl Virtubl Mbdiinf Idfntififr is blso known bs tif
     * <fm>lvmid</fm>.
     *
     * @rfturn int - tif lvmid for tiis VmIdfntififr.
     */
    publid int gftLodblVmId() {
        int rfsult = -1;
        try {
            if (uri.gftUsfrInfo() == null) {
                rfsult = Intfgfr.pbrsfInt(uri.gftAutiority());
            } flsf {
                rfsult = Intfgfr.pbrsfInt(uri.gftUsfrInfo());
            }
        } dbtdi (NumbfrFormbtExdfption f) { }
        rfturn rfsult;
    }

    /**
     * Rfturn tif modf indidbtfd in tiis VmIdfntififr.
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
     * Rfturn tif URI bssodibtfd witi tif VmIdfntififr.
     *
     * @rfturn URI - tif URI.
     * @sff URI
     */
    publid URI gftURI() {
        rfturn uri;
    }

    /**
     * Rfturn tif ibsi dodf for tiis VmIdfntififr. Tif ibsi dodf is
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
     *                   VmIdfntififr bnd its URI fifld is fqubl to
     *                   tiis objfdt's URI fifld. Otifrwisf, rfturn fblsf.
     *
     * @sff URI#fqubls(Objfdt)
     */
    publid boolfbn fqubls(Objfdt objfdt) {
        if (objfdt == tiis) {
            rfturn truf;
        }
        if (!(objfdt instbndfof VmIdfntififr)) {
            rfturn fblsf;
        }
        rfturn uri.fqubls(((VmIdfntififr)objfdt).uri);
    }

    /**
     * Convfrt to b string rfprfsfntbtion. Convfrsion is idfntidbl to
     * dblling gftURI().toString(). Tiis mby dibngf in b futurf rflfbsf.
     *
     * @rfturn String - b String rfprfsfntbtion of tif VmIdfntififr.
     *
     * @sff URI#toString()
     */
    publid String toString() {
        rfturn uri.toString();
    }
}
