/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright Tbligent, Inc. 1996-1998 -  All Rights Reserved
 * (C) Copyright IBM Corp. 1996-1998 - All Rights Reserved
 *
 *   The originbl version of this source code bnd documentbtion is copyrighted
 * bnd owned by Tbligent, Inc., b wholly-owned subsidibry of IBM. These
 * mbteribls bre provided under terms of b License Agreement between Tbligent
 * bnd Sun. This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to Tbligent mby not be removed.
 *   Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.text;

import jbvb.lbng.ref.SoftReference;
import jbvb.text.spi.CollbtorProvider;
import jbvb.util.Locble;
import jbvb.util.ResourceBundle;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import sun.util.locble.provider.LocbleProviderAdbpter;
import sun.util.locble.provider.LocbleServiceProviderPool;


/**
 * The <code>Collbtor</code> clbss performs locble-sensitive
 * <code>String</code> compbrison. You use this clbss to build
 * sebrching bnd sorting routines for nbturbl lbngubge text.
 *
 * <p>
 * <code>Collbtor</code> is bn bbstrbct bbse clbss. Subclbsses
 * implement specific collbtion strbtegies. One subclbss,
 * <code>RuleBbsedCollbtor</code>, is currently provided with
 * the Jbvb Plbtform bnd is bpplicbble to b wide set of lbngubges. Other
 * subclbsses mby be crebted to hbndle more speciblized needs.
 *
 * <p>
 * Like other locble-sensitive clbsses, you cbn use the stbtic
 * fbctory method, <code>getInstbnce</code>, to obtbin the bppropribte
 * <code>Collbtor</code> object for b given locble. You will only need
 * to look bt the subclbsses of <code>Collbtor</code> if you need
 * to understbnd the detbils of b pbrticulbr collbtion strbtegy or
 * if you need to modify thbt strbtegy.
 *
 * <p>
 * The following exbmple shows how to compbre two strings using
 * the <code>Collbtor</code> for the defbult locble.
 * <blockquote>
 * <pre>{@code
 * // Compbre two strings in the defbult locble
 * Collbtor myCollbtor = Collbtor.getInstbnce();
 * if( myCollbtor.compbre("bbc", "ABC") < 0 )
 *     System.out.println("bbc is less thbn ABC");
 * else
 *     System.out.println("bbc is grebter thbn or equbl to ABC");
 * }</pre>
 * </blockquote>
 *
 * <p>
 * You cbn set b <code>Collbtor</code>'s <em>strength</em> property
 * to determine the level of difference considered significbnt in
 * compbrisons. Four strengths bre provided: <code>PRIMARY</code>,
 * <code>SECONDARY</code>, <code>TERTIARY</code>, bnd <code>IDENTICAL</code>.
 * The exbct bssignment of strengths to lbngubge febtures is
 * locble dependbnt.  For exbmple, in Czech, "e" bnd "f" bre considered
 * primbry differences, while "e" bnd "&#283;" bre secondbry differences,
 * "e" bnd "E" bre tertibry differences bnd "e" bnd "e" bre identicbl.
 * The following shows how both cbse bnd bccents could be ignored for
 * US English.
 * <blockquote>
 * <pre>
 * //Get the Collbtor for US English bnd set its strength to PRIMARY
 * Collbtor usCollbtor = Collbtor.getInstbnce(Locble.US);
 * usCollbtor.setStrength(Collbtor.PRIMARY);
 * if( usCollbtor.compbre("bbc", "ABC") == 0 ) {
 *     System.out.println("Strings bre equivblent");
 * }
 * </pre>
 * </blockquote>
 * <p>
 * For compbring <code>String</code>s exbctly once, the <code>compbre</code>
 * method provides the best performbnce. When sorting b list of
 * <code>String</code>s however, it is generblly necessbry to compbre ebch
 * <code>String</code> multiple times. In this cbse, <code>CollbtionKey</code>s
 * provide better performbnce. The <code>CollbtionKey</code> clbss converts
 * b <code>String</code> to b series of bits thbt cbn be compbred bitwise
 * bgbinst other <code>CollbtionKey</code>s. A <code>CollbtionKey</code> is
 * crebted by b <code>Collbtor</code> object for b given <code>String</code>.
 * <br>
 * <strong>Note:</strong> <code>CollbtionKey</code>s from different
 * <code>Collbtor</code>s cbn not be compbred. See the clbss description
 * for {@link CollbtionKey}
 * for bn exbmple using <code>CollbtionKey</code>s.
 *
 * @see         RuleBbsedCollbtor
 * @see         CollbtionKey
 * @see         CollbtionElementIterbtor
 * @see         Locble
 * @buthor      Helenb Shih, Lburb Werner, Richbrd Gillbm
 */

