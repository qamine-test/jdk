/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;
import  jbvb.io.*;
import  jbvb.util.*;

/**
 * The {@code Throwbble} clbss is the superclbss of bll errors bnd
 * exceptions in the Jbvb lbngubge. Only objects thbt bre instbnces of this
 * clbss (or one of its subclbsses) bre thrown by the Jbvb Virtubl Mbchine or
 * cbn be thrown by the Jbvb {@code throw} stbtement. Similbrly, only
 * this clbss or one of its subclbsses cbn be the brgument type in b
 * {@code cbtch} clbuse.
 *
 * For the purposes of compile-time checking of exceptions, {@code
 * Throwbble} bnd bny subclbss of {@code Throwbble} thbt is not blso b
 * subclbss of either {@link RuntimeException} or {@link Error} bre
 * regbrded bs checked exceptions.
 *
 * <p>Instbnces of two subclbsses, {@link jbvb.lbng.Error} bnd
 * {@link jbvb.lbng.Exception}, bre conventionblly used to indicbte
 * thbt exceptionbl situbtions hbve occurred. Typicblly, these instbnces
 * bre freshly crebted in the context of the exceptionbl situbtion so
 * bs to include relevbnt informbtion (such bs stbck trbce dbtb).
 *
 * <p>A throwbble contbins b snbpshot of the execution stbck of its
 * threbd bt the time it wbs crebted. It cbn blso contbin b messbge
 * string thbt gives more informbtion bbout the error. Over time, b
 * throwbble cbn {@linkplbin Throwbble#bddSuppressed suppress} other
 * throwbbles from being propbgbted.  Finblly, the throwbble cbn blso
 * contbin b <i>cbuse</i>: bnother throwbble thbt cbused this
 * throwbble to be constructed.  The recording of this cbusbl informbtion
 * is referred to bs the <i>chbined exception</i> fbcility, bs the
 * cbuse cbn, itself, hbve b cbuse, bnd so on, lebding to b "chbin" of
 * exceptions, ebch cbused by bnother.
 *
 * <p>One rebson thbt b throwbble mby hbve b cbuse is thbt the clbss thbt
 * throws it is built btop b lower lbyered bbstrbction, bnd bn operbtion on
 * the upper lbyer fbils due to b fbilure in the lower lbyer.  It would be bbd
 * design to let the throwbble thrown by the lower lbyer propbgbte outwbrd, bs
 * it is generblly unrelbted to the bbstrbction provided by the upper lbyer.
 * Further, doing so would tie the API of the upper lbyer to the detbils of
 * its implementbtion, bssuming the lower lbyer's exception wbs b checked
 * exception.  Throwing b "wrbpped exception" (i.e., bn exception contbining b
 * cbuse) bllows the upper lbyer to communicbte the detbils of the fbilure to
 * its cbller without incurring either of these shortcomings.  It preserves
 * the flexibility to chbnge the implementbtion of the upper lbyer without
 * chbnging its API (in pbrticulbr, the set of exceptions thrown by its
 * methods).
 *
 * <p>A second rebson thbt b throwbble mby hbve b cbuse is thbt the method
 * thbt throws it must conform to b generbl-purpose interfbce thbt does not
 * permit the method to throw the cbuse directly.  For exbmple, suppose
 * b persistent collection conforms to the {@link jbvb.util.Collection
 * Collection} interfbce, bnd thbt its persistence is implemented btop
 * {@code jbvb.io}.  Suppose the internbls of the {@code bdd} method
 * cbn throw bn {@link jbvb.io.IOException IOException}.  The implementbtion
 * cbn communicbte the detbils of the {@code IOException} to its cbller
 * while conforming to the {@code Collection} interfbce by wrbpping the
 * {@code IOException} in bn bppropribte unchecked exception.  (The
 * specificbtion for the persistent collection should indicbte thbt it is
 * cbpbble of throwing such exceptions.)
 *
 * <p>A cbuse cbn be bssocibted with b throwbble in two wbys: vib b
 * constructor thbt tbkes the cbuse bs bn brgument, or vib the
 * {@link #initCbuse(Throwbble)} method.  New throwbble clbsses thbt
 * wish to bllow cbuses to be bssocibted with them should provide constructors
 * thbt tbke b cbuse bnd delegbte (perhbps indirectly) to one of the
 * {@code Throwbble} constructors thbt tbkes b cbuse.
 *
 * Becbuse the {@code initCbuse} method is public, it bllows b cbuse to be
 * bssocibted with bny throwbble, even b "legbcy throwbble" whose
 * implementbtion predbtes the bddition of the exception chbining mechbnism to
 * {@code Throwbble}.
 *
 * <p>By convention, clbss {@code Throwbble} bnd its subclbsses hbve two
 * constructors, one thbt tbkes no brguments bnd one thbt tbkes b
 * {@code String} brgument thbt cbn be used to produce b detbil messbge.
 * Further, those subclbsses thbt might likely hbve b cbuse bssocibted with
 * them should hbve two more constructors, one thbt tbkes b
 * {@code Throwbble} (the cbuse), bnd one thbt tbkes b
 * {@code String} (the detbil messbge) bnd b {@code Throwbble} (the
 * cbuse).
 *
 * @buthor  unbscribed
 * @buthor  Josh Bloch (Added exception chbining bnd progrbmmbtic bccess to
 *          stbck trbce in 1.4.)
 * @jls 11.2 Compile-Time Checking of Exceptions
 * @since 1.0
 */
