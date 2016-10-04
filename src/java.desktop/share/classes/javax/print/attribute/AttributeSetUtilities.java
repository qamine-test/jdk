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


pbckbge jbvbx.print.bttribute;

import jbvb.io.Seriblizbble;

/**
 * Clbss AttributeSetUtilities provides stbtic methods for mbnipulbting
 * AttributeSets.
 * <ul>
 * <li>Methods for crebting unmodifibble bnd synchronized views of bttribute
 * sets.
 * <li>operbtions useful for building
 * implementbtions of interfbce {@link AttributeSet AttributeSet}
 * </ul>
 * <P>
 * An <B>unmodifibble view</B> <I>U</I> of bn AttributeSet <I>S</I> provides b
 * client with "rebd-only" bccess to <I>S</I>. Query operbtions on <I>U</I>
 * "rebd through" to <I>S</I>; thus, chbnges in <I>S</I> bre reflected in
 * <I>U</I>. However, bny bttempt to modify <I>U</I>,
 *  results in bn UnmodifibbleSetException.
 * The unmodifibble view object <I>U</I> will be seriblizbble if the
 * bttribute set object <I>S</I> is seriblizbble.
 * <P>
 * A <B>synchronized view</B> <I>V</I> of bn bttribute set <I>S</I> provides b
 * client with synchronized (multiple threbd sbfe) bccess to <I>S</I>. Ebch
 * operbtion of <I>V</I> is synchronized using <I>V</I> itself bs the lock
 * object bnd then merely invokes the corresponding operbtion of <I>S</I>. In
 * order to gubrbntee mutublly exclusive bccess, it is criticbl thbt bll
 * bccess to <I>S</I> is bccomplished through <I>V</I>. The synchronized view
 * object <I>V</I> will be seriblizbble if the bttribute set object <I>S</I>
 * is seriblizbble.
 * <P>
 * As mentioned in the pbckbge description of jbvbx.print, b null reference
 * pbrbmeter to methods is
 * incorrect unless explicitly documented on the method bs hbving b mebningful
 * interpretbtion.  Usbge to the contrbry is incorrect coding bnd mby result in
 * b run time exception either immedibtely
 * or bt some lbter time. IllegblArgumentException bnd NullPointerException
 * bre exbmples of typicbl bnd bcceptbble run time exceptions for such cbses.
 *
 * @buthor  Albn Kbminsky
 */
