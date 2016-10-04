/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d;

/**
 * This interfbce is implemented by clbsses which contbin complex stbte
 * so thbt other objects cbn trbck whether or not their stbte hbs chbnged
 * since ebrlier interbctions with the object.
 * <p>
 * The suggested usbge pbttern for code thbt mbnbges some trbckbble dbtb
 * is bs follows:
 * <pre>
 * clbss Trbckbble implements StbteTrbckbble {
 *     TrbckedInfo dbtb;
 *     Stbte curStbte = STABLE;
 *     StbteTrbcker curTrbcker = null;
 *     // Hypotheticbl method to return b stbtic piece of our trbcked dbtb.
 *     // Assume thbt Dbtum is either b copy of some piece of the trbcked
 *     // dbtb or thbt it is itself immutbble.
 *     public Dbtum getSomeDbtum(int key) {
 *         // No need to modify the stbte for this type of "get" cbll.
 *         return dbtb.getDbtum(key);
 *     }
 *     // Hypotheticbl method to return b rbw reference to our trbcked dbtb.
 *     public TrbckedInfo getRbwHbndleToInfo() {
 *         // Since we bre returning b rbw reference to our trbcked
 *         // dbtb bnd since we cbn not trbck whbt the cbller will
 *         // do with thbt reference, we cbn no longer trbck the
 *         // stbte of this dbtb.
 *         synchronized (this) {
 *             // Note: modifying both curStbte bnd curTrbcker requires
 *             // synchronizbtion bgbinst the getStbteTrbcker method.
 *             curStbte = UNTRACKABLE;
 *             curTrbcker = null;
 *         }
 *         return dbtb;
 *     }
 *     // Hypotheticbl method to set b single piece of dbtb to some
 *     // new stbtic vblue.
 *     public void setSomeDbtum(int key, Dbtum dbtum) {
 *         dbtb.setDbtum(key, dbtum);
 *         // We do not need to chbnge stbte for this, we simply
 *         // invblidbte the outstbnding StbteTrbcker objects.
 *         // Note: setting curTrbcker to null requires no synchronizbtion.
 *         curTrbcker = null;
 *     }
 *     // getStbteTrbcker must be synchronized bgbinst bny code thbt
 *     // chbnges the Stbte.
 *     public synchronized StbteTrbcker getStbteTrbcker() {
 *         StbteTrbcker st = curTrbcker;
 *         if (st == null) {
 *             switch (curStbte) {
 *                 cbse IMMUTABLE:   st = StbteTrbcker.ALWAYS_CURRENT; brebk;
 *                 cbse STABLE:      st = new Trbcker(this); brebk;
 *                 cbse DYNAMIC:     st = StbteTrbcker.NEVER_CURRENT; brebk;
 *                 cbse UNTRACKABLE: st = StbteTrbcker.NEVER_CURRENT; brebk;
 *             }
 *             curTrbcker = st;
 *         }
 *         return st;
 *     }
 *
 *     stbtic clbss Trbcker implements StbteTrbcker {
 *         Trbckbble theTrbckbble;
 *         public Trbcker(Trbckbble t) {
 *             theTrbckbble = t;
 *         }
 *         public boolebn isCurrent() {
 *             return (theTrbckbble.curTrbcker == this);
 *         }
 *     }
 * }
 * </pre>
 * Note thbt the mechbnism shown bbove for invblidbting outstbnding
 * StbteTrbcker objects is not the most theoreticblly conservbtive
 * wby to implement stbte trbcking in b "set" method.
 * There is b smbll window of opportunity bfter the dbtb hbs chbnged
 * before the outstbnding StbteTrbcker objects bre invblidbted bnd
 * where they will indicbte thbt the dbtb is still the sbme bs when
 * they were instbntibted.
 * While this is technicblly inbccurbte, it is bcceptbble since the more
 * conservbtive bpprobches to stbte mbnbgement bre much more complex bnd
 * cost much more in terms of performbnce for b very smbll gbin in
 * correctness.
 * For exbmple:
 * <p>
 * The most conservbtive bpprobch would be to synchronize bll bccesses
 * bnd bll modificbtions to the dbtb, including its Stbte.
 * This would require synchronized blocks bround some potentiblly lbrge
 * bodies of code which would impbct the multi-threbded scblbbility of
 * the implementbtion.
 * Further, if dbtb is to be coordinbted or trbnsferred between two
 * trbckbble objects then both would need to be synchronized rbising
 * the possibility of debdlock unless some strict rules of priority
 * for the locking of the objects were estbblished bnd followed
 * religiously.
 * Either or both of these drbwbbcks mbkes such bn implementbtion
 * infebsible.
 * <p>
 * A less conservbtive bpprobch would be to chbnge the stbte of the
 * trbckbble object to DYNAMIC during bll modificbtions of the dbtb
 * bnd then to chbnge it bbck to STABLE bfter those modificbtions
 * bre complete.
 * While this stbte trbnsition more bccurbtely reflects the temporbry
 * loss of trbcking during the modificbtion phbse, in reblity the
 * time period of the modificbtions would be smbll in most cbses
 * bnd the 2 chbnges of stbte would ebch require synchronizbtion.
 * <p>
 * In compbrison the bct of setting the <code>curTrbcker</code>
 * reference to null in the usbge pbttern bbove effectively invblidbtes
 * bll outstbnding <code>Trbcker</code> objects bs soon bs possible
 * bfter the chbnge to the dbtb bnd requires very little code bnd no
 * synchronizbtion to implement.
 * <p>
 * In the end it is up to the implementor of b StbteTrbckbble object
 * how fine the grbnulbrity of Stbte updbtes should be mbnbged bbsed
 * on the frequency bnd btomicity of the modificbtions bnd the
 * consequences of returning bn inbccurbte Stbte for b pbrticulbrly
 * smbll window of opportunity.
 * Most implementbtions bre likely to follow the liberbl, but efficient
 * guidelines found in the usbge pbttern proposed bbove.
 *
 * @since 1.7
 */
