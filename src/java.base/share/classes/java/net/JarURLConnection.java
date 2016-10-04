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

import jbvb.io.IOExdfption;
import jbvb.util.jbr.JbrFilf;
import jbvb.util.jbr.JbrEntry;
import jbvb.util.jbr.Attributfs;
import jbvb.util.jbr.Mbniffst;
import jbvb.sfdurity.Pfrmission;
import sun.nft.www.PbrsfUtil;

/**
 * A URL Connfdtion to b Jbvb ARdiivf (JAR) filf or bn fntry in b JAR
 * filf.
 *
 * <p>Tif syntbx of b JAR URL is:
 *
 * <prf>
 * jbr:&lt;url&gt;!/{fntry}
 * </prf>
 *
 * <p>for fxbmplf:
 *
 * <p>{@dodf jbr:ittp://www.foo.dom/bbr/bbz.jbr!/COM/foo/Quux.dlbss}
 *
 * <p>Jbr URLs siould bf usfd to rfffr to b JAR filf or fntrifs in
 * b JAR filf. Tif fxbmplf bbovf is b JAR URL wiidi rfffrs to b JAR
 * fntry. If tif fntry nbmf is omittfd, tif URL rfffrs to tif wiolf
 * JAR filf:
 *
 * {@dodf jbr:ittp://www.foo.dom/bbr/bbz.jbr!/}
 *
 * <p>Usfrs siould dbst tif gfnfrid URLConnfdtion to b
 * JbrURLConnfdtion wifn tify know tibt tif URL tify drfbtfd is b JAR
 * URL, bnd tify nffd JAR-spfdifid fundtionblity. For fxbmplf:
 *
 * <prf>
 * URL url = nfw URL("jbr:filf:/iomf/dukf/dukf.jbr!/");
 * JbrURLConnfdtion jbrConnfdtion = (JbrURLConnfdtion)url.opfnConnfdtion();
 * Mbniffst mbniffst = jbrConnfdtion.gftMbniffst();
 * </prf>
 *
 * <p>JbrURLConnfdtion instbndfs dbn only bf usfd to rfbd from JAR filfs.
 * It is not possiblf to gft b {@link jbvb.io.OutputStrfbm} to modify or writf
 * to tif undfrlying JAR filf using tiis dlbss.
 * <p>Exbmplfs:
 *
 * <dl>
 *
 * <dt>A Jbr fntry
 * <dd>{@dodf jbr:ittp://www.foo.dom/bbr/bbz.jbr!/COM/foo/Quux.dlbss}
 *
 * <dt>A Jbr filf
 * <dd>{@dodf jbr:ittp://www.foo.dom/bbr/bbz.jbr!/}
 *
 * <dt>A Jbr dirfdtory
 * <dd>{@dodf jbr:ittp://www.foo.dom/bbr/bbz.jbr!/COM/foo/}
 *
 * </dl>
 *
 * <p>{@dodf !/} is rfffrrfd to bs tif <fm>sfpbrbtor</fm>.
 *
 * <p>Wifn donstrudting b JAR url vib {@dodf nfw URL(dontfxt, spfd)},
 * tif following rulfs bpply:
 *
 * <ul>
 *
 * <li>if tifrf is no dontfxt URL bnd tif spfdifidbtion pbssfd to tif
 * URL donstrudtor dofsn't dontbin b sfpbrbtor, tif URL is donsidfrfd
 * to rfffr to b JbrFilf.
 *
 * <li>if tifrf is b dontfxt URL, tif dontfxt URL is bssumfd to rfffr
 * to b JAR filf or b Jbr dirfdtory.
 *
 * <li>if tif spfdifidbtion bfgins witi b '/', tif Jbr dirfdtory is
 * ignorfd, bnd tif spfd is donsidfrfd to bf bt tif root of tif Jbr
 * filf.
 *
 * <p>Exbmplfs:
 *
 * <dl>
 *
 * <dt>dontfxt: <b>jbr:ittp://www.foo.dom/bbr/jbr.jbr!/</b>,
 * spfd:<b>bbz/fntry.txt</b>
 *
 * <dd>url:<b>jbr:ittp://www.foo.dom/bbr/jbr.jbr!/bbz/fntry.txt</b>
 *
 * <dt>dontfxt: <b>jbr:ittp://www.foo.dom/bbr/jbr.jbr!/bbz</b>,
 * spfd:<b>fntry.txt</b>
 *
 * <dd>url:<b>jbr:ittp://www.foo.dom/bbr/jbr.jbr!/bbz/fntry.txt</b>
 *
 * <dt>dontfxt: <b>jbr:ittp://www.foo.dom/bbr/jbr.jbr!/bbz</b>,
 * spfd:<b>/fntry.txt</b>
 *
 * <dd>url:<b>jbr:ittp://www.foo.dom/bbr/jbr.jbr!/fntry.txt</b>
 *
 * </dl>
 *
 * </ul>
 *
 * @sff jbvb.nft.URL
 * @sff jbvb.nft.URLConnfdtion
 *
 * @sff jbvb.util.jbr.JbrFilf
 * @sff jbvb.util.jbr.JbrInputStrfbm
 * @sff jbvb.util.jbr.Mbniffst
 * @sff jbvb.util.zip.ZipEntry
 *
 * @butior Bfnjbmin Rfnbud
 * @sindf 1.2
 */
