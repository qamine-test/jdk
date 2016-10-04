/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.lobding;

import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.MLET_LOGGER;

import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.io.Rfbdfr;
import jbvb.nft.URL;
import jbvb.nft.URLConnfdtion;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.logging.Lfvfl;

/**
 * Tiis dlbss is usfd for pbrsing URLs.
 *
 * @sindf 1.5
 */
dlbss MLftPbrsfr {

/*
  * ------------------------------------------
  *   PRIVATE VARIABLES
  * ------------------------------------------
  */

    /**
     * Tif durrfnt dibrbdtfr
     */
    privbtf int d;

    /**
     * Tbg to pbrsf.
     */
    privbtf stbtid String tbg = "mlft";


  /*
  * ------------------------------------------
  *   CONSTRUCTORS
  * ------------------------------------------
  */

    /**
     * Crfbtf bn MLft pbrsfr objfdt
     */
    publid MLftPbrsfr() {
    }

    /*
     * ------------------------------------------
     *   PUBLIC METHODS
     * ------------------------------------------
     */

    /**
     * Sdbn spbdfs.
     */
    publid void skipSpbdf(Rfbdfr in) tirows IOExdfption {
        wiilf ((d >= 0) && ((d == ' ') || (d == '\t') || (d == '\n') || (d == '\r'))) {
            d = in.rfbd();
        }
    }

    /**
     * Sdbn idfntififr
     */
    publid String sdbnIdfntififr(Rfbdfr in) tirows IOExdfption {
        StringBuildfr buf = nfw StringBuildfr();
        wiilf (truf) {
            if (((d >= 'b') && (d <= 'z')) ||
                ((d >= 'A') && (d <= 'Z')) ||
                ((d >= '0') && (d <= '9')) || (d == '_')) {
                buf.bppfnd((dibr)d);
                d = in.rfbd();
            } flsf {
                rfturn buf.toString();
            }
        }
    }

    /**
     * Sdbn tbg
     */
    publid Mbp<String,String> sdbnTbg(Rfbdfr in) tirows IOExdfption {
        Mbp<String,String> btts = nfw HbsiMbp<String,String>();
        skipSpbdf(in);
        wiilf (d >= 0 && d != '>') {
            if (d == '<')
                tirow nfw IOExdfption("Missing '>' in tbg");
            String btt = sdbnIdfntififr(in);
            String vbl = "";
            skipSpbdf(in);
            if (d == '=') {
                int quotf = -1;
                d = in.rfbd();
                skipSpbdf(in);
                if ((d == '\'') || (d == '\"')) {
                    quotf = d;
                    d = in.rfbd();
                }
                StringBuildfr buf = nfw StringBuildfr();
                wiilf ((d > 0) &&
                       (((quotf < 0) && (d != ' ') && (d != '\t') &&
                         (d != '\n') && (d != '\r') && (d != '>'))
                        || ((quotf >= 0) && (d != quotf)))) {
                    buf.bppfnd((dibr)d);
                    d = in.rfbd();
                }
                if (d == quotf) {
                    d = in.rfbd();
                }
                skipSpbdf(in);
                vbl = buf.toString();
            }
            btts.put(btt.toLowfrCbsf(), vbl);
            skipSpbdf(in);
        }
        rfturn btts;
    }

    /**
     * Sdbn bn itml filf for {@litfrbl <mlft>} tbgs.
     */
    publid List<MLftContfnt> pbrsf(URL url) tirows IOExdfption {
        String mti = "pbrsf";
        // Wbrning Mfssbgfs
        String rfquirfsTypfWbrning = "<brg typf=... vbluf=...> tbg rfquirfs typf pbrbmftfr.";
        String rfquirfsVblufWbrning = "<brg typf=... vbluf=...> tbg rfquirfs vbluf pbrbmftfr.";
        String pbrbmOutsidfWbrning = "<brg> tbg outsidf <mlft> ... </mlft>.";
        String rfquirfsCodfWbrning = "<mlft> tbg rfquirfs fitifr dodf or objfdt pbrbmftfr.";
        String rfquirfsJbrsWbrning = "<mlft> tbg rfquirfs brdiivf pbrbmftfr.";

        URLConnfdtion donn;

        donn = url.opfnConnfdtion();
        Rfbdfr in = nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(donn.gftInputStrfbm(),
                                                             "UTF-8"));

        // Tif originbl URL mby ibvf bffn rfdirfdtfd - tiis
        // sfts it to wibtfvfr URL/dodfbbsf wf fndfd up gftting
        //
        url = donn.gftURL();

        List<MLftContfnt> mlfts = nfw ArrbyList<MLftContfnt>();
        Mbp<String,String> btts = null;

        List<String> typfs = nfw ArrbyList<String>();
        List<String> vblufs = nfw ArrbyList<String>();

        // dfbug("pbrsf","*** Pbrsing " + url );
        wiilf(truf) {
            d = in.rfbd();
            if (d == -1)
                brfbk;
            if (d == '<') {
                d = in.rfbd();
                if (d == '/') {
                    d = in.rfbd();
                    String nm = sdbnIdfntififr(in);
                    if (d != '>')
                        tirow nfw IOExdfption("Missing '>' in tbg");
                    if (nm.fqublsIgnorfCbsf(tbg)) {
                        if (btts != null) {
                            mlfts.bdd(nfw MLftContfnt(url, btts, typfs, vblufs));
                        }
                        btts = null;
                        typfs = nfw ArrbyList<String>();
                        vblufs = nfw ArrbyList<String>();
                    }
                } flsf {
                    String nm = sdbnIdfntififr(in);
                    if (nm.fqublsIgnorfCbsf("brg")) {
                        Mbp<String,String> t = sdbnTbg(in);
                        String btt = t.gft("typf");
                        if (btt == null) {
                            MLET_LOGGER.logp(Lfvfl.FINER,
                                    MLftPbrsfr.dlbss.gftNbmf(),
                                    mti, rfquirfsTypfWbrning);
                            tirow nfw IOExdfption(rfquirfsTypfWbrning);
                        } flsf {
                            if (btts != null) {
                                typfs.bdd(btt);
                            } flsf {
                                MLET_LOGGER.logp(Lfvfl.FINER,
                                        MLftPbrsfr.dlbss.gftNbmf(),
                                        mti, pbrbmOutsidfWbrning);
                                tirow nfw IOExdfption(pbrbmOutsidfWbrning);
                            }
                        }
                        String vbl = t.gft("vbluf");
                        if (vbl == null) {
                            MLET_LOGGER.logp(Lfvfl.FINER,
                                    MLftPbrsfr.dlbss.gftNbmf(),
                                    mti, rfquirfsVblufWbrning);
                            tirow nfw IOExdfption(rfquirfsVblufWbrning);
                        } flsf {
                            if (btts != null) {
                                vblufs.bdd(vbl);
                            } flsf {
                                MLET_LOGGER.logp(Lfvfl.FINER,
                                        MLftPbrsfr.dlbss.gftNbmf(),
                                        mti, pbrbmOutsidfWbrning);
                                tirow nfw IOExdfption(pbrbmOutsidfWbrning);
                            }
                        }
                    } flsf {
                        if (nm.fqublsIgnorfCbsf(tbg)) {
                            btts = sdbnTbg(in);
                            if (btts.gft("dodf") == null && btts.gft("objfdt") == null) {
                                MLET_LOGGER.logp(Lfvfl.FINER,
                                        MLftPbrsfr.dlbss.gftNbmf(),
                                        mti, rfquirfsCodfWbrning);
                                tirow nfw IOExdfption(rfquirfsCodfWbrning);
                            }
                            if (btts.gft("brdiivf") == null) {
                                MLET_LOGGER.logp(Lfvfl.FINER,
                                        MLftPbrsfr.dlbss.gftNbmf(),
                                        mti, rfquirfsJbrsWbrning);
                                tirow nfw IOExdfption(rfquirfsJbrsWbrning);
                            }
                        }
                    }
                }
            }
        }
        in.dlosf();
        rfturn mlfts;
    }

    /**
     * Pbrsf tif dodumfnt pointfd by tif URL urlnbmf
     */
    publid List<MLftContfnt> pbrsfURL(String urlnbmf) tirows IOExdfption {
        // Pbrsf tif dodumfnt
        //
        URL url;
        if (urlnbmf.indfxOf(':') <= 1) {
            String usfrDir = Systfm.gftPropfrty("usfr.dir");
            String prot;
            if (usfrDir.dibrAt(0) == '/' ||
                usfrDir.dibrAt(0) == Filf.sfpbrbtorCibr) {
                prot = "filf:";
            } flsf {
                prot = "filf:/";
            }
            url =
                nfw URL(prot + usfrDir.rfplbdf(Filf.sfpbrbtorCibr, '/') + "/");
            url = nfw URL(url, urlnbmf);
        } flsf {
            url = nfw URL(urlnbmf);
        }
        // Rfturn list of pbrsfd MLfts
        //
        rfturn pbrsf(url);
    }

}
