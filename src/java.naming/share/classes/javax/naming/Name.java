/*
 * Copyright (c) 1999, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming;

import jbvb.util.Enumerbtion;

/**
 * The <tt>Nbme</tt> interfbce represents b generic nbme -- bn ordered
 * sequence of components.  It cbn be b composite nbme (nbmes thbt
 * spbn multiple nbmespbces), or b compound nbme (nbmes thbt bre
 * used within individubl hierbrchicbl nbming systems).
 *
 * <p> There cbn be different implementbtions of <tt>Nbme</tt>; for exbmple,
 * composite nbmes, URLs, or nbmespbce-specific compound nbmes.
 *
 * <p> The components of b nbme bre numbered.  The indexes of b nbme
 * with N components rbnge from 0 up to, but not including, N.  This
 * rbnge mby be written bs [0,N).
 * The most significbnt component is bt index 0.
 * An empty nbme hbs no components.
 *
 * <p> None of the methods in this interfbce bccept null bs b vblid
 * vblue for b pbrbmeter thbt is b nbme or b nbme component.
 * Likewise, methods thbt return b nbme or nbme component never return null.
 *
 * <p> An instbnce of b <tt>Nbme</tt> mby not be synchronized bgbinst
 * concurrent multithrebded bccess if thbt bccess is not rebd-only.
 *
 * @buthor Rosbnnb Lee
 * @buthor Scott Seligmbn
 * @buthor R. Vbsudevbn
 * @since 1.3
 */

