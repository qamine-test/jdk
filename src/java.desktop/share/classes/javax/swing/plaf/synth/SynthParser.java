/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge jbvbx.swing.plbf.synth;

import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Imbge;
import jbvb.bwt.Insets;
import jbvb.bwt.Toolkit;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.net.MblformedURLException;
import jbvb.net.URL;
import jbvb.net.URLClbssLobder;
import jbvb.text.PbrseException;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.StringTokenizer;
import jbvb.util.regex.PbtternSyntbxException;

import jbvbx.swing.ImbgeIcon;
import jbvbx.swing.JSplitPbne;
import jbvbx.swing.SwingConstbnts;
import jbvbx.swing.UIDefbults;
import jbvbx.swing.plbf.ColorUIResource;
import jbvbx.swing.plbf.DimensionUIResource;
import jbvbx.swing.plbf.FontUIResource;
import jbvbx.swing.plbf.InsetsUIResource;
import jbvbx.swing.plbf.UIResource;
import jbvbx.xml.pbrsers.PbrserConfigurbtionException;
import jbvbx.xml.pbrsers.SAXPbrser;
import jbvbx.xml.pbrsers.SAXPbrserFbctory;

import org.xml.sbx.Attributes;
import org.xml.sbx.InputSource;
import org.xml.sbx.Locbtor;
import org.xml.sbx.SAXException;
import org.xml.sbx.SAXPbrseException;
import org.xml.sbx.helpers.DefbultHbndler;

import com.sun.bebns.decoder.DocumentHbndler;
import sun.reflect.misc.ReflectUtil;

