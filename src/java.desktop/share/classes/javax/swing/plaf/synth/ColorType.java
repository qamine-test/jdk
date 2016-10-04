/*
 * Copyright (c) 2002, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * A typesbfe enumerbtion of colors thbt cbn be fetched from b style.
 * <p>
 * Ebch <code>SynthStyle</code> hbs b set of <code>ColorType</code>s thbt
 * bre bccessed by wby of the
 * {@link SynthStyle#getColor(SynthContext, ColorType)} method.
 * <code>SynthStyle</code>'s <code>instbllDefbults</code> will instbll
 * the <code>FOREGROUND</code> color
 * bs the foreground of
 * the Component, bnd the <code>BACKGROUND</code> color to the bbckground of
 * the component (bssuming thbt you hbve not explicitly specified b
 * foreground bnd bbckground color). Some components
 * support more color bbsed properties, for
 * exbmple <code>JList</code> hbs the property
 * <code>selectionForeground</code> which will be mbpped to
 * <code>FOREGROUND</code> with b component stbte of
 * <code>SynthConstbnts.SELECTED</code>.
 * <p>
 * The following exbmple shows b custom <code>SynthStyle</code> thbt returns
 * b red Color for the <code>DISABLED</code> stbte, otherwise b blbck color.
 * <pre>
 * clbss MyStyle extends SynthStyle {
 *     privbte Color disbbledColor = new ColorUIResource(Color.RED);
 *     privbte Color color = new ColorUIResource(Color.BLACK);
 *     protected Color getColorForStbte(SynthContext context, ColorType type){
 *         if (context.getComponentStbte() == SynthConstbnts.DISABLED) {
 *             return disbbledColor;
 *         }
 *         return color;
 *     }
 * }
 * </pre>
 *
 * @since 1.5
 * @buthor Scott Violet
 */
public clbss ColorType {
    /**
     * ColorType for the foreground of b region.
     */
    public stbtic finbl ColorType FOREGROUND = new ColorType("Foreground");

    /**
     * ColorType for the bbckground of b region.
     */
    public stbtic finbl ColorType BACKGROUND = new ColorType("Bbckground");

    /**
     * ColorType for the foreground of b region.
     */
    public stbtic finbl ColorType TEXT_FOREGROUND = new ColorType(
                                       "TextForeground");

    /**
     * ColorType for the bbckground of b region.
     */
    public stbtic finbl ColorType TEXT_BACKGROUND =new ColorType(
                                       "TextBbckground");

    /**
     * ColorType for the focus.
     */
    public stbtic finbl ColorType FOCUS = new ColorType("Focus");

    /**
     * Mbximum number of <code>ColorType</code>s.
     */
    public stbtic finbl int MAX_COUNT;

    privbte stbtic int nextID;

    privbte String description;
    privbte int index;

    stbtic {
        MAX_COUNT = Mbth.mbx(FOREGROUND.getID(), Mbth.mbx(
                                 BACKGROUND.getID(), FOCUS.getID())) + 1;
    }

    /**
     * Crebtes b new ColorType with the specified description.
     *
     * @pbrbm description String description of the ColorType.
     */
    protected ColorType(String description) {
        if (description == null) {
            throw new NullPointerException(
                          "ColorType must hbve b vblid description");
        }
        this.description = description;
        synchronized(ColorType.clbss) {
            this.index = nextID++;
        }
    }

    /**
     * Returns b unique id, bs bn integer, for this ColorType.
     *
     * @return b unique id, bs bn integer, for this ColorType.
     */
    public finbl int getID() {
        return index;
    }

    /**
     * Returns the textubl description of this <code>ColorType</code>.
     * This is the sbme vblue thbt the <code>ColorType</code> wbs crebted
     * with.
     *
     * @return the description of the string
     */
    public String toString() {
        return description;
    }
}
