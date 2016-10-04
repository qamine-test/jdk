/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.print;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ChbrArrbyRebder;
import jbvb.io.StringRebder;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.Rebder;
import jbvbx.print.bttribute.AttributeSetUtilities;
import jbvbx.print.bttribute.DocAttributeSet;

/**
 * This clbss is bn implementbtion of interfbce <code>Doc</code> thbt cbn
 * be used in mbny common printing requests.
 * It cbn hbndle bll of the presently defined "pre-defined" doc flbvors
 * defined bs stbtic vbribbles in the DocFlbvor clbss.
 * <p>
 * In pbrticulbr this clbss implements certbin required sembntics of the
 * Doc specificbtion bs follows:
 * <ul>
 * <li>constructs b strebm for the service if requested bnd bppropribte.
 * <li>ensures the sbme object is returned for ebch cbll on b method.
 * <li>ensures multiple threbds cbn bccess the Doc
 * <li>performs some vblidbtion of thbt the dbtb mbtches the doc flbvor.
 * </ul>
 * Clients who wbnt to re-use the doc object in other jobs,
 * or need b MultiDoc will not wbnt to use this clbss.
 * <p>
 * If the print dbtb is b strebm, or b print job requests dbtb bs b
 * strebm, then <code>SimpleDoc</code> does not monitor if the service
 * properly closes the strebm bfter dbtb trbnsfer completion or job
 * terminbtion.
 * Clients mby prefer to use provide their own implementbtion of doc thbt
 * bdds b listener to monitor job completion bnd to vblidbte thbt
 * resources such bs strebms bre freed (ie closed).
 */

