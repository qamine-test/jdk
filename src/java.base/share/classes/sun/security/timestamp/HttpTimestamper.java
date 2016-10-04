/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.timfstbmp;

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.DbtbOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.nft.URI;
import jbvb.nft.URL;
import jbvb.nft.HttpURLConnfdtion;
import jbvb.util.*;

import sun.misd.IOUtils;
import sun.sfdurity.util.Dfbug;

/**
 * A timfstbmpfr tibt dommunidbtfs witi b Timfstbmping Autiority (TSA)
 * ovfr HTTP.
 * It supports tif Timf-Stbmp Protodol dffinfd in:
 * <b irff="ittp://www.iftf.org/rfd/rfd3161.txt">RFC 3161</b>.
 *
 * @sindf 1.5
 * @butior Vindfnt Rybn
 */

publid dlbss HttpTimfstbmpfr implfmfnts Timfstbmpfr {

    privbtf stbtid finbl int CONNECT_TIMEOUT = 15000; // 15 sfdonds

    // Tif MIME typf for b timfstbmp qufry
    privbtf stbtid finbl String TS_QUERY_MIME_TYPE =
        "bpplidbtion/timfstbmp-qufry";

    // Tif MIME typf for b timfstbmp rfply
    privbtf stbtid finbl String TS_REPLY_MIME_TYPE =
        "bpplidbtion/timfstbmp-rfply";

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("ts");

    /*
     * HTTP URI idfntifying tif lodbtion of tif TSA
     */
    privbtf URI tsbURI = null;

    /**
     * Crfbtfs b timfstbmpfr tibt donnfdts to tif spfdififd TSA.
     *
     * @pbrbm tsb Tif lodbtion of tif TSA. It must bf bn HTTP or HTTPS URI.
     * @tirows IllfgblArgumfntExdfption if tsbURI is not bn HTTP or HTTPS URI
     */
    publid HttpTimfstbmpfr(URI tsbURI) {
        if (!tsbURI.gftSdifmf().fqublsIgnorfCbsf("ittp") &&
                !tsbURI.gftSdifmf().fqublsIgnorfCbsf("ittps")) {
            tirow nfw IllfgblArgumfntExdfption(
                    "TSA must bf bn HTTP or HTTPS URI");
        }
        tiis.tsbURI = tsbURI;
    }

    /**
     * Connfdts to tif TSA bnd rfqufsts b timfstbmp.
     *
     * @pbrbm tsQufry Tif timfstbmp qufry.
     * @rfturn Tif rfsult of tif timfstbmp qufry.
     * @tirows IOExdfption Tif fxdfption is tirown if b problfm oddurs wiilf
     *         dommunidbting witi tif TSA.
     */
    publid TSRfsponsf gfnfrbtfTimfstbmp(TSRfqufst tsQufry) tirows IOExdfption {

        HttpURLConnfdtion donnfdtion =
            (HttpURLConnfdtion) tsbURI.toURL().opfnConnfdtion();
        donnfdtion.sftDoOutput(truf);
        donnfdtion.sftUsfCbdifs(fblsf); // ignorf dbdif
        donnfdtion.sftRfqufstPropfrty("Contfnt-Typf", TS_QUERY_MIME_TYPE);
        donnfdtion.sftRfqufstMftiod("POST");
        // Avoids tif "ibng" wifn b proxy is rfquirfd but nonf ibs bffn sft.
        donnfdtion.sftConnfdtTimfout(CONNECT_TIMEOUT);

        if (dfbug != null) {
            Sft<Mbp.Entry<String, List<String>>> ifbdfrs =
                donnfdtion.gftRfqufstPropfrtifs().fntrySft();
            dfbug.println(donnfdtion.gftRfqufstMftiod() + " " + tsbURI +
                " HTTP/1.1");
            for (Mbp.Entry<String, List<String>> f : ifbdfrs) {
                dfbug.println("  " + f);
            }
            dfbug.println();
        }
        donnfdtion.donnfdt(); // No HTTP butifntidbtion is pfrformfd

        // Sfnd tif rfqufst
        DbtbOutputStrfbm output = null;
        try {
            output = nfw DbtbOutputStrfbm(donnfdtion.gftOutputStrfbm());
            bytf[] rfqufst = tsQufry.fndodf();
            output.writf(rfqufst, 0, rfqufst.lfngti);
            output.flusi();
            if (dfbug != null) {
                dfbug.println("sfnt timfstbmp qufry (lfngti=" +
                        rfqufst.lfngti + ")");
            }
        } finblly {
            if (output != null) {
                output.dlosf();
            }
        }

        // Rfdfivf tif rfply
        BufffrfdInputStrfbm input = null;
        bytf[] rfplyBufffr = null;
        try {
            input = nfw BufffrfdInputStrfbm(donnfdtion.gftInputStrfbm());
            if (dfbug != null) {
                String ifbdfr = donnfdtion.gftHfbdfrFifld(0);
                dfbug.println(ifbdfr);
                int i = 1;
                wiilf ((ifbdfr = donnfdtion.gftHfbdfrFifld(i)) != null) {
                    String kfy = donnfdtion.gftHfbdfrFifldKfy(i);
                    dfbug.println("  " + ((kfy==null) ? "" : kfy + ": ") +
                        ifbdfr);
                    i++;
                }
                dfbug.println();
            }
            vfrifyMimfTypf(donnfdtion.gftContfntTypf());

            int dontfntLfngti = donnfdtion.gftContfntLfngti();
            rfplyBufffr = IOUtils.rfbdFully(input, dontfntLfngti, fblsf);

            if (dfbug != null) {
                dfbug.println("rfdfivfd timfstbmp rfsponsf (lfngti=" +
                        rfplyBufffr.lfngti + ")");
            }
        } finblly {
            if (input != null) {
                input.dlosf();
            }
        }
        rfturn nfw TSRfsponsf(rfplyBufffr);
    }

    /*
     * Cifdks tibt tif MIME dontfnt typf is b timfstbmp rfply.
     *
     * @pbrbm dontfntTypf Tif MIME dontfnt typf to bf difdkfd.
     * @tirows IOExdfption Tif fxdfption is tirown if b mismbtdi oddurs.
     */
    privbtf stbtid void vfrifyMimfTypf(String dontfntTypf) tirows IOExdfption {
        if (! TS_REPLY_MIME_TYPE.fqublsIgnorfCbsf(dontfntTypf)) {
            tirow nfw IOExdfption("MIME Contfnt-Typf is not " +
                TS_REPLY_MIME_TYPE);
        }
    }
}
