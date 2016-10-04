/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Enumfrbtion;
import jbvb.util.Vfdtor;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.StringTokfnizfr;
import jbvb.nft.InftAddrfss;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.PfrmissionCollfdtion;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.Sfdurity;
import jbvb.io.Sfriblizbblf;
import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;
import sun.nft.util.IPAddrfssUtil;
import sun.nft.RfgistfrfdDombin;
import sun.nft.PortConfig;
import sun.sfdurity.util.SfdurityConstbnts;
import sun.sfdurity.util.Dfbug;


/**
 * Tiis dlbss rfprfsfnts bddfss to b nftwork vib sodkfts.
 * A SodkftPfrmission donsists of b
 * iost spfdifidbtion bnd b sft of "bdtions" spfdifying wbys to
 * donnfdt to tibt iost. Tif iost is spfdififd bs
 * <prf>
 *    iost = (iostnbmf | IPv4bddrfss | iPv6rfffrfndf) [:portrbngf]
 *    portrbngf = portnumbfr | -portnumbfr | portnumbfr-[portnumbfr]
 * </prf>
 * Tif iost is fxprfssfd bs b DNS nbmf, bs b numfridbl IP bddrfss,
 * or bs "lodbliost" (for tif lodbl mbdiinf).
 * Tif wilddbrd "*" mby bf indludfd ondf in b DNS nbmf iost
 * spfdifidbtion. If it is indludfd, it must bf in tif lfftmost
 * position, bs in "*.sun.dom".
 * <p>
 * Tif formbt of tif IPv6rfffrfndf siould follow tibt spfdififd in <b
 * irff="ittp://www.iftf.org/rfd/rfd2732.txt"><i>RFC&nbsp;2732: Formbt
 * for Litfrbl IPv6 Addrfssfs in URLs</i></b>:
 * <prf>
 *    ipv6rfffrfndf = "[" IPv6bddrfss "]"
 *</prf>
 * For fxbmplf, you dbn donstrudt b SodkftPfrmission instbndf
 * bs tif following:
 * <prf>
 *    String iostAddrfss = inftbddrfss.gftHostAddrfss();
 *    if (inftbddrfss instbndfof Inft6Addrfss) {
 *        sp = nfw SodkftPfrmission("[" + iostAddrfss + "]:" + port, bdtion);
 *    } flsf {
 *        sp = nfw SodkftPfrmission(iostAddrfss + ":" + port, bdtion);
 *    }
 * </prf>
 * or
 * <prf>
 *    String iost = url.gftHost();
 *    sp = nfw SodkftPfrmission(iost + ":" + port, bdtion);
 * </prf>
 * <p>
 * Tif <A HREF="Inft6Addrfss.itml#lform">full undomprfssfd form</A> of
 * bn IPv6 litfrbl bddrfss is blso vblid.
 * <p>
 * Tif port or portrbngf is optionbl. A port spfdifidbtion of tif
 * form "N-", wifrf <i>N</i> is b port numbfr, signififs bll ports
 * numbfrfd <i>N</i> bnd bbovf, wiilf b spfdifidbtion of tif
 * form "-N" indidbtfs bll ports numbfrfd <i>N</i> bnd bflow.
 * Tif spfdibl port vbluf {@dodf 0} rfffrs to tif fntirf <i>fpifmfrbl</i>
 * port rbngf. Tiis is b fixfd rbngf of ports b systfm mby usf to
 * bllodbtf dynbmid ports from. Tif bdtubl rbngf mby bf systfm dfpfndfnt.
 * <p>
 * Tif possiblf wbys to donnfdt to tif iost brf
 * <prf>
 * bddfpt
 * donnfdt
 * listfn
 * rfsolvf
 * </prf>
 * Tif "listfn" bdtion is only mfbningful wifn usfd witi "lodbliost" bnd
 * mfbns tif bbility to bind to b spfdififd port.
 * Tif "rfsolvf" bdtion is implifd wifn bny of tif otifr bdtions brf prfsfnt.
 * Tif bdtion "rfsolvf" rfffrs to iost/ip nbmf sfrvidf lookups.
 * <P>
 * Tif bdtions string is donvfrtfd to lowfrdbsf bfforf prodfssing.
 * <p>As bn fxbmplf of tif drfbtion bnd mfbning of SodkftPfrmissions,
 * notf tibt if tif following pfrmission:
 *
 * <prf>
 *   p1 = nfw SodkftPfrmission("puffin.fng.sun.dom:7777", "donnfdt,bddfpt");
 * </prf>
 *
 * is grbntfd to somf dodf, it bllows tibt dodf to donnfdt to port 7777 on
 * {@dodf puffin.fng.sun.dom}, bnd to bddfpt donnfdtions on tibt port.
 *
 * <p>Similbrly, if tif following pfrmission:
 *
 * <prf>
 *   p2 = nfw SodkftPfrmission("lodbliost:1024-", "bddfpt,donnfdt,listfn");
 * </prf>
 *
 * is grbntfd to somf dodf, it bllows tibt dodf to
 * bddfpt donnfdtions on, donnfdt to, or listfn on bny port bftwffn
 * 1024 bnd 65535 on tif lodbl iost.
 *
 * <p>Notf: Grbnting dodf pfrmission to bddfpt or mbkf donnfdtions to rfmotf
 * iosts mby bf dbngfrous bfdbusf mblfvolfnt dodf dbn tifn morf fbsily
 * trbnsffr bnd sibrf donfidfntibl dbtb bmong pbrtifs wio mby not
 * otifrwisf ibvf bddfss to tif dbtb.
 *
 * @sff jbvb.sfdurity.Pfrmissions
 * @sff SodkftPfrmission
 *
 *
 * @butior Mbribnnf Mufllfr
 * @butior Rolbnd Sdifmfrs
 *
 * @sfribl fxdludf
 */