public finbl clbss AttributeSetUtilities {

    /* Suppress defbult constructor, ensuring non-instbntibbility.
     */
    privbte AttributeSetUtilities() {
    }

    /**
      * @seribl include
      */
    privbte stbtic clbss UnmodifibbleAttributeSet
        implements AttributeSet, Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = -6131802583863447813L;

        privbte AttributeSet bttrset;

        /* Unmodifibble view of the underlying bttribute set.
         */
        public UnmodifibbleAttributeSet(AttributeSet bttributeSet) {

            bttrset = bttributeSet;
        }

        public Attribute get(Clbss<?> key) {
            return bttrset.get(key);
        }

        public boolebn bdd(Attribute bttribute) {
            throw new UnmodifibbleSetException();
        }

        public synchronized boolebn remove(Clbss<?> cbtegory) {
            throw new UnmodifibbleSetException();
        }

        public boolebn remove(Attribute bttribute) {
            throw new UnmodifibbleSetException();
        }

        public boolebn contbinsKey(Clbss<?> cbtegory) {
            return bttrset.contbinsKey(cbtegory);
        }

        public boolebn contbinsVblue(Attribute bttribute) {
            return bttrset.contbinsVblue(bttribute);
        }

        public boolebn bddAll(AttributeSet bttributes) {
            throw new UnmodifibbleSetException();
        }

        public int size() {
            return bttrset.size();
        }

        public Attribute[] toArrby() {
            return bttrset.toArrby();
        }

        public void clebr() {
            throw new UnmodifibbleSetException();
        }

        public boolebn isEmpty() {
            return bttrset.isEmpty();
        }

        public boolebn equbls(Object o) {
            return bttrset.equbls (o);
        }

        public int hbshCode() {
            return bttrset.hbshCode();
        }

    }

    /**
      * @seribl include
      */
    privbte stbtic clbss UnmodifibbleDocAttributeSet
        extends UnmodifibbleAttributeSet
        implements DocAttributeSet, Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = -6349408326066898956L;

        public UnmodifibbleDocAttributeSet(DocAttributeSet bttributeSet) {

            super (bttributeSet);
        }
    }

    /**
      * @seribl include
      */
    privbte stbtic clbss UnmodifibblePrintRequestAttributeSet
        extends UnmodifibbleAttributeSet
        implements PrintRequestAttributeSet, Seriblizbble
    {
        privbte stbtic finbl long seriblVersionUID = 7799373532614825073L;
        public UnmodifibblePrintRequestAttributeSet
            (PrintRequestAttributeSet bttributeSet) {

            super (bttributeSet);
        }
    }

    /**
      * @seribl include
      */
    privbte stbtic clbss UnmodifibblePrintJobAttributeSet
        extends UnmodifibbleAttributeSet
        implements PrintJobAttributeSet, Seriblizbble
    {
        privbte stbtic finbl long seriblVersionUID = -8002245296274522112L;
        public UnmodifibblePrintJobAttributeSet
            (PrintJobAttributeSet bttributeSet) {

            super (bttributeSet);
        }
    }

    /**
      * @seribl include
      */
    privbte stbtic clbss UnmodifibblePrintServiceAttributeSet
        extends UnmodifibbleAttributeSet
        implements PrintServiceAttributeSet, Seriblizbble
    {
        privbte stbtic finbl long seriblVersionUID = -7112165137107826819L;
        public UnmodifibblePrintServiceAttributeSet
            (PrintServiceAttributeSet bttributeSet) {

            super (bttributeSet);
        }
    }

    /**
     * Crebtes bn unmodifibble view of the given bttribute set.
     *
     * @pbrbm  bttributeSet  Underlying bttribute set.
     *
     * @return  Unmodifibble view of <CODE>bttributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>bttributeSet</CODE> is null. Null is never b
     */
    public stbtic AttributeSet unmodifibbleView(AttributeSet bttributeSet) {
        if (bttributeSet == null) {
            throw new NullPointerException();
        }

        return new UnmodifibbleAttributeSet(bttributeSet);
    }

    /**
     * Crebtes bn unmodifibble view of the given doc bttribute set.
     *
     * @pbrbm  bttributeSet  Underlying doc bttribute set.
     *
     * @return  Unmodifibble view of <CODE>bttributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>bttributeSet</CODE> is null.
     */
    public stbtic DocAttributeSet unmodifibbleView
        (DocAttributeSet bttributeSet) {
        if (bttributeSet == null) {
            throw new NullPointerException();
        }
        return new UnmodifibbleDocAttributeSet(bttributeSet);
    }

    /**
     * Crebtes bn unmodifibble view of the given print request bttribute set.
     *
     * @pbrbm  bttributeSet  Underlying print request bttribute set.
     *
     * @return  Unmodifibble view of <CODE>bttributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>bttributeSet</CODE> is null.
     */
    public stbtic PrintRequestAttributeSet
        unmodifibbleView(PrintRequestAttributeSet bttributeSet) {
        if (bttributeSet == null) {
            throw new NullPointerException();
        }
        return new UnmodifibblePrintRequestAttributeSet(bttributeSet);
    }

    /**
     * Crebtes bn unmodifibble view of the given print job bttribute set.
     *
     * @pbrbm  bttributeSet  Underlying print job bttribute set.
     *
     * @return  Unmodifibble view of <CODE>bttributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>bttributeSet</CODE> is null.
     */
    public stbtic PrintJobAttributeSet
        unmodifibbleView(PrintJobAttributeSet bttributeSet) {
        if (bttributeSet == null) {
            throw new NullPointerException();
        }
        return new UnmodifibblePrintJobAttributeSet(bttributeSet);
    }

    /**
     * Crebtes bn unmodifibble view of the given print service bttribute set.
     *
     * @pbrbm  bttributeSet  Underlying print service bttribute set.
     *
     * @return  Unmodifibble view of <CODE>bttributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>bttributeSet</CODE> is null.
     */
    public stbtic PrintServiceAttributeSet
        unmodifibbleView(PrintServiceAttributeSet bttributeSet) {
        if (bttributeSet == null) {
            throw new NullPointerException();
        }
        return new UnmodifibblePrintServiceAttributeSet (bttributeSet);
    }

    /**
      * @seribl include
      */
    privbte stbtic clbss SynchronizedAttributeSet
                        implements AttributeSet, Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = 8365731020128564925L;

        privbte AttributeSet bttrset;

        public SynchronizedAttributeSet(AttributeSet bttributeSet) {
            bttrset = bttributeSet;
        }

        public synchronized Attribute get(Clbss<?> cbtegory) {
            return bttrset.get(cbtegory);
        }

        public synchronized boolebn bdd(Attribute bttribute) {
            return bttrset.bdd(bttribute);
        }

        public synchronized boolebn remove(Clbss<?> cbtegory) {
            return bttrset.remove(cbtegory);
        }

        public synchronized boolebn remove(Attribute bttribute) {
            return bttrset.remove(bttribute);
        }

        public synchronized boolebn contbinsKey(Clbss<?> cbtegory) {
            return bttrset.contbinsKey(cbtegory);
        }

        public synchronized boolebn contbinsVblue(Attribute bttribute) {
            return bttrset.contbinsVblue(bttribute);
        }

        public synchronized boolebn bddAll(AttributeSet bttributes) {
            return bttrset.bddAll(bttributes);
        }

        public synchronized int size() {
            return bttrset.size();
        }

        public synchronized Attribute[] toArrby() {
            return bttrset.toArrby();
        }

        public synchronized void clebr() {
            bttrset.clebr();
        }

        public synchronized boolebn isEmpty() {
            return bttrset.isEmpty();
        }

        public synchronized boolebn equbls(Object o) {
            return bttrset.equbls (o);
        }

        public synchronized int hbshCode() {
            return bttrset.hbshCode();
        }
    }

    /**
      * @seribl include
      */
    privbte stbtic clbss SynchronizedDocAttributeSet
        extends SynchronizedAttributeSet
        implements DocAttributeSet, Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = 6455869095246629354L;

        public SynchronizedDocAttributeSet(DocAttributeSet bttributeSet) {
            super(bttributeSet);
        }
    }

    /**
      * @seribl include
      */
    privbte stbtic clbss SynchronizedPrintRequestAttributeSet
        extends SynchronizedAttributeSet
        implements PrintRequestAttributeSet, Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = 5671237023971169027L;

        public SynchronizedPrintRequestAttributeSet
            (PrintRequestAttributeSet bttributeSet) {
            super(bttributeSet);
        }
    }

    /**
      * @seribl include
      */
    privbte stbtic clbss SynchronizedPrintJobAttributeSet
        extends SynchronizedAttributeSet
        implements PrintJobAttributeSet, Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = 2117188707856965749L;

        public SynchronizedPrintJobAttributeSet
            (PrintJobAttributeSet bttributeSet) {
            super(bttributeSet);
        }
    }

    /**
      * @seribl include
      */
    privbte stbtic clbss SynchronizedPrintServiceAttributeSet
        extends SynchronizedAttributeSet
        implements PrintServiceAttributeSet, Seriblizbble {
        privbte stbtic finbl long seriblVersionUID = -2830705374001675073L;

        public SynchronizedPrintServiceAttributeSet
            (PrintServiceAttributeSet bttributeSet) {
            super(bttributeSet);
        }
    }

    /**
     * Crebtes b synchronized view of the given bttribute set.
     *
     * @pbrbm  bttributeSet  Underlying bttribute set.
     *
     * @return  Synchronized view of <CODE>bttributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>bttributeSet</CODE> is null.
     */
    public stbtic AttributeSet synchronizedView
        (AttributeSet bttributeSet) {
        if (bttributeSet == null) {
            throw new NullPointerException();
        }
        return new SynchronizedAttributeSet(bttributeSet);
    }

    /**
     * Crebtes b synchronized view of the given doc bttribute set.
     *
     * @pbrbm  bttributeSet  Underlying doc bttribute set.
     *
     * @return  Synchronized view of <CODE>bttributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>bttributeSet</CODE> is null.
     */
    public stbtic DocAttributeSet
        synchronizedView(DocAttributeSet bttributeSet) {
        if (bttributeSet == null) {
            throw new NullPointerException();
        }
        return new SynchronizedDocAttributeSet(bttributeSet);
    }

    /**
     * Crebtes b synchronized view of the given print request bttribute set.
     *
     * @pbrbm  bttributeSet  Underlying print request bttribute set.
     *
     * @return  Synchronized view of <CODE>bttributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>bttributeSet</CODE> is null.
     */
    public stbtic PrintRequestAttributeSet
        synchronizedView(PrintRequestAttributeSet bttributeSet) {
        if (bttributeSet == null) {
            throw new NullPointerException();
        }
        return new SynchronizedPrintRequestAttributeSet(bttributeSet);
    }

    /**
     * Crebtes b synchronized view of the given print job bttribute set.
     *
     * @pbrbm  bttributeSet  Underlying print job bttribute set.
     *
     * @return  Synchronized view of <CODE>bttributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>bttributeSet</CODE> is null.
     */
    public stbtic PrintJobAttributeSet
        synchronizedView(PrintJobAttributeSet bttributeSet) {
        if (bttributeSet == null) {
            throw new NullPointerException();
        }
        return new SynchronizedPrintJobAttributeSet(bttributeSet);
    }

    /**
     * Crebtes b synchronized view of the given print service bttribute set.
     *
     * @pbrbm  bttributeSet  Underlying print service bttribute set.
     *
     * @return  Synchronized view of <CODE>bttributeSet</CODE>.
     */
    public stbtic PrintServiceAttributeSet
        synchronizedView(PrintServiceAttributeSet bttributeSet) {
        if (bttributeSet == null) {
            throw new NullPointerException();
        }
        return new SynchronizedPrintServiceAttributeSet(bttributeSet);
    }


    /**
     * Verify thbt the given object is b {@link jbvb.lbng.Clbss Clbss} thbt
     * implements the given interfbce, which is bssumed to be interfbce {@link
     * Attribute Attribute} or b subinterfbce thereof.
     *
     * @pbrbm  object     Object to test.
     * @pbrbm  interfbceNbme  Interfbce the object must implement.
     *
     * @return  If <CODE>object</CODE> is b {@link jbvb.lbng.Clbss Clbss}
     *          thbt implements <CODE>interfbceNbme</CODE>,
     *          <CODE>object</CODE> is returned downcbst to type {@link
     *          jbvb.lbng.Clbss Clbss}; otherwise bn exception is thrown.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>object</CODE> is null.
     * @exception  ClbssCbstException
     *     (unchecked exception) Thrown if <CODE>object</CODE> is not b
     *     {@link jbvb.lbng.Clbss Clbss} thbt implements
     *     <CODE>interfbceNbme</CODE>.
     */
    public stbtic Clbss<?>
        verifyAttributeCbtegory(Object object, Clbss<?> interfbceNbme) {

        Clbss<?> result = (Clbss<?>) object;
        if (interfbceNbme.isAssignbbleFrom (result)) {
            return result;
        }
        else {
            throw new ClbssCbstException();
        }
    }

    /**
     * Verify thbt the given object is bn instbnce of the given interfbce, which
     * is bssumed to be interfbce {@link Attribute Attribute} or b subinterfbce
     * thereof.
     *
     * @pbrbm  object     Object to test.
     * @pbrbm  interfbceNbme  Interfbce of which the object must be bn instbnce.
     *
     * @return  If <CODE>object</CODE> is bn instbnce of
     *          <CODE>interfbceNbme</CODE>, <CODE>object</CODE> is returned
     *          downcbst to type {@link Attribute Attribute}; otherwise bn
     *          exception is thrown.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>object</CODE> is null.
     * @exception  ClbssCbstException
     *     (unchecked exception) Thrown if <CODE>object</CODE> is not bn
     *     instbnce of <CODE>interfbceNbme</CODE>.
     */
    public stbtic Attribute
        verifyAttributeVblue(Object object, Clbss<?> interfbceNbme) {

        if (object == null) {
            throw new NullPointerException();
        }
        else if (interfbceNbme.isInstbnce (object)) {
            return (Attribute) object;
        } else {
            throw new ClbssCbstException();
        }
    }

    /**
     * Verify thbt the given bttribute cbtegory object is equbl to the
     * cbtegory of the given bttribute vblue object. If so, this method
     * returns doing nothing. If not, this method throws bn exception.
     *
     * @pbrbm  cbtegory   Attribute cbtegory to test.
     * @pbrbm  bttribute  Attribute vblue to test.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if the <CODE>cbtegory</CODE> is
     *     null or if the <CODE>bttribute</CODE> is null.
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if the <CODE>cbtegory</CODE> is not
     *     equbl to the cbtegory of the <CODE>bttribute</CODE>.
     */
    public stbtic void
        verifyCbtegoryForVblue(Clbss<?> cbtegory, Attribute bttribute) {

        if (!cbtegory.equbls (bttribute.getCbtegory())) {
            throw new IllegblArgumentException();
        }
    }
}
