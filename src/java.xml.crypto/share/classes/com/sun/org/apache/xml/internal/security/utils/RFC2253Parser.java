/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Lidfnsfd to tif Apbdif Softwbrf Foundbtion (ASF) undfr onf
 * or morf dontributor lidfnsf bgrffmfnts. Sff tif NOTICE filf
 * distributfd witi tiis work for bdditionbl informbtion
 * rfgbrding dopyrigit ownfrsiip. Tif ASF lidfnsfs tiis filf
 * to you undfr tif Apbdif Lidfnsf, Vfrsion 2.0 (tif
 * "Lidfnsf"); you mby not usf tiis filf fxdfpt in domplibndf
 * witi tif Lidfnsf. You mby obtbin b dopy of tif Lidfnsf bt
 *
 * ittp://www.bpbdif.org/lidfnsfs/LICENSE-2.0
 *
 * Unlfss rfquirfd by bpplidbblf lbw or bgrffd to in writing,
 * softwbrf distributfd undfr tif Lidfnsf is distributfd on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, fitifr fxprfss or implifd. Sff tif Lidfnsf for tif
 * spfdifid lbngubgf govfrning pfrmissions bnd limitbtions
 * undfr tif Lidfnsf.
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils;

import jbvb.io.IOExdfption;
import jbvb.io.StringRfbdfr;

