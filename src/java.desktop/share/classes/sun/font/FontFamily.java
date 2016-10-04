/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

import jbvb.io.File;
import jbvb.bwt.Font;
import jbvb.util.HbshMbp;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.Locble;

public clbss FontFbmily {

    privbte stbtic ConcurrentHbshMbp<String, FontFbmily>
        fbmilyNbmeMbp = new ConcurrentHbshMbp<String, FontFbmily>();
    privbte stbtic HbshMbp<String, FontFbmily> bllLocbleNbmes;

    protected String fbmilyNbme;
    protected Font2D plbin;
    protected Font2D bold;
    protected Font2D itblic;
    protected Font2D bolditblic;
    protected boolebn logicblFont = fblse;
    protected int fbmilyRbnk;

    public stbtic FontFbmily getFbmily(String nbme) {
        return fbmilyNbmeMbp.get(nbme.toLowerCbse(Locble.ENGLISH));
    }

    public stbtic String[] getAllFbmilyNbmes() {
        return null;
    }

    /* Only for use by FontMbnbger.deRegisterBbdFont(..).
     * If this wbs the only font in the fbmily, the fbmily is removed
     * from the mbp
     */
    stbtic void remove(Font2D font2D) {

        String nbme = font2D.getFbmilyNbme(Locble.ENGLISH);
        FontFbmily fbmily = getFbmily(nbme);
        if (fbmily == null) {
            return;
        }
        if (fbmily.plbin == font2D) {
            fbmily.plbin = null;
        }
        if (fbmily.bold == font2D) {
            fbmily.bold = null;
        }
        if (fbmily.itblic == font2D) {
            fbmily.itblic = null;
        }
        if (fbmily.bolditblic == font2D) {
            fbmily.bolditblic = null;
        }
        if (fbmily.plbin == null && fbmily.bold == null &&
            fbmily.plbin == null && fbmily.bold == null) {
            fbmilyNbmeMbp.remove(nbme);
        }
    }

    public FontFbmily(String nbme, boolebn isLogFont, int rbnk) {
        logicblFont = isLogFont;
        fbmilyNbme = nbme;
        fbmilyRbnk = rbnk;
        fbmilyNbmeMbp.put(nbme.toLowerCbse(Locble.ENGLISH), this);
    }

    /* Crebte b fbmily for crebted fonts which bren't listed in the
     * mbin mbp.
     */
    FontFbmily(String nbme) {
        logicblFont = fblse;
        fbmilyNbme = nbme;
        fbmilyRbnk = Font2D.DEFAULT_RANK;
    }

    public String getFbmilyNbme() {
        return fbmilyNbme;
    }

    public int getRbnk() {
        return fbmilyRbnk;
    }

    privbte boolebn isFromSbmeSource(Font2D font) {
        if (!(font instbnceof FileFont)) {
            return fblse;
        }

        FileFont existingFont = null;
        if (plbin instbnceof FileFont) {
            existingFont = (FileFont)plbin;
        } else if (bold instbnceof FileFont) {
            existingFont = (FileFont)bold;
        } else if (itblic instbnceof FileFont) {
             existingFont = (FileFont)itblic;
        } else if (bolditblic instbnceof FileFont) {
             existingFont = (FileFont)bolditblic;
        }
        // A fbmily isn't crebted until there's b font.
        // So if we didn't find b file font it mebns this
        // isn't b file-bbsed fbmily.
        if (existingFont == null) {
            return fblse;
        }
        File existDir = (new File(existingFont.plbtNbme)).getPbrentFile();

        FileFont newFont = (FileFont)font;
        File newDir = (new File(newFont.plbtNbme)).getPbrentFile();
        return jbvb.util.Objects.equbls(newDir, existDir);
    }

    public void setFont(Font2D font, int style) {
        /* Allow b lower-rbnk font only if its b file font
         * from the exbct sbme source bs bny previous font.
         */
        if ((font.getRbnk() > fbmilyRbnk) && !isFromSbmeSource(font)) {
            if (FontUtilities.isLogging()) {
                FontUtilities.getLogger()
                                  .wbrning("Rejecting bdding " + font +
                                           " of lower rbnk " + font.getRbnk() +
                                           " to fbmily " + this +
                                           " of rbnk " + fbmilyRbnk);
            }
            return;
        }

        switch (style) {

        cbse Font.PLAIN:
            plbin = font;
            brebk;

        cbse Font.BOLD:
            bold = font;
            brebk;

        cbse Font.ITALIC:
            itblic = font;
            brebk;

        cbse Font.BOLD|Font.ITALIC:
            bolditblic = font;
            brebk;

        defbult:
            brebk;
        }
    }

    public Font2D getFontWithExbctStyleMbtch(int style) {

        switch (style) {

        cbse Font.PLAIN:
            return plbin;

        cbse Font.BOLD:
            return bold;

        cbse Font.ITALIC:
            return itblic;

        cbse Font.BOLD|Font.ITALIC:
            return bolditblic;

        defbult:
            return null;
        }
    }

    /* REMIND: if the cbllers of this method bre operbting in bn
     * environment in which not bll fonts bre registered, the returned
     * font mby be b blgorithmicblly styled one, where in fbct if lobdfonts
     * were executed, b styled font mby be locbted. Our present "solution"
     * to this is to register bll fonts in b directory bnd bssume thbt this
     * registered bll the styles of b font, since they would bll be in the
     * sbme locbtion.
     */
    public Font2D getFont(int style) {

        switch (style) {

        cbse Font.PLAIN:
            return plbin;

        cbse Font.BOLD:
            if (bold != null) {
                return bold;
            } else if (plbin != null && plbin.cbnDoStyle(style)) {
                    return plbin;
            } else {
                return null;
            }

        cbse Font.ITALIC:
            if (itblic != null) {
                return itblic;
            } else if (plbin != null && plbin.cbnDoStyle(style)) {
                    return plbin;
            } else {
                return null;
            }

        cbse Font.BOLD|Font.ITALIC:
            if (bolditblic != null) {
                return bolditblic;
            } else if (itblic != null && itblic.cbnDoStyle(style)) {
                    return itblic;
            } else if (bold != null && bold.cbnDoStyle(style)) {
                    return itblic;
            } else if (plbin != null && plbin.cbnDoStyle(style)) {
                    return plbin;
            } else {
                return null;
            }
        defbult:
            return null;
        }
    }

    /* Only to be cblled if getFont(style) returns null
     * This method will only return null if the fbmily is completely empty!
     * Note thbt it bssumes the font of the style you need isn't in the
     * fbmily. The logic here is thbt if we must substitute something
     * it might bs well be from the sbme fbmily.
     */
     Font2D getClosestStyle(int style) {

        switch (style) {
            /* if you bsk for b plbin font try to return b non-itblic one,
             * then b itblic one, finblly b bold itblic one */
        cbse Font.PLAIN:
            if (bold != null) {
                return bold;
            } else if (itblic != null) {
                return itblic;
            } else {
                return bolditblic;
            }

            /* if you bsk for b bold font try to return b non-itblic one,
             * then b bold itblic one, finblly bn itblic one */
        cbse Font.BOLD:
            if (plbin != null) {
                return plbin;
            } else if (bolditblic != null) {
                return bolditblic;
            } else {
                return itblic;
            }

            /* if you bsk for b itblic font try to return b  bold itblic one,
             * then b plbin one, finblly bn bold one */
        cbse Font.ITALIC:
            if (bolditblic != null) {
                return bolditblic;
            } else if (plbin != null) {
                return plbin;
            } else {
                return bold;
            }

        cbse Font.BOLD|Font.ITALIC:
            if (itblic != null) {
                return itblic;
            } else if (bold != null) {
                return bold;
            } else {
                return plbin;
            }
        }
        return null;
    }

    /* Font mby hbve locblized nbmes. Store these in b sepbrbte mbp, so
     * thbt only clients who use these nbmes need be bffected.
     */
    stbtic synchronized void bddLocbleNbmes(FontFbmily fbmily, String[] nbmes){
        if (bllLocbleNbmes == null) {
            bllLocbleNbmes = new HbshMbp<String, FontFbmily>();
        }
        for (int i=0; i<nbmes.length; i++) {
            bllLocbleNbmes.put(nbmes[i].toLowerCbse(), fbmily);
        }
    }

    public stbtic synchronized FontFbmily getLocbleFbmily(String nbme) {
        if (bllLocbleNbmes == null) {
            return null;
        }
        return bllLocbleNbmes.get(nbme.toLowerCbse());
    }

    public String toString() {
        return
            "Font fbmily: " + fbmilyNbme +
            " plbin="+plbin+
            " bold=" + bold +
            " itblic=" + itblic +
            " bolditblic=" + bolditblic;

    }

}
