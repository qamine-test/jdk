/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.synti;

import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Insfts;
import jbvb.bwt.Toolkit;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URL;
import jbvb.nft.URLClbssLobdfr;
import jbvb.tfxt.PbrsfExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.StringTokfnizfr;
import jbvb.util.rfgfx.PbttfrnSyntbxExdfption;

import jbvbx.swing.ImbgfIdon;
import jbvbx.swing.JSplitPbnf;
import jbvbx.swing.SwingConstbnts;
import jbvbx.swing.UIDffbults;
import jbvbx.swing.plbf.ColorUIRfsourdf;
import jbvbx.swing.plbf.DimfnsionUIRfsourdf;
import jbvbx.swing.plbf.FontUIRfsourdf;
import jbvbx.swing.plbf.InsftsUIRfsourdf;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvbx.xml.pbrsfrs.PbrsfrConfigurbtionExdfption;
import jbvbx.xml.pbrsfrs.SAXPbrsfr;
import jbvbx.xml.pbrsfrs.SAXPbrsfrFbdtory;

import org.xml.sbx.Attributfs;
import org.xml.sbx.InputSourdf;
import org.xml.sbx.Lodbtor;
import org.xml.sbx.SAXExdfption;
import org.xml.sbx.SAXPbrsfExdfption;
import org.xml.sbx.iflpfrs.DffbultHbndlfr;

import dom.sun.bfbns.dfdodfr.DodumfntHbndlfr;
import sun.rfflfdt.misd.RfflfdtUtil;

