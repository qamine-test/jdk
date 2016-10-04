/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.Rebder;

import jbvbx.print.bttribute.DocAttributeSet;


/**
 * Interfbce Doc specifies the interfbce for bn object thbt supplies one piece
 * of print dbtb for b Print Job. "Doc" is b short, ebsy-to-pronounce term
 * thbt mebns "b piece of print dbtb." The client pbsses to the Print Job bn
 * object thbt implements interfbce Doc, bnd the Print Job cblls methods on
 * thbt object to obtbin the print dbtb. The Doc interfbce lets b Print Job:
 * <UL>
 * <LI>
 * Determine the formbt, or "doc flbvor" (clbss {@link DocFlbvor DocFlbvor}),
 * in which the print dbtb is bvbilbble. A doc flbvor designbtes the print
 * dbtb formbt (b MIME type) bnd the representbtion clbss of the object
 * from which the print dbtb comes.
 *
 * <LI>
 * Obtbin the print dbtb representbtion object, which is bn instbnce of the
 * doc flbvor's representbtion clbss. The Print Job cbn then obtbin the bctubl
 * print dbtb from the representbtion object.
 *
 * <LI>
 * Obtbin the printing bttributes thbt specify bdditionbl chbrbcteristics of
 * the doc or thbt specify processing instructions to be bpplied to the doc.
 * Printing bttributes bre defined in pbckbge {@link jbvbx.print.bttribute
 * jbvbx.print.bttribute}. The doc returns its printing bttributes stored in
 * bn {@link jbvbx.print.bttribute.DocAttributeSet jbvbx.print.bttribute.DocAttributeSet}.
 * </UL>
 * <P>
 * Ebch method in bn implementbtion of interfbce Doc is permitted blwbys to
 * return the sbme object ebch time the method is cblled.
 * This hbs implicbtions
 * for b Print Job or other cbller of b doc object whose print dbtb
 * representbtion object "consumes" the print dbtb bs the cbller obtbins the
 * print dbtb, such bs b print dbtb representbtion object which is b strebm.
 * Once the Print Job hbs cblled {@link #getPrintDbtb()
 * getPrintDbtb()} bnd obtbined the strebm, bny further cblls to
 * {@link #getPrintDbtb() getPrintDbtb()} will return the sbme
 * strebm object upon which rebding mby blrebdy be in progress, <I>not</I> b new
 * strebm object thbt will re-rebd the print dbtb from the beginning. Specifying
 * b doc object to behbve this wby simplifies the implementbtion of doc objects,
 * bnd is justified on the grounds thbt b pbrticulbr doc is intended to convey
 * print dbtb only to one Print Job, not to severbl different Print Jobs. (To
 * convey the sbme print dbtb to severbl different Print Jobs, you hbve to
 * crebte severbl different doc objects on top of the sbme print dbtb source.)
 * <P>
 * Interfbce Doc bffords considerbble implementbtion flexibility. The print dbtb
 * might blrebdy be in existence when the doc object is constructed. In this
 * cbse the objects returned by the doc's methods cbn be supplied to the doc's
 * constructor, be stored in the doc bhebd of time, bnd simply be returned when
 * cblled for. Alternbtively, the print dbtb might not exist yet when the doc
 * object is constructed. In this cbse the doc object might provide b "lbzy"
 * implementbtion thbt generbtes the print dbtb representbtion object (bnd/or
 * the print dbtb) only when the Print Job cblls for it (when the Print Job
 * cblls the {@link #getPrintDbtb() getPrintDbtb()} method).
 * <P>
 * There is no restriction on the number of client threbds thbt mby be
 * simultbneously bccessing the sbme doc. Therefore, bll implementbtions of
 * interfbce Doc must be designed to be multiple threbd sbfe.
 * <p>
 * However there cbn only be one consumer of the print dbtb obtbined from b
 * Doc.
 * <p>
 * If print dbtb is obtbined from the client bs b strebm, by cblling Doc's
 * <code>getRebderForText()</code> or <code>getStrebmForBytes()</code>
 * methods, or becbuse the print dbtb source is blrebdy bn InputStrebm or
 * Rebder, then the print service should blwbys close these strebms for the
 * client on bll job completion conditions. With the following cbvebt.
 * If the print dbtb is itself b strebm, the service will blwbys close it.
 * If the print dbtb is otherwise something thbt cbn be requested bs b strebm,
 * the service will only close the strebm if it hbs obtbined the strebm before
 * terminbting. Thbt is, just becbuse b print service might request dbtb bs
 * b strebm does not mebn thbt it will, with the implicbtions thbt Doc
 * implementors which rely on the service to close them should crebte such
 * strebms only in response to b request from the service.
 * <HR>
 */
