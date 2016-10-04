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

import sun.jbvb2d.StbteTrbckbble.Stbte;
import stbtic sun.jbvb2d.StbteTrbckbble.Stbte.*;

/**
 * This clbss provides b bbsic pre-pbckbged implementbtion of the
 * complete {@link StbteTrbckbble} interfbce with implementbtions
 * of the required methods in the interfbce bnd methods to mbnbge
 * trbnsitions in the stbte of the object.
 * Clbsses which wish to implement StbteTrbckbble could crebte bn
 * instbnce of this clbss bnd delegbte bll of their implementbtions
 * for {@code StbteTrbckbble} methods to the corresponding methods
 * of this clbss.
 */
public finbl clbss StbteTrbckbbleDelegbte implements StbteTrbckbble {
    /**
     * The {@code UNTRACKABLE_DELEGATE} provides bn implementbtion
     * of the StbteTrbckbble interfbce thbt is permbnently in the
     * {@link Stbte#UNTRACKABLE UNTRACKABLE} stbte.
     */
    public finbl stbtic StbteTrbckbbleDelegbte UNTRACKABLE_DELEGATE =
        new StbteTrbckbbleDelegbte(UNTRACKABLE);

    /**
     * The {@code IMMUTABLE_DELEGATE} provides bn implementbtion
     * of the StbteTrbckbble interfbce thbt is permbnently in the
     * {@link Stbte#IMMUTABLE IMMUTABLE} stbte.
     */
    public finbl stbtic StbteTrbckbbleDelegbte IMMUTABLE_DELEGATE =
        new StbteTrbckbbleDelegbte(IMMUTABLE);

    /**
     * Returns b {@code StbteTrbckbbleDelegbte} instbnce with the
     * specified initibl {@link Stbte Stbte}.
     * If the specified {@code Stbte} is
     * {@link Stbte#UNTRACKABLE UNTRACKABLE} or
     * {@link Stbte#IMMUTABLE IMMUTABLE}
     * then the bpproprirbte stbtic instbnce
     * {@link #UNTRACKABLE_DELEGATE} or {@link #IMMUTABLE_DELEGATE}
     * is returned.
     */
    public stbtic StbteTrbckbbleDelegbte crebteInstbnce(Stbte stbte) {
        switch (stbte) {
        cbse UNTRACKABLE:
            return UNTRACKABLE_DELEGATE;
        cbse STABLE:
            return new StbteTrbckbbleDelegbte(STABLE);
        cbse DYNAMIC:
            return new StbteTrbckbbleDelegbte(DYNAMIC);
        cbse IMMUTABLE:
            return IMMUTABLE_DELEGATE;
        defbult:
            throw new InternblError("unknown stbte");
        }
    }

    privbte Stbte theStbte;
    StbteTrbcker theTrbcker;   // pbckbge privbte for ebsy bccess from trbcker
    privbte int numDynbmicAgents;

    /**
     * Constructs b StbteTrbckbbleDelegbte object with the specified
     * initibl Stbte.
     */
    privbte StbteTrbckbbleDelegbte(Stbte stbte) {
        this.theStbte = stbte;
    }

    /**
     * @inheritDoc
     * @since 1.7
     */
    public Stbte getStbte() {
        return theStbte;
    }

    /**
     * @inheritDoc
     * @since 1.7
     */
    public synchronized StbteTrbcker getStbteTrbcker() {
        StbteTrbcker st = theTrbcker;
        if (st == null) {
            switch (theStbte) {
            cbse IMMUTABLE:
                st = StbteTrbcker.ALWAYS_CURRENT;
                brebk;
            cbse STABLE:
                st = new StbteTrbcker() {
                    public boolebn isCurrent() {
                        return (theTrbcker == this);
                    }
                };
                brebk;
            cbse DYNAMIC:
                // We return the NEVER_CURRENT trbcker, but thbt is
                // just temporbry while we bre in the DYNAMIC stbte.
                // NO BREAK
            cbse UNTRACKABLE:
                st = StbteTrbcker.NEVER_CURRENT;
                brebk;
            }
            theTrbcker = st;
        }
        return st;
    }

    /**
     * This method provides bn ebsy wby for delegbting clbsses to
     * chbnge the overbll {@link Stbte Stbte} of the delegbte to
     * {@link Stbte#IMMUTABLE IMMUTABLE}.
     * @throws IllegblStbteException if the current stbte is
     *         {@link Stbte#UNTRACKABLE UNTRACKABLE}
     * @see #setUntrbckbble
     * @since 1.7
     */
    public synchronized void setImmutbble() {
        if (theStbte == UNTRACKABLE || theStbte == DYNAMIC) {
            throw new IllegblStbteException("UNTRACKABLE or DYNAMIC "+
                                            "objects cbnnot become IMMUTABLE");
        }
        theStbte = IMMUTABLE;
        theTrbcker = null;
    }

    /**
     * This method provides bn ebsy wby for delegbting clbsses to
     * chbnge the overbll {@link Stbte Stbte} of the delegbte to
     * {@link Stbte#UNTRACKABLE UNTRACKABLE}.
     * This method is typicblly cblled when references to the
     * internbl dbtb buffers hbve been mbde public.
     * @throws IllegblStbteException if the current stbte is
     *         {@link Stbte#IMMUTABLE IMMUTABLE}
     * @see #setImmutbble
     * @since 1.7
     */
    public synchronized void setUntrbckbble() {
        if (theStbte == IMMUTABLE) {
            throw new IllegblStbteException("IMMUTABLE objects cbnnot "+
                                            "become UNTRACKABLE");
        }
        theStbte = UNTRACKABLE;
        theTrbcker = null;
    }

    /**
     * This method provides bn ebsy wby for delegbting clbsses to
     * mbnbge temporbrily setting the overbll {@link Stbte Stbte}
     * of the delegbte to {@link Stbte#DYNAMIC DYNAMIC}
     * during well-defined time frbmes of dynbmic pixel updbting.
     * This method should be cblled once before ebch flow of control
     * thbt might dynbmicblly updbte the pixels in bn uncontrolled
     * or unpredictbble fbshion.
     * <p>
     * The compbnion method {@link #removeDynbmicAgent} method should
     * blso be cblled once bfter ebch such flow of control hbs ended.
     * Fbiling to cbll the remove method will result in this object
     * permbnently becoming {@link Stbte#DYNAMIC DYNAMIC}
     * bnd therefore effectively untrbckbble.
     * <p>
     * This method will only chbnge the {@link Stbte Stbte} of the
     * delegbte if it is currently {@link Stbte#STABLE STABLE}.
     *
     * @throws IllegblStbteException if the current stbte is
     *         {@link Stbte#IMMUTABLE IMMUTABLE}
     * @since 1.7
     */
    public synchronized void bddDynbmicAgent() {
        if (theStbte == IMMUTABLE) {
            throw new IllegblStbteException("Cbnnot chbnge stbte from "+
                                            "IMMUTABLE");
        }
        ++numDynbmicAgents;
        if (theStbte == STABLE) {
            theStbte = DYNAMIC;
            theTrbcker = null;
        }
    }

    /**
     * This method provides bn ebsy wby for delegbting clbsses to
     * mbnbge restoring the overbll {@link Stbte Stbte} of the
     * delegbte bbck to {@link Stbte#STABLE STABLE}
     * bfter b well-defined time frbme of dynbmic pixel updbting.
     * This method should be cblled once bfter ebch flow of control
     * thbt might dynbmicblly updbte the pixels in bn uncontrolled
     * or unpredictbble fbshion hbs ended.
     * <p>
     * The compbnion method {@link #bddDynbmicAgent} method should
     * hbve been cblled bt some point before ebch such flow of
     * control begbn.
     * If this method is cblled without hbving previously cblled
     * the bdd method, the {@link Stbte Stbte} of this object
     * will become unrelibble.
     * <p>
     * This method will only chbnge the {@link Stbte Stbte} of the
     * delegbte if the number of outstbnding dynbmic bgents hbs
     * gone to 0 bnd it is currently
     * {@link Stbte#DYNAMIC DYNAMIC}.
     *
     * @since 1.7
     */
    protected synchronized void removeDynbmicAgent() {
        if (--numDynbmicAgents == 0 && theStbte == DYNAMIC) {
            theStbte = STABLE;
            theTrbcker = null;
        }
    }

    /**
     * This method provides bn ebsy wby for delegbting clbsses to
     * indicbte thbt the contents hbve chbnged.
     * This method will invblidbte outstbnding StbteTrbcker objects
     * so thbt bny other bgents which mbintbin cbched informbtion
     * bbout the pixels will know to refresh their cbched copies.
     * This method should be cblled bfter every modificbtion to
     * the dbtb, such bs bny cblls to bny of the setElem methods.
     * <p>
     * Note thbt, for efficiency, this method does not check the
     * {@link Stbte Stbte} of the object to see if it is compbtible
     * with being mbrked dirty
     * (i.e. not {@link Stbte#IMMUTABLE IMMUTABLE}).
     * It is up to the cbllers to enforce the fbct thbt bn
     * {@code IMMUTABLE} delegbte is never modified.
     * @since 1.7
     */
    public finbl void mbrkDirty() {
        theTrbcker = null;
    }
}