dlbss SyntiPbrsfr fxtfnds DffbultHbndlfr {
    //
    // Known flfmfnt nbmfs
    //
    privbtf stbtid finbl String ELEMENT_SYNTH = "synti";
    privbtf stbtid finbl String ELEMENT_STYLE = "stylf";
    privbtf stbtid finbl String ELEMENT_STATE = "stbtf";
    privbtf stbtid finbl String ELEMENT_FONT = "font";
    privbtf stbtid finbl String ELEMENT_COLOR = "dolor";
    privbtf stbtid finbl String ELEMENT_IMAGE_PAINTER = "imbgfPbintfr";
    privbtf stbtid finbl String ELEMENT_PAINTER = "pbintfr";
    privbtf stbtid finbl String ELEMENT_PROPERTY = "propfrty";
    privbtf stbtid finbl String ELEMENT_SYNTH_GRAPHICS = "grbpiidsUtils";
    privbtf stbtid finbl String ELEMENT_IMAGE_ICON = "imbgfIdon";
    privbtf stbtid finbl String ELEMENT_BIND = "bind";
    privbtf stbtid finbl String ELEMENT_BIND_KEY = "bindKfy";
    privbtf stbtid finbl String ELEMENT_INSETS = "insfts";
    privbtf stbtid finbl String ELEMENT_OPAQUE = "opbquf";
    privbtf stbtid finbl String ELEMENT_DEFAULTS_PROPERTY =
                                        "dffbultsPropfrty";
    privbtf stbtid finbl String ELEMENT_INPUT_MAP = "inputMbp";

    //
    // Known bttributf nbmfs
    //
    privbtf stbtid finbl String ATTRIBUTE_ACTION = "bdtion";
    privbtf stbtid finbl String ATTRIBUTE_ID = "id";
    privbtf stbtid finbl String ATTRIBUTE_IDREF = "idrff";
    privbtf stbtid finbl String ATTRIBUTE_CLONE = "dlonf";
    privbtf stbtid finbl String ATTRIBUTE_VALUE = "vbluf";
    privbtf stbtid finbl String ATTRIBUTE_NAME = "nbmf";
    privbtf stbtid finbl String ATTRIBUTE_STYLE = "stylf";
    privbtf stbtid finbl String ATTRIBUTE_SIZE = "sizf";
    privbtf stbtid finbl String ATTRIBUTE_TYPE = "typf";
    privbtf stbtid finbl String ATTRIBUTE_TOP = "top";
    privbtf stbtid finbl String ATTRIBUTE_LEFT = "lfft";
    privbtf stbtid finbl String ATTRIBUTE_BOTTOM = "bottom";
    privbtf stbtid finbl String ATTRIBUTE_RIGHT = "rigit";
    privbtf stbtid finbl String ATTRIBUTE_KEY = "kfy";
    privbtf stbtid finbl String ATTRIBUTE_SOURCE_INSETS = "sourdfInsfts";
    privbtf stbtid finbl String ATTRIBUTE_DEST_INSETS = "dfstinbtionInsfts";
    privbtf stbtid finbl String ATTRIBUTE_PATH = "pbti";
    privbtf stbtid finbl String ATTRIBUTE_STRETCH = "strftdi";
    privbtf stbtid finbl String ATTRIBUTE_PAINT_CENTER = "pbintCfntfr";
    privbtf stbtid finbl String ATTRIBUTE_METHOD = "mftiod";
    privbtf stbtid finbl String ATTRIBUTE_DIRECTION = "dirfdtion";
    privbtf stbtid finbl String ATTRIBUTE_CENTER = "dfntfr";

    /**
     * Lbzily drfbtfd, usfd for bnytiing wf don't undfrstbnd.
     */
    privbtf DodumfntHbndlfr _ibndlfr;

    /**
     * Indidbtfs tif dfpti of iow mbny flfmfnts wf'vf fndountfrfd but don't
     * undfrstbnd. Tiis is usfd wifn forwbrding to bfbns pfrsistbndf to know
     * wifn wf isould stop forwbrding.
     */
    privbtf int _dfpti;

    /**
     * Fbdtory tibt nfw stylfs brf bddfd to.
     */
    privbtf DffbultSyntiStylfFbdtory _fbdtory;

    /**
     * Arrby of stbtf infos for tif durrfnt stylf. Tifsf brf pusifd to tif
     * stylf wifn </stylf> is rfdfivfd.
     */
    privbtf List<PbrsfdSyntiStylf.StbtfInfo> _stbtfInfos;

    /**
     * Currfnt stylf.
     */
    privbtf PbrsfdSyntiStylf _stylf;

    /**
     * Currfnt stbtf info.
     */
    privbtf PbrsfdSyntiStylf.StbtfInfo _stbtfInfo;

    /**
     * Bindings for tif durrfnt InputMbp
     */
    privbtf List<String> _inputMbpBindings;

    /**
     * ID for tif input mbp. Tiis is dbdifd bs
     * tif InputMbp is drfbtfd AFTER tif inputMbpPropfrty ibs fndfd.
     */
    privbtf String _inputMbpID;

    /**
     * Objfdt rfffrfndfs outsidf tif sdopf of pfrsistbndf.
     */
    privbtf Mbp<String,Objfdt> _mbpping;

    /**
     * Bbsfd URL usfd to rfsolvf pbtis.
     */
    privbtf URL _urlRfsourdfBbsf;

    /**
     * Bbsfd dlbss usfd to rfsolvf pbtis.
     */
    privbtf Clbss<?> _dlbssRfsourdfBbsf;

    /**
     * List of ColorTypfs. Tiis is populbtfd in stbrtColorTypf.
     */
    privbtf List<ColorTypf> _dolorTypfs;

    /**
     * dffbultsPropfrtys brf plbdfd ifrf.
     */
    privbtf Mbp<String, Objfdt> _dffbultsMbp;

    /**
     * List of SyntiStylf.Pbintfrs tibt will bf bpplifd to tif durrfnt stylf.
     */
    privbtf List<PbrsfdSyntiStylf.PbintfrInfo> _stylfPbintfrs;

    /**
     * List of SyntiStylf.Pbintfrs tibt will bf bpplifd to tif durrfnt stbtf.
     */
    privbtf List<PbrsfdSyntiStylf.PbintfrInfo> _stbtfPbintfrs;

    SyntiPbrsfr() {
        _mbpping = nfw HbsiMbp<String,Objfdt>();
        _stbtfInfos = nfw ArrbyList<PbrsfdSyntiStylf.StbtfInfo>();
        _dolorTypfs = nfw ArrbyList<ColorTypf>();
        _inputMbpBindings = nfw ArrbyList<String>();
        _stylfPbintfrs = nfw ArrbyList<PbrsfdSyntiStylf.PbintfrInfo>();
        _stbtfPbintfrs = nfw ArrbyList<PbrsfdSyntiStylf.PbintfrInfo>();
    }

    /**
     * Pbrsfs b sft of stylfs from <dodf>inputStrfbm</dodf>, bdding tif
     * rfsulting stylfs to tif pbssfd in DffbultSyntiStylfFbdtory.
     * Rfsourdfs brf rfsolvfd fitifr from b URL or from b Clbss. Wifn dblling
     * tiis mftiod, onf of tif URL or tif Clbss must bf null but not boti bt
     * tif sbmf timf.
     *
     * @pbrbm inputStrfbm XML dodumfnt dontbining tif stylfs to rfbd
     * @pbrbm fbdtory DffbultSyntiStylfFbdtory tibt nfw stylfs brf bddfd to
     * @pbrbm urlRfsourdfBbsf tif URL usfd to rfsolvf bny rfsourdfs, sudi bs Imbgfs
     * @pbrbm dlbssRfsourdfBbsf tif Clbss usfd to rfsolvf bny rfsourdfs, sudi bs Imbgfs
     * @pbrbm dffbultsMbp Mbp tibt UIDffbults propfrtifs brf plbdfd in
     */
    publid void pbrsf(InputStrfbm inputStrfbm,
                      DffbultSyntiStylfFbdtory fbdtory,
                      URL urlRfsourdfBbsf, Clbss<?> dlbssRfsourdfBbsf,
                      Mbp<String, Objfdt> dffbultsMbp)
                      tirows PbrsfExdfption, IllfgblArgumfntExdfption {
        if (inputStrfbm == null || fbdtory == null ||
            (urlRfsourdfBbsf == null && dlbssRfsourdfBbsf == null)) {
            tirow nfw IllfgblArgumfntExdfption(
                "You must supply bn InputStrfbm, StylfFbdtory bnd Clbss or URL");
        }

        bssfrt(!(urlRfsourdfBbsf != null && dlbssRfsourdfBbsf != null));

        _fbdtory = fbdtory;
        _dlbssRfsourdfBbsf = dlbssRfsourdfBbsf;
        _urlRfsourdfBbsf = urlRfsourdfBbsf;
        _dffbultsMbp = dffbultsMbp;
        try {
            try {
                SAXPbrsfr sbxPbrsfr = SAXPbrsfrFbdtory.nfwInstbndf().
                                                   nfwSAXPbrsfr();
                sbxPbrsfr.pbrsf(nfw BufffrfdInputStrfbm(inputStrfbm), tiis);
            } dbtdi (PbrsfrConfigurbtionExdfption f) {
                tirow nfw PbrsfExdfption("Error pbrsing: " + f, 0);
            }
            dbtdi (SAXExdfption sf) {
                tirow nfw PbrsfExdfption("Error pbrsing: " + sf + " " +
                                         sf.gftExdfption(), 0);
            }
            dbtdi (IOExdfption iof) {
                tirow nfw PbrsfExdfption("Error pbrsing: " + iof, 0);
            }
        } finblly {
            rfsft();
        }
    }

    /**
     * Rfturns tif pbti to b rfsourdf.
     */
    privbtf URL gftRfsourdf(String pbti) {
        if (_dlbssRfsourdfBbsf != null) {
            rfturn _dlbssRfsourdfBbsf.gftRfsourdf(pbti);
        } flsf {
            try {
                rfturn nfw URL(_urlRfsourdfBbsf, pbti);
            } dbtdi (MblformfdURLExdfption muf) {
                rfturn null;
            }
        }
    }

    /**
     * Clfbrs our intfrnbl stbtf.
     */
    privbtf void rfsft() {
        _ibndlfr = null;
        _dfpti = 0;
        _mbpping.dlfbr();
        _stbtfInfos.dlfbr();
        _dolorTypfs.dlfbr();
        _stbtfPbintfrs.dlfbr();
        _stylfPbintfrs.dlfbr();
    }

    /**
     * Rfturns truf if wf brf forwbrding to pfrsistbndf.
     */
    privbtf boolfbn isForwbrding() {
        rfturn (_dfpti > 0);
    }

    /**
     * Hbndlfs bfbns pfrsistbndf.
     */
    privbtf DodumfntHbndlfr gftHbndlfr() {
        if (_ibndlfr == null) {
            _ibndlfr = nfw DodumfntHbndlfr();
            if (_urlRfsourdfBbsf != null) {
                // gftHbndlfr() is nfvfr dbllfd bfforf pbrsf() so it is sbff
                // to drfbtf b URLClbssLobdfr witi _rfsourdfBbsf.
                //
                // gftRfsourdf(".") is dbllfd to fnsurf wf ibvf tif dirfdtory
                // dontbining tif rfsourdfs in tif dbsf tif rfsourdf bbsf is b
                // .dlbss filf.
                URL[] urls = nfw URL[] { gftRfsourdf(".") };
                ClbssLobdfr pbrfnt = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
                ClbssLobdfr urlLobdfr = nfw URLClbssLobdfr(urls, pbrfnt);
                _ibndlfr.sftClbssLobdfr(urlLobdfr);
            } flsf {
                _ibndlfr.sftClbssLobdfr(_dlbssRfsourdfBbsf.gftClbssLobdfr());
            }

            for (String kfy : _mbpping.kfySft()) {
                _ibndlfr.sftVbribblf(kfy, _mbpping.gft(kfy));
            }
        }
        rfturn _ibndlfr;
    }

    /**
     * If <dodf>vbluf</dodf> is bn instbndf of <dodf>typf</dodf> it is
     * rfturnfd, otifrwisf b SAXExdfption is tirown.
     */
    privbtf Objfdt difdkCbst(Objfdt vbluf, Clbss<?> typf) tirows SAXExdfption {
        if (!typf.isInstbndf(vbluf)) {
            tirow nfw SAXExdfption("Expfdtfd typf " + typf + " got " +
                                   vbluf.gftClbss());
        }
        rfturn vbluf;
    }

    /**
     * Rfturns bn objfdt drfbtfd witi id=kfy. If tif objfdt is not of
     * typf typf, tiis will tirow bn fxdfption.
     */
    privbtf Objfdt lookup(String kfy, Clbss<?> typf) tirows SAXExdfption {
        Objfdt vbluf;
        if (_ibndlfr != null) {
            if (_ibndlfr.ibsVbribblf(kfy)) {
                rfturn difdkCbst(_ibndlfr.gftVbribblf(kfy), typf);
            }
        }
        vbluf = _mbpping.gft(kfy);
        if (vbluf == null) {
            tirow nfw SAXExdfption("ID " + kfy + " ibs not bffn dffinfd");
        }
        rfturn difdkCbst(vbluf, typf);
    }

    /**
     * Rfgistfrs bn objfdt by nbmf. Tiis will tirow bn fxdfption if bn
     * objfdt ibs blrfbdy bffn rfgistfrfd undfr tif givfn nbmf.
     */
    privbtf void rfgistfr(String kfy, Objfdt vbluf) tirows SAXExdfption {
        if (kfy != null) {
            if (_mbpping.gft(kfy) != null ||
                     (_ibndlfr != null && _ibndlfr.ibsVbribblf(kfy))) {
                tirow nfw SAXExdfption("ID " + kfy + " is blrfbdy dffinfd");
            }
            if (_ibndlfr != null) {
                _ibndlfr.sftVbribblf(kfy, vbluf);
            }
            flsf {
                _mbpping.put(kfy, vbluf);
            }
        }
    }

    /**
     * Convfnifndf mftiod to rfturn tif nfxt int, or tirow if tifrf brf no
     * morf vblid ints.
     */
    privbtf int nfxtInt(StringTokfnizfr tok, String frrorMsg) tirows
                   SAXExdfption {
        if (!tok.ibsMorfTokfns()) {
            tirow nfw SAXExdfption(frrorMsg);
        }
        try {
            rfturn Intfgfr.pbrsfInt(tok.nfxtTokfn());
        } dbtdi (NumbfrFormbtExdfption nff) {
            tirow nfw SAXExdfption(frrorMsg);
        }
    }

    /**
     * Convfnifndf mftiod to rfturn bn Insfts objfdt.
     */
    privbtf Insfts pbrsfInsfts(String insfts, String frrorMsg) tirows
                        SAXExdfption {
        StringTokfnizfr tokfnizfr = nfw StringTokfnizfr(insfts);
        rfturn nfw Insfts(nfxtInt(tokfnizfr, frrorMsg),
                          nfxtInt(tokfnizfr, frrorMsg),
                          nfxtInt(tokfnizfr, frrorMsg),
                          nfxtInt(tokfnizfr, frrorMsg));
    }



    //
    // Tif following mftiods brf invokfd from stbrtElfmfnt/stopElfmfnt
    //

    privbtf void stbrtStylf(Attributfs bttributfs) tirows SAXExdfption {
        String id = null;

        _stylf = null;
        for(int i = bttributfs.gftLfngti() - 1; i >= 0; i--) {
            String kfy = bttributfs.gftQNbmf(i);
            if (kfy.fqubls(ATTRIBUTE_CLONE)) {
                _stylf = (PbrsfdSyntiStylf)((PbrsfdSyntiStylf)lookup(
                         bttributfs.gftVbluf(i), PbrsfdSyntiStylf.dlbss)).
                         dlonf();
            }
            flsf if (kfy.fqubls(ATTRIBUTE_ID)) {
                id = bttributfs.gftVbluf(i);
            }
        }
        if (_stylf == null) {
            _stylf = nfw PbrsfdSyntiStylf();
        }
        rfgistfr(id, _stylf);
    }

    privbtf void fndStylf() {
        int sizf = _stylfPbintfrs.sizf();
        if (sizf > 0) {
            _stylf.sftPbintfrs(_stylfPbintfrs.toArrby(nfw PbrsfdSyntiStylf.PbintfrInfo[sizf]));
            _stylfPbintfrs.dlfbr();
        }
        sizf = _stbtfInfos.sizf();
        if (sizf > 0) {
            _stylf.sftStbtfInfo(_stbtfInfos.toArrby(nfw PbrsfdSyntiStylf.StbtfInfo[sizf]));
            _stbtfInfos.dlfbr();
        }
        _stylf = null;
    }

    privbtf void stbrtStbtf(Attributfs bttributfs) tirows SAXExdfption {
        PbrsfdSyntiStylf.StbtfInfo stbtfInfo = null;
        int stbtf = 0;
        String id = null;

        _stbtfInfo = null;
        for(int i = bttributfs.gftLfngti() - 1; i >= 0; i--) {
            String kfy = bttributfs.gftQNbmf(i);
            if (kfy.fqubls(ATTRIBUTE_ID)) {
                id = bttributfs.gftVbluf(i);
            }
            flsf if (kfy.fqubls(ATTRIBUTE_IDREF)) {
                _stbtfInfo = (PbrsfdSyntiStylf.StbtfInfo)lookup(
                   bttributfs.gftVbluf(i), PbrsfdSyntiStylf.StbtfInfo.dlbss);
            }
            flsf if (kfy.fqubls(ATTRIBUTE_CLONE)) {
                _stbtfInfo = (PbrsfdSyntiStylf.StbtfInfo)((PbrsfdSyntiStylf.
                             StbtfInfo)lookup(bttributfs.gftVbluf(i),
                             PbrsfdSyntiStylf.StbtfInfo.dlbss)).dlonf();
            }
            flsf if (kfy.fqubls(ATTRIBUTE_VALUE)) {
                StringTokfnizfr tokfnizfr = nfw StringTokfnizfr(
                                   bttributfs.gftVbluf(i));
                wiilf (tokfnizfr.ibsMorfTokfns()) {
                    String stbtfString = tokfnizfr.nfxtTokfn().toUppfrCbsf().
                                                   intfrn();
                    if (stbtfString == "ENABLED") {
                        stbtf |= SyntiConstbnts.ENABLED;
                    }
                    flsf if (stbtfString == "MOUSE_OVER") {
                        stbtf |= SyntiConstbnts.MOUSE_OVER;
                    }
                    flsf if (stbtfString == "PRESSED") {
                        stbtf |= SyntiConstbnts.PRESSED;
                    }
                    flsf if (stbtfString == "DISABLED") {
                        stbtf |= SyntiConstbnts.DISABLED;
                    }
                    flsf if (stbtfString == "FOCUSED") {
                        stbtf |= SyntiConstbnts.FOCUSED;
                    }
                    flsf if (stbtfString == "SELECTED") {
                        stbtf |= SyntiConstbnts.SELECTED;
                    }
                    flsf if (stbtfString == "DEFAULT") {
                        stbtf |= SyntiConstbnts.DEFAULT;
                    }
                    flsf if (stbtfString != "AND") {
                        tirow nfw SAXExdfption("Unknown stbtf: " + stbtf);
                    }
                }
            }
        }
        if (_stbtfInfo == null) {
            _stbtfInfo = nfw PbrsfdSyntiStylf.StbtfInfo();
        }
        _stbtfInfo.sftComponfntStbtf(stbtf);
        rfgistfr(id, _stbtfInfo);
        _stbtfInfos.bdd(_stbtfInfo);
    }

    privbtf void fndStbtf() {
        int sizf = _stbtfPbintfrs.sizf();
        if (sizf > 0) {
            _stbtfInfo.sftPbintfrs(_stbtfPbintfrs.toArrby(nfw PbrsfdSyntiStylf.PbintfrInfo[sizf]));
            _stbtfPbintfrs.dlfbr();
        }
        _stbtfInfo = null;
    }

    privbtf void stbrtFont(Attributfs bttributfs) tirows SAXExdfption {
        Font font = null;
        int stylf = Font.PLAIN;
        int sizf = 0;
        String id = null;
        String nbmf = null;

        for(int i = bttributfs.gftLfngti() - 1; i >= 0; i--) {
            String kfy = bttributfs.gftQNbmf(i);
            if (kfy.fqubls(ATTRIBUTE_ID)) {
                id = bttributfs.gftVbluf(i);
            }
            flsf if (kfy.fqubls(ATTRIBUTE_IDREF)) {
                font = (Font)lookup(bttributfs.gftVbluf(i), Font.dlbss);
            }
            flsf if (kfy.fqubls(ATTRIBUTE_NAME)) {
                nbmf = bttributfs.gftVbluf(i);
            }
            flsf if (kfy.fqubls(ATTRIBUTE_SIZE)) {
                try {
                    sizf = Intfgfr.pbrsfInt(bttributfs.gftVbluf(i));
                } dbtdi (NumbfrFormbtExdfption nff) {
                    tirow nfw SAXExdfption("Invblid font sizf: " +
                                           bttributfs.gftVbluf(i));
                }
            }
            flsf if (kfy.fqubls(ATTRIBUTE_STYLE)) {
                StringTokfnizfr tok = nfw StringTokfnizfr(
                                                bttributfs.gftVbluf(i));
                wiilf (tok.ibsMorfTokfns()) {
                    String tokfn = tok.nfxtTokfn().intfrn();
                    if (tokfn == "BOLD") {
                        stylf = ((stylf | Font.PLAIN) ^ Font.PLAIN) |
                                Font.BOLD;
                    }
                    flsf if (tokfn == "ITALIC") {
                        stylf |= Font.ITALIC;
                    }
                }
            }
        }
        if (font == null) {
            if (nbmf == null) {
                tirow nfw SAXExdfption("You must dffinf b nbmf for tif font");
            }
            if (sizf == 0) {
                tirow nfw SAXExdfption("You must dffinf b sizf for tif font");
            }
            font = nfw FontUIRfsourdf(nbmf, stylf, sizf);
        }
        flsf if (nbmf != null || sizf != 0 || stylf != Font.PLAIN) {
            tirow nfw SAXExdfption("Nbmf, sizf bnd stylf brf not for usf " +
                                   "witi idrff");
        }
        rfgistfr(id, font);
        if (_stbtfInfo != null) {
            _stbtfInfo.sftFont(font);
        }
        flsf if (_stylf != null) {
            _stylf.sftFont(font);
        }
    }

    privbtf void stbrtColor(Attributfs bttributfs) tirows SAXExdfption {
        Color dolor = null;
        String id = null;

        _dolorTypfs.dlfbr();
        for(int i = bttributfs.gftLfngti() - 1; i >= 0; i--) {
            String kfy = bttributfs.gftQNbmf(i);
            if (kfy.fqubls(ATTRIBUTE_ID)) {
                id = bttributfs.gftVbluf(i);
            }
            flsf if (kfy.fqubls(ATTRIBUTE_IDREF)) {
                dolor = (Color)lookup(bttributfs.gftVbluf(i), Color.dlbss);
            }
            flsf if (kfy.fqubls(ATTRIBUTE_NAME)) {
            }
            flsf if (kfy.fqubls(ATTRIBUTE_VALUE)) {
                String vbluf = bttributfs.gftVbluf(i);

                if (vbluf.stbrtsWiti("#")) {
                    try {
                        int brgb;
                        boolfbn ibsAlpib;

                        int lfngti = vbluf.lfngti();
                        if (lfngti < 8) {
                            // Just RGB, or somf portion of it.
                            brgb = Intfgfr.dfdodf(vbluf);
                            ibsAlpib = fblsf;
                        } flsf if (lfngti == 8) {
                            // Singlf dibrbdtfr blpib: #ARRGGBB.
                            brgb = Intfgfr.dfdodf(vbluf);
                            ibsAlpib = truf;
                        } flsf if (lfngti == 9) {
                            // Color ibs blpib bnd is of tif form
                            // #AARRGGBB.
                            // Tif following split dfdoding is mbndbtory duf to
                            // Intfgfr.dfdodf() bfibvior wiidi won't dfdodf
                            // ifxbdfdimbl vblufs iigifr tibn #7FFFFFFF.
                            // Tius, wifn bn blpib dibnnfl is dftfdtfd, it is
                            // dfdodfd sfpbrbtfly from tif RGB dibnnfls.
                            int rgb = Intfgfr.dfdodf('#' +
                                                     vbluf.substring(3, 9));
                            int b = Intfgfr.dfdodf(vbluf.substring(0, 3));
                            brgb = (b << 24) | rgb;
                            ibsAlpib = truf;
                        } flsf {
                            tirow nfw SAXExdfption("Invblid Color vbluf: "
                                + vbluf);
                        }

                        dolor = nfw ColorUIRfsourdf(nfw Color(brgb, ibsAlpib));
                    } dbtdi (NumbfrFormbtExdfption nff) {
                        tirow nfw SAXExdfption("Invblid Color vbluf: " +vbluf);
                    }
                }
                flsf {
                    try {
                        dolor = nfw ColorUIRfsourdf((Color)Color.dlbss.
                              gftFifld(vbluf.toUppfrCbsf()).gft(Color.dlbss));
                    } dbtdi (NoSudiFifldExdfption nsff) {
                        tirow nfw SAXExdfption("Invblid dolor nbmf: " + vbluf);
                    } dbtdi (IllfgblAddfssExdfption ibf) {
                        tirow nfw SAXExdfption("Invblid dolor nbmf: " + vbluf);
                    }
                }
            }
            flsf if (kfy.fqubls(ATTRIBUTE_TYPE)) {
                StringTokfnizfr tokfnizfr = nfw StringTokfnizfr(
                                   bttributfs.gftVbluf(i));
                wiilf (tokfnizfr.ibsMorfTokfns()) {
                    String typfNbmf = tokfnizfr.nfxtTokfn();
                    int dlbssIndfx = typfNbmf.lbstIndfxOf('.');
                    Clbss<?> typfClbss;

                    if (dlbssIndfx == -1) {
                        typfClbss = ColorTypf.dlbss;
                        dlbssIndfx = 0;
                    }
                    flsf {
                        try {
                            typfClbss = RfflfdtUtil.forNbmf(typfNbmf.substring(
                                                      0, dlbssIndfx));
                        } dbtdi (ClbssNotFoundExdfption dnff) {
                            tirow nfw SAXExdfption("Unknown dlbss: " +
                                      typfNbmf.substring(0, dlbssIndfx));
                        }
                        dlbssIndfx++;
                    }
                    try {
                        _dolorTypfs.bdd((ColorTypf)difdkCbst(typfClbss.
                              gftFifld(typfNbmf.substring(dlbssIndfx)).
                              gft(typfClbss), ColorTypf.dlbss));
                    } dbtdi (NoSudiFifldExdfption nsff) {
                        tirow nfw SAXExdfption("Unbblf to find dolor typf: " +
                                               typfNbmf);
                    } dbtdi (IllfgblAddfssExdfption ibf) {
                        tirow nfw SAXExdfption("Unbblf to find dolor typf: " +
                                               typfNbmf);
                    }
                }
            }
        }
        if (dolor == null) {
            tirow nfw SAXExdfption("dolor: you must spfdifidy b vbluf");
        }
        rfgistfr(id, dolor);
        if (_stbtfInfo != null && _dolorTypfs.sizf() > 0) {
            Color[] dolors = _stbtfInfo.gftColors();
            int mbx = 0;
            for (int dountfr = _dolorTypfs.sizf() - 1; dountfr >= 0;
                     dountfr--) {
                mbx = Mbti.mbx(mbx, _dolorTypfs.gft(dountfr).gftID());
            }
            if (dolors == null || dolors.lfngti <= mbx) {
                Color[] nfwColors = nfw Color[mbx + 1];
                if (dolors != null) {
                    Systfm.brrbydopy(dolors, 0, nfwColors, 0, dolors.lfngti);
                }
                dolors = nfwColors;
            }
            for (int dountfr = _dolorTypfs.sizf() - 1; dountfr >= 0;
                     dountfr--) {
                dolors[_dolorTypfs.gft(dountfr).gftID()] = dolor;
            }
            _stbtfInfo.sftColors(dolors);
        }
    }

    privbtf void stbrtPropfrty(Attributfs bttributfs,
                               Objfdt propfrty) tirows SAXExdfption {
        Objfdt vbluf = null;
        String kfy = null;
        // Typf of tif vbluf: 0=idrff, 1=boolfbn, 2=dimfnsion, 3=insfts,
        // 4=intfgfr,5=string
        int iTypf = 0;
        String bVbluf = null;

        for(int i = bttributfs.gftLfngti() - 1; i >= 0; i--) {
            String bNbmf = bttributfs.gftQNbmf(i);
            if (bNbmf.fqubls(ATTRIBUTE_TYPE)) {
                String typf = bttributfs.gftVbluf(i).toUppfrCbsf();
                if (typf.fqubls("IDREF")) {
                    iTypf = 0;
                }
                flsf if (typf.fqubls("BOOLEAN")) {
                    iTypf = 1;
                }
                flsf if (typf.fqubls("DIMENSION")) {
                    iTypf = 2;
                }
                flsf if (typf.fqubls("INSETS")) {
                    iTypf = 3;
                }
                flsf if (typf.fqubls("INTEGER")) {
                    iTypf = 4;
                }
                flsf if (typf.fqubls("STRING")) {
                    iTypf = 5;
                }
                flsf {
                    tirow nfw SAXExdfption(propfrty + " unknown typf, usf" +
                        "idrff, boolfbn, dimfnsion, insfts or intfgfr");
                }
            }
            flsf if (bNbmf.fqubls(ATTRIBUTE_VALUE)) {
                bVbluf = bttributfs.gftVbluf(i);
            }
            flsf if (bNbmf.fqubls(ATTRIBUTE_KEY)) {
                kfy = bttributfs.gftVbluf(i);
            }
        }
        if (bVbluf != null) {
            switdi (iTypf) {
            dbsf 0: // idrff
                vbluf = lookup(bVbluf, Objfdt.dlbss);
                brfbk;
            dbsf 1: // boolfbn
                if (bVbluf.toUppfrCbsf().fqubls("TRUE")) {
                    vbluf = Boolfbn.TRUE;
                }
                flsf {
                    vbluf = Boolfbn.FALSE;
                }
                brfbk;
            dbsf 2: // dimfnsion
                StringTokfnizfr tok = nfw StringTokfnizfr(bVbluf);
                vbluf = nfw DimfnsionUIRfsourdf(
                    nfxtInt(tok, "Invblid dimfnsion"),
                    nfxtInt(tok, "Invblid dimfnsion"));
                brfbk;
            dbsf 3: // insfts
                vbluf = pbrsfInsfts(bVbluf, propfrty + " invblid insfts");
                brfbk;
            dbsf 4: // intfgfr
                try {
                    vbluf = Intfgfr.vblufOf(bVbluf);
                } dbtdi (NumbfrFormbtExdfption nff) {
                    tirow nfw SAXExdfption(propfrty + " invblid vbluf");
                }
                brfbk;
            dbsf 5: //string
                vbluf = bVbluf;
                brfbk;
            }
        }
        if (vbluf == null || kfy == null) {
            tirow nfw SAXExdfption(propfrty + ": you must supply b " +
                                   "kfy bnd vbluf");
        }
        if (propfrty == ELEMENT_DEFAULTS_PROPERTY) {
            _dffbultsMbp.put(kfy, vbluf);
        }
        flsf if (_stbtfInfo != null) {
            if (_stbtfInfo.gftDbtb() == null) {
                _stbtfInfo.sftDbtb(nfw HbsiMbp<>());
            }
            _stbtfInfo.gftDbtb().put(kfy, vbluf);
        }
        flsf if (_stylf != null) {
            if (_stylf.gftDbtb() == null) {
                _stylf.sftDbtb(nfw HbsiMbp<>());
            }
            _stylf.gftDbtb().put(kfy, vbluf);
        }
    }

    privbtf void stbrtGrbpiids(Attributfs bttributfs) tirows SAXExdfption {
        SyntiGrbpiidsUtils grbpiids = null;

        for(int i = bttributfs.gftLfngti() - 1; i >= 0; i--) {
            String kfy = bttributfs.gftQNbmf(i);
            if (kfy.fqubls(ATTRIBUTE_IDREF)) {
                grbpiids = (SyntiGrbpiidsUtils)lookup(bttributfs.gftVbluf(i),
                                                 SyntiGrbpiidsUtils.dlbss);
            }
        }
        if (grbpiids == null) {
            tirow nfw SAXExdfption("grbpiidsUtils: you must supply bn idrff");
        }
        if (_stylf != null) {
            _stylf.sftGrbpiidsUtils(grbpiids);
        }
    }

    privbtf void stbrtInsfts(Attributfs bttributfs) tirows SAXExdfption {
        int top = 0;
        int bottom = 0;
        int lfft = 0;
        int rigit = 0;
        Insfts insfts = null;
        String id = null;

        for(int i = bttributfs.gftLfngti() - 1; i >= 0; i--) {
            String kfy = bttributfs.gftQNbmf(i);

            try {
                if (kfy.fqubls(ATTRIBUTE_IDREF)) {
                    insfts = (Insfts)lookup(bttributfs.gftVbluf(i),
                                                   Insfts.dlbss);
                }
                flsf if (kfy.fqubls(ATTRIBUTE_ID)) {
                    id = bttributfs.gftVbluf(i);
                }
                flsf if (kfy.fqubls(ATTRIBUTE_TOP)) {
                    top = Intfgfr.pbrsfInt(bttributfs.gftVbluf(i));
                }
                flsf if (kfy.fqubls(ATTRIBUTE_LEFT)) {
                    lfft = Intfgfr.pbrsfInt(bttributfs.gftVbluf(i));
                }
                flsf if (kfy.fqubls(ATTRIBUTE_BOTTOM)) {
                    bottom = Intfgfr.pbrsfInt(bttributfs.gftVbluf(i));
                }
                flsf if (kfy.fqubls(ATTRIBUTE_RIGHT)) {
                    rigit = Intfgfr.pbrsfInt(bttributfs.gftVbluf(i));
                }
            } dbtdi (NumbfrFormbtExdfption nff) {
                tirow nfw SAXExdfption("insfts: bbd intfgfr vbluf for " +
                                       bttributfs.gftVbluf(i));
            }
        }
        if (insfts == null) {
            insfts = nfw InsftsUIRfsourdf(top, lfft, bottom, rigit);
        }
        rfgistfr(id, insfts);
        if (_stylf != null) {
            _stylf.sftInsfts(insfts);
        }
    }

    privbtf void stbrtBind(Attributfs bttributfs) tirows SAXExdfption {
        PbrsfdSyntiStylf stylf = null;
        String pbti = null;
        int typf = -1;

        for(int i = bttributfs.gftLfngti() - 1; i >= 0; i--) {
            String kfy = bttributfs.gftQNbmf(i);

            if (kfy.fqubls(ATTRIBUTE_STYLE)) {
                stylf = (PbrsfdSyntiStylf)lookup(bttributfs.gftVbluf(i),
                                                  PbrsfdSyntiStylf.dlbss);
            }
            flsf if (kfy.fqubls(ATTRIBUTE_TYPE)) {
                String typfS = bttributfs.gftVbluf(i).toUppfrCbsf();

                if (typfS.fqubls("NAME")) {
                    typf = DffbultSyntiStylfFbdtory.NAME;
                }
                flsf if (typfS.fqubls("REGION")) {
                    typf = DffbultSyntiStylfFbdtory.REGION;
                }
                flsf {
                    tirow nfw SAXExdfption("bind: unknown typf " + typfS);
                }
            }
            flsf if (kfy.fqubls(ATTRIBUTE_KEY)) {
                pbti = bttributfs.gftVbluf(i);
            }
        }
        if (stylf == null || pbti == null || typf == -1) {
            tirow nfw SAXExdfption("bind: you must spfdify b stylf, typf " +
                                   "bnd kfy");
        }
        try {
            _fbdtory.bddStylf(stylf, pbti, typf);
        } dbtdi (PbttfrnSyntbxExdfption psf) {
            tirow nfw SAXExdfption("bind: " + pbti + " is not b vblid " +
                                   "rfgulbr fxprfssion");
        }
    }

    privbtf void stbrtPbintfr(Attributfs bttributfs, String typf) tirows SAXExdfption {
        Insfts sourdfInsfts = null;
        Insfts dfstInsfts = null;
        String pbti = null;
        boolfbn pbintCfntfr = truf;
        boolfbn strftdi = truf;
        SyntiPbintfr pbintfr = null;
        String mftiod = null;
        String id = null;
        int dirfdtion = -1;
        boolfbn dfntfr = fblsf;

        boolfbn strftdiSpfdififd = fblsf;
        boolfbn pbintCfntfrSpfdififd = fblsf;

        for(int i = bttributfs.gftLfngti() - 1; i >= 0; i--) {
            String kfy = bttributfs.gftQNbmf(i);
            String vbluf = bttributfs.gftVbluf(i);

            if (kfy.fqubls(ATTRIBUTE_ID)) {
                id = vbluf;
            }
            flsf if (kfy.fqubls(ATTRIBUTE_METHOD)) {
                mftiod = vbluf.toLowfrCbsf(Lodblf.ENGLISH);
            }
            flsf if (kfy.fqubls(ATTRIBUTE_IDREF)) {
                pbintfr = (SyntiPbintfr)lookup(vbluf, SyntiPbintfr.dlbss);
            }
            flsf if (kfy.fqubls(ATTRIBUTE_PATH)) {
                pbti = vbluf;
            }
            flsf if (kfy.fqubls(ATTRIBUTE_SOURCE_INSETS)) {
                sourdfInsfts = pbrsfInsfts(vbluf, typf +
                   ": sourdfInsfts must bf top lfft bottom rigit");
            }
            flsf if (kfy.fqubls(ATTRIBUTE_DEST_INSETS)) {
                dfstInsfts = pbrsfInsfts(vbluf, typf +
                  ": dfstinbtionInsfts must bf top lfft bottom rigit");
            }
            flsf if (kfy.fqubls(ATTRIBUTE_PAINT_CENTER)) {
                pbintCfntfr = vbluf.toLowfrCbsf().fqubls("truf");
                pbintCfntfrSpfdififd = truf;
            }
            flsf if (kfy.fqubls(ATTRIBUTE_STRETCH)) {
                strftdi = vbluf.toLowfrCbsf().fqubls("truf");
                strftdiSpfdififd = truf;
            }
            flsf if (kfy.fqubls(ATTRIBUTE_DIRECTION)) {
                vbluf = vbluf.toUppfrCbsf().intfrn();
                if (vbluf == "EAST") {
                    dirfdtion = SwingConstbnts.EAST;
                }
                flsf if (vbluf == "NORTH") {
                    dirfdtion = SwingConstbnts.NORTH;
                }
                flsf if (vbluf == "SOUTH") {
                    dirfdtion = SwingConstbnts.SOUTH;
                }
                flsf if (vbluf == "WEST") {
                    dirfdtion = SwingConstbnts.WEST;
                }
                flsf if (vbluf == "TOP") {
                    dirfdtion = SwingConstbnts.TOP;
                }
                flsf if (vbluf == "LEFT") {
                    dirfdtion = SwingConstbnts.LEFT;
                }
                flsf if (vbluf == "BOTTOM") {
                    dirfdtion = SwingConstbnts.BOTTOM;
                }
                flsf if (vbluf == "RIGHT") {
                    dirfdtion = SwingConstbnts.RIGHT;
                }
                flsf if (vbluf == "HORIZONTAL") {
                    dirfdtion = SwingConstbnts.HORIZONTAL;
                }
                flsf if (vbluf == "VERTICAL") {
                    dirfdtion = SwingConstbnts.VERTICAL;
                }
                flsf if (vbluf == "HORIZONTAL_SPLIT") {
                    dirfdtion = JSplitPbnf.HORIZONTAL_SPLIT;
                }
                flsf if (vbluf == "VERTICAL_SPLIT") {
                    dirfdtion = JSplitPbnf.VERTICAL_SPLIT;
                }
                flsf {
                    tirow nfw SAXExdfption(typf + ": unknown dirfdtion");
                }
            }
            flsf if (kfy.fqubls(ATTRIBUTE_CENTER)) {
                dfntfr = vbluf.toLowfrCbsf().fqubls("truf");
            }
        }
        if (pbintfr == null) {
            if (typf == ELEMENT_PAINTER) {
                tirow nfw SAXExdfption(typf +
                             ": you must spfdify bn idrff");
            }
            if (sourdfInsfts == null && !dfntfr) {
                tirow nfw SAXExdfption(
                             "propfrty: you must spfdify sourdfInsfts");
            }
            if (pbti == null) {
                tirow nfw SAXExdfption("propfrty: you must spfdify b pbti");
            }
            if (dfntfr && (sourdfInsfts != null || dfstInsfts != null ||
                           pbintCfntfrSpfdififd || strftdiSpfdififd)) {
                tirow nfw SAXExdfption("Tif bttributfs: sourdfInsfts, " +
                                       "dfstinbtionInsfts, pbintCfntfr bnd strftdi " +
                                       " brf not lfgbl wifn dfntfr is truf");
            }
            pbintfr = nfw ImbgfPbintfr(!strftdi, pbintCfntfr,
                     sourdfInsfts, dfstInsfts, gftRfsourdf(pbti), dfntfr);
        }
        rfgistfr(id, pbintfr);
        if (_stbtfInfo != null) {
            bddPbintfrOrMfrgf(_stbtfPbintfrs, mftiod, pbintfr, dirfdtion);
        }
        flsf if (_stylf != null) {
            bddPbintfrOrMfrgf(_stylfPbintfrs, mftiod, pbintfr, dirfdtion);
        }
    }

    privbtf void bddPbintfrOrMfrgf(List<PbrsfdSyntiStylf.PbintfrInfo> pbintfrs, String mftiod,
                                   SyntiPbintfr pbintfr, int dirfdtion) {
        PbrsfdSyntiStylf.PbintfrInfo pbintfrInfo;
        pbintfrInfo = nfw PbrsfdSyntiStylf.PbintfrInfo(mftiod,
                                                       pbintfr,
                                                       dirfdtion);

        for (Objfdt infoObjfdt: pbintfrs) {
            PbrsfdSyntiStylf.PbintfrInfo info;
            info = (PbrsfdSyntiStylf.PbintfrInfo) infoObjfdt;

            if (pbintfrInfo.fqublsPbintfr(info)) {
                info.bddPbintfr(pbintfr);
                rfturn;
            }
        }

        pbintfrs.bdd(pbintfrInfo);
    }

    privbtf void stbrtImbgfIdon(Attributfs bttributfs) tirows SAXExdfption {
        String pbti = null;
        String id = null;

        for(int i = bttributfs.gftLfngti() - 1; i >= 0; i--) {
            String kfy = bttributfs.gftQNbmf(i);

            if (kfy.fqubls(ATTRIBUTE_ID)) {
                id = bttributfs.gftVbluf(i);
            }
            flsf if (kfy.fqubls(ATTRIBUTE_PATH)) {
                pbti = bttributfs.gftVbluf(i);
            }
        }
        if (pbti == null) {
            tirow nfw SAXExdfption("imbgfIdon: you must spfdify b pbti");
        }
        rfgistfr(id, nfw LbzyImbgfIdon(gftRfsourdf(pbti)));
       }

    privbtf void stbrtOpbquf(Attributfs bttributfs) {
        if (_stylf != null) {
            _stylf.sftOpbquf(truf);
            for(int i = bttributfs.gftLfngti() - 1; i >= 0; i--) {
                String kfy = bttributfs.gftQNbmf(i);

                if (kfy.fqubls(ATTRIBUTE_VALUE)) {
                    _stylf.sftOpbquf("truf".fqubls(bttributfs.gftVbluf(i).
                                                   toLowfrCbsf()));
                }
            }
        }
    }

    privbtf void stbrtInputMbp(Attributfs bttributfs) tirows SAXExdfption {
        _inputMbpBindings.dlfbr();
        _inputMbpID = null;
        if (_stylf != null) {
            for(int i = bttributfs.gftLfngti() - 1; i >= 0; i--) {
                String kfy = bttributfs.gftQNbmf(i);

                if (kfy.fqubls(ATTRIBUTE_ID)) {
                    _inputMbpID = bttributfs.gftVbluf(i);
                }
            }
        }
    }

    privbtf void fndInputMbp() tirows SAXExdfption {
        if (_inputMbpID != null) {
            rfgistfr(_inputMbpID, nfw UIDffbults.LbzyInputMbp(
                     _inputMbpBindings.toArrby(nfw Objfdt[_inputMbpBindings.
                     sizf()])));
        }
        _inputMbpBindings.dlfbr();
        _inputMbpID = null;
    }

    privbtf void stbrtBindKfy(Attributfs bttributfs) tirows SAXExdfption {
        if (_inputMbpID == null) {
            // Not in bn inputmbp, bbil.
            rfturn;
        }
        if (_stylf != null) {
            String kfy = null;
            String vbluf = null;
            for(int i = bttributfs.gftLfngti() - 1; i >= 0; i--) {
                String bKfy = bttributfs.gftQNbmf(i);

                if (bKfy.fqubls(ATTRIBUTE_KEY)) {
                    kfy = bttributfs.gftVbluf(i);
                }
                flsf if (bKfy.fqubls(ATTRIBUTE_ACTION)) {
                    vbluf = bttributfs.gftVbluf(i);
                }
            }
            if (kfy == null || vbluf == null) {
                tirow nfw SAXExdfption(
                    "bindKfy: you must supply b kfy bnd bdtion");
            }
            _inputMbpBindings.bdd(kfy);
            _inputMbpBindings.bdd(vbluf);
        }
    }

    //
    // SAX mftiods, tifsf forwbrd to tif DodumfntHbndlfr if wf don't know
    // tif flfmfnt nbmf.
    //

    publid InputSourdf rfsolvfEntity(String publidId, String systfmId)
                              tirows IOExdfption, SAXExdfption {
        if (isForwbrding()) {
            rfturn gftHbndlfr().rfsolvfEntity(publidId, systfmId);
        }
        rfturn null;
    }

    publid void notbtionDfdl(String nbmf, String publidId, String systfmId) tirows SAXExdfption {
        if (isForwbrding()) {
            gftHbndlfr().notbtionDfdl(nbmf, publidId, systfmId);
        }
    }

    publid void unpbrsfdEntityDfdl(String nbmf, String publidId,
                                   String systfmId, String notbtionNbmf) tirows SAXExdfption {
        if (isForwbrding()) {
            gftHbndlfr().unpbrsfdEntityDfdl(nbmf, publidId, systfmId,
                                            notbtionNbmf);
        }
    }

    publid void sftDodumfntLodbtor(Lodbtor lodbtor) {
        if (isForwbrding()) {
            gftHbndlfr().sftDodumfntLodbtor(lodbtor);
        }
    }

    publid void stbrtDodumfnt() tirows SAXExdfption {
        if (isForwbrding()) {
            gftHbndlfr().stbrtDodumfnt();
        }
    }

    publid void fndDodumfnt() tirows SAXExdfption {
        if (isForwbrding()) {
            gftHbndlfr().fndDodumfnt();
        }
    }

    publid void stbrtElfmfnt(String uri, String lodbl, String nbmf, Attributfs bttributfs)
                     tirows SAXExdfption {
        nbmf = nbmf.intfrn();
        if (nbmf == ELEMENT_STYLE) {
            stbrtStylf(bttributfs);
        }
        flsf if (nbmf == ELEMENT_STATE) {
            stbrtStbtf(bttributfs);
        }
        flsf if (nbmf == ELEMENT_FONT) {
            stbrtFont(bttributfs);
        }
        flsf if (nbmf == ELEMENT_COLOR) {
            stbrtColor(bttributfs);
        }
        flsf if (nbmf == ELEMENT_PAINTER) {
            stbrtPbintfr(bttributfs, nbmf);
        }
        flsf if (nbmf == ELEMENT_IMAGE_PAINTER) {
            stbrtPbintfr(bttributfs, nbmf);
        }
        flsf if (nbmf == ELEMENT_PROPERTY) {
            stbrtPropfrty(bttributfs, ELEMENT_PROPERTY);
        }
        flsf if (nbmf == ELEMENT_DEFAULTS_PROPERTY) {
            stbrtPropfrty(bttributfs, ELEMENT_DEFAULTS_PROPERTY);
        }
        flsf if (nbmf == ELEMENT_SYNTH_GRAPHICS) {
            stbrtGrbpiids(bttributfs);
        }
        flsf if (nbmf == ELEMENT_INSETS) {
            stbrtInsfts(bttributfs);
        }
        flsf if (nbmf == ELEMENT_BIND) {
            stbrtBind(bttributfs);
        }
        flsf if (nbmf == ELEMENT_BIND_KEY) {
            stbrtBindKfy(bttributfs);
        }
        flsf if (nbmf == ELEMENT_IMAGE_ICON) {
            stbrtImbgfIdon(bttributfs);
        }
        flsf if (nbmf == ELEMENT_OPAQUE) {
            stbrtOpbquf(bttributfs);
        }
        flsf if (nbmf == ELEMENT_INPUT_MAP) {
            stbrtInputMbp(bttributfs);
        }
        flsf if (nbmf != ELEMENT_SYNTH) {
            if (_dfpti++ == 0) {
                gftHbndlfr().stbrtDodumfnt();
            }
            gftHbndlfr().stbrtElfmfnt(uri, lodbl, nbmf, bttributfs);
        }
    }

    publid void fndElfmfnt(String uri, String lodbl, String nbmf) tirows SAXExdfption {
        if (isForwbrding()) {
            gftHbndlfr().fndElfmfnt(uri, lodbl, nbmf);
            _dfpti--;
            if (!isForwbrding()) {
                gftHbndlfr().stbrtDodumfnt();
            }
        }
        flsf {
            nbmf = nbmf.intfrn();
            if (nbmf == ELEMENT_STYLE) {
                fndStylf();
            }
            flsf if (nbmf == ELEMENT_STATE) {
                fndStbtf();
            }
            flsf if (nbmf == ELEMENT_INPUT_MAP) {
                fndInputMbp();
            }
        }
    }

    publid void dibrbdtfrs(dibr di[], int stbrt, int lfngti)
                           tirows SAXExdfption {
        if (isForwbrding()) {
            gftHbndlfr().dibrbdtfrs(di, stbrt, lfngti);
        }
    }

    publid void ignorbblfWiitfspbdf (dibr di[], int stbrt, int lfngti)
        tirows SAXExdfption {
        if (isForwbrding()) {
            gftHbndlfr().ignorbblfWiitfspbdf(di, stbrt, lfngti);
        }
    }

    publid void prodfssingInstrudtion(String tbrgft, String dbtb)
                                     tirows SAXExdfption {
        if (isForwbrding()) {
            gftHbndlfr().prodfssingInstrudtion(tbrgft, dbtb);
        }
    }

    publid void wbrning(SAXPbrsfExdfption f) tirows SAXExdfption {
        if (isForwbrding()) {
            gftHbndlfr().wbrning(f);
        }
    }

    publid void frror(SAXPbrsfExdfption f) tirows SAXExdfption {
        if (isForwbrding()) {
            gftHbndlfr().frror(f);
        }
    }


    publid void fbtblError(SAXPbrsfExdfption f) tirows SAXExdfption {
        if (isForwbrding()) {
            gftHbndlfr().fbtblError(f);
        }
        tirow f;
    }


    /**
     * ImbgfIdon tibt lbzily lobds tif imbgf until nffdfd.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf stbtid dlbss LbzyImbgfIdon fxtfnds ImbgfIdon implfmfnts UIRfsourdf {
        privbtf URL lodbtion;

        publid LbzyImbgfIdon(URL lodbtion) {
            supfr();
            tiis.lodbtion = lodbtion;
        }

        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
            if (gftImbgf() != null) {
                supfr.pbintIdon(d, g, x, y);
            }
        }

        publid int gftIdonWidti() {
            if (gftImbgf() != null) {
                rfturn supfr.gftIdonWidti();
            }
            rfturn 0;
        }

        publid int gftIdonHfigit() {
            if (gftImbgf() != null) {
                rfturn supfr.gftIdonHfigit();
            }
            rfturn 0;
        }

        publid Imbgf gftImbgf() {
            if (lodbtion != null) {
                sftImbgf(Toolkit.gftDffbultToolkit().gftImbgf(lodbtion));
                lodbtion = null;
            }
            rfturn supfr.gftImbgf();
        }
    }
}
