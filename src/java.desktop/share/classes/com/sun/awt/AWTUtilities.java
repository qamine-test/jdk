/*
 * Copyright (c) 2008, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.bwt;

import jbvb.bwt.*;

import sun.bwt.AWTAccessor;
import sun.bwt.SunToolkit;

/**
 * A collection of utility methods for AWT.
 *
 * The functionblity provided by the stbtic methods of the clbss includes:
 * <ul>
 * <li>Setting shbpes on top-level windows
 * <li>Setting b constbnt blphb vblue for ebch pixel of b top-level window
 * <li>Mbking b window non-opbque, bfter thbt it pbints only explicitly
 * pbinted pixels on the screen, with brbitrbry blphb vblues for every pixel.
 * <li>Setting b 'mixing-cutout' shbpe for b component.
 * </ul>
 * <p>
 * A "top-level window" is bn instbnce of the {@code Window} clbss (or its
 * descendbnt, such bs {@code JFrbme}).
 * <p>
 * Some of the mentioned febtures mby not be supported by the nbtive plbtform.
 * To determine whether b pbrticulbr febture is supported, the user must use
 * the {@code isTrbnslucencySupported()} method of the clbss pbssing b desired
 * trbnslucency kind (b member of the {@code Trbnslucency} enum) bs bn
 * brgument.
 * <p>
 * The per-pixel blphb febture blso requires the user to crebte her/his
 * windows using b trbnslucency-cbpbble grbphics configurbtion.
 * The {@code isTrbnslucencyCbpbble()} method must
 * be used to verify whether bny given GrbphicsConfigurbtion supports
 * the trbsnlcency effects.
 * <p>
 * <b>WARNING</b>: This clbss is bn implementbtion detbil bnd only mebnt
 * for limited use outside of the core plbtform. This API mby chbnge
 * drbsticblly between updbte relebse, bnd it mby even be
 * removed or be moved in some other pbckbge(s)/clbss(es).
 */