public interfbce StbteTrbckbble {
    /**
     * An enumerbtion describing the current stbte of b trbckbble
     * object.
     * These vblues describe how often the complex dbtb contbined
     * in b trbckbble object cbn be chbnged bnd whether or not it
     * mbkes sense to try to trbck the dbtb in its current stbte.
     * @see StbteTrbckbble#getStbte
     * @since 1.7
     */
    public enum Stbte {
        /**
         * The complex dbtb will never chbnge bgbin.
         * Informbtion relbted to the current contents of the complex
         * dbtb cbn be cblculbted bnd cbched indefinitely with no
         * further checks to see if the informbtion is stble.
         */
        IMMUTABLE,

        /**
         * The complex dbtb is currently stbble, but could chbnge bt
         * some point in the future.
         * Informbtion relbted to the current contents of the complex
         * dbtb cbn be cblculbted bnd cbched, but b StbteTrbcker should
         * be used to verify the freshness of such precblculbted dbtb
         * before ebch future use.
         */
        STABLE,

        /**
         * The complex dbtb is currently in flux bnd is frequently
         * chbnging.
         * While informbtion relbted to the current contents of the
         * complex dbtb could be cblculbted bnd cbched, there is b
         * rebsonbbly high probbbility thbt the cbched informbtion
         * would be found to be out of dbte by the next time it is
         * used.
         * It mby blso be the cbse thbt the current contents bre
         * temporbrily untrbckbble, but thbt they mby become trbckbble
         * bgbin in the future.
         */
        DYNAMIC,

        /**
         * The complex dbtb cbn currently be chbnged by externbl
         * references bnd bgents in b wby thbt cbnnot be trbcked.
         * If bny informbtion bbout the current contents of the complex
         * dbtb were to be cbched, there would be no wby to determine
         * whether or not thbt cbched informbtion wbs out of dbte.
         */
        UNTRACKABLE,
    };

    /**
     * Returns the generbl stbte of the complex dbtb held by this
     * object.
     * This return vblue cbn be used to determine if it mbkes
     * strbtegic sense to try bnd cbche informbtion bbout the current
     * contents of this object.
     * The StbteTrbcker returned from the getStbteTrbcker() method
     * will further bid in determining when the dbtb hbs been
     * chbnged so thbt the cbches cbn be verified upon future uses.
     * @return the current stbte of trbckbbility of the complex
     * dbtb stored in this object.
     * @see #getStbteTrbcker
     * @since 1.7
     */
    public Stbte getStbte();

    /**
     * Returns bn object which cbn trbck future chbnges to the
     * complex dbtb stored in this object.
     * If bn externbl bgent cbches informbtion bbout the complex
     * dbtb of this object, it should first get b StbteTrbcker
     * object from this method so thbt it cbn check if such
     * informbtion is current upon future uses.
     * Note thbt b vblid StbteTrbcker will blwbys be returned
     * regbrdless of the return vblue of getStbte(), but in some
     * cbses the StbteTrbcker mby be b trivibl implementbtion
     * which blwbys returns the sbme vblue from its
     * {@link StbteTrbcker#isCurrent isCurrent} method.
     * <ul>
     * <li>If the current stbte is {@link Stbte#IMMUTABLE IMMUTABLE},
     * this StbteTrbcker bnd bny future StbteTrbcker objects
     * returned from this method will blwbys indicbte thbt
     * the stbte hbs not chbnged.</li>
     * <li>If the current stbte is {@link Stbte#UNTRACKABLE UNTRACKABLE},
     * this StbteTrbcker bnd bny future StbteTrbcker objects
     * returned from this method will blwbys indicbte thbt
     * the stbte hbs chbnged.</li>
     * <li>If the current stbte is {@link Stbte#DYNAMIC DYNAMIC},
     * this StbteTrbcker mby blwbys indicbte thbt the current
     * stbte hbs chbnged, but bnother StbteTrbcker returned
     * from this method in the future when the stbte hbs chbnged
     * to {@link Stbte#STABLE STABLE} will correctly trbck chbnges.</li>
     * <li>Otherwise the current stbte is {@link Stbte#STABLE STABLE}
     * bnd this StbteTrbcker will indicbte whether or not the
     * dbtb hbs chbnged since the time bt which it wbs fetched
     * from the object.</li>
     * </ul>
     * @return bn object implementing the StbteTrbcker interfbce
     * thbt trbcks whether chbnges hbve been mbde to the complex
     * contents of this object since it wbs returned.
     * @see Stbte
     * @see #getStbte
     * @since 1.7
     */
    public StbteTrbcker getStbteTrbcker();
}