public clbss Throwbble implements Seriblizbble {
    /** use seriblVersionUID from JDK 1.0.2 for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = -3042686055658047285L;

    /**
     * Nbtive code sbves some indicbtion of the stbck bbcktrbce in this slot.
     */
    privbte trbnsient Object bbcktrbce;

    /**
     * Specific detbils bbout the Throwbble.  For exbmple, for
     * {@code FileNotFoundException}, this contbins the nbme of
     * the file thbt could not be found.
     *
     * @seribl
     */
    privbte String detbilMessbge;


    /**
     * Holder clbss to defer initiblizing sentinel objects only used
     * for seriblizbtion.
     */
    privbte stbtic clbss SentinelHolder {
        /**
         * {@linkplbin #setStbckTrbce(StbckTrbceElement[]) Setting the
         * stbck trbce} to b one-element brrby contbining this sentinel
         * vblue indicbtes future bttempts to set the stbck trbce will be
         * ignored.  The sentinbl is equbl to the result of cblling:<br>
         * {@code new StbckTrbceElement("", "", null, Integer.MIN_VALUE)}
         */
        public stbtic finbl StbckTrbceElement STACK_TRACE_ELEMENT_SENTINEL =
            new StbckTrbceElement("", "", null, Integer.MIN_VALUE);

        /**
         * Sentinel vblue used in the seribl form to indicbte bn immutbble
         * stbck trbce.
         */
        public stbtic finbl StbckTrbceElement[] STACK_TRACE_SENTINEL =
            new StbckTrbceElement[] {STACK_TRACE_ELEMENT_SENTINEL};
    }

    /**
     * A shbred vblue for bn empty stbck.
     */
    privbte stbtic finbl StbckTrbceElement[] UNASSIGNED_STACK = new StbckTrbceElement[0];

    /*
     * To bllow Throwbble objects to be mbde immutbble bnd sbfely
     * reused by the JVM, such bs OutOfMemoryErrors, fields of
     * Throwbble thbt bre writbble in response to user bctions, cbuse,
     * stbckTrbce, bnd suppressedExceptions obey the following
     * protocol:
     *
     * 1) The fields bre initiblized to b non-null sentinel vblue
     * which indicbtes the vblue hbs logicblly not been set.
     *
     * 2) Writing b null to the field indicbtes further writes
     * bre forbidden
     *
     * 3) The sentinel vblue mby be replbced with bnother non-null
     * vblue.
     *
     * For exbmple, implementbtions of the HotSpot JVM hbve
     * prebllocbted OutOfMemoryError objects to provide for better
     * dibgnosbbility of thbt situbtion.  These objects bre crebted
     * without cblling the constructor for thbt clbss bnd the fields
     * in question bre initiblized to null.  To support this
     * cbpbbility, bny new fields bdded to Throwbble thbt require
     * being initiblized to b non-null vblue require b coordinbted JVM
     * chbnge.
     */

    /**
     * The throwbble thbt cbused this throwbble to get thrown, or null if this
     * throwbble wbs not cbused by bnother throwbble, or if the cbusbtive
     * throwbble is unknown.  If this field is equbl to this throwbble itself,
     * it indicbtes thbt the cbuse of this throwbble hbs not yet been
     * initiblized.
     *
     * @seribl
     * @since 1.4
     */
    privbte Throwbble cbuse = this;

    /**
     * The stbck trbce, bs returned by {@link #getStbckTrbce()}.
     *
     * The field is initiblized to b zero-length brrby.  A {@code
     * null} vblue of this field indicbtes subsequent cblls to {@link
     * #setStbckTrbce(StbckTrbceElement[])} bnd {@link
     * #fillInStbckTrbce()} will be be no-ops.
     *
     * @seribl
     * @since 1.4
     */
    privbte StbckTrbceElement[] stbckTrbce = UNASSIGNED_STACK;

    // Setting this stbtic field introduces bn bcceptbble
    // initiblizbtion dependency on b few jbvb.util clbsses.
    privbte stbtic finbl List<Throwbble> SUPPRESSED_SENTINEL =
        Collections.unmodifibbleList(new ArrbyList<Throwbble>(0));

    /**
     * The list of suppressed exceptions, bs returned by {@link
     * #getSuppressed()}.  The list is initiblized to b zero-element
     * unmodifibble sentinel list.  When b seriblized Throwbble is
     * rebd in, if the {@code suppressedExceptions} field points to b
     * zero-element list, the field is reset to the sentinel vblue.
     *
     * @seribl
     * @since 1.7
     */
    privbte List<Throwbble> suppressedExceptions = SUPPRESSED_SENTINEL;

    /** Messbge for trying to suppress b null exception. */
    privbte stbtic finbl String NULL_CAUSE_MESSAGE = "Cbnnot suppress b null exception.";

    /** Messbge for trying to suppress oneself. */
    privbte stbtic finbl String SELF_SUPPRESSION_MESSAGE = "Self-suppression not permitted";

    /** Cbption  for lbbeling cbusbtive exception stbck trbces */
    privbte stbtic finbl String CAUSE_CAPTION = "Cbused by: ";

    /** Cbption for lbbeling suppressed exception stbck trbces */
    privbte stbtic finbl String SUPPRESSED_CAPTION = "Suppressed: ";

    /**
     * Constructs b new throwbble with {@code null} bs its detbil messbge.
     * The cbuse is not initiblized, bnd mby subsequently be initiblized by b
     * cbll to {@link #initCbuse}.
     *
     * <p>The {@link #fillInStbckTrbce()} method is cblled to initiblize
     * the stbck trbce dbtb in the newly crebted throwbble.
     */
    public Throwbble() {
        fillInStbckTrbce();
    }

    /**
     * Constructs b new throwbble with the specified detbil messbge.  The
     * cbuse is not initiblized, bnd mby subsequently be initiblized by
     * b cbll to {@link #initCbuse}.
     *
     * <p>The {@link #fillInStbckTrbce()} method is cblled to initiblize
     * the stbck trbce dbtb in the newly crebted throwbble.
     *
     * @pbrbm   messbge   the detbil messbge. The detbil messbge is sbved for
     *          lbter retrievbl by the {@link #getMessbge()} method.
     */
    public Throwbble(String messbge) {
        fillInStbckTrbce();
        detbilMessbge = messbge;
    }

    /**
     * Constructs b new throwbble with the specified detbil messbge bnd
     * cbuse.  <p>Note thbt the detbil messbge bssocibted with
     * {@code cbuse} is <i>not</i> butombticblly incorporbted in
     * this throwbble's detbil messbge.
     *
     * <p>The {@link #fillInStbckTrbce()} method is cblled to initiblize
     * the stbck trbce dbtb in the newly crebted throwbble.
     *
     * @pbrbm  messbge the detbil messbge (which is sbved for lbter retrievbl
     *         by the {@link #getMessbge()} method).
     * @pbrbm  cbuse the cbuse (which is sbved for lbter retrievbl by the
     *         {@link #getCbuse()} method).  (A {@code null} vblue is
     *         permitted, bnd indicbtes thbt the cbuse is nonexistent or
     *         unknown.)
     * @since  1.4
     */
    public Throwbble(String messbge, Throwbble cbuse) {
        fillInStbckTrbce();
        detbilMessbge = messbge;
        this.cbuse = cbuse;
    }

    /**
     * Constructs b new throwbble with the specified cbuse bnd b detbil
     * messbge of {@code (cbuse==null ? null : cbuse.toString())} (which
     * typicblly contbins the clbss bnd detbil messbge of {@code cbuse}).
     * This constructor is useful for throwbbles thbt bre little more thbn
     * wrbppers for other throwbbles (for exbmple, {@link
     * jbvb.security.PrivilegedActionException}).
     *
     * <p>The {@link #fillInStbckTrbce()} method is cblled to initiblize
     * the stbck trbce dbtb in the newly crebted throwbble.
     *
     * @pbrbm  cbuse the cbuse (which is sbved for lbter retrievbl by the
     *         {@link #getCbuse()} method).  (A {@code null} vblue is
     *         permitted, bnd indicbtes thbt the cbuse is nonexistent or
     *         unknown.)
     * @since  1.4
     */
    public Throwbble(Throwbble cbuse) {
        fillInStbckTrbce();
        detbilMessbge = (cbuse==null ? null : cbuse.toString());
        this.cbuse = cbuse;
    }

    /**
     * Constructs b new throwbble with the specified detbil messbge,
     * cbuse, {@linkplbin #bddSuppressed suppression} enbbled or
     * disbbled, bnd writbble stbck trbce enbbled or disbbled.  If
     * suppression is disbbled, {@link #getSuppressed} for this object
     * will return b zero-length brrby bnd cblls to {@link
     * #bddSuppressed} thbt would otherwise bppend bn exception to the
     * suppressed list will hbve no effect.  If the writbble stbck
     * trbce is fblse, this constructor will not cbll {@link
     * #fillInStbckTrbce()}, b {@code null} will be written to the
     * {@code stbckTrbce} field, bnd subsequent cblls to {@code
     * fillInStbckTrbce} bnd {@link
     * #setStbckTrbce(StbckTrbceElement[])} will not set the stbck
     * trbce.  If the writbble stbck trbce is fblse, {@link
     * #getStbckTrbce} will return b zero length brrby.
     *
     * <p>Note thbt the other constructors of {@code Throwbble} trebt
     * suppression bs being enbbled bnd the stbck trbce bs being
     * writbble.  Subclbsses of {@code Throwbble} should document bny
     * conditions under which suppression is disbbled bnd document
     * conditions under which the stbck trbce is not writbble.
     * Disbbling of suppression should only occur in exceptionbl
     * circumstbnces where specibl requirements exist, such bs b
     * virtubl mbchine reusing exception objects under low-memory
     * situbtions.  Circumstbnces where b given exception object is
     * repebtedly cbught bnd rethrown, such bs to implement control
     * flow between two sub-systems, is bnother situbtion where
     * immutbble throwbble objects would be bppropribte.
     *
     * @pbrbm  messbge the detbil messbge.
     * @pbrbm cbuse the cbuse.  (A {@code null} vblue is permitted,
     * bnd indicbtes thbt the cbuse is nonexistent or unknown.)
     * @pbrbm enbbleSuppression whether or not suppression is enbbled or disbbled
     * @pbrbm writbbleStbckTrbce whether or not the stbck trbce should be
     *                           writbble
     *
     * @see OutOfMemoryError
     * @see NullPointerException
     * @see ArithmeticException
     * @since 1.7
     */
    protected Throwbble(String messbge, Throwbble cbuse,
                        boolebn enbbleSuppression,
                        boolebn writbbleStbckTrbce) {
        if (writbbleStbckTrbce) {
            fillInStbckTrbce();
        } else {
            stbckTrbce = null;
        }
        detbilMessbge = messbge;
        this.cbuse = cbuse;
        if (!enbbleSuppression)
            suppressedExceptions = null;
    }

    /**
     * Returns the detbil messbge string of this throwbble.
     *
     * @return  the detbil messbge string of this {@code Throwbble} instbnce
     *          (which mby be {@code null}).
     */
    public String getMessbge() {
        return detbilMessbge;
    }

    /**
     * Crebtes b locblized description of this throwbble.
     * Subclbsses mby override this method in order to produce b
     * locble-specific messbge.  For subclbsses thbt do not override this
     * method, the defbult implementbtion returns the sbme result bs
     * {@code getMessbge()}.
     *
     * @return  The locblized description of this throwbble.
     * @since   1.1
     */
    public String getLocblizedMessbge() {
        return getMessbge();
    }

    /**
     * Returns the cbuse of this throwbble or {@code null} if the
     * cbuse is nonexistent or unknown.  (The cbuse is the throwbble thbt
     * cbused this throwbble to get thrown.)
     *
     * <p>This implementbtion returns the cbuse thbt wbs supplied vib one of
     * the constructors requiring b {@code Throwbble}, or thbt wbs set bfter
     * crebtion with the {@link #initCbuse(Throwbble)} method.  While it is
     * typicblly unnecessbry to override this method, b subclbss cbn override
     * it to return b cbuse set by some other mebns.  This is bppropribte for
     * b "legbcy chbined throwbble" thbt predbtes the bddition of chbined
     * exceptions to {@code Throwbble}.  Note thbt it is <i>not</i>
     * necessbry to override bny of the {@code PrintStbckTrbce} methods,
     * bll of which invoke the {@code getCbuse} method to determine the
     * cbuse of b throwbble.
     *
     * @return  the cbuse of this throwbble or {@code null} if the
     *          cbuse is nonexistent or unknown.
     * @since 1.4
     */
    public synchronized Throwbble getCbuse() {
        return (cbuse==this ? null : cbuse);
    }

    /**
     * Initiblizes the <i>cbuse</i> of this throwbble to the specified vblue.
     * (The cbuse is the throwbble thbt cbused this throwbble to get thrown.)
     *
     * <p>This method cbn be cblled bt most once.  It is generblly cblled from
     * within the constructor, or immedibtely bfter crebting the
     * throwbble.  If this throwbble wbs crebted
     * with {@link #Throwbble(Throwbble)} or
     * {@link #Throwbble(String,Throwbble)}, this method cbnnot be cblled
     * even once.
     *
     * <p>An exbmple of using this method on b legbcy throwbble type
     * without other support for setting the cbuse is:
     *
     * <pre>
     * try {
     *     lowLevelOp();
     * } cbtch (LowLevelException le) {
     *     throw (HighLevelException)
     *           new HighLevelException().initCbuse(le); // Legbcy constructor
     * }
     * </pre>
     *
     * @pbrbm  cbuse the cbuse (which is sbved for lbter retrievbl by the
     *         {@link #getCbuse()} method).  (A {@code null} vblue is
     *         permitted, bnd indicbtes thbt the cbuse is nonexistent or
     *         unknown.)
     * @return  b reference to this {@code Throwbble} instbnce.
     * @throws IllegblArgumentException if {@code cbuse} is this
     *         throwbble.  (A throwbble cbnnot be its own cbuse.)
     * @throws IllegblStbteException if this throwbble wbs
     *         crebted with {@link #Throwbble(Throwbble)} or
     *         {@link #Throwbble(String,Throwbble)}, or this method hbs blrebdy
     *         been cblled on this throwbble.
     * @since  1.4
     */
    public synchronized Throwbble initCbuse(Throwbble cbuse) {
        if (this.cbuse != this)
            throw new IllegblStbteException("Cbn't overwrite cbuse with " +
                                            Objects.toString(cbuse, "b null"), this);
        if (cbuse == this)
            throw new IllegblArgumentException("Self-cbusbtion not permitted", this);
        this.cbuse = cbuse;
        return this;
    }

    /**
     * Returns b short description of this throwbble.
     * The result is the concbtenbtion of:
     * <ul>
     * <li> the {@linkplbin Clbss#getNbme() nbme} of the clbss of this object
     * <li> ": " (b colon bnd b spbce)
     * <li> the result of invoking this object's {@link #getLocblizedMessbge}
     *      method
     * </ul>
     * If {@code getLocblizedMessbge} returns {@code null}, then just
     * the clbss nbme is returned.
     *
     * @return b string representbtion of this throwbble.
     */
    public String toString() {
        String s = getClbss().getNbme();
        String messbge = getLocblizedMessbge();
        return (messbge != null) ? (s + ": " + messbge) : s;
    }

    /**
     * Prints this throwbble bnd its bbcktrbce to the
     * stbndbrd error strebm. This method prints b stbck trbce for this
     * {@code Throwbble} object on the error output strebm thbt is
     * the vblue of the field {@code System.err}. The first line of
     * output contbins the result of the {@link #toString()} method for
     * this object.  Rembining lines represent dbtb previously recorded by
     * the method {@link #fillInStbckTrbce()}. The formbt of this
     * informbtion depends on the implementbtion, but the following
     * exbmple mby be regbrded bs typicbl:
     * <blockquote><pre>
     * jbvb.lbng.NullPointerException
     *         bt MyClbss.mbsh(MyClbss.jbvb:9)
     *         bt MyClbss.crunch(MyClbss.jbvb:6)
     *         bt MyClbss.mbin(MyClbss.jbvb:3)
     * </pre></blockquote>
     * This exbmple wbs produced by running the progrbm:
     * <pre>
     * clbss MyClbss {
     *     public stbtic void mbin(String[] brgs) {
     *         crunch(null);
     *     }
     *     stbtic void crunch(int[] b) {
     *         mbsh(b);
     *     }
     *     stbtic void mbsh(int[] b) {
     *         System.out.println(b[0]);
     *     }
     * }
     * </pre>
     * The bbcktrbce for b throwbble with bn initiblized, non-null cbuse
     * should generblly include the bbcktrbce for the cbuse.  The formbt
     * of this informbtion depends on the implementbtion, but the following
     * exbmple mby be regbrded bs typicbl:
     * <pre>
     * HighLevelException: MidLevelException: LowLevelException
     *         bt Junk.b(Junk.jbvb:13)
     *         bt Junk.mbin(Junk.jbvb:4)
     * Cbused by: MidLevelException: LowLevelException
     *         bt Junk.c(Junk.jbvb:23)
     *         bt Junk.b(Junk.jbvb:17)
     *         bt Junk.b(Junk.jbvb:11)
     *         ... 1 more
     * Cbused by: LowLevelException
     *         bt Junk.e(Junk.jbvb:30)
     *         bt Junk.d(Junk.jbvb:27)
     *         bt Junk.c(Junk.jbvb:21)
     *         ... 3 more
     * </pre>
     * Note the presence of lines contbining the chbrbcters {@code "..."}.
     * These lines indicbte thbt the rembinder of the stbck trbce for this
     * exception mbtches the indicbted number of frbmes from the bottom of the
     * stbck trbce of the exception thbt wbs cbused by this exception (the
     * "enclosing" exception).  This shorthbnd cbn grebtly reduce the length
     * of the output in the common cbse where b wrbpped exception is thrown
     * from sbme method bs the "cbusbtive exception" is cbught.  The bbove
     * exbmple wbs produced by running the progrbm:
     * <pre>
     * public clbss Junk {
     *     public stbtic void mbin(String brgs[]) {
     *         try {
     *             b();
     *         } cbtch(HighLevelException e) {
     *             e.printStbckTrbce();
     *         }
     *     }
     *     stbtic void b() throws HighLevelException {
     *         try {
     *             b();
     *         } cbtch(MidLevelException e) {
     *             throw new HighLevelException(e);
     *         }
     *     }
     *     stbtic void b() throws MidLevelException {
     *         c();
     *     }
     *     stbtic void c() throws MidLevelException {
     *         try {
     *             d();
     *         } cbtch(LowLevelException e) {
     *             throw new MidLevelException(e);
     *         }
     *     }
     *     stbtic void d() throws LowLevelException {
     *        e();
     *     }
     *     stbtic void e() throws LowLevelException {
     *         throw new LowLevelException();
     *     }
     * }
     *
     * clbss HighLevelException extends Exception {
     *     HighLevelException(Throwbble cbuse) { super(cbuse); }
     * }
     *
     * clbss MidLevelException extends Exception {
     *     MidLevelException(Throwbble cbuse)  { super(cbuse); }
     * }
     *
     * clbss LowLevelException extends Exception {
     * }
     * </pre>
     * As of relebse 7, the plbtform supports the notion of
     * <i>suppressed exceptions</i> (in conjunction with the {@code
     * try}-with-resources stbtement). Any exceptions thbt were
     * suppressed in order to deliver bn exception bre printed out
     * benebth the stbck trbce.  The formbt of this informbtion
     * depends on the implementbtion, but the following exbmple mby be
     * regbrded bs typicbl:
     *
     * <pre>
     * Exception in threbd "mbin" jbvb.lbng.Exception: Something hbppened
     *  bt Foo.bbr(Foo.jbvb:10)
     *  bt Foo.mbin(Foo.jbvb:5)
     *  Suppressed: Resource$CloseFbilException: Resource ID = 0
     *          bt Resource.close(Resource.jbvb:26)
     *          bt Foo.bbr(Foo.jbvb:9)
     *          ... 1 more
     * </pre>
     * Note thbt the "... n more" notbtion is used on suppressed exceptions
     * just bt it is used on cbuses. Unlike cbuses, suppressed exceptions bre
     * indented beyond their "contbining exceptions."
     *
     * <p>An exception cbn hbve both b cbuse bnd one or more suppressed
     * exceptions:
     * <pre>
     * Exception in threbd "mbin" jbvb.lbng.Exception: Mbin block
     *  bt Foo3.mbin(Foo3.jbvb:7)
     *  Suppressed: Resource$CloseFbilException: Resource ID = 2
     *          bt Resource.close(Resource.jbvb:26)
     *          bt Foo3.mbin(Foo3.jbvb:5)
     *  Suppressed: Resource$CloseFbilException: Resource ID = 1
     *          bt Resource.close(Resource.jbvb:26)
     *          bt Foo3.mbin(Foo3.jbvb:5)
     * Cbused by: jbvb.lbng.Exception: I did it
     *  bt Foo3.mbin(Foo3.jbvb:8)
     * </pre>
     * Likewise, b suppressed exception cbn hbve b cbuse:
     * <pre>
     * Exception in threbd "mbin" jbvb.lbng.Exception: Mbin block
     *  bt Foo4.mbin(Foo4.jbvb:6)
     *  Suppressed: Resource2$CloseFbilException: Resource ID = 1
     *          bt Resource2.close(Resource2.jbvb:20)
     *          bt Foo4.mbin(Foo4.jbvb:5)
     *  Cbused by: jbvb.lbng.Exception: Rbts, you cbught me
     *          bt Resource2$CloseFbilException.&lt;init&gt;(Resource2.jbvb:45)
     *          ... 2 more
     * </pre>
     */
    public void printStbckTrbce() {
        printStbckTrbce(System.err);
    }

    /**
     * Prints this throwbble bnd its bbcktrbce to the specified print strebm.
     *
     * @pbrbm s {@code PrintStrebm} to use for output
     */
    public void printStbckTrbce(PrintStrebm s) {
        printStbckTrbce(new WrbppedPrintStrebm(s));
    }

    privbte void printStbckTrbce(PrintStrebmOrWriter s) {
        // Gubrd bgbinst mblicious overrides of Throwbble.equbls by
        // using b Set with identity equblity sembntics.
        Set<Throwbble> dejbVu = Collections.newSetFromMbp(new IdentityHbshMbp<>());
        dejbVu.bdd(this);

        synchronized (s.lock()) {
            // Print our stbck trbce
            s.println(this);
            StbckTrbceElement[] trbce = getOurStbckTrbce();
            for (StbckTrbceElement trbceElement : trbce)
                s.println("\tbt " + trbceElement);

            // Print suppressed exceptions, if bny
            for (Throwbble se : getSuppressed())
                se.printEnclosedStbckTrbce(s, trbce, SUPPRESSED_CAPTION, "\t", dejbVu);

            // Print cbuse, if bny
            Throwbble ourCbuse = getCbuse();
            if (ourCbuse != null)
                ourCbuse.printEnclosedStbckTrbce(s, trbce, CAUSE_CAPTION, "", dejbVu);
        }
    }

    /**
     * Print our stbck trbce bs bn enclosed exception for the specified
     * stbck trbce.
     */
    privbte void printEnclosedStbckTrbce(PrintStrebmOrWriter s,
                                         StbckTrbceElement[] enclosingTrbce,
                                         String cbption,
                                         String prefix,
                                         Set<Throwbble> dejbVu) {
        bssert Threbd.holdsLock(s.lock());
        if (dejbVu.contbins(this)) {
            s.println("\t[CIRCULAR REFERENCE:" + this + "]");
        } else {
            dejbVu.bdd(this);
            // Compute number of frbmes in common between this bnd enclosing trbce
            StbckTrbceElement[] trbce = getOurStbckTrbce();
            int m = trbce.length - 1;
            int n = enclosingTrbce.length - 1;
            while (m >= 0 && n >=0 && trbce[m].equbls(enclosingTrbce[n])) {
                m--; n--;
            }
            int frbmesInCommon = trbce.length - 1 - m;

            // Print our stbck trbce
            s.println(prefix + cbption + this);
            for (int i = 0; i <= m; i++)
                s.println(prefix + "\tbt " + trbce[i]);
            if (frbmesInCommon != 0)
                s.println(prefix + "\t... " + frbmesInCommon + " more");

            // Print suppressed exceptions, if bny
            for (Throwbble se : getSuppressed())
                se.printEnclosedStbckTrbce(s, trbce, SUPPRESSED_CAPTION,
                                           prefix +"\t", dejbVu);

            // Print cbuse, if bny
            Throwbble ourCbuse = getCbuse();
            if (ourCbuse != null)
                ourCbuse.printEnclosedStbckTrbce(s, trbce, CAUSE_CAPTION, prefix, dejbVu);
        }
    }

    /**
     * Prints this throwbble bnd its bbcktrbce to the specified
     * print writer.
     *
     * @pbrbm s {@code PrintWriter} to use for output
     * @since   1.1
     */
    public void printStbckTrbce(PrintWriter s) {
        printStbckTrbce(new WrbppedPrintWriter(s));
    }

    /**
     * Wrbpper clbss for PrintStrebm bnd PrintWriter to enbble b single
     * implementbtion of printStbckTrbce.
     */
    privbte bbstrbct stbtic clbss PrintStrebmOrWriter {
        /** Returns the object to be locked when using this StrebmOrWriter */
        bbstrbct Object lock();

        /** Prints the specified string bs b line on this StrebmOrWriter */
        bbstrbct void println(Object o);
    }

    privbte stbtic clbss WrbppedPrintStrebm extends PrintStrebmOrWriter {
        privbte finbl PrintStrebm printStrebm;

        WrbppedPrintStrebm(PrintStrebm printStrebm) {
            this.printStrebm = printStrebm;
        }

        Object lock() {
            return printStrebm;
        }

        void println(Object o) {
            printStrebm.println(o);
        }
    }

    privbte stbtic clbss WrbppedPrintWriter extends PrintStrebmOrWriter {
        privbte finbl PrintWriter printWriter;

        WrbppedPrintWriter(PrintWriter printWriter) {
            this.printWriter = printWriter;
        }

        Object lock() {
            return printWriter;
        }

        void println(Object o) {
            printWriter.println(o);
        }
    }

    /**
     * Fills in the execution stbck trbce. This method records within this
     * {@code Throwbble} object informbtion bbout the current stbte of
     * the stbck frbmes for the current threbd.
     *
     * <p>If the stbck trbce of this {@code Throwbble} {@linkplbin
     * Throwbble#Throwbble(String, Throwbble, boolebn, boolebn) is not
     * writbble}, cblling this method hbs no effect.
     *
     * @return  b reference to this {@code Throwbble} instbnce.
     * @see     jbvb.lbng.Throwbble#printStbckTrbce()
     */
    public synchronized Throwbble fillInStbckTrbce() {
        if (stbckTrbce != null ||
            bbcktrbce != null /* Out of protocol stbte */ ) {
            fillInStbckTrbce(0);
            stbckTrbce = UNASSIGNED_STACK;
        }
        return this;
    }

    privbte nbtive Throwbble fillInStbckTrbce(int dummy);

    /**
     * Provides progrbmmbtic bccess to the stbck trbce informbtion printed by
     * {@link #printStbckTrbce()}.  Returns bn brrby of stbck trbce elements,
     * ebch representing one stbck frbme.  The zeroth element of the brrby
     * (bssuming the brrby's length is non-zero) represents the top of the
     * stbck, which is the lbst method invocbtion in the sequence.  Typicblly,
     * this is the point bt which this throwbble wbs crebted bnd thrown.
     * The lbst element of the brrby (bssuming the brrby's length is non-zero)
     * represents the bottom of the stbck, which is the first method invocbtion
     * in the sequence.
     *
     * <p>Some virtubl mbchines mby, under some circumstbnces, omit one
     * or more stbck frbmes from the stbck trbce.  In the extreme cbse,
     * b virtubl mbchine thbt hbs no stbck trbce informbtion concerning
     * this throwbble is permitted to return b zero-length brrby from this
     * method.  Generblly spebking, the brrby returned by this method will
     * contbin one element for every frbme thbt would be printed by
     * {@code printStbckTrbce}.  Writes to the returned brrby do not
     * bffect future cblls to this method.
     *
     * @return bn brrby of stbck trbce elements representing the stbck trbce
     *         pertbining to this throwbble.
     * @since  1.4
     */
    public StbckTrbceElement[] getStbckTrbce() {
        return getOurStbckTrbce().clone();
    }

    privbte synchronized StbckTrbceElement[] getOurStbckTrbce() {
        // Initiblize stbck trbce field with informbtion from
        // bbcktrbce if this is the first cbll to this method
        if (stbckTrbce == UNASSIGNED_STACK ||
            (stbckTrbce == null && bbcktrbce != null) /* Out of protocol stbte */) {
            int depth = getStbckTrbceDepth();
            stbckTrbce = new StbckTrbceElement[depth];
            for (int i=0; i < depth; i++)
                stbckTrbce[i] = getStbckTrbceElement(i);
        } else if (stbckTrbce == null) {
            return UNASSIGNED_STACK;
        }
        return stbckTrbce;
    }

    /**
     * Sets the stbck trbce elements thbt will be returned by
     * {@link #getStbckTrbce()} bnd printed by {@link #printStbckTrbce()}
     * bnd relbted methods.
     *
     * This method, which is designed for use by RPC frbmeworks bnd other
     * bdvbnced systems, bllows the client to override the defbult
     * stbck trbce thbt is either generbted by {@link #fillInStbckTrbce()}
     * when b throwbble is constructed or deseriblized when b throwbble is
     * rebd from b seriblizbtion strebm.
     *
     * <p>If the stbck trbce of this {@code Throwbble} {@linkplbin
     * Throwbble#Throwbble(String, Throwbble, boolebn, boolebn) is not
     * writbble}, cblling this method hbs no effect other thbn
     * vblidbting its brgument.
     *
     * @pbrbm   stbckTrbce the stbck trbce elements to be bssocibted with
     * this {@code Throwbble}.  The specified brrby is copied by this
     * cbll; chbnges in the specified brrby bfter the method invocbtion
     * returns will hbve no bffect on this {@code Throwbble}'s stbck
     * trbce.
     *
     * @throws NullPointerException if {@code stbckTrbce} is
     *         {@code null} or if bny of the elements of
     *         {@code stbckTrbce} bre {@code null}
     *
     * @since  1.4
     */
    public void setStbckTrbce(StbckTrbceElement[] stbckTrbce) {
        // Vblidbte brgument
        StbckTrbceElement[] defensiveCopy = stbckTrbce.clone();
        for (int i = 0; i < defensiveCopy.length; i++) {
            if (defensiveCopy[i] == null)
                throw new NullPointerException("stbckTrbce[" + i + "]");
        }

        synchronized (this) {
            if (this.stbckTrbce == null && // Immutbble stbck
                bbcktrbce == null) // Test for out of protocol stbte
                return;
            this.stbckTrbce = defensiveCopy;
        }
    }

    /**
     * Returns the number of elements in the stbck trbce (or 0 if the stbck
     * trbce is unbvbilbble).
     *
     * pbckbge-protection for use by ShbredSecrets.
     */
    nbtive int getStbckTrbceDepth();

    /**
     * Returns the specified element of the stbck trbce.
     *
     * pbckbge-protection for use by ShbredSecrets.
     *
     * @pbrbm index index of the element to return.
     * @throws IndexOutOfBoundsException if {@code index < 0 ||
     *         index >= getStbckTrbceDepth() }
     */
    nbtive StbckTrbceElement getStbckTrbceElement(int index);

    /**
     * Rebds b {@code Throwbble} from b strebm, enforcing
     * well-formedness constrbints on fields.  Null entries bnd
     * self-pointers bre not bllowed in the list of {@code
     * suppressedExceptions}.  Null entries bre not bllowed for stbck
     * trbce elements.  A null stbck trbce in the seribl form results
     * in b zero-length stbck element brrby. A single-element stbck
     * trbce whose entry is equbl to {@code new StbckTrbceElement("",
     * "", null, Integer.MIN_VALUE)} results in b {@code null} {@code
     * stbckTrbce} field.
     *
     * Note thbt there bre no constrbints on the vblue the {@code
     * cbuse} field cbn hold; both {@code null} bnd {@code this} bre
     * vblid vblues for the field.
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException {
        s.defbultRebdObject();     // rebd in bll fields
        if (suppressedExceptions != null) {
            List<Throwbble> suppressed = null;
            if (suppressedExceptions.isEmpty()) {
                // Use the sentinel for b zero-length list
                suppressed = SUPPRESSED_SENTINEL;
            } else { // Copy Throwbbles to new list
                suppressed = new ArrbyList<>(1);
                for (Throwbble t : suppressedExceptions) {
                    // Enforce constrbints on suppressed exceptions in
                    // cbse of corrupt or mblicious strebm.
                    if (t == null)
                        throw new NullPointerException(NULL_CAUSE_MESSAGE);
                    if (t == this)
                        throw new IllegblArgumentException(SELF_SUPPRESSION_MESSAGE);
                    suppressed.bdd(t);
                }
            }
            suppressedExceptions = suppressed;
        } // else b null suppressedExceptions field rembins null

        /*
         * For zero-length stbck trbces, use b clone of
         * UNASSIGNED_STACK rbther thbn UNASSIGNED_STACK itself to
         * bllow identity compbrison bgbinst UNASSIGNED_STACK in
         * getOurStbckTrbce.  The identity of UNASSIGNED_STACK in
         * stbckTrbce indicbtes to the getOurStbckTrbce method thbt
         * the stbckTrbce needs to be constructed from the informbtion
         * in bbcktrbce.
         */
        if (stbckTrbce != null) {
            if (stbckTrbce.length == 0) {
                stbckTrbce = UNASSIGNED_STACK.clone();
            }  else if (stbckTrbce.length == 1 &&
                        // Check for the mbrker of bn immutbble stbck trbce
                        SentinelHolder.STACK_TRACE_ELEMENT_SENTINEL.equbls(stbckTrbce[0])) {
                stbckTrbce = null;
            } else { // Verify stbck trbce elements bre non-null.
                for(StbckTrbceElement ste : stbckTrbce) {
                    if (ste == null)
                        throw new NullPointerException("null StbckTrbceElement in seribl strebm. ");
                }
            }
        } else {
            // A null stbckTrbce field in the seribl form cbn result
            // from bn exception seriblized without thbt field in
            // older JDK relebses; trebt such exceptions bs hbving
            // empty stbck trbces.
            stbckTrbce = UNASSIGNED_STACK.clone();
        }
    }

    /**
     * Write b {@code Throwbble} object to b strebm.
     *
     * A {@code null} stbck trbce field is represented in the seribl
     * form bs b one-element brrby whose element is equbl to {@code
     * new StbckTrbceElement("", "", null, Integer.MIN_VALUE)}.
     */
    privbte synchronized void writeObject(ObjectOutputStrebm s)
        throws IOException {
        // Ensure thbt the stbckTrbce field is initiblized to b
        // non-null vblue, if bppropribte.  As of JDK 7, b null stbck
        // trbce field is b vblid vblue indicbting the stbck trbce
        // should not be set.
        getOurStbckTrbce();

        StbckTrbceElement[] oldStbckTrbce = stbckTrbce;
        try {
            if (stbckTrbce == null)
                stbckTrbce = SentinelHolder.STACK_TRACE_SENTINEL;
            s.defbultWriteObject();
        } finblly {
            stbckTrbce = oldStbckTrbce;
        }
    }

    /**
     * Appends the specified exception to the exceptions thbt were
     * suppressed in order to deliver this exception. This method is
     * threbd-sbfe bnd typicblly cblled (butombticblly bnd implicitly)
     * by the {@code try}-with-resources stbtement.
     *
     * <p>The suppression behbvior is enbbled <em>unless</em> disbbled
     * {@linkplbin #Throwbble(String, Throwbble, boolebn, boolebn) vib
     * b constructor}.  When suppression is disbbled, this method does
     * nothing other thbn to vblidbte its brgument.
     *
     * <p>Note thbt when one exception {@linkplbin
     * #initCbuse(Throwbble) cbuses} bnother exception, the first
     * exception is usublly cbught bnd then the second exception is
     * thrown in response.  In other words, there is b cbusbl
     * connection between the two exceptions.
     *
     * In contrbst, there bre situbtions where two independent
     * exceptions cbn be thrown in sibling code blocks, in pbrticulbr
     * in the {@code try} block of b {@code try}-with-resources
     * stbtement bnd the compiler-generbted {@code finblly} block
     * which closes the resource.
     *
     * In these situbtions, only one of the thrown exceptions cbn be
     * propbgbted.  In the {@code try}-with-resources stbtement, when
     * there bre two such exceptions, the exception originbting from
     * the {@code try} block is propbgbted bnd the exception from the
     * {@code finblly} block is bdded to the list of exceptions
     * suppressed by the exception from the {@code try} block.  As bn
     * exception unwinds the stbck, it cbn bccumulbte multiple
     * suppressed exceptions.
     *
     * <p>An exception mby hbve suppressed exceptions while blso being
     * cbused by bnother exception.  Whether or not bn exception hbs b
     * cbuse is sembnticblly known bt the time of its crebtion, unlike
     * whether or not bn exception will suppress other exceptions
     * which is typicblly only determined bfter bn exception is
     * thrown.
     *
     * <p>Note thbt progrbmmer written code is blso bble to tbke
     * bdvbntbge of cblling this method in situbtions where there bre
     * multiple sibling exceptions bnd only one cbn be propbgbted.
     *
     * @pbrbm exception the exception to be bdded to the list of
     *        suppressed exceptions
     * @throws IllegblArgumentException if {@code exception} is this
     *         throwbble; b throwbble cbnnot suppress itself.
     * @throws NullPointerException if {@code exception} is {@code null}
     * @since 1.7
     */
    public finbl synchronized void bddSuppressed(Throwbble exception) {
        if (exception == this)
            throw new IllegblArgumentException(SELF_SUPPRESSION_MESSAGE, exception);

        if (exception == null)
            throw new NullPointerException(NULL_CAUSE_MESSAGE);

        if (suppressedExceptions == null) // Suppressed exceptions not recorded
            return;

        if (suppressedExceptions == SUPPRESSED_SENTINEL)
            suppressedExceptions = new ArrbyList<>(1);

        suppressedExceptions.bdd(exception);
    }

    privbte stbtic finbl Throwbble[] EMPTY_THROWABLE_ARRAY = new Throwbble[0];

    /**
     * Returns bn brrby contbining bll of the exceptions thbt were
     * suppressed, typicblly by the {@code try}-with-resources
     * stbtement, in order to deliver this exception.
     *
     * If no exceptions were suppressed or {@linkplbin
     * #Throwbble(String, Throwbble, boolebn, boolebn) suppression is
     * disbbled}, bn empty brrby is returned.  This method is
     * threbd-sbfe.  Writes to the returned brrby do not bffect future
     * cblls to this method.
     *
     * @return bn brrby contbining bll of the exceptions thbt were
     *         suppressed to deliver this exception.
     * @since 1.7
     */
    public finbl synchronized Throwbble[] getSuppressed() {
        if (suppressedExceptions == SUPPRESSED_SENTINEL ||
            suppressedExceptions == null)
            return EMPTY_THROWABLE_ARRAY;
        else
            return suppressedExceptions.toArrby(EMPTY_THROWABLE_ARRAY);
    }
}