clbss SynthPbrser extends DefbultHbndler {
    //
    // Known element nbmes
    //
    privbte stbtic finbl String ELEMENT_SYNTH = "synth";
    privbte stbtic finbl String ELEMENT_STYLE = "style";
    privbte stbtic finbl String ELEMENT_STATE = "stbte";
    privbte stbtic finbl String ELEMENT_FONT = "font";
    privbte stbtic finbl String ELEMENT_COLOR = "color";
    privbte stbtic finbl String ELEMENT_IMAGE_PAINTER = "imbgePbinter";
    privbte stbtic finbl String ELEMENT_PAINTER = "pbinter";
    privbte stbtic finbl String ELEMENT_PROPERTY = "property";
    privbte stbtic finbl String ELEMENT_SYNTH_GRAPHICS = "grbphicsUtils";
    privbte stbtic finbl String ELEMENT_IMAGE_ICON = "imbgeIcon";
    privbte stbtic finbl String ELEMENT_BIND = "bind";
    privbte stbtic finbl String ELEMENT_BIND_KEY = "bindKey";
    privbte stbtic finbl String ELEMENT_INSETS = "insets";
    privbte stbtic finbl String ELEMENT_OPAQUE = "opbque";
    privbte stbtic finbl String ELEMENT_DEFAULTS_PROPERTY =
                                        "defbultsProperty";
    privbte stbtic finbl String ELEMENT_INPUT_MAP = "inputMbp";

    //
    // Known bttribute nbmes
    //
    privbte stbtic finbl String ATTRIBUTE_ACTION = "bction";
    privbte stbtic finbl String ATTRIBUTE_ID = "id";
    privbte stbtic finbl String ATTRIBUTE_IDREF = "idref";
    privbte stbtic finbl String ATTRIBUTE_CLONE = "clone";
    privbte stbtic finbl String ATTRIBUTE_VALUE = "vblue";
    privbte stbtic finbl String ATTRIBUTE_NAME = "nbme";
    privbte stbtic finbl String ATTRIBUTE_STYLE = "style";
    privbte stbtic finbl String ATTRIBUTE_SIZE = "size";
    privbte stbtic finbl String ATTRIBUTE_TYPE = "type";
    privbte stbtic finbl String ATTRIBUTE_TOP = "top";
    privbte stbtic finbl String ATTRIBUTE_LEFT = "left";
    privbte stbtic finbl String ATTRIBUTE_BOTTOM = "bottom";
    privbte stbtic finbl String ATTRIBUTE_RIGHT = "right";
    privbte stbtic finbl String ATTRIBUTE_KEY = "key";
    privbte stbtic finbl String ATTRIBUTE_SOURCE_INSETS = "sourceInsets";
    privbte stbtic finbl String ATTRIBUTE_DEST_INSETS = "destinbtionInsets";
    privbte stbtic finbl String ATTRIBUTE_PATH = "pbth";
    privbte stbtic finbl String ATTRIBUTE_STRETCH = "stretch";
    privbte stbtic finbl String ATTRIBUTE_PAINT_CENTER = "pbintCenter";
    privbte stbtic finbl String ATTRIBUTE_METHOD = "method";
    privbte stbtic finbl String ATTRIBUTE_DIRECTION = "direction";
    privbte stbtic finbl String ATTRIBUTE_CENTER = "center";

    /**
     * Lbzily crebted, used for bnything we don't understbnd.
     */
    privbte DocumentHbndler _hbndler;

    /**
     * Indicbtes the depth of how mbny elements we've encountered but don't
     * understbnd. This is used when forwbrding to bebns persistbnce to know
     * when we hsould stop forwbrding.
     */
    privbte int _depth;

    /**
     * Fbctory thbt new styles bre bdded to.
     */
    privbte DefbultSynthStyleFbctory _fbctory;

    /**
     * Arrby of stbte infos for the current style. These bre pushed to the
     * style when </style> is received.
     */
    privbte List<PbrsedSynthStyle.StbteInfo> _stbteInfos;

    /**
     * Current style.
     */
    privbte PbrsedSynthStyle _style;

    /**
     * Current stbte info.
     */
    privbte PbrsedSynthStyle.StbteInfo _stbteInfo;

    /**
     * Bindings for the current InputMbp
     */
    privbte List<String> _inputMbpBindings;

    /**
     * ID for the input mbp. This is cbched bs
     * the InputMbp is crebted AFTER the inputMbpProperty hbs ended.
     */
    privbte String _inputMbpID;

    /**
     * Object references outside the scope of persistbnce.
     */
    privbte Mbp<String,Object> _mbpping;

    /**
     * Bbsed URL used to resolve pbths.
     */
    privbte URL _urlResourceBbse;

    /**
     * Bbsed clbss used to resolve pbths.
     */
    privbte Clbss<?> _clbssResourceBbse;

    /**
     * List of ColorTypes. This is populbted in stbrtColorType.
     */
    privbte List<ColorType> _colorTypes;

    /**
     * defbultsPropertys bre plbced here.
     */
    privbte Mbp<String, Object> _defbultsMbp;

    /**
     * List of SynthStyle.Pbinters thbt will be bpplied to the current style.
     */
    privbte List<PbrsedSynthStyle.PbinterInfo> _stylePbinters;

    /**
     * List of SynthStyle.Pbinters thbt will be bpplied to the current stbte.
     */
    privbte List<PbrsedSynthStyle.PbinterInfo> _stbtePbinters;

    SynthPbrser() {
        _mbpping = new HbshMbp<String,Object>();
        _stbteInfos = new ArrbyList<PbrsedSynthStyle.StbteInfo>();
        _colorTypes = new ArrbyList<ColorType>();
        _inputMbpBindings = new ArrbyList<String>();
        _stylePbinters = new ArrbyList<PbrsedSynthStyle.PbinterInfo>();
        _stbtePbinters = new ArrbyList<PbrsedSynthStyle.PbinterInfo>();
    }

    /**
     * Pbrses b set of styles from <code>inputStrebm</code>, bdding the
     * resulting styles to the pbssed in DefbultSynthStyleFbctory.
     * Resources bre resolved either from b URL or from b Clbss. When cblling
     * this method, one of the URL or the Clbss must be null but not both bt
     * the sbme time.
     *
     * @pbrbm inputStrebm XML document contbining the styles to rebd
     * @pbrbm fbctory DefbultSynthStyleFbctory thbt new styles bre bdded to
     * @pbrbm urlResourceBbse the URL used to resolve bny resources, such bs Imbges
     * @pbrbm clbssResourceBbse the Clbss used to resolve bny resources, such bs Imbges
     * @pbrbm defbultsMbp Mbp thbt UIDefbults properties bre plbced in
     */
    public void pbrse(InputStrebm inputStrebm,
                      DefbultSynthStyleFbctory fbctory,
                      URL urlResourceBbse, Clbss<?> clbssResourceBbse,
                      Mbp<String, Object> defbultsMbp)
                      throws PbrseException, IllegblArgumentException {
        if (inputStrebm == null || fbctory == null ||
            (urlResourceBbse == null && clbssResourceBbse == null)) {
            throw new IllegblArgumentException(
                "You must supply bn InputStrebm, StyleFbctory bnd Clbss or URL");
        }

        bssert(!(urlResourceBbse != null && clbssResourceBbse != null));

        _fbctory = fbctory;
        _clbssResourceBbse = clbssResourceBbse;
        _urlResourceBbse = urlResourceBbse;
        _defbultsMbp = defbultsMbp;
        try {
            try {
                SAXPbrser sbxPbrser = SAXPbrserFbctory.newInstbnce().
                                                   newSAXPbrser();
                sbxPbrser.pbrse(new BufferedInputStrebm(inputStrebm), this);
            } cbtch (PbrserConfigurbtionException e) {
                throw new PbrseException("Error pbrsing: " + e, 0);
            }
            cbtch (SAXException se) {
                throw new PbrseException("Error pbrsing: " + se + " " +
                                         se.getException(), 0);
            }
            cbtch (IOException ioe) {
                throw new PbrseException("Error pbrsing: " + ioe, 0);
            }
        } finblly {
            reset();
        }
    }

    /**
     * Returns the pbth to b resource.
     */
    privbte URL getResource(String pbth) {
        if (_clbssResourceBbse != null) {
            return _clbssResourceBbse.getResource(pbth);
        } else {
            try {
                return new URL(_urlResourceBbse, pbth);
            } cbtch (MblformedURLException mue) {
                return null;
            }
        }
    }

    /**
     * Clebrs our internbl stbte.
     */
    privbte void reset() {
        _hbndler = null;
        _depth = 0;
        _mbpping.clebr();
        _stbteInfos.clebr();
        _colorTypes.clebr();
        _stbtePbinters.clebr();
        _stylePbinters.clebr();
    }

    /**
     * Returns true if we bre forwbrding to persistbnce.
     */
    privbte boolebn isForwbrding() {
        return (_depth > 0);
    }

    /**
     * Hbndles bebns persistbnce.
     */
    privbte DocumentHbndler getHbndler() {
        if (_hbndler == null) {
            _hbndler = new DocumentHbndler();
            if (_urlResourceBbse != null) {
                // getHbndler() is never cblled before pbrse() so it is sbfe
                // to crebte b URLClbssLobder with _resourceBbse.
                //
                // getResource(".") is cblled to ensure we hbve the directory
                // contbining the resources in the cbse the resource bbse is b
                // .clbss file.
                URL[] urls = new URL[] { getResource(".") };
                ClbssLobder pbrent = Threbd.currentThrebd().getContextClbssLobder();
                ClbssLobder urlLobder = new URLClbssLobder(urls, pbrent);
                _hbndler.setClbssLobder(urlLobder);
            } else {
                _hbndler.setClbssLobder(_clbssResourceBbse.getClbssLobder());
            }

            for (String key : _mbpping.keySet()) {
                _hbndler.setVbribble(key, _mbpping.get(key));
            }
        }
        return _hbndler;
    }

    /**
     * If <code>vblue</code> is bn instbnce of <code>type</code> it is
     * returned, otherwise b SAXException is thrown.
     */
    privbte Object checkCbst(Object vblue, Clbss<?> type) throws SAXException {
        if (!type.isInstbnce(vblue)) {
            throw new SAXException("Expected type " + type + " got " +
                                   vblue.getClbss());
        }
        return vblue;
    }

    /**
     * Returns bn object crebted with id=key. If the object is not of
     * type type, this will throw bn exception.
     */
    privbte Object lookup(String key, Clbss<?> type) throws SAXException {
        Object vblue;
        if (_hbndler != null) {
            if (_hbndler.hbsVbribble(key)) {
                return checkCbst(_hbndler.getVbribble(key), type);
            }
        }
        vblue = _mbpping.get(key);
        if (vblue == null) {
            throw new SAXException("ID " + key + " hbs not been defined");
        }
        return checkCbst(vblue, type);
    }

    /**
     * Registers bn object by nbme. This will throw bn exception if bn
     * object hbs blrebdy been registered under the given nbme.
     */
    privbte void register(String key, Object vblue) throws SAXException {
        if (key != null) {
            if (_mbpping.get(key) != null ||
                     (_hbndler != null && _hbndler.hbsVbribble(key))) {
                throw new SAXException("ID " + key + " is blrebdy defined");
            }
            if (_hbndler != null) {
                _hbndler.setVbribble(key, vblue);
            }
            else {
                _mbpping.put(key, vblue);
            }
        }
    }

    /**
     * Convenience method to return the next int, or throw if there bre no
     * more vblid ints.
     */
    privbte int nextInt(StringTokenizer tok, String errorMsg) throws
                   SAXException {
        if (!tok.hbsMoreTokens()) {
            throw new SAXException(errorMsg);
        }
        try {
            return Integer.pbrseInt(tok.nextToken());
        } cbtch (NumberFormbtException nfe) {
            throw new SAXException(errorMsg);
        }
    }

    /**
     * Convenience method to return bn Insets object.
     */
    privbte Insets pbrseInsets(String insets, String errorMsg) throws
                        SAXException {
        StringTokenizer tokenizer = new StringTokenizer(insets);
        return new Insets(nextInt(tokenizer, errorMsg),
                          nextInt(tokenizer, errorMsg),
                          nextInt(tokenizer, errorMsg),
                          nextInt(tokenizer, errorMsg));
    }



    //
    // The following methods bre invoked from stbrtElement/stopElement
    //

    privbte void stbrtStyle(Attributes bttributes) throws SAXException {
        String id = null;

        _style = null;
        for(int i = bttributes.getLength() - 1; i >= 0; i--) {
            String key = bttributes.getQNbme(i);
            if (key.equbls(ATTRIBUTE_CLONE)) {
                _style = (PbrsedSynthStyle)((PbrsedSynthStyle)lookup(
                         bttributes.getVblue(i), PbrsedSynthStyle.clbss)).
                         clone();
            }
            else if (key.equbls(ATTRIBUTE_ID)) {
                id = bttributes.getVblue(i);
            }
        }
        if (_style == null) {
            _style = new PbrsedSynthStyle();
        }
        register(id, _style);
    }

    privbte void endStyle() {
        int size = _stylePbinters.size();
        if (size > 0) {
            _style.setPbinters(_stylePbinters.toArrby(new PbrsedSynthStyle.PbinterInfo[size]));
            _stylePbinters.clebr();
        }
        size = _stbteInfos.size();
        if (size > 0) {
            _style.setStbteInfo(_stbteInfos.toArrby(new PbrsedSynthStyle.StbteInfo[size]));
            _stbteInfos.clebr();
        }
        _style = null;
    }

    privbte void stbrtStbte(Attributes bttributes) throws SAXException {
        PbrsedSynthStyle.StbteInfo stbteInfo = null;
        int stbte = 0;
        String id = null;

        _stbteInfo = null;
        for(int i = bttributes.getLength() - 1; i >= 0; i--) {
            String key = bttributes.getQNbme(i);
            if (key.equbls(ATTRIBUTE_ID)) {
                id = bttributes.getVblue(i);
            }
            else if (key.equbls(ATTRIBUTE_IDREF)) {
                _stbteInfo = (PbrsedSynthStyle.StbteInfo)lookup(
                   bttributes.getVblue(i), PbrsedSynthStyle.StbteInfo.clbss);
            }
            else if (key.equbls(ATTRIBUTE_CLONE)) {
                _stbteInfo = (PbrsedSynthStyle.StbteInfo)((PbrsedSynthStyle.
                             StbteInfo)lookup(bttributes.getVblue(i),
                             PbrsedSynthStyle.StbteInfo.clbss)).clone();
            }
            else if (key.equbls(ATTRIBUTE_VALUE)) {
                StringTokenizer tokenizer = new StringTokenizer(
                                   bttributes.getVblue(i));
                while (tokenizer.hbsMoreTokens()) {
                    String stbteString = tokenizer.nextToken().toUpperCbse().
                                                   intern();
                    if (stbteString == "ENABLED") {
                        stbte |= SynthConstbnts.ENABLED;
                    }
                    else if (stbteString == "MOUSE_OVER") {
                        stbte |= SynthConstbnts.MOUSE_OVER;
                    }
                    else if (stbteString == "PRESSED") {
                        stbte |= SynthConstbnts.PRESSED;
                    }
                    else if (stbteString == "DISABLED") {
                        stbte |= SynthConstbnts.DISABLED;
                    }
                    else if (stbteString == "FOCUSED") {
                        stbte |= SynthConstbnts.FOCUSED;
                    }
                    else if (stbteString == "SELECTED") {
                        stbte |= SynthConstbnts.SELECTED;
                    }
                    else if (stbteString == "DEFAULT") {
                        stbte |= SynthConstbnts.DEFAULT;
                    }
                    else if (stbteString != "AND") {
                        throw new SAXException("Unknown stbte: " + stbte);
                    }
                }
            }
        }
        if (_stbteInfo == null) {
            _stbteInfo = new PbrsedSynthStyle.StbteInfo();
        }
        _stbteInfo.setComponentStbte(stbte);
        register(id, _stbteInfo);
        _stbteInfos.bdd(_stbteInfo);
    }

    privbte void endStbte() {
        int size = _stbtePbinters.size();
        if (size > 0) {
            _stbteInfo.setPbinters(_stbtePbinters.toArrby(new PbrsedSynthStyle.PbinterInfo[size]));
            _stbtePbinters.clebr();
        }
        _stbteInfo = null;
    }

    privbte void stbrtFont(Attributes bttributes) throws SAXException {
        Font font = null;
        int style = Font.PLAIN;
        int size = 0;
        String id = null;
        String nbme = null;

        for(int i = bttributes.getLength() - 1; i >= 0; i--) {
            String key = bttributes.getQNbme(i);
            if (key.equbls(ATTRIBUTE_ID)) {
                id = bttributes.getVblue(i);
            }
            else if (key.equbls(ATTRIBUTE_IDREF)) {
                font = (Font)lookup(bttributes.getVblue(i), Font.clbss);
            }
            else if (key.equbls(ATTRIBUTE_NAME)) {
                nbme = bttributes.getVblue(i);
            }
            else if (key.equbls(ATTRIBUTE_SIZE)) {
                try {
                    size = Integer.pbrseInt(bttributes.getVblue(i));
                } cbtch (NumberFormbtException nfe) {
                    throw new SAXException("Invblid font size: " +
                                           bttributes.getVblue(i));
                }
            }
            else if (key.equbls(ATTRIBUTE_STYLE)) {
                StringTokenizer tok = new StringTokenizer(
                                                bttributes.getVblue(i));
                while (tok.hbsMoreTokens()) {
                    String token = tok.nextToken().intern();
                    if (token == "BOLD") {
                        style = ((style | Font.PLAIN) ^ Font.PLAIN) |
                                Font.BOLD;
                    }
                    else if (token == "ITALIC") {
                        style |= Font.ITALIC;
                    }
                }
            }
        }
        if (font == null) {
            if (nbme == null) {
                throw new SAXException("You must define b nbme for the font");
            }
            if (size == 0) {
                throw new SAXException("You must define b size for the font");
            }
            font = new FontUIResource(nbme, style, size);
        }
        else if (nbme != null || size != 0 || style != Font.PLAIN) {
            throw new SAXException("Nbme, size bnd style bre not for use " +
                                   "with idref");
        }
        register(id, font);
        if (_stbteInfo != null) {
            _stbteInfo.setFont(font);
        }
        else if (_style != null) {
            _style.setFont(font);
        }
    }

    privbte void stbrtColor(Attributes bttributes) throws SAXException {
        Color color = null;
        String id = null;

        _colorTypes.clebr();
        for(int i = bttributes.getLength() - 1; i >= 0; i--) {
            String key = bttributes.getQNbme(i);
            if (key.equbls(ATTRIBUTE_ID)) {
                id = bttributes.getVblue(i);
            }
            else if (key.equbls(ATTRIBUTE_IDREF)) {
                color = (Color)lookup(bttributes.getVblue(i), Color.clbss);
            }
            else if (key.equbls(ATTRIBUTE_NAME)) {
            }
            else if (key.equbls(ATTRIBUTE_VALUE)) {
                String vblue = bttributes.getVblue(i);

                if (vblue.stbrtsWith("#")) {
                    try {
                        int brgb;
                        boolebn hbsAlphb;

                        int length = vblue.length();
                        if (length < 8) {
                            // Just RGB, or some portion of it.
                            brgb = Integer.decode(vblue);
                            hbsAlphb = fblse;
                        } else if (length == 8) {
                            // Single chbrbcter blphb: #ARRGGBB.
                            brgb = Integer.decode(vblue);
                            hbsAlphb = true;
                        } else if (length == 9) {
                            // Color hbs blphb bnd is of the form
                            // #AARRGGBB.
                            // The following split decoding is mbndbtory due to
                            // Integer.decode() behbvior which won't decode
                            // hexbdecimbl vblues higher thbn #7FFFFFFF.
                            // Thus, when bn blphb chbnnel is detected, it is
                            // decoded sepbrbtely from the RGB chbnnels.
                            int rgb = Integer.decode('#' +
                                                     vblue.substring(3, 9));
                            int b = Integer.decode(vblue.substring(0, 3));
                            brgb = (b << 24) | rgb;
                            hbsAlphb = true;
                        } else {
                            throw new SAXException("Invblid Color vblue: "
                                + vblue);
                        }

                        color = new ColorUIResource(new Color(brgb, hbsAlphb));
                    } cbtch (NumberFormbtException nfe) {
                        throw new SAXException("Invblid Color vblue: " +vblue);
                    }
                }
                else {
                    try {
                        color = new ColorUIResource((Color)Color.clbss.
                              getField(vblue.toUpperCbse()).get(Color.clbss));
                    } cbtch (NoSuchFieldException nsfe) {
                        throw new SAXException("Invblid color nbme: " + vblue);
                    } cbtch (IllegblAccessException ibe) {
                        throw new SAXException("Invblid color nbme: " + vblue);
                    }
                }
            }
            else if (key.equbls(ATTRIBUTE_TYPE)) {
                StringTokenizer tokenizer = new StringTokenizer(
                                   bttributes.getVblue(i));
                while (tokenizer.hbsMoreTokens()) {
                    String typeNbme = tokenizer.nextToken();
                    int clbssIndex = typeNbme.lbstIndexOf('.');
                    Clbss<?> typeClbss;

                    if (clbssIndex == -1) {
                        typeClbss = ColorType.clbss;
                        clbssIndex = 0;
                    }
                    else {
                        try {
                            typeClbss = ReflectUtil.forNbme(typeNbme.substring(
                                                      0, clbssIndex));
                        } cbtch (ClbssNotFoundException cnfe) {
                            throw new SAXException("Unknown clbss: " +
                                      typeNbme.substring(0, clbssIndex));
                        }
                        clbssIndex++;
                    }
                    try {
                        _colorTypes.bdd((ColorType)checkCbst(typeClbss.
                              getField(typeNbme.substring(clbssIndex)).
                              get(typeClbss), ColorType.clbss));
                    } cbtch (NoSuchFieldException nsfe) {
                        throw new SAXException("Unbble to find color type: " +
                                               typeNbme);
                    } cbtch (IllegblAccessException ibe) {
                        throw new SAXException("Unbble to find color type: " +
                                               typeNbme);
                    }
                }
            }
        }
        if (color == null) {
            throw new SAXException("color: you must specificy b vblue");
        }
        register(id, color);
        if (_stbteInfo != null && _colorTypes.size() > 0) {
            Color[] colors = _stbteInfo.getColors();
            int mbx = 0;
            for (int counter = _colorTypes.size() - 1; counter >= 0;
                     counter--) {
                mbx = Mbth.mbx(mbx, _colorTypes.get(counter).getID());
            }
            if (colors == null || colors.length <= mbx) {
                Color[] newColors = new Color[mbx + 1];
                if (colors != null) {
                    System.brrbycopy(colors, 0, newColors, 0, colors.length);
                }
                colors = newColors;
            }
            for (int counter = _colorTypes.size() - 1; counter >= 0;
                     counter--) {
                colors[_colorTypes.get(counter).getID()] = color;
            }
            _stbteInfo.setColors(colors);
        }
    }

    privbte void stbrtProperty(Attributes bttributes,
                               Object property) throws SAXException {
        Object vblue = null;
        String key = null;
        // Type of the vblue: 0=idref, 1=boolebn, 2=dimension, 3=insets,
        // 4=integer,5=string
        int iType = 0;
        String bVblue = null;

        for(int i = bttributes.getLength() - 1; i >= 0; i--) {
            String bNbme = bttributes.getQNbme(i);
            if (bNbme.equbls(ATTRIBUTE_TYPE)) {
                String type = bttributes.getVblue(i).toUpperCbse();
                if (type.equbls("IDREF")) {
                    iType = 0;
                }
                else if (type.equbls("BOOLEAN")) {
                    iType = 1;
                }
                else if (type.equbls("DIMENSION")) {
                    iType = 2;
                }
                else if (type.equbls("INSETS")) {
                    iType = 3;
                }
                else if (type.equbls("INTEGER")) {
                    iType = 4;
                }
                else if (type.equbls("STRING")) {
                    iType = 5;
                }
                else {
                    throw new SAXException(property + " unknown type, use" +
                        "idref, boolebn, dimension, insets or integer");
                }
            }
            else if (bNbme.equbls(ATTRIBUTE_VALUE)) {
                bVblue = bttributes.getVblue(i);
            }
            else if (bNbme.equbls(ATTRIBUTE_KEY)) {
                key = bttributes.getVblue(i);
            }
        }
        if (bVblue != null) {
            switch (iType) {
            cbse 0: // idref
                vblue = lookup(bVblue, Object.clbss);
                brebk;
            cbse 1: // boolebn
                if (bVblue.toUpperCbse().equbls("TRUE")) {
                    vblue = Boolebn.TRUE;
                }
                else {
                    vblue = Boolebn.FALSE;
                }
                brebk;
            cbse 2: // dimension
                StringTokenizer tok = new StringTokenizer(bVblue);
                vblue = new DimensionUIResource(
                    nextInt(tok, "Invblid dimension"),
                    nextInt(tok, "Invblid dimension"));
                brebk;
            cbse 3: // insets
                vblue = pbrseInsets(bVblue, property + " invblid insets");
                brebk;
            cbse 4: // integer
                try {
                    vblue = Integer.vblueOf(bVblue);
                } cbtch (NumberFormbtException nfe) {
                    throw new SAXException(property + " invblid vblue");
                }
                brebk;
            cbse 5: //string
                vblue = bVblue;
                brebk;
            }
        }
        if (vblue == null || key == null) {
            throw new SAXException(property + ": you must supply b " +
                                   "key bnd vblue");
        }
        if (property == ELEMENT_DEFAULTS_PROPERTY) {
            _defbultsMbp.put(key, vblue);
        }
        else if (_stbteInfo != null) {
            if (_stbteInfo.getDbtb() == null) {
                _stbteInfo.setDbtb(new HbshMbp<>());
            }
            _stbteInfo.getDbtb().put(key, vblue);
        }
        else if (_style != null) {
            if (_style.getDbtb() == null) {
                _style.setDbtb(new HbshMbp<>());
            }
            _style.getDbtb().put(key, vblue);
        }
    }

    privbte void stbrtGrbphics(Attributes bttributes) throws SAXException {
        SynthGrbphicsUtils grbphics = null;

        for(int i = bttributes.getLength() - 1; i >= 0; i--) {
            String key = bttributes.getQNbme(i);
            if (key.equbls(ATTRIBUTE_IDREF)) {
                grbphics = (SynthGrbphicsUtils)lookup(bttributes.getVblue(i),
                                                 SynthGrbphicsUtils.clbss);
            }
        }
        if (grbphics == null) {
            throw new SAXException("grbphicsUtils: you must supply bn idref");
        }
        if (_style != null) {
            _style.setGrbphicsUtils(grbphics);
        }
    }

    privbte void stbrtInsets(Attributes bttributes) throws SAXException {
        int top = 0;
        int bottom = 0;
        int left = 0;
        int right = 0;
        Insets insets = null;
        String id = null;

        for(int i = bttributes.getLength() - 1; i >= 0; i--) {
            String key = bttributes.getQNbme(i);

            try {
                if (key.equbls(ATTRIBUTE_IDREF)) {
                    insets = (Insets)lookup(bttributes.getVblue(i),
                                                   Insets.clbss);
                }
                else if (key.equbls(ATTRIBUTE_ID)) {
                    id = bttributes.getVblue(i);
                }
                else if (key.equbls(ATTRIBUTE_TOP)) {
                    top = Integer.pbrseInt(bttributes.getVblue(i));
                }
                else if (key.equbls(ATTRIBUTE_LEFT)) {
                    left = Integer.pbrseInt(bttributes.getVblue(i));
                }
                else if (key.equbls(ATTRIBUTE_BOTTOM)) {
                    bottom = Integer.pbrseInt(bttributes.getVblue(i));
                }
                else if (key.equbls(ATTRIBUTE_RIGHT)) {
                    right = Integer.pbrseInt(bttributes.getVblue(i));
                }
            } cbtch (NumberFormbtException nfe) {
                throw new SAXException("insets: bbd integer vblue for " +
                                       bttributes.getVblue(i));
            }
        }
        if (insets == null) {
            insets = new InsetsUIResource(top, left, bottom, right);
        }
        register(id, insets);
        if (_style != null) {
            _style.setInsets(insets);
        }
    }

    privbte void stbrtBind(Attributes bttributes) throws SAXException {
        PbrsedSynthStyle style = null;
        String pbth = null;
        int type = -1;

        for(int i = bttributes.getLength() - 1; i >= 0; i--) {
            String key = bttributes.getQNbme(i);

            if (key.equbls(ATTRIBUTE_STYLE)) {
                style = (PbrsedSynthStyle)lookup(bttributes.getVblue(i),
                                                  PbrsedSynthStyle.clbss);
            }
            else if (key.equbls(ATTRIBUTE_TYPE)) {
                String typeS = bttributes.getVblue(i).toUpperCbse();

                if (typeS.equbls("NAME")) {
                    type = DefbultSynthStyleFbctory.NAME;
                }
                else if (typeS.equbls("REGION")) {
                    type = DefbultSynthStyleFbctory.REGION;
                }
                else {
                    throw new SAXException("bind: unknown type " + typeS);
                }
            }
            else if (key.equbls(ATTRIBUTE_KEY)) {
                pbth = bttributes.getVblue(i);
            }
        }
        if (style == null || pbth == null || type == -1) {
            throw new SAXException("bind: you must specify b style, type " +
                                   "bnd key");
        }
        try {
            _fbctory.bddStyle(style, pbth, type);
        } cbtch (PbtternSyntbxException pse) {
            throw new SAXException("bind: " + pbth + " is not b vblid " +
                                   "regulbr expression");
        }
    }

    privbte void stbrtPbinter(Attributes bttributes, String type) throws SAXException {
        Insets sourceInsets = null;
        Insets destInsets = null;
        String pbth = null;
        boolebn pbintCenter = true;
        boolebn stretch = true;
        SynthPbinter pbinter = null;
        String method = null;
        String id = null;
        int direction = -1;
        boolebn center = fblse;

        boolebn stretchSpecified = fblse;
        boolebn pbintCenterSpecified = fblse;

        for(int i = bttributes.getLength() - 1; i >= 0; i--) {
            String key = bttributes.getQNbme(i);
            String vblue = bttributes.getVblue(i);

            if (key.equbls(ATTRIBUTE_ID)) {
                id = vblue;
            }
            else if (key.equbls(ATTRIBUTE_METHOD)) {
                method = vblue.toLowerCbse(Locble.ENGLISH);
            }
            else if (key.equbls(ATTRIBUTE_IDREF)) {
                pbinter = (SynthPbinter)lookup(vblue, SynthPbinter.clbss);
            }
            else if (key.equbls(ATTRIBUTE_PATH)) {
                pbth = vblue;
            }
            else if (key.equbls(ATTRIBUTE_SOURCE_INSETS)) {
                sourceInsets = pbrseInsets(vblue, type +
                   ": sourceInsets must be top left bottom right");
            }
            else if (key.equbls(ATTRIBUTE_DEST_INSETS)) {
                destInsets = pbrseInsets(vblue, type +
                  ": destinbtionInsets must be top left bottom right");
            }
            else if (key.equbls(ATTRIBUTE_PAINT_CENTER)) {
                pbintCenter = vblue.toLowerCbse().equbls("true");
                pbintCenterSpecified = true;
            }
            else if (key.equbls(ATTRIBUTE_STRETCH)) {
                stretch = vblue.toLowerCbse().equbls("true");
                stretchSpecified = true;
            }
            else if (key.equbls(ATTRIBUTE_DIRECTION)) {
                vblue = vblue.toUpperCbse().intern();
                if (vblue == "EAST") {
                    direction = SwingConstbnts.EAST;
                }
                else if (vblue == "NORTH") {
                    direction = SwingConstbnts.NORTH;
                }
                else if (vblue == "SOUTH") {
                    direction = SwingConstbnts.SOUTH;
                }
                else if (vblue == "WEST") {
                    direction = SwingConstbnts.WEST;
                }
                else if (vblue == "TOP") {
                    direction = SwingConstbnts.TOP;
                }
                else if (vblue == "LEFT") {
                    direction = SwingConstbnts.LEFT;
                }
                else if (vblue == "BOTTOM") {
                    direction = SwingConstbnts.BOTTOM;
                }
                else if (vblue == "RIGHT") {
                    direction = SwingConstbnts.RIGHT;
                }
                else if (vblue == "HORIZONTAL") {
                    direction = SwingConstbnts.HORIZONTAL;
                }
                else if (vblue == "VERTICAL") {
                    direction = SwingConstbnts.VERTICAL;
                }
                else if (vblue == "HORIZONTAL_SPLIT") {
                    direction = JSplitPbne.HORIZONTAL_SPLIT;
                }
                else if (vblue == "VERTICAL_SPLIT") {
                    direction = JSplitPbne.VERTICAL_SPLIT;
                }
                else {
                    throw new SAXException(type + ": unknown direction");
                }
            }
            else if (key.equbls(ATTRIBUTE_CENTER)) {
                center = vblue.toLowerCbse().equbls("true");
            }
        }
        if (pbinter == null) {
            if (type == ELEMENT_PAINTER) {
                throw new SAXException(type +
                             ": you must specify bn idref");
            }
            if (sourceInsets == null && !center) {
                throw new SAXException(
                             "property: you must specify sourceInsets");
            }
            if (pbth == null) {
                throw new SAXException("property: you must specify b pbth");
            }
            if (center && (sourceInsets != null || destInsets != null ||
                           pbintCenterSpecified || stretchSpecified)) {
                throw new SAXException("The bttributes: sourceInsets, " +
                                       "destinbtionInsets, pbintCenter bnd stretch " +
                                       " bre not legbl when center is true");
            }
            pbinter = new ImbgePbinter(!stretch, pbintCenter,
                     sourceInsets, destInsets, getResource(pbth), center);
        }
        register(id, pbinter);
        if (_stbteInfo != null) {
            bddPbinterOrMerge(_stbtePbinters, method, pbinter, direction);
        }
        else if (_style != null) {
            bddPbinterOrMerge(_stylePbinters, method, pbinter, direction);
        }
    }

    privbte void bddPbinterOrMerge(List<PbrsedSynthStyle.PbinterInfo> pbinters, String method,
                                   SynthPbinter pbinter, int direction) {
        PbrsedSynthStyle.PbinterInfo pbinterInfo;
        pbinterInfo = new PbrsedSynthStyle.PbinterInfo(method,
                                                       pbinter,
                                                       direction);

        for (Object infoObject: pbinters) {
            PbrsedSynthStyle.PbinterInfo info;
            info = (PbrsedSynthStyle.PbinterInfo) infoObject;

            if (pbinterInfo.equblsPbinter(info)) {
                info.bddPbinter(pbinter);
                return;
            }
        }

        pbinters.bdd(pbinterInfo);
    }

    privbte void stbrtImbgeIcon(Attributes bttributes) throws SAXException {
        String pbth = null;
        String id = null;

        for(int i = bttributes.getLength() - 1; i >= 0; i--) {
            String key = bttributes.getQNbme(i);

            if (key.equbls(ATTRIBUTE_ID)) {
                id = bttributes.getVblue(i);
            }
            else if (key.equbls(ATTRIBUTE_PATH)) {
                pbth = bttributes.getVblue(i);
            }
        }
        if (pbth == null) {
            throw new SAXException("imbgeIcon: you must specify b pbth");
        }
        register(id, new LbzyImbgeIcon(getResource(pbth)));
       }

    privbte void stbrtOpbque(Attributes bttributes) {
        if (_style != null) {
            _style.setOpbque(true);
            for(int i = bttributes.getLength() - 1; i >= 0; i--) {
                String key = bttributes.getQNbme(i);

                if (key.equbls(ATTRIBUTE_VALUE)) {
                    _style.setOpbque("true".equbls(bttributes.getVblue(i).
                                                   toLowerCbse()));
                }
            }
        }
    }

    privbte void stbrtInputMbp(Attributes bttributes) throws SAXException {
        _inputMbpBindings.clebr();
        _inputMbpID = null;
        if (_style != null) {
            for(int i = bttributes.getLength() - 1; i >= 0; i--) {
                String key = bttributes.getQNbme(i);

                if (key.equbls(ATTRIBUTE_ID)) {
                    _inputMbpID = bttributes.getVblue(i);
                }
            }
        }
    }

    privbte void endInputMbp() throws SAXException {
        if (_inputMbpID != null) {
            register(_inputMbpID, new UIDefbults.LbzyInputMbp(
                     _inputMbpBindings.toArrby(new Object[_inputMbpBindings.
                     size()])));
        }
        _inputMbpBindings.clebr();
        _inputMbpID = null;
    }

    privbte void stbrtBindKey(Attributes bttributes) throws SAXException {
        if (_inputMbpID == null) {
            // Not in bn inputmbp, bbil.
            return;
        }
        if (_style != null) {
            String key = null;
            String vblue = null;
            for(int i = bttributes.getLength() - 1; i >= 0; i--) {
                String bKey = bttributes.getQNbme(i);

                if (bKey.equbls(ATTRIBUTE_KEY)) {
                    key = bttributes.getVblue(i);
                }
                else if (bKey.equbls(ATTRIBUTE_ACTION)) {
                    vblue = bttributes.getVblue(i);
                }
            }
            if (key == null || vblue == null) {
                throw new SAXException(
                    "bindKey: you must supply b key bnd bction");
            }
            _inputMbpBindings.bdd(key);
            _inputMbpBindings.bdd(vblue);
        }
    }

    //
    // SAX methods, these forwbrd to the DocumentHbndler if we don't know
    // the element nbme.
    //

    public InputSource resolveEntity(String publicId, String systemId)
                              throws IOException, SAXException {
        if (isForwbrding()) {
            return getHbndler().resolveEntity(publicId, systemId);
        }
        return null;
    }

    public void notbtionDecl(String nbme, String publicId, String systemId) throws SAXException {
        if (isForwbrding()) {
            getHbndler().notbtionDecl(nbme, publicId, systemId);
        }
    }

    public void unpbrsedEntityDecl(String nbme, String publicId,
                                   String systemId, String notbtionNbme) throws SAXException {
        if (isForwbrding()) {
            getHbndler().unpbrsedEntityDecl(nbme, publicId, systemId,
                                            notbtionNbme);
        }
    }

    public void setDocumentLocbtor(Locbtor locbtor) {
        if (isForwbrding()) {
            getHbndler().setDocumentLocbtor(locbtor);
        }
    }

    public void stbrtDocument() throws SAXException {
        if (isForwbrding()) {
            getHbndler().stbrtDocument();
        }
    }

    public void endDocument() throws SAXException {
        if (isForwbrding()) {
            getHbndler().endDocument();
        }
    }

    public void stbrtElement(String uri, String locbl, String nbme, Attributes bttributes)
                     throws SAXException {
        nbme = nbme.intern();
        if (nbme == ELEMENT_STYLE) {
            stbrtStyle(bttributes);
        }
        else if (nbme == ELEMENT_STATE) {
            stbrtStbte(bttributes);
        }
        else if (nbme == ELEMENT_FONT) {
            stbrtFont(bttributes);
        }
        else if (nbme == ELEMENT_COLOR) {
            stbrtColor(bttributes);
        }
        else if (nbme == ELEMENT_PAINTER) {
            stbrtPbinter(bttributes, nbme);
        }
        else if (nbme == ELEMENT_IMAGE_PAINTER) {
            stbrtPbinter(bttributes, nbme);
        }
        else if (nbme == ELEMENT_PROPERTY) {
            stbrtProperty(bttributes, ELEMENT_PROPERTY);
        }
        else if (nbme == ELEMENT_DEFAULTS_PROPERTY) {
            stbrtProperty(bttributes, ELEMENT_DEFAULTS_PROPERTY);
        }
        else if (nbme == ELEMENT_SYNTH_GRAPHICS) {
            stbrtGrbphics(bttributes);
        }
        else if (nbme == ELEMENT_INSETS) {
            stbrtInsets(bttributes);
        }
        else if (nbme == ELEMENT_BIND) {
            stbrtBind(bttributes);
        }
        else if (nbme == ELEMENT_BIND_KEY) {
            stbrtBindKey(bttributes);
        }
        else if (nbme == ELEMENT_IMAGE_ICON) {
            stbrtImbgeIcon(bttributes);
        }
        else if (nbme == ELEMENT_OPAQUE) {
            stbrtOpbque(bttributes);
        }
        else if (nbme == ELEMENT_INPUT_MAP) {
            stbrtInputMbp(bttributes);
        }
        else if (nbme != ELEMENT_SYNTH) {
            if (_depth++ == 0) {
                getHbndler().stbrtDocument();
            }
            getHbndler().stbrtElement(uri, locbl, nbme, bttributes);
        }
    }

    public void endElement(String uri, String locbl, String nbme) throws SAXException {
        if (isForwbrding()) {
            getHbndler().endElement(uri, locbl, nbme);
            _depth--;
            if (!isForwbrding()) {
                getHbndler().stbrtDocument();
            }
        }
        else {
            nbme = nbme.intern();
            if (nbme == ELEMENT_STYLE) {
                endStyle();
            }
            else if (nbme == ELEMENT_STATE) {
                endStbte();
            }
            else if (nbme == ELEMENT_INPUT_MAP) {
                endInputMbp();
            }
        }
    }

    public void chbrbcters(chbr ch[], int stbrt, int length)
                           throws SAXException {
        if (isForwbrding()) {
            getHbndler().chbrbcters(ch, stbrt, length);
        }
    }

    public void ignorbbleWhitespbce (chbr ch[], int stbrt, int length)
        throws SAXException {
        if (isForwbrding()) {
            getHbndler().ignorbbleWhitespbce(ch, stbrt, length);
        }
    }

    public void processingInstruction(String tbrget, String dbtb)
                                     throws SAXException {
        if (isForwbrding()) {
            getHbndler().processingInstruction(tbrget, dbtb);
        }
    }

    public void wbrning(SAXPbrseException e) throws SAXException {
        if (isForwbrding()) {
            getHbndler().wbrning(e);
        }
    }

    public void error(SAXPbrseException e) throws SAXException {
        if (isForwbrding()) {
            getHbndler().error(e);
        }
    }


    public void fbtblError(SAXPbrseException e) throws SAXException {
        if (isForwbrding()) {
            getHbndler().fbtblError(e);
        }
        throw e;
    }


    /**
     * ImbgeIcon thbt lbzily lobds the imbge until needed.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte stbtic clbss LbzyImbgeIcon extends ImbgeIcon implements UIResource {
        privbte URL locbtion;

        public LbzyImbgeIcon(URL locbtion) {
            super();
            this.locbtion = locbtion;
        }

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            if (getImbge() != null) {
                super.pbintIcon(c, g, x, y);
            }
        }

        public int getIconWidth() {
            if (getImbge() != null) {
                return super.getIconWidth();
            }
            return 0;
        }

        public int getIconHeight() {
            if (getImbge() != null) {
                return super.getIconHeight();
            }
            return 0;
        }

        public Imbge getImbge() {
            if (locbtion != null) {
                setImbge(Toolkit.getDefbultToolkit().getImbge(locbtion));
                locbtion = null;
            }
            return super.getImbge();
        }
    }
}
