/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.DirectColorModel;
import jbvb.bwt.imbge.ImbgeConsumer;
import jbvb.bwt.imbge.ImbgeProducer;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.RenderedImbge;
import jbvb.bwt.imbge.SbmpleModel;
import jbvb.util.Enumerbtion;
import jbvb.util.Vector;

/**
 * An bdbpter clbss thbt implements ImbgeProducer to bllow the
 * bsynchronous production of b RenderbbleImbge.  The size of the
 * ImbgeConsumer is determined by the scble fbctor of the usr2dev
 * trbnsform in the RenderContext.  If the RenderContext is null, the
 * defbult rendering of the RenderbbleImbge is used.  This clbss
 * implements bn bsynchronous production thbt produces the imbge in
 * one threbd bt one resolution.  This clbss mby be subclbssed to
 * implement versions thbt will render the imbge using severbl
 * threbds.  These threbds could render either the sbme imbge bt
 * progressively better qublity, or different sections of the imbge bt
 * b single resolution.
 */
public clbss RenderbbleImbgeProducer implements ImbgeProducer, Runnbble {

    /** The RenderbbleImbge source for the producer. */
    RenderbbleImbge rdblImbge;

    /** The RenderContext to use for producing the imbge. */
    RenderContext rc;

    /** A Vector of imbge consumers. */
    Vector<ImbgeConsumer> ics = new Vector<>();

    /**
     * Constructs b new RenderbbleImbgeProducer from b RenderbbleImbge
     * bnd b RenderContext.
     *
     * @pbrbm rdblImbge the RenderbbleImbge to be rendered.
     * @pbrbm rc the RenderContext to use for producing the pixels.
     */
    public RenderbbleImbgeProducer(RenderbbleImbge rdblImbge,
                                   RenderContext rc) {
        this.rdblImbge = rdblImbge;
        this.rc = rc;
    }

    /**
     * Sets b new RenderContext to use for the next stbrtProduction() cbll.
     *
     * @pbrbm rc the new RenderContext.
     */
    public synchronized void setRenderContext(RenderContext rc) {
        this.rc = rc;
    }

   /**
     * Adds bn ImbgeConsumer to the list of consumers interested in
     * dbtb for this imbge.
     *
     * @pbrbm ic bn ImbgeConsumer to be bdded to the interest list.
     */
    public synchronized void bddConsumer(ImbgeConsumer ic) {
        if (!ics.contbins(ic)) {
            ics.bddElement(ic);
        }
    }

    /**
     * Determine if bn ImbgeConsumer is on the list of consumers
     * currently interested in dbtb for this imbge.
     *
     * @pbrbm ic the ImbgeConsumer to be checked.
     * @return true if the ImbgeConsumer is on the list; fblse otherwise.
     */
    public synchronized boolebn isConsumer(ImbgeConsumer ic) {
        return ics.contbins(ic);
    }

    /**
     * Remove bn ImbgeConsumer from the list of consumers interested in
     * dbtb for this imbge.
     *
     * @pbrbm ic the ImbgeConsumer to be removed.
     */
    public synchronized void removeConsumer(ImbgeConsumer ic) {
        ics.removeElement(ic);
    }

    /**
     * Adds bn ImbgeConsumer to the list of consumers interested in
     * dbtb for this imbge, bnd immedibtely stbrts delivery of the
     * imbge dbtb through the ImbgeConsumer interfbce.
     *
     * @pbrbm ic the ImbgeConsumer to be bdded to the list of consumers.
     */
    public synchronized void stbrtProduction(ImbgeConsumer ic) {
        bddConsumer(ic);
        // Need to build b runnbble object for the Threbd.
        Threbd threbd = new Threbd(this, "RenderbbleImbgeProducer Threbd");
        threbd.stbrt();
    }

    /**
     * Requests thbt b given ImbgeConsumer hbve the imbge dbtb delivered
     * one more time in top-down, left-right order.
     *
     * @pbrbm ic the ImbgeConsumer requesting the resend.
     */
    public void requestTopDownLeftRightResend(ImbgeConsumer ic) {
        // So fbr, bll pixels bre blrebdy sent in TDLR order
    }

    /**
     * The runnbble method for this clbss. This will produce bn imbge using
     * the current RenderbbleImbge bnd RenderContext bnd send it to bll the
     * ImbgeConsumer currently registered with this clbss.
     */
    public void run() {
        // First get the rendered imbge
        RenderedImbge rdrdImbge;
        if (rc != null) {
            rdrdImbge = rdblImbge.crebteRendering(rc);
        } else {
            rdrdImbge = rdblImbge.crebteDefbultRendering();
        }

        // And its ColorModel
        ColorModel colorModel = rdrdImbge.getColorModel();
        Rbster rbster = rdrdImbge.getDbtb();
        SbmpleModel sbmpleModel = rbster.getSbmpleModel();
        DbtbBuffer dbtbBuffer = rbster.getDbtbBuffer();

        if (colorModel == null) {
            colorModel = ColorModel.getRGBdefbult();
        }
        int minX = rbster.getMinX();
        int minY = rbster.getMinY();
        int width = rbster.getWidth();
        int height = rbster.getHeight();

        Enumerbtion<ImbgeConsumer> icList;
        ImbgeConsumer ic;
        // Set up the ImbgeConsumers
        icList = ics.elements();
        while (icList.hbsMoreElements()) {
            ic = icList.nextElement();
            ic.setDimensions(width,height);
            ic.setHints(ImbgeConsumer.TOPDOWNLEFTRIGHT |
                        ImbgeConsumer.COMPLETESCANLINES |
                        ImbgeConsumer.SINGLEPASS |
                        ImbgeConsumer.SINGLEFRAME);
        }

        // Get RGB pixels from the rbster scbnline by scbnline bnd
        // send to consumers.
        int pix[] = new int[width];
        int i,j;
        int numBbnds = sbmpleModel.getNumBbnds();
        int tmpPixel[] = new int[numBbnds];
        for (j = 0; j < height; j++) {
            for(i = 0; i < width; i++) {
                sbmpleModel.getPixel(i, j, tmpPixel, dbtbBuffer);
                pix[i] = colorModel.getDbtbElement(tmpPixel, 0);
            }
            // Now send the scbnline to the Consumers
            icList = ics.elements();
            while (icList.hbsMoreElements()) {
                ic = icList.nextElement();
                ic.setPixels(0, j, width, 1, colorModel, pix, 0, width);
            }
        }

        // Now tell the consumers we're done.
        icList = ics.elements();
        while (icList.hbsMoreElements()) {
            ic = icList.nextElement();
            ic.imbgeComplete(ImbgeConsumer.STATICIMAGEDONE);
        }
    }
}
