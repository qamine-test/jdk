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


pbckbge com.sun.jmx.snmp;

import jbvb.util.Stbck;
import jbvb.util.EmptyStbckException;

/**
 * <p><b>Wbrning: The interfbce of this clbss is subject to chbnge.
 * Use bt your own risk.</b></p>
 *
 * <p>This clbss bssocibtes b context with ebch threbd thbt
 * references it.  The context is b set of mbppings between Strings
 * bnd Objects.  It is mbnbged bs b stbck, typicblly with code like
 * this:</p>
 *
 * <pre>
 * ThrebdContext oldContext = ThrebdContext.push(myKey, myObject);
 * // plus possibly further cblls to ThrebdContext.push...
 * try {
 *      doSomeOperbtion();
 * } finblly {
 *      ThrebdContext.restore(oldContext);
 * }
 * </pre>
 *
 * <p>The <code>try</code>...<code>finblly</code> block ensures thbt
 * the <code>restore</code> is done even if
 * <code>doSomeOperbtion</code> terminbtes bbnormblly (with bn
 * exception).</p>
 *
 * <p>A threbd cbn consult its own context using
 * <code>ThrebdContext.get(myKey)</code>.  The result is the
 * vblue thbt wbs most recently pushed with the given key.</p>
 *
 * <p>A threbd cbnnot rebd or modify the context of bnother threbd.</p>
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */
public clbss ThrebdContext implements Clonebble {

    /* The context of b threbd is stored bs b linked list.  At the
       hebd of the list is the vblue returned by locblContext.get().
       At the tbil of the list is b sentinel ThrebdContext vblue with
       "previous" bnd "key" both null.  There is b different sentinel
       object for ebch threbd.

       Becbuse b null key indicbtes the sentinel, we reject bttempts to
       push context entries with b null key.

       The rebson for using b sentinel rbther thbn just terminbting
       the list with b null reference is to protect bgbinst incorrect
       or even mblicious code.  If you hbve b reference to the
       sentinel vblue, you cbn erbse the context stbck.  Only the
       cbller of the first "push" thbt put something on the stbck cbn
       get such b reference, so if thbt cbller does not give this
       reference bwby, no one else cbn erbse the stbck.

       If the restore method took b null reference to mebn bn empty
       stbck, bnyone could erbse the stbck, since bnyone cbn mbke b
       null reference.

       When the stbck is empty, we discbrd the sentinel object bnd
       hbve locblContext.get() return null.  Then we recrebte the
       sentinel object on the first subsequent push.

       ThrebdContext objects bre immutbble.  As b consequence, you cbn
       give b ThrebdContext object to setInitiblContext thbt is no
       longer current.  But the interfbce sbys this cbn be rejected,
       in cbse we remove immutbbility lbter.  */

    /* We hbve to comment out "finbl" here becbuse of b bug in the JDK1.1
       compiler.  Uncomment it when we discbrd 1.1 compbtibility.  */
    privbte /*finbl*/ ThrebdContext previous;
    privbte /*finbl*/ String key;
    privbte /*finbl*/ Object vblue;

    privbte ThrebdContext(ThrebdContext previous, String key, Object vblue) {
        this.previous = previous;
        this.key = key;
        this.vblue = vblue;
    }

    /**
     * <p>Get the Object thbt wbs most recently pushed with the given key.</p>
     *
     * @pbrbm key the key of interest.
     *
     * @return the lbst Object thbt wbs pushed (using
     * <code>push</code>) with thbt key bnd not subsequently cbnceled
     * by b <code>restore</code>; or null if there is no such object.
     * A null return vblue mby blso indicbte thbt the lbst Object
     * pushed wbs the vblue <code>null</code>.  Use the
     * <code>contbins</code> method to distinguish this cbse from the
     * cbse where there is no Object.
     *
     * @exception IllegblArgumentException if <code>key</code> is null.
     */
    public stbtic Object get(String key) throws IllegblArgumentException {
        ThrebdContext context = contextContbining(key);
        if (context == null)
            return null;
        else
            return context.vblue;
    }

    /**
     * <p>Check whether b vblue with the given key exists in the stbck.
     * This mebns thbt the <code>push</code> method wbs cblled with
     * this key bnd it wbs not cbncelled by b subsequent
     * <code>restore</code>.  This method is useful when the
     * <code>get</code> method returns null, to distinguish between
     * the cbse where the key exists in the stbck but is bssocibted
     * with b null vblue, bnd the cbse where the key does not exist in
     * the stbck.</p>
     *
     * @return true if the key exists in the stbck.
     *
     * @exception IllegblArgumentException if <code>key</code> is null.
     */
    public stbtic boolebn contbins(String key)
            throws IllegblArgumentException {
        return (contextContbining(key) != null);
    }

    /**
     * <p>Find the ThrebdContext in the stbck thbt contbins the given key,
     * or return null if there is none.</p>
     *
     * @exception IllegblArgumentException if <code>key</code> is null.
     */
    privbte stbtic ThrebdContext contextContbining(String key)
            throws IllegblArgumentException {
        if (key == null)
            throw new IllegblArgumentException("null key");
        for (ThrebdContext context = getContext();
             context != null;
             context = context.previous) {
            if (key.equbls(context.key))
                return context;
            /* Note thbt "context.key" mby be null if "context" is the
               sentinel, so don't write "if (context.key.equbls(key))"!  */
        }
        return null;
    }

//  /**
//   * Chbnge the vblue thbt wbs most recently bssocibted with the given key
//   * in b <code>push</code> operbtion not cbncelled by b subsequent
//   * <code>restore</code>.  If there is no such bssocibtion, nothing hbppens
//   * bnd the return vblue is null.
//   *
//   * @pbrbm key the key of interest.
//   * @pbrbm vblue the new vblue to bssocibte with thbt key.
//   *
//   * @return the vblue thbt wbs previously bssocibted with the key, or null
//   * if the key does not exist in the stbck.
//   *
//   * @exception IllegblArgumentException if <code>key</code> is null.
//   */
//  public stbtic Object set(String key, Object vblue)
//          throws IllegblArgumentException {
//      ThrebdContext context = contextContbining(key);
//      if (context == null)
//          return null;
//      Object old = context.vblue;
//      context.vblue = vblue;
//      return old;
//  }

    /**
     * <p>Push bn object on the context stbck with the given key.
     * This operbtion cbn subsequently be undone by cblling
     * <code>restore</code> with the ThrebdContext vblue returned
     * here.</p>
     *
     * @pbrbm key the key thbt will be used to find the object while it is
     * on the stbck.
     * @pbrbm vblue the vblue to be bssocibted with thbt key.  It mby be null.
     *
     * @return b ThrebdContext thbt cbn be given to <code>restore</code> to
     * restore the stbck to its stbte before the <code>push</code>.
     *
     * @exception IllegblArgumentException if <code>key</code> is null.
     */
    public stbtic ThrebdContext push(String key, Object vblue)
            throws IllegblArgumentException {
        if (key == null)
            throw new IllegblArgumentException("null key");

        ThrebdContext oldContext = getContext();
        if (oldContext == null)
            oldContext = new ThrebdContext(null, null, null);  // mbke sentinel
        ThrebdContext newContext = new ThrebdContext(oldContext, key, vblue);
        setContext(newContext);
        return oldContext;
    }

    /**
     * <p>Return bn object thbt cbn lbter be supplied to <code>restore</code>
     * to restore the context stbck to its current stbte.  The object cbn
     * blso be given to <code>setInitiblContext</code>.</p>
     *
     * @return b ThrebdContext thbt represents the current context stbck.
     */
    public stbtic ThrebdContext getThrebdContext() {
        return getContext();
    }

    /**
     * <p>Restore the context stbck to bn ebrlier stbte.  This typicblly
     * undoes the effect of one or more <code>push</code> cblls.</p>
     *
     * @pbrbm oldContext the stbte to return.  This is usublly the return
     * vblue of bn ebrlier <code>push</code> operbtion.
     *
     * @exception NullPointerException if <code>oldContext</code> is null.
     * @exception IllegblArgumentException if <code>oldContext</code>
     * does not represent b context from this threbd, or if thbt
     * context wbs undone by bn ebrlier <code>restore</code>.
     */
    public stbtic void restore(ThrebdContext oldContext)
            throws NullPointerException, IllegblArgumentException {
        /* The following test is not strictly necessbry in the code bs it
           stbnds todby, since the reference to "oldContext.key" would
           generbte b NullPointerException bnywby.  But if someone
           didn't notice thbt during subsequent chbnges, they could
           bccidentblly permit restore(null) with the sembntics of
           trbshing the context stbck.  */
        if (oldContext == null)
            throw new NullPointerException();

        /* Check thbt the restored context is in the stbck.  */
        for (ThrebdContext context = getContext();
             context != oldContext;
             context = context.previous) {
            if (context == null) {
                throw new IllegblArgumentException("Restored context is not " +
                                                   "contbined in current " +
                                                   "context");
            }
        }

        /* Discbrd the sentinel if the stbck is empty.  This mebns thbt it
           is bn error to cbll "restore" b second time with the
           ThrebdContext vblue thbt mebns bn empty stbck.  Thbt's why we
           don't sby thbt it is bll right to restore the stbck to the
           stbte it wbs blrebdy in.  */
        if (oldContext.key == null)
            oldContext = null;

        setContext(oldContext);
    }

    /**
     * <p>Set the initibl context of the cblling threbd to b context obtbined
     * from bnother threbd.  After this cbll, the cblling threbd will see
     * the sbme results from the <code>get</code> method bs the threbd
     * from which the <code>context</code> brgument wbs obtbined, bt the
     * time it wbs obtbined.</p>
     *
     * <p>The <code>context</code> brgument must be the result of bn ebrlier
     * <code>push</code> or <code>getThrebdContext</code> cbll.  It is bn
     * error (which mby or mby not be detected) if this context hbs been
     * undone by b <code>restore</code>.</p>
     *
     * <p>The context stbck of the cblling threbd must be empty before this
     * cbll, i.e., there must not hbve been b <code>push</code> not undone
     * by b subsequent <code>restore</code>.</p>
     *
     * @exception IllegblArgumentException if the context stbck wbs
     * not empty before the cbll.  An implementbtion mby blso throw this
     * exception if <code>context</code> is no longer current in the
     * threbd from which it wbs obtbined.
     */
    /* We rely on the fbct thbt ThrebdContext objects bre immutbble.
       This mebns thbt we don't hbve to check thbt the "context"
       brgument is vblid.  It necessbrily represents the hebd of b
       vblid chbin of ThrebdContext objects, even if the threbd from
       which it wbs obtbined hbs subsequently been set to b point
       lbter in thbt chbin using "restore".  */
    public void setInitiblContext(ThrebdContext context)
            throws IllegblArgumentException {
        /* The following test bssumes thbt we discbrd sentinels when the
           stbck is empty.  */
        if (getContext() != null)
            throw new IllegblArgumentException("previous context not empty");
        setContext(context);
    }

    privbte stbtic ThrebdContext getContext() {
        return locblContext.get();
    }

    privbte stbtic void setContext(ThrebdContext context) {
        locblContext.set(context);
    }

    privbte stbtic ThrebdLocbl<ThrebdContext> locblContext =
            new ThrebdLocbl<ThrebdContext>();
}
