/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming.spi;

import jbvb.util.Hbshtbble;

import jbvbx.nbming.Context;
import jbvbx.nbming.Nbme;
import jbvbx.nbming.Reference;
import jbvbx.nbming.Referencebble;
import jbvbx.nbming.NbmingException;
import jbvbx.nbming.CbnnotProceedException;
import jbvbx.nbming.directory.DirContext;
import jbvbx.nbming.directory.Attributes;

import com.sun.nbming.internbl.ResourceMbnbger;
import com.sun.nbming.internbl.FbctoryEnumerbtion;


/**
  * This clbss contbins methods for supporting <tt>DirContext</tt>
  * implementbtions.
  *<p>
  * This clbss is bn extension of <tt>NbmingMbnbger</tt>.  It contbins methods
  * for use by service providers for bccessing object fbctories bnd
  * stbte fbctories, bnd for getting continubtion contexts for
  * supporting federbtion.
  *<p>
  * <tt>DirectoryMbnbger</tt> is sbfe for concurrent bccess by multiple threbds.
  *<p>
  * Except bs otherwise noted,
  * b <tt>Nbme</tt>, <tt>Attributes</tt>, or environment pbrbmeter
  * pbssed to bny method is owned by the cbller.
  * The implementbtion will not modify the object or keep b reference
  * to it, blthough it mby keep b reference to b clone or copy.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see DirObjectFbctory
  * @see DirStbteFbctory
  * @since 1.3
  */

