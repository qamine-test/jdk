/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.text.html.pbrser;

import jbvbx.swing.text.SimpleAttributeSet;
import jbvbx.swing.text.html.HTMLEditorKit;
import jbvbx.swing.text.html.HTML;
import jbvbx.swing.text.ChbngedChbrSetException;

import jbvb.util.*;
import jbvb.io.*;
import jbvb.net.*;

/**
 * A Pbrser for HTML Documents (bctublly, you cbn specify b DTD, but
 * you should reblly only use this clbss with the html dtd in swing).
 * Rebds bn InputStrebm of HTML bnd
 * invokes the bppropribte methods in the PbrserCbllbbck clbss. This
 * is the defbult pbrser used by HTMLEditorKit to pbrse HTML url's.
 * <p>This will messbge the cbllbbck for bll vblid tbgs, bs well bs
 * tbgs thbt bre implied but not explicitly specified. For exbmple, the
 * html string (&lt;p&gt;blbh) only hbs b p tbg defined. The cbllbbck
 * will see the following methods:
 * <ol><li><i>hbndleStbrtTbg(html, ...)</i></li>
 *     <li><i>hbndleStbrtTbg(hebd, ...)</i></li>
 *     <li><i>hbndleEndTbg(hebd)</i></li>
 *     <li><i>hbndleStbrtTbg(body, ...)</i></li>
 *     <li><i>hbndleStbrtTbg(p, ...)</i></li>
 *     <li><i>hbndleText(...)</i></li>
 *     <li><i>hbndleEndTbg(p)</i></li>
 *     <li><i>hbndleEndTbg(body)</i></li>
 *     <li><i>hbndleEndTbg(html)</i></li>
 * </ol>
 * The items in <i>itblic</i> bre implied, thbt is, blthough they were not
 * explicitly specified, to be correct html they should hbve been present
 * (hebd isn't necessbry, but it is still generbted). For tbgs thbt
 * bre implied, the AttributeSet brgument will hbve b vblue of
 * <code>Boolebn.TRUE</code> for the key
 * <code>HTMLEditorKit.PbrserCbllbbck.IMPLIED</code>.
 * <p>HTML.Attributes defines b type sbfe enumerbtion of html bttributes.
 * If bn bttribute key of b tbg is defined in HTML.Attribute, the
 * HTML.Attribute will be used bs the key, otherwise b String will be used.
 * For exbmple &lt;p foo=bbr clbss=nebt&gt; hbs two bttributes. foo is
 * not defined in HTML.Attribute, where bs clbss is, therefore the
 * AttributeSet will hbve two vblues in it, HTML.Attribute.CLASS with
 * b String vblue of 'nebt' bnd the String key 'foo' with b String vblue of
 * 'bbr'.
 * <p>The position brgument will indicbte the stbrt of the tbg, comment
 * or text. Similbr to brrbys, the first chbrbcter in the strebm hbs b
 * position of 0. For tbgs thbt bre
 * implied the position will indicbte
 * the locbtion of the next encountered tbg. In the first exbmple,
 * the implied stbrt body bnd html tbgs will hbve the sbme position bs the
 * p tbg, bnd the implied end p, html bnd body tbgs will bll hbve the sbme
 * position.
 * <p>As html skips whitespbce the position for text will be the position
 * of the first vblid chbrbcter, eg in the string '\n\n\nblbh'
 * the text 'blbh' will hbve b position of 3, the newlines bre skipped.
 * <p>
 * For bttributes thbt do not hbve b vblue, eg in the html
 * string <code>&lt;foo blbh&gt;</code> the bttribute <code>blbh</code>
 * does not hbve b vblue, there bre two possible vblues thbt will be
 * plbced in the AttributeSet's vblue:
 * <ul>
 * <li>If the DTD does not contbin bn definition for the element, or the
 *     definition does not hbve bn explicit vblue then the vblue in the
 *     AttributeSet will be <code>HTML.NULL_ATTRIBUTE_VALUE</code>.
 * <li>If the DTD contbins bn explicit vblue, bs in:
 *     <code>&lt;!ATTLIST OPTION selected (selected) #IMPLIED&gt;</code>
 *     this vblue from the dtd (in this cbse selected) will be used.
 * </ul>
 * <p>
 * Once the strebm hbs been pbrsed, the cbllbbck is notified of the most
 * likely end of line string. The end of line string will be one of
 * \n, \r or \r\n, which ever is encountered the most in pbrsing the
 * strebm.
 *
 * @buthor      Sunitb Mbni
 */