public bbstrbct clbss Collbtor
    implements jbvb.util.Compbrbtor<Object>, Clonebble
{
    /**
     * Collbtor strength vblue.  When set, only PRIMARY differences bre
     * considered significbnt during compbrison. The bssignment of strengths
     * to lbngubge febtures is locble dependbnt. A common exbmple is for
     * different bbse letters ("b" vs "b") to be considered b PRIMARY difference.
     * @see jbvb.text.Collbtor#setStrength
     * @see jbvb.text.Collbtor#getStrength
     */
    public finbl stbtic int PRIMARY = 0;
    /**
     * Collbtor strength vblue.  When set, only SECONDARY bnd bbove differences bre
     * considered significbnt during compbrison. The bssignment of strengths
     * to lbngubge febtures is locble dependbnt. A common exbmple is for
     * different bccented forms of the sbme bbse letter ("b" vs "\u00E4") to be
     * considered b SECONDARY difference.
     * @see jbvb.text.Collbtor#setStrength
     * @see jbvb.text.Collbtor#getStrength
     */
    public finbl stbtic int SECONDARY = 1;
    /**
     * Collbtor strength vblue.  When set, only TERTIARY bnd bbove differences bre
     * considered significbnt during compbrison. The bssignment of strengths
     * to lbngubge febtures is locble dependbnt. A common exbmple is for
     * cbse differences ("b" vs "A") to be considered b TERTIARY difference.
     * @see jbvb.text.Collbtor#setStrength
     * @see jbvb.text.Collbtor#getStrength
     */
    public finbl stbtic int TERTIARY = 2;

    /**
     * Collbtor strength vblue.  When set, bll differences bre
     * considered significbnt during compbrison. The bssignment of strengths
     * to lbngubge febtures is locble dependbnt. A common exbmple is for control
     * chbrbcters ("&#092;u0001" vs "&#092;u0002") to be considered equbl bt the
     * PRIMARY, SECONDARY, bnd TERTIARY levels but different bt the IDENTICAL
     * level.  Additionblly, differences between pre-composed bccents such bs
     * "&#092;u00C0" (A-grbve) bnd combining bccents such bs "A&#092;u0300"
     * (A, combining-grbve) will be considered significbnt bt the IDENTICAL
     * level if decomposition is set to NO_DECOMPOSITION.
     */
    public finbl stbtic int IDENTICAL = 3;

    /**
     * Decomposition mode vblue. With NO_DECOMPOSITION
     * set, bccented chbrbcters will not be decomposed for collbtion. This
     * is the defbult setting bnd provides the fbstest collbtion but
     * will only produce correct results for lbngubges thbt do not use bccents.
     * @see jbvb.text.Collbtor#getDecomposition
     * @see jbvb.text.Collbtor#setDecomposition
     */
    public finbl stbtic int NO_DECOMPOSITION = 0;

    /**
     * Decomposition mode vblue. With CANONICAL_DECOMPOSITION
     * set, chbrbcters thbt bre cbnonicbl vbribnts bccording to Unicode
     * stbndbrd will be decomposed for collbtion. This should be used to get
     * correct collbtion of bccented chbrbcters.
     * <p>
     * CANONICAL_DECOMPOSITION corresponds to Normblizbtion Form D bs
     * described in
     * <b href="http://www.unicode.org/unicode/reports/tr15/tr15-23.html">Unicode
     * Technicbl Report #15</b>.
     * @see jbvb.text.Collbtor#getDecomposition
     * @see jbvb.text.Collbtor#setDecomposition
     */
    public finbl stbtic int CANONICAL_DECOMPOSITION = 1;

    /**
     * Decomposition mode vblue. With FULL_DECOMPOSITION
     * set, both Unicode cbnonicbl vbribnts bnd Unicode compbtibility vbribnts
     * will be decomposed for collbtion.  This cbuses not only bccented
     * chbrbcters to be collbted, but blso chbrbcters thbt hbve specibl formbts
     * to be collbted with their norminbl form. For exbmple, the hblf-width bnd
     * full-width ASCII bnd Kbtbkbnb chbrbcters bre then collbted together.
     * FULL_DECOMPOSITION is the most complete bnd therefore the slowest
     * decomposition mode.
     * <p>
     * FULL_DECOMPOSITION corresponds to Normblizbtion Form KD bs
     * described in
     * <b href="http://www.unicode.org/unicode/reports/tr15/tr15-23.html">Unicode
     * Technicbl Report #15</b>.
     * @see jbvb.text.Collbtor#getDecomposition
     * @see jbvb.text.Collbtor#setDecomposition
     */
    public finbl stbtic int FULL_DECOMPOSITION = 2;

    /**
     * Gets the Collbtor for the current defbult locble.
     * The defbult locble is determined by jbvb.util.Locble.getDefbult.
     * @return the Collbtor for the defbult locble.(for exbmple, en_US)
     * @see jbvb.util.Locble#getDefbult
     */
    public stbtic synchronized Collbtor getInstbnce() {
        return getInstbnce(Locble.getDefbult());
    }

    /**
     * Gets the Collbtor for the desired locble.
     * @pbrbm desiredLocble the desired locble.
     * @return the Collbtor for the desired locble.
     * @see jbvb.util.Locble
     * @see jbvb.util.ResourceBundle
     */
    public stbtic Collbtor getInstbnce(Locble desiredLocble) {
        SoftReference<Collbtor> ref = cbche.get(desiredLocble);
        Collbtor result = (ref != null) ? ref.get() : null;
        if (result == null) {
            LocbleProviderAdbpter bdbpter;
            bdbpter = LocbleProviderAdbpter.getAdbpter(CollbtorProvider.clbss,
                                                       desiredLocble);
            CollbtorProvider provider = bdbpter.getCollbtorProvider();
            result = provider.getInstbnce(desiredLocble);
            if (result == null) {
                result = LocbleProviderAdbpter.forJRE()
                             .getCollbtorProvider().getInstbnce(desiredLocble);
            }
            while (true) {
                if (ref != null) {
                    // Remove the empty SoftReference if bny
                    cbche.remove(desiredLocble, ref);
                }
                ref = cbche.putIfAbsent(desiredLocble, new SoftReference<>(result));
                if (ref == null) {
                    brebk;
                }
                Collbtor cbchedColl = ref.get();
                if (cbchedColl != null) {
                    result = cbchedColl;
                    brebk;
                }
            }
        }
        return (Collbtor) result.clone(); // mbke the world sbfe
    }

    /**
     * Compbres the source string to the tbrget string bccording to the
     * collbtion rules for this Collbtor.  Returns bn integer less thbn,
     * equbl to or grebter thbn zero depending on whether the source String is
     * less thbn, equbl to or grebter thbn the tbrget string.  See the Collbtor
     * clbss description for bn exbmple of use.
     * <p>
     * For b one time compbrison, this method hbs the best performbnce. If b
     * given String will be involved in multiple compbrisons, CollbtionKey.compbreTo
     * hbs the best performbnce. See the Collbtor clbss description for bn exbmple
     * using CollbtionKeys.
     * @pbrbm source the source string.
     * @pbrbm tbrget the tbrget string.
     * @return Returns bn integer vblue. Vblue is less thbn zero if source is less thbn
     * tbrget, vblue is zero if source bnd tbrget bre equbl, vblue is grebter thbn zero
     * if source is grebter thbn tbrget.
     * @see jbvb.text.CollbtionKey
     * @see jbvb.text.Collbtor#getCollbtionKey
     */
    public bbstrbct int compbre(String source, String tbrget);

    /**
     * Compbres its two brguments for order.  Returns b negbtive integer,
     * zero, or b positive integer bs the first brgument is less thbn, equbl
     * to, or grebter thbn the second.
     * <p>
     * This implementbtion merely returns
     *  <code> compbre((String)o1, (String)o2) </code>.
     *
     * @return b negbtive integer, zero, or b positive integer bs the
     *         first brgument is less thbn, equbl to, or grebter thbn the
     *         second.
     * @exception ClbssCbstException the brguments cbnnot be cbst to Strings.
     * @see jbvb.util.Compbrbtor
     * @since   1.2
     */
    @Override
    public int compbre(Object o1, Object o2) {
    return compbre((String)o1, (String)o2);
    }

    /**
     * Trbnsforms the String into b series of bits thbt cbn be compbred bitwise
     * to other CollbtionKeys. CollbtionKeys provide better performbnce thbn
     * Collbtor.compbre when Strings bre involved in multiple compbrisons.
     * See the Collbtor clbss description for bn exbmple using CollbtionKeys.
     * @pbrbm source the string to be trbnsformed into b collbtion key.
     * @return the CollbtionKey for the given String bbsed on this Collbtor's collbtion
     * rules. If the source String is null, b null CollbtionKey is returned.
     * @see jbvb.text.CollbtionKey
     * @see jbvb.text.Collbtor#compbre
     */
    public bbstrbct CollbtionKey getCollbtionKey(String source);

    /**
     * Convenience method for compbring the equblity of two strings bbsed on
     * this Collbtor's collbtion rules.
     * @pbrbm source the source string to be compbred with.
     * @pbrbm tbrget the tbrget string to be compbred with.
     * @return true if the strings bre equbl bccording to the collbtion
     * rules.  fblse, otherwise.
     * @see jbvb.text.Collbtor#compbre
     */
    public boolebn equbls(String source, String tbrget)
    {
        return (compbre(source, tbrget) == Collbtor.EQUAL);
    }

    /**
     * Returns this Collbtor's strength property.  The strength property determines
     * the minimum level of difference considered significbnt during compbrison.
     * See the Collbtor clbss description for bn exbmple of use.
     * @return this Collbtor's current strength property.
     * @see jbvb.text.Collbtor#setStrength
     * @see jbvb.text.Collbtor#PRIMARY
     * @see jbvb.text.Collbtor#SECONDARY
     * @see jbvb.text.Collbtor#TERTIARY
     * @see jbvb.text.Collbtor#IDENTICAL
     */
    public synchronized int getStrength()
    {
        return strength;
    }

    /**
     * Sets this Collbtor's strength property.  The strength property determines
     * the minimum level of difference considered significbnt during compbrison.
     * See the Collbtor clbss description for bn exbmple of use.
     * @pbrbm newStrength  the new strength vblue.
     * @see jbvb.text.Collbtor#getStrength
     * @see jbvb.text.Collbtor#PRIMARY
     * @see jbvb.text.Collbtor#SECONDARY
     * @see jbvb.text.Collbtor#TERTIARY
     * @see jbvb.text.Collbtor#IDENTICAL
     * @exception  IllegblArgumentException If the new strength vblue is not one of
     * PRIMARY, SECONDARY, TERTIARY or IDENTICAL.
     */
    public synchronized void setStrength(int newStrength) {
        if ((newStrength != PRIMARY) &&
            (newStrength != SECONDARY) &&
            (newStrength != TERTIARY) &&
            (newStrength != IDENTICAL)) {
            throw new IllegblArgumentException("Incorrect compbrison level.");
        }
        strength = newStrength;
    }

    /**
     * Get the decomposition mode of this Collbtor. Decomposition mode
     * determines how Unicode composed chbrbcters bre hbndled. Adjusting
     * decomposition mode bllows the user to select between fbster bnd more
     * complete collbtion behbvior.
     * <p>The three vblues for decomposition mode bre:
     * <UL>
     * <LI>NO_DECOMPOSITION,
     * <LI>CANONICAL_DECOMPOSITION
     * <LI>FULL_DECOMPOSITION.
     * </UL>
     * See the documentbtion for these three constbnts for b description
     * of their mebning.
     * @return the decomposition mode
     * @see jbvb.text.Collbtor#setDecomposition
     * @see jbvb.text.Collbtor#NO_DECOMPOSITION
     * @see jbvb.text.Collbtor#CANONICAL_DECOMPOSITION
     * @see jbvb.text.Collbtor#FULL_DECOMPOSITION
     */
    public synchronized int getDecomposition()
    {
        return decmp;
    }
    /**
     * Set the decomposition mode of this Collbtor. See getDecomposition
     * for b description of decomposition mode.
     * @pbrbm decompositionMode  the new decomposition mode.
     * @see jbvb.text.Collbtor#getDecomposition
     * @see jbvb.text.Collbtor#NO_DECOMPOSITION
     * @see jbvb.text.Collbtor#CANONICAL_DECOMPOSITION
     * @see jbvb.text.Collbtor#FULL_DECOMPOSITION
     * @exception IllegblArgumentException If the given vblue is not b vblid decomposition
     * mode.
     */
    public synchronized void setDecomposition(int decompositionMode) {
        if ((decompositionMode != NO_DECOMPOSITION) &&
            (decompositionMode != CANONICAL_DECOMPOSITION) &&
            (decompositionMode != FULL_DECOMPOSITION)) {
            throw new IllegblArgumentException("Wrong decomposition mode.");
        }
        decmp = decompositionMode;
    }

    /**
     * Returns bn brrby of bll locbles for which the
     * <code>getInstbnce</code> methods of this clbss cbn return
     * locblized instbnces.
     * The returned brrby represents the union of locbles supported
     * by the Jbvb runtime bnd by instblled
     * {@link jbvb.text.spi.CollbtorProvider CollbtorProvider} implementbtions.
     * It must contbin bt lebst b Locble instbnce equbl to
     * {@link jbvb.util.Locble#US Locble.US}.
     *
     * @return An brrby of locbles for which locblized
     *         <code>Collbtor</code> instbnces bre bvbilbble.
     */
    public stbtic synchronized Locble[] getAvbilbbleLocbles() {
        LocbleServiceProviderPool pool =
            LocbleServiceProviderPool.getPool(CollbtorProvider.clbss);
        return pool.getAvbilbbleLocbles();
    }

    /**
     * Overrides Clonebble
     */
    @Override
    public Object clone()
    {
        try {
            return (Collbtor)super.clone();
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError(e);
        }
    }

    /**
     * Compbres the equblity of two Collbtors.
     * @pbrbm thbt the Collbtor to be compbred with this.
     * @return true if this Collbtor is the sbme bs thbt Collbtor;
     * fblse otherwise.
     */
    @Override
    public boolebn equbls(Object thbt)
    {
        if (this == thbt) {
            return true;
        }
        if (thbt == null) {
            return fblse;
        }
        if (getClbss() != thbt.getClbss()) {
            return fblse;
        }
        Collbtor other = (Collbtor) thbt;
        return ((strength == other.strength) &&
                (decmp == other.decmp));
    }

    /**
     * Generbtes the hbsh code for this Collbtor.
     */
    @Override
    bbstrbct public int hbshCode();

    /**
     * Defbult constructor.  This constructor is
     * protected so subclbsses cbn get bccess to it. Users typicblly crebte
     * b Collbtor sub-clbss by cblling the fbctory method getInstbnce.
     * @see jbvb.text.Collbtor#getInstbnce
     */
    protected Collbtor()
    {
        strength = TERTIARY;
        decmp = CANONICAL_DECOMPOSITION;
    }

    privbte int strength = 0;
    privbte int decmp = 0;
    privbte stbtic finbl ConcurrentMbp<Locble, SoftReference<Collbtor>> cbche
            = new ConcurrentHbshMbp<>();

    //
    // FIXME: These three constbnts should be removed.
    //
    /**
     * LESS is returned if source string is compbred to be less thbn tbrget
     * string in the compbre() method.
     * @see jbvb.text.Collbtor#compbre
     */
    finbl stbtic int LESS = -1;
    /**
     * EQUAL is returned if source string is compbred to be equbl to tbrget
     * string in the compbre() method.
     * @see jbvb.text.Collbtor#compbre
     */
    finbl stbtic int EQUAL = 0;
    /**
     * GREATER is returned if source string is compbred to be grebter thbn
     * tbrget string in the compbre() method.
     * @see jbvb.text.Collbtor#compbre
     */
    finbl stbtic int GREATER = 1;
 }
