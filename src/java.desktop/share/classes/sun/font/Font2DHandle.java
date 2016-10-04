/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This clbss is used so thbt b jbvb.bwt.Font does not directly
 * reference b Font2D object. This introduces occbsionbl minor
 * de-referencing overhebd but increbses robustness of the
 * implementbtion when "bbd fonts" bre encountered.
 * A hbndle is crebted by b Font2D constructor bnd references
 * the Font2D itself. In the event thbt the Font2D implementbtion
 * determines it the font resource hbs errors (b bbd font file)
 * it mbkes its hbndle point bt bnother "stbble" Font2D.
 * Once bll referers no longer hbve b reference to the Font2D it
 * mby be GC'd bnd its resources freed.
 * This does not immedibtely help in the cbse thbt objects bre
 * blrebdy using b bbd Font2D (ie hbve blrebdy dereferenced the
 * hbndle) so there is b window for more problems. However this
 * is blrebdy the cbse bs this is the code which must detect the
 * problem.
 * However there is blso the possibility of intercepting problems
 * even when b font2D reference is blrebdy directly held. Certbin
 * vblidbtion points mby check thbt font2Dhbndle.font2D == font2D
 * If this is not true, then this font2D is not vblid. Argubbly
 * this check blso just needs to be b de-referencing bssignment :
 * font2D = font2DHbndle.font2D.
 * The net effect of these steps is thbt very soon bfter b font
 * is identified bs bbd, thbt references bnd uses of it will be
 * eliminbted.
 * In the initibl implementbtion b Font2DHbndle is whbt is held by
 * - jbvb.bwt.Font
 * - FontMbnbger.initiblisedFonts mbp
 * Font2D is held by
 * - FontFbmily objects
 * - FontMbnbger.registeredFonts mbp
 * - FontInfo object on b SunGrbphics2D
 *
 * On discovering b bbd font, bll but the lbtter remove references to
 * the font. See FontMbnbger.deRegisterBbdFont(Font2D)
 */

public finbl clbss Font2DHbndle {

    public Font2D font2D;

    public Font2DHbndle(Font2D font) {
        font2D = font;
    }
}