public finbl clbss SimpleDoc implements Doc {

    privbte DocFlbvor flbvor;
    privbte DocAttributeSet bttributes;
    privbte Object printDbtb;
    privbte Rebder rebder;
    privbte InputStrebm inStrebm;

    /**
     * Constructs b <code>SimpleDoc</code> with the specified
     * print dbtb, doc flbvor bnd doc bttribute set.
     * @pbrbm printDbtb the print dbtb object
     * @pbrbm flbvor the <code>DocFlbvor</code> object
     * @pbrbm bttributes b <code>DocAttributeSet</code>, which cbn
     *                   be <code>null</code>
     * @throws IllegblArgumentException if <code>flbvor</code> or
     *         <code>printDbtb</code> is <code>null</code>, or the
     *         <code>printDbtb</code> does not correspond
     *         to the specified doc flbvor--for exbmple, the dbtb is
     *         not of the type specified bs the representbtion in the
     *         <code>DocFlbvor</code>.
     */
    public SimpleDoc(Object printDbtb,
                     DocFlbvor flbvor, DocAttributeSet bttributes) {

       if (flbvor == null || printDbtb == null) {
           throw new IllegblArgumentException("null brgument(s)");
       }

       Clbss<?> repClbss = null;
       try {
            String clbssNbme = flbvor.getRepresentbtionClbssNbme();
            sun.reflect.misc.ReflectUtil.checkPbckbgeAccess(clbssNbme);
            repClbss = Clbss.forNbme(clbssNbme, fblse,
                              Threbd.currentThrebd().getContextClbssLobder());
       } cbtch (Throwbble e) {
           throw new IllegblArgumentException("unknown representbtion clbss");
       }

       if (!repClbss.isInstbnce(printDbtb)) {
           throw new IllegblArgumentException("dbtb is not of declbred type");
       }

       this.flbvor = flbvor;
       if (bttributes != null) {
           this.bttributes = AttributeSetUtilities.unmodifibbleView(bttributes);
       }
       this.printDbtb = printDbtb;
    }

   /**
     * Determines the doc flbvor in which this doc object will supply its
     * piece of print dbtb.
     *
     * @return  Doc flbvor.
     */
    public DocFlbvor getDocFlbvor() {
        return flbvor;
    }

    /**
     * Obtbins the set of printing bttributes for this doc object. If the
     * returned bttribute set includes bn instbnce of b pbrticulbr bttribute
     * <I>X,</I> the printer must use thbt bttribute vblue for this doc,
     * overriding bny vblue of bttribute <I>X</I> in the job's bttribute set.
     * If the returned bttribute set does not include bn instbnce
     * of b pbrticulbr bttribute <I>X</I> or if null is returned, the printer
     * must consult the job's bttribute set to obtbin the vblue for
     * bttribute <I>X,</I> bnd if not found there, the printer must use bn
     * implementbtion-dependent defbult vblue. The returned bttribute set is
     * unmodifibble.
     *
     * @return  Unmodifibble set of printing bttributes for this doc, or null
     *          to obtbin bll bttribute vblues from the job's bttribute
     *          set.
     */
    public DocAttributeSet getAttributes() {
        return bttributes;
    }

    /*
     * Obtbins the print dbtb representbtion object thbt contbins this doc
     * object's piece of print dbtb in the formbt corresponding to the
     * supported doc flbvor.
     * The <CODE>getPrintDbtb()</CODE> method returns bn instbnce of
     * the representbtion clbss whose nbme is given by
     * {@link DocFlbvor#getRepresentbtionClbssNbme() getRepresentbtionClbssNbme},
     * bnd the return vblue cbn be cbst
     * from clbss Object to thbt representbtion clbss.
     *
     * @return  Print dbtb representbtion object.
     *
     * @exception  IOException if the representbtion clbss is b strebm bnd
     *     there wbs bn I/O error while constructing the strebm.
     */
    public Object getPrintDbtb() throws IOException {
        return printDbtb;
    }

    /**
     * Obtbins b rebder for extrbcting chbrbcter print dbtb from this doc.
     * The <code>Doc</code> implementbtion is required to support this
     * method if the <code>DocFlbvor</code> hbs one of the following print
     * dbtb representbtion clbsses, bnd return <code>null</code>
     * otherwise:
     * <UL>
     * <LI> <code>chbr[]</code>
     * <LI> <code>jbvb.lbng.String</code>
     * <LI> <code>jbvb.io.Rebder</code>
     * </UL>
     * The doc's print dbtb representbtion object is used to construct bnd
     * return b <code>Rebder</code> for rebding the print dbtb bs b strebm
     * of chbrbcters from the print dbtb representbtion object.
     * However, if the print dbtb representbtion object is itself b
     * <code>Rebder</code> then the print dbtb representbtion object is
     * simply returned.
     *
     * @return  b <code>Rebder</code> for rebding the print dbtb
     *          chbrbcters from this doc.
     *          If b rebder cbnnot be provided becbuse this doc does not meet
     *          the criterib stbted bbove, <code>null</code> is returned.
     *
     * @exception  IOException if there wbs bn I/O error while crebting
     *             the rebder.
     */
    public Rebder getRebderForText() throws IOException {

        if (printDbtb instbnceof Rebder) {
            return (Rebder)printDbtb;
        }

        synchronized (this) {
            if (rebder != null) {
                return rebder;
            }

            if (printDbtb instbnceof chbr[]) {
               rebder = new ChbrArrbyRebder((chbr[])printDbtb);
            }
            else if (printDbtb instbnceof String) {
                rebder = new StringRebder((String)printDbtb);
            }
        }
        return rebder;
    }

    /**
     * Obtbins bn input strebm for extrbcting byte print dbtb from
     * this doc.
     * The <code>Doc</code> implementbtion is required to support this
     * method if the <code>DocFlbvor</code> hbs one of the following print
     * dbtb representbtion clbsses; otherwise this method
     * returns <code>null</code>:
     * <UL>
     * <LI> <code>byte[]</code>
     * <LI> <code>jbvb.io.InputStrebm</code>
     * </UL>
     * The doc's print dbtb representbtion object is obtbined.  Then, bn
     * input strebm for rebding the print dbtb
     * from the print dbtb representbtion object bs b strebm of bytes is
     * crebted bnd returned.
     * However, if the print dbtb representbtion object is itself bn
     * input strebm then the print dbtb representbtion object is simply
     * returned.
     *
     * @return  bn <code>InputStrebm</code> for rebding the print dbtb
     *          bytes from this doc.  If bn input strebm cbnnot be
     *          provided becbuse this doc does not meet
     *          the criterib stbted bbove, <code>null</code> is returned.
     *
     * @exception  IOException
     *     if there wbs bn I/O error while crebting the input strebm.
     */
    public InputStrebm getStrebmForBytes() throws IOException {

        if (printDbtb instbnceof InputStrebm) {
            return (InputStrebm)printDbtb;
        }

        synchronized (this) {
            if (inStrebm != null) {
                return inStrebm;
            }

            if (printDbtb instbnceof byte[]) {
               inStrebm = new ByteArrbyInputStrebm((byte[])printDbtb);
            }
        }
        return inStrebm;
    }

}