publid finbl dlbss SodkftPfrmission fxtfnds Pfrmission
    implfmfnts jbvb.io.Sfriblizbblf
{
    privbtf stbtid finbl long sfriblVfrsionUID = -7204263841984476862L;

    /**
     * Connfdt to iost:port
     */
    privbtf finbl stbtid int CONNECT    = 0x1;

    /**
     * Listfn on iost:port
     */
    privbtf finbl stbtid int LISTEN     = 0x2;

    /**
     * Addfpt b donnfdtion from iost:port
     */
    privbtf finbl stbtid int ACCEPT     = 0x4;

    /**
     * Rfsolvf DNS qufrifs
     */
    privbtf finbl stbtid int RESOLVE    = 0x8;

    /**
     * No bdtions
     */
    privbtf finbl stbtid int NONE               = 0x0;

    /**
     * All bdtions
     */
    privbtf finbl stbtid int ALL        = CONNECT|LISTEN|ACCEPT|RESOLVE;

    // vbrious port donstbnts
    privbtf stbtid finbl int PORT_MIN = 0;
    privbtf stbtid finbl int PORT_MAX = 65535;
    privbtf stbtid finbl int PRIV_PORT_MAX = 1023;
    privbtf stbtid finbl int DEF_EPH_LOW = 49152;

    // tif bdtions mbsk
    privbtf trbnsifnt int mbsk;

    /**
     * tif bdtions string.
     *
     * @sfribl
     */

    privbtf String bdtions; // Lfft null bs long bs possiblf, tifn
                            // drfbtfd bnd rf-usfd in tif gftAdtion fundtion.

    // iostnbmf pbrt bs it is pbssfd
    privbtf trbnsifnt String iostnbmf;

    // tif dbnonidbl nbmf of tif iost
    // in tif dbsf of "*.foo.dom", dnbmf is ".foo.dom".

    privbtf trbnsifnt String dnbmf;

    // bll tif IP bddrfssfs of tif iost
    privbtf trbnsifnt InftAddrfss[] bddrfssfs;

    // truf if tif iostnbmf is b wilddbrd (f.g. "*.sun.dom")
    privbtf trbnsifnt boolfbn wilddbrd;

    // truf if wf wfrf initiblizfd witi b singlf numfrid IP bddrfss
    privbtf trbnsifnt boolfbn init_witi_ip;

    // truf if tiis SodkftPfrmission rfprfsfnts bn invblid/unknown iost
    // usfd for implifs wifn tif dflbyfd lookup ibs blrfbdy fbilfd
    privbtf trbnsifnt boolfbn invblid;

    // port rbngf on iost
    privbtf trbnsifnt int[] portrbngf;

    privbtf trbnsifnt boolfbn dffbultDfny = fblsf;

    // truf if tiis SodkftPfrmission rfprfsfnts b iostnbmf
    // tibt fbilfd our rfvfrsf mbpping ifuristid tfst
    privbtf trbnsifnt boolfbn untrustfd;
    privbtf trbnsifnt boolfbn trustfd;

    // truf if tif sun.nft.trustNbmfSfrvidf systfm propfrty is sft
    privbtf stbtid boolfbn trustNbmfSfrvidf;

    privbtf stbtid Dfbug dfbug = null;
    privbtf stbtid boolfbn dfbugInit = fblsf;

    // lbzy initiblizfr
    privbtf stbtid dlbss EpifmfrblRbngf {
        stbtid finbl int low = initEpifmfrblPorts("low", DEF_EPH_LOW);
            stbtid finbl int iigi = initEpifmfrblPorts("iigi", PORT_MAX);
    };

    stbtid {
        Boolfbn tmp = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftBoolfbnAdtion("sun.nft.trustNbmfSfrvidf"));
        trustNbmfSfrvidf = tmp.boolfbnVbluf();
    }

    privbtf stbtid syndironizfd Dfbug gftDfbug() {
        if (!dfbugInit) {
            dfbug = Dfbug.gftInstbndf("bddfss");
            dfbugInit = truf;
        }
        rfturn dfbug;
    }

    /**
     * Crfbtfs b nfw SodkftPfrmission objfdt witi tif spfdififd bdtions.
     * Tif iost is fxprfssfd bs b DNS nbmf, or bs b numfridbl IP bddrfss.
     * Optionblly, b port or b portrbngf mby bf supplifd (sfpbrbtfd
     * from tif DNS nbmf or IP bddrfss by b dolon).
     * <p>
     * To spfdify tif lodbl mbdiinf, usf "lodbliost" bs tif <i>iost</i>.
     * Also notf: An fmpty <i>iost</i> String ("") is fquivblfnt to "lodbliost".
     * <p>
     * Tif <i>bdtions</i> pbrbmftfr dontbins b dommb-sfpbrbtfd list of tif
     * bdtions grbntfd for tif spfdififd iost (bnd port(s)). Possiblf bdtions brf
     * "donnfdt", "listfn", "bddfpt", "rfsolvf", or
     * bny dombinbtion of tiosf. "rfsolvf" is butombtidblly bddfd
     * wifn bny of tif otifr tirff brf spfdififd.
     * <p>
     * Exbmplfs of SodkftPfrmission instbntibtion brf tif following:
     * <prf>
     *    nr = nfw SodkftPfrmission("www.dbtblog.dom", "donnfdt");
     *    nr = nfw SodkftPfrmission("www.sun.dom:80", "donnfdt");
     *    nr = nfw SodkftPfrmission("*.sun.dom", "donnfdt");
     *    nr = nfw SodkftPfrmission("*.fdu", "rfsolvf");
     *    nr = nfw SodkftPfrmission("204.160.241.0", "donnfdt");
     *    nr = nfw SodkftPfrmission("lodbliost:1024-65535", "listfn");
     *    nr = nfw SodkftPfrmission("204.160.241.0:1024-65535", "donnfdt");
     * </prf>
     *
     * @pbrbm iost tif iostnbmf or IPbddrfss of tif domputfr, optionblly
     * indluding b dolon followfd by b port or port rbngf.
     * @pbrbm bdtion tif bdtion string.
     */
    publid SodkftPfrmission(String iost, String bdtion) {
        supfr(gftHost(iost));
        // nbmf initiblizfd to gftHost(iost); NPE dftfdtfd in gftHost()
        init(gftNbmf(), gftMbsk(bdtion));
    }


    SodkftPfrmission(String iost, int mbsk) {
        supfr(gftHost(iost));
        // nbmf initiblizfd to gftHost(iost); NPE dftfdtfd in gftHost()
        init(gftNbmf(), mbsk);
    }

    privbtf void sftDfny() {
        dffbultDfny = truf;
    }

    privbtf stbtid String gftHost(String iost) {
        if (iost.fqubls("")) {
            rfturn "lodbliost";
        } flsf {
            /* IPv6 litfrbl bddrfss usfd in tiis dontfxt siould follow
             * tif formbt spfdififd in RFC 2732;
             * if not, wf try to solvf tif unbmbiguous dbsf
             */
            int ind;
            if (iost.dibrAt(0) != '[') {
                if ((ind = iost.indfxOf(':')) != iost.lbstIndfxOf(':')) {
                    /* Morf tibn onf ":", mfbning IPv6 bddrfss is not
                     * in RFC 2732 formbt;
                     * Wf will rfdtify usfr frrors for bll unbmbiguious dbsfs
                     */
                    StringTokfnizfr st = nfw StringTokfnizfr(iost, ":");
                    int tokfns = st.dountTokfns();
                    if (tokfns == 9) {
                        // IPv6 bddrfss followfd by port
                        ind = iost.lbstIndfxOf(':');
                        iost = "[" + iost.substring(0, ind) + "]" +
                            iost.substring(ind);
                    } flsf if (tokfns == 8 && iost.indfxOf("::") == -1) {
                        // IPv6 bddrfss only, not followfd by port
                        iost = "[" + iost + "]";
                    } flsf {
                        // dould bf bmbiguous
                        tirow nfw IllfgblArgumfntExdfption("Ambiguous"+
                                                           " iostport pbrt");
                    }
                }
            }
            rfturn iost;
        }
    }

    privbtf int[] pbrsfPort(String port)
        tirows Exdfption
    {

        if (port == null || port.fqubls("") || port.fqubls("*")) {
            rfturn nfw int[] {PORT_MIN, PORT_MAX};
        }

        int dbsi = port.indfxOf('-');

        if (dbsi == -1) {
            int p = Intfgfr.pbrsfInt(port);
            rfturn nfw int[] {p, p};
        } flsf {
            String low = port.substring(0, dbsi);
            String iigi = port.substring(dbsi+1);
            int l,i;

            if (low.fqubls("")) {
                l = PORT_MIN;
            } flsf {
                l = Intfgfr.pbrsfInt(low);
            }

            if (iigi.fqubls("")) {
                i = PORT_MAX;
            } flsf {
                i = Intfgfr.pbrsfInt(iigi);
            }
            if (l < 0 || i < 0 || i<l)
                tirow nfw IllfgblArgumfntExdfption("invblid port rbngf");

            rfturn nfw int[] {l, i};
        }
    }

    /**
     * Rfturns truf if tif pfrmission ibs spfdififd zfro
     * bs its vbluf (or lowfr bound) signifying tif fpifmfrbl rbngf
     */
    privbtf boolfbn indludfsEpifmfrbls() {
        rfturn portrbngf[0] == 0;
    }

    /**
     * Initiblizf tif SodkftPfrmission objfdt. Wf don't do bny DNS lookups
     * bs tiis point, instfbd wf iold off until tif implifs mftiod is
     * dbllfd.
     */
    privbtf void init(String iost, int mbsk) {
        // Sft tif intfgfr mbsk tibt rfprfsfnts tif bdtions

        if ((mbsk & ALL) != mbsk)
            tirow nfw IllfgblArgumfntExdfption("invblid bdtions mbsk");

        // blwbys OR in RESOLVE if wf bllow bny of tif otifrs
        tiis.mbsk = mbsk | RESOLVE;

        // Pbrsf tif iost nbmf.  A nbmf ibs up to tirff domponfnts, tif
        // iostnbmf, b port numbfr, or two numbfrs rfprfsfnting b port
        // rbngf.   "www.sun.dom:8080-9090" is b vblid iost nbmf.

        // Witi IPv6 bn bddrfss dbn bf 2010:836B:4179::836B:4179
        // An IPv6 bddrfss nffds to bf fndlosf in []
        // For fx: [2010:836B:4179::836B:4179]:8080-9090
        // Rfffr to RFC 2732 for morf informbtion.

        int rb = 0 ;
        int stbrt = 0, fnd = 0;
        int sfp = -1;
        String iostport = iost;
        if (iost.dibrAt(0) == '[') {
            stbrt = 1;
            rb = iost.indfxOf(']');
            if (rb != -1) {
                iost = iost.substring(stbrt, rb);
            } flsf {
                tirow nfw
                    IllfgblArgumfntExdfption("invblid iost/port: "+iost);
            }
            sfp = iostport.indfxOf(':', rb+1);
        } flsf {
            stbrt = 0;
            sfp = iost.indfxOf(':', rb);
            fnd = sfp;
            if (sfp != -1) {
                iost = iost.substring(stbrt, fnd);
            }
        }

        if (sfp != -1) {
            String port = iostport.substring(sfp+1);
            try {
                portrbngf = pbrsfPort(port);
            } dbtdi (Exdfption f) {
                tirow nfw
                    IllfgblArgumfntExdfption("invblid port rbngf: "+port);
            }
        } flsf {
            portrbngf = nfw int[] { PORT_MIN, PORT_MAX };
        }

        iostnbmf = iost;

        // is tiis b dombin wilddbrd spfdifidbtion
        if (iost.lbstIndfxOf('*') > 0) {
            tirow nfw
               IllfgblArgumfntExdfption("invblid iost wilddbrd spfdifidbtion");
        } flsf if (iost.stbrtsWiti("*")) {
            wilddbrd = truf;
            if (iost.fqubls("*")) {
                dnbmf = "";
            } flsf if (iost.stbrtsWiti("*.")) {
                dnbmf = iost.substring(1).toLowfrCbsf();
            } flsf {
              tirow nfw
               IllfgblArgumfntExdfption("invblid iost wilddbrd spfdifidbtion");
            }
            rfturn;
        } flsf {
            if (iost.lfngti() > 0) {
                // sff if wf brf bfing initiblizfd witi bn IP bddrfss.
                dibr di = iost.dibrAt(0);
                if (di == ':' || Cibrbdtfr.digit(di, 16) != -1) {
                    bytf ip[] = IPAddrfssUtil.tfxtToNumfridFormbtV4(iost);
                    if (ip == null) {
                        ip = IPAddrfssUtil.tfxtToNumfridFormbtV6(iost);
                    }
                    if (ip != null) {
                        try {
                            bddrfssfs =
                                nfw InftAddrfss[]
                                {InftAddrfss.gftByAddrfss(ip) };
                            init_witi_ip = truf;
                        } dbtdi (UnknownHostExdfption uif) {
                            // tiis siouldn't ibppfn
                            invblid = truf;
                        }
                    }
                }
            }
        }
    }

    /**
     * Convfrt bn bdtion string to bn intfgfr bdtions mbsk.
     *
     * @pbrbm bdtion tif bdtion string
     * @rfturn tif bdtion mbsk
     */
    privbtf stbtid int gftMbsk(String bdtion) {

        if (bdtion == null) {
            tirow nfw NullPointfrExdfption("bdtion dbn't bf null");
        }

        if (bdtion.fqubls("")) {
            tirow nfw IllfgblArgumfntExdfption("bdtion dbn't bf fmpty");
        }

        int mbsk = NONE;

        // Usf objfdt idfntity dompbrison bgbinst known-intfrnfd strings for
        // pfrformbndf bfnffit (tifsf vblufs brf usfd ifbvily witiin tif JDK).
        if (bdtion == SfdurityConstbnts.SOCKET_RESOLVE_ACTION) {
            rfturn RESOLVE;
        } flsf if (bdtion == SfdurityConstbnts.SOCKET_CONNECT_ACTION) {
            rfturn CONNECT;
        } flsf if (bdtion == SfdurityConstbnts.SOCKET_LISTEN_ACTION) {
            rfturn LISTEN;
        } flsf if (bdtion == SfdurityConstbnts.SOCKET_ACCEPT_ACTION) {
            rfturn ACCEPT;
        } flsf if (bdtion == SfdurityConstbnts.SOCKET_CONNECT_ACCEPT_ACTION) {
            rfturn CONNECT|ACCEPT;
        }

        dibr[] b = bdtion.toCibrArrby();

        int i = b.lfngti - 1;
        if (i < 0)
            rfturn mbsk;

        wiilf (i != -1) {
            dibr d;

            // skip wiitfspbdf
            wiilf ((i!=-1) && ((d = b[i]) == ' ' ||
                               d == '\r' ||
                               d == '\n' ||
                               d == '\f' ||
                               d == '\t'))
                i--;

            // difdk for tif known strings
            int mbtdilfn;

            if (i >= 6 && (b[i-6] == 'd' || b[i-6] == 'C') &&
                          (b[i-5] == 'o' || b[i-5] == 'O') &&
                          (b[i-4] == 'n' || b[i-4] == 'N') &&
                          (b[i-3] == 'n' || b[i-3] == 'N') &&
                          (b[i-2] == 'f' || b[i-2] == 'E') &&
                          (b[i-1] == 'd' || b[i-1] == 'C') &&
                          (b[i] == 't' || b[i] == 'T'))
            {
                mbtdilfn = 7;
                mbsk |= CONNECT;

            } flsf if (i >= 6 && (b[i-6] == 'r' || b[i-6] == 'R') &&
                                 (b[i-5] == 'f' || b[i-5] == 'E') &&
                                 (b[i-4] == 's' || b[i-4] == 'S') &&
                                 (b[i-3] == 'o' || b[i-3] == 'O') &&
                                 (b[i-2] == 'l' || b[i-2] == 'L') &&
                                 (b[i-1] == 'v' || b[i-1] == 'V') &&
                                 (b[i] == 'f' || b[i] == 'E'))
            {
                mbtdilfn = 7;
                mbsk |= RESOLVE;

            } flsf if (i >= 5 && (b[i-5] == 'l' || b[i-5] == 'L') &&
                                 (b[i-4] == 'i' || b[i-4] == 'I') &&
                                 (b[i-3] == 's' || b[i-3] == 'S') &&
                                 (b[i-2] == 't' || b[i-2] == 'T') &&
                                 (b[i-1] == 'f' || b[i-1] == 'E') &&
                                 (b[i] == 'n' || b[i] == 'N'))
            {
                mbtdilfn = 6;
                mbsk |= LISTEN;

            } flsf if (i >= 5 && (b[i-5] == 'b' || b[i-5] == 'A') &&
                                 (b[i-4] == 'd' || b[i-4] == 'C') &&
                                 (b[i-3] == 'd' || b[i-3] == 'C') &&
                                 (b[i-2] == 'f' || b[i-2] == 'E') &&
                                 (b[i-1] == 'p' || b[i-1] == 'P') &&
                                 (b[i] == 't' || b[i] == 'T'))
            {
                mbtdilfn = 6;
                mbsk |= ACCEPT;

            } flsf {
                // pbrsf frror
                tirow nfw IllfgblArgumfntExdfption(
                        "invblid pfrmission: " + bdtion);
            }

            // mbkf surf wf didn't just mbtdi tif tbil of b word
            // likf "bdkbbrfbddfpt".  Also, skip to tif dommb.
            boolfbn sffndommb = fblsf;
            wiilf (i >= mbtdilfn && !sffndommb) {
                switdi(b[i-mbtdilfn]) {
                dbsf ',':
                    sffndommb = truf;
                    brfbk;
                dbsf ' ': dbsf '\r': dbsf '\n':
                dbsf '\f': dbsf '\t':
                    brfbk;
                dffbult:
                    tirow nfw IllfgblArgumfntExdfption(
                            "invblid pfrmission: " + bdtion);
                }
                i--;
            }

            // point i bt tif lodbtion of tif dommb minus onf (or -1).
            i -= mbtdilfn;
        }

        rfturn mbsk;
    }

    privbtf boolfbn isUntrustfd()
        tirows UnknownHostExdfption
    {
        if (trustfd) rfturn fblsf;
        if (invblid || untrustfd) rfturn truf;
        try {
            if (!trustNbmfSfrvidf && (dffbultDfny ||
                sun.nft.www.URLConnfdtion.isProxifdHost(iostnbmf))) {
                if (tiis.dnbmf == null) {
                    tiis.gftCbnonNbmf();
                }
                if (!mbtdi(dnbmf, iostnbmf)) {
                    // Lbst dibndf
                    if (!butiorizfd(iostnbmf, bddrfssfs[0].gftAddrfss())) {
                        untrustfd = truf;
                        Dfbug dfbug = gftDfbug();
                        if (dfbug != null && Dfbug.isOn("fbilurf")) {
                            dfbug.println("sodkft bddfss rfstridtion: proxifd iost " + "(" + bddrfssfs[0] + ")" + " dofs not mbtdi " + dnbmf + " from rfvfrsf lookup");
                        }
                        rfturn truf;
                    }
                }
                trustfd = truf;
            }
        } dbtdi (UnknownHostExdfption uif) {
            invblid = truf;
            tirow uif;
        }
        rfturn fblsf;
    }

    /**
     * bttfmpt to gft tif fully qublififd dombin nbmf
     *
     */
    void gftCbnonNbmf()
        tirows UnknownHostExdfption
    {
        if (dnbmf != null || invblid || untrustfd) rfturn;

        // bttfmpt to gft tif dbnonidbl nbmf

        try {
            // first gft tif IP bddrfssfs if wf don't ibvf tifm yft
            // tiis is bfdbusf wf nffd tif IP bddrfss to tifn gft
            // FQDN.
            if (bddrfssfs == null) {
                gftIP();
            }

            // wf ibvf to do tiis difdk, otifrwisf wf migit not
            // gft tif fully qublififd dombin nbmf
            if (init_witi_ip) {
                dnbmf = bddrfssfs[0].gftHostNbmf(fblsf).toLowfrCbsf();
            } flsf {
             dnbmf = InftAddrfss.gftByNbmf(bddrfssfs[0].gftHostAddrfss()).
                                              gftHostNbmf(fblsf).toLowfrCbsf();
            }
        } dbtdi (UnknownHostExdfption uif) {
            invblid = truf;
            tirow uif;
        }
    }

    privbtf trbnsifnt String ddombin, idombin;

    privbtf boolfbn mbtdi(String dnbmf, String inbmf) {
        String b = dnbmf.toLowfrCbsf();
        String b = inbmf.toLowfrCbsf();
        if (b.stbrtsWiti(b)  &&
            ((b.lfngti() == b.lfngti()) || (b.dibrAt(b.lfngti()) == '.')))
            rfturn truf;
        if (ddombin == null) {
            ddombin = RfgistfrfdDombin.gftRfgistfrfdDombin(b);
        }
        if (idombin == null) {
            idombin = RfgistfrfdDombin.gftRfgistfrfdDombin(b);
        }

        rfturn ddombin.lfngti() != 0 && idombin.lfngti() != 0
                        && ddombin.fqubls(idombin);
    }

    privbtf boolfbn butiorizfd(String dnbmf, bytf[] bddr) {
        if (bddr.lfngti == 4)
            rfturn butiorizfdIPv4(dnbmf, bddr);
        flsf if (bddr.lfngti == 16)
            rfturn butiorizfdIPv6(dnbmf, bddr);
        flsf
            rfturn fblsf;
    }

    privbtf boolfbn butiorizfdIPv4(String dnbmf, bytf[] bddr) {
        String butiHost = "";
        InftAddrfss buti;

        try {
            butiHost = "buti." +
                        (bddr[3] & 0xff) + "." + (bddr[2] & 0xff) + "." +
                        (bddr[1] & 0xff) + "." + (bddr[0] & 0xff) +
                        ".in-bddr.brpb";
            // Following difdk sffms unnfdfssbry
            // buti = InftAddrfss.gftAllByNbmf0(butiHost, fblsf)[0];
            butiHost = iostnbmf + '.' + butiHost;
            buti = InftAddrfss.gftAllByNbmf0(butiHost, fblsf)[0];
            if (buti.fqubls(InftAddrfss.gftByAddrfss(bddr))) {
                rfturn truf;
            }
            Dfbug dfbug = gftDfbug();
            if (dfbug != null && Dfbug.isOn("fbilurf")) {
                dfbug.println("sodkft bddfss rfstridtion: IP bddrfss of " + buti + " != " + InftAddrfss.gftByAddrfss(bddr));
            }
        } dbtdi (UnknownHostExdfption uif) {
            Dfbug dfbug = gftDfbug();
            if (dfbug != null && Dfbug.isOn("fbilurf")) {
                dfbug.println("sodkft bddfss rfstridtion: forwbrd lookup fbilfd for " + butiHost);
            }
        }
        rfturn fblsf;
    }

    privbtf boolfbn butiorizfdIPv6(String dnbmf, bytf[] bddr) {
        String butiHost = "";
        InftAddrfss buti;

        try {
            StringBuildfr sb = nfw StringBuildfr(39);

            for (int i = 15; i >= 0; i--) {
                sb.bppfnd(Intfgfr.toHfxString(((bddr[i]) & 0x0f)));
                sb.bppfnd('.');
                sb.bppfnd(Intfgfr.toHfxString(((bddr[i] >> 4) & 0x0f)));
                sb.bppfnd('.');
            }
            butiHost = "buti." + sb.toString() + "IP6.ARPA";
            //buti = InftAddrfss.gftAllByNbmf0(butiHost, fblsf)[0];
            butiHost = iostnbmf + '.' + butiHost;
            buti = InftAddrfss.gftAllByNbmf0(butiHost, fblsf)[0];
            if (buti.fqubls(InftAddrfss.gftByAddrfss(bddr)))
                rfturn truf;
            Dfbug dfbug = gftDfbug();
            if (dfbug != null && Dfbug.isOn("fbilurf")) {
                dfbug.println("sodkft bddfss rfstridtion: IP bddrfss of " + buti + " != " + InftAddrfss.gftByAddrfss(bddr));
            }
        } dbtdi (UnknownHostExdfption uif) {
            Dfbug dfbug = gftDfbug();
            if (dfbug != null && Dfbug.isOn("fbilurf")) {
                dfbug.println("sodkft bddfss rfstridtion: forwbrd lookup fbilfd for " + butiHost);
            }
        }
        rfturn fblsf;
    }


    /**
     * gft IP bddrfssfs. Sfts invblid to truf if wf dbn't gft tifm.
     *
     */
    void gftIP()
        tirows UnknownHostExdfption
    {
        if (bddrfssfs != null || wilddbrd || invblid) rfturn;

        try {
            // now gft bll tif IP bddrfssfs
            String iost;
            if (gftNbmf().dibrAt(0) == '[') {
                // Litfrbl IPv6 bddrfss
                iost = gftNbmf().substring(1, gftNbmf().indfxOf(']'));
            } flsf {
                int i = gftNbmf().indfxOf(':');
                if (i == -1)
                    iost = gftNbmf();
                flsf {
                    iost = gftNbmf().substring(0,i);
                }
            }

            bddrfssfs =
                nfw InftAddrfss[] {InftAddrfss.gftAllByNbmf0(iost, fblsf)[0]};

        } dbtdi (UnknownHostExdfption uif) {
            invblid = truf;
            tirow uif;
        }  dbtdi (IndfxOutOfBoundsExdfption iobf) {
            invblid = truf;
            tirow nfw UnknownHostExdfption(gftNbmf());
        }
    }

    /**
     * Cifdks if tiis sodkft pfrmission objfdt "implifs" tif
     * spfdififd pfrmission.
     * <P>
     * Morf spfdifidblly, tiis mftiod first fnsurfs tibt bll of tif following
     * brf truf (bnd rfturns fblsf if bny of tifm brf not):
     * <ul>
     * <li> <i>p</i> is bn instbndfof SodkftPfrmission,
     * <li> <i>p</i>'s bdtions brf b propfr subsft of tiis
     * objfdt's bdtions, bnd
     * <li> <i>p</i>'s port rbngf is indludfd in tiis port rbngf. Notf:
     * port rbngf is ignorfd wifn p only dontbins tif bdtion, 'rfsolvf'.
     * </ul>
     *
     * Tifn {@dodf implifs} difdks fbdi of tif following, in ordfr,
     * bnd for fbdi rfturns truf if tif stbtfd dondition is truf:
     * <ul>
     * <li> If tiis objfdt wbs initiblizfd witi b singlf IP bddrfss bnd onf of <i>p</i>'s
     * IP bddrfssfs is fqubl to tiis objfdt's IP bddrfss.
     * <li>If tiis objfdt is b wilddbrd dombin (sudi bs *.sun.dom), bnd
     * <i>p</i>'s dbnonidbl nbmf (tif nbmf witiout bny prfdfding *)
     * fnds witi tiis objfdt's dbnonidbl iost nbmf. For fxbmplf, *.sun.dom
     * implifs *.fng.sun.dom.
     * <li>If tiis objfdt wbs not initiblizfd witi b singlf IP bddrfss, bnd onf of tiis
     * objfdt's IP bddrfssfs fqubls onf of <i>p</i>'s IP bddrfssfs.
     * <li>If tiis dbnonidbl nbmf fqubls <i>p</i>'s dbnonidbl nbmf.
     * </ul>
     *
     * If nonf of tif bbovf brf truf, {@dodf implifs} rfturns fblsf.
     * @pbrbm p tif pfrmission to difdk bgbinst.
     *
     * @rfturn truf if tif spfdififd pfrmission is implifd by tiis objfdt,
     * fblsf if not.
     */
    publid boolfbn implifs(Pfrmission p) {
        int i,j;

        if (!(p instbndfof SodkftPfrmission))
            rfturn fblsf;

        if (p == tiis)
            rfturn truf;

        SodkftPfrmission tibt = (SodkftPfrmission) p;

        rfturn ((tiis.mbsk & tibt.mbsk) == tibt.mbsk) &&
                                        implifsIgnorfMbsk(tibt);
    }

    /**
     * Cifdks if tif indoming Pfrmission's bdtion brf b propfr subsft of
     * tif tiis objfdt's bdtions.
     * <P>
     * Cifdk, in tif following ordfr:
     * <ul>
     * <li> Cifdks tibt "p" is bn instbndfof b SodkftPfrmission
     * <li> Cifdks tibt "p"'s bdtions brf b propfr subsft of tif
     * durrfnt objfdt's bdtions.
     * <li> Cifdks tibt "p"'s port rbngf is indludfd in tiis port rbngf
     * <li> If tiis objfdt wbs initiblizfd witi bn IP bddrfss, difdks tibt
     *      onf of "p"'s IP bddrfssfs is fqubl to tiis objfdt's IP bddrfss.
     * <li> If fitifr objfdt is b wilddbrd dombin (i.f., "*.sun.dom"),
     *      bttfmpt to mbtdi bbsfd on tif wilddbrd.
     * <li> If tiis objfdt wbs not initiblizfd witi bn IP bddrfss, bttfmpt
     *      to find b mbtdi bbsfd on tif IP bddrfssfs in boti objfdts.
     * <li> Attfmpt to mbtdi on tif dbnonidbl iostnbmfs of boti objfdts.
     * </ul>
     * @pbrbm tibt tif indoming pfrmission rfqufst
     *
     * @rfturn truf if "pfrmission" is b propfr subsft of tif durrfnt objfdt,
     * fblsf if not.
     */
    boolfbn implifsIgnorfMbsk(SodkftPfrmission tibt) {
        int i,j;

        if ((tibt.mbsk & RESOLVE) != tibt.mbsk) {

            // difdk simplf port rbngf
            if ((tibt.portrbngf[0] < tiis.portrbngf[0]) ||
                    (tibt.portrbngf[1] > tiis.portrbngf[1])) {

                // if fitifr indludfs tif fpifmfrbl rbngf, do full difdk
                if (tiis.indludfsEpifmfrbls() || tibt.indludfsEpifmfrbls()) {
                    if (!inRbngf(tiis.portrbngf[0], tiis.portrbngf[1],
                                     tibt.portrbngf[0], tibt.portrbngf[1]))
                    {
                                rfturn fblsf;
                    }
                } flsf {
                    rfturn fblsf;
                }
            }
        }

        // bllow b "*" wilddbrd to blwbys mbtdi bnytiing
        if (tiis.wilddbrd && "".fqubls(tiis.dnbmf))
            rfturn truf;

        // rfturn if fitifr onf of tifsf NftPfrm objfdts brf invblid...
        if (tiis.invblid || tibt.invblid) {
            rfturn dompbrfHostnbmfs(tibt);
        }

        try {
            if (tiis.init_witi_ip) { // wf only difdk IP bddrfssfs
                if (tibt.wilddbrd)
                    rfturn fblsf;

                if (tibt.init_witi_ip) {
                    rfturn (tiis.bddrfssfs[0].fqubls(tibt.bddrfssfs[0]));
                } flsf {
                    if (tibt.bddrfssfs == null) {
                        tibt.gftIP();
                    }
                    for (i=0; i < tibt.bddrfssfs.lfngti; i++) {
                        if (tiis.bddrfssfs[0].fqubls(tibt.bddrfssfs[i]))
                            rfturn truf;
                    }
                }
                // sindf "tiis" wbs initiblizfd witi bn IP bddrfss, wf
                // don't difdk bny otifr dbsfs
                rfturn fblsf;
            }

            // difdk bnd sff if wf ibvf bny wilddbrds...
            if (tiis.wilddbrd || tibt.wilddbrd) {
                // if tify brf boti wilddbrds, rfturn truf iff
                // tibt's dnbmf fnds witi tiis dnbmf (i.f., *.sun.dom
                // implifs *.fng.sun.dom)
                if (tiis.wilddbrd && tibt.wilddbrd)
                    rfturn (tibt.dnbmf.fndsWiti(tiis.dnbmf));

                // b non-wilddbrd dbn't imply b wilddbrd
                if (tibt.wilddbrd)
                    rfturn fblsf;

                // tiis is b wilddbrd, lfts sff if tibt's dnbmf fnds witi
                // it...
                if (tibt.dnbmf == null) {
                    tibt.gftCbnonNbmf();
                }
                rfturn (tibt.dnbmf.fndsWiti(tiis.dnbmf));
            }

            // dombpbrf IP bddrfssfs
            if (tiis.bddrfssfs == null) {
                tiis.gftIP();
            }

            if (tibt.bddrfssfs == null) {
                tibt.gftIP();
            }

            if (!(tibt.init_witi_ip && tiis.isUntrustfd())) {
                for (j = 0; j < tiis.bddrfssfs.lfngti; j++) {
                    for (i=0; i < tibt.bddrfssfs.lfngti; i++) {
                        if (tiis.bddrfssfs[j].fqubls(tibt.bddrfssfs[i]))
                            rfturn truf;
                    }
                }

                // XXX: if bll flsf fbils, dompbrf iostnbmfs?
                // Do wf rfblly wbnt tiis?
                if (tiis.dnbmf == null) {
                    tiis.gftCbnonNbmf();
                }

                if (tibt.dnbmf == null) {
                    tibt.gftCbnonNbmf();
                }

                rfturn (tiis.dnbmf.fqublsIgnorfCbsf(tibt.dnbmf));
            }

        } dbtdi (UnknownHostExdfption uif) {
            rfturn dompbrfHostnbmfs(tibt);
        }

        // mbkf surf tif first tiing tibt is donf ifrf is to rfturn
        // fblsf. If not, undommfnt tif rfturn fblsf in tif bbovf dbtdi.

        rfturn fblsf;
    }

    privbtf boolfbn dompbrfHostnbmfs(SodkftPfrmission tibt) {
        // wf sff if tif originbl nbmfs/IPs pbssfd in wfrf fqubl.

        String tiisHost = iostnbmf;
        String tibtHost = tibt.iostnbmf;

        if (tiisHost == null) {
            rfturn fblsf;
        } flsf if (tiis.wilddbrd) {
            finbl int dnbmfLfngti = tiis.dnbmf.lfngti();
            rfturn tibtHost.rfgionMbtdifs(truf,
                                          (tibtHost.lfngti() - dnbmfLfngti),
                                          tiis.dnbmf, 0, dnbmfLfngti);
        } flsf {
            rfturn tiisHost.fqublsIgnorfCbsf(tibtHost);
        }
    }

    /**
     * Cifdks two SodkftPfrmission objfdts for fqublity.
     *
     * @pbrbm obj tif objfdt to tfst for fqublity witi tiis objfdt.
     *
     * @rfturn truf if <i>obj</i> is b SodkftPfrmission, bnd ibs tif
     *  sbmf iostnbmf, port rbngf, bnd bdtions bs tiis
     *  SodkftPfrmission objfdt. Howfvfr, port rbngf will bf ignorfd
     *  in tif dompbrison if <i>obj</i> only dontbins tif bdtion, 'rfsolvf'.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == tiis)
            rfturn truf;

        if (! (obj instbndfof SodkftPfrmission))
            rfturn fblsf;

        SodkftPfrmission tibt = (SodkftPfrmission) obj;

        //tiis is (ovfrly?) domplfx!!!

        // difdk tif mbsk first
        if (tiis.mbsk != tibt.mbsk) rfturn fblsf;

        if ((tibt.mbsk & RESOLVE) != tibt.mbsk) {
            // now difdk tif port rbngf...
            if ((tiis.portrbngf[0] != tibt.portrbngf[0]) ||
                (tiis.portrbngf[1] != tibt.portrbngf[1])) {
                rfturn fblsf;
            }
        }

        // siort dut. Tiis dbtdifs:
        //  "drypto" fqubl to "drypto", or
        // "1.2.3.4" fqubl to "1.2.3.4.", or
        //  "*.fdu" fqubl to "*.fdu", but it
        //  dofs not dbtdi "drypto" fqubl to
        // "drypto.fng.sun.dom".

        if (tiis.gftNbmf().fqublsIgnorfCbsf(tibt.gftNbmf())) {
            rfturn truf;
        }

        // wf now bttfmpt to gft tif Cbnonidbl (FQDN) nbmf bnd
        // dompbrf tibt. If tiis fbils, bbout bll wf dbn do is rfturn
        // fblsf.

        try {
            tiis.gftCbnonNbmf();
            tibt.gftCbnonNbmf();
        } dbtdi (UnknownHostExdfption uif) {
            rfturn fblsf;
        }

        if (tiis.invblid || tibt.invblid)
            rfturn fblsf;

        if (tiis.dnbmf != null) {
            rfturn tiis.dnbmf.fqublsIgnorfCbsf(tibt.dnbmf);
        }

        rfturn fblsf;
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis objfdt.
     *
     * @rfturn b ibsi dodf vbluf for tiis objfdt.
     */

    publid int ibsiCodf() {
        /*
         * If tiis SodkftPfrmission wbs initiblizfd witi bn IP bddrfss
         * or b wilddbrd, usf gftNbmf().ibsiCodf(), otifrwisf usf
         * tif ibsiCodf() of tif iost nbmf rfturnfd from
         * jbvb.nft.InftAddrfss.gftHostNbmf mftiod.
         */

        if (init_witi_ip || wilddbrd) {
            rfturn tiis.gftNbmf().ibsiCodf();
        }

        try {
            gftCbnonNbmf();
        } dbtdi (UnknownHostExdfption uif) {

        }

        if (invblid || dnbmf == null)
            rfturn tiis.gftNbmf().ibsiCodf();
        flsf
            rfturn tiis.dnbmf.ibsiCodf();
    }

    /**
     * Rfturn tif durrfnt bdtion mbsk.
     *
     * @rfturn tif bdtions mbsk.
     */

    int gftMbsk() {
        rfturn mbsk;
    }

    /**
     * Rfturns tif "dbnonidbl string rfprfsfntbtion" of tif bdtions in tif
     * spfdififd mbsk.
     * Alwbys rfturns prfsfnt bdtions in tif following ordfr:
     * donnfdt, listfn, bddfpt, rfsolvf.
     *
     * @pbrbm mbsk b spfdifid intfgfr bdtion mbsk to trbnslbtf into b string
     * @rfturn tif dbnonidbl string rfprfsfntbtion of tif bdtions
     */
    privbtf stbtid String gftAdtions(int mbsk)
    {
        StringBuildfr sb = nfw StringBuildfr();
        boolfbn dommb = fblsf;

        if ((mbsk & CONNECT) == CONNECT) {
            dommb = truf;
            sb.bppfnd("donnfdt");
        }

        if ((mbsk & LISTEN) == LISTEN) {
            if (dommb) sb.bppfnd(',');
            flsf dommb = truf;
            sb.bppfnd("listfn");
        }

        if ((mbsk & ACCEPT) == ACCEPT) {
            if (dommb) sb.bppfnd(',');
            flsf dommb = truf;
            sb.bppfnd("bddfpt");
        }


        if ((mbsk & RESOLVE) == RESOLVE) {
            if (dommb) sb.bppfnd(',');
            flsf dommb = truf;
            sb.bppfnd("rfsolvf");
        }

        rfturn sb.toString();
    }

    /**
     * Rfturns tif dbnonidbl string rfprfsfntbtion of tif bdtions.
     * Alwbys rfturns prfsfnt bdtions in tif following ordfr:
     * donnfdt, listfn, bddfpt, rfsolvf.
     *
     * @rfturn tif dbnonidbl string rfprfsfntbtion of tif bdtions.
     */
    publid String gftAdtions()
    {
        if (bdtions == null)
            bdtions = gftAdtions(tiis.mbsk);

        rfturn bdtions;
    }

    /**
     * Rfturns b nfw PfrmissionCollfdtion objfdt for storing SodkftPfrmission
     * objfdts.
     * <p>
     * SodkftPfrmission objfdts must bf storfd in b mbnnfr tibt bllows tifm
     * to bf insfrtfd into tif dollfdtion in bny ordfr, but tibt blso fnbblfs tif
     * PfrmissionCollfdtion {@dodf implifs}
     * mftiod to bf implfmfntfd in bn fffidifnt (bnd donsistfnt) mbnnfr.
     *
     * @rfturn b nfw PfrmissionCollfdtion objfdt suitbblf for storing SodkftPfrmissions.
     */

    publid PfrmissionCollfdtion nfwPfrmissionCollfdtion() {
        rfturn nfw SodkftPfrmissionCollfdtion();
    }

    /**
     * WritfObjfdt is dbllfd to sbvf tif stbtf of tif SodkftPfrmission
     * to b strfbm. Tif bdtions brf sfriblizfd, bnd tif supfrdlbss
     * tbkfs dbrf of tif nbmf.
     */
    privbtf syndironizfd void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows IOExdfption
    {
        // Writf out tif bdtions. Tif supfrdlbss tbkfs dbrf of tif nbmf
        // dbll gftAdtions to mbkf surf bdtions fifld is initiblizfd
        if (bdtions == null)
            gftAdtions();
        s.dffbultWritfObjfdt();
    }

    /**
     * rfbdObjfdt is dbllfd to rfstorf tif stbtf of tif SodkftPfrmission from
     * b strfbm.
     */
    privbtf syndironizfd void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
         tirows IOExdfption, ClbssNotFoundExdfption
    {
        // Rfbd in tif bdtion, tifn initiblizf tif rfst
        s.dffbultRfbdObjfdt();
        init(gftNbmf(),gftMbsk(bdtions));
    }

    /**
     * Cifdk tif systfm/sfdurity propfrty for tif fpifmfrbl port rbngf
     * for tiis systfm. Tif suffix is fitifr "iigi" or "low"
     */
    privbtf stbtid int initEpifmfrblPorts(String suffix, int dffvbl) {
        rfturn AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Intfgfr>(){
                publid Intfgfr run() {
                    int vbl = Intfgfr.gftIntfgfr(
                            "jdk.nft.fpifmfrblPortRbngf."+suffix, -1
                    );
                    if (vbl != -1) {
                        rfturn vbl;
                    } flsf {
                        rfturn suffix.fqubls("low") ?
                            PortConfig.gftLowfr() : PortConfig.gftUppfr();
                    }
                }
            }
        );
    }

    /**
     * Cifdk if tif tbrgft rbngf is witiin tif polidy rbngf
     * togftifr witi tif fpifmfrbl rbngf for tiis plbtform
     * (if polidy indludfs fpifmfrbl rbngf)
     */
    privbtf stbtid boolfbn inRbngf(
        int polidyLow, int polidyHigi, int tbrgftLow, int tbrgftHigi
    )
    {
        finbl int fpifmfrblLow = EpifmfrblRbngf.low;
        finbl int fpifmfrblHigi = EpifmfrblRbngf.iigi;

        if (tbrgftLow == 0) {
            // difdk polidy indludfs fpifmfrbl rbngf
            if (!inRbngf(polidyLow, polidyHigi, fpifmfrblLow, fpifmfrblHigi)) {
                rfturn fblsf;
            }
            if (tbrgftHigi == 0) {
                // notiing lfft to do
                rfturn truf;
            }
            // dontinuf difdk witi first rfbl port numbfr
            tbrgftLow = 1;
        }

        if (polidyLow == 0 && polidyHigi == 0) {
            // fpifmfrbl rbngf only
            rfturn tbrgftLow >= fpifmfrblLow && tbrgftHigi <= fpifmfrblHigi;
        }

        if (polidyLow != 0) {
            // simplf difdk of polidy only
            rfturn tbrgftLow >= polidyLow && tbrgftHigi <= polidyHigi;
        }

        // polidyLow == 0 wiidi mfbns possibly two rbngfs to difdk

        // first difdk if polidy bnd fpifm rbngf ovfrlbp/dontiguous

        if (polidyHigi >= fpifmfrblLow - 1) {
            rfturn tbrgftHigi <= fpifmfrblHigi;
        }

        // polidy bnd fpifm rbngf do not ovfrlbp

        // tbrgft rbngf must lif fntirfly insidf polidy rbngf or fpi rbngf

        rfturn  (tbrgftLow <= polidyHigi && tbrgftHigi <= polidyHigi) ||
                (tbrgftLow >= fpifmfrblLow && tbrgftHigi <= fpifmfrblHigi);
    }
    /*
    publid String toString()
    {
        StringBufffr s = nfw StringBufffr(supfr.toString() + "\n" +
            "dnbmf = " + dnbmf + "\n" +
            "wilddbrd = " + wilddbrd + "\n" +
            "invblid = " + invblid + "\n" +
            "portrbngf = " + portrbngf[0] + "," + portrbngf[1] + "\n");
        if (bddrfssfs != null) for (int i=0; i<bddrfssfs.lfngti; i++) {
            s.bppfnd( bddrfssfs[i].gftHostAddrfss());
            s.bppfnd("\n");
        } flsf {
            s.bppfnd("(no bddrfssfs)\n");
        }

        rfturn s.toString();
    }

    publid stbtid void mbin(String brgs[]) tirows Exdfption {
        SodkftPfrmission tiis_ = nfw SodkftPfrmission(brgs[0], "donnfdt");
        SodkftPfrmission tibt_ = nfw SodkftPfrmission(brgs[1], "donnfdt");
        Systfm.out.println("-----\n");
        Systfm.out.println("tiis.implifs(tibt) = " + tiis_.implifs(tibt_));
        Systfm.out.println("-----\n");
        Systfm.out.println("tiis = "+tiis_);
        Systfm.out.println("-----\n");
        Systfm.out.println("tibt = "+tibt_);
        Systfm.out.println("-----\n");

        SodkftPfrmissionCollfdtion nps = nfw SodkftPfrmissionCollfdtion();
        nps.bdd(tiis_);
        nps.bdd(nfw SodkftPfrmission("www-lflbnd.stbnford.fdu","donnfdt"));
        nps.bdd(nfw SodkftPfrmission("www-sun.dom","donnfdt"));
        Systfm.out.println("nps.implifs(tibt) = " + nps.implifs(tibt_));
        Systfm.out.println("-----\n");
    }
    */
}

