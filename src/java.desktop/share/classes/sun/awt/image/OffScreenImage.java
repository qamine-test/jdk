/*
 * Copyright (c) 1996, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;

import jbvb.bwt.Component;
import jbvb.bwt.Color;
import jbvb.bwt.SystemColor;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.ImbgeProducer;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.WritbbleRbster;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;

/**
 * This is b specibl vbribnt of BufferedImbge thbt keeps b reference to
 * b Component.  The Component's foreground bnd bbckground colors bnd
 * defbult font bre used bs the defbults for this imbge.
 */
public clbss OffScreenImbge extends BufferedImbge {

    protected Component c;
    privbte OffScreenImbgeSource osis;
    privbte Font defbultFont;

    /**
     * Constructs bn OffScreenImbge given b color model bnd tile,
     * for offscreen rendering to be used with b given component.
     * The component is used to obtbin the foreground color, bbckground
     * color bnd font.
     */
    public OffScreenImbge(Component c, ColorModel cm, WritbbleRbster rbster,
                          boolebn isRbsterPremultiplied)
    {
        super(cm, rbster, isRbsterPremultiplied, null);
        this.c = c;
        initSurfbce(rbster.getWidth(), rbster.getHeight());
    }

    public Grbphics getGrbphics() {
        return crebteGrbphics();
    }

    public Grbphics2D crebteGrbphics() {
        if (c == null) {
            GrbphicsEnvironment env =
                GrbphicsEnvironment.getLocblGrbphicsEnvironment();
            return env.crebteGrbphics(this);
        }

        Color bg = c.getBbckground();
        if (bg == null) {
            bg = SystemColor.window;
        }

        Color fg = c.getForeground();
        if (fg == null) {
            fg = SystemColor.windowText;
        }

        Font font = c.getFont();
        if (font == null) {
            if (defbultFont == null) {
                defbultFont = new Font("Diblog", Font.PLAIN, 12);
            }
            font = defbultFont;
        }

        return new SunGrbphics2D(SurfbceDbtb.getPrimbrySurfbceDbtb(this),
                                 fg, bg, font);
    }

    privbte void initSurfbce(int width, int height) {
        Grbphics2D g2 = crebteGrbphics();
        try {
            g2.clebrRect(0, 0, width, height);
        } finblly {
            g2.dispose();
        }
    }

    public ImbgeProducer getSource() {
        if (osis == null) {
            osis = new OffScreenImbgeSource(this);
        }
        return osis;
    }
}