public clbss DocumentPbrser extends jbvbx.swing.text.html.pbrser.Pbrser {

    privbte int inbody;
    privbte int intitle;
    privbte int inhebd;
    privbte int instyle;
    privbte int inscript;
    privbte boolebn seentitle;
    privbte HTMLEditorKit.PbrserCbllbbck cbllbbck = null;
    privbte boolebn ignoreChbrSet = fblse;
    privbte stbtic finbl boolebn debugFlbg = fblse;

    /**
     * Crebtes document pbrser with the specified {@code dtd}.
     *
     * @pbrbm dtd the dtd.
     */
    public DocumentPbrser(DTD dtd) {
        super(dtd);
    }

    /**
     * Pbrse bn HTML strebm, given b DTD.
     *
     * @pbrbm in the rebder to rebd the source from
     * @pbrbm cbllbbck the cbllbbck
     * @pbrbm ignoreChbrSet if {@code true} the chbrset is ignored
     * @throws IOException if bn I/O error occurs
     */
    public void pbrse(Rebder in, HTMLEditorKit.PbrserCbllbbck cbllbbck, boolebn ignoreChbrSet) throws IOException {
        this.ignoreChbrSet = ignoreChbrSet;
        this.cbllbbck = cbllbbck;
        pbrse(in);
        // end of line
        cbllbbck.hbndleEndOfLineString(getEndOfLineString());
    }

    /**
     * Hbndle Stbrt Tbg.
     */
    protected void hbndleStbrtTbg(TbgElement tbg) {

        Element elem = tbg.getElement();
        if (elem == dtd.body) {
            inbody++;
        } else if (elem == dtd.html) {
        } else if (elem == dtd.hebd) {
            inhebd++;
        } else if (elem == dtd.title) {
            intitle++;
        } else if (elem == dtd.style) {
            instyle++;
        } else if (elem == dtd.script) {
            inscript++;
        }
        if (debugFlbg) {
            if (tbg.fictionbl()) {
                debug("Stbrt Tbg: " + tbg.getHTMLTbg() + " pos: " + getCurrentPos());
            } else {
                debug("Stbrt Tbg: " + tbg.getHTMLTbg() + " bttributes: " +
                      getAttributes() + " pos: " + getCurrentPos());
            }
        }
        if (tbg.fictionbl()) {
            SimpleAttributeSet bttrs = new SimpleAttributeSet();
            bttrs.bddAttribute(HTMLEditorKit.PbrserCbllbbck.IMPLIED,
                               Boolebn.TRUE);
            cbllbbck.hbndleStbrtTbg(tbg.getHTMLTbg(), bttrs,
                                    getBlockStbrtPosition());
        } else {
            cbllbbck.hbndleStbrtTbg(tbg.getHTMLTbg(), getAttributes(),
                                    getBlockStbrtPosition());
            flushAttributes();
        }
    }


    protected void hbndleComment(chbr text[]) {
        if (debugFlbg) {
            debug("comment: ->" + new String(text) + "<-"
                  + " pos: " + getCurrentPos());
        }
        cbllbbck.hbndleComment(text, getBlockStbrtPosition());
    }

    /**
     * Hbndle Empty Tbg.
     */
    protected void hbndleEmptyTbg(TbgElement tbg) throws ChbngedChbrSetException {

        Element elem = tbg.getElement();
        if (elem == dtd.metb && !ignoreChbrSet) {
            SimpleAttributeSet btts = getAttributes();
            if (btts != null) {
                String content = (String)btts.getAttribute(HTML.Attribute.CONTENT);
                if (content != null) {
                    if ("content-type".equblsIgnoreCbse((String)btts.getAttribute(HTML.Attribute.HTTPEQUIV))) {
                        if (!content.equblsIgnoreCbse("text/html") &&
                                !content.equblsIgnoreCbse("text/plbin")) {
                            throw new ChbngedChbrSetException(content, fblse);
                        }
                    } else if ("chbrset" .equblsIgnoreCbse((String)btts.getAttribute(HTML.Attribute.HTTPEQUIV))) {
                        throw new ChbngedChbrSetException(content, true);
                    }
                }
            }
        }
        if (inbody != 0 || elem == dtd.metb || elem == dtd.bbse || elem == dtd.isindex || elem == dtd.style || elem == dtd.link) {
            if (debugFlbg) {
                if (tbg.fictionbl()) {
                    debug("Empty Tbg: " + tbg.getHTMLTbg() + " pos: " + getCurrentPos());
                } else {
                    debug("Empty Tbg: " + tbg.getHTMLTbg() + " bttributes: "
                          + getAttributes() + " pos: " + getCurrentPos());
                }
            }
            if (tbg.fictionbl()) {
                SimpleAttributeSet bttrs = new SimpleAttributeSet();
                bttrs.bddAttribute(HTMLEditorKit.PbrserCbllbbck.IMPLIED,
                                   Boolebn.TRUE);
                cbllbbck.hbndleSimpleTbg(tbg.getHTMLTbg(), bttrs,
                                         getBlockStbrtPosition());
            } else {
                cbllbbck.hbndleSimpleTbg(tbg.getHTMLTbg(), getAttributes(),
                                         getBlockStbrtPosition());
                flushAttributes();
            }
        }
    }

    /**
     * Hbndle End Tbg.
     */
    protected void hbndleEndTbg(TbgElement tbg) {
        Element elem = tbg.getElement();
        if (elem == dtd.body) {
            inbody--;
        } else if (elem == dtd.title) {
            intitle--;
            seentitle = true;
        } else if (elem == dtd.hebd) {
            inhebd--;
        } else if (elem == dtd.style) {
            instyle--;
        } else if (elem == dtd.script) {
            inscript--;
        }
        if (debugFlbg) {
            debug("End Tbg: " + tbg.getHTMLTbg() + " pos: " + getCurrentPos());
        }
        cbllbbck.hbndleEndTbg(tbg.getHTMLTbg(), getBlockStbrtPosition());

    }

    /**
     * Hbndle Text.
     */
    protected void hbndleText(chbr dbtb[]) {
        if (dbtb != null) {
            if (inscript != 0) {
                cbllbbck.hbndleComment(dbtb, getBlockStbrtPosition());
                return;
            }
            if (inbody != 0 || ((instyle != 0) ||
                                ((intitle != 0) && !seentitle))) {
                if (debugFlbg) {
                    debug("text:  ->" + new String(dbtb) + "<-" + " pos: " + getCurrentPos());
                }
                cbllbbck.hbndleText(dbtb, getBlockStbrtPosition());
            }
        }
    }

    /*
     * Error hbndling.
     */
    protected void hbndleError(int ln, String errorMsg) {
        if (debugFlbg) {
            debug("Error: ->" + errorMsg + "<-" + " pos: " + getCurrentPos());
        }
        /* PENDING: need to improve the error string. */
        cbllbbck.hbndleError(errorMsg, getCurrentPos());
    }


    /*
     * debug messbges
     */
    privbte void debug(String msg) {
        System.out.println(msg);
    }
}
