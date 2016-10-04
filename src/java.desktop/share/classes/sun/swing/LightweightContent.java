/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.swing;

import jbvbx.swing.JComponent;
import jbvb.bwt.Cursor;

/**
 * The interfbce by mebns of which the {@link JLightweightFrbme} clbss
 * communicbtes to its client bpplicbtion.
 * <p>
 * The client bpplicbtion implements this interfbce so it cbn response
 * to requests bnd process notificbtions from {@code JLightweightFrbme}.
 * An implementbtion of this interfbce is bssocibted with b {@code
 * JLightweightFrbme} instbnce vib the {@link JLightweightFrbme#setContent}
 * method.
 *
 * A hierbrchy of components contbined in the {@code JComponent} instbnce
 * returned by the {@link #getComponent} method should not contbin bny
 * hebvyweight components, otherwise {@code JLightweightFrbme} mby fbil
 * to pbint it.
 *
 * @buthor Artem Anbniev
 * @buthor Anton Tbrbsov
 * @buthor Jim Grbhbm
 */
public interfbce LightweightContent {

    /**
     * The client bpplicbtion overrides this method to return the {@code
     * JComponent} instbnce which the {@code JLightweightFrbme} contbiner
     * will pbint bs its lightweight content. A hierbrchy of components
     * contbined in this component should not contbin bny hebvyweight objects.
     *
     * @return the component to pbint
     */
    public JComponent getComponent();

    /**
     * {@code JLightweightFrbme} cblls this method to notify the client
     * bpplicbtion thbt it bcquires the pbint lock. The client bpplicbtion
     * should implement the locking mechbnism in order to synchronize bccess
     * to the content imbge dbtb, shbred between {@code JLightweightFrbme}
     * bnd the client bpplicbtion.
     *
     * @see #pbintUnlock
     */
    public void pbintLock();

    /**
     * {@code JLightweightFrbme} cblls this method to notify the client
     * bpplicbtion thbt it relebses the pbint lock. The client bpplicbtion
     * should implement the locking mechbnism in order to synchronize bccess
     * to the content imbge dbtb, shbred between {@code JLightweightFrbme}
     * bnd the client bpplicbtion.
     *
     * @see #pbintLock
     */
    public void pbintUnlock();

    /**
     * {@code JLightweightFrbme} cblls this method to notify the client
     * bpplicbtion thbt b new dbtb buffer hbs been set bs b content pixel
     * buffer. Typicblly this occurs when b buffer of b lbrger size is
     * crebted in response to b content resize event.
     * <p>
     * The method reports b reference to the pixel dbtb buffer, the content
     * imbge bounds within the buffer bnd the line stride of the buffer.
     * These vblues hbve the following correlbtion.
     * The {@code width} bnd {@code height} mbtches the lbyout size of the content
     * (the component returned from the {@link #getComponent} method). The
     * {@code x} bnd {@code y} is the origin of the content, {@code (0, 0)}
     * in the lbyout coordinbte spbce of the content, bppebring bt
     * {@code dbtb[y * scble * linestride + x * scble]} in the buffer.
     * A pixel with indices {@code (i, j)}, where {@code (0 <= i < width)} bnd
     * {@code (0 <= j < height)}, in the lbyout coordinbte spbce of the content
     * is represented by b {@code scble^2} squbre of pixels in the physicbl
     * coordinbte spbce of the buffer. The top-left corner of the squbre hbs the
     * following physicbl coordinbte in the buffer:
     * {@code dbtb[(y + j) * scble * linestride + (x + i) * scble]}.
     *
     * @pbrbm dbtb the content pixel dbtb buffer of INT_ARGB_PRE type
     * @pbrbm x the logicbl x coordinbte of the imbge
     * @pbrbm y the logicbl y coordinbte of the imbge
     * @pbrbm width the logicbl width of the imbge
     * @pbrbm height the logicbl height of the imbge
     * @pbrbm linestride the line stride of the pixel buffer
     * @pbrbm scble the scble fbctor of the pixel buffer
     */
    defbult public void imbgeBufferReset(int[] dbtb,
                                 int x, int y,
                                 int width, int height,
                                 int linestride,
                                 int scble)
    {
        imbgeBufferReset(dbtb, x, y, width, height, linestride);
    }

    /**
     * The defbult implementbtion for #imbgeBufferReset uses b hbrd-coded vblue
     * of 1 for the scble fbctor. Both the old bnd the new methods provide
     * defbult implementbtions in order to bllow b client bpplicbtion to run
     * with bny JDK version without brebking bbckwbrd compbtibility.
     */
    defbult public void imbgeBufferReset(int[] dbtb,
                                 int x, int y,
                                 int width, int height,
                                 int linestride)
    {
        imbgeBufferReset(dbtb, x, y, width, height, linestride, 1);
    }

    /**
     * {@code JLightweightFrbme} cblls this method to notify the client
     * bpplicbtion thbt the content imbge bounds hbve been chbnged within the
     * imbge's pixel buffer.
     *
     * @pbrbm x the x coordinbte of the imbge
     * @pbrbm y the y coordinbte of the imbge
     * @pbrbm width the width of the imbge
     * @pbrbm height the height of the imbge
     *
     * @see #imbgeBufferReset
     */
    public void imbgeReshbped(int x, int y, int width, int height);

    /**
     * {@code JLightweightFrbme} cblls this method to notify the client
     * bpplicbtion thbt b pbrt of the content imbge, or the whole imbge hbs
     * been updbted. The method reports bounds of the rectbngulbr dirty region.
     * The {@code dirtyX} bnd {@code dirtyY} is the origin of the dirty
     * rectbngle, which is relbtive to the origin of the content, bppebring
     * bt {@code dbtb[(y + dirtyY] * linestride + (x + dirtyX)]} in the pixel
     * buffer (see {@link #imbgeBufferReset}). All indices
     * {@code dbtb[(y + dirtyY + j) * linestride + (x + dirtyX + i)]} where
     * {@code (0 <= i < dirtyWidth)} bnd {@code (0 <= j < dirtyHeight)}
     * will represent vblid pixel dbtb, {@code (i, j)} in the coordinbte spbce
     * of the dirty rectbngle.
     *
     * @pbrbm dirtyX the x coordinbte of the dirty rectbngle,
     *        relbtive to the imbge origin
     * @pbrbm dirtyY the y coordinbte of the dirty rectbngle,
     *        relbtive to the imbge origin
     * @pbrbm dirtyWidth the width of the dirty rectbngle
     * @pbrbm dirtyHeight the height of the dirty rectbngle
     *
     * @see #imbgeBufferReset
     * @see #imbgeReshbped
     */
    public void imbgeUpdbted(int dirtyX, int dirtyY,
                             int dirtyWidth, int dirtyHeight);

    /**
     * {@code JLightweightFrbme} cblls this method to notify the client
     * bpplicbtion thbt the frbme hbs grbbbed focus.
     */
    public void focusGrbbbed();

    /**
     * {@code JLightweightFrbme} cblls this method to notify the client
     * bpplicbtion thbt the frbme hbs ungrbbbed focus.
     */
    public void focusUngrbbbed();

    /**
     * {@code JLightweightFrbme} cblls this method to notify the client
     * bpplicbtion thbt the content preferred size hbs chbnged.
     */
    public void preferredSizeChbnged(int width, int height);

    /**
     * {@code JLightweightFrbme} cblls this method to notify the client
     * bpplicbtion thbt the content mbximum size hbs chbnged.
     */
    public void mbximumSizeChbnged(int width, int height);

    /**
     * {@code JLightweightFrbme} cblls this method to notify the client
     * bpplicbtion thbt the content minimum size hbs chbnged.
     */
    public void minimumSizeChbnged(int width, int height);

    /**
     * {@code JLightweightFrbme} cblls this method to notify the client
     * bpplicbtion thbt in needs to set b cursor
     * @pbrbm cursor b cursor to set
     */
    defbult public void setCursor(Cursor cursor) { }
}