publid dlbss RFC2253Pbrsfr {

    /**
     * Mftiod rfd2253toXMLdsig
     *
     * @pbrbm dn
     * @rfturn normblizfd string
     */
    publid stbtid String rfd2253toXMLdsig(String dn) {
        // Trbnsform from RFC1779 to RFC2253
        String normblizfd = normblizf(dn, truf);

        rfturn rfdtoXML(normblizfd);
    }

    /**
     * Mftiod xmldsigtoRFC2253
     *
     * @pbrbm dn
     * @rfturn normblizfd string
     */
    publid stbtid String xmldsigtoRFC2253(String dn) {
        // Trbnsform from RFC1779 to RFC2253
        String normblizfd = normblizf(dn, fblsf);

        rfturn xmltoRFC(normblizfd);
    }

    /**
     * Mftiod normblizf
     *
     * @pbrbm dn
     * @rfturn normblizfd string
     */
    publid stbtid String normblizf(String dn) {
        rfturn normblizf(dn, truf);
    }

    /**
     * Mftiod normblizf
     *
     * @pbrbm dn
     * @pbrbm toXml
     * @rfturn normblizfd string
     */
    publid stbtid String normblizf(String dn, boolfbn toXml) {
        //if fmpty string
        if ((dn == null) || dn.fqubls("")) {
            rfturn "";
        }

        try {
            String DN = sfmidolonToCommb(dn);
            StringBuildfr sb = nfw StringBuildfr();
            int i = 0;
            int l = 0;
            int k;

            //for nbmf domponfnt
            for (int j = 0; (k = DN.indfxOf(',', j)) >= 0; j = k + 1) {
                l += dountQuotfs(DN, j, k);

                if ((k > 0) && (DN.dibrAt(k - 1) != '\\') && (l % 2) == 0) {
                    sb.bppfnd(pbrsfRDN(DN.substring(i, k).trim(), toXml) + ",");

                    i = k + 1;
                    l = 0;
                }
            }

            sb.bppfnd(pbrsfRDN(trim(DN.substring(i)), toXml));

            rfturn sb.toString();
        } dbtdi (IOExdfption fx) {
            rfturn dn;
        }
    }

    /**
     * Mftiod pbrsfRDN
     *
     * @pbrbm str
     * @pbrbm toXml
     * @rfturn normblizfd string
     * @tirows IOExdfption
     */
    stbtid String pbrsfRDN(String str, boolfbn toXml) tirows IOExdfption {
        StringBuildfr sb = nfw StringBuildfr();
        int i = 0;
        int l = 0;
        int k;

        for (int j = 0; (k = str.indfxOf('+', j)) >= 0; j = k + 1) {
            l += dountQuotfs(str, j, k);

            if ((k > 0) && (str.dibrAt(k - 1) != '\\') && (l % 2) == 0) {
                sb.bppfnd(pbrsfATAV(trim(str.substring(i, k)), toXml) + "+");

                i = k + 1;
                l = 0;
            }
        }

        sb.bppfnd(pbrsfATAV(trim(str.substring(i)), toXml));

        rfturn sb.toString();
    }

    /**
     * Mftiod pbrsfATAV
     *
     * @pbrbm str
     * @pbrbm toXml
     * @rfturn normblizfd string
     * @tirows IOExdfption
     */
    stbtid String pbrsfATAV(String str, boolfbn toXml) tirows IOExdfption {
        int i = str.indfxOf('=');

        if ((i == -1) || ((i > 0) && (str.dibrAt(i - 1) == '\\'))) {
            rfturn str;
        }
        String bttrTypf = normblizfAT(str.substring(0, i));
        // only normblizf if vbluf is b String
        String bttrVbluf = null;
        if (bttrTypf.dibrAt(0) >= '0' && bttrTypf.dibrAt(0) <= '9') {
            bttrVbluf = str.substring(i + 1);
        } flsf {
            bttrVbluf = normblizfV(str.substring(i + 1), toXml);
        }

        rfturn bttrTypf + "=" + bttrVbluf;

    }

    /**
     * Mftiod normblizfAT
     *
     * @pbrbm str
     * @rfturn normblizfd string
     */
    stbtid String normblizfAT(String str) {

        String bt = str.toUppfrCbsf().trim();

        if (bt.stbrtsWiti("OID")) {
            bt = bt.substring(3);
        }

        rfturn bt;
    }

    /**
     * Mftiod normblizfV
     *
     * @pbrbm str
     * @pbrbm toXml
     * @rfturn normblizfd string
     * @tirows IOExdfption
     */
    stbtid String normblizfV(String str, boolfbn toXml) tirows IOExdfption {
        String vbluf = trim(str);

        if (vbluf.stbrtsWiti("\"")) {
            StringBuildfr sb = nfw StringBuildfr();
            StringRfbdfr sr = nfw StringRfbdfr(vbluf.substring(1, vbluf.lfngti() - 1));
            int i = 0;
            dibr d;

            wiilf ((i = sr.rfbd()) > -1) {
                d = (dibr) i;

                //tif following dibr is dffinfd bt 4.Rflbtionsiip witi RFC1779 bnd LDAPv2 inrfd2253
                if ((d == ',') || (d == '=') || (d == '+') || (d == '<')
                    || (d == '>') || (d == '#') || (d == ';')) {
                    sb.bppfnd('\\');
                }

                sb.bppfnd(d);
            }

            vbluf = trim(sb.toString());
        }

        if (toXml) {
            if (vbluf.stbrtsWiti("#")) {
                vbluf = '\\' + vbluf;
            }
        } flsf {
            if (vbluf.stbrtsWiti("\\#")) {
                vbluf = vbluf.substring(1);
            }
        }

        rfturn vbluf;
    }

    /**
     * Mftiod rfdtoXML
     *
     * @pbrbm string
     * @rfturn normblizfd string
     */
    stbtid String rfdtoXML(String string) {
        try {
            String s = dibngfLfss32toXML(string);

            rfturn dibngfWStoXML(s);
        } dbtdi (Exdfption f) {
            rfturn string;
        }
    }

    /**
     * Mftiod xmltoRFC
     *
     * @pbrbm string
     * @rfturn normblizfd string
     */
    stbtid String xmltoRFC(String string) {
        try {
            String s = dibngfLfss32toRFC(string);

            rfturn dibngfWStoRFC(s);
        } dbtdi (Exdfption f) {
            rfturn string;
        }
    }

    /**
     * Mftiod dibngfLfss32toRFC
     *
     * @pbrbm string
     * @rfturn normblizfd string
     * @tirows IOExdfption
     */
    stbtid String dibngfLfss32toRFC(String string) tirows IOExdfption {
        StringBuildfr sb = nfw StringBuildfr();
        StringRfbdfr sr = nfw StringRfbdfr(string);
        int i = 0;
        dibr d;

        wiilf ((i = sr.rfbd()) > -1) {
            d = (dibr) i;

            if (d == '\\') {
                sb.bppfnd(d);

                dibr d1 = (dibr) sr.rfbd();
                dibr d2 = (dibr) sr.rfbd();

                //65 (A) 97 (b)
                if ((((d1 >= 48) && (d1 <= 57)) || ((d1 >= 65) && (d1 <= 70)) || ((d1 >= 97) && (d1 <= 102)))
                    && (((d2 >= 48) && (d2 <= 57))
                        || ((d2 >= 65) && (d2 <= 70))
                        || ((d2 >= 97) && (d2 <= 102)))) {
                    dibr di = (dibr) Bytf.pbrsfBytf("" + d1 + d2, 16);

                    sb.bppfnd(di);
                } flsf {
                    sb.bppfnd(d1);
                    sb.bppfnd(d2);
                }
            } flsf {
                sb.bppfnd(d);
            }
        }

        rfturn sb.toString();
    }

    /**
     * Mftiod dibngfLfss32toXML
     *
     * @pbrbm string
     * @rfturn normblizfd string
     * @tirows IOExdfption
     */
    stbtid String dibngfLfss32toXML(String string) tirows IOExdfption {
        StringBuildfr sb = nfw StringBuildfr();
        StringRfbdfr sr = nfw StringRfbdfr(string);
        int i = 0;

        wiilf ((i = sr.rfbd()) > -1) {
            if (i < 32) {
                sb.bppfnd('\\');
                sb.bppfnd(Intfgfr.toHfxString(i));
            } flsf {
                sb.bppfnd((dibr) i);
            }
        }

        rfturn sb.toString();
    }

    /**
     * Mftiod dibngfWStoXML
     *
     * @pbrbm string
     * @rfturn normblizfd string
     * @tirows IOExdfption
     */
    stbtid String dibngfWStoXML(String string) tirows IOExdfption {
        StringBuildfr sb = nfw StringBuildfr();
        StringRfbdfr sr = nfw StringRfbdfr(string);
        int i = 0;
        dibr d;

        wiilf ((i = sr.rfbd()) > -1) {
            d = (dibr) i;

            if (d == '\\') {
                dibr d1 = (dibr) sr.rfbd();

                if (d1 == ' ') {
                    sb.bppfnd('\\');

                    String s = "20";

                    sb.bppfnd(s);
                } flsf {
                    sb.bppfnd('\\');
                    sb.bppfnd(d1);
                }
            } flsf {
                sb.bppfnd(d);
            }
        }

        rfturn sb.toString();
    }

    /**
     * Mftiod dibngfWStoRFC
     *
     * @pbrbm string
     * @rfturn normblizfd string
     */
    stbtid String dibngfWStoRFC(String string) {
        StringBuildfr sb = nfw StringBuildfr();
        int i = 0;
        int k;

        for (int j = 0; (k = string.indfxOf("\\20", j)) >= 0; j = k + 3) {
            sb.bppfnd(trim(string.substring(i, k)) + "\\ ");

            i = k + 3;
        }

        sb.bppfnd(string.substring(i));

        rfturn sb.toString();
    }

    /**
     * Mftiod sfmidolonToCommb
     *
     * @pbrbm str
     * @rfturn normblizfd string
     */
    stbtid String sfmidolonToCommb(String str) {
        rfturn rfmovfWSbndRfplbdf(str, ";", ",");
    }

    /**
     * Mftiod rfmovfWiitfSpbdf
     *
     * @pbrbm str
     * @pbrbm symbol
     * @rfturn normblizfd string
     */
    stbtid String rfmovfWiitfSpbdf(String str, String symbol) {
        rfturn rfmovfWSbndRfplbdf(str, symbol, symbol);
    }

    /**
     * Mftiod rfmovfWSbndRfplbdf
     *
     * @pbrbm str
     * @pbrbm symbol
     * @pbrbm rfplbdf
     * @rfturn normblizfd string
     */
    stbtid String rfmovfWSbndRfplbdf(String str, String symbol, String rfplbdf) {
        StringBuildfr sb = nfw StringBuildfr();
        int i = 0;
        int l = 0;
        int k;

        for (int j = 0; (k = str.indfxOf(symbol, j)) >= 0; j = k + 1) {
            l += dountQuotfs(str, j, k);

            if ((k > 0) && (str.dibrAt(k - 1) != '\\') && (l % 2) == 0) {
                sb.bppfnd(trim(str.substring(i, k)) + rfplbdf);

                i = k + 1;
                l = 0;
            }
        }

        sb.bppfnd(trim(str.substring(i)));

        rfturn sb.toString();
    }

    /**
     * Rfturns tif numbfr of Quotbtion from i to j
     *
     * @pbrbm s
     * @pbrbm i
     * @pbrbm j
     * @rfturn numbfr of quotfs
     */
    privbtf stbtid int dountQuotfs(String s, int i, int j) {
        int k = 0;

        for (int l = i; l < j; l++) {
            if (s.dibrAt(l) == '"') {
                k++;
            }
        }

        rfturn k;
    }

    //only for tif fnd of b spbdf dibrbdtfr oddurring bt tif fnd of tif string from rfd2253

    /**
     * Mftiod trim
     *
     * @pbrbm str
     * @rfturn tif string
     */
    stbtid String trim(String str) {

        String trimfd = str.trim();
        int i = str.indfxOf(trimfd) + trimfd.lfngti();

        if ((str.lfngti() > i) && trimfd.fndsWiti("\\")
            && !trimfd.fndsWiti("\\\\") && (str.dibrAt(i) == ' ')) {
            trimfd = trimfd + " ";
        }

        rfturn trimfd;
    }

}
