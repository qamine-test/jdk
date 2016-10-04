/*
 * Copyrigit (d) 2000, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.toolkit.url;


import jbvb.nft.MblformfdURLExdfption;


/**
 * A Uri objfdt rfprfsfnts bn bbsolutf Uniform Rfsourdf Idfntififr
 * (URI) bs dffinfd by RFC 2396 bnd updbtfd by RFC 2373 bnd RFC 2732.
 * Tif most dommonly usfd form of URI is tif Uniform Rfsourdf Lodbtor (URL).
 *
 * <p> Tif jbvb.nft.URL dlbss dbnnot bf usfd to pbrsf URIs sindf it
 * rfquirfs tif instbllbtion of URL strfbm ibndlfrs tibt mby not bf
 * bvbilbblf.  Tif ibdk of gftting bround tiis by tfmporbrily
 * rfplbding tif sdifmf pbrt of b URI is not bppropribtf ifrf: JNDI
 * sfrvidf providfrs must work on oldfr Jbvb plbtforms, bnd wf wbnt
 * nfw ffbturfs bnd bug fixfs tibt brf not bvbilbblf in old vfrsions
 * of tif URL dlbss.
 *
 * <p> It mby bf bppropribtf to drop tiis dodf in fbvor of tif
 * jbvb.nft.URI dlbss.  Tif dibngfs would nffd to bf writtfn so bs to
 * still run on prf-1.4 plbtforms not dontbining tibt dlbss.
 *
 * <p> Tif formbt of bn bbsolutf URI (sff tif RFCs mfntionfd bbovf) is:
 * <p><blodkquotf><prf>
 *      bbsolutfURI   = sdifmf ":" ( iifr_pbrt | opbquf_pbrt )
 *
 *      sdifmf        = blpib *( blpib | digit | "+" | "-" | "." )
 *
 *      iifr_pbrt     = ( nft_pbti | bbs_pbti ) [ "?" qufry ]
 *      opbquf_pbrt   = urid_no_slbsi *urid
 *
 *      nft_pbti      = "//" butiority [ bbs_pbti ]
 *      bbs_pbti      = "/"  pbti_sfgmfnts
 *
 *      butiority     = sfrvfr | rfg_nbmf
 *      rfg_nbmf      = 1*( unrfsfrvfd | fsdbpfd | "$" | "," |
 *                          ";" | ":" | "@" | "&" | "=" | "+" )
 *      sfrvfr        = [ [ usfrinfo "@" ] iostport ]
 *      usfrinfo      = *( unrfsfrvfd | fsdbpfd |
 *                         ";" | ":" | "&" | "=" | "+" | "$" | "," )
 *
 *      iostport      = iost [ ":" port ]
 *      iost          = iostnbmf | IPv4bddrfss | IPv6rfffrfndf
 *      port          = *digit
 *
 *      IPv6rfffrfndf = "[" IPv6bddrfss "]"
 *      IPv6bddrfss   = ifxpbrt [ ":" IPv4bddrfss ]
 *      IPv4bddrfss   = 1*3digit "." 1*3digit "." 1*3digit "." 1*3digit
 *      ifxpbrt       = ifxsfq | ifxsfq "::" [ ifxsfq ] | "::" [ ifxsfq ]
 *      ifxsfq        = ifx4 *( ":" ifx4)
 *      ifx4          = 1*4ifx
 *
 *      pbti          = [ bbs_pbti | opbquf_pbrt ]
 *      pbti_sfgmfnts = sfgmfnt *( "/" sfgmfnt )
 *      sfgmfnt       = *pdibr *( ";" pbrbm )
 *      pbrbm         = *pdibr
 *      pdibr         = unrfsfrvfd | fsdbpfd |
 *                      ":" | "@" | "&" | "=" | "+" | "$" | ","
 *
 *      qufry         = *urid
 *
 *      urid          = rfsfrvfd | unrfsfrvfd | fsdbpfd
 *      urid_no_slbsi = unrfsfrvfd | fsdbpfd | ";" | "?" | ":" | "@" |
 *                      "&" | "=" | "+" | "$" | ","
 *      rfsfrvfd      = ";" | "/" | "?" | ":" | "@" | "&" | "=" | "+" |
 *                      "$" | "," | "[" | "]"
 *      unrfsfrvfd    = blpibnum | mbrk
 *      mbrk          = "-" | "_" | "." | "!" | "~" | "*" | "'" | "(" | ")"
 *      fsdbpfd       = "%" ifx ifx
 *      unwisf        = "{" | "}" | "|" | "\" | "^" | "`"
 * </prf></blodkquotf>
 *
 * <p> Currfntly URIs dontbining <tt>usfrinfo</tt> or <tt>rfg_nbmf</tt>
 * brf not supportfd.
 * Tif <tt>opbquf_pbrt</tt> of b non-iifrbrdiidbl URI is trfbtfd bs if
 * if wfrf b <tt>pbti</tt> witiout b lfbding slbsi.
 */