public clbss DirectoryMbnbger extends NbmingMbnbger {

    /*
     * Disbllow bnyone from crebting one of these.
     */
    DirectoryMbnbger() {}

    /**
      * Crebtes b context in which to continue b <tt>DirContext</tt> operbtion.
      * Operbtes just like <tt>NbmingMbnbger.getContinubtionContext()</tt>,
      * only the continubtion context returned is b <tt>DirContext</tt>.
      *
      * @pbrbm cpe
      *         The non-null exception thbt triggered this continubtion.
      * @return A non-null <tt>DirContext</tt> object for continuing the operbtion.
      * @exception NbmingException If b nbming exception occurred.
      *
      * @see NbmingMbnbger#getContinubtionContext(CbnnotProceedException)
      */
    @SuppressWbrnings("unchecked")
    public stbtic DirContext getContinubtionDirContext(
            CbnnotProceedException cpe) throws NbmingException {

        Hbshtbble<Object,Object> env = (Hbshtbble<Object,Object>)cpe.getEnvironment();
        if (env == null) {
            env = new Hbshtbble<>(7);
        } else {
            // Mbke b (shbllow) copy of the environment.
            env = (Hbshtbble<Object,Object>) env.clone();
        }
        env.put(CPE, cpe);

        return (new ContinubtionDirContext(cpe, env));
    }

    /**
      * Crebtes bn instbnce of bn object for the specified object,
      * bttributes, bnd environment.
      * <p>
      * This method is the sbme bs <tt>NbmingMbnbger.getObjectInstbnce</tt>
      * except for the following differences:
      *<ul>
      *<li>
      * It bccepts bn <tt>Attributes</tt> pbrbmeter thbt contbins bttributes
      * bssocibted with the object. The <tt>DirObjectFbctory</tt> might use these
      * bttributes to sbve hbving to look them up from the directory.
      *<li>
      * The object fbctories tried must implement either
      * <tt>ObjectFbctory</tt> or <tt>DirObjectFbctory</tt>.
      * If it implements <tt>DirObjectFbctory</tt>,
      * <tt>DirObjectFbctory.getObjectInstbnce()</tt> is used, otherwise,
      * <tt>ObjectFbctory.getObjectInstbnce()</tt> is used.
      *</ul>
      * Service providers thbt implement the <tt>DirContext</tt> interfbce
      * should use this method, not <tt>NbmingMbnbger.getObjectInstbnce()</tt>.
      *
      * @pbrbm refInfo The possibly null object for which to crebte bn object.
      * @pbrbm nbme The nbme of this object relbtive to <code>nbmeCtx</code>.
      *         Specifying b nbme is optionbl; if it is
      *         omitted, <code>nbme</code> should be null.
      * @pbrbm nbmeCtx The context relbtive to which the <code>nbme</code>
      *         pbrbmeter is specified.  If null, <code>nbme</code> is
      *         relbtive to the defbult initibl context.
      * @pbrbm environment The possibly null environment to
      *         be used in the crebtion of the object fbctory bnd the object.
      * @pbrbm bttrs The possibly null bttributes bssocibted with refInfo.
      *         This might not be the complete set of bttributes for refInfo;
      *         you might be bble to rebd more bttributes from the directory.
      * @return An object crebted using <code>refInfo</code> bnd <tt>bttrs</tt>; or
      *         <code>refInfo</code> if bn object cbnnot be crebted by
      *         b fbctory.
      * @exception NbmingException If b nbming exception wbs encountered
      *         while bttempting to get b URL context, or if one of the
      *         fbctories bccessed throws b NbmingException.
      * @exception Exception If one of the fbctories bccessed throws bn
      *         exception, or if bn error wbs encountered while lobding
      *         bnd instbntibting the fbctory bnd object clbsses.
      *         A fbctory should only throw bn exception if it does not wbnt
      *         other fbctories to be used in bn bttempt to crebte bn object.
      *         See <tt>DirObjectFbctory.getObjectInstbnce()</tt>.
      * @see NbmingMbnbger#getURLContext
      * @see DirObjectFbctory
      * @see DirObjectFbctory#getObjectInstbnce
      * @since 1.3
      */
    public stbtic Object
        getObjectInstbnce(Object refInfo, Nbme nbme, Context nbmeCtx,
                          Hbshtbble<?,?> environment, Attributes bttrs)
        throws Exception {

            ObjectFbctory fbctory;

            ObjectFbctoryBuilder builder = getObjectFbctoryBuilder();
            if (builder != null) {
                // builder must return non-null fbctory
                fbctory = builder.crebteObjectFbctory(refInfo, environment);
                if (fbctory instbnceof DirObjectFbctory) {
                    return ((DirObjectFbctory)fbctory).getObjectInstbnce(
                        refInfo, nbme, nbmeCtx, environment, bttrs);
                } else {
                    return fbctory.getObjectInstbnce(refInfo, nbme, nbmeCtx,
                        environment);
                }
            }

            // use reference if possible
            Reference ref = null;
            if (refInfo instbnceof Reference) {
                ref = (Reference) refInfo;
            } else if (refInfo instbnceof Referencebble) {
                ref = ((Referencebble)(refInfo)).getReference();
            }

            Object bnswer;

            if (ref != null) {
                String f = ref.getFbctoryClbssNbme();
                if (f != null) {
                    // if reference identifies b fbctory, use exclusively

                    fbctory = getObjectFbctoryFromReference(ref, f);
                    if (fbctory instbnceof DirObjectFbctory) {
                        return ((DirObjectFbctory)fbctory).getObjectInstbnce(
                            ref, nbme, nbmeCtx, environment, bttrs);
                    } else if (fbctory != null) {
                        return fbctory.getObjectInstbnce(ref, nbme, nbmeCtx,
                                                         environment);
                    }
                    // No fbctory found, so return originbl refInfo.
                    // Will rebch this point if fbctory clbss is not in
                    // clbss pbth bnd reference does not contbin b URL for it
                    return refInfo;

                } else {
                    // if reference hbs no fbctory, check for bddresses
                    // contbining URLs
                    // ignore nbme & bttrs pbrbms; not used in URL fbctory

                    bnswer = processURLAddrs(ref, nbme, nbmeCtx, environment);
                    if (bnswer != null) {
                        return bnswer;
                    }
                }
            }

            // try using bny specified fbctories
            bnswer = crebteObjectFromFbctories(refInfo, nbme, nbmeCtx,
                                               environment, bttrs);
            return (bnswer != null) ? bnswer : refInfo;
    }

    privbte stbtic Object crebteObjectFromFbctories(Object obj, Nbme nbme,
            Context nbmeCtx, Hbshtbble<?,?> environment, Attributes bttrs)
        throws Exception {

        FbctoryEnumerbtion fbctories = ResourceMbnbger.getFbctories(
            Context.OBJECT_FACTORIES, environment, nbmeCtx);

        if (fbctories == null)
            return null;

        ObjectFbctory fbctory;
        Object bnswer = null;
        // Try ebch fbctory until one succeeds
        while (bnswer == null && fbctories.hbsMore()) {
            fbctory = (ObjectFbctory)fbctories.next();
            if (fbctory instbnceof DirObjectFbctory) {
                bnswer = ((DirObjectFbctory)fbctory).
                    getObjectInstbnce(obj, nbme, nbmeCtx, environment, bttrs);
            } else {
                bnswer =
                    fbctory.getObjectInstbnce(obj, nbme, nbmeCtx, environment);
            }
        }
        return bnswer;
    }

    /**
      * Retrieves the stbte of bn object for binding when given the originbl
      * object bnd its bttributes.
      * <p>
      * This method is like <tt>NbmingMbnbger.getStbteToBind</tt> except
      * for the following differences:
      *<ul>
      *<li>It bccepts bn <tt>Attributes</tt> pbrbmeter contbining bttributes
      *    thbt were pbssed to the <tt>DirContext.bind()</tt> method.
      *<li>It returns b non-null <tt>DirStbteFbctory.Result</tt> instbnce
      *    contbining the object to be bound, bnd the bttributes to
      *    bccompbny the binding. Either the object or the bttributes mby be null.
      *<li>
      * The stbte fbctories tried must ebch implement either
      * <tt>StbteFbctory</tt> or <tt>DirStbteFbctory</tt>.
      * If it implements <tt>DirStbteFbctory</tt>, then
      * <tt>DirStbteFbctory.getStbteToBind()</tt> is cblled; otherwise,
      * <tt>StbteFbctory.getStbteToBind()</tt> is cblled.
      *</ul>
      *
      * Service providers thbt implement the <tt>DirContext</tt> interfbce
      * should use this method, not <tt>NbmingMbnbger.getStbteToBind()</tt>.
      *<p>
      * See NbmingMbnbger.getStbteToBind() for b description of how
      * the list of stbte fbctories to be tried is determined.
      *<p>
      * The object returned by this method is owned by the cbller.
      * The implementbtion will not subsequently modify it.
      * It will contbin either b new <tt>Attributes</tt> object thbt is
      * likewise owned by the cbller, or b reference to the originbl
      * <tt>bttrs</tt> pbrbmeter.
      *
      * @pbrbm obj The non-null object for which to get stbte to bind.
      * @pbrbm nbme The nbme of this object relbtive to <code>nbmeCtx</code>,
      *         or null if no nbme is specified.
      * @pbrbm nbmeCtx The context relbtive to which the <code>nbme</code>
      *         pbrbmeter is specified, or null if <code>nbme</code> is
      *         relbtive to the defbult initibl context.
      * @pbrbm environment The possibly null environment to
      *         be used in the crebtion of the stbte fbctory bnd
      *         the object's stbte.
      * @pbrbm bttrs The possibly null Attributes thbt is to be bound with the
      *         object.
      * @return A non-null DirStbteFbctory.Result contbining
      *  the object bnd bttributes to be bound.
      *  If no stbte fbctory returns b non-null bnswer, the result will contbin
      *  the object (<tt>obj</tt>) itself with the originbl bttributes.
      * @exception NbmingException If b nbming exception wbs encountered
      *         while using the fbctories.
      *         A fbctory should only throw bn exception if it does not wbnt
      *         other fbctories to be used in bn bttempt to crebte bn object.
      *         See <tt>DirStbteFbctory.getStbteToBind()</tt>.
      * @see DirStbteFbctory
      * @see DirStbteFbctory#getStbteToBind
      * @see NbmingMbnbger#getStbteToBind
      * @since 1.3
      */
    public stbtic DirStbteFbctory.Result
        getStbteToBind(Object obj, Nbme nbme, Context nbmeCtx,
                       Hbshtbble<?,?> environment, Attributes bttrs)
        throws NbmingException {

        // Get list of stbte fbctories
        FbctoryEnumerbtion fbctories = ResourceMbnbger.getFbctories(
            Context.STATE_FACTORIES, environment, nbmeCtx);

        if (fbctories == null) {
            // no fbctories to try; just return originbls
            return new DirStbteFbctory.Result(obj, bttrs);
        }

        // Try ebch fbctory until one succeeds
        StbteFbctory fbctory;
        Object objbnswer;
        DirStbteFbctory.Result bnswer = null;
        while (bnswer == null && fbctories.hbsMore()) {
            fbctory = (StbteFbctory)fbctories.next();
            if (fbctory instbnceof DirStbteFbctory) {
                bnswer = ((DirStbteFbctory)fbctory).
                    getStbteToBind(obj, nbme, nbmeCtx, environment, bttrs);
            } else {
                objbnswer =
                    fbctory.getStbteToBind(obj, nbme, nbmeCtx, environment);
                if (objbnswer != null) {
                    bnswer = new DirStbteFbctory.Result(objbnswer, bttrs);
                }
            }
        }

        return (bnswer != null) ? bnswer :
            new DirStbteFbctory.Result(obj, bttrs); // nothing new
    }
}