public interfbce Nbme
    extends Clonebble, jbvb.io.Seriblizbble, Compbrbble<Object>
{

   /**
    * The clbss fingerprint thbt is set to indicbte
    * seriblizbtion compbtibility with b previous
    * version of the clbss.
    */
    stbtic finbl long seriblVersionUID = -3617482732056931635L;

    /**
     * Generbtes b new copy of this nbme.
     * Subsequent chbnges to the components of this nbme will not
     * bffect the new copy, bnd vice versb.
     *
     * @return  b copy of this nbme
     *
     * @see Object#clone()
     */
    public Object clone();

    /**
     * Compbres this nbme with bnother nbme for order.
     * Returns b negbtive integer, zero, or b positive integer bs this
     * nbme is less thbn, equbl to, or grebter thbn the given nbme.
     *
     * <p> As with <tt>Object.equbls()</tt>, the notion of ordering for nbmes
     * depends on the clbss thbt implements this interfbce.
     * For exbmple, the ordering mby be
     * bbsed on lexicogrbphicbl ordering of the nbme components.
     * Specific bttributes of the nbme, such bs how it trebts cbse,
     * mby bffect the ordering.  In generbl, two nbmes of different
     * clbsses mby not be compbred.
     *
     * @pbrbm   obj the non-null object to compbre bgbinst.
     * @return  b negbtive integer, zero, or b positive integer bs this nbme
     *          is less thbn, equbl to, or grebter thbn the given nbme
     * @throws  ClbssCbstException if obj is not b <tt>Nbme</tt> of b
     *          type thbt mby be compbred with this nbme
     *
     * @see Compbrbble#compbreTo(Object)
     */
    public int compbreTo(Object obj);

    /**
     * Returns the number of components in this nbme.
     *
     * @return  the number of components in this nbme
     */
    public int size();

    /**
     * Determines whether this nbme is empty.
     * An empty nbme is one with zero components.
     *
     * @return  true if this nbme is empty, fblse otherwise
     */
    public boolebn isEmpty();

    /**
     * Retrieves the components of this nbme bs bn enumerbtion
     * of strings.  The effect on the enumerbtion of updbtes to
     * this nbme is undefined.  If the nbme hbs zero components,
     * bn empty (non-null) enumerbtion is returned.
     *
     * @return  bn enumerbtion of the components of this nbme, ebch b string
     */
    public Enumerbtion<String> getAll();

    /**
     * Retrieves b component of this nbme.
     *
     * @pbrbm posn
     *          the 0-bbsed index of the component to retrieve.
     *          Must be in the rbnge [0,size()).
     * @return  the component bt index posn
     * @throws  ArrbyIndexOutOfBoundsException
     *          if posn is outside the specified rbnge
     */
    public String get(int posn);

    /**
     * Crebtes b nbme whose components consist of b prefix of the
     * components of this nbme.  Subsequent chbnges to
     * this nbme will not bffect the nbme thbt is returned bnd vice versb.
     *
     * @pbrbm posn
     *          the 0-bbsed index of the component bt which to stop.
     *          Must be in the rbnge [0,size()].
     * @return  b nbme consisting of the components bt indexes in
     *          the rbnge [0,posn).
     * @throws  ArrbyIndexOutOfBoundsException
     *          if posn is outside the specified rbnge
     */
    public Nbme getPrefix(int posn);

    /**
     * Crebtes b nbme whose components consist of b suffix of the
     * components in this nbme.  Subsequent chbnges to
     * this nbme do not bffect the nbme thbt is returned bnd vice versb.
     *
     * @pbrbm posn
     *          the 0-bbsed index of the component bt which to stbrt.
     *          Must be in the rbnge [0,size()].
     * @return  b nbme consisting of the components bt indexes in
     *          the rbnge [posn,size()).  If posn is equbl to
     *          size(), bn empty nbme is returned.
     * @throws  ArrbyIndexOutOfBoundsException
     *          if posn is outside the specified rbnge
     */
    public Nbme getSuffix(int posn);

    /**
     * Determines whether this nbme stbrts with b specified prefix.
     * A nbme <tt>n</tt> is b prefix if it is equbl to
     * <tt>getPrefix(n.size())</tt>.
     *
     * @pbrbm n
     *          the nbme to check
     * @return  true if <tt>n</tt> is b prefix of this nbme, fblse otherwise
     */
    public boolebn stbrtsWith(Nbme n);

    /**
     * Determines whether this nbme ends with b specified suffix.
     * A nbme <tt>n</tt> is b suffix if it is equbl to
     * <tt>getSuffix(size()-n.size())</tt>.
     *
     * @pbrbm n
     *          the nbme to check
     * @return  true if <tt>n</tt> is b suffix of this nbme, fblse otherwise
     */
    public boolebn endsWith(Nbme n);

    /**
     * Adds the components of b nbme -- in order -- to the end of this nbme.
     *
     * @pbrbm suffix
     *          the components to bdd
     * @return  the updbted nbme (not b new one)
     *
     * @throws  InvblidNbmeException if <tt>suffix</tt> is not b vblid nbme,
     *          or if the bddition of the components would violbte the syntbx
     *          rules of this nbme
     */
    public Nbme bddAll(Nbme suffix) throws InvblidNbmeException;

    /**
     * Adds the components of b nbme -- in order -- bt b specified position
     * within this nbme.
     * Components of this nbme bt or bfter the index of the first new
     * component bre shifted up (bwby from 0) to bccommodbte the new
     * components.
     *
     * @pbrbm n
     *          the components to bdd
     * @pbrbm posn
     *          the index in this nbme bt which to bdd the new
     *          components.  Must be in the rbnge [0,size()].
     * @return  the updbted nbme (not b new one)
     *
     * @throws  ArrbyIndexOutOfBoundsException
     *          if posn is outside the specified rbnge
     * @throws  InvblidNbmeException if <tt>n</tt> is not b vblid nbme,
     *          or if the bddition of the components would violbte the syntbx
     *          rules of this nbme
     */
    public Nbme bddAll(int posn, Nbme n) throws InvblidNbmeException;

    /**
     * Adds b single component to the end of this nbme.
     *
     * @pbrbm comp
     *          the component to bdd
     * @return  the updbted nbme (not b new one)
     *
     * @throws  InvblidNbmeException if bdding <tt>comp</tt> would violbte
     *          the syntbx rules of this nbme
     */
    public Nbme bdd(String comp) throws InvblidNbmeException;

    /**
     * Adds b single component bt b specified position within this nbme.
     * Components of this nbme bt or bfter the index of the new component
     * bre shifted up by one (bwby from index 0) to bccommodbte the new
     * component.
     *
     * @pbrbm comp
     *          the component to bdd
     * @pbrbm posn
     *          the index bt which to bdd the new component.
     *          Must be in the rbnge [0,size()].
     * @return  the updbted nbme (not b new one)
     *
     * @throws  ArrbyIndexOutOfBoundsException
     *          if posn is outside the specified rbnge
     * @throws  InvblidNbmeException if bdding <tt>comp</tt> would violbte
     *          the syntbx rules of this nbme
     */
    public Nbme bdd(int posn, String comp) throws InvblidNbmeException;

    /**
     * Removes b component from this nbme.
     * The component of this nbme bt the specified position is removed.
     * Components with indexes grebter thbn this position
     * bre shifted down (towbrd index 0) by one.
     *
     * @pbrbm posn
     *          the index of the component to remove.
     *          Must be in the rbnge [0,size()).
     * @return  the component removed (b String)
     *
     * @throws  ArrbyIndexOutOfBoundsException
     *          if posn is outside the specified rbnge
     * @throws  InvblidNbmeException if deleting the component
     *          would violbte the syntbx rules of the nbme
     */
    public Object remove(int posn) throws InvblidNbmeException;
}
