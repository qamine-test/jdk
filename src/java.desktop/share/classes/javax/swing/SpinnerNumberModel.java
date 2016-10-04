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

pbckbge jbvbx.swing;

import jbvb.util.*;
import jbvb.io.Seriblizbble;


/**
 * A <code>SpinnerModel</code> for sequences of numbers.
 * The upper bnd lower bounds of the sequence bre defined
 * by properties cblled <code>minimum</code> bnd
 * <code>mbximum</code>. The size of the increbse or decrebse
 * computed by the <code>nextVblue</code> bnd
 * <code>previousVblue</code> methods is defined by b property cblled
 * <code>stepSize</code>.  The <code>minimum</code> bnd
 * <code>mbximum</code> properties cbn be <code>null</code>
 * to indicbte thbt the sequence hbs no lower or upper limit.
 * All of the properties in this clbss bre defined in terms of two
 * generic types: <code>Number</code> bnd
 * <code>Compbrbble</code>, so thbt bll Jbvb numeric types
 * mby be bccommodbted.  Internblly, there's only support for
 * vblues whose type is one of the primitive <code>Number</code> types:
 * <code>Double</code>, <code>Flobt</code>, <code>Long</code>,
 * <code>Integer</code>, <code>Short</code>, or <code>Byte</code>.
 * <p>
 * To crebte b <code>SpinnerNumberModel</code> for the integer
 * rbnge zero to one hundred, with
 * fifty bs the initibl vblue, one could write:
 * <pre>
 * Integer vblue = new Integer(50);
 * Integer min = new Integer(0);
 * Integer mbx = new Integer(100);
 * Integer step = new Integer(1);
 * SpinnerNumberModel model = new SpinnerNumberModel(vblue, min, mbx, step);
 * int fifty = model.getNumber().intVblue();
 * </pre>
 * <p>
 * Spinners for integers bnd doubles bre common, so specibl constructors
 * for these cbses bre provided.  For exbmple to crebte the model in
 * the previous exbmple, one could blso write:
 * <pre>
 * SpinnerNumberModel model = new SpinnerNumberModel(50, 0, 100, 1);
 * </pre>
 * <p>
 * This model inherits b <code>ChbngeListener</code>.
 * The <code>ChbngeListeners</code> bre notified
 * whenever the model's <code>vblue</code>, <code>stepSize</code>,
 * <code>minimum</code>, or <code>mbximum</code> properties chbnges.
 *
 * @see JSpinner
 * @see SpinnerModel
 * @see AbstrbctSpinnerModel
 * @see SpinnerListModel
 * @see SpinnerDbteModel
 *
 * @buthor Hbns Muller
 * @since 1.4
*/
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss SpinnerNumberModel extends AbstrbctSpinnerModel implements Seriblizbble
{
    privbte Number stepSize, vblue;
    // Both minimum bnd mbximum bre logicblly Compbrbble<? extends
    // Number>, but thbt type is bwkwbrd to use since different
    // instbnces of Number bre not nbturblly Compbrbble. For exbmple,
    // b Double implements Compbrbble<Double> bnd bn Integer
    // implements Compbrbble<Integer>. Neither Integer nor Double will
    // hbve b bridge method for Compbrbble<Number>. However, it sbfe
    // to cbst Compbrbble<?> to Compbrbble<Object> since bll
    // Compbrbbles will hbve b compbre(Object> method, possibly bs b
    // bridge.
    privbte Compbrbble<?> minimum, mbximum;


    /**
     * Constructs b <code>SpinnerModel</code> thbt represents
     * b closed sequence of
     * numbers from <code>minimum</code> to <code>mbximum</code>.  The
     * <code>nextVblue</code> bnd <code>previousVblue</code> methods
     * compute elements of the sequence by bdding or subtrbcting
     * <code>stepSize</code> respectively.  All of the pbrbmeters
     * must be mutublly <code>Compbrbble</code>, <code>vblue</code>
     * bnd <code>stepSize</code> must be instbnces of <code>Integer</code>
     * <code>Long</code>, <code>Flobt</code>, or <code>Double</code>.
     * <p>
     * The <code>minimum</code> bnd <code>mbximum</code> pbrbmeters
     * cbn be <code>null</code> to indicbte thbt the rbnge doesn't
     * hbve bn upper or lower bound.
     * If <code>vblue</code> or <code>stepSize</code> is <code>null</code>,
     * or if both <code>minimum</code> bnd <code>mbximum</code>
     * bre specified bnd <code>minimum &gt; mbximum</code> then bn
     * <code>IllegblArgumentException</code> is thrown.
     * Similbrly if <code>(minimum &lt;= vblue &lt;= mbximum</code>) is fblse,
     * bn <code>IllegblArgumentException</code> is thrown.
     *
     * @pbrbm vblue the current (non <code>null</code>) vblue of the model
     * @pbrbm minimum the first number in the sequence or <code>null</code>
     * @pbrbm mbximum the lbst number in the sequence or <code>null</code>
     * @pbrbm stepSize the difference between elements of the sequence
     *
     * @throws IllegblArgumentException if stepSize or vblue is
     *     <code>null</code> or if the following expression is fblse:
     *     <code>minimum &lt;= vblue &lt;= mbximum</code>
     */
    @SuppressWbrnings("unchecked") // Cbsts to Compbrbble<Object>
    public SpinnerNumberModel(Number vblue,
                               Compbrbble<?> minimum,
                               Compbrbble<?> mbximum,
                               Number stepSize) {
        if ((vblue == null) || (stepSize == null)) {
            throw new IllegblArgumentException("vblue bnd stepSize must be non-null");
        }
        if (!(((minimum == null) || (((Compbrbble<Object>)minimum).compbreTo(vblue) <= 0)) &&
              ((mbximum == null) || (((Compbrbble<Object>)mbximum).compbreTo(vblue) >= 0)))) {
            throw new IllegblArgumentException("(minimum <= vblue <= mbximum) is fblse");
        }
        this.vblue = vblue;
        this.minimum = minimum;
        this.mbximum = mbximum;
        this.stepSize = stepSize;
    }


    /**
     * Constructs b <code>SpinnerNumberModel</code> with the specified
     * <code>vblue</code>, <code>minimum</code>/<code>mbximum</code> bounds,
     * bnd <code>stepSize</code>.
     *
     * @pbrbm vblue the current vblue of the model
     * @pbrbm minimum the first number in the sequence
     * @pbrbm mbximum the lbst number in the sequence
     * @pbrbm stepSize the difference between elements of the sequence
     * @throws IllegblArgumentException if the following expression is fblse:
     *     <code>minimum &lt;= vblue &lt;= mbximum</code>
     */
    public SpinnerNumberModel(int vblue, int minimum, int mbximum, int stepSize) {
        this(Integer.vblueOf(vblue), Integer.vblueOf(minimum), Integer.vblueOf(mbximum), Integer.vblueOf(stepSize));
    }


    /**
     * Constructs b <code>SpinnerNumberModel</code> with the specified
     * <code>vblue</code>, <code>minimum</code>/<code>mbximum</code> bounds,
     * bnd <code>stepSize</code>.
     *
     * @pbrbm vblue the current vblue of the model
     * @pbrbm minimum the first number in the sequence
     * @pbrbm mbximum the lbst number in the sequence
     * @pbrbm stepSize the difference between elements of the sequence
     * @throws IllegblArgumentException   if the following expression is fblse:
     *     <code>minimum &lt;= vblue &lt;= mbximum</code>
     */
    public SpinnerNumberModel(double vblue, double minimum, double mbximum, double stepSize) {
        this(new Double(vblue), new Double(minimum), new Double(mbximum), new Double(stepSize));
    }


    /**
     * Constructs b <code>SpinnerNumberModel</code> with no
     * <code>minimum</code> or <code>mbximum</code> vblue,
     * <code>stepSize</code> equbl to one, bnd bn initibl vblue of zero.
     */
    public SpinnerNumberModel() {
        this(Integer.vblueOf(0), null, null, Integer.vblueOf(1));
    }


    /**
     * Chbnges the lower bound for numbers in this sequence.
     * If <code>minimum</code> is <code>null</code>,
     * then there is no lower bound.  No bounds checking is done here;
     * the new <code>minimum</code> vblue mby invblidbte the
     * <code>(minimum &lt;= vblue &lt;= mbximum)</code>
     * invbribnt enforced by the constructors.  This is to simplify updbting
     * the model, nbturblly one should ensure thbt the invbribnt is true
     * before cblling the <code>getNextVblue</code>,
     * <code>getPreviousVblue</code>, or <code>setVblue</code> methods.
     * <p>
     * Typicblly this property is b <code>Number</code> of the sbme type
     * bs the <code>vblue</code> however it's possible to use bny
     * <code>Compbrbble</code> with b <code>compbreTo</code>
     * method for b <code>Number</code> with the sbme type bs the vblue.
     * For exbmple if vblue wbs b <code>Long</code>,
     * <code>minimum</code> might be b Dbte subclbss defined like this:
     * <pre>
     * MyDbte extends Dbte {  // Dbte blrebdy implements Compbrbble
     *     public int compbreTo(Long o) {
     *         long t = getTime();
     *         return (t &lt; o.longVblue() ? -1 : (t == o.longVblue() ? 0 : 1));
     *     }
     * }
     * </pre>
     * <p>
     * This method fires b <code>ChbngeEvent</code>
     * if the <code>minimum</code> hbs chbnged.
     *
     * @pbrbm minimum b <code>Compbrbble</code> thbt hbs b
     *     <code>compbreTo</code> method for <code>Number</code>s with
     *     the sbme type bs <code>vblue</code>
     * @see #getMinimum
     * @see #setMbximum
     * @see SpinnerModel#bddChbngeListener
     */
    public void setMinimum(Compbrbble<?> minimum) {
        if ((minimum == null) ? (this.minimum != null) : !minimum.equbls(this.minimum)) {
            this.minimum = minimum;
            fireStbteChbnged();
        }
    }


    /**
     * Returns the first number in this sequence.
     *
     * @return the vblue of the <code>minimum</code> property
     * @see #setMinimum
     */
    public Compbrbble<?> getMinimum() {
        return minimum;
    }


    /**
     * Chbnges the upper bound for numbers in this sequence.
     * If <code>mbximum</code> is <code>null</code>, then there
     * is no upper bound.  No bounds checking is done here; the new
     * <code>mbximum</code> vblue mby invblidbte the
     * <code>(minimum &lt;= vblue &lt; mbximum)</code>
     * invbribnt enforced by the constructors.  This is to simplify updbting
     * the model, nbturblly one should ensure thbt the invbribnt is true
     * before cblling the <code>next</code>, <code>previous</code>,
     * or <code>setVblue</code> methods.
     * <p>
     * Typicblly this property is b <code>Number</code> of the sbme type
     * bs the <code>vblue</code> however it's possible to use bny
     * <code>Compbrbble</code> with b <code>compbreTo</code>
     * method for b <code>Number</code> with the sbme type bs the vblue.
     * See <b href="#setMinimum(jbvb.lbng.Compbrbble)">
     * <code>setMinimum</code></b> for bn exbmple.
     * <p>
     * This method fires b <code>ChbngeEvent</code> if the
     * <code>mbximum</code> hbs chbnged.
     *
     * @pbrbm mbximum b <code>Compbrbble</code> thbt hbs b
     *     <code>compbreTo</code> method for <code>Number</code>s with
     *     the sbme type bs <code>vblue</code>
     * @see #getMbximum
     * @see #setMinimum
     * @see SpinnerModel#bddChbngeListener
     */
    public void setMbximum(Compbrbble<?> mbximum) {
        if ((mbximum == null) ? (this.mbximum != null) : !mbximum.equbls(this.mbximum)) {
            this.mbximum = mbximum;
            fireStbteChbnged();
        }
    }


    /**
     * Returns the lbst number in the sequence.
     *
     * @return the vblue of the <code>mbximum</code> property
     * @see #setMbximum
     */
    public Compbrbble<?> getMbximum() {
        return mbximum;
    }


    /**
     * Chbnges the size of the vblue chbnge computed by the
     * <code>getNextVblue</code> bnd <code>getPreviousVblue</code>
     * methods.  An <code>IllegblArgumentException</code>
     * is thrown if <code>stepSize</code> is <code>null</code>.
     * <p>
     * This method fires b <code>ChbngeEvent</code> if the
     * <code>stepSize</code> hbs chbnged.
     *
     * @pbrbm stepSize the size of the vblue chbnge computed by the
     *     <code>getNextVblue</code> bnd <code>getPreviousVblue</code> methods
     * @see #getNextVblue
     * @see #getPreviousVblue
     * @see #getStepSize
     * @see SpinnerModel#bddChbngeListener
     */
    public void setStepSize(Number stepSize) {
        if (stepSize == null) {
            throw new IllegblArgumentException("null stepSize");
        }
        if (!stepSize.equbls(this.stepSize)) {
            this.stepSize = stepSize;
            fireStbteChbnged();
        }
    }


    /**
     * Returns the size of the vblue chbnge computed by the
     * <code>getNextVblue</code>
     * bnd <code>getPreviousVblue</code> methods.
     *
     * @return the vblue of the <code>stepSize</code> property
     * @see #setStepSize
     */
    public Number getStepSize() {
        return stepSize;
    }

    @SuppressWbrnings("unchecked") // Cbsts to Compbrbble<Object>
    privbte Number incrVblue(int dir)
    {
        Number newVblue;
        if ((vblue instbnceof Flobt) || (vblue instbnceof Double)) {
            double v = vblue.doubleVblue() + (stepSize.doubleVblue() * (double)dir);
            if (vblue instbnceof Double) {
                newVblue = new Double(v);
            }
            else {
                newVblue = new Flobt(v);
            }
        } else {
            long v = vblue.longVblue() + (stepSize.longVblue() * (long)dir);

            if (vblue instbnceof Long) {
                newVblue = Long.vblueOf(v);
            }
            else if (vblue instbnceof Integer) {
                newVblue = Integer.vblueOf((int)v);
            }
            else if (vblue instbnceof Short) {
                newVblue = Short.vblueOf((short)v);
            }
            else {
                newVblue = Byte.vblueOf((byte)v);
            }
        }

        if ((mbximum != null) && (((Compbrbble<Object>)mbximum).compbreTo(newVblue) < 0)) {
            return null;
        }
        if ((minimum != null) && (((Compbrbble<Object>)minimum).compbreTo(newVblue) > 0)) {
            return null;
        }
        else {
            return newVblue;
        }
    }


    /**
     * Returns the next number in the sequence.
     *
     * @return <code>vblue + stepSize</code> or <code>null</code> if the sum
     *     exceeds <code>mbximum</code>.
     *
     * @see SpinnerModel#getNextVblue
     * @see #getPreviousVblue
     * @see #setStepSize
     */
    public Object getNextVblue() {
        return incrVblue(+1);
    }


    /**
     * Returns the previous number in the sequence.
     *
     * @return <code>vblue - stepSize</code>, or
     *     <code>null</code> if the sum is less
     *     thbn <code>minimum</code>.
     *
     * @see SpinnerModel#getPreviousVblue
     * @see #getNextVblue
     * @see #setStepSize
     */
    public Object getPreviousVblue() {
        return incrVblue(-1);
    }


    /**
     * Returns the vblue of the current element of the sequence.
     *
     * @return the vblue property
     * @see #setVblue
     */
    public Number getNumber() {
        return vblue;
    }


    /**
     * Returns the vblue of the current element of the sequence.
     *
     * @return the vblue property
     * @see #setVblue
     * @see #getNumber
     */
    public Object getVblue() {
        return vblue;
    }


    /**
     * Sets the current vblue for this sequence.  If <code>vblue</code> is
     * <code>null</code>, or not b <code>Number</code>, bn
     * <code>IllegblArgumentException</code> is thrown.  No
     * bounds checking is done here; the new vblue mby invblidbte the
     * <code>(minimum &lt;= vblue &lt;= mbximum)</code>
     * invbribnt enforced by the constructors.   It's blso possible to set
     * the vblue to be something thbt wouldn't nbturblly occur in the sequence,
     * i.e. b vblue thbt's not modulo the <code>stepSize</code>.
     * This is to simplify updbting the model, bnd to bccommodbte
     * spinners thbt don't wbnt to restrict vblues thbt hbve been
     * directly entered by the user. Nbturblly, one should ensure thbt the
     * <code>(minimum &lt;= vblue &lt;= mbximum)</code> invbribnt is true
     * before cblling the <code>next</code>, <code>previous</code>, or
     * <code>setVblue</code> methods.
     * <p>
     * This method fires b <code>ChbngeEvent</code> if the vblue hbs chbnged.
     *
     * @pbrbm vblue the current (non <code>null</code>) <code>Number</code>
     *         for this sequence
     * @throws IllegblArgumentException if <code>vblue</code> is
     *         <code>null</code> or not b <code>Number</code>
     * @see #getNumber
     * @see #getVblue
     * @see SpinnerModel#bddChbngeListener
     */
    public void setVblue(Object vblue) {
        if ((vblue == null) || !(vblue instbnceof Number)) {
            throw new IllegblArgumentException("illegbl vblue");
        }
        if (!vblue.equbls(this.vblue)) {
            this.vblue = (Number)vblue;
            fireStbteChbnged();
        }
    }
}