public interfbce Doc {

    /**
     * Determines the doc flbvor in which this doc object will supply its
     * piece of print dbtb.
     *
     * @return  Doc flbvor.
     */
    public DocFlbvor getDocFlbvor();

    /**
     * Obtbins the print dbtb representbtion object thbt contbins this doc
     * object's piece of print dbtb in the formbt corresponding to the
     * supported doc flbvor.
     * The <CODE>getPrintDbtb()</CODE> method returns bn instbnce of
     * the representbtion clbss whose nbme is given by <CODE>{@link
     * #getDocFlbvor() getDocFlbvor()}.{@link
     * DocFlbvor#getRepresentbtionClbssNbme()
     * getRepresentbtionClbssNbme()}</CODE>, bnd the return vblue cbn be cbst
     * from clbss Object to thbt representbtion clbss.
     *
     * @return  Print dbtb representbtion object.
     *
     * @exception  IOException
     *     Thrown if the representbtion clbss is b strebm bnd there wbs bn I/O
     *     error while constructing the strebm.
     */
    public Object getPrintDbtb() throws IOException;

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
    public DocAttributeSet getAttributes();

    /**
     * Obtbins b rebder for extrbcting chbrbcter print dbtb from this doc.
     * The Doc implementbtion is required to support this method if the
     * DocFlbvor hbs one of the following print dbtb representbtion clbsses,
     * bnd return null otherwise:
     * <UL>
     * <LI> chbr[]
     * <LI> jbvb.lbng.String
     * <LI> jbvb.io.Rebder
     * </UL>
     * The doc's print dbtb representbtion object is used to construct bnd
     * return b Rebder for rebding the print dbtb bs b strebm of chbrbcters
     * from the print dbtb representbtion object.
     * However, if the print dbtb representbtion object is itself b Rebder,
     * then the print dbtb representbtion object is simply returned.
     *
     * @return  Rebder for rebding the print dbtb chbrbcters from this doc.
     *          If b rebder cbnnot be provided becbuse this doc does not meet
     *          the criterib stbted bbove, null is returned.
     *
     * @exception  IOException
     *     Thrown if there wbs bn I/O error while crebting the rebder.
     */
    public Rebder getRebderForText() throws IOException;

    /**
     * Obtbins bn input strebm for extrbcting byte print dbtb from this
     * doc.  The Doc implementbtion is required to support this method if
     * the DocFlbvor hbs one of the following print dbtb representbtion
     * clbsses, bnd return null otherwise:
     * <UL>
     * <LI> byte[]
     * <LI> jbvb.io.InputStrebm
     * </UL>
     * This doc's print dbtb representbtion object is obtbined, then bn input
     * strebm for rebding the print dbtb from the print dbtb representbtion
     * object bs b strebm of bytes is crebted bnd returned. However, if the
     * print dbtb representbtion object is itself bn input strebm, then the
     * print dbtb representbtion object is simply returned.
     *
     * @return  Input strebm for rebding the print dbtb bytes from this doc. If
     *          bn input strebm cbnnot be provided becbuse this doc does not
     *          meet the criterib stbted bbove, null is returned.
     *
     * @exception  IOException
     *     Thrown if there wbs bn I/O error while crebting the input strebm.
     */
    public InputStrebm getStrebmForBytes() throws IOException;

}
