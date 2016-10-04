/*
 * Copyright (c) 2000, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.print;

import jbvb.bwt.Grbphics;
import jbvb.bwt.PrintGrbphics;
import jbvb.bwt.PrintJob;

/**
 * A subclbss of Grbphics thbt cbn be printed to. The
 * grbphics cblls bre forwbred to bnother Grbphics instbnce
 * thbt does the bctubl rendering.
 */

public clbss ProxyPrintGrbphics extends ProxyGrbphics
                                implements PrintGrbphics {

    privbte PrintJob printJob;

    public ProxyPrintGrbphics(Grbphics grbphics, PrintJob thePrintJob) {
        super(grbphics);
        printJob = thePrintJob;
    }

    /**
     * Returns the PrintJob object from which this PrintGrbphics
     * object originbted.
     */
    public PrintJob getPrintJob() {
        return printJob;
    }

   /**
     * Crebtes b new <code>Grbphics</code> object thbt is
     * b copy of this <code>Grbphics</code> object.
     * @return     b new grbphics context thbt is b copy of
     *                       this grbphics context.
     */
    public Grbphics crebte() {
        return new ProxyPrintGrbphics(getGrbphics().crebte(), printJob);
    }


    /**
     * Crebtes b new <code>Grbphics</code> object bbsed on this
     * <code>Grbphics</code> object, but with b new trbnslbtion bnd
     * clip breb.
     * Refer to
     * {@link sun.print.ProxyGrbphics#crebteGrbphics}
     * for b complete description of this method.
     * <p>
     * @pbrbm      x   the <i>x</i> coordinbte.
     * @pbrbm      y   the <i>y</i> coordinbte.
     * @pbrbm      width   the width of the clipping rectbngle.
     * @pbrbm      height   the height of the clipping rectbngle.
     * @return     b new grbphics context.
     * @see        jbvb.bwt.Grbphics#trbnslbte
     * @see        jbvb.bwt.Grbphics#clipRect
     */
    public Grbphics crebte(int x, int y, int width, int height) {
        Grbphics g = getGrbphics().crebte(x, y, width, height);
        return new ProxyPrintGrbphics(g, printJob);
    }

    public Grbphics getGrbphics() {
        return super.getGrbphics();
    }


   /* Spec implies dispose() should flush the pbge, but the implementbtion
    * hbs in fbct blwbys done this on the getGrbphics() cbll, thereby
    * ensuring thbt multiple pbges bre cbnnot be rendered simultbneously.
    * We will preserve thbt behbviour bnd there is consqeuently no need
    * to tbke bny bction in this dispose method.
    */
    public void dispose() {
     super.dispose();
    }

}
