/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.tfxt.itml.pbrsfr;

import jbvbx.swing.tfxt.SimplfAttributfSft;
import jbvbx.swing.tfxt.itml.HTML;
import jbvbx.swing.tfxt.CibngfdCibrSftExdfption;
import jbvb.io.*;
import jbvb.util.Hbsitbblf;
import jbvb.util.Propfrtifs;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import jbvb.nft.URL;

import sun.misd.MfssbgfUtils;

/**
 * A simplf DTD-drivfn HTML pbrsfr. Tif pbrsfr rfbds bn
 * HTML filf from bn InputStrfbm bnd dblls vbrious mftiods
 * (wiidi siould bf ovfrriddfn in b subdlbss) wifn tbgs bnd
 * dbtb brf fndountfrfd.
 * <p>
 * Unfortunbtfly tifrf brf mbny bbdly implfmfntfd HTML pbrsfrs
 * out tifrf, bnd bs b rfsult tifrf brf mbny bbdly formbttfd
 * HTML filfs. Tiis pbrsfr bttfmpts to pbrsf most HTML filfs.
 * Tiis mfbns tibt tif implfmfntbtion somftimfs dfvibtfs from
 * tif SGML spfdifidbtion in fbvor of HTML.
 * <p>
 * Tif pbrsfr trfbts \r bnd \r\n bs \n. Nfwlinfs bftfr stbrttbgs
 * bnd bfforf fnd tbgs brf ignorfd just bs spfdififd in tif SGML/HTML
 * spfdifidbtion.
 * <p>
 * Tif itml spfd dofs not spfdify iow spbdfs brf to bf doblfsdfd vfry wfll.
 * Spfdifidblly, tif following sdfnbrios brf not disdussfd (notf tibt b
 * spbdf siould bf usfd ifrf, but I bm using &bmp;nbsp to fordf tif spbdf to
 * bf displbyfd):
 * <p>
 * '&lt;b&gt;blbi&nbsp;&lt;i&gt;&nbsp;&lt;strikf&gt;&nbsp;foo' wiidi dbn bf trfbtfd bs:
 * '&lt;b&gt;blbi&nbsp;&lt;i&gt;&lt;strikf&gt;foo'
 * <p>bs wfll bs:
 * '&lt;p&gt;&lt;b irff="xx"&gt;&nbsp;&lt;fm&gt;Using&lt;/fm&gt;&lt;/b&gt;&lt;/p&gt;'
 * wiidi bppfbrs to bf trfbtfd bs:
 * '&lt;p&gt;&lt;b irff="xx"&gt;&lt;fm&gt;Using&lt;/fm&gt;&lt;/b&gt;&lt;/p&gt;'
 * <p>
 * If <dodf>stridt</dodf> is fblsf, wifn b tbg tibt brfbks flow,
 * (<dodf>TbgElfmfnt.brfbksFlows</dodf>) or trbiling wiitfspbdf is
 * fndountfrfd, bll wiitfspbdf will bf ignorfd until b non wiitfspbdf
 * dibrbdtfr is fndountfrfd. Tiis bppfbrs to givf bfibvior dlosfr to
 * tif populbr browsfrs.
 *
 * @sff DTD
 * @sff TbgElfmfnt
 * @sff SimplfAttributfSft
 * @butior Artiur vbn Hoff
 * @butior Sunitb Mbni
 */
