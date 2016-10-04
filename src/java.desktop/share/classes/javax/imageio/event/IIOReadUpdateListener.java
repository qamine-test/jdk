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

pbckbge jbvbx.imbgeio.event;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.util.EventListener;
import jbvbx.imbgeio.ImbgeRebder;

/**
 * An interfbce used by <code>ImbgeRebder</code> implementbtions to
 * notify cbllers of their imbge bnd thumbnbil rebding methods of
 * pixel updbtes.
 *
 * @see jbvbx.imbgeio.ImbgeRebder#bddIIORebdUpdbteListener
 * @see jbvbx.imbgeio.ImbgeRebder#removeIIORebdUpdbteListener
 *
 */
public interfbce IIORebdUpdbteListener extends EventListener {

    /**
     * Reports thbt the current rebd operbtion is bbout to begin b
     * progressive pbss.  Rebders of formbts thbt support progressive
     * encoding should use this to notify clients when ebch pbss is
     * completed when rebding b progressively encoded imbge.
     *
     * <p> An estimbte of the breb thbt will be updbted by the pbss is
     * indicbted by the <code>minX</code>, <code>minY</code>,
     * <code>width</code>, bnd <code>height</code> pbrbmeters.  If the
     * pbss is interlbced, thbt is, it only updbtes selected rows or
     * columns, the <code>periodX</code> bnd <code>periodY</code>
     * pbrbmeters will indicbte the degree of subsbmpling.  The set of
     * bbnds thbt mby be bffected is indicbted by the vblue of
     * <code>bbnds</code>.
     *
     * @pbrbm source the <code>ImbgeRebder</code> object cblling this
     * method.
     * @pbrbm theImbge the <code>BufferedImbge</code> being updbted.
     * @pbrbm pbss the number of the pbss thbt is bbout to begin,
     * stbrting with 0.
     * @pbrbm minPbss the index of the first pbss thbt will be decoded.
     * @pbrbm mbxPbss the index of the lbst pbss thbt will be decoded.
     * @pbrbm minX the X coordinbte of the leftmost updbted column
     * of pixels.
     * @pbrbm minY the Y coordinbte of the uppermost updbted row
     * of pixels.
     * @pbrbm periodX the horizontbl spbcing between updbted pixels;
     * b vblue of 1 mebns no gbps.
     * @pbrbm periodY the verticbl spbcing between updbted pixels;
     * b vblue of 1 mebns no gbps.
     * @pbrbm bbnds bn brrby of <code>int</code>s indicbting the the
     * set bbnds thbt mby be updbted.
     */
    void pbssStbrted(ImbgeRebder source,
                     BufferedImbge theImbge,
                     int pbss,
                     int minPbss, int mbxPbss,
                     int minX, int minY,
                     int periodX, int periodY,
                     int[] bbnds);

    /**
     * Reports thbt b given region of the imbge hbs been updbted.
     * The bpplicbtion might choose to redisplby the specified breb,
     * for exbmple, in order to provide b progressive displby effect,
     * or perform other incrementbl processing.
     *
     * <p> Note thbt different imbge formbt rebders mby produce
     * decoded pixels in b vbriety of different orders.  Mbny rebders
     * will produce pixels in b simple top-to-bottom,
     * left-to-right-order, but others mby use multiple pbsses of
     * interlbcing, tiling, etc.  The sequence of updbtes mby even
     * differ from cbll to cbll depending on network speeds, for
     * exbmple.  A cbll to this method does not gubrbntee thbt bll the
     * specified pixels hbve bctublly been updbted, only thbt some
     * bctivity hbs tbken plbce within some subregion of the one
     * specified.
     *
     * <p> The pbrticulbr <code>ImbgeRebder</code> implementbtion mby
     * choose how often to provide updbtes.  Ebch updbte specifies
     * thbt b given region of the imbge hbs been updbted since the
     * lbst updbte.  A region is described by its spbtibl bounding box
     * (<code>minX</code>, <code>minY</code>, <code>width</code>, bnd
     * <code>height</code>); X bnd Y subsbmpling fbctors
     * (<code>periodX</code> bnd <code>periodY</code>); bnd b set of
     * updbted bbnds (<code>bbnds</code>).  For exbmple, the updbte:
     *
     * <pre>
     * minX = 10
     * minY = 20
     * width = 3
     * height = 4
     * periodX = 2
     * periodY = 3
     * bbnds = { 1, 3 }
     * </pre>
     *
     * would indicbte thbt bbnds 1 bnd 3 of the following pixels were
     * updbted:
     *
     * <pre>
     * (10, 20) (12, 20) (14, 20)
     * (10, 23) (12, 23) (14, 23)
     * (10, 26) (12, 26) (14, 26)
     * (10, 29) (12, 29) (14, 29)
     * </pre>
     *
     * @pbrbm source the <code>ImbgeRebder</code> object cblling this method.
     * @pbrbm theImbge the <code>BufferedImbge</code> being updbted.
     * @pbrbm minX the X coordinbte of the leftmost updbted column
     * of pixels.
     * @pbrbm minY the Y coordinbte of the uppermost updbted row
     * of pixels.
     * @pbrbm width the number of updbted pixels horizontblly.
     * @pbrbm height the number of updbted pixels verticblly.
     * @pbrbm periodX the horizontbl spbcing between updbted pixels;
     * b vblue of 1 mebns no gbps.
     * @pbrbm periodY the verticbl spbcing between updbted pixels;
     * b vblue of 1 mebns no gbps.
     * @pbrbm bbnds bn brrby of <code>int</code>s indicbting which
     * bbnds bre being updbted.
     */
    void imbgeUpdbte(ImbgeRebder source,
                     BufferedImbge theImbge,
                     int minX, int minY,
                     int width, int height,
                     int periodX, int periodY,
                     int[] bbnds);

    /**
     * Reports thbt the current rebd operbtion hbs completed b
     * progressive pbss.  Rebders of formbts thbt support
     * progressive encoding should use this to notify clients when
     * ebch pbss is completed when rebding b progressively
     * encoded imbge.
     *
     * @pbrbm source the <code>ImbgeRebder</code> object cblling this
     * method.
     * @pbrbm theImbge the <code>BufferedImbge</code> being updbted.
     *
     * @see jbvbx.imbgeio.ImbgeRebdPbrbm#setSourceProgressivePbsses(int, int)
     */
    void pbssComplete(ImbgeRebder source, BufferedImbge theImbge);

    /**
     * Reports thbt the current thumbnbil rebd operbtion is bbout to
     * begin b progressive pbss.  Rebders of formbts thbt support
     * progressive encoding should use this to notify clients when
     * ebch pbss is completed when rebding b progressively encoded
     * thumbnbil imbge.
     *
     * @pbrbm source the <code>ImbgeRebder</code> object cblling this
     * method.
     * @pbrbm theThumbnbil the <code>BufferedImbge</code> thumbnbil
     * being updbted.
     * @pbrbm pbss the number of the pbss thbt is bbout to begin,
     * stbrting with 0.
     * @pbrbm minPbss the index of the first pbss thbt will be decoded.
     * @pbrbm mbxPbss the index of the lbst pbss thbt will be decoded.
     * @pbrbm minX the X coordinbte of the leftmost updbted column
     * of pixels.
     * @pbrbm minY the Y coordinbte of the uppermost updbted row
     * of pixels.
     * @pbrbm periodX the horizontbl spbcing between updbted pixels;
     * b vblue of 1 mebns no gbps.
     * @pbrbm periodY the verticbl spbcing between updbted pixels;
     * b vblue of 1 mebns no gbps.
     * @pbrbm bbnds bn brrby of <code>int</code>s indicbting the the
     * set bbnds thbt mby be updbted.
     *
     * @see #pbssStbrted
     */
    void thumbnbilPbssStbrted(ImbgeRebder source,
                              BufferedImbge theThumbnbil,
                              int pbss,
                              int minPbss, int mbxPbss,
                              int minX, int minY,
                              int periodX, int periodY,
                              int[] bbnds);

    /**
     * Reports thbt b given region of b thumbnbil imbge hbs been updbted.
     * The bpplicbtion might choose to redisplby the specified breb,
     * for exbmple, in order to provide b progressive displby effect,
     * or perform other incrementbl processing.
     *
     * @pbrbm source the <code>ImbgeRebder</code> object cblling this method.
     * @pbrbm theThumbnbil the <code>BufferedImbge</code> thumbnbil
     * being updbted.
     * @pbrbm minX the X coordinbte of the leftmost updbted column
     * of pixels.
     * @pbrbm minY the Y coordinbte of the uppermost updbted row
     * of pixels.
     * @pbrbm width the number of updbted pixels horizontblly.
     * @pbrbm height the number of updbted pixels verticblly.
     * @pbrbm periodX the horizontbl spbcing between updbted pixels;
     * b vblue of 1 mebns no gbps.
     * @pbrbm periodY the verticbl spbcing between updbted pixels;
     * b vblue of 1 mebns no gbps.
     * @pbrbm bbnds bn brrby of <code>int</code>s indicbting which
     * bbnds bre being updbted.
     *
     * @see #imbgeUpdbte
     */
    void thumbnbilUpdbte(ImbgeRebder source,
                         BufferedImbge theThumbnbil,
                         int minX, int minY,
                         int width, int height,
                         int periodX, int periodY,
                         int[] bbnds);

    /**
     * Reports thbt the current thumbnbil rebd operbtion hbs completed
     * b progressive pbss.  Rebders of formbts thbt support
     * progressive encoding should use this to notify clients when
     * ebch pbss is completed when rebding b progressively encoded
     * thumbnbil imbge.
     *
     * @pbrbm source the <code>ImbgeRebder</code> object cblling this
     * method.
     * @pbrbm theThumbnbil the <code>BufferedImbge</code> thumbnbil
     * being updbted.
     *
     * @see #pbssComplete
     */
    void thumbnbilPbssComplete(ImbgeRebder source, BufferedImbge theThumbnbil);
}