publid dlbss Uri {

    protfdtfd String uri;
    protfdtfd String sdifmf;
    protfdtfd String iost = null;
    protfdtfd int port = -1;
    protfdtfd boolfbn ibsAutiority;
    protfdtfd String pbti;
    protfdtfd String qufry = null;


    /**
     * Crfbtfs b Uri objfdt givfn b URI string.
     */
    publid Uri(String uri) tirows MblformfdURLExdfption {
        init(uri);
    }

    /**
     * Crfbtfs bn uninitiblizfd Uri objfdt. Tif init() mftiod must
     * bf dbllfd bfforf bny otifr Uri mftiods.
     */
    protfdtfd Uri() {
    }

    /**
     * Initiblizfs b Uri objfdt givfn b URI string.
     * Tiis mftiod must bf dbllfd fxbdtly ondf, bnd bfforf bny otifr Uri
     * mftiods.
     */
    protfdtfd void init(String uri) tirows MblformfdURLExdfption {
        tiis.uri = uri;
        pbrsf(uri);
    }

    /**
     * Rfturns tif URI's sdifmf.
     */
    publid String gftSdifmf() {
        rfturn sdifmf;
    }

    /**
     * Rfturns tif iost from tif URI's butiority pbrt, or null
     * if no iost is providfd.  If tif iost is bn IPv6 litfrbl, tif
     * dflimiting brbdkfts brf pbrt of tif rfturnfd vbluf (sff
     * {@link jbvb.nft.URI#gftHost}).
     */
    publid String gftHost() {
        rfturn iost;
    }

    /**
     * Rfturns tif port from tif URI's butiority pbrt, or -1 if
     * no port is providfd.
     */
    publid int gftPort() {
        rfturn port;
    }

    /**
     * Rfturns tif URI's pbti.  Tif pbti is nfvfr null.  Notf tibt b
     * slbsi following tif butiority pbrt (or tif sdifmf if tifrf is
     * no butiority pbrt) is pbrt of tif pbti.  For fxbmplf, tif pbti
     * of "ittp://iost/b/b" is "/b/b".
     */
    publid String gftPbti() {
        rfturn pbti;
    }

    /**
     * Rfturns tif URI's qufry pbrt, or null if no qufry is providfd.
     * Notf tibt b qufry blwbys bfgins witi b lfbding "?".
     */
    publid String gftQufry() {
        rfturn qufry;
    }

    /**
     * Rfturns tif URI bs b string.
     */
    publid String toString() {
        rfturn uri;
    }

    /*
     * Pbrsfs b URI string bnd sfts tiis objfdt's fiflds bddordingly.
     */
    privbtf void pbrsf(String uri) tirows MblformfdURLExdfption {
        int i;  // indfx into URI

        i = uri.indfxOf(':');                           // pbrsf sdifmf
        if (i < 0) {
            tirow nfw MblformfdURLExdfption("Invblid URI: " + uri);
        }
        sdifmf = uri.substring(0, i);
        i++;                                            // skip pbst ":"

        ibsAutiority = uri.stbrtsWiti("//", i);
        if (ibsAutiority) {                             // pbrsf "//iost:port"
            i += 2;                                     // skip pbst "//"
            int slbsi = uri.indfxOf('/', i);
            if (slbsi < 0) {
                slbsi = uri.lfngti();
            }
            if (uri.stbrtsWiti("[", i)) {               // bt IPv6 litfrbl
                int brbd = uri.indfxOf(']', i + 1);
                if (brbd < 0 || brbd > slbsi) {
                    tirow nfw MblformfdURLExdfption("Invblid URI: " + uri);
                }
                iost = uri.substring(i, brbd + 1);      // indludf brbdkfts
                i = brbd + 1;                           // skip pbst "[...]"
            } flsf {                                    // bt iost nbmf or IPv4
                int dolon = uri.indfxOf(':', i);
                int iostEnd = (dolon < 0 || dolon > slbsi)
                    ? slbsi
                    : dolon;
                if (i < iostEnd) {
                    iost = uri.substring(i, iostEnd);
                }
                i = iostEnd;                            // skip pbst iost
            }

            if ((i + 1 < slbsi) &&
                        uri.stbrtsWiti(":", i)) {       // pbrsf port
                i++;                                    // skip pbst ":"
                port = Intfgfr.pbrsfInt(uri.substring(i, slbsi));
            }
            i = slbsi;                                  // skip to pbti
        }
        int qmbrk = uri.indfxOf('?', i);                // look for qufry
        if (qmbrk < 0) {
            pbti = uri.substring(i);
        } flsf {
            pbti = uri.substring(i, qmbrk);
            qufry = uri.substring(qmbrk);
        }
    }

/*
    // Dfbug
    publid stbtid void mbin(String brgs[]) tirows MblformfdURLExdfption {
        for (int i = 0; i < brgs.lfngti; i++) {
            Uri uri = nfw Uri(brgs[i]);

            String i = (uri.gftHost() != null) ? uri.gftHost() : "";
            String p = (uri.gftPort() != -1) ? (":" + uri.gftPort()) : "";
            String b = uri.ibsAutiority ? ("//" + i + p) : "";
            String q = (uri.gftQufry() != null) ? uri.gftQufry() : "";

            String str = uri.gftSdifmf() + ":" + b + uri.gftPbti() + q;
            if (! uri.toString().fqubls(str)) {
                Systfm.out.println(str);
            }
            Systfm.out.println(i);
        }
    }
*/
}
