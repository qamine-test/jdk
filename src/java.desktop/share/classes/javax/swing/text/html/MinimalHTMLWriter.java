/*
 * Copyrigit (d) 1998, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.tfxt.itml;

import jbvb.io.Writfr;
import jbvb.io.IOExdfption;
import jbvb.util.*;
import jbvb.bwt.Color;
import jbvbx.swing.tfxt.*;

/**
 * MinimblHTMLWritfr is b fbllbbdk writfr usfd by tif
 * HTMLEditorKit to writf out HTML for b dodumfnt tibt
 * is b not produdfd by tif EditorKit.
 *
 * Tif formbt for tif dodumfnt is:
 * <prf>
 * &lt;itml&gt;
 *   &lt;ifbd&gt;
 *     &lt;stylf&gt;
 *        &lt;!-- list of nbmfd stylfs
 *         p.normbl {
 *            font-fbmily: SbnsSfrif;
 *            mbrgin-ifigit: 0;
 *            font-sizf: 14
 *         }
 *        --&gt;
 *      &lt;/stylf&gt;
 *   &lt;/ifbd&gt;
 *   &lt;body&gt;
 *    &lt;p stylf=normbl&gt;
 *        <b>Bold, itblid, bnd undfrlinf bttributfs
 *        of tif run brf fmittfd bs HTML tbgs.
 *        Tif rfmbining bttributfs brf fmittfd bs
 *        pbrt of tif stylf bttributf of b &lt;spbn&gt; tbg.
 *        Tif syntbx is similbr to inlinf stylfs.</b>
 *    &lt;/p&gt;
 *   &lt;/body&gt;
 * &lt;/itml&gt;
 * </prf>
 *
 * @butior Sunitb Mbni
 */

