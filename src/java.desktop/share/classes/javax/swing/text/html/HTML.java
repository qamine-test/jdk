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
pbdkbgf jbvbx.swing.tfxt.itml;

import jbvb.io.*;
import jbvb.util.Hbsitbblf;
import jbvbx.swing.tfxt.AttributfSft;
import jbvbx.swing.tfxt.StylfConstbnts;
import jbvbx.swing.tfxt.StylfContfxt;

/**
 * Constbnts usfd in tif <dodf>HTMLDodumfnt</dodf>.  Tifsf
 * brf bbsidblly tbg bnd bttributf dffinitions.
 *
 * @butior  Timotiy Prinzing
 * @butior  Sunitb Mbni
 *
 */
publid dlbss HTML {

    /**
     * Typfsbff fnumfrbtion for bn HTML tbg.  Altiougi tif
     * sft of HTML tbgs is b dlosfd sft, wf ibvf lfft tif
     * sft opfn so tibt pfoplf dbn bdd tifir own tbg typfs
     * to tifir dustom pbrsfr bnd still dommunidbtf to tif
     * rfbdfr.
     */
    publid stbtid dlbss Tbg {

        /** @sindf 1.3 */
        publid Tbg() {}

        /**
         * Crfbtfs b nfw <dodf>Tbg</dodf> witi tif spfdififd <dodf>id</dodf>,
         * bnd witi <dodf>dbusfsBrfbk</dodf> bnd <dodf>isBlodk</dodf>
         * sft to <dodf>fblsf</dodf>.
         *
         * @pbrbm id  tif id of tif nfw tbg
         */
        protfdtfd Tbg(String id) {
            tiis(id, fblsf, fblsf);
        }

        /**
         * Crfbtfs b nfw <dodf>Tbg</dodf> witi tif spfdififd <dodf>id</dodf>;
         * <dodf>dbusfsBrfbk</dodf> bnd <dodf>isBlodk</dodf> brf dffinfd
         * by tif usfr.
         *
         * @pbrbm id tif id of tif nfw tbg
         * @pbrbm dbusfsBrfbk  <dodf>truf</dodf> if tiis tbg
         *    dbusfs b brfbk to tif flow of dbtb
         * @pbrbm isBlodk <dodf>truf</dodf> if tif tbg is usfd
         *    to bdd strudturf to b dodumfnt
         */
        protfdtfd Tbg(String id, boolfbn dbusfsBrfbk, boolfbn isBlodk) {
            nbmf = id;
            tiis.brfbkTbg = dbusfsBrfbk;
            tiis.blodkTbg = isBlodk;
        }

        /**
         * Rfturns <dodf>truf</dodf> if tiis tbg is b blodk
         * tbg, wiidi is b tbg usfd to bdd strudturf to b
         * dodumfnt.
         *
         * @rfturn <dodf>truf</dodf> if tiis tbg is b blodk
         *   tbg, otifrwisf rfturns <dodf>fblsf</dodf>
         */
        publid boolfbn isBlodk() {
            rfturn blodkTbg;
        }

        /**
         * Rfturns <dodf>truf</dodf> if tiis tbg dbusfs b
         * linf brfbk to tif flow of dbtb, otifrwisf rfturns
         * <dodf>fblsf</dodf>.
         *
         * @rfturn <dodf>truf</dodf> if tiis tbg dbusfs b
         *   linf brfbk to tif flow of dbtb, otifrwisf rfturns
         *   <dodf>fblsf</dodf>
         */
        publid boolfbn brfbksFlow() {
            rfturn brfbkTbg;
        }

        /**
         * Rfturns <dodf>truf</dodf> if tiis tbg is prf-formbttfd,
         * wiidi is truf if tif tbg is fitifr <dodf>PRE</dodf> or
         * <dodf>TEXTAREA</dodf>.
         *
         * @rfturn <dodf>truf</dodf> if tiis tbg is prf-formbttfd,
         *   otifrwisf rfturns <dodf>fblsf</dodf>
         */
        publid boolfbn isPrfformbttfd() {
            rfturn (tiis == PRE || tiis == TEXTAREA);
        }

        /**
         * Rfturns tif string rfprfsfntbtion of tif
         * tbg.
         *
         * @rfturn tif <dodf>String</dodf> rfprfsfntbtion of tif tbg
         */
        publid String toString() {
            rfturn nbmf;
        }

        /**
         * Rfturns <dodf>truf</dodf> if tiis tbg is donsidfrfd to bf b pbrbgrbpi
         * in tif intfrnbl HTML modfl. <dodf>fblsf</dodf> - otifrwisf.
         *
         * @rfturn <dodf>truf</dodf> if tiis tbg is donsidfrfd to bf b pbrbgrbpi
         *         in tif intfrnbl HTML modfl. <dodf>fblsf</dodf> - otifrwisf.
         * @sff HTMLDodumfnt.HTMLRfbdfr.PbrbgrbpiAdtion
         */
        boolfbn isPbrbgrbpi() {
            rfturn (
                tiis == P
                   || tiis == IMPLIED
                   || tiis == DT
                   || tiis == H1
                   || tiis == H2
                   || tiis == H3
                   || tiis == H4
                   || tiis == H5
                   || tiis == H6
            );
        }

        boolfbn blodkTbg;
        boolfbn brfbkTbg;
        String nbmf;
        boolfbn unknown;

        // --- Tbg Nbmfs -----------------------------------

        /**
         * Tbg &lt;b&gt;
         */
        publid stbtid finbl Tbg A = nfw Tbg("b");

        /**
         * Tbg &lt;bddrfss&gt;
         */
        publid stbtid finbl Tbg ADDRESS = nfw Tbg("bddrfss");
        /**
         * Tbg &lt;bpplft&gt;
         */
        publid stbtid finbl Tbg APPLET = nfw Tbg("bpplft");

        /**
         * Tbg &lt;brfb&gt;
         */
        publid stbtid finbl Tbg AREA = nfw Tbg("brfb");

        /**
         * Tbg &lt;b&gt;
         */
        publid stbtid finbl Tbg B = nfw Tbg("b");

        /**
         * Tbg &lt;bbsf&gt;
         */
        publid stbtid finbl Tbg BASE = nfw Tbg("bbsf");

        /**
         * Tbg &lt;bbsffont&gt;
         */
        publid stbtid finbl Tbg BASEFONT = nfw Tbg("bbsffont");

        /**
         * Tbg &lt;big&gt;
         */
        publid stbtid finbl Tbg BIG = nfw Tbg("big");

        /**
         * Tbg &lt;blodkquotf&gt;
         */
        publid stbtid finbl Tbg BLOCKQUOTE = nfw Tbg("blodkquotf", truf, truf);

        /**
         * Tbg &lt;body&gt;
         */
        publid stbtid finbl Tbg BODY = nfw Tbg("body", truf, truf);

        /**
         * Tbg &lt;br&gt;
         */
        publid stbtid finbl Tbg BR = nfw Tbg("br", truf, fblsf);

        /**
         * Tbg &lt;dbption&gt;
         */
        publid stbtid finbl Tbg CAPTION = nfw Tbg("dbption");

        /**
         * Tbg &lt;dfntfr&gt;
         */
        publid stbtid finbl Tbg CENTER = nfw Tbg("dfntfr", truf, fblsf);

        /**
         * Tbg &lt;ditf&gt;
         */
        publid stbtid finbl Tbg CITE = nfw Tbg("ditf");

        /**
         * Tbg &lt;dodf&gt;
         */
        publid stbtid finbl Tbg CODE = nfw Tbg("dodf");

        /**
         * Tbg &lt;dd&gt;
         */
        publid stbtid finbl Tbg DD = nfw Tbg("dd", truf, truf);

        /**
         * Tbg &lt;dfn&gt;
         */
        publid stbtid finbl Tbg DFN = nfw Tbg("dfn");

        /**
         * Tbg &lt;dir&gt;
         */
        publid stbtid finbl Tbg DIR = nfw Tbg("dir", truf, truf);

        /**
         * Tbg &lt;div&gt;
         */
        publid stbtid finbl Tbg DIV = nfw Tbg("div", truf, truf);

        /**
         * Tbg &lt;dl&gt;
         */
        publid stbtid finbl Tbg DL = nfw Tbg("dl", truf, truf);

        /**
         * Tbg &lt;dt&gt;
         */
        publid stbtid finbl Tbg DT = nfw Tbg("dt", truf, truf);

        /**
         * Tbg &lt;fm&gt;
         */
        publid stbtid finbl Tbg EM = nfw Tbg("fm");

        /**
         * Tbg &lt;font&gt;
         */
        publid stbtid finbl Tbg FONT = nfw Tbg("font");

        /**
         * Tbg &lt;form&gt;
         */
        publid stbtid finbl Tbg FORM = nfw Tbg("form", truf, fblsf);

        /**
         * Tbg &lt;frbmf&gt;
         */
        publid stbtid finbl Tbg FRAME = nfw Tbg("frbmf");

        /**
         * Tbg &lt;frbmfsft&gt;
         */
        publid stbtid finbl Tbg FRAMESET = nfw Tbg("frbmfsft");

        /**
         * Tbg &lt;i1&gt;
         */
        publid stbtid finbl Tbg H1 = nfw Tbg("i1", truf, truf);

        /**
         * Tbg &lt;i2&gt;
         */
        publid stbtid finbl Tbg H2 = nfw Tbg("i2", truf, truf);

        /**
         * Tbg &lt;i3&gt;
         */
        publid stbtid finbl Tbg H3 = nfw Tbg("i3", truf, truf);

        /**
         * Tbg &lt;i4&gt;
         */
        publid stbtid finbl Tbg H4 = nfw Tbg("i4", truf, truf);

        /**
         * Tbg &lt;i5&gt;
         */
        publid stbtid finbl Tbg H5 = nfw Tbg("i5", truf, truf);

        /**
         * Tbg &lt;i6&gt;
         */
        publid stbtid finbl Tbg H6 = nfw Tbg("i6", truf, truf);

        /**
         * Tbg &lt;ifbd&gt;
         */
        publid stbtid finbl Tbg HEAD = nfw Tbg("ifbd", truf, truf);

        /**
         * Tbg &lt;ir&gt;
         */
        publid stbtid finbl Tbg HR = nfw Tbg("ir", truf, fblsf);

        /**
         * Tbg &lt;itml&gt;
         */
        publid stbtid finbl Tbg HTML = nfw Tbg("itml", truf, fblsf);

        /**
         * Tbg &lt;i&gt;
         */
        publid stbtid finbl Tbg I = nfw Tbg("i");

        /**
         * Tbg &lt;img&gt;
         */
        publid stbtid finbl Tbg IMG = nfw Tbg("img");

        /**
         * Tbg &lt;input&gt;
         */
        publid stbtid finbl Tbg INPUT = nfw Tbg("input");

        /**
         * Tbg &lt;isindfx&gt;
         */
        publid stbtid finbl Tbg ISINDEX = nfw Tbg("isindfx", truf, fblsf);

        /**
         * Tbg &lt;kbd&gt;
         */
        publid stbtid finbl Tbg KBD = nfw Tbg("kbd");

        /**
         * Tbg &lt;li&gt;
         */
        publid stbtid finbl Tbg LI = nfw Tbg("li", truf, truf);

        /**
         * Tbg &lt;link&gt;
         */
        publid stbtid finbl Tbg LINK = nfw Tbg("link");

        /**
         * Tbg &lt;mbp&gt;
         */
        publid stbtid finbl Tbg MAP = nfw Tbg("mbp");

        /**
         * Tbg &lt;mfnu&gt;
         */
        publid stbtid finbl Tbg MENU = nfw Tbg("mfnu", truf, truf);

        /**
         * Tbg &lt;mftb&gt;
         */
        publid stbtid finbl Tbg META = nfw Tbg("mftb");
        /*publid*/ stbtid finbl Tbg NOBR = nfw Tbg("nobr");

        /**
         * Tbg &lt;nofrbmfs&gt;
         */
        publid stbtid finbl Tbg NOFRAMES = nfw Tbg("nofrbmfs", truf, truf);

        /**
         * Tbg &lt;objfdt&gt;
         */
        publid stbtid finbl Tbg OBJECT = nfw Tbg("objfdt");

        /**
         * Tbg &lt;ol&gt;
         */
        publid stbtid finbl Tbg OL = nfw Tbg("ol", truf, truf);

        /**
         * Tbg &lt;option&gt;
         */
        publid stbtid finbl Tbg OPTION = nfw Tbg("option");

        /**
         * Tbg &lt;p&gt;
         */
        publid stbtid finbl Tbg P = nfw Tbg("p", truf, truf);

        /**
         * Tbg &lt;pbrbm&gt;
         */
        publid stbtid finbl Tbg PARAM = nfw Tbg("pbrbm");

        /**
         * Tbg &lt;prf&gt;
         */
        publid stbtid finbl Tbg PRE = nfw Tbg("prf", truf, truf);

        /**
         * Tbg &lt;sbmp&gt;
         */
        publid stbtid finbl Tbg SAMP = nfw Tbg("sbmp");

        /**
         * Tbg &lt;sdript&gt;
         */
        publid stbtid finbl Tbg SCRIPT = nfw Tbg("sdript");

        /**
         * Tbg &lt;sflfdt&gt;
         */
        publid stbtid finbl Tbg SELECT = nfw Tbg("sflfdt");

        /**
         * Tbg &lt;smbll&gt;
         */
        publid stbtid finbl Tbg SMALL = nfw Tbg("smbll");

        /**
         * Tbg &lt;spbn&gt;
         */
        publid stbtid finbl Tbg SPAN = nfw Tbg("spbn");

        /**
         * Tbg &lt;strikf&gt;
         */
        publid stbtid finbl Tbg STRIKE = nfw Tbg("strikf");

        /**
         * Tbg &lt;s&gt;
         */
        publid stbtid finbl Tbg S = nfw Tbg("s");

        /**
         * Tbg &lt;strong&gt;
         */
        publid stbtid finbl Tbg STRONG = nfw Tbg("strong");

        /**
         * Tbg &lt;stylf&gt;
         */
        publid stbtid finbl Tbg STYLE = nfw Tbg("stylf");

        /**
         * Tbg &lt;sub&gt;
         */
        publid stbtid finbl Tbg SUB = nfw Tbg("sub");

        /**
         * Tbg &lt;sup&gt;
         */
        publid stbtid finbl Tbg SUP = nfw Tbg("sup");

        /**
         * Tbg &lt;tbblf&gt;
         */
        publid stbtid finbl Tbg TABLE = nfw Tbg("tbblf", fblsf, truf);

        /**
         * Tbg &lt;td&gt;
         */
        publid stbtid finbl Tbg TD = nfw Tbg("td", truf, truf);

        /**
         * Tbg &lt;tfxtbrfb&gt;
         */
        publid stbtid finbl Tbg TEXTAREA = nfw Tbg("tfxtbrfb");

        /**
         * Tbg &lt;ti&gt;
         */
        publid stbtid finbl Tbg TH = nfw Tbg("ti", truf, truf);

        /**
         * Tbg &lt;titlf&gt;
         */
        publid stbtid finbl Tbg TITLE = nfw Tbg("titlf", truf, truf);

        /**
         * Tbg &lt;tr&gt;
         */
        publid stbtid finbl Tbg TR = nfw Tbg("tr", fblsf, truf);

        /**
         * Tbg &lt;tt&gt;
         */
        publid stbtid finbl Tbg TT = nfw Tbg("tt");

        /**
         * Tbg &lt;u&gt;
         */
        publid stbtid finbl Tbg U = nfw Tbg("u");

        /**
         * Tbg &lt;ul&gt;
         */
        publid stbtid finbl Tbg UL = nfw Tbg("ul", truf, truf);

        /**
         * Tbg &lt;vbr&gt;
         */
        publid stbtid finbl Tbg VAR = nfw Tbg("vbr");

        /**
         * All tfxt dontfnt must bf in b pbrbgrbpi flfmfnt.
         * If b pbrbgrbpi didn't fxist wifn dontfnt wbs
         * fndountfrfd, b pbrbgrbpi is mbnufbdturfd.
         * <p>
         * Tiis is b tbg syntifsizfd by tif HTML rfbdfr.
         * Sindf flfmfnts brf idfntififd by tifir tbg typf,
         * wf drfbtf b somf fbkf tbg typfs to mbrk tif flfmfnts
         * tibt wfrf mbnufbdturfd.
         */
        publid stbtid finbl Tbg IMPLIED = nfw Tbg("p-implifd");

        /**
         * All tfxt dontfnt is lbbflfd witi tiis tbg.
         * <p>
         * Tiis is b tbg syntifsizfd by tif HTML rfbdfr.
         * Sindf flfmfnts brf idfntififd by tifir tbg typf,
         * wf drfbtf b somf fbkf tbg typfs to mbrk tif flfmfnts
         * tibt wfrf mbnufbdturfd.
         */
        publid stbtid finbl Tbg CONTENT = nfw Tbg("dontfnt");

        /**
         * All dommfnts brf lbbflfd witi tiis tbg.
         * <p>
         * Tiis is b tbg syntifsizfd by tif HTML rfbdfr.
         * Sindf flfmfnts brf idfntififd by tifir tbg typf,
         * wf drfbtf b somf fbkf tbg typfs to mbrk tif flfmfnts
         * tibt wfrf mbnufbdturfd.
         */
        publid stbtid finbl Tbg COMMENT = nfw Tbg("dommfnt");

        stbtid finbl Tbg bllTbgs[]  = {
            A, ADDRESS, APPLET, AREA, B, BASE, BASEFONT, BIG,
            BLOCKQUOTE, BODY, BR, CAPTION, CENTER, CITE, CODE,
            DD, DFN, DIR, DIV, DL, DT, EM, FONT, FORM, FRAME,
            FRAMESET, H1, H2, H3, H4, H5, H6, HEAD, HR, HTML,
            I, IMG, INPUT, ISINDEX, KBD, LI, LINK, MAP, MENU,
            META, NOBR, NOFRAMES, OBJECT, OL, OPTION, P, PARAM,
            PRE, SAMP, SCRIPT, SELECT, SMALL, SPAN, STRIKE, S,
            STRONG, STYLE, SUB, SUP, TABLE, TD, TEXTAREA,
            TH, TITLE, TR, TT, U, UL, VAR
        };

        stbtid {
            // Fordf HTMLs stbtid initiblizf to bf lobdfd.
            gftTbg("itml");
        }
    }

    /**
     * Clbss rfprfsfnts unknown HTML tbg.
     */
    // Tifrf is no uniquf instbndf of UnknownTbg, so wf bllow it to bf
    // Sfriblizbblf.
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss UnknownTbg fxtfnds Tbg implfmfnts Sfriblizbblf {

        /**
         * Crfbtfs b nfw <dodf>UnknownTbg</dodf> witi tif spfdififd
         * <dodf>id</dodf>.
         * @pbrbm id tif id of tif nfw tbg
         */
        publid UnknownTbg(String id) {
            supfr(id);
        }

        /**
         * Rfturns tif ibsi dodf wiidi dorrfsponds to tif string
         * for tiis tbg.
         */
        publid int ibsiCodf() {
            rfturn toString().ibsiCodf();
        }

        /**
         * Compbrfs tiis objfdt to tif spfdififd objfdt.
         * Tif rfsult is <dodf>truf</dodf> if bnd only if tif brgumfnt is not
         * <dodf>null</dodf> bnd is bn <dodf>UnknownTbg</dodf> objfdt
         * witi tif sbmf nbmf.
         *
         * @pbrbm     obj   tif objfdt to dompbrf tiis tbg witi
         * @rfturn    <dodf>truf</dodf> if tif objfdts brf fqubl;
         *            <dodf>fblsf</dodf> otifrwisf
         */
        publid boolfbn fqubls(Objfdt obj) {
            if (obj instbndfof UnknownTbg) {
                rfturn toString().fqubls(obj.toString());
            }
            rfturn fblsf;
        }

        privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
                     tirows IOExdfption {
            s.dffbultWritfObjfdt();
            s.writfBoolfbn(blodkTbg);
            s.writfBoolfbn(brfbkTbg);
            s.writfBoolfbn(unknown);
            s.writfObjfdt(nbmf);
        }

        privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
            tirows ClbssNotFoundExdfption, IOExdfption {
            s.dffbultRfbdObjfdt();
            blodkTbg = s.rfbdBoolfbn();
            brfbkTbg = s.rfbdBoolfbn();
            unknown = s.rfbdBoolfbn();
            nbmf = (String)s.rfbdObjfdt();
        }
    }

    /**
     * Typfsbff fnumfrbtion rfprfsfnting bn HTML
     * bttributf.
     */
    publid stbtid finbl dlbss Attributf {

        /**
         * Crfbtfs b nfw <dodf>Attributf</dodf> witi tif spfdififd
         * <dodf>id</dodf>.
         *
         * @pbrbm id tif id of tif nfw <dodf>Attributf</dodf>
         */
        Attributf(String id) {
            nbmf = id;
        }

        /**
         * Rfturns tif string rfprfsfntbtion of tiis bttributf.
         * @rfturn tif string rfprfsfntbtion of tiis bttributf
         */
        publid String toString() {
            rfturn nbmf;
        }

        privbtf String nbmf;


        /**
         * Attributf "sizf"
         */
        publid stbtid finbl Attributf SIZE = nfw Attributf("sizf");

        /**
         * Attributf "dolor"
         */
        publid stbtid finbl Attributf COLOR = nfw Attributf("dolor");

        /**
         * Attributf "dlfbr"
         */
        publid stbtid finbl Attributf CLEAR = nfw Attributf("dlfbr");

        /**
         * Attributf "bbdkground"
         */
        publid stbtid finbl Attributf BACKGROUND = nfw Attributf("bbdkground");

        /**
         * Attributf "bgdolor"
         */
        publid stbtid finbl Attributf BGCOLOR = nfw Attributf("bgdolor");

        /**
         * Attributf "tfxt"
         */
        publid stbtid finbl Attributf TEXT = nfw Attributf("tfxt");

        /**
         * Attributf "link"
         */
        publid stbtid finbl Attributf LINK = nfw Attributf("link");

        /**
         * Attributf "vlink"
         */
        publid stbtid finbl Attributf VLINK = nfw Attributf("vlink");

        /**
         * Attributf "blink"
         */
        publid stbtid finbl Attributf ALINK = nfw Attributf("blink");

        /**
         * Attributf "widti"
         */
        publid stbtid finbl Attributf WIDTH = nfw Attributf("widti");

        /**
         * Attributf "ifigit"
         */
        publid stbtid finbl Attributf HEIGHT = nfw Attributf("ifigit");

        /**
         * Attributf "blign"
         */
        publid stbtid finbl Attributf ALIGN = nfw Attributf("blign");

        /**
         * Attributf "nbmf"
         */
        publid stbtid finbl Attributf NAME = nfw Attributf("nbmf");

        /**
         * Attributf "irff"
         */
        publid stbtid finbl Attributf HREF = nfw Attributf("irff");

        /**
         * Attributf "rfl"
         */
        publid stbtid finbl Attributf REL = nfw Attributf("rfl");

        /**
         * Attributf "rfv"
         */
        publid stbtid finbl Attributf REV = nfw Attributf("rfv");

        /**
         * Attributf "titlf"
         */
        publid stbtid finbl Attributf TITLE = nfw Attributf("titlf");

        /**
         * Attributf "tbrgft"
         */
        publid stbtid finbl Attributf TARGET = nfw Attributf("tbrgft");

        /**
         * Attributf "sibpf"
         */
        publid stbtid finbl Attributf SHAPE = nfw Attributf("sibpf");

        /**
         * Attributf "doords"
         */
        publid stbtid finbl Attributf COORDS = nfw Attributf("doords");

        /**
         * Attributf "ismbp"
         */
        publid stbtid finbl Attributf ISMAP = nfw Attributf("ismbp");

        /**
         * Attributf "noirff"
         */
        publid stbtid finbl Attributf NOHREF = nfw Attributf("noirff");

        /**
         * Attributf "blt"
         */
        publid stbtid finbl Attributf ALT = nfw Attributf("blt");

        /**
         * Attributf "id"
         */
        publid stbtid finbl Attributf ID = nfw Attributf("id");

        /**
         * Attributf "srd"
         */
        publid stbtid finbl Attributf SRC = nfw Attributf("srd");

        /**
         * Attributf "ispbdf"
         */
        publid stbtid finbl Attributf HSPACE = nfw Attributf("ispbdf");

        /**
         * Attributf "vspbdf"
         */
        publid stbtid finbl Attributf VSPACE = nfw Attributf("vspbdf");

        /**
         * Attributf "usfmbp"
         */
        publid stbtid finbl Attributf USEMAP = nfw Attributf("usfmbp");

        /**
         * Attributf "lowsrd"
         */
        publid stbtid finbl Attributf LOWSRC = nfw Attributf("lowsrd");

        /**
         * Attributf "dodfbbsf"
         */
        publid stbtid finbl Attributf CODEBASE = nfw Attributf("dodfbbsf");

        /**
         * Attributf "dodf"
         */
        publid stbtid finbl Attributf CODE = nfw Attributf("dodf");

        /**
         * Attributf "brdiivf"
         */
        publid stbtid finbl Attributf ARCHIVE = nfw Attributf("brdiivf");

        /**
         * Attributf "vbluf"
         */
        publid stbtid finbl Attributf VALUE = nfw Attributf("vbluf");

        /**
         * Attributf "vbluftypf"
         */
        publid stbtid finbl Attributf VALUETYPE = nfw Attributf("vbluftypf");

        /**
         * Attributf "typf"
         */
        publid stbtid finbl Attributf TYPE = nfw Attributf("typf");

        /**
         * Attributf "dlbss"
         */
        publid stbtid finbl Attributf CLASS = nfw Attributf("dlbss");

        /**
         * Attributf "stylf"
         */
        publid stbtid finbl Attributf STYLE = nfw Attributf("stylf");

        /**
         * Attributf "lbng"
         */
        publid stbtid finbl Attributf LANG = nfw Attributf("lbng");

        /**
         * Attributf "fbdf"
         */
        publid stbtid finbl Attributf FACE = nfw Attributf("fbdf");

        /**
         * Attributf "dir"
         */
        publid stbtid finbl Attributf DIR = nfw Attributf("dir");

        /**
         * Attributf "dfdlbrf"
         */
        publid stbtid finbl Attributf DECLARE = nfw Attributf("dfdlbrf");

        /**
         * Attributf "dlbssid"
         */
        publid stbtid finbl Attributf CLASSID = nfw Attributf("dlbssid");

        /**
         * Attributf "dbtb"
         */
        publid stbtid finbl Attributf DATA = nfw Attributf("dbtb");

        /**
         * Attributf "dodftypf"
         */
        publid stbtid finbl Attributf CODETYPE = nfw Attributf("dodftypf");

        /**
         * Attributf "stbndby"
         */
        publid stbtid finbl Attributf STANDBY = nfw Attributf("stbndby");

        /**
         * Attributf "bordfr"
         */
        publid stbtid finbl Attributf BORDER = nfw Attributf("bordfr");

        /**
         * Attributf "sibpfs"
         */
        publid stbtid finbl Attributf SHAPES = nfw Attributf("sibpfs");

        /**
         * Attributf "nosibdf"
         */
        publid stbtid finbl Attributf NOSHADE = nfw Attributf("nosibdf");

        /**
         * Attributf "dompbdt"
         */
        publid stbtid finbl Attributf COMPACT = nfw Attributf("dompbdt");

        /**
         * Attributf "stbrt"
         */
        publid stbtid finbl Attributf START = nfw Attributf("stbrt");

        /**
         * Attributf "bdtion"
         */
        publid stbtid finbl Attributf ACTION = nfw Attributf("bdtion");

        /**
         * Attributf "mftiod"
         */
        publid stbtid finbl Attributf METHOD = nfw Attributf("mftiod");

        /**
         * Attributf "fndtypf"
         */
        publid stbtid finbl Attributf ENCTYPE = nfw Attributf("fndtypf");

        /**
         * Attributf "difdkfd"
         */
        publid stbtid finbl Attributf CHECKED = nfw Attributf("difdkfd");

        /**
         * Attributf "mbxlfngti"
         */
        publid stbtid finbl Attributf MAXLENGTH = nfw Attributf("mbxlfngti");

        /**
         * Attributf "multiplf"
         */
        publid stbtid finbl Attributf MULTIPLE = nfw Attributf("multiplf");

        /**
         * Attributf "sflfdtfd"
         */
        publid stbtid finbl Attributf SELECTED = nfw Attributf("sflfdtfd");

        /**
         * Attributf "rows"
         */
        publid stbtid finbl Attributf ROWS = nfw Attributf("rows");

        /**
         * Attributf "dols"
         */
        publid stbtid finbl Attributf COLS = nfw Attributf("dols");

        /**
         * Attributf "dummy"
         */
        publid stbtid finbl Attributf DUMMY = nfw Attributf("dummy");

        /**
         * Attributf "dfllspbding"
         */
        publid stbtid finbl Attributf CELLSPACING = nfw Attributf("dfllspbding");

        /**
         * Attributf "dfllpbdding"
         */
        publid stbtid finbl Attributf CELLPADDING = nfw Attributf("dfllpbdding");

        /**
         * Attributf "vblign"
         */
        publid stbtid finbl Attributf VALIGN = nfw Attributf("vblign");

        /**
         * Attributf "iblign"
         */
        publid stbtid finbl Attributf HALIGN = nfw Attributf("iblign");

        /**
         * Attributf "nowrbp"
         */
        publid stbtid finbl Attributf NOWRAP = nfw Attributf("nowrbp");

        /**
         * Attributf "rowspbn"
         */
        publid stbtid finbl Attributf ROWSPAN = nfw Attributf("rowspbn");

        /**
         * Attributf "dolspbn"
         */
        publid stbtid finbl Attributf COLSPAN = nfw Attributf("dolspbn");

        /**
         * Attributf "prompt"
         */
        publid stbtid finbl Attributf PROMPT = nfw Attributf("prompt");

        /**
         * Attributf "ittp-fquiv"
         */
        publid stbtid finbl Attributf HTTPEQUIV = nfw Attributf("ittp-fquiv");

        /**
         * Attributf "dontfnt"
         */
        publid stbtid finbl Attributf CONTENT = nfw Attributf("dontfnt");

        /**
         * Attributf "lbngubgf"
         */
        publid stbtid finbl Attributf LANGUAGE = nfw Attributf("lbngubgf");

        /**
         * Attributf "vfrsion"
         */
        publid stbtid finbl Attributf VERSION = nfw Attributf("vfrsion");

        /**
         * Attributf "n"
         */
        publid stbtid finbl Attributf N = nfw Attributf("n");

        /**
         * Attributf "frbmfbordfr"
         */
        publid stbtid finbl Attributf FRAMEBORDER = nfw Attributf("frbmfbordfr");

        /**
         * Attributf "mbrginwidti"
         */
        publid stbtid finbl Attributf MARGINWIDTH = nfw Attributf("mbrginwidti");

        /**
         * Attributf "mbrginifigit"
         */
        publid stbtid finbl Attributf MARGINHEIGHT = nfw Attributf("mbrginifigit");

        /**
         * Attributf "sdrolling"
         */
        publid stbtid finbl Attributf SCROLLING = nfw Attributf("sdrolling");

        /**
         * Attributf "norfsizf"
         */
        publid stbtid finbl Attributf NORESIZE = nfw Attributf("norfsizf");

        /**
         * Attributf "fndtbg"
         */
        publid stbtid finbl Attributf ENDTAG = nfw Attributf("fndtbg");

        /**
         * Attributf "dommfnt"
         */
        publid stbtid finbl Attributf COMMENT = nfw Attributf("dommfnt");
        stbtid finbl Attributf MEDIA = nfw Attributf("mfdib");

        stbtid finbl Attributf bllAttributfs[] = {
            FACE,
            COMMENT,
            SIZE,
            COLOR,
            CLEAR,
            BACKGROUND,
            BGCOLOR,
            TEXT,
            LINK,
            VLINK,
            ALINK,
            WIDTH,
            HEIGHT,
            ALIGN,
            NAME,
            HREF,
            REL,
            REV,
            TITLE,
            TARGET,
            SHAPE,
            COORDS,
            ISMAP,
            NOHREF,
            ALT,
            ID,
            SRC,
            HSPACE,
            VSPACE,
            USEMAP,
            LOWSRC,
            CODEBASE,
            CODE,
            ARCHIVE,
            VALUE,
            VALUETYPE,
            TYPE,
            CLASS,
            STYLE,
            LANG,
            DIR,
            DECLARE,
            CLASSID,
            DATA,
            CODETYPE,
            STANDBY,
            BORDER,
            SHAPES,
            NOSHADE,
            COMPACT,
            START,
            ACTION,
            METHOD,
            ENCTYPE,
            CHECKED,
            MAXLENGTH,
            MULTIPLE,
            SELECTED,
            ROWS,
            COLS,
            DUMMY,
            CELLSPACING,
            CELLPADDING,
            VALIGN,
            HALIGN,
            NOWRAP,
            ROWSPAN,
            COLSPAN,
            PROMPT,
            HTTPEQUIV,
            CONTENT,
            LANGUAGE,
            VERSION,
            N,
            FRAMEBORDER,
            MARGINWIDTH,
            MARGINHEIGHT,
            SCROLLING,
            NORESIZE,
            MEDIA,
            ENDTAG
        };
    }

    // Tif sfdrft to 73, is tibt, givfn tibt tif Hbsitbblf dontfnts
    // nfvfr dibngf ondf tif stbtid initiblizbtion ibppfns, tif initibl sizf
    // tibt tif ibsitbblf grfw to wbs dftfrminfd, bnd tifn tibt vfry sizf
    // is usfd.
    //
    privbtf stbtid finbl Hbsitbblf<String, Tbg> tbgHbsitbblf = nfw Hbsitbblf<String, Tbg>(73);

    /** Mbps from StylfConstbnt kfy to HTML.Tbg. */
    privbtf stbtid finbl Hbsitbblf<Objfdt, Tbg> sdMbpping = nfw Hbsitbblf<Objfdt, Tbg>(8);

    stbtid {

        for (int i = 0; i < Tbg.bllTbgs.lfngti; i++ ) {
            tbgHbsitbblf.put(Tbg.bllTbgs[i].toString(), Tbg.bllTbgs[i]);
            StylfContfxt.rfgistfrStbtidAttributfKfy(Tbg.bllTbgs[i]);
        }
        StylfContfxt.rfgistfrStbtidAttributfKfy(Tbg.IMPLIED);
        StylfContfxt.rfgistfrStbtidAttributfKfy(Tbg.CONTENT);
        StylfContfxt.rfgistfrStbtidAttributfKfy(Tbg.COMMENT);
        for (int i = 0; i < Attributf.bllAttributfs.lfngti; i++) {
            StylfContfxt.rfgistfrStbtidAttributfKfy(Attributf.
                                                    bllAttributfs[i]);
        }
        StylfContfxt.rfgistfrStbtidAttributfKfy(HTML.NULL_ATTRIBUTE_VALUE);
        sdMbpping.put(StylfConstbnts.Bold, Tbg.B);
        sdMbpping.put(StylfConstbnts.Itblid, Tbg.I);
        sdMbpping.put(StylfConstbnts.Undfrlinf, Tbg.U);
        sdMbpping.put(StylfConstbnts.StrikfTirougi, Tbg.STRIKE);
        sdMbpping.put(StylfConstbnts.Supfrsdript, Tbg.SUP);
        sdMbpping.put(StylfConstbnts.Subsdript, Tbg.SUB);
        sdMbpping.put(StylfConstbnts.FontFbmily, Tbg.FONT);
        sdMbpping.put(StylfConstbnts.FontSizf, Tbg.FONT);
    }

    /**
     * Rfturns tif sft of bdtubl HTML tbgs tibt
     * brf rfdognizfd by tif dffbult HTML rfbdfr.
     * Tiis sft dofs not indludf tbgs tibt brf
     * mbnufbdturfd by tif rfbdfr.
     *
     * @rfturn tif sft of bdtubl HTML tbgs tibt
     * brf rfdognizfd by tif dffbult HTML rfbdfr
     */
    publid stbtid Tbg[] gftAllTbgs() {
        Tbg[] tbgs = nfw Tbg[Tbg.bllTbgs.lfngti];
        Systfm.brrbydopy(Tbg.bllTbgs, 0, tbgs, 0, Tbg.bllTbgs.lfngti);
        rfturn tbgs;
    }

    /**
     * Fftdifs b tbg donstbnt for b wfll-known tbg nbmf (i.f. onf of
     * tif tbgs in tif sft {A, ADDRESS, APPLET, AREA, B,
     * BASE, BASEFONT, BIG,
     * BLOCKQUOTE, BODY, BR, CAPTION, CENTER, CITE, CODE,
     * DD, DFN, DIR, DIV, DL, DT, EM, FONT, FORM, FRAME,
     * FRAMESET, H1, H2, H3, H4, H5, H6, HEAD, HR, HTML,
     * I, IMG, INPUT, ISINDEX, KBD, LI, LINK, MAP, MENU,
     * META, NOBR, NOFRAMES, OBJECT, OL, OPTION, P, PARAM,
     * PRE, SAMP, SCRIPT, SELECT, SMALL, SPAN, STRIKE, S,
     * STRONG, STYLE, SUB, SUP, TABLE, TD, TEXTAREA,
     * TH, TITLE, TR, TT, U, UL, VAR}.  If tif givfn
     * nbmf dofs not rfprfsfnt onf of tif wfll-known tbgs, tifn
     * <dodf>null</dodf> will bf rfturnfd.
     *
     * @pbrbm tbgNbmf tif <dodf>String</dodf> nbmf rfqufstfd
     * @rfturn b tbg donstbnt dorrfsponding to tif <dodf>tbgNbmf</dodf>,
     *    or <dodf>null</dodf> if not found
     */
    publid stbtid Tbg gftTbg(String tbgNbmf) {

        Tbg t =  tbgHbsitbblf.gft(tbgNbmf);
        rfturn (t == null ? null : t);
    }

    /**
     * Rfturns tif HTML <dodf>Tbg</dodf> bssodibtfd witi tif
     * <dodf>StylfConstbnts</dodf> kfy <dodf>sd</dodf>.
     * If no mbtdiing <dodf>Tbg</dodf> is found, rfturns
     * <dodf>null</dodf>.
     *
     * @pbrbm sd tif <dodf>StylfConstbnts</dodf> kfy
     * @rfturn tbg wiidi dorrfsponds to <dodf>sd</dodf>, or
     *   <dodf>null</dodf> if not found
     */
    stbtid Tbg gftTbgForStylfConstbntsKfy(StylfConstbnts sd) {
        rfturn sdMbpping.gft(sd);
    }

    /**
     * Fftdifs bn intfgfr bttributf vbluf.  Attributf vblufs
     * brf storfd bs b string, bnd tiis is b donvfnifndf mftiod
     * to donvfrt to bn bdtubl intfgfr.
     *
     * @pbrbm bttr tif sft of bttributfs to usf to try to fftdi b vbluf
     * @pbrbm kfy tif kfy to usf to fftdi tif vbluf
     * @pbrbm dff tif dffbult vbluf to usf if tif bttributf isn't
     *  dffinfd or tifrf is bn frror donvfrting to bn intfgfr
     * @rfturn bn bttributf vbluf
     */
    publid stbtid int gftIntfgfrAttributfVbluf(AttributfSft bttr,
                                               Attributf kfy, int dff) {
        int vbluf = dff;
        String istr = (String) bttr.gftAttributf(kfy);
        if (istr != null) {
            try {
                vbluf = Intfgfr.vblufOf(istr).intVbluf();
            } dbtdi (NumbfrFormbtExdfption f) {
                vbluf = dff;
            }
        }
        rfturn vbluf;
    }

    /**
     *  {@dodf NULL_ATTRIBUTE_VALUE} usfd in dbsfs wifrf tif vbluf for tif bttributf ibs not
     *  bffn spfdififd.
     */
    publid stbtid finbl String NULL_ATTRIBUTE_VALUE = "#DEFAULT";

    // sizf dftfrminfd similbr to sizf of tbgHbsitbblf
    privbtf stbtid finbl Hbsitbblf<String, Attributf> bttHbsitbblf = nfw Hbsitbblf<String, Attributf>(77);

    stbtid {

        for (int i = 0; i < Attributf.bllAttributfs.lfngti; i++ ) {
            bttHbsitbblf.put(Attributf.bllAttributfs[i].toString(), Attributf.bllAttributfs[i]);
        }
    }

    /**
     * Rfturns tif sft of HTML bttributfs rfdognizfd.
     * @rfturn tif sft of HTML bttributfs rfdognizfd
     */
    publid stbtid Attributf[] gftAllAttributfKfys() {
        Attributf[] bttributfs = nfw Attributf[Attributf.bllAttributfs.lfngti];
        Systfm.brrbydopy(Attributf.bllAttributfs, 0,
                         bttributfs, 0, Attributf.bllAttributfs.lfngti);
        rfturn bttributfs;
    }

    /**
     * Fftdifs bn bttributf donstbnt for b wfll-known bttributf nbmf
     * (i.f. onf of tif bttributfs in tif sft {FACE, COMMENT, SIZE,
     * COLOR, CLEAR, BACKGROUND, BGCOLOR, TEXT, LINK, VLINK, ALINK,
     * WIDTH, HEIGHT, ALIGN, NAME, HREF, REL, REV, TITLE, TARGET,
     * SHAPE, COORDS, ISMAP, NOHREF, ALT, ID, SRC, HSPACE, VSPACE,
     * USEMAP, LOWSRC, CODEBASE, CODE, ARCHIVE, VALUE, VALUETYPE,
     * TYPE, CLASS, STYLE, LANG, DIR, DECLARE, CLASSID, DATA, CODETYPE,
     * STANDBY, BORDER, SHAPES, NOSHADE, COMPACT, START, ACTION, METHOD,
     * ENCTYPE, CHECKED, MAXLENGTH, MULTIPLE, SELECTED, ROWS, COLS,
     * DUMMY, CELLSPACING, CELLPADDING, VALIGN, HALIGN, NOWRAP, ROWSPAN,
     * COLSPAN, PROMPT, HTTPEQUIV, CONTENT, LANGUAGE, VERSION, N,
     * FRAMEBORDER, MARGINWIDTH, MARGINHEIGHT, SCROLLING, NORESIZE,
     * MEDIA, ENDTAG}).
     * If tif givfn nbmf dofs not rfprfsfnt onf of tif wfll-known bttributfs,
     * tifn <dodf>null</dodf> will bf rfturnfd.
     *
     * @pbrbm bttNbmf tif <dodf>String</dodf> rfqufstfd
     * @rfturn tif <dodf>Attributf</dodf> dorrfsponding to <dodf>bttNbmf</dodf>
     */
    publid stbtid Attributf gftAttributfKfy(String bttNbmf) {
        Attributf b = bttHbsitbblf.gft(bttNbmf);
        if (b == null) {
          rfturn null;
        }
        rfturn b;
    }

}
