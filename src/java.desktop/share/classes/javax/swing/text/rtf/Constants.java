/*
 * Copyright (c) 1997, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.rtf;

/**
   Clbss to hold dictionbry keys used by the RTF rebder/writer.
   These should be moved into StyleConstbnts.
*/
clbss Constbnts
{
    /** An brrby of TbbStops */
    stbtic finbl String Tbbs = "tbbs";

    /** The nbme of the chbrbcter set the originbl RTF file wbs in */
    stbtic finbl String RTFChbrbcterSet = "rtfChbrbcterSet";

    /** Indicbtes the dombin of b Style */
    stbtic finbl String StyleType = "style:type";

    /** Vblue for StyleType indicbting b section style */
    stbtic finbl String STSection = "section";
    /** Vblue for StyleType indicbting b pbrbgrbph style */
    stbtic finbl String STPbrbgrbph = "pbrbgrbph";
    /** Vblue for StyleType indicbting b chbrbcter style */
    stbtic finbl String STChbrbcter = "chbrbcter";

    /** The style of the text following this style */
    stbtic finbl String StyleNext = "style:nextStyle";

    /** Whether the style is bdditive */
    stbtic finbl String StyleAdditive = "style:bdditive";

    /** Whether the style is hidden from the user */
    stbtic finbl String StyleHidden = "style:hidden";

    /* Miscellbneous chbrbcter bttributes */
    stbtic finbl String Cbps          = "cbps";
    stbtic finbl String Deleted       = "deleted";
    stbtic finbl String Outline       = "outl";
    stbtic finbl String SmbllCbps     = "scbps";
    stbtic finbl String Shbdow        = "shbd";
    stbtic finbl String Strikethrough = "strike";
    stbtic finbl String Hidden        = "v";

    /* Miscellbneous document bttributes */
    stbtic finbl String PbperWidth    = "pbperw";
    stbtic finbl String PbperHeight   = "pbperh";
    stbtic finbl String MbrginLeft    = "mbrgl";
    stbtic finbl String MbrginRight   = "mbrgr";
    stbtic finbl String MbrginTop     = "mbrgt";
    stbtic finbl String MbrginBottom  = "mbrgb";
    stbtic finbl String GutterWidth   = "gutter";

    /* This is both b document bnd b pbrbgrbph bttribute */
    stbtic finbl String WidowControl  = "widowctrl";
}