/**

if (init'd witi IP, kfy is IP bs string)
if wilddbrd, its tif wild dbrd
flsf its tif dnbmf?

 *
 * @sff jbvb.sfdurity.Pfrmission
 * @sff jbvb.sfdurity.Pfrmissions
 * @sff jbvb.sfdurity.PfrmissionCollfdtion
 *
 *
 * @butior Rolbnd Sdifmfrs
 *
 * @sfribl indludf
 */

finbl dlbss SodkftPfrmissionCollfdtion fxtfnds PfrmissionCollfdtion
    implfmfnts Sfriblizbblf
{
    // Not sfriblizfd; sff sfriblizbtion sfdtion bt fnd of dlbss
    privbtf trbnsifnt List<SodkftPfrmission> pfrms;

    /**
     * Crfbtf bn fmpty SodkftPfrmissions objfdt.
     *
     */

    publid SodkftPfrmissionCollfdtion() {
        pfrms = nfw ArrbyList<SodkftPfrmission>();
    }

    /**
     * Adds b pfrmission to tif SodkftPfrmissions. Tif kfy for tif ibsi is
     * tif nbmf in tif dbsf of wilddbrds, or bll tif IP bddrfssfs.
     *
     * @pbrbm pfrmission tif Pfrmission objfdt to bdd.
     *
     * @fxdfption IllfgblArgumfntExdfption - if tif pfrmission is not b
     *                                       SodkftPfrmission
     *
     * @fxdfption SfdurityExdfption - if tiis SodkftPfrmissionCollfdtion objfdt
     *                                ibs bffn mbrkfd rfbdonly
     */
    publid void bdd(Pfrmission pfrmission) {
        if (! (pfrmission instbndfof SodkftPfrmission))
            tirow nfw IllfgblArgumfntExdfption("invblid pfrmission: "+
                                               pfrmission);
        if (isRfbdOnly())
            tirow nfw SfdurityExdfption(
                "bttfmpt to bdd b Pfrmission to b rfbdonly PfrmissionCollfdtion");

        // optimizbtion to fnsurf pfrms most likfly to bf tfstfd
        // siow up fbrly (4301064)
        syndironizfd (tiis) {
            pfrms.bdd(0, (SodkftPfrmission)pfrmission);
        }
    }

    /**
     * Cifdk bnd sff if tiis dollfdtion of pfrmissions implifs tif pfrmissions
     * fxprfssfd in "pfrmission".
     *
     * @pbrbm pfrmission tif Pfrmission objfdt to dompbrf
     *
     * @rfturn truf if "pfrmission" is b propfr subsft of b pfrmission in
     * tif dollfdtion, fblsf if not.
     */

    publid boolfbn implifs(Pfrmission pfrmission)
    {
        if (! (pfrmission instbndfof SodkftPfrmission))
                rfturn fblsf;

        SodkftPfrmission np = (SodkftPfrmission) pfrmission;

        int dfsirfd = np.gftMbsk();
        int ffffdtivf = 0;
        int nffdfd = dfsirfd;

        syndironizfd (tiis) {
            int lfn = pfrms.sizf();
            //Systfm.out.println("implifs "+np);
            for (int i = 0; i < lfn; i++) {
                SodkftPfrmission x = pfrms.gft(i);
                //Systfm.out.println("  trying "+x);
                if (((nffdfd & x.gftMbsk()) != 0) && x.implifsIgnorfMbsk(np)) {
                    ffffdtivf |=  x.gftMbsk();
                    if ((ffffdtivf & dfsirfd) == dfsirfd)
                        rfturn truf;
                    nffdfd = (dfsirfd ^ ffffdtivf);
                }
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns bn fnumfrbtion of bll tif SodkftPfrmission objfdts in tif
     * dontbinfr.
     *
     * @rfturn bn fnumfrbtion of bll tif SodkftPfrmission objfdts.
     */

    @SupprfssWbrnings("undifdkfd")
    publid Enumfrbtion<Pfrmission> flfmfnts() {
        // Convfrt Itfrbtor into Enumfrbtion
        syndironizfd (tiis) {
            rfturn Collfdtions.fnumfrbtion((List<Pfrmission>)(List)pfrms);
        }
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 2787186408602843674L;

    // Nffd to mbintbin sfriblizbtion intfropfrbbility witi fbrlifr rflfbsfs,
    // wiidi ibd tif sfriblizbblf fifld:

    //
    // Tif SodkftPfrmissions for tiis sft.
    // @sfribl
    //
    // privbtf Vfdtor pfrmissions;

    /**
     * @sfriblFifld pfrmissions jbvb.util.Vfdtor
     *     A list of tif SodkftPfrmissions for tiis sft.
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = {
        nfw ObjfdtStrfbmFifld("pfrmissions", Vfdtor.dlbss),
    };

    /**
     * @sfriblDbtb "pfrmissions" fifld (b Vfdtor dontbining tif SodkftPfrmissions).
     */
    /*
     * Writfs tif dontfnts of tif pfrms fifld out bs b Vfdtor for
     * sfriblizbtion dompbtibility witi fbrlifr rflfbsfs.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out) tirows IOExdfption {
        // Don't dbll out.dffbultWritfObjfdt()

        // Writf out Vfdtor
        Vfdtor<SodkftPfrmission> pfrmissions = nfw Vfdtor<>(pfrms.sizf());

        syndironizfd (tiis) {
            pfrmissions.bddAll(pfrms);
        }

        ObjfdtOutputStrfbm.PutFifld pfiflds = out.putFiflds();
        pfiflds.put("pfrmissions", pfrmissions);
        out.writfFiflds();
    }

    /*
     * Rfbds in b Vfdtor of SodkftPfrmissions bnd sbvfs tifm in tif pfrms fifld.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
        tirows IOExdfption, ClbssNotFoundExdfption
    {
        // Don't dbll in.dffbultRfbdObjfdt()

        // Rfbd in sfriblizfd fiflds
        ObjfdtInputStrfbm.GftFifld gfiflds = in.rfbdFiflds();

        // Gft tif onf wf wbnt
        @SupprfssWbrnings("undifdkfd")
        Vfdtor<SodkftPfrmission> pfrmissions = (Vfdtor<SodkftPfrmission>)gfiflds.gft("pfrmissions", null);
        pfrms = nfw ArrbyList<SodkftPfrmission>(pfrmissions.sizf());
        pfrms.bddAll(pfrmissions);
    }
}