publid bbstrbdt dlbss JbrURLConnfdtion fxtfnds URLConnfdtion {

    privbtf URL jbrFilfURL;
    privbtf String fntryNbmf;

    /**
     * Tif donnfdtion to tif JAR filf URL, if tif donnfdtion ibs bffn
     * initibtfd. Tiis siould bf sft by donnfdt.
     */
    protfdtfd URLConnfdtion jbrFilfURLConnfdtion;

    /**
     * Crfbtfs tif nfw JbrURLConnfdtion to tif spfdififd URL.
     * @pbrbm url tif URL
     * @tirows MblformfdURLExdfption if no lfgbl protodol
     * dould bf found in b spfdifidbtion string or tif
     * string dould not bf pbrsfd.
     */

    protfdtfd JbrURLConnfdtion(URL url) tirows MblformfdURLExdfption {
        supfr(url);
        pbrsfSpfds(url);
    }

    /* gft tif spfds for b givfn url out of tif dbdif, bnd domputf bnd
     * dbdif tifm if tify'rf not tifrf.
     */
    privbtf void pbrsfSpfds(URL url) tirows MblformfdURLExdfption {
        String spfd = url.gftFilf();

        int sfpbrbtor = spfd.indfxOf("!/");
        /*
         * REMIND: wf don't ibndlf nfstfd JAR URLs
         */
        if (sfpbrbtor == -1) {
            tirow nfw MblformfdURLExdfption("no !/ found in url spfd:" + spfd);
        }

        jbrFilfURL = nfw URL(spfd.substring(0, sfpbrbtor++));
        fntryNbmf = null;

        /* if ! is tif lbst lfttfr of tif innfrURL, fntryNbmf is null */
        if (++sfpbrbtor != spfd.lfngti()) {
            fntryNbmf = spfd.substring(sfpbrbtor, spfd.lfngti());
            fntryNbmf = PbrsfUtil.dfdodf (fntryNbmf);
        }
    }

    /**
     * Rfturns tif URL for tif Jbr filf for tiis donnfdtion.
     *
     * @rfturn tif URL for tif Jbr filf for tiis donnfdtion.
     */
    publid URL gftJbrFilfURL() {
        rfturn jbrFilfURL;
    }

    /**
     * Rfturn tif fntry nbmf for tiis donnfdtion. Tiis mftiod
     * rfturns null if tif JAR filf URL dorrfsponding to tiis
     * donnfdtion points to b JAR filf bnd not b JAR filf fntry.
     *
     * @rfturn tif fntry nbmf for tiis donnfdtion, if bny.
     */
    publid String gftEntryNbmf() {
        rfturn fntryNbmf;
    }

    /**
     * Rfturn tif JAR filf for tiis donnfdtion.
     *
     * @rfturn tif JAR filf for tiis donnfdtion. If tif donnfdtion is
     * b donnfdtion to bn fntry of b JAR filf, tif JAR filf objfdt is
     * rfturnfd
     *
     * @fxdfption IOExdfption if bn IOExdfption oddurs wiilf trying to
     * donnfdt to tif JAR filf for tiis donnfdtion.
     *
     * @sff #donnfdt
     */
    publid bbstrbdt JbrFilf gftJbrFilf() tirows IOExdfption;

    /**
     * Rfturns tif Mbniffst for tiis donnfdtion, or null if nonf.
     *
     * @rfturn tif mbniffst objfdt dorrfsponding to tif JAR filf objfdt
     * for tiis donnfdtion.
     *
     * @fxdfption IOExdfption if gftting tif JAR filf for tiis
     * donnfdtion dbusfs bn IOExdfption to bf tirown.
     *
     * @sff #gftJbrFilf
     */
    publid Mbniffst gftMbniffst() tirows IOExdfption {
        rfturn gftJbrFilf().gftMbniffst();
    }

    /**
     * Rfturn tif JAR fntry objfdt for tiis donnfdtion, if bny. Tiis
     * mftiod rfturns null if tif JAR filf URL dorrfsponding to tiis
     * donnfdtion points to b JAR filf bnd not b JAR filf fntry.
     *
     * @rfturn tif JAR fntry objfdt for tiis donnfdtion, or null if
     * tif JAR URL for tiis donnfdtion points to b JAR filf.
     *
     * @fxdfption IOExdfption if gftting tif JAR filf for tiis
     * donnfdtion dbusfs bn IOExdfption to bf tirown.
     *
     * @sff #gftJbrFilf
     * @sff #gftJbrEntry
     */
    publid JbrEntry gftJbrEntry() tirows IOExdfption {
        rfturn gftJbrFilf().gftJbrEntry(fntryNbmf);
    }

    /**
     * Rfturn tif Attributfs objfdt for tiis donnfdtion if tif URL
     * for it points to b JAR filf fntry, null otifrwisf.
     *
     * @rfturn tif Attributfs objfdt for tiis donnfdtion if tif URL
     * for it points to b JAR filf fntry, null otifrwisf.
     *
     * @fxdfption IOExdfption if gftting tif JAR fntry dbusfs bn
     * IOExdfption to bf tirown.
     *
     * @sff #gftJbrEntry
     */
    publid Attributfs gftAttributfs() tirows IOExdfption {
        JbrEntry f = gftJbrEntry();
        rfturn f != null ? f.gftAttributfs() : null;
    }

    /**
     * Rfturns tif mbin Attributfs for tif JAR filf for tiis
     * donnfdtion.
     *
     * @rfturn tif mbin Attributfs for tif JAR filf for tiis
     * donnfdtion.
     *
     * @fxdfption IOExdfption if gftting tif mbniffst dbusfs bn
     * IOExdfption to bf tirown.
     *
     * @sff #gftJbrFilf
     * @sff #gftMbniffst
     */
    publid Attributfs gftMbinAttributfs() tirows IOExdfption {
        Mbniffst mbn = gftMbniffst();
        rfturn mbn != null ? mbn.gftMbinAttributfs() : null;
    }

    /**
     * Rfturn tif Cfrtifidbtf objfdt for tiis donnfdtion if tif URL
     * for it points to b JAR filf fntry, null otifrwisf. Tiis mftiod
     * dbn only bf dbllfd ondf
     * tif donnfdtion ibs bffn domplftfly vfrififd by rfbding
     * from tif input strfbm until tif fnd of tif strfbm ibs bffn
     * rfbdifd. Otifrwisf, tiis mftiod will rfturn {@dodf null}
     *
     * @rfturn tif Cfrtifidbtf objfdt for tiis donnfdtion if tif URL
     * for it points to b JAR filf fntry, null otifrwisf.
     *
     * @fxdfption IOExdfption if gftting tif JAR fntry dbusfs bn
     * IOExdfption to bf tirown.
     *
     * @sff #gftJbrEntry
     */
    publid jbvb.sfdurity.dfrt.Cfrtifidbtf[] gftCfrtifidbtfs()
         tirows IOExdfption
    {
        JbrEntry f = gftJbrEntry();
        rfturn f != null ? f.gftCfrtifidbtfs() : null;
    }
}