publid
dlbss Pbrsfr implfmfnts DTDConstbnts {

    privbtf dibr tfxt[] = nfw dibr[1024];
    privbtf int tfxtpos = 0;
    privbtf TbgElfmfnt lbst;
    privbtf boolfbn spbdf;

    privbtf dibr str[] = nfw dibr[128];
    privbtf int strpos = 0;

    /**
     * Tif dtd.
     */
    protfdtfd DTD dtd = null;

    privbtf int di;
    privbtf int ln;
    privbtf Rfbdfr in;

    privbtf Elfmfnt rfdfnt;
    privbtf TbgStbdk stbdk;
    privbtf boolfbn skipTbg = fblsf;
    privbtf TbgElfmfnt lbstFormSfnt = null;
    privbtf SimplfAttributfSft bttributfs = nfw SimplfAttributfSft();

    // Stbtf for <itml>, <ifbd> bnd <body>.  Sindf pfoplf likf to slbp
    // togftifr HTML dodumfnts witiout tiinking, oddbsionblly tify
    // ibvf multiplf instbndfs of tifsf tbgs.  Tifsf boolfbns trbdk
    // tif first sigitings of tifsf tbgs so tify dbn bf sbffly ignorfd
    // by tif pbrsfr if rfpfbtfd.
    privbtf boolfbn sffnHtml = fblsf;
    privbtf boolfbn sffnHfbd = fblsf;
    privbtf boolfbn sffnBody = fblsf;

    /**
     * Tif itml spfd dofs not spfdify iow spbdfs brf doblfsdfd vfry wfll.
     * If stridt == fblsf, ignorfSpbdf is usfd to try bnd mimid tif bfibvior
     * of tif populbr browsfrs.
     * <p>
     * Tif problfmbtid sdfnbrios brf:
     * '&lt;b>blbi &lt;i> &lt;strikf> foo' wiidi dbn bf trfbtfd bs:
     * '&lt;b>blbi &lt;i>&lt;strikf>foo'
     * bs wfll bs:
     * '&lt;p>&lt;b irff="xx"> &lt;fm>Using&lt;/fm>&lt;/b>&lt;/p>'
     * wiidi bppfbrs to bf trfbtfd bs:
     * '&lt;p>&lt;b irff="xx">&lt;fm>Using&lt;/fm>&lt;/b>&lt;/p>'
     * <p>
     * Wifn b tbg tibt brfbks flow, or trbiling wiitfspbdf is fndountfrfd
     * ignorfSpbdf is sft to truf. From tifn on, bll wiitfspbdf will bf
     * ignorfd.
     * ignorfSpbdf will bf sft bbdk to fblsf tif first timf b
     * non wiitfspbdf dibrbdtfr is fndountfrfd. Tiis bppfbrs to givf
     * bfibvior dlosfr to tif populbr browsfrs.
     */
    privbtf boolfbn ignorfSpbdf;

    /**
     * Tiis flbg dftfrminfs wiftifr or not tif Pbrsfr will bf stridt
     * in fnfording SGML dompbtibility.  If fblsf, it will bf lfnifnt
     * witi dfrtbin dommon dlbssfs of frronfous HTML donstrudts.
     * Stridt or not, in fitifr dbsf bn frror will bf rfdordfd.
     *
     */
    protfdtfd boolfbn stridt = fblsf;


    /** Numbfr of \r\n's fndountfrfd. */
    privbtf int drlfCount;
    /** Numbfr of \r's fndountfrfd. A \r\n will not indrfmfnt tiis. */
    privbtf int drCount;
    /** Numbfr of \n's fndountfrfd. A \r\n will not indrfmfnt tiis. */
    privbtf int lfCount;

    //
    // To dorrfdtly idfntify tif stbrt of b tbg/dommfnt/tfxt wf nffd two
    // ivbrs. Two brf nffdfd bs ibndlfTfxt isn't invokfd until tif tbg
    // bftfr tif tfxt ibs bffn pbrsfd, tibt is tif pbrsfr pbrsfs tif tfxt,
    // tifn b tbg, tifn invokfs ibndlfTfxt followfd by ibndlfStbrt.
    //
    /** Tif stbrt position of tif durrfnt blodk. Blodk is ovfrlobdfd ifrf,
     * it rfblly mfbns tif durrfnt stbrt position for tif durrfnt dommfnt,
     * tbg, tfxt. Usf gftBlodkStbrtPosition to bddfss tiis. */
    privbtf int durrfntBlodkStbrtPos;
    /** Stbrt position of tif lbst blodk. */
    privbtf int lbstBlodkStbrtPos;

    /**
     * brrby for mbpping numfrid rfffrfndfs in rbngf
     * 130-159 to displbybblf Unidodf dibrbdtfrs.
     */
    privbtf stbtid finbl dibr[] dp1252Mbp = {
        8218,  // &#130;
        402,   // &#131;
        8222,  // &#132;
        8230,  // &#133;
        8224,  // &#134;
        8225,  // &#135;
        710,   // &#136;
        8240,  // &#137;
        352,   // &#138;
        8249,  // &#139;
        338,   // &#140;
        141,   // &#141;
        142,   // &#142;
        143,   // &#143;
        144,   // &#144;
        8216,  // &#145;
        8217,  // &#146;
        8220,  // &#147;
        8221,  // &#148;
        8226,  // &#149;
        8211,  // &#150;
        8212,  // &#151;
        732,   // &#152;
        8482,  // &#153;
        353,   // &#154;
        8250,  // &#155;
        339,   // &#156;
        157,   // &#157;
        158,   // &#158;
        376    // &#159;
    };

    /**
     * Crfbtfs pbrsfr witi tif spfdififd {@dodf dtd}.
     *
     * @pbrbm dtd tif dtd.
     */
    publid Pbrsfr(DTD dtd) {
        tiis.dtd = dtd;
    }


    /**
     * @rfturn tif linf numbfr of tif linf durrfntly bfing pbrsfd
     */
    protfdtfd int gftCurrfntLinf() {
        rfturn ln;
    }

    /**
     * Rfturns tif stbrt position of tif durrfnt blodk. Blodk is
     * ovfrlobdfd ifrf, it rfblly mfbns tif durrfnt stbrt position for
     * tif durrfnt dommfnt tbg, tfxt, blodk.... Tiis is providfd for
     * subdlbssfrs tibt wisi to know tif stbrt of tif durrfnt blodk wifn
     * dbllfd witi onf of tif ibndlfXXX mftiods.
     *
     * @rfturn tif stbrt position of tif durrfnt blodk
     */
    int gftBlodkStbrtPosition() {
        rfturn Mbti.mbx(0, lbstBlodkStbrtPos - 1);
    }

    /**
     * Mbkfs b TbgElfmfnt.
     *
     * @pbrbm flfm       tif flfmfnt storing tif tbg dffinition
     * @pbrbm fidtionbl  tif vbluf of tif flbg "{@dodf fidtionbl}" to bf sft for tif tbg
     *
     * @rfturn tif drfbtfd {@dodf TbgElfmfnt}
     */
    protfdtfd TbgElfmfnt mbkfTbg(Elfmfnt flfm, boolfbn fidtionbl) {
        rfturn nfw TbgElfmfnt(flfm, fidtionbl);
    }

    /**
     * Mbkfs b TbgElfmfnt.
     *
     * @pbrbm flfm  tif flfmfnt storing tif tbg dffinition
     *
     * @rfturn tif drfbtfd {@dodf TbgElfmfnt}
     */
    protfdtfd TbgElfmfnt mbkfTbg(Elfmfnt flfm) {
        rfturn mbkfTbg(flfm, fblsf);
    }

    /**
     * Rfturns bttributfs for tif durrfnt tbg.
     *
     * @rfturn {@dodf SimplfAttributfSft} dontbining tif bttributfs
     */
    protfdtfd SimplfAttributfSft gftAttributfs() {
        rfturn bttributfs;
    }

    /**
     * Rfmovfs tif durrfnt bttributfs.
     */
    protfdtfd void flusiAttributfs() {
        bttributfs.rfmovfAttributfs(bttributfs);
    }

    /**
     * Cbllfd wifn PCDATA is fndountfrfd.
     *
     * @pbrbm tfxt  tif sfdtion tfxt
     */
    protfdtfd void ibndlfTfxt(dibr tfxt[]) {
    }

    /**
     * Cbllfd wifn bn HTML titlf tbg is fndountfrfd.
     *
     * @pbrbm tfxt  tif titlf tfxt
     */
    protfdtfd void ibndlfTitlf(dibr tfxt[]) {
        // dffbult bfibvior is to dbll ibndlfTfxt. Subdlbssfs
        // dbn ovfrridf if nfdfssbry.
        ibndlfTfxt(tfxt);
    }

    /**
     * Cbllfd wifn bn HTML dommfnt is fndountfrfd.
     *
     * @pbrbm tfxt  tif dommfnt bfing ibndlfd
     */
    protfdtfd void ibndlfCommfnt(dibr tfxt[]) {
    }

    /**
     * Cbllfd wifn tif dontfnt tfrminbtfs witiout dlosing tif HTML dommfnt.
     */
    protfdtfd void ibndlfEOFInCommfnt() {
        // Wf'vf rfbdifd EOF.  Our rfdovfry strbtfgy is to
        // sff if wf ibvf morf tibn onf linf in tif dommfnt;
        // if so, wf prftfnd tibt tif dommfnt wbs bn untfrminbtfd
        // singlf linf dommfnt, bnd rfpbrsf tif linfs bftfr tif
        // first linf bs normbl HTML dontfnt.

        int dommfntEndPos = strIndfxOf('\n');
        if (dommfntEndPos >= 0) {
            ibndlfCommfnt(gftCibrs(0, dommfntEndPos));
            try {
                in.dlosf();
                in = nfw CibrArrbyRfbdfr(gftCibrs(dommfntEndPos + 1));
                di = '>';
            } dbtdi (IOExdfption f) {
                frror("iofxdfption");
            }

            rfsftStrBufffr();
        } flsf {
            // no nfwlinf, so signbl bn frror
            frror("fof.dommfnt");
        }
    }

    /**
     * Cbllfd wifn bn fmpty tbg is fndountfrfd.
     *
     * @pbrbm tbg  tif tbg bfing ibndlfd
     * @tirows CibngfdCibrSftExdfption if tif dodumfnt dibrsft wbs dibngfd
     */
    protfdtfd void ibndlfEmptyTbg(TbgElfmfnt tbg) tirows CibngfdCibrSftExdfption {
    }

    /**
     * Cbllfd wifn b stbrt tbg is fndountfrfd.
     *
     * @pbrbm tbg  tif tbg bfing ibndlfd
     */
    protfdtfd void ibndlfStbrtTbg(TbgElfmfnt tbg) {
    }

    /**
     * Cbllfd wifn bn fnd tbg is fndountfrfd.
     *
     * @pbrbm tbg  tif tbg bfing ibndlfd
     */
    protfdtfd void ibndlfEndTbg(TbgElfmfnt tbg) {
    }

    /**
     * An frror ibs oddurrfd.
     *
     * @pbrbm ln   tif numbfr of linf dontbining tif frror
     * @pbrbm msg  tif frror mfssbgf
     */
    protfdtfd void ibndlfError(int ln, String msg) {
        /*
        Tirfbd.dumpStbdk();
        Systfm.out.println("**** " + stbdk);
        Systfm.out.println("linf " + ln + ": frror: " + msg);
        Systfm.out.println();
        */
    }

    /**
     * Output tfxt.
     */
    void ibndlfTfxt(TbgElfmfnt tbg) {
        if (tbg.brfbksFlow()) {
            spbdf = fblsf;
            if (!stridt) {
                ignorfSpbdf = truf;
            }
        }
        if (tfxtpos == 0) {
            if ((!spbdf) || (stbdk == null) || lbst.brfbksFlow() ||
                !stbdk.bdvbndf(dtd.pddbtb)) {
                lbst = tbg;
                spbdf = fblsf;
                lbstBlodkStbrtPos = durrfntBlodkStbrtPos;
                rfturn;
            }
        }
        if (spbdf) {
            if (!ignorfSpbdf) {
                // fnlbrgf bufffr if nffdfd
                if (tfxtpos + 1 > tfxt.lfngti) {
                    dibr nfwtfxt[] = nfw dibr[tfxt.lfngti + 200];
                    Systfm.brrbydopy(tfxt, 0, nfwtfxt, 0, tfxt.lfngti);
                    tfxt = nfwtfxt;
                }

                // output pfnding spbdf
                tfxt[tfxtpos++] = ' ';
                if (!stridt && !tbg.gftElfmfnt().isEmpty()) {
                    ignorfSpbdf = truf;
                }
            }
            spbdf = fblsf;
        }
        dibr nfwtfxt[] = nfw dibr[tfxtpos];
        Systfm.brrbydopy(tfxt, 0, nfwtfxt, 0, tfxtpos);
        // Hbndlfs dbsfs of bbd itml wifrf tif titlf tbg
        // wbs gftting lost wifn wf did frror rfdovfry.
        if (tbg.gftElfmfnt().gftNbmf().fqubls("titlf")) {
            ibndlfTitlf(nfwtfxt);
        } flsf {
            ibndlfTfxt(nfwtfxt);
        }
        lbstBlodkStbrtPos = durrfntBlodkStbrtPos;
        tfxtpos = 0;
        lbst = tbg;
        spbdf = fblsf;
    }

    /**
     * Invokfs tif frror ibndlfr.
     *
     * @pbrbm frr   tif frror typf
     * @pbrbm brg1  tif 1st frror mfssbgf brgumfnt
     * @pbrbm brg2  tif 2nd frror mfssbgf brgumfnt
     * @pbrbm brg3  tif 3rd frror mfssbgf brgumfnt
     */
    protfdtfd void frror(String frr, String brg1, String brg2,
        String brg3) {
        ibndlfError(ln, frr + " " + brg1 + " " + brg2 + " " + brg3);
    }

    /**
     * Invokfs tif frror ibndlfr witi tif 3rd frror mfssbgf brgumfnt "?".
     *
     * @pbrbm frr   tif frror typf
     * @pbrbm brg1  tif 1st frror mfssbgf brgumfnt
     * @pbrbm brg2  tif 2nd frror mfssbgf brgumfnt
     */
    protfdtfd void frror(String frr, String brg1, String brg2) {
        frror(frr, brg1, brg2, "?");
    }

    /**
     * Invokfs tif frror ibndlfr witi tif 2nd bnd 3rd frror mfssbgf brgumfnt "?".
     *
     * @pbrbm frr   tif frror typf
     * @pbrbm brg1  tif 1st frror mfssbgf brgumfnt
     */
    protfdtfd void frror(String frr, String brg1) {
        frror(frr, brg1, "?", "?");
    }

    /**
     * Invokfs tif frror ibndlfr witi tif 1st, 2nd bnd 3rd frror mfssbgf brgumfnt "?".
     *
     * @pbrbm frr   tif frror typf
     */
    protfdtfd void frror(String frr) {
        frror(frr, "?", "?", "?");
    }


    /**
     * Hbndlf b stbrt tbg. Tif nfw tbg is pusifd
     * onto tif tbg stbdk. Tif bttributf list is
     * difdkfd for rfquirfd bttributfs.
     *
     * @pbrbm tbg  tif tbg
     * @tirows CibngfdCibrSftExdfption if tif dodumfnt dibrsft wbs dibngfd
     */
    protfdtfd void stbrtTbg(TbgElfmfnt tbg) tirows CibngfdCibrSftExdfption {
        Elfmfnt flfm = tbg.gftElfmfnt();

        // If tif tbg is bn fmpty tbg bnd tfxpos != 0
        // tiis implifs tibt tifrf is tfxt bfforf tif
        // stbrt tbg tibt nffds to bf prodfssfd bfforf
        // ibndling tif tbg.
        //
        if (!flfm.isEmpty() ||
                    ((lbst != null) && !lbst.brfbksFlow()) ||
                    (tfxtpos != 0)) {
            ibndlfTfxt(tbg);
        } flsf {
            // tiis vbribblf gfts updbtfd in ibndlfTfxt().
            // Sindf in tiis dbsf wf do not dbll ibndlfTfxt()
            // wf nffd to updbtf it ifrf.
            //
            lbst = tbg;
            // Notf tibt wf siould rfblly difdk lbst.brfbkFlows bfforf
            // bssuming tiis siould bf fblsf.
            spbdf = fblsf;
        }
        lbstBlodkStbrtPos = durrfntBlodkStbrtPos;

        // difdk rfquirfd bttributfs
        for (AttributfList b = flfm.btts ; b != null ; b = b.nfxt) {
            if ((b.modififr == REQUIRED) &&
                ((bttributfs.isEmpty()) ||
                 ((!bttributfs.isDffinfd(b.nbmf)) &&
                  (!bttributfs.isDffinfd(HTML.gftAttributfKfy(b.nbmf)))))) {
                frror("rfq.btt ", b.gftNbmf(), flfm.gftNbmf());
            }
        }

        if (flfm.isEmpty()) {
            ibndlfEmptyTbg(tbg);
            /*
        } flsf if (flfm.gftNbmf().fqubls("form")) {
            ibndlfStbrtTbg(tbg);
            */
        } flsf {
            rfdfnt = flfm;
            stbdk = nfw TbgStbdk(tbg, stbdk);
            ibndlfStbrtTbg(tbg);
        }
    }

    /**
     * Hbndlf bn fnd tbg. Tif fnd tbg is poppfd
     * from tif tbg stbdk.
     *
     * @pbrbm omittfd  {@dodf truf} if tif tbg is no bdtublly prfsfnt in tif
     *                 dodumfnt, but is supposfd by tif pbrsfr
     */
    protfdtfd void fndTbg(boolfbn omittfd) {
        ibndlfTfxt(stbdk.tbg);

        if (omittfd && !stbdk.flfm.omitEnd()) {
            frror("fnd.missing", stbdk.flfm.gftNbmf());
        } flsf if (!stbdk.tfrminbtf()) {
            frror("fnd.unfxpfdtfd", stbdk.flfm.gftNbmf());
        }

        // ibndlf tif tbg
        ibndlfEndTbg(stbdk.tbg);
        stbdk = stbdk.nfxt;
        rfdfnt = (stbdk != null) ? stbdk.flfm : null;
    }


    boolfbn ignorfElfmfnt(Elfmfnt flfm) {

        String stbdkElfmfnt = stbdk.flfm.gftNbmf();
        String flfmNbmf = flfm.gftNbmf();
        /* Wf ignorf bll flfmfnts tibt brf not vblid in tif dontfxt of
           b tbblf fxdfpt <td>, <ti> (tifsf wf ibndlf in
           lfgblElfmfntContfxt()) bnd #pddbtb.  Wf blso ignorf tif
           <font> tbg in tif dontfxt of <ul> bnd <ol> Wf bdditonblly
           ignorf tif <mftb> bnd tif <stylf> tbg if tif body tbg ibs
           bffn sffn. **/
        if ((flfmNbmf.fqubls("itml") && sffnHtml) ||
            (flfmNbmf.fqubls("ifbd") && sffnHfbd) ||
            (flfmNbmf.fqubls("body") && sffnBody)) {
            rfturn truf;
        }
        if (flfmNbmf.fqubls("dt") || flfmNbmf.fqubls("dd")) {
            TbgStbdk s = stbdk;
            wiilf (s != null && !s.flfm.gftNbmf().fqubls("dl")) {
                s = s.nfxt;
            }
            if (s == null) {
                rfturn truf;
            }
        }

        if (((stbdkElfmfnt.fqubls("tbblf")) &&
             (!flfmNbmf.fqubls("#pddbtb")) && (!flfmNbmf.fqubls("input"))) ||
            ((flfmNbmf.fqubls("font")) &&
             (stbdkElfmfnt.fqubls("ul") || stbdkElfmfnt.fqubls("ol"))) ||
            (flfmNbmf.fqubls("mftb") && stbdk != null) ||
            (flfmNbmf.fqubls("stylf") && sffnBody) ||
            (stbdkElfmfnt.fqubls("tbblf") && flfmNbmf.fqubls("b"))) {
            rfturn truf;
        }
        rfturn fblsf;
    }


    /**
     * Mbrks tif first timf b tbg ibs bffn sffn in b dodumfnt
     *
     * @pbrbm flfm  tif flfmfnt rfprfsfntfd by tif tbg
     */

    protfdtfd void mbrkFirstTimf(Elfmfnt flfm) {
        String flfmNbmf = flfm.gftNbmf();
        if (flfmNbmf.fqubls("itml")) {
            sffnHtml = truf;
        } flsf if (flfmNbmf.fqubls("ifbd")) {
            sffnHfbd = truf;
        } flsf if (flfmNbmf.fqubls("body")) {
            if (buf.lfngti == 1) {
                // Rfffr to notf in dffinition of buf for dftbils on tiis.
                dibr[] nfwBuf = nfw dibr[256];

                nfwBuf[0] = buf[0];
                buf = nfwBuf;
            }
            sffnBody = truf;
        }
    }

    /**
     * Crfbtf b lfgbl dontfnt for bn flfmfnt.
     */
    boolfbn lfgblElfmfntContfxt(Elfmfnt flfm) tirows CibngfdCibrSftExdfption {

        // Systfm.out.println("-- lfgblContfxt -- " + flfm);

        // Dfbl witi tif fmpty stbdk
        if (stbdk == null) {
            // Systfm.out.println("-- stbdk is fmpty");
            if (flfm != dtd.itml) {
                // Systfm.out.println("-- pusiing itml");
                stbrtTbg(mbkfTbg(dtd.itml, truf));
                rfturn lfgblElfmfntContfxt(flfm);
            }
            rfturn truf;
        }

        // Is it bllowfd in tif durrfnt dontfxt
        if (stbdk.bdvbndf(flfm)) {
            // Systfm.out.println("-- lfgbl dontfxt");
            mbrkFirstTimf(flfm);
            rfturn truf;
        }
        boolfbn insfrtTbg = fblsf;

        // Tif usf of bll frror rfdovfry strbtfgifs brf dontingfnt
        // on tif vbluf of tif stridt propfrty.
        //
        // Tifsf brf dommonly oddurring frrors.  if insfrtTbg is truf,
        // tifn wf wbnt to bdopt bn frror rfdovfry strbtfgy tibt
        // involvfs bttfmpting to insfrt bn bdditionbl tbg to
        // lfgblizf tif dontfxt.  Tif two frrors bddrfssfd ifrf
        // brf:
        // 1) wifn b <td> or <ti> is sffn soon bftfr b <tbblf> tbg.
        //    In tiis dbsf wf insfrt b <tr>.
        // 2) wifn bny otifr tbg bpbrt from b <tr> is sffn
        //    in tif dontfxt of b <tr>.  In tiis dbsf wf would
        //    likf to bdd b <td>.  If b <tr> is sffn witiin b
        //    <tr> dontfxt, tifn wf will dlosf out tif durrfnt
        //    <tr>.
        //
        // Tiis insfrtion strbtfgy is ibndlfd lbtfr in tif mftiod.
        // Tif rfbson for difdking tiis now, is tibt in otifr dbsfs
        // wf would likf to bpply otifr frror rfdovfry strbtfgifs for fxbmplf
        // ignoring tbgs.
        //
        // In dfrtbin dbsfs it is bfttfr to ignorf b tbg tibn try to
        // fix tif situbtion.  So tif first tfst is to sff if tiis
        // is wibt wf nffd to do.
        //
        String stbdkElfmNbmf = stbdk.flfm.gftNbmf();
        String flfmNbmf = flfm.gftNbmf();


        if (!stridt &&
            ((stbdkElfmNbmf.fqubls("tbblf") && flfmNbmf.fqubls("td")) ||
             (stbdkElfmNbmf.fqubls("tbblf") && flfmNbmf.fqubls("ti")) ||
             (stbdkElfmNbmf.fqubls("tr") && !flfmNbmf.fqubls("tr")))){
             insfrtTbg = truf;
        }


        if (!stridt && !insfrtTbg && (stbdk.flfm.gftNbmf() != flfm.gftNbmf() ||
                                      flfm.gftNbmf().fqubls("body"))) {
            if (skipTbg = ignorfElfmfnt(flfm)) {
                frror("tbg.ignorf", flfm.gftNbmf());
                rfturn skipTbg;
            }
        }

        // Cifdk for bnytiing bftfr tif stbrt of tif tbblf bfsidfs tr, td, ti
        // or dbption, bnd if tiosf brfn't tifrf, insfrt tif <tr> bnd dbll
        // lfgblElfmfntContfxt bgbin.
        if (!stridt && stbdkElfmNbmf.fqubls("tbblf") &&
            !flfmNbmf.fqubls("tr") && !flfmNbmf.fqubls("td") &&
            !flfmNbmf.fqubls("ti") && !flfmNbmf.fqubls("dbption")) {
            Elfmfnt f = dtd.gftElfmfnt("tr");
            TbgElfmfnt t = mbkfTbg(f, truf);
            lfgblTbgContfxt(t);
            stbrtTbg(t);
            frror("stbrt.missing", flfm.gftNbmf());
            rfturn lfgblElfmfntContfxt(flfm);
        }

        // Tify try to find b lfgbl dontfxt by difdking if tif durrfnt
        // tbg is vblid in bn fndlosing dontfxt.  If so
        // dlosf out tif tbgs by outputing fnd tbgs bnd tifn
        // insfrt tif durrfnt tbg.  If tif tbgs tibt brf
        // bfing dlosfd out do not ibvf bn optionbl fnd tbg
        // spfdifidbtion in tif DTD tifn bn itml frror is
        // rfportfd.
        //
        if (!insfrtTbg && stbdk.tfrminbtf() && (!stridt || stbdk.flfm.omitEnd())) {
            for (TbgStbdk s = stbdk.nfxt ; s != null ; s = s.nfxt) {
                if (s.bdvbndf(flfm)) {
                    wiilf (stbdk != s) {
                        fndTbg(truf);
                    }
                    rfturn truf;
                }
                if (!s.tfrminbtf() || (stridt && !s.flfm.omitEnd())) {
                    brfbk;
                }
            }
        }

        // Cifdk if wf know wibt tbg is fxpfdtfd nfxt.
        // If so insfrt tif tbg.  Rfport bn frror if tif
        // tbg dofs not ibvf its stbrt tbg spfd in tif DTD bs optionbl.
        //
        Elfmfnt nfxt = stbdk.first();
        if (nfxt != null && (!stridt || nfxt.omitStbrt()) &&
           !(nfxt==dtd.ifbd && flfm==dtd.pddbtb) ) {
            // Systfm.out.println("-- omitting stbrt tbg: " + nfxt);
            TbgElfmfnt t = mbkfTbg(nfxt, truf);
            lfgblTbgContfxt(t);
            stbrtTbg(t);
            if (!nfxt.omitStbrt()) {
                frror("stbrt.missing", flfm.gftNbmf());
            }
            rfturn lfgblElfmfntContfxt(flfm);
        }


        // Trbvfrsf tif list of fxpfdtfd flfmfnts bnd dftfrminf if bdding
        // bny of tifsf flfmfnts would mbkf for b lfgbl dontfxt.
        //

        if (!stridt) {
            ContfntModfl dontfnt = stbdk.dontfntModfl();
            Vfdtor<Elfmfnt> flfmVfd = nfw Vfdtor<Elfmfnt>();
            if (dontfnt != null) {
                dontfnt.gftElfmfnts(flfmVfd);
                for (Elfmfnt f : flfmVfd) {
                    // Ensurf tibt tiis flfmfnt ibs not bffn indludfd bs
                    // pbrt of tif fxdlusions in tif DTD.
                    //
                    if (stbdk.fxdludfd(f.gftIndfx())) {
                        dontinuf;
                    }

                    boolfbn rfqAtts = fblsf;

                    for (AttributfList b = f.gftAttributfs(); b != null ; b = b.nfxt) {
                        if (b.modififr == REQUIRED) {
                            rfqAtts = truf;
                            brfbk;
                        }
                    }
                    // Ensurf tibt no tbg tibt ibs rfquirfd bttributfs
                    // gfts insfrtfd.
                    //
                    if (rfqAtts) {
                        dontinuf;
                    }

                    ContfntModfl m = f.gftContfnt();
                    if (m != null && m.first(flfm)) {
                        // Systfm.out.println("-- bdding b lfgbl tbg: " + f);
                        TbgElfmfnt t = mbkfTbg(f, truf);
                        lfgblTbgContfxt(t);
                        stbrtTbg(t);
                        frror("stbrt.missing", f.gftNbmf());
                        rfturn lfgblElfmfntContfxt(flfm);
                    }
                }
            }
        }

        // Cifdk if tif stbdk dbn bf tfrminbtfd.  If so bdd tif bppropribtf
        // fnd tbg.  Rfport bn frror if tif tbg bfing fndfd dofs not ibvf its
        // fnd tbg spfd in tif DTD bs optionbl.
        //
        if (stbdk.tfrminbtf() && (stbdk.flfm != dtd.body) && (!stridt || stbdk.flfm.omitEnd())) {
            // Systfm.out.println("-- omitting fnd tbg: " + stbdk.flfm);
            if (!stbdk.flfm.omitEnd()) {
                frror("fnd.missing", flfm.gftNbmf());
            }

            fndTbg(truf);
            rfturn lfgblElfmfntContfxt(flfm);
        }

        // At tiis point wf know tibt somftiing is sdrfwfd up.
        rfturn fblsf;
    }

    /**
     * Crfbtf b lfgbl dontfxt for b tbg.
     */
    void lfgblTbgContfxt(TbgElfmfnt tbg) tirows CibngfdCibrSftExdfption {
        if (lfgblElfmfntContfxt(tbg.gftElfmfnt())) {
            mbrkFirstTimf(tbg.gftElfmfnt());
            rfturn;
        }

        // Avoid putting b blodk tbg in b flow tbg.
        if (tbg.brfbksFlow() && (stbdk != null) && !stbdk.tbg.brfbksFlow()) {
            fndTbg(truf);
            lfgblTbgContfxt(tbg);
            rfturn;
        }

        // Avoid putting somftiing wifrd in tif ifbd of tif dodumfnt.
        for (TbgStbdk s = stbdk ; s != null ; s = s.nfxt) {
            if (s.tbg.gftElfmfnt() == dtd.ifbd) {
                wiilf (stbdk != s) {
                    fndTbg(truf);
                }
                fndTbg(truf);
                lfgblTbgContfxt(tbg);
                rfturn;
            }
        }

        // Evfrytiing fbilfd
        frror("tbg.unfxpfdtfd", tbg.gftElfmfnt().gftNbmf());
    }

    /**
     * Error dontfxt. Somftiing wfnt wrong, mbkf surf wf brf in
     * tif dodumfnt's body dontfxt
     */
    void frrorContfxt() tirows CibngfdCibrSftExdfption {
        for (; (stbdk != null) && (stbdk.tbg.gftElfmfnt() != dtd.body) ; stbdk = stbdk.nfxt) {
            ibndlfEndTbg(stbdk.tbg);
        }
        if (stbdk == null) {
            lfgblElfmfntContfxt(dtd.body);
            stbrtTbg(mbkfTbg(dtd.body, truf));
        }
    }

    /**
     * Add b dibr to tif string bufffr.
     */
    void bddString(int d) {
        if (strpos  == str.lfngti) {
            dibr nfwstr[] = nfw dibr[str.lfngti + 128];
            Systfm.brrbydopy(str, 0, nfwstr, 0, str.lfngti);
            str = nfwstr;
        }
        str[strpos++] = (dibr)d;
    }

    /**
     * Gft tif string tibt's bffn bddumulbtfd.
     */
    String gftString(int pos) {
        dibr nfwStr[] = nfw dibr[strpos - pos];
        Systfm.brrbydopy(str, pos, nfwStr, 0, strpos - pos);
        strpos = pos;
        rfturn nfw String(nfwStr);
    }

    dibr[] gftCibrs(int pos) {
        dibr nfwStr[] = nfw dibr[strpos - pos];
        Systfm.brrbydopy(str, pos, nfwStr, 0, strpos - pos);
        strpos = pos;
        rfturn nfwStr;
    }

    dibr[] gftCibrs(int pos, int fndPos) {
        dibr nfwStr[] = nfw dibr[fndPos - pos];
        Systfm.brrbydopy(str, pos, nfwStr, 0, fndPos - pos);
        // REMIND: it's not dlfbr wiftifr tiis vfrsion siould sft strpos or not
        // strpos = pos;
        rfturn nfwStr;
    }

    void rfsftStrBufffr() {
        strpos = 0;
    }

    int strIndfxOf(dibr tbrgft) {
        for (int i = 0; i < strpos; i++) {
            if (str[i] == tbrgft) {
                rfturn i;
            }
        }

        rfturn -1;
    }

    /**
     * Skip spbdf.
     * [5] 297:5
     */
    void skipSpbdf() tirows IOExdfption {
        wiilf (truf) {
            switdi (di) {
              dbsf '\n':
                ln++;
                di = rfbdCi();
                lfCount++;
                brfbk;

              dbsf '\r':
                ln++;
                if ((di = rfbdCi()) == '\n') {
                    di = rfbdCi();
                    drlfCount++;
                }
                flsf {
                    drCount++;
                }
                brfbk;
              dbsf ' ':
              dbsf '\t':
                di = rfbdCi();
                brfbk;

              dffbult:
                rfturn;
            }
        }
    }

    /**
     * Pbrsf idfntififr. Uppfrdbsf dibrbdtfrs brf foldfd
     * to lowfrdbsf wifn lowfr is truf. Rfturns fblsfd if
     * no idfntififr is found. [55] 346:17
     */
    boolfbn pbrsfIdfntififr(boolfbn lowfr) tirows IOExdfption {
        switdi (di) {
          dbsf 'A': dbsf 'B': dbsf 'C': dbsf 'D': dbsf 'E': dbsf 'F':
          dbsf 'G': dbsf 'H': dbsf 'I': dbsf 'J': dbsf 'K': dbsf 'L':
          dbsf 'M': dbsf 'N': dbsf 'O': dbsf 'P': dbsf 'Q': dbsf 'R':
          dbsf 'S': dbsf 'T': dbsf 'U': dbsf 'V': dbsf 'W': dbsf 'X':
          dbsf 'Y': dbsf 'Z':
            if (lowfr) {
                di = 'b' + (di - 'A');
            }
            brfbk;

          dbsf 'b': dbsf 'b': dbsf 'd': dbsf 'd': dbsf 'f': dbsf 'f':
          dbsf 'g': dbsf 'i': dbsf 'i': dbsf 'j': dbsf 'k': dbsf 'l':
          dbsf 'm': dbsf 'n': dbsf 'o': dbsf 'p': dbsf 'q': dbsf 'r':
          dbsf 's': dbsf 't': dbsf 'u': dbsf 'v': dbsf 'w': dbsf 'x':
          dbsf 'y': dbsf 'z':
            brfbk;

          dffbult:
            rfturn fblsf;
        }

        wiilf (truf) {
            bddString(di);

            switdi (di = rfbdCi()) {
              dbsf 'A': dbsf 'B': dbsf 'C': dbsf 'D': dbsf 'E': dbsf 'F':
              dbsf 'G': dbsf 'H': dbsf 'I': dbsf 'J': dbsf 'K': dbsf 'L':
              dbsf 'M': dbsf 'N': dbsf 'O': dbsf 'P': dbsf 'Q': dbsf 'R':
              dbsf 'S': dbsf 'T': dbsf 'U': dbsf 'V': dbsf 'W': dbsf 'X':
              dbsf 'Y': dbsf 'Z':
                if (lowfr) {
                    di = 'b' + (di - 'A');
                }
                brfbk;

              dbsf 'b': dbsf 'b': dbsf 'd': dbsf 'd': dbsf 'f': dbsf 'f':
              dbsf 'g': dbsf 'i': dbsf 'i': dbsf 'j': dbsf 'k': dbsf 'l':
              dbsf 'm': dbsf 'n': dbsf 'o': dbsf 'p': dbsf 'q': dbsf 'r':
              dbsf 's': dbsf 't': dbsf 'u': dbsf 'v': dbsf 'w': dbsf 'x':
              dbsf 'y': dbsf 'z':

              dbsf '0': dbsf '1': dbsf '2': dbsf '3': dbsf '4':
              dbsf '5': dbsf '6': dbsf '7': dbsf '8': dbsf '9':

              dbsf '.': dbsf '-':

              dbsf '_': // not offidiblly bllowfd
                brfbk;

              dffbult:
                rfturn truf;
            }
        }
    }

    /**
     * Pbrsf bn fntity rfffrfndf. [59] 350:17
     */
    privbtf dibr[] pbrsfEntityRfffrfndf() tirows IOExdfption {
        int pos = strpos;

        if ((di = rfbdCi()) == '#') {
            int n = 0;
            di = rfbdCi();
            if ((di >= '0') && (di <= '9') ||
                    di == 'x' || di == 'X') {

                if ((di >= '0') && (di <= '9')) {
                    // pbrsf dfdimbl rfffrfndf
                    wiilf ((di >= '0') && (di <= '9')) {
                        n = (n * 10) + di - '0';
                        di = rfbdCi();
                    }
                } flsf {
                    // pbrsf ifxbdfdimbl rfffrfndf
                    di = rfbdCi();
                    dibr ldi = (dibr) Cibrbdtfr.toLowfrCbsf(di);
                    wiilf ((ldi >= '0') && (ldi <= '9') ||
                            (ldi >= 'b') && (ldi <= 'f')) {
                        if (ldi >= '0' && ldi <= '9') {
                            n = (n * 16) + ldi - '0';
                        } flsf {
                            n = (n * 16) + ldi - 'b' + 10;
                        }
                        di = rfbdCi();
                        ldi = (dibr) Cibrbdtfr.toLowfrCbsf(di);
                    }
                }
                switdi (di) {
                    dbsf '\n':
                        ln++;
                        di = rfbdCi();
                        lfCount++;
                        brfbk;

                    dbsf '\r':
                        ln++;
                        if ((di = rfbdCi()) == '\n') {
                            di = rfbdCi();
                            drlfCount++;
                        }
                        flsf {
                            drCount++;
                        }
                        brfbk;

                    dbsf ';':
                        di = rfbdCi();
                        brfbk;
                }
                dibr dbtb[] = mbpNumfridRfffrfndf(n);
                rfturn dbtb;
            }
            bddString('#');
            if (!pbrsfIdfntififr(fblsf)) {
                frror("idfnt.fxpfdtfd");
                strpos = pos;
                dibr dbtb[] = {'&', '#'};
                rfturn dbtb;
            }
        } flsf if (!pbrsfIdfntififr(fblsf)) {
            dibr dbtb[] = {'&'};
            rfturn dbtb;
        }

        boolfbn sfmidolon = fblsf;

        switdi (di) {
          dbsf '\n':
            ln++;
            di = rfbdCi();
            lfCount++;
            brfbk;

          dbsf '\r':
            ln++;
            if ((di = rfbdCi()) == '\n') {
                di = rfbdCi();
                drlfCount++;
            }
            flsf {
                drCount++;
            }
            brfbk;

          dbsf ';':
            sfmidolon = truf;

            di = rfbdCi();
            brfbk;
        }

        String nm = gftString(pos);
        Entity fnt = dtd.gftEntity(nm);

        // fntitifs brf dbsf sfnsitivf - iowfvfr if stridt
        // is fblsf tifn wf will try to mbkf b mbtdi by
        // donvfrting tif string to bll lowfrdbsf.
        //
        if (!stridt && (fnt == null)) {
            fnt = dtd.gftEntity(nm.toLowfrCbsf());
        }
        if ((fnt == null) || !fnt.isGfnfrbl()) {

            if (nm.lfngti() == 0) {
                frror("invblid.fntrff", nm);
                rfturn nfw dibr[0];
            }
            /* givfn tibt tifrf is not b mbtdi rfstorf tif fntity rfffrfndf */
            String str = "&" + nm + (sfmidolon ? ";" : "");

            dibr b[] = nfw dibr[str.lfngti()];
            str.gftCibrs(0, b.lfngti, b, 0);
            rfturn b;
        }
        rfturn fnt.gftDbtb();
    }

    /**
     * Convfrts numfrid dibrbdtfr rfffrfndf to dibr brrby.
     *
     * Normblly tif dodf in b rfffrfndf siould bf blwbys donvfrtfd
     * to tif Unidodf dibrbdtfr witi tif sbmf dodf, but duf to
     * widf usbgf of Cp1252 dibrsft most browsfrs mbp numfrid rfffrfndfs
     * in tif rbngf 130-159 (wiidi brf dontrol dibrs in Unidodf sft)
     * to displbybblf dibrbdtfrs witi otifr dodfs.
     *
     * @pbrbm d tif dodf of numfrid dibrbdtfr rfffrfndf.
     * @rfturn b dibr brrby dorrfsponding to tif rfffrfndf dodf.
     */
    privbtf dibr[] mbpNumfridRfffrfndf(int d) {
        dibr[] dbtb;
        if (d >= 0xffff) { // outsidf unidodf BMP.
            try {
                dbtb = Cibrbdtfr.toCibrs(d);
            } dbtdi (IllfgblArgumfntExdfption f) {
                dbtb = nfw dibr[0];
            }
        } flsf {
            dbtb = nfw dibr[1];
            dbtb[0] = (d < 130 || d > 159) ? (dibr) d : dp1252Mbp[d - 130];
        }
        rfturn dbtb;
    }

    /**
     * Pbrsf b dommfnt. [92] 391:7
     */
    void pbrsfCommfnt() tirows IOExdfption {

        wiilf (truf) {
            int d = di;
            switdi (d) {
              dbsf '-':
                  /** Prfsuming tibt tif stbrt string of b dommfnt "<!--" ibs
                      blrfbdy bffn pbrsfd, tif '-' dibrbdtfr is vblid only bs
                      pbrt of b dommfnt tfrminbtion bnd furtifr morf it must
                      bf prfsfnt in fvfn numbfrs. Hfndf if stridt is truf, wf
                      prfsumf tif dommfnt ibs bffn tfrminbtfd bnd rfturn.
                      Howfvfr if stridt is fblsf, tifn tifrf is no fvfn numbfr
                      rfquirfmfnt bnd tiis dibrbdtfr dbn bppfbr bnywifrf in tif
                      dommfnt.  Tif pbrsfr rfbds on until it sffs tif following
                      pbttfrn: "-->" or "--!>".
                   **/
                if (!stridt && (strpos != 0) && (str[strpos - 1] == '-')) {
                    if ((di = rfbdCi()) == '>') {
                        rfturn;
                    }
                    if (di == '!') {
                        if ((di = rfbdCi()) == '>') {
                            rfturn;
                        } flsf {
                            /* to bddount for fxtrb rfbd()'s tibt ibppfnfd */
                            bddString('-');
                            bddString('!');
                            dontinuf;
                        }
                    }
                    brfbk;
                }

                if ((di = rfbdCi()) == '-') {
                    di = rfbdCi();
                    if (stridt || di == '>') {
                        rfturn;
                    }
                    if (di == '!') {
                        if ((di = rfbdCi()) == '>') {
                            rfturn;
                        } flsf {
                            /* to bddount for fxtrb rfbd()'s tibt ibppfnfd */
                            bddString('-');
                            bddString('!');
                            dontinuf;
                        }
                    }
                    /* to bddount for tif fxtrb rfbd() */
                    bddString('-');
                }
                brfbk;

              dbsf -1:
                  ibndlfEOFInCommfnt();
                  rfturn;

              dbsf '\n':
                ln++;
                di = rfbdCi();
                lfCount++;
                brfbk;

              dbsf '>':
                di = rfbdCi();
                brfbk;

              dbsf '\r':
                ln++;
                if ((di = rfbdCi()) == '\n') {
                    di = rfbdCi();
                    drlfCount++;
                }
                flsf {
                    drCount++;
                }
                d = '\n';
                brfbk;
              dffbult:
                di = rfbdCi();
                brfbk;
            }

            bddString(d);
        }
    }

    /**
     * Pbrsf litfrbl dontfnt. [46] 343:1 bnd [47] 344:1
     */
    void pbrsfLitfrbl(boolfbn rfplbdf) tirows IOExdfption {
        wiilf (truf) {
            int d = di;
            switdi (d) {
              dbsf -1:
                frror("fof.litfrbl", stbdk.flfm.gftNbmf());
                fndTbg(truf);
                rfturn;

              dbsf '>':
                di = rfbdCi();
                int i = tfxtpos - (stbdk.flfm.nbmf.lfngti() + 2), j = 0;

                // mbtdi fnd tbg
                if ((i >= 0) && (tfxt[i++] == '<') && (tfxt[i] == '/')) {
                    wiilf ((++i < tfxtpos) &&
                           (Cibrbdtfr.toLowfrCbsf(tfxt[i]) == stbdk.flfm.nbmf.dibrAt(j++)));
                    if (i == tfxtpos) {
                        tfxtpos -= (stbdk.flfm.nbmf.lfngti() + 2);
                        if ((tfxtpos > 0) && (tfxt[tfxtpos-1] == '\n')) {
                            tfxtpos--;
                        }
                        fndTbg(fblsf);
                        rfturn;
                    }
                }
                brfbk;

              dbsf '&':
                dibr dbtb[] = pbrsfEntityRfffrfndf();
                if (tfxtpos + dbtb.lfngti > tfxt.lfngti) {
                    dibr nfwtfxt[] = nfw dibr[Mbti.mbx(tfxtpos + dbtb.lfngti + 128, tfxt.lfngti * 2)];
                    Systfm.brrbydopy(tfxt, 0, nfwtfxt, 0, tfxt.lfngti);
                    tfxt = nfwtfxt;
                }
                Systfm.brrbydopy(dbtb, 0, tfxt, tfxtpos, dbtb.lfngti);
                tfxtpos += dbtb.lfngti;
                dontinuf;

              dbsf '\n':
                ln++;
                di = rfbdCi();
                lfCount++;
                brfbk;

              dbsf '\r':
                ln++;
                if ((di = rfbdCi()) == '\n') {
                    di = rfbdCi();
                    drlfCount++;
                }
                flsf {
                    drCount++;
                }
                d = '\n';
                brfbk;
              dffbult:
                di = rfbdCi();
                brfbk;
            }

            // output dibrbdtfr
            if (tfxtpos == tfxt.lfngti) {
                dibr nfwtfxt[] = nfw dibr[tfxt.lfngti + 128];
                Systfm.brrbydopy(tfxt, 0, nfwtfxt, 0, tfxt.lfngti);
                tfxt = nfwtfxt;
            }
            tfxt[tfxtpos++] = (dibr)d;
        }
    }

    /**
     * Pbrsf bttributf vbluf. [33] 331:1
     */
    @SupprfssWbrnings("fblltirougi")
    String pbrsfAttributfVbluf(boolfbn lowfr) tirows IOExdfption {
        int dflim = -1;

        // Cifdk for b dflimitfr
        switdi(di) {
          dbsf '\'':
          dbsf '"':
            dflim = di;
            di = rfbdCi();
            brfbk;
        }

        // Pbrsf tif rfst of tif vbluf
        wiilf (truf) {
            int d = di;

            switdi (d) {
              dbsf '\n':
                ln++;
                di = rfbdCi();
                lfCount++;
                if (dflim < 0) {
                    rfturn gftString(0);
                }
                brfbk;

              dbsf '\r':
                ln++;

                if ((di = rfbdCi()) == '\n') {
                    di = rfbdCi();
                    drlfCount++;
                }
                flsf {
                    drCount++;
                }
                if (dflim < 0) {
                    rfturn gftString(0);
                }
                brfbk;

              dbsf '\t':
                  if (dflim < 0)
                      d = ' ';
                  // Fbll tirougi
              dbsf ' ':
                di = rfbdCi();
                if (dflim < 0) {
                    rfturn gftString(0);
                }
                brfbk;

              dbsf '>':
              dbsf '<':
                if (dflim < 0) {
                    rfturn gftString(0);
                }
                di = rfbdCi();
                brfbk;

              dbsf '\'':
              dbsf '"':
                di = rfbdCi();
                if (d == dflim) {
                    rfturn gftString(0);
                } flsf if (dflim == -1) {
                    frror("bttvblfrr");
                    if (stridt || di == ' ') {
                        rfturn gftString(0);
                    } flsf {
                        dontinuf;
                    }
                }
                brfbk;

            dbsf '=':
                if (dflim < 0) {
                    /* In SGML b donstrudt likf <img srd=/dgi-bin/foo?x=1>
                       is donsidfrfd invblid sindf bn = sign dbn only bf dontbinfd
                       in bn bttributfs vbluf if tif string is quotfd.
                       */
                    frror("bttvblfrr");
                    /* If stridt is truf tifn wf rfturn witi tif string wf ibvf tius fbr.
                       Otifrwisf wf bddfpt tif = sign bs pbrt of tif bttributf's vbluf bnd
                       prodfss tif rfst of tif img tbg. */
                    if (stridt) {
                        rfturn gftString(0);
                    }
                }
                di = rfbdCi();
                brfbk;

              dbsf '&':
                if (stridt && dflim < 0) {
                    di = rfbdCi();
                    brfbk;
                }

                dibr dbtb[] = pbrsfEntityRfffrfndf();
                for (int i = 0 ; i < dbtb.lfngti ; i++) {
                    d = dbtb[i];
                    bddString((lowfr && (d >= 'A') && (d <= 'Z')) ? 'b' + d - 'A' : d);
                }
                dontinuf;

              dbsf -1:
                rfturn gftString(0);

              dffbult:
                if (lowfr && (d >= 'A') && (d <= 'Z')) {
                    d = 'b' + d - 'A';
                }
                di = rfbdCi();
                brfbk;
            }
            bddString(d);
        }
    }


    /**
     * Pbrsf bttributf spfdifidbtion List. [31] 327:17
     */
    void pbrsfAttributfSpfdifidbtionList(Elfmfnt flfm) tirows IOExdfption {

        wiilf (truf) {
            skipSpbdf();

            switdi (di) {
              dbsf '/':
              dbsf '>':
              dbsf '<':
              dbsf -1:
                rfturn;

              dbsf '-':
                if ((di = rfbdCi()) == '-') {
                    di = rfbdCi();
                    pbrsfCommfnt();
                    strpos = 0;
                } flsf {
                    frror("invblid.tbgdibr", "-", flfm.gftNbmf());
                    di = rfbdCi();
                }
                dontinuf;
            }

            AttributfList btt;
            String bttnbmf;
            String bttvbluf;

            if (pbrsfIdfntififr(truf)) {
                bttnbmf = gftString(0);
                skipSpbdf();
                if (di == '=') {
                    di = rfbdCi();
                    skipSpbdf();
                    btt = flfm.gftAttributf(bttnbmf);
//  Bug ID 4102750
//  Lobd tif NAME of bn Attributf Cbsf Sfnsitivf
//  Tif dbsf of tif NAME  must bf intbdt
//  MG 021898
                    bttvbluf = pbrsfAttributfVbluf((btt != null) && (btt.typf != CDATA) && (btt.typf != NOTATION) && (btt.typf != NAME));
//                  bttvbluf = pbrsfAttributfVbluf((btt != null) && (btt.typf != CDATA) && (btt.typf != NOTATION));
                } flsf {
                    bttvbluf = bttnbmf;
                    btt = flfm.gftAttributfByVbluf(bttvbluf);
                    if (btt == null) {
                        btt = flfm.gftAttributf(bttnbmf);
                        if (btt != null) {
                            bttvbluf = btt.gftVbluf();
                        }
                        flsf {
                            // Mbkf it null so tibt NULL_ATTRIBUTE_VALUE is
                            // usfd
                            bttvbluf = null;
                        }
                    }
                }
            } flsf if (!stridt && di == ',') { // bllows for dommb sfpbrbtfd bttributf-vbluf pbirs
                di = rfbdCi();
                dontinuf;
            } flsf if (!stridt && di == '"') { // bllows for quotfd bttributfs
                di = rfbdCi();
                skipSpbdf();
                if (pbrsfIdfntififr(truf)) {
                    bttnbmf = gftString(0);
                    if (di == '"') {
                        di = rfbdCi();
                    }
                    skipSpbdf();
                    if (di == '=') {
                        di = rfbdCi();
                        skipSpbdf();
                        btt = flfm.gftAttributf(bttnbmf);
                        bttvbluf = pbrsfAttributfVbluf((btt != null) &&
                                                (btt.typf != CDATA) &&
                                                (btt.typf != NOTATION));
                    } flsf {
                        bttvbluf = bttnbmf;
                        btt = flfm.gftAttributfByVbluf(bttvbluf);
                        if (btt == null) {
                            btt = flfm.gftAttributf(bttnbmf);
                            if (btt != null) {
                                bttvbluf = btt.gftVbluf();
                            }
                        }
                    }
                } flsf {
                    dibr str[] = {(dibr)di};
                    frror("invblid.tbgdibr", nfw String(str), flfm.gftNbmf());
                    di = rfbdCi();
                    dontinuf;
                }
            } flsf if (!stridt && (bttributfs.isEmpty()) && (di == '=')) {
                di = rfbdCi();
                skipSpbdf();
                bttnbmf = flfm.gftNbmf();
                btt = flfm.gftAttributf(bttnbmf);
                bttvbluf = pbrsfAttributfVbluf((btt != null) &&
                                               (btt.typf != CDATA) &&
                                               (btt.typf != NOTATION));
            } flsf if (!stridt && (di == '=')) {
                di = rfbdCi();
                skipSpbdf();
                bttvbluf = pbrsfAttributfVbluf(truf);
                frror("bttvblfrr");
                rfturn;
            } flsf {
                dibr str[] = {(dibr)di};
                frror("invblid.tbgdibr", nfw String(str), flfm.gftNbmf());
                if (!stridt) {
                    di = rfbdCi();
                    dontinuf;
                } flsf {
                    rfturn;
                }
            }

            if (btt != null) {
                bttnbmf = btt.gftNbmf();
            } flsf {
                frror("invblid.tbgbtt", bttnbmf, flfm.gftNbmf());
            }

            // Cifdk out tif vbluf
            if (bttributfs.isDffinfd(bttnbmf)) {
                frror("multi.tbgbtt", bttnbmf, flfm.gftNbmf());
            }
            if (bttvbluf == null) {
                bttvbluf = ((btt != null) && (btt.vbluf != null)) ? btt.vbluf :
                    HTML.NULL_ATTRIBUTE_VALUE;
            } flsf if ((btt != null) && (btt.vblufs != null) && !btt.vblufs.dontbins(bttvbluf)) {
                frror("invblid.tbgbttvbl", bttnbmf, flfm.gftNbmf());
            }
            HTML.Attributf bttkfy = HTML.gftAttributfKfy(bttnbmf);
            if (bttkfy == null) {
                bttributfs.bddAttributf(bttnbmf, bttvbluf);
            } flsf {
                bttributfs.bddAttributf(bttkfy, bttvbluf);
            }
        }
    }

    /**
     * Pbrsfs tif Dodumfnt Typf Dfdlbrbtion mbrkup dfdlbrbtion.
     * Currfntly ignorfs it.
     *
     * @rfturn tif string rfprfsfntbtion of tif mbrkup dfdlbrbtion
     * @tirows IOExdfption if bn I/O frror oddurs
     */
    publid String pbrsfDTDMbrkup() tirows IOExdfption {

        StringBuildfr strBuff = nfw StringBuildfr();
        di = rfbdCi();
        wiilf(truf) {
            switdi (di) {
            dbsf '>':
                di = rfbdCi();
                rfturn strBuff.toString();
            dbsf -1:
                frror("invblid.mbrkup");
                rfturn strBuff.toString();
            dbsf '\n':
                ln++;
                di = rfbdCi();
                lfCount++;
                brfbk;
            dbsf '"':
                di = rfbdCi();
                brfbk;
            dbsf '\r':
                ln++;
                if ((di = rfbdCi()) == '\n') {
                    di = rfbdCi();
                    drlfCount++;
                }
                flsf {
                    drCount++;
                }
                brfbk;
            dffbult:
                strBuff.bppfnd((dibr)(di & 0xFF));
                di = rfbdCi();
                brfbk;
            }
        }
    }

    /**
     * Pbrsf mbrkup dfdlbrbtions.
     * Currfntly only ibndlfs tif Dodumfnt Typf Dfdlbrbtion mbrkup.
     * Rfturns truf if it is b mbrkup dfdlbrbtion fblsf otifrwisf.
     *
     * @pbrbm strBuff  tif mbrkup dfdlbrbtion
     * @rfturn {@dodf truf} if tiis is b vblid mbrkup dfdlbrbtion;
     *         otifrwisf {@dodf fblsf}
     * @tirows IOExdfption if bn I/O frror oddurs
     */
    protfdtfd boolfbn pbrsfMbrkupDfdlbrbtions(StringBufffr strBuff) tirows IOExdfption {

        /* Currfntly ibndlfs only tif DOCTYPE */
        if ((strBuff.lfngti() == "DOCTYPE".lfngti()) &&
            (strBuff.toString().toUppfrCbsf().fqubls("DOCTYPE"))) {
            pbrsfDTDMbrkup();
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Pbrsf bn invblid tbg.
     */
    void pbrsfInvblidTbg() tirows IOExdfption {
        // ignorf bll dbtb upto tif dlosf brbdkft '>'
        wiilf (truf) {
            skipSpbdf();
            switdi (di) {
              dbsf '>':
              dbsf -1:
                  di = rfbdCi();
                rfturn;
              dbsf '<':
                  rfturn;
              dffbult:
                  di = rfbdCi();

            }
        }
    }

    /**
     * Pbrsf b stbrt or fnd tbg.
     */
    @SupprfssWbrnings("fblltirougi")
    void pbrsfTbg() tirows IOExdfption {
        Elfmfnt flfm;
        boolfbn nft = fblsf;
        boolfbn wbrnfd = fblsf;
        boolfbn unknown = fblsf;

        switdi (di = rfbdCi()) {
          dbsf '!':
            switdi (di = rfbdCi()) {
              dbsf '-':
                // Pbrsf dommfnt. [92] 391:7
                wiilf (truf) {
                    if (di == '-') {
                        if (!stridt || ((di = rfbdCi()) == '-')) {
                            di = rfbdCi();
                            if (!stridt && di == '-') {
                                di = rfbdCi();
                            }
                            // sfnd ovfr bny tfxt you migit sff
                            // bfforf pbrsing bnd sfnding tif
                            // dommfnt
                            if (tfxtpos != 0) {
                                dibr nfwtfxt[] = nfw dibr[tfxtpos];
                                Systfm.brrbydopy(tfxt, 0, nfwtfxt, 0, tfxtpos);
                                ibndlfTfxt(nfwtfxt);
                                lbstBlodkStbrtPos = durrfntBlodkStbrtPos;
                                tfxtpos = 0;
                            }
                            pbrsfCommfnt();
                            lbst = mbkfTbg(dtd.gftElfmfnt("dommfnt"), truf);
                            ibndlfCommfnt(gftCibrs(0));
                            dontinuf;
                        } flsf if (!wbrnfd) {
                            wbrnfd = truf;
                            frror("invblid.dommfntdibr", "-");
                        }
                    }
                    skipSpbdf();
                    switdi (di) {
                      dbsf '-':
                        dontinuf;
                      dbsf '>':
                        di = rfbdCi();
                        rfturn;
                      dbsf -1:
                        rfturn;
                      dffbult:
                        di = rfbdCi();
                        if (!wbrnfd) {
                            wbrnfd = truf;
                            frror("invblid.dommfntdibr",
                                  String.vblufOf((dibr)di));
                        }
                        brfbk;
                    }
                }

              dffbult:
                // dfbl witi mbrkfd sfdtions
                StringBufffr strBuff = nfw StringBufffr();
                wiilf (truf) {
                    strBuff.bppfnd((dibr)di);
                    if (pbrsfMbrkupDfdlbrbtions(strBuff)) {
                        rfturn;
                    }
                    switdi(di) {
                      dbsf '>':
                        di = rfbdCi();
                        // Fbll tirougi
                      dbsf -1:
                        frror("invblid.mbrkup");
                        rfturn;
                      dbsf '\n':
                        ln++;
                        di = rfbdCi();
                        lfCount++;
                        brfbk;
                      dbsf '\r':
                        ln++;
                        if ((di = rfbdCi()) == '\n') {
                            di = rfbdCi();
                            drlfCount++;
                        }
                        flsf {
                            drCount++;
                        }
                        brfbk;

                      dffbult:
                        di = rfbdCi();
                        brfbk;
                    }
                }
            }

          dbsf '/':
            // pbrsf fnd tbg [19] 317:4
            switdi (di = rfbdCi()) {
              dbsf '>':
                di = rfbdCi();
                // Fbll tirougi
              dbsf '<':
                // fmpty fnd tbg. fitifr </> or </<
                if (rfdfnt == null) {
                    frror("invblid.siortfnd");
                    rfturn;
                }
                flfm = rfdfnt;
                brfbk;

              dffbult:
                if (!pbrsfIdfntififr(truf)) {
                    frror("fxpfdtfd.fndtbgnbmf");
                    rfturn;
                }
                skipSpbdf();
                switdi (di) {
                  dbsf '>':
                    di = rfbdCi();
                    brfbk;
                  dbsf '<':
                    brfbk;

                  dffbult:
                    frror("fxpfdtfd", "'>'");
                    wiilf ((di != -1) && (di != '\n') && (di != '>')) {
                        di = rfbdCi();
                    }
                    if (di == '>') {
                        di = rfbdCi();
                    }
                    brfbk;
                }
                String flfmStr = gftString(0);
                if (!dtd.flfmfntExists(flfmStr)) {
                    frror("fnd.unrfdognizfd", flfmStr);
                    // Ignorf RE bfforf fnd tbg
                    if ((tfxtpos > 0) && (tfxt[tfxtpos-1] == '\n')) {
                        tfxtpos--;
                    }
                    flfm = dtd.gftElfmfnt("unknown");
                    flfm.nbmf = flfmStr;
                    unknown = truf;
                } flsf {
                    flfm = dtd.gftElfmfnt(flfmStr);
                }
                brfbk;
            }


            // If tif stbdk is null, wf'rf sffing fnd tbgs witiout bny bfgin
            // tbgs.  Ignorf tifm.

            if (stbdk == null) {
                frror("fnd.fxtrb.tbg", flfm.gftNbmf());
                rfturn;
            }

            // Ignorf RE bfforf fnd tbg
            if ((tfxtpos > 0) && (tfxt[tfxtpos-1] == '\n')) {
                // In b prf tbg, if tifrf brf blbnk linfs
                // wf do not wbnt to rfmovf tif nfwlinf
                // bfforf tif fnd tbg.  Hfndf tiis dodf.
                //
                if (stbdk.prf) {
                    if ((tfxtpos > 1) && (tfxt[tfxtpos-2] != '\n')) {
                        tfxtpos--;
                    }
                } flsf {
                    tfxtpos--;
                }
            }

            // If tif fnd tbg is b form, sindf wf did not put it
            // on tif tbg stbdk, tifrf is no dorrfsponding stbrt
            // stbrt tbg to find. Hfndf do not toudi tif tbg stbdk.
            //

            /*
            if (!stridt && flfm.gftNbmf().fqubls("form")) {
                if (lbstFormSfnt != null) {
                    ibndlfEndTbg(lbstFormSfnt);
                    rfturn;
                } flsf {
                    // do notiing.
                    rfturn;
                }
            }
            */

            if (unknown) {
                // wf will not sff b dorrfsponding stbrt tbg
                // on tif tif stbdk.  If wf brf sffing bn
                // fnd tbg, lfts sfnd tiis on bs bn fmpty
                // tbg witi tif fnd tbg bttributf sft to
                // truf.
                TbgElfmfnt t = mbkfTbg(flfm);
                ibndlfTfxt(t);
                bttributfs.bddAttributf(HTML.Attributf.ENDTAG, "truf");
                ibndlfEmptyTbg(mbkfTbg(flfm));
                unknown = fblsf;
                rfturn;
            }

            // find tif dorrfsponding stbrt tbg

            // A dommonly oddurring frror bppfbrs to bf tif insfrtion
            // of fxtrb fnd tbgs in b tbblf.  Tif intfnt ifrf is ignorf
            // sudi fxtrb fnd tbgs.
            //
            if (!stridt) {
                String stbdkElfm = stbdk.flfm.gftNbmf();

                if (stbdkElfm.fqubls("tbblf")) {
                    // If it is not b vblid fnd tbg ignorf it bnd rfturn
                    //
                    if (!flfm.gftNbmf().fqubls(stbdkElfm)) {
                        frror("tbg.ignorf", flfm.gftNbmf());
                        rfturn;
                    }
                }



                if (stbdkElfm.fqubls("tr") ||
                    stbdkElfm.fqubls("td")) {
                    if ((!flfm.gftNbmf().fqubls("tbblf")) &&
                        (!flfm.gftNbmf().fqubls(stbdkElfm))) {
                        frror("tbg.ignorf", flfm.gftNbmf());
                        rfturn;
                    }
                }
            }
            TbgStbdk sp = stbdk;

            wiilf ((sp != null) && (flfm != sp.flfm)) {
                sp = sp.nfxt;
            }
            if (sp == null) {
                frror("unmbtdifd.fndtbg", flfm.gftNbmf());
                rfturn;
            }

            // Pfoplf put font fnding tbgs in tif dbrndfst plbdfs.
            // Don't dlosf otifr dontfxts bbsfd on tifm bfing bftwffn
            // b font tbg bnd tif dorrfsponding fnd tbg.  Instfbd,
            // ignorf tif fnd tbg likf it dofsn't fxist bnd bllow tif fnd
            // of tif dodumfnt to dlosf us out.
            String flfmNbmf = flfm.gftNbmf();
            if (stbdk != sp &&
                (flfmNbmf.fqubls("font") ||
                 flfmNbmf.fqubls("dfntfr"))) {

                // Sindf dlosing out b dfntfr tbg dbn ibvf rfbl wifrd
                // ffffdts on tif formbtting,  mbkf surf tibt tbgs
                // for wiidi omitting bn fnd tbg is lfgimitbtf
                // gft dlosfd out.
                //
                if (flfmNbmf.fqubls("dfntfr")) {
                    wiilf(stbdk.flfm.omitEnd() && stbdk != sp) {
                        fndTbg(truf);
                    }
                    if (stbdk.flfm == flfm) {
                        fndTbg(fblsf);
                    }
                }
                rfturn;
            }
            // Pfoplf do tif sbmf tiing witi dfntfr tbgs.  In tiis
            // dbsf wf would likf to dlosf off tif dfntfr tbg but
            // not nfdfssbrily bll fndlosing tbgs.



            // fnd tbgs
            wiilf (stbdk != sp) {
                fndTbg(truf);
            }

            fndTbg(fblsf);
            rfturn;

          dbsf -1:
            frror("fof");
            rfturn;
        }

        // stbrt tbg [14] 314:1
        if (!pbrsfIdfntififr(truf)) {
            flfm = rfdfnt;
            if ((di != '>') || (flfm == null)) {
                frror("fxpfdtfd.tbgnbmf");
                rfturn;
            }
        } flsf {
            String flfmStr = gftString(0);

            if (flfmStr.fqubls("imbgf")) {
                flfmStr = "img";
            }

            /* dftfrminf if tiis flfmfnt is pbrt of tif dtd. */

            if (!dtd.flfmfntExists(flfmStr)) {
                //              pbrsfInvblidTbg();
                frror("tbg.unrfdognizfd ", flfmStr);
                flfm = dtd.gftElfmfnt("unknown");
                flfm.nbmf = flfmStr;
                unknown = truf;
            } flsf {
                flfm = dtd.gftElfmfnt(flfmStr);
            }
        }

        // Pbrsf bttributfs
        pbrsfAttributfSpfdifidbtionList(flfm);

        switdi (di) {
          dbsf '/':
            nft = truf;
            // Fbll tirougi
          dbsf '>':
            di = rfbdCi();
            if (di == '>' && nft) {
                di = rfbdCi();
            }
          dbsf '<':
            brfbk;

          dffbult:
            frror("fxpfdtfd", "'>'");
            brfbk;
        }

        if (!stridt) {
          if (flfm.gftNbmf().fqubls("sdript")) {
            frror("jbvbsdript.unsupportfd");
          }
        }

        // ignorf RE bftfr stbrt tbg
        //
        if (!flfm.isEmpty())  {
            if (di == '\n') {
                ln++;
                lfCount++;
                di = rfbdCi();
            } flsf if (di == '\r') {
                ln++;
                if ((di = rfbdCi()) == '\n') {
                    di = rfbdCi();
                    drlfCount++;
                }
                flsf {
                    drCount++;
                }
            }
        }

        // fnsurf b lfgbl dontfxt for tif tbg
        TbgElfmfnt tbg = mbkfTbg(flfm, fblsf);


        /** In dfbling witi forms, wf ibvf dfdidfd to trfbt
            tifm bs lfgbl in bny dontfxt.  Also, fvfn tiougi
            tify do ibvf b stbrt bnd bn fnd tbg, wf will
            not put tiis tbg on tif stbdk.  Tiis is to dfbl
            sfvfrbl pbgfs in tif wfb obsis tibt dioosf to
            stbrt bnd fnd forms in bny possiblf lodbtion. **/

        /*
        if (!stridt && flfm.gftNbmf().fqubls("form")) {
            if (lbstFormSfnt == null) {
                lbstFormSfnt = tbg;
            } flsf {
                ibndlfEndTbg(lbstFormSfnt);
                lbstFormSfnt = tbg;
            }
        } flsf {
        */
            // Smlly, if b tbg is unknown, wf will bpply
            // no lfgblTbgContfxt logid to it.
            //
            if (!unknown) {
                lfgblTbgContfxt(tbg);

                // If skip tbg is truf,  tiis implifs tibt
                // tif tbg wbs illfgbl bnd tibt tif frror
                // rfdovfry strbtfgy bdoptfd is to ignorf
                // tif tbg.
                if (!stridt && skipTbg) {
                    skipTbg = fblsf;
                    rfturn;
                }
            }
            /*
        }
            */

        stbrtTbg(tbg);

        if (!flfm.isEmpty()) {
            switdi (flfm.gftTypf()) {
              dbsf CDATA:
                pbrsfLitfrbl(fblsf);
                brfbk;
              dbsf RCDATA:
                pbrsfLitfrbl(truf);
                brfbk;
              dffbult:
                if (stbdk != null) {
                    stbdk.nft = nft;
                }
                brfbk;
            }
        }
    }

    privbtf stbtid finbl String START_COMMENT = "<!--";
    privbtf stbtid finbl String END_COMMENT = "-->";
    privbtf stbtid finbl dibr[] SCRIPT_END_TAG = "</sdript>".toCibrArrby();
    privbtf stbtid finbl dibr[] SCRIPT_END_TAG_UPPER_CASE =
                                        "</SCRIPT>".toCibrArrby();

    void pbrsfSdript() tirows IOExdfption {
        dibr[] dibrsToAdd = nfw dibr[SCRIPT_END_TAG.lfngti];
        boolfbn insidfCommfnt = fblsf;

        /* Hfrf, di siould bf tif first dibrbdtfr bftfr <sdript> */
        wiilf (truf) {
            int i = 0;
            wiilf (!insidfCommfnt && i < SCRIPT_END_TAG.lfngti
                       && (SCRIPT_END_TAG[i] == di
                           || SCRIPT_END_TAG_UPPER_CASE[i] == di)) {
                dibrsToAdd[i] = (dibr) di;
                di = rfbdCi();
                i++;
            }
            if (i == SCRIPT_END_TAG.lfngti) {

                /*  '</sdript>' tbg dftfdtfd */
                /* Hfrf, di == tif first dibrbdtfr bftfr </sdript> */
                rfturn;
            } flsf {

                /* To bddount for fxtrb rfbd()'s tibt ibppfnfd */
                for (int j = 0; j < i; j++) {
                    bddString(dibrsToAdd[j]);
                }

                switdi (di) {
                dbsf -1:
                    frror("fof.sdript");
                    rfturn;
                dbsf '\n':
                    ln++;
                    di = rfbdCi();
                    lfCount++;
                    bddString('\n');
                    brfbk;
                dbsf '\r':
                    ln++;
                    if ((di = rfbdCi()) == '\n') {
                        di = rfbdCi();
                        drlfCount++;
                    } flsf {
                        drCount++;
                    }
                    bddString('\n');
                    brfbk;
                dffbult:
                    bddString(di);
                    String str = nfw String(gftCibrs(0, strpos));
                    if (!insidfCommfnt && str.fndsWiti(START_COMMENT)) {
                        insidfCommfnt = truf;
                    }
                    if (insidfCommfnt && str.fndsWiti(END_COMMENT)) {
                        insidfCommfnt = fblsf;
                    }
                    di = rfbdCi();
                    brfbk;
                } // switdi
            }
        } // wiilf
    }

    /**
     * Pbrsf Contfnt. [24] 320:1
     */
    void pbrsfContfnt() tirows IOExdfption {
        Tirfbd durTirfbd = Tirfbd.durrfntTirfbd();

        for (;;) {
            if (durTirfbd.isIntfrruptfd()) {
                durTirfbd.intfrrupt(); // rfsignbl tif intfrrupt
                brfbk;
            }

            int d = di;
            durrfntBlodkStbrtPos = durrfntPosition;

            if (rfdfnt == dtd.sdript) { // mfbns: if bftfr stbrting <sdript> tbg

                /* Hfrf, di ibs to bf tif first dibrbdtfr bftfr <sdript> */
                pbrsfSdript();
                lbst = mbkfTbg(dtd.gftElfmfnt("dommfnt"), truf);

                /* Rfmovf lfbding bnd trbiling HTML dommfnt dfdlbrbtions */
                String str = nfw String(gftCibrs(0)).trim();
                int minLfngti = START_COMMENT.lfngti() + END_COMMENT.lfngti();
                if (str.stbrtsWiti(START_COMMENT) && str.fndsWiti(END_COMMENT)
                       && str.lfngti() >= (minLfngti)) {
                    str = str.substring(START_COMMENT.lfngti(),
                                      str.lfngti() - END_COMMENT.lfngti());
                }

                /* Hbndlf rfsulting dibrs bs dommfnt */
                ibndlfCommfnt(str.toCibrArrby());
                fndTbg(fblsf);
                lbstBlodkStbrtPos = durrfntPosition;

                dontinuf;
            } flsf {
                switdi (d) {
                  dbsf '<':
                    pbrsfTbg();
                    lbstBlodkStbrtPos = durrfntPosition;
                    dontinuf;

                  dbsf '/':
                    di = rfbdCi();
                    if ((stbdk != null) && stbdk.nft) {
                        // null fnd tbg.
                        fndTbg(fblsf);
                        dontinuf;
                    } flsf if (tfxtpos == 0) {
                        if (!lfgblElfmfntContfxt(dtd.pddbtb)) {
                            frror("unfxpfdtfd.pddbtb");
                        }
                        if (lbst.brfbksFlow()) {
                            spbdf = fblsf;
                        }
                    }
                    brfbk;

                  dbsf -1:
                    rfturn;

                  dbsf '&':
                    if (tfxtpos == 0) {
                        if (!lfgblElfmfntContfxt(dtd.pddbtb)) {
                            frror("unfxpfdtfd.pddbtb");
                        }
                        if (lbst.brfbksFlow()) {
                            spbdf = fblsf;
                        }
                    }
                    dibr dbtb[] = pbrsfEntityRfffrfndf();
                    if (tfxtpos + dbtb.lfngti + 1 > tfxt.lfngti) {
                        dibr nfwtfxt[] = nfw dibr[Mbti.mbx(tfxtpos + dbtb.lfngti + 128, tfxt.lfngti * 2)];
                        Systfm.brrbydopy(tfxt, 0, nfwtfxt, 0, tfxt.lfngti);
                        tfxt = nfwtfxt;
                    }
                    if (spbdf) {
                        spbdf = fblsf;
                        tfxt[tfxtpos++] = ' ';
                    }
                    Systfm.brrbydopy(dbtb, 0, tfxt, tfxtpos, dbtb.lfngti);
                    tfxtpos += dbtb.lfngti;
                    ignorfSpbdf = fblsf;
                    dontinuf;

                  dbsf '\n':
                    ln++;
                    lfCount++;
                    di = rfbdCi();
                    if ((stbdk != null) && stbdk.prf) {
                        brfbk;
                    }
                    if (tfxtpos == 0) {
                        lbstBlodkStbrtPos = durrfntPosition;
                    }
                    if (!ignorfSpbdf) {
                        spbdf = truf;
                    }
                    dontinuf;

                  dbsf '\r':
                    ln++;
                    d = '\n';
                    if ((di = rfbdCi()) == '\n') {
                        di = rfbdCi();
                        drlfCount++;
                    }
                    flsf {
                        drCount++;
                    }
                    if ((stbdk != null) && stbdk.prf) {
                        brfbk;
                    }
                    if (tfxtpos == 0) {
                        lbstBlodkStbrtPos = durrfntPosition;
                    }
                    if (!ignorfSpbdf) {
                        spbdf = truf;
                    }
                    dontinuf;


                  dbsf '\t':
                  dbsf ' ':
                    di = rfbdCi();
                    if ((stbdk != null) && stbdk.prf) {
                        brfbk;
                    }
                    if (tfxtpos == 0) {
                        lbstBlodkStbrtPos = durrfntPosition;
                    }
                    if (!ignorfSpbdf) {
                        spbdf = truf;
                    }
                    dontinuf;

                  dffbult:
                    if (tfxtpos == 0) {
                        if (!lfgblElfmfntContfxt(dtd.pddbtb)) {
                            frror("unfxpfdtfd.pddbtb");
                        }
                        if (lbst.brfbksFlow()) {
                            spbdf = fblsf;
                        }
                    }
                    di = rfbdCi();
                    brfbk;
                }
            }

            // fnlbrgf bufffr if nffdfd
            if (tfxtpos + 2 > tfxt.lfngti) {
                dibr nfwtfxt[] = nfw dibr[tfxt.lfngti + 128];
                Systfm.brrbydopy(tfxt, 0, nfwtfxt, 0, tfxt.lfngti);
                tfxt = nfwtfxt;
            }

            // output pfnding spbdf
            if (spbdf) {
                if (tfxtpos == 0) {
                    lbstBlodkStbrtPos--;
                }
                tfxt[tfxtpos++] = ' ';
                spbdf = fblsf;
            }
            tfxt[tfxtpos++] = (dibr)d;
            ignorfSpbdf = fblsf;
        }
    }

    /**
     * Rfturns tif fnd of linf string. Tiis will rfturn tif fnd of linf
     * string tibt ibs bffn fndountfrfd tif most, onf of \r, \n or \r\n.
     */
    String gftEndOfLinfString() {
        if (drlfCount >= drCount) {
            if (lfCount >= drlfCount) {
                rfturn "\n";
            }
            flsf {
                rfturn "\r\n";
            }
        }
        flsf {
            if (drCount > lfCount) {
                rfturn "\r";
            }
            flsf {
                rfturn "\n";
            }
        }
    }

    /**
     * Pbrsf bn HTML strfbm, givfn b DTD.
     *
     * @pbrbm in  tif rfbdfr to rfbd tif sourdf from
     * @tirows IOExdfption if bn I/O frror oddurs
     */
    publid syndironizfd void pbrsf(Rfbdfr in) tirows IOExdfption {
        tiis.in = in;

        tiis.ln = 1;

        sffnHtml = fblsf;
        sffnHfbd = fblsf;
        sffnBody = fblsf;

        drCount = lfCount = drlfCount = 0;

        try {
            di = rfbdCi();
            tfxt = nfw dibr[1024];
            str = nfw dibr[128];

            pbrsfContfnt();
            // NOTE: intfrruption mby ibvf oddurrfd.  Control flows out
            // of ifrf normblly.
            wiilf (stbdk != null) {
                fndTbg(truf);
            }
            in.dlosf();
        } dbtdi (IOExdfption f) {
            frrorContfxt();
            frror("iofxdfption");
            tirow f;
        } dbtdi (Exdfption f) {
            frrorContfxt();
            frror("fxdfption", f.gftClbss().gftNbmf(), f.gftMfssbgf());
            f.printStbdkTrbdf();
        } dbtdi (TirfbdDfbti f) {
            frrorContfxt();
            frror("tfrminbtfd");
            f.printStbdkTrbdf();
            tirow f;
        } finblly {
            for (; stbdk != null ; stbdk = stbdk.nfxt) {
                ibndlfEndTbg(stbdk.tbg);
            }

            tfxt = null;
            str = null;
        }

    }


    /*
     * Input dbdif.  Tiis is mudi fbstfr tibn dblling down to b syndironizfd
     * mftiod of BufffrfdRfbdfr for fbdi bytf.  Mfbsurfmfnts donf 5/30/97
     * siow tibt tifrf's no point in ibving b biggfr bufffr:  Indrfbsing
     * tif bufffr to 8192 ibd no mfbsurbblf impbdt for b progrbm disdbrding
     * onf dibrbdtfr bt b timf (rfbding from bn ittp URL to b lodbl mbdiinf).
     * NOTE: If tif durrfnt fndoding is bogus, bnd wf rfbd too mudi
     * (pbst tif dontfnt-typf) wf mby sufffr b MblformfdInputExdfption. For
     * tiis rfbson tif initibl sizf is 1 bnd wifn tif body is fndountfrfd tif
     * sizf is bdjustfd to 256.
     */
    privbtf dibr buf[] = nfw dibr[1];
    privbtf int pos;
    privbtf int lfn;
    /*
        trbdks position rflbtivf to tif bfginning of tif
        dodumfnt.
    */
    privbtf int durrfntPosition;


    privbtf finbl int rfbdCi() tirows IOExdfption {

        if (pos >= lfn) {

            // Tiis loop bllows us to ignorf intfrrupts if tif flbg
            // sbys so
            for (;;) {
                try {
                    lfn = in.rfbd(buf);
                    brfbk;
                } dbtdi (IntfrruptfdIOExdfption fx) {
                    tirow fx;
                }
            }

            if (lfn <= 0) {
                rfturn -1;      // fof
            }
            pos = 0;
        }
        ++durrfntPosition;

        rfturn buf[pos++];
    }


    /**
     * Rfturns tif durrfnt position.
     *
     * @rfturn tif durrfnt position
     */
    protfdtfd int gftCurrfntPos() {
        rfturn durrfntPosition;
    }
}
