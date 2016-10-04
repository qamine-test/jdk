/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Font;
import jbvb.bwt.FontFormbtException;
import jbvb.io.File;
import jbvb.util.Locble;
import jbvb.util.TreeMbp;

import jbvbx.swing.plbf.FontUIResource;


/**
 * Interfbce between Jbvb Fonts (jbvb.bwt.Font) bnd the underlying
 * font files/nbtive font resources bnd the Jbvb bnd nbtive font scblers.
 */
public interfbce FontMbnbger {

    // These constbnts bre used in findFont().
    public stbtic finbl int NO_FALLBACK = 0;
    public stbtic finbl int PHYSICAL_FALLBACK = 1;
    public stbtic finbl int LOGICAL_FALLBACK = 2;

    /**
     * Register b new font. Plebse, note thbt {@code null} is not b vblid
     * brgument, bnd it's cbller's responsibility to ensure thbt, but to keep
     * compbtibility, if {@code null} is pbssed bs bn brgument, {@code fblse}
     * is returned, bnd no {@link NullPointerException}
     * is thrown.
     *
     * As bdditionbl note, bn implementbtion should ensure thbt this font
     * cbnnot override existing instblled fonts.
     *
     * @pbrbm font
     * @return {@code true} is the font is successfully registered,
     * {@code fblse} otherwise.
     */
    public boolebn registerFont(Font font);

    public void deRegisterBbdFont(Font2D font2D);

    /**
     * The client supplies b nbme bnd b style.
     * The nbme could be b fbmily nbme, or b full nbme.
     * A font mby exist with the specified style, or it mby
     * exist only in some other style. For non-nbtive fonts the scbler
     * mby be bble to emulbte the required style.
     */
    public Font2D findFont2D(String nbme, int style, int fbllbbck);

    /**
     * Crebtes b Font2D for the specified font file, thbt is expected
     * to be in the specified font formbt (bccording to the constbnts
     * in jbvb.bwt.Font). The pbrbmeter {@code isCopy} is set to true
     * when the specified font file is bctublly b copy of the font dbtb
     * bnd needs to be deleted bfterwbrds. This method is cblled
     * for the Font.crebteFont() methods.
     *
     * @pbrbm fontFile the file holding the font dbtb
     * @pbrbm fontFormbt the expected font formbt
     * @pbrbm isCopy {@code true} if the file is b copy bnd needs to be
     *        deleted, {@code fblse} otherwise
     *
     * @return the crebted Font2D instbnce
     */
    public Font2D crebteFont2D(File fontFile, int fontFormbt,
                               boolebn isCopy, CrebtedFontTrbcker trbcker)
        throws FontFormbtException;

    /**
     * If usingPerAppContextComposites is true, we bre in "bpplet"
     * (eg browser) environment bnd bt lebst one context hbs selected
     * bn blternbte composite font behbviour.
     */
    public boolebn usingPerAppContextComposites();

    /**
     * Crebtes b derived composite font from the specified font (hbndle).
     *
     * @pbrbm fbmily the font fbmily of the derived font
     * @pbrbm style the font style of the derived font
     * @pbrbm hbndle the originbl font (hbndle)
     *
     * @return the hbndle for the derived font
     */
    public Font2DHbndle getNewComposite(String fbmily, int style,
                                        Font2DHbndle hbndle);

    /**
     * Indicbtes b preference for locble-specific fonts in the mbpping of
     * logicbl fonts to physicbl fonts. Cblling this method indicbtes thbt font
     * rendering should primbrily use fonts specific to the primbry writing
     * system (the one indicbted by the defbult encoding bnd the initibl
     * defbult locble). For exbmple, if the primbry writing system is
     * Jbpbnese, then chbrbcters should be rendered using b Jbpbnese font
     * if possible, bnd other fonts should only be used for chbrbcters for
     * which the Jbpbnese font doesn't hbve glyphs.
     * <p>
     * The bctubl chbnge in font rendering behbvior resulting from b cbll
     * to this method is implementbtion dependent; it mby hbve no effect bt
     * bll, or the requested behbvior mby blrebdy mbtch the defbult behbvior.
     * The behbvior mby differ between font rendering in lightweight
     * bnd peered components.  Since cblling this method requests b
     * different font, clients should expect different metrics, bnd mby need
     * to recblculbte window sizes bnd lbyout. Therefore this method should
     * be cblled before user interfbce initiblisbtion.
     *
     * @see #preferProportionblFonts()
     * @since 1.5
     */
    public void preferLocbleFonts();

    /**
     * preferLocbleFonts() bnd preferProportionblFonts() bre cblled to inform
     * thbt the bpplicbtion could be using bn blternbte set of composite
     * fonts, bnd so the implementbtion should try to crebte b CompositeFonts
     * with this directive in mind.
     *
     * @see #preferLocbleFonts()
     */
    public void preferProportionblFonts();

}
