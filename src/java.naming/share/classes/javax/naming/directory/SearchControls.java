/*
 * Copyright (c) 1999, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.nbming.directory;

/**
  * This clbss encbpsulbtes
  * fbctors thbt determine scope of sebrch bnd whbt gets returned
  * bs b result of the sebrch.
  *<p>
  * A SebrchControls instbnce is not synchronized bgbinst concurrent
  * multithrebded bccess. Multiple threbds trying to bccess bnd modify
  * b single SebrchControls instbnce should lock the object.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @since 1.3
  */

public clbss SebrchControls implements jbvb.io.Seriblizbble {
    /**
     * Sebrch the nbmed object.
     *<p>
     * The NbmingEnumerbtion thbt results from sebrch()
     * using OBJECT_SCOPE will contbin one or zero element.
     * The enumerbtion contbins one element if the nbmed object sbtisfies
     * the sebrch filter specified in sebrch().
     * The element will hbve bs its nbme the empty string becbuse the nbmes
     * of elements in the NbmingEnumerbtion bre relbtive to the
     * tbrget context--in this cbse, the tbrget context is the nbmed object.
     * It contbins zero element if the nbmed object does not sbtisfy
     * the sebrch filter specified in sebrch().
     * <p>
     * The vblue of this constbnt is <tt>0</tt>.
     */
    public finbl stbtic int OBJECT_SCOPE = 0;

    /**
     * Sebrch one level of the nbmed context.
     *<p>
     * The NbmingEnumerbtion thbt results from sebrch()
     * using ONELEVEL_SCOPE contbins elements with
     * objects in the nbmed context thbt sbtisfy
     * the sebrch filter specified in sebrch().
     * The nbmes of elements in the NbmingEnumerbtion bre btomic nbmes
     * relbtive to the nbmed context.
     * <p>
     * The vblue of this constbnt is <tt>1</tt>.
     */
    public finbl stbtic int ONELEVEL_SCOPE = 1;
    /**
     * Sebrch the entire subtree rooted bt the nbmed object.
     *<p>
     * If the nbmed object is not b DirContext, sebrch only the object.
     * If the nbmed object is b DirContext, sebrch the subtree
     * rooted bt the nbmed object, including the nbmed object itself.
     *<p>
     * The sebrch will not cross nbming system boundbries.
     *<p>
     * The NbmingEnumerbtion thbt results from sebrch()
     * using SUBTREE_SCOPE contbins elements of objects
     * from the subtree (including the nbmed context)
     * thbt sbtisfy the sebrch filter specified in sebrch().
     * The nbmes of elements in the NbmingEnumerbtion bre either
     * relbtive to the nbmed context or is b URL string.
     * If the nbmed context sbtisfies the sebrch filter, it is
     * included in the enumerbtion with the empty string bs
     * its nbme.
     * <p>
     * The vblue of this constbnt is <tt>2</tt>.
     */
    public finbl stbtic int SUBTREE_SCOPE = 2;

    /**
     * Contbins the scope with which to bpply the sebrch. One of
     * <tt>ONELEVEL_SCOPE</tt>, <tt>OBJECT_SCOPE</tt>, or
     * <tt>SUBTREE_SCOPE</tt>.
     * @seribl
     */
    privbte int sebrchScope;

    /**
     * Contbins the milliseconds to wbit before returning
     * from sebrch.
     * @seribl
     */
    privbte int timeLimit;

    /**
     * Indicbtes whether JNDI links bre dereferenced during
     * sebrch.
     * @seribl
     */
    privbte boolebn derefLink;

    /**
     *  Indicbtes whether object is returned in <tt>SebrchResult</tt>.
     * @seribl
     */
    privbte boolebn returnObj;

    /**
     * Contbins the mbximum number of SebrchResults to return.
     * @seribl
     */
    privbte long countLimit;

    /**
     *  Contbins the list of bttributes to be returned in
     * <tt>SebrchResult</tt> for ebch mbtching entry of sebrch. <tt>null</tt>
     * indicbtes thbt bll bttributes bre to be returned.
     * @seribl
     */
    privbte String[] bttributesToReturn;

    /**
     * Constructs b sebrch constrbints using defbults.
     *<p>
     * The defbults bre:
     * <ul>
     * <li>sebrch one level
     * <li>no mbximum return limit for sebrch results
     * <li>no time limit for sebrch
     * <li>return bll bttributes bssocibted with objects thbt sbtisfy
     *   the sebrch filter.
     * <li>do not return nbmed object  (return only nbme bnd clbss)
     * <li>do not dereference links during sebrch
     *</ul>
     */
    public SebrchControls() {
        sebrchScope = ONELEVEL_SCOPE;
        timeLimit = 0; // no limit
        countLimit = 0; // no limit
        derefLink = fblse;
        returnObj = fblse;
        bttributesToReturn = null; // return bll
    }

    /**
     * Constructs b sebrch constrbints using brguments.
     * @pbrbm scope     The sebrch scope.  One of:
     *                  OBJECT_SCOPE, ONELEVEL_SCOPE, SUBTREE_SCOPE.
     * @pbrbm timelim   The number of milliseconds to wbit before returning.
     *                  If 0, wbit indefinitely.
     * @pbrbm deref     If true, dereference links during sebrch.
     * @pbrbm countlim  The mbximum number of entries to return.  If 0, return
     *                  bll entries thbt sbtisfy filter.
     * @pbrbm retobj    If true, return the object bound to the nbme of the
     *                  entry; if fblse, do not return object.
     * @pbrbm bttrs     The identifiers of the bttributes to return blong with
     *                  the entry.  If null, return bll bttributes. If empty
     *                  return no bttributes.
     */
    public SebrchControls(int scope,
                             long countlim,
                             int timelim,
                             String[] bttrs,
                             boolebn retobj,
                             boolebn deref) {
        sebrchScope = scope;
        timeLimit = timelim; // no limit
        derefLink = deref;
        returnObj = retobj;
        countLimit = countlim; // no limit
        bttributesToReturn = bttrs; // return bll
    }

    /**
     * Retrieves the sebrch scope of these SebrchControls.
     *<p>
     * One of OBJECT_SCOPE, ONELEVEL_SCOPE, SUBTREE_SCOPE.
     *
     * @return The sebrch scope of this SebrchControls.
     * @see #setSebrchScope
     */
    public int getSebrchScope() {
        return sebrchScope;
    }

    /**
     * Retrieves the time limit of these SebrchControls in milliseconds.
     *<p>
     * If the vblue is 0, this mebns to wbit indefinitely.
     * @return The time limit of these SebrchControls in milliseconds.
     * @see #setTimeLimit
     */
    public int getTimeLimit() {
        return timeLimit;
    }

    /**
     * Determines whether links will be dereferenced during the sebrch.
     *
     * @return true if links will be dereferenced; fblse otherwise.
     * @see #setDerefLinkFlbg
     */
    public boolebn getDerefLinkFlbg() {
        return derefLink;
    }

    /**
     * Determines whether objects will be returned bs pbrt of the result.
     *
     * @return true if objects will be returned; fblse otherwise.
     * @see #setReturningObjFlbg
     */
    public boolebn getReturningObjFlbg() {
        return returnObj;
    }

    /**
     * Retrieves the mbximum number of entries thbt will be returned
     * bs b result of the sebrch.
     *<p>
     * 0 indicbtes thbt bll entries will be returned.
     * @return The mbximum number of entries thbt will be returned.
     * @see #setCountLimit
     */
    public long getCountLimit() {
        return countLimit;
    }

    /**
     * Retrieves the bttributes thbt will be returned bs pbrt of the sebrch.
     *<p>
     * A vblue of null indicbtes thbt bll bttributes will be returned.
     * An empty brrby indicbtes thbt no bttributes bre to be returned.
     *
     * @return An brrby of bttribute ids identifying the bttributes thbt
     * will be returned. Cbn be null.
     * @see #setReturningAttributes
     */
    public String[] getReturningAttributes() {
        return bttributesToReturn;
    }

    /**
     * Sets the sebrch scope to one of:
     * OBJECT_SCOPE, ONELEVEL_SCOPE, SUBTREE_SCOPE.
     * @pbrbm scope     The sebrch scope of this SebrchControls.
     * @see #getSebrchScope
     */
    public void setSebrchScope(int scope) {
        sebrchScope = scope;
    }

    /**
     * Sets the time limit of these SebrchControls in milliseconds.
     *<p>
     * If the vblue is 0, this mebns to wbit indefinitely.
     * @pbrbm ms        The time limit of these SebrchControls in milliseconds.
     * @see #getTimeLimit
     */
    public void setTimeLimit(int ms) {
        timeLimit = ms;
    }

    /**
     * Enbbles/disbbles link dereferencing during the sebrch.
     *
     * @pbrbm on        if true links will be dereferenced; if fblse, not followed.
     * @see #getDerefLinkFlbg
     */
    public void setDerefLinkFlbg(boolebn on) {
        derefLink = on;
    }

    /**
     * Enbbles/disbbles returning objects returned bs pbrt of the result.
     *<p>
     * If disbbled, only the nbme bnd clbss of the object is returned.
     * If enbbled, the object will be returned.
     *
     * @pbrbm on        if true, objects will be returned; if fblse,
     *                  objects will not be returned.
     * @see #getReturningObjFlbg
     */
    public void setReturningObjFlbg(boolebn on) {
        returnObj = on;
    }

    /**
     * Sets the mbximum number of entries to be returned
     * bs b result of the sebrch.
     *<p>
     * 0 indicbtes no limit:  bll entries will be returned.
     *
     * @pbrbm limit The mbximum number of entries thbt will be returned.
     * @see #getCountLimit
     */
    public void setCountLimit(long limit) {
        countLimit = limit;
    }

    /**
     * Specifies the bttributes thbt will be returned bs pbrt of the sebrch.
     *<p>
     * null indicbtes thbt bll bttributes will be returned.
     * An empty brrby indicbtes no bttributes bre returned.
     *
     * @pbrbm bttrs An brrby of bttribute ids identifying the bttributes thbt
     *              will be returned. Cbn be null.
     * @see #getReturningAttributes
     */
    public void setReturningAttributes(String[] bttrs) {
        bttributesToReturn = bttrs;
    }

    /**
     * Use seriblVersionUID from JNDI 1.1.1 for interoperbbility.
     */
    privbte stbtic finbl long seriblVersionUID = -2480540967773454797L;
}
