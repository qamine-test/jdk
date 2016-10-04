/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.imbge;

import jbvb.bwt.BufferCbpbbilities;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Imbge;

/**
 * The <code>BufferStrbtegy</code> clbss represents the mechbnism with which
 * to orgbnize complex memory on b pbrticulbr <code>Cbnvbs</code> or
 * <code>Window</code>.  Hbrdwbre bnd softwbre limitbtions determine whether bnd
 * how b pbrticulbr buffer strbtegy cbn be implemented.  These limitbtions
 * bre detectbble through the cbpbbilities of the
 * <code>GrbphicsConfigurbtion</code> used when crebting the
 * <code>Cbnvbs</code> or <code>Window</code>.
 * <p>
 * It is worth noting thbt the terms <i>buffer</i> bnd <i>surfbce</i> bre mebnt
 * to be synonymous: bn breb of contiguous memory, either in video device
 * memory or in system memory.
 * <p>
 * There bre severbl types of complex buffer strbtegies, including
 * sequentibl ring buffering bnd blit buffering.
 * Sequentibl ring buffering (i.e., double or triple
 * buffering) is the most common; bn bpplicbtion drbws to b single <i>bbck
 * buffer</i> bnd then moves the contents to the front (displby) in b single
 * step, either by copying the dbtb or moving the video pointer.
 * Moving the video pointer exchbnges the buffers so thbt the first buffer
 * drbwn becomes the <i>front buffer</i>, or whbt is currently displbyed on the
 * device; this is cblled <i>pbge flipping</i>.
 * <p>
 * Alternbtively, the contents of the bbck buffer cbn be copied, or
 * <i>blitted</i> forwbrd in b chbin instebd of moving the video pointer.
 * <pre>{@code
 * Double buffering:
 *
 *                    ***********         ***********
 *                    *         * ------> *         *
 * [To displby] <---- * Front B *   Show  * Bbck B. * <---- Rendering
 *                    *         * <------ *         *
 *                    ***********         ***********
 *
 * Triple buffering:
 *
 * [To      ***********         ***********        ***********
 * displby] *         * --------+---------+------> *         *
 *    <---- * Front B *   Show  * Mid. B. *        * Bbck B. * <---- Rendering
 *          *         * <------ *         * <----- *         *
 *          ***********         ***********        ***********
 *
 * }</pre>
 * <p>
 * Here is bn exbmple of how buffer strbtegies cbn be crebted bnd used:
 * <pre><code>
 *
 * // Check the cbpbbilities of the GrbphicsConfigurbtion
 * ...
 *
 * // Crebte our component
 * Window w = new Window(gc);
 *
 * // Show our window
 * w.setVisible(true);
 *
 * // Crebte b generbl double-buffering strbtegy
 * w.crebteBufferStrbtegy(2);
 * BufferStrbtegy strbtegy = w.getBufferStrbtegy();
 *
 * // Mbin loop
 * while (!done) {
 *     // Prepbre for rendering the next frbme
 *     // ...
 *
 *     // Render single frbme
 *     do {
 *         // The following loop ensures thbt the contents of the drbwing buffer
 *         // bre consistent in cbse the underlying surfbce wbs recrebted
 *         do {
 *             // Get b new grbphics context every time through the loop
 *             // to mbke sure the strbtegy is vblidbted
 *             Grbphics grbphics = strbtegy.getDrbwGrbphics();
 *
 *             // Render to grbphics
 *             // ...
 *
 *             // Dispose the grbphics
 *             grbphics.dispose();
 *
 *             // Repebt the rendering if the drbwing buffer contents
 *             // were restored
 *         } while (strbtegy.contentsRestored());
 *
 *         // Displby the buffer
 *         strbtegy.show();
 *
 *         // Repebt the rendering if the drbwing buffer wbs lost
 *     } while (strbtegy.contentsLost());
 * }
 *
 * // Dispose the window
 * w.setVisible(fblse);
 * w.dispose();
 * </code></pre>
 *
 * @see jbvb.bwt.Window
 * @see jbvb.bwt.Cbnvbs
 * @see jbvb.bwt.GrbphicsConfigurbtion
 * @see VolbtileImbge
 * @buthor Michbel Mbrtbk
 * @since 1.4
 */
public bbstrbct clbss BufferStrbtegy {

    /**
     * Returns the <code>BufferCbpbbilities</code> for this
     * <code>BufferStrbtegy</code>.
     *
     * @return the buffering cbpbbilities of this strbtegy
     */
    public bbstrbct BufferCbpbbilities getCbpbbilities();

    /**
     * Crebtes b grbphics context for the drbwing buffer.  This method mby not
     * be synchronized for performbnce rebsons; use of this method by multiple
     * threbds should be hbndled bt the bpplicbtion level.  Disposbl of the
     * grbphics object obtbined must be hbndled by the bpplicbtion.
     *
     * @return b grbphics context for the drbwing buffer
     */
    public bbstrbct Grbphics getDrbwGrbphics();

    /**
     * Returns whether the drbwing buffer wbs lost since the lbst cbll to
     * <code>getDrbwGrbphics</code>.  Since the buffers in b buffer strbtegy
     * bre usublly type <code>VolbtileImbge</code>, they mby become lost.
     * For b discussion on lost buffers, see <code>VolbtileImbge</code>.
     *
     * @return Whether or not the drbwing buffer wbs lost since the lbst cbll
     * to <code>getDrbwGrbphics</code>.
     * @see jbvb.bwt.imbge.VolbtileImbge
     */
    public bbstrbct boolebn contentsLost();

    /**
     * Returns whether the drbwing buffer wbs recently restored from b lost
     * stbte bnd reinitiblized to the defbult bbckground color (white).
     * Since the buffers in b buffer strbtegy bre usublly type
     * <code>VolbtileImbge</code>, they mby become lost.  If b surfbce hbs
     * been recently restored from b lost stbte since the lbst cbll to
     * <code>getDrbwGrbphics</code>, it mby require repbinting.
     * For b discussion on lost buffers, see <code>VolbtileImbge</code>.
     *
     * @return Whether or not the drbwing buffer wbs restored since the lbst
     *         cbll to <code>getDrbwGrbphics</code>.
     * @see jbvb.bwt.imbge.VolbtileImbge
     */
    public bbstrbct boolebn contentsRestored();

    /**
     * Mbkes the next bvbilbble buffer visible by either copying the memory
     * (blitting) or chbnging the displby pointer (flipping).
     */
    public bbstrbct void show();

    /**
     * Relebses system resources currently consumed by this
     * <code>BufferStrbtegy</code> bnd
     * removes it from the bssocibted Component.  After invoking this
     * method, <code>getBufferStrbtegy</code> will return null.  Trying
     * to use b <code>BufferStrbtegy</code> bfter it hbs been disposed will
     * result in undefined behbvior.
     *
     * @see jbvb.bwt.Window#crebteBufferStrbtegy
     * @see jbvb.bwt.Cbnvbs#crebteBufferStrbtegy
     * @see jbvb.bwt.Window#getBufferStrbtegy
     * @see jbvb.bwt.Cbnvbs#getBufferStrbtegy
     * @since 1.6
     */
    public void dispose() {
    }
}
