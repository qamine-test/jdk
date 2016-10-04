/*
 * Copyright (c) 1998, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.imbge.RenderedImbge;

/**
 * ContextublRenderedImbgeFbctory provides bn interfbce for the
 * functionblity thbt mby differ between instbnces of
 * RenderbbleImbgeOp.  Thus different operbtions on RenderbbleImbges
 * mby be performed by b single clbss such bs RenderedImbgeOp through
 * the use of multiple instbnces of ContextublRenderedImbgeFbctory.
 * The nbme ContextublRenderedImbgeFbctory is commonly shortened to
 * "CRIF."
 *
 * <p> All operbtions thbt bre to be used in b rendering-independent
 * chbin must implement ContextublRenderedImbgeFbctory.
 *
 * <p> Clbsses thbt implement this interfbce must provide b
 * constructor with no brguments.
 */
public interfbce ContextublRenderedImbgeFbctory extends RenderedImbgeFbctory {

    /**
     * Mbps the operbtion's output RenderContext into b RenderContext
     * for ebch of the operbtion's sources.  This is useful for
     * operbtions thbt cbn be expressed in whole or in pbrt simply bs
     * blterbtions in the RenderContext, such bs bn bffine mbpping, or
     * operbtions thbt wish to obtbin lower qublity renderings of
     * their sources in order to sbve processing effort or
     * trbnsmission bbndwith.  Some operbtions, such bs blur, cbn blso
     * use this mechbnism to bvoid obtbining sources of higher qublity
     * thbn necessbry.
     *
     * @pbrbm i the index of the source imbge.
     * @pbrbm renderContext the RenderContext being bpplied to the operbtion.
     * @pbrbm pbrbmBlock b PbrbmeterBlock contbining the operbtion's
     *        sources bnd pbrbmeters.
     * @pbrbm imbge the RenderbbleImbge being rendered.
     * @return b <code>RenderContext</code> for
     *         the source bt the specified index of the pbrbmeters
     *         Vector contbined in the specified PbrbmeterBlock.
     */
    RenderContext mbpRenderContext(int i,
                                   RenderContext renderContext,
                                   PbrbmeterBlock pbrbmBlock,
                                   RenderbbleImbge imbge);

    /**
     * Crebtes b rendering, given b RenderContext bnd b PbrbmeterBlock
     * contbining the operbtion's sources bnd pbrbmeters.  The output
     * is b RenderedImbge thbt tbkes the RenderContext into bccount to
     * determine its dimensions bnd plbcement on the imbge plbne.
     * This method houses the "intelligence" thbt bllows b
     * rendering-independent operbtion to bdbpt to b specific
     * RenderContext.
     *
     * @pbrbm renderContext The RenderContext specifying the rendering
     * @pbrbm pbrbmBlock b PbrbmeterBlock contbining the operbtion's
     *        sources bnd pbrbmeters
     * @return b <code>RenderedImbge</code> from the sources bnd pbrbmeters
     *         in the specified PbrbmeterBlock bnd bccording to the
     *         rendering instructions in the specified RenderContext.
     */
    RenderedImbge crebte(RenderContext renderContext,
                         PbrbmeterBlock pbrbmBlock);

    /**
     * Returns the bounding box for the output of the operbtion,
     * performed on b given set of sources, in rendering-independent
     * spbce.  The bounds bre returned bs b Rectbngle2D, thbt is, bn
     * bxis-bligned rectbngle with flobting-point corner coordinbtes.
     *
     * @pbrbm pbrbmBlock b PbrbmeterBlock contbining the operbtion's
     *        sources bnd pbrbmeters.
     * @return b Rectbngle2D specifying the rendering-independent
     *         bounding box of the output.
     */
    Rectbngle2D getBounds2D(PbrbmeterBlock pbrbmBlock);

    /**
     * Gets the bppropribte instbnce of the property specified by the nbme
     * pbrbmeter.  This method must determine which instbnce of b property to
     * return when there bre multiple sources thbt ebch specify the property.
     *
     * @pbrbm pbrbmBlock b PbrbmeterBlock contbining the operbtion's
     *        sources bnd pbrbmeters.
     * @pbrbm nbme b String nbming the desired property.
     * @return bn object reference to the vblue of the property requested.
     */
    Object getProperty(PbrbmeterBlock pbrbmBlock, String nbme);

    /**
     * Returns b list of nbmes recognized by getProperty.
     * @return the list of property nbmes.
     */
    String[] getPropertyNbmes();

    /**
     * Returns true if successive renderings (thbt is, cblls to
     * crebte(RenderContext, PbrbmeterBlock)) with the sbme brguments
     * mby produce different results.  This method mby be used to
     * determine whether bn existing rendering mby be cbched bnd
     * reused.  It is blwbys sbfe to return true.
     * @return <code>true</code> if successive renderings with the
     *         sbme brguments might produce different results;
     *         <code>fblse</code> otherwise.
     */
    boolebn isDynbmic();
}
