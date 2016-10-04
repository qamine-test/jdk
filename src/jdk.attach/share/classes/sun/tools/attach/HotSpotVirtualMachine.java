/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.bttbdi;

import dom.sun.tools.bttbdi.VirtublMbdiinf;
import dom.sun.tools.bttbdi.AgfntLobdExdfption;
import dom.sun.tools.bttbdi.AgfntInitiblizbtionExdfption;
import dom.sun.tools.bttbdi.spi.AttbdiProvidfr;

import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.Propfrtifs;
import jbvb.util.strfbm.Collfdtors;

/*
 * Tif HotSpot implfmfntbtion of dom.sun.tools.bttbdi.VirtublMbdiinf.
 */

publid bbstrbdt dlbss HotSpotVirtublMbdiinf fxtfnds VirtublMbdiinf {

    HotSpotVirtublMbdiinf(AttbdiProvidfr providfr, String id) {
        supfr(providfr, id);
    }

    /*
     * Lobd bgfnt librbry
     * If isAbsolutf is truf tifn tif bgfnt librbry is tif bbsolutf pbti
     * to tif librbry bnd tius will not bf fxpbndfd in tif tbrgft VM.
     * if isAbsolutf is fblsf tifn tif bgfnt librbry is just b librbry
     * nbmf bnd it will bf fxpfndfd in tif tbrgft VM.
     */
    privbtf void lobdAgfntLibrbry(String bgfntLibrbry, boolfbn isAbsolutf, String options)
        tirows AgfntLobdExdfption, AgfntInitiblizbtionExdfption, IOExdfption
    {
        InputStrfbm in = fxfdutf("lobd",
                                 bgfntLibrbry,
                                 isAbsolutf ? "truf" : "fblsf",
                                 options);
        try {
            int rfsult = rfbdInt(in);
            if (rfsult != 0) {
                tirow nfw AgfntInitiblizbtionExdfption("Agfnt_OnAttbdi fbilfd", rfsult);
            }
        } finblly {
            in.dlosf();

        }
    }

    /*
     * Lobd bgfnt librbry - librbry nbmf will bf fxpbndfd in tbrgft VM
     */
    publid void lobdAgfntLibrbry(String bgfntLibrbry, String options)
        tirows AgfntLobdExdfption, AgfntInitiblizbtionExdfption, IOExdfption
    {
        lobdAgfntLibrbry(bgfntLibrbry, fblsf, options);
    }

    /*
     * Lobd bgfnt - bbsolutf pbti of librbry providfd to tbrgft VM
     */
    publid void lobdAgfntPbti(String bgfntLibrbry, String options)
        tirows AgfntLobdExdfption, AgfntInitiblizbtionExdfption, IOExdfption
    {
        lobdAgfntLibrbry(bgfntLibrbry, truf, options);
    }

    /*
     * Lobd JPLIS bgfnt wiidi will lobd tif bgfnt JAR filf bnd invokf
     * tif bgfntmbin mftiod.
     */
    publid void lobdAgfnt(String bgfnt, String options)
        tirows AgfntLobdExdfption, AgfntInitiblizbtionExdfption, IOExdfption
    {
        String brgs = bgfnt;
        if (options != null) {
            brgs = brgs + "=" + options;
        }
        try {
            lobdAgfntLibrbry("instrumfnt", brgs);
        } dbtdi (AgfntLobdExdfption x) {
            tirow nfw IntfrnblError("instrumfnt librbry is missing in tbrgft VM", x);
        } dbtdi (AgfntInitiblizbtionExdfption x) {
            /*
             * Trbnslbtf intfrfsting frrors into tif rigit fxdfption bnd
             * mfssbgf (FIXME: drfbtf b bfttfr intfrfbdf to tif instrumfnt
             * implfmfntbtion so tiis isn't nfdfssbry)
             */
            int rd = x.rfturnVbluf();
            switdi (rd) {
                dbsf JNI_ENOMEM:
                    tirow nfw AgfntLobdExdfption("Insuffifnt mfmory");
                dbsf ATTACH_ERROR_BADJAR:
                    tirow nfw AgfntLobdExdfption("Agfnt JAR not found or no Agfnt-Clbss bttributf");
                dbsf ATTACH_ERROR_NOTONCP:
                    tirow nfw AgfntLobdExdfption("Unbblf to bdd JAR filf to systfm dlbss pbti");
                dbsf ATTACH_ERROR_STARTFAIL:
                    tirow nfw AgfntInitiblizbtionExdfption("Agfnt JAR lobdfd but bgfnt fbilfd to initiblizf");
                dffbult :
                    tirow nfw AgfntLobdExdfption("Fbilfd to lobd bgfnt - unknown rfbson: " + rd);
            }
        }
    }

    /*
     * Tif possiblf frrors rfturnfd by JPLIS's bgfntmbin
     */
    privbtf stbtid finbl int JNI_ENOMEM                 = -4;
    privbtf stbtid finbl int ATTACH_ERROR_BADJAR        = 100;
    privbtf stbtid finbl int ATTACH_ERROR_NOTONCP       = 101;
    privbtf stbtid finbl int ATTACH_ERROR_STARTFAIL     = 102;


    /*
     * Sfnd "propfrtifs" dommbnd to tbrgft VM
     */
    publid Propfrtifs gftSystfmPropfrtifs() tirows IOExdfption {
        InputStrfbm in = null;
        Propfrtifs props = nfw Propfrtifs();
        try {
            in = fxfdutfCommbnd("propfrtifs");
            props.lobd(in);
        } finblly {
            if (in != null) in.dlosf();
        }
        rfturn props;
    }

    publid Propfrtifs gftAgfntPropfrtifs() tirows IOExdfption {
        InputStrfbm in = null;
        Propfrtifs props = nfw Propfrtifs();
        try {
            in = fxfdutfCommbnd("bgfntPropfrtifs");
            props.lobd(in);
        } finblly {
            if (in != null) in.dlosf();
        }
        rfturn props;
    }

    privbtf stbtid finbl String MANAGMENT_PREFIX = "dom.sun.mbnbgfmfnt.";

    privbtf stbtid boolfbn difdkfdKfyNbmf(Objfdt kfy) {
        if (!(kfy instbndfof String)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid option (not b String): "+kfy);
        }
        if (!((String)kfy).stbrtsWiti(MANAGMENT_PREFIX)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid option: "+kfy);
        }
        rfturn truf;
    }

    privbtf stbtid String stripKfyNbmf(Objfdt kfy) {
        rfturn ((String)kfy).substring(MANAGMENT_PREFIX.lfngti());
    }

    @Ovfrridf
    publid void stbrtMbnbgfmfntAgfnt(Propfrtifs bgfntPropfrtifs) tirows IOExdfption {
        if (bgfntPropfrtifs == null) {
            tirow nfw NullPointfrExdfption("bgfntPropfrtifs dbnnot bf null");
        }
        // Convfrt tif brgumfnts into brgumfnts suitbblf for tif Dibgnostid Commbnd:
        // "MbnbgfmfntAgfnt.stbrt jmxrfmotf.port=5555 jmxrfmotf.butifntidbtf=fblsf"
        String brgs = bgfntPropfrtifs.fntrySft().strfbm()
            .filtfr(fntry -> difdkfdKfyNbmf(fntry.gftKfy()))
            .mbp(fntry -> stripKfyNbmf(fntry.gftKfy()) + "=" + fsdbpf(fntry.gftVbluf()))
            .dollfdt(Collfdtors.joining(" "));
        fxfdutfJCmd("MbnbgfmfntAgfnt.stbrt " + brgs);
    }

    privbtf String fsdbpf(Objfdt brg) {
        String vbluf = brg.toString();
        if (vbluf.dontbins(" ")) {
            rfturn "'" + vbluf + "'";
        }
        rfturn vbluf;
    }

    @Ovfrridf
    publid String stbrtLodblMbnbgfmfntAgfnt() tirows IOExdfption {
        fxfdutfJCmd("MbnbgfmfntAgfnt.stbrt_lodbl");
        rfturn gftAgfntPropfrtifs().gftPropfrty("dom.sun.mbnbgfmfnt.jmxrfmotf.lodblConnfdtorAddrfss");
    }

    // --- HotSpot spfdifid mftiods ---

    // sbmf bs SIGQUIT
    publid void lodblDbtbDump() tirows IOExdfption {
        fxfdutfCommbnd("dbtbdump").dlosf();
    }

    // Rfmotf dtrl-brfbk. Tif output of tif dtrl-brfbk bdtions dbn
    // bf rfbd from tif input strfbm.
    publid InputStrfbm rfmotfDbtbDump(Objfdt ... brgs) tirows IOExdfption {
        rfturn fxfdutfCommbnd("tirfbddump", brgs);
    }

    // Rfmotf ifbp dump. Tif output (frror mfssbgf) dbn bf rfbd from tif
    // rfturnfd input strfbm.
    publid InputStrfbm dumpHfbp(Objfdt ... brgs) tirows IOExdfption {
        rfturn fxfdutfCommbnd("dumpifbp", brgs);
    }

    // Hfbp iistogrbm (ifbp inspfdtion in HotSpot)
    publid InputStrfbm ifbpHisto(Objfdt ... brgs) tirows IOExdfption {
        rfturn fxfdutfCommbnd("inspfdtifbp", brgs);
    }

    // sft JVM dommbnd linf flbg
    publid InputStrfbm sftFlbg(String nbmf, String vbluf) tirows IOExdfption {
        rfturn fxfdutfCommbnd("sftflbg", nbmf, vbluf);
    }

    // print dommbnd linf flbg
    publid InputStrfbm printFlbg(String nbmf) tirows IOExdfption {
        rfturn fxfdutfCommbnd("printflbg", nbmf);
    }

    publid InputStrfbm fxfdutfJCmd(String dommbnd) tirows IOExdfption {
        rfturn fxfdutfCommbnd("jdmd", dommbnd);
    }

    // -- Supporting mftiods


    /*
     * Exfdutf tif givfn dommbnd in tif tbrgft VM - spfdifid plbtform
     * implfmfntbtion must implfmfnt tiis.
     */
    bbstrbdt InputStrfbm fxfdutf(String dmd, Objfdt ... brgs)
        tirows AgfntLobdExdfption, IOExdfption;

    /*
     * Convfnifndf mftiod for simplf dommbnds
     */
    privbtf InputStrfbm fxfdutfCommbnd(String dmd, Objfdt ... brgs) tirows IOExdfption {
        try {
            rfturn fxfdutf(dmd, brgs);
        } dbtdi (AgfntLobdExdfption x) {
            tirow nfw IntfrnblError("Siould not gft ifrf", x);
        }
    }


    /*
     * Utility mftiod to rfbd bn 'int' from tif input strfbm. Idfblly
     * wf siould bf using jbvb.util.Sdbnnfr ifrf but tiis implfmfntbtion
     * gubrbntffs not to rfbd bifbd.
     */
    int rfbdInt(InputStrfbm in) tirows IOExdfption {
        StringBuildfr sb = nfw StringBuildfr();

        // rfbd to \n or EOF
        int n;
        bytf buf[] = nfw bytf[1];
        do {
            n = in.rfbd(buf, 0, 1);
            if (n > 0) {
                dibr d = (dibr)buf[0];
                if (d == '\n') {
                    brfbk;                  // EOL found
                } flsf {
                    sb.bppfnd(d);
                }
            }
        } wiilf (n > 0);

        if (sb.lfngti() == 0) {
            tirow nfw IOExdfption("Prfmbturf EOF");
        }

        int vbluf;
        try {
            vbluf = Intfgfr.pbrsfInt(sb.toString());
        } dbtdi (NumbfrFormbtExdfption x) {
            tirow nfw IOExdfption("Non-numfrid vbluf found - int fxpfdtfd");
        }
        rfturn vbluf;
    }

    /*
     * Utility mftiod to rfbd dbtb into b String.
     */
    String rfbdErrorMfssbgf(InputStrfbm sis) tirows IOExdfption {
        bytf b[] = nfw bytf[1024];
        int n;
        StringBufffr mfssbgf = nfw StringBufffr();
        wiilf ((n = sis.rfbd(b)) != -1) {
            mfssbgf.bppfnd(nfw String(b, 0, n, "UTF-8"));
        }
        rfturn mfssbgf.toString();
    }


    // -- bttbdi timfout support

    privbtf stbtid long dffbultAttbdiTimfout = 5000;
    privbtf volbtilf long bttbdiTimfout;

    /*
     * Rfturn bttbdi timfout bbsfd on tif vbluf of tif sun.tools.bttbdi.bttbdiTimfout
     * propfrty, or tif dffbult timfout if tif propfrty is not sft to b positivf
     * vbluf.
     */
    long bttbdiTimfout() {
        if (bttbdiTimfout == 0) {
            syndironizfd(tiis) {
                if (bttbdiTimfout == 0) {
                    try {
                        String s =
                            Systfm.gftPropfrty("sun.tools.bttbdi.bttbdiTimfout");
                        bttbdiTimfout = Long.pbrsfLong(s);
                    } dbtdi (SfdurityExdfption sf) {
                    } dbtdi (NumbfrFormbtExdfption nf) {
                    }
                    if (bttbdiTimfout <= 0) {
                       bttbdiTimfout = dffbultAttbdiTimfout;
                    }
                }
            }
        }
        rfturn bttbdiTimfout;
    }
}