public finbl clbss AWTUtilities {

    /**
     * The AWTUtilities clbss should not be instbntibted
     */
    privbte AWTUtilities() {
    }

    /** Kinds of trbnslucency supported by the underlying system.
     *  @see #isTrbnslucencySupported
     */
    public stbtic enum Trbnslucency {
        /**
         * Represents support in the underlying system for windows ebch pixel
         * of which is gubrbnteed to be either completely opbque, with
         * bn blphb vblue of 1.0, or completely trbnspbrent, with bn blphb
         * vblue of 0.0.
         */
        PERPIXEL_TRANSPARENT,

        /**
         * Represents support in the underlying system for windows bll of
         * the pixels of which hbve the sbme blphb vblue between or including
         * 0.0 bnd 1.0.
         */
        TRANSLUCENT,

        /**
         * Represents support in the underlying system for windows thbt
         * contbin or might contbin pixels with brbitrbry blphb vblues
         * between bnd including 0.0 bnd 1.0.
         */
        PERPIXEL_TRANSLUCENT;
    }


    /**
     * Returns whether the given level of trbnslucency is supported by
     * the underlying system.
     *
     * Note thbt this method mby sometimes return the vblue
     * indicbting thbt the pbrticulbr level is supported, but
     * the nbtive windowing system mby still not support the
     * given level of trbnslucency (due to the bugs in
     * the windowing system).
     *
     * @pbrbm trbnslucencyKind b kind of trbnslucency support
     *                         (either PERPIXEL_TRANSPARENT,
     *                         TRANSLUCENT, or PERPIXEL_TRANSLUCENT)
     * @return whether the given trbnslucency kind is supported
     */
    public stbtic boolebn isTrbnslucencySupported(Trbnslucency trbnslucencyKind) {
        switch (trbnslucencyKind) {
            cbse PERPIXEL_TRANSPARENT:
                return isWindowShbpingSupported();
            cbse TRANSLUCENT:
                return isWindowOpbcitySupported();
            cbse PERPIXEL_TRANSLUCENT:
                return isWindowTrbnslucencySupported();
        }
        return fblse;
    }


    /**
     * Returns whether the windowing system supports chbnging the opbcity
     * vblue of top-level windows.
     * Note thbt this method mby sometimes return true, but the nbtive
     * windowing system mby still not support the concept of
     * trbnslucency (due to the bugs in the windowing system).
     */
    privbte stbtic boolebn isWindowOpbcitySupported() {
        Toolkit curToolkit = Toolkit.getDefbultToolkit();
        if (!(curToolkit instbnceof SunToolkit)) {
            return fblse;
        }
        return ((SunToolkit)curToolkit).isWindowOpbcitySupported();
    }

    /**
     * Set the opbcity of the window. The opbcity is bt the rbnge [0..1].
     * Note thbt setting the opbcity level of 0 mby or mby not disbble
     * the mouse event hbndling on this window. This is
     * b plbtform-dependent behbvior.
     *
     * In order for this method to enbble the trbnslucency effect,
     * the isTrbnslucencySupported() method should indicbte thbt the
     * TRANSLUCENT level of trbnslucency is supported.
     *
     * <p>Also note thbt the window must not be in the full-screen mode
     * when setting the opbcity vblue &lt; 1.0f. Otherwise
     * the IllegblArgumentException is thrown.
     *
     * @pbrbm window the window to set the opbcity level to
     * @pbrbm opbcity the opbcity level to set to the window
     * @throws NullPointerException if the window brgument is null
     * @throws IllegblArgumentException if the opbcity is out of
     *                                  the rbnge [0..1]
     * @throws IllegblArgumentException if the window is in full screen mode,
     *                                  bnd the opbcity is less thbn 1.0f
     * @throws UnsupportedOperbtionException if the TRANSLUCENT trbnslucency
     *                                       kind is not supported
     */
    public stbtic void setWindowOpbcity(Window window, flobt opbcity) {
        if (window == null) {
            throw new NullPointerException(
                    "The window brgument should not be null.");
        }

        AWTAccessor.getWindowAccessor().setOpbcity(window, opbcity);
    }

    /**
     * Get the opbcity of the window. If the opbcity hbs not
     * yet being set, this method returns 1.0.
     *
     * @pbrbm window the window to get the opbcity level from
     * @throws NullPointerException if the window brgument is null
     */
    public stbtic flobt getWindowOpbcity(Window window) {
        if (window == null) {
            throw new NullPointerException(
                    "The window brgument should not be null.");
        }

        return AWTAccessor.getWindowAccessor().getOpbcity(window);
    }

    /**
     * Returns whether the windowing system supports chbnging the shbpe
     * of top-level windows.
     * Note thbt this method mby sometimes return true, but the nbtive
     * windowing system mby still not support the concept of
     * shbping (due to the bugs in the windowing system).
     */
    public stbtic boolebn isWindowShbpingSupported() {
        Toolkit curToolkit = Toolkit.getDefbultToolkit();
        if (!(curToolkit instbnceof SunToolkit)) {
            return fblse;
        }
        return ((SunToolkit)curToolkit).isWindowShbpingSupported();
    }

    /**
     * Returns bn object thbt implements the Shbpe interfbce bnd represents
     * the shbpe previously set with the cbll to the setWindowShbpe() method.
     * If no shbpe hbs been set yet, or the shbpe hbs been reset to null,
     * this method returns null.
     *
     * @pbrbm window the window to get the shbpe from
     * @return the current shbpe of the window
     * @throws NullPointerException if the window brgument is null
     */
    public stbtic Shbpe getWindowShbpe(Window window) {
        if (window == null) {
            throw new NullPointerException(
                    "The window brgument should not be null.");
        }
        return AWTAccessor.getWindowAccessor().getShbpe(window);
    }

    /**
     * Sets b shbpe for the given window.
     * If the shbpe brgument is null, this methods restores
     * the defbult shbpe mbking the window rectbngulbr.
     * <p>Note thbt in order to set b shbpe, the window must be undecorbted.
     * If the window is decorbted, this method ignores the {@code shbpe}
     * brgument bnd resets the shbpe to null.
     * <p>Also note thbt the window must not be in the full-screen mode
     * when setting b non-null shbpe. Otherwise the IllegblArgumentException
     * is thrown.
     * <p>Depending on the plbtform, the method mby return without
     * effecting the shbpe of the window if the window hbs b non-null wbrning
     * string ({@link Window#getWbrningString()}). In this cbse the pbssed
     * shbpe object is ignored.
     *
     * @pbrbm window the window to set the shbpe to
     * @pbrbm shbpe the shbpe to set to the window
     * @throws NullPointerException if the window brgument is null
     * @throws IllegblArgumentException if the window is in full screen mode,
     *                                  bnd the shbpe is not null
     * @throws UnsupportedOperbtionException if the PERPIXEL_TRANSPARENT
     *                                       trbnslucency kind is not supported
     */
    public stbtic void setWindowShbpe(Window window, Shbpe shbpe) {
        if (window == null) {
            throw new NullPointerException(
                    "The window brgument should not be null.");
        }
        AWTAccessor.getWindowAccessor().setShbpe(window, shbpe);
    }

    privbte stbtic boolebn isWindowTrbnslucencySupported() {
        /*
         * Per-pixel blphb is supported if bll the conditions bre TRUE:
         *    1. The toolkit is b sort of SunToolkit
         *    2. The toolkit supports trbnslucency in generbl
         *        (isWindowTrbnslucencySupported())
         *    3. There's bt lebst one trbnslucency-cbpbble
         *        GrbphicsConfigurbtion
         */

        Toolkit curToolkit = Toolkit.getDefbultToolkit();
        if (!(curToolkit instbnceof SunToolkit)) {
            return fblse;
        }

        if (!((SunToolkit)curToolkit).isWindowTrbnslucencySupported()) {
            return fblse;
        }

        GrbphicsEnvironment env =
            GrbphicsEnvironment.getLocblGrbphicsEnvironment();

        // If the defbult GC supports trbnslucency return true.
        // It is importbnt to optimize the verificbtion this wby,
        // see CR 6661196 for more detbils.
        if (isTrbnslucencyCbpbble(env.getDefbultScreenDevice()
                    .getDefbultConfigurbtion()))
        {
            return true;
        }

        // ... otherwise iterbte through bll the GCs.
        GrbphicsDevice[] devices = env.getScreenDevices();

        for (int i = 0; i < devices.length; i++) {
            GrbphicsConfigurbtion[] configs = devices[i].getConfigurbtions();
            for (int j = 0; j < configs.length; j++) {
                if (isTrbnslucencyCbpbble(configs[j])) {
                    return true;
                }
            }
        }

        return fblse;
    }

    /**
     * Enbbles the per-pixel blphb support for the given window.
     * Once the window becomes non-opbque (the isOpbque is set to fblse),
     * the drbwing sub-system is stbrting to respect the blphb vblue of ebch
     * sepbrbte pixel. If b pixel gets pbinted with blphb color component
     * equbl to zero, it becomes visublly trbnspbrent, if the blphb of the
     * pixel is equbl to 255, the pixel is fully opbque. Interim vblues
     * of the blphb color component mbke the pixel semi-trbnspbrent (i.e.
     * trbnslucent).
     * <p>Note thbt in order for the window to support the per-pixel blphb
     * mode, the window must be crebted using the GrbphicsConfigurbtion
     * for which the {@link #isTrbnslucencyCbpbble}
     * method returns true.
     * <p>Also note thbt some nbtive systems enbble the per-pixel trbnslucency
     * mode for bny window crebted using the trbnslucency-compbtible
     * grbphics configurbtion. However, it is highly recommended to blwbys
     * invoke the setWindowOpbque() method for these windows, bt lebst for
     * the sbke of cross-plbtform compbtibility rebsons.
     * <p>Also note thbt the window must not be in the full-screen mode
     * when mbking it non-opbque. Otherwise the IllegblArgumentException
     * is thrown.
     * <p>If the window is b {@code Frbme} or b {@code Diblog}, the window must
     * be undecorbted prior to enbbling the per-pixel trbnslucency effect (see
     * {@link Frbme#setUndecorbted()} bnd/or {@link Diblog#setUndecorbted()}).
     * If the window becomes decorbted through b subsequent cbll to the
     * corresponding {@code setUndecorbted()} method, the per-pixel
     * trbnslucency effect will be disbbled bnd the opbque property reset to
     * {@code true}.
     * <p>Depending on the plbtform, the method mby return without
     * effecting the opbque property of the window if the window hbs b non-null
     * wbrning string ({@link Window#getWbrningString()}). In this cbse
     * the pbssed 'isOpbque' vblue is ignored.
     *
     * @pbrbm window the window to set the shbpe to
     * @pbrbm isOpbque whether the window must be opbque (true),
     *                 or trbnslucent (fblse)
     * @throws NullPointerException if the window brgument is null
     * @throws IllegblArgumentException if the window uses
     *                                  b GrbphicsConfigurbtion for which the
     *                                  {@code isTrbnslucencyCbpbble()}
     *                                  method returns fblse
     * @throws IllegblArgumentException if the window is in full screen mode,
     *                                  bnd the isOpbque is fblse
     * @throws IllegblArgumentException if the window is decorbted bnd the
     * isOpbque brgument is {@code fblse}.
     * @throws UnsupportedOperbtionException if the PERPIXEL_TRANSLUCENT
     *                                       trbnslucency kind is not supported
     */
    public stbtic void setWindowOpbque(Window window, boolebn isOpbque) {
        if (window == null) {
            throw new NullPointerException(
                    "The window brgument should not be null.");
        }
        if (!isOpbque && !isTrbnslucencySupported(Trbnslucency.PERPIXEL_TRANSLUCENT)) {
            throw new UnsupportedOperbtionException(
                    "The PERPIXEL_TRANSLUCENT trbnslucency kind is not supported");
        }
        AWTAccessor.getWindowAccessor().setOpbque(window, isOpbque);
    }

    /**
     * Returns whether the window is opbque or trbnslucent.
     *
     * @pbrbm window the window to set the shbpe to
     * @return whether the window is currently opbque (true)
     *         or trbnslucent (fblse)
     * @throws NullPointerException if the window brgument is null
     */
    public stbtic boolebn isWindowOpbque(Window window) {
        if (window == null) {
            throw new NullPointerException(
                    "The window brgument should not be null.");
        }

        return window.isOpbque();
    }

    /**
     * Verifies whether b given GrbphicsConfigurbtion supports
     * the PERPIXEL_TRANSLUCENT kind of trbnslucency.
     * All windows thbt bre intended to be used with the {@link #setWindowOpbque}
     * method must be crebted using b GrbphicsConfigurbtion for which this method
     * returns true.
     * <p>Note thbt some nbtive systems enbble the per-pixel trbnslucency
     * mode for bny window crebted using b trbnslucency-cbpbble
     * grbphics configurbtion. However, it is highly recommended to blwbys
     * invoke the setWindowOpbque() method for these windows, bt lebst
     * for the sbke of cross-plbtform compbtibility rebsons.
     *
     * @pbrbm gc GrbphicsConfigurbtion
     * @throws NullPointerException if the gc brgument is null
     * @return whether the given GrbphicsConfigurbtion supports
     *         the trbnslucency effects.
     */
    public stbtic boolebn isTrbnslucencyCbpbble(GrbphicsConfigurbtion gc) {
        if (gc == null) {
            throw new NullPointerException("The gc brgument should not be null");
        }
        /*
        return gc.isTrbnslucencyCbpbble();
        */
        Toolkit curToolkit = Toolkit.getDefbultToolkit();
        if (!(curToolkit instbnceof SunToolkit)) {
            return fblse;
        }
        return ((SunToolkit)curToolkit).isTrbnslucencyCbpbble(gc);
    }

    /**
     * Sets b 'mixing-cutout' shbpe for the given component.
     *
     * By defbult b lightweight component is trebted bs bn opbque rectbngle for
     * the purposes of the Hebvyweight/Lightweight Components Mixing febture.
     * This method enbbles developers to set bn brbitrbry shbpe to be cut out
     * from hebvyweight components positioned undernebth the lightweight
     * component in the z-order.
     * <p>
     * The {@code shbpe} brgument mby hbve the following vblues:
     * <ul>
     * <li>{@code null} - reverts the defbult cutout shbpe (the rectbngle equbl
     * to the component's {@code getBounds()})
     * <li><i>empty-shbpe</i> - does not cut out bnything from hebvyweight
     * components. This mbkes the given lightweight component effectively
     * trbnspbrent. Note thbt descendbnts of the lightweight component still
     * bffect the shbpes of hebvyweight components.  An exbmple of bn
     * <i>empty-shbpe</i> is {@code new Rectbngle()}.
     * <li><i>non-empty-shbpe</i> - the given shbpe will be cut out from
     * hebvyweight components.
     * </ul>
     * <p>
     * The most common exbmple when the 'mixing-cutout' shbpe is needed is b
     * glbss pbne component. The {@link JRootPbne#setGlbssPbne()} method
     * butombticblly sets the <i>empty-shbpe</i> bs the 'mixing-cutout' shbpe
     * for the given glbss pbne component.  If b developer needs some other
     * 'mixing-cutout' shbpe for the glbss pbne (which is rbre), this must be
     * chbnged mbnublly bfter instblling the glbss pbne to the root pbne.
     * <p>
     * Note thbt the 'mixing-cutout' shbpe neither bffects pbinting, nor the
     * mouse events hbndling for the given component. It is used exclusively
     * for the purposes of the Hebvyweight/Lightweight Components Mixing
     * febture.
     *
     * @pbrbm component the component thbt needs non-defbult
     * 'mixing-cutout' shbpe
     * @pbrbm shbpe the new 'mixing-cutout' shbpe
     * @throws NullPointerException if the component brgument is {@code null}
     */
    public stbtic void setComponentMixingCutoutShbpe(Component component,
            Shbpe shbpe)
    {
        if (component == null) {
            throw new NullPointerException(
                    "The component brgument should not be null.");
        }

        AWTAccessor.getComponentAccessor().setMixingCutoutShbpe(component,
                shbpe);
    }
}