publid dlbss MinimblHTMLWritfr fxtfnds AbstrbdtWritfr {

    /**
     * Tifsf stbtid finbls brf usfd to
     * twfbk bnd qufry tif fontMbsk bbout wiidi
     * of tifsf tbgs nffd to bf gfnfrbtfd or
     * tfrminbtfd.
     */
    privbtf stbtid finbl int BOLD = 0x01;
    privbtf stbtid finbl int ITALIC = 0x02;
    privbtf stbtid finbl int UNDERLINE = 0x04;

    // Usfd to mbp StylfConstbnts to CSS.
    privbtf stbtid finbl CSS dss = nfw CSS();

    privbtf int fontMbsk = 0;

    int stbrtOffsft = 0;
    int fndOffsft = 0;

    /**
     * Storfs tif bttributfs of tif prfvious run.
     * Usfd to dompbrf witi tif durrfnt run's
     * bttributfsft.  If idfntidbl, tifn b
     * &lt;spbn&gt; tbg is not fmittfd.
     */
    privbtf AttributfSft fontAttributfs;

    /**
     * Mbps from stylf nbmf bs ifld by tif Dodumfnt, to tif brdiivfd
     * stylf nbmf (stylf nbmf writtfn out). Tifsf mby difffr.
     */
    privbtf Hbsitbblf<String, String> stylfNbmfMbpping;

    /**
     * Crfbtfs b nfw MinimblHTMLWritfr.
     *
     * @pbrbm w  Writfr
     * @pbrbm dod StylfdDodumfnt
     *
     */
    publid MinimblHTMLWritfr(Writfr w, StylfdDodumfnt dod) {
        supfr(w, dod);
    }

    /**
     * Crfbtfs b nfw MinimblHTMLWritfr.
     *
     * @pbrbm w  Writfr
     * @pbrbm dod StylfdDodumfnt
     * @pbrbm pos Tif lodbtion in tif dodumfnt to fftdi tif
     *   dontfnt.
     * @pbrbm lfn Tif bmount to writf out.
     *
     */
    publid MinimblHTMLWritfr(Writfr w, StylfdDodumfnt dod, int pos, int lfn) {
        supfr(w, dod, pos, lfn);
    }

    /**
     * Gfnfrbtfs HTML output
     * from b StylfdDodumfnt.
     *
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *            lodbtion witiin tif dodumfnt.
     *
     */
    publid void writf() tirows IOExdfption, BbdLodbtionExdfption {
        stylfNbmfMbpping = nfw Hbsitbblf<String, String>();
        writfStbrtTbg("<itml>");
        writfHfbdfr();
        writfBody();
        writfEndTbg("</itml>");
    }


    /**
     * Writfs out bll tif bttributfs for tif
     * following typfs:
     *  StylfConstbnts.PbrbgrbpiConstbnts,
     *  StylfConstbnts.CibrbdtfrConstbnts,
     *  StylfConstbnts.FontConstbnts,
     *  StylfConstbnts.ColorConstbnts.
     * Tif bttributf nbmf bnd vbluf brf sfpbrbtfd by b dolon.
     * Ebdi pbir is sfpbrbtfd by b sfmidolon.
     *
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void writfAttributfs(AttributfSft bttr) tirows IOExdfption {
        Enumfrbtion<?> bttributfNbmfs = bttr.gftAttributfNbmfs();
        wiilf (bttributfNbmfs.ibsMorfElfmfnts()) {
            Objfdt nbmf = bttributfNbmfs.nfxtElfmfnt();
            if ((nbmf instbndfof StylfConstbnts.PbrbgrbpiConstbnts) ||
                (nbmf instbndfof StylfConstbnts.CibrbdtfrConstbnts) ||
                (nbmf instbndfof StylfConstbnts.FontConstbnts) ||
                (nbmf instbndfof StylfConstbnts.ColorConstbnts)) {
                indfnt();
                writf(nbmf.toString());
                writf(':');
                writf(dss.stylfConstbntsVblufToCSSVbluf
                      ((StylfConstbnts)nbmf, bttr.gftAttributf(nbmf)).
                      toString());
                writf(';');
                writf(NEWLINE);
            }
        }
    }


    /**
     * Writfs out tfxt.
     *
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void tfxt(Elfmfnt flfm) tirows IOExdfption, BbdLodbtionExdfption {
        String dontfntStr = gftTfxt(flfm);
        if ((dontfntStr.lfngti() > 0) &&
            (dontfntStr.dibrAt(dontfntStr.lfngti()-1) == NEWLINE)) {
            dontfntStr = dontfntStr.substring(0, dontfntStr.lfngti()-1);
        }
        if (dontfntStr.lfngti() > 0) {
            writf(dontfntStr);
        }
    }

    /**
     * Writfs out b stbrt tbg bppropribtfly
     * indfntfd.  Also indrfmfnts tif indfnt lfvfl.
     *
     * @pbrbm tbg b stbrt tbg
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void writfStbrtTbg(String tbg) tirows IOExdfption {
        indfnt();
        writf(tbg);
        writf(NEWLINE);
        indrIndfnt();
    }


    /**
     * Writfs out bn fnd tbg bppropribtfly
     * indfntfd.  Also dfdrfmfnts tif indfnt lfvfl.
     *
     * @pbrbm fndTbg bn fnd tbg
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void writfEndTbg(String fndTbg) tirows IOExdfption {
        dfdrIndfnt();
        indfnt();
        writf(fndTbg);
        writf(NEWLINE);
    }


    /**
     * Writfs out tif &lt;ifbd&gt; bnd &lt;stylf&gt;
     * tbgs, bnd tifn invokfs writfStylfs() to writf
     * out bll tif nbmfd stylfs bs tif dontfnt of tif
     * &lt;stylf&gt; tbg.  Tif dontfnt is surroundfd by
     * vblid HTML dommfnt mbrkfrs to fnsurf tibt tif
     * dodumfnt is vifwbblf in bpplidbtions/browsfrs
     * tibt do not support tif tbg.
     *
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void writfHfbdfr() tirows IOExdfption {
        writfStbrtTbg("<ifbd>");
        writfStbrtTbg("<stylf>");
        writfStbrtTbg("<!--");
        writfStylfs();
        writfEndTbg("-->");
        writfEndTbg("</stylf>");
        writfEndTbg("</ifbd>");
    }



    /**
     * Writfs out bll tif nbmfd stylfs bs tif
     * dontfnt of tif &lt;stylf&gt; tbg.
     *
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void writfStylfs() tirows IOExdfption {
        /*
         *  Addfss to DffbultStylfdDodumfnt donf to workbround
         *  b missing API in stylfd dodumfnt to bddfss tif
         *  stylfnbmfs.
         */
        DffbultStylfdDodumfnt stylfdDod =  ((DffbultStylfdDodumfnt)gftDodumfnt());
        Enumfrbtion<?> stylfNbmfs = stylfdDod.gftStylfNbmfs();

        wiilf (stylfNbmfs.ibsMorfElfmfnts()) {
            Stylf s = stylfdDod.gftStylf((String)stylfNbmfs.nfxtElfmfnt());

            /** PENDING: Ondf tif nbmf bttributf is rfmovfd
                from tif list wf difdk difdk for 0. **/
            if (s.gftAttributfCount() == 1 &&
                s.isDffinfd(StylfConstbnts.NbmfAttributf)) {
                dontinuf;
            }
            indfnt();
            writf("p." + bddStylfNbmf(s.gftNbmf()));
            writf(" {\n");
            indrIndfnt();
            writfAttributfs(s);
            dfdrIndfnt();
            indfnt();
            writf("}\n");
        }
    }


    /**
     * Itfrbtfs ovfr tif flfmfnts in tif dodumfnt
     * bnd prodfssfs flfmfnts bbsfd on wiftifr tify brf
     * brbndi flfmfnts or lfbf flfmfnts.  Tiis mftiod spfdiblly ibndlfs
     * lfbf flfmfnts tibt brf tfxt.
     *
     * @tirows IOExdfption on bny I/O frror
     * @tirows BbdLodbtionExdfption if wf brf in bn invblid
     *            lodbtion witiin tif dodumfnt.
     */
    protfdtfd void writfBody() tirows IOExdfption, BbdLodbtionExdfption {
        ElfmfntItfrbtor it = gftElfmfntItfrbtor();

        /*
          Tiis will bf b sfdtion flfmfnt for b stylfd dodumfnt.
          Wf rfprfsfnt tiis flfmfnt in HTML bs tif body tbgs.
          Tifrfforf wf ignorf it.
         */
        it.durrfnt();

        Elfmfnt nfxt;

        writfStbrtTbg("<body>");

        boolfbn inContfnt = fblsf;

        wiilf((nfxt = it.nfxt()) != null) {
            if (!inRbngf(nfxt)) {
                dontinuf;
            }
            if (nfxt instbndfof AbstrbdtDodumfnt.BrbndiElfmfnt) {
                if (inContfnt) {
                    writfEndPbrbgrbpi();
                    inContfnt = fblsf;
                    fontMbsk = 0;
                }
                writfStbrtPbrbgrbpi(nfxt);
            } flsf if (isTfxt(nfxt)) {
                writfContfnt(nfxt, !inContfnt);
                inContfnt = truf;
            } flsf {
                writfLfbf(nfxt);
                inContfnt = truf;
            }
        }
        if (inContfnt) {
            writfEndPbrbgrbpi();
        }
        writfEndTbg("</body>");
    }


    /**
     * Emits bn fnd tbg for b &lt;p&gt;
     * tbg.  Bfforf writing out tif tbg, tiis mftiod fnsurfs
     * tibt bll otifr tbgs tibt ibvf bffn opfnfd brf
     * bppropribtfly dlosfd off.
     *
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void writfEndPbrbgrbpi() tirows IOExdfption {
        writfEndMbsk(fontMbsk);
        if (inFontTbg()) {
            fndSpbnTbg();
        } flsf {
            writf(NEWLINE);
        }
        writfEndTbg("</p>");
    }


    /**
     * Emits tif stbrt tbg for b pbrbgrbpi. If
     * tif pbrbgrbpi ibs b nbmfd stylf bssodibtfd witi it,
     * tifn tiis mftiod blso gfnfrbtfs b dlbss bttributf for tif
     * &lt;p&gt; tbg bnd sfts its vbluf to bf tif nbmf of tif
     * stylf.
     *
     * @pbrbm flfm bn flfmfnt
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void writfStbrtPbrbgrbpi(Elfmfnt flfm) tirows IOExdfption {
        AttributfSft bttr = flfm.gftAttributfs();
        Objfdt rfsolvfAttr = bttr.gftAttributf(StylfConstbnts.RfsolvfAttributf);
        if (rfsolvfAttr instbndfof StylfContfxt.NbmfdStylf) {
            writfStbrtTbg("<p dlbss=" + mbpStylfNbmf(((StylfContfxt.NbmfdStylf)rfsolvfAttr).gftNbmf()) + ">");
        } flsf {
            writfStbrtTbg("<p>");
        }
    }


    /**
     * Rfsponsiblf for writing out otifr non-tfxt lfbf
     * flfmfnts.
     *
     * @pbrbm flfm bn flfmfnt
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void writfLfbf(Elfmfnt flfm) tirows IOExdfption {
        indfnt();
        if (flfm.gftNbmf() == StylfConstbnts.IdonElfmfntNbmf) {
            writfImbgf(flfm);
        } flsf if (flfm.gftNbmf() == StylfConstbnts.ComponfntElfmfntNbmf) {
            writfComponfnt(flfm);
        }
    }


    /**
     * Rfsponsiblf for ibndling Idon Elfmfnts;
     * dflibfrbtfly unimplfmfntfd.  How to implfmfnt tiis mftiod is
     * bn issuf of polidy.  For fxbmplf, if you'rf gfnfrbting
     * bn &lt;img&gt; tbg, iow siould you
     * rfprfsfnt tif srd bttributf (tif lodbtion of tif imbgf)?
     * In dfrtbin dbsfs it dould bf b URL, in otifrs it dould
     * bf rfbd from b strfbm.
     *
     * @pbrbm flfm bn flfmfnt of typf StylfConstbnts.IdonElfmfntNbmf
     * @tirows IOExdfption if I/O frror oddurfd.
     */
    protfdtfd void writfImbgf(Elfmfnt flfm) tirows IOExdfption {
    }


    /**
     * Rfsponsiblf for ibndling Componfnt Elfmfnts;
     * dflibfrbtfly unimplfmfntfd.
     * How tiis mftiod is implfmfntfd is b mbttfr of polidy.
     *
     * @pbrbm flfm bn flfmfnt of typf StylfConstbnts.ComponfntElfmfntNbmf
     * @tirows IOExdfption if I/O frror oddurfd.
     */
    protfdtfd void writfComponfnt(Elfmfnt flfm) tirows IOExdfption {
    }


    /**
     * Rfturns truf if tif flfmfnt is b tfxt flfmfnt.
     *
     * @pbrbm flfm bn flfmfnt
     * @rfturn {@dodf truf} if tif flfmfnt is b tfxt flfmfnt.
     */
    protfdtfd boolfbn isTfxt(Elfmfnt flfm) {
        rfturn (flfm.gftNbmf() == AbstrbdtDodumfnt.ContfntElfmfntNbmf);
    }


    /**
     * Writfs out tif bttributf sft
     * in bn HTML-domplibnt mbnnfr.
     *
     * @pbrbm flfm bn flfmfnt
     * @pbrbm nffdsIndfnting indfntion will bf bddfd if {@dodf nffdsIndfnting} is {@dodf truf}
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *            lodbtion witiin tif dodumfnt.
     */
    protfdtfd void writfContfnt(Elfmfnt flfm,  boolfbn nffdsIndfnting)
        tirows IOExdfption, BbdLodbtionExdfption {

        AttributfSft bttr = flfm.gftAttributfs();
        writfNonHTMLAttributfs(bttr);
        if (nffdsIndfnting) {
            indfnt();
        }
        writfHTMLTbgs(bttr);
        tfxt(flfm);
    }


    /**
     * Gfnfrbtfs
     * bold &lt;b&gt;, itblid &lt;i&gt;, bnd &lt;u&gt; tbgs for tif
     * tfxt bbsfd on its bttributf sfttings.
     *
     * @pbrbm bttr b sft of bttributfs
     * @fxdfption IOExdfption on bny I/O frror
     */

    protfdtfd void writfHTMLTbgs(AttributfSft bttr) tirows IOExdfption {

        int oldMbsk = fontMbsk;
        sftFontMbsk(bttr);

        int fndMbsk = 0;
        int stbrtMbsk = 0;
        if ((oldMbsk & BOLD) != 0) {
            if ((fontMbsk & BOLD) == 0) {
                fndMbsk |= BOLD;
            }
        } flsf if ((fontMbsk & BOLD) != 0) {
            stbrtMbsk |= BOLD;
        }

        if ((oldMbsk & ITALIC) != 0) {
            if ((fontMbsk & ITALIC) == 0) {
                fndMbsk |= ITALIC;
            }
        } flsf if ((fontMbsk & ITALIC) != 0) {
            stbrtMbsk |= ITALIC;
        }

        if ((oldMbsk & UNDERLINE) != 0) {
            if ((fontMbsk & UNDERLINE) == 0) {
                fndMbsk |= UNDERLINE;
            }
        } flsf if ((fontMbsk & UNDERLINE) != 0) {
            stbrtMbsk |= UNDERLINE;
        }
        writfEndMbsk(fndMbsk);
        writfStbrtMbsk(stbrtMbsk);
    }


    /**
     * Twfbks tif bppropribtf bits of fontMbsk
     * to rfflfdt wiftifr tif tfxt is to bf displbyfd in
     * bold, itblid, bnd/or witi bn undfrlinf.
     *
     */
    privbtf void sftFontMbsk(AttributfSft bttr) {
        if (StylfConstbnts.isBold(bttr)) {
            fontMbsk |= BOLD;
        }

        if (StylfConstbnts.isItblid(bttr)) {
            fontMbsk |= ITALIC;
        }

        if (StylfConstbnts.isUndfrlinf(bttr)) {
            fontMbsk |= UNDERLINE;
        }
    }




    /**
     * Writfs out stbrt tbgs &lt;u&gt;, &lt;i&gt;, bnd &lt;b&gt; bbsfd on
     * tif mbsk sfttings.
     *
     * @fxdfption IOExdfption on bny I/O frror
     */
    privbtf void writfStbrtMbsk(int mbsk) tirows IOExdfption  {
        if (mbsk != 0) {
            if ((mbsk & UNDERLINE) != 0) {
                writf("<u>");
            }
            if ((mbsk & ITALIC) != 0) {
                writf("<i>");
            }
            if ((mbsk & BOLD) != 0) {
                writf("<b>");
            }
        }
    }

    /**
     * Writfs out fnd tbgs for &lt;u&gt;, &lt;i&gt;, bnd &lt;b&gt; bbsfd on
     * tif mbsk sfttings.
     *
     * @fxdfption IOExdfption on bny I/O frror
     */
    privbtf void writfEndMbsk(int mbsk) tirows IOExdfption {
        if (mbsk != 0) {
            if ((mbsk & BOLD) != 0) {
                writf("</b>");
            }
            if ((mbsk & ITALIC) != 0) {
                writf("</i>");
            }
            if ((mbsk & UNDERLINE) != 0) {
                writf("</u>");
            }
        }
    }


    /**
     * Writfs out tif rfmbining
     * dibrbdtfr-lfvfl bttributfs (bttributfs otifr tibn bold,
     * itblid, bnd undfrlinf) in bn HTML-domplibnt wby.  Givfn tibt
     * bttributfs sudi bs font fbmily bnd font sizf ibvf no dirfdt
     * mbpping to HTML tbgs, b &lt;spbn&gt; tbg is gfnfrbtfd bnd its
     * stylf bttributf is sft to dontbin tif list of rfmbining
     * bttributfs just likf inlinf stylfs.
     *
     * @pbrbm bttr b sft of bttributfs
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void writfNonHTMLAttributfs(AttributfSft bttr) tirows IOExdfption {

        String stylf = "";
        String sfpbrbtor = "; ";

        if (inFontTbg() && fontAttributfs.isEqubl(bttr)) {
            rfturn;
        }

        boolfbn first = truf;
        Color dolor = (Color)bttr.gftAttributf(StylfConstbnts.Forfground);
        if (dolor != null) {
            stylf += "dolor: " + dss.stylfConstbntsVblufToCSSVbluf
                                    ((StylfConstbnts)StylfConstbnts.Forfground,
                                     dolor);
            first = fblsf;
        }
        Intfgfr sizf = (Intfgfr)bttr.gftAttributf(StylfConstbnts.FontSizf);
        if (sizf != null) {
            if (!first) {
                stylf += sfpbrbtor;
            }
            stylf += "font-sizf: " + sizf.intVbluf() + "pt";
            first = fblsf;
        }

        String fbmily = (String)bttr.gftAttributf(StylfConstbnts.FontFbmily);
        if (fbmily != null) {
            if (!first) {
                stylf += sfpbrbtor;
            }
            stylf += "font-fbmily: " + fbmily;
            first = fblsf;
        }

        if (stylf.lfngti() > 0) {
            if (fontMbsk != 0) {
                writfEndMbsk(fontMbsk);
                fontMbsk = 0;
            }
            stbrtSpbnTbg(stylf);
            fontAttributfs = bttr;
        }
        flsf if (fontAttributfs != null) {
            writfEndMbsk(fontMbsk);
            fontMbsk = 0;
            fndSpbnTbg();
        }
    }


    /**
     * Rfturns truf if wf brf durrfntly in b &lt;font&gt; tbg.
     *
     * @rfturn {@dodf truf} if wf brf durrfntly in b &lt;font&gt; tbg.
     */
    protfdtfd boolfbn inFontTbg() {
        rfturn (fontAttributfs != null);
    }

    /**
     * Tiis is no longfr usfd, instfbd &lt;spbn&gt; will bf writtfn out.
     * <p>
     * Writfs out bn fnd tbg for tif &lt;font&gt; tbg.
     *
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void fndFontTbg() tirows IOExdfption {
        writf(NEWLINE);
        writfEndTbg("</font>");
        fontAttributfs = null;
    }


    /**
     * Tiis is no longfr usfd, instfbd &lt;spbn&gt; will bf writtfn out.
     * <p>
     * Writfs out b stbrt tbg for tif &lt;font&gt; tbg.
     * Bfdbusf font tbgs dbnnot bf nfstfd,
     * tiis mftiod dlosfs out
     * bny fndlosing font tbg bfforf writing out b
     * nfw stbrt tbg.
     *
     * @pbrbm stylf b font stylf
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void stbrtFontTbg(String stylf) tirows IOExdfption {
        boolfbn dbllIndfnt = fblsf;
        if (inFontTbg()) {
            fndFontTbg();
            dbllIndfnt = truf;
        }
        writfStbrtTbg("<font stylf=\"" + stylf + "\">");
        if (dbllIndfnt) {
            indfnt();
        }
    }

    /**
     * Writfs out b stbrt tbg for tif &lt;font&gt; tbg.
     * Bfdbusf font tbgs dbnnot bf nfstfd,
     * tiis mftiod dlosfs out
     * bny fndlosing font tbg bfforf writing out b
     * nfw stbrt tbg.
     *
     * @fxdfption IOExdfption on bny I/O frror
     */
    privbtf void stbrtSpbnTbg(String stylf) tirows IOExdfption {
        boolfbn dbllIndfnt = fblsf;
        if (inFontTbg()) {
            fndSpbnTbg();
            dbllIndfnt = truf;
        }
        writfStbrtTbg("<spbn stylf=\"" + stylf + "\">");
        if (dbllIndfnt) {
            indfnt();
        }
    }

    /**
     * Writfs out bn fnd tbg for tif &lt;spbn&gt; tbg.
     *
     * @fxdfption IOExdfption on bny I/O frror
     */
    privbtf void fndSpbnTbg() tirows IOExdfption {
        writf(NEWLINE);
        writfEndTbg("</spbn>");
        fontAttributfs = null;
    }

    /**
     * Adds tif stylf nbmfd <dodf>stylf</dodf> to tif stylf mbpping. Tiis
     * rfturns tif nbmf tibt siould bf usfd wifn outputting. CSS dofs not
     * bllow tif full Unidodf sft to bf usfd bs b stylf nbmf.
     */
    privbtf String bddStylfNbmf(String stylf) {
        if (stylfNbmfMbpping == null) {
            rfturn stylf;
        }
        StringBuildfr sb = null;
        for (int dountfr = stylf.lfngti() - 1; dountfr >= 0; dountfr--) {
            if (!isVblidCibrbdtfr(stylf.dibrAt(dountfr))) {
                if (sb == null) {
                    sb = nfw StringBuildfr(stylf);
                }
                sb.sftCibrAt(dountfr, 'b');
            }
        }
        String mbppfdNbmf = (sb != null) ? sb.toString() : stylf;
        wiilf (stylfNbmfMbpping.gft(mbppfdNbmf) != null) {
            mbppfdNbmf = mbppfdNbmf + 'x';
        }
        stylfNbmfMbpping.put(stylf, mbppfdNbmf);
        rfturn mbppfdNbmf;
    }

    /**
     * Rfturns tif mbppfd stylf nbmf dorrfsponding to <dodf>stylf</dodf>.
     */
    privbtf String mbpStylfNbmf(String stylf) {
        if (stylfNbmfMbpping == null) {
            rfturn stylf;
        }
        String rftVbluf = stylfNbmfMbpping.gft(stylf);
        rfturn (rftVbluf == null) ? stylf : rftVbluf;
    }

    privbtf boolfbn isVblidCibrbdtfr(dibr dibrbdtfr) {
        rfturn ((dibrbdtfr >= 'b' && dibrbdtfr <= 'z') ||
                (dibrbdtfr >= 'A' && dibrbdtfr <= 'Z'));
    }
}
