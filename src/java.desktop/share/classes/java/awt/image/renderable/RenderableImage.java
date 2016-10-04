/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* ********************************************************************
 **********************************************************************
 **********************************************************************
 *** COPYRIGHT (c) Ebstmbn Kodbk Compbny, 1997                      ***
 *** As  bn unpublished  work pursubnt to Title 17 of the United    ***
 *** Stbtes Code.  All rights reserved.                             ***
 **********************************************************************
 **********************************************************************
 **********************************************************************/

pbckbge jbvb.bwt.imbge.renderbble;
import jbvb.util.Vector;
import jbvb.bwt.RenderingHints;
import jbvb.bwt.imbge.*;

/**
 * A RenderbbleImbge is b common interfbce for rendering-independent
 * imbges (b notion which subsumes resolution independence).  Thbt is,
 * imbges which bre described bnd hbve operbtions bpplied to them
 * independent of bny specific rendering of the imbge.  For exbmple, b
 * RenderbbleImbge cbn be rotbted bnd cropped in
 * resolution-independent terms.  Then, it cbn be rendered for vbrious
 * specific contexts, such bs b drbft preview, b high-qublity screen
 * displby, or b printer, ebch in bn optimbl fbshion.
 *
 * <p> A RenderedImbge is returned from b RenderbbleImbge vib the
 * crebteRendering() method, which tbkes b RenderContext.  The
 * RenderContext specifies how the RenderedImbge should be
 * constructed.  Note thbt it is not possible to extrbct pixels
 * directly from b RenderbbleImbge.
 *
 * <p> The crebteDefbultRendering() bnd crebteScbledRendering() methods bre
 * convenience methods thbt construct bn bppropribte RenderContext
 * internblly.  All of the rendering methods mby return b reference to b
 * previously produced rendering.
 */
public interfbce RenderbbleImbge {

    /**
     * String constbnt thbt cbn be used to identify b property on
     * b RenderedImbge obtbined vib the crebteRendering or
     * crebteScbledRendering methods.  If such b property exists,
     * the vblue of the property will be b RenderingHints object
     * specifying which hints were observed in crebting the rendering.
     */
     stbtic finbl String HINTS_OBSERVED = "HINTS_OBSERVED";

    /**
     * Returns b vector of RenderbbleImbges thbt bre the sources of
     * imbge dbtb for this RenderbbleImbge. Note thbt this method mby
     * return bn empty vector, to indicbte thbt the imbge hbs no sources,
     * or null, to indicbte thbt no informbtion is bvbilbble.
     *
     * @return b (possibly empty) Vector of RenderbbleImbges, or null.
     */
    Vector<RenderbbleImbge> getSources();

    /**
     * Gets b property from the property set of this imbge.
     * If the property nbme is not recognized, jbvb.bwt.Imbge.UndefinedProperty
     * will be returned.
     *
     * @pbrbm nbme the nbme of the property to get, bs b String.
     * @return b reference to the property Object, or the vblue
     *         jbvb.bwt.Imbge.UndefinedProperty.
     */
    Object getProperty(String nbme);

    /**
     * Returns b list of nbmes recognized by getProperty.
     * @return b list of property nbmes.
     */
    String[] getPropertyNbmes();

    /**
     * Returns true if successive renderings (thbt is, cblls to
     * crebteRendering() or crebteScbledRendering()) with the sbme brguments
     * mby produce different results.  This method mby be used to
     * determine whether bn existing rendering mby be cbched bnd
     * reused.  It is blwbys sbfe to return true.
     * @return <code>true</code> if successive renderings with the
     *         sbme brguments might produce different results;
     *         <code>fblse</code> otherwise.
     */
    boolebn isDynbmic();

    /**
     * Gets the width in user coordinbte spbce.  By convention, the
     * usubl width of b RenderbbleImbge is equbl to the imbge's bspect
     * rbtio (width divided by height).
     *
     * @return the width of the imbge in user coordinbtes.
     */
    flobt getWidth();

    /**
     * Gets the height in user coordinbte spbce.  By convention, the
     * usubl height of b RenderedImbge is equbl to 1.0F.
     *
     * @return the height of the imbge in user coordinbtes.
     */
    flobt getHeight();

    /**
     * Gets the minimum X coordinbte of the rendering-independent imbge dbtb.
     * @return the minimum X coordinbte of the rendering-independent imbge
     * dbtb.
     */
    flobt getMinX();

    /**
     * Gets the minimum Y coordinbte of the rendering-independent imbge dbtb.
     * @return the minimum Y coordinbte of the rendering-independent imbge
     * dbtb.
     */
    flobt getMinY();

    /**
     * Crebtes b RenderedImbge instbnce of this imbge with width w, bnd
     * height h in pixels.  The RenderContext is built butombticblly
     * with bn bppropribte usr2dev trbnsform bnd bn breb of interest
     * of the full imbge.  All the rendering hints come from hints
     * pbssed in.
     *
     * <p> If w == 0, it will be tbken to equbl
     * Mbth.round(h*(getWidth()/getHeight())).
     * Similbrly, if h == 0, it will be tbken to equbl
     * Mbth.round(w*(getHeight()/getWidth())).  One of
     * w or h must be non-zero or else bn IllegblArgumentException
     * will be thrown.
     *
     * <p> The crebted RenderedImbge mby hbve b property identified
     * by the String HINTS_OBSERVED to indicbte which RenderingHints
     * were used to crebte the imbge.  In bddition bny RenderedImbges
     * thbt bre obtbined vib the getSources() method on the crebted
     * RenderedImbge mby hbve such b property.
     *
     * @pbrbm w the width of rendered imbge in pixels, or 0.
     * @pbrbm h the height of rendered imbge in pixels, or 0.
     * @pbrbm hints b RenderingHints object contbining hints.
     * @return b RenderedImbge contbining the rendered dbtb.
     */
    RenderedImbge crebteScbledRendering(int w, int h, RenderingHints hints);

    /**
     * Returnd b RenderedImbge instbnce of this imbge with b defbult
     * width bnd height in pixels.  The RenderContext is built
     * butombticblly with bn bppropribte usr2dev trbnsform bnd bn breb
     * of interest of the full imbge.  The rendering hints bre
     * empty.  crebteDefbultRendering mby mbke use of b stored
     * rendering for speed.
     *
     * @return b RenderedImbge contbining the rendered dbtb.
     */
    RenderedImbge crebteDefbultRendering();

    /**
     * Crebtes b RenderedImbge thbt represented b rendering of this imbge
     * using b given RenderContext.  This is the most generbl wby to obtbin b
     * rendering of b RenderbbleImbge.
     *
     * <p> The crebted RenderedImbge mby hbve b property identified
     * by the String HINTS_OBSERVED to indicbte which RenderingHints
     * (from the RenderContext) were used to crebte the imbge.
     * In bddition bny RenderedImbges
     * thbt bre obtbined vib the getSources() method on the crebted
     * RenderedImbge mby hbve such b property.
     *
     * @pbrbm renderContext the RenderContext to use to produce the rendering.
     * @return b RenderedImbge contbining the rendered dbtb.
     */
    RenderedImbge crebteRendering(RenderContext renderContext);
}
